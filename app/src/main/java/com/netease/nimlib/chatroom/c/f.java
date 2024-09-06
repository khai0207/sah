package com.netease.nimlib.chatroom.c;

import u.aly.df;

/* compiled from: FetchMembersByPageRequest.java */
/* loaded from: classes.dex */
public class f extends com.netease.nimlib.biz.d.a {
    private byte a;
    private long b;
    private int c;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return df.k;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 8;
    }

    public f(byte b, long j, int i) {
        this.a = b;
        this.b = j;
        this.c = i;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        bVar.a(this.a);
        bVar.a(this.b);
        bVar.a(this.c);
        return bVar;
    }
}
