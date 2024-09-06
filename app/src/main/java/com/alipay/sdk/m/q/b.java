package com.alipay.sdk.m.q;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;

/* loaded from: classes.dex */
public class b {
    public static final String b = "00:00:00:00:00:00";
    public static b c;
    public String a;

    public b(Context context) {
        try {
            try {
                String macAddress = ((WifiManager) context.getApplicationContext().getSystemService("wifi")).getConnectionInfo().getMacAddress();
                this.a = macAddress;
                if (!TextUtils.isEmpty(macAddress)) {
                    return;
                }
            } catch (Exception e) {
                d.a(e);
                if (!TextUtils.isEmpty(this.a)) {
                    return;
                }
            }
            this.a = b;
        } catch (Throwable th) {
            if (TextUtils.isEmpty(this.a)) {
                this.a = b;
            }
            throw th;
        }
    }

    public static b b(Context context) {
        if (c == null) {
            c = new b(context);
        }
        return c;
    }

    public static String c(Context context) {
        if (context == null) {
            return "";
        }
        try {
            return context.getResources().getConfiguration().locale.toString();
        } catch (Throwable unused) {
            return "";
        }
    }

    public String a() {
        String str = b() + "|";
        String c2 = c();
        if (TextUtils.isEmpty(c2)) {
            return str + "000000000000000";
        }
        return str + c2;
    }

    public String b() {
        return "000000000000000";
    }

    public String c() {
        return "000000000000000";
    }

    public String d() {
        return this.a;
    }

    public static f d(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.getType() == 0) {
                return f.a(activeNetworkInfo.getSubtype());
            }
            if (activeNetworkInfo != null && activeNetworkInfo.getType() == 1) {
                return f.WIFI;
            }
            return f.NONE;
        } catch (Exception unused) {
            return f.NONE;
        }
    }

    public static String a(Context context) {
        return b(context).a().substring(0, 8);
    }
}
