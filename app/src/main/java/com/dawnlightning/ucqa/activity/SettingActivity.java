package com.dawnlightning.ucqa.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.dawnlightning.ucqa.R;
import com.dawnlightning.ucqa.base.BaseActivity;
import com.dawnlightning.ucqa.bean.others.UserBean;
import com.dawnlightning.ucqa.bean.response.account.UpdateBean;
import com.dawnlightning.ucqa.db.SharedPreferenceDb;
import com.dawnlightning.ucqa.presenter.SettingPresenter;
import com.dawnlightning.ucqa.update.UpdateManager;
import com.dawnlightning.ucqa.update.UpdateStatus;
import com.dawnlightning.ucqa.utils.DataCleanManager;
import com.dawnlightning.ucqa.utils.SdCardUtil;
import com.dawnlightning.ucqa.viewinterface.ISettingView;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Kyo on 2016/7/11.
 */
public class SettingActivity extends BaseActivity implements ISettingView {

    @Bind(R.id.iv_setting_back)
    ImageView ivSettingBack;
    @Bind(R.id.rl_setting_title)
    RelativeLayout rlSettingTitle;
    @Bind(R.id.re_setting_help)
    RelativeLayout reSettingHelp;
    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.tv_setting_cahesize)
    TextView tvSettingCahesize;
    @Bind(R.id.imageView)
    ImageView imageView;
    @Bind(R.id.tb_setting_push)
    ToggleButton tbSettingPush;
    @Bind(R.id.re_setting_push)
    RelativeLayout reSettingPush;
    @Bind(R.id.textView3)
    TextView textView3;
    @Bind(R.id.tv_setting_update_status)
    TextView tvSettingUpdateStatus;
    @Bind(R.id.tv_setting_version)
    TextView tvSettingVersion;
    @Bind(R.id.imageView_3)
    ImageView imageView3;
    @Bind(R.id.re_setting_checkupdate)
    RelativeLayout reSettingCheckupdate;
    @Bind(R.id.re_setting_aboutme)
    RelativeLayout reSettingAboutme;
    @Bind(R.id.bt_setting_logoff)
    Button btSettingLogoff;
    @Bind(R.id.tv_setting_title)
    TextView tvSettingTitle;
    @Bind(R.id.re_setting_clearcahe)
    RelativeLayout reSettingClearcahe;

    private UserBean userBean;
    private SettingPresenter settingPresenter;
    private UpdateManager updateManager;
    private UpdateBean updateBean;
    private SharedPreferenceDb sharedPreferenceDb;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        setNeedBackGesture(true);//设置需要手势监听
        userBean = (UserBean) getIntent().getSerializableExtra("userdata");
        settingPresenter = new SettingPresenter(this, getcontext());
        initData();
        initEvent();

    }

    public void getVersion() {
        tvSettingVersion.setText("V" + String.valueOf(updateManager.getversionname()));
    }

    private void getPushStatus(){
        sharedPreferences= getSharedPreferences("setting",
                Activity.MODE_PRIVATE);
        if(sharedPreferences.getString("push","true").equals("true")){
            tbSettingPush.setChecked(true);
        }else{
            tbSettingPush.setChecked(false);
        }
    }

    @Override
    public void doUpDate(UpdateBean updateBean) {
        this.updateBean = updateBean;
        Log.d("kyo", "Version = V" + updateBean.getName());
        updateManager.showNoticeDialog(updateBean);

    }

    private void initData() {
        updateManager = new UpdateManager(getcontext());
        sharedPreferenceDb=new SharedPreferenceDb(getcontext());
        getCacheSize();
        getVersion();
        getPushStatus();
    }

    private void initEvent(){
        editor = sharedPreferences.edit();
        tbSettingPush.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d("kyo","" + isChecked);
                sharedPreferenceDb.setPush(isChecked);
                if(isChecked == true){
                    editor.putString("push","true");
                    Toast.makeText(getcontext(),"已打开推送",Toast.LENGTH_SHORT).show();
                }else{
                    editor.putString("push","false");
                    Toast.makeText(getcontext(),"已关闭推送",Toast.LENGTH_SHORT).show();
                }
                editor.commit();
            }
        });
    }


    private void showcachesize(String cachesize) {
        tvSettingCahesize.setText(cachesize);
    }

    private void clearcache() {
        File cacheDir = StorageUtils.getOwnCacheDirectory(this, SdCardUtil.FILEDIR + "/" + SdCardUtil.FILECACHE);//获取到缓存的目录地址
        try {
            DataCleanManager.deleteFilesByDirectory(cacheDir);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Toast.makeText(this, "清除成功", Toast.LENGTH_SHORT).show();
        getCacheSize();

    }

    private void getCacheSize() {
        File cacheDir = StorageUtils.getOwnCacheDirectory(this, SdCardUtil.FILEDIR + "/" + SdCardUtil.FILECACHE);//获取到缓存的目录地址
        try {
            showcachesize(DataCleanManager.getCacheSize(cacheDir));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @OnClick({R.id.iv_setting_back, R.id.re_setting_clearcahe, R.id.re_setting_checkupdate})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_setting_back:
                finish();
                break;
            case R.id.re_setting_clearcahe:
                clearcache();
                break;
            case R.id.re_setting_checkupdate:
                settingPresenter.checkUpDate();
                break;
        }
    }
}
