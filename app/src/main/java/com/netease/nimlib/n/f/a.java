package com.netease.nimlib.n.f;

import android.os.SystemClock;
import com.netease.nimlib.biz.i;
import com.netease.nimlib.h;
import com.netease.nimlib.log.b;
import com.netease.nimlib.m.c;
import java.util.Locale;

/* compiled from: NtpTimeUtil.java */
/* loaded from: classes.dex */
public class a {
    public static boolean a() {
        if (h.h()) {
            return i.a().b().a();
        }
        return c.d().a();
    }

    private static long c() {
        long c;
        if (h.h()) {
            c = i.a().b().c();
        } else {
            c = c.d().c();
        }
        if (c < 0) {
            b.d("NtpTimeUtil", "getServerNow no NTP");
            return System.currentTimeMillis();
        }
        b.a("NtpTimeUtil", String.format(Locale.ENGLISH, "getServerNow currentTime = %d, time = %d", Long.valueOf(System.currentTimeMillis()), Long.valueOf(c)));
        return c;
    }

    private static long a(long j) {
        long a;
        if (j == 0) {
            return 0L;
        }
        long currentTimeMillis = System.currentTimeMillis();
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (h.h()) {
            a = i.a().b().a((j - currentTimeMillis) + elapsedRealtime);
        } else {
            a = c.d().a((j - currentTimeMillis) + elapsedRealtime);
        }
        if (a < 0) {
            b.d("NtpTimeUtil", "getServerTimestamp no NTP");
            return j;
        }
        b.a("NtpTimeUtil", String.format(Locale.ENGLISH, "getServerTimestamp localTimestamp = %d, time = %d", Long.valueOf(j), Long.valueOf(a)));
        return a;
    }

    private static long b(long j) {
        long a;
        if (h.h()) {
            a = i.a().b().a(j);
        } else {
            a = c.d().a(j);
        }
        if (a < 0) {
            b.d("NtpTimeUtil", "getServerTimestampByElapsedRealtime no NTP");
            return (System.currentTimeMillis() + j) - SystemClock.elapsedRealtime();
        }
        b.a("NtpTimeUtil", String.format(Locale.ENGLISH, "getServerTimestampByElapsedRealtime elapsedRealtime = %d, time = %d", Long.valueOf(j), Long.valueOf(a)));
        return a;
    }

    public static long b() {
        if (h.h()) {
            return i.a().b().b().a().b();
        }
        return c.d().b().a().b();
    }

    public static long a(boolean z) {
        if (!z) {
            return System.currentTimeMillis();
        }
        return c();
    }

    public static long a(boolean z, long j) {
        return !z ? j : a(j);
    }

    public static long b(boolean z, long j) {
        if (!z) {
            return (System.currentTimeMillis() + j) - SystemClock.elapsedRealtime();
        }
        return b(j);
    }
}
