package com.yzf.king.kit;

import android.os.Build;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.support.annotation.RequiresApi;

/**
 * @author AigeStudio
 * @since 2016-05-05
 */
@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
public class DService extends NotificationListenerService {
    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
    }
}