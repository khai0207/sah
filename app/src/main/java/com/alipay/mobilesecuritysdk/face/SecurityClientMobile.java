package com.alipay.mobilesecuritysdk.face;

import android.content.Context;
import com.alipay.apmobilesecuritysdk.face.APSecuritySdk;
import com.alipay.sdk.m.h.b;
import com.alipay.sdk.m.u.a;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class SecurityClientMobile {
    public static synchronized String GetApdid(Context context, Map<String, String> map) {
        String a;
        synchronized (SecurityClientMobile.class) {
            HashMap hashMap = new HashMap();
            hashMap.put(b.g, a.a(map, b.g, ""));
            hashMap.put("tid", a.a(map, "tid", ""));
            hashMap.put("userId", a.a(map, "userId", ""));
            APSecuritySdk.getInstance(context).initToken(0, hashMap, null);
            a = com.alipay.apmobilesecuritysdk.a.a.a(context);
        }
        return a;
    }
}
