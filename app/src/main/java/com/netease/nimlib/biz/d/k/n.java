package com.netease.nimlib.biz.d.k;

/* compiled from: LeaveTeamRequest.java */
/* loaded from: classes.dex */
public class n extends com.netease.nimlib.biz.d.a {
    private String a;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 8;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 8;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        return new com.netease.nimlib.push.packet.c.b().b(this.a);
    }

    public void a(String str) {
        this.a = str;
    }
}
