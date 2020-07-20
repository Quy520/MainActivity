package com.fm.designstar.utils;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fm.designstar.R;
import com.fm.designstar.app.AppManager;
import com.google.android.material.snackbar.Snackbar;

import androidx.fragment.app.FragmentActivity;


/**
 * @author DELL
 */
public class ToastUtil {

    /**
     * toast对象
     */
    public static final int GRAVITY_LEFT = 1;

    public static final int GRAVITY_RIGHT = 2;

    public static final int GRAVITY_TOP = 3;

    public static final int GRAVITY_BOTTOM = 4;
    private static Toast toast = null;

    private static Context mContext;
    private static Handler handler = new Handler(Looper.getMainLooper());


    /**
     * 程序入口注册
     *
     * @param context
     */
    public static void register(Context context) {
        mContext = context;
    }

    /**Maaf saat ini server kami sedang sibuk
     * 描述：Toast提示文本.
     *
     * @param text 文本
     */
    public static void showToast(final String text) {
        if (StringUtil.isBlank(text)) {
            return;
        }
        if (Build.VERSION.SDK_INT != Build.VERSION_CODES.N_MR1 && Build.VERSION.SDK_INT != Build.VERSION_CODES.N) {
            if (!Util.ActivityIsClose(mContext)) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (toast == null) {
                                toast = Toast.makeText(mContext, text, Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                            } else {

                                toast.setText(text);
                            }
                            toast.show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                return;
            }
        }
        FragmentActivity activity = AppManager.getInstance().currentActivity();
        if (!Util.ActivityIsClose(activity)) {
            Snackbar.make(activity.findViewById(android.R.id.content), text, Snackbar.LENGTH_SHORT).show();
        }
    }


    /**
     * 描述：Snackbar提示文本.
     *
     * @param resId 文本的资源ID
     */
    public static void showToast(int resId) {
        if (mContext == null) {
            return;
        }
        showToast(mContext.getString(resId));
    }
    /**
     * 描述：Snackbar提示文本.
     *
     * @param resId 文本的资源ID
     */
    public static void showToast(final int resId, final int gravity) {
        if (mContext == null) {
            return;
        }
        if (Build.VERSION.SDK_INT != Build.VERSION_CODES.N_MR1 && Build.VERSION.SDK_INT != Build.VERSION_CODES.N) {
            if (!Util.ActivityIsClose(mContext)) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            toast = Toast.makeText(mContext, mContext.getString(resId), Toast.LENGTH_SHORT);
                            LayoutInflater inflate = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                            View v = inflate.inflate(R.layout.toast_view, null);
                            TextView textView=v.findViewById(R.id.message);
                            ImageView imageView=v.findViewById(R.id.image);

                            textView.setText(mContext.getString(resId));
                            textView.setGravity(Gravity.CENTER);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.setView(v);
                            toast.show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                return;
            }
        }
    }

}
