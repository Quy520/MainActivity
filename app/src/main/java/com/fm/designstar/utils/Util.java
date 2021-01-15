package com.fm.designstar.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.media.MediaMetadataRetriever;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.provider.Settings;

import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;


import com.fm.designstar.app.App;
import com.fm.designstar.model.bean.TagsInfoVoBean;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;

import androidx.core.app.ActivityCompat;

import static android.content.Context.ACTIVITY_SERVICE;
import static android.content.Context.TELEPHONY_SERVICE;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/4/19 13:09
 * @update : 2018/4/19
 */

public class Util {
    public static String touzi_ed_values22 = "";
    public static boolean ActivityIsClose(Context context) {
        if (context == null) {
            return true;
        }
        if (context instanceof Activity) {
            if (((Activity) context).isFinishing()) {
                return true;
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                if (((Activity) context).isDestroyed()) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean ActivityIsClose(Activity activity) {
        if (activity == null) {
            return true;
        }
        if (activity.isFinishing()) {
            return true;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            if (activity.isDestroyed()) {
                return true;
            }
        }
        return false;
    }

    public static HashMap<String, String> getMap(String params) {
        HashMap<String, String> map = new HashMap<>();
        if (StringUtil.isBlank(params)) {
            return map;
        }
        String[] arrSplit = null;
        //每个键值为一组 www.2cto.com
        arrSplit = params.split("[&]");
        if (arrSplit.length > 0) {
            for (String strSplit : arrSplit) {
                String[] arrSplitEqual = null;
                arrSplitEqual = strSplit.split("[=]");
                //解析出键值
                if (arrSplitEqual.length > 1) {
                    //正确解析
                    if (!StringUtil.isBlank(arrSplitEqual[0])) {
                        map.put(arrSplitEqual[0], arrSplitEqual[1]);
                    }
                } else {
                    if (arrSplitEqual.length > 0) {
                        if (!StringUtil.isBlank(arrSplitEqual[0])) {
                            //只有参数没有值，不加入
                            map.put(arrSplitEqual[0], "");
                        }
                    }
                }
            }
        }
        return map;
    }

    public static String getPath() {
        Context context = App.getContext();
        if (context == null) {
            return "";
        }
        String path;
        //文件路径
        if (checkSD()) {
            path = Environment.getExternalStorageDirectory() + "/" + context.getApplicationInfo().packageName + "/image/";
        } else {
            path = context.getFilesDir() + "/" + context.getApplicationInfo().packageName + "/image/";
        }
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        return path;
    }


    public static boolean checkSD() {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            return true;
        } else {
            return false;
        }
    }

    public static void deleteImage() {
        Context context = App.getContext();
        if (context == null) {
            return;
        }
        delFolder(getPath());
    }

    private static void delFolder(String folderPath) {
        try {
            delAllFile(folderPath); //删除完里面所有内容
            String filePath = folderPath;
            filePath = filePath.toString();
            java.io.File myFilePath = new java.io.File(filePath);
            myFilePath.delete(); //删除空文件夹
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //删除指定文件夹下所有文件
//param path 文件夹完整绝对路径
    public static boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文件
                delFolder(path + "/" + tempList[i]);//再删除空文件夹
                flag = true;
            }
        }
        return flag;
    }


    public static String versionName;
    public static int versionCode = -1;
    public static String installedTime;
    public static String DeviceId;

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /*********
     * 获取屏幕宽度
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        DisplayMetrics metric = context.getResources().getDisplayMetrics();
        int width = metric.widthPixels;
        // 屏幕宽度（像素）
        return width;
    }

    /***********
     * 获取屏幕高度
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        DisplayMetrics metric = context.getResources().getDisplayMetrics();
        int height = metric.heightPixels;
        // 屏幕高度（像素）
        return height;
    }

    /******
     * 获取状态栏高度
     * @param context
     * @return
     */
    public static int getStatusBarH(Context context) {
        /*int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;*/
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        return height;
    }
    public static int getScreenWidth2(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        return width;
    }
//	/**
//	 * 得到状态栏的高度
//	 * @param context 上下文
//	 * @return 状态栏的高度
//	 */
//	public static int getStatusBarHeight(Context context) {
//		Class<?> c = null;
//		Object obj = null;
//		Field field = null;
//		int x = 0, sbar = 0;
//		try {
//			c = Class.forName("com.android.internal.R$dimen");
//			obj = c.newInstance();
//			field = c.getField("status_bar_height");
//			x = Integer.parseInt(field.get(obj).toString());
//			sbar = context.getResources().getDimensionPixelSize(x);
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}
//		return sbar;
//	}

