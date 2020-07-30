package com.fm.designstar.views.mine.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.fm.designstar.R;
import com.fm.designstar.base.BaseActivity;
import com.fm.designstar.model.server.response.UserinfoResponse;
import com.fm.designstar.model.server.response.UserlikeResponse;
import com.fm.designstar.views.mine.contract.GetInfoContract;
import com.fm.designstar.views.mine.contract.UseMomentContract;
import com.fm.designstar.views.mine.presenter.GetInfoPresenter;
import com.fm.designstar.views.mine.presenter.UseMomentPresenter;

import androidx.core.os.UserManagerCompat;
import butterknife.BindView;
import butterknife.OnClick;
import io.rong.imkit.RongIM;

public class InfoDetailActivity extends BaseActivity<UseMomentPresenter>  implements UseMomentContract.View , GetInfoContract.View{

private GetInfoPresenter getInfoPresenter;
    @BindView(R.id.sixin)
    TextView textView;



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



    }

    @OnClick({R.id.sixin})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.sixin:
                //跳转到聊天页面 传入对方的id 和 名字
                RongIM.getInstance().startPrivateChat(mContext, "1278287241650765824", "test");

                break;
        }
    }

    @Override
    public void UseMomentSuccess() {

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

    }
}