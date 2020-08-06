package com.fm.designstar.views.mine.activity;

import com.fm.designstar.R;
import com.fm.designstar.app.App;
import com.fm.designstar.base.BaseActivity;
import com.fm.designstar.model.server.response.FansResponse;
import com.fm.designstar.utils.SpaceItemDecoration;
import com.fm.designstar.utils.ToastUtil;
import com.fm.designstar.utils.Tool;
import com.fm.designstar.views.main.contract.FansContract;
import com.fm.designstar.views.main.presenter.FansPresenter;
import com.fm.designstar.views.mine.adapter.BlackListAdapter;
import com.fm.designstar.views.mine.adapter.FansListAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class FansListActivity extends BaseActivity<FansPresenter>  implements FansContract.View {
    @BindView(R.id.recy_fans)
    RecyclerView recy_black;
    FansListAdapter fansListAdapter;
    private List<String> urls=new ArrayList<>();
    private int type;
    private int pagenum=0;
    private String uuid;

    @Override
    public int getLayoutId() {
        return R.layout.activity_fans_list;
    }

    @Override
    public void initPresenter() {
        mPresenter.init(this);

    }

    @Override
    public void loadData() {
        type=  getIntent().getIntExtra("type",1);
        uuid= getIntent().getStringExtra("UUID");
        if (type==2){
            mTitle.setTitle(R.string.my_fans);
            mPresenter.Fans(pagenum,10,uuid);
        }else {
            mTitle.setTitle(R.string.guanzhu);
            mPresenter.Guanzhu(pagenum,10,uuid);
        }
               recy_black.setLayoutManager(new LinearLayoutManager(mContext));
        recy_black.addItemDecoration(new SpaceItemDecoration().setBottom(Tool.dip2px(mContext, 1)));
        recy_black.setNestedScrollingEnabled(false);
        fansListAdapter=new FansListAdapter();
        recy_black.setAdapter(fansListAdapter);
        recy_black.setHasFixedSize(true);
        recy_black.setFocusable(false);


    }

    @Override
    public void FansListSuccess(FansResponse Response) {
        fansListAdapter.addData(Response.getResult());

    }

    @Override
    public void GuanzhuListSuccess(FansResponse Response) {
        fansListAdapter.addData(Response.getResult());

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