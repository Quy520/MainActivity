package com.fm.designstar.app;

import android.app.Activity;
import android.content.Context;

import android.text.TextUtils;
import android.util.Log;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.fm.designstar.R;
import com.fm.designstar.config.ConfigUtil;
import com.fm.designstar.events.BaseEvent;
import com.fm.designstar.events.EventController;
import com.fm.designstar.utils.LogUtils;
import com.fm.designstar.utils.ToastUtil;
import com.fm.designstar.utils.ViewUtil;
import com.mob.MobSDK;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;

import androidx.multidex.MultiDexApplication;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import io.rong.imkit.RongIM;
import io.rong.imkit.manager.IUnReadMessageObserver;
import io.rong.imlib.model.Conversation;
import io.rong.push.RongPushClient;
import io.rong.push.pushconfig.PushConfig;


/**
 * @author DELL
 */
public class App extends MultiDexApplication {
    private static App mApp;
    private AMapLocationClient mlocationClient;
    boolean showStatus = true;
   boolean granted=true;
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
        //融云消息推送
        PushConfig config = new PushConfig.Builder()
                .enableMiPush("2882303761518619068", "5781861954068")//小米
                .enableOppoPush("O64bfa1ce65564162b667aaec850f6a57", "0f8d83148be542d6871d8ba92b96d5b5")//oppo
                .build();
        RongPushClient.setPushConfig(config);
        //融云注册
        String appKey = "z3v5yqkbz7we0";
        RongIM.init(mApp, appKey);
        //极光推送注册
        JPushInterface.setDebugMode(true); //允许被debug，正式版本的时候注掉
        JPushInterface.init(this);  //初始化
//mob分享协议
        MobSDK.submitPolicyGrantResult(granted, null);

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
