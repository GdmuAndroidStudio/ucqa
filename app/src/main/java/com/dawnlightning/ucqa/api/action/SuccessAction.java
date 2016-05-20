package com.dawnlightning.ucqa.api.action;

import android.util.Log;
import com.dawnlightning.ucqa.bean.ApiBase;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.utils.L;

import java.lang.reflect.ParameterizedType;

import rx.functions.Action1;

/**
 * 作者：Administrator on 2016/5/16 11:22
 * 邮箱：823894716@qq.com
 */
public abstract  class SuccessAction<T> implements Action1<ApiBase> {
    private Class<T> type;
    public SuccessAction(){
        this.type = (Class<T>) ((ParameterizedType) super.getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }
    @Override
    public void call(ApiBase apiBase) {
        if (apiBase.getCode()==0){
            Success(Convert(apiBase,type));
        }else{
            Failure(apiBase.getCode(),apiBase.getMsg());
        }

    }
    /**
     * json字符串转成对象
     * @paramApiBase对象
     * @param type 转换的类型
     * @return
     */
    public static  <T> T Convert(ApiBase base, Class<T> type) {
        Gson gson = new Gson();
        return gson.fromJson(base.getData().toString(), type);
    }
    public abstract void Success(T target);
    public abstract void Failure(int code,String msg);
}
