package com.dawnlightning.ucqa.presenter;

import com.dawnlightning.ucqa.base.Actions;
import com.dawnlightning.ucqa.bean.others.ConsultMessageBean;
import com.dawnlightning.ucqa.model.ConsultListModel;

import java.util.List;

/**
 * 作者：Administrator on 2016/5/30 16:09
 * 邮箱：823894716@qq.com
 */
public class SimplePresenter {
    private ConsultListModel model=new ConsultListModel();
    public SimplePresenter(/*View的接口实现类*/){

    }
    public void loadConsultList(){
        model.GetAllConsultList(1, new ConsultListModel.ConsultListListener() {
            @Override
            public void getSuccess(List<ConsultMessageBean> list, Actions actions) {
                //actions只有刷新和加载更多，需要区别对待
                //获取数据成功后的操作
            }

            @Override
            public void getFailure(String msg, Actions actions) {
                //获取数据成功后的操作
            }

            @Override
            public void getError(String msg, Actions actions) {
                //服务器挂了
            }

            @Override
            public void noNextPage(Actions actions) {
                //没有下一页
            }

            @Override
            public void noData(Actions actions) {
                //没有更多的咨询
            }
        });
    }
}
