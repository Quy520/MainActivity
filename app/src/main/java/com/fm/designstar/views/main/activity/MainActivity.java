package com.fm.designstar.views.main.activity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import butterknife.BindView;
import butterknife.OnClick;

import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fm.designstar.R;
import com.fm.designstar.app.App;
import com.fm.designstar.base.BaseActivity;
import com.fm.designstar.base.BaseFragment;
import com.fm.designstar.utils.StatusBarUtil;
import com.fm.designstar.utils.ToastUtil;
import com.fm.designstar.views.login.activitys.LoginActivity;
import com.fm.designstar.views.main.fragment.DesignerFragment;
import com.fm.designstar.views.main.fragment.HomeFragment;
import com.fm.designstar.views.main.fragment.MessageFragment;
import com.fm.designstar.views.main.fragment.MineFragment;
import com.fm.designstar.widget.NoScrollViewPager;
import com.fm.designstar.map.Selectaddress;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    @BindView(R.id.home)
    ImageView home;
    @BindView(R.id.location)
    ImageView location;
    @BindView(R.id.coupon)
    ImageView coupon;
    @BindView(R.id.me)
    ImageView me;

    @BindView(R.id.tv_home)
    TextView tv_home;
    @BindView(R.id.tv_des)
    TextView tv_des;
    @BindView(R.id.tv_message)
    TextView tv_message;
    @BindView(R.id.tv_my)
    TextView tv_my;


    @BindView(R.id.viewPager)
    NoScrollViewPager viewPager;
    @BindView(R.id.tabLayout)
    LinearLayout tabLayout;
    private long timeMillis;

    private List<BaseFragment> fragmentList = new ArrayList<>();
    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void loadData() {
        fragmentList.add(new HomeFragment());
        fragmentList.add(new DesignerFragment());
        fragmentList.add(new MessageFragment());
        fragmentList.add(new MineFragment());

        viewPager.setEnabled(false);
        viewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager()));
        viewPager.setOffscreenPageLimit(3);
    }
    @OnClick({R.id.homeLay, R.id.couponLay, R.id.locationLay, R.id.meLay,R.id.addLay
    })
    public void OnClick(View view) {

        switch (view.getId()) {
            case R.id.addLay:
            startActivity(Selectaddress.class);
                break;
            case R.id.homeLay:

                setItem();
                home.setImageResource(R.mipmap.icon_homeh);
                tv_home.setTextColor(getResources().getColor(R.color.notice));
                viewPager.setCurrentItem(0);
                StatusBarUtil.setAndroidNativeLightStatusBar(mActivity, true);
                break;

            case R.id.locationLay:

                setItem();
                viewPager.setCurrentItem(1);
                location.setImageResource(R.mipmap.me_h);
                tv_des.setTextColor(getResources().getColor(R.color.notice));

                StatusBarUtil.setAndroidNativeLightStatusBar(mActivity, true);
                break;
            case R.id.couponLay:
                if (App.getConfig().getLoginStatus()) {
                    setItem();
                    coupon.setImageResource(R.mipmap.icon_xiaoxih);
                    tv_message.setTextColor(getResources().getColor(R.color.notice));

                    viewPager.setCurrentItem(2);
                    StatusBarUtil.setAndroidNativeLightStatusBar(mActivity, true);
                } else {
                    startActivity(LoginActivity.class);
                }
                break;
            case R.id.meLay:
                if (App.getConfig().getLoginStatus()) {
                    setItem();
                    me.setImageResource(R.mipmap.icon_meh);
                    tv_my.setTextColor(getResources().getColor(R.color.notice));

                    viewPager.setCurrentItem(3);
                    StatusBarUtil.setAndroidNativeLightStatusBar(mActivity, true);
                } else {
                    startActivity(LoginActivity.class);
                }
                break;



            default:
                break;
        }
    }

    private void setItem() {
        home.setImageResource(R.mipmap.icon_homen);
        location.setImageResource(R.mipmap.me_n);
        coupon.setImageResource(R.mipmap.icon_xiaoxin);
        me.setImageResource(R.mipmap.icon_men);
        tv_home.setTextColor(getResources().getColor(R.color.hint_color));
        tv_des.setTextColor(getResources().getColor(R.color.hint_color));
        tv_message.setTextColor(getResources().getColor(R.color.hint_color));
        tv_my.setTextColor(getResources().getColor(R.color.hint_color));

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
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - timeMillis > 2000) {
                ToastUtil.showToast("再按一次退出程序");
                timeMillis = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}