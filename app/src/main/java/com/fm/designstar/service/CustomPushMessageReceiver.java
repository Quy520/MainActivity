package com.fm.designstar.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import io.rong.push.PushType;
import io.rong.push.notification.PushMessageReceiver;
import io.rong.push.notification.PushNotificationMessage;

public class CustomPushMessageReceiver extends PushMessageReceiver {
    public CustomPushMessageReceiver() {
    }

    @Override
    public boolean onNotificationMessageArrived(Context context, PushType pushType, PushNotificationMessage pushNotificationMessage) {
        return false;
    }

    @Override
    public boolean onNotificationMessageClicked(Context context, PushType pushType, PushNotificationMessage pushNotificationMessage) {
        // 可通过 pushType 判断 Push 的类型
        if (pushType == PushType.XIAOMI) {
            //实现您自定义的通知点击跳转逻辑
            return true; // 此处返回 true. 代表不触发 SDK 默认实现，您自定义处理通知点击跳转事件。
        } else if (pushType == PushType.HUAWEI) {
            //实现您自定义的通知点击跳转逻辑
            return true; // 此处返回 true. 代表不触发 SDK 默认实现，您自定义处理通知点击跳转事件。
        } else if (pushType == PushType.MEIZU) {
            //实现您自定义的通知点击跳转逻辑
            return true; // 此处返回 true. 代表不触发 SDK 默认实现，您自定义处理通知点击跳转事件。
        } else if (pushType == PushType.VIVO) {
            //实现您自定义的通知点击跳转逻辑
            return true; // 此处返回 true. 代表不触发 SDK 默认实现，您自定义处理通知点击跳转事件。
        } else if (pushType == PushType.OPPO) {
            //实现您自定义的通知点击跳转逻辑
            return true; // 此处返回 true. 代表不触发 SDK 默认实现，您自定义处理通知点击跳转事件。

        } else if (pushType == PushType.GOOGLE_FCM) {
            //实现您自定义的通知点击跳转逻辑
            return true; // 此处返回 true. 代表不触发 SDK 默认实现，您自定义处理通知点击跳转事件。
        } else if (pushType == PushType.RONG) {
            //实现您自定义的通知点击跳转逻辑
            return true; // 此处返回 true. 代表不触发 SDK 默认实现，您自定义处理通知点击跳转事件。
        }
        return false;
    }



}
