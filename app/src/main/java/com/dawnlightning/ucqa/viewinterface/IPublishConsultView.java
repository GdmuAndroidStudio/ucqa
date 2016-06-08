package com.dawnlightning.ucqa.viewinterface;

import android.widget.Button;
import android.widget.EditText;

import com.dawnlightning.ucqa.activity.ConsultActivity;
import com.dawnlightning.ucqa.adapter.ConsultPicsAdapter;
import com.dawnlightning.ucqa.bean.others.UploadPicsBean;

import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 2016/5/28.
 */
public interface IPublishConsultView {
    public ConsultPicsAdapter getConsultPicsAdapter();
    public List<UploadPicsBean> getUploadPicBeabList();
    public void sendSuccess(Object object);
    public void sendFailure(String msg);
    public void sendError(String msg);
    public void uploadPicError(int picid, File file);
    public void savePicid(int picid, String strpicid);//保存服务器返回的图片ID
    public void updatePb(int pbid, int present);//更新图片上传进度
}
