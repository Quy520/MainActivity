package com.fm.designstar.views.main.fragment;

import butterknife.BindView;
import butterknife.OnClick;

import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fm.designstar.R;
import com.fm.designstar.app.App;
import com.fm.designstar.base.BaseFragment;
import com.fm.designstar.events.UpdatainfoEvent;
import com.fm.designstar.model.server.response.UserinfoResponse;
import com.fm.designstar.model.server.response.UserlikeResponse;
import com.fm.designstar.utils.StringUtil;
import com.fm.designstar.utils.ToastUtil;
import com.fm.designstar.utils.Tool;
import com.fm.designstar.utils.Util;
import com.fm.designstar.views.mine.activity.BeDesignerActivity;
import com.fm.designstar.views.mine.activity.BlackListActivity;
import com.fm.designstar.views.mine.activity.DesignerMangerActivity;
import com.fm.designstar.views.mine.activity.MyActivitysActivity;
import com.fm.designstar.views.mine.activity.MyWorkActivity;
import com.fm.designstar.views.mine.activity.SettingActivity;
import com.fm.designstar.views.mine.activity.ShDesignerActivity;
import com.fm.designstar.views.mine.contract.GetInfoContract;
import com.fm.designstar.views.mine.presenter.GetInfoPresenter;
import com.fm.designstar.widget.CircleImageView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Type;
import java.util.List;


public class MineFragment extends BaseFragment<GetInfoPresenter> implements GetInfoContract.View {
    @BindView(R.id.re_top)
    RelativeLayout re_top;
    @BindView(R.id.hand)
    CircleImageView hand;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.info)
    TextView info;
    @BindView(R.id.info2)
    TextView info2;
    @BindView(R.id.zp_num)
    TextView zp_num;
    @BindView(R.id.like_num)
    TextView like_num;
    @BindView(R.id.guanzhu_num)
    TextView guanzhu_num;
    @BindView(R.id.fans_num)
    TextView fans_num;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine;
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
        ((ViewGroup.MarginLayoutParams) hand.getLayoutParams()).topMargin = Util.getStatusBarH(mContext)+ Tool.dip2px(mContext, 24);
        ((ViewGroup.MarginLayoutParams) re_top.getLayoutParams()).height =  Tool.dip2px(mContext, 210);
        ((ViewGroup.MarginLayoutParams) name.getLayoutParams()).topMargin = Util.getStatusBarH(mContext)+ Tool.dip2px(mContext, 24);
        if (!StringUtil.isBlank(App.getConfig().getUser_head())){
            Glide.with(mActivity).load(App.getConfig().getUser_head()).error(R.mipmap.defu_hand).into(hand);
        }
        name.setText(App.getConfig().getUser_name());
        info.setText(App.getConfig().getAddress()+"|"+"中国设计星评委导师");
        if (StringUtil.isBlank(App.getConfig().getSingmarks())){
            info2.setText("我的征途是星辰大海");
        }else {

            info2.setText(App.getConfig().getSingmarks());
        }


    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            mPresenter.GetuserLikeInfo();
        }
    }
    @OnClick({R.id.re_zp,R.id.zuopin,R.id.re_dt,R.id.re_guanzhu,R.id.re_fans, R.id.re_designer,R.id.re_shdes,R.id.re_demanger,R.id.re_black,R.id.re_setting})
    public void OnClick(View view) {
        switch (view.getId()) {

            case R.id.zuopin:
                startActivity(MyWorkActivity.class);

                break;
                case R.id.re_guanzhu:
                startActivity(MyWorkActivity.class);

                break;
               case R.id.re_fans:
                startActivity(MyWorkActivity.class);

                break;

            case R.id.re_zp:
                startActivity(MyWorkActivity.class);

                break;

                case R.id.re_dt:
                    startActivity(MyActivitysActivity.class);

                break;

            case R.id.re_designer:
                startActivity(BeDesignerActivity.class);


                break;


             case R.id.re_shdes:
                 startActivity(ShDesignerActivity.class);


                 break;

            case R.id.re_demanger:
                startActivity(DesignerMangerActivity.class);


                break;

            case R.id.re_black:
                startActivity(BlackListActivity.class);


                break;
            case R.id.re_setting:
                startActivity(SettingActivity.class);

                break;

            default:
                break;
        }
    }

    @Override
    public void GetotherLikeInfoSuccess(UserlikeResponse userlikeResponse) {

    }

    @Override
    public void GetuserlikeInfoSuccess(UserlikeResponse userlikeResponse) {
        zp_num.setText(userlikeResponse.getMomentNum()+"");
        like_num.setText(userlikeResponse.getLikeNum()+"");
        guanzhu_num.setText(userlikeResponse.getFollowNum()+"");
        fans_num.setText(userlikeResponse.getFansNum()+"");

    }

    @Override
    public void getOtherUserInfoSuccess(UserinfoResponse response) {

    }

    @Override
    public void showLoading(String content, int code) {

    }

    @Override
    public void stopLoading(int code) {

    }

    @Override
    public void showErrorMsg(String msg, int code) {
        ToastUtil.showToast(msg);

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(UpdatainfoEvent event) {
        if (!StringUtil.isBlank(App.getConfig().getUser_head())){
            Glide.with(mActivity).load(App.getConfig().getUser_head()).error(R.mipmap.defu_hand).into(hand);
        }
        name.setText(App.getConfig().getUser_name());
        info.setText(App.getConfig().getAddress()+"|"+"中国设计星评委导师");
        if (StringUtil.isBlank(App.getConfig().getSingmarks())){
            info2.setText("我的征途是星辰大海");
        }else {

            info2.setText(App.getConfig().getSingmarks());
        }
    }
}