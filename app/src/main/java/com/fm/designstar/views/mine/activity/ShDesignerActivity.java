package com.fm.designstar.views.mine.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import butterknife.BindView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.fm.designstar.R;
import com.fm.designstar.app.App;
import com.fm.designstar.base.BaseActivity;
import com.fm.designstar.model.server.response.DesignerPageResponse;
import com.fm.designstar.utils.SpaceItemDecoration;
import com.fm.designstar.utils.ToastUtil;
import com.fm.designstar.utils.Tool;
import com.fm.designstar.views.main.adapter.HomeRecomAdapter;
import com.fm.designstar.views.mine.adapter.DesignerPageAdapter;
import com.fm.designstar.views.mine.contract.DesignerPageContract;
import com.fm.designstar.views.mine.presenter.DesignerPagePresenter;
import com.fm.designstar.widget.recycler.BaseRecyclerAdapter;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

public class ShDesignerActivity extends BaseActivity<DesignerPagePresenter>  implements DesignerPageContract.View ,XRecyclerView.LoadingListener{
    @BindView(R.id.recy_mansger)
    XRecyclerView recy_mansger;
    private DesignerPageAdapter  homeRecomAdapter;
private int pagenum=0;
private boolean hasnext;
    @Override
    public int getLayoutId() {
        return R.layout.activity_sh_designer;
    }

    @Override
    public void initPresenter() {
        mPresenter.init(this);
    }

    @Override
    public void loadData() {
        mTitle.setTitle(R.string.my_shemanger);

        recy_mansger.setPullRefreshEnabled(true);
        recy_mansger.setLoadingMoreEnabled(true);
        recy_mansger.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);

        recy_mansger.setLayoutManager(new LinearLayoutManager(mContext));
        recy_mansger.addItemDecoration(new SpaceItemDecoration().setBottom(Tool.dip2px(mContext, 1)));

        recy_mansger.setNestedScrollingEnabled(false);
        homeRecomAdapter=new DesignerPageAdapter();
        recy_mansger.setAdapter(homeRecomAdapter);

        //4)实现 下拉刷新和加载更多 接口
        recy_mansger.setLoadingListener(this);
        mPresenter.DesignerPage(pagenum,10);

        homeRecomAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClick() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=  new Intent(mContext,InfoDetailActivity.class);
                intent.putExtra("UUID",homeRecomAdapter.getData().get(position-1).getCode()+"");
                intent.putExtra("type",1);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public void DesignerPageSuccess(DesignerPageResponse statebody) {
        hasnext=statebody.isHasNextPage();if (pagenum==0){
            homeRecomAdapter.clearData();
        }
        homeRecomAdapter.addData(statebody.getResult());
    }


    @Override
    public void showLoading(String content, int code) {


    }

    @Override
    public void stopLoading(int code) {
        recy_mansger.refreshComplete(); //下拉刷新完成
        recy_mansger.loadMoreComplete();

    }

    @Override
    public void showErrorMsg(String msg, int code) {
        recy_mansger.refreshComplete(); //下拉刷新完成
        recy_mansger.loadMoreComplete();
        ToastUtil.showToast(msg);

    }

    @Override
    public void onRefresh() {
        hasnext=true;
        pagenum=0;
        mPresenter.DesignerPage(pagenum,10);



    }

    @Override
    public void onLoadMore() {
        if (hasnext){
            pagenum++;
            mPresenter.DesignerPage(pagenum,10);

        } else {
            recy_mansger.loadMoreComplete();
        }

    }
}