package com.dawnlightning.ucqa.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.dawnlightning.ucqa.R;
import com.dawnlightning.ucqa.adapter.LeftMenuAdapter;
import com.dawnlightning.ucqa.adapter.MyFragmentPagerAdapter;
import com.dawnlightning.ucqa.base.BaseActivity;
import com.dawnlightning.ucqa.base.Menu;
import com.dawnlightning.ucqa.fragment.ConsultFragment;
import com.dawnlightning.ucqa.fragment.MainFragment;
import com.dawnlightning.ucqa.fragment.MessageFragment;
import com.dawnlightning.ucqa.model.TestModel;
import com.dawnlightning.ucqa.utils.BaseTools;
import com.dawnlightning.ucqa.viewinterface.IMainView;
import com.dawnlightning.ucqa.widget.DragLayout;
import com.dawnlightning.ucqa.widget.MyViewPager;
import com.dawnlightning.ucqa.widget.RoundImageView;
import com.nineoldandroids.view.ViewHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 *
 */
public class MainActivity extends BaseActivity implements IMainView {
    @Bind(R.id.iv_icon)
    RoundImageView ivIcon;
    @Bind(R.id.tv_username)
    TextView tvUsername;
    @Bind(R.id.lefe_content)
    LinearLayout lefeContent;
    @Bind(R.id.lv_menu)
    ListView lvMenu;
    @Bind(R.id.iv_menu)
    ImageView ivMenu;
    @Bind(R.id.unread_msg_number)
    TextView unreadMsgNumber;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.mvp_mainactivity)
    MyViewPager mvpMainactivity;
    @Bind(R.id.dl_main)
    DragLayout dlMain;

    private TestModel testModel = new TestModel();
    /**
     * 屏幕宽度
     */
    private int mScreenWidth = 0;
    /**
     * 屏幕高度
     */
    private int mScreenHeight = 0;
    private List<Menu> menuList = new ArrayList<Menu>();
    private LeftMenuAdapter menuadapter;
    private MyFragmentPagerAdapter myFragmentPagerAdapter;
    private MainFragment mainFragment;
    private MessageFragment messageFragment;
    private ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
    private ConsultFragment consultFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initDragLayout();
        initLeftContent();
        TestModel model = new TestModel();
        //model.GetClassify();
        //model.GetConsultList();
        //model.GetConsultDetailed();
        //model.GetSpecifyConsult();
        //     model.GetNotice();
//        model.GetCommentList();
//        model.GetMyConsult();
        // model.UploadPicture();
        //  model.PublicIssuse();
        // model.Solove();
        //model.Comment();
        //model.Reply();
        // model.Report();
        // model.Delete();
        // model.Upadte();

    }

    public void test() {

    }

    @Override
    public void showupdate() {
        ((Menu) menuadapter.getItem(4)).setStatus(1);
        menuadapter.notifyDataSetChanged();
    }

    /*
 * 显示分类（中部）
* */
    @Override
    public void showtitleclassift(String strclassify) {
        title.setText(strclassify);
    }

    private void initView() {
        showtitleclassift("全部");
        mvpMainactivity.setPagingEnabled(false);//不允许滑动
        ivMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlMain.open();
            }
        });
        mainFragment = new MainFragment();
        messageFragment = new MessageFragment();
        consultFragment = new ConsultFragment();
        fragmentArrayList.add(mainFragment);
        fragmentArrayList.add(messageFragment);
        fragmentArrayList.add(consultFragment);
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentArrayList);
        mvpMainactivity.setAdapter(myFragmentPagerAdapter);
        lvMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {
                selectview(position);

            }
        });
    }

    public void selectview(int id) {
        switch (id) {
            case 0:
                showtitleclassift("全部");
                dlMain.close();
                mvpMainactivity.setCurrentItem(0);
                break;
            case 1:
                showtitleclassift("消息列表");
                dlMain.close();
                mvpMainactivity.setCurrentItem(1);
                break;
            case 2:
                showtitleclassift("我的咨询");
                dlMain.close();
                mvpMainactivity.setCurrentItem(2);
                break;
            case 3:
                showtitleclassift("咨询");
                dlMain.close();
                Intent consult = new Intent();
                consult.setClass(this, ConsultActivity.class);
                startActivity(consult);
                break;
        }
    }

    /**
     * 初始化侧拉栏
     */
    private void initDragLayout() {
        dlMain.setDragListener(new DragLayout.DragListener() {
            //界面打开的时候
            @Override
            public void onOpen() {

            }

            //界面关闭的时候
            @Override
            public void onClose() {
            }

            //界面滑动的时候
            @Override
            public void onDrag(float percent) {
                ViewHelper.setAlpha(ivMenu, 1 - percent);
            }
        });
    }

    /**
     * 初始化左侧栏的相关信息
     */
    private void initLeftContent() {
        mScreenWidth = BaseTools.getWindowsWidth(this);
        mScreenHeight = BaseTools.getWindowsHeight(this);
        System.out.println(mScreenHeight + " " + mScreenWidth);
        LinearLayout.LayoutParams IconParams = (LinearLayout.LayoutParams) ivIcon.getLayoutParams();
        IconParams.setMargins((int) (mScreenWidth * 0.25), (int) (mScreenHeight * 0.1), 0, 0);
        ivIcon.setLayoutParams(IconParams);
        LinearLayout.LayoutParams NameParams = (LinearLayout.LayoutParams) tvUsername.getLayoutParams();
        NameParams.setMargins((int) (mScreenWidth * 0.25), 5, 0, 0);
        tvUsername.setLayoutParams(NameParams);
        menuList.add(new Menu("主        页", 0));
        menuList.add(new Menu("我的消息", 0));
        menuList.add(new Menu("我的咨询", 0));
        menuList.add(new Menu("我要咨询", 0));
        menuList.add(new Menu("设        置", 0));
        menuadapter = new LeftMenuAdapter(MainActivity.this, menuList);
        lvMenu.setAdapter(menuadapter);

    }

}
