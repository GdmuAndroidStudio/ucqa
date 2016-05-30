package com.dawnlightning.ucqa.api.apimanager;

import com.dawnlightning.ucqa.api.httphelper.OkHttpHelper;
import com.dawnlightning.ucqa.api.httphelper.RetrofitHelper;
import com.dawnlightning.ucqa.bean.ApiBase;
import com.dawnlightning.ucqa.common.HttpConstants;


import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作者：Administrator on 2016/5/15 20:15
 * 邮箱：823894716@qq.com
 */
public class ConsultApiManager {
    private Retrofit retrofit;
    public ConsultApiService consultApiService;
    public ConsultApiManager(){
        this.retrofit=new RetrofitHelper().getInstance();
        this.consultApiService=retrofit.create(ConsultApiService.class);
    }
    public ConsultApiManager(final int max_age, final int max_stale){
        this.retrofit=new RetrofitHelper(max_age,max_stale).getInstance();
        this.consultApiService=retrofit.create(ConsultApiService.class);
    }
    public  Observable<ApiBase> GetClassify(){
        return consultApiService.doGetClassify()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }
    public  Observable<ApiBase> GetAllConsultList(int page){
        return consultApiService.doGetAllConsultList(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }
    public  Observable<ApiBase> GetSpecifyConsultList(int classifyid,int page){
        return consultApiService.doGetSpecifyConsultList(classifyid,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }
    public  Observable<ApiBase> GetMyConsultList(int uid,String m_auth,int page){
        return consultApiService.doGetMyConsultList(uid, m_auth, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }
    public  Observable<ApiBase> GetConsultDetailed(int uid,String m_auth,int id){
        return consultApiService.doGetConsultDetailed(uid, m_auth, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }
    public  Observable<ApiBase> GetConsultComment(int uid,String m_auth,int clssifyid,int page){
        return consultApiService.doGetConsultComment(uid, m_auth, clssifyid, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }
    public  Observable<ApiBase> GetNotice(String m_auth,int page){
        return  consultApiService.doGetNotice(m_auth,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }
    public interface ConsultApiService{

        /**
         * 获取咨询分类
         */
        @GET("/capi/space.php?do=bwzt&view=class")
        Observable<ApiBase> doGetClassify();

        /**
         * 获取全部的咨询
         * @param page 页数
         * @return
         */
        @GET("/capi/space.php?do=bwzt&view=all&orderby=dateline")
        Observable<ApiBase> doGetAllConsultList(@Query("page") int page);

        /**
         * 按指定获取全部咨询
         * @param classid 咨询分类id
         * @param page 页数
         * @return
         */
        @GET("/capi/space.php?do=bwzt&view=all&orderby=dateline")
        Observable<ApiBase> doGetSpecifyConsultList(@Query("bwztclassid") int classid,@Query("page") int page);

        /**
         * 获取我的咨询
         * @param uid 用户的id
         * @param m_auth 用户登陆后的秘钥
         * @param page 页数
         * @return
         */
        @GET("/capi/space.php?do=bwzt&view=me&orderby=dateline")
        Observable<ApiBase> doGetMyConsultList(@Query("uid") int uid,@Query("m_auth")String m_auth,@Query("page") int page);

        /**
         * 获取咨询详细
         * @param uid 发布者的id
         * @param m_auth 登陆后的秘钥
         * @param id 指定咨询的分类id
         * @return
         */
        @GET("/capi/space.php?do=bwzt")
        Observable<ApiBase> doGetConsultDetailed(@Query("uid") int uid,@Query("m_auth")String m_auth,@Query("id") int id);

        /**
         * 获取评论列表
         * @param uid 发布者的id
         * @param m_auth 登陆后的秘钥
         * @param classid 指定咨询的分类id
         * @return
         */
        @GET("/capi/space.php?do=bwzt")
        Observable<ApiBase> doGetConsultComment(@Query("uid") int uid,@Query("m_auth")String m_auth,@Query("id") int classid,@Query("page") int page);

        /**
         * 获取所有通知
         ** @return
         */
        @GET("/capi/space.php?/do=notice")
        Observable<ApiBase> doGetNotice(@Query(value = "m_auth",encoded = false) String m_auth,@Query("page") int page);



    }
}
