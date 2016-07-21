package com.dawnlightning.ucqa.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.dawnlightning.ucqa.utils.Options;
import com.dawnlightning.ucqa.utils.TimeUtil;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

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
    private ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options = Options.getListOptions();

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
    public void setBeforeFoot() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                footViewHolder.progressBar.setVisibility(View.GONE);
                footViewHolder.textView.setText("点击查看更多");
                footViewHolder.textView.setTextColor(context.getResources().getColor(R.color.green));
                footViewHolder.itemView.setClickable(true);
            }
        }, 1000);
    }

    @Override
    public void setFooting() {
        footViewHolder.progressBar.setVisibility(View.VISIBLE);
        footViewHolder.textView.setText("正在加载更多评论");
        footViewHolder.itemView.setClickable(false);
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
            View view = LayoutInflater.from(context).inflate(R.layout.item_consult_comment, parent,
                    false);

            return new ItemViewHolder(view);
        } else if (viewType == TYPE_FOOTER) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_foot, parent,
                    false);
            footViewHolder = new FootViewHolder(view);
            setFooting();
            return footViewHolder;
        }

        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ItemViewHolder) {
            holder.itemView.setTag(position);
            imageLoader.displayImage(data.get(position).getAvatar_url().toString(), ((ItemViewHolder) holder).ivUser, options);
            ((ItemViewHolder) holder).tvMessage.setText(data.get(position).getMessage().toString());
            ((ItemViewHolder) holder).tvTime.setText(TimeUtil.TimeStamp2Date(data.get(position).getDateline().toString()));
        } else if (holder instanceof FootViewHolder) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setFooting();
                    ((ConsultDetailActivity) context).LoadMore();
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
            ((ConsultDetailActivity) context).clickToReply(name);
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
