package com.fm.designstar.views.main.activity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import butterknife.BindView;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
//mport cn.jzvd.JZVideoPlayer;
import cn.jzvdother.JzvdStd;
import io.rong.imkit.RongIM;
import io.rong.imkit.manager.IUnReadMessageObserver;
import io.rong.imlib.model.Conversation;

import android.content.Intent;
import android.net.Uri;
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
import com.fm.designstar.base.BaseActivity;
import com.fm.designstar.base.BaseFragment;
import com.fm.designstar.dialog.AlertFragmentDialog;
import com.fm.designstar.dialog.FabuDialogUtil;
import com.fm.designstar.dialog.UpgradeDialog;
import com.fm.designstar.events.messageEvent;
import com.fm.designstar.https.HttpManager;
import com.fm.designstar.map.LocationInfo;
import com.fm.designstar.model.server.body.DesignerStatebody;
import com.fm.designstar.model.server.response.RoleResponse;
import com.fm.designstar.model.server.response.VesionResponse;
import com.fm.designstar.utils.OssImageUtil;
import com.fm.designstar.utils.SpUtil;
import com.fm.designstar.utils.StatusBarUtil;
import com.fm.designstar.utils.StringUtil;
import com.fm.designstar.utils.ToastUtil;
import com.fm.designstar.utils.Tool;
import com.fm.designstar.utils.Util;
import com.fm.designstar.views.login.activitys.LoginActivity;
import com.fm.designstar.views.main.contract.RoleContract;
import com.fm.designstar.views.main.contract.UpdataLocationContract;
import com.fm.designstar.views.main.contract.VesionContract;
import com.fm.designstar.views.main.fragment.DesignerFragment;
import com.fm.designstar.views.main.fragment.HomeFragment;
import com.fm.designstar.views.main.fragment.MessageFragment;
import com.fm.designstar.views.main.fragment.MineFragment;
import com.fm.designstar.views.main.presenter.RolePresenter;
import com.fm.designstar.views.main.presenter.UpdataLocationPresenter;
import com.fm.designstar.views.main.presenter.VesipnPresenter;
import com.fm.designstar.views.mine.activity.BeDesignerActivity;
import com.fm.designstar.views.mine.activity.DesignerRecordActivity;
import com.fm.designstar.views.mine.contract.FindDesignerContract;
import com.fm.designstar.views.mine.presenter.FindDesignerPresenter;
import com.fm.designstar.widget.NoScrollViewPager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MainActivity extends BaseActivity<UpdataLocationPresenter> implements AMapLocationListener, UpdataLocationContract.View, RoleContract.View, VesionContract.View , FindDesignerContract.View {
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
    @BindView(R.id.interNo)
    TextView interNo;


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
    private VesipnPresenter vesipnPresenter;
    private RolePresenter rolePresenter;
    private int state=3;
    private DesignerStatebody mstatebody;
    private FindDesignerPresenter designerPresenter;
    private UpgradeDialog dialog;
    private boolean index=false;
    private int first=1;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initPresenter() {
        mPresenter.init(this);
        vesipnPresenter=new VesipnPresenter();
        vesipnPresenter.init(this);
        rolePresenter=new RolePresenter();
        rolePresenter.init(this);

        designerPresenter=new FindDesignerPresenter();
        designerPresenter.init(this);


    }

    @Override
    public void loadData() {
        if(!EventBus.getDefault().isRegistered(this)){//加上判断
            EventBus.getDefault().register(this);
        }
        fragmentList.add(new HomeFragment());
        fragmentList.add(new DesignerFragment());
        fragmentList.add(new MessageFragment());
        fragmentList.add(new MineFragment());

        viewPager.setEnabled(false);
        viewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager()));
        viewPager.setOffscreenPageLimit(3);
        initLocate();
        RongIM.getInstance().addUnReadMessageCountChangedObserver(observer, Conversation.ConversationType.PRIVATE);
        JPushInterface.setAlias(mContext, App.getConfig().getUserToken(), new TagAliasCallback() {
            @Override
            public void gotResult(int i, String s, Set<String> set) {
                Log.e("qsd",i+"alias"+s+"==="+App.getConfig().getUserToken());
            }
        });
       //JPushInterface.setAlias(mContext,1,App.getConfig().getUserToken());
      //  JPushInterface.deleteAlias(mContext,1);

       if (App.getConfig().getRole()==2){
           designerPresenter.FindDesigner();
       }
        rolePresenter.GetRole();
        vesipnPresenter.Vesion(Util.getAppVersionCode(App.getContext()),"android");
      /*  try {
            OssImageUtil.getImgWH("https://yuxuanlin.oss-cn-shanghai.aliyuncs.com/1-1-5f3f6b43b07629bcb03ea551-1597991775-416543.jpg");
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
    /**
     * 未读消息监听回调
     * @param i
     */
    private IUnReadMessageObserver observer = new IUnReadMessageObserver() {
        @Override
        public void onCountChanged(int i) {
          if (i>0){
              interNo.setVisibility(View.VISIBLE);
          } else {
              interNo.setVisibility(View.GONE);
          }
        }
    };
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
        mLocationOption.setInterval(20000);
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
                if (Tool.isFastDoubleClick()) {
                    return;
                }
                first=2;
                rolePresenter.GetRole();
                if (App.getConfig().getRole()==1){
                    designerPresenter.FindDesigner();
                }else {
                     if (App.getConfig().getRole()==3){
                        ToastUtil.showToast("您不是设计师不能发表作品和动态");
                    } else {
                        fabuDialogUtil = new FabuDialogUtil(mContext,mActivity);
                        fabuDialogUtil.showDialog();
                    }
                }
           /*     if (App.getConfig().getRole()==1){
                    if (state==0){
                        new AlertFragmentDialog.Builder(mActivity)
                                .setContent(getString(R.string.designering) )
                                .setLeftBtnText(getString(R.string.sheet_dialog_cancel))
                                .setRightBtnText(getString(R.string.sure))

                                .build();

                    }else if (state==1){
                        fabuDialogUtil = new FabuDialogUtil(mContext);
                        fabuDialogUtil.showDialog();

                    }else if (state==2){
                        new AlertFragmentDialog.Builder(mActivity)
                                .setContent(getString(R.string.designerfalse) )
                                .setLeftBtnText(getString(R.string.sheet_dialog_cancel))
                                .setRightBtnText(getString(R.string.go_look))
                                .setRightCallBack(new AlertFragmentDialog.RightClickCallBack() {
                                    @Override
                                    public void dialogRightBtnClick() {
                                        if (mstatebody!=null){
                                            Intent intent3=new Intent(mContext, DesignerRecordActivity.class);
                                            intent3.putExtra("Result",mstatebody);
                                            startActivity(intent3);
                                        }

                                    }
                                }).build();

                    }else {

                        new AlertFragmentDialog.Builder(mActivity)
                                .setContent(getString(R.string.no_designer) )
                                .setLeftBtnText(getString(R.string.sheet_dialog_cancel))
                                .setRightBtnText(getString(R.string.sheet_dialog_ok))
                                .setRightCallBack(new AlertFragmentDialog.RightClickCallBack() {
                                    @Override
                                    public void dialogRightBtnClick() {
                                        startActivity(BeDesignerActivity.class);

                                    }
                                }).build();
                    }

                  *//*     fabuDialogUtil = new FabuDialogUtil(mContext);
                    fabuDialogUtil.showDialog();*//*

                } else if (App.getConfig().getRole()==3){
                   *//* fabuDialogUtil = new FabuDialogUtil(mContext);
                    fabuDialogUtil.showDialog();*//*

                    ToastUtil.showToast("您不是设计师不能发表作品和动态");
                } else {
                    fabuDialogUtil = new FabuDialogUtil(mContext);
                    fabuDialogUtil.showDialog();
                }*/

                break;
            case R.id.homeLay:
                setItem();
                home.setImageResource(R.mipmap.icon_homeh);
                tv_home.setTextColor(getResources().getColor(R.color.black3));
                viewPager.setCurrentItem(0);
                StatusBarUtil.setAndroidNativeLightStatusBar(mActivity, true);
                break;

            case R.id.locationLay:

                setItem();
                viewPager.setCurrentItem(1);
                location.setImageResource(R.mipmap.me_h);
                tv_des.setTextColor(getResources().getColor(R.color.black3));

                StatusBarUtil.setAndroidNativeLightStatusBar(mActivity, true);
                break;
            case R.id.couponLay:
                if (App.getConfig().getLoginStatus()) {
                    interNo.setVisibility(View.GONE);
                    setItem();
                    coupon.setImageResource(R.mipmap.icon_xiaoxih);
                    tv_message.setTextColor(getResources().getColor(R.color.black3));

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
                    tv_my.setTextColor(getResources().getColor(R.color.black3));

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
                 LocationInfo locationInfo = new LocationInfo();
                locationInfo.setAddress(amapLocation.getAddress());
                locationInfo.setLatitude(latitude);
                locationInfo.setLonTitude(longitude);
                mPresenter.UpdataLocation(amapLocation.getAddress(),amapLocation.getCity(),amapLocation.getDistrict(),latitude,longitude);
                SpUtil.putLong("latitude",(long) latitude);
                SpUtil.putLong("longitude",(long) longitude);
                if (mlocationClient != null) {
                    mlocationClient.onDestroy();//销毁定位客户端，同时销毁本地定位服务。
                }
                Log.e("AmapError",latitude+"=="+longitude);
            } else {
                mlocationClient.stopLocation();
                mPresenter.UpdataLocation("上海市","上海市","",31.159168,121.355786);

              /*  //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + amapLocation.getErrorCode() + ", errInfo:"
                        + amapLocation.getErrorInfo());
                ToastUtil.showToast(amapLocation.getErrorInfo()+"请检查权限是否开启");
*/
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
        mlocationClient.stopLocation();
    }

    @Override
    public void GetRoleSuccess(RoleResponse infoResponse) {
        Log.e("qsd","infoResponse"+infoResponse.getTagBean().getTagName());
        App.getConfig().setRole(infoResponse.getRole());
        if (infoResponse.getTagBean()!=null){
            App.getConfig().setTagname(infoResponse.getTagBean().getTagName());
        }


    }

    @Override
    public void VesionSuccess(VesionResponse response) {

        final int version = Util.getAppVersionCode(App.getContext());//100
        if (StringUtil.isBlank(response.getVersionName())){
            return;
        }


        if (response.getVersionCode() > version) {
            if (response.getType() == 1) {//强制升级版本
                setDialog(false, response, new UpgradeDialog.OnClickListener() {
                    @Override
                    public void up() {
                        Uri uri = Uri.parse(HttpManager.getUrl("https://static.laifuyun.com/app/com.fm.designstar.apk"));
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        if (getPackageManager().resolveActivity(intent, 0) == null) {
                            // 说明系统中不存在这个activity
                            return;
                        }
                        startActivity(intent);
                    }
                    @Override
                    public void close() {

                    }
                });
                return;

            }else {
                setDialog(true, response, new UpgradeDialog.OnClickListener() {
                    @Override
                    public void up() {
                        Uri uri = Uri.parse(HttpManager.getUrl("https://static.laifuyun.com/app/com.fm.designstar.apk"));
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        if (getPackageManager().resolveActivity(intent, 0) == null) {
                            // 说明系统中不存在这个activity
                            return;
                        }
                        startActivity(intent);

                    }

                    @Override
                    public void close() {

                    }
                });
                return;
            }



        }

    }

    private void setDialog(boolean noUpgrade, VesionResponse response, UpgradeDialog.OnClickListener onClickListener) {
        if (dialog == null) {
            dialog = new UpgradeDialog(mContext);
            dialog.setNoUpgrade(noUpgrade);
            dialog.setVersionInfo(response.getVersionInfo());
            dialog.setOnClickListener(onClickListener);
        }
        if (!dialog.isShowing()) {
            dialog.show();
        }
    }
    @Override
    public void DFindDesignerSuccess(DesignerStatebody statebody) {
        if(StringUtil.isBlank(statebody.getImgUrl())){
            state=3;
          if (first==2) {
              new AlertFragmentDialog.Builder(mActivity)
                      .setContent(getString(R.string.no_designer) )
                      .setLeftBtnText(getString(R.string.sheet_dialog_cancel))
                      .setRightBtnText(getString(R.string.sheet_dialog_ok))
                      .setRightCallBack(new AlertFragmentDialog.RightClickCallBack() {
                          @Override
                          public void dialogRightBtnClick() {
                              startActivity(BeDesignerActivity.class);

                          }
                      }).build();
          }

            return;
        }

        mstatebody=statebody;
        state=statebody.getStatus();
        if (App.getConfig().getRole()==1){
            if (state==0){
                new AlertFragmentDialog.Builder(mActivity)
                        .setContent(getString(R.string.designering) )
                        .setLeftBtnText(getString(R.string.sheet_dialog_cancel))
                        .setRightBtnText(getString(R.string.sure))

                        .build();

            }else if (state==1){
                App.getConfig().setRole(2);
                fabuDialogUtil = new FabuDialogUtil(mContext,mActivity);
                fabuDialogUtil.showDialog();

            }else if (state==2){
                new AlertFragmentDialog.Builder(mActivity)
                        .setContent(getString(R.string.designerfalse) )
                        .setLeftBtnText(getString(R.string.sheet_dialog_cancel))
                        .setRightBtnText(getString(R.string.go_look))
                        .setRightCallBack(new AlertFragmentDialog.RightClickCallBack() {
                            @Override
                            public void dialogRightBtnClick() {
                                if (mstatebody!=null){
                                    Intent intent3=new Intent(mContext, DesignerRecordActivity.class);
                                    intent3.putExtra("Result",mstatebody);
                                    startActivity(intent3);
                                }

                            }
                        }).build();

            }else {
                new AlertFragmentDialog.Builder(mActivity)
                        .setContent(getString(R.string.no_designer) )
                        .setLeftBtnText(getString(R.string.sheet_dialog_cancel))
                        .setRightBtnText(getString(R.string.sheet_dialog_ok))
                        .setRightCallBack(new AlertFragmentDialog.RightClickCallBack() {
                            @Override
                            public void dialogRightBtnClick() {
                                startActivity(BeDesignerActivity.class);

                            }
                        }).build();
            }

        }


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

//移除监听，防止内存泄漏
        RongIM.getInstance().removeUnReadMessageCountChangedObserver(observer);
    }
    @Override
    public void onBackPressed() {
        if (JzvdStd.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JzvdStd.releaseAllVideos();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(messageEvent event) {
        Log.e("qsd","messageEvent"+event.getType());
        if (event.getType()==1){
        if (event.getTAG()>0){
            interNo.setVisibility(View.VISIBLE);
            interNo.setText(event.getTAG()+"");
        } else {
            interNo.setVisibility(View.GONE);
        }
        }else {
            if (event.getTAG()==0){//当前消息页面
                interNo.setVisibility(View.INVISIBLE);
            }else {
                interNo.setVisibility(View.VISIBLE);

            }


        }
    }
}