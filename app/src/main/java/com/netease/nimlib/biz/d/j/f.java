package com.netease.nimlib.biz.d.j;

import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;

/* compiled from: MsgPinSyncRequest.java */
/* loaded from: classes.dex */
public class f extends com.netease.nimlib.biz.d.a {
    private final String a;
    private final SessionTypeEnum b;
    private final long c;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 23;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 21;
    }

    public f(String str, SessionTypeEnum sessionTypeEnum, long j) {
        this.a = str;
        this.b = sessionTypeEnum;
        this.c = j;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        String a = com.netease.nimlib.session.j.a(this.b, this.a);
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
        cVar.a(1, a);
        cVar.a(2, this.c);
        bVar.a(cVar);
        return bVar;
    }

    public String d() {
        return this.a;
    }
}
