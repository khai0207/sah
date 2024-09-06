package com.unionpay.mobile.android.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

/* loaded from: classes.dex */
public final class e {
    public static String a() {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", String.class, String.class).invoke(cls.newInstance(), "gsm.version.baseband", "no message");
        } catch (Exception unused) {
            return "";
        }
    }

    public static String a(Context context) {
        Activity activity = (Activity) context;
        try {
            String str = activity.getPackageManager().getPackageInfo(activity.getPackageName(), 4160).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return com.unionpay.mobile.android.languages.c.bD.a;
    }

    public static String b(Context context) {
        String packageName = ((Activity) context).getPackageName();
        return packageName != null ? packageName : "";
    }

    public static final String c(Context context) {
        WifiInfo connectionInfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
        return (connectionInfo == null || connectionInfo.getMacAddress() == null) ? "" : connectionInfo.getMacAddress().replaceAll(":", "");
    }
}
