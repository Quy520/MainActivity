package com.fm.designstar.views.main.fragment;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.fm.designstar.R;
import com.fm.designstar.base.BaseFragment;
import com.fm.designstar.dialog.ActionSheetDialog;
import com.fm.designstar.events.DeleteMonEvent;
import com.fm.designstar.events.GetCommetsEvent;
import com.fm.designstar.events.GetLikesEvent;
import com.fm.designstar.events.HomeEvent;
import com.fm.designstar.events.UploadzpEvent;
import com.fm.designstar.model.bean.HomeFindBean;
import com.fm.designstar.model.server.response.HomeFindResponse;
import com.fm.designstar.model.server.response.LikeResponse;
import com.fm.designstar.utils.AutoPlayUtils;
import com.fm.designstar.utils.SpaceItemDecoration;
import com.fm.designstar.utils.ToastUtil;
import com.fm.designstar.utils.Tool;
import com.fm.designstar.utils.Util;
import com.fm.designstar.views.Detail.activity.DTDetailsActivity;
import com.fm.designstar.views.Detail.activity.VedioPlayActivity;
import com.fm.designstar.views.Detail.contract.DeleteContract;
import com.fm.designstar.views.Detail.contract.LikeContract;
import com.fm.designstar.views.Detail.presenter.DeletePresenter;
import com.fm.designstar.views.Detail.presenter.LikePresenter;
import com.fm.designstar.views.main.adapter.HomeGuanzhuAdapter;
import com.fm.designstar.views.main.contract.HomeFindContract;
import com.fm.designstar.views.main.contract.HomeGuanzhuContract;
import com.fm.designstar.views.main.presenter.HomeFindPresenter;
import com.fm.designstar.views.main.presenter.HomeGuanzhuPresenter;
import com.fm.designstar.widget.recycler.BaseRecyclerAdapter;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
//import cn.jzvd.JZVideoPlayerStandard;
import cn.jzvd.JZVideoPlayerManager;
import cn.jzvd.JZVideoPlayerStandard;



public class HomeGuanzhuFragment extends   BaseFragment<HomeGuanzhuPresenter> implements HomeGuanzhuContract.View ,HomeFindContract.View , LikeContract.View ,XRecyclerView.LoadingListener, DeleteContract.View {

    private List<String> urls=new ArrayList<>();

    private HomeGuanzhuAdapter guanzhuAdapter;
    @BindView(R.id.home_recy)
    XRecyclerView hotRecycler;
    @BindView(R.id.nodada)
    ImageView imageView;
    @BindView(R.id.tv_noda)
    TextView tv_noda;
    private int pagenum=1;
    private HomeFindPresenter homeFindPresenter;
    private LikePresenter likePresenter;
    private int like=0,islike,type=1,index;
    HomeFindBean findBean;

