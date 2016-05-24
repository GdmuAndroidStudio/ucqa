package com.dawnlightning.ucqa.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.dawnlightning.ucqa.R;
import com.dawnlightning.ucqa.adapter.CommentListAdapter;
import com.dawnlightning.ucqa.base.BaseActivity;
import com.dawnlightning.ucqa.bean.response.consult.detailed.CommentBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Kyo on 2016/5/22.
 */
public class ConsultDetailActivity extends BaseActivity {

    @Bind(R.id.iv_detail_back)
    ImageView ivDetailBack;
    @Bind(R.id.rv_comment_list)
    RecyclerView rvCommentList;

    private Handler handler = new Handler();
    private List<CommentBean> data = new ArrayList<>();
    private CommentListAdapter commentListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        initView();
        initData();
        System.out.println("size = " + commentListAdapter.getItemCount());
    }

    private void initView() {
        commentListAdapter = new CommentListAdapter(this, data);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rvCommentList.setLayoutManager(linearLayoutManager);
        rvCommentList.setAdapter(commentListAdapter);
        rvCommentList.setHasFixedSize(true);
        rvCommentList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.d("test", "StateChanged = " + newState);


            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.d("test", "onScrolled");

            }
        });
    }

    private void initData() {
        for (int i = 0; i < 10; i++) {
            CommentBean commentBean = new CommentBean();
            data.add(commentBean);
        }
        commentListAdapter = new CommentListAdapter(this, data);
        rvCommentList.setAdapter(commentListAdapter);
    }

    public void clickToReply() {
        Toast.makeText(this, "test", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.iv_detail_back)
    public void onClick() {
        finish();
        this.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

    }
}
