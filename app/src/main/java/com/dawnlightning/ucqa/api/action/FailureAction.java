package com.dawnlightning.ucqa.api.action;

import com.dawnlightning.ucqa.common.Code;

import rx.functions.Action1;

/**
 * 作者：Administrator on 2016/5/16 11:35
 * 邮箱：823894716@qq.com
 */
public abstract  class FailureAction implements Action1<Throwable> {
    @Override
    public void call(Throwable throwable) {
        Error(throwable.toString());
    }

    public abstract void Error(String msg);
}
