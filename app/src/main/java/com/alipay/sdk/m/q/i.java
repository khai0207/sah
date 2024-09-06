package com.alipay.sdk.m.q;

import android.content.Context;
import android.preference.PreferenceManager;
import android.text.TextUtils;

/* loaded from: classes.dex */
public class i {
    public static String a;

    public static synchronized boolean a(Context context, String str) {
        boolean z;
        synchronized (i.class) {
            try {
                z = PreferenceManager.getDefaultSharedPreferences(context).contains(str);
            } catch (Throwable th) {
                d.a(th);
                z = false;
            }
        }
        return z;
    }

    public static synchronized void b(Context context, String str) {
        synchronized (i.class) {
            try {
                PreferenceManager.getDefaultSharedPreferences(context).edit().remove(str).apply();
            } catch (Throwable th) {
                d.a(th);
            }
        }
    }

    public static synchronized String a(com.alipay.sdk.m.o.a aVar, Context context, String str, String str2) {
        String str3;
        synchronized (i.class) {
            try {
                String string = PreferenceManager.getDefaultSharedPreferences(context).getString(str, str2);
                str3 = TextUtils.isEmpty(string) ? null : com.alipay.sdk.m.j.e.a(a(context), string, str);
                if (!TextUtils.isEmpty(string) && TextUtils.isEmpty(str3)) {
                    com.alipay.sdk.m.g.a.b(aVar, com.alipay.sdk.m.g.b.m, com.alipay.sdk.m.g.b.D, String.format("%s,%s", str, string));
                }
            } catch (Exception e) {
                d.a(e);
            }
        }
        return str3;
    }

    public static synchronized void b(com.alipay.sdk.m.o.a aVar, Context context, String str, String str2) {
        synchronized (i.class) {
            try {
                String b = com.alipay.sdk.m.j.e.b(a(context), str2, str);
                if (!TextUtils.isEmpty(str2) && TextUtils.isEmpty(b)) {
                    com.alipay.sdk.m.g.a.b(aVar, com.alipay.sdk.m.g.b.m, com.alipay.sdk.m.g.b.E, String.format("%s,%s", str, str2));
                }
                PreferenceManager.getDefaultSharedPreferences(context).edit().putString(str, b).apply();
            } catch (Throwable th) {
                d.a(th);
            }
        }
    }

    public static String a(Context context) {
        String str;
        if (TextUtils.isEmpty(a)) {
            try {
                str = context.getApplicationContext().getPackageName();
            } catch (Throwable th) {
                d.a(th);
                str = "";
            }
            a = (str + "0000000000000000000000000000").substring(0, 24);
        }
        return a;
    }
}
