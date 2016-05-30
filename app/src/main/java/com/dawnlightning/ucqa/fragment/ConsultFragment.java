package com.dawnlightning.ucqa.fragment;

import com.dawnlightning.ucqa.adapter.ConsultAdapter;
import com.dawnlightning.ucqa.base.Actions;
import com.dawnlightning.ucqa.base.BaseFragment;
import com.dawnlightning.ucqa.bean.others.ConsultBean;
import com.dawnlightning.ucqa.bean.others.ConsultMessageBean;
import com.dawnlightning.ucqa.presenter.ConsultPresenter;
import com.dawnlightning.ucqa.viewinterface.IConsultMessageView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2016/5/20.
 */
public class ConsultFragment extends BaseFragment implements IConsultMessageView{
    private List<ConsultBean> consultBeanList;
    private ConsultPresenter consultPresenter;
    @Override
    public void initAdapter() {
        consultBeanList = new ArrayList<ConsultBean>();
        adapter = new ConsultAdapter(getContext());
        consultPresenter = new ConsultPresenter(this,getContext());
        for(int i=0;i<4;i++)
            consultBeanList.add(new ConsultBean());
        adapter.setList(consultBeanList);
    }

    @Override
    public void Refresh(Actions action) {
        consultPresenter.refresh();
    }

    @Override
    public void LoadMore(Actions action) {
        consultPresenter.loadMore();
    }

    @Override
    public ConsultAdapter getConsultAdapter() {
        return (ConsultAdapter)adapter;
    }

    @Override
    public List<ConsultBean> getConsultBeanList() {
        return consultBeanList;
    }
}
