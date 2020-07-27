package com.fm.designstar.views.mine.activity;



import com.fm.designstar.R;
import com.fm.designstar.base.BaseActivity;

public class BeDesignerActivity extends BaseActivity {



    @Override
    public int getLayoutId() {
        return R.layout.activity_be_designer;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void loadData() {
        mTitle.setTitle(R.string.be_designer);

    }
}