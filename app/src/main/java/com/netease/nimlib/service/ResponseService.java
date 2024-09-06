package com.netease.nimlib.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import com.netease.nimlib.biz.i;
import com.netease.nimlib.c;
import com.netease.nimlib.l.a.c;
import com.netease.nimlib.l.e;
import com.netease.nimlib.o.aa;
import com.netease.nimlib.o.n;
import com.netease.nimlib.sdk.StatusBarNotificationConfig;

/* loaded from: classes.dex */
public class ResponseService extends Service {
    private boolean a;

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    public static void a(Context context) {
        if (context == null) {
            com.netease.nimlib.log.b.O("Push awake UI by Service failed, as context is null");
            return;
        }
        if (!n.a(context, ResponseService.class)) {
            com.netease.nimlib.log.b.O("Push awake UI by Service failed, as AndroidManifest.xml has not configured");
        }
        Intent intent = new Intent(context, (Class<?>) ResponseService.class);
        try {
            if (aa.a(context)) {
                com.netease.nimlib.log.b.O("android 12 can not startForegroundService in background");
            } else if (aa.b(context)) {
                context.startForegroundService(intent);
            } else {
                context.startService(intent);
            }
        } catch (Throwable th) {
            com.netease.nimlib.log.b.O("Push awake UI Exception = " + th);
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        a();
    }

    public static int b(Context context) {
        StatusBarNotificationConfig statusBarNotificationConfig = c.i().statusBarNotificationConfig;
        if (statusBarNotificationConfig == null || statusBarNotificationConfig.notificationSmallIconId == 0) {
            return context.getApplicationInfo().icon;
        }
        return statusBarNotificationConfig.notificationSmallIconId;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        try {
            a();
            i.a().j();
        } catch (Throwable unused) {
        }
        this.a = false;
        return super.onStartCommand(intent, i, i2);
    }

    private void a() {
        if (!aa.b(this) || this.a) {
            com.netease.nimlib.log.b.d("ResponseService", String.format("callStartForeground %s %s", Boolean.valueOf(!aa.b(this)), Boolean.valueOf(this.a)));
            return;
        }
        com.netease.nimlib.log.b.d("ResponseService", "callStartForeground");
        try {
            e.e(this);
            startForeground(10001, new c.d(this, e.d(this)).a(b(this)).a());
            com.netease.nimlib.c.b.a.a(this).postDelayed(new Runnable() { // from class: com.netease.nimlib.service.ResponseService.1
                @Override // java.lang.Runnable
                public void run() {
                    ResponseService.this.stopForeground(true);
                }
            }, 1000L);
            this.a = true;
        } catch (Throwable th) {
            th.printStackTrace();
            com.netease.nimlib.log.b.e("ResponseService", "callStartForeground", th);
        }
    }
}
