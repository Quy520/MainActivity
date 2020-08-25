package com.fm.designstar.views.mine.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.fm.designstar.R;
import com.fm.designstar.app.App;
import com.fm.designstar.base.BaseActivity;
import com.fm.designstar.model.server.response.HomeFindResponse;
import com.fm.designstar.utils.SpaceItemDecoration;
import com.fm.designstar.utils.ToastUtil;
import com.fm.designstar.utils.Tool;
import com.fm.designstar.utils.Util;
import com.fm.designstar.views.main.adapter.HomeRecomAdapter;
import com.fm.designstar.views.mine.adapter.BlackListAdapter;
import com.fm.designstar.views.mine.contract.UseMomentContract;
import com.fm.designstar.views.mine.presenter.UseMomentPresenter;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyWorkActivity extends BaseActivity<UseMomentPresenter> implements UseMomentContract.View ,XRecyclerView.LoadingListener{
    @BindView(R.id.recy_works)
    XRecyclerView recy_works;
    HomeRecomAdapter homeRecomAdapter;

    private int pagenum=0;
    @BindView(R.id.nodada)
    ImageView imageView;
    private boolean hasnext;

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_work;
    }

    @Override
    public void initPresenter() {
        mPresenter.init(this);

    }

    @Override
    public void loadData() {
        mTitle.setTitle(R.string.my_zp);

        recy_works.setPullRefreshEnabled(true);
        recy_works.setLoadingMoreEnabled(true);
        recy_works.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recy_works.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotate);
        mPresenter.UseMoment(pagenum,10,2,null, App.getConfig().getUserid());
               recy_works.setLayoutManager(new LinearLayoutManager(mContext));
        recy_works.addItemDecoration(new SpaceItemDecoration().setBottom(Tool.dip2px(mContext, 1)));

        recy_works.setNestedScrollingEnabled(false);
        homeRecomAdapter=new HomeRecomAdapter();
        recy_works.setAdapter(homeRecomAdapter);

        //4)实现 下拉刷新和加载更多 接口
        recy_works.setLoadingListener(this);

    }

    @Override
    public void UseMomentSuccess(HomeFindResponse findResponse ) {
        if (pagenum==0){
            homeRecomAdapter.clearData();
        }
        hasnext=findResponse.isHasNextPage();
        if (findResponse.getResult()==null){
            imageView.setVisibility(View.VISIBLE);
            recy_works.setVisibility(View.GONE);
        }else {
            if (findResponse.getResult().size()>0){
                imageView.setVisibility(View.GONE);
                recy_works.setVisibility(View.VISIBLE);
                homeRecomAdapter.addData(findResponse.getResult());
            }else {
                imageView.setVisibility(View.VISIBLE);
                recy_works.setVisibility(View.GONE);
            }

        }

    }

    @Override
    public void showLoading(String content, int code) {


    }

    @Override
    public void stopLoading(int code) {
        recy_works.refreshComplete(); //下拉刷新完成
        recy_works.loadMoreComplete();

    }

    @Override
    public void showErrorMsg(String msg, int code) {
        recy_works.refreshComplete(); //下拉刷新完成
        recy_works.loadMoreComplete();
        ToastUtil.showToast(msg);

    }

    @Override
    public void onRefresh() {
        hasnext=true;
        pagenum=0;
        mPresenter.UseMoment(pagenum,10,2,null, App.getConfig().getUserid());


    }

    @Override
    public void onLoadMore() {
        if (hasnext){
            pagenum++;
            mPresenter.UseMoment(pagenum,10,2,null, App.getConfig().getUserid());


        } else {
            recy_works.loadMoreComplete();
        }

    }
}