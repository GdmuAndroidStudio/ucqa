package com.dawnlightning.ucqa.viewinterface;

import com.dawnlightning.ucqa.base.Results;
import com.dawnlightning.ucqa.bean.others.UserBean;
import com.dawnlightning.ucqa.bean.response.consult.detailed.DetailedBean;

/**
 * Created by Kyo on 2016/6/5.
 */
public interface ConsultDetailView {

    public void setDetailBean(DetailedBean detailBean);
    public DetailedBean getDetailedBean();
    public UserBean getUserBean();
    public int getUid();
    public int getBwztid();
    public void initData();
    public void setResult(Results result);
}
