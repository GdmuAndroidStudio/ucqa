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

import butterknife.Bind;
import butterknife.ButterKnife;
import fr.castorflex.android.circularprogressbar.CircularProgressBar;


/**
 * Created by Administrator on 2016/4/18.
 */
public class MessageFragment extends BaseFragment {

    private int count=12;

    @Override
    public void initAdapter() {
        adapter = new MessageAdapter(getContext());
        adapter.setCount(count);
        adapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener(){
            @Override
            public void onItemLongClick(View view, int position) {
                Log.i("test", "item position = " + position);
            }

            @Override
            public void onItemClick(View view, int position) {
                Log.i("test", "item position = " + position);
            }
        });
    }

    @Override
    public void Refresh(Actions action) {
        count=12;
        adapter.setCount(count);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void LoadMore(Actions action) {
        count+=2;
        adapter.setCount(count);
        adapter.notifyDataSetChanged();
        adapter.notifyItemRemoved(adapter.getItemCount());;
    }
}
