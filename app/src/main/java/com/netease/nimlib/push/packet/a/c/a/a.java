package com.netease.nimlib.push.packet.a.c.a;

/* compiled from: DecoderException.java */
/* loaded from: classes.dex */
public class a extends IllegalStateException {
    private Throwable a;

    a(String str, Throwable th) {
        super(str);
        this.a = th;
    }

    @Override // java.lang.Throwable
    public synchronized Throwable getCause() {
        return this.a;
    }
}
