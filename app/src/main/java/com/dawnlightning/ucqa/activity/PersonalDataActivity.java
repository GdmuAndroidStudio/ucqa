package com.dawnlightning.ucqa.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dawnlightning.ucqa.R;
import com.dawnlightning.ucqa.base.BaseActivity;
import com.dawnlightning.ucqa.bean.others.UploadPicsBean;
import com.dawnlightning.ucqa.bean.others.UserBean;
import com.dawnlightning.ucqa.bean.others.UserDataBean;
import com.dawnlightning.ucqa.common.Code;
import com.dawnlightning.ucqa.db.SharedPreferenceDb;
import com.dawnlightning.ucqa.presenter.PersonalPersenter;
import com.dawnlightning.ucqa.utils.DataCleanManager;
import com.dawnlightning.ucqa.utils.ImageLoaderOptions;
import com.dawnlightning.ucqa.utils.NetUtils;
import com.dawnlightning.ucqa.utils.SdCardUtil;
import com.dawnlightning.ucqa.viewinterface.IPersonalView;
import com.dawnlightning.ucqa.widget.ProcessImageView;
import com.dawnlightning.ucqa.widget.wheelview.NumericWheelAdapter;
import com.dawnlightning.ucqa.widget.wheelview.OnWheelScrollListener;
import com.dawnlightning.ucqa.widget.wheelview.WheelView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/4/20.
 */
