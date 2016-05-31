package com.dawnlightning.ucqa.model;

import android.util.Log;

import com.dawnlightning.ucqa.api.apimanager.ConsultApiManager;
import com.dawnlightning.ucqa.api.action.FailureAction;
import com.dawnlightning.ucqa.api.action.SuccessAction;
import com.dawnlightning.ucqa.base.Actions;
import com.dawnlightning.ucqa.bean.others.ConsultMessageBean;
import com.dawnlightning.ucqa.bean.response.consult.NoticeBean;
import com.google.gson.JsonObject;

import java.util.List;

/**
 * 作者：Administrator on 2016/5/24 13:58
 * 邮箱：823894716@qq.com
 */
public class MessageModel {
    ConsultApiManager consultApiManager=new ConsultApiManager();
    public interface MesssgaeListListener{
        void getSuccess(NoticeBean bean, Actions actions);
        void getFailure(String msg,Actions actions);
        void getError(String msg,Actions actions);
        void noNextPage(Actions actions);
        void noData(Actions actions);
    }
    /**
     * 尚未与后端沟通，先搁置
     * 获取消息列表
     * @param m_auth 登陆后返回
     * @param page  页数
     */
    public void GetNotice(String m_auth, final int page, final  MesssgaeListListener  listListener){
      final  Actions actions;
        if (page==1){
            actions=Actions.Refresh;
        }else{
            actions=Actions.LoadMore;
        }
        consultApiManager.GetNotice(m_auth,page)
                .subscribe(new SuccessAction<NoticeBean>() {
                    @Override
                    public void Success(NoticeBean target) {
                        if (target.getNotices().getList().size()==0){
                            listListener.noData(actions);
                        }else if (target.getNotices().getPages()==page){
                            listListener.getSuccess(target,actions);
                            listListener.noNextPage(actions);
                        }else{
                            listListener.getSuccess(target,actions);
                        }
                    }
                    @Override
                    public void Failure( String msg) {
                        listListener.getFailure(msg,actions);
                    }
                }, new FailureAction() {
                    @Override
                    public void Error(String msg) {
                        listListener.getError(msg,actions);
                    }
                });
    }
}

