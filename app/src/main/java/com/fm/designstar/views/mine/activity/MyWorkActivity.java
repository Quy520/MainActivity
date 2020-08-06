package com.fm.designstar.views.mine.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.fm.designstar.R;
import com.fm.designstar.app.App;
import com.fm.designstar.base.BaseActivity;
import com.fm.designstar.model.server.response.HomeFindResponse;
import com.fm.designstar.utils.SpaceItemDecoration;
import com.fm.designstar.utils.ToastUtil;
import com.fm.designstar.utils.Tool;
import com.fm.designstar.views.main.adapter.HomeRecomAdapter;
import com.fm.designstar.views.mine.adapter.BlackListAdapter;
import com.fm.designstar.views.mine.contract.UseMomentContract;
import com.fm.designstar.views.mine.presenter.UseMomentPresenter;

import java.util.ArrayList;
import java.util.List;

public class MyWorkActivity extends BaseActivity<UseMomentPresenter> implements UseMomentContract.View {
    @BindView(R.id.recy_works)
    RecyclerView recy_works;
    HomeRecomAdapter homeRecomAdapter;
    private List<String> urls=new ArrayList<>();
    private int pagenum=0;
    @BindView(R.id.nodada)
    ImageView imageView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_work;
    }

    @Override
    public void initPresenter() {
        mPresenter.init(this);

    }

    @Override
    public void loadData() {
        mTitle.setTitle(R.string.my_zp);
        mPresenter.UseMoment(pagenum,10,2, App.getConfig().getUserid());
               recy_works.setLayoutManager(new LinearLayoutManager(mContext));
        recy_works.addItemDecoration(new SpaceItemDecoration().setBottom(Tool.dip2px(mContext, 1)));
        recy_works.setNestedScrollingEnabled(false);
        homeRecomAdapter=new HomeRecomAdapter();
        recy_works.setAdapter(homeRecomAdapter);
        recy_works.setHasFixedSize(true);
        recy_works.setFocusable(false);

    }

    @Override
    public void UseMomentSuccess(HomeFindResponse findResponse ) {
        if (pagenum==0){
            homeRecomAdapter.clearData();
        }
        if (findResponse.getResult()==null){
            imageView.setVisibility(View.VISIBLE);
            recy_works.setVisibility(View.GONE);
        }else {
            if (findResponse.getResult().size()>0){
                imageView.setVisibility(View.GONE);
                recy_works.setVisibility(View.VISIBLE);
                homeRecomAdapter.addData(findResponse.getResult());
            }else {
                imageView.setVisibility(View.VISIBLE);
                recy_works.setVisibility(View.GONE);
            }

        }

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