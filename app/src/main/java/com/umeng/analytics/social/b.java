package com.umeng.analytics.social;

import android.util.Log;

/* compiled from: UMLog.java */
/* loaded from: classes.dex */
public class b {
    public static void a(String str, String str2) {
        if (e.v) {
            Log.i(str, str2);
        }
    }

    public static void a(String str, String str2, Exception exc) {
        if (e.v) {
            Log.i(str, exc.toString() + ":  [" + str2 + "]");
        }
    }

    public static void b(String str, String str2) {
        if (e.v) {
            Log.e(str, str2);
        }
    }

    public static void b(String str, String str2, Exception exc) {
        if (e.v) {
            Log.e(str, exc.toString() + ":  [" + str2 + "]");
            for (StackTraceElement stackTraceElement : exc.getStackTrace()) {
                Log.e(str, "        at\t " + stackTraceElement.toString());
            }
        }
    }

    public static void c(String str, String str2) {
        if (e.v) {
            Log.d(str, str2);
        }
    }

    public static void c(String str, String str2, Exception exc) {
        if (e.v) {
            Log.d(str, exc.toString() + ":  [" + str2 + "]");
        }
    }

    public static void d(String str, String str2) {
        if (e.v) {
            Log.v(str, str2);
        }
    }

    public static void d(String str, String str2, Exception exc) {
        if (e.v) {
            Log.v(str, exc.toString() + ":  [" + str2 + "]");
        }
    }

    public static void e(String str, String str2) {
        if (e.v) {
            Log.w(str, str2);
        }
    }

    public static void e(String str, String str2, Exception exc) {
        if (e.v) {
            Log.w(str, exc.toString() + ":  [" + str2 + "]");
            for (StackTraceElement stackTraceElement : exc.getStackTrace()) {
                Log.w(str, "        at\t " + stackTraceElement.toString());
            }
        }
    }
}
