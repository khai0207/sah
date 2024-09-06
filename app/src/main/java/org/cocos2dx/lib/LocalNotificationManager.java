package org.cocos2dx.lib;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.widget.Toast;
import androidx.core.app.NotificationCompat;
import com.netease.nimlib.amazonaws.mobileconnectors.s3.transferutility.TransferService;
import com.tencent.mm.opensdk.modelmsg.WXVideoFileObject;
import java.util.Calendar;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
public class LocalNotificationManager {
    public static final String ACTION_STUB = "com.kaiqigu.notification.actionfilter";
    public static final String KEY_MSG_BODY = "notif_body";
    public static final String KEY_MSG_TITLE = "notif_title";
    public static final String KEY_NOTIFICATION_ID = "notif_type";
    public static final String KEY_POPUP_HEADER = "notif_header";
    public static final String KEY_TARGET_ACTIVITY = "notif_activity";
    private static AtomicInteger atomInt;
    private static LocalNotificationManager instance = new LocalNotificationManager();
    public static int iconId = 0;
    private static Activity activity = null;

    private LocalNotificationManager() {
        atomInt = new AtomicInteger(0);
    }

    public static LocalNotificationManager getInstance() {
        return instance;
    }

    public void setIconId(int i) {
        iconId = i;
    }

    public void setActivity(Activity activity2) {
        activity = activity2;
    }

    protected static void scheduleLocalNotification(int i, String str, String str2, String str3, Activity activity2, Class cls, long j, boolean z) {
        Intent intent = new Intent(activity2.getApplicationContext(), (Class<?>) LocalNotificationAlarmReceiver.class);
        intent.setAction(ACTION_STUB + i);
        intent.putExtra(KEY_NOTIFICATION_ID, i);
        intent.putExtra(KEY_POPUP_HEADER, str);
        intent.putExtra(KEY_MSG_TITLE, str2);
        intent.putExtra(KEY_MSG_BODY, str3);
        intent.putExtra(KEY_TARGET_ACTIVITY, cls);
        ((AlarmManager) activity2.getSystemService(NotificationCompat.CATEGORY_ALARM)).set(0, j, PendingIntent.getBroadcast(activity2.getApplicationContext(), 0, intent, WXVideoFileObject.FILE_SIZE_LIMIT));
        if (z) {
            Toast.makeText(activity2.getApplicationContext(), j + "触发通知", 1).show();
        }
        activity2.sendBroadcast(intent, "setalaram");
    }

    protected static void scheduleLocalNotification(int i, String str, String str2, String str3, Activity activity2, Class cls, long j) {
        scheduleLocalNotification(i, str, str2, str3, activity2, cls, j, false);
    }

    public static void scheduleLocalNotification(int i, String str, String str2, String str3, int i2) {
        if (activity == null) {
            return;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.add(13, i2);
        System.out.println("notification in seconds " + i2);
        Activity activity2 = activity;
        scheduleLocalNotification(i, str, str2, str3, activity2, activity2.getClass(), calendar.getTimeInMillis());
    }

    public static void cancelLocalNotification(int i) {
        Activity activity2 = activity;
        if (activity2 == null) {
            return;
        }
        ((NotificationManager) activity2.getSystemService(TransferService.INTENT_KEY_NOTIFICATION)).cancel(i);
        Intent intent = new Intent(activity, (Class<?>) LocalNotificationAlarmReceiver.class);
        intent.setAction(ACTION_STUB + i);
        ((AlarmManager) activity.getSystemService(NotificationCompat.CATEGORY_ALARM)).cancel(PendingIntent.getBroadcast(activity, 0, intent, 268435456));
    }
}
