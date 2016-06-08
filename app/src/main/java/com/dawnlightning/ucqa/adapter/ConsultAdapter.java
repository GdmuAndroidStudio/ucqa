package com.dawnlightning.ucqa.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dawnlightning.ucqa.R;
import com.dawnlightning.ucqa.activity.ConsultActivity;
import com.dawnlightning.ucqa.bean.others.ConsultBean;
import com.dawnlightning.ucqa.bean.others.ConsultMessageBean;
import com.dawnlightning.ucqa.utils.ImageLoaderOptions;
import com.dawnlightning.ucqa.utils.TimeUtil;
import com.dawnlightning.ucqa.widget.RoundImageView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.Bind;

/**
 * Created by Administrator on 2016/5/20.
 */
public class ConsultAdapter extends BaseAdapter {
    public static final int TYPE_ITEM = 0;
    public static final int TYPE_FOOTER = 1;
    private static final int TYPE_HEAD = 2;
    private Context context;
    private FootViewHolder footViewHolder;
    private Handler handler = new Handler();
    private ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options;

    public ConsultAdapter(Context context) {
        this.context = context;
        options = ImageLoaderOptions.getConsultLoadPictureOptions();
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
    public void setOverFoot() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                footViewHolder.progressBar.setVisibility(View.GONE);
                footViewHolder.textView.setText("已加载完毕");
            }
        }, 1000);

    }

    @Override
    public void setBeforeFoot() {
        footViewHolder.progressBar.setVisibility(View.GONE);
        footViewHolder.textView.setText("下拉加载更多");

    }

    @Override
    public void setFooting() {
        footViewHolder.progressBar.setVisibility(View.VISIBLE);
        footViewHolder.textView.setText("正在加载中");

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder,position);
        if (holder instanceof ItemViewHolder) {
            ConsultMessageBean consultBean = (ConsultMessageBean)data.get(position);
            ((ItemViewHolder) holder).content.setText(consultBean.getMessage());
            ((ItemViewHolder) holder).numreply.setText(consultBean.getReplynum());
            ((ItemViewHolder) holder).numview.setText(consultBean.getViewnum());
            imageLoader.displayImage(consultBean.getAvatar_url(), ((ItemViewHolder) holder).photoView, options);
            ((ItemViewHolder) holder).subject.setText(consultBean.getSubject());
            ((ItemViewHolder) holder).time.setText(TimeUtil.TimeStamp2Date(consultBean.getDateline()));
            if (((ItemViewHolder) holder).photoView.getTag() != null && ((ItemViewHolder) ((ItemViewHolder) holder)).photoView.getTag().equals(position)) {
            } else {
                ((ItemViewHolder) holder).photoView.setTag(position);
            }
        }
        if(data.size()==0&&position==data.size()){
            ((FootViewHolder)holder).linearLayout.setVisibility(View.INVISIBLE);
        }else{
            if(data.size()>0&&position==data.size()) {
                ((FootViewHolder) holder).linearLayout.setVisibility(View.VISIBLE);
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
        TextView subject;

        public ItemViewHolder(View view) {
            super(view);
            photoView = (ImageView) view.findViewById(R.id.iv_consultpic);
            content = (TextView) view.findViewById(R.id.message);
            textView = (TextView) view.findViewById(R.id.tv_messsage_note);
            consultStatus = (ImageView) view.findViewById(R.id.consult_status);
            numview = (TextView) view.findViewById(R.id.numview);
            numreply = (TextView) view.findViewById(R.id.numreply);
            time = (TextView) view.findViewById(R.id.time);
            subject = (TextView) view.findViewById(R.id.subject);
        }
    }

    class FootViewHolder extends RecyclerView.ViewHolder {
        ProgressBar progressBar;
        TextView textView;
        LinearLayout linearLayout;

        public FootViewHolder(View view) {
            super(view);
            progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
            textView = (TextView) view.findViewById(R.id.tv_recyclerview_foot);
            linearLayout= (LinearLayout) view.findViewById(R.id.item_foot);

        }
    }
}
