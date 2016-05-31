package com.dawnlightning.ucqa.model;

import android.util.Log;

import com.dawnlightning.ucqa.api.apimanager.ConsultApiManager;
import com.dawnlightning.ucqa.api.action.FailureAction;
import com.dawnlightning.ucqa.api.action.SuccessAction;
import com.dawnlightning.ucqa.api.jsonparse.JsonParseHelper;
import com.dawnlightning.ucqa.base.Actions;
import com.dawnlightning.ucqa.bean.others.ConsultMessageBean;
import com.dawnlightning.ucqa.common.Code;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 作者：Administrator on 2016/5/24 13:57
 * 邮箱：823894716@qq.com
 */
public class ConsultListModel {
    public static final int MaxConsultPageSize=30;
    ConsultApiManager consultApiManager;
    public interface ConsultListListener{
        void getSuccess(List<ConsultMessageBean> list, Actions actions);
        void getFailure(String msg,Actions actions);
        void getError(String msg,Actions actions);
        void noNextPage(Actions actions);
        void noData(Actions actions);
    }
    /**
     * 弃用
     * 缓存一周时间，最长寿命为一个月
     * 获取分类
     */
    public void GetClassify(){
        consultApiManager=new ConsultApiManager(7*24*60*60,30*24*60*60);
        consultApiManager .GetClassify().subscribe(new SuccessAction<JsonObject>() {
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

    /**
     * 缓存30s，最长寿命为一个小时
     * 全部的咨询列表
     * @param page    页数
     */
    public void GetAllConsultList(int page,final ConsultListListener listListener){
        consultApiManager= new ConsultApiManager(30,60*60);
        final Actions actions;
        if (page==1){
            actions=Actions.Refresh;
        }else{
            actions=Actions.LoadMore;
        }
        consultApiManager.GetAllConsultList(page).subscribe(new SuccessAction<JsonObject>() {
            @Override
            public void Success(JsonObject target) {
               List<ConsultMessageBean> list= JsonParseHelper.ParseConsultList(target);
                if (list.size()==0){
                    listListener.noData(actions);
                }else if (list.size()< MaxConsultPageSize&&list.size()>0){
                    listListener.getSuccess(list,actions);
                    listListener.noNextPage(actions);
                }else if(list.size()==MaxConsultPageSize){
                    listListener.getSuccess(list,actions);
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
    /**
     * 缓存30s，最长寿命为一个小时
     * 指定分类的咨询列表
     * @param classifyid   分类id
     * @param page    页数
     */
    public void GetSpecifyConsult(int classifyid,int page,final ConsultListListener listListener){
        consultApiManager=new ConsultApiManager(30,60*60);
        final Actions actions;
        if (page==1){
            actions=Actions.Refresh;
        }else{
            actions=Actions.LoadMore;
        }
        consultApiManager .GetSpecifyConsultList(classifyid,page).subscribe(new SuccessAction<JsonObject>() {
            @Override
            public void Success(JsonObject target) {
                List<ConsultMessageBean> list= JsonParseHelper.ParseConsultList(target);
                if (list.size()==0){
                    listListener.noData(actions);
                }else if (list.size()< MaxConsultPageSize&&list.size()>0){
                    listListener.getSuccess(list,actions);
                    listListener.noNextPage(actions);
                }else if(list.size()==MaxConsultPageSize){
                    listListener.getSuccess(list,actions);
                }
            }

            @Override
            public void Failure(String msg) {
                listListener.getFailure(msg,actions);
            }
        }, new FailureAction() {
            @Override
            public void Error(String msg) {
                listListener.getError(msg,actions);
            }
        });
    }

    /**
     * 无缓存
     * 指定分类的咨询列表
     * @param uid     用户id
     * @param m_auth  登陆返回秘钥
     * @param page    页数
     */
    public void GetMyConsult(int uid,String m_auth,int page,final ConsultListListener listListener ){
        consultApiManager=new ConsultApiManager();
        final Actions actions;
        if (page==1){
            actions=Actions.Refresh;
        }else{
            actions=Actions.LoadMore;
        }
        consultApiManager.GetMyConsultList(uid,m_auth,page)
                .subscribe(new SuccessAction<JsonObject>() {
                    @Override
                    public void Success(JsonObject target) {
                        List<ConsultMessageBean> list= JsonParseHelper.ParseConsultList(target);
                        if (list.size()==0){
                            listListener.noData(actions);
                        }else if (list.size()< MaxConsultPageSize&&list.size()>0){
                            listListener.getSuccess(list,actions);
                            listListener.noNextPage(actions);
                        }else if(list.size()>=MaxConsultPageSize){
                            listListener.getSuccess(list,actions);
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
