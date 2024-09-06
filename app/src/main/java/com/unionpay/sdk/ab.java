package com.unionpay.sdk;

import android.content.Context;
import java.lang.reflect.Method;
import java.util.HashMap;

/* loaded from: classes.dex */
final class ab implements ao {
    final /* synthetic */ Context a;

    ab(Context context) {
        this.a = context;
    }

    @Override // com.unionpay.sdk.ao
    public final void a(Method method) {
        String name = method.getName();
        HashMap hashMap = new HashMap();
        hashMap.put("isSessionOnly", "1");
        hashMap.put("occurTime", Long.valueOf(System.currentTimeMillis()));
        if (name.equalsIgnoreCase("activityPaused")) {
            r.b(this.a);
        } else if (name.equalsIgnoreCase("activityIdle")) {
            r.c(this.a);
        }
    }
}
