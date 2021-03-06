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
    public List<T> data;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {

        return data.size()+ 1;
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
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition();
                    onItemClickListener.onItemClick(holder.itemView, position);
                }
            });
        }
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
    public void add(T obj){
        this.data.add(obj);
    }
    //设置全部加载后的foot样式
    public void setOverFoot(){};
    //设置加载前的foot样式
    public void setBeforeFoot(){};
    //设置加载中的foot样式
    public void setFooting(){};

}

