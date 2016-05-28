package com.dawnlightning.ucqa.fragment;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.dawnlightning.ucqa.R;
import com.dawnlightning.ucqa.activity.ConsultActivity;
import com.dawnlightning.ucqa.adapter.ConsultPicsAdapter;
import com.dawnlightning.ucqa.bean.others.UploadPicsBean;
import com.dawnlightning.ucqa.utils.JsonParser;
import com.dawnlightning.ucqa.utils.SdCardUtil;
import com.dawnlightning.ucqa.utils.TimeUtil;
import com.dawnlightning.ucqa.viewinterface.IConsultView;
import com.dawnlightning.ucqa.widget.ColorDialog;
import com.dawnlightning.ucqa.widget.ExpandListView;
import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.LogRecord;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/4/13.
 */
public class ConsultPageTwoFragment extends Fragment implements IConsultView,ColorDialog.OnNegativeListener,ColorDialog.OnPositiveListener{

    @Bind(R.id.et_consult_subject)
    EditText etConsultSubject;
    @Bind(R.id.re_setting_help)
    RelativeLayout reSettingHelp;
    @Bind(R.id.lv_consult_pic)
    RecyclerView lvConsultPic;
    @Bind(R.id.iv_consult_sentvoice)
    ImageView ivConsultSentvoice;
    @Bind(R.id.iv_consult_sentphotos)
    ImageView ivConsultSentphotos;
    @Bind(R.id.iv_consult_sentcamera)
    ImageView ivConsultSentcamera;
    @Bind(R.id.bt_consult_submit)
    Button btConsultSubmit;
    private View headview;
    private EditText etConsultMessage;
    private ConsultPicsAdapter consultPicsAdapter;
    private ConsultActivity consultActivity;
    private List<UploadPicsBean> list = new ArrayList<UploadPicsBean>();
    private List<String> picids = new ArrayList<String>();//用于存储服务器回调的picsid
    private Handler handler;
    private ColorDialog colorDialog;


    //    private ConsultPresenter consultPresenter;
    String fileName = String.valueOf(System.currentTimeMillis()) + ".jpg";

    //List<String> imageUrl=new ArrayList<String>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_condition, container, false);
        headview = inflater.inflate(R.layout.consult_girdview_head, null);
        etConsultMessage=(EditText)view.findViewById(R.id.et_consult_message);
        handler=new Handler();
        ButterKnife.bind(this, view);
        initdata();
        initevent();
        return view;
    }


    @Override
    public void onDestroy() {
        mIatDialog.destroy();
        super.onDestroy();

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        consultActivity = (ConsultActivity) activity;
//        consultPresenter=new ConsultPresenter(this, MyApp.getApp());
        initSpeechRecognizer();
    }

    public void initview() {

    }

    public void initdata() {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        lvConsultPic.setLayoutManager(layoutManager);
        consultPicsAdapter =new ConsultPicsAdapter(getActivity(),list);
        colorDialog = new ColorDialog(getContext());
        colorDialog.setPositiveListener("确定",this);
        colorDialog.setNegativeListener("取消",this);
        lvConsultPic.setAdapter(consultPicsAdapter);
//        lvConsultPic.addView(headview);
    }

    public void initevent() {
        //标题的输入监听
        etConsultSubject.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                setButtonBackground();
            }
        });
        //删除图片的点击事件
        consultPicsAdapter.setDeletePicture(new ConsultPicsAdapter.DeletePicture() {
            @Override
            public void Detele(int postion) {
                list.remove(postion);
                consultPicsAdapter.notifyDataSetChanged();
            }
        });
        //图片描述的输入监听
        consultPicsAdapter.setEditTextListener(new ConsultPicsAdapter.EditTextListener() {
            @Override
            public void AdapterTextChaged(int postion, String str) {
//                    UploadPicsBean bean = (UploadPicsBean) consultPicsAdapter.getitem(postion);
//                    bean.setPicturetitle(str);
//                    Log.d("test",postion+"   "+str+"  ");
                colorDialog.show();
                colorDialog.setContentText(str,postion);
            }
        });
        //咨询正文的输入监听
        etConsultMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                setButtonBackground();
            }
        });
        //语言输入的点击事件
        ivConsultSentvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showlanguagedialog();
            }
        });
        //从相册中选图片
        ivConsultSentphotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getImageForAlbum();
            }
        });
        //照相机选取
        ivConsultSentcamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getImageFromCamera();
            }
        });
        //提交
        btConsultSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (consultPicsAdapter.getUploadPicsBeans().size() > 0) {
//                    list.clear();
//                } else {
                    for(int i=0;i<100;i++) {
                        final int j = i;
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                updatepb(0, j);
                            }
                        }, 1000);
