package com.dawnlightning.ucqa.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dawnlightning.ucqa.R;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

/**
 * Created by Administrator on 2016/5/18.
 */
public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public  static final int TYPE_ITEM = 0;
    public  static final  int TYPE_FOOTER = 1;
    private Context context;
    private View.OnClickListener onClickListener;
    private FootViewHolder footViewHolder;
    private int count=10;

    public MessageAdapter(Context context) {
        this.context = context;
    }

    public void setCount(int count){
        this.count=count;
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
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_message, null);
            view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
            return new ItemViewHolder(view);
        }
         else if (viewType ==TYPE_FOOTER) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_foot,null);
            view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
            footViewHolder=new FootViewHolder(view);
            return footViewHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            if (onClickListener != null) {
                holder.itemView.setOnClickListener(this.onClickListener);
            }

            if (((ItemViewHolder) holder).photoView.getTag()!=null&&((ItemViewHolder)((ItemViewHolder) holder)).photoView.getTag().equals(position)){
            }else{
                ((ItemViewHolder) holder).photoView.setTag(position);
            }
        }
    }


    public int getItemCount() {
        return count+1;
    }

    private void setListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView content;
        TextView textView;
        ImageView photoView;

        public ItemViewHolder(View view) {
            super(view);
            photoView = (ImageView) view.findViewById(R.id.rv_message_icon);
            content=(TextView)view.findViewById(R.id.tv_message_content);
            textView=(TextView)view.findViewById(R.id.tv_messsage_note);
        }
    }

    class FootViewHolder extends RecyclerView.ViewHolder {
        ProgressBar progressBar;
        TextView textView;
        public FootViewHolder(View view) {
            super(view);
            progressBar=(ProgressBar)view.findViewById(R.id.progressBar);
            textView=(TextView)view.findViewById(R.id.tv_recyclerview_foot);

        }
    }
}
