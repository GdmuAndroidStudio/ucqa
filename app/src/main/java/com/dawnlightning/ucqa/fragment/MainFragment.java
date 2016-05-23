package com.dawnlightning.ucqa.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.dawnlightning.ucqa.R;
import com.dawnlightning.ucqa.activity.ConsultDetailActivity;
import com.dawnlightning.ucqa.adapter.MainAdapter;
import com.dawnlightning.ucqa.base.Actions;
import com.dawnlightning.ucqa.base.BaseFragment;
import com.dawnlightning.ucqa.bean.others.ConsultMessageBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kyo on 2016/5/21.
 */
public class MainFragment extends BaseFragment {

    private List<ConsultMessageBean> data = new ArrayList<>();

    public static MainFragment newInstance(Bundle bundle) {
        MainFragment mainFragment = new MainFragment();
        mainFragment.setArguments(bundle);
        return mainFragment;
    }

    @Override
    public void initAdapter() {
        adapter = new MainAdapter(getActivity(), data);
        adapter.setOnItemClickListener(new MainAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.d("test", "item position = " + position);
//                Toast.makeText(getActivity(),"test",Toast.LENGTH_SHORT).show();
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
        data.clear();
        ConsultMessageBean nullBean = new ConsultMessageBean();
        data.add(nullBean);
        for (int i = 0; i < 4; i++) {
            ConsultMessageBean consultMessageBean = new ConsultMessageBean();
            data.add(consultMessageBean);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void LoadMore(Actions actions) {
        for (int i = 0; i < 1; i++) {
            ConsultMessageBean consultMessageBean = new ConsultMessageBean();
            data.add(consultMessageBean);
        }
        adapter.notifyDataSetChanged();
        adapter.notifyItemRemoved(adapter.getItemCount());
    }
}
