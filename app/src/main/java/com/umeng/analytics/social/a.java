package com.umeng.analytics.social;

import com.alipay.sdk.app.OpenAuthTask;

/* compiled from: UMException.java */
/* loaded from: classes.dex */
public class a extends RuntimeException {
    private static final long b = -4656673116019167471L;
    protected int a;
    private String c;

    public int a() {
        return this.a;
    }

    public a(int i, String str) {
        super(str);
        this.a = OpenAuthTask.Duplex;
        this.c = "";
        this.a = i;
        this.c = str;
    }

    public a(String str, Throwable th) {
        super(str, th);
        this.a = OpenAuthTask.Duplex;
        this.c = "";
        this.c = str;
    }

    public a(String str) {
        super(str);
        this.a = OpenAuthTask.Duplex;
        this.c = "";
        this.c = str;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return this.c;
    }
}
