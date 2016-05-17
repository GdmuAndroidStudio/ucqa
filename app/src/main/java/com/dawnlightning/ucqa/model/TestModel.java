package com.dawnlightning.ucqa.model;

import android.util.Log;

import com.dawnlightning.ucqa.api.AccountApiManager;
import com.dawnlightning.ucqa.api.action.FailureAction;
import com.dawnlightning.ucqa.api.action.SuccessAction;
import com.dawnlightning.ucqa.api.requestbody.ProgressRequestBody;
import com.dawnlightning.ucqa.base.MyApp;
import com.dawnlightning.ucqa.bean.response.account.GetAvatarBean;
import com.dawnlightning.ucqa.bean.response.account.GetSeccodeBean;
import com.dawnlightning.ucqa.bean.response.account.LoginBean;
import com.dawnlightning.ucqa.bean.response.account.RegisterBean;
import com.dawnlightning.ucqa.utils.SdCardUtil;
import com.nostra13.universalimageloader.utils.L;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 作者：Administrator on 2016/5/15 22:22
 * 邮箱：823894716@qq.com
 * model测试类
 */
public class TestModel {
    AccountApiManager apiManager=new AccountApiManager(0,0);
    public void login(){
        apiManager.Login("13650421544","123456").subscribe(new SuccessAction<LoginBean>() {
            @Override
            public void Success(LoginBean target) {
                Log.e("Success",target.toString());
            }
            @Override
            public void Failure(int code, String msg) {
                Log.e("Failure",msg);
            }
        }, new FailureAction() {
            @Override
            public void Error(String msg) {
                Log.e("Error",msg);
            }
        });
    }
    public void Register(){
        apiManager.GetSeccode().subscribe(new SuccessAction<GetSeccodeBean>() {
            @Override
            public void Success(GetSeccodeBean target) {
                apiManager.Register("13550421248","123456",target).subscribe(new SuccessAction<RegisterBean>() {
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
    public void Upload(){
        File file= new File("/storage/sdcard0/lightup/photos/345.jpg");
        RequestBody requestBody=RequestBody.create(MediaType.parse("image/jpeg"),file);

        ProgressRequestBody progressRequestBody=new ProgressRequestBody(requestBody, new ProgressRequestBody.ProgressListener() {
            @Override
            public void update(long bytesRead, long contentLength, boolean done) {
                int count = (int) ((bytesRead * 1.0 / contentLength) * 100);
                Log.e("progress",count+"%");
            }
        });
        Map<String, RequestBody> map = new HashMap<>();
        map.put("Filedata\";filename=\"+"+file.getName()+"",progressRequestBody);
        apiManager.UploadAvater("70cf2VjWzAADPMb5Q2X7ZORDbiUIHk3guj9k0HD2qlAAtpNNraFgpy1cqAfE%2FG%2BIG2kNIR5kPdsOjbNqrz8FRg",map)
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

}
