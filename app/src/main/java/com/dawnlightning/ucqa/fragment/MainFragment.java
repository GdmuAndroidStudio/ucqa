package com.dawnlightning.ucqa.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dawnlightning.ucqa.R;
import com.dawnlightning.ucqa.activity.ConsultDetailActivity;
import com.dawnlightning.ucqa.activity.MainActivity;
import com.dawnlightning.ucqa.adapter.MainAdapter;
import com.dawnlightning.ucqa.base.Actions;
import com.dawnlightning.ucqa.base.BaseFragment;
import com.dawnlightning.ucqa.base.Results;
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
    private boolean isFirst = true;
    private Handler handler = new Handler();

    public static MainFragment newInstance(Bundle bundle) {
        MainFragment mainFragment = new MainFragment();
        mainFragment.setArguments(bundle);
        return mainFragment;
    }

    @Override
    public void setClassifyTitle(int classifyID){
        switch (classifyID){
            case 0:
                ((MainActivity)getActivity()).showtitleclassift("全部");
                break;
            case 1:
                ((MainActivity)getActivity()).showtitleclassift("青少年近视");
                break;
            case 2:
                ((MainActivity)getActivity()).showtitleclassift("防盲治盲");
                break;
            case 3:
                ((MainActivity)getActivity()).showtitleclassift("飞秒激光治疗近视");
                break;
            case 4:
                ((MainActivity)getActivity()).showtitleclassift("青光眼");
                break;
            case 5:
                ((MainActivity)getActivity()).showtitleclassift("白内障");
                break;


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        doRefresh();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void setClassifyId(int classifyId) {
        mainFragPresenter.setClassifyList(classifyId);
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
                    swipeRefreshWidget.setRefreshing(true);
                    Refresh(Actions.Refresh);
                }
            });
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
                i.putExtra("userBean",MainActivity.userBean);
                i.putExtra("bwztid",data.get(position).getBwztid());
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

    @Override
    public void setScroller() {
        super.setScroller();
    }

    @Override
    public void setResult(Actions actions, Results results, final String msg) {
        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        if (actions == Actions.Refresh) {
            recyclerView.clearOnScrollListeners();
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    setScroller();

                }
            });
            switch (results) {
                case Success:
                    System.out.println("success");
                    swipeRefreshWidget.setRefreshing(false);
                    recyclerView.setVisibility(View.VISIBLE);
                    rlDetailNodata.setVisibility(View.GONE);
                    System.out.println("text " + linearLayoutManager.findFirstCompletelyVisibleItemPosition() + " text " + linearLayoutManager.findLastCompletelyVisibleItemPosition() + " text " + ((MainAdapter) adapter).getItemCount());
                    if (linearLayoutManager.findFirstCompletelyVisibleItemPosition() == 0 && linearLayoutManager.findLastVisibleItemPosition() + 1 == ((MainAdapter) adapter).getItemCount()) {
                        ((MainAdapter) adapter).setOverFoot();
                    }
                    break;
                case Fail:
                    swipeRefreshWidget.setRefreshing(false);
                    recyclerView.setVisibility(View.VISIBLE);
                    rlDetailNodata.setVisibility(View.GONE);
                    Toast.makeText(getContext(), "加载失败", Toast.LENGTH_SHORT).show();
                    break;
                case Error:
                    System.out.println("error");
                    swipeRefreshWidget.setEnabled(false);
                    swipeRefreshWidget.setRefreshing(true);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            swipeRefreshWidget.setRefreshing(false);
                            recyclerView.setVisibility(View.GONE);
                            rlDetailNodata.setVisibility(View.VISIBLE);
                            tvDetailNodata.setText(msg);
                            btDetailNodata.setText("重新加载");
                            btDetailNodata.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    swipeRefreshWidget.setRefreshing(true);
                                    Refresh(Actions.Refresh);
                                }
                            });
                            Toast.makeText(getContext(), "请求失败", Toast.LENGTH_SHORT).show();
                        }
                    }, 1500);
                    break;
                case NoNextPage:
                    break;
                case NoData:
                    recyclerView.setVisibility(View.GONE);
                    rlDetailNodata.setVisibility(View.VISIBLE);
                    tvDetailNodata.setText(msg);
                    break;
                default:
                    break;
            }
        } else {
            switch (results) {
                case Success:
                    System.out.println("success");
                    swipeRefreshWidget.setRefreshing(false);
                    break;
                case NoNextPage:
                    if (msg.equals("end")) {
                        recyclerView.clearOnScrollListeners();
                        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                            @Override
                            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                                super.onScrolled(recyclerView, dx, dy);
                                //设置滑动至顶部菜能刷新
                                swipeRefreshWidget.setEnabled(linearLayoutManager.findFirstCompletelyVisibleItemPosition() == 0);
                                if (linearLayoutManager.findLastCompletelyVisibleItemPosition() + 1 == ((MainAdapter) adapter).getItemCount()) {
                                    ((MainAdapter) adapter).setOverFoot();
                                }
                            }
                        });
                    }
                    break;
                case NoData:
                    ((MainAdapter) adapter).setOverFoot();
                    recyclerView.clearOnScrollListeners();
                    recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                        @Override
                        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                            super.onScrolled(recyclerView, dx, dy);
                            System.out.println("OnScrolled");
                            //设置滑动至顶部菜能刷新
                            swipeRefreshWidget.setEnabled(linearLayoutManager.findFirstCompletelyVisibleItemPosition() == 0);
                        }
                    });
                    break;
            }
        }
    }
}
