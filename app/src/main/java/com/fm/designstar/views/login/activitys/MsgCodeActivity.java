package com.fm.designstar.views.login.activitys;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.fm.designstar.R;
import com.fm.designstar.base.BaseActivity;
import com.fm.designstar.utils.ToastUtil;
import com.fm.designstar.widget.VerificationCodeView;

public class MsgCodeActivity extends BaseActivity {
@BindView(R.id.verificationCodeView)

VerificationCodeView verificationCodeView;
private String phone;
@BindView(R.id.phone)
TextView tv_phone;
@BindView(R.id.tv_time)
TextView tv_time;
    private CountDownTimer timer;
    private int roade;
    @Override
    public int getLayoutId() {
        return R.layout.activity_msg_code;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void loadData() {
        phone=getIntent().getStringExtra("phone");
        roade=  getIntent().getIntExtra("Rode",0);

        tv_phone.setText(phone);
        verificationCodeView.setOnVerificationCodeCompleteListener(new VerificationCodeView.OnVerificationCodeCompleteListener() {
            @Override
            public void verificationCodeComplete(String verificationCode) {
                Bundle bundle = new Bundle();
                bundle.putInt("Rode", roade);
                bundle.putString("phone", phone);
                bundle.putString("code", verificationCode);
               startActivity(PwdActivity.class,bundle);
               finish();
            }

            @Override
            public void verificationCodeIncomplete(String verificationCode) {
            }
        });
        timer = new CountDownTimer(300 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tv_time.setText((millisUntilFinished / 1000) + "s");
            }

            @Override
            public void onFinish() {

            }
        };
        timer.start();
    }
}