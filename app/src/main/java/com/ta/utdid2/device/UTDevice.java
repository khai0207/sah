package com.ta.utdid2.device;

import android.content.Context;
import com.ta.utdid2.a.a.f;

/* loaded from: classes.dex */
public class UTDevice {
    public static String c(Context context) {
        a b = b.b(context);
        return (b == null || f.m59a(b.e())) ? "ffffffffffffffffffffffff" : b.e();
    }

    public static String d(Context context) {
        String g = c.a(context).g();
        return (g == null || f.m59a(g)) ? "ffffffffffffffffffffffff" : g;
    }

    @Deprecated
    public static String getUtdid(Context context) {
        return c(context);
    }

    @Deprecated
    public static String getUtdidForUpdate(Context context) {
        return d(context);
    }
}
