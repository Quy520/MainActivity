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
import com.fm.designstar.views.Fabu.contract.UploadContract;
import com.fm.designstar.views.main.adapter.HomeGuanzhuAdapter;
import com.fm.designstar.views.mine.adapter.BlackListAdapter;
import com.fm.designstar.views.mine.contract.UseMomentContract;
import com.fm.designstar.views.mine.presenter.UseMomentPresenter;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyActivitysActivity extends BaseActivity<UseMomentPresenter> implements UseMomentContract.View,XRecyclerView.LoadingListener {
    @BindView(R.id.recy_activits)
    XRecyclerView recy_activits;
    HomeGuanzhuAdapter guanzhuAdapter;
    private List<String> urls=new ArrayList<>();
    private int pagenum=0;
    @BindView(R.id.nodada)
    ImageView imageView;
    private boolean hasnext;

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_activitys;
    }

    @Override
    public void initPresenter() {
        mPresenter.init(this);

    }

    @Override
    public void loadData() {
        mTitle.setTitle(R.string.my_dt);
        recy_activits.setPullRefreshEnabled(true);
        recy_activits.setLoadingMoreEnabled(true);
        recy_activits.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recy_activits.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotate);
               recy_activits.setLayoutManager(new LinearLayoutManager(mContext));
        recy_activits.addItemDecoration(new SpaceItemDecoration().setBottom(Tool.dip2px(mContext, 1)));
        recy_activits.setNestedScrollingEnabled(false);
        guanzhuAdapter=new HomeGuanzhuAdapter();
        recy_activits.setAdapter(guanzhuAdapter);
        guanzhuAdapter.setScreenWidth(Util.getScreenWidth(mContext), getResources().getDisplayMetrics().density);

        //4)实现 下拉刷新和加载更多 接口
        recy_activits.setLoadingListener(this);
        mPresenter.UseMoment(pagenum,10,1, null,App.getConfig().getUserid());
    }




    @Override
    public void showLoading(String content, int code) {


    }

    @Override
    public void stopLoading(int code) {
        recy_activits.refreshComplete(); //下拉刷新完成
        recy_activits.loadMoreComplete();

    }

    @Override
    public void showErrorMsg(String msg, int code) {
        recy_activits.refreshComplete(); //下拉刷新完成
        recy_activits.loadMoreComplete();
        ToastUtil.showToast(msg);

    }

    @Override
    public void UseMomentSuccess(HomeFindResponse homeFindResponse ) {
        if (pagenum==0){
            guanzhuAdapter.clearData();
        }
        hasnext=homeFindResponse.isHasNextPage();
        if (homeFindResponse.getResult()==null){
            imageView.setVisibility(View.VISIBLE);
            recy_activits.setVisibility(View.GONE);
        }else {
            if (homeFindResponse.getResult().size()>0){
                imageView.setVisibility(View.GONE);
                recy_activits.setVisibility(View.VISIBLE);
                guanzhuAdapter.addData(homeFindResponse.getResult());
            }else {
                imageView.setVisibility(View.VISIBLE);
                recy_activits.setVisibility(View.GONE);
            }

        }
    }


    @Override
    public void onRefresh() {
        hasnext=true;
        pagenum=0;
        mPresenter.UseMoment(pagenum,10,1, null,App.getConfig().getUserid());


    }

    @Override
    public void onLoadMore() {
        if (hasnext){
            pagenum++;
            mPresenter.UseMoment(pagenum,10,1, null,App.getConfig().getUserid());



        } else {
            recy_activits.loadMoreComplete();
        }

    }
}