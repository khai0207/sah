package com.alipay.apmobilesecuritysdk.d;

import android.content.Context;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public final class a {
    public static synchronized Map<String, String> a(Context context, Map<String, String> map) {
        HashMap hashMap;
        synchronized (a.class) {
            String a = com.alipay.sdk.m.u.a.a(map, "appchannel", "");
            hashMap = new HashMap();
            hashMap.put("AA1", context.getPackageName());
            com.alipay.sdk.m.v.a.a();
            hashMap.put("AA2", com.alipay.sdk.m.v.a.a(context));
            hashMap.put("AA3", "APPSecuritySDK-ALIPAYSDK");
            hashMap.put("AA4", "3.4.0.202109291244");
            hashMap.put("AA6", a);
        }
        return hashMap;
    }
}
