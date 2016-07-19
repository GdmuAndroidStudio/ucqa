package com.dawnlightning.ucqa.activity;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.dawnlightning.ucqa.R;
import com.dawnlightning.ucqa.adapter.CommentListAdapter;
import com.dawnlightning.ucqa.adapter.DetailPicsAdapter;
import com.dawnlightning.ucqa.base.BaseActivity;
import com.dawnlightning.ucqa.base.Results;
import com.dawnlightning.ucqa.bean.others.UserBean;
import com.dawnlightning.ucqa.bean.response.consult.detailed.CommentBean;
import com.dawnlightning.ucqa.bean.response.consult.detailed.DetailedBean;
import com.dawnlightning.ucqa.presenter.ConsultDetailPresenter;
import com.dawnlightning.ucqa.share.ShareModel;
import com.dawnlightning.ucqa.share.ShareTool;
import com.dawnlightning.ucqa.utils.Options;
import com.dawnlightning.ucqa.viewinterface.ConsultDetailView;
import com.dawnlightning.ucqa.widget.ActionItem;
import com.dawnlightning.ucqa.widget.DividerLine;
import com.dawnlightning.ucqa.widget.FullyLinearLayoutManager;
import com.dawnlightning.ucqa.widget.OtherGridView;
import com.dawnlightning.ucqa.widget.RoundImageView;
import com.dawnlightning.ucqa.widget.TitlePopup;
import com.mob.tools.utils.UIHandler;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;

/**
 * Created by Kyo on 2016/5/22.
 */
public class ConsultDetailActivity extends BaseActivity implements ConsultDetailView, PlatformActionListener, Handler.Callback {

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
    @Bind(R.id.iv_detail_icon)
    RoundImageView ivDetailIcon;
    @Bind(R.id.tv_detail_subject)
    TextView tvDetailSubject;
    @Bind(R.id.tv_detail_userdata)
    TextView tvDetailUserdata;
    @Bind(R.id.tv_detail_message)
    TextView tvDetailMessage;
    @Bind(R.id.tv_detail_numview)
    TextView tvDetailNumview;
    @Bind(R.id.tv_detail_numreply)
    TextView tvDetailNumreply;
    @Bind(R.id.tv_detail_time)
    TextView tvDetailTime;
    @Bind(R.id.gv_detail_picslist)
    OtherGridView gvDetailPicslist;
    @Bind(R.id.tv_detail_nocomment)
    TextView tvDetailNocomment;

