package com.dawnlightning.ucqa.viewinterface;

import com.dawnlightning.ucqa.adapter.MainAdapter;
import com.dawnlightning.ucqa.bean.others.BannerBean;
import com.dawnlightning.ucqa.bean.others.ConsultMessageBean;

import java.util.List;

/**
 * Created by Kyo on 2016/5/29.
 */
public interface IMainFragView {

    public MainAdapter getMainAdapter();
    public List<ConsultMessageBean> getConsultMessageBeanList();
    public List<BannerBean> getBannerBeanList();

}
