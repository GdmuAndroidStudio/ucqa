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
import android.widget.Toast;

import com.dawnlightning.ucqa.R;
import com.dawnlightning.ucqa.adapter.BaseAdapter;
import com.dawnlightning.ucqa.adapter.RecyclerViewAdapter;
import com.dawnlightning.ucqa.bean.others.ConsultMessageBean;
import com.dawnlightning.ucqa.viewinterface.IBase;
import com.dawnlightning.ucqa.viewinterface.IRefreshAndLoadmore;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Kyo on 2016/5/17.
 */
public abstract class BaseFragment extends Fragment implements IBase,IRefreshAndLoadmore {


    boolean isLoading = false;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.swipe_refresh_widget)
    SwipeRefreshLayout swipeRefreshWidget;
    private Handler handler = new Handler();
    private SwipeRefreshLayout.OnRefreshListener onRefreshListener;
    private ArrayList<ConsultMessageBean> consultMessageBeanList = new ArrayList<>();
    public BaseAdapter adapter;

    public abstract void initAdapter();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(setContentLayout(), container, false);
        ButterKnife.bind(this, view);
        initAdapter();
        initData();
        initView();
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
        swipeRefreshWidget.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshWidget.setRefreshing(true);
            }
        });
        swipeRefreshWidget.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        consultMessageBeanList.clear();
                        Refresh(Actions.Refresh);
                        swipeRefreshWidget.setRefreshing(false);
                    }
                }, 2000);
            }
        });
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

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
//                int topRowVerticalPosition =
//                        (recyclerView == null || recyclerView.getChildCount() == 0) ? 0 : recyclerView.getChildAt(0).getTop();
//                swipeRefreshWidget.setEnabled(topRowVerticalPosition >= 0);
                if (layoutManager.findFirstCompletelyVisibleItemPosition() == 0) {
                    swipeRefreshWidget.setEnabled(true);
                } else {
                    swipeRefreshWidget.setEnabled(false);
                }
                System.out.println("topRowVerticalPosition = " + recyclerView.getChildAt(0).getTop());
                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                if (lastVisibleItemPosition + 1 == adapter.getItemCount()) {
                    Log.d("test", "loading executed");

                    boolean isRefreshing = swipeRefreshWidget.isRefreshing();
                    if (isRefreshing) {
                        adapter.notifyItemRemoved(adapter.getItemCount());
                        return;
                    }
                    System.out.println("loading = " + isLoading);
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
        });

    }

    @Override
    public void initEvent() {
        adapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.d("test", "item position = " + position);
                Toast.makeText(getActivity(),"test",Toast.LENGTH_SHORT);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }

    @Override
    public void initData() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Refresh(Actions.Refresh);
                swipeRefreshWidget.setRefreshing(false);
            }
        }, 2000);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
