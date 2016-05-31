package com.dawnlightning.ucqa.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.dawnlightning.ucqa.R;
import com.dawnlightning.ucqa.activity.MainActivity;
import com.dawnlightning.ucqa.base.Actions;
import com.dawnlightning.ucqa.bean.others.BannerBean;
import com.dawnlightning.ucqa.bean.others.ConsultClassifyBean;
import com.dawnlightning.ucqa.bean.others.ConsultMessageBean;
import com.dawnlightning.ucqa.fragment.MainFragment;
import com.dawnlightning.ucqa.utils.Options;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainAdapter extends BaseAdapter {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;
    private static final int TYPE_HEADER = 2;
    private static final int TYPE_BANNER = 3;
    private Context context;
    private FootViewHolder footViewHolder;
    private Handler handler = new Handler();
    private List<BannerBean> bannerBeanList;
    private MainFragment mainFragment;

    public MainAdapter(MainFragment mainFragment, List data,List<BannerBean> bannerBeanList) {
        this.mainFragment = mainFragment;
        this.context = mainFragment.getContext();
        this.data = data;
        this.bannerBeanList = bannerBeanList;
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
        footViewHolder.progressBar.setVisibility(View.GONE);
        footViewHolder.textView.setText("下拉加载更多");

    }

    @Override
    public void setFooting() {
        footViewHolder.progressBar.setVisibility(View.VISIBLE);
        footViewHolder.textView.setText("正在加载中");

    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else if (position == 1) {
            return TYPE_HEADER;
        } else if (position == 0) {
            return TYPE_BANNER;
        } else {
            return TYPE_ITEM;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        if (viewType == TYPE_ITEM) {
            final View view = LayoutInflater.from(context).inflate(R.layout.item_consult, parent,
                    false);
            return new ItemViewHolder(view);
        } else if (viewType == TYPE_FOOTER) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_foot, parent,
                    false);
            footViewHolder = new FootViewHolder(view);
            return footViewHolder;
        } else if (viewType == TYPE_HEADER) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_header_girdview, parent,
                    false);
            return new HeadViewHolder(context, view);
        } else if (viewType == TYPE_BANNER) {
            final View view = LayoutInflater.from(context).inflate(R.layout.item_banner, parent,
                    false);
            return new BannerViewHolder(context, view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ItemViewHolder) {
            if (onItemClickListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = holder.getLayoutPosition();
                        onItemClickListener.onItemClick(holder.itemView, position);
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

    static class FootViewHolder extends RecyclerView.ViewHolder {
        ProgressBar progressBar;
        TextView textView;

        public FootViewHolder(View view) {
            super(view);
            progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
            textView = (TextView) view.findViewById(R.id.tv_recyclerview_foot);

        }
    }

    class HeadViewHolder extends ViewHolder {

        private GridView gridView;
        private List<ConsultClassifyBean> classifylist = new ArrayList<>(6);
        private ClassifyAdapter classifyAdapter;

        public HeadViewHolder(final Context context, View view) {
            super(view);
            gridView = (GridView) itemView.findViewById(R.id.gv_classify);
            for (int i = 0; i < 6; i++) {
                ConsultClassifyBean consultClassifyBean = new ConsultClassifyBean();
                consultClassifyBean.setBwztclassarrid("" + i);
                switch (i) {
                    case 0:
                        consultClassifyBean.setBwztclassarrname("全部");
                        break;
                    case 1:
                        consultClassifyBean.setBwztclassarrname("激光治近视");
                        break;
                    case 2:
                        consultClassifyBean.setBwztclassarrname("白内障");
                        break;
                    case 3:
                        consultClassifyBean.setBwztclassarrname("青光眼");
                        break;
                    case 4:
                        consultClassifyBean.setBwztclassarrname("青少年近视");
                        break;
                    case 5:
                        consultClassifyBean.setBwztclassarrname("防盲治盲");
                        break;
                    default:
                        break;

                }
                classifylist.add(consultClassifyBean);
            }
            classifyAdapter = new ClassifyAdapter(context, classifylist);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    ConsultClassifyBean bean = (ConsultClassifyBean) classifyAdapter.getItem(position);
                    ((MainActivity)mainFragment.getActivity()).showtitleclassift(bean.getBwztclassarrname());
                    mainFragment.setClassifyName(bean.getBwztclassarrname());

                }
            });
            gridView.setAdapter(classifyAdapter);
        }
    }

    class BannerViewHolder extends ViewHolder {

        private ConvenientBanner convenientBanner;
        private ImageLoader imageLoader = ImageLoader.getInstance();
        private DisplayImageOptions options = Options.getListOptions();
        private ArrayList<Integer> localImages = new ArrayList<Integer>();
        private List<String> networkImages = new ArrayList<>();

        public BannerViewHolder(final Context context, View view) {
            super(view);
            convenientBanner = (ConvenientBanner) view.findViewById(R.id.banner);
//            loadLocalImages();
//            convenientBanner.setPages(
//                    new CBViewHolderCreator<LocalImageHolderView>() {
//                        @Override
//                        public LocalImageHolderView createHolder() {
//                            return new LocalImageHolderView();
//                        }
//                    }, localImages)
//                    //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
//                    .setPageIndicator(new int[]{R.mipmap.indicator, R.mipmap.indicator_focused})
//                    //设置指示器的方向
//                    .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
//            //开始自动翻页
//            convenientBanner.startTurning(3000);

//            网络加载例子
            for(int i = 0; i< MainAdapter.this.bannerBeanList.size(); i++){
                String img = MainAdapter.this.bannerBeanList.get(i).getImg();
                networkImages.add(img);
            }
            convenientBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
                @Override
                public NetworkImageHolderView createHolder() {
                    return new NetworkImageHolderView();
                }
            }, networkImages)
                    //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                    .setPageIndicator(new int[]{R.mipmap.indicator, R.mipmap.indicator_focused})
                    //设置指示器的方向
                    .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                    .setOnItemClickListener(new com.bigkoo.convenientbanner.listener.OnItemClickListener() {
                        @Override
                        public void onItemClick(int position) {
                            Toast.makeText(context, "banner" + position, Toast.LENGTH_SHORT).show();
                        }
                    });
            //开始自动翻页
            convenientBanner.startTurning(3000);
        }

        private void loadLocalImages() {
            //本地图片集合
            for (int position = 0; position < 2; position++)
                localImages.add(getResId("banner" + position, R.mipmap.class));

        }

        /**
         * 通过文件名获取资源id 例子：getResId("icon", R.drawable.class);
         *
         * @param variableName
         * @param c
         * @return
         */
        public int getResId(String variableName, Class<?> c) {
            try {
                Field idField = c.getDeclaredField(variableName);
                return idField.getInt(idField);
            } catch (Exception e) {
                e.printStackTrace();
                return -1;
            }
        }

        /*
        本地图片Holder例子
        */
        public class LocalImageHolderView implements Holder<Integer> {
            private ImageView imageView;

            @Override
            public View createView(Context context) {
                imageView = new ImageView(context);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                return imageView;
            }

            @Override
            public void UpdateUI(Context context, int position, Integer data) {
                imageView.setImageResource(data);
            }
        }

        /**
         * Created by Sai on 15/8/4.
         * 网络图片加载例子
         */
        public class NetworkImageHolderView implements Holder<String> {
            private ImageView imageView;

            @Override
            public View createView(Context context) {
                //你可以通过layout文件来创建，也可以像我一样用代码创建，不一定是Image，任何控件都可以进行翻页
                imageView = new ImageView(context);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                return imageView;
            }

            @Override
            public void UpdateUI(Context context, int position, String data) {
                imageLoader.displayImage(data, imageView, options);
            }
        }
    }
}