    /*************
     * 获取屏幕宽跟高
     * @param context
     * @return
     */
    public static int[] getScreenExtent(Context context) {
        int[] buf = new int[2];
        DisplayMetrics metric = context.getResources().getDisplayMetrics();
        int height = metric.heightPixels;
        // 屏幕高度（像素）
        int width = metric.widthPixels;
        // 屏幕宽度（像素）
        buf[0] = width;
        buf[1] = height;
        return buf;
    }


    /**
     * 获取当前app的版本号
     *
     * @param context
     * @return
     */
    public static String getAppVersion(Context context) {
        if (StringUtil.isBlank(versionName)) {
            try {
                PackageManager packageManager = context.getPackageManager();
                PackageInfo packInfo;
                packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
                versionName = packInfo.versionName;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                versionName = "";
            }
        }
        return versionName;
    }


    /**
     * 获取当前app的版本号
     *
     * @param context
     * @return
     */
    public static int getAppVersionCode(Context context) {
        if (versionCode <= 0) {
            try {
                PackageManager packageManager = context.getPackageManager();
                PackageInfo packInfo;
                packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
                versionCode = packInfo.versionCode;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                versionCode = 0;
            }
        }
        return versionCode;
    }

    /**
     * 获取当前设备ID(设备号为空时使用AndroidId)
     *
     * @param context
     * @return
     */
    public static String getDeviceId(Context context) {
        StringBuilder m_szUniqueID = new StringBuilder();
        if (StringUtil.isBlank(DeviceId)) {
            String m_szLongID = getIMEI(context) + getAndroidID(context) + getPesudoUniqueID() + Build.SERIAL;
            // compute md5
            MessageDigest m = null;
            try {
                m = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            m.update(m_szLongID.getBytes(), 0, m_szLongID.length());
            // get md5 bytes
            byte p_md5Data[] = m.digest();
            // create a hex string
            for (byte aP_md5Data : p_md5Data) {
                int b = (0xFF & aP_md5Data);
                // if it is a single digit, make sure it have 0 in front (proper padding)
                if (b <= 0xF) {
                    m_szUniqueID.append("0");
                }
                // add number to string
                m_szUniqueID.append(Integer.toHexString(b));
            }   // hex string to uppercase
            m_szUniqueID = new StringBuilder(m_szUniqueID.toString().toUpperCase());
        }
        DeviceId = m_szUniqueID.toString();
        return DeviceId;
    }

    public static String getIMEI(Context context) {
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(TELEPHONY_SERVICE);
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {

                return "";
            }
            return tm.getDeviceId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Pseudo-Unique ID, 这个在任何Android手机中都有效
     * 有一些特殊的情况，一些如平板电脑的设置没有通话功能，或者你不愿加入READ_PHONE_STATE许可。而你仍然想获得唯
     * 一序列号之类的东西。这时你可以通过取出ROM版本、制造商、CPU型号、以及其他硬件信息来实现这一点。这样计算出
     * 来的ID不是唯一的（因为如果两个手机应用了同样的硬件以及Rom 镜像）。但应当明白的是，出现类似情况的可能性基
     * 本可以忽略。大多数的Build成员都是字符串形式的，我们只取他们的长度信息。我们取到13个数字，并在前面加上“35
     * ”。这样这个ID看起来就和15位IMEI一样了。
     *
     * @return PesudoUniqueID
     */
    public static String getPesudoUniqueID() {
        String m_szDevIDShort = "35" + //we make this look like a valid IMEI
                Build.BOARD.length() % 10 +  //主板：The name of the underlying board, like "goldfish".
                Build.BRAND.length() % 10 +  //系统定制商：The consumer-visible brand with which the product/hardware will be associated, if any
                Build.CPU_ABI.length() % 10 +  // cpu指令集：The name of the instruction set (CPU type + ABI convention) of native code.
                Build.DEVICE.length() % 10 +  //设备参数：The name of the industrial design.
                Build.DISPLAY.length() % 10 +  //显示屏参数：A build ID string meant for displaying to the user
                Build.ID.length() % 10 +
                Build.MANUFACTURER.length() % 10 +  //硬件制造商：The manufacturer of the product/hardware.
                Build.MODEL.length() % 10 +   //版本即最终用户可见的名称：The end-user-visible name for the end product.
                Build.PRODUCT.length() % 10;  //整个产品的名称：The name of the overall product.
        return m_szDevIDShort;
    }

    /**
     * The Android ID
     * 通常被认为不可信，因为它有时为null。开发文档中说明了：这个ID会改变如果进行了出厂设置。并且，如果某个
     * Andorid手机被Root过的话，这个ID也可以被任意改变。无需任何许可。
     *
     * @return AndroidID
     */
    public static String getAndroidID(Context mContext) {
        String m_szAndroidID = Settings.Secure.getString(mContext.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        return m_szAndroidID;
    }

    /**
     * 获取安装时间
     *
     * @param context
     * @return
     */
    public static String getInstalledTime(Context context) {
        if (StringUtil.isBlank(installedTime)) {
            PackageManager packageManager = context.getPackageManager();
            try {
                PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                //应用第一次安装的时间
                long firstInstallTime = packageInfo.firstInstallTime;
                installedTime = sdf.format(firstInstallTime);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        return installedTime;
    }

    /**
     * 获取当前设备信息
     *
     * @return
     */
    public static String getDeviceName() {
        try {
            return Build.MODEL;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 获取当前设备生产商
     *
     * @return
     */
    public static String getDeviceMake() {
        try {
            return Build.MANUFACTURER;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 获取当前设备品牌
     *
     * @return
     */
    public static String getDeviceBrand() {
        try {
            return Build.BRAND;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 获取当前设备时间
     *
     * @return
     */
    public static long getDeviceTime() {
        try {
            return Build.TIME;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取视频宽
     *
     * @param videoPath
     * @return
     */
    public static int getVideoWidth(String videoPath) {
        MediaMetadataRetriever retr = new MediaMetadataRetriever();
        retr.setDataSource(videoPath);
        String width = retr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH); // 视频宽度
        String height = retr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT); // 视频高度
        String rotation = retr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_ROTATION);
        if ("90".equals(rotation) || "270".equals(rotation)) {//当视频是竖屏的时候 orientation = 90，横屏 orientation = 0 ，正确转换宽高
            width = height;
        }
        retr.release();
        return Integer.parseInt(width);
    }

    /**
     * 获取视频高
     *
     * @param videoPath
     * @return
     */
    public static int getVideoHeight(String videoPath) {
        MediaMetadataRetriever retr = new MediaMetadataRetriever();
        retr.setDataSource(videoPath);
        String width = retr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH); // 视频宽度
        String height = retr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT); // 视频高度
        String rotation = retr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_ROTATION);
        if ("90".equals(rotation) || "270".equals(rotation)) {//当视频是竖屏的时候 orientation = 90，横屏 orientation = 0 ，正确转换宽高
            height = width;
        }
        retr.release();
        return Integer.parseInt(height);
    }


//    /**
//     * 获取手机内部存储空间
//     *
//     * @param context
//     * @return 以M, G为单位的容量
//     */
//    public static String getInternalMemorySize(Context context) {
//        File file = Environment.getDataDirectory();
//        StatFs statFs = new StatFs(file.getPath());
//        long blockSizeLong = statFs.getBlockSizeLong();
//        long blockCountLong = statFs.getBlockCountLong();
//        long size = blockCountLong * blockSizeLong;
//        return Formatter.formatFileSize(context, size);
//    }
//
//    /**
//     * 获取手机内存空间
//     *
//     * @param context
//     * @return 以M, G为单位的容量
//     */
//    public static String getMemoryInfo(Context context) {
//        ActivityManager am = (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);
//        ActivityManager.MemoryInfo outInfo = new ActivityManager.MemoryInfo();
//        am.getMemoryInfo(outInfo);
////        long availMem = outInfo.availMem;
//        long totalMem = outInfo.totalMem;
////        Log.i(TAG, "availMem = " + Formatter.formatFileSize(context, availMem));
////        Log.i(TAG, "totalMem = " + Formatter.formatFileSize(context, totalMem));
//        return Formatter.formatFileSize(context, totalMem);
//    }
//
//    /**
//     * 获取cpu核数
//     *
//     * @return
//     */
//    public static int getNumberOfCPUCores() {
//        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.GINGERBREAD_MR1) {
//            // Gingerbread doesn't support giving a single application access to both cores, but a
//            // handful of devices (Atrix 4G and Droid X2 for example) were released with a dual-core
//            // chipset and Gingerbread; that can let an app in the background run without impacting
//            // the foreground application. But for our purposes, it makes them single core.
//            return 1;
//        }
//        int cores;
//        try {
//            cores = new File("/sys/devices/system/cpu/").listFiles(CPU_FILTER).length;
//        } catch (SecurityException e) {
//            cores = -1;
//        } catch (NullPointerException e) {
//            cores = -1;
//        }
//        return cores;
//    }
//
//    private static final FileFilter CPU_FILTER = new FileFilter() {
//        @Override
//        public boolean accept(File pathname) {
//            String path = pathname.getName();
//            //regex is slow, so checking char by char.
//            if (path.startsWith("cpu")) {
//                for (int i = 3; i < path.length(); i++) {
//                    if (path.charAt(i) < '0' || path.charAt(i) > '9') {
//                        return false;
//                    }
//                }
//                return true;
//            }
//            return false;
//        }
//    };
//
//    /**
//     * 获取CPU最大频率（单位KHZ）
//     *
//     * @return
//     */
//    public static String getMaxCpuFreq() {
//        String result = "";
//        ProcessBuilder cmd;
//        try {
//            String[] args = {"/system/bin/cat",
//                    "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq"};
//            cmd = new ProcessBuilder(args);
//            Process process = cmd.start();
//            InputStream in = process.getInputStream();
//            byte[] re = new byte[24];
//            while (in.read(re) != -1) {
//                result = result + new String(re);
//            }
//            in.close();
//        } catch (IOException ex) {
//            ex.printStackTrace();
//            result = "N/A";
//        }
//        int maxFreq = Integer.parseInt(result.trim()) / (1024);
//        return String.valueOf(maxFreq);
//    }
//
//    /**
//     * 获取CPU最小频率（单位KHZ）
//     *
//     * @return
//     */
//    public static String getMinCpuFreq() {
//        String result = "";
//        ProcessBuilder cmd;
//        try {
//            String[] args = {"/system/bin/cat",
//                    "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_min_freq"};
//            cmd = new ProcessBuilder(args);
//            Process process = cmd.start();
//            InputStream in = process.getInputStream();
//            byte[] re = new byte[24];
//            while (in.read(re) != -1) {
//                result = result + new String(re);
//            }
//            in.close();
//        } catch (IOException ex) {
//            ex.printStackTrace();
//            result = "N/A";
//        }
//        int minFreq = Integer.parseInt(result.trim()) / (1024);
//        return String.valueOf(minFreq);
//    }


    /**
     * 获取当前系统的版本号
     *
     * @return
     */
    public static String getOsVersion() {
        try {
            return android.os.Build.VERSION.RELEASE;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 获取当前网络信息
     *
     * @return
     */
    public static String getNetworkType(Context context) {
        String strNetworkType = "";

        NetworkInfo networkInfo = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                strNetworkType = "WIFI";
            } else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                String strSubTypeName = networkInfo.getSubtypeName();
                // TD-SCDMA   networkType is 17
                int networkType = networkInfo.getSubtype();
                switch (networkType) {
                    case TelephonyManager.NETWORK_TYPE_GPRS:
                    case TelephonyManager.NETWORK_TYPE_EDGE:
                    case TelephonyManager.NETWORK_TYPE_CDMA:
                    case TelephonyManager.NETWORK_TYPE_1xRTT:
                    case TelephonyManager.NETWORK_TYPE_IDEN: //api<8 : replace by 11
                        strNetworkType = "2G";
                        break;
                    case TelephonyManager.NETWORK_TYPE_UMTS:
                    case TelephonyManager.NETWORK_TYPE_EVDO_0:
                    case TelephonyManager.NETWORK_TYPE_EVDO_A:
                    case TelephonyManager.NETWORK_TYPE_HSDPA:
                    case TelephonyManager.NETWORK_TYPE_HSUPA:
                    case TelephonyManager.NETWORK_TYPE_HSPA:
                    case TelephonyManager.NETWORK_TYPE_EVDO_B: //api<9 : replace by 14
                    case TelephonyManager.NETWORK_TYPE_EHRPD:  //api<11 : replace by 12
                    case TelephonyManager.NETWORK_TYPE_HSPAP:  //api<13 : replace by 15
                        strNetworkType = "3G";
                        break;
                    case TelephonyManager.NETWORK_TYPE_LTE:    //api<11 : replace by 13
                        strNetworkType = "4G";
                        break;
                    default:
                        // http://baike.baidu.com/item/TD-SCDMA 中国移动 联通 电信 三种3G制式
                        if (strSubTypeName.equalsIgnoreCase("TD-SCDMA") || strSubTypeName.equalsIgnoreCase("WCDMA") || strSubTypeName.equalsIgnoreCase("CDMA2000")) {
                            strNetworkType = "3G";
                        } else {
                            strNetworkType = strSubTypeName;
                        }

                        break;
                }

            }
        }
        return strNetworkType;
    }


    /**
     * 获取当前进程名称
     *
     * @param context
     * @return
     */
    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();

        ActivityManager mActivityManager = (ActivityManager) context
                .getSystemService(ACTIVITY_SERVICE);

        for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager
                .getRunningAppProcesses()) {
            if (appProcess.pid == pid) {

                return appProcess.processName;
            }
        }
        return null;
    }


    /**
     * 还原View的触摸和点击响应范围,最小不小于View自身范围
     *
     * @param view
     */
    public static void restoreViewTouchDelegate(final View view) {

        ((View) view.getParent()).post(new Runnable() {
            @Override
            public void run() {
                Rect bounds = new Rect();
                bounds.setEmpty();
                TouchDelegate touchDelegate = new TouchDelegate(bounds, view);

                if (View.class.isInstance(view.getParent())) {
                    ((View) view.getParent()).setTouchDelegate(touchDelegate);
                }
            }
        });
    }

    /**
     * 扩大View的触摸和点击响应范围,最大不超过其父View范围
     *
     * @param view
     * @param top
     * @param bottom
     * @param left
     * @param right
     */
    public static void expandViewTouchDelegate(final View view, final int top,
                                               final int bottom, final int left, final int right) {

        ((View) view.getParent()).post(new Runnable() {
            @Override
            public void run() {
                Rect bounds = new Rect();
                view.setEnabled(true);
                view.getHitRect(bounds);

                bounds.top -= top;
                bounds.bottom += bottom;
                bounds.left -= left;
                bounds.right += right;

                TouchDelegate touchDelegate = new TouchDelegate(bounds, view);

                if (View.class.isInstance(view.getParent())) {
                    ((View) view.getParent()).setTouchDelegate(touchDelegate);
                }
            }
        });
    }

    public static List removeDuplicate(List list) {
        HashSet h = new HashSet(list);
        list.clear();
        list.addAll(h);
        return list;
    }

    public static int getNavigationBarHeight(Context context) {
        if (checkDeviceHasNavigationBar(context)) {
            // 做任何你需要做的,这个设备有一个导航栏
            Resources resources = context.getResources();
            int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
            int height = resources.getDimensionPixelSize(resourceId);
            Log.v("dbw", "Navi height:" + height);
            return height;
        }
        return 0;
    }

    /**
     * 检查设备是否有导航栏
     *
     * @return
     */
    @SuppressLint("NewApi")
    private static boolean checkDeviceHasNavigationBar(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            Display display = ((Activity) context).getWindowManager().getDefaultDisplay();
            Point size = new Point();
            Point realSize = new Point();
            display.getSize(size);
            display.getRealSize(realSize);
            return realSize.y != size.y;
        } else {
            boolean menu = ViewConfiguration.get(context).hasPermanentMenuKey();
            boolean back = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK);
            return !(menu || back);
        }
    }

    private static int getKeyboardHight(Activity activity) {
        Rect r = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(r);
        int heightDiff = r.bottom - r.top;
        //获得屏幕整体的高度
        int hight = activity.getWindow().getDecorView().getRootView().getHeight();
        //获得键盘高度
        return hight - heightDiff;
    }


    /**
     * 判断键盘
     */
    public static void controlKeyboardLayout(final Activity activity, final View view) {
        final int keyHeight = activity.getWindowManager().getDefaultDisplay().getHeight() / 3;
        activity.getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (getKeyboardHight(activity) > keyHeight) {
                    view.setVisibility(View.GONE);
                } else {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            view.setVisibility(View.VISIBLE);
                        }
                    }, 50);
                }
            }
        });
    }

    // Convert a view to bitmap
    public static Bitmap createDrawableFromView(Context context, View view) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        view.measure(displayMetrics.widthPixels, displayMetrics.heightPixels);
        view.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
        view.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }


    public static String getDistance(double distance) {
        if (distance < 1000) {
            return Math.ceil(distance) + "m";
        }
        return String.format(Locale.ENGLISH,"%.1f", distance / 1000) + "km";
    }
    public static String getDistance2(double distance) {
        if (distance < 1000) {
            return String.format(Locale.ENGLISH,"%.1f", distance) + "m";
        }
        return String.format(Locale.ENGLISH,"%.1f", distance / 1000) + "km";
    }
    public static String getNum(int count) {
        if (count >= 1000) {
            return String.format(Locale.ENGLISH,"%.1f", count * 1.0 / 1000) + "k";
        }
        return count + "";
    }



