package com.alipay.sdk.m.q;

import android.content.Context;
import android.text.TextUtils;

/* loaded from: classes.dex */
public class h {
    public static final String a = "pref_trade_token";
    public static final String b = ";";
    public static final String c = "result={";
    public static final String d = "}";
    public static final String e = "trade_token=\"";
    public static final String f = "\"";
    public static final String g = "trade_token=";

    public static void a(com.alipay.sdk.m.o.a aVar, Context context, String str) {
        try {
            String a2 = a(str);
            d.b(com.alipay.sdk.m.h.a.z, "trade token: " + a2);
            if (TextUtils.isEmpty(a2)) {
                return;
            }
            i.b(aVar, context, a, a2);
        } catch (Throwable th) {
            com.alipay.sdk.m.g.a.a(aVar, com.alipay.sdk.m.g.b.l, com.alipay.sdk.m.g.b.G, th);
            d.a(th);
        }
    }

    public static String a(String str) {
        String str2 = null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] split = str.split(";");
        for (int i = 0; i < split.length; i++) {
            if (split[i].startsWith(c) && split[i].endsWith(d)) {
                String[] split2 = split[i].substring(8, split[i].length() - 1).split(com.alipay.sdk.m.o.a.l);
                int i2 = 0;
                while (true) {
                    if (i2 >= split2.length) {
                        break;
                    }
                    if (split2[i2].startsWith(e) && split2[i2].endsWith("\"")) {
                        str2 = split2[i2].substring(13, split2[i2].length() - 1);
                        break;
                    }
                    if (split2[i2].startsWith(g)) {
                        str2 = split2[i2].substring(12);
                        break;
                    }
                    i2++;
                }
            }
        }
        return str2;
    }

    public static String a(com.alipay.sdk.m.o.a aVar, Context context) {
        String a2 = i.a(aVar, context, a, "");
        d.b(com.alipay.sdk.m.h.a.z, "get trade token: " + a2);
        return a2;
    }
}
