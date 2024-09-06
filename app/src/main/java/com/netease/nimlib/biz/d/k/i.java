package com.netease.nimlib.biz.d.k;

import java.util.Collection;
import java.util.List;

/* compiled from: GetTeamInfoListRequest.java */
/* loaded from: classes.dex */
public class i extends com.netease.nimlib.biz.d.a {
    private List<Long> a;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 8;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 34;
    }

    public i(List<Long> list) {
        this.a = list;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        com.netease.nimlib.push.packet.c.d.a(bVar, (Collection<?>) this.a);
        return bVar;
    }
}
