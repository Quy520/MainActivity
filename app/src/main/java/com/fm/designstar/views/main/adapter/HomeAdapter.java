package com.fm.designstar.views.main.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.fm.designstar.R;
import com.fm.designstar.app.App;
import com.fm.designstar.model.bean.HomeFindBean;
import com.fm.designstar.model.bean.NewsListBean;
import com.fm.designstar.utils.SpaceItemDecoration;
import com.fm.designstar.utils.ToastUtil;
import com.fm.designstar.utils.Tool;
import com.fm.designstar.utils.image.RequestOptionsUtil;
import com.fm.designstar.views.Detail.activity.VedioPlayActivity;
import com.fm.designstar.views.mine.activity.InfoDetailActivity;
import com.fm.designstar.widget.CircleImageView;
import com.fm.designstar.widget.recycler.BaseRecyclerAdapter;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

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

    public HomeAdapter(LifecycleOwner mowner) {
        this.mowner=mowner;
        urls.add("https://ss1.baidu.com/6ON1bjeh1BF3odCf/it/u=3848182578,3212131776&fm=15&gp=0.jpg");
        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1595928179792&di=64dfa2fcbaed252126d9182ae67e053c&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20171107%2F62a8b9b47e6f41708416c4eb5f44fc6a.jpeg");
        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1595928179787&di=578959ca7cecfc13376805894ff6e52d&imgtype=0&src=http%3A%2F%2Finews.gtimg.com%2Fnewsapp_match%2F0%2F5377154223%2F0");
        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1595928179787&di=578959ca7cecfc13376805894ff6e52d&imgtype=0&src=http%3A%2F%2Finews.gtimg.com%2Fnewsapp_match%2F0%2F5377154223%2F0");

    }


    @Override
    public RecyclerView.ViewHolder mOnCreateViewHolder(ViewGroup parent, int viewType) {
        View view ;
        RecyclerView.ViewHolder likeViewHolder;
        Log.e("qsd","getItemViewType"+viewType);
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
        Log.e("qsd","mOnBindViewHolder"+getItemViewType(position)+"=="+position);

        switch (getItemViewType(position)) {
            case 1: //1张图 情况

                LikeViewHolder viewHolderOne = (LikeViewHolder) holder;
                ImageAdapter imageAdapter =new ImageAdapter(urls);

                viewHolderOne. banner2.setAdapter(imageAdapter)
                        .addBannerLifecycleObserver(mowner)//添加生命周期观察者
                        .setIndicator(new CircleIndicator(mContext))//设置指示器
                        .setOnBannerListener((data, i) -> {

                        });
                viewHolderOne. banner2.setDatas(urls);
                break;
            case 2: //3张图 情况
                List<HomeFindBean> result2 = data.get(0).getHot();
                Log.e("qsd","2张图 情况"+position);

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
                homeRecomAdapter=new HomeRecomAdapter();
                homeRecomAdapter.clearData();
                List<HomeFindBean> result3 =new ArrayList<>();
                result3 = data.get(0).getRecom();

                LikeViewHolderthree viewHolderthree = (LikeViewHolderthree) holder;

                viewHolderthree.hotRecycler.setLayoutManager(new LinearLayoutManager(mContext));
                viewHolderthree.hotRecycler.addItemDecoration(new SpaceItemDecoration().setBottom(Tool.dip2px(mContext, 0)));
                viewHolderthree.hotRecycler.setNestedScrollingEnabled(false);

                viewHolderthree. hotRecycler.setAdapter(homeRecomAdapter);
                Log.e("qsd","3张图 情况"+position+"udhkjahdhs"+result3.size());

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
