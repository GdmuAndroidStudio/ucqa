package com.dawnlightning.ucqa.presenter;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dawnlightning.ucqa.R;
import com.dawnlightning.ucqa.activity.ConsultActivity;
import com.dawnlightning.ucqa.adapter.ConsultPicsAdapter;
import com.dawnlightning.ucqa.bean.others.ConsultBean;
import com.dawnlightning.ucqa.bean.others.UploadPicsBean;
import com.dawnlightning.ucqa.common.Code;
import com.dawnlightning.ucqa.model.ConsultPublicModel;
import com.dawnlightning.ucqa.viewinterface.IPublishConsultView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/28.
 */
public class PublishConsultPresenter {
    private ConsultPicsAdapter consultPicsAdapter;
    private IPublishConsultView iPublishConsultView;
    private ConsultPublicModel consultPublicModel = new ConsultPublicModel();

    public PublishConsultPresenter(IPublishConsultView iPublishConsultView){
        this.iPublishConsultView = iPublishConsultView;
        this.consultPicsAdapter = iPublishConsultView.getConsultPicsAdapter();
    }


    public void dosentconsult(ConsultBean consultBean,String othur) {
        consultPublicModel.PublicIssuse(consultBean,othur,new ConsultPublicModel.PublicListener(){
            @Override
            public void sendSuccess(Object object) {
                Log.i("test"," "+"成功");
                iPublishConsultView.sendSuccess(object);

            }

            @Override
            public void sendFailure(String msg) {
                Log.i("test"," "+msg);
                iPublishConsultView.sendFailure(msg);
            }

            @Override
            public void sendError(String msg) {
                Log.i("test"," "+msg);
                iPublishConsultView.sendError(msg);

            }
        });
    }

    public void uploadPicture(List<UploadPicsBean> list, final String m_auth){
         consultPublicModel.UploadPicture(list,m_auth,mHandle);
    }

    public Handler mHandle=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case Code.UPLOADSUCCESS:
                    iPublishConsultView.savepicid(msg.arg1,msg.obj.toString());
                    break;
                case Code.UPLOADCHANGE:
                    iPublishConsultView.updatepb(msg.arg1,Integer.parseInt(msg.obj.toString()));
                    break;
                case Code.UPLOADFAILURE:
                    iPublishConsultView.uploadpicerror(msg.arg1,(File)msg.obj);
                    break;
            }
            super.handleMessage(msg);
        }
    };
}
