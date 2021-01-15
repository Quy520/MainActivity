package com.fm.designstar.views.main.fragment;

import android.content.Context;
import android.media.AudioManager;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;

import com.alibaba.fastjson.JSON;
import com.fm.designstar.R;
import com.fm.designstar.app.App;
import com.fm.designstar.base.BaseFragment;
import com.fm.designstar.events.HomeEvent;
import com.fm.designstar.model.bean.HomeFindBean;
import com.fm.designstar.model.bean.NewsListBean;
import com.fm.designstar.model.server.response.BannerResponse;
import com.fm.designstar.model.server.response.HomeFindResponse;
import com.fm.designstar.model.server.response.LikeResponse;
import com.fm.designstar.utils.SpUtil;
import com.fm.designstar.utils.SpaceItemDecoration;
import com.fm.designstar.utils.StringUtil;
import com.fm.designstar.utils.ToastUtil;
import com.fm.designstar.utils.Tool;
import com.fm.designstar.views.Detail.contract.LikeContract;
import com.fm.designstar.views.Detail.presenter.LikePresenter;
import com.fm.designstar.views.main.adapter.HomeAdapter;
import com.fm.designstar.views.main.adapter.HomeRecomAdapter;
import com.fm.designstar.views.main.adapter.ImageAdapter;
import com.fm.designstar.views.main.adapter.MainLikeAdapter;
import com.fm.designstar.views.main.adapter.NewsListRecycleAdapter;
import com.fm.designstar.views.main.contract.HomeRecomContract;
import com.fm.designstar.views.main.presenter.HomeRecomPresenter;
import com.fm.designstar.views.mine.contract.followContract;
import com.fm.designstar.views.mine.presenter.followPresenter;
import com.fm.designstar.widget.MyScrollView;
import com.fm.designstar.widget.imageview.ImageCycleViewHomeBanner;
import com.fm.designstar.widget.recycler.BaseRecyclerAdapter;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;


public class HomeTuijianFragment extends BaseFragment<HomeRecomPresenter> implements HomeRecomContract.View ,XRecyclerView.LoadingListener , LikeContract.View, followContract.View{



    @BindView(R.id.home_recy)
    XRecyclerView home_recy;
    private int pagenum=1;

    private HomeAdapter homeRecomAdapter;
    private List<String> urls=new ArrayList<>();
    private List<NewsListBean> NewsList=new ArrayList<>();
    private NewsListBean newsListBean=new NewsListBean();
    private List<HomeFindBean> result=new ArrayList<>();
    HomeFindBean homeFindBean=new HomeFindBean();
    private boolean islding=false,hasnext;
    private int banner=0,hot,recome;
    private LikePresenter likePresenter;
    private followPresenter presenter;

