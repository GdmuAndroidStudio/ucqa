package com.dawnlightning.ucqa.model;

import android.util.Log;

import com.dawnlightning.ucqa.api.apimanager.AccountApiManager;
import com.dawnlightning.ucqa.api.apimanager.ConsultApiManager;
import com.dawnlightning.ucqa.api.apimanager.IssueApiManager;
import com.dawnlightning.ucqa.api.apimanager.OperateApiManager;
import com.dawnlightning.ucqa.api.action.FailureAction;
import com.dawnlightning.ucqa.api.action.SuccessAction;
import com.dawnlightning.ucqa.api.requestbody.ProgressRequestBody;
import com.dawnlightning.ucqa.bean.response.account.GetAvatarBean;
import com.dawnlightning.ucqa.bean.response.account.GetSeccodeBean;
import com.dawnlightning.ucqa.bean.response.account.LoginBean;
import com.dawnlightning.ucqa.bean.response.account.RegisterBean;
import com.dawnlightning.ucqa.bean.response.consult.NoticeBean;
import com.google.gson.JsonObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.functions.Action1;

/**
 * 作者：Administrator on 2016/5/15 22:22
 * 邮箱：823894716@qq.com
 * model测试类
 */
public class TestModel {
//    AccountApiManager apiManager=new AccountApiManager(0,0);
//
//    IssueApiManager issueApiManager=new IssueApiManager();
//    ConsultApiManager consultApiManager=new ConsultApiManager();
//    OperateApiManager operateApiManager=new OperateApiManager();
//    /**
//     * 登陆
//     */
//    public void login(){
//
//        apiManager.Login("13650421544","123456").subscribe(new SuccessAction<LoginBean>() {
//            @Override
//            public void Success(LoginBean target) {
//                Log.e("Success",target.toString());
//            }
//            @Override
//            public void Failure(int code, String msg) {
//                Log.e("Failure",msg);
//            }
//        }, new FailureAction() {
//            @Override
//            public void Error(String msg) {
//                Log.e("Error",msg);
//            }
//        });
//
//    }
//    public void newlogin(){
//        apiManager.Login("89619RrjakPaownrziz6SOyPV%2FTgNJfOY26m%2BBDvoQJoN31R%2FX6xCWGqoBFezeuDQUmQ28jEQZ1lBJMTePveDw").subscribe(new SuccessAction<LoginBean>() {
//            @Override
//            public void Success(LoginBean target) {
//                Log.e("Success",target.toString());
//            }
//            @Override
//            public void Failure(int code, String msg) {
//                Log.e("Failure",msg);
//            }
//        }, new FailureAction() {
//            @Override
//            public void Error(String msg) {
//                Log.e("Error",msg);
//            }
//        });
//    }
//    /**
//     * 注册
//     */
//    public void Register(){
//        apiManager.GetSeccode().subscribe(new SuccessAction<GetSeccodeBean>() {
//            @Override
//            public void Success(GetSeccodeBean target) {
//                apiManager.Register("13550421248","123456",target).subscribe(new SuccessAction<RegisterBean>() {
//                    @Override
//                    public void Success(RegisterBean target) {
//                        Log.e("register",target.getSpace().toString());
//                    }
//                    @Override
//                    public void Failure(int code, String msg) {
//                        Log.e("registerfailure",msg);
//                    }
//                }, new FailureAction() {
//                    @Override
//                    public void Error(String msg) {
//                        Log.e("error",msg);
//                    }
//                });
//            }
//
//            @Override
//            public void Failure(int code, String msg) {
//
//            }
//        }, new FailureAction() {
//            @Override
//            public void Error(String msg) {
//
//            }
//        });
//    }
//    /**
//     * 修改用户资料
//     */
//    public void edit(){
//        Map<String,Object> map=new HashMap<String,Object>();
//        map.put("profilesubmit","true");
//        map.put("formhash","d295e322");
//        map.put("name","doctor");
//        map.put("sex",1);
//        map.put("birthyear",1990);
//        map.put("birthmonth",8);
//        map.put("birthday",1);
//                                //70cf2VjWzAADPMb5Q2X7ZORDbiUIHk3guj9k0HD2qlAAtpNNraFgpy1cqAfE%2FG%2BIG2kNIR5kPdsOjbNqrz8FRg
//        apiManager.EditProfile("70cf2VjWzAADPMb5Q2X7ZORDbiUIHk3guj9k0HD2qlAAtpNNraFgpy1cqAfE%2FG%2BIG2kNIR5kPdsOjbNqrz8FRg",map)
//                .subscribe(new SuccessAction<String>() {
//                    @Override
//                    public void Success(String target) {
//                        Log.e("成功","修改资料成功");
//                    }
//
//                    @Override
//                    public void Failure(int code, String msg) {
//                        Log.e("失败","修改资料失败");
//                    }
//                }, new FailureAction() {
//                    @Override
//                    public void Error(String msg) {
//                        Log.e("提交失败","修改资料失败");
//                    }
//                });
//    }
//    /**
//     * 上传用户头像
//     */
//    public void Upload(){
//        File file= new File("/storage/sdcard0/lightup/photos/345.jpg");
//        RequestBody requestBody=RequestBody.create(MediaType.parse("image/jpeg"),file);
//        ProgressRequestBody progressRequestBody=new ProgressRequestBody(requestBody, new ProgressRequestBody.ProgressListener() {
//            @Override
//            public void update(long bytesRead, long contentLength, boolean done) {
//                int count = (int) ((bytesRead * 1.0 / contentLength) * 100);
//                Log.e("progress",count+"%");
//            }
//        });
//        Map<String, RequestBody> map = new HashMap<>();
//        map.put("Filedata\";filename=\"+"+file.getName()+"",progressRequestBody);
//        apiManager.UploadAvater("70cf2VjWzAADPMb5Q2X7ZORDbiUIHk3guj9k0HD2qlAAtpNNraFgpy1cqAfE%2FG%2BIG2kNIR5kPdsOjbNqrz8FRg",map)
//        .subscribe(new SuccessAction<GetAvatarBean>() {
//            @Override
//            public void Success(GetAvatarBean target) {
//                Log.e("bean", target.toString());
//            }
//
//            @Override
//            public void Failure(int code, String msg) {
//                Log.e("bean", msg);
//            }
//        }, new FailureAction() {
//            @Override
//            public void Error(String msg) {
//                Log.e("error", msg);
//            }
//        });
//    }
//    public void GetClassify(){
//        consultApiManager.GetClassify().subscribe(new SuccessAction<JsonObject>() {
//            @Override
//            public void Success(JsonObject target) {
//                Log.e("success",target.toString());
//            }
//
//            @Override
//            public void Failure(int code, String msg) {
//                Log.e("failure",msg);
//            }
//        }, new FailureAction() {
//            @Override
//            public void Error(String msg) {
//                Log.e("error",msg);
//            }
//        });
//    }
//    public void GetConsultList(){
//        consultApiManager.GetAllConsultList(1).subscribe(new SuccessAction<JsonObject>() {
//            @Override
//            public void Success(JsonObject target) {
//                Log.e("success",target.toString());
//            }
//
//            @Override
//            public void Failure(int code, String msg) {
//                Log.e("failure",msg);
//            }
//        }, new FailureAction() {
//            @Override
//            public void Error(String msg) {
//                Log.e("error",msg);
//            }
//        });
//    }
//    public void GetSpecifyConsult(){
//        consultApiManager.GetSpecifyConsultList(1,1).subscribe(new SuccessAction<JsonObject>() {
//            @Override
//            public void Success(JsonObject target) {
//                Log.e("success",target.toString());
//            }
//
//            @Override
//            public void Failure(int code, String msg) {
//                Log.e("failure",msg);
//            }
//        }, new FailureAction() {
//            @Override
//            public void Error(String msg) {
//                Log.e("error",msg);
//            }
//        });
//    }
//    public void GetConsultDetailed(){
//        consultApiManager.GetConsultDetailed(11,"70cf2VjWzAADPMb5Q2X7ZORDbiUIHk3guj9k0HD2qlAAtpNNraFgpy1cqAfE%2FG%2BIG2kNIR5kPdsOjbNqrz8FRg",167)
//                .subscribe(new SuccessAction<JsonObject>() {
//                    @Override
//                    public void Success(JsonObject target) {
//                        Log.e("success",target.toString());
//                    }
//
//                    @Override
//                    public void Failure(int code, String msg) {
//                        Log.e("failure",msg);
//                    }
//                }, new FailureAction() {
//                    @Override
//                    public void Error(String msg) {
//                        Log.e("error",msg);
//                    }
//                });
//    }
    public void GetNotice(){
       new ConsultApiManager().GetNotice("0c914G8pdYvAB6bEMUaRhPXfcBTKAqBeZdNTR1iWly7NG9t5%2FdEmD1VM2fvDW%2BFKDa5c9me5Hm4GeTH6KE59aQ",1)
                .subscribe(new SuccessAction<NoticeBean>() {
                    @Override
                    public void Success(NoticeBean target) {
                        Log.e("success",target.toString());
                    }

                    @Override
                    public void Failure(String msg) {
                        Log.e("failure",msg);
                    }
                }, new FailureAction() {
                    @Override
                    public void Error(String msg) {
                        Log.e("error",msg);
                    }
                });
    }
//    public void GetMyConsult(){
//        consultApiManager.GetMyConsultList(11,"70cf2VjWzAADPMb5Q2X7ZORDbiUIHk3guj9k0HD2qlAAtpNNraFgpy1cqAfE%2FG%2BIG2kNIR5kPdsOjbNqrz8FRg",1)
//                .subscribe(new SuccessAction<JsonObject>() {
//                    @Override
//                    public void Success(JsonObject target) {
//                        Log.e("success",target.toString());
//                    }
//
//                    @Override
//                    public void Failure(int code, String msg) {
//                        Log.e("failure",msg);
//                    }
//                }, new FailureAction() {
//                    @Override
//                    public void Error(String msg) {
//                        Log.e("error",msg);
//                    }
//                });
//    }
//    public void GetCommentList(){
//        consultApiManager.GetConsultComment(11,"70cf2VjWzAADPMb5Q2X7ZORDbiUIHk3guj9k0HD2qlAAtpNNraFgpy1cqAfE%2FG%2BIG2kNIR5kPdsOjbNqrz8FRg",167,2)
//                .subscribe(new SuccessAction<JsonObject>() {
//                    @Override
//                    public void Success(JsonObject target) {
//                        Log.e("success",target.toString());
//                    }
//
//                    @Override
//                    public void Failure(int code, String msg) {
//                        Log.e("failure",msg);
//                    }
//                }, new FailureAction() {
//                    @Override
//                    public void Error(String msg) {
//                        Log.e("error",msg);
//                    }
//                });
//    }
//    public void PublicIssuse(){
//        Map<String,Object> params=new HashMap<String,Object>();
//        params.put("subject","点亮第3版");
//        params.put("bwztclassid", 1);
//        params.put("bwztdivisionid", 1);
//        params.put("sex",1);
//        params.put("age",18);
//        params.put("message","OkHttp");
//        params.put("makefeed", 1);
//        params.put("bwztsubmit", true);
//        params.put("formhash","d295e322");
//        params.put("picids[101]",0);
//        issueApiManager.PublicIssue("89619RrjakPaownrziz6SOyPV%2FTgNJfOY26m%2BBDvoQJoN31R%2FX6xCWGqoBFezeuDQUmQ28jEQZ1lBJMTePveDw",params)
//                .subscribe(new SuccessAction<JsonObject>() {
//                    @Override
//                    public void Success(JsonObject target) {
//                        Log.e("success",target.toString());
//                    }
//
//                    @Override
//                    public void Failure(int code, String msg) {
//                        Log.e("failure",msg);
//                    }
//                }, new FailureAction() {
//                    @Override
//                    public void Error(String msg) {
//                        Log.e("error",msg);
//                    }
//                });
//    }
//    public void UploadPicture(){
//
//        File file= new File("/storage/sdcard0/lightup/photos/345.jpg");
//        RequestBody requestBody=RequestBody.create(MediaType.parse("image/jpeg"),file);
//        ProgressRequestBody progressRequestBody=new ProgressRequestBody(requestBody, new ProgressRequestBody.ProgressListener() {
//            @Override
//            public void update(long bytesRead, long contentLength, boolean done) {
//                int count = (int) ((bytesRead * 1.0 / contentLength) * 100);
//                Log.e("progress",count+"%");
//            }
//        });
//        Map<String,RequestBody> data=new HashMap<String,RequestBody>();
//        data.put("attach\";filename=\"+"+file.getName()+"",progressRequestBody);
//        data.put("op", RequestBody.create(MediaType.parse("text/plain"), " uploadphoto2"));
//        data.put("uid", RequestBody.create(MediaType.parse("text/plain"), "11"));
//        data.put("topicid", RequestBody.create(MediaType.parse("text/plain"), "0"));
//        data.put("albumid", RequestBody.create(MediaType.parse("text/plain"), "0"));
//        data.put("uploadsubmit2",  RequestBody.create(MediaType.parse("text/plain"), "true"));
//        data.put("pic_title", RequestBody.create(MediaType.parse("text/plain"),"lightupV3.0 post test"));
//        issueApiManager.UploadPicture("70cf2VjWzAADPMb5Q2X7ZORDbiUIHk3guj9k0HD2qlAAtpNNraFgpy1cqAfE%2FG%2BIG2kNIR5kPdsOjbNqrz8FRg",data)
//        .subscribe(new SuccessAction<JsonObject>() {
//            @Override
//            public void Success(JsonObject target) {
//                Log.e("success",target.toString());
//            }
//
//            @Override
//            public void Failure(int code, String msg) {
//                Log.e("failure",msg);
//            }
//        }, new FailureAction() {
//            @Override
//            public void Error(String msg) {
//                Log.e("error",msg);
//            }
//        });
//
//    }
//    public void Reply(){
//        Map<String,Object> params=new HashMap<String,Object>();
//        params.put("message","评论测试");
//        params.put("id",171);
//        params.put("cid",628);
//        params.put("idtype","bwztid");
//        params.put("formhash","d295e322");
//        params.put("commentsubmit", true);
//        operateApiManager.Reply("89619RrjakPaownrziz6SOyPV%2FTgNJfOY26m%2BBDvoQJoN31R%2FX6xCWGqoBFezeuDQUmQ28jEQZ1lBJMTePveDw",params)
//                .subscribe(new SuccessAction<JsonObject>() {
//                    @Override
//                    public void Success(JsonObject target) {
//                        Log.e("success",target.toString());
//                    }
//
//                    @Override
//                    public void Failure(int code, String msg) {
//                        Log.e("failure",msg);
//                    }
//                }, new FailureAction() {
//                    @Override
//                    public void Error(String msg) {
//                        Log.e("error",msg);
//                    }
//                });
//
//
//
//
//    }
//    public void Comment(){
//        Map<String,Object> params=new HashMap<String,Object>();
//        params.put("message","评论测试");
//        params.put("id",171);
//        params.put("idtype","bwztid");
//        params.put("formhash","d295e322");
//        params.put("commentsubmit", true);
//        operateApiManager.Comment("89619RrjakPaownrziz6SOyPV%2FTgNJfOY26m%2BBDvoQJoN31R%2FX6xCWGqoBFezeuDQUmQ28jEQZ1lBJMTePveDw",params)
//                .subscribe(new SuccessAction<JsonObject>() {
//                    @Override
//                    public void Success(JsonObject target) {
//                        Log.e("success",target.toString());
//                    }
//
//                    @Override
//                    public void Failure(int code, String msg) {
//                        Log.e("failure",msg);
//                    }
//                }, new FailureAction() {
//                    @Override
//                    public void Error(String msg) {
//                        Log.e("error",msg);
//                    }
//                });
//    }
//    public void Solove(){
//        operateApiManager.Solve("89619RrjakPaownrziz6SOyPV%2FTgNJfOY26m%2BBDvoQJoN31R%2FX6xCWGqoBFezeuDQUmQ28jEQZ1lBJMTePveDw",171)
//                .subscribe(new SuccessAction<JsonObject>() {
//                    @Override
//                    public void Success(JsonObject target) {
//                        Log.e("success",target.toString());
//                    }
//
//                    @Override
//                    public void Failure(int code, String msg) {
//                        Log.e("failure",msg);
//                    }
//                }, new FailureAction() {
//                    @Override
//                    public void Error(String msg) {
//                        Log.e("error",msg);
//                    }
//                });
//    }
//    public void Delete(){
//        operateApiManager.Delete("89619RrjakPaownrziz6SOyPV%2FTgNJfOY26m%2BBDvoQJoN31R%2FX6xCWGqoBFezeuDQUmQ28jEQZ1lBJMTePveDw",171)
//                .subscribe(new SuccessAction<JsonObject>() {
//                    @Override
//                    public void Success(JsonObject target) {
//                        Log.e("success",target.toString());
//                    }
//
//                    @Override
//                    public void Failure(int code, String msg) {
//                        Log.e("failure",msg);
//                    }
//                }, new FailureAction() {
//                    @Override
//                    public void Error(String msg) {
//                        Log.e("error",msg);
//                    }
//                });
//    }
//    public void Report(){
//        Map<String,Object> params=new HashMap<String,Object>();
//        params.put("reportsubmit",true);
//        params.put("reason","举报测试");
//        operateApiManager.Report("89619RrjakPaownrziz6SOyPV%2FTgNJfOY26m%2BBDvoQJoN31R%2FX6xCWGqoBFezeuDQUmQ28jEQZ1lBJMTePveDw",171,params)
//                .subscribe(new Action1<JsonObject>() {
//                    @Override
//                    public void call(JsonObject jsonObject) {
//                        Log.e("jsonobject",jsonObject.toString());
//                    }
//                }, new Action1<Throwable>() {
//                    @Override
//                    public void call(Throwable throwable) {
//
//                    }
//                });
//    }
//    public void Upadte(){
//        apiManager.CheckUpdate().subscribe(new Action1<JsonObject>() {
//            @Override
//            public void call(JsonObject jsonObject) {
//                Log.e("jsonobject",jsonObject.toString());
//            }
//        }, new Action1<Throwable>() {
//            @Override
//            public void call(Throwable throwable) {
//                Log.e("jsonobject",throwable.toString());
//            }
//        });
//    }
}
