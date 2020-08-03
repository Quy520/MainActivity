package com.fm.designstar.views.main.activity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import butterknife.BindView;
import butterknife.OnClick;

import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.fm.designstar.R;
import com.fm.designstar.app.App;
import com.fm.designstar.app.AppManager;
import com.fm.designstar.base.BaseActivity;
import com.fm.designstar.base.BaseFragment;
import com.fm.designstar.dialog.FabuDialogUtil;
import com.fm.designstar.dialog.ShareDialogUtil;
import com.fm.designstar.map.LocationInfo;
import com.fm.designstar.utils.StatusBarUtil;
import com.fm.designstar.utils.ToastUtil;
import com.fm.designstar.views.login.activitys.LoginActivity;
import com.fm.designstar.views.main.contract.UpdataLocationContract;
import com.fm.designstar.views.main.fragment.DesignerFragment;
import com.fm.designstar.views.main.fragment.HomeFragment;
import com.fm.designstar.views.main.fragment.MessageFragment;
import com.fm.designstar.views.main.fragment.MineFragment;
import com.fm.designstar.views.main.presenter.UpdataLocationPresenter;
import com.fm.designstar.widget.NoScrollViewPager;
import com.fm.designstar.map.Selectaddress;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends BaseActivity<UpdataLocationPresenter> implements AMapLocationListener, UpdataLocationContract.View {
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
    public AMapLocationClient mlocationClient = null;
    private List<BaseFragment> fragmentList = new ArrayList<>();
    double latitude ;//获取纬度
    double longitude;
    private FabuDialogUtil fabuDialogUtil;
    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initPresenter() {
        mPresenter.init(this);

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
        initLocate();
    }

    private void initLocate() {
        //声明mLocationOption对象
        AMapLocationClientOption mLocationOption = null;
        mlocationClient = new AMapLocationClient(this);
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置定位监听
        mlocationClient.setLocationListener(this);
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
        mLocationOption.setOnceLocationLatest(true);
        //设置定位参数
        mlocationClient.setLocationOption(mLocationOption);
        mlocationClient.startLocation();


    }
    @OnClick({R.id.homeLay, R.id.couponLay, R.id.locationLay, R.id.meLay,R.id.addLay
    })
    public void OnClick(View view) {

        switch (view.getId()) {
            case R.id.addLay:
            //startActivity(Selectaddress.class);
                fabuDialogUtil = new FabuDialogUtil(mContext);


                fabuDialogUtil.showDialog();

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

    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {
                //定位成功回调信息，设置相关消息
                amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                latitude = amapLocation.getLatitude();//获取纬度
                longitude = amapLocation.getLongitude();//获取经度
                amapLocation.getAccuracy();//获取精度信息
                Log.d("qsdhaha", amapLocation.getAddress()+"=="+latitude+"==="+longitude+"=="+amapLocation.getCity()+"=="+amapLocation.getDistrict());
                LocationInfo locationInfo = new LocationInfo();
                locationInfo.setAddress(amapLocation.getAddress());
                locationInfo.setLatitude(latitude);
                locationInfo.setLonTitude(longitude);
                mPresenter.UpdataLocation(amapLocation.getAddress(),amapLocation.getCity(),amapLocation.getDistrict(),latitude,longitude);


            } else {
                mlocationClient.stopLocation();
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + amapLocation.getErrorCode() + ", errInfo:"
                        + amapLocation.getErrorInfo());
                ToastUtil.showToast(amapLocation.getErrorInfo()+"请检查权限是否开启");

            }
        }
    }

    @Override
    public void UpdataLocationSuccess() {
        mlocationClient.stopLocation();
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
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mlocationClient != null) {
            mlocationClient.onDestroy();//销毁定位客户端，同时销毁本地定位服务。
        }

    }
}