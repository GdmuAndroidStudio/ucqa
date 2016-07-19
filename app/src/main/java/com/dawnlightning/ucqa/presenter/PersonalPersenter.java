package com.dawnlightning.ucqa.presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.dawnlightning.ucqa.bean.others.UploadPicsBean;
import com.dawnlightning.ucqa.bean.others.UserDataBean;
import com.dawnlightning.ucqa.common.Code;
import com.dawnlightning.ucqa.model.AccountModel;
import com.dawnlightning.ucqa.utils.DataCleanManager;
import com.dawnlightning.ucqa.utils.SdCardUtil;
import com.dawnlightning.ucqa.viewinterface.IPersonalView;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

/**
 * Created by Administrator on 2016/4/20.
 */
public class PersonalPersenter{
    private IPersonalView view;
    private AccountModel model;
    public PersonalPersenter(IPersonalView view){
        this.view=view;
        model=new AccountModel();
    }

    public void uploadAvater(UploadPicsBean uploadPicsBean, String m_auth) {
        model.UploadAvater(uploadPicsBean,mHandle);
    }

    public void modifyPersonalData(UserDataBean bean, String m_auth) {
        model.EditPersonalData(m_auth, bean, new AccountModel.AccountListener() {
            @Override
            public void doSuccess(Object bean) {
                view.showSuccess(bean.toString());
                Log.i("changesuccess","...");
                view.clearCache();
            }

            @Override
            public void doFailure(String msg) {
                view.showFailure(msg);
            }

            @Override
            public void doError(String msg) {
                view.showError(msg);
            }
        });
    }

    public Handler mHandle=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case Code.UPLOADSUCCESS:
                    view.clearCache();
                    view.showAvtar(msg.obj.toString());
                    break;
                case Code.UPLOADCHANGE:
                    view.updatePb(Integer.parseInt(msg.obj.toString()));
                    break;
                case Code.UPLOADFAILURE:
                    view.showError(msg.obj.toString());
                    break;
            }
            super.handleMessage(msg);
        }
    };
}
