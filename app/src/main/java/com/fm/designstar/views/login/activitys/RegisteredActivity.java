package com.fm.designstar.views.login.activitys;

import butterknife.BindView;
import butterknife.OnClick;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.fm.designstar.R;
import com.fm.designstar.base.BaseActivity;
import com.fm.designstar.utils.TextViewUtil;
import com.fm.designstar.utils.Tool;

public class RegisteredActivity extends BaseActivity {
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.next)
    TextView next;
    @BindView(R.id.reg_notice)
    TextView reg_notice; @BindView(R.id.login_top)
    TextView login_top;


    @Override
    public int getLayoutId() {
        return R.layout.activity_registered;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void loadData() {
      int roade=  getIntent().getIntExtra("Rode",0);
      if (roade==1){
          login_top.setText(R.string.wel_regi);
      }else {
          login_top.setText(R.string.complete_phone);
      }

        int[] strt={13,22};
        int[] end={21,reg_notice.getText().length()};
        TextViewUtil.setPartialColors(reg_notice,reg_notice.getText().toString(),strt,end,R.color.notice);
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
                startActivity(MsgCodeActivity.class);
                break;

            default:
                break;
        }
    }
}