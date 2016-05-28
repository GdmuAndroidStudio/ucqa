package com.dawnlightning.ucqa.viewinterface;

import com.dawnlightning.ucqa.adapter.MessageAdapter;
import com.dawnlightning.ucqa.base.Actions;
import com.dawnlightning.ucqa.bean.others.ConsultMessageBean;

import java.util.List;

/**
 * Created by Administrator on 2016/5/22.
 */
public interface IMessageView {
    public MessageAdapter getMessageAdapter();
    public List<ConsultMessageBean> getConsultMessageBeanList();
}
