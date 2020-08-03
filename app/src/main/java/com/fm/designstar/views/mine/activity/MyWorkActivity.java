package com.fm.designstar.views.mine.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

import android.os.Bundle;

import com.fm.designstar.R;
import com.fm.designstar.base.BaseActivity;
import com.fm.designstar.utils.SpaceItemDecoration;
import com.fm.designstar.utils.Tool;
import com.fm.designstar.views.main.adapter.HomeRecomAdapter;
import com.fm.designstar.views.mine.adapter.BlackListAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyWorkActivity extends BaseActivity {
    @BindView(R.id.recy_works)
    RecyclerView recy_works;
    HomeRecomAdapter blackListAdapter;
    private List<String> urls=new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_work;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void loadData() {
        mTitle.setTitle(R.string.my_zp);
        urls.add("https://ss1.baidu.com/6ON1bjeh1BF3odCf/it/u=2400807498,1022710002&fm=15&gp=0.jpg");
        urls.add("https://ss1.baidu.com/6ON1bjeh1BF3odCf/it/u=3848182578,3212131776&fm=15&gp=0.jpg");
        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1595928179792&di=64dfa2fcbaed252126d9182ae67e053c&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20171107%2F62a8b9b47e6f41708416c4eb5f44fc6a.jpeg");
        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1595928179787&di=578959ca7cecfc13376805894ff6e52d&imgtype=0&src=http%3A%2F%2Finews.gtimg.com%2Fnewsapp_match%2F0%2F5377154223%2F0");
        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1595928179792&di=64dfa2fcbaed252126d9182ae67e053c&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20171107%2F62a8b9b47e6f41708416c4eb5f44fc6a.jpeg");
        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1595928179787&di=578959ca7cecfc13376805894ff6e52d&imgtype=0&src=http%3A%2F%2Finews.gtimg.com%2Fnewsapp_match%2F0%2F5377154223%2F0");
        recy_works.setLayoutManager(new LinearLayoutManager(mContext));
        recy_works.addItemDecoration(new SpaceItemDecoration().setBottom(Tool.dip2px(mContext, 1)));
        recy_works.setNestedScrollingEnabled(false);
        blackListAdapter=new HomeRecomAdapter();
        recy_works.setAdapter(blackListAdapter);
        recy_works.setHasFixedSize(true);
        recy_works.setFocusable(false);
        blackListAdapter.addData(urls);
    }
}