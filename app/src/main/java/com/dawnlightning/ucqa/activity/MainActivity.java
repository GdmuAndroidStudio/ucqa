package com.dawnlightning.ucqa.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
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
import com.dawnlightning.ucqa.bean.others.UserBean;
import com.dawnlightning.ucqa.bean.others.UserData;
import com.dawnlightning.ucqa.common.Code;
import com.dawnlightning.ucqa.fragment.ConsultFragment;
import com.dawnlightning.ucqa.fragment.MainFragment;
import com.dawnlightning.ucqa.fragment.MessageFragment;
import com.dawnlightning.ucqa.model.TestModel;
import com.dawnlightning.ucqa.presenter.MainPresenter;
import com.dawnlightning.ucqa.utils.BaseTools;
import com.dawnlightning.ucqa.utils.ImageLoaderOptions;
import com.dawnlightning.ucqa.utils.Options;
import com.dawnlightning.ucqa.viewinterface.IMainView;
import com.dawnlightning.ucqa.widget.DragLayout;
import com.dawnlightning.ucqa.widget.MyViewPager;
import com.dawnlightning.ucqa.widget.RoundImageView;
import com.nineoldandroids.view.ViewHelper;
import com.nostra13.universalimageloader.core.ImageLoader;

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
    public MyFragmentPagerAdapter myFragmentPagerAdapter;
    private MainFragment mainFragment;
    private MessageFragment messageFragment;
    private ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
    private ConsultFragment consultFragment;
    private List<Integer> newItems = new ArrayList<>();  //左拉列表要显示new字样的item数组
    private List<Integer> unreadNumbers = new ArrayList<>();  //未读信息数数组
    private MainPresenter mainPresenter;
    private boolean isUpDate = false; // 是否要更新，初始化为false
    private String classifyName = "全部";
    public static UserBean userBean = new UserBean();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
        initView();
        initDragLayout();
        initLeftContent();
        checkUnreadMessage();
        checkUpDate();
        TestModel model = new TestModel();
        //model.GetClassify();
        //model.GetConsultList();
        //model.GetConsultDetailed();
        //model.GetSpecifyConsult();
         model.GetNotice();
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

    @Override
    public UserBean getUserBean() {
        return userBean;
    }

    @Override
    public List<Integer> getNewItems() {
        return newItems;
    }

    @Override
    public List<Integer> getUnreadNumbers() {
        return unreadNumbers;
    }

    @Override
    public boolean setCheckUpDate() {
        return isUpDate;
    }





    /*
 * 显示分类（中部）
* */
    public void showtitleclassift(String strclassify) {
        title.setText(strclassify);
        classifyName = strclassify;
    }

    private void initData(){
        mainPresenter = new MainPresenter(this, this);
        mainPresenter.initUserData();
        mainPresenter.getUnreadNumbers();
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
        ivIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getcontext(),PersonalDataActivity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("userdata",userBean);
                intent.putExtras(bundle);
                startActivityForResult(intent, Code.ModifyPersonaldataForResult);
            }
        });
        Bundle data = new Bundle();
        data.putString("classifyName", classifyName);
        mainFragment = MainFragment.newInstance(data);
        messageFragment = new MessageFragment();
        consultFragment = new ConsultFragment();
        fragmentArrayList.add(mainFragment);
        fragmentArrayList.add(messageFragment);
        fragmentArrayList.add(consultFragment);
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentArrayList);
        mvpMainactivity.setOffscreenPageLimit(3);
        mvpMainactivity.setAdapter(myFragmentPagerAdapter);
        lvMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {
                selectview(position);
                mvpMainactivity.getAdapter().notifyDataSetChanged();

            }
        });
        initUser();
    }

    /**
     * 加载用户数据，如头像，昵称
     */
    private void initUser() {
        ImageLoader.getInstance().displayImage(userBean.getUserdata().getAvatar_url(), ivIcon, Options.getListOptions());
        tvUsername.setText(userBean.getUserdata().getName());
    }

    /**
     * 检测是否有未读信息
     */
    private void checkUnreadMessage() {
        if (unreadNumbers.get(0) <= 0) {
            unreadMsgNumber.setVisibility(View.GONE);
        } else {
            unreadMsgNumber.setVisibility(View.VISIBLE);
            unreadMsgNumber.setText("" + unreadNumbers.get(0));
        }
    }

    @Override
    public void checkUpDate() {
        if (setCheckUpDate() == true) {
            ((Menu) menuadapter.getItem(4)).setStatus(1);
            menuadapter.notifyDataSetChanged();
        }
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
                if (((Menu) menuadapter.getItem(1)).getStatus() == 1) {
                    ((Menu) menuadapter.getItem(1)).setStatus(0);
                    menuadapter.notifyDataSetChanged();
                }
                unreadNumbers.set(0,0);
                checkUnreadMessage();
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
            case 4:
                Intent intent=new Intent();
                intent.setClass(MainActivity.this, SettingActivity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("userdata",userBean);
                intent.putExtras(bundle);
                startActivityForResult(intent, Code.LoginoffForResult);
                //设置
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
                if (unreadMsgNumber.getVisibility() == View.VISIBLE) {
                    ViewHelper.setAlpha(unreadMsgNumber, 1 - percent);
                }

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
        mainPresenter.getNewItems();
        showUpDate();
    }

    public void showUpDate() {
        for (int i = 0; i < newItems.size(); i++) {
            ((Menu) menuadapter.getItem(newItems.get(i))).setStatus(1);
        }
        menuadapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        if (requestCode==requestCode) {
            switch (resultCode) {
                case Code.ConsultForResult:
                    mvpMainactivity.setCurrentItem(0);
                    break;
                case Code.LoginoffForResult:
                    finish();
                    break;
                case Code.ModifyPersonaldataForResult:
                    String username=data.getStringExtra("username");
                    String avater=data.getStringExtra("avatar");
                    userBean.getUserdata().setName(username);
                    ImageLoader.getInstance().displayImage(avater,ivIcon, ImageLoaderOptions.getListOptions());
                    tvUsername.setText(username);
                    break;
                default:
                    break;
            }
        }


        super.onActivityResult(requestCode, resultCode, data);
    }

}
