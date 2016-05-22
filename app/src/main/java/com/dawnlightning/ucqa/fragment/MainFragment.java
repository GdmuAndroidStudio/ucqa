package com.dawnlightning.ucqa.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.dawnlightning.ucqa.adapter.BaseAdapter;
import com.dawnlightning.ucqa.adapter.RecyclerViewAdapter;
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
        adapter = new RecyclerViewAdapter(getActivity(), data);
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
    public void Refresh(Actions actions) {
        data.clear();
        for (int i = 0; i < 6; i++) {
            ConsultMessageBean consultMessageBean = new ConsultMessageBean();
            data.add(consultMessageBean);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void LoadMore(Actions actions) {
        for (int i = 0; i < 4; i++) {
            ConsultMessageBean consultMessageBean = new ConsultMessageBean();
            data.add(consultMessageBean);
        }
        adapter.notifyDataSetChanged();
        adapter.notifyItemRemoved(adapter.getItemCount());
    }
}
