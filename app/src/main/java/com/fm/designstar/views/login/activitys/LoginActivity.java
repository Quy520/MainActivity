package com.fm.designstar.views.login.activitys;

import butterknife.BindView;
import butterknife.OnClick;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.fm.designstar.R;
import com.fm.designstar.base.BaseActivity;
import com.fm.designstar.model.server.response.LoginResponse;
import com.fm.designstar.utils.FileUtils;
import com.fm.designstar.utils.FormatUtil;
import com.fm.designstar.utils.SpUtil;
import com.fm.designstar.utils.StringUtil;
import com.fm.designstar.utils.TextViewUtil;
import com.fm.designstar.utils.ToastUtil;
import com.fm.designstar.utils.Tool;
import com.fm.designstar.views.login.contract.LoginContract;
import com.fm.designstar.views.login.presenter.LoginPresenter;

public class LoginActivity extends BaseActivity<LoginPresenter>  implements LoginContract.View {

    @BindView(R.id.phone)
    EditText phone;@BindView(R.id.pwd)
    EditText pwd;
    @BindView(R.id.longin)
    TextView longin;
    @BindView(R.id.long_notice)
    TextView reg_notice;


    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initPresenter() {
        mPresenter.init(this);
    }

    @Override
    public void loadData() {
         int[] strt={13,22};
         int[] end={21,reg_notice.getText().length()};
        TextViewUtil.setPartialColors(reg_notice,reg_notice.getText().toString(),strt,end,R.color.notice);


    }
    @OnClick({R.id.back,R.id.longin, R.id.forgot, R.id.regist, R.id.qq,R.id.wx})
    public void OnClick(View view) {
        if (Tool.isFastDoubleClick()) {
            return;
        }
        switch (view.getId()) {
            case R.id.back:
               finish();
                break;
                case R.id.longin:
                    if (StringUtil.isBlank(phone.getText().toString())) {
                        ToastUtil.showToast(R.string.phone_err);
                        return;
                    }
                    if (StringUtil.isBlank(pwd.getText().toString())) {
                        ToastUtil.showToast(R.string.pwd_err);
                        return;
                    }
                    if (pwd.getText().toString().contains(" ")) {
                        ToastUtil.showToast(R.string.pwd_err);
                        return;
                    }
                    if (pwd.getText().length() < 6 || pwd.getText().length() > 16) {
                        ToastUtil.showToast(R.string.pwd_err2);
                        return;
                    }

                    mPresenter.login(phone.getText().toString(),pwd.getText().toString(), FormatUtil.getIMEI(mContext));
                break;
            case R.id.regist:
                Bundle bundle = new Bundle();
                bundle.putInt("Rode", 1);
                startActivity(RegisteredActivity.class,bundle);
                break;
            case R.id.forgot:
                Bundle bundle2 = new Bundle();
                bundle2.putInt("Rode", 2);
                startActivity(RegisteredActivity.class,bundle2);
                break;
            default:
                break;
        }
    }

    @Override
    public void loginSuccess(LoginResponse userToken) {
        Log.e("qsd","userToken"+userToken.getEmail());
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
}