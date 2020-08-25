package com.fm.designstar.views.mine.activity;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.fm.designstar.R;
import com.fm.designstar.app.App;
import com.fm.designstar.base.BaseActivity;
import com.fm.designstar.events.UpdataEvent;
import com.fm.designstar.utils.StringUtil;
import com.fm.designstar.utils.ToastUtil;
import com.fm.designstar.views.mine.contract.AddTagsContract;
import com.fm.designstar.views.mine.presenter.AddTagsPresenter;

import org.greenrobot.eventbus.EventBus;

public class ChangInfoActivity extends BaseActivity <AddTagsPresenter> implements AddTagsContract.View {

    private int type;
    @BindView(R.id.info)
    EditText editText;

    @Override
    public int getLayoutId() {
        return R.layout.activity_chang_info;
    }

    @Override
    public void initPresenter() {
        mPresenter.init(this);

    }

    @Override
    public void loadData() {
        mTitle.setRightTitle("保存", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (StringUtil.isBlank(editText.getText().toString())){
                    return;
                }
                if (type==3){
                    mPresenter.AddTags(editText.getText().toString(),1);
                }else {
                    Intent intent = new Intent();
                    intent.putExtra("SELECT_RESULT", editText.getText().toString());
                    setResult(RESULT_OK, intent);
                    finish();
                }


            }
        });
        mTitle.setRightTitleColor(R.color.notice);
        type=getIntent().getIntExtra("type",0);
        if (type==2){
            mTitle.setTitle(R.string.chang_name);
            editText.setHint("请输入姓名");
        }else if (type==1){
            mTitle.setTitle(R.string.change_sin);
            editText.setHint("请输入签名");
        }else {
            mTitle.setTitle(R.string.addtags);
            editText.setHint("请输入新增标签");
        }

    }

    @Override
    public void AddTagsSuccess() {
        ToastUtil.showToast("添加成功");
        EventBus.getDefault().removeStickyEvent(UpdataEvent.class);
        EventBus.getDefault().post(new UpdataEvent());
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