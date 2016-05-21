package com.dawnlightning.ucqa.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dawnlightning.ucqa.R;
import com.dawnlightning.ucqa.activity.MainActivity;
import com.dawnlightning.ucqa.bean.others.ConsultClassifyBean;
import com.dawnlightning.ucqa.bean.others.ConsultMessageBean;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends Adapter<ViewHolder> {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;
    private static final int TYPE_HEADER = 2;
    private Context context;
    private List data;


    public RecyclerViewAdapter(Context context,List data) {
        this.context = context;
        this.data = data;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return data.size() == 0 ? 0 : data.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else if(position == 0){
            return TYPE_HEADER;
        }else{
            return TYPE_ITEM;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_consult, parent,
                    false);
            return new ItemViewHolder(view);
        } else if (viewType == TYPE_FOOTER) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_foot, parent,
                    false);
            return new FootViewHolder(view);
        } else if (viewType == TYPE_HEADER) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_header_girdview, parent,
                    false);
            return new HeadViewHolder(context, view);
        }
        return null;
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            //holder.tv.setText(data.get(position));
            if (onItemClickListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = holder.getLayoutPosition();
                        onItemClickListener.onItemClick(holder.itemView, position);
                    }
                });

                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        int position = holder.getLayoutPosition();
                        onItemClickListener.onItemLongClick(holder.itemView, position);
                        return false;
                    }
                });
            }
        }
    }


    static class ItemViewHolder extends ViewHolder {

        private ImageView ivPic;
        private TextView tvTitle;
        private TextView tvContent;
        private TextView tvView;
        private TextView tvReplay;
        private TextView tvTime;

        public ItemViewHolder(View view) {
            super(view);
            ivPic = (ImageView) view.findViewById(R.id.iv_consultpic);
            tvTitle = (TextView) view.findViewById(R.id.subject);
            tvContent = (TextView) view.findViewById(R.id.message);
            tvView = (TextView) view.findViewById(R.id.numview);
            tvReplay = (TextView) view.findViewById(R.id.numreply);
            tvTime = (TextView) view.findViewById(R.id.time);
        }
    }

    static     class FootViewHolder extends RecyclerView.ViewHolder {
        ProgressBar progressBar;
        TextView textView;

        public FootViewHolder(View view) {
            super(view);
            progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
            textView = (TextView) view.findViewById(R.id.tv_recyclerview_foot);

        }
    }

    static class HeadViewHolder extends ViewHolder {

        private GridView gridView;
        private List<ConsultClassifyBean> classifylist = new ArrayList<>(6);
        private ClassifyAdapter classifyAdapter;
        private MainActivity mainActivity;

        public HeadViewHolder(Context context, View view) {
            super(view);
            gridView = (GridView) itemView.findViewById(R.id.gv_classify);
            for (int i = 0; i < 6; i++) {
                ConsultClassifyBean consultClassifyBean = new ConsultClassifyBean();
                consultClassifyBean.setBwztclassarrid("" + (i + 1));
                consultClassifyBean.setBwztclassarrname("" + (i + 1));
                classifylist.add(consultClassifyBean);
            }
            classifyAdapter = new ClassifyAdapter(context, classifylist);
            mainActivity = (MainActivity) context;
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    ConsultClassifyBean bean = (ConsultClassifyBean) classifyAdapter.getItem(position);
                    mainActivity.showtitleclassift(bean.getBwztclassarrname());
                }
            });
            gridView.setAdapter(classifyAdapter);
        }
    }
}