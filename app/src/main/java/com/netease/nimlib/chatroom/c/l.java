package com.netease.nimlib.chatroom.c;

import u.aly.df;

/* compiled from: KickMemberRequest.java */
/* loaded from: classes.dex */
public class l extends com.netease.nimlib.biz.d.a {
    private String a;
    private String b;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return df.k;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 17;
    }

    public l(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        bVar.a(this.a);
        bVar.a(this.b);
        return bVar;
    }
}
