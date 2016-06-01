package com.dawnlightning.ucqa.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.dawnlightning.ucqa.R;
import com.dawnlightning.ucqa.adapter.CommentListAdapter;
import com.dawnlightning.ucqa.base.BaseActivity;
import com.dawnlightning.ucqa.bean.response.consult.detailed.CommentBean;
import com.dawnlightning.ucqa.widget.ActionItem;
import com.dawnlightning.ucqa.widget.DividerLine;
import com.dawnlightning.ucqa.widget.FullyLinearLayoutManager;
import com.dawnlightning.ucqa.widget.TitlePopup;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Kyo on 2016/5/22.
 */
public class ConsultDetailActivity extends BaseActivity {

    @Bind(R.id.rv_comment_list)
    RecyclerView rvCommentList;
    @Bind(R.id.ed_setcomment)
    EditText edSetcomment;
    @Bind(R.id.detail_toolbar)
    Toolbar toolbar;
    @Bind(R.id.iv_detail_back)
    ImageView ivDetailBack;
    @Bind(R.id.iv_detail_actions)
    ImageView ivDetailActions;

    private Handler handler = new Handler();
    private List<CommentBean> data = new ArrayList<>();
    private CommentListAdapter commentListAdapter;
    private final FullyLinearLayoutManager fullyLinearLayoutManager = new FullyLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);//false参数设置是否逆布局
    private final DividerLine dividerLine = new DividerLine(DividerLine.VERTICAL);
    private TitlePopup titlePopup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        initView();
        initData();
    }

    private void initView() {
        commentListAdapter = new CommentListAdapter(this, data);
        dividerLine.setSize(1);
        dividerLine.setColor(this.getResources().getColor(R.color.lightgray));
        rvCommentList.addItemDecoration(dividerLine);
        rvCommentList.setLayoutManager(fullyLinearLayoutManager);
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
        data.clear();
        for (int i = 0; i < 10; i++) {
            CommentBean commentBean = new CommentBean();
            commentBean.setName("user" + (i + 1));
            data.add(commentBean);
        }
        commentListAdapter.notifyDataSetChanged();
    }

    public void clickToReply(String name) {
        edSetcomment.setHint("@" + name);
        edSetcomment.setFocusable(true);
        edSetcomment.setFocusableInTouchMode(true);
        edSetcomment.requestFocus();
        edSetcomment.requestFocusFromTouch();
        InputMethodManager inputManager =
                (InputMethodManager) edSetcomment.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.showSoftInput(edSetcomment, 0);
    }

    public void LoadMore() {
        for (int i = 0; i < 1; i++) {
            CommentBean commentBean = new CommentBean();
            commentBean.setName("add user");
            data.add(commentBean);
        }
        commentListAdapter.notifyDataSetChanged();
    }

    @OnClick({R.id.iv_detail_back, R.id.iv_detail_actions})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_detail_back:
                finish();
                this.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                break;
            case R.id.iv_detail_actions:
                if(titlePopup!=null){
                    titlePopup.show(view);
                }else{
                    initdialoglistview();
                }
                break;
        }
    }

    private void initdialoglistview() {
        //实例化标题栏弹窗
        titlePopup = new TitlePopup(this, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //给标题栏弹窗添加子类
        titlePopup.addAction(new ActionItem(this, "分享", R.mipmap.ic_share));
        titlePopup.addAction(new ActionItem(this,"举报",R.mipmap.ic_report));
//        if(detailedBean.getUid().equals(userBean.getUserdata().getUid())){
//            titlePopup.addAction(new ActionItem(this, "采纳", R.mipmap.ic_slove));
//            titlePopup.addAction(new ActionItem(this, "删除",  R.mipmap.ic_delete));
//        }
//        titlePopup.setItemOnClickListener(new TitlePopup.OnItemOnClickListener() {
//            @Override
//            public void onItemClick(ActionItem item, int position) {
//                if (position==0){
//                    shareconsult();//分享
//                }else if(position==1){
//                    showreportdialog();//举报
//                }else{
//                    if(detailedBean.getUid().equals(userBean.getUserdata().getUid())) {
//                        switch (position){
//                            case 2:
//                                dosolve(detailedBean.getBwztid(),userBean.getM_auth());
//                                break;
//                            case 3:
//                                dodelete(detailedBean.getBwztid(), userBean.getM_auth());
//                                break;
//                        }
//                    }
//                }
//
//            }
//        });


    }

}
