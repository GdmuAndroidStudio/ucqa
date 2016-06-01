package com.dawnlightning.ucqa.presenter;

import android.content.Context;

import com.dawnlightning.ucqa.adapter.MainAdapter;
import com.dawnlightning.ucqa.bean.others.BannerBean;
import com.dawnlightning.ucqa.bean.others.ConsultMessageBean;
import com.dawnlightning.ucqa.viewinterface.IMainFragView;

import java.util.List;

/**
 * Created by Kyo on 2016/5/28.
 */
public class MainFragPresenter {

    private Context context;
    private IMainFragView iMainFragView;
    private MainAdapter mainAdapter;
    private List<ConsultMessageBean> consultMessageBeanList;
    private List<BannerBean> bannerBeanList;

    public MainFragPresenter(IMainFragView iMainFragView, Context context){
        this.iMainFragView = iMainFragView;
        mainAdapter = iMainFragView.getMainAdapter();
        consultMessageBeanList= iMainFragView.getConsultMessageBeanList();
        bannerBeanList = iMainFragView.getBannerBeanList();
        this.context = context;
    }

    public void refresh(){
        consultMessageBeanList.clear();
        bannerBeanList.clear();
        //因为有一个banner一个head，需要在数组内添加两个空实体
        for (int i = 0; i < 2; i++) {
            ConsultMessageBean nullBean = new ConsultMessageBean();
            consultMessageBeanList.add(nullBean);
        }
        //添加消息列表实体，此处应该用 setInfoList()方法添加
        for (int i = 0; i < 4; i++) {
            ConsultMessageBean consultMessageBean = new ConsultMessageBean();
            consultMessageBeanList.add(consultMessageBean);
        }
        BannerBean bannerBean1 = new BannerBean();
        bannerBean1.setImg("http://img2.imgtn.bdimg.com/it/u=1514107744,1001213722&fm=21&gp=0.jpg");
        bannerBeanList.add(bannerBean1);
        BannerBean bannerBean2 = new BannerBean();
        bannerBean2.setImg("http://img3.imgtn.bdimg.com/it/u=882887467,2910558374&fm=21&gp=0.jpg");
        bannerBeanList.add(bannerBean2);
        mainAdapter.notifyDataSetChanged();
    }

    public void loadMore(){
        //添加消息列表实体，此处应该用 setInfoList()方法添加
        for (int i = 0; i < 1; i++) {
            ConsultMessageBean consultMessageBean = new ConsultMessageBean();
            consultMessageBeanList.add(consultMessageBean);
        }
        mainAdapter.notifyDataSetChanged();
        mainAdapter.notifyItemRemoved(mainAdapter.getItemCount());
    }

    /**
     * 用分类名获取列表信息
     */
    public void setInfoList(String classifyName){
        System.out.println("classifyname = " + classifyName);
        //。。。。
    }
}
