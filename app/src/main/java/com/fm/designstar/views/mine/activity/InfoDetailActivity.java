package com.fm.designstar.views.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fm.designstar.R;
import com.fm.designstar.app.App;
import com.fm.designstar.base.BaseActivity;
import com.fm.designstar.dialog.ActionSheetDialog;
import com.fm.designstar.dialog.AlertFragmentDialog;
import com.fm.designstar.events.FllowEvent;
import com.fm.designstar.events.messageEvent;
import com.fm.designstar.model.bean.HomeFindBean;
import com.fm.designstar.model.server.response.HomeFindResponse;
import com.fm.designstar.model.server.response.LikeResponse;
import com.fm.designstar.model.server.response.UserinfoResponse;
import com.fm.designstar.model.server.response.UserlikeResponse;
import com.fm.designstar.utils.SpaceItemDecoration;
import com.fm.designstar.utils.StringUtil;
import com.fm.designstar.utils.ToastUtil;
import com.fm.designstar.utils.Tool;
import com.fm.designstar.utils.Util;
import com.fm.designstar.views.Detail.activity.DTDetailsActivity;
import com.fm.designstar.views.Detail.contract.DeleteContract;
import com.fm.designstar.views.Detail.contract.LikeContract;
import com.fm.designstar.views.Detail.presenter.DeletePresenter;
import com.fm.designstar.views.Detail.presenter.LikePresenter;
import com.fm.designstar.views.main.adapter.HomeGuanzhuAdapter;
import com.fm.designstar.views.main.adapter.HomeRecomAdapter;
import com.fm.designstar.views.mine.contract.FirDesignerContract;
import com.fm.designstar.views.mine.contract.GetInfoContract;
import com.fm.designstar.views.mine.contract.UseMomentContract;
import com.fm.designstar.views.mine.contract.followContract;
import com.fm.designstar.views.mine.presenter.FirDesignerPresenter;
import com.fm.designstar.views.mine.presenter.GetInfoPresenter;
import com.fm.designstar.views.mine.presenter.UseMomentPresenter;
import com.fm.designstar.views.mine.presenter.followPresenter;
import com.fm.designstar.widget.CircleImageView;
import com.fm.designstar.widget.recycler.BaseRecyclerAdapter;

import org.greenrobot.eventbus.EventBus;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;
import cn.jzvdother.JzvdStd;
import io.rong.imkit.RongIM;

public class InfoDetailActivity extends BaseActivity<UseMomentPresenter>  implements UseMomentContract.View , GetInfoContract.View, followContract.View, LikeContract.View, FirDesignerContract.View, DeleteContract.View {

private GetInfoPresenter getInfoPresenter;

    @BindView(R.id.my_recy)
    RecyclerView my_recy;

    private int pageNum=1;
    private String uuid;

