package com.dawnlightning.ucqa.base;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.dawnlightning.ucqa.R;
import com.dawnlightning.ucqa.activity.MainActivity;
import com.dawnlightning.ucqa.adapter.ClassifyAdapter;
import com.dawnlightning.ucqa.bean.others.ConsultClassifyBean;
import com.dawnlightning.ucqa.viewinterface.IBase;
import com.dawnlightning.ucqa.widget.OtherGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Kyo on 2016/5/17.
 */
public class BaseFragment extends Fragment implements IBase {

    @Bind(R.id.swipe_refresh_widget)
    SwipeRefreshLayout swipeRefreshWidget;
    @Bind(R.id.gv_classify)
    GridView gvClassify;

    private static boolean FirstIn = true;
    private Handler handler = new Handler();
    private SwipeRefreshLayout.OnRefreshListener onRefreshListener;
    private List<ConsultClassifyBean> list = new ArrayList<>(6);
    private ClassifyAdapter classifyAdapter;
    private MainActivity mainActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_base, container, false);
        ButterKnife.bind(this, view);
        initdata();
        initevent();
        initview();
        return view;
    }

    @Override
    public void initview() {

        gvClassify.setAdapter(classifyAdapter);
        swipeRefreshWidget.setColorSchemeResources(R.color.green);
        swipeRefreshWidget.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshWidget.setRefreshing(true);
            }
        });
        onRefreshListener.onRefresh();
    }

    @Override
    public void initevent() {
        swipeRefreshWidget.setOnRefreshListener( onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshWidget.setRefreshing(false);
                    }
                }, 5000);
            }
        });
        gvClassify.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ConsultClassifyBean bean = (ConsultClassifyBean) classifyAdapter.getItem(position);
                mainActivity.showtitleclassift(bean.getBwztclassarrname());
            }
        });
    }

    @Override
    public void initdata() {
        for (int i = 0; i < 6; i++) {
            ConsultClassifyBean consultClassifyBean = new ConsultClassifyBean();
            consultClassifyBean.setBwztclassarrid(""+(i+1));
            consultClassifyBean.setBwztclassarrname(""+(i+1));
            list.add(consultClassifyBean);
        }
        classifyAdapter = new ClassifyAdapter(getContext(), list);
        mainActivity = (MainActivity)getActivity();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
