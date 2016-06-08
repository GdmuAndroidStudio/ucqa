package com.dawnlightning.ucqa.presenter;

import android.content.Context;

import com.dawnlightning.ucqa.viewinterface.ConsultDetailView;

/**
 * Created by Kyo on 2016/6/5.
 */
public class ConsultDetailPresenter {
    private ConsultDetailView consultDetailView;
    private Context context;

    public ConsultDetailPresenter(final ConsultDetailView consultDetailView, final Context context){
        this.consultDetailView = consultDetailView;
        this.context = context;
    }
}
