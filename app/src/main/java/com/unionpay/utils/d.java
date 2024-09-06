package com.unionpay.utils;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

/* loaded from: classes.dex */
public final class d {
    public static String a() {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", String.class, String.class).invoke(cls.newInstance(), "gsm.version.baseband", "no message");
        } catch (Exception unused) {
            return "";
        }
    }

    public static final String a(Context context) {
        WifiInfo connectionInfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
        return (connectionInfo == null || connectionInfo.getMacAddress() == null) ? "" : connectionInfo.getMacAddress().replaceAll(":", "");
    }
}
