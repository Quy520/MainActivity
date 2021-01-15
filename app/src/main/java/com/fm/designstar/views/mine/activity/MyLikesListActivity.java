package com.fm.designstar.views.mine.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.fm.designstar.R;
import com.fm.designstar.base.BaseActivity;
import com.fm.designstar.events.FllowEvent;
import com.fm.designstar.model.bean.HomeFindBean;
import com.fm.designstar.model.bean.MessageBean;
import com.fm.designstar.model.server.response.FansResponse;
import com.fm.designstar.model.server.response.MessageResponse;
import com.fm.designstar.utils.SpaceItemDecoration;
import com.fm.designstar.utils.ToastUtil;
import com.fm.designstar.utils.Tool;
import com.fm.designstar.views.Detail.activity.DTDetailsActivity;
import com.fm.designstar.views.Detail.activity.VedioPlayActivity;
import com.fm.designstar.views.Detail.contract.FindBidContract;
import com.fm.designstar.views.Detail.presenter.FindByIdPresenter;
import com.fm.designstar.views.main.adapter.MessageAdapter;
import com.fm.designstar.views.main.contract.FansContract;
import com.fm.designstar.views.main.contract.MessageContract;
import com.fm.designstar.views.main.presenter.FansPresenter;
import com.fm.designstar.views.main.presenter.MessagePresenter;
import com.fm.designstar.views.mine.adapter.FansListAdapter;
import com.fm.designstar.widget.recycler.BaseRecyclerAdapter;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import butterknife.BindView;

public class MyLikesListActivity extends BaseActivity<MessagePresenter>  implements MessageContract.View ,XRecyclerView.LoadingListener, FindBidContract.View{
    @BindView(R.id.recy_fans)
    XRecyclerView recy_black;
    private MessageAdapter messageAdapter;

    private List<String> urls=new ArrayList<>();
    private int type;
    private int pagenum=1;
    private String uuid;
    private boolean hasnext;
    MessageBean findBean;
    private FindByIdPresenter findByIdPresenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_fans_list;
    }

    @Override
    public void initPresenter() {
        mPresenter.init(this);

        findByIdPresenter=new FindByIdPresenter();
        findByIdPresenter.init(this);
    }

    @Override
    public void loadData() {


            mTitle.setTitle(R.string.like);
        mPresenter.Message(pagenum,10,"1");



        recy_black.setPullRefreshEnabled(true);
        recy_black.setLoadingMoreEnabled(true);
        recy_black.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recy_black.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotate);
               recy_black.setLayoutManager(new LinearLayoutManager(mContext));
        recy_black.addItemDecoration(new SpaceItemDecoration().setBottom(Tool.dip2px(mContext, 1)));


        recy_black.setHasFixedSize(true);
        recy_black.setFocusable(false);
        messageAdapter=new MessageAdapter();
        recy_black.setAdapter(messageAdapter);

        //4)实现 下拉刷新和加载更多 接口
        recy_black.setLoadingListener(this);

        messageAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClick() {
            @Override
            public void onItemClick(View view, int position) {

                findBean = messageAdapter.getData().get(position-1);
                Log.e("qsd","messageAdapter"+position+"==="+new Gson().toJson(findBean));
                findByIdPresenter.FindBid(findBean.getMomentId());

            }
        });
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
        mPresenter.Message(pagenum,10,"1");

    }

    @Override
    public void onLoadMore() {
        if (hasnext) {
            pagenum++;
            mPresenter.Message(pagenum,10,"1");

        }else {
            recy_black.loadMoreComplete();

        }
    }



    @Override
    public void MessageSuccess(MessageResponse response) {
        if( pagenum==1){
            messageAdapter.clearData();
        }
        messageAdapter.addData(response.getResult());
    }

    @Override
    public void FindBidSuccess(HomeFindBean homeFindBean) {
        if (homeFindBean.getMediaType()==2){
            Bundle bundle = new Bundle();
            bundle.putSerializable("info",homeFindBean);
            startActivity(VedioPlayActivity.class, bundle);

        }else {

            Bundle bundle = new Bundle();
            bundle.putSerializable("info", homeFindBean);
            startActivity(DTDetailsActivity.class, bundle);
        }
    }
}