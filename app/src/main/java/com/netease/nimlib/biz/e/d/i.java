package com.netease.nimlib.biz.e.d;

import java.util.Arrays;

/* compiled from: GetServerTimeResponse.java */
@com.netease.nimlib.biz.e.b(a = 6, b = {"23"})
/* loaded from: classes.dex */
public class i extends com.netease.nimlib.biz.e.a {
    private long c;
    private byte[] d;

    @Override // com.netease.nimlib.biz.e.a
    public com.netease.nimlib.push.packet.c.f a(com.netease.nimlib.push.packet.c.f fVar) throws Exception {
        byte[] array = fVar.b().array();
        this.d = Arrays.copyOf(array, array.length);
        this.c = fVar.h();
        return null;
    }

    public long a() {
        return this.c;
    }
}
