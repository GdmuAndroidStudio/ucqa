package com.dawnlightning.ucqa.presenter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dawnlightning.ucqa.R;
import com.dawnlightning.ucqa.activity.ConsultActivity;
import com.dawnlightning.ucqa.adapter.ConsultPicsAdapter;
import com.dawnlightning.ucqa.bean.others.UploadPicsBean;
import com.dawnlightning.ucqa.viewinterface.IPublishConsultView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/28.
 */
public class PublishConsultPresenter {

    private Context context;
    private ConsultPicsAdapter consultPicsAdapter;
    private IPublishConsultView iPublishConsultView;
    private Button btConsultSubmit;
    private ConsultActivity consultActivity;
    private List<String> picids = new ArrayList<String>();//用于存储服务器回调的picsid
    private EditText etConsultMessage;

    public PublishConsultPresenter(IPublishConsultView iPublishConsultView, Context context){
        this.iPublishConsultView = iPublishConsultView;
        this.consultPicsAdapter = iPublishConsultView.getConsultPicsAdapter();
        this.context = context;
        btConsultSubmit = iPublishConsultView.getSubmitButton();
        consultActivity = iPublishConsultView.getConsultActivity();
        etConsultMessage = iPublishConsultView.getEtConsultMessage();
    }

    private void sendConsultSuccess(int code, String msg){
            /*String bwztid=msg.substring(msg.lastIndexOf("=")+1);
        Intent intent = new Intent();
        intent.setClass(getActivity(), DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("userdata",consultActivity.userBean);
        bundle.putString("bwztid", bwztid);
        bundle.putString("uid",consultActivity.userBean.getUserdata().getUid());
        intent.putExtras(bundle);
        startActivity(intent);
        showerror(code,"发布成功");
        getActivity().finish();*/
    }

    public void uploadpicerror(int picid, File file) {
        setButtonClickable();
        showerror(0, "图片上传失败");
        btConsultSubmit.setText("继续上传");
    }

    private void sendConsultFailure(int code, String msg){
        setButtonClickable();
        showerror(code, msg);
    }

    public void showerror(int code, String msg) {
        consultActivity.showmessage(msg, Toast.LENGTH_SHORT);
    }

    //设置提交按钮是否可点击
    private void setButtonClickable() {
        if (btConsultSubmit.isClickable()) {
            btConsultSubmit.setBackgroundColor(((Fragment)iPublishConsultView).getResources().getColor(R.color.lightgray));
            btConsultSubmit.setClickable(false);

        } else {
            btConsultSubmit.setBackgroundColor(((Activity)iPublishConsultView).getResources().getColor(R.color.green));
            btConsultSubmit.setClickable(true);
        }
    }

    public void savepicid(int picid, String strpicid) {
        ((UploadPicsBean)consultPicsAdapter .getitem(picid)).setPresent(100);
        consultPicsAdapter.notifyDataSetChanged();
        picids.add(strpicid);
        if (picids.size() == consultPicsAdapter.getItemCount()) {
            sendconsult();
        }

    }

    public void sendconsult() {
        consultActivity.consultBean.setMessage(etConsultMessage.getText().toString());
        consultActivity.consultBean.setSubject(etConsultMessage.getText().toString());
        consultActivity.consultBean.setPicids(picids);
//        consultPresenter.dosentconsult(consultActivity.consultBean);
    }


    public void updatepb(int pbid, int present) {
        ((UploadPicsBean) consultPicsAdapter.getitem(pbid)).setPresent(present);
        consultPicsAdapter.notifyDataSetChanged();
    }


}
