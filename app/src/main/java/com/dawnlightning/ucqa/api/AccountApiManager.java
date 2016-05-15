package com.dawnlightning.ucqa.api;

import com.dawnlightning.ucqa.api.httphelper.RetrofitHelper;
import com.dawnlightning.ucqa.api.requestbody.ProgressRequestBody;
import com.dawnlightning.ucqa.bean.response.account.GetAvatarBean;
import com.dawnlightning.ucqa.bean.response.account.GetSeccodeBean;
import com.dawnlightning.ucqa.bean.response.account.LoginBean;
import com.dawnlightning.ucqa.bean.response.account.LoginOutBean;
import com.dawnlightning.ucqa.bean.response.account.ProfileBean;
import com.dawnlightning.ucqa.bean.response.account.RegisterBean;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * 作者：Administrator on 2016/5/15 20:14
 * 邮箱：823894716@qq.com
 * 用于操作用户信息的api
 */
public class AccountApiManager {
    Retrofit retrofit;
    AccountApiService accountApiManager;
    public AccountApiManager(final  int max_age,final int max_stale){
        this.retrofit= new RetrofitHelper(max_age,max_stale).getInstance();
        this.accountApiManager=this.retrofit.create(AccountApiService.class);
    }
    public  Observable<LoginBean> Login(String username,String password){
       return accountApiManager.doLogin(username,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }
    public  Observable<GetSeccodeBean> GetSeccode(){
        return accountApiManager.getSeccode()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public  Observable<RegisterBean> Register(final String username,final String password,final GetSeccodeBean seccodeBean ){
        return accountApiManager.doRegister(username,password,password,seccodeBean.getData().getSeccode_auth(),seccodeBean.getData().getSeccode())
                 .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public Observable<LoginOutBean> Loginoff(String uhash){
        return accountApiManager.doLoginoff(uhash)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public  Observable<GetAvatarBean> UploadAvater(String m_auth,ProgressRequestBody imgs){
        return accountApiManager.doUploadAvatar(m_auth,imgs)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public  Observable<ProfileBean> EditProfile(String m_auth,Map<String,String> map){
        return accountApiManager.doEditProfile(m_auth,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public interface AccountApiService {
        /**
         * 登陆
         * @param username 用户名
         * @param password 密码
         * @return 登陆结果
         */
        @GET("/capi/do.php?ac=login&loginsubmit=true")
        Observable<LoginBean> doLogin(@Query("username") String username, @Query("password") String password);

        /**
         * 获取防伪验证码
         */
        @Headers("Cache-Control:no-cache")
        @GET("/capi/do.php?ac=register&op=seccode")
        Observable<GetSeccodeBean> getSeccode();

        /**
         * 注册
         * @param username 用户名
         * @param password  第一次密码
         * @param password2 第二次密码
         * @param m_auth  soccode返回的结果
         * @return
         */
        @Headers("Cache-Control:no-cache")
        @GET("/capi/do.php?ac=register&registersubmit=true")
        Observable<RegisterBean> doRegister(@Query("username") String username,@Query("password") String password,@Query("password2") String password2,@Query("m_auth") String m_auth,@Query("seccode")String seccode);

        /**
         * 注销
         * @param uhash 登陆返回的uhash
         * @return
         */
        @GET("/capi/cp.php?ac=common&op=logout")
        Observable<LoginOutBean> doLoginoff(@Query("uhash") String uhash);


        /**
         * 上传用户头像
         * @param m_auth 登陆返回的m_auth
         * @param imgs 图片
         * @return
         */
        @Multipart
        @POST("/capi/cp.php?ac=avatar&m_auth={m_auth}&avatarsubmit=ture")
        Observable<GetAvatarBean> doUploadAvatar(@Path("m_auth") String m_auth,@Part("Filedata") ProgressRequestBody imgs);

        /**
         * 修改用户资料
         * @param m_auth 登陆返回的m_ath
         * @param options 资料
         * @return
         */
        @POST("/capi/cp.php?ac=profile&m_auth={m_auth}&op=base")
        Observable<ProfileBean> doEditProfile(@Path("m_auth") String m_auth,@FieldMap Map<String, String> options);
    }
}
