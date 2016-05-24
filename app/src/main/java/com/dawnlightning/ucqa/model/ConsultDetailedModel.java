package com.dawnlightning.ucqa.model;

import android.util.Log;

import com.dawnlightning.ucqa.api.ConsultApiManager;
import com.dawnlightning.ucqa.api.OperateApiManager;
import com.dawnlightning.ucqa.api.action.FailureAction;
import com.dawnlightning.ucqa.api.action.SuccessAction;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

import rx.functions.Action1;

/**
 * 作者：Administrator on 2016/5/24 13:57
 * 邮箱：823894716@qq.com
 */
public class ConsultDetailedModel {
    ConsultApiManager consultApiManager=new ConsultApiManager();
    OperateApiManager operateApiManager=new OperateApiManager();
    /**
     * 无缓存
     * 获取咨询详细
     * @param uid 发布者id
     * @param m_auth 登陆后秘钥
     * @param bwztid  咨询id
     */
    public void GetConsultDetailed(int uid,String m_auth,int bwztid){
        consultApiManager .GetConsultDetailed(11,"70cf2VjWzAADPMb5Q2X7ZORDbiUIHk3guj9k0HD2qlAAtpNNraFgpy1cqAfE%2FG%2BIG2kNIR5kPdsOjbNqrz8FRg",167)
                .subscribe(new SuccessAction<JsonObject>() {
                    @Override
                    public void Success(JsonObject target) {
                        Log.e("success",target.toString());
                    }

                    @Override
                    public void Failure(int code, String msg) {
                        Log.e("failure",msg);
                    }
                }, new FailureAction() {
                    @Override
                    public void Error(String msg) {
                        Log.e("error",msg);
                    }
                });
    }

    /**
     * 无缓存
     * 获取更多的评论
     * @param uid 发布者id
     * @param m_auth 登陆后秘钥
     * @param bwztid  咨询id
     * @param page 页数
     */
    public void GetCommentList(int uid,String m_auth,int bwztid,int page){
        consultApiManager.GetConsultComment(uid,m_auth,bwztid,page)
                .subscribe(new SuccessAction<JsonObject>() {
                    @Override
                    public void Success(JsonObject target) {
                        Log.e("success",target.toString());
                    }

                    @Override
                    public void Failure(int code, String msg) {
                        Log.e("failure",msg);
                    }
                }, new FailureAction() {
                    @Override
                    public void Error(String msg) {
                        Log.e("error",msg);
                    }
                });
    }

    /**
     * 发布评论
     * @param message 评论内容
     * @param bwztid 要评论的咨询id
     * @param formhash 登陆后返回的formhash
     * @param m_auth   登陆后返回
     */
    public void Comment(String message,int bwztid,String formhash,String m_auth){
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("message",message);
        params.put("id",bwztid);
        params.put("idtype","bwztid");
        params.put("formhash",formhash);
        params.put("commentsubmit", true);
       operateApiManager.Comment(m_auth,params)
                .subscribe(new SuccessAction<JsonObject>() {
                    @Override
                    public void Success(JsonObject target) {
                        Log.e("success",target.toString());
                    }

                    @Override
                    public void Failure(int code, String msg) {
                        Log.e("failure",msg);
                    }
                }, new FailureAction() {
                    @Override
                    public void Error(String msg) {
                        Log.e("error",msg);
                    }
                });
    }

    /**
     * 回复评论
     * @param message 评论内容
     * @param bwztid 要评论的咨询id
     * @param formhash 登陆后返回的formhash
     * @param m_auth   登陆后返回
     * @param cid   要回复的评论id
     */
    public void Reply(String message,int bwztid,int cid,String formhash,String m_auth){
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("message",message);
        params.put("id",bwztid);
        params.put("cid",cid);
        params.put("idtype","bwztid");
        params.put("formhash",formhash);
        params.put("commentsubmit", true);
        operateApiManager.Reply(m_auth,params)
                .subscribe(new SuccessAction<JsonObject>() {
                    @Override
                    public void Success(JsonObject target) {
                        Log.e("success",target.toString());
                    }

                    @Override
                    public void Failure(int code, String msg) {
                        Log.e("failure",msg);
                    }
                }, new FailureAction() {
                    @Override
                    public void Error(String msg) {
                        Log.e("error",msg);
                    }
                });
    }

    /**
     * 采纳咨询
     * @param m_auth 登陆后返回
     * @param bwztid 咨询id
     */
    public void Solove(String m_auth,int bwztid){
        operateApiManager.Solve(m_auth,bwztid)
                .subscribe(new SuccessAction<JsonObject>() {
                    @Override
                    public void Success(JsonObject target) {
                        Log.e("success",target.toString());
                    }

                    @Override
                    public void Failure(int code, String msg) {
                        Log.e("failure",msg);
                    }
                }, new FailureAction() {
                    @Override
                    public void Error(String msg) {
                        Log.e("error",msg);
                    }
                });
    }

    /**
     * 举报
     * @param m_auth  m_auth 登陆后返回
     * @param bwztid bwztid 咨询id
     * @param reason 举报的原因
     */
    public void Report(String m_auth,int bwztid,String reason){
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("reportsubmit",true);
        params.put("reason",reason);
        operateApiManager.Report(m_auth,bwztid,params)
                .subscribe(new Action1<JsonObject>() {
                    @Override
                    public void call(JsonObject jsonObject) {
                        Log.e("jsonobject",jsonObject.toString());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
    }

    /**
     * 删除咨询
     * @param m_auth 登陆后返回
     * @param bwztid 咨询id
     */
    public void Delete(String m_auth,int bwztid){
        operateApiManager.Delete(m_auth,bwztid)
                .subscribe(new SuccessAction<JsonObject>() {
                    @Override
                    public void Success(JsonObject target) {
                        Log.e("success",target.toString());
                    }

                    @Override
                    public void Failure(int code, String msg) {
                        Log.e("failure",msg);
                    }
                }, new FailureAction() {
                    @Override
                    public void Error(String msg) {
                        Log.e("error",msg);
                    }
                });
    }

}
