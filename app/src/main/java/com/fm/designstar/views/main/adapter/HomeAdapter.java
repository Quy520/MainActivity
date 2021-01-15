package com.fm.designstar.views.main.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.fm.designstar.R;
import com.fm.designstar.app.App;
import com.fm.designstar.model.bean.BannerBean;
import com.fm.designstar.model.bean.HomeFindBean;
import com.fm.designstar.model.bean.NewsListBean;
import com.fm.designstar.utils.SpaceItemDecoration;
import com.fm.designstar.utils.ToastUtil;
import com.fm.designstar.utils.Tool;
import com.fm.designstar.views.Detail.activity.VedioPlayActivity;
import com.fm.designstar.views.main.activity.WebActivity;
import com.fm.designstar.widget.recycler.BaseRecyclerAdapter;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jzvd.JZVideoPlayerManager;
import cn.jzvd.JZVideoPlayerStandard;
import cn.jzvdother.Jzvd;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/8/14 16:38
 * @update : 2018/8/14
 */
public class HomeAdapter extends BaseRecyclerAdapter<RecyclerView.ViewHolder, NewsListBean> {
    private LifecycleOwner mowner ;
    private MainLikeAdapter likeAdapter;
    private HomeRecomAdapter homeRecomAdapter;

    private List<String> urls=new ArrayList<>();
    List<BannerBean> bnnerlist=new ArrayList<>();
    private OnClickListener listener;
    public interface OnClickListener {
        void onLikeClick(int position,boolean b,CompoundButton compoundButton);
        void onguanClick(int position,boolean b,CompoundButton compoundButton);

    }
    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }
    public HomeAdapter(LifecycleOwner mowner) {
        this.mowner=mowner;

    }


    @Override
    public RecyclerView.ViewHolder mOnCreateViewHolder(ViewGroup parent, int viewType) {
        View view ;
        RecyclerView.ViewHolder likeViewHolder;
         switch (viewType) {
            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recome1, parent, false);
                likeViewHolder=new LikeViewHolder(view);
                break;
            case 2:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recome2, parent, false);
                likeViewHolder=new LikeViewHoldertwo(view);
                break;
            case 3:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recome3, parent, false);
                likeViewHolder=new LikeViewHolderthree(view);
                break;

            default:
                Log.e("qsd","getItemViewType"+viewType);

                throw new IllegalStateException("Unexpected value: " + viewType);
        }
        return likeViewHolder;
    }

    @Override
    public void mOnBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (getItemViewType(position)) {
            case 1: //1张图 情况urls
                urls=new ArrayList<>();
               bnnerlist = data.get(0).getBanner();

                if (bnnerlist!=null){
                    if (bnnerlist.size()>0){
                    if (bnnerlist.get(0).getBanner()!=null){
                        if (bnnerlist.size()>0){
                            for (int i=0;i<bnnerlist.size();i++){
                                urls.add(bnnerlist.get(i).getBanner());
                            }
                    }
                    }else {
                        urls.add("https://cdefile.oss-cn-hangzhou.aliyuncs.com/1-1-5f844c05b076ac5a2ff3d0b8-1602557549-687149.jpg");
                        urls.add("https://cdefile.oss-cn-hangzhou.aliyuncs.com/1-1-5f844c05b076ac5a2ff3d0b8-1602557549-870361.jpg");
                        urls.add("https://cdefile.oss-cn-hangzhou.aliyuncs.com/1-1-5f844c05b076ac5a2ff3d0b8-1602557549-372720.jpg");
                        urls.add("https://cdefile.oss-cn-hangzhou.aliyuncs.com/1-1-5f844c05b076ac5a2ff3d0b8-1602557550-949497.jpg");

                    }
                }

                }else {
                    urls.add("https://cdefile.oss-cn-hangzhou.aliyuncs.com/1-1-5f844c05b076ac5a2ff3d0b8-1602557549-687149.jpg");
                    urls.add("https://cdefile.oss-cn-hangzhou.aliyuncs.com/1-1-5f844c05b076ac5a2ff3d0b8-1602557549-870361.jpg");
                    urls.add("https://cdefile.oss-cn-hangzhou.aliyuncs.com/1-1-5f844c05b076ac5a2ff3d0b8-1602557549-372720.jpg");
                    urls.add("https://cdefile.oss-cn-hangzhou.aliyuncs.com/1-1-5f844c05b076ac5a2ff3d0b8-1602557550-949497.jpg");

                }
                LikeViewHolder viewHolderOne = (LikeViewHolder) holder;
                ImageAdapter imageAdapter =new ImageAdapter(urls);

                viewHolderOne. banner2.setAdapter(imageAdapter)
                        .addBannerLifecycleObserver(mowner)//添加生命周期观察者
                        .setIndicator(new CircleIndicator(mContext))//设置指示器
                        .setOnBannerListener((data, i) -> {
                            if (bnnerlist==null){
                                return;
                            }
                            Intent intent  = new Intent(App.getContext(),WebActivity.class);
                            Bundle bundle3 = new Bundle();
                            bundle3.putString("url", bnnerlist.get(i).getActivityContent());

                            intent.putExtras(bundle3);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                            App.getContext(). startActivity(intent);

                        });
                viewHolderOne. banner2.setDatas(urls);
                break;
            case 2: //3张图 情况
                List<HomeFindBean> result2 = data.get(0).getHot();

                LikeViewHoldertwo viewHoldertwo = (LikeViewHoldertwo) holder;

                viewHoldertwo .likeRecycler.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));

                likeAdapter = new MainLikeAdapter();
                viewHoldertwo .likeRecycler.setAdapter(likeAdapter);
                likeAdapter.addData(result2);
                likeAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClick() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent=  new Intent(mContext, VedioPlayActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("info", likeAdapter.getData().get(position));
                        intent.putExtras(bundle);
                        mContext. startActivity(intent);
                    }
                });
                break;
            case 3: //3张图 情况
                homeRecomAdapter=new HomeRecomAdapter(0);
                homeRecomAdapter.clearData();
                List<HomeFindBean> result3 =new ArrayList<>();
                result3 = data.get(0).getRecom();

                LikeViewHolderthree viewHolderthree = (LikeViewHolderthree) holder;

                viewHolderthree.hotRecycler.setLayoutManager(new LinearLayoutManager(mContext));
                viewHolderthree.hotRecycler.addItemDecoration(new SpaceItemDecoration().setBottom(Tool.dip2px(mContext, 0)));
                viewHolderthree.hotRecycler.setNestedScrollingEnabled(false);

                viewHolderthree. hotRecycler.setAdapter(homeRecomAdapter);

                homeRecomAdapter.addData(result3);
                homeRecomAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClick() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Log.e("qsd",position+"ppp"+new Gson().toJson(homeRecomAdapter.getData().get(position)));
                        Intent intent=  new Intent(mContext, VedioPlayActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("info", homeRecomAdapter.getData().get(position));
                        intent.putExtras(bundle);
                        mContext. startActivity(intent);
                    }
                });

                homeRecomAdapter.setOnClickListener(new HomeRecomAdapter.OnClickListener() {
                    @Override
                    public void onLikeClick(int position, boolean b, CompoundButton compoundButton) {
                        if (listener!=null){
                            listener.onLikeClick(position,b,compoundButton);
                        }
                    }

                    @Override
                    public void onGuanClick(int position, boolean b, CompoundButton compoundButton) {
                        if (listener!=null){
                            listener.onguanClick(position,b,compoundButton);
                        }
                    }
                });
                viewHolderthree.hotRecycler.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
                    @Override
                    public void onChildViewAttachedToWindow(View view) {

                    }

                    @Override
                    public void onChildViewDetachedFromWindow(View view) {
                        Jzvd jzvd = view.findViewById(R.id.video_player);
                        if (jzvd != null && Jzvd.CURRENT_JZVD != null) {
                            if (Jzvd.CURRENT_JZVD != null && Jzvd.CURRENT_JZVD.screen != Jzvd.SCREEN_FULLSCREEN) {
                                Jzvd.releaseAllVideos();
                            }
                        }
                       /* if (jzvd != null) {
                            if (JZVideoPlayerManager.getCurrentJzvd()!= null ) {
                                jzvd.releaseAllVideos();
                            }
                        }*/
                    }
                });
                break;
        }

    }

    @Override
    public int getItemViewType(int position) {

        if (position == 0) {
            return 1;
        } else if (position == 1){
            return 2;
        }else {
            return 3;
        }

    }
      class LikeViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.banner2)
        Banner banner2;


        public LikeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
     class LikeViewHoldertwo extends RecyclerView.ViewHolder {

        @BindView(R.id.recy_gusslike)
        RecyclerView likeRecycler;




        public LikeViewHoldertwo(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
      class LikeViewHolderthree extends RecyclerView.ViewHolder {


        @BindView(R.id.recy_home)
        RecyclerView hotRecycler;


        public LikeViewHolderthree(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
