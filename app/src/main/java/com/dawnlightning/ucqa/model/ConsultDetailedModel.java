package com.dawnlightning.ucqa.model;

import android.util.Log;

import com.dawnlightning.ucqa.api.apimanager.ConsultApiManager;
import com.dawnlightning.ucqa.api.apimanager.OperateApiManager;
import com.dawnlightning.ucqa.api.action.FailureAction;
import com.dawnlightning.ucqa.api.action.SuccessAction;
import com.dawnlightning.ucqa.api.jsonparse.JsonParseHelper;
import com.dawnlightning.ucqa.bean.ApiBase;
import com.dawnlightning.ucqa.bean.response.consult.detailed.CommentBean;
import com.dawnlightning.ucqa.bean.response.consult.detailed.DetailedBean;
import com.dawnlightning.ucqa.bean.response.consult.detailed.PicsBean;
import com.dawnlightning.ucqa.common.Code;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import rx.functions.Action1;

/**
 * 作者：Administrator on 2016/5/24 13:57
 * 邮箱：823894716@qq.com
 */
public class ConsultDetailedModel {
    public static final int MaxCommentSize = 30;

    public interface DetailedListener {
        void getSuccess(DetailedBean bean);

        void getFailure(String msg);

        void getError(String msg);
    }

    public interface GetCommentListener {
        void getSuccess(List<CommentBean> list);

        void getFailure(String msg);

        void getError(String msg);

        void noNextPage();

        void noData();
    }

    public interface OperateListener {
        void doSuccess(String msg);

        void doFailure(String msg);

        void doError(String msg);
    }

    ConsultApiManager consultApiManager = new ConsultApiManager();
    OperateApiManager operateApiManager = new OperateApiManager();

    /**
     * 无缓存
     * 获取咨询详细
     *
     * @param uid    发布者id
     * @param m_auth 登陆后秘钥
     * @param bwztid 咨询id
     */
    public void GetConsultDetailed(int uid, String m_auth, int bwztid, final DetailedListener listener) {
        consultApiManager.GetConsultDetailed(uid, m_auth, bwztid)

                .subscribe(new SuccessAction<JsonObject>() {
                    /**
                     * @param target
                     */
                    @Override
                    public void Success(JsonObject target) {
                        /*
                        * 咨询详细
                        * */
                        DetailedBean detailedBean = new DetailedBean();
                        JsonObject js = target.getAsJsonObject("bwzt");
                        detailedBean.setAge(js.get("age").getAsString());
                        detailedBean.setContent(js.get("message").getAsString());
                        detailedBean.setDatetime(js.get("dateline").getAsString());
                        detailedBean.setSubject(js.get("subject").getAsString());
                        detailedBean.setUid(js.get("uid").getAsString());
                        detailedBean.setUsename(js.get("username").getAsString());
                        detailedBean.setAvatar_url(js.get("avatar_url").getAsString());
                        detailedBean.setName(js.get("name").getAsString());
                        detailedBean.setViewnum(js.get("viewnum").getAsString());
                        detailedBean.setName(js.get("name").getAsString());
                        detailedBean.setReplynum(js.get("replynum").getAsString());
                        detailedBean.setBwztid(Integer.parseInt(js.get("bwztid").getAsString()));
                        detailedBean.setStatus(Integer.parseInt(js.get("status").getAsString()));
                        detailedBean.setSex(js.get("sex").getAsString());
                        /*
                        * 解析评论
                        * */
                        detailedBean.setComment(JsonParseHelper.ParseComment(js));
                          /*
                        * 解析2级回复
                        * */
                        detailedBean.setComment(JsonParseHelper.ParseReply(detailedBean.getComment()));
                        /*
                        * 获取图片列表
                        * */
                        detailedBean.setPics(JsonParseHelper.ParsePictureList(js));
                        listener.getSuccess(detailedBean);
                    }

                    @Override
                    public void Failure(String msg) {
                        listener.getFailure(msg);
                    }
                }, new FailureAction() {
                    @Override
                    public void Error(String msg) {
                        listener.getError(msg);
                    }
                });
    }