public class PersonalDataActivity extends BaseActivity implements IPersonalView {
    @Bind(R.id.iv_personaldata_back)
    ImageView iv_personaldata_back;
    @Bind(R.id.tv_setting_title)
    TextView tvSettingTitle;
    @Bind(R.id.tv_personaldata_save)
    TextView tv_personaldata_save;
    @Bind(R.id.rl_setting_title)
    RelativeLayout rlSettingTitle;
    @Bind(R.id.iv_personal_avatar)
    ImageView iv_personaldata_avatar;
    @Bind(R.id.imageView_3)
    ImageView imageView3;
    @Bind(R.id.rl_personaldata_avatar)
    RelativeLayout rl_personaldata_avatar;
    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.et_personaldata_name)
    EditText et_personaldata_name;
    @Bind(R.id.tv_personaldata_sex)
    TextView tv_personaldata_sex;
    @Bind(R.id.rl_personaldata_sex)
    RelativeLayout rl_personaldata_sex;
    @Bind(R.id.tv_personaldata_age)
    TextView tv_personaldata_age;
    @Bind(R.id.rl_personaldata_age)
    RelativeLayout rl_personaldata_age;
    private UserBean userBean;
    private WheelView tv_dialog_year;
    private WheelView tv_dialog_month;
    private WheelView tv_dialog_day;
    private TextView tv_dialog_age;
    private ProcessImageView piv_avater;//头像上传图片框
    PopupWindow menuWindow;
    private PersonalPersenter personalPersenter;
    private UserDataBean bean = new UserDataBean();
    String fileName = String.valueOf(System.currentTimeMillis()) + ".jpg";
    String avaterurl = "";
    String username = "";
    private String othur = "86dbHma%2FLr19gbGC%2FCH16GG%2FmBowm8P5Lai2amtBgoBerRxT5F34SPqeqC%2FbTg6zX%2FTjhlAEGtLKTbozWIZm%2FKk";
    private UploadPicsBean uploadPicsBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setNeedBackGesture(true);//设置需要手势监听
        setContentView(R.layout.activity_personaldata);
        ButterKnife.bind(this);
        initdata();
        initevent();
    }

    public void initdata() {
        personalPersenter=new PersonalPersenter(this);
        userBean = (UserBean) this.getIntent().getSerializableExtra("userdata");
        bean.setFormhash(userBean.getFormhash());
        avaterurl = userBean.getUserdata().getAvatar_url();
        ImageLoader.getInstance().displayImage(userBean.getUserdata().getAvatar_url(), iv_personaldata_avatar, ImageLoaderOptions.getListOptions());
        if (userBean.getUserdata().getName().length() > 0) {
            et_personaldata_name.setText(userBean.getUserdata().getName());
            username = userBean.getUserdata().getName();
        } else {

            et_personaldata_name.setText(userBean.getUserdata().getUsername());
            username = userBean.getUserdata().getUsername();
        }
        if (userBean.getUserdata().getSex().length() > 0) {
            tv_personaldata_sex.setText(userBean.getUserdata().getSex());
        } else {
            tv_personaldata_sex.setText("未设置");
        }
        if (userBean.getUserdata().getAge().length() > 0) {
            Calendar mycalendar = Calendar.getInstance();//获取现在时间
            int year = mycalendar.get(Calendar.YEAR);//获取年份
            int month = mycalendar.get(Calendar.MONTH);
            int day = mycalendar.get(Calendar.DAY_OF_WEEK);
            // 用文本框输入年龄
            int age = Integer.parseInt(userBean.getUserdata().getAge());
            int birth = year - age;
            bean.setBirthyear(birth);
            bean.setBirthmonth(month);
            bean.setBirthday(day);
            tv_personaldata_age.setText(userBean.getUserdata().getAge() + "岁");
        } else {
            tv_personaldata_age.setText("未设置");
        }
    }

    public void initevent() {
        rl_personaldata_age.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopwindow(getDataPick());
            }
        });
        rl_personaldata_avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopwindow(picturepike());
            }
        });
        rl_personaldata_sex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopwindow(sexselectdialog());
            }
        });
        iv_personaldata_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_personaldata_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username = et_personaldata_name.getText().toString();
                bean.setName(username);
                if(NetUtils.isNetworkConnected(getcontext())) {
                    personalPersenter.modifyPersonalData(bean, userBean.getM_auth());
                }
                else
                    showError("网络出错！");
            }
        });
        iv_personaldata_avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(PersonalDataActivity.this, DisplayActivity.class);
                intent.putExtra("image", userBean.getUserdata().getAvatar_url());
                startActivity(intent);
            }
        });
    }

    /**
     * 初始化popupWindow
     *
     * @param view
     */
    private void showPopwindow(View view) {
        menuWindow = new PopupWindow(view, ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        menuWindow.setFocusable(true);
        menuWindow.setBackgroundDrawable(new BitmapDrawable());
        menuWindow.showAtLocation(view, Gravity.CENTER_HORIZONTAL, 0, 0);
        menuWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                menuWindow = null;
            }
        });
    }

    /**
     * @return
     */
    private View getDataPick() {
        Calendar c = Calendar.getInstance();
        int curYear = c.get(Calendar.YEAR);
        int curMonth = c.get(Calendar.MONTH) + 1;//通过Calendar算出的月数要+1
        int curDate = c.get(Calendar.DATE);
        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_age, null);

        tv_dialog_year = (WheelView) view.findViewById(R.id.wv_dialog_year);
        tv_dialog_year.setAdapter(new NumericWheelAdapter(1930, curYear));
        tv_dialog_year.setLabel("年");
        tv_dialog_year.setCyclic(true);
        tv_dialog_year.addScrollingListener(scrollListener);

        tv_dialog_month = (WheelView) view.findViewById(R.id.wv_dialog_month);
        tv_dialog_month.setAdapter(new NumericWheelAdapter(1, 12));
        tv_dialog_month.setLabel("月");
        tv_dialog_month.setCyclic(true);
        tv_dialog_month.addScrollingListener(scrollListener);

        tv_dialog_day = (WheelView) view.findViewById(R.id.wv_dialog_day);
        initDay(curYear, curMonth);
        tv_dialog_day.setLabel("日");
        tv_dialog_day.setCyclic(true);

        tv_dialog_year.setCurrentItem(curYear - 1930);
        tv_dialog_month.setCurrentItem(curMonth - 1);
        tv_dialog_day.setCurrentItem(curDate - 1);

        tv_dialog_age = (TextView) view.findViewById(R.id.tv_dialog_age);
        tv_dialog_age.setText("0岁");
        Button bt = (Button) view.findViewById(R.id.set);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_personaldata_age.setText(tv_dialog_age.getText().toString());
                bean.setBirthyear(tv_dialog_year.getCurrentItem() + 1930);
                bean.setBirthmonth(tv_dialog_month.getCurrentItem() + 1);
                bean.setBirthday(tv_dialog_day.getCurrentItem() + 1);
                menuWindow.dismiss();
            }
        });

        return view;
    }

    OnWheelScrollListener scrollListener = new OnWheelScrollListener() {

        @Override
        public void onScrollingStarted(WheelView wheel) {

        }

        @Override
        public void onScrollingFinished(WheelView wheel) {
            // TODO Auto-generated method stub
            String str = (tv_dialog_year.getCurrentItem() + 1930) + "-" + (tv_dialog_month.getCurrentItem() + 1) + "-" + (tv_dialog_day.getCurrentItem() + 1);
            calculateAge(str);
        }
    };

    private void calculateAge(String str) {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Date mydate;
        try {
            mydate = myFormatter.parse(str);
            int day = (int) ((date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000) + 1);
            int age = day / 365;

            tv_dialog_age.setText(age + "岁");
        } catch (ParseException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }

    }

    /**
     * @param year
     * @param month
     * @return
     */
    private int getDay(int year, int month) {
        int day = 30;
        boolean flag = false;
        switch (year % 4) {
            case 0:
                flag = true;
                break;
            default:
                flag = false;
                break;
        }
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                day = 31;
                break;
            case 2:
                day = flag ? 29 : 28;
                break;
            default:
                day = 30;
                break;
        }
        return day;
    }

    /**
     */
    private void initDay(int arg1, int arg2) {
        tv_dialog_day.setAdapter(new NumericWheelAdapter(1, getDay(arg1, arg2), "%02d"));
    }

    private View picturepike() {
        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_picturepike, null);
        Button bt_album = (Button) view.findViewById(R.id.bt_personaldata_picture_album);
        bt_album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SdCardUtil.checkSdCard() == true) {
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");// 相片类型
                    startActivityForResult(intent, 2);
                } else {
                    showError("SD卡不存在");
                }
                menuWindow.dismiss();
            }
        });
        Button bt_camera = (Button) view.findViewById(R.id.bt_personaldata_picture_camera);
        bt_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Uri imageUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), fileName));
                openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(openCameraIntent, 1);
                menuWindow.dismiss();
            }
        });
        Button bt_cancel = (Button) view.findViewById(R.id.bt_personaldata_picture_cancel);
        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuWindow.dismiss();
            }
        });
        return view;
    }

    private View sexselectdialog() {
        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_sex, null);
        final Button bt_man = (Button) view.findViewById(R.id.bt_personaldata_sex_man);
        bt_man.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_personaldata_sex.setText("男");
                bean.setSex(1);
                menuWindow.dismiss();
            }
        });
        Button bt_woman = (Button) view.findViewById(R.id.bt_personaldata_sex_woman);
        bt_woman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_personaldata_sex.setText("女");
                bean.setSex(2);
                menuWindow.dismiss();
            }
        });
        Button bt_cancel = (Button) view.findViewById(R.id.bt_personaldata_sex_cancel);
        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuWindow.dismiss();
            }
        });
        return view;
    }

    @Override
    public void showError(String msg) {
        showmessage(msg, Toast.LENGTH_SHORT);
    }

    @Override
    public void showFailure(String msg) {
        showmessage(msg, Toast.LENGTH_SHORT);
    }

    @Override
    public void showSuccess(String msg) {
        showmessage(msg, Toast.LENGTH_SHORT);
        SharedPreferenceDb sharedPreferenceDb = getMySharedPreferenceDb();
        String phone = sharedPreferenceDb.getPhone();
        String password = sharedPreferenceDb.getPassword();
//        personalPersenter.doclearlogincache(phone,password);
        finish();
    }

    @Override
    public void clearCache() {
        File cacheDir = StorageUtils.getOwnCacheDirectory(getcontext(), SdCardUtil.FILEDIR + "/" + SdCardUtil.FILECACHE);//获取到缓存的目录地址
        try{
            DataCleanManager.deleteFilesByDirectory(cacheDir);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showAvtar(String url) {
        menuWindow.dismiss();
        avaterurl = url;
        ImageLoader.getInstance().clearMemoryCache();
        ImageLoader.getInstance().clearDiscCache();
        ImageLoader.getInstance().displayImage(url, iv_personaldata_avatar, ImageLoaderOptions.getListOptions());
    }

    @Override
    public void updatePb(int present) {
        piv_avater.setProgress(present);
    }

    public void saveImageToFile(Bitmap bitmap) {
        SdCardUtil.createFileDir(SdCardUtil.FILEDIR + "/" + "/icon" + "/");
        FileOutputStream fos = null;
        String fileName = SdCardUtil.getSdPath() + SdCardUtil.FILEDIR + "/"
                + "/icon" + "/" + "userIcon"
                + String.valueOf(System.currentTimeMillis()) + ".jpg";

        File f = new File(fileName);
        if (f.exists()) {
            f.delete();
        }
        try {
            fos = new FileOutputStream(fileName);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                fos.flush();
                if (bitmap != null) {
                    bitmap.recycle();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                showPopwindow(showpivturesentdialog(fileName));
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int respCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, respCode, data);
        if (requestCode == 1 && respCode == RESULT_OK) {
            Uri uri = null;
            if (data != null) {
                uri = data.getData();
                System.out.println("Data");
            } else {
                uri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), fileName));
            }
            cropImage(uri, 120, 120, 3);
        } else if (requestCode == 2 && respCode == RESULT_OK) {

            Uri uri = data.getData();
            try {

                cropImage(uri, 120, 120, 3);

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (requestCode == 3 && respCode == RESULT_OK) {
            Bitmap photo = null;
            Uri photoUri = data.getData();
            if (photoUri != null) {
                photo = BitmapFactory.decodeFile(photoUri.getPath());
            }
            if (photo == null) {
                Bundle extra = data.getExtras();
                if (extra != null) {
                    photo = (Bitmap) extra.get("data");
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    photo.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                }
            }
            if (photo != null) {

                saveImageToFile(photo);
            }

        }
    }

    //截取图片
    public void cropImage(Uri uri, int outputX, int outputY, int requestCode) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);
        intent.putExtra("outputFormat", "JPEG");
        intent.putExtra("noFaceDetection", true);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, requestCode);
    }

    public View showpivturesentdialog(String filename) {
        final File file = new File(filename);
        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_avater, null);
        Button bt_sent = (Button) view.findViewById(R.id.bt_personaldata_picture_sent);
        uploadPicsBean = new UploadPicsBean();
        uploadPicsBean.setM_auth(othur);
        uploadPicsBean.setPicture(file);
        bt_sent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(NetUtils.isNetworkConnected(getcontext()))
                    personalPersenter.uploadAvater(uploadPicsBean, userBean.getM_auth());
                else
                    showError("网络出错！");
            }
        });
        Button bt_cancel = (Button) view.findViewById(R.id.bt_personaldata_picture_cancel);
        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuWindow.dismiss();
            }
        });
        piv_avater = (ProcessImageView) view.findViewById(R.id.piv_personaldata_picture);
        ImageLoader.getInstance().displayImage("file://" + filename, piv_avater, ImageLoaderOptions.getLoadPictureOptions());
        return view;
    }

    @Override
    public void finish() {
        Intent intent = new Intent();
        intent.putExtra("username", username);
        intent.putExtra("avatar", avaterurl);
        setResult(Code.ModifyPersonaldataForResult, intent);
        super.finish();
    }
}
