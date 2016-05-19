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
import android.widget.TextView;

import com.dawnlightning.ucqa.R;
import com.dawnlightning.ucqa.adapter.MessageAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by Administrator on 2016/4/18.
 */
public class MessageFragment extends Fragment {

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.consult_list_progressbar)
    ProgressBar consultListProgressbar;
    @Bind(R.id.iv_error)
    ImageView ivError;
    @Bind(R.id.tv_error)
    TextView tvError;
    @Bind(R.id.bt_error)
    Button btError;
    @Bind(R.id.ll_error)
    LinearLayout llError;
    @Bind(R.id.swipe_refresh_widget)
    SwipeRefreshLayout swipeRefreshLayout;
    private MyHandler handler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_content, container, false);
        ButterKnife.bind(this, view);
        initview();
        return view;
    }


    private void initview() {
        handler=new MyHandler();
        recyclerView.setAdapter(new MessageAdapter(getContext()));
        swipeRefreshLayout.setColorSchemeResources(R.color.jianshured);
        Message message=Message.obtain();
        message.arg1=1;
        handler.sendMessage(message);
        message.arg1=0;
        handler.sendMessageDelayed(message,2000);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Message message=Message.obtain();
                        message.arg1=0;
                        handler.sendMessage(message);
                    }
                }, 2000);
            }
        });

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    class MyHandler extends Handler {
        public MyHandler() {
        }

        // 子类必须重写此方法,接受数据
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            Log.d("MyHandler", "handleMessage......");
            switch (msg.arg1){
                case 0:
                    swipeRefreshLayout.setRefreshing(false);
                    break;
                case 1:
                    swipeRefreshLayout.setRefreshing(true);
                    break;
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
