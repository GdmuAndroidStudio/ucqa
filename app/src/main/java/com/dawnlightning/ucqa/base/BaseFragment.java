package com.dawnlightning.ucqa.base;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dawnlightning.ucqa.R;
import com.dawnlightning.ucqa.adapter.BaseAdapter;
import com.dawnlightning.ucqa.viewinterface.IBase;
import com.dawnlightning.ucqa.viewinterface.IRefreshAndLoadmore;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Kyo on 2016/5/17.
 */
public abstract class BaseFragment extends Fragment implements IBase, IRefreshAndLoadmore {


    boolean isLoading = false;
    @Bind(R.id.iv_detail_error)
    public ImageView ivDetailError;
    @Bind(R.id.tv_detail_error)
    public TextView tvDetailError;
    @Bind(R.id.bt_detail_error)
    public Button btDetailError;
    @Bind(R.id.rl_detail_error)
    public RelativeLayout rlDetailError;
    @Bind(R.id.recyclerView)
    public RecyclerView recyclerView;
    @Bind(R.id.swipe_refresh_widget)
    public SwipeRefreshLayout swipeRefreshWidget;
    @Bind(R.id.iv_detail_nodata)
    public ImageView ivDetailNodata;
    @Bind(R.id.tv_detail_nodata)
    public TextView tvDetailNodata;
    @Bind(R.id.bt_detail_nodata)
    public Button btDetailNodata;
    @Bind(R.id.rl_detail_nodata)
    public RelativeLayout rlDetailNodata;
    private Handler handler = new Handler();
    public BaseAdapter adapter;
    private LinearLayoutManager layoutManager;
    public View view;


    public abstract void initAdapter();

    public abstract void doRefresh();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        System.out.println("base oncreate");
        view = inflater.inflate(setContentLayout(), container, false);
        ButterKnife.bind(this, view);
        initAdapter();
        initView();
        initData();
        initEvent();
        return view;
    }

    @Override
    public int setContentLayout() {
        return R.layout.fragment_content;
    }

    @Override
    public void initView() {
        swipeRefreshWidget.setColorSchemeResources(R.color.green);
        swipeRefreshWidget.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Refresh(Actions.Refresh);
                        swipeRefreshWidget.setRefreshing(false);
                    }
                }, 2000);
            }
        });
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.d("test", "StateChanged = " + newState);

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.d("test", "onScrolled");
                setScroller();
            }
        });

    }

    public void setScroller() {
        //设置滑动至顶部菜能刷新
        swipeRefreshWidget.setEnabled(layoutManager.findFirstCompletelyVisibleItemPosition() == 0);
        int lastCompleteVisibleItemPosition = layoutManager.findLastCompletelyVisibleItemPosition();
        int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
        //设置加载前foot样式
        if (lastVisibleItemPosition + 1 == adapter.getItemCount()) {
            adapter.setBeforeFoot();
        }
        //当foot完全出现在屏幕中进行加载更多操作
        if (lastCompleteVisibleItemPosition + 1 == adapter.getItemCount()) {
            Log.d("test", "loading executed");
            //设置加载中foot样式
            adapter.setFooting();
            boolean isRefreshing = swipeRefreshWidget.isRefreshing();
            //如果刷新进行中则不能加载更多
            if (isRefreshing) {
                adapter.notifyItemRemoved(adapter.getItemCount());
                return;
            }
            //如果一次性加载完但是没有item没有填满屏幕则foot显示加载完毕
            if (layoutManager.findFirstCompletelyVisibleItemPosition() == 0) {
                adapter.setOverFoot();
                return;
            }
            //加载更多
            if (!isLoading) {
                isLoading = true;
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        LoadMore(Actions.LoadMore);
                        Log.d("test", "load more completed");
                        isLoading = false;
                    }
                }, 1000);
            }
        }
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initData() {
//        handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Refresh(Actions.Refresh);
//                swipeRefreshWidget.setRefreshing(false);
//            }
//        }, 3000);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
