package com.fm.designstar.views.mine.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.fm.designstar.R;
import com.fm.designstar.app.App;
import com.fm.designstar.base.BaseActivity;
import com.fm.designstar.dialog.ActionSheetDialog;
import com.fm.designstar.events.DeleteMonEvent;
import com.fm.designstar.model.bean.HomeFindBean;
import com.fm.designstar.model.server.response.HomeFindResponse;
import com.fm.designstar.model.server.response.LikeResponse;
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
import com.fm.designstar.views.Fabu.contract.UploadContract;
import com.fm.designstar.views.main.adapter.HomeGuanzhuAdapter;
import com.fm.designstar.views.mine.adapter.BlackListAdapter;
import com.fm.designstar.views.mine.contract.UseMomentContract;
import com.fm.designstar.views.mine.presenter.UseMomentPresenter;
import com.fm.designstar.widget.recycler.BaseRecyclerAdapter;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class MyActivitysActivity extends BaseActivity<UseMomentPresenter> implements UseMomentContract.View,XRecyclerView.LoadingListener , LikeContract.View , DeleteContract.View {
    @BindView(R.id.recy_activits)
    XRecyclerView recy_activits;
    HomeGuanzhuAdapter guanzhuAdapter;
    private List<String> urls=new ArrayList<>();
    private int pagenum=1;
    @BindView(R.id.nodada)
    ImageView imageView;
    private boolean hasnext;
    private LikePresenter likePresenter;
    HomeFindBean findBean;
    private int like=0,islike,type=1,index;
    private DeletePresenter deletePresenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_activitys;
    }

    @Override
    public void initPresenter() {
        mPresenter.init(this);
        likePresenter=new LikePresenter();
        likePresenter.init(this);
        deletePresenter=new DeletePresenter();
        deletePresenter.init(this);
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
        if (pagenum==1){
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
        pagenum=1;
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

    @Override
    public void LikeSuccess(LikeResponse likeResponse) {

        findBean .setLikes(likeResponse.getLikes());
        findBean.setIsLike(islike);
        guanzhuAdapter.notifyItemChanged(like+1);
    }

    @Override
    public void DeleteSuccess(long momentId) {
        ToastUtil.showToast("删除成功");
        EventBus.getDefault().removeStickyEvent(DeleteMonEvent.class);
        EventBus.getDefault().post(new DeleteMonEvent(momentId));
       // mPresenter.UseMoment(pagenum,10,1, null,App.getConfig().getUserid());

        for (int i = 0; i < guanzhuAdapter.getData().size(); i++) {
            HomeFindBean bean = guanzhuAdapter.getData().get(i);
            if (String.valueOf(bean.getMomentId()).equals(String.valueOf(momentId))) {
                Log.e("qsd","shanchu"+i+"=="+bean.getMomentId()+"="+momentId);
                recy_activits.removeViewAt(i+1);
                guanzhuAdapter.getData().remove(i);
                guanzhuAdapter.notifyItemRemoved(i+1);

            }
        }
    }
}