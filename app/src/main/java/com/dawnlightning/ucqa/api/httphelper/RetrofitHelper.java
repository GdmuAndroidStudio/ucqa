package com.dawnlightning.ucqa.api.httphelper;



import com.dawnlightning.ucqa.common.HttpConstants;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者：Administrator on 2016/5/15 21:40
 * 邮箱：823894716@qq.com
 * Retrofit简单封装
 */
public class RetrofitHelper {
    /*    *
    * 默认不做缓存
    * */
    public static final int max_age=0;
    public static final  int max_stale=0;
    private Retrofit sRetrofit;
    public RetrofitHelper(final  int max_age,final  int max_stale){
        this.sRetrofit=init(max_age,max_stale);

    }
    public RetrofitHelper(){
        this.sRetrofit=init(max_age,max_stale);

    }
    public static  Retrofit init(final  int max_age, final  int max_stale){
         return   new Retrofit.Builder()
                .baseUrl(HttpConstants.HTTP_REQUEST)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())// 使用RxJava作为回调适配器
                 .client(new OkHttpHelper(max_age,max_stale).getInstance())
                 .build();

    }
    public Retrofit getInstance(){
        return this.sRetrofit;
    }
}
