package com.fm.designstar.views.mine.activity;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.fm.designstar.R;
import com.fm.designstar.base.BaseActivity;
import com.fm.designstar.utils.StringUtil;

public class ChangInfoActivity extends BaseActivity {

    private int type;
    @BindView(R.id.info)
    EditText editText;

    @Override
    public int getLayoutId() {
        return R.layout.activity_chang_info;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void loadData() {
        mTitle.setRightTitle("保存", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (StringUtil.isBlank(editText.getText().toString())){
                    return;
                }
                Intent intent = new Intent();
                intent.putExtra("SELECT_RESULT", editText.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        mTitle.setRightTitleColor(R.color.notice);
        type=getIntent().getIntExtra("type",0);
        if (type==2){
            mTitle.setTitle(R.string.chang_name);
            editText.setHint("请输入姓名");
        }else {
            mTitle.setTitle(R.string.change_sin);
            editText.setHint("请输入签名");
        }

    }
}