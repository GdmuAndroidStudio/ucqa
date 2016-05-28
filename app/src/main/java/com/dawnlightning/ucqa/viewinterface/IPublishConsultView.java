package com.dawnlightning.ucqa.viewinterface;

import android.widget.Button;
import android.widget.EditText;

import com.dawnlightning.ucqa.activity.ConsultActivity;
import com.dawnlightning.ucqa.adapter.ConsultPicsAdapter;
import com.dawnlightning.ucqa.bean.others.UploadPicsBean;

import java.util.List;

/**
 * Created by Administrator on 2016/5/28.
 */
public interface IPublishConsultView {
    public ConsultPicsAdapter getConsultPicsAdapter();
    public List<UploadPicsBean> getUploadPicBeabList();
    public Button getSubmitButton();
    public ConsultActivity getConsultActivity();
    public EditText getEtConsultMessage();
}
