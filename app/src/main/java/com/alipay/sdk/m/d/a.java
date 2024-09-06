package com.alipay.sdk.m.d;

/* loaded from: classes.dex */
public class a extends com.alipay.sdk.m.c.a {
    public static final /* synthetic */ boolean d = !a.class.desiredAssertionStatus();

    public a(byte[] bArr) {
        super(bArr);
    }

    public static a a(String str, long j, b bVar, short s, d dVar) throws Exception {
        byte[] a = com.alipay.sdk.m.c.c.a((byte) 1);
        if (!d && a.length != 1) {
            throw new AssertionError();
        }
        byte[] a2 = com.alipay.sdk.m.c.c.a(str.charAt(0), str.charAt(1));
        if (!d && a2.length != 2) {
            throw new AssertionError();
        }
        byte[] a3 = com.alipay.sdk.m.c.c.a(j);
        if (!d && a3.length != 8) {
            throw new AssertionError();
        }
        byte[] b = com.alipay.sdk.m.c.c.b();
        if (!d && b.length != 2) {
            throw new AssertionError();
        }
        bVar.a();
        byte[] a4 = com.alipay.sdk.m.c.c.a(bVar.a);
        if (!d && a4.length != 1) {
            throw new AssertionError();
        }
        byte[] a5 = com.alipay.sdk.m.c.c.a(bVar.b);
        if (!d && a5.length != 1) {
            throw new AssertionError();
        }
        byte[] bArr = (byte[]) bVar.c.clone();
        if (!d && bArr.length != (bVar.b & 255)) {
            throw new AssertionError();
        }
        byte[] a6 = com.alipay.sdk.m.c.c.a(s);
        if (!d && a6.length != 2) {
            throw new AssertionError();
        }
        byte[] b2 = com.alipay.sdk.m.c.c.b();
        if (!d && b2.length != 2) {
            throw new AssertionError();
        }
        dVar.a();
        byte[] a7 = com.alipay.sdk.m.c.c.a(dVar.a);
        if (!d && a7.length != 1) {
            throw new AssertionError();
        }
        byte[] bArr2 = (byte[]) dVar.b.clone();
        if (!d && bArr2.length != (dVar.a & 255)) {
            throw new AssertionError();
        }
        byte[] c = com.alipay.sdk.m.c.c.c();
        if (d || c.length == 4) {
            return new a(com.alipay.sdk.m.c.c.a(a, a2, a3, b, a4, a5, bArr, a6, b2, a7, bArr2, c));
        }
        throw new AssertionError();
    }

    public static a c() {
        try {
            return a(com.alipay.sdk.m.c.a.c, 0L, new c(""), (short) 0, new e());
        } catch (Exception unused) {
            return null;
        }
    }
}
