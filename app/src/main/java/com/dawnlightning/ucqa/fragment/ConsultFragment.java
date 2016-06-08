package com.dawnlightning.ucqa.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.dawnlightning.ucqa.R;
import com.dawnlightning.ucqa.activity.ConsultActivity;
import com.dawnlightning.ucqa.activity.ConsultDetailActivity;
import com.dawnlightning.ucqa.adapter.ConsultAdapter;
import com.dawnlightning.ucqa.base.Actions;
import com.dawnlightning.ucqa.base.BaseFragment;
import com.dawnlightning.ucqa.bean.others.ConsultBean;
import com.dawnlightning.ucqa.bean.others.ConsultMessageBean;
import com.dawnlightning.ucqa.presenter.ConsultPresenter;
import com.dawnlightning.ucqa.utils.NetUtils;
import com.dawnlightning.ucqa.viewinterface.IConsultMessageView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2016/5/20.
 */
public class ConsultFragment extends BaseFragment implements IConsultMessageView{
    private List<ConsultMessageBean> consultBeanList;
    private ConsultPresenter consultPresenter;
    private Button addConsult;
    private boolean isFirst = true;
    private String othur = "86dbHma%2FLr19gbGC%2FCH16GG%2FmBowm8P5Lai2amtBgoBerRxT5F34SPqeqC%2FbTg6zX%2FTjhlAEGtLKTbozWIZm%2FKk";
    private int uid = 100;
    private RelativeLayout noMessage;
    private int currentPage = 1;
    private int NOMAL = 0;
    private int NODATA = 1;
    private int NONEXTPAGE = 2;
    private int status = NOMAL;

    @Override
    public void getError(String msg, Actions actions) {
        swipeRefreshWidget.setRefreshing(false);
        Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT);
    }

    @Override
    public void noNextPage(Actions actions) {
        swipeRefreshWidget.setRefreshing(false);
        status = NONEXTPAGE;
    }

    @Override
    public void noData(Actions actions) {
        swipeRefreshWidget.setRefreshing(false);
        status = NODATA;
        if(actions.equals(Actions.LoadMore)){
            adapter.setOverFoot();
            currentPage-=1;
        }
        if(currentPage==1) {
            Log.i("test","隐藏");
            swipeRefreshWidget.setEnabled(true);
            noMessage.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.INVISIBLE);
        }
    }


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
        if(NetUtils.isNetworkConnected(getContext())==true) {
            if (isFirst == true) {
                swipeRefreshWidget.post(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("do refresh2");
                        swipeRefreshWidget.setRefreshing(true);
                    }
                });
                Refresh(Actions.Refresh);
                isFirst = false;
            }
        }else{
            Toast.makeText(getActivity(),"网络出错",Toast.LENGTH_SHORT);
        }
    }

    @Override
    public void initAdapter() {
        consultBeanList = new ArrayList<ConsultMessageBean>();
        adapter = new ConsultAdapter(getContext());
        consultPresenter = new ConsultPresenter(this);
        adapter.setList(consultBeanList);
        noMessage = (RelativeLayout) view.findViewById(R.id.rl_detail_error);
        addConsult = (Button) view.findViewById(R.id.bt_detail_error);
        addConsult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setClass(getActivity(), ConsultActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void getSuccess() {
        swipeRefreshWidget.setRefreshing(false);
    }

    @Override
    public void Refresh(Actions action) {
        currentPage=1;
        if(noMessage.getVisibility()==View.VISIBLE){
            noMessage.setVisibility(View.INVISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
            swipeRefreshWidget.setEnabled(layoutManager.findFirstCompletelyVisibleItemPosition() == 0);
        }
        consultPresenter.refreshAndLoadMore(uid,othur,currentPage);
    }

    @Override
    public void LoadMore(Actions action) {
        currentPage+=1;
        if(status!=NONEXTPAGE&&status!=NODATA)
            consultPresenter.refreshAndLoadMore(uid,othur,currentPage);
        else{
            adapter.setOverFoot();
            Toast.makeText(getActivity(),"已没有更多数据",Toast.LENGTH_SHORT);
        }
    }

    @Override
    public ConsultAdapter getConsultAdapter() {
        return (ConsultAdapter)adapter;
    }

    @Override
    public void getFailure(String msg, Actions actions) {
        Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT);
    }

    @Override
    public List<ConsultMessageBean> getConsultBeanList() {
        return consultBeanList;
    }
}
