package com.dawnlightning.ucqa.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dawnlightning.ucqa.bean.others.ConsultClassifyBean;
import com.dawnlightning.ucqa.R;
import com.dawnlightning.ucqa.bean.others.ConsultClassifyBean;
import com.dawnlightning.ucqa.utils.BaseTools;

import java.io.Serializable;
import java.util.List;
import android.view.ViewGroup.LayoutParams;

/**
 * Created by Administrator on 2016/4/2.
 */
public class ClassifyAdapter extends BaseAdapter implements Serializable {
    private Context context;
    private List<ConsultClassifyBean> list;
    private ViewHolder viewHolder;
    private  LayoutInflater layoutInflater;
    public ClassifyAdapter( Context context,List<ConsultClassifyBean> list){
        this.context=context;
        this.list=list;
        layoutInflater = (LayoutInflater) LayoutInflater.from(context);
    }
    public void setlist(List<ConsultClassifyBean> list){
        this.list=list;
    }
    @Override
    public int getCount() {
        return list.size();
    }


    @Override
    public Object getItem(int position) {


        return list.get(position);
    }

    public List<ConsultClassifyBean> getList(){
        return this.list;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (null == convertView) {
            convertView = layoutInflater.inflate(R.layout.item_classify, null);
            viewHolder=new ViewHolder();
            viewHolder.img=(ImageView)convertView.findViewById(R.id.iv_classify);
            viewHolder.title=(TextView)convertView.findViewById(R.id.tv_classify);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ConsultClassifyBean bean=list.get(position);
        switch(bean.getBwztclassarrid()){
            case 1:
                viewHolder.img.setBackgroundResource(R.drawable.classify_item5_selector);
                viewHolder.title.setText("青少年近视");
                break;
            case 2:
                viewHolder.img.setBackgroundResource(R.drawable.classify_item6_selector);
                viewHolder.title.setText("防盲治盲");
                break;
            case 3:
                viewHolder.img.setBackgroundResource(R.drawable.classify_item2_selector);
                viewHolder.title.setText("飞秒激光治疗近视");
                break;
            case 4:
                viewHolder.img.setBackgroundResource(R.drawable.classify_item4_selector);
                viewHolder.title.setText("青光眼");
                break;
            case 5:
                viewHolder.img.setBackgroundResource(R.drawable.classify_item3_selector);
                viewHolder.title.setText("白内障");
                break;
           default:
               viewHolder.img.setBackgroundResource(R.drawable.classify_item1_selector);
               viewHolder.title.setText("全部");
                break;


        }
        return convertView;

    }
    public class ViewHolder{
        public ImageView img;
        public TextView title;
    }
}
