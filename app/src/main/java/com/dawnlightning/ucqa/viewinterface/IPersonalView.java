package com.dawnlightning.ucqa.viewinterface;

/**
 * Created by Administrator on 2016/4/20.
 */
public interface IPersonalView {
    public void showFailure(String msg);
    public void showError(String msg);
    public void showSuccess(String msg);
    public void showAvtar(String url);
    public void updatePb(int present);//更新图片上传进度
    public void clearCache();
}
