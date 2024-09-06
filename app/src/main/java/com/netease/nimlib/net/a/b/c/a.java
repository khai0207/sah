package com.netease.nimlib.net.a.b.c;

import android.util.Base64;

/* compiled from: CallRet.java */
/* loaded from: classes.dex */
public class a {
    private Object a;
    private String b;
    private int c;
    private String d;
    private Exception e;
    private String f;
    private String g;

    public a(Object obj, String str, int i, String str2, String str3, String str4, Exception exc) {
        this.a = obj;
        this.b = str;
        this.c = i;
        this.f = str2;
        this.g = new String(Base64.decode(str3, 0));
        this.d = str4;
        this.e = exc;
    }

    public Object a() {
        return this.a;
    }

    public int b() {
        return this.c;
    }

    public String c() {
        return this.d;
    }

    public Exception d() {
        return this.e;
    }
}
