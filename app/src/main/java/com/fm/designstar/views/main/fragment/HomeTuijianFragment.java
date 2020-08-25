package com.fm.designstar.views.main.fragment;

import android.content.Context;
import android.media.AudioManager;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;

import com.fm.designstar.R;
import com.fm.designstar.app.App;
import com.fm.designstar.base.BaseFragment;
import com.fm.designstar.events.HomeEvent;
import com.fm.designstar.model.bean.HomeFindBean;
import com.fm.designstar.model.bean.NewsListBean;
import com.fm.designstar.model.server.response.HomeFindResponse;
import com.fm.designstar.model.server.response.LikeResponse;
import com.fm.designstar.utils.SpaceItemDecoration;
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


public class HomeTuijianFragment extends BaseFragment<HomeRecomPresenter> implements HomeRecomContract.View ,XRecyclerView.LoadingListener , LikeContract.View{



    @BindView(R.id.home_recy)
    XRecyclerView home_recy;
    private int pagenum=0;

    private HomeAdapter homeRecomAdapter;
    private List<String> urls=new ArrayList<>();
    private List<NewsListBean> NewsList=new ArrayList<>();
    private NewsListBean newsListBean=new NewsListBean();
    private List<HomeFindBean> result=new ArrayList<>();
    HomeFindBean homeFindBean=new HomeFindBean();
    private boolean islding=false,hasnext;
    private int banner,hot,recome;
    private LikePresenter likePresenter;
    private int index=0;
    private int like=0;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_tuijian;
    }

    @Override
    public void initPresenter() {
        mPresenter.init(this);likePresenter=new LikePresenter();
        likePresenter.init(this);

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
        mPresenter.Homehote(pagenum,10);
        mPresenter.Homebanner(pagenum,10);
        mPresenter.HomeRecom(pagenum,50);

        //4)实现 下拉刷新和加载更多 接口
        home_recy.setLoadingListener(this);
        homeRecomAdapter.setOnClickListener(new HomeAdapter.OnClickListener() {
            @Override
            public void onLikeClick(int position, boolean b, CompoundButton compoundButton) {

                if (b){
                    if (compoundButton.isPressed()){
                        like=1;
                        index=position;
                        likePresenter.Like(1,homeRecomAdapter.getData().get(0).getRecom().get(position).getMomentId());
                    }
                }else {
                    like=0;
                    index=position;
                    likePresenter.Like(1,homeRecomAdapter.getData().get(0).getRecom().get(position).getMomentId());

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
        recome=1;
        hasnext=homeFindResponse.isHasNextPage();
        if (pagenum>0){
            for (int i=0;i<homeFindResponse.getResult().size();i++){
                NewsList.get(0).getRecom().add(homeFindResponse.getResult().get(i));
            }
        }else {
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

        newsListBean.setHot(homeFindResponse.getResult());

        NewsList.add(newsListBean);

        if (recome==1&&hot==1&&banner==1){
            homeRecomAdapter.addData(NewsList);
        }

    }

    @Override
    public void HomebannerRecomSuccess(HomeFindResponse homeFindResponse) {
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
        pagenum=0;
        mPresenter.Homehote(pagenum,10);
        mPresenter.Homebanner(pagenum,10);
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
       /* pagenum=0;
        mPresenter.HomeRecom(pagenum,50);*/
       Log.e("qsd","likeResponse"+index);
        homeRecomAdapter.getData().get(0).getRecom().get(index) .setLikes(likeResponse.getLikes());
        homeRecomAdapter.getData().get(0).getRecom().get(index).setIsLike(like);
        homeRecomAdapter.notifyItemChanged(index+3);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(HomeEvent event) {
        AudioManager audioManager = (AudioManager)mContext.getSystemService(Context.AUDIO_SERVICE);
        //当前音量
        int mCurrentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        Log.e("qsd","手机音量"+mCurrentVolume);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, mCurrentVolume, AudioManager.FLAG_PLAY_SOUND);
       if (event.getTAG()==3){
           pagenum=0;
           mPresenter.Homehote(pagenum,10);
           mPresenter.Homebanner(pagenum,10);
           mPresenter.HomeRecom(pagenum,50);
        }


    }
}