package com.netease.nimlib.biz.d.j;

import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.MessageKey;

/* compiled from: RemoveMsgPinRequest.java */
/* loaded from: classes.dex */
public class l extends com.netease.nimlib.biz.d.a {
    private final SessionTypeEnum a;
    private final String b;
    private final String c;
    private final long d;
    private final long e;
    private final String f;
    private final String g;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 23;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 17;
    }

    public l(SessionTypeEnum sessionTypeEnum, String str, String str2, long j, long j2, String str3, String str4) {
        this.a = sessionTypeEnum;
        this.b = str;
        this.c = str2;
        this.d = j;
        this.e = j2;
        this.f = str3;
        this.g = str4;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
        cVar.a(0, this.a.getValue());
        cVar.a(2, this.b);
        cVar.a(1, this.c);
        cVar.a(7, this.d);
        cVar.a(12, this.e);
        cVar.a(11, this.f);
        bVar.a(cVar);
        com.netease.nimlib.push.packet.b.c cVar2 = new com.netease.nimlib.push.packet.b.c();
        cVar2.a(2, this.g);
        bVar.a(cVar2);
        return bVar;
    }

    public MessageKey d() {
        return new MessageKey(this.a, this.b, this.c, this.d, this.e, this.f);
    }
}
