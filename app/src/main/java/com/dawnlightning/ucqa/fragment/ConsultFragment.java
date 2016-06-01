package com.dawnlightning.ucqa.fragment;

import android.os.Bundle;
import android.os.Handler;

import com.dawnlightning.ucqa.adapter.ConsultAdapter;
import com.dawnlightning.ucqa.base.Actions;
import com.dawnlightning.ucqa.base.BaseFragment;
import com.dawnlightning.ucqa.bean.others.ConsultBean;
import com.dawnlightning.ucqa.bean.others.ConsultMessageBean;
import com.dawnlightning.ucqa.presenter.ConsultPresenter;
import com.dawnlightning.ucqa.viewinterface.IConsultMessageView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2016/5/20.
 */
public class ConsultFragment extends BaseFragment implements IConsultMessageView{
    private List<ConsultBean> consultBeanList;
    private ConsultPresenter consultPresenter;
    private boolean isFirst = true;

    public static MainFragment newInstance(Bundle bundle) {
        MainFragment mainFragment = new MainFragment();
        mainFragment.setArguments(bundle);
        return mainFragment;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            doRefresh();
        }
    }


    @Override
    public void doRefresh() {
        if (isFirst == true) {
            swipeRefreshWidget.post(new Runnable() {
                @Override
                public void run() {
                    System.out.println("do refresh3");
                    swipeRefreshWidget.setRefreshing(true);
                }
            });
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Refresh(Actions.Refresh);
                    swipeRefreshWidget.setRefreshing(false);
                }
            }, 3000);
            isFirst = false;
        }
    }

    @Override
    public void initAdapter() {
        consultBeanList = new ArrayList<ConsultBean>();
        adapter = new ConsultAdapter(getContext());
        consultPresenter = new ConsultPresenter(this,getContext());
        adapter.setList(consultBeanList);
    }

    @Override
    public void Refresh(Actions action) {
        consultPresenter.refresh();
    }

    @Override
    public void LoadMore(Actions action) {
        consultPresenter.loadMore();
    }

    @Override
    public ConsultAdapter getConsultAdapter() {
        return (ConsultAdapter)adapter;
    }

    @Override
    public List<ConsultBean> getConsultBeanList() {
        return consultBeanList;
    }
}
