package com.fm.designstar.service;

import android.content.Context;

import com.huawei.hms.support.api.entity.core.CommonCode;

import cn.jpush.android.service.JPushMessageReceiver;
import io.rong.push.PushType;
import io.rong.push.notification.PushMessageReceiver;
import io.rong.push.notification.PushNotificationMessage;

public class SealNotificationReceiver extends PushMessageReceiver {
    public static boolean needUpdate = false;

    @Override
    public boolean onNotificationMessageArrived(Context context, PushType pushType, PushNotificationMessage message) {
        return false;
    }

    @Override
    public boolean onNotificationMessageClicked(Context context, PushType pushType, PushNotificationMessage message) {
        return false;
    }
    //华为获取 token 异常回调此方法
    @Override
    public void onThirdPartyPushState(PushType pushType, String action, long resultCode) {
        super.onThirdPartyPushState(pushType, action, resultCode);
        if (pushType.equals(PushType.HUAWEI)) {
            if (resultCode == CommonCode.ErrorCode.CLIENT_API_INVALID) {
                //设置标记位，引导用户升级
                needUpdate = true;
            }
        }
    }
}