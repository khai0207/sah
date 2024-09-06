package com.netease.nimlib.chatroom.c;

import u.aly.df;

/* compiled from: TemporaryMuteRequest.java */
/* loaded from: classes.dex */
public class t extends com.netease.nimlib.biz.d.a {
    private String a;
    private long b;
    private boolean c;
    private String d;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return df.k;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 19;
    }

    public t(String str, long j, boolean z, String str2) {
        this.a = str;
        this.b = j;
        this.c = z;
        this.d = str2;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        bVar.a(this.a);
        bVar.a(this.b);
        bVar.a(this.c);
        bVar.a(this.d);
        return bVar;
    }
}
