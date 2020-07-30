package com.fm.designstar.views.login.activitys;

import butterknife.BindView;
import butterknife.OnClick;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.fm.designstar.R;
import com.fm.designstar.app.App;
import com.fm.designstar.base.BaseActivity;
import com.fm.designstar.model.server.response.LoginResponse;
import com.fm.designstar.utils.FormatUtil;
import com.fm.designstar.utils.StringUtil;
import com.fm.designstar.utils.TextViewUtil;
import com.fm.designstar.utils.ToastUtil;
import com.fm.designstar.utils.Tool;
import com.fm.designstar.views.main.activity.MainActivity;
import com.fm.designstar.views.login.contract.LoginContract;
import com.fm.designstar.views.login.presenter.LoginPresenter;

public class LoginActivity extends BaseActivity<LoginPresenter>  implements LoginContract.View {

    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.pwd)
    EditText pwd;
    @BindView(R.id.longin)
    Button longin;
    @BindView(R.id.long_notice)
    TextView reg_notice;
    @BindView(R.id.pwd_open)
    CheckBox pwd_open;


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
        phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length()>10&&pwd.getText().length()>5){
                    longin.setBackground(getResources().getDrawable(R.drawable.btn_round_click_shape));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        pwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length()>5&&phone.getText().length()>10){
                    longin.setBackground(getResources().getDrawable(R.drawable.btn_round_click_shape));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        pwd_open.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (buttonView.isPressed()) {
                        pwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                        pwd .setSelection(pwd.getText().length());
                    }
                    return;
                } else {
                    Log.e("qsd", "top==checkBox取消选中");
                    pwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    pwd .setSelection(pwd.getText().length());
                }


            }});

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
    public void loginSuccess(LoginResponse userInfoResponse) {
        App.getConfig().setUserToken(userInfoResponse.getToken());//token
        App.getConfig().setUser_head(userInfoResponse.getAvatar());//touxiang
        App.getConfig().setUser_name(userInfoResponse.getUserName());//nicheng
        App.getConfig().setUserPhone(userInfoResponse.getMobile());//phone
        App.getConfig().setAddress(userInfoResponse.getAddress());//dizhi
        App.getConfig().setBirthday(userInfoResponse.getBirthday());//shengeri
        App.getConfig().setEmail(userInfoResponse.getEmail());//youdjian
        App.getConfig().setRealName(userInfoResponse.getRealName());//zhengshixingm
        App.getConfig().setRole(userInfoResponse.getRole());
        App.getConfig().setSex(userInfoResponse.getSex());
        App.getConfig().setIsgoHome(userInfoResponse.getCertificationMark());
        App.getConfig().setUserid(userInfoResponse.getCode()+"");
        if (userInfoResponse.getCertificationMark()==0){
            startActivity(ComUserInfoActivity.class);
        }else {
            startActivity(MainActivity.class);
        }
        finish();
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
}