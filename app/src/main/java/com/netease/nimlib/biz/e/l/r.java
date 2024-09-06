package com.netease.nimlib.biz.e.l;

import com.netease.nimlib.sdk.msg.model.TeamMsgAckInfo;

/* compiled from: TeamMsgAckDetailResponse.java */
@com.netease.nimlib.biz.e.b(a = 8, b = {"30"})
/* loaded from: classes.dex */
public class r extends com.netease.nimlib.biz.e.a {
    private TeamMsgAckInfo c;
    private String d;

    @Override // com.netease.nimlib.biz.e.a
    public com.netease.nimlib.push.packet.c.f a(com.netease.nimlib.push.packet.c.f fVar) throws Exception {
        com.netease.nimlib.push.packet.b.c a = com.netease.nimlib.push.packet.c.d.a(fVar);
        this.d = a.c(104);
        this.c = new TeamMsgAckInfo(String.valueOf(a.e(0)), a.c(102), com.netease.nimlib.push.packet.c.d.b(fVar), com.netease.nimlib.push.packet.c.d.b(fVar));
        return null;
    }

    public TeamMsgAckInfo a() {
        return this.c;
    }

    public String b() {
        return this.d;
    }
}
