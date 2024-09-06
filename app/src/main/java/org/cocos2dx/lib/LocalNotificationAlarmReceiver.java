package org.cocos2dx.lib;

import android.R;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.tencent.mm.opensdk.modelmsg.WXVideoFileObject;

/* loaded from: classes.dex */
public class LocalNotificationAlarmReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        extras.getInt(LocalNotificationManager.KEY_NOTIFICATION_ID);
        String string = extras.getString(LocalNotificationManager.KEY_POPUP_HEADER);
        String string2 = extras.getString(LocalNotificationManager.KEY_MSG_TITLE);
        String string3 = extras.getString(LocalNotificationManager.KEY_MSG_BODY);
        Intent intent2 = new Intent(context, (Class<?>) extras.getSerializable(LocalNotificationManager.KEY_TARGET_ACTIVITY));
        intent2.setFlags(536870912);
        PendingIntent.getActivity(context, 0, intent2, WXVideoFileObject.FILE_SIZE_LIMIT);
        Notification notification = new Notification(LocalNotificationManager.iconId != 0 ? LocalNotificationManager.iconId : R.drawable.ic_notification_overlay, string, System.currentTimeMillis());
        notification.defaults |= 1;
        notification.flags |= 16;
        Notification.Builder builder = new Notification.Builder(context);
        builder.setContentTitle(string2);
        builder.setContentText(string3);
        builder.setContentIntent(PendingIntent.getActivity(context, 0, intent, 0));
    }
}
