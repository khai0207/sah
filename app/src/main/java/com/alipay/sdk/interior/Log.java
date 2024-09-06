package com.alipay.sdk.interior;

import android.content.Context;
import android.os.SystemClock;
import com.alipay.sdk.m.g.a;
import com.alipay.sdk.m.o.b;
import com.alipay.sdk.m.q.d;

/* loaded from: classes.dex */
public class Log {
    public static long a;

    /* loaded from: classes.dex */
    public interface ISdkLogCallback {
        void onLogLine(String str);
    }

    public static boolean forcedLogReport(Context context) {
        try {
            b.d().a(context);
            long elapsedRealtime = SystemClock.elapsedRealtime() / 1000;
            if (elapsedRealtime - a < 600) {
                return false;
            }
            a = elapsedRealtime;
            a.a(context);
            return true;
        } catch (Exception e) {
            d.a(e);
            return false;
        }
    }

    public static void setupLogCallback(ISdkLogCallback iSdkLogCallback) {
        d.a(iSdkLogCallback);
    }
}
