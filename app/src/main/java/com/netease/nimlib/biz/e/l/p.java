package com.netease.nimlib.biz.e.l;

/* compiled from: SyncUpdateTeamMemberNotify.java */
@com.netease.nimlib.biz.e.b(a = 8, b = {"119"})
/* loaded from: classes.dex */
public class p extends com.netease.nimlib.biz.e.a {
    private com.netease.nimlib.push.packet.b.c c;

    @Override // com.netease.nimlib.biz.e.a
    public com.netease.nimlib.push.packet.c.f a(com.netease.nimlib.push.packet.c.f fVar) throws Exception {
        this.c = com.netease.nimlib.push.packet.c.d.a(fVar);
        com.netease.nimlib.push.packet.a j = j();
        if (j == null) {
            return null;
        }
        com.netease.nimlib.log.b.J("************ SyncUpdateTeamMemberNotify begin ****************");
        com.netease.nimlib.log.b.a(j.i(), j.j(), "code = " + ((int) r()));
        com.netease.nimlib.log.b.a(j.i(), j.j(), "members", this.c);
        com.netease.nimlib.log.b.J("************ SyncUpdateTeamMemberNotify end ****************");
        return null;
    }

    public com.netease.nimlib.push.packet.b.c a() {
        return this.c;
    }
}
