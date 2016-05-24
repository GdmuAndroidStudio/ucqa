package com.dawnlightning.ucqa.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dawnlightning.ucqa.R;
import com.dawnlightning.ucqa.activity.ConsultDetailActivity;
import com.dawnlightning.ucqa.bean.response.consult.detailed.CommentBean;

import java.util.List;

/**
 * Created by Kyo on 2016/5/23.
 */
public class CommentListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_ITEM = 0;
    private Context context;
    private List<CommentBean> data;

    public CommentListAdapter(Context context, List<CommentBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
            return TYPE_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_ITEM){
            View view = LayoutInflater.from(context).inflate(R.layout.item_consult_comment, parent,
                    false);
            return new ItemViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {

            //holder.tv.setText(data.get(position));
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
            ((ConsultDetailActivity) context).clickToReply();
        }
    }
}