    @BindView(R.id.zp_num)
    TextView zp_num;
    @BindView(R.id.like_num)
    TextView like_num;
    @BindView(R.id.guanzhu_num)
    TextView guanzhu_num;
    @BindView(R.id.fans_num)
    TextView fans_num;
    @BindView(R.id.hand)
    CircleImageView hand;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.info)
    TextView info;
    @BindView(R.id.info2)
    TextView info2;
    @BindView(R.id.tv_guanzhu)
    TextView tv_guanzhu;
    @BindView(R.id.tv_zp)
    TextView tv_zp;
    @BindView(R.id.tv_dt)
    TextView tv_dt;
    @BindView(R.id.sixin)
    TextView sixin;
    @BindView(R.id.guanzhu)
    LinearLayout guanzhu;
    @BindView(R.id.re_top)
    RelativeLayout re_top;
    @BindView(R.id.re_zp)
    RelativeLayout re_zp;
    @BindView(R.id.re_dt)
    RelativeLayout re_dt;

    @BindView(R.id.im_zp)
    ImageView im_zp;
    @BindView(R.id.im_dt)
    ImageView im_dt;
    private String nikenam="Designer";
    private String city="上海";
    private followPresenter presenter;
    private UserinfoResponse mresponse;
    private FirDesignerPresenter firDesignerPresenter;
    private HomeGuanzhuAdapter guanzhuAdapter;
    private HomeRecomAdapter homeRecomAdapter;
    private LikePresenter likePresenter;
    private int like=0,islike,type=2,lujin;
    HomeFindBean findBean;
    private DeletePresenter deletePresenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_info_detail;
    }

    @Override
    public void initPresenter() {
        mPresenter.init(this);
        getInfoPresenter=new GetInfoPresenter();
        getInfoPresenter.init(this);
        presenter=new followPresenter();
        presenter.init(this);
        likePresenter=new LikePresenter();
        likePresenter.init(this);
        firDesignerPresenter =new FirDesignerPresenter();
        firDesignerPresenter.init(this);
        deletePresenter=new DeletePresenter();
        deletePresenter.init(this);

    }

    @Override
    public void loadData() {
       uuid= getIntent().getStringExtra("UUID");
        lujin= getIntent().getIntExtra("type",0);
        mPresenter.UseMoment(pageNum,100,2,null,uuid);
Log.e("qsd","uuid"+uuid+"=="+Long.parseLong(uuid));
        getInfoPresenter.getOtherUserInfo(uuid);

if (!StringUtil.isBlank(uuid)){
if (uuid.equals(App.getConfig().getUserid())){
    guanzhu.setVisibility(View.GONE);
    re_top.getLayoutParams().height = Tool.dip2px(mContext, 210) ;
    getInfoPresenter.GetuserLikeInfo();
}else {
    guanzhu.setVisibility(View.VISIBLE);
    re_top.getLayoutParams().height = Tool.dip2px(mContext, 250) ;

    getInfoPresenter.GetotherLikeInfo(uuid);
}
}

        my_recy.setLayoutManager(new LinearLayoutManager(mContext));
        my_recy.addItemDecoration(new SpaceItemDecoration().setBottom(Tool.dip2px(mContext, 5)));
        my_recy.setNestedScrollingEnabled(false);
        guanzhuAdapter=new HomeGuanzhuAdapter();
        guanzhuAdapter.setScreenWidth(Util.getScreenWidth(mContext), getResources().getDisplayMetrics().density);
        my_recy.setHasFixedSize(true);
        my_recy.setFocusable(false);


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

                if (guanzhuAdapter.getData().get(position).getMomentType()==1){
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("info", guanzhuAdapter.getData().get(position));
                    startActivity(DTDetailsActivity.class, bundle);
                }

            }
        });



        my_recy.setLayoutManager(new LinearLayoutManager(mContext));
        my_recy.addItemDecoration(new SpaceItemDecoration().setBottom(Tool.dip2px(mContext, 0)));
        my_recy.setNestedScrollingEnabled(false);
        homeRecomAdapter=new HomeRecomAdapter(1);
        my_recy.setAdapter(homeRecomAdapter);
        my_recy.setHasFixedSize(true);
        my_recy.setFocusable(false);


        homeRecomAdapter.setOnClickListener(new HomeRecomAdapter.OnClickListener() {
            @Override
            public void onLikeClick(int position, boolean b, CompoundButton compoundButton) {
like=position;
                findBean = homeRecomAdapter.getData().get(position);

                if (b){
                    if (compoundButton.isPressed()){
                        islike=1;
                       // index=position;
                        likePresenter.Like(1,homeRecomAdapter.getData().get(position).getMomentId());
                    }
                }else {
                    islike=0;
                  //  index=position;
                    likePresenter.Like(1,homeRecomAdapter.getData().get(position).getMomentId());

                }
            }

            @Override
            public void onGuanClick(int position, boolean b, CompoundButton compoundButton) {
                if (mresponse.getStatus() == 1) {
                    presenter.canclefollow(mresponse.getCode() + "");
                } else {
                    presenter.follow(mresponse.getCode() + "");

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

    @OnClick({R.id.sixin,R.id.tv_guanzhu,R.id.re_zp,R.id.re_dt})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.re_zp:
                setItem();
                type=2;
                mPresenter.UseMoment(pageNum,100,2,null,uuid);
                tv_zp.setTextSize(22);
                im_zp.setVisibility(View.VISIBLE);
                my_recy.setAdapter(homeRecomAdapter);
                break;
            case R.id.re_dt:
                setItem();
                type=1;
                mPresenter.UseMoment(pageNum,100,1,null,uuid);
                tv_dt.setTextSize(22);
                im_dt.setVisibility(View.VISIBLE);
                my_recy.setAdapter(guanzhuAdapter);

                break;
            case R.id.sixin:
                if(lujin==1){
                    Intent intent=new Intent(mContext,ChoseDesignerTagsActivity.class);

                    intent.putExtra("uuid",uuid);
                    if (mresponse!=null){
                        intent.putExtra("name",mresponse.getTagInfo().getTagName());
                    }
                    startActivityForResult(intent,1111);
                }else {
                  //跳转到聊天页面 传入对方的id 和 名字
                    RongIM.getInstance().startPrivateChat(mContext, uuid, nikenam);

                }

                break;
            case R.id.tv_guanzhu:
                if(lujin==1){
                    new AlertFragmentDialog.Builder(mActivity)
                            .setContent("确定要解雇设计师吗?" + "\n" + "解雇后设计师要重新提交审核，才能加入到中国设计星。" )
                            .setLeftBtnText(getString(R.string.sheet_dialog_cancel))
                            .setRightBtnText(getString(R.string.cancle_designer))
                            .setRightCallBack(new AlertFragmentDialog.RightClickCallBack() {
                                @Override
                                public void dialogRightBtnClick() {
                                 if (!StringUtil.isBlank(uuid))  {
                                     firDesignerPresenter.FirDesigner(Long.parseLong(uuid));
                                 }
                                }
                            }).build();


                }else {
                    if (mresponse != null) {
                        if (mresponse.getStatus() == 1) {
                            presenter.canclefollow(mresponse.getCode() + "");
                        } else {
                            presenter.follow(mresponse.getCode() + "");

                        }

                    }
                }

                break;
        }
    }
    private void setItem() {
        tv_zp.setTextSize(16);
        im_zp.setVisibility(View.GONE);
        tv_dt.setTextSize(16);
        im_dt.setVisibility(View.GONE);


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        if (data == null) {
            return;
        }
        if (requestCode == 1111) {
         String tags=   data.getStringExtra("resule");
            info.setText(city+"|"+tags);
            mresponse.getTagInfo().setTagName(tags);
            mPresenter.UseMoment(pageNum,100,type,null,uuid);
           // mPresenter.UseMoment(pageNum,100,1,null,uuid);

        }

    }

    @Override
    public void UseMomentSuccess(HomeFindResponse homeFindResponse) {
        if (pageNum==1){
            guanzhuAdapter.clearData();
            homeRecomAdapter.clearData();
        }
        if (type==1){
            guanzhuAdapter.addData(homeFindResponse.getResult());

        }else {
            homeRecomAdapter.addData(homeFindResponse.getResult());
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
    public void GetotherLikeInfoSuccess(UserlikeResponse userlikeResponse) {
        zp_num.setText(userlikeResponse.getMomentNum()+"");
        like_num.setText(userlikeResponse.getLikeNum()+"");
        guanzhu_num.setText(userlikeResponse.getFollowNum()+"");
        fans_num.setText(userlikeResponse.getFansNum()+"");

    }

    @Override
    public void GetuserlikeInfoSuccess(UserlikeResponse userlikeResponse) {
        if (pageNum==1){
            zp_num.setText(userlikeResponse.getMomentNum()+"");
            like_num.setText(userlikeResponse.getLikeNum()+"");
            guanzhu_num.setText(userlikeResponse.getFollowNum()+"");
            fans_num.setText(userlikeResponse.getFansNum()+"");
        }
    }

    @Override
    public void getOtherUserInfoSuccess(UserinfoResponse response) {
        mresponse=response;
            Glide.with(mActivity).load(response.getAvatar()).error(R.mipmap.defu_hand).into(hand);
        name.setText(response.getUserName());
        nikenam=response.getUserName();
        if (!StringUtil.isBlank(response.getAddress())){
            city=response.getAddress();
        }
        if (response.getTagInfo()==null){
            info.setText(city+"|"+"暂未设置标签");

        }else {
            info.setText(city+"|"+response.getTagInfo().getTagName());
        }
        if (StringUtil.isBlank(response.getSignature())){
            info2.setText("我的征途是星辰大海");
        }else {
            info2.setText(response.getSignature());
        }
        if (lujin==1){
            sixin.setText("设置标签");
            tv_guanzhu.setText("解雇");
        }else {
            if (response.getStatus()==0){
                tv_guanzhu.setText("关注");
            }else {
                tv_guanzhu.setText("已关注");

            }
        }



    }

    @Override
    public void followSuccess() {
        tv_guanzhu.setText("已关注");
        ToastUtil.showToast("关注成功");
        mresponse.setStatus(1);
        mPresenter.UseMoment(pageNum,100,2,null,uuid);
        getInfoPresenter.GetotherLikeInfo(uuid);
        EventBus.getDefault().removeStickyEvent(FllowEvent.class);
        EventBus.getDefault().post(new FllowEvent());
    }

    @Override
    public void canclefollowSuccess() {
        ToastUtil.showToast("已取消成功");
        tv_guanzhu.setText("关注");
        mresponse.setStatus(0);
        mPresenter.UseMoment(pageNum,100,2,null,uuid);
        getInfoPresenter.GetotherLikeInfo(uuid);
        EventBus.getDefault().removeStickyEvent(FllowEvent.class);
        EventBus.getDefault().post(new FllowEvent());
    }

    @Override
    public void LikeSuccess(LikeResponse likeResponse) {
        if (type==1){
            findBean .setLikes(likeResponse.getLikes());
            findBean.setIsLike(islike);
            guanzhuAdapter.notifyItemChanged(like);
            getInfoPresenter.GetotherLikeInfo(uuid);
        }else {
            findBean .setLikes(likeResponse.getLikes());
            findBean.setIsLike(islike);
            homeRecomAdapter.notifyItemChanged(like);
            getInfoPresenter.GetotherLikeInfo(uuid);
        }

    }

    @Override
    public void FirignerSuccess() {
        ToastUtil.showToast("解雇成功");

    }
    @Override
    public void onBackPressed() {
        if (JzvdStd.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JzvdStd.releaseAllVideos();
    }

    @Override
    public void DeleteSuccess(long momentId) {
        ToastUtil.showToast("删除成功");
        for (int i = 0; i < guanzhuAdapter.getData().size(); i++) {
            HomeFindBean bean = guanzhuAdapter.getData().get(i);
            if (String.valueOf(bean.getMomentId()).equals(String.valueOf(momentId))) {
                guanzhuAdapter.getData().remove(i);
                guanzhuAdapter.notifyItemRemoved(i);
            }

        }
    }
}