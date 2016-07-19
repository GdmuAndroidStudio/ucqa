package com.dawnlightning.ucqa.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dawnlightning.ucqa.R;
import com.dawnlightning.ucqa.adapter.MyFragmentPagerAdapter;
import com.dawnlightning.ucqa.base.BaseActivity;
import com.dawnlightning.ucqa.bean.others.ConsultBean;
import com.dawnlightning.ucqa.bean.others.ConsultClassifyBean;
import com.dawnlightning.ucqa.bean.others.UserBean;
import com.dawnlightning.ucqa.fragment.ConsultPageOneFragment;
import com.dawnlightning.ucqa.fragment.ConsultPageTwoFragment;
import com.dawnlightning.ucqa.widget.MyViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/4/13.
 */
public class ConsultActivity extends BaseActivity {
    @Bind(R.id.iv_consult_back)
    ImageView iv_consult_back;
    @Bind(R.id.bt_consult_page1)
    Button bt_consult_page1;
    @Bind(R.id.bt_consult_page2)
    Button bt_consult_page2;
    @Bind(R.id.vp_consult_pageconview)
    public MyViewPager vp_consult_contentview;
    private MyFragmentPagerAdapter myFragmentPagerAdapter;
    private ArrayList<Fragment> fragmentList = new ArrayList<Fragment>();
    private ConsultPageOneFragment consultPageOneFragment;
    private ConsultPageTwoFragment consultPageTwoFragment;
    public static ConsultBean consultBean = new ConsultBean();
    public UserBean userBean;
    public List<ConsultClassifyBean> consultClassifyBeanList;

    public void initdata() {
        consultPageOneFragment = new ConsultPageOneFragment();
        consultPageTwoFragment = new ConsultPageTwoFragment();
        fragmentList.add(consultPageOneFragment);
        fragmentList.add(consultPageTwoFragment);
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentList);
        vp_consult_contentview.setCurrentItem(0);
        setTabSelected(bt_consult_page1);
        vp_consult_contentview.setAdapter(myFragmentPagerAdapter);
        vp_consult_contentview.setPagingEnabled(false);
    }


    public void initevent() {
        vp_consult_contentview.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {


            }

            @Override
            public void onPageSelected(int arg0) {
                if (arg0 == 0) {
                    setTabSelected(bt_consult_page1);
                } else if (arg0 == 1) {
                    setTabSelected(bt_consult_page2);
                }

            }

        });
        iv_consult_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult);
        ButterKnife.bind(this);
        setUserBean(getIntent());
        initdata();
        initevent();
    }

    //页面切换时的背景切换
    private void setTabSelected(Button btnSelected) {
        if (btnSelected.getId() == R.id.bt_consult_page1) {

            bt_consult_page1.setBackgroundDrawable(getResources().getDrawable(R.mipmap.ic_consult_left_press));
            bt_consult_page2.setBackgroundDrawable(getResources().getDrawable(R.mipmap.ic_consult_right_normal));

        } else {
            bt_consult_page1.setBackgroundDrawable(getResources().getDrawable(R.mipmap.ic_consult_left_normal));
            bt_consult_page2.setBackgroundDrawable(getResources().getDrawable(R.mipmap.ic_consult_right_press));

        }

    }

    //只能从第二页滑动回到第一页，不能反过来
    public void selectpage(int pageid) {
        if (pageid == 1) {
            vp_consult_contentview.setCurrentItem(pageid);
            vp_consult_contentview.setPagingEnabled(true);
        }

    }

    //存储第一页的患者信息
    public void PageOneSetConsultBean(String name, String age, String sex, String classifyname) {
        consultBean.setAge(age);
        consultBean.setSex(sex);
        consultBean.setBwztclassid(findclassifyid(classifyname));
        consultBean.setBwztdivisionid(1);//以后可能有更多的科室
    }

    //保存主界面的用户信息
    public void setUserBean(Intent intent) {
        userBean = (UserBean) intent.getSerializableExtra("userdata");
//        consultClassifyBeanList=(List<ConsultClassifyBean>)intent.getSerializableExtra("classifybeanlist");
//        consultBean.setFormhash(userBean.getFormhash());
//        consultBean.setM_auth(userBean.getM_auth());
        consultBean.setFormhash("05154ee8");
        consultBean.setM_auth("86dbHma%2FLr19gbGC%2FCH16GG%2FmBowm8P5Lai2amtBgoBerRxT5F34SPqeqC%2FbTg6zX%2FTjhlAEGtLKTbozWIZm%2FKk");
    }

    //根据科室名称寻找对应的ID
    private int findclassifyid(String classifyname) {
//        for (ConsultClassifyBean bean:consultClassifyBeanList){
//            if (bean.getBwztclassarrname().equals(classifyname)){
//                return bean.getBwztclassarrid();
//            }
//        }
        return 1;
    }


}

