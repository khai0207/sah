package com.netease.nimlib.chatroom.c;

import u.aly.df;

/* compiled from: FetchTagMembersRequest.java */
/* loaded from: classes.dex */
public class g extends com.netease.nimlib.biz.d.a {
    private String a;
    private long b;
    private int c;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return df.k;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 31;
    }

    public g(String str, long j, int i) {
        this.a = str;
        this.b = j;
        this.c = i;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
        cVar.a(1, this.a);
        cVar.a(2, this.b);
        cVar.a(3, this.c);
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        bVar.a(cVar);
        return bVar;
    }
}
