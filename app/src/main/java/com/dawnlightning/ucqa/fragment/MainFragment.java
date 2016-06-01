package com.dawnlightning.ucqa.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dawnlightning.ucqa.R;
import com.dawnlightning.ucqa.activity.ConsultDetailActivity;
import com.dawnlightning.ucqa.activity.MainActivity;
import com.dawnlightning.ucqa.adapter.MainAdapter;
import com.dawnlightning.ucqa.base.Actions;
import com.dawnlightning.ucqa.base.BaseFragment;
import com.dawnlightning.ucqa.bean.others.BannerBean;
import com.dawnlightning.ucqa.bean.others.ConsultMessageBean;
import com.dawnlightning.ucqa.presenter.MainFragPresenter;
import com.dawnlightning.ucqa.viewinterface.IMainFragView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kyo on 2016/5/21.
 */
public class MainFragment extends BaseFragment implements IMainFragView {

    private List<ConsultMessageBean> data = new ArrayList<>();
    private List<BannerBean> bannerBeanList = new ArrayList<>();
    private MainFragPresenter mainFragPresenter;
    private String classifyName = "全部";  //分类名称，初始化为全部
    private boolean isFirst = true;

    public static MainFragment newInstance(Bundle bundle) {
        MainFragment mainFragment = new MainFragment();
        mainFragment.setArguments(bundle);
        return mainFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        doRefresh();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void setClassifyName(String classifyName){
        mainFragPresenter.setInfoList(classifyName);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void doRefresh() {
        if (isFirst == true) {
            swipeRefreshWidget.post(new Runnable() {
                @Override
                public void run() {
                    System.out.println("do refresh1");
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
        adapter = new MainAdapter(this, data, bannerBeanList);
        mainFragPresenter = new MainFragPresenter(this, getContext());
        adapter.setOnItemClickListener(new MainAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent i = new Intent();
                i.setClass(getActivity(), ConsultDetailActivity.class);
                startActivity(i);
                // 动画过渡
                getActivity().overridePendingTransition(R.anim.slide_in_right,
                        R.anim.slide_out_left);

            }
        });
    }

    @Override
    public void Refresh(Actions actions) {
        mainFragPresenter.refresh();
    }

    @Override
    public void LoadMore(Actions actions) {
        mainFragPresenter.loadMore();
    }

    @Override
    public MainAdapter getMainAdapter() {
        return (MainAdapter) adapter;
    }

    @Override
    public List<ConsultMessageBean> getConsultMessageBeanList() {
        return data;
    }

    @Override
    public List<BannerBean> getBannerBeanList() {
        return bannerBeanList;
    }

}
