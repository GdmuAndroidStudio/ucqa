package com.dawnlightning.ucqa.api.httphelper;

import android.text.TextUtils;

import com.dawnlightning.ucqa.base.MyApp;
import com.dawnlightning.ucqa.utils.SdCardUtil;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 作者：Administrator on 2016/5/15 21:25
 * 邮箱：823894716@qq.com
 * OkHttp简单封装
 */
public class OkHttpHelper {
    public final  static int  cachesize=10*1024*1024;
    public  OkHttpClient client;
    private static File cacheDir = StorageUtils.getOwnCacheDirectory(MyApp.getApp(), SdCardUtil.FILEDIR+"/"+SdCardUtil.RESPONSE);//获取到缓存的目录地址
    public OkHttpHelper(final int max_age, final int max_stale){
       client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        Response response= chain.proceed(request);
                        String cacheControl =request.cacheControl().toString();
                        if(TextUtils.isEmpty(cacheControl))
                        {
                            cacheControl =String.format("public, max-age=%s,max-stale=%s",max_age,max_stale);
                        }
                        return response.newBuilder().header("Cache-Control", cacheControl) .removeHeader("Pragma") .build();
                    }
                }).cache(new Cache(cacheDir,cachesize)).build();
    }
    public OkHttpClient getInstance(){
        return this.client;
    }
}
