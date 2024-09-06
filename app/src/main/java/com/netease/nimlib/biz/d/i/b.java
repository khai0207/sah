package com.netease.nimlib.biz.d.i;

import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import u.aly.df;

/* compiled from: AckSessionRequest.java */
/* loaded from: classes.dex */
public class b extends com.netease.nimlib.biz.d.a {
    private final SessionTypeEnum a;
    private final String b;
    private final long c;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 7;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return df.n;
    }

    public b(SessionTypeEnum sessionTypeEnum, String str, long j) {
        this.a = sessionTypeEnum;
        this.b = str;
        this.c = j;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        bVar.a((byte) this.a.getValue());
        bVar.a(this.b);
        bVar.a(this.c);
        return bVar;
    }

    public String d() {
        return this.b;
    }

    public SessionTypeEnum e() {
        return this.a;
    }

    public long f() {
        return this.c;
    }
}
