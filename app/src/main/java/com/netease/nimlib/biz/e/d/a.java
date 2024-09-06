package com.netease.nimlib.biz.e.d;

import java.util.Arrays;

/* compiled from: FileQuickTransferResponse.java */
@com.netease.nimlib.biz.e.b(a = 6, b = {"18"})
/* loaded from: classes.dex */
public class a extends com.netease.nimlib.biz.e.a {
    private String c;
    private String d;
    private long e;
    private byte[] f;

    @Override // com.netease.nimlib.biz.e.a
    public com.netease.nimlib.push.packet.c.f a(com.netease.nimlib.push.packet.c.f fVar) throws Exception {
        byte[] array = fVar.b().array();
        this.f = Arrays.copyOf(array, array.length);
        com.netease.nimlib.push.packet.b.c a = com.netease.nimlib.push.packet.c.d.a(fVar);
        this.c = a.c(1);
        this.d = a.c(2);
        this.e = a.e(4);
        return null;
    }

    public String a() {
        return this.d;
    }

    public long b() {
        return this.e;
    }

    public byte[] c() {
        return this.f;
    }
}
