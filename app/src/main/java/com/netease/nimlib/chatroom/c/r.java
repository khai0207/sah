package com.netease.nimlib.chatroom.c;

import u.aly.df;

/* compiled from: SetRoomMemberRequest.java */
/* loaded from: classes.dex */
public class r extends com.netease.nimlib.biz.d.a {
    private String a;
    private int b;
    private boolean c;
    private int d;
    private String e;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return df.k;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 11;
    }

    public r(String str, int i, boolean z, int i2, String str2) {
        this.a = str;
        this.b = i;
        this.c = z;
        this.d = i2;
        this.e = str2;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        bVar.a(this.a);
        bVar.a(this.b);
        bVar.a(this.c);
        bVar.a(this.d);
        bVar.a(this.e);
        return bVar;
    }
}
