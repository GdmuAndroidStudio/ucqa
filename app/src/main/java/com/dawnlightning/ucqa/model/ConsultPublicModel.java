package com.dawnlightning.ucqa.model;

import android.util.Log;

import com.dawnlightning.ucqa.api.IssueApiManager;
import com.dawnlightning.ucqa.api.action.FailureAction;
import com.dawnlightning.ucqa.api.action.SuccessAction;
import com.dawnlightning.ucqa.api.requestbody.ProgressRequestBody;
import com.dawnlightning.ucqa.bean.others.ConsultBean;
import com.dawnlightning.ucqa.bean.others.UploadPicsBean;
import com.dawnlightning.ucqa.bean.response.consult.ListBean;
import com.google.gson.JsonObject;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 作者：Administrator on 2016/5/24 13:58
 * 邮箱：823894716@qq.com
 */
public class ConsultPublicModel {
    IssueApiManager issueApiManager=new IssueApiManager();

    /**
     * 发布咨询
     * @param bean 咨询实体对象
     * @param m_auth 登陆后返回
     */
    public void PublicIssuse(ConsultBean bean,String m_auth){
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("subject",bean.getSubject());
        params.put("bwztclassid", bean.getBwztclassid());
        params.put("bwztdivisionid", bean.getBwztdivisionid());
        params.put("sex",bean.getSex());
        params.put("age",bean.getAge());
        params.put("message",bean.getMessage());
        params.put("makefeed",1);
        params.put("bwztsubmit", true);
        params.put("formhash",bean.getFormhash());
        if(bean.getPicids()!=null&&bean.getPicids().size()>0){
            for(int i=0;i<bean.getPicids().size();i++){
                String picsid=String.format("picids[%s]",bean.getPicids().get(i));
                params.put(picsid, i);
            }
        }
        issueApiManager.PublicIssue(m_auth,params)
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
     * 上传咨询图片
     * @param list 图片数组
     * @param m_auth 登陆后返回
     */
    public void UploadPicture(List<UploadPicsBean> list, String m_auth){
      for (final  UploadPicsBean bean :list){
          RequestBody requestBody=RequestBody.create(MediaType.parse("image/jpeg"),bean.getPicture());
          ProgressRequestBody progressRequestBody=new ProgressRequestBody(requestBody, new ProgressRequestBody.ProgressListener() {
              @Override
              public void update(long bytesRead, long contentLength, boolean done) {
                  int count = (int) ((bytesRead * 1.0 / contentLength) * 100);
                  Log.e("progress",count+"%");
              }
          });
          Map<String,RequestBody> data=new HashMap<String,RequestBody>();
          data.put("attach\";filename=\"+"+bean.getPicture().getName()+"",progressRequestBody);
          data.put("op", RequestBody.create(MediaType.parse("text/plain"), " uploadphoto2"));
          data.put("uid", RequestBody.create(MediaType.parse("text/plain"), bean.getUid()));
          data.put("topicid", RequestBody.create(MediaType.parse("text/plain"), "0"));
          data.put("albumid", RequestBody.create(MediaType.parse("text/plain"), "0"));
          data.put("uploadsubmit2",  RequestBody.create(MediaType.parse("text/plain"), "true"));
          data.put("pic_title", RequestBody.create(MediaType.parse("text/plain"),bean.getPicturetitle()));
          issueApiManager.UploadPicture(m_auth,data)
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
}
