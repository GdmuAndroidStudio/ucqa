package com.dawnlightning.ucqa.model;

import android.util.Log;

import com.dawnlightning.ucqa.api.ConsultApiManager;
import com.dawnlightning.ucqa.api.action.FailureAction;
import com.dawnlightning.ucqa.api.action.SuccessAction;
import com.google.gson.JsonObject;

/**
 * 作者：Administrator on 2016/5/24 13:57
 * 邮箱：823894716@qq.com
 */
public class ConsultListModel {
    ConsultApiManager consultApiManager;
    /**
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
     * 缓存30s，最长寿命为一个小时
     * 全部的咨询列表
     * @param page    页数
     */
    public void GetAllConsultList(int page){
        consultApiManager= new ConsultApiManager(30,60*60);
        consultApiManager.GetAllConsultList(page).subscribe(new SuccessAction<JsonObject>() {
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
     * 缓存30s，最长寿命为一个小时
     * 指定分类的咨询列表
     * @param classifyid   分类id
     * @param page    页数
     */
    public void GetSpecifyConsult(int classifyid,int page){
        consultApiManager=new ConsultApiManager(30,60*60);
        consultApiManager .GetSpecifyConsultList(classifyid,page).subscribe(new SuccessAction<JsonObject>() {
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
     * 指定分类的咨询列表
     * @param uid     用户id
     * @param m_auth  登陆返回秘钥
     * @param page    页数
     */
    public void GetMyConsult(int uid,String m_auth,int page){
        consultApiManager=new ConsultApiManager();
        consultApiManager.GetMyConsultList(uid,m_auth,page)
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
