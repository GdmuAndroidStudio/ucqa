package com.dawnlightning.ucqa.presenter;

import android.content.Context;
import android.widget.Toast;

import com.dawnlightning.ucqa.bean.response.account.UpdateBean;
import com.dawnlightning.ucqa.db.SharedPreferenceDb;
import com.dawnlightning.ucqa.model.AccountModel;
import com.dawnlightning.ucqa.update.UpdateManager;
import com.dawnlightning.ucqa.utils.DataCleanManager;
import com.dawnlightning.ucqa.utils.SdCardUtil;
import com.dawnlightning.ucqa.viewinterface.ISettingView;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

/**
 * Created by Kyo on 2016/7/11.
 */
public class SettingPresenter {

    public ISettingView iSettingView;
    public Context mContext;
    private UpdateManager updateManager;
    private AccountModel accountModel;

    public SettingPresenter(ISettingView iSettingView, Context mContext){
        this.iSettingView=iSettingView;
        this.mContext=mContext;
        accountModel = new AccountModel();
    }

    public void checkUpDate(){
        accountModel.CheckUpdate(new AccountModel.UpdateListener() {
            @Override
            public void needUpdate(UpdateBean bean) {
                iSettingView.doUpDate(bean);
            }

            @Override
            public void noUpdate() {
                Toast.makeText(mContext,"已为最新版本",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
