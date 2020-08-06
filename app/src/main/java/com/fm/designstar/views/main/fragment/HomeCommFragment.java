package com.fm.designstar.views.main.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.fm.designstar.R;
import com.fm.designstar.base.BaseFragment;
import com.fm.designstar.model.bean.HomeFindBean;
import com.fm.designstar.utils.SpaceItemDecoration;
import com.fm.designstar.utils.Tool;
import com.fm.designstar.views.main.adapter.HomeRecomAdapter;
import com.fm.designstar.views.main.adapter.ImageAdapter;
import com.fm.designstar.views.main.adapter.MainLikeAdapter;
import com.fm.designstar.views.main.contract.HomeRecomContract;
import com.fm.designstar.views.main.presenter.HomeRecomPresenter;
import com.fm.designstar.widget.MyScrollView;
import com.fm.designstar.widget.imageview.ImageCycleViewHomeBanner;
import com.fm.designstar.widget.recycler.BaseRecyclerAdapter;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.indicator.CircleIndicator;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;


public class HomeCommFragment extends BaseFragment<HomeRecomPresenter> implements HomeRecomContract.View  {

    @BindView(R.id.scrollView)
    MyScrollView scrollView;

    @BindView(R.id.banner)
    ImageCycleViewHomeBanner banner;
    @BindView(R.id.banner2)
    Banner banner2;
    @BindView(R.id.recy_gusslike)
    RecyclerView likeRecycler;

    @BindView(R.id.recy_home)
    RecyclerView hotRecycler;
    private int pagenum=0;
    private MainLikeAdapter likeAdapter;
    private HomeRecomAdapter homeRecomAdapter;
    private List<String> urls=new ArrayList<>();
    private List<HomeFindBean> result=new ArrayList<>();
    HomeFindBean homeFindBean=new HomeFindBean();

    @Override
    public int getLayoutId() {
        return R.layout.fragment_com;
    }

    @Override
    public void initPresenter() {
        mPresenter.init(this);

    }

    @Override
    public void loadData() {
        homeFindBean.setAddress("qsd");
        result.add(homeFindBean);
        result.add(homeFindBean);
        result.add(homeFindBean);
        result.add(homeFindBean);
        urls.add("https://ss1.baidu.com/6ON1bjeh1BF3odCf/it/u=3848182578,3212131776&fm=15&gp=0.jpg");
        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1595928179792&di=64dfa2fcbaed252126d9182ae67e053c&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20171107%2F62a8b9b47e6f41708416c4eb5f44fc6a.jpeg");
        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1595928179787&di=578959ca7cecfc13376805894ff6e52d&imgtype=0&src=http%3A%2F%2Finews.gtimg.com%2Fnewsapp_match%2F0%2F5377154223%2F0");
        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1595928179787&di=578959ca7cecfc13376805894ff6e52d&imgtype=0&src=http%3A%2F%2Finews.gtimg.com%2Fnewsapp_match%2F0%2F5377154223%2F0");
        mPresenter.HomeRecom(pagenum,10);
        ImageAdapter imageAdapter =new ImageAdapter(urls);

        banner2.setAdapter(imageAdapter)
                .addBannerLifecycleObserver(this)//添加生命周期观察者
                .setIndicator(new CircleIndicator(mContext))//设置指示器
                .setOnBannerListener((data, position) -> {

                });
        banner2.setDatas(urls);
        likeRecycler.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        likeRecycler.addItemDecoration(new SpaceItemDecoration().setRight(Tool.dip2px(mContext, 7)));
        likeAdapter = new MainLikeAdapter();
        likeRecycler.setAdapter(likeAdapter);
        likeAdapter.addData(urls);
        likeAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClick() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });

        hotRecycler.setLayoutManager(new LinearLayoutManager(mContext));
        hotRecycler.addItemDecoration(new SpaceItemDecoration().setBottom(Tool.dip2px(mContext, 0)));
        hotRecycler.setNestedScrollingEnabled(false);
        homeRecomAdapter=new HomeRecomAdapter();
        hotRecycler.setAdapter(homeRecomAdapter);
        hotRecycler.setHasFixedSize(true);
        hotRecycler.setFocusable(false);
        homeRecomAdapter.addData(result);
    }

    @Override
    public void HomeRecomSuccess() {

    }

    @Override
    public void showLoading(String content, int code) {

    }

    @Override
    public void stopLoading(int code) {

    }

    @Override
    public void showErrorMsg(String msg, int code) {

    }
}