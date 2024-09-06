package com.unionpay.mobile.android.utils;

/* loaded from: classes.dex */
final class m extends Thread {
    final /* synthetic */ k a;

    m(k kVar) {
        this.a = kVar;
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0038, code lost:
    
        if (r1.isConnected() == false) goto L19;
     */
    @Override // java.lang.Thread, java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void run() {
        /*
            r3 = this;
            super.run()
            monitor-enter(r3)     // Catch: java.lang.InterruptedException -> Le
            r0 = 1000(0x3e8, double:4.94E-321)
            r3.wait(r0)     // Catch: java.lang.Throwable -> Lb
            monitor-exit(r3)     // Catch: java.lang.Throwable -> Lb
            goto L27
        Lb:
            r0 = move-exception
            monitor-exit(r3)     // Catch: java.lang.InterruptedException -> Le
            throw r0     // Catch: java.lang.InterruptedException -> Le
        Le:
            r0 = move-exception
            r0.printStackTrace()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r0 = r0.getMessage()
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            java.lang.String r1 = "uppay"
            com.unionpay.mobile.android.utils.j.c(r1, r0)
        L27:
            com.unionpay.mobile.android.utils.k r0 = r3.a
            monitor-enter(r0)
            org.simalliance.openmobileapi.SEService r1 = com.unionpay.mobile.android.utils.k.b()     // Catch: java.lang.Throwable -> L55
            if (r1 == 0) goto L3a
            org.simalliance.openmobileapi.SEService r1 = com.unionpay.mobile.android.utils.k.b()     // Catch: java.lang.Throwable -> L55
            boolean r1 = r1.isConnected()     // Catch: java.lang.Throwable -> L55
            if (r1 != 0) goto L53
        L3a:
            java.lang.String r1 = "uppay"
            java.lang.String r2 = "se service connection time out"
            com.unionpay.mobile.android.utils.j.c(r1, r2)     // Catch: java.lang.Throwable -> L55
            com.unionpay.mobile.android.utils.k r1 = r3.a     // Catch: java.lang.Throwable -> L55
            android.os.Handler r1 = com.unionpay.mobile.android.utils.k.b(r1)     // Catch: java.lang.Throwable -> L55
            if (r1 == 0) goto L53
            com.unionpay.mobile.android.utils.k r1 = r3.a     // Catch: java.lang.Throwable -> L55
            android.os.Handler r1 = com.unionpay.mobile.android.utils.k.b(r1)     // Catch: java.lang.Throwable -> L55
            r2 = 2
            r1.sendEmptyMessage(r2)     // Catch: java.lang.Throwable -> L55
        L53:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L55
            return
        L55:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.mobile.android.utils.m.run():void");
    }
}
