package com.alipay.apmobilesecuritysdk.e;

import android.content.Context;
import android.content.SharedPreferences;

/* loaded from: classes.dex */
public final class g {
    public static synchronized String a(Context context, String str) {
        synchronized (g.class) {
            String a = com.alipay.sdk.m.w.e.a(context, "openapi_file_pri", "openApi" + str, "");
            if (com.alipay.sdk.m.u.a.a(a)) {
                return "";
            }
            String b = com.alipay.sdk.m.t.c.b(com.alipay.sdk.m.t.c.a(), a);
            return com.alipay.sdk.m.u.a.a(b) ? "" : b;
        }
    }

    public static synchronized void a() {
        synchronized (g.class) {
        }
    }

    public static synchronized void a(Context context) {
        synchronized (g.class) {
            SharedPreferences.Editor edit = context.getSharedPreferences("openapi_file_pri", 0).edit();
            if (edit != null) {
                edit.clear();
                edit.commit();
            }
        }
    }

    public static synchronized void a(Context context, String str, String str2) {
        synchronized (g.class) {
            try {
                SharedPreferences.Editor edit = context.getSharedPreferences("openapi_file_pri", 0).edit();
                if (edit != null) {
                    edit.putString("openApi" + str, com.alipay.sdk.m.t.c.a(com.alipay.sdk.m.t.c.a(), str2));
                    edit.commit();
                }
            } catch (Throwable unused) {
            }
        }
    }
}
