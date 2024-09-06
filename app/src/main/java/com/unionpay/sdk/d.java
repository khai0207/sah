package com.unionpay.sdk;

import android.location.LocationManager;
import android.net.wifi.WifiManager;

/* loaded from: classes.dex */
class d extends ae {
    private static volatile d d;
    private static ao e = new e();
    private final String a = "140.207.168.45";
    private final String b = "140.207.168.45";
    private final String c = "http://140.207.168.45/g/d";
    private b f = new a("140.207.168.45", "140.207.168.45", "http://140.207.168.45/g/d");
    private long g = 0;
    private final String h = null;

    /* loaded from: classes.dex */
    final class a extends b {
        a(String str, String str2, String str3) {
            this.a = str;
            this.b = str2;
            this.c = str3;
            this.d = 1;
        }
    }

    static {
        ai.a().register(a());
        a();
        if ((ad.c == null ? false : ad.c.getSharedPreferences("unionpay_CHANNEL_ID", 0).getBoolean("location_called", false)) || ad.c == null) {
            al.b("registerListenLocationMethodCalled : location method already called");
            return;
        }
        LocationManager locationManager = (LocationManager) ad.c.getApplicationContext().getSystemService("location");
        WifiManager wifiManager = (WifiManager) ad.c.getApplicationContext().getSystemService("wifi");
        try {
            aw.a(locationManager, e, "mService", "android.location.ILocationManager");
            aw.a(wifiManager, e, "mService", "android.net.wifi.IWifiManager");
        } catch (Throwable th) {
            al.b(th.getMessage());
        }
    }

    private d() {
    }

    static d a() {
        if (d == null) {
            synchronized (d.class) {
                if (d == null) {
                    d = new d();
                }
            }
        }
        return d;
    }
}