    private LinearLayoutManager mLayoutManager;
    JZVideoPlayerStandard jzvd;
    private DeletePresenter deletePresenter;

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
        deletePresenter=new DeletePresenter();
        deletePresenter.init(this);
    }

    @Override
    public void loadData() {
        if(!EventBus.getDefault().isRegistered(this)){//加上判断
            EventBus.getDefault().register(this);
        }
        hotRecycler.setPullRefreshEnabled(true);
        hotRecycler.setLoadingMoreEnabled(true);
        hotRecycler.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        hotRecycler.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotate);
        mLayoutManager = new LinearLayoutManager(mContext);

        hotRecycler.setLayoutManager(mLayoutManager);
        hotRecycler.addItemDecoration(new SpaceItemDecoration().setBottom(Tool.dip2px(mContext, 5)));

        guanzhuAdapter=new HomeGuanzhuAdapter();
        guanzhuAdapter.setScreenWidth(Util.getScreenWidth(mContext), getResources().getDisplayMetrics().density);
        //4)实现 下拉刷新和加载更多 接口
        hotRecycler.setLoadingListener(this);
        hotRecycler.setAdapter(guanzhuAdapter);


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

           @Override
           public void onmoreClick(long id) {
               showdeleteDialog(id);


           }

           @Override
           public void onjubaoClick(long id) {
               showjubaoDialog(id);
           }
       });
       guanzhuAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClick() {
           @Override
           public void onItemClick(View view, int position) {
               findBean = guanzhuAdapter.getData().get(position-1);

               if (guanzhuAdapter.getData().get(position-1).getMediaType()==2){
                   Bundle bundle = new Bundle();
                   bundle.putSerializable("info", guanzhuAdapter.getData().get(position-1));
                   startActivity(VedioPlayActivity.class, bundle);

               }else {
                   index=position-1;
                   Bundle bundle = new Bundle();
                   bundle.putSerializable("info", guanzhuAdapter.getData().get(position-1));
                   startActivity(DTDetailsActivity.class, bundle);
               }
           }
       });
        hotRecycler.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(View view) {

            }

            @Override
            public void onChildViewDetachedFromWindow(View view) {
                 jzvd = view.findViewById(R.id.video_player);
                if (jzvd != null) {
                    if (JZVideoPlayerManager.getCurrentJzvd()!= null ) {
                        jzvd.releaseAllVideos();
                    }
                }
            }
        });

        hotRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    Log.e("qsd","滑动播放");
                    AutoPlayUtils.onScrollPlayVideo(recyclerView, R.id.video_player, mLayoutManager.findFirstVisibleItemPosition(), mLayoutManager.findLastVisibleItemPosition());
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy != 0) {
                    Log.e("qsd","滑动停止播放");
                    AutoPlayUtils.onScrollReleaseAllVideos(mLayoutManager.findFirstVisibleItemPosition(), mLayoutManager.findLastVisibleItemPosition(), 0.2f);
                }
            }
        });


    }

    private void showjubaoDialog(long id) {
        ActionSheetDialog dialog = new ActionSheetDialog(mActivity).builder();

        dialog.addSheetItem(R.string.jubao_review, ActionSheetDialog.SheetItemColor.Red, new ActionSheetDialog.OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {

            }
        });


        dialog.show();
    }

    private void showdeleteDialog(long id) {
        ActionSheetDialog dialog = new ActionSheetDialog(mActivity).builder();

        dialog.addSheetItem(R.string.delete_review, ActionSheetDialog.SheetItemColor.Red, new ActionSheetDialog.OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                deletePresenter.Delete(id);
            }
        });


        dialog.show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(HomeEvent event) {
        AudioManager audioManager = (AudioManager)mContext.getSystemService(Context.AUDIO_SERVICE);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, AudioManager.FLAG_PLAY_SOUND);
        type=event.getTAG();
       if (event.getTAG()==1){
           guanzhuAdapter.clearData();

           mPresenter.HomeGuanzhu(pagenum,10);
       } else if (event.getTAG()==3){

       }
       else {
           guanzhuAdapter.clearData();
           homeFindPresenter.HomeFind(pagenum,10);
       }

    }

    @Override
    public void HomeGuanzhuSuccess(HomeFindResponse homeFindResponse) {
        AutoPlayUtils.onScrollReleaseAllVideos(mLayoutManager.findFirstVisibleItemPosition(), mLayoutManager.findLastVisibleItemPosition(), 0.2f);

        if (pagenum==1){
            guanzhuAdapter.clearData();
        }
        if (homeFindResponse.getResult()==null){
            imageView.setVisibility(View.VISIBLE);
            hotRecycler.setVisibility(View.GONE);
            tv_noda.setVisibility(View.VISIBLE);
        }else {
            if (homeFindResponse.getResult().size()>0){
                imageView.setVisibility(View.GONE);
                tv_noda.setVisibility(View.GONE);

                hotRecycler.setVisibility(View.VISIBLE);
                guanzhuAdapter.addData(homeFindResponse.getResult());
            }else {
                if (pagenum==1){
                    imageView.setVisibility(View.VISIBLE);
                    tv_noda.setVisibility(View.VISIBLE);

                    hotRecycler.setVisibility(View.GONE);
                }

            }

        }
    }

    @Override
    public void showLoading(String content, int code) {
      //  App.loadingDefault(mActivity);

    }

    @Override
    public void stopLoading(int code) {
        hotRecycler.refreshComplete(); //下拉刷新完成
        hotRecycler.loadMoreComplete();
    }

    @Override
    public void showErrorMsg(String msg, int code) {

        ToastUtil.showToast(msg);
        hotRecycler.refreshComplete(); //下拉刷新完成
        hotRecycler.loadMoreComplete();
    }

    @Override
    public void HomeFindSuccess(HomeFindResponse homeFindResponse) {
        if (pagenum==1){
            guanzhuAdapter.clearData();
        }
        if (homeFindResponse.getResult()==null){
            if (pagenum==1){
                imageView.setVisibility(View.VISIBLE);
                tv_noda.setVisibility(View.VISIBLE);

                hotRecycler.setVisibility(View.GONE);
            }
        }else {

            if (homeFindResponse.getResult().size()>0){
                imageView.setVisibility(View.GONE);
                tv_noda.setVisibility(View.GONE);

                hotRecycler.setVisibility(View.VISIBLE);
                guanzhuAdapter.addData(homeFindResponse.getResult());
            }else {
                if (pagenum==1){
                    imageView.setVisibility(View.VISIBLE);
                    tv_noda.setVisibility(View.VISIBLE);

                    hotRecycler.setVisibility(View.GONE);
                }
            }

        }
    }

    @Override
    public void LikeSuccess(LikeResponse likeResponse) {
        Log.e("qsd",islike+"==="+like);
         findBean .setLikes(likeResponse.getLikes());
        findBean.setIsLike(islike);
        guanzhuAdapter.notifyItemChanged(like+1);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(UploadzpEvent event) {
            homeFindPresenter.HomeFind(pagenum,10);


    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(GetCommetsEvent event) {

        String id = event.getId();
        for (int i=0;i<guanzhuAdapter.getData().size();i++){
            if (String.valueOf(guanzhuAdapter.getData().get(i).getMomentId()).equals(id)){
                guanzhuAdapter.getData().get(i).setComments(event.getNum());
                guanzhuAdapter.notifyItemChanged(i+1);

            }
        }



    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(DeleteMonEvent event) {
Log.e("qsd","getTAG"+event.getTAG());
        for (int i = 0; i < guanzhuAdapter.getData().size(); i++) {
            HomeFindBean bean = guanzhuAdapter.getData().get(i);
            if (String.valueOf(bean.getMomentId()).equals(String.valueOf(event.getTAG()))) {
                Log.e("qsd","getTAG"+event.getTAG()+"===="+i);

                hotRecycler.removeViewAt(i+1);
                guanzhuAdapter.getData().remove(i);

                guanzhuAdapter.notifyItemRemoved(i+1);

            }

        }



    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(GetLikesEvent event) {
        findBean .setLikes(event.getName());
        if (event.isSelect()){
            findBean.setIsLike(1);
        }else {
            findBean.setIsLike(0);

        }
        Log.e("qsd","event"+event.getName()+"=="+event.isSelect()+"=="+index);
        guanzhuAdapter.notifyItemChanged(index+1);


    }

    @Override
    public void onRefresh() {
        pagenum=1;
        if (type==1){
            mPresenter.HomeGuanzhu(pagenum,10);
        }else {
            homeFindPresenter.HomeFind(pagenum,10);
        }

    }

    @Override
    public void onLoadMore() {
        pagenum++;
        if (type==1){
            mPresenter.HomeGuanzhu(pagenum,10);
        }else {
            homeFindPresenter.HomeFind(pagenum,10);
        }
    }



    @Override
    public void onPause() {
        super.onPause();
        JZVideoPlayerStandard.releaseAllVideos();
    }

    @Override
    public void DeleteSuccess(long id) {
        ToastUtil.showToast("删除成功");

        for (int i = 0; i < guanzhuAdapter.getData().size(); i++) {
            HomeFindBean bean = guanzhuAdapter.getData().get(i);
                if (String.valueOf(bean.getMomentId()).equals(String.valueOf(id))) {
                    hotRecycler.removeViewAt(i+1);
                    guanzhuAdapter.getData().remove(i);

                    guanzhuAdapter.notifyItemRemoved(i+1);


                }

        }


    }
}