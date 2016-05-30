package com.dawnlightning.ucqa.model;

import android.util.Log;

import com.dawnlightning.ucqa.api.apimanager.ConsultApiManager;
import com.dawnlightning.ucqa.api.action.FailureAction;
import com.dawnlightning.ucqa.api.action.SuccessAction;
import com.google.gson.JsonObject;

/**
 * 作者：Administrator on 2016/5/24 13:58
 * 邮箱：823894716@qq.com
 */
public class MessageModel {
    ConsultApiManager consultApiManager=new ConsultApiManager();
    /**
     * 尚未与后端沟通，先搁置
     * 获取消息列表
     * @param m_auth 登陆后返回
     * @param page  页数
     */
    public void GetNotice(String m_auth,int page){
        consultApiManager.GetNotice(m_auth,page)
                .subscribe(new SuccessAction<JsonObject>() {
                    @Override
                    public void Success(JsonObject target) {
                        Log.e("success",target.toString());
                    }

                    @Override
                    public void Failure( String msg) {
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

