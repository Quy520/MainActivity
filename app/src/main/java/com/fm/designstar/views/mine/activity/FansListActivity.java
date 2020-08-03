package com.fm.designstar.views.mine.activity;

import com.fm.designstar.R;
import com.fm.designstar.base.BaseActivity;
import com.fm.designstar.model.server.response.FansResponse;
import com.fm.designstar.utils.SpaceItemDecoration;
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
        urls.add("https://ss1.baidu.com/6ON1bjeh1BF3odCf/it/u=2400807498,1022710002&fm=15&gp=0.jpg");
        urls.add("https://ss1.baidu.com/6ON1bjeh1BF3odCf/it/u=3848182578,3212131776&fm=15&gp=0.jpg");
        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1595928179792&di=64dfa2fcbaed252126d9182ae67e053c&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20171107%2F62a8b9b47e6f41708416c4eb5f44fc6a.jpeg");
        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1595928179787&di=578959ca7cecfc13376805894ff6e52d&imgtype=0&src=http%3A%2F%2Finews.gtimg.com%2Fnewsapp_match%2F0%2F5377154223%2F0");
        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1595928179792&di=64dfa2fcbaed252126d9182ae67e053c&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20171107%2F62a8b9b47e6f41708416c4eb5f44fc6a.jpeg");
        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1595928179787&di=578959ca7cecfc13376805894ff6e52d&imgtype=0&src=http%3A%2F%2Finews.gtimg.com%2Fnewsapp_match%2F0%2F5377154223%2F0");
        recy_black.setLayoutManager(new LinearLayoutManager(mContext));
        recy_black.addItemDecoration(new SpaceItemDecoration().setBottom(Tool.dip2px(mContext, 1)));
        recy_black.setNestedScrollingEnabled(false);
        fansListAdapter=new FansListAdapter();
        recy_black.setAdapter(fansListAdapter);
        recy_black.setHasFixedSize(true);
        recy_black.setFocusable(false);
        fansListAdapter.addData(urls);

    }

    @Override
    public void FansListSuccess(FansResponse Response) {

    }

    @Override
    public void GuanzhuListSuccess(FansResponse Response) {

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
}