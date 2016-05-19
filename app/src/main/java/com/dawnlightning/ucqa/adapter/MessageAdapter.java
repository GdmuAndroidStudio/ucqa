package com.dawnlightning.ucqa.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dawnlightning.ucqa.R;

/**
 * Created by Administrator on 2016/5/18.
 */
public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private View.OnClickListener onClickListener;

    public MessageAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_message, null);
        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,RecyclerView.LayoutParams.WRAP_CONTENT));
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (onClickListener != null) {
            holder.itemView.setOnClickListener(this.onClickListener);
        }
    }


    public int getItemCount() {
        return 10;
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
}
