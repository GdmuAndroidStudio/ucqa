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
import com.dawnlightning.ucqa.adapter.MessageAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;
import fr.castorflex.android.circularprogressbar.CircularProgressBar;


/**
 * Created by Administrator on 2016/4/18.
 */
public class MessageFragment extends Fragment {

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
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
    private MessageAdapter messageAdapter;
    private int lastVisibleItem;
    private LinearLayoutManager mLayoutManager;
    private int verticalPosition;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_content, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initview();
    }

    private void initview() {
        handler=new MyHandler();
        messageAdapter=new MessageAdapter(getContext());
        swipeRefreshLayout.setColorSchemeResources(R.color.green);
        Message message = Message.obtain();
        message.arg1 = 1;
        handler.sendMessage(message);
        Message message1=Message.obtain();
        message1.arg1=0;
        handler.sendMessageDelayed(message1,4000);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Message message2=Message.obtain();
                message2.arg1=0;
                handler.sendMessageDelayed(message2,2000);
            }
        });

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView,
                                             int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == messageAdapter.getItemCount()) {
                    swipeRefreshLayout.setRefreshing(true);
                    // 此处在现实项目中，请换成网络请求数据代码，sendRequest .....
                    handler.sendEmptyMessageDelayed(0, 3000);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                verticalPosition = (recyclerView == null || recyclerView.getChildCount() == 0) ? 0 : recyclerView.getChildAt(0).getTop();
                swipeRefreshLayout.setEnabled(verticalPosition >= 0);
                lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();       /*处理滑动冲突*/
                if (lastVisibleItemPosition + 1 == messageAdapter.getItemCount()) {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Message message3=Message.obtain();
                            message3.arg1=2;
                            handler.sendMessageDelayed(message3,2000);
                        }
                    }, 1000);

                }
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
                    if (swipeRefreshLayout!=null){
                        recyclerView.setAdapter(messageAdapter);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                    break;
                case 1:
                    if (swipeRefreshLayout!=null)
                         swipeRefreshLayout.setRefreshing(true);
                    break;
                case 2:
                    messageAdapter.setCount(20);
                    messageAdapter.notifyItemRemoved(messageAdapter.getItemCount());
                    messageAdapter.notifyDataSetChanged();
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
