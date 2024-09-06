package com.netease.nimlib.biz.c.k;

import com.netease.nimlib.biz.d.k.o;
import com.netease.nimlib.team.TeamDBHelper;

/* compiled from: MuteTeamMemberResponseHandler.java */
/* loaded from: classes.dex */
public class f extends com.netease.nimlib.biz.c.i {
    @Override // com.netease.nimlib.biz.c.a
    public void a(com.netease.nimlib.biz.e.a aVar) {
        if (aVar.n()) {
            o oVar = (o) b(aVar);
            TeamDBHelper.updateTeamMemberTimeTag(oVar.d(), ((com.netease.nimlib.biz.e.l.k) aVar).a());
            com.netease.nimlib.team.c.a(oVar.d(), oVar.e(), oVar.f());
        }
        a(aVar, null);
    }
}
