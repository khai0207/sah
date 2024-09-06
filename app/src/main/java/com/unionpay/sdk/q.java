package com.unionpay.sdk;

import android.net.Proxy;
import android.text.TextUtils;

/* loaded from: classes.dex */
final class q {
    static boolean a = false;
    static long b = -300000;
    private static final String[] c = {"UNKNOWN", "GPRS", "EDGE", "UMTS", "CDMA", "EVDO_0", "EVDO_A", "1xRTT", "HSDPA", "HSUPA", "HSPA", "IDEN", "EVDO_B", "LTE", "EHRPD", "HSPAP"};
    private static final String[] d = {"NONE", "GSM", "CDMA", "SIP"};

    public static boolean a() {
        return aw.a(11) ? !TextUtils.isEmpty(System.getProperty("http.proxyHost")) : !TextUtils.isEmpty(Proxy.getDefaultHost());
    }
}
