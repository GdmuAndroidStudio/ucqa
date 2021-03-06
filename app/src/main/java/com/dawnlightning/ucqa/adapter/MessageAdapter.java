package com.dawnlightning.ucqa.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dawnlightning.ucqa.R;
import com.dawnlightning.ucqa.base.BaseFragment;
import com.dawnlightning.ucqa.bean.others.ConsultMessageBean;
import com.dawnlightning.ucqa.bean.others.MessageBean;
import com.dawnlightning.ucqa.utils.ImageLoaderOptions;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

/**
 * Created by Administrator on 2016/5/18.
 */
public class MessageAdapter extends BaseAdapter {
    public  static final int TYPE_ITEM = 0;
    public  static final  int TYPE_FOOTER = 1;
    private Context context;
    private FootViewHolder footViewHolder;
    private Handler handler=new Handler();
    private ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options;

    public MessageAdapter(Context context) {
        this.context = context;
        options = ImageLoaderOptions.getConsultLoadPictureOptions();
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
        super.onBindViewHolder(holder,position);
        if (holder instanceof ItemViewHolder) {
            MessageBean consultMessageBean=(MessageBean)data.get(position);
            ((ItemViewHolder) holder).content.setText(consultMessageBean.getMessage());
            ((ItemViewHolder) holder).host.setText(consultMessageBean.getAuthor());
            imageLoader.displayImage(consultMessageBean.getAvatar_url(), ((ItemViewHolder) holder).photoView, options);
            if (((ItemViewHolder) holder).photoView.getTag()!=null&&((ItemViewHolder)((ItemViewHolder) holder)).photoView.getTag().equals(position)){
            }else{
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
        TextView textView;
        ImageView photoView;
        TextView host;

        public ItemViewHolder(View view) {
            super(view);
            photoView = (ImageView) view.findViewById(R.id.rv_message_icon);
            content=(TextView)view.findViewById(R.id.tv_message_content);
            textView=(TextView)view.findViewById(R.id.tv_messsage_note);
            host=(TextView)view.findViewById(R.id.tv_message_host);
        }
    }

    class FootViewHolder extends RecyclerView.ViewHolder {
        ProgressBar progressBar;
        TextView textView;
        LinearLayout linearLayout;
        public FootViewHolder(View view) {
            super(view);
            progressBar=(ProgressBar)view.findViewById(R.id.progressBar);
            textView=(TextView)view.findViewById(R.id.tv_recyclerview_foot);
            linearLayout= (LinearLayout) view.findViewById(R.id.item_foot);

        }
    }
}
