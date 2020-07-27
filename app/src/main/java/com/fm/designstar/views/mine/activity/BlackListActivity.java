package com.fm.designstar.views.mine.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.fm.designstar.R;
import com.fm.designstar.base.BaseActivity;

public class BlackListActivity extends BaseActivity {


    @Override
    public int getLayoutId() {
        return R.layout.activity_black_list;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void loadData() {
        mTitle.setTitle(R.string.my_black);

    }
}