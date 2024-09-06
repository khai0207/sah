package com.unionpay.mobile.android.net;

import java.util.HashMap;

/* loaded from: classes.dex */
public final class d {
    private int a;
    private String b;
    private HashMap<String, String> c;
    private byte[] d;

    public d(int i, String str, byte[] bArr) {
        this.a = i;
        this.b = str;
        this.c = null;
        this.d = bArr;
    }

    public d(String str) {
        this.a = 1;
        this.b = str;
        this.c = null;
        this.d = null;
    }

    public final int a() {
        return this.a;
    }

    public final void a(String str) {
        if (str != null) {
            this.d = str.getBytes();
        }
    }

    public final void a(HashMap<String, String> hashMap) {
        this.c = hashMap;
    }

    public final String b() {
        return this.b;
    }

    public final HashMap<String, String> c() {
        return this.c;
    }

    public final byte[] d() {
        return this.d;
    }
}
