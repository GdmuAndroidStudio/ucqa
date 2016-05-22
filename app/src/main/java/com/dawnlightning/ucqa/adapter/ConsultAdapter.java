package com.dawnlightning.ucqa.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dawnlightning.ucqa.R;
import com.dawnlightning.ucqa.widget.RoundImageView;

import butterknife.Bind;

/**
 * Created by Administrator on 2016/5/20.
 */
public class ConsultAdapter extends BaseAdapter {
    public static final int TYPE_ITEM = 0;
    public static final int TYPE_FOOTER = 1;
    private Context context;
    private FootViewHolder footViewHolder;

    public ConsultAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_consult, null);
            view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
            return new ItemViewHolder(view);
        } else if (viewType == TYPE_FOOTER) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_foot, null);
            view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
            footViewHolder = new FootViewHolder(view);
            return footViewHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder,position);
        if (holder instanceof ItemViewHolder) {
            if (((ItemViewHolder) holder).photoView.getTag() != null && ((ItemViewHolder) ((ItemViewHolder) holder)).photoView.getTag().equals(position)) {
            } else {
                ((ItemViewHolder) holder).photoView.setTag(position);
            }
        }
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView content;
        TextView numview;
        ImageView photoView;
        ImageView consultStatus;
        TextView textView;
        TextView numreply;
        TextView time;

        public ItemViewHolder(View view) {
            super(view);
            photoView = (ImageView) view.findViewById(R.id.iv_consultpic);
            content = (TextView) view.findViewById(R.id.message);
            textView = (TextView) view.findViewById(R.id.tv_messsage_note);
            consultStatus = (ImageView) view.findViewById(R.id.consult_status);
            numview = (TextView) view.findViewById(R.id.numview);
            numreply = (TextView) view.findViewById(R.id.numreply);
            time = (TextView) view.findViewById(R.id.time);
        }
    }

    class FootViewHolder extends RecyclerView.ViewHolder {
        ProgressBar progressBar;
        TextView textView;

        public FootViewHolder(View view) {
            super(view);
            progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
            textView = (TextView) view.findViewById(R.id.tv_recyclerview_foot);

        }
    }
}
