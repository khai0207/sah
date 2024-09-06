package com.netease.nimlib.biz.e.d;

import java.util.Arrays;

/* compiled from: GetMixStorePolicyResponse.java */
@com.netease.nimlib.biz.e.b(a = 6, b = {"28"})
/* loaded from: classes.dex */
public class e extends com.netease.nimlib.biz.e.a {
    private byte[] c;
    private com.netease.nimlib.push.packet.b.c d;

    @Override // com.netease.nimlib.biz.e.a
    public com.netease.nimlib.push.packet.c.f a(com.netease.nimlib.push.packet.c.f fVar) throws Exception {
        byte[] array = fVar.b().array();
        this.c = Arrays.copyOf(array, array.length);
        this.d = com.netease.nimlib.push.packet.c.d.a(fVar);
        return null;
    }

    public byte[] a() {
        return this.c;
    }

    public com.netease.nimlib.push.packet.b.c b() {
        return this.d;
    }
}
