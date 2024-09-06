package com.alipay.apmobilesecuritysdk.b;

import com.alipay.sdk.m.y.d;

/* loaded from: classes.dex */
public final class a {
    public static a b = new a();
    public int a = 0;

    public static a a() {
        return b;
    }

    public static String a(String str, String str2) {
        return str + str2;
    }

    public final void a(int i) {
        this.a = i;
    }

    public final int b() {
        return this.a;
    }

    public final String c() {
        String str;
        String a = d.a();
        if (com.alipay.sdk.m.u.a.b(a)) {
            return a;
        }
        int i = this.a;
        if (i == 1) {
            str = "://mobilegw.stable.alipay.net/mgw.htm";
        } else {
            if (i == 2) {
                return "https://mobilegwpre.alipay.com/mgw.htm";
            }
            if (i == 3) {
                str = "://mobilegw-1-64.test.alipay.net/mgw.htm";
            } else {
                if (i != 4) {
                    return "https://mobilegw.alipay.com/mgw.htm";
                }
                str = "://mobilegw.aaa.alipay.net/mgw.htm";
            }
        }
        return a(com.alipay.sdk.m.h.a.q, str);
    }
}
