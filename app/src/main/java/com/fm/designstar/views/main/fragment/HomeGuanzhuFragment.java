package com.fm.designstar.views.main.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.fm.designstar.R;
import com.fm.designstar.app.App;
import com.fm.designstar.base.BaseFragment;
import com.fm.designstar.events.HomeEvent;
import com.fm.designstar.events.UpdatainfoEvent;
import com.fm.designstar.events.UploadzpEvent;
import com.fm.designstar.model.bean.HomeFindBean;
import com.fm.designstar.model.server.response.HomeFindResponse;
import com.fm.designstar.model.server.response.LikeResponse;
import com.fm.designstar.utils.SpaceItemDecoration;
import com.fm.designstar.utils.StringUtil;
import com.fm.designstar.utils.ToastUtil;
import com.fm.designstar.utils.Tool;
import com.fm.designstar.utils.Util;
import com.fm.designstar.views.Detail.DTDetailsActivity;
import com.fm.designstar.views.Detail.contract.LikeContract;
import com.fm.designstar.views.Detail.presenter.LikePresenter;
import com.fm.designstar.views.main.adapter.HomeGuanzhuAdapter;
import com.fm.designstar.views.main.adapter.HomeRecomAdapter;
import com.fm.designstar.views.main.contract.HomeFindContract;
import com.fm.designstar.views.main.contract.HomeGuanzhuContract;
import com.fm.designstar.views.main.presenter.HomeFindPresenter;
import com.fm.designstar.views.main.presenter.HomeGuanzhuPresenter;
import com.fm.designstar.widget.recycler.BaseRecyclerAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;


public class HomeGuanzhuFragment extends   BaseFragment<HomeGuanzhuPresenter> implements HomeGuanzhuContract.View ,HomeFindContract.View , LikeContract.View  {

    private List<String> urls=new ArrayList<>();

    private HomeGuanzhuAdapter guanzhuAdapter;
    @BindView(R.id.home_recy)
    RecyclerView hotRecycler;
    @BindView(R.id.nodada)
    ImageView imageView;
    private int pagenum=0;
    private HomeFindPresenter homeFindPresenter;
    private LikePresenter likePresenter;
    private int like=0,islike;
    HomeFindBean findBean;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_guanzhu;
    }

    @Override
    public void initPresenter() {
    mPresenter.init(this);
        homeFindPresenter=new HomeFindPresenter();
        homeFindPresenter.init(this);
        likePresenter=new LikePresenter();
        likePresenter.init(this);
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

       guanzhuAdapter.setOnClickListener(new HomeGuanzhuAdapter.OnClickListener() {
           @Override
           public void onLikeClick(int position, boolean b, CompoundButton compoundButton) {
               like=position;
                findBean = guanzhuAdapter.getData().get(position);
               if (b){
                   if (compoundButton.isPressed()) {
                       islike=1;
                       likePresenter.Like(guanzhuAdapter.getData().get(position).getMediaType(),guanzhuAdapter.getData().get(position).getMomentId());
                   }

               }else {
                   if (compoundButton.isPressed()) {
                       islike=0;
                       likePresenter.Like(guanzhuAdapter.getData().get(position).getMediaType(),guanzhuAdapter.getData().get(position).getMomentId());
                   }
               }

           }
       });
       guanzhuAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClick() {
           @Override
           public void onItemClick(View view, int position) {
               if (guanzhuAdapter.getData().get(position).getMomentType()==1){
                   Bundle bundle = new Bundle();
                   bundle.putSerializable("info", guanzhuAdapter.getData().get(position));
                   startActivity(DTDetailsActivity.class, bundle);
               }

           }
       });



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
        if (pagenum==0){
            guanzhuAdapter.clearData();
        }
        if (homeFindResponse.getResult()==null){
            imageView.setVisibility(View.VISIBLE);
            hotRecycler.setVisibility(View.GONE);
        }else {
            if (homeFindResponse.getResult().size()>0){
                imageView.setVisibility(View.GONE);
                hotRecycler.setVisibility(View.VISIBLE);
                guanzhuAdapter.addData(homeFindResponse.getResult());
            }else {
                imageView.setVisibility(View.VISIBLE);
                hotRecycler.setVisibility(View.GONE);
            }

        }
    }

    @Override
    public void showLoading(String content, int code) {
        App.loadingDefault(mActivity);

    }

    @Override
    public void stopLoading(int code) {
        App.hideLoading();

    }

    @Override
    public void showErrorMsg(String msg, int code) {
        App.hideLoading();
        ToastUtil.showToast(msg);

    }

    @Override
    public void HomeFindSuccess(HomeFindResponse homeFindResponse) {
        if (pagenum==0){
            guanzhuAdapter.clearData();
        }
        if (homeFindResponse.getResult()==null){
            imageView.setVisibility(View.VISIBLE);
            hotRecycler.setVisibility(View.GONE);
        }else {
            if (homeFindResponse.getResult().size()>0){
                imageView.setVisibility(View.GONE);
                hotRecycler.setVisibility(View.VISIBLE);
                guanzhuAdapter.addData(homeFindResponse.getResult());
            }else {
                imageView.setVisibility(View.VISIBLE);
                hotRecycler.setVisibility(View.GONE);
            }

        }
    }

    @Override
    public void LikeSuccess(LikeResponse likeResponse) {
         findBean .setLikes(likeResponse.getLikes());
        findBean.setIsLike(islike);
        guanzhuAdapter.notifyItemChanged(like);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(UploadzpEvent event) {


            homeFindPresenter.HomeFind(pagenum,10);


    }
}