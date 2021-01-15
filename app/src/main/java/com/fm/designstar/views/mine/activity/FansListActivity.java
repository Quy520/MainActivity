package com.fm.designstar.views.mine.activity;

import android.util.Log;
import android.widget.CompoundButton;

import com.fm.designstar.R;
import com.fm.designstar.app.App;
import com.fm.designstar.base.BaseActivity;
import com.fm.designstar.events.FllowEvent;
import com.fm.designstar.model.server.response.FansResponse;
import com.fm.designstar.utils.SpaceItemDecoration;
import com.fm.designstar.utils.ToastUtil;
import com.fm.designstar.utils.Tool;
import com.fm.designstar.views.main.adapter.HomeAdapter;
import com.fm.designstar.views.main.contract.FansContract;
import com.fm.designstar.views.main.presenter.FansPresenter;
import com.fm.designstar.views.mine.adapter.BlackListAdapter;
import com.fm.designstar.views.mine.adapter.FansListAdapter;
import com.fm.designstar.views.mine.contract.followContract;
import com.fm.designstar.views.mine.presenter.followPresenter;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class FansListActivity extends BaseActivity<FansPresenter>  implements FansContract.View ,XRecyclerView.LoadingListener, followContract.View{
    @BindView(R.id.recy_fans)
    XRecyclerView recy_black;
    FansListAdapter fansListAdapter;
    private List<String> urls=new ArrayList<>();
    private int type;
    private int pagenum=1;
    private String uuid;
    private boolean hasnext;
    private followPresenter presenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_fans_list;
    }

    @Override
    public void initPresenter() {
        mPresenter.init(this);

        presenter=new followPresenter();
        presenter.init(this);
    }

    @Override
    public void loadData() {
        if(!EventBus.getDefault().isRegistered(this)){//加上判断
            EventBus.getDefault().register(this);
        }
        type=  getIntent().getIntExtra("type",1);
        uuid= getIntent().getStringExtra("UUID");
        if (type==2){
            mTitle.setTitle(R.string.my_fans);
            mPresenter.Fans(pagenum,20,uuid);
        }else {
            mTitle.setTitle(R.string.guanzhu);
            mPresenter.Guanzhu(pagenum,20,uuid);
        }

        recy_black.setPullRefreshEnabled(true);
        recy_black.setLoadingMoreEnabled(true);
        recy_black.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recy_black.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotate);
               recy_black.setLayoutManager(new LinearLayoutManager(mContext));
        recy_black.addItemDecoration(new SpaceItemDecoration().setBottom(Tool.dip2px(mContext, 1)));

        fansListAdapter=new FansListAdapter(type);
        recy_black.setAdapter(fansListAdapter);
        recy_black.setHasFixedSize(true);
        recy_black.setFocusable(false);
        //4)实现 下拉刷新和加载更多 接口
        recy_black.setLoadingListener(this);

        fansListAdapter.setOnClickListener(new FansListAdapter.OnClickListener() {
            @Override
            public void onGuanClick(int position, boolean b, CompoundButton compoundButton) {
                if (fansListAdapter.getData().get(position).getStatus()==1){
                    if (type==1){
                        presenter.canclefollow(fansListAdapter.getData().get(position).getFollowedUserId()+"");

                    }else {
                        presenter.canclefollow(fansListAdapter.getData().get(position).getUserId()+"");

                    }
                }else {
                    if (type==1){
                        presenter.follow(fansListAdapter.getData().get(position).getFollowedUserId()+"");

                    }else {
                        presenter.follow(fansListAdapter.getData().get(position).getUserId()+"");

                    }

                }
            }
        });
    }

    @Override
    public void FansListSuccess(FansResponse Response) {
        if (Response.getResult()==null){
            return;
        }
        if (pagenum==1){
            fansListAdapter.clearData();
        }

        fansListAdapter.addData(Response.getResult());

    }

    @Override
    public void GuanzhuListSuccess(FansResponse Response) {
        if (Response.getResult()==null){
            return;
        }
        hasnext=Response.isHasNextPage();

        if (pagenum==1){
            fansListAdapter.clearData();
        }
        fansListAdapter.addData(Response.getResult());

    }

    @Override
    public void showLoading(String content, int code) {


    }

    @Override
    public void stopLoading(int code) {
        recy_black.refreshComplete(); //下拉刷新完成
        recy_black.loadMoreComplete();


    }

    @Override
    public void showErrorMsg(String msg, int code) {
        recy_black.refreshComplete(); //下拉刷新完成
        recy_black.loadMoreComplete();

        ToastUtil.showToast(msg);

    }

    @Override
    public void onRefresh() {
        hasnext=true;

        pagenum=1;
        if (type==2){

            mPresenter.Fans(pagenum,10,uuid);
        }else {
            mTitle.setTitle(R.string.guanzhu);
            mPresenter.Guanzhu(pagenum,10,uuid);
        }
    }

    @Override
    public void onLoadMore() {
        if (hasnext) {
            pagenum++;
            if (type == 2) {

                mPresenter.Fans(pagenum, 10, uuid);
            } else {

                mPresenter.Guanzhu(pagenum, 10, uuid);
            }
        }else {
            recy_black.loadMoreComplete();

        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(FllowEvent event) {
        pagenum=1;

        if (type == 2) {

            mPresenter.Fans(pagenum, 10, uuid);
        } else {

            mPresenter.Guanzhu(pagenum, 10, uuid);
        }
    }

    @Override
    public void followSuccess() {
        if (type == 2) {

            mPresenter.Fans(pagenum, 20, uuid);
        } else {

            mPresenter.Guanzhu(pagenum, 20, uuid);
        }
    }

    @Override
    public void canclefollowSuccess() {
        if (type == 2) {
            mPresenter.Fans(pagenum, 20, uuid);
        } else {

            mPresenter.Guanzhu(pagenum, 20, uuid);
        }
    }
}