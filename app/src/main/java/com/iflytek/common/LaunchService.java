package com.iflytek.common;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.os.Process;
import androidx.core.app.NotificationCompat;
import com.iflytek.common.a.d;

/* loaded from: classes.dex */
public class LaunchService extends Service {
    private d a;
    private long b = 0;
    private BroadcastReceiver c = new b(this);

    private void a() {
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(getPackageName() + "_LAUNCH_ALARM_LAUNCH");
            registerReceiver(this.c, intentFilter);
        } catch (Exception e) {
            com.iflytek.common.c.c.b("LaunchService", "", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void b() {
        long c = this.a.c();
        long currentTimeMillis = System.currentTimeMillis() + c;
        long j = currentTimeMillis - this.b;
        if (j > 0 && j < c) {
            com.iflytek.common.c.c.a(this, "reg next alarm too short:" + j);
            return;
        }
        try {
            AlarmManager alarmManager = (AlarmManager) getSystemService(NotificationCompat.CATEGORY_ALARM);
            PendingIntent broadcast = PendingIntent.getBroadcast(getApplicationContext(), 0, new Intent(getPackageName() + "_LAUNCH_ALARM_LAUNCH"), 134217728);
            if (c <= 0) {
                com.iflytek.common.c.c.a(this, "not reg alarm,periodrun = 0.");
                return;
            }
            this.b = currentTimeMillis;
            alarmManager.cancel(broadcast);
            alarmManager.set(0, this.b, broadcast);
            com.iflytek.common.c.c.a(this, "reg next alarm: " + com.iflytek.common.c.c.a(this.b));
        } catch (Exception e) {
            com.iflytek.common.c.c.b("LaunchService", "", e);
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        com.iflytek.common.c.c.a(this, "LaunchService onCreate pid=" + Process.myTid());
        this.a = d.a(getApplicationContext());
        a();
        com.iflytek.common.a.c.a(this);
        b();
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        com.iflytek.common.c.c.a(this, "LaunchService onDestroy");
        try {
            unregisterReceiver(this.c);
        } catch (Exception e) {
            com.iflytek.common.c.c.b("LaunchService", "", e);
        }
    }

    @Override // android.app.Service
    public void onStart(Intent intent, int i) {
        super.onStart(intent, i);
        com.iflytek.common.c.c.a(this, "LaunchService onStart lastalarm=" + this.b + " periodrun=" + this.a.c());
        if (this.b != 0 || this.a.c() <= 0) {
            return;
        }
        b();
    }
}
