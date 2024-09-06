package com.netease.nimlib.o;

import android.content.Context;
import android.os.Build;

/* compiled from: VersionUtil.java */
/* loaded from: classes.dex */
public class aa {
    public static boolean a(Context context) {
        return Build.VERSION.SDK_INT > 30 && context.getApplicationInfo().targetSdkVersion > 30;
    }

    public static boolean b(Context context) {
        return Build.VERSION.SDK_INT >= 26 && context.getApplicationInfo().targetSdkVersion >= 26;
    }

    public static boolean a() {
        return a(26);
    }

    public static boolean a(int i) {
        return Build.VERSION.SDK_INT >= i;
    }
}
