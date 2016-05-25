package com.dawnlightning.ucqa.fragment;

import com.dawnlightning.ucqa.adapter.ConsultAdapter;
import com.dawnlightning.ucqa.base.Actions;
import com.dawnlightning.ucqa.base.BaseFragment;
import com.dawnlightning.ucqa.bean.others.ConsultBean;
import com.dawnlightning.ucqa.bean.others.ConsultMessageBean;
import com.dawnlightning.ucqa.viewinterface.IConsultMessageView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2016/5/20.
 */
public class ConsultFragment extends BaseFragment implements IConsultMessageView{
    private List<ConsultBean> consultBeanList;
    @Override
    public void initAdapter() {
        consultBeanList = new ArrayList<ConsultBean>();
        for(int i=0;i<4;i++)
            consultBeanList.add(new ConsultBean());
        adapter = new ConsultAdapter(getContext());
        adapter.setList(consultBeanList);
    }

    @Override
    public void Refresh(Actions action) {
        consultBeanList.clear();
        for(int i=0;i<10;i++)
            consultBeanList.add(new ConsultBean());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void LoadMore(Actions action) {
        for(int i=0;i<2;i++)
            consultBeanList.add(new ConsultBean());
        adapter.notifyDataSetChanged();
        adapter.notifyItemRemoved(adapter.getItemCount());
    }

    @Override
    public void showerror(int code, String msg) {

    }

    @Override
    public void showConsultMessageList(List<ConsultMessageBean> list, Actions action) {

    }
}
