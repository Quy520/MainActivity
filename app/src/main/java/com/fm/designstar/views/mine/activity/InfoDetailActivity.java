package com.fm.designstar.views.mine.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fm.designstar.R;
import com.fm.designstar.app.App;
import com.fm.designstar.base.BaseActivity;
import com.fm.designstar.model.server.response.HomeFindResponse;
import com.fm.designstar.model.server.response.UserinfoResponse;
import com.fm.designstar.model.server.response.UserlikeResponse;
import com.fm.designstar.utils.StringUtil;
import com.fm.designstar.views.mine.contract.GetInfoContract;
import com.fm.designstar.views.mine.contract.UseMomentContract;
import com.fm.designstar.views.mine.presenter.GetInfoPresenter;
import com.fm.designstar.views.mine.presenter.UseMomentPresenter;
import com.fm.designstar.widget.CircleImageView;

import androidx.core.os.UserManagerCompat;
import butterknife.BindView;
import butterknife.OnClick;
import io.rong.imkit.RongIM;

public class InfoDetailActivity extends BaseActivity<UseMomentPresenter>  implements UseMomentContract.View , GetInfoContract.View{

private GetInfoPresenter getInfoPresenter;
    @BindView(R.id.sixin)
    TextView textView;

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
    private String nikenam="Designer";
    @Override
    public int getLayoutId() {
        return R.layout.activity_info_detail;
    }

    @Override
    public void initPresenter() {
        mPresenter.init(this);
        getInfoPresenter=new GetInfoPresenter();
        getInfoPresenter.init(this);

    }

    @Override
    public void loadData() {
       uuid= getIntent().getStringExtra("UUID");

        mPresenter.UseMoment(pageNum,10,1,uuid);

        getInfoPresenter.getOtherUserInfo(uuid);



    }

    @OnClick({R.id.sixin})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.sixin:
                //跳转到聊天页面 传入对方的id 和 名字
                RongIM.getInstance().startPrivateChat(mContext, uuid, nikenam);

                break;
        }
    }

    @Override
    public void UseMomentSuccess(HomeFindResponse homeFindResponse) {

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
    public void GetotherLikeInfoSuccess(UserlikeResponse userlikeResponse) {

    }

    @Override
    public void GetuserlikeInfoSuccess(UserlikeResponse userlikeResponse) {

    }

    @Override
    public void getOtherUserInfoSuccess(UserinfoResponse response) {
            Glide.with(mActivity).load(response.getAvatar()).error(R.mipmap.defu_hand).into(hand);
        name.setText(response.getUserName());
        nikenam=response.getUserName();
        info.setText(response.getAddress()+"|"+"中国设计星评委导师");
        if (StringUtil.isBlank(response.getCertificationMark())){
            info2.setText("我的征途是星辰大海");
        }else {
            info2.setText(response.getCertificationMark());
        }

    }
}