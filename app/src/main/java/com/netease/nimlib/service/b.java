package com.netease.nimlib.service;

import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.os.Build;
import com.netease.nimlib.c;
import com.netease.nimlib.l.a.c;
import com.netease.nimlib.l.e;

/* compiled from: Foreground.java */
/* loaded from: classes.dex */
class b {
    private static final int a = -317045405;

    static void a(Service service) {
        if (service == null) {
            return;
        }
        try {
            service.startForeground(a, a((Context) service));
            com.netease.nimlib.log.b.c("Foreground", "start foreground, service=" + service.getPackageName());
        } catch (Throwable th) {
            th.printStackTrace();
            com.netease.nimlib.log.b.d("Foreground", "start foreground error, e=" + th.getMessage());
        }
    }

    static void b(Service service) {
        if (service == null || !a()) {
            return;
        }
        try {
            service.startForeground(a, a((Context) service));
            com.netease.nimlib.log.b.c("Foreground", "start foreground, service=" + service.getPackageName());
        } catch (Throwable th) {
            th.printStackTrace();
            com.netease.nimlib.log.b.d("Foreground", "start foreground error, e=" + th.getMessage());
        }
    }

    private static boolean a() {
        return Build.VERSION.SDK_INT <= 23 && b() && c();
    }

    private static boolean b() {
        boolean z = c.i().improveSDKProcessPriority;
        if (!z) {
            com.netease.nimlib.log.b.d("Foreground", "user reject to improve sdk process priority");
        }
        return z;
    }

    private static boolean c() {
        String upperCase = com.netease.nimlib.p.a.a().toUpperCase();
        if (upperCase.contains("COOLPAD")) {
            com.netease.nimlib.log.b.d("Foreground", "unable to improve sdk process priority, as rubbish manufacturer=" + upperCase);
            return false;
        }
        if (!upperCase.contains("VIVO")) {
            return true;
        }
        com.netease.nimlib.log.b.d("Foreground", "unable to improve sdk process priority, as rubbish manufacturer=" + upperCase);
        return false;
    }

    private static Notification a(Context context) {
        e.e(context);
        return new c.d(context, e.a(com.netease.nimlib.c.e())).a();
    }
}