    private int index=0;
    private int like=0;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_tuijian;
    }

    @Override
    public void initPresenter() {
        mPresenter.init(this);
        likePresenter=new LikePresenter();
        likePresenter.init(this);
        presenter=new followPresenter();
        presenter.init(this);

    }

    @Override
    public void loadData() {
        if(!EventBus.getDefault().isRegistered(this)){//加上判断
            EventBus.getDefault().register(this);
        }
        home_recy.setPullRefreshEnabled(true);
        home_recy.setLoadingMoreEnabled(false);
        home_recy.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        home_recy.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotate);

        home_recy.setLayoutManager(new LinearLayoutManager(mContext));
        home_recy.addItemDecoration(new SpaceItemDecoration().setBottom(Tool.dip2px(mContext, 0)));
        homeRecomAdapter=new HomeAdapter(this);
        home_recy.setAdapter(homeRecomAdapter);
        mPresenter.Homebanner(pagenum,10);

        if (StringUtil.isBlank(SpUtil.getString("HomehotecomSuccess"))){
            mPresenter.Homehote(pagenum,10);
        }else {
            hot=1;
            HomeFindResponse muserinfo= JSON.parseObject(SpUtil.getString("HomehotecomSuccess"), HomeFindResponse.class);
            newsListBean.setHot(muserinfo.getResult());
            NewsList.add(newsListBean);
            homeRecomAdapter.addData(NewsList);

        }
        if (StringUtil.isBlank(SpUtil.getString("HomeRecomSuccess"))) {
            mPresenter.HomeRecom(pagenum, 50);
        }else {
            recome=1;
            HomeFindResponse muserinfo= JSON.parseObject(SpUtil.getString("HomeRecomSuccess"), HomeFindResponse.class);
            newsListBean.setRecom(muserinfo.getResult());
            NewsList.add(newsListBean);
            homeRecomAdapter.addData(NewsList);

        }


        //4)实现 下拉刷新和加载更多 接口
        home_recy.setLoadingListener(this);

        homeRecomAdapter.setOnClickListener(new HomeAdapter.OnClickListener() {
            @Override
            public void onLikeClick(int position, boolean b, CompoundButton compoundButton) {

                if (b){
                    if (compoundButton.isPressed()){
                        Log.e("qsd","position"+position);

                        like=1;
                        index=position;
                        likePresenter.Like(1,homeRecomAdapter.getData().get(0).getRecom().get(position).getMomentId());
                    }
                }else {
                    Log.e("qsd","position"+position);

                    like=0;
                    index=position;
                    likePresenter.Like(1,homeRecomAdapter.getData().get(0).getRecom().get(position).getMomentId());

                }
            }

            @Override
            public void onguanClick(int position, boolean b, CompoundButton compoundButton) {
                if (homeRecomAdapter.getData().get(0).getRecom().get(position).isFollow()){
                    presenter.canclefollow(homeRecomAdapter.getData().get(0).getRecom().get(position).getUserId()+"");
                }else {
                    presenter.follow(homeRecomAdapter.getData().get(0).getRecom().get(position).getUserId()+"");

                }
            }
        });
    }


    @Override
    public void showLoading(String content, int code) {
        if (!islding){
            App.loadingDefault(mActivity);
        }

    }

    @Override
    public void stopLoading(int code) {

        home_recy.refreshComplete(); //下拉刷新完成
        home_recy.loadMoreComplete();
        if (!islding){
            App.hideLoading();
        }

    }

    @Override
    public void showErrorMsg(String msg, int code) {

        ToastUtil.showToast(msg);
        home_recy.refreshComplete(); //下拉刷新完成
        home_recy.loadMoreComplete();
        if (!islding){
            App.hideLoading();
        }


    }

    @Override
    public void HomeRecomSuccess(HomeFindResponse homeFindResponse) {
        Log.e("qsd","HomeRecomSuccess==="+new Gson().toJson(homeFindResponse));

        recome=1;
        hasnext=homeFindResponse.isHasNextPage();
        if (pagenum>1){
            for (int i=0;i<homeFindResponse.getResult().size();i++){
                NewsList.get(0).getRecom().add(homeFindResponse.getResult().get(i));
            }
        }else {
            if (homeFindResponse!=null){
                SpUtil.putString("HomeRecomSuccess",new Gson().toJson(homeFindResponse));
            }
            newsListBean.setRecom(homeFindResponse.getResult());
            NewsList.add(newsListBean);

        }


        if (recome==1&&hot==1&&banner==1){
            homeRecomAdapter.addData(NewsList);
            }
        home_recy.refreshComplete(); //下拉刷新完成
        home_recy.loadMoreComplete();

    }

    @Override
    public void HomehotecomSuccess(HomeFindResponse homeFindResponse) {
        hot=1;
        Log.e("qsd","HomehotecomSuccess==="+new Gson().toJson(homeFindResponse));
        if (pagenum==1){
            if (homeFindResponse!=null){
                SpUtil.putString("HomehotecomSuccess",new Gson().toJson(homeFindResponse));
            }
        }
        newsListBean.setHot(homeFindResponse.getResult());

        NewsList.add(newsListBean);

        if (recome==1&&hot==1&&banner==1){
            homeRecomAdapter.addData(NewsList);
        }

    }

    @Override
    public void HomebannerRecomSuccess(BannerResponse homeFindResponse) {
       // Log.e("qsd","==="+new Gson().toJson(homeFindResponse));
        banner=1;


        newsListBean.setBanner(homeFindResponse.getResult());
        NewsList.add(newsListBean);

        if (recome==1&&hot==1&&banner==1){
            homeRecomAdapter.addData(NewsList);
        }



    }

    @Override
    public void onRefresh() {
        islding=true;
        hasnext=true;
        pagenum=1;
        if (StringUtil.isBlank(SpUtil.getString("HomehotecomSuccess"))){
            mPresenter.Homebanner(pagenum,10);
        }
        mPresenter.Homehote(pagenum,10);
        mPresenter.HomeRecom(pagenum,50);
        NewsList=new ArrayList<>();
    }

    @Override
    public void onLoadMore() {
        if (hasnext){
            islding=true;
            pagenum++;
            mPresenter.HomeRecom(pagenum,50);
        }else {
            home_recy.loadMoreComplete();

        }

    }

    @Override
    public void LikeSuccess(LikeResponse likeResponse) {
       /* pagenum=1;
        mPresenter.HomeRecom(pagenum,50);*/
       Log.e("qsd","likeResponse"+likeResponse.getLikes());
        homeRecomAdapter.getData().get(0).getRecom().get(index) .setLikes(likeResponse.getLikes());
        homeRecomAdapter.getData().get(0).getRecom().get(index).setIsLike(like);
        homeRecomAdapter.notifyItemChanged(3);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(HomeEvent event) {
        AudioManager audioManager = (AudioManager)mContext.getSystemService(Context.AUDIO_SERVICE);
        //当前音量
        int mCurrentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        Log.e("qsd","手机音量"+mCurrentVolume);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, mCurrentVolume, AudioManager.FLAG_PLAY_SOUND);
       if (event.getTAG()==3){
           pagenum=1;
           mPresenter.Homehote(pagenum,10);
           mPresenter.Homebanner(pagenum,10);
           mPresenter.HomeRecom(pagenum,50);
        }


    }

    @Override
    public void followSuccess() {
        mPresenter.HomeRecom(pagenum,50);

    }

    @Override
    public void canclefollowSuccess() {
        mPresenter.HomeRecom(pagenum,50);

    }
}