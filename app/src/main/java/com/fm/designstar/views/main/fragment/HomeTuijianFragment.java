package com.fm.designstar.views.main.fragment;

import android.util.Log;
import android.view.View;

import com.fm.designstar.R;
import com.fm.designstar.app.App;
import com.fm.designstar.base.BaseFragment;
import com.fm.designstar.model.bean.HomeFindBean;
import com.fm.designstar.model.bean.NewsListBean;
import com.fm.designstar.model.server.response.HomeFindResponse;
import com.fm.designstar.utils.SpaceItemDecoration;
import com.fm.designstar.utils.ToastUtil;
import com.fm.designstar.utils.Tool;
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

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;


public class HomeTuijianFragment extends BaseFragment<HomeRecomPresenter> implements HomeRecomContract.View ,XRecyclerView.LoadingListener {



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

    @Override
    public int getLayoutId() {
        return R.layout.fragment_tuijian;
    }

    @Override
    public void initPresenter() {
        mPresenter.init(this);

    }

    @Override
    public void loadData() {
        home_recy.setPullRefreshEnabled(true);
        home_recy.setLoadingMoreEnabled(true);
        home_recy.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        home_recy.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotate);

        home_recy.setLayoutManager(new LinearLayoutManager(mContext));
        home_recy.addItemDecoration(new SpaceItemDecoration().setBottom(Tool.dip2px(mContext, 0)));
        homeRecomAdapter=new HomeAdapter(this);
        home_recy.setAdapter(homeRecomAdapter);
        mPresenter.Homehote(pagenum,10);
        mPresenter.Homebanner(pagenum,10);
        mPresenter.HomeRecom(pagenum,10);

        //4)实现 下拉刷新和加载更多 接口
       // home_recy.setLoadingListener(this);
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
            Log.e("qsd","=="+ NewsList.get(0).getRecom().size()+"==="+new Gson().toJson( NewsList.get(0).getRecom()));
        }


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
        mPresenter.HomeRecom(pagenum,10);
        NewsList=new ArrayList<>();
    }

    @Override
    public void onLoadMore() {
        if (hasnext){
            islding=true;
            pagenum++;
            mPresenter.HomeRecom(pagenum,10);
        }else {
            home_recy.loadMoreComplete();

        }

    }
}