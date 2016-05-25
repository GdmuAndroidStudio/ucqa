package com.dawnlightning.ucqa.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dawnlightning.ucqa.R;
import com.dawnlightning.ucqa.activity.ConsultDetailActivity;
import com.dawnlightning.ucqa.bean.response.consult.detailed.CommentBean;

import java.util.List;

/**
 * Created by Kyo on 2016/5/23.
 */
public class CommentListAdapter extends BaseAdapter {

    private static final int TYPE_ITEM = 0;
    private Context context;
    private List<CommentBean> data;
    private FootViewHolder footViewHolder;
    private Handler handler = new Handler();

    public CommentListAdapter(Context context, List<CommentBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getItemCount() {
        return data.size() == 0 ? 0 : data.size() + 1;
    }

    @Override
    public void setOverFoot() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                footViewHolder.progressBar.setVisibility(View.GONE);
                footViewHolder.textView.setText("已全部加载完毕");
                footViewHolder.itemView.setClickable(false);
            }
        }, 1000);

    }

    @Override
    public void setFooting() {
        footViewHolder.progressBar.setVisibility(View.GONE);
        footViewHolder.textView.setText("点击查看更多");
        footViewHolder.textView.setTextColor(context.getResources().getColor(R.color.green));
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
        if(viewType == TYPE_ITEM){
            View view = LayoutInflater.from(context).inflate(R.layout.item_consult_comment, parent,
                    false);
            System.out.println("create item");
            return new ItemViewHolder(view);
        }else if(viewType == TYPE_FOOTER){
            View view = LayoutInflater.from(context).inflate(R.layout.item_foot, parent,
                    false);
            footViewHolder = new FootViewHolder(view);
            setFooting();
            System.out.println("create foot");
            return footViewHolder;
        }
        System.out.println("create null");
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ItemViewHolder) {
            holder.itemView.setTag(position);
            System.out.println("tag = " + holder.itemView.getTag());
        }else if(holder instanceof  FootViewHolder){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((ConsultDetailActivity)context).LoadMore();
                }
            });
        }
    }

    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView ivUser;
        TextView tvMessage;
        TextView tvTime;
        TextView tvReply;

        public ItemViewHolder(View view) {
            super(view);
            ivUser = (ImageView) view.findViewById(R.id.iv_comment_icon);
            tvMessage = (TextView) view.findViewById(R.id.tv_comment_message);
            tvTime = (TextView) view.findViewById(R.id.tv_comment_time);
            tvReply = (TextView) view.findViewById(R.id.tv_comment_reply);
            tvReply.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            String name = data.get(Integer.parseInt(this.itemView.getTag().toString())).getName();
            ((ConsultDetailActivity)context).clickToReply(name);
        }
    }

    static class FootViewHolder extends RecyclerView.ViewHolder {
        ProgressBar progressBar;
        TextView textView;

        public FootViewHolder(View view) {
            super(view);
            progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
            textView = (TextView) view.findViewById(R.id.tv_recyclerview_foot);

        }
    }
}
