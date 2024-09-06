package com.netease.nimlib.net.b.a;

/* compiled from: ChannelException.java */
/* loaded from: classes.dex */
public class b extends RuntimeException {
    public b() {
    }

    public b(String str, Throwable th) {
        super(str, th);
    }

    public b(String str) {
        super(str);
    }

    @Override // java.lang.Throwable
    public String toString() {
        return getCause() != null ? String.format("%s, inner cause:%s", super.toString(), getCause().toString()) : super.toString();
    }
}
