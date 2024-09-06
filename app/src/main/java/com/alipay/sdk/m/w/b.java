package com.alipay.sdk.m.w;

import java.io.File;

/* loaded from: classes.dex */
public final class b {
    public static String a(String str) {
        String str2;
        try {
            str2 = f.a(str);
        } catch (Throwable unused) {
            str2 = "";
        }
        if (!com.alipay.sdk.m.u.a.a(str2)) {
            return str2;
        }
        return c.a(".SystemConfig" + File.separator + str);
    }
}