    /**
     * 无缓存
     * 获取更多的评论
     *
     * @param uid    发布者id
     * @param m_auth 登陆后秘钥
     * @param bwztid 咨询id
     * @param page   页数
     */
    public void GetCommentList(int uid, String m_auth, int bwztid, int page, final GetCommentListener listener) {
        consultApiManager.GetConsultComment(uid, m_auth, bwztid, page)
                .subscribe(new SuccessAction<JsonObject>() {
                    @Override
                    public void Success(JsonObject target) {
                        JsonObject js = target.getAsJsonObject("bwzt");
                        List<CommentBean> list = JsonParseHelper.ParseComment(js);//一级评论列表
                        List<CommentBean> newlist = JsonParseHelper.ParseReply(list);//二级评论列表
                        if (newlist.size() == 0) {
                            listener.noData();
                        } else if (newlist.size() < MaxCommentSize && newlist.size() > 0) {
                            listener.getSuccess(newlist);
                            listener.noNextPage();
                        } else if (newlist.size() >= MaxCommentSize) {
                            listener.getSuccess(newlist);
                        }
                    }

                    @Override
                    public void Failure(String msg) {
                        listener.getFailure(msg);
                    }
                }, new FailureAction() {
                    @Override
                    public void Error(String msg) {
                        listener.getError(msg);
                    }
                });
    }

    /**
     * 发布评论
     *
     * @param message  评论内容
     * @param bwztid   要评论的咨询id
     * @param formhash 登陆后返回的formhash
     * @param m_auth   登陆后返回
     */
    public void Comment(String message, int bwztid, String formhash, String m_auth, final OperateListener listener) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("message", message);
        params.put("id", bwztid);
        params.put("idtype", "bwztid").toString();
        params.put("formhash", formhash);
        params.put("commentsubmit", true);
        operateApiManager.Comment(m_auth, params)
                .subscribe(new SuccessAction<JsonObject>() {
                    @Override
                    public void Success(JsonObject target) {
                        listener.doSuccess("评论成功");
                    }

                    @Override
                    public void Failure(String msg) {
                        listener.doFailure("评论失败");
                    }
                }, new FailureAction() {
                    @Override
                    public void Error(String msg) {

                        listener.doError("服务器失去响应");
                    }
                });
    }

    /**
     * 回复评论
     *
     * @param message  评论内容
     * @param bwztid   要评论的咨询id
     * @param formhash 登陆后返回的formhash
     * @param m_auth   登陆后返回
     * @param cid      要回复的评论id
     */
    public void Reply(String message, int bwztid, int cid, String formhash, String m_auth, final OperateListener listener) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("message", message);
        params.put("id", bwztid);
        params.put("cid", cid);
        params.put("idtype", "bwztid").toString();
        params.put("formhash", formhash);
        params.put("commentsubmit", true);
        operateApiManager.Reply(m_auth, params)
                .subscribe(new SuccessAction<JsonObject>() {
                    @Override
                    public void Success(JsonObject target) {

                        listener.doSuccess("回复成功");
                    }

                    @Override
                    public void Failure(String msg) {

                        listener.doFailure("回复失败");
                    }
                }, new FailureAction() {
                    @Override
                    public void Error(String msg) {

                        listener.doError("服务器失去响应");

                    }
                });
    }

    /**
     * 采纳咨询
     *
     * @param m_auth 登陆后返回
     * @param bwztid 咨询id
     */
    public void Solve(String m_auth, int bwztid, final OperateListener listener) {
        operateApiManager.Solve(m_auth, bwztid)
                .subscribe(new SuccessAction<JsonObject>() {
                    @Override
                    public void Success(JsonObject target) {

                        listener.doSuccess("采纳成功");
                    }

                    @Override
                    public void Failure(String msg) {

                        listener.doFailure("采纳失败");
                    }
                }, new FailureAction() {
                    @Override
                    public void Error(String msg) {

                        listener.doError("服务器失去响应");

                    }
                });
    }

    /**
     * 举报
     *
     * @param m_auth m_auth 登陆后返回
     * @param bwztid bwztid 咨询id
     * @param reason 举报的原因
     */
    public void Report(String m_auth, int bwztid, String reason, final OperateListener listener) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("reportsubmit", true);
        params.put("reason", reason);
        operateApiManager.Report(m_auth, bwztid, params)
                .subscribe(new Action1<JsonObject>() {
                    @Override
                    public void call(JsonObject jsonObject) {
                        listener.doSuccess("举报成功");
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        listener.doSuccess("举报失败");
                    }
                });
    }

    /**
     * 删除咨询
     *
     * @param m_auth 登陆后返回
     * @param bwztid 咨询id
     */
    public void Delete(String m_auth, int bwztid, final OperateListener listener) {
        operateApiManager.Delete(m_auth, bwztid)
                .subscribe(new SuccessAction<JsonObject>() {
                    @Override
                    public void Success(JsonObject target) {

                        listener.doSuccess("删除成功");
                    }

                    @Override
                    public void Failure(String msg) {

                        listener.doFailure("删除失败");
                    }
                }, new FailureAction() {
                    @Override
                    public void Error(String msg) {

                        listener.doError("服务器失去响应");

                    }
                });
    }

}
