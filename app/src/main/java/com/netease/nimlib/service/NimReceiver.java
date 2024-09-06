package com.netease.nimlib.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import com.netease.nimlib.c;
import com.netease.nimlib.h;

/* loaded from: classes.dex */
public class NimReceiver extends BroadcastReceiver {
    public static void a(Context context) {
        PendingIntent broadcast;
        if (c.H() || c.s() || c.G()) {
            return;
        }
        com.netease.nimlib.log.b.c(NotificationCompat.CATEGORY_SERVICE, "start repeating alarm");
        Context applicationContext = context.getApplicationContext();
        Intent intent = new Intent(applicationContext, (Class<?>) NimReceiver.class);
        intent.setAction("com.netease.nim.ACTION.ALARM.REPEATING");
        AlarmManager alarmManager = (AlarmManager) applicationContext.getSystemService(NotificationCompat.CATEGORY_ALARM);
        if (alarmManager == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 23) {
            com.netease.nimlib.log.b.d("NimReceiver", "PendingIntent.getBroadcast Flag = PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE");
            broadcast = PendingIntent.getBroadcast(applicationContext, 0, intent, 201326592);
        } else {
            com.netease.nimlib.log.b.d("NimReceiver", "PendingIntent.getBroadcast Flag = PendingIntent.FLAG_UPDATE_CURRENT");
            broadcast = PendingIntent.getBroadcast(applicationContext, 0, intent, 134217728);
        }
        PendingIntent pendingIntent = broadcast;
        if (pendingIntent == null) {
            return;
        }
        try {
            alarmManager.setRepeating(2, 900000 + SystemClock.elapsedRealtime(), 900000L, pendingIntent);
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e(NotificationCompat.CATEGORY_SERVICE, "start repeating alarm err : ", th);
        }
    }

    public static void b(Context context) {
        PendingIntent broadcast;
        if (c.H() || c.s() || c.G()) {
            return;
        }
        com.netease.nimlib.log.b.c(NotificationCompat.CATEGORY_SERVICE, "stop repeating alarm");
        Context applicationContext = context.getApplicationContext();
        Intent intent = new Intent(applicationContext, (Class<?>) NimReceiver.class);
        intent.setAction("com.netease.nim.ACTION.ALARM.REPEATING");
        AlarmManager alarmManager = (AlarmManager) applicationContext.getSystemService(NotificationCompat.CATEGORY_ALARM);
        if (alarmManager == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 23) {
            com.netease.nimlib.log.b.d("NimReceiver", "PendingIntent.getBroadcast Flag = PendingIntent.FLAG_NO_CREATE | PendingIntent.FLAG_IMMUTABLE");
            broadcast = PendingIntent.getBroadcast(applicationContext, 0, intent, 603979776);
        } else {
            com.netease.nimlib.log.b.d("NimReceiver", "PendingIntent.getBroadcast Flag = PendingIntent.FLAG_NO_CREATE");
            broadcast = PendingIntent.getBroadcast(applicationContext, 0, intent, 536870912);
        }
        if (broadcast == null) {
            return;
        }
        alarmManager.cancel(broadcast);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (c.H() || c.s() || c.G()) {
            return;
        }
        try {
            c.a("NimReceiver");
            if (!h.a()) {
                if (!TextUtils.isEmpty(c.n())) {
                    NimService.a(context, a(intent.getAction()));
                } else {
                    b(context);
                }
            }
        } catch (Throwable th) {
            com.netease.nimlib.log.b.f("NimReceiver", "NimReceiver on Receive exception, e=" + th.getMessage());
        }
    }

    private int a(String str) {
        return "com.netease.nim.ACTION.ALARM.REPEATING".equals(str) ? 2 : -1;
    }
}
