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

import com.dawnlightning.ucqa.R;
<<<<<<< HEAD
import com.dawnlightning.ucqa.adapter.RecyclerViewAdapter;
import com.dawnlightning.ucqa.bean.others.ConsultMessageBean;
import com.dawnlightning.ucqa.viewinterface.IBase;

=======
import com.dawnlightning.ucqa.activity.MainActivity;
import com.dawnlightning.ucqa.adapter.ClassifyAdapter;
import com.dawnlightning.ucqa.bean.others.ConsultClassifyBean;
import com.dawnlightning.ucqa.viewinterface.IBase;
import com.dawnlightning.ucqa.widget.OtherGridView;
import com.dawnlightning.ucqa.bean.others.*;
>>>>>>> 796cfc7378f72e8023c00bfbc3625cdcabb55ddb
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Kyo on 2016/5/17.
 */
public class BaseFragment extends Fragment implements IBase {

    @Bind(R.id.swipe_refresh_widget)
    SwipeRefreshLayout swipeRefreshWidget;
<<<<<<< HEAD
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    boolean isLoading = false;
=======
    @Bind(R.id.gv_classify)
    GridView gvClassify;
    private static boolean FirstIn = true;
>>>>>>> 796cfc7378f72e8023c00bfbc3625cdcabb55ddb
    private Handler handler = new Handler();
    private SwipeRefreshLayout.OnRefreshListener onRefreshListener;
    private ArrayList<ConsultMessageBean> consultMessageBeanList = new ArrayList<>();
    private RecyclerViewAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(setContentLayout(), container, false);
        ButterKnife.bind(this, view);
        initView();
        initData();
        initEvent();
        return view;
    }

    @Override
    public int setContentLayout() {
        return R.layout.fragment_base;
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
<<<<<<< HEAD
                        consultMessageBeanList.clear();
                        getData();
=======
                        if (swipeRefreshWidget!=null)
                        swipeRefreshWidget.setRefreshing(false);
>>>>>>> 796cfc7378f72e8023c00bfbc3625cdcabb55ddb
                    }
                }, 2000);
            }
        });
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        adapter = new RecyclerViewAdapter(getActivity(), consultMessageBeanList);
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
                                getData();
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
        //添加点击事件
        adapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.d("test", "item position = " + position);
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
                getData();
            }
        }, 1500);
    }

    private void getData() {
        for (int i = 0; i < 8; i++) {
            ConsultMessageBean bean = new ConsultMessageBean();
            bean.setSubject("title" + i);
            bean.setMessage("content" + i);
            bean.setViewnum("" + i);
            bean.setReplynum("" + i);
            bean.setDateline("" + i);
            consultMessageBeanList.add(bean);
        }
        adapter.notifyDataSetChanged();
        swipeRefreshWidget.setRefreshing(false);
        adapter.notifyItemRemoved(adapter.getItemCount());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
