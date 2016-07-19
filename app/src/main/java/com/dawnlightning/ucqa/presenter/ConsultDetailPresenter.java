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
    private UserBean userBean;
    private ConsultDetailedModel consultDetailedModel = new ConsultDetailedModel();
    private int bwztid;
    private int uid;

    public ConsultDetailPresenter(final ConsultDetailView consultDetailView, final Context context){
        this.consultDetailView = consultDetailView;
        this.context = context;
        this.userBean = consultDetailView.getUserBean();
        this.bwztid = consultDetailView.getBwztid();
        this.uid = consultDetailView.getUid();
    }

    public void initData(){
        Log.d("kyo1","" + uid);
        Log.d("kyo1", userBean.getM_auth());
        Log.d("kyo1","" + bwztid);
        consultDetailedModel.GetConsultDetailed(uid, userBean.getM_auth(), bwztid, new ConsultDetailedModel.DetailedListener() {
                    @Override
                    public void getSuccess(DetailedBean bean) {
                        Log.d("kyo","Success");
                        consultDetailView.setDetailBean(bean);
                        consultDetailView.setResult(Results.Success);
                    }

                    @Override
                    public void getFailure(String msg) {
                        Log.d("kyo","Failure " + msg);
                    }

                    @Override
                    public void getError(String msg) {
                        Log.d("kyo","Error " + msg);
                    }
                }
        );
    }
}
