package com.dawnlightning.ucqa.presenter;

import android.content.Context;
import android.util.Log;

import java.util.List;

import com.dawnlightning.ucqa.adapter.ConsultAdapter;
import com.dawnlightning.ucqa.base.Actions;
import com.dawnlightning.ucqa.bean.others.ConsultBean;
import com.dawnlightning.ucqa.bean.others.ConsultMessageBean;
import com.dawnlightning.ucqa.model.ConsultDetailedModel;
import com.dawnlightning.ucqa.model.ConsultListModel;
import com.dawnlightning.ucqa.viewinterface.IConsultMessageView;

/**
 * Created by Administrator on 2016/5/26.
 */
public class ConsultPresenter {
    private ConsultListModel consultListModel = new ConsultListModel();
    private IConsultMessageView iConsultMessageView;

    public  ConsultPresenter(IConsultMessageView iConsultMessageView){
        this.iConsultMessageView = iConsultMessageView;
    }


    public void refreshAndLoadMore(int uid,String othur,int page){
        consultListModel.GetMyConsult(uid, othur, page, new ConsultListModel.ConsultListListener() {
            @Override
            public void getSuccess(List<ConsultMessageBean> list, Actions actions) {
                if(actions.equals(Actions.Refresh)){
                    iConsultMessageView.getConsultBeanList().clear();
                }
                for(ConsultMessageBean consultMessageBean : list){
                    Log.i("test"," "+"refresh getSuccess"+consultMessageBean.toString()+" loadmore"+list.size());
                    iConsultMessageView.getConsultBeanList().add(consultMessageBean);
                }
                iConsultMessageView.getConsultAdapter().notifyDataSetChanged();
                iConsultMessageView.getSuccess();
            }

            @Override
            public void getFailure(String msg, Actions actions) {
                Log.i("test"," "+"refresh getFailure");
                iConsultMessageView.getFailure(msg,actions);
            }

            @Override
            public void getError(String msg, Actions actions) {
                Log.i("test"," "+"refresh getError");
                iConsultMessageView.getError(msg,actions);
            }

            @Override
            public void noNextPage(Actions actions) {
                Log.i("test"," "+"refresh noNextPage");
                iConsultMessageView.noNextPage(actions);
            }

            @Override
            public void noData(Actions actions) {
                Log.i("test"," "+"refresh noData");
                iConsultMessageView.noData(actions);
            }
        });
        iConsultMessageView.getConsultAdapter().notifyDataSetChanged();
    }
}