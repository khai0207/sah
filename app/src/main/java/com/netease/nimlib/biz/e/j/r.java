package com.netease.nimlib.biz.e.j;

import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import u.aly.cs;

/* compiled from: OnlineSyncAckSessionNotify.java */
@com.netease.nimlib.biz.e.b(a = cs.b.g, b = {"116"})
/* loaded from: classes.dex */
public class r extends com.netease.nimlib.biz.e.a {
    private byte c;
    private String d;
    private long e;

    @Override // com.netease.nimlib.biz.e.a
    public com.netease.nimlib.push.packet.c.f a(com.netease.nimlib.push.packet.c.f fVar) throws Exception {
        this.c = fVar.c();
        this.d = fVar.e();
        this.e = fVar.h();
        return null;
    }

    public SessionTypeEnum a() {
        byte b = this.c;
        if (b == 0) {
            return SessionTypeEnum.P2P;
        }
        if (b == 1) {
            return SessionTypeEnum.Team;
        }
        return SessionTypeEnum.None;
    }

    public String b() {
        return this.d;
    }

    public long c() {
        return this.e;
    }
}
