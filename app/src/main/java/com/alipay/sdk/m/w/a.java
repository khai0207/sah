package com.alipay.sdk.m.w;

import android.content.Context;
import java.util.HashMap;

/* loaded from: classes.dex */
public class a {
    public static String a(Context context, String str, String str2) {
        String a;
        synchronized (a.class) {
            String str3 = null;
            if (context != null) {
                try {
                    if (!com.alipay.sdk.m.u.a.a(str) && !com.alipay.sdk.m.u.a.a(str2)) {
                        try {
                            a = e.a(context, str, str2, "");
                        } catch (Throwable unused) {
                        }
                        if (com.alipay.sdk.m.u.a.a(a)) {
                            return null;
                        }
                        str3 = com.alipay.sdk.m.t.c.b(com.alipay.sdk.m.t.c.a(), a);
                        return str3;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            return null;
        }
    }

    public static void a(Context context, String str, String str2, String str3) {
        synchronized (a.class) {
            if (com.alipay.sdk.m.u.a.a(str) || com.alipay.sdk.m.u.a.a(str2) || context == null) {
                return;
            }
            try {
                String a = com.alipay.sdk.m.t.c.a(com.alipay.sdk.m.t.c.a(), str3);
                HashMap hashMap = new HashMap();
                hashMap.put(str2, a);
                e.a(context, str, hashMap);
            } catch (Throwable unused) {
            }
        }
    }
}
