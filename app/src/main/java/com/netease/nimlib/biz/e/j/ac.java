package com.netease.nimlib.biz.e.j;

import u.aly.cs;

/* compiled from: TalkResponse.java */
@com.netease.nimlib.biz.e.b(a = cs.b.g, b = {"1"})
/* loaded from: classes.dex */
public class ac extends com.netease.nimlib.biz.e.a {
    private com.netease.nimlib.push.packet.b.c c;

    @Override // com.netease.nimlib.biz.e.a
    public com.netease.nimlib.push.packet.c.f a(com.netease.nimlib.push.packet.c.f fVar) throws Exception {
        this.c = com.netease.nimlib.push.packet.c.d.a(fVar);
        com.netease.nimlib.push.packet.a j = j();
        if (j == null) {
            return null;
        }
        com.netease.nimlib.log.b.J("************ TalkResponse begin ****************");
        com.netease.nimlib.log.b.a(j.i(), j.j(), "code = " + ((int) r()));
        com.netease.nimlib.log.b.a(j.i(), j.j(), "property", this.c);
        com.netease.nimlib.log.b.J("************ TalkResponse end ****************");
        return null;
    }

    public com.netease.nimlib.push.packet.b.c a() {
        return this.c;
    }
}
