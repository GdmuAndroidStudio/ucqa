package com.dawnlightning.ucqa.viewinterface;

import android.content.Intent;

import com.dawnlightning.ucqa.bean.others.UserBean;

import java.util.List;

/**
 * Created by Administrator on 2016/4/1.
 */
public interface IMainView {
//    public void selectview(int id);//根据ID跳转不同的view
    public boolean setCheckUpDate();//检查用户是否需要更新
    public void checkUpDate();
    public UserBean getUserBean();//获取用户数据
    public List<Integer> getNewItems();//获取左拉列表中带有new的items
    public List<Integer> getUnreadNumbers();//获取未读信息

//    public void initjpush(UserBean bean);//初始化推送服务
//    public void showUpDate();//左边菜单显示new

}
