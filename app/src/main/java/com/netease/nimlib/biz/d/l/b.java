package com.netease.nimlib.biz.d.l;

import java.util.Collection;
import java.util.List;

/* compiled from: GetUserInfoRequest.java */
/* loaded from: classes.dex */
public class b extends com.netease.nimlib.biz.d.a {
    private List<String> a;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 3;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 7;
    }

    public b(List<String> list) {
        this.a = list;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        com.netease.nimlib.push.packet.c.d.a(bVar, (Collection<?>) this.a);
        return bVar;
    }
}
