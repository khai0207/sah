package com.netease.nimlib.chatroom.c;

import u.aly.df;

/* compiled from: QueryTagMemberCountRequest.java */
/* loaded from: classes.dex */
public class o extends com.netease.nimlib.biz.d.a {
    private final String a;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return df.k;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 32;
    }

    public o(String str) {
        this.a = str;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        bVar.a(this.a);
        return bVar;
    }
}
