package com.dawnlightning.ucqa.viewinterface;

import com.dawnlightning.ucqa.adapter.MainAdapter;
import com.dawnlightning.ucqa.base.Actions;
import com.dawnlightning.ucqa.base.Results;
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

    public void setResult(Actions actions, Results results, String msg);

    public void setClassifyTitle(int classifyID);

}
