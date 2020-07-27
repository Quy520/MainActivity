package com.fm.designstar.views.mine.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.fm.designstar.R;
import com.fm.designstar.base.BaseActivity;

public class ShDesignerActivity extends BaseActivity {


    @Override
    public int getLayoutId() {
        return R.layout.activity_sh_designer;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void loadData() {
        mTitle.setTitle(R.string.my_shdesigner);
    }
}