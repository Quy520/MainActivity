package com.fm.designstar.views.main.fragment;

import android.util.Log;

import com.fm.designstar.R;
import com.fm.designstar.base.BaseFragment;
import com.fm.designstar.utils.SpaceItemDecoration;
import com.fm.designstar.utils.Tool;
import com.fm.designstar.utils.Util;
import com.fm.designstar.views.main.adapter.HomeGuanzhuAdapter;
import com.fm.designstar.views.main.adapter.HomeRecomAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;


public class guanzhuFragment extends BaseFragment {

    private List<String> urls=new ArrayList<>();

    private HomeGuanzhuAdapter guanzhuAdapter;
    @BindView(R.id.home_recy)
    RecyclerView hotRecycler;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_guanzhu;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void loadData() {
        urls.add("https://ss1.baidu.com/6ON1bjeh1BF3odCf/it/u=2400807498,1022710002&fm=15&gp=0.jpg");
        urls.add("https://ss1.baidu.com/6ON1bjeh1BF3odCf/it/u=3848182578,3212131776&fm=15&gp=0.jpg");
        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1595928179792&di=64dfa2fcbaed252126d9182ae67e053c&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20171107%2F62a8b9b47e6f41708416c4eb5f44fc6a.jpeg");
        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1595928179787&di=578959ca7cecfc13376805894ff6e52d&imgtype=0&src=http%3A%2F%2Finews.gtimg.com%2Fnewsapp_match%2F0%2F5377154223%2F0");
        hotRecycler.setLayoutManager(new LinearLayoutManager(mContext));
        hotRecycler.addItemDecoration(new SpaceItemDecoration().setBottom(Tool.dip2px(mContext, 5)));
        hotRecycler.setNestedScrollingEnabled(false);
        guanzhuAdapter=new HomeGuanzhuAdapter();
        guanzhuAdapter.setScreenWidth(Util.getScreenWidth(mContext), getResources().getDisplayMetrics().density);

        hotRecycler.setAdapter(guanzhuAdapter);
        hotRecycler.setHasFixedSize(true);
        hotRecycler.setFocusable(false);
        guanzhuAdapter.addData(urls);
    }
}