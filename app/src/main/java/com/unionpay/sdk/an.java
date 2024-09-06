package com.unionpay.sdk;

import android.content.Context;

/* loaded from: classes.dex */
final class an {
    public static long a(Context context, String str, String str2) {
        try {
            return context.getSharedPreferences(str, 0).getLong(str2, 0L);
        } catch (Exception unused) {
            return 0L;
        }
    }

    public static void a(Context context, String str, String str2, long j) {
        try {
            context.getSharedPreferences(str, 0).edit().putLong(str2, j).commit();
        } catch (Throwable unused) {
        }
    }

    public static void a(Context context, String str, String str2, String str3) {
        try {
            context.getSharedPreferences(str, 0).edit().putString(str2, str3).commit();
        } catch (Throwable unused) {
        }
    }

    public static String b(Context context, String str, String str2) {
        try {
            return context.getSharedPreferences(str, 0).getString(str2, null);
        } catch (Throwable unused) {
            return null;
        }
    }
}
