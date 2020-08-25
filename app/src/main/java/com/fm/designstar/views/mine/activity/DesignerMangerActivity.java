package com.fm.designstar.views.mine.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import butterknife.BindView;

import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.fm.designstar.R;
import com.fm.designstar.app.App;
import com.fm.designstar.base.BaseActivity;
import com.fm.designstar.events.UpdataEvent;
import com.fm.designstar.events.UpdatainfoEvent;
import com.fm.designstar.model.server.response.findPageResponse;
import com.fm.designstar.utils.SpaceItemDecoration;
import com.fm.designstar.utils.StringUtil;
import com.fm.designstar.utils.ToastUtil;
import com.fm.designstar.utils.Tool;
import com.fm.designstar.utils.Util;
import com.fm.designstar.views.Detail.activity.VedioPlayActivity;
import com.fm.designstar.views.main.adapter.HomeGuanzhuAdapter;
import com.fm.designstar.views.mine.adapter.DesignerMangerAdapter;
import com.fm.designstar.views.mine.contract.DesignerRecordContract;
import com.fm.designstar.views.mine.presenter.DesignerRecordPresenter;
import com.fm.designstar.widget.recycler.BaseRecyclerAdapter;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class DesignerMangerActivity extends BaseActivity<DesignerRecordPresenter>  implements DesignerRecordContract.View ,XRecyclerView.LoadingListener {
    @BindView(R.id.recy_black)
    XRecyclerView hotRecycler;
    private int pagenum=0;
    private DesignerMangerAdapter mangerAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_designer_manger;
    }

    @Override
    public void initPresenter() {
        mPresenter.init(this);

    }

    @Override
    public void loadData() {
        if(!EventBus.getDefault().isRegistered(this)){//加上判断
            EventBus.getDefault().register(this);
        }
        mTitle.setTitle(R.string.my_shdesigner);
        mPresenter.DesignerRecord(pagenum,10);
        hotRecycler.setPullRefreshEnabled(true);
        hotRecycler.setLoadingMoreEnabled(true);
        hotRecycler.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        hotRecycler.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotate);
        hotRecycler.setLayoutManager(new LinearLayoutManager(mContext));
        hotRecycler.addItemDecoration(new SpaceItemDecoration().setBottom(Tool.dip2px(mContext, 1)));

        mangerAdapter=new DesignerMangerAdapter();

        //4)实现 下拉刷新和加载更多 接口
        hotRecycler.setLoadingListener(this);
        hotRecycler.setAdapter(mangerAdapter);

        mangerAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClick() {
            @Override
            public void onItemClick(View view, int position) {

                Bundle bundle = new Bundle();
                bundle.putSerializable("info", mangerAdapter.getData().get(position-1));
                startActivity(DesignerMangerDetailActivity.class, bundle);




            }
        });
    }

    @Override
    public void DesignerRecordSuccess(findPageResponse pageResponse) {
        if( pagenum==0){
            mangerAdapter.clearData();
        }
        mangerAdapter.addData(pageResponse.getResult());

    }

    @Override
    public void showLoading(String content, int code) {
       // App.loadingDefault(mActivity);

    }

    @Override
    public void stopLoading(int code) {
       // App.hideLoading();
        hotRecycler.refreshComplete(); //下拉刷新完成
        hotRecycler.loadMoreComplete();
    }

    @Override
    public void showErrorMsg(String msg, int code) {
       // App.hideLoading();
        ToastUtil.showToast(msg);
        hotRecycler.refreshComplete(); //下拉刷新完成
        hotRecycler.loadMoreComplete();
    }

    @Override
    public void onRefresh() {
        pagenum=0;
        mPresenter.DesignerRecord(pagenum,10);

    }

    @Override
    public void onLoadMore() {
        pagenum++;
        mPresenter.DesignerRecord(pagenum,10);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(UpdataEvent event) {
        pagenum=0;
        mPresenter.DesignerRecord(pagenum,10);
    }

}