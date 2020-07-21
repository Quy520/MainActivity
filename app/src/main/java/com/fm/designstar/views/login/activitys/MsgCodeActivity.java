package com.fm.designstar.views.login.activitys;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.fm.designstar.R;
import com.fm.designstar.base.BaseActivity;
import com.fm.designstar.utils.ToastUtil;
import com.fm.designstar.widget.VerificationCodeView;

public class MsgCodeActivity extends BaseActivity {
@BindView(R.id.verificationCodeView)

    VerificationCodeView verificationCodeView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_msg_code;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void loadData() {
        verificationCodeView.setOnVerificationCodeCompleteListener(new VerificationCodeView.OnVerificationCodeCompleteListener() {
            @Override
            public void verificationCodeComplete(String verificationCode) {

                Log.e("qsd,",verificationCode);
            }

            @Override
            public void verificationCodeIncomplete(String verificationCode) {
            }
        });

    }
}