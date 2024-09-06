package com.netease.nimlib.biz.e.l;

import com.netease.nimlib.sdk.msg.model.TeamMsgAckInfo;
import java.util.ArrayList;
import java.util.List;

/* compiled from: TeamMsgAckNotify.java */
@com.netease.nimlib.biz.e.b(a = 8, b = {"31"})
/* loaded from: classes.dex */
public class s extends com.netease.nimlib.biz.e.a {
    private List<TeamMsgAckInfo> c;

    @Override // com.netease.nimlib.biz.e.a
    public com.netease.nimlib.push.packet.c.f a(com.netease.nimlib.push.packet.c.f fVar) throws Exception {
        int g = fVar.g();
        this.c = new ArrayList(g);
        for (int i = 0; i < g; i++) {
            com.netease.nimlib.push.packet.b.c a = com.netease.nimlib.push.packet.c.d.a(fVar);
            this.c.add(new TeamMsgAckInfo(String.valueOf(a.e(0)), a.c(102), a.d(100), a.d(101), a.c(103)));
        }
        return null;
    }

    public List<TeamMsgAckInfo> a() {
        return this.c;
    }
}
