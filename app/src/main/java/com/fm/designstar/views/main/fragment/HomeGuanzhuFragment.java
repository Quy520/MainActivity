package com.fm.designstar.views.main.fragment;

import android.util.Log;

import com.fm.designstar.R;
import com.fm.designstar.base.BaseFragment;
import com.fm.designstar.events.HomeEvent;
import com.fm.designstar.events.UpdatainfoEvent;
import com.fm.designstar.model.server.response.HomeFindResponse;
import com.fm.designstar.utils.SpaceItemDecoration;
import com.fm.designstar.utils.Tool;
import com.fm.designstar.utils.Util;
import com.fm.designstar.views.main.adapter.HomeGuanzhuAdapter;
import com.fm.designstar.views.main.adapter.HomeRecomAdapter;
import com.fm.designstar.views.main.contract.HomeFindContract;
import com.fm.designstar.views.main.contract.HomeGuanzhuContract;
import com.fm.designstar.views.main.presenter.HomeFindPresenter;
import com.fm.designstar.views.main.presenter.HomeGuanzhuPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;


public class HomeGuanzhuFragment extends   BaseFragment<HomeGuanzhuPresenter> implements HomeGuanzhuContract.View ,HomeFindContract.View  {

    private List<String> urls=new ArrayList<>();

    private HomeGuanzhuAdapter guanzhuAdapter;
    @BindView(R.id.home_recy)
    RecyclerView hotRecycler;
    private int pagenum=1;
    private HomeFindPresenter homeFindPresenter;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_guanzhu;
    }

    @Override
    public void initPresenter() {
    mPresenter.init(this);
        homeFindPresenter=new HomeFindPresenter();
        homeFindPresenter.init(this);
    }

    @Override
    public void loadData() {
        if(!EventBus.getDefault().isRegistered(this)){//加上判断
            EventBus.getDefault().register(this);
        }
          hotRecycler.setLayoutManager(new LinearLayoutManager(mContext));
        hotRecycler.addItemDecoration(new SpaceItemDecoration().setBottom(Tool.dip2px(mContext, 5)));
        hotRecycler.setNestedScrollingEnabled(false);
        guanzhuAdapter=new HomeGuanzhuAdapter();
        guanzhuAdapter.setScreenWidth(Util.getScreenWidth(mContext), getResources().getDisplayMetrics().density);

        hotRecycler.setAdapter(guanzhuAdapter);
        hotRecycler.setHasFixedSize(true);
        hotRecycler.setFocusable(false);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(HomeEvent event) {
       if (event.getTAG()==1){
           mPresenter.HomeGuanzhu(pagenum,10);
       }else {
           homeFindPresenter.HomeFind(pagenum,10);
       }

    }

    @Override
    public void HomeGuanzhuSuccess(HomeFindResponse homeFindResponse) {
        guanzhuAdapter.addData(homeFindResponse.getResult());
    }

    @Override
    public void showLoading(String content, int code) {

    }

    @Override
    public void stopLoading(int code) {

    }

    @Override
    public void showErrorMsg(String msg, int code) {

    }

    @Override
    public void HomeFindSuccess(HomeFindResponse homeFindResponse) {

    }
}