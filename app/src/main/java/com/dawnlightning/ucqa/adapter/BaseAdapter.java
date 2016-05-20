package com.dawnlightning.ucqa.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2016/5/20.
 */
public class BaseAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    public  static final int TYPE_ITEM = 0;
    public  static final  int TYPE_FOOTER = 1;
    private int count=10;
    public List<T> data;
    private View.OnClickListener onClickListener;

    public void setCount(int count){
        this.count=count;
    }
    @Override
    public int getItemCount() {

        return count+ 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder,final int position){
        if (onClickListener != null) {
            holder.itemView.setOnClickListener(this.onClickListener);
            }
    }


    public  void setListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setList(List<T> list){
        this.data=list;
    }
    public void addAll(List<T> list){
        this.data.addAll(list);
    }
    public void clearList(){
        this.data.clear();
    }
    public T getitem(int position){
        return this.data.get(position);
    }

}

