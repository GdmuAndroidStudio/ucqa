package com.dawnlightning.ucqa.model;

import android.util.Log;

import com.dawnlightning.ucqa.api.AccountApiManager;
import com.dawnlightning.ucqa.api.action.FailureAction;
import com.dawnlightning.ucqa.api.action.SuccessAction;
import com.dawnlightning.ucqa.api.requestbody.ProgressRequestBody;
import com.dawnlightning.ucqa.bean.others.UploadPicsBean;
import com.dawnlightning.ucqa.bean.others.UserDataBean;
import com.dawnlightning.ucqa.bean.response.account.GetAvatarBean;
import com.dawnlightning.ucqa.bean.response.account.GetSeccodeBean;
import com.dawnlightning.ucqa.bean.response.account.LoginBean;
import com.dawnlightning.ucqa.bean.response.account.RegisterBean;
import com.google.gson.JsonObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.functions.Action1;

/**
 * 作者：Administrator on 2016/5/24 13:58
 * 邮箱：823894716@qq.com
 */
public class AccountModel {
    public interface  LoginListener{
        void getSuccess(LoginBean bean);
        void getFailure(int code ,String msg);
    }
    AccountApiManager accountApiManager=new AccountApiManager();
    /**
     * 登陆
     * 登陆信息缓存4小时，最大寿命为4天，4天后失效
     * @param username    用户名
     * @param password    密码
     */
    public void Login(String username,String password,final  LoginListener listener){
        new AccountApiManager(14400,345600)
                .Login(username,password).subscribe(new SuccessAction<LoginBean>() {
            @Override
            public void Success(LoginBean target) {
                listener.getSuccess(target);
                Log.e("Success",target.toString());
            }
            @Override
            public void Failure(int code, String msg) {
                listener.getFailure(code,msg);
                Log.e("Failure",msg);
            }
        }, new FailureAction() {
            @Override
            public void Error(String msg) {
                Log.e("Error",msg);
            }
        });
    }
    /**
     * 不做数据缓存
     * 注册
     * @param username    用户名
     * @param pssword     密码
     */
    public void Register(final  String username,final  String pssword){
        accountApiManager.GetSeccode().subscribe(new SuccessAction<GetSeccodeBean>() {
            @Override
            public void Success(GetSeccodeBean target) {
                new AccountApiManager().Register(username,pssword,target).subscribe(new SuccessAction<RegisterBean>() {
                    @Override
                    public void Success(RegisterBean target) {
                        Log.e("register",target.getSpace().toString());
                    }
                    @Override
                    public void Failure(int code, String msg) {
                        Log.e("registerfailure",msg);
                    }
                }, new FailureAction() {
                    @Override
                    public void Error(String msg) {
                        Log.e("error",msg);
                    }
                });
            }

            @Override
            public void Failure(int code, String msg) {

            }
        }, new FailureAction() {
            @Override
            public void Error(String msg) {

            }
        });
    }

    /**
     * 无缓存
     * 修改用户资料
     * @param m_auth    登陆返回的秘钥
     * @param bean   用户资料修改后的数据
     */
    public void EditPersonalData(String m_auth,UserDataBean bean){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("profilesubmit","true");
        map.put("formhash",bean.getFormhash());
        map.put("name",bean.getName());
        map.put("sex",bean.getSex());
        map.put("birthyear",bean.getBirthyear());
        map.put("birthmonth",bean.getBirthmonth());
        map.put("birthday",bean.getBirthday());
        accountApiManager.EditProfile(m_auth,map)
                .subscribe(new SuccessAction<String>() {
                    @Override
                    public void Success(String target) {
                        Log.e("成功","修改资料成功");
                    }

                    @Override
                    public void Failure(int code, String msg) {
                        Log.e("失败","修改资料失败");
                    }
                }, new FailureAction() {
                    @Override
                    public void Error(String msg) {
                        Log.e("提交失败","修改资料失败");
                    }
                });
    }
    /**
     * 无缓存
     * 上传用户头像
     * @param bean  图片上传实体类
     */
    public void UploadAvater(UploadPicsBean bean){
        RequestBody requestBody=RequestBody.create(MediaType.parse("image/jpeg"),bean.getPicture());
        ProgressRequestBody progressRequestBody=new ProgressRequestBody(requestBody, new ProgressRequestBody.ProgressListener() {
            @Override
            public void update(long bytesRead, long contentLength, boolean done) {
                int count = (int) ((bytesRead * 1.0 / contentLength) * 100);
                Log.e("progress",count+"%");
            }
        });
        Map<String, RequestBody> map = new HashMap<>();
        map.put("Filedata\";filename=\"+"+bean.getPicture().getName()+"",progressRequestBody);
        accountApiManager.UploadAvater(bean.getM_auth(),map)
                .subscribe(new SuccessAction<GetAvatarBean>() {
                    @Override
                    public void Success(GetAvatarBean target) {
                        Log.e("bean", target.toString());
                    }

                    @Override
                    public void Failure(int code, String msg) {
                        Log.e("bean", msg);
                    }
                }, new FailureAction() {
                    @Override
                    public void Error(String msg) {
                        Log.e("error", msg);
                    }
                });
    }

    /**
     * 检查App更新
     */
    public void CheckUpdate(){
        accountApiManager.CheckUpdate()
                .subscribe(new Action1<JsonObject>() {
                    @Override
                    public void call(JsonObject jsonObject) {
                        Log.e("jsonobject",jsonObject.toString());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e("jsonobject",throwable.toString());
                    }
                });
    }
}
