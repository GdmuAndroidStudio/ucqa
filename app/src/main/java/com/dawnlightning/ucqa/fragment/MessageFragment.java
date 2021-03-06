package com.dawnlightning.ucqa.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dawnlightning.ucqa.R;
import com.dawnlightning.ucqa.activity.ConsultActivity;
import com.dawnlightning.ucqa.activity.ConsultDetailActivity;
import com.dawnlightning.ucqa.adapter.BaseAdapter;
import com.dawnlightning.ucqa.adapter.ConsultAdapter;
import com.dawnlightning.ucqa.adapter.MainAdapter;
import com.dawnlightning.ucqa.adapter.MessageAdapter;
import com.dawnlightning.ucqa.base.Actions;
import com.dawnlightning.ucqa.base.BaseFragment;
import com.dawnlightning.ucqa.base.Results;
import com.dawnlightning.ucqa.bean.others.ConsultBean;
import com.dawnlightning.ucqa.bean.others.ConsultMessageBean;
import com.dawnlightning.ucqa.bean.others.MessageBean;
import com.dawnlightning.ucqa.presenter.MessagePresenter;
import com.dawnlightning.ucqa.utils.NetUtils;
import com.dawnlightning.ucqa.viewinterface.IMessageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import fr.castorflex.android.circularprogressbar.CircularProgressBar;


/**
 * Created by Administrator on 2016/4/18.
 */
public class MessageFragment extends BaseFragment implements IMessageView{
    private List<MessageBean> messageBeanList;
    private MessagePresenter messagePresenter;
    private boolean isFirst = true;
    private String othur = "86dbHma%2FLr19gbGC%2FCH16GG%2FmBowm8P5Lai2amtBgoBerRxT5F34SPqeqC%2FbTg6zX%2FTjhlAEGtLKTbozWIZm%2FKk";
    private int uid = 100;
    private int currentPage = 1;
    private int NOMAL = 0;
    private int NODATA = 1;
    private int NONEXTPAGE = 2;
    private int status = NOMAL;

    @Override
    public void getActionResult(String msg, Results results,Actions actions) {
        switch (results){
            case Success:
                swipeRefreshWidget.setRefreshing(false);
                break;
            case Fail:
                swipeRefreshWidget.setRefreshing(false);
                swipeRefreshWidget.setEnabled(false);
                showMessage(msg);
                break;
            case NoNextPage:
                swipeRefreshWidget.setRefreshing(false);
                status = NONEXTPAGE;
                showMessage("没有下一页了");
                break;
            case NoData:
                swipeRefreshWidget.setRefreshing(false);
                swipeRefreshWidget.setEnabled(true);
                status = NODATA;
                if(actions.equals(Actions.LoadMore)){
                    adapter.setOverFoot();
                    currentPage-=1;
                }
                if(currentPage==1) {
                    Log.i("test","隐藏");
                    rlDetailNodata.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.INVISIBLE);
                }
                break;

        }
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
            Log.i("havenet","net");
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
            Log.i("nonet","nonet");
            recyclerView.setVisibility(View.INVISIBLE);
            rlDetailError.setVisibility(View.VISIBLE);
            rlDetailNodata.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void initAdapter() {
        messageBeanList = new ArrayList<MessageBean>();
        adapter = new MessageAdapter(getContext());
        messagePresenter = new MessagePresenter(this);
        adapter.setList(messageBeanList);
        btDetailNodata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setClass(getActivity(), ConsultActivity.class);
                startActivity(i);
            }
        });
        btDetailError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                swipeRefreshWidget.setRefreshing(true);
                recyclerView.setVisibility(View.VISIBLE);
                rlDetailError.setVisibility(View.INVISIBLE);
                rlDetailNodata.setVisibility(View.INVISIBLE);
                Refresh(Actions.Refresh);
            }
        });
    }



    @Override
    public void Refresh(Actions action) {
        currentPage=1;
        if(rlDetailNodata.getVisibility()==View.VISIBLE||rlDetailError.getVisibility()==View.VISIBLE){
            rlDetailNodata.setVisibility(View.INVISIBLE);
            rlDetailError.setVisibility(View.INVISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
        }
        if(NetUtils.isNetworkConnected(getContext())==true) {
            messagePresenter.refreshAndLoadMore(othur, currentPage);
        }else {
            swipeRefreshWidget.setRefreshing(false);
            recyclerView.setVisibility(View.INVISIBLE);
            rlDetailError.setVisibility(View.VISIBLE);
            rlDetailNodata.setVisibility(View.INVISIBLE);
        }
    }

    private void showMessage(String msg){
        Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void LoadMore(Actions action) {
        currentPage+=1;
        if(status!=NONEXTPAGE&&status!=NODATA)
            messagePresenter.refreshAndLoadMore(othur,currentPage);
        else{
            adapter.setOverFoot();
            showMessage("已没有更多数据！");
        }
    }

    @Override
    public MessageAdapter getMessageAdapter() {
        return (MessageAdapter)adapter;
    }

    @Override
    public List<MessageBean> getConsultMessageBeanList() {
        return messageBeanList;
    }

    }
