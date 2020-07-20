package com.fm.designstar.https;


import android.app.Activity;
import android.content.Intent;

import com.fm.designstar.utils.LogUtils;

import org.greenrobot.eventbus.EventBus;

import java.net.SocketTimeoutException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * @class description: 网络请求处理
 * @author: yinsujun
 */
public abstract class AbstractHttpSubscriber<T> extends Subscriber<T> {
    /**
     * 默认异常
     */
    private final int ERROR_DEFAULT = -1;
//    /**
//     * 登录失效
//     */
//    private final int ERROR_NOT_LOGIN = -2;
//    /**
//     * 请求繁忙
//     */
//    private final int ERROR_BUSY = -3;
    /**
     * 无网络
     */
    private final int ERROR_NETWORK = -4;
    /**
     * 请求超时
     */
    private final int ERROR_TIME_OUT = -5;
    /**
     * 请先登录
     */
    private final int NO_LOGIN = 1007;
    /**
     * 账号或密码错误
     */
    private final int ACCOUNT_PASSWORD_ERR = 1005;
    /**
     * 初始密码，需要修改
     */
    private final int TO_SET_PWD = 1008;
    /**
     * 没有权限
     */
    private final int PERMISSION_DENIED = 3000;

    /**
     * 请求参数不合法
     */
    private final int REQUEST_PARAMETER_INVALID = 3001;
    /**
     * 当前版本太低
     */
    private final int VERSION_TOO_LOW = 3002;
//    /**
//     * 已经签到过
//     */
//    public final int ALREADY_SIGNED_UP = 3003;
    /**
     * 服务器异常
     */
    private final int SERVICE_ERROR = 5000;
//
//    /**
//     * 强制升级升级 0129 add
//     */
//    private final int FORCED_UPGRADE = 3020;
//    /**
//     * 异地登陆 0129 add
//     */
//    private final int LANDING_OFF = 3010;
   private final int FORCED_UPGRADE = 3020;

    private final int SHOP_OFF = 1012;

    private final int JIFEN_OFF = 1013;

    private final int KUCUN_OFF = 1014;
    private final int EXCHANGE_OFF = 1015;
    private final int FOBID_OFF = 1022;


    public AbstractHttpSubscriber() {
    }

    @Override
    public void onStart() {
        super.onStart();
        onHttpStart();
    }

    @Override
    public void onCompleted() {
        onHttpCompleted();
    }

    @Override
    public void onNext(T t) {
        onHttpNext(t);
    }

    @Override
    public void onError(Throwable e) {
 /*       LogUtils.loge(e.getMessage());
        if (!NetUtil.isConnected(App.getContext())) {
            onHttpError(HttpErrorMessage.NET_ERROR_MESSAGE, ERROR_NETWORK);
        } else if (e instanceof ApiException) {
            if (((ApiException) e).getCode() == NO_LOGIN) {
                //请先登录
                EventBus.getDefault().removeStickyEvent(LogoutEvent.class);
                EventBus.getDefault().post(new LogoutEvent(0));
                onHttpError(HttpErrorMessage.NET_LOGON_ERROR, ((ApiException) e).getCode());
            } else if (((ApiException) e).getCode() == TO_SET_PWD) {
                Activity activity = AppManager.getInstance().currentActivity();
                if (activity != null) {
                    if (!StringUtil.isBlank(App.getConfig().getUserPhone())) {
                        if (!SpUtil.getBoolean(Constant.IS_OTHER_LOGIN, false)) {
                            Intent intent = new Intent(activity, ChangePwdActivity.class);
                            activity.startActivity(intent);
                        }
                    }
                }
            } else if (((ApiException) e).getCode() == VERSION_TOO_LOW) {
                EventBus.getDefault().removeStickyEvent(VersionEvent.class);
                EventBus.getDefault().post(new VersionEvent());
                onHttpError(e.getMessage(), ((ApiException) e).getCode());
            }else if (((ApiException) e).getCode() == JIFEN_OFF) {
                onHttpError(e.getMessage(), ((ApiException) e).getCode());
            }else if (((ApiException) e).getCode() == KUCUN_OFF) {
                onHttpError(e.getMessage(), ((ApiException) e).getCode());
            }else if (((ApiException) e).getCode() == EXCHANGE_OFF) {
                onHttpError(e.getMessage(), ((ApiException) e).getCode());
            }else if (((ApiException) e).getCode() == FOBID_OFF){
                onHttpError(e.getMessage(), ((ApiException) e).getCode());
               *//* JSONObject demoJson = null;
                try {
                    demoJson = new JSONObject(((ApiException) e).getData());
                    String url = demoJson.getString("url");
                    SpUtil.putString("URL",url+"");
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }*//*

               //
            } else {
                if (((ApiException) e).getCode() == 0) {
                    if (!StringUtil.isBlank(((ApiException) e).getCodeStr())) {
                        onHttpError(e.getMessage(), ((ApiException) e).getCodeStr(), ((ApiException) e).getCode());
                    } else {
                        onHttpError(e.getMessage(), ((ApiException) e).getCode());
                    }
                } else {
                    onHttpError(e.getMessage(), ((ApiException) e).getCode());
                }
            }
        } else if (e instanceof HttpException) {
            HttpException exception = (HttpException) e;
            if (exception.code() == NO_LOGIN) {
                //清空
                EventBus.getDefault().removeStickyEvent(LogoutEvent.class);
                EventBus.getDefault().post(new LogoutEvent(0));
                onHttpError(HttpErrorMessage.NET_LOGON_ERROR, exception.code());
            } else if (exception.code() == SERVICE_ERROR) {
                //"服务器异常，请稍后重试"
                onHttpError(Constant.LOADING_FAIL, ERROR_DEFAULT);
            } else {
                //"连接服务器失败，请稍后再试"
                onHttpError(Constant.LOADING_FAIL, ERROR_DEFAULT);
            }
        } else if (e instanceof SocketTimeoutException) {
            //连接超时，稍后重试
            onHttpError(HttpErrorMessage.NET_ERROR_TIME_OUT, ERROR_TIME_OUT);
        } else {
            //"连接服务器失败，请稍后再试"
            onHttpError(Constant.LOADING_FAIL, ERROR_DEFAULT);
        }
        onHttpCompleted();*/
    }

    /**
     * 请求开始
     */
    protected abstract void onHttpStart();

    /**
     * 请求成功调用方法
     *
     * @param t 成功返回数据
     */
    protected abstract void onHttpNext(T t);

    /**
     * 请求异常调用方法
     *
     * @param message 异常信息
     */
    protected abstract void onHttpError(String message);

    /**
     * 请求完成调用方法
     */
    protected abstract void onHttpCompleted();

    protected void onHttpError(String message, int code) {
        onHttpError(message);
    }

    protected void onHttpError(String message, String codeStr, int code) {
        onHttpError(message, code);
    }
}
