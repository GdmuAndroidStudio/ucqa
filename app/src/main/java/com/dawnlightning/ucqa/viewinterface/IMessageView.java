package com.dawnlightning.ucqa.viewinterface;

import com.dawnlightning.ucqa.adapter.MessageAdapter;
import com.dawnlightning.ucqa.base.Actions;
import com.dawnlightning.ucqa.bean.others.ConsultMessageBean;
import com.dawnlightning.ucqa.bean.others.MessageBean;

import java.util.List;

/**
 * Created by Administrator on 2016/5/22.
 */
public interface IMessageView {
    public MessageAdapter getMessageAdapter();
    public List<MessageBean> getConsultMessageBeanList();
    public void getFailure(String msg,Actions actions);
    public void getError(String msg,Actions actions);
    public void noNextPage(Actions actions);
    public void noData(Actions actions);
    public void getSuccess();
}
