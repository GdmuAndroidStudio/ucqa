package com.dawnlightning.ucqa.viewinterface;

import com.dawnlightning.ucqa.base.Actions;
import com.dawnlightning.ucqa.bean.others.ConsultMessageBean;

import java.util.List;

/**
 * Created by Administrator on 2016/5/22.
 */
public interface IConsultMessageView {
    public void showerror(int code, String msg);//显示错误
    public void showConsultMessageList(List<ConsultMessageBean> list, Actions action);
}
