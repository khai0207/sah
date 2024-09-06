package com.netease.nim.highavailable;

import android.util.Log;
import com.netease.nimlib.log.b;
import com.netease.nimlib.log.b.a;

/* loaded from: classes.dex */
public class LogUtils {
    private static HighAvailableLogDelegate sLogDelegate = new HighAvailableLogDelegate() { // from class: com.netease.nim.highavailable.LogUtils.1
        @Override // com.netease.nim.highavailable.HighAvailableLogDelegate
        public int v(String str, String str2) {
            return b.a(2, str, str2, (Throwable) null);
        }

        @Override // com.netease.nim.highavailable.HighAvailableLogDelegate
        public int v(String str, String str2, Throwable th) {
            return b.a(2, str, str2, th);
        }

        @Override // com.netease.nim.highavailable.HighAvailableLogDelegate
        public int d(String str, String str2) {
            return b.a(3, str, str2, (Throwable) null);
        }

        @Override // com.netease.nim.highavailable.HighAvailableLogDelegate
        public int d(String str, String str2, Throwable th) {
            return b.a(3, str, str2, th);
        }

        @Override // com.netease.nim.highavailable.HighAvailableLogDelegate
        public int i(String str, String str2) {
            return b.a(4, str, str2, (Throwable) null);
        }

        @Override // com.netease.nim.highavailable.HighAvailableLogDelegate
        public int i(String str, String str2, Throwable th) {
            return b.a(4, str, str2, th);
        }

        @Override // com.netease.nim.highavailable.HighAvailableLogDelegate
        public int w(String str, String str2) {
            return b.a(5, str, str2, (Throwable) null);
        }

        @Override // com.netease.nim.highavailable.HighAvailableLogDelegate
        public int w(String str, String str2, Throwable th) {
            return b.a(5, str, str2, th);
        }

        @Override // com.netease.nim.highavailable.HighAvailableLogDelegate
        public int w(String str, Throwable th) {
            return b.a(5, str, (String) null, th);
        }

        @Override // com.netease.nim.highavailable.HighAvailableLogDelegate
        public int e(String str, String str2) {
            return b.a(6, str, str2, (Throwable) null);
        }

        @Override // com.netease.nim.highavailable.HighAvailableLogDelegate
        public int e(String str, String str2, Throwable th) {
            return b.a(6, str, str2, th);
        }
    };

    public static void setLogDelegate(HighAvailableLogDelegate highAvailableLogDelegate) {
        sLogDelegate = highAvailableLogDelegate;
    }

    public static int v(String str, String str2) {
        HighAvailableLogDelegate highAvailableLogDelegate = sLogDelegate;
        if (highAvailableLogDelegate != null) {
            return highAvailableLogDelegate.v(str, str2);
        }
        if (a.a()) {
            return Log.d(str, str2);
        }
        return 0;
    }

    public static int v(String str, String str2, Throwable th) {
        HighAvailableLogDelegate highAvailableLogDelegate = sLogDelegate;
        if (highAvailableLogDelegate != null) {
            return highAvailableLogDelegate.v(str, str2, th);
        }
        if (a.a()) {
            return Log.v(str, str2, th);
        }
        return 0;
    }

    public static int d(String str, String str2) {
        HighAvailableLogDelegate highAvailableLogDelegate = sLogDelegate;
        if (highAvailableLogDelegate != null) {
            return highAvailableLogDelegate.d(str, str2);
        }
        if (a.a()) {
            return Log.d(str, str2);
        }
        return 0;
    }

    public static int d(String str, String str2, Throwable th) {
        HighAvailableLogDelegate highAvailableLogDelegate = sLogDelegate;
        if (highAvailableLogDelegate != null) {
            return highAvailableLogDelegate.d(str, str2, th);
        }
        if (a.a()) {
            return Log.d(str, str2, th);
        }
        return 0;
    }

    public static int i(String str, String str2) {
        HighAvailableLogDelegate highAvailableLogDelegate = sLogDelegate;
        if (highAvailableLogDelegate != null) {
            return highAvailableLogDelegate.i(str, str2);
        }
        if (a.a()) {
            return Log.i(str, str2);
        }
        return 0;
    }

    public static int i(String str, String str2, Throwable th) {
        HighAvailableLogDelegate highAvailableLogDelegate = sLogDelegate;
        if (highAvailableLogDelegate != null) {
            return highAvailableLogDelegate.i(str, str2, th);
        }
        if (a.a()) {
            return Log.i(str, str2, th);
        }
        return 0;
    }

    public static int w(String str, String str2) {
        HighAvailableLogDelegate highAvailableLogDelegate = sLogDelegate;
        if (highAvailableLogDelegate != null) {
            return highAvailableLogDelegate.w(str, str2);
        }
        if (a.a()) {
            return Log.w(str, str2);
        }
        return 0;
    }

    public static int w(String str, String str2, Throwable th) {
        HighAvailableLogDelegate highAvailableLogDelegate = sLogDelegate;
        if (highAvailableLogDelegate != null) {
            return highAvailableLogDelegate.w(str, str2, th);
        }
        if (a.a()) {
            return Log.w(str, str2, th);
        }
        return 0;
    }

    public static int w(String str, Throwable th) {
        HighAvailableLogDelegate highAvailableLogDelegate = sLogDelegate;
        if (highAvailableLogDelegate != null) {
            return highAvailableLogDelegate.w(str, th);
        }
        if (a.a()) {
            return Log.w(str, th);
        }
        return 0;
    }

    public static int e(String str, String str2) {
        HighAvailableLogDelegate highAvailableLogDelegate = sLogDelegate;
        if (highAvailableLogDelegate != null) {
            return highAvailableLogDelegate.e(str, str2);
        }
        if (a.a()) {
            return Log.e(str, str2);
        }
        return 0;
    }

    public static int e(String str, String str2, Throwable th) {
        HighAvailableLogDelegate highAvailableLogDelegate = sLogDelegate;
        if (highAvailableLogDelegate != null) {
            return highAvailableLogDelegate.e(str, str2, th);
        }
        if (a.a()) {
            return Log.e(str, str2, th);
        }
        return 0;
    }
}
