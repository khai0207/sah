package com.netease.nimlib.biz.d.l;

import u.aly.df;

/* compiled from: GetRobotListRequest.java */
/* loaded from: classes.dex */
public class a extends com.netease.nimlib.biz.d.a {
    private long a;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 3;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return df.n;
    }

    public a(long j) {
        this.a = j;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        bVar.a(this.a);
        return bVar;
    }
}