//    private int firstImgs[] = {R.mipmap.ktv, R.mipmap.mall,
//            R.mipmap.game, R.mipmap.internet_cafe,
//            R.mipmap.art, R.mipmap.bar,
//            R.mipmap.bath, R.mipmap.sport};
//    private int firstStr[] = {R.string.ktv, R.string.lounge,
//            R.string.game, R.string.entertainment,
//            R.string.art, R.string.bar, R.string.jakusi, R.string.fitness};
//    private int firstImgs2[] = {R.mipmap.nail, R.mipmap.manicure,
//            R.mipmap.wax, R.mipmap.eyelashes,
//            R.mipmap.beauty_, R.mipmap.skin_care,
//            R.mipmap.care, R.mipmap.spa};
//    private int firstStr2[] = {R.string.nail, R.string.manicure, R.string.wax, R.string.eyelashes,
//            R.string.face, R.string.skin_care, R.string.care, R.string.SPA};



    /**
     * 计算ListView内容高度
     */
    public static void setListViewHeight(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) { // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0); // 计算子项View 的宽高
            totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        int height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        params.height = height + listView.getPaddingTop() + listView.getPaddingBottom();
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }

  /*  *//**
     * 判断是否有特殊字符
     *
     * @param
     * @return
     *//*
   public static boolean hasEmoji(String content) {
        Matcher matcher = PatternUtil.SPECIAL_CHARACTERS.matcher(content);
        if (matcher.find()) {
            return true;
        }
        return false;
    }*/

    public static String replaceCharacter(String str) {
        return str.replaceAll("\n", "");
    }

    /**
     * 拨打电话（直接拨打电话）
     *
     * @param phoneNum 电话号码
     */
    public static void callPhone(Context context, String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ToastUtil.showToast(App.getAPPName() + "\"打开电话权限");
            return;
        }
        context.startActivity(intent);
    }

    public static String getBaseUrl() {
        if (App.getConfig().getBaseUrl().contains("192.168.0.99")||App.getConfig().getBaseUrl().contains("nothreenofour.51vip.biz")) {
            return "http://192.168.0.99/dzdph5";
        }
        if (App.getConfig().getBaseUrl().contains("t.100")) {
            return "https://t.100baoxian.com/dzdpH5";
        }
        if (App.getConfig().getBaseUrl().contains("47.74.178.77:8081")) {
            return "https://api.recomeapp.com/h5_pre";
        }
        return "https://api.recomeapp.com/h5";
       // return "https://api.recomeapp.com/h5_pre";
    }

    public static String getShopBaseUrl() {
        if (App.getConfig().getBaseUrl().contains("192.168.0.99")) {
            return "http://192.168.0.99/RecommandMe";
        }
        if (App.getConfig().getBaseUrl().contains("t.100")) {
            return "https://t.100baoxian.com/RecommandMe";
        }
        if (App.getConfig().getBaseUrl().contains("47.74.178.77:8081")) {
            return "https://api.recomeapp.com/recome2";
        }
        return "https://api.recomeapp.com/recome";
    }



    public static String getAppMetaData(Context ctx, String key) {
        if (ctx == null || TextUtils.isEmpty(key)) {
            return null;
        }
        String resultData = null;
        try {
            PackageManager packageManager = ctx.getPackageManager();
            if (packageManager != null) {
                //注意此处为ApplicationInfo，因为友盟设置的meta-data是在application标签中
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(ctx.getPackageName(), PackageManager.GET_META_DATA);
                if (applicationInfo != null) {
                    if (applicationInfo.metaData != null) {
                        //key要与manifest中的配置文件标识一致
                        resultData = applicationInfo.metaData.getString(key);
                    }
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return resultData;
    }

    public static void rateNow(final Context context, String PackageName) {
        try {

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("market://details?id=" +PackageName));
            if (intent.resolveActivity(context.getPackageManager()) != null) {
                context.startActivity(intent);
            } else {//没有应用市场，通过浏览器跳转到Google Play
                Intent intent2 = new Intent(Intent.ACTION_VIEW);
                intent2.setData(Uri.parse("https://play.google.com/store/ereview?docId=" + PackageName.toString().trim()));
                if (intent2.resolveActivity(context.getPackageManager()) != null) {
                    context.startActivity(intent2);
                } else {
                    //没有Google Play 也没有浏览器
                }
            }
        } catch (ActivityNotFoundException activityNotFoundException1) {
        }
    }

    public static String addComma(String str, EditText edtext){

        touzi_ed_values22 = edtext.getText().toString().trim().replaceAll(",","");

        boolean neg = false;
        if (str.startsWith("-")){  //处理负数
            str = str.substring(1);
            neg = true;
        }
        String tail = null;
        if (str.indexOf('.') != -1){ //处理小数点
            tail = str.substring(str.indexOf('.'));
            str = str.substring(0, str.indexOf('.'));
        }
        StringBuilder sb = new StringBuilder(str);
        sb.reverse();
        for (int i = 3; i < sb.length(); i += 4){
            sb.insert(i, ',');
        }
        sb.reverse();
        if (neg){
            sb.insert(0, '-');
        }
        if (tail != null){
            sb.append(tail);
        }
        return sb.toString();
    }

    public static String getValue(String str){
        String[] s = str.split(",");
        String value = "";
        for (int i=0;i<s.length;i++){
            value += s[i];
        }
        return value;
    }

    public static String ToSBC(String input) {
            char c[] = input.toCharArray();
    for (int i = 0; i < c.length; i++) {
        if (c[i] == ' ') {
         c[i] = '\u3000';
        } else if (c[i] < '\177') {
                   c[i] = (char) (c[i] + 65248);
        }
 }
        return new String(c);
}


    public static ArrayList<TagsInfoVoBean> removeDuplicteUsers(ArrayList<TagsInfoVoBean> userList) {
        Set<TagsInfoVoBean> s = new TreeSet<TagsInfoVoBean>(new Comparator<TagsInfoVoBean>() {

            @Override
            public int compare(TagsInfoVoBean o1, TagsInfoVoBean o2) {
                return o1.getTagName().compareTo(o2.getTagName());
            }
        });
        s.addAll(userList);
        return new ArrayList<TagsInfoVoBean>(s);
    }

}
