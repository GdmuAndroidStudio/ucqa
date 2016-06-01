package com.dawnlightning.ucqa.fragment;

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

import com.dawnlightning.ucqa.R;
import com.dawnlightning.ucqa.adapter.BaseAdapter;
import com.dawnlightning.ucqa.adapter.ConsultAdapter;
import com.dawnlightning.ucqa.adapter.MessageAdapter;
import com.dawnlightning.ucqa.base.Actions;
import com.dawnlightning.ucqa.base.BaseFragment;
import com.dawnlightning.ucqa.bean.others.ConsultBean;
import com.dawnlightning.ucqa.bean.others.ConsultMessageBean;
import com.dawnlightning.ucqa.presenter.MessagePresenter;
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
    private List<ConsultMessageBean> consultMessageBeanList;

    @Override
    public void initAdapter() {
        consultMessageBeanList = new ArrayList<ConsultMessageBean>();
        adapter = new MessageAdapter(getContext());
        messagePresenter = new MessagePresenter(this,getContext());
        adapter.setList(consultMessageBeanList);
    }

    @Override
    public void Refresh(Actions action) {
        messagePresenter.refresh();
    }

    @Override
    public void LoadMore(Actions action) {
        messagePresenter.loadMore();
    }

    @Override
    public MessageAdapter getMessageAdapter() {
        return (MessageAdapter)adapter;
    }

    @Override
    public List<ConsultMessageBean> getConsultMessageBeanList() {
        return consultMessageBeanList;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        firstLoad = isVisibleToUser;
    }
    }
