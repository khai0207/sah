package com.netease.nimlib.push.packet.a.a;

/* compiled from: CryptoException.java */
/* loaded from: classes.dex */
public class b extends Exception {
    private Throwable a;

    public b() {
    }

    public b(String str) {
        super(str);
    }

    @Override // java.lang.Throwable
    public synchronized Throwable getCause() {
        return this.a;
    }
}
