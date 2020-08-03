package com.fm.designstar.views.main.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import butterknife.BindView;
import butterknife.OnClick;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fm.designstar.R;
import com.fm.designstar.base.BaseFragment;
import com.fm.designstar.utils.Tool;
import com.fm.designstar.utils.Util;
import com.fm.designstar.widget.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;


public class DesignerFragment extends BaseFragment {


    @BindView(R.id.re_title)
    LinearLayout re_title;


    @BindView(R.id.re_guanzhu)
    RelativeLayout re_guanzhu;
    @BindView(R.id.tv_guanzhu)
    TextView tv_guanzhu;
    @BindView(R.id.im_guanzhu)
    ImageView im_guanzhu;

    @BindView(R.id.re_tuijain)
    RelativeLayout re_tuijain;
    @BindView(R.id.tv_tuijain)
    TextView tv_tuijain;
    @BindView(R.id.im_tuijain)
    ImageView im_tuijain;

    @BindView(R.id.re_find)
    RelativeLayout re_find;
    @BindView(R.id.tv_find)
    TextView tv_find;
    @BindView(R.id.im_find)
    ImageView im_find;

    @BindView(R.id.viewPager)
    NoScrollViewPager viewPager;

    private List<BaseFragment> fragmentList = new ArrayList<>();
    @Override
    public int getLayoutId() {
        return R.layout.fragment_designer;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void loadData() {

        re_title.getLayoutParams().height = Tool.dip2px(mContext, 44) + Util.getStatusBarH(mContext);
        ((ViewGroup.MarginLayoutParams) re_title.getLayoutParams()).topMargin = Util.getStatusBarH(mContext);
        fragmentList.add(new DesingnerChildFragment());
        fragmentList.add(new DesingnerAroundFragment());
        fragmentList.add(new DesingnerActivityFragment());
        viewPager.setEnabled(false);
        viewPager.setAdapter(new MyFragmentPagerAdapter(getChildFragmentManager()));
        viewPager.setOffscreenPageLimit(3);
        viewPager.setCurrentItem(0);

    }
    @OnClick({R.id.re_guanzhu, R.id.re_tuijain, R.id.re_find
    })
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.re_guanzhu:
                setItem();
                tv_guanzhu.setTextSize(22);
                im_guanzhu.setVisibility(View.VISIBLE);
                viewPager.setCurrentItem(0);
                break;
            case R.id.re_tuijain:
                setItem();
                tv_tuijain.setTextSize(22);
                im_tuijain.setVisibility(View.VISIBLE);
                viewPager.setCurrentItem(0);
                break;
            case R.id.re_find:
                setItem();
                tv_find.setTextSize(22);
                im_find.setVisibility(View.VISIBLE);
                viewPager.setCurrentItem(0);

                break;




            default:
                break;
        }
    }

    private void setItem() {
        tv_guanzhu.setTextSize(16);
        im_guanzhu.setVisibility(View.GONE);
        tv_tuijain.setTextSize(16);
        im_tuijain.setVisibility(View.GONE);
        tv_find.setTextSize(16);
        im_find.setVisibility(View.GONE);

    }

    private class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return fragmentList.get(i);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }
}