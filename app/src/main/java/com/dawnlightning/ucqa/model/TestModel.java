package com.dawnlightning.ucqa.model;

import android.util.Log;

import com.dawnlightning.ucqa.api.AccountApiManager;
import com.dawnlightning.ucqa.bean.response.account.GetSeccodeBean;
import com.dawnlightning.ucqa.bean.response.account.LoginBean;
import com.dawnlightning.ucqa.bean.response.account.LoginOutBean;
import com.dawnlightning.ucqa.bean.response.account.RegisterBean;

import rx.functions.Action1;

/**
 * 作者：Administrator on 2016/5/15 22:22
 * 邮箱：823894716@qq.com
 * model测试类
 */
public class TestModel {
    AccountApiManager apiManager=new AccountApiManager(0,0);
    public void login(){
        apiManager.Login("13650421544","123456").subscribe(new Action1<LoginBean>() {
            @Override
            public void call(LoginBean loginBean) {
                Log.e("login",loginBean.toString());
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Log.e("login",throwable.toString());
            }
        });
    }
    public void register(final String username, final String password){
        apiManager.GetSeccode().subscribe(new Action1<GetSeccodeBean>() {
            @Override
            public void call(GetSeccodeBean getSeccodeBean) {
                Log.e("getseccode",getSeccodeBean.toString());
                apiManager.Register(username,password,getSeccodeBean).subscribe(new Action1<RegisterBean>() {
                    @Override
                    public void call(RegisterBean registerBean) {
                        Log.e("register",registerBean.toString());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e("register",throwable.toString());
                    }
                });
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Log.e("getseccode",throwable.toString());
            }
        });
    }
    public void loginoff(String uhash){
        apiManager.Loginoff(uhash).subscribe(new Action1<LoginOutBean>() {
            @Override
            public void call(LoginOutBean loginOutBean) {
                Log.e("logoff",loginOutBean.toString());
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Log.e("uhash",throwable.toString());
            }
        });
    }
}
