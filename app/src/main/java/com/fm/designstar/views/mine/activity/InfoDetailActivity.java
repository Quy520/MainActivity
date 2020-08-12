package com.fm.designstar.views.mine.activity;

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
import com.fm.designstar.views.Detail.contract.LikeContract;
import com.fm.designstar.views.Detail.presenter.LikePresenter;
import com.fm.designstar.views.main.adapter.HomeGuanzhuAdapter;
import com.fm.designstar.views.main.adapter.HomeRecomAdapter;
import com.fm.designstar.views.mine.contract.GetInfoContract;
import com.fm.designstar.views.mine.contract.UseMomentContract;
import com.fm.designstar.views.mine.contract.followContract;
import com.fm.designstar.views.mine.presenter.GetInfoPresenter;
import com.fm.designstar.views.mine.presenter.UseMomentPresenter;
import com.fm.designstar.views.mine.presenter.followPresenter;
import com.fm.designstar.widget.CircleImageView;
import com.fm.designstar.widget.recycler.BaseRecyclerAdapter;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;
import io.rong.imkit.RongIM;

public class InfoDetailActivity extends BaseActivity<UseMomentPresenter>  implements UseMomentContract.View , GetInfoContract.View, followContract.View, LikeContract.View {

private GetInfoPresenter getInfoPresenter;
    @BindView(R.id.sixin)
    TextView textView;
    @BindView(R.id.my_recy)
    RecyclerView my_recy;

    private int pageNum=0;
    private String uuid;


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
    private HomeGuanzhuAdapter guanzhuAdapter;
    private HomeRecomAdapter homeRecomAdapter;
    private LikePresenter likePresenter;
    private int like=0,islike,type=2;
    HomeFindBean findBean;
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

    }

    @Override
    public void loadData() {
       uuid= getIntent().getStringExtra("UUID");
        Log.e("qsd",uuid+"uuid");
        mPresenter.UseMoment(pageNum,10,2,null,uuid);

        getInfoPresenter.getOtherUserInfo(uuid);

if (!StringUtil.isBlank(uuid)){
if (uuid.equals(App.getConfig().getUserid())){
    guanzhu.setVisibility(View.GONE);
    re_top.getLayoutParams().height = Tool.dip2px(mContext, 210) ;

}else {
    guanzhu.setVisibility(View.VISIBLE);
    re_top.getLayoutParams().height = Tool.dip2px(mContext, 250) ;

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
        });
        guanzhuAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClick() {
            @Override
            public void onItemClick(View view, int position) {
                ToastUtil.showToast("qsd"+position);
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
        homeRecomAdapter=new HomeRecomAdapter();
        my_recy.setAdapter(homeRecomAdapter);
        my_recy.setHasFixedSize(true);
        my_recy.setFocusable(false);




    }

    @OnClick({R.id.sixin,R.id.tv_guanzhu,R.id.re_zp,R.id.re_dt})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.re_zp:
                setItem();
                type=2;
                mPresenter.UseMoment(pageNum,10,2,null,uuid);
                tv_zp.setTextSize(22);
                im_zp.setVisibility(View.VISIBLE);
                my_recy.setAdapter(homeRecomAdapter);
                break;
            case R.id.re_dt:
                setItem();
                type=1;
                mPresenter.UseMoment(pageNum,10,1,null,uuid);
                tv_dt.setTextSize(22);
                im_dt.setVisibility(View.VISIBLE);
                my_recy.setAdapter(guanzhuAdapter);

                break;
            case R.id.sixin:
                //跳转到聊天页面 传入对方的id 和 名字
                RongIM.getInstance().startPrivateChat(mContext, uuid, nikenam);

                break;
            case R.id.tv_guanzhu:
                if (mresponse!=null){
                    if (mresponse.getStatus()==1){
                        presenter.canclefollow(mresponse.getCode()+"");
                    }else {
                        presenter.follow(mresponse.getCode()+"");

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
    public void UseMomentSuccess(HomeFindResponse homeFindResponse) {
        if (pageNum==0){
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


    }

    @Override
    public void GetuserlikeInfoSuccess(UserlikeResponse userlikeResponse) {
        if (pageNum==0){

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
        info.setText(city+"|"+"中国设计星评委导师");
        if (StringUtil.isBlank(response.getSignature())){
            info2.setText("我的征途是星辰大海");
        }else {
            info2.setText(response.getSignature());
        }

        if (response.getStatus()==0){
            tv_guanzhu.setText("关注");
        }else {
            tv_guanzhu.setText("已关注");

        }

    }

    @Override
    public void followSuccess() {
        tv_guanzhu.setText("已关注");
        ToastUtil.showToast("关注成功");
        mresponse.setStatus(1);

    }

    @Override
    public void canclefollowSuccess() {
        ToastUtil.showToast("已取消成功");
        tv_guanzhu.setText("关注");
        mresponse.setStatus(0);

    }

    @Override
    public void LikeSuccess(LikeResponse likeResponse) {
        findBean .setLikes(likeResponse.getLikes());
        findBean.setIsLike(islike);
        guanzhuAdapter.notifyItemChanged(like);
    }
}