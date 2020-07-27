package com.fm.designstar.app;

import android.content.Context;

import com.fm.designstar.views.main.activity.MainActivity;

import java.util.Iterator;
import java.util.Stack;

import androidx.fragment.app.FragmentActivity;

/**
 * 应用程序Activity管理类：用于Activity管理和应用程序退出
 *
 * @author DELL
 */
public class AppManager {

    private static Stack<FragmentActivity> activityStack;
    private static AppManager instance;

    private AppManager() {
    }

    /**
     * 单一实例
     */
    public static AppManager getInstance() {
        if (instance == null) {
            instance = new AppManager();
        }
        return instance;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(FragmentActivity activity) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public FragmentActivity currentActivity() {
        if (activityStack.empty()) {
            return null;
        }
        FragmentActivity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public boolean hasActivity(Class<?> cls) {
        if (activityStack.empty()) {
            return false;
        }
        for (FragmentActivity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity() {
        if (activityStack.empty()) {
            return;
        }
        FragmentActivity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(FragmentActivity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        Iterator<FragmentActivity> iterator = activityStack.iterator();
        while (iterator.hasNext()) {
            FragmentActivity activity = iterator.next();
            if (null != activity) {
                if (activity.getClass().equals(cls)) {
                    activity.finish();
                    iterator.remove();
                }
            }
        }
//        for (FragmentActivity activity : activityStack) {
//            if (activity.getClass().equals(cls)) {
//                activity.finish();
//            }
//        }
    }


    /**
     * 结束指定Login的Activity
     */
/*
    public void finishLoginActivity() {
        Iterator<FragmentActivity> iterator = activityStack.iterator();
        while (iterator.hasNext()) {
            FragmentActivity activity = iterator.next();
            if (null != activity) {
                if (activity instanceof LoginActivity || activity instanceof SignActivity) {
                    activity.finish();
                    iterator.remove();
                }
            }
        }
    }
*/

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        Iterator<FragmentActivity> iterator = activityStack.iterator();
        while (iterator.hasNext()) {
            FragmentActivity activity = iterator.next();
            if (null != activity) {
                activity.finish();
                iterator.remove();
            }
        }
//
//        for (int i = 0, size = activityStack.size(); i < size; i++) {
//            if (null != activityStack.get(i)) {
//                activityStack.get(i).finish();
//            }
//        }
//        activityStack.clear();
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivityButMain() {
        Iterator<FragmentActivity> iterator = activityStack.iterator();
        while (iterator.hasNext()) {
            FragmentActivity activity = iterator.next();
            if (null != activity) {
                if (!(activity instanceof MainActivity)) {
                    activity.finish();
                    iterator.remove();
                }
            }
        }


    }


    /**
     * 退出应用程序
     */
    public void appExit(Context context) {
        try {
            finishAllActivity();
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isAppExit() {
        return activityStack == null || activityStack.isEmpty();
    }
}
