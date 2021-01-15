package com.fm.designstar.views.login.activitys;

import butterknife.BindView;
import butterknife.OnClick;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.fm.designstar.R;
import com.fm.designstar.app.App;
import com.fm.designstar.base.BaseActivity;
import com.fm.designstar.utils.StringUtil;
import com.fm.designstar.utils.TextViewUtil;
import com.fm.designstar.utils.ToastUtil;
import com.fm.designstar.utils.Tool;
import com.fm.designstar.views.login.contract.SendMsgContract;
import com.fm.designstar.views.login.presenter.SendMsgPresenter;

public class RegisteredActivity extends BaseActivity<SendMsgPresenter>  implements SendMsgContract.View {
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.next)
    TextView next;
    @BindView(R.id.reg_notice)
    TextView reg_notice; @BindView(R.id.login_top)
    TextView login_top;
    private  int roade;

    @Override
    public int getLayoutId() {
        return R.layout.activity_registered;
    }

    @Override
    public void initPresenter() {
        mPresenter.init(this);

    }

    @Override
    public void loadData() {
       roade=  getIntent().getIntExtra("Rode",0);
      if (roade==1){
          login_top.setText(R.string.wel_regi);
      }else {
          login_top.setText(R.string.complete_phone);
      }

        int[] strt={13,22};
        int[] end={21,reg_notice.getText().length()};
        TextViewUtil.setPartialColors(mContext,reg_notice,reg_notice.getText().toString(),R.color.transparent);

        phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length()>0){
                    next.setBackground(getResources().getDrawable(R.drawable.btn_round_click_shape));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @OnClick({R.id.back,R.id.next})
    public void OnClick(View view) {
        if (Tool.isFastDoubleClick()) {
            return;
        }
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.next:
                if (StringUtil.isBlank(phone.getText().toString())) {
                    ToastUtil.showToast(R.string.no_phone);
                    return;
                }
                if (!StringUtil.isMobileNO(phone.getText().toString())) {
                    ToastUtil.showToast(R.string.phone_err);
                    return;
                }
                if (roade==1){
                    mPresenter.SendMsg(phone.getText().toString());
                }else {
                    mPresenter.SendMsgforget(phone.getText().toString());
                }



                break;

            default:
                break;
        }
    }

    @Override
    public void SendMsgSuccess() {
        Bundle bundle = new Bundle();
        bundle.putInt("Rode", roade);
        bundle.putString("phone", phone.getText().toString());
        startActivity(MsgCodeActivity.class,bundle);
    }

    @Override
    public void SendMsgforgetSuccess() {
        Bundle bundle = new Bundle();
        bundle.putInt("Rode", roade);
        bundle.putString("phone", phone.getText().toString());
        startActivity(MsgCodeActivity.class,bundle);
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