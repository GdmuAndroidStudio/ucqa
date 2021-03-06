package com.dawnlightning.ucqa.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dawnlightning.ucqa.R;
import com.dawnlightning.ucqa.activity.DisplayActivity;
import com.dawnlightning.ucqa.bean.response.consult.detailed.PicsBean;
import com.dawnlightning.ucqa.utils.HttpConstants;
import com.dawnlightning.ucqa.utils.Options;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2016/4/8.
 */
public class DetailPicsAdapter extends BaseAdapter {

    private Context context;
    private List<PicsBean> list;
    private ViewHolder viewHolder;
    private  LayoutInflater layoutInflater;
    private ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options;
    public DetailPicsAdapter(Context context, List<PicsBean> list){
        this.context=context;
        this.list=list;
        layoutInflater = (LayoutInflater) LayoutInflater.from(context);
        options = Options.getListOptions();
    }
    public void setlist(List<PicsBean> list){
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

    public void addlist(List<PicsBean> list){
        this.list.addAll(list);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (null == convertView) {
            convertView = layoutInflater.inflate(R.layout.item_detail_picslist, null);
            viewHolder=new ViewHolder();
            viewHolder.img=(ImageView)convertView.findViewById(R.id.iv_item_pic);
            viewHolder.title=(TextView)convertView.findViewById(R.id.tv_item_title);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final  PicsBean bean=list.get(position);
        if (bean.getUrl()!=null) {

           imageLoader.displayImage(HttpConstants.HTTP_HEAD+HttpConstants.HTTP_IP+bean.getUrl(), viewHolder.img, options);
            viewHolder.img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(context, DisplayActivity.class);
                    intent.putExtra("image", HttpConstants.HTTP_HEAD + HttpConstants.HTTP_IP + bean.getUrl());
                    context.startActivity(intent);

                }
            });

            viewHolder.title.setText(decodeUnicode(bean.getTitle()));
        }

        return convertView;

    }
    public class ViewHolder{
        public ImageView img;
        public TextView title;
    }
    /**
     *
     * unicode 转换成 中文
     * @param theString
     * @return
     */

    public static String decodeUnicode(String theString) {

        StringBuffer s1=new StringBuffer(theString);
        for(int index=0;index<theString.length();index += 6) {
            s1.insert(index, '\\');
        }
        theString=s1.toString();
        char aChar;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);

        for (int x = 0; x < len;) {

            aChar = theString.charAt(x++);

            if (aChar == '\\') {

                aChar = theString.charAt(x++);

                if (aChar == 'u') {

                    // Read the xxxx

                    int value = 0;

                    for (int i = 0; i < 4; i++) {

                        aChar = theString.charAt(x++);

                        switch (aChar) {

                            case '0':

                            case '1':

                            case '2':

                            case '3':

                            case '4':

                            case '5':

                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException(
                                        "Malformed   \\uxxxx   encoding.");
                        }

                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';

                    else if (aChar == 'n')

                        aChar = '\n';

                    else if (aChar == 'f')

                        aChar = '\f';

                    outBuffer.append(aChar);

                }

            } else

                outBuffer.append(aChar);

        }

        return outBuffer.toString();

    }
}
