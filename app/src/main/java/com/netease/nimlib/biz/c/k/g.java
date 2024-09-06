package com.netease.nimlib.biz.c.k;

import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: SyncMyTeamMemberResponseHandler.java */
/* loaded from: classes.dex */
public class g extends com.netease.nimlib.biz.c.i {
    @Override // com.netease.nimlib.biz.c.a
    public void a(com.netease.nimlib.biz.e.a aVar) {
        if (aVar.n()) {
            com.netease.nimlib.biz.e.l.m mVar = (com.netease.nimlib.biz.e.l.m) aVar;
            long b = mVar.b();
            ArrayList arrayList = new ArrayList();
            Iterator<com.netease.nimlib.push.packet.b.c> it = mVar.a().iterator();
            while (it.hasNext()) {
                arrayList.add(com.netease.nimlib.team.g.a(it.next()));
            }
            com.netease.nimlib.log.b.c("ui", "sync my team member size=" + arrayList.size() + ", response timetag=" + b);
            if (arrayList.size() > 0) {
                com.netease.nimlib.team.c.a((ArrayList<com.netease.nimlib.team.g>) arrayList);
            }
            com.netease.nimlib.biz.l.s(b);
        }
    }
}
