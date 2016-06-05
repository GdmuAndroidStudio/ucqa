package com.dawnlightning.ucqa.viewinterface;

import com.dawnlightning.ucqa.adapter.ConsultAdapter;
import com.dawnlightning.ucqa.base.Actions;
import com.dawnlightning.ucqa.bean.others.ConsultBean;
import com.dawnlightning.ucqa.bean.others.ConsultMessageBean;

import java.util.List;

/**
 * Created by Administrator on 2016/5/22.
 */
public interface IConsultMessageView {
    public ConsultAdapter getConsultAdapter();
    public List<ConsultMessageBean> getConsultBeanList();
    public void getFailure(String msg,Actions actions);
    public void getError(String msg,Actions actions);
    public void noNextPage(Actions actions);
    public void noData(Actions actions);
    public void getSuccess();
}
