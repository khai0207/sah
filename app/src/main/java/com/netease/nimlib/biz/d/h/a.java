package com.netease.nimlib.biz.d.h;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* compiled from: KickSelfRequest.java */
/* loaded from: classes.dex */
public class a extends com.netease.nimlib.biz.d.a {
    List<String> a;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 2;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 8;
    }

    public a(String str) {
        ArrayList arrayList = new ArrayList(1);
        this.a = arrayList;
        arrayList.add(str);
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        com.netease.nimlib.push.packet.c.d.a(bVar, (Collection<?>) this.a);
        return bVar;
    }
}
