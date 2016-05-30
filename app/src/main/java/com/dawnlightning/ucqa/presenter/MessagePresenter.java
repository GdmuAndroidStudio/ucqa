package com.dawnlightning.ucqa.presenter;

import android.content.Context;
import java.util.List;

import com.dawnlightning.ucqa.adapter.ConsultAdapter;
import com.dawnlightning.ucqa.adapter.MessageAdapter;
import com.dawnlightning.ucqa.bean.others.ConsultBean;
import com.dawnlightning.ucqa.bean.others.ConsultMessageBean;
import com.dawnlightning.ucqa.viewinterface.IConsultMessageView;
import com.dawnlightning.ucqa.viewinterface.IConsultView;
import com.dawnlightning.ucqa.viewinterface.IMessageView;

/**
 * Created by Administrator on 2016/5/26.
 */
public class MessagePresenter {
    private Context context;
    private IMessageView iMessageView;
    private MessageAdapter consultAdapter;
    private List<ConsultMessageBean> consultMessageBeanList;

    public  MessagePresenter(IMessageView iMessageView, Context context){
        this.iMessageView = iMessageView;
        consultAdapter = iMessageView.getMessageAdapter();
        consultMessageBeanList=iMessageView.getConsultMessageBeanList();
        this.context = context;
    }

    public void refresh(){
        consultMessageBeanList.clear();
        for(int i=0;i<10;i++) {
            ConsultMessageBean consultMessageBean = new ConsultMessageBean();
            consultMessageBean.setBwztclassid("102");
            consultMessageBean.setBwztdivisionid("214");
            consultMessageBean.setMessage("dasdasdwqwe");
            consultMessageBeanList.add(consultMessageBean);
        }
        consultAdapter.notifyDataSetChanged();
    }

    public void loadMore(){
        for(int i=0;i<2;i++) {
            ConsultMessageBean consultMessageBean = new ConsultMessageBean();
            consultMessageBean.setBwztclassid("102");
            consultMessageBean.setBwztdivisionid("214");
            consultMessageBean.setMessage("dasdasdwqwe");
            consultMessageBeanList.add(consultMessageBean);
        }
        consultAdapter.notifyDataSetChanged();
        consultAdapter.notifyItemRemoved(consultAdapter.getItemCount());
    }
}
