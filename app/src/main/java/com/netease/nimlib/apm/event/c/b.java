package com.netease.nimlib.apm.event.c;

import android.text.TextUtils;

/* compiled from: EventReportStrategy.java */
/* loaded from: classes.dex */
public final class b {
    private String a = "https://statistic.live.126.net/";
    private int b = 0;
    private long c = 0;
    private long d = 0;
    private long e = 0;

    public String a() {
        return this.a;
    }

    public void a(String str) {
        this.a = str;
    }

    public int b() {
        return this.b;
    }

    public void a(int i) {
        this.b = i;
    }

    public long c() {
        return this.c;
    }

    public void a(long j) {
        this.c = j;
    }

    public long d() {
        return this.d;
    }

    public void b(long j) {
        this.d = j;
    }

    public long e() {
        return this.e;
    }

    public void c(long j) {
        this.e = j;
    }

    public boolean f() {
        if (TextUtils.isEmpty(this.a)) {
            return false;
        }
        long j = this.c;
        return j >= 10000 && j <= 600000 && this.b <= 10000 && this.d >= 1000 && this.e <= 600000;
    }
}
