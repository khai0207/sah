package com.unionpay.sdk;

import android.content.Context;
import android.os.Handler;
import java.nio.channels.FileChannel;
import java.util.Map;
import java.util.TreeMap;

/* loaded from: classes.dex */
final class ad {
    static FileChannel e;
    static final Map a = new TreeMap();
    static boolean b = false;
    static Context c = null;
    static Handler d = null;
    static long f = 0;
    static long g = 0;
    static String h = "";
    static String i = "Default";
    static long j = 30000;

    static void a(Context context, String str, String str2) {
        if (context != null) {
            d = new Handler(c.getMainLooper());
        }
        if (str != null && !str.trim().isEmpty()) {
            h = str;
        }
        if (str2 != null && !str2.trim().isEmpty()) {
            i = str2;
        }
        al.a("Analytics SDK Initializing...\n\tSDK_VERSION is: Android+unionpay+V1.1.2\n\tApp ID is: " + h + "\n\tApp Channel is: " + i);
    }
}
