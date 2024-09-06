package com.netease.nimlib.biz.c.k;

import com.netease.nimlib.team.TeamDBHelper;
import java.util.HashMap;

/* compiled from: GetMemberInvitorResponseHandler.java */
/* loaded from: classes.dex */
public class b extends com.netease.nimlib.biz.c.i {
    @Override // com.netease.nimlib.biz.c.a
    public void a(com.netease.nimlib.biz.e.a aVar) {
        if (aVar.n()) {
            com.netease.nimlib.push.packet.b.e a = ((com.netease.nimlib.biz.e.l.c) aVar).a();
            HashMap hashMap = new HashMap();
            if (a != null && a.a() != null) {
                hashMap.putAll(a.a());
            }
            com.netease.nimlib.biz.d.k.g gVar = (com.netease.nimlib.biz.d.k.g) b(aVar);
            if (gVar != null) {
                TeamDBHelper.updateTeamMemberInvitor(gVar.d(), hashMap);
            }
            a(aVar, hashMap);
            return;
        }
        a(aVar, null);
    }
}
