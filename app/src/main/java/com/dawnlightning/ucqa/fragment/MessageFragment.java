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
    private MessagePresenter messagePresenter;
    private IMessageView iMessageView;
    private boolean firstLoad;
    private String othur = "86dbHma%2FLr19gbGC%2FCH16GG%2FmBowm8P5Lai2amtBgoBerRxT5F34SPqeqC%2FbTg6zX%2FTjhlAEGtLKTbozWIZm%2FKk";
    private List<MessageBean> consultMessageBeanList;
    private boolean isFirst = true;
    private int currentPage = 1;
    private int NOMAL = 0;
    private int NODATA = 1;
    private int NONEXTPAGE = 2;
    private int status = NOMAL;
    private Button addConsult;
    private RelativeLayout noMessage;
    private TextView noneMessage;


    @Override
    public void getSuccess() {
        swipeRefreshWidget.setRefreshing(false);
    }

    @Override
    public void getFailure(String msg, Actions actions) {
        swipeRefreshWidget.setRefreshing(false);
        showMessage(msg);
    }

    @Override
    public void getError(String msg, Actions actions) {
        swipeRefreshWidget.setRefreshing(false);
        showMessage(msg);
    }

    @Override
    public void noNextPage(Actions actions) {
        status = NONEXTPAGE;
    }

    @Override
    public void noData(Actions actions) {
        swipeRefreshWidget.setRefreshing(false);
        status = NODATA;
        if(actions.equals(Actions.LoadMore))
            currentPage-=1;
        if(currentPage==1) {
            Log.i("test","隐藏");
            noMessage.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
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
            showMessage("网络出错");
        }
    }

    @Override
    public void initAdapter() {
        consultMessageBeanList = new ArrayList<MessageBean>();
        adapter = new MessageAdapter(getContext());
        messagePresenter = new MessagePresenter(this);
        adapter.setList(consultMessageBeanList);
        adapter.setOnItemClickListener(new MainAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent i = new Intent();
                i.setClass(getActivity(), ConsultDetailActivity.class);
                startActivity(i);
                // 动画过渡
            }
        });
        noMessage = (RelativeLayout) view.findViewById(R.id.rl_detail_error);
        noneMessage = (TextView) view.findViewById(R.id.tv_detail_error);
        noneMessage.setText("暂无消息");
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
    public void Refresh(Actions action) {
           currentPage=1;
            messagePresenter.refreshAndLoadMore(othur, currentPage);
    }

    @Override
    public void LoadMore(Actions action) {
        currentPage+=1;
        if(status!=NONEXTPAGE&&status!=NODATA)
            messagePresenter.refreshAndLoadMore(othur, currentPage);
        else{
            adapter.setOverFoot();
            Toast.makeText(getActivity(),"已没有更多数据",Toast.LENGTH_SHORT);
        }

    }

    private void showMessage(String msg){
        Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT);
    }

    @Override
    public MessageAdapter getMessageAdapter() {
        return (MessageAdapter)adapter;
    }

    @Override
    public List<MessageBean> getConsultMessageBeanList() {
        return consultMessageBeanList;
    }

    }
