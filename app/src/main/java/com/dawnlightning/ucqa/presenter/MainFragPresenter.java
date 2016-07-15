package com.dawnlightning.ucqa.presenter;

import android.content.Context;
import android.widget.Toast;

import com.dawnlightning.ucqa.adapter.MainAdapter;
import com.dawnlightning.ucqa.base.Actions;
import com.dawnlightning.ucqa.base.Results;
import com.dawnlightning.ucqa.bean.others.BannerBean;
import com.dawnlightning.ucqa.bean.others.ConsultMessageBean;
import com.dawnlightning.ucqa.bean.response.account.LoginBean;
import com.dawnlightning.ucqa.fragment.MainFragment;
import com.dawnlightning.ucqa.model.AccountModel;
import com.dawnlightning.ucqa.model.ConsultListModel;
import com.dawnlightning.ucqa.viewinterface.IMainFragView;

import java.util.List;

/**
 * Created by Kyo on 2016/5/28.
 */
public class MainFragPresenter {

    private Context context;
    private IMainFragView iMainFragView;
    private MainAdapter mainAdapter;
    private List<ConsultMessageBean> consultMessageBeanList;
    private List<BannerBean> bannerBeanList;
    private ConsultListModel consultListModel;
    private int classifyId;
    private int page;
    private int listCounts;
    private ConsultListModel.ConsultListListener refreshConsultListListener;
    private ConsultListModel.ConsultListListener loadmoreConsultListListener;
    private boolean isSending = false;

    public MainFragPresenter(final IMainFragView iMainFragView, final Context context) {
        this.iMainFragView = iMainFragView;
        mainAdapter = iMainFragView.getMainAdapter();
        consultMessageBeanList = iMainFragView.getConsultMessageBeanList();
        bannerBeanList = iMainFragView.getBannerBeanList();
        this.context = context;
        consultListModel = new ConsultListModel();
        refreshConsultListListener = new ConsultListModel.ConsultListListener() {
            @Override
            public void getSuccess(List<ConsultMessageBean> list, Actions actions) {

                System.out.println("listsize = " + list.size());
                consultMessageBeanList.addAll(list);
                for (int i = 0; i < 2; i++) {
                    ConsultMessageBean nullBean = new ConsultMessageBean();
                    consultMessageBeanList.add(0, nullBean);
                }

                //因为有一个banner一个head，需要在数组头部添加两个空实体
                for (int i = 0; i < 2; i++) {
                    ConsultMessageBean nullBean = new ConsultMessageBean();
                    list.add(0, nullBean);
                }
                mainAdapter.setList(list);
                mainAdapter.notifyDataSetChanged();
                iMainFragView.setResult(Actions.Refresh, Results.Success, "");
                isSending = false;
            }

            @Override
            public void getFailure(String msg, Actions actions) {
                iMainFragView.setResult(Actions.Refresh, Results.Fail, "");
                isSending = false;
            }

            @Override
            public void getError(String msg, Actions actions) {
                iMainFragView.setResult(Actions.Refresh, Results.Error, "服务器内部错误");
                isSending = false;
            }

            @Override
            public void noNextPage(Actions actions) {
                Toast.makeText(context, "refresh nonextpage", Toast.LENGTH_SHORT).show();
                isSending = false;
            }

            @Override
            public void noData(Actions actions) {
                iMainFragView.setResult(Actions.Refresh, Results.NoData, "暂无咨询");
                isSending = false;
            }
        };
        loadmoreConsultListListener = new ConsultListModel.ConsultListListener() {
            @Override
            public void getSuccess(List<ConsultMessageBean> list, Actions actions) {
                System.out.println("loadmore success");
                consultMessageBeanList.addAll(list);
                mainAdapter.addAll(list);
                mainAdapter.notifyDataSetChanged();
                listCounts = list.size();
                iMainFragView.setResult(Actions.LoadMore, Results.Success, "");

            }

            @Override
            public void getFailure(String msg, Actions actions) {
                System.out.println("loadmore fail");
            }

            @Override
            public void getError(String msg, Actions actions) {
                System.out.println("loadmore error");
            }

            @Override
            public void noNextPage(Actions actions) {
                System.out.println("loadmore nonextpage");
                if (listCounts % 10 != 0) {
                    System.out.println("end");
                    iMainFragView.setResult(Actions.LoadMore, Results.NoNextPage, "end");
                } else {
                    System.out.println("not end");
                    iMainFragView.setResult(Actions.LoadMore, Results.NoNextPage, "not end");
                }
            }

            @Override
            public void noData(Actions actions) {
                System.out.println("loadmore nodata");
                page--;
                iMainFragView.setResult(Actions.LoadMore, Results.NoData, "");
            }
        };
    }

    public void refresh() {
        page = 1;
        consultMessageBeanList.clear();
        bannerBeanList.clear();
        BannerBean bannerBean1 = new BannerBean();
        bannerBean1.setImg("http://img2.imgtn.bdimg.com/it/u=1514107744,1001213722&fm=21&gp=0.jpg");
        bannerBeanList.add(bannerBean1);
        BannerBean bannerBean2 = new BannerBean();
        bannerBean2.setImg("http://img3.imgtn.bdimg.com/it/u=882887467,2910558374&fm=21&gp=0.jpg");
        bannerBeanList.add(bannerBean2);
        if (classifyId == 0) {
            consultListModel.GetAllConsultList(page, refreshConsultListListener);
        } else {
            consultListModel.GetSpecifyConsult(classifyId, page, refreshConsultListListener);
        }
    }

    public void loadMore() {
        //添加消息列表实体，此处应该用 setInfoList()方法添加
//        for (int i = 0; i < 1; i++) {
//            ConsultMessageBean consultMessageBean = new ConsultMessageBean();
//            consultMessageBeanList.add(consultMessageBean);
//        }
        page++;
        if (classifyId == 0) {
            consultListModel.GetAllConsultList(page, loadmoreConsultListListener);
        } else {
            consultListModel.GetSpecifyConsult(classifyId, page, loadmoreConsultListListener);
        }
        mainAdapter.notifyDataSetChanged();
        mainAdapter.notifyItemRemoved(mainAdapter.getItemCount());
        System.out.println("page = " + page);
    }

    /**
     * 设置分类id
     *
     * @param classifyId
     */
    public void setClassifyList(int classifyId) {
        System.out.println("issending = " + isSending);
        if(!isSending){
            isSending = true;
            this.classifyId = classifyId;
            refresh();
            iMainFragView.setClassifyTitle(classifyId);
        }
        //。。。。
    }
}
