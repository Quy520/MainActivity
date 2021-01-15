package com.fm.designstar.views.mine.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.fm.designstar.R;
import com.fm.designstar.app.App;
import com.fm.designstar.base.BaseActivity;
import com.fm.designstar.model.server.response.HomeFindResponse;
import com.fm.designstar.model.server.response.LikeResponse;
import com.fm.designstar.utils.SpaceItemDecoration;
import com.fm.designstar.utils.ToastUtil;
import com.fm.designstar.utils.Tool;
import com.fm.designstar.utils.Util;
import com.fm.designstar.views.Detail.activity.VedioPlayActivity;
import com.fm.designstar.views.Detail.contract.LikeContract;
import com.fm.designstar.views.Detail.presenter.LikePresenter;
import com.fm.designstar.views.main.adapter.HomeRecomAdapter;
import com.fm.designstar.views.mine.adapter.BlackListAdapter;
import com.fm.designstar.views.mine.contract.UseMomentContract;
import com.fm.designstar.views.mine.presenter.UseMomentPresenter;
import com.fm.designstar.widget.recycler.BaseRecyclerAdapter;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyWorkActivity extends BaseActivity<UseMomentPresenter> implements UseMomentContract.View ,XRecyclerView.LoadingListener, LikeContract.View{
    @BindView(R.id.recy_works)
    XRecyclerView recy_works;
    HomeRecomAdapter homeRecomAdapter;

    private int pagenum=1;
    @BindView(R.id.nodada)
    ImageView imageView;
    private boolean hasnext;
    private LikePresenter likePresenter;
    private int index=0;
    private int like=0;

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_work;
    }

    @Override
    public void initPresenter() {
        mPresenter.init(this);
        likePresenter=new LikePresenter();
        likePresenter.init(this);

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
        homeRecomAdapter=new HomeRecomAdapter(0);
        recy_works.setAdapter(homeRecomAdapter);

        //4)实现 下拉刷新和加载更多 接口
        recy_works.setLoadingListener(this);
        homeRecomAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClick() {
            @Override
            public void onItemClick(View view, int position) {

                Intent intent=  new Intent(mContext, VedioPlayActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("info", homeRecomAdapter.getData().get(position-1));
                intent.putExtras(bundle);
                mContext. startActivity(intent);
            }
        });

        homeRecomAdapter.setOnClickListener(new HomeRecomAdapter.OnClickListener() {
            @Override
            public void onLikeClick(int position, boolean b, CompoundButton compoundButton) {
                if (b){
                    if (compoundButton.isPressed()){
                        like=1;
                        index=position;
                        likePresenter.Like(1,homeRecomAdapter.getData().get(position).getMomentId());
                    }
                }else {
                    like=0;
                    index=position;
                    likePresenter.Like(1,homeRecomAdapter.getData().get(position).getMomentId());

                }
            }

            @Override
            public void onGuanClick(int position, boolean b, CompoundButton compoundButton) {

            }
        });

    }

    @Override
    public void UseMomentSuccess(HomeFindResponse findResponse ) {
        if (pagenum==1){
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
        pagenum=1;
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

    @Override
    public void LikeSuccess(LikeResponse likeResponse) {
        Log.e("qsd","likeResponse"+index);
        homeRecomAdapter.getData().get(index) .setLikes(likeResponse.getLikes());
        homeRecomAdapter.getData().get(index).setIsLike(like);
        homeRecomAdapter.notifyItemChanged(index+1);
    }
}