package com.dawnlightning.ucqa.presenter;

import android.content.Context;
import android.widget.ImageView;

import com.dawnlightning.ucqa.bean.others.UserBean;
import com.dawnlightning.ucqa.bean.others.UserData;
import com.dawnlightning.ucqa.bean.response.account.UpdateBean;
import com.dawnlightning.ucqa.model.AccountModel;
import com.dawnlightning.ucqa.viewinterface.IMainFragView;
import com.dawnlightning.ucqa.viewinterface.IMainView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by Kyo on 2016/5/29.
 */
public class MainPresenter {

    private IMainView iMainView;
    private Context context;
    private UserBean userBean;
    private List<Integer> newItems;
    private List<Integer> unreadNumbers;
    private boolean isUpDate;
    private AccountModel accountModel;


    public MainPresenter(IMainView iMainView, Context context) {
        this.context = context;
        this.iMainView = iMainView;
        userBean = iMainView.getUserBean();
        newItems = iMainView.getNewItems();
        unreadNumbers = iMainView.getUnreadNumbers();
        isUpDate = iMainView.setCheckUpDate();
        accountModel = new AccountModel();
    }

    public void initUserData(){
        UserData userData = new UserData();
        userData.setUid("11");
        userData.setName("测试用户");
        userData.setAvatar_url("http://img0.imgtn.bdimg.com/it/u=3098891403,1943866363&fm=21&gp=0.jpg");
        userData.setSex("男");
        userData.setAge("21");
        userBean.setM_auth("86dbHma%2FLr19gbGC%2FCH16GG%2FmBowm8P5Lai2amtBgoBerRxT5F34SPqeqC%2FbTg6zX%2FTjhlAEGtLKTbozWIZm%2FKk");
        userBean.setUserdata(userData);
        userBean.setM_auth("1ef3poLwJ40hJdOx2OpAxGbbvxfGiY%2BPMr4c7GzW8x8k5fYFvCxx5Orq%2BQZxZqHArO%2FKTnYnjTL6zXSZMLzMng");
    }

    public void getNewItems(){
        newItems.clear();
        newItems.add(1);
    }

    public void getUnreadNumbers(){
        unreadNumbers.clear();
        unreadNumbers.add(7);
    }

    public void doCheckUpDate(){
        accountModel.CheckUpdate(new AccountModel.UpdateListener() {
            @Override
            public void needUpdate(UpdateBean bean) {
                isUpDate = true;
                iMainView.checkUpDate();
            }

            @Override
            public void noUpdate() {
                isUpDate = false;
                iMainView.checkUpDate();
            }
        });
    }


}
