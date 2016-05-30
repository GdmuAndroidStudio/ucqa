package com.dawnlightning.ucqa.api.apimanager;

import com.dawnlightning.ucqa.api.httphelper.RetrofitHelper;
import com.dawnlightning.ucqa.bean.ApiBase;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作者：Administrator on 2016/5/15 20:16
 * 邮箱：823894716@qq.com
 */
public class IssueApiManager {
    private Retrofit retrofit;
    public IssueApiService IssueApiService;
    public IssueApiManager(){
        this.retrofit=new RetrofitHelper().getInstance();
        this.IssueApiService=retrofit.create(IssueApiService.class);
    }
    public IssueApiManager(final int max_age,final int max_stale){
        this.retrofit=new RetrofitHelper(max_age,max_stale).getInstance();
        this.IssueApiService=retrofit.create(IssueApiService.class);
    }
    public  Observable<ApiBase> PublicIssue(String m_auth,Map<String,Object> data){
        return IssueApiService.doPublicIssue(m_auth, data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }
    public  Observable<ApiBase> UploadPicture(String m_auth,Map<String,RequestBody> data){
        return IssueApiService.doUploadPicture(m_auth,data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public interface IssueApiService{

        /**
         * 发布咨询
         * @param m_auth 登陆后的秘钥
         * @param IssueData 发布咨询的内容
         * @return
         */
        @FormUrlEncoded
        @POST("/capi/cp.php?ac=bwzt")
        Observable<ApiBase> doPublicIssue(@Query("m_auth") String m_auth, @FieldMap Map<String, Object> IssueData);

        /**
         * 上传咨询描述图片
         * @param m_auth 登陆返回的m_auth
         * @param params 上传图片相关配置
         * @return
         *
         */
        @Multipart
        @POST("/capi/cp.php?ac=upload")
        Observable<ApiBase> doUploadPicture(@Query("m_auth") String m_auth,@PartMap  Map<String, RequestBody> data);
    }
}
