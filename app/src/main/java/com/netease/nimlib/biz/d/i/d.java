package com.netease.nimlib.biz.d.i;

import u.aly.df;

/* compiled from: CommandRequest.java */
/* loaded from: classes.dex */
public class d extends com.netease.nimlib.biz.d.a {
    private int a;
    private com.netease.nimlib.push.packet.b.c b;

    public d(int i) {
        this.a = i;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        return new com.netease.nimlib.push.packet.c.b().a(this.b);
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return this.a == 103 ? (byte) 21 : (byte) 7;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        if (this.a == 103) {
            return df.n;
        }
        return (byte) 7;
    }

    public void a(com.netease.nimlib.push.packet.b.c cVar) {
        this.b = cVar;
    }
}
