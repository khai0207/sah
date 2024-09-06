package com.talkingdata.sdk;

import android.content.Context;

/* compiled from: td */
/* loaded from: classes.dex */
final class at extends Thread {
    final /* synthetic */ Context a;

    at(Context context) {
        this.a = context;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        try {
            boolean unused = as.j = true;
            String unused2 = as.i = (String) Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient$Info").getMethod("getId", new Class[0]).invoke(Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient").getMethod("getAdvertisingIdInfo", Context.class).invoke(null, this.a), new Object[0]);
        } catch (Throwable unused3) {
        }
    }
}