//                    }
                    sendconsult();
                }
                setButtonClickable();
            }
        });
        btConsultSubmit.setClickable(false);
    }

    //设置提交的背景颜色
    private void setButtonBackground() {
        if (etConsultSubject.getText().toString().length() > 0 && etConsultMessage.getText().toString().length() > 0) {
            btConsultSubmit.setBackgroundColor(getActivity().getResources().getColor(R.color.green));
            btConsultSubmit.setClickable(true);
        } else {
            btConsultSubmit.setBackgroundColor(getActivity().getResources().getColor(R.color.lightgray));
            btConsultSubmit.setClickable(false);
        }
    }

    //设置提交按钮是否可点击
    private void setButtonClickable() {
        if (btConsultSubmit.isClickable()) {
            btConsultSubmit.setBackgroundColor(getActivity().getResources().getColor(R.color.lightgray));
            btConsultSubmit.setClickable(false);

        } else {
            btConsultSubmit.setBackgroundColor(getActivity().getResources().getColor(R.color.green));
            btConsultSubmit.setClickable(true);
        }
    }

    //从相册中获取
    private void getImageForAlbum() {

        if (SdCardUtil.checkSdCard() == true) {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");//相片类型
            this.startActivityForResult(intent, 7);
        } else {
            Toast.makeText(getActivity(), "SD卡不存在", Toast.LENGTH_LONG).show();
        }
    }

    //从照相机获取图片
    private void getImageFromCamera() {
        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri imageUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), fileName));
        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        this.startActivityForResult(openCameraIntent, 1);
    }

    //获取回调的图片url
    public void getImaged(Intent data) {

        Uri uri = null;
        if (data != null) {
            uri = data.getData();
            System.out.println("Data");
        } else {

            uri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), fileName));
        }
        Bitmap photo = null;
        if (uri != null) {
            photo = BitmapFactory.decodeFile(uri.getPath());

        }
        if (photo != null) {
            saveImageToFile(photo);
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("requestcode", String.valueOf(requestCode));
        Log.e("result", String.valueOf(requestCode));
        if (SdCardUtil.checkSdCard() == true) {
            if (requestCode == 1 && resultCode == getActivity().RESULT_OK) {
                getImaged(data);
            } else if (requestCode == 2 && resultCode == getActivity().RESULT_OK) {
                getImaged(data);
            } else if (requestCode == 3 && resultCode == getActivity().RESULT_OK) {
                getImaged(data);
            } else if (requestCode == 4 && resultCode == getActivity().RESULT_OK) {
                getImaged(data);
            } else if (requestCode == 5 && resultCode == getActivity().RESULT_OK) {
                getImaged(data);
            } else if (requestCode == 6 && resultCode == getActivity().RESULT_OK) {
                getImaged(data);
            } else if (requestCode == 7 && resultCode == getActivity().RESULT_OK) {
                ContentResolver resolver = getActivity().getContentResolver();
                Uri uri = data.getData();
                Bitmap bm = null;
                if (uri != null) {
                    try {
                        bm = MediaStore.Images.Media.getBitmap(resolver, uri);
                    } catch (IOException e) {
                        // TODO 自动生成的 catch 块
                        e.printStackTrace();
                    }

                }
                if (bm != null) {
                    saveImageToFile(bm);
                }
            } else if (requestCode == 8 && resultCode == getActivity().RESULT_OK) {
                /**
                 裁剪完图片
                 */
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
        } else {
            Toast.makeText(getActivity(), "SD卡不存在,请检查您的sd卡",
                    Toast.LENGTH_LONG).show();
        }

    }

    //保存图片并压缩
    private void saveImageToFile(Bitmap bitmap) {
        shownotification("正在保存图片...");
        SdCardUtil.createFileDir(SdCardUtil.FILEDIR + "/" + SdCardUtil.FILEPHOTO + "/");
        String fileName = SdCardUtil.getSdPath() + SdCardUtil.FILEDIR  + SdCardUtil.FILEPHOTO + "/" + TimeUtil.getCurrentTimeForImage();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int options = 100;
        bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
        while (baos.toByteArray().length / 1024 > 200) {
            baos.reset();
            options -= 5;
            if (options < 0) {
                options = 80;
                continue;
            }
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
        }
        Log.d("test",fileName);
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            fos.write(baos.toByteArray());
            fos.flush();
            Log.d("test","addpictrue-1");
            if (bitmap != null) {
                bitmap.recycle();
                Log.d("test","addpictrue0");
            }
            Log.d("test","addpictrue1");
            UploadPicsBean bean = new UploadPicsBean();
//            bean.setM_auth(consultActivity.userBean.getM_auth());
            bean.setM_auth("");
            bean.setPicturetitle("");
            bean.setPicture(new File(fileName));
            bean.setPictureid(consultPicsAdapter.getItemCount());
//            bean.setUid(consultActivity.userBean.getUserdata().getUid());
            bean.setUid("");
            Log.d("test","addpictrue2");
            bean.setPresent(0);
            cancelnotification();
            consultPicsAdapter.add(bean);
            consultPicsAdapter.notifyDataSetChanged();
            Log.d("test","addpictrue3");
            fos.close();
        } catch (Exception e) {
            Log.d("test","addpictruefalse");
            e.printStackTrace();
        }
    }

    @Override
    public void showerror(int code, String msg) {
        consultActivity.showmessage(msg, Toast.LENGTH_SHORT);
    }

    @Override
    public void savepicid(int picid, String strpicid) {
        ((UploadPicsBean) consultPicsAdapter.getitem(picid)).setPresent(100);
        consultPicsAdapter.notifyDataSetChanged();
        picids.add(strpicid);
        if (picids.size() == consultPicsAdapter.getItemCount()) {
            sendconsult();
        }

    }

    @Override
    public void uploadpicerror(int picid, File file) {
        setButtonClickable();
        showerror(0, "图片上传失败");
        btConsultSubmit.setText("继续上传");
    }

    @Override
    public void updatepb(int pbid, int present) {
        ((UploadPicsBean) consultPicsAdapter.getitem(pbid)).setPresent(present);
        consultPicsAdapter.notifyDataSetChanged();
    }

    @Override
    public void sendconsult() {
        consultActivity.consultBean.setMessage(etConsultMessage.getText().toString());
        consultActivity.consultBean.setSubject(etConsultMessage.getText().toString());
        consultActivity.consultBean.setPicids(picids);
//        consultPresenter.dosentconsult(consultActivity.consultBean);
    }


    @Override
    public void sendconsultSuccess(int code, String msg) {
        Log.e("url", msg);
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

    @Override
    public void sendconsultFailure(int code, String msg) {
        setButtonClickable();
        showerror(code, msg);
    }

    // 语音听写UI
    private RecognizerDialog mIatDialog;
    // 用HashMap存储听写结果
    private HashMap<String, String> mIatResults = new LinkedHashMap<String, String>();
    // 引擎类型
    private String mEngineType = SpeechConstant.TYPE_CLOUD;
    //语言类型
    private PopupWindow LanguageWindow;
    private String domain = "mandarin";
    private String lag = "zh_cn";

    private void initSpeechRecognizer() {
        // 初始化听写Dialog，如果只使用有UI听写功能，无需创建SpeechRecognizer
        // 使用UI听写功能，请根据sdk文件目录下的notice.txt,放置布局文件和图片资源
        mIatDialog = new RecognizerDialog(getActivity(), mInitListener);
    }

    private void showspeechdialog() {
        setParam();
        mIatDialog.setListener(mRecognizerDialogListener);
        mIatDialog.show();
    }

    /**
     * 初始化监听器。
     */
    private InitListener mInitListener = new InitListener() {

        @Override
        public void onInit(int code) {
            if (code != ErrorCode.SUCCESS) {
                showerror(code, "初始化失败");
                //showTip("初始化失败，错误码：" + code);
            }
        }
    };

    private void printResult(RecognizerResult results) {
        String text = JsonParser.parseIatResult(results.getResultString());

        String sn = null;
        // 读取json结果中的sn字段
        try {
            JSONObject resultJson = new JSONObject(results.getResultString());
            sn = resultJson.optString("sn");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mIatResults.put(sn, text);

        StringBuffer resultBuffer = new StringBuffer();
        for (String key : mIatResults.keySet()) {
            resultBuffer.append(mIatResults.get(key));
        }
        //et_consult_message.append(resultBuffer.toString());
        Log.e("result", resultBuffer.toString());
        findfouce().append(resultBuffer.toString());

    }

    /**
     * 听写UI监听器
     */
    private RecognizerDialogListener mRecognizerDialogListener = new RecognizerDialogListener() {
        public void onResult(RecognizerResult results, boolean isLast) {
            if (!isLast) {

                printResult(results);
            }
        }

        /**
         * 识别回调错误.
         */
        public void onError(SpeechError error) {

        }

    };

    /**
     * 参数设置
     *
     * @return
     * @paramparam
     */
    public void setParam() {
        // 清空参数
        mIatDialog.setParameter(SpeechConstant.PARAMS, null);

        // 设置听写引擎
        mIatDialog.setParameter(SpeechConstant.ENGINE_TYPE, mEngineType);
        // 设置返回结果格式
        mIatDialog.setParameter(SpeechConstant.RESULT_TYPE, "json");
        //语言
        if (lag.equals("en_us")) {
            // 设置语言
            mIatDialog.setParameter(SpeechConstant.LANGUAGE, "en_us");
        } else {
            // 设置语言
            mIatDialog.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
            // 设置语言区域
            mIatDialog.setParameter(SpeechConstant.ACCENT, domain);
        }

        // 设置语音前端点:静音超时时间，即用户多长时间不说话则当做超时处理
        mIatDialog.setParameter(SpeechConstant.VAD_BOS, "4000");

        // 设置语音后端点:后端点静音检测时间，即用户停止说话多长时间内即认为不再输入， 自动停止录音
        mIatDialog.setParameter(SpeechConstant.VAD_EOS, "2000");

        // 设置标点符号,设置为"0"返回结果无标点,设置为"1"返回结果有标点
        mIatDialog.setParameter(SpeechConstant.ASR_PTT, "0");

        // 设置音频保存路径，保存音频格式支持pcm、wav，设置路径为sd卡请注意WRITE_EXTERNAL_STORAGE权限
        // 注：AUDIO_FORMAT参数语记需要更新版本才能生效
        //mIat.setParameter(SpeechConstant.AUDIO_FORMAT,"wav");
        //mIat.setParameter(SpeechConstant.ASR_AUDIO_PATH, Environment.getExternalStorageDirectory()+"/msc/iat.wav");
    }

    private void showlanguagedialog() {
        domain = "mandarin";
        lag = "zh_cn";
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_language, null);
        LanguageWindow = new PopupWindow(view, ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LanguageWindow.setFocusable(true);
        LanguageWindow.setBackgroundDrawable(new BitmapDrawable());
        LanguageWindow.showAtLocation(view, Gravity.CENTER_HORIZONTAL, 0, 0);
        Button bt_normal_chinese = (Button) view.findViewById(R.id.bt_consult_language_normal_chinese);
        Button bt_gdlanguage = (Button) view.findViewById(R.id.bt_consult_language_gdlanguage);
        Button bt_english = (Button) view.findViewById(R.id.bt_consult_language_english);
        Button bt_cancel = (Button) view.findViewById(R.id.bt_consult_language_cancel);
        bt_normal_chinese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                domain = "mandarin";
                showspeechdialog();
                LanguageWindow.dismiss();
            }
        });
        bt_gdlanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                domain = "cantonese";
                showspeechdialog();
                LanguageWindow.dismiss();
            }
        });
        bt_english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lag = "en_us";
                showspeechdialog();
                LanguageWindow.dismiss();
            }
        });
        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LanguageWindow.dismiss();
            }
        });


        LanguageWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                LanguageWindow = null;
            }
        });

    }

    private EditText findfouce() {
        if (etConsultMessage.hasFocus()) {
            return etConsultMessage;
        } else if (etConsultMessage.hasFocus()) {
            return etConsultMessage;
        } else {
            return etConsultMessage;
        }
    }

    private void cancelnotification() {
        mNotificationManager.cancel(0);
    }

    private NotificationManager mNotificationManager;

    //显示照片保存通知
    private void shownotification(String msg) {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
                getActivity()).setSmallIcon(R.drawable.mylogo);
        mBuilder.setTicker(msg);
        mBuilder.setAutoCancel(true);//自己维护通知的消失
        //获取通知管理器对象
        mNotificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(0, mBuilder.build());

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClicks(ColorDialog dialog,int position) {
        dialog.dismiss();
    }

    @Override
    public void onClick(ColorDialog dialog,int position) {
        UploadPicsBean bean = (UploadPicsBean) consultPicsAdapter.getitem(position);
        bean.setPicturetitle(dialog.getContentText().toString());
        consultPicsAdapter.notifyDataSetChanged();
        dialog.dismiss();
    }
}
