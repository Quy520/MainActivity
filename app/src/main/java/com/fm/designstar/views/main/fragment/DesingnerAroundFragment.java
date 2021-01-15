package com.fm.designstar.views.main.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.fm.designstar.R;
import com.fm.designstar.app.App;
import com.fm.designstar.base.BaseFragment;
import com.fm.designstar.events.FllowEvent;
import com.fm.designstar.model.bean.DesignerBean;
import com.fm.designstar.model.server.response.DesignerResponse;
import com.fm.designstar.utils.SpaceItemDecoration;
import com.fm.designstar.utils.ToastUtil;
import com.fm.designstar.utils.Tool;
import com.fm.designstar.views.main.adapter.DesignerAdapter;
import com.fm.designstar.views.main.contract.DesignerContract;
import com.fm.designstar.views.main.presenter.DesignerPresenter;
import com.fm.designstar.views.mine.activity.InfoDetailActivity;
import com.fm.designstar.views.mine.contract.followContract;
import com.fm.designstar.views.mine.presenter.followPresenter;
import com.fm.designstar.widget.recycler.BaseRecyclerAdapter;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import butterknife.BindView;


public class DesingnerAroundFragment extends BaseFragment<DesignerPresenter>  implements DesignerContract.View , XRecyclerView.LoadingListener, followContract.View{

    DesignerBean designerBean;
    private int pagenum=1;
    @BindView(R.id.designer_recy)
    XRecyclerView designer_recy;
    private DesignerAdapter designerAdapter;

    private List<String> urls=new ArrayList<>();
    private followPresenter presenter;
    private int positiona;
    private boolean idnext;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_designer_child;
    }

    @Override
    public void initPresenter() {
        mPresenter.init(this);
        presenter=new followPresenter();
        presenter.init(this);
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            mPresenter.Designer(pagenum,10);
        }
    }
    @Override
    public void loadData() {
        if(!EventBus.getDefault().isRegistered(this)){//加上判断
            EventBus.getDefault().register(this);
        }
        designer_recy.setPullRefreshEnabled(true);
        designer_recy.setLoadingMoreEnabled(true);
        designer_recy.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        designer_recy.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotate);
        designer_recy.setLayoutManager(new LinearLayoutManager(mContext));
        designer_recy.addItemDecoration(new SpaceItemDecoration().setBottom(Tool.dip2px(mContext, 1)));
        designer_recy.setNestedScrollingEnabled(false);
        designerAdapter=new DesignerAdapter();
        designer_recy.setAdapter(designerAdapter);
        designer_recy.setHasFixedSize(true);
        designer_recy.setFocusable(false);
        //4)实现 下拉刷新和加载更多 接口
        designer_recy.setLoadingListener(this);

        designerAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClick() {
            @Override
            public void onItemClick(View view, int position) {

                Intent intent=  new Intent(mContext, InfoDetailActivity.class);
                intent.putExtra("UUID",designerAdapter.getData().get(position-1).getUserId());
                mContext.startActivity(intent);
            }
        });

        designerAdapter.setOnClickListener(new DesignerAdapter.OnClickListener() {
            @Override
            public void onGuamzhuClick(int position) {
                Log.e("qsd","qsd"+position);
                positiona=position;
                if (designerAdapter.getData().get(position).isFollow()){
                    presenter.canclefollow(designerAdapter.getData().get(position).getUserId());
                }else {
                    presenter.follow(designerAdapter.getData().get(position).getUserId());

                }

            }
        });
    }

    @Override
    public void DesignerSuccess(DesignerResponse homeFindResponse) {
        idnext=homeFindResponse.isHasNextPage();
        if (pagenum==1){
            designerAdapter.clearData();
        }
        designerAdapter.addData(homeFindResponse.getResult());
    }

    @Override
    public void DesignerfindSuccess(DesignerResponse homeFindResponse) {

    }

    @Override
    public void showLoading(String content, int code) {
        //  App.loadingDefault(mActivity);

    }

    @Override
    public void stopLoading(int code) {
        designer_recy.refreshComplete(); //下拉刷新完成
        designer_recy.loadMoreComplete();
    }

    @Override
    public void showErrorMsg(String msg, int code) {

        ToastUtil.showToast(msg);
        designer_recy.refreshComplete(); //下拉刷新完成
        designer_recy.loadMoreComplete();
    }

    @Override
    public void onRefresh() {
        pagenum=1;
        if (pagenum==1){
            designerAdapter.clearData();
        }


        mPresenter.Designer(pagenum,10);



    }

    @Override
    public void onLoadMore() {
        if (idnext){
            pagenum++;
            mPresenter.Designer(pagenum,10);

        }else {
            designer_recy.loadMoreComplete();
        }



    }

    @Override
    public void followSuccess() {
        pagenum=1;
        mPresenter.Designer(pagenum,10);
        ;


    }

    @Override
    public void canclefollowSuccess() {
        pagenum=1;
        mPresenter.Designer(pagenum,10);


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(FllowEvent event) {
        pagenum=1;

        mPresenter.Designer(pagenum,10);

    }
}