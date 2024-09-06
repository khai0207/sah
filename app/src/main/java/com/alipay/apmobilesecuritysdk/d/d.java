package com.alipay.apmobilesecuritysdk.d;

import android.content.Context;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public final class d {
    public static synchronized Map<String, String> a() {
        HashMap hashMap;
        synchronized (d.class) {
            hashMap = new HashMap();
            try {
                new com.alipay.apmobilesecuritysdk.c.b();
                hashMap.put("AE16", "");
            } catch (Throwable unused) {
            }
        }
        return hashMap;
    }

    public static synchronized Map<String, String> a(Context context) {
        HashMap hashMap;
        synchronized (d.class) {
            com.alipay.sdk.m.v.d.a();
            com.alipay.sdk.m.v.b.b();
            hashMap = new HashMap();
            hashMap.put("AE1", com.alipay.sdk.m.v.d.b());
            StringBuilder sb = new StringBuilder();
            sb.append(com.alipay.sdk.m.v.d.c() ? "1" : "0");
            hashMap.put("AE2", sb.toString());
            StringBuilder sb2 = new StringBuilder();
            sb2.append(com.alipay.sdk.m.v.d.a(context) ? "1" : "0");
            hashMap.put("AE3", sb2.toString());
            hashMap.put("AE4", com.alipay.sdk.m.v.d.d());
            hashMap.put("AE5", com.alipay.sdk.m.v.d.e());
            hashMap.put("AE6", com.alipay.sdk.m.v.d.f());
            hashMap.put("AE7", com.alipay.sdk.m.v.d.g());
            hashMap.put("AE8", com.alipay.sdk.m.v.d.h());
            hashMap.put("AE9", com.alipay.sdk.m.v.d.i());
            hashMap.put("AE10", com.alipay.sdk.m.v.d.j());
            hashMap.put("AE11", com.alipay.sdk.m.v.d.k());
            hashMap.put("AE12", com.alipay.sdk.m.v.d.l());
            hashMap.put("AE13", com.alipay.sdk.m.v.d.m());
            hashMap.put("AE14", com.alipay.sdk.m.v.d.n());
            hashMap.put("AE15", com.alipay.sdk.m.v.d.o());
            hashMap.put("AE21", com.alipay.sdk.m.v.b.g());
        }
        return hashMap;
    }
}
