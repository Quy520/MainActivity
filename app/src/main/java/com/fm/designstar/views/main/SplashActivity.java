package com.fm.designstar.views.main;

import android.Manifest;
import android.content.Intent;

import com.fm.designstar.R;
import com.fm.designstar.base.BaseActivity;
import com.fm.designstar.base.PermissionsListener;
import com.fm.designstar.utils.FormatUtil;
import com.fm.designstar.utils.SpUtil;
import com.fm.designstar.views.login.activitys.LoginActivity;

import java.util.List;

public class SplashActivity extends BaseActivity {


    /**
     * 为了避免在onResume中多次请求权限
     */
    private boolean isRequesting;
    private String type,value;
    private String[] permissions = {
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.SYSTEM_ALERT_WINDOW

    };


    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void loadData() {
        //新的应用重复启动解决方法
        if (!isTaskRoot()) {
            //判断该Activity是不是任务空间的源Activity,"非"也就是说是被系统重新实例化出来的
            //如果你就放在Launcher Activity中的话，这里可以直接return了
            Intent mainIntent = getIntent();
            String action = mainIntent.getAction();
            assert action != null;
            if (mainIntent.hasCategory(Intent.CATEGORY_LAUNCHER) && action.equals(Intent.ACTION_MAIN)) {
                finish();
            }
        }


    }


    @Override
    protected void onResume() {
        super.onResume();
        if (!isRequesting) {
            requestPermissions(permissions, mListener);
            isRequesting = true;
        }

    }

    private PermissionsListener mListener = new PermissionsListener() {
        @Override
        public void onGranted() {

            startActivity(LoginActivity.class);
            SpUtil.putString("IMEI", FormatUtil.getIMEI(mContext));
            finish();


        }


        @Override
        public void onDenied(List<String> deniedPermissions, boolean isNeverAsk) {

                startActivity(LoginActivity.class);

            finish();

        }
    };




}
