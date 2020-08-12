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
        return false;
    }


}
