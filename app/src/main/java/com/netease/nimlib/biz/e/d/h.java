package com.netease.nimlib.biz.e.d;

import java.util.Arrays;

/* compiled from: GetOriginalUrlResponse.java */
@com.netease.nimlib.biz.e.b(a = 6, b = {"22"})
/* loaded from: classes.dex */
public class h extends com.netease.nimlib.biz.e.a {
    private String c;
    private byte[] d;

    @Override // com.netease.nimlib.biz.e.a
    public com.netease.nimlib.push.packet.c.f a(com.netease.nimlib.push.packet.c.f fVar) throws Exception {
        byte[] array = fVar.b().array();
        this.d = Arrays.copyOf(array, array.length);
        this.c = com.netease.nimlib.push.packet.c.d.a(fVar).c(1);
        return null;
    }

    public String a() {
        return this.c;
    }

    public byte[] b() {
        return this.d;
    }
}
