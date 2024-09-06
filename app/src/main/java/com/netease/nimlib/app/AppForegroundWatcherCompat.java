package com.netease.nimlib.app;

import android.content.Context;
import android.os.Build;

/* loaded from: classes.dex */
public class AppForegroundWatcherCompat {

    /* loaded from: classes.dex */
    public interface a {
        void a();

        void b();
    }

    public static void a(Context context) {
        if (b()) {
            com.netease.nimlib.app.a.a(context);
        }
    }

    public static void a(a aVar) {
        if (b()) {
            com.netease.nimlib.app.a.a(aVar);
        }
    }

    public static void b(a aVar) {
        if (b()) {
            com.netease.nimlib.app.a.b(aVar);
        }
    }

    public static boolean a() {
        if (b()) {
            return com.netease.nimlib.app.a.a();
        }
        return false;
    }

    public static boolean isBackground() {
        if (b()) {
            return com.netease.nimlib.app.a.b();
        }
        return false;
    }

    public static long a(boolean z) {
        return com.netease.nimlib.app.a.a(z);
    }

    public static long b(boolean z) {
        return com.netease.nimlib.app.a.b(z);
    }

    private static boolean b() {
        return Build.VERSION.SDK_INT >= 14;
    }
}
