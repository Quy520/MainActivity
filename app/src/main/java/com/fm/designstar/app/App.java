package com.fm.designstar.app;

import android.app.Activity;
import android.content.Context;

import android.text.TextUtils;
import com.fm.designstar.R;
import com.fm.designstar.config.ConfigUtil;
import com.fm.designstar.events.BaseEvent;
import com.fm.designstar.events.EventController;
import com.fm.designstar.utils.LogUtils;
import com.fm.designstar.utils.ToastUtil;
import com.fm.designstar.utils.ViewUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import androidx.multidex.MultiDexApplication;


/**
 * @author DELL
 */
public class App extends MultiDexApplication {
    private static App mApp;
    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
        //log初始化,根据configUtil的isDebug参数控制是否显示log
        LogUtils.logInit(getConfig().isDebug());
        //toast初始化
        ToastUtil.register(mApp);
        //注册eventbus
        EventBus.getDefault().register(mApp);

    }


    public static void loadingDefault(Activity activity) {
        loadingContent(activity, "");
    }

    public static void loadingContent(Activity activity, String content) {
        ViewUtil.setCancelable(true);
//        ViewUtil.setLoadingView(activity, null);
        ViewUtil.showLoading(activity, content);
    }

    public static boolean loadingIsShowing() {
        return ViewUtil.isShowing();
    }

    public static void loadingContentCancel(Activity activity, String content) {
        ViewUtil.setCancelable(true);
//        ViewUtil.setLoadingView(activity, null);
        ViewUtil.showLoading(activity, content);
    }

    public static void hideLoading() {
        ViewUtil.hideLoading();
    }


    public static String getAPPName() {
        return getContext().getString(R.string.app_name);
    }

    /*******
     * 将事件交给事件派发controller处理
     * @param event
     */

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(BaseEvent event) {
        event.setApplicationContext(getContext());
        EventController.getInstance().handleMessage(event);
    }


    /**
     * 保存一些常用的配置
     */
    private static ConfigUtil configUtil = null;


    public static ConfigUtil getConfig() {
        if (configUtil == null) {
            synchronized (App.class) {
                if (configUtil == null) {
                    configUtil = new ConfigUtil();
                }
            }
        }
        return configUtil;
    }


    public static Context getContext() {
        return mApp.getApplicationContext();
    }

    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    private static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }
}
