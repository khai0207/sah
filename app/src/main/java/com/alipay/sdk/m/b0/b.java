package com.alipay.sdk.m.b0;

import android.content.Context;
import com.alipay.sdk.m.a0.c;
import com.alipay.sdk.m.y.d;

/* loaded from: classes.dex */
public class b implements a {
    public static a a;
    public static com.alipay.sdk.m.y.a b;

    public static a a(Context context, String str) {
        if (context == null) {
            return null;
        }
        if (a == null) {
            b = d.a(context, str);
            a = new b();
        }
        return a;
    }

    @Override // com.alipay.sdk.m.b0.a
    public c a(com.alipay.sdk.m.a0.d dVar) {
        return com.alipay.sdk.m.a0.b.a(b.a(com.alipay.sdk.m.a0.b.a(dVar)));
    }

    @Override // com.alipay.sdk.m.b0.a
    public boolean logCollect(String str) {
        return b.logCollect(str);
    }
}
