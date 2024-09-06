package com.talkingdata.sdk;

import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;

/* compiled from: td */
/* loaded from: classes.dex */
public final class cc {
    static Handler a = null;
    static HandlerThread b = null;
    private static final String c = "check_wifi_permission";
    private static final String d = "check_bs_permission";
    private static final String e = "check_gps_permission";
    private static final int f = 1;
    private static final int g = 2;
    private static final int h = 3;
    private static final int i = 4;
    private static final long j = 600000;
    private static volatile cc k;
    private static WifiManager l;

    public static cc a() {
        if (k == null) {
            synchronized (cc.class) {
                if (k == null) {
                    k = new cc();
                }
            }
        }
        return k;
    }

    static {
        try {
            bk.a().register(a());
        } catch (Throwable unused) {
        }
    }

    private cc() {
        try {
            HandlerThread handlerThread = new HandlerThread("locHandlerThread");
            b = handlerThread;
            handlerThread.start();
            a = new cd(this, b.getLooper());
            a(4, 0L);
        } catch (Throwable th) {
            ce.postSDKError(th);
        }
    }

    protected void a(int i2, long j2) {
        try {
            Message obtain = Message.obtain();
            obtain.what = i2;
            a.sendMessageDelayed(obtain, j2);
        } catch (Throwable th) {
            ce.postSDKError(th);
        }
    }

    public final void onTDEBEventLocationEvent(ck ckVar) {
        try {
            if (Integer.parseInt(String.valueOf(ckVar.m.get("eventType"))) != 5) {
                return;
            }
            cn cnVar = new cn();
            cnVar.b = "env";
            cnVar.c = "lwcUpdate";
            cnVar.a = a.e;
            bk.a().post(cnVar);
        } catch (Throwable th) {
            ce.postSDKError(th);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x0049, code lost:
    
        if (com.talkingdata.sdk.bh.b(com.talkingdata.sdk.ab.f, "android.permission.ACCESS_FINE_LOCATION") != false) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0029, code lost:
    
        if (com.talkingdata.sdk.ab.f.checkSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0) goto L12;
     */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x006b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected boolean a(java.lang.String r8) {
        /*
            r7 = this;
            r0 = 23
            boolean r1 = com.talkingdata.sdk.bh.a(r0)
            java.lang.String r2 = "android.permission.ACCESS_FINE_LOCATION"
            java.lang.String r3 = "android.permission.ACCESS_COARSE_LOCATION"
            java.lang.String r4 = "android.permission.READ_PHONE_STATE"
            r5 = 1
            r6 = 0
            if (r1 == 0) goto L35
            android.content.Context r1 = com.talkingdata.sdk.ab.f     // Catch: java.lang.Throwable -> L2f
            int r1 = r1.checkSelfPermission(r4)     // Catch: java.lang.Throwable -> L2f
            if (r1 != 0) goto L1a
            r1 = 1
            goto L1b
        L1a:
            r1 = 0
        L1b:
            android.content.Context r4 = com.talkingdata.sdk.ab.f     // Catch: java.lang.Throwable -> L2d
            int r3 = r4.checkSelfPermission(r3)     // Catch: java.lang.Throwable -> L2d
            if (r3 == 0) goto L2b
            android.content.Context r3 = com.talkingdata.sdk.ab.f     // Catch: java.lang.Throwable -> L2d
            int r2 = r3.checkSelfPermission(r2)     // Catch: java.lang.Throwable -> L2d
            if (r2 != 0) goto L53
        L2b:
            r2 = 1
            goto L54
        L2d:
            r2 = move-exception
            goto L31
        L2f:
            r2 = move-exception
            r1 = 0
        L31:
            com.talkingdata.sdk.ce.postSDKError(r2)
            goto L53
        L35:
            android.content.Context r1 = com.talkingdata.sdk.ab.f     // Catch: java.lang.Throwable -> L4e
            boolean r1 = com.talkingdata.sdk.bh.b(r1, r4)     // Catch: java.lang.Throwable -> L4e
            android.content.Context r4 = com.talkingdata.sdk.ab.f     // Catch: java.lang.Throwable -> L4c
            boolean r3 = com.talkingdata.sdk.bh.b(r4, r3)     // Catch: java.lang.Throwable -> L4c
            if (r3 != 0) goto L2b
            android.content.Context r3 = com.talkingdata.sdk.ab.f     // Catch: java.lang.Throwable -> L4c
            boolean r2 = com.talkingdata.sdk.bh.b(r3, r2)     // Catch: java.lang.Throwable -> L4c
            if (r2 == 0) goto L53
            goto L2b
        L4c:
            r2 = move-exception
            goto L50
        L4e:
            r2 = move-exception
            r1 = 0
        L50:
            com.talkingdata.sdk.ce.postSDKError(r2)
        L53:
            r2 = 0
        L54:
            android.content.Context r3 = com.talkingdata.sdk.ab.f
            java.lang.String r4 = "android.permission.ACCESS_WIFI_STATE"
            boolean r3 = com.talkingdata.sdk.bh.b(r3, r4)
            java.lang.String r4 = "check_bs_permission"
            boolean r4 = r8.equals(r4)
            if (r4 == 0) goto L6b
            if (r2 == 0) goto L69
            if (r1 == 0) goto L69
            goto L6a
        L69:
            r5 = 0
        L6a:
            return r5
        L6b:
            java.lang.String r1 = "check_gps_permission"
            boolean r1 = r8.equals(r1)
            if (r1 == 0) goto L74
            return r2
        L74:
            java.lang.String r1 = "check_wifi_permission"
            boolean r8 = r8.equals(r1)
            if (r8 == 0) goto L8a
            boolean r8 = com.talkingdata.sdk.bh.a(r0)
            if (r8 == 0) goto L89
            if (r3 == 0) goto L87
            if (r2 == 0) goto L87
            goto L88
        L87:
            r5 = 0
        L88:
            return r5
        L89:
            return r3
        L8a:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.talkingdata.sdk.cc.a(java.lang.String):boolean");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        try {
            if (!a(c)) {
                a(1, 180000L);
                return;
            }
            Context context = ab.f;
            Context context2 = ab.f;
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            l = wifiManager;
            if (wifiManager.isWifiEnabled()) {
                ab.f.registerReceiver(new ci(l), new IntentFilter("android.net.wifi.SCAN_RESULTS"));
            }
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        if (!a(e)) {
            a(2, j);
            return;
        }
        try {
            cn cnVar = new cn();
            cnVar.b = "env";
            cnVar.c = "locationUpdate";
            cnVar.a = a.e;
            bk.a().post(cnVar);
            a(2, j);
        } catch (Throwable th) {
            ce.postSDKError(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        try {
            if (!a(d)) {
                a(3, 180000L);
                return;
            }
            if (ab.f != null) {
                try {
                    Context context = ab.f;
                    Context context2 = ab.f;
                    TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                    if (telephonyManager.getSimState() == 5) {
                        telephonyManager.getCellLocation();
                        telephonyManager.listen(new ch(), 16);
                        CellLocation.requestLocationUpdate();
                    }
                } catch (Throwable unused) {
                }
            }
        } catch (Throwable th) {
            ce.postSDKError(th);
        }
    }
}
