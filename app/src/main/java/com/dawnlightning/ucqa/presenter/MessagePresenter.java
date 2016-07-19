package com.dawnlightning.ucqa.presenter;

import android.content.Context;
import android.util.Log;

import java.util.List;

import com.dawnlightning.ucqa.adapter.ConsultAdapter;
import com.dawnlightning.ucqa.adapter.MessageAdapter;
import com.dawnlightning.ucqa.base.Actions;
import com.dawnlightning.ucqa.base.Results;
import com.dawnlightning.ucqa.bean.others.ConsultBean;
import com.dawnlightning.ucqa.bean.others.ConsultMessageBean;
import com.dawnlightning.ucqa.bean.others.MessageBean;
import com.dawnlightning.ucqa.bean.response.consult.NoticeBean;
import com.dawnlightning.ucqa.model.MessageModel;
import com.dawnlightning.ucqa.viewinterface.IConsultMessageView;
import com.dawnlightning.ucqa.viewinterface.IMessageView;

/**
 * Created by Administrator on 2016/5/26.
 */
public class MessagePresenter {
    private IMessageView iMessageView;
    private MessageModel messageModel = new MessageModel();

    public  MessagePresenter(IMessageView iMessageView){
        this.iMessageView = iMessageView;
    }

    public void refreshAndLoadMore(String othur,int page){
        messageModel.GetNotice(othur, page, new MessageModel.MesssgaeListListener() {
            @Override
            public void getSuccess(NoticeBean bean, Actions actions) {
                if(actions.equals(Actions.Refresh)) {
                    Log.i("test", " " + "refresh sucess");
                    iMessageView.getConsultMessageBeanList().clear();
                }
                for (NoticeBean.NoticesEntity.ListEntity listEntity : bean.getNotices().getList()) {
                    Log.i("test","content"+listEntity.toString());
                    MessageBean messageBean = new MessageBean();
                    messageBean.setAuthor(listEntity.getAuthor());
                    messageBean.setBwztid(listEntity.getBwztid());
                    messageBean.setDateline(listEntity.getDateline());
                    messageBean.setIsnew(listEntity.getIsnew());
                    messageBean.setLink(listEntity.getLink());
                    messageBean.setMessage(listEntity.getMessage());
                    messageBean.setName(listEntity.getName());
                    messageBean.setUid(listEntity.getUid());
                    messageBean.setNote(listEntity.getNote());
                    messageBean.setType(listEntity.getType());
                    messageBean.setIsfriend(listEntity.getIsfriend());
                    messageBean.setStyle(listEntity.getStyle());
                    messageBean.setAvatar_url(listEntity.getAvatar_url());
                    iMessageView.getConsultMessageBeanList().add(messageBean);
                }
                iMessageView.getMessageAdapter().notifyDataSetChanged();
                iMessageView.getActionResult("", Results.Success,actions);
            }

            @Override
            public void getFailure(String msg, Actions actions) {
                Log.i("test"," "+"refresh failure");
                iMessageView.getActionResult(msg, Results.Fail,actions);
            }

            @Override
            public void getError(String msg, Actions actions) {
                Log.i("test"," "+"refresh error");
                iMessageView.getActionResult(msg, Results.Error,actions);
            }

            @Override
            public void noNextPage(Actions actions) {
                Log.i("test"," "+"refresh noNextPage");
                iMessageView.getActionResult("", Results.NoNextPage,actions);
            }

            @Override
            public void noData(Actions actions) {
                Log.i("test"," "+"refresh noData");
                iMessageView.getActionResult("", Results.NoData,actions);
            }
        });
    }
}
