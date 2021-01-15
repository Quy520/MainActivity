package com.fm.designstar.views.main.activity;

import android.graphics.Rect;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;


import com.fm.designstar.R;
import com.fm.designstar.base.BaseActivity;
import com.fm.designstar.utils.AndroidBug5497Workaround;
import com.fm.designstar.utils.StringUtil;
import com.fm.designstar.utils.ToastUtil;
import com.fm.designstar.utils.Tool;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/13 14:24
 * @update : 2018/9/13
 */
public class FeedbackActivity extends BaseActivity {
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    @BindView(R.id.editText)
    EditText editText;

    @BindView(R.id.next)
    TextView next;


    @Override
    public int getLayoutId() {
        return R.layout.act_my_feedback;
    }

    @Override
    public void initPresenter() {
        AndroidBug5497Workaround.assistActivity(mActivity);

    }

    @Override
    public void loadData() {
        mTitle.setTitle(R.string.feedback);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
              //  tvSize.setText(String.valueOf(charSequence.length() + "/240"));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }

    @OnClick(R.id.next)
    public void OnClick(View view) {
        if (Tool.isFastDoubleClick()) {
            return;
        }
        switch (view.getId()) {
            case R.id.next:
                if (StringUtil.isBlankEdit(editText)) {
                    ToastUtil.showToast(getString(R.string.feedback_err));
                    return;
                }
//                if (Util.hasSensitive(mContext,editText.getText().toString())) {
//                    ToastUtil.showToast(getString(R.string.special_characters));
//                    return;
//                }
               /* if (!StringUtil.isMobileNO(phone.getText())) {
                    ToastUtil.showToast(R.string.phone_err);
                    return;
                }*/
               ToastUtil.showToast("提交成功");
               finish();
               // mPresenter.feedback(editText.getText().toString(), phone.getText(),android.os.Build.MODEL,android.os.Build.VERSION.RELEASE);
                break;
            default:
                break;
        }
    }


}
