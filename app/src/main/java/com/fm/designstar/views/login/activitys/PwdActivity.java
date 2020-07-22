package com.fm.designstar.views.login.activitys;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.OnClick;

import android.app.ActivityManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.fm.designstar.R;
import com.fm.designstar.app.App;
import com.fm.designstar.app.AppManager;
import com.fm.designstar.base.BaseActivity;
import com.fm.designstar.utils.StringUtil;
import com.fm.designstar.utils.TextViewUtil;
import com.fm.designstar.utils.ToastUtil;
import com.fm.designstar.utils.Tool;
import com.fm.designstar.views.MainActivity;
import com.fm.designstar.views.login.contract.ChangePwdContract;
import com.fm.designstar.views.login.presenter.ChangePwdPresenter;

public class PwdActivity extends BaseActivity<ChangePwdPresenter> implements ChangePwdContract.View {
    @BindView(R.id.pwd_top)
    TextView pwd_top;
    @BindView(R.id.long_notice)
    TextView reg_notice;
    @BindView(R.id.sure)
    TextView sure;
    @BindView(R.id.pwd)
    EditText pwd;
    private  int roade;
    private String phone,code;
    @Override
    public int getLayoutId() {
        return R.layout.activity_pwd;
    }

    @Override
    public void initPresenter() {
        mPresenter.init(this);

    }

    @Override
    public void loadData() {
        phone=getIntent().getStringExtra("phone");
        code=getIntent().getStringExtra("code");
        roade=  getIntent().getIntExtra("Rode",0);
        if (roade==1){
            pwd_top.setText(R.string.com_pwd);
        }else {
            pwd_top.setText(R.string.com_nwe_pwd);
        }
        int[] strt={13,22};
        int[] end={21,reg_notice.getText().length()};
        TextViewUtil.setPartialColors(reg_notice,reg_notice.getText().toString(),strt,end,R.color.notice);
        pwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length()>0&&pwd.getText().length()>0){
                    sure.setBackground(getResources().getDrawable(R.drawable.btn_round_click_shape));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    @OnClick({R.id.back,R.id.sure})
    public void OnClick(View view) {
        if (Tool.isFastDoubleClick()) {
            return;
        }
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.sure:
                if (StringUtil.isBlank(pwd.getText().toString())) {
                    ToastUtil.showToast(R.string.pwd_err);
                    return;
                }
                mPresenter.changePwd(phone,code,pwd.getText().toString());

                break;

            default:
                break;
        }
    }

    @Override
    public void changepwdSuccess() {
        startActivity(LoginActivity.class);
        AppManager.getInstance().finishActivity(RegisteredActivity.class);
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