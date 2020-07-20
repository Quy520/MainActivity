package com.fm.designstar.utils;


import android.app.Activity;
import android.app.AlertDialog;
import android.os.Build;
import android.view.View;
import android.view.Window;

import com.fm.designstar.R;


/**
 * @author DELL
 */
public class ViewUtil {
    /**
     * 自定义内容加载提示窗
     */
    private static AlertDialog loadingDialog;
//    private static View loadingView;
    private static boolean cancelable = false;

    public static void showLoading(Activity activity, String content) {
        try {
            if (Util.ActivityIsClose(activity)) {
                return;
            }
            if (content == null) {
                return;
            }
            hideLoading();
            loadingDialog = new AlertDialog.Builder(activity, R.style.alert_dialog).create();
            loadingDialog.setCancelable(cancelable);
            loadingDialog.setCanceledOnTouchOutside(false);
            loadingDialog.show();
            if (loadingDialog == null) {
                return;
            }
            Window window = loadingDialog.getWindow();
//            if (loadingView != null) {
//                window.setContentView(R.layout.layout_loading);
//                LinearLayout view = window.findViewById(R.id.loading_content);
//                view.addView(loadingView);
//            } else {
         //   window.setContentView( R.layout.widget_loading_page);
                window.setContentView(R.layout.layout_dialog_progress);
                //适配大小
                int screenWidth = Util.getScreenWidth(activity);
               // TextView contentView = window.findViewById(R.id.contentView);
               // contentView.setText(content);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setCancelable(boolean flag) {
        cancelable = flag;
    }

    public static void hideSystemUI(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

//    public static void setLoadingView(Context context, View view) {
//        loadingView = view;
//    }

    /*******
     * 关闭loading
     */
    public static void hideLoading() {
        try {
            if (loadingDialog != null) {
                if (Util.ActivityIsClose(loadingDialog.getContext())) {
                    return;
                }
                if (loadingDialog.isShowing()) {
                    loadingDialog.dismiss();
                }
//                if (loadingView != null) {
//                    ((LinearLayout) (loadingDialog.getWindow().findViewById(R.id.loading_content))).removeAllViews();
//                }
                loadingDialog = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isShowing() {
        if (loadingDialog != null) {
            if (loadingDialog.isShowing()) {
                return true;
            }
        }
        return false;
    }
}
