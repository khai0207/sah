package com.netease.nimlib.biz.c.k;

import com.netease.nimlib.biz.e.l.x;
import com.netease.nimlib.sdk.ResponseCode;
import com.netease.nimlib.team.TeamDBHelper;

/* compiled from: UpdateMemberResponseHandler.java */
/* loaded from: classes.dex */
public class m extends com.netease.nimlib.biz.c.i {
    @Override // com.netease.nimlib.biz.c.a
    public void a(com.netease.nimlib.biz.e.a aVar) {
        String d;
        if (aVar.n()) {
            if (aVar.q() == 6) {
                com.netease.nimlib.biz.d.k.m mVar = (com.netease.nimlib.biz.d.k.m) b(aVar);
                d = mVar.d();
                com.netease.nimlib.team.c.a(d, mVar.e());
            } else {
                d = aVar.q() == 5 ? ((com.netease.nimlib.biz.d.k.k) b(aVar)).d() : null;
            }
            TeamDBHelper.updateTeamMemberTimeTag(d, ((x) aVar).a());
        }
        if (aVar.q() == 5) {
            if (aVar.j().l() == 813) {
                aVar.j().b(ResponseCode.RES_SUCCESS);
            }
            a(aVar, ((x) aVar).b());
        } else if (aVar.q() == 6) {
            a(aVar, null);
        }
    }
}
