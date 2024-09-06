package u.aly;

import android.content.Context;
import android.content.SharedPreferences;
import java.lang.reflect.Method;

/* compiled from: TrafficTracker.java */
/* loaded from: classes.dex */
public class ad {
    private static final String a = "uptr";
    private static final String b = "dntr";

    public static bo a(Context context) {
        try {
            bo boVar = new bo();
            long[] b2 = b(context);
            if (b2[0] > 0 && b2[1] > 0) {
                SharedPreferences a2 = y.a(context);
                long j = a2.getLong(a, -1L);
                long j2 = a2.getLong(b, -1L);
                a2.edit().putLong(a, b2[1]).putLong(b, b2[0]).commit();
                if (j > 0 && j2 > 0) {
                    b2[0] = b2[0] - j2;
                    b2[1] = b2[1] - j;
                    if (b2[0] > 0 && b2[1] > 0) {
                        boVar.c((int) b2[0]);
                        boVar.a((int) b2[1]);
                        return boVar;
                    }
                }
            }
            return null;
        } catch (Exception unused) {
            bv.e(com.umeng.analytics.a.e, "sdk less than 2.2 has get no traffic");
            return null;
        }
    }

    private static long[] b(Context context) throws Exception {
        Class<?> cls = Class.forName("android.net.TrafficStats");
        Method method = cls.getMethod("getUidRxBytes", Integer.TYPE);
        Method method2 = cls.getMethod("getUidTxBytes", Integer.TYPE);
        int i = context.getApplicationInfo().uid;
        if (i == -1) {
            return null;
        }
        return new long[]{((Long) method.invoke(null, Integer.valueOf(i))).longValue(), ((Long) method2.invoke(null, Integer.valueOf(i))).longValue()};
    }
}
