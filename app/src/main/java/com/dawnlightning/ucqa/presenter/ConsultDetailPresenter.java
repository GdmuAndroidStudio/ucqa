package com.dawnlightning.ucqa.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.dawnlightning.ucqa.base.Results;
import com.dawnlightning.ucqa.bean.others.UserBean;
import com.dawnlightning.ucqa.bean.response.consult.detailed.DetailedBean;
import com.dawnlightning.ucqa.model.ConsultDetailedModel;
import com.dawnlightning.ucqa.viewinterface.ConsultDetailView;
import com.dawnlightning.ucqa.utils.NetworkUtil;

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

    public ConsultDetailPresenter(final ConsultDetailView consultDetailView, final Context context) {
        this.consultDetailView = consultDetailView;
        this.context = context;
        this.userBean = consultDetailView.getUserBean();
        this.bwztid = consultDetailView.getBwztid();
        this.uid = consultDetailView.getUid();
    }

    public void initData() {
        Log.d("kyo1", "" + uid);
        Log.d("kyo1", userBean.getM_auth());
        Log.d("kyo1", "" + bwztid);
        consultDetailedModel.GetConsultDetailed(uid, userBean.getM_auth(), bwztid, new ConsultDetailedModel.DetailedListener() {
                    @Override
                    public void getSuccess(DetailedBean bean) {
                        Log.d("kyo", "Success");
                        consultDetailView.setDetailBean(bean);
                        consultDetailView.setResult(Results.Success);
                    }

                    @Override
                    public void getFailure(String msg) {
                        Log.d("kyo", "Failure " + msg);
                    }

                    @Override
                    public void getError(String msg) {
                        Log.d("kyo", "Error " + msg);
                    }
                }
        );
    }

    public void Report(String m_auth, int id, String reason) {
        if (NetworkUtil.checkNetwork(context)) {
            consultDetailedModel.Report(m_auth, id, reason, new ConsultDetailedModel.OperateListener() {
                @Override
                public void doSuccess(String msg) {
                    Toast.makeText(context, "举报成功", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void doFailure(String msg) {
                    Toast.makeText(context, "举报失败，请尝试重新举报", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void doError(String msg) {
                    Toast.makeText(context, "发生未知错误，请尝试重新举报", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(context, "网络连接不可用", Toast.LENGTH_SHORT).show();
        }
    }

    public void Solve(String m_auth, int bwztid) {
        if (NetworkUtil.checkNetwork(context)) {
            consultDetailedModel.Solve(m_auth, bwztid, new ConsultDetailedModel.OperateListener() {
                @Override
                public void doSuccess(String msg) {
                    Toast.makeText(context, "采纳成功", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void doFailure(String msg) {
                    Toast.makeText(context, "采纳失败，请尝试重新举报", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void doError(String msg) {
                    Toast.makeText(context, "采纳失败，请尝试重新举报", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(context, "网络连接不可用", Toast.LENGTH_SHORT).show();
        }
    }

    public void Delete(String m_auth, int bwztid) {
        if (NetworkUtil.checkNetwork(context)) {
            consultDetailedModel.Delete(m_auth, bwztid, new ConsultDetailedModel.OperateListener() {
                @Override
                public void doSuccess(String msg) {
                    Toast.makeText(context, "删除成功", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void doFailure(String msg) {
                    Toast.makeText(context, "删除失败，请尝试重新举报", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void doError(String msg) {
                    Toast.makeText(context, "删除失败，请尝试重新举报", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(context, "网络连接不可用", Toast.LENGTH_SHORT).show();
        }
    }
}
