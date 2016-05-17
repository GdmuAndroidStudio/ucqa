package com.dawnlightning.ucqa.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.Window;
import android.widget.Toast;

import com.dawnlightning.ucqa.R;
import com.dawnlightning.ucqa.db.SharedPreferenceDb;
import com.dawnlightning.ucqa.gesture.BackGestureListener;
import com.dawnlightning.ucqa.gesture.IBase;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Administrator on 2016/3/30.
 */
public abstract  class BaseActivity extends AppCompatActivity{
    private Context context;
    private  SharedPreferenceDb MySharedPreferenceDb;
    public static boolean isForeground = false;//推送判断是否在主界面
    protected int activityCloseEnterAnimation;

    protected int activityCloseExitAnimation;
    /** 手势监听 */
    GestureDetector mGestureDetector;
    /** 是否需要监听手势关闭功能 */
    private boolean mNeedBackGesture = false;

    public  void showmessage(String msg,int time){
        Toast.makeText(context,msg,time).show();
    }

    public  void initsharepreference(Context context){
        MySharedPreferenceDb=new SharedPreferenceDb(context);
    }
    public  SharedPreferenceDb getMySharedPreferenceDb(){
        return  this.MySharedPreferenceDb;
    }
    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        isForeground = true;
        JPushInterface.onResume(context);

    }

    @Override
    protected void onPause() {
        super.onPause();
        isForeground = false;
        JPushInterface.onPause(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        context=this;
        initsharepreference(context);
        initGestureDetector();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
    private void initGestureDetector() {
        if (mGestureDetector == null) {
            mGestureDetector = new GestureDetector(getApplicationContext(),
                    new BackGestureListener(this));
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        // TODO Auto-generated method stub
        if(mNeedBackGesture){
            return mGestureDetector.onTouchEvent(ev) || super.dispatchTouchEvent(ev);
        }
        return super.dispatchTouchEvent(ev);
    }

    /*
     * 设置是否进行手势监听
     */
    public void setNeedBackGesture(boolean mNeedBackGesture){
        this.mNeedBackGesture = mNeedBackGesture;
    }

    public Context getcontext() {
        return this.context;
    }
    /*
	 * 返回
	 */
    @Override
    public void finish() {
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        super.finish();
    }


}
