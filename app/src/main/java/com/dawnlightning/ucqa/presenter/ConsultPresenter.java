package com.dawnlightning.ucqa.presenter;

import android.content.Context;
import java.util.List;

import com.dawnlightning.ucqa.adapter.ConsultAdapter;
import com.dawnlightning.ucqa.bean.others.ConsultBean;
import com.dawnlightning.ucqa.bean.others.ConsultMessageBean;
import com.dawnlightning.ucqa.viewinterface.IConsultMessageView;

/**
 * Created by Administrator on 2016/5/26.
 */
public class ConsultPresenter {
    private Context context;
    private IConsultMessageView iConsultMessageView;
    private ConsultAdapter consultAdapter;
    private List<ConsultBean> consultBeanList;

    public  ConsultPresenter(IConsultMessageView iConsultMessageView, Context context){
        this.iConsultMessageView = iConsultMessageView;
        consultAdapter = iConsultMessageView.getConsultAdapter();
        consultBeanList=iConsultMessageView.getConsultBeanList();
        this.context = context;
    }

    public void refresh(){
        consultBeanList.clear();
        for(int i=0;i<10;i++) {
            ConsultBean consultBean = new ConsultBean();
            consultBean.setMessage("adsdqweq");
            consultBeanList.add(consultBean);
        }
        consultAdapter.notifyDataSetChanged();
    }

    public void loadMore(){
        for(int i=0;i<2;i++) {
            ConsultBean consultBean = new ConsultBean();
            consultBean.setBwztclassid(102);
            consultBean.setBwztdivisionid(214);
            consultBean.setMessage("dasdasdwqwe");
            consultBeanList.add(new ConsultBean());
        }
        consultAdapter.notifyDataSetChanged();
        consultAdapter.notifyItemRemoved(consultAdapter.getItemCount());
    }
}
