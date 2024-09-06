package com.netease.nimlib.log.c;

import android.util.Log;
import com.android.pc.ioc.internet.FastHttp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* compiled from: LogFormat.java */
/* loaded from: classes.dex */
class b {
    private static long c;
    private static String d;
    private static final DateFormat a = new SimpleDateFormat("MM-dd HH:mm:ss.SSS zzz", Locale.ENGLISH);
    private static final Date b = new Date();
    private static final DateFormat e = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());

    static String a(long j) {
        if (j == c) {
            return d;
        }
        b.setTime(j);
        c = j;
        String format = a.format(b);
        d = format;
        return format;
    }

    static String a(String str, String str2, String str3, Throwable th) {
        StringBuilder sb = new StringBuilder();
        sb.append(str2);
        sb.append(": ");
        sb.append(str);
        sb.append(": ");
        sb.append(str3);
        sb.append(FastHttp.LINEND);
        if (th != null) {
            sb.append(Log.getStackTraceString(th));
            sb.append(FastHttp.LINEND);
        }
        return sb.toString();
    }
}
