package com.dawnlightning.ucqa.api;

import com.dawnlightning.ucqa.api.httphelper.RetrofitHelper;
import com.dawnlightning.ucqa.bean.ApiBase;
import com.google.gson.JsonObject;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作者：Administrator on 2016/5/15 20:15
 * 邮箱：823894716@qq.com
 */
public class OperateApiManager {
    private Retrofit retrofit;
    public OperateApiService OperateApiService;
    public OperateApiManager(){
        this.retrofit=new RetrofitHelper().getInstance();
        this.OperateApiService=retrofit.create(OperateApiService.class);
    }
    public OperateApiManager(final int max_age,final int max_stale){
        this.retrofit=new RetrofitHelper(max_age,max_stale).getInstance();
        this.OperateApiService=retrofit.create(OperateApiService.class);
    }
    public  Observable<ApiBase> Solve(String m_auth,int bwztid){
        return OperateApiService.doConsultSolve(m_auth, bwztid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }
    public  Observable<ApiBase> Delete(String m_auth,int bwztid){
        return OperateApiService.doConsultDelete(m_auth, bwztid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }
    public  Observable<ApiBase> Comment(String m_auth,Map<String,Object> map){
        return OperateApiService.doPublicComment(m_auth, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }
    public  Observable<ApiBase> Reply(String m_auth,Map<String,Object> map){
        return OperateApiService.doPublicReply(m_auth, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }
    public  Observable<JsonObject> Report(String m_auth, int id, Map<String,Object> map){
        return OperateApiService.doReport(m_auth,id, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

    public interface  OperateApiService{
        /**
         * 采纳咨询
         * @param m_auth 登陆后返回秘钥
         * @param bwztid 采纳咨询的id
         * @return
         */
        @GET("/capi/cp.php?ac=bwzt&bwztsubmit=true&status=1&op=alterstatus")
        Observable<ApiBase> doConsultSolve(@Query("m_auth") String m_auth,@Query("bwztid") int bwztid);

        /**
         * 删除咨询
         * @param m_auth 登陆后返回秘钥
         * @param bwztid 采纳咨询的id
         * @return
         */
        @GET("/capi/cp.php?ac=bwzt&op=delete&deletesubmit=true")
        Observable<ApiBase> doConsultDelete(@Query("m_auth") String m_auth,@Query("bwztid") int bwztid);

        /**
         * 发布评论
         * @param m_auth 登陆返回的m_ath
         * @param commentdata 资料
         * @return
         */
        @FormUrlEncoded
        @POST("/capi/cp.php?ac=comment&inajax=1")
        Observable<ApiBase> doPublicComment(@Query("m_auth") String m_auth, @FieldMap Map<String, Object> commentdata);

        /**
         * 回复评论
         * @param m_auth 登陆返回的m_ath
         * @param replydata 资料
         * @return
         */
        @FormUrlEncoded
        @POST("/capi/cp.php?ac=comment&inajax=1")
        Observable<ApiBase> doPublicReply(@Query("m_auth") String m_auth, @FieldMap Map<String, Object> replydata);
        /**
         * 举报
         * @param m_auth 登陆返回的m_ath
         * @param id   要举报的id
         * @param commentdata 举报的内容
         * @return
         */
        @FormUrlEncoded
        @POST("/capi/cp.php?ac=common&op=report&idtype=bwztid")
        Observable<JsonObject> doReport(@Query("m_auth") String m_auth, @Query("id") int id, @FieldMap Map<String, Object> commentdata);


    }
}
