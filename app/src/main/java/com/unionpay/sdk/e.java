package com.unionpay.sdk;

import java.lang.reflect.Method;

/* loaded from: classes.dex */
final class e implements ao {
    e() {
    }

    @Override // com.unionpay.sdk.ao
    public final void a(Method method) {
        if (aw.b) {
            al.b("location method already called");
            return;
        }
        if (method.getName().equalsIgnoreCase("getLastKnownLocation") || method.getName().equalsIgnoreCase("requestLocationUpdates") || method.getName().equalsIgnoreCase("getScanResults")) {
            if (ad.c != null) {
                ad.c.getSharedPreferences("unionpay_CHANNEL_ID", 0).edit().putBoolean("location_called", true).commit();
            }
            aw.b = true;
            al.b(method.getName() + " calling");
        }
    }
}
