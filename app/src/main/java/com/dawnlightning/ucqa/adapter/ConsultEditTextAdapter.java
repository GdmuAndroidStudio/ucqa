package com.dawnlightning.ucqa.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dawnlightning.ucqa.R;
import com.dawnlightning.ucqa.bean.others.UploadPicsBean;
import com.dawnlightning.ucqa.widget.PictureTitleEdittext;

import java.util.List;

/**
 * Created by Administrator on 2016/6/1.
 */
public class ConsultEditTextAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private EditTextListener editTextListener;
    private List<UploadPicsBean> uploadNeanList;

    public void setUploadNeanList(List<UploadPicsBean> uploadNeanList){
        this.uploadNeanList = uploadNeanList;
    }

    public void setEditTextListener( EditTextListener editTextListener){
        this.editTextListener=editTextListener;
    }

    public interface EditTextListener{
        public void AdapterTextChaged(int postion, String str);
    }

    public ConsultEditTextAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(context).inflate(R.layout.item_consult_edit, parent,
                false);
        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final int position1=position;
        ((ViewHolder)holder).editText.setHint("请输入图片"+(position+1)+"描述");
        ((ViewHolder)holder).editText.clearTextChangedListeners();
        ((ViewHolder)holder).editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editTextListener!=null){
                    editTextListener.AdapterTextChaged(position1,editable.toString());
                }
            }
        });
        if(uploadNeanList.get(position).getPicturetitle()!=""){
            ((ViewHolder)holder).editText.setText(uploadNeanList.get(position).getPicturetitle());
        }else{
            ((ViewHolder)holder).editText.setText("");
        }
    }

    @Override
    public int getItemCount() {
        return uploadNeanList.size();
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        private PictureTitleEdittext editText;
        public ViewHolder(View view) {
            super(view);
            editText = (PictureTitleEdittext) view.findViewById(R.id.item_consult_edittext);
        }
    }
}
