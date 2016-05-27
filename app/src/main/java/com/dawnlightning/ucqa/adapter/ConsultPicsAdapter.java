
package com.dawnlightning.ucqa.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dawnlightning.ucqa.R;
import com.dawnlightning.ucqa.activity.DisplayActivity;
import com.dawnlightning.ucqa.bean.others.UploadPicsBean;
import com.dawnlightning.ucqa.utils.ImageLoaderOptions;
import com.dawnlightning.ucqa.widget.PictureTitleEdittext;
import com.dawnlightning.ucqa.widget.ProcessImageView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/4/14.
 */
public class ConsultPicsAdapter extends com.dawnlightning.ucqa.adapter.BaseAdapter{
    private Context context;
    private List<ProcessImageView> processImageViewList=new ArrayList<ProcessImageView>();
    private List<UploadPicsBean> list;
    private LayoutInflater layoutInflater;
    private DisplayImageOptions options;
    private  DeletePicture  deletePicture;
    private EditTextListener editTextListener;
    public ConsultPicsAdapter(Context context, List<UploadPicsBean> list){
        this.context=context;
        this.list=list;
        layoutInflater = (LayoutInflater) LayoutInflater.from(context);
        options = ImageLoaderOptions.getConsultLoadPictureOptions();
    }

    public void  setDeletePicture(DeletePicture deletePicture){
        this.deletePicture=deletePicture;
    }
    public void setEditTextListener( EditTextListener editTextListener){
        this.editTextListener=editTextListener;
    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    public UploadPicsBean getitem(int position){
        return list.get(position);
    }

    public void add(UploadPicsBean uploadPicsBean){
        list.add(uploadPicsBean);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_consult_pics, null);
        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
            UploadPicsBean bean = list.get(position);
            final   RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) ((ViewHolder) holder).img.getLayoutParams();
            ((ViewHolder) holder).delete.setVisibility(View.INVISIBLE);
            ((ViewHolder) holder).delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (deletePicture != null) {
                        ((ViewHolder) holder).delete.setVisibility(View.INVISIBLE);
                        deletePicture.Detele(position);
                    }
                }
            });
            ((ViewHolder) holder).title.clearTextChangedListeners();
              ((ViewHolder) holder).title.addTextChangedListener(new TextWatcher() {
                  @Override
                  public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                  }

                  @Override
                  public void onTextChanged(CharSequence s, int start, int before, int count) {

                  }

                  @Override
                  public void afterTextChanged(Editable s) {

                      if (s != null && !"".equals(s.toString())) {
                          if (editTextListener != null) {
                              editTextListener.AdapterTextChaged(position, s.toString());

                          }
                      }
                  }
              });
        ((ViewHolder) holder).title.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){//获得焦点
                    ((ViewHolder) holder).title.setTop(((ViewHolder) holder).title.getBottom()-params.height/2);
                }else{//失去焦点
                    ((ViewHolder) holder).title.setTop(((ViewHolder) holder).title.getBottom()-params.height);
                }
            }
        });
            ((ViewHolder) holder).img.setOnClickListener(new OnClickListener(((ViewHolder) holder), position));
            ((ViewHolder) holder).img.setOnLongClickListener(new LongClickListener(((ViewHolder) holder)));
            Log.d("test", "" + position);
            if (bean != null) {
//               imageLoader.displayImage("file://" + bean.getPicture().getPath(), viewHolder.img);
                Bitmap bitmap = getLoacalBitmap(bean.getPicture().getPath());
                ((ViewHolder) holder).img.setImageBitmap(upImageSize(bitmap, params.height));
                ((ViewHolder) holder).img.setProgress(bean.getPresent());
                ((ViewHolder) holder).title.setText(bean.getPicturetitle());
                Log.d("test", bean.getPicturetitle());
                ((ViewHolder) holder).delete.setBackgroundResource(R.mipmap.ic_delete);
            }
            ((ViewHolder) holder).title.clearFocus();
    }

    private Bitmap upImageSize(Bitmap bmp,int height) {
        if(bmp==null){
            return null;
        }
        // 计算比例
        float scaleY = (float)height / bmp.getHeight();// 高的比例
        //新的宽高
        int newW = 0;
        int newH = 0;
//        if(scaleX > scaleY){
//            newW = (int) (bmp.getWidth() * scaleX);
//            newH = (int) (bmp.getHeight() * scaleX);
//        }else if(scaleX <= scaleY){
            newW = (int) (bmp.getWidth() * scaleY);
            newH = (int) (bmp.getHeight() * scaleY);
//        }
        Log.d("test",height+"  "+bmp.getHeight()+"   "+newW+"  "+newH);
        return Bitmap.createScaledBitmap(bmp, newW, newH, true);
    }

    public Bitmap getLoacalBitmap(String url) {
        try {
            FileInputStream fis = new FileInputStream(url);
            return BitmapFactory.decodeStream(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private class OnClickListener implements View.OnClickListener{
        private ViewHolder viewHolder;
        private int position;
        public OnClickListener(ViewHolder viewHolder,int position){
            this.viewHolder=viewHolder;
            this.position=position;
        }
        @Override
        public void onClick(View view) {
            if(viewHolder.delete.getVisibility()==View.VISIBLE) {
                viewHolder.delete.setVisibility(View.INVISIBLE);
            }else {
                Intent intent = new Intent();
                intent.setClass(context, DisplayActivity.class);
                intent.putExtra("image",list.get(position).getPicture().getPath());
                context.startActivity(intent);
            }

        }
    }


    private class LongClickListener implements View.OnLongClickListener{
        private ViewHolder viewHolder;

        public LongClickListener(ViewHolder viewHolder){
            this.viewHolder = viewHolder;
        }
        @Override
        public boolean onLongClick(View view) {
            viewHolder.delete.setVisibility(View.VISIBLE);
            return true;
        }
    }
    //获取待上传或者要重新上传的图片
    public List<UploadPicsBean> getUploadPicsBeans(){
        List<UploadPicsBean> uploadPicsBeanList=new ArrayList<UploadPicsBean>();
        for (UploadPicsBean bean:list){
            if (bean.getPresent()<100){
                uploadPicsBeanList.add(bean);
            }
        }
        return  uploadPicsBeanList;
    }

    public void remove(int postion){
        this.list.remove(postion);
    }
    //
    public class ViewHolder extends RecyclerView.ViewHolder {
        ProcessImageView img;
        PictureTitleEdittext title;
        ImageView delete;
        public ViewHolder(View view){
            super(view);
            img =(ProcessImageView)view.findViewById(R.id.piv_consult_picture) ;
            title = (PictureTitleEdittext)view.findViewById(R.id.et_consult_picture_title);
            delete=(ImageView)view.findViewById(R.id.iv_consult_picture_delete) ;
        }
    }
    public interface DeletePicture{
        public void Detele(int postion);
    }
    public interface EditTextListener{
        public void AdapterTextChaged(int postion, String str);
    }


}