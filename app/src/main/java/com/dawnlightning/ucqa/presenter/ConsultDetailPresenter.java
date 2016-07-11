package com.dawnlightning.ucqa.presenter;

import android.content.Context;
import android.util.Log;

import com.dawnlightning.ucqa.base.Results;
import com.dawnlightning.ucqa.bean.others.UserBean;
import com.dawnlightning.ucqa.bean.response.consult.detailed.DetailedBean;
import com.dawnlightning.ucqa.model.ConsultDetailedModel;
import com.dawnlightning.ucqa.viewinterface.ConsultDetailView;

/**
 * Created by Kyo on 2016/6/5.
 */
public class ConsultDetailPresenter {
    private ConsultDetailView consultDetailView;
    private Context context;
    private DetailedBean detailedBean;
    private UserBean userBean;
    private ConsultDetailedModel consultDetailedModel = new ConsultDetailedModel();
    private int bwztid;

    public ConsultDetailPresenter(final ConsultDetailView consultDetailView, final Context context){
        this.consultDetailView = consultDetailView;
        this.context = context;
        this.detailedBean = consultDetailView.getDetailedBean();
        this.userBean = consultDetailView.getUserBean();
        this.bwztid = consultDetailView.getBwztid();
        Log.d("kyo",userBean.getUserdata().getUid());
        Log.d("kyo", userBean.getM_auth());
        Log.d("kyo","" + bwztid);
    }

    public void initData(){
        consultDetailedModel.GetConsultDetailed(Integer.parseInt(userBean.getUserdata().getUid()), userBean.getM_auth(), bwztid, new ConsultDetailedModel.DetailedListener() {
                    @Override
                    public void getSuccess(DetailedBean bean) {
                        Log.d("kyo","Success");
                        ConsultDetailPresenter.this.detailedBean = bean;
                        consultDetailView.setResult(Results.Success);
                    }

                    @Override
                    public void getFailure(String msg) {
                        Log.d("kyo","Failure");
                    }

                    @Override
                    public void getError(String msg) {
                        Log.d("kyo","Error");
                    }
                }
        );
    }
}
