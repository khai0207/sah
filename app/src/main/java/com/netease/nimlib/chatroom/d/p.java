package com.netease.nimlib.chatroom.d;

import u.aly.df;

/* compiled from: PollQueueResponse.java */
@com.netease.nimlib.biz.e.b(a = df.k, b = {"21"})
/* loaded from: classes.dex */
public class p extends com.netease.nimlib.biz.e.a {
    private String c;
    private String d;

    @Override // com.netease.nimlib.biz.e.a
    public com.netease.nimlib.push.packet.c.f a(com.netease.nimlib.push.packet.c.f fVar) throws Exception {
        this.c = fVar.e();
        this.d = fVar.e();
        return null;
    }

    public String a() {
        return this.c;
    }

    public String b() {
        return this.d;
    }
}
