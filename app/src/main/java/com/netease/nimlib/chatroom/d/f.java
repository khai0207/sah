package com.netease.nimlib.chatroom.d;

import u.aly.df;

/* compiled from: EnterRoomResponse.java */
@com.netease.nimlib.biz.e.b(a = df.k, b = {"2"})
/* loaded from: classes.dex */
public class f extends com.netease.nimlib.biz.e.a {
    private com.netease.nimlib.push.packet.b.c c;
    private com.netease.nimlib.push.packet.b.c d;
    private com.netease.nimlib.push.packet.b.c e;

    @Override // com.netease.nimlib.biz.e.a
    public com.netease.nimlib.push.packet.c.f a(com.netease.nimlib.push.packet.c.f fVar) throws Exception {
        this.c = com.netease.nimlib.push.packet.c.d.a(fVar);
        this.d = com.netease.nimlib.push.packet.c.d.a(fVar);
        try {
            this.e = com.netease.nimlib.push.packet.c.d.a(fVar);
        } catch (com.netease.nimlib.push.packet.c.g unused) {
            this.e = null;
        }
        com.netease.nimlib.push.packet.a j = j();
        if (j != null) {
            com.netease.nimlib.log.b.J("************ LoginResponse begin ****************");
            com.netease.nimlib.log.b.a(j.i(), j.j(), "code = " + ((int) r()));
            com.netease.nimlib.log.b.a(j.i(), j.j(), "roomInfoProperty", this.c);
            com.netease.nimlib.log.b.a(j.i(), j.j(), "memberInfoProperty", this.d);
            com.netease.nimlib.log.b.a(j.i(), j.j(), "cdnInfoProperty", this.e);
            com.netease.nimlib.log.b.J("************ LoginResponse end ****************");
        }
        return null;
    }

    public com.netease.nimlib.push.packet.b.c a() {
        return this.c;
    }

    public com.netease.nimlib.push.packet.b.c b() {
        return this.d;
    }

    public com.netease.nimlib.push.packet.b.c c() {
        return this.e;
    }
}
