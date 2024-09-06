package com.netease.nimlib.biz.c.i;

import com.netease.nimlib.biz.e.l.s;
import com.netease.nimlib.sdk.msg.model.TeamMessageReceipt;
import com.netease.nimlib.sdk.msg.model.TeamMsgAckInfo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: TeamMsgAckResponseHandler.java */
/* loaded from: classes.dex */
public class q extends com.netease.nimlib.biz.c.i {
    @Override // com.netease.nimlib.biz.c.a
    public void a(com.netease.nimlib.biz.e.a aVar) {
        if (aVar instanceof s) {
            a((s) aVar);
        }
    }

    private void a(s sVar) {
        a(sVar.a());
    }

    public static synchronized void a(List<TeamMsgAckInfo> list) {
        synchronized (q.class) {
            if (list != null) {
                ArrayList arrayList = new ArrayList(list.size());
                Iterator<TeamMsgAckInfo> it = list.iterator();
                while (it.hasNext()) {
                    arrayList.add(new TeamMessageReceipt(it.next()));
                }
                com.netease.nimlib.team.j.b().a(arrayList);
            }
        }
    }
}
