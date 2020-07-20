package com.fm.designstar.base;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;

import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;


import com.fm.designstar.R;
import com.fm.designstar.app.App;
import com.fm.designstar.app.AppManager;
import com.fm.designstar.dialog.AlertFragmentDialog;
import com.fm.designstar.utils.StatusBarUtil;
import com.fm.designstar.utils.TUtil;
import com.fm.designstar.utils.TitleUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import butterknife.ButterKnife;


/**
 * 基类
 *
 * @author DELL
 */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {
    public T mPresenter;
    public Context mContext;
    public BaseActivity mActivity;
    private PermissionsListener mListener;
    protected TitleUtil mTitle;
    protected int type;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        AppManager.getInstance().addActivity(this);
        ButterKnife.bind(this);
        mContext = this;
        mActivity = this;
        mPresenter = TUtil.getT(this, 0);
        mTitle = new TitleUtil(this, getWindow().getDecorView());
        //google分析初始化

        initStatusBar();
        initPresenter();
        loadData();
    }

    private void initStatusBar() {
        //层垫式状态栏
//        StatusBarUtil.immersive(this,0x8C8C8C,0.36f);
        StatusBarUtil.immersive(this);
    }


    /*********************
     * 子类实现
     *****************************/
    //获取布局文件
    public abstract int getLayoutId();

    //简单页面无需mvp就不用管此方法即可,完美兼容各种实际场景的变通
    public abstract void initPresenter();

    //加载、设置数据
    public abstract void loadData();


    /**
     * 请求权限封装
     *
     * @param permissions
     * @param listener
     */
    public void requestPermissions(String[] permissions, PermissionsListener listener) {
        mListener = listener;
        List<String> requestPermissions = new ArrayList<>();
        for (String permission : permissions) {
            try {
                if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_DENIED) {
                    requestPermissions.add(permission);
                }
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
        if (!requestPermissions.isEmpty() && (Build.VERSION.SDK_INT >= 23)) {
            ActivityCompat.requestPermissions(this, requestPermissions.toArray(new String[requestPermissions.size()]), 1);
        } else {
            if (mListener != null) {
                mListener.onGranted();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                List<String> deniedPermissions = new ArrayList<>();
                //当所有拒绝的权限都勾选不再询问，这个值为true,这个时候可以引导用户手动去授权。
                boolean isNeverAsk = true;
                for (int i = 0; i < grantResults.length; i++) {
                    int grantResult = grantResults[i];
                    String permission = permissions[i];
                    if (grantResult == PackageManager.PERMISSION_DENIED) {
                        deniedPermissions.add(permissions[i]);
                        // 点击拒绝但没有勾选不再询问
                        if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                            isNeverAsk = false;
                        }
                    }
                }
                if (deniedPermissions.isEmpty()) {
                    try {
                        if (mListener != null) {
                            mListener.onGranted();
                        }
                    } catch (RuntimeException e) {
                        e.printStackTrace();
                        if (mListener != null) {
                            mListener.onDenied(Arrays.asList(permissions), true);
                        }
                    }
                } else {
                    if (mListener != null) {
                       // mListener.onDenied(deniedPermissions, isNeverAsk);
                        mListener.onDenied(deniedPermissions,isNeverAsk);
                    }
                }
                break;
            default:
                break;
        }
    }


    /**
     * 通过Class跳转界面
     **/
    protected void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 通过Class跳转界面
     **/
    protected void startActivityForResult(Class<?> cls, int requestCode) {
        startActivityForResult(cls, null, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    protected void startActivityForResult(Class<?> cls, Bundle bundle,
                                          int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.activity_start_open, R.anim.activity_start_close);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    protected void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        overridePendingTransition(R.anim.activity_start_open, R.anim.activity_start_close);
    }

//    @Override
//    public void finish() {
//        super.finish();
//        overridePendingTransition(R.anim.activity_finish_close,R.anim.activity_finish_open);
//    }

    IWindowFocus iFocus;

    public void setOnIWindowFocus(IWindowFocus windowFocus) {
        iFocus = windowFocus;
    }

    public interface IWindowFocus {
        void focused();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        closeKeyboard();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
        App.hideLoading();
        AppManager.getInstance().finishActivity(this);
    }

    public void closeKeyboard() {
        View view = getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            assert inputMethodManager != null;
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void showKeyboard(EditText view) {
        InputMethodManager inputMethodManager = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            view.requestFocus();
            inputMethodManager.showSoftInput(view, 0);
        }
    }

    // 启动应用的设置弹窗
    public void toAppSettings(String message, final boolean isFinish) {
        if (TextUtils.isEmpty(message)) {
            message ="\""+ App.getAPPName()+"\"缺少必要权限";
            //message = "\"" + App.getAPPName() + "\"kekurangan perizinan seharusnya";
        }
        AlertFragmentDialog.Builder builder = new AlertFragmentDialog.Builder(this);
        if (isFinish) {
            builder.setLeftBtnText(R.string.exit_login)
                    .setLeftCallBack(new AlertFragmentDialog.LeftClickCallBack() {
                        @Override
                        public void dialogLeftBtnClick() {
                            finish();
                        }
                    });
        } else {
            builder.setLeftBtnText(R.string.sheet_dialog_cancel_batal);
        }
        builder.setContent(message+"\n请手动授予\""+ App.getAPPName()+"\"访问您的权限")
       // builder.setContent(message + "\nIzinkan\"" + App.getAPPName() + "\"mengakses secara manual")
                .setRightBtnText(R.string.go_to_setting)
                .setRightCallBack(new AlertFragmentDialog.RightClickCallBack() {
                    @Override
                    public void dialogRightBtnClick() {
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        intent.setData(Uri.parse("package:" + getPackageName()));
                        startActivity(intent);
                    }
                }).build();
    }
}
