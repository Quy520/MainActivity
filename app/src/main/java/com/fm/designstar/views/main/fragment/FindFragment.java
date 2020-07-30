package com.fm.designstar.views.main.fragment;

import android.util.Log;

import com.fm.designstar.R;
import com.fm.designstar.base.BaseFragment;
import com.fm.designstar.model.server.response.HomeFindResponse;
import com.fm.designstar.views.main.contract.HomeFindContract;
import com.fm.designstar.views.main.contract.HomeGuanzhuContract;
import com.fm.designstar.views.main.presenter.HomeFindPresenter;


public class FindFragment extends BaseFragment<HomeFindPresenter> implements HomeFindContract.View , HomeGuanzhuContract.View {


    private int pagenum=1;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_find;
    }

    @Override
    public void initPresenter() {
        mPresenter.init(this);

    }

    @Override
    public void loadData() {
        Log.e("qsd","fragment_find");


    }

    @Override
    public void HomeFindSuccess(HomeFindResponse homeFindResponse) {

    }

    @Override
    public void showLoading(String content, int code) {

    }

    @Override
    public void stopLoading(int code) {

    }

    @Override
    public void showErrorMsg(String msg, int code) {

    }

    @Override
    public void HomeGuanzhuSuccess(HomeFindResponse homeFindResponse) {

    }
}