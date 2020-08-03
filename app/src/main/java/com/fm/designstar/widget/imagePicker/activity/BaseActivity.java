package com.fm.designstar.widget.imagePicker.activity;

import android.os.Build;
import android.os.Bundle;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.fm.designstar.R;
import com.fm.designstar.app.AppManager;
import com.fm.designstar.utils.StatusBarUtil;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


/**
 * BaseActivity基类
 * Create by: chenWei.li
 * Date: 2018/10/9
 * Time: 下午11:34
 * Email: lichenwei.me@foxmail.com
 */
public abstract class BaseActivity extends AppCompatActivity {

    private View mView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getInstance().addActivity(this);
        //在5.0系统以上设置状态栏颜色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.theme_color));
        }
        if (mView == null) {
            mView = View.inflate(this, bindLayout(), null);
        }

        setContentView(mView);
        initStatusBar();
        initConfig();
        initView();
        initListener();
        getData();
    }

    private void initStatusBar() {
        //层垫式状态栏
//        StatusBarUtil.immersive(this,0x8C8C8C,0.36f);
        StatusBarUtil.immersive(this);
    }
    protected abstract int bindLayout();

    protected void initConfig() {
    }

    protected abstract void initView();

    protected abstract void initListener();

    protected abstract void getData();


}
