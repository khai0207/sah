package com.netease.nimlib.biz.d.d;

import java.util.ArrayList;
import java.util.Collection;

/* compiled from: GetMixStorePolicyRequest2.java */
/* loaded from: classes.dex */
public class h extends com.netease.nimlib.biz.d.a {
    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 6;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 28;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(2L);
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        com.netease.nimlib.push.packet.c.d.a(bVar, (Collection<?>) arrayList);
        return bVar;
    }
}
