package com.dawnlightning.ucqa.fragment;

import com.dawnlightning.ucqa.adapter.ConsultAdapter;
import com.dawnlightning.ucqa.base.Actions;
import com.dawnlightning.ucqa.base.BaseFragment;


/**
 * Created by Administrator on 2016/5/20.
 */
public class ConsultFragment extends BaseFragment {
    int count=8;
    @Override
    public void initAdapter() {
        adapter = new ConsultAdapter(getContext());
        adapter.setCount(count);
    }

    @Override
    public void Refresh(Actions action) {
        count=8;
        adapter.setCount(count);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void LoadMore(Actions action) {
        count+=2;
        adapter.setCount(count);
        adapter.notifyDataSetChanged();
        adapter.notifyItemRemoved(adapter.getItemCount());
    }
}
