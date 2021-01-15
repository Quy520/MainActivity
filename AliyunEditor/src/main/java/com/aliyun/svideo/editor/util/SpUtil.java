package com.aliyun.svideo.editor.util;


import android.content.Context;

import net.grandcentrix.tray.AppPreferences;

/**
 * 跨进程存储工具类
 * @author DELL
 */
public class SpUtil {
    private static AppPreferences appPreferences;
    private Context mcontext;

    public static String getString(String key,Context mcontext) {
        return getSharedPreferences(mcontext).getString(key, "");
    }

    public static void putString(String key, String value,Context context) {

        getSharedPreferences(context).put(key, value);
    }


    public static AppPreferences getSharedPreferences(Context context) {

        if (appPreferences ==null){
            appPreferences=new AppPreferences(context);
        }
        return appPreferences;
    }

}