    private Handler handler = new Handler();
    private List<CommentBean> data = new ArrayList<>();
    private CommentListAdapter commentListAdapter;
    private final FullyLinearLayoutManager fullyLinearLayoutManager = new FullyLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);//false参数设置是否逆布局
    private final DividerLine dividerLine = new DividerLine(DividerLine.VERTICAL);
    private TitlePopup titlePopup;
    private DetailedBean detailedBean = new DetailedBean();
    private UserBean userBean;
    private int bwztid;
    private int uid;
    private ConsultDetailPresenter consultDetailPresenter;
    private DetailPicsAdapter detailPicsAdapter;
    private ShareTool Share;
    private PopupWindow reportdialog;
    private int loadmoreNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        userBean = (UserBean) getIntent().getSerializableExtra("userBean");
        bwztid = Integer.parseInt(getIntent().getStringExtra("bwztid"));
        uid = Integer.parseInt(getIntent().getStringExtra("uid"));
        consultDetailPresenter = new ConsultDetailPresenter(this, getcontext());
        initView();
    }

    private void initComment(List<CommentBean> data) {
        Log.d("kyo4", "" + data.size());
        if (data.size() == 0) {
            rvCommentList.setVisibility(View.GONE);
            tvDetailNocomment.setVisibility(View.VISIBLE);
        } else {
            if (data.size() <= 10) {
                for (int i = 0; i < data.size(); i++) {
                    this.data.add(data.get(i));
                }
            } else {
                for (int i = 0; i < 10; i++) {
                    this.data.add(data.get(i));
                }
            }
            commentListAdapter = new CommentListAdapter(this, this.data);
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


                }

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);

                }
            });
            if (data.size() <= 10) {
                Log.d("kyo","setoverfoot");
                commentListAdapter.setOverFoot();
            } else {
              Log.d("kkyo","setbeforefoot");
                commentListAdapter.setBeforeFoot();
            }
            commentListAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void setDetailBean(DetailedBean detailBean) {
        this.detailedBean = detailBean;
    }

    @Override
    public DetailedBean getDetailedBean() {
        return detailedBean;
    }

    @Override
    public UserBean getUserBean() {
        return userBean;
    }

    @Override
    public int getBwztid() {
        return bwztid;
    }

    @Override
    public int getUid() {
        return uid;
    }

    private void initView() {
        consultDetailPresenter.initData();
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
        if (detailedBean.getComment().size() > 10 + loadmoreNum && detailedBean.getComment().size() <= 10 + loadmoreNum + 4) {
            for (int i = 10 + loadmoreNum; i < detailedBean.getComment().size(); i++) {
                Log.d("kkyo","add comment");
                this.data.add(detailedBean.getComment().get(i));
            }
            commentListAdapter.setOverFoot();
        } else {
            for (int i = 10 + loadmoreNum; i < 4; i++) {
                this.data.add(detailedBean.getComment().get(i));
            }
            commentListAdapter.setBeforeFoot();
        }
        loadmoreNum += 4;
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
                if (titlePopup != null) {
                    titlePopup.show(view);
                } else {
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
        titlePopup.addAction(new ActionItem(this, "举报", R.mipmap.ic_report));
        if (uid == Integer.parseInt(userBean.getUserdata().getUid())) {
            titlePopup.addAction(new ActionItem(this, "采纳", R.mipmap.ic_slove));
            titlePopup.addAction(new ActionItem(this, "删除", R.mipmap.ic_delete));
        }
        titlePopup.setItemOnClickListener(new TitlePopup.OnItemOnClickListener() {
            @Override
            public void onItemClick(ActionItem item, int position) {
                if (position == 0) {
                    shareConsult();//分享
                } else if (position == 1) {
                    showReportDialog();//举报
                } else {
                    if (detailedBean.getUid().equals(userBean.getUserdata().getUid())) {
                        switch (position) {
                            case 2:
                                consultDetailPresenter.Solve(userBean.getM_auth(), bwztid);
                                break;
                            case 3:
                                consultDetailPresenter.Delete(userBean.getM_auth(), bwztid);
                                break;
                        }
                    }
                }

            }
        });
    }

    public void shareConsult() {
        Share = new ShareTool(getcontext());
        Share.setPlatformActionListener(this);
        ShareModel model = new ShareModel();
        model.setImageUrl("http://image.baidu.com/search/detail?ct=503316480&z=0&ipn=false&word=美女&pn=0&spn=0&di=0&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&ie=utf-8&oe=utf-8&in=3354&cl=2&lm=-1&cs=1735880100%2C3223346182&os=2896705163%2C2839266134&simid=&adpicid=0&fr=ala&fm=&sme=&statnum=girl&cg=girl&bdtype=-1&oriquery=&objurl=http%3A%2F%2Fe.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2Fe7cd7b899e510fb34395d1c3de33c895d0430cd1.jpg&fromurl=http%3A%2F%2Fimage.baidu.com%2Fdetail%2Fnewindex%3Fcol%3D%26tag%3D%26pn%3D0%26pid%3D31752171852%26aid%3D400266147%26user_id%3D980778976%26setid%3D-1%26sort%3D0%26newsPn%3D%26star%3D%26fr%3D%26from%3D2&gsm=0");
        model.setText(detailedBean.getContent());
        model.setTitle(detailedBean.getSubject());
        String url = String.format("https://ucqa.dawnlightning.com/space.php?uid=%s&do=bwzt&id=%s", uid, bwztid);
        model.setUrl(url);
        Share.initShareParams(model);

        Share.showShareWindow();
    }

    public void showReportDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_report, null);
        reportdialog = new PopupWindow(view, ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        final ImageView iv_consult_report_close = (ImageView) view.findViewById(R.id.iv_consult_report_close);
        final EditText et_consult_report_reason = (EditText) view.findViewById(R.id.et_consult_report_reason);
        final Button bt_consult_report_sent = (Button) view.findViewById(R.id.bt_consult_report_sent);
        bt_consult_report_sent.setClickable(false);
        bt_consult_report_sent.setBackgroundColor(getResources().getColor(R.color.lightgray));
        iv_consult_report_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reportdialog.dismiss();
            }
        });
        et_consult_report_reason.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 20) {
                    bt_consult_report_sent.setClickable(true);
                    bt_consult_report_sent.setBackgroundColor(getResources().getColor(R.color.green));
                } else {
                    bt_consult_report_sent.setClickable(false);
                    bt_consult_report_sent.setBackgroundColor(getResources().getColor(R.color.lightgray));
                }
            }
        });
        bt_consult_report_sent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consultDetailPresenter.Report(userBean.getM_auth(), bwztid, et_consult_report_reason.getText().toString());

            }
        });

        reportdialog.setFocusable(true);
        // 必须设置背景
        reportdialog.setBackgroundDrawable(new BitmapDrawable());
        // 设置点击其他地方 就消失 (只设置这个，没有效果)
        reportdialog.setOutsideTouchable(true);
        reportdialog.showAtLocation(view, Gravity.CENTER_HORIZONTAL, 0, 0);

        reportdialog.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                reportdialog = null;
            }
        });
    }

    @Override
    public void onCancel(Platform arg0, int arg1) {

        Message msg = new Message();
        msg.what = 0;
        UIHandler.sendMessage(msg, this);
        Share.dismiss();
    }

    @Override
    public void onComplete(Platform plat, int action, HashMap<String, Object> res) {
        Message msg = new Message();
        msg.arg1 = 1;
        msg.arg2 = action;
        msg.obj = plat;
        UIHandler.sendMessage(msg, this);
        Share.dismiss();
    }

    @Override
    public void onError(Platform arg0, int arg1, Throwable arg2) {
        Message msg = new Message();
        msg.what = 1;
        UIHandler.sendMessage(msg, this);
        Share.dismiss();
    }

    @Override
    public boolean handleMessage(Message msg) {
        int what = msg.what;
        if (what == 1) {
            Toast.makeText(this, "分享失败", Toast.LENGTH_SHORT).show();
            Share.dismiss();
        } else {

            Share.dismiss();
        }

        return false;
    }

    @Override
    public void setResult(Results result) {
        switch (result) {
            case Success:
//                Log.d("kyo","" + detailedBean.getPics().size());
//                Log.d("kyo","" + detailedBean.getComment().size());
//                Log.d("kyo","" + detailedBean.getMessage());
//                Log.d("kyo","" + detailedBean.getSubject());
//                Log.d("kyo","" + detailedBean.getAvatar_url());
//                for(int i = 0; i<detailedBean.getComment().size(); i++){
//                    Log.d("kyo2","" + detailedBean.getComment().get(i));
//                }
                ImageLoader.getInstance().displayImage(detailedBean.getAvatar_url(), ivDetailIcon, Options.getListOptions());
                tvDetailMessage.setText(detailedBean.getMessage());
                tvDetailSubject.setText(detailedBean.getSubject());
                tvDetailNumreply.setText(detailedBean.getReplynum());
                tvDetailNumview.setText(detailedBean.getViewnum());
                tvDetailUserdata.setText(detailedBean.getName() + "，" + detailedBean.getSex() + "，" + detailedBean.getAge() + "岁");

                String[] Date = detailedBean.getDatetime().split("/");
                tvDetailTime.setText(Date[0] + "年" + Date[1] + "月" + Date[2] + "日");

                if (detailedBean.getPics().size() != 0) {
                    gvDetailPicslist.setVisibility(View.VISIBLE);
                    detailPicsAdapter = new DetailPicsAdapter(this, detailedBean.getPics());
                    gvDetailPicslist.setAdapter(detailPicsAdapter);
                    detailPicsAdapter.notifyDataSetChanged();
                } else {
                    gvDetailPicslist.setVisibility(View.GONE);
                }

                initComment(detailedBean.getComment());

                break;
        }
    }

}
