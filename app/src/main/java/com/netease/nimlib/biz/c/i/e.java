package com.netease.nimlib.biz.c.i;

import com.netease.nimlib.biz.e.j.w;
import com.netease.nimlib.o.f;
import com.netease.nimlib.sdk.msg.model.SessionMsgDeleteOption;
import com.netease.nimlib.session.MsgDBHelper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* compiled from: DeleteSessionHistoryMsgResponseHandler.java */
/* loaded from: classes.dex */
public class e extends com.netease.nimlib.biz.c.i {
    @Override // com.netease.nimlib.biz.c.a
    public void a(com.netease.nimlib.biz.e.a aVar) {
        if (!aVar.n()) {
            a(aVar, (Object) null);
        } else if (aVar instanceof w) {
            a((w) aVar);
        } else if (aVar instanceof com.netease.nimlib.biz.e.j.j) {
            a((com.netease.nimlib.biz.e.j.j) aVar);
        }
    }

    private void a(com.netease.nimlib.biz.e.j.j jVar) {
        a(jVar.a());
    }

    private void a(w wVar) {
        a(wVar.a());
    }

    private void a(com.netease.nimlib.push.packet.b.c cVar) {
        SessionMsgDeleteOption fromProperty = SessionMsgDeleteOption.fromProperty(cVar);
        com.netease.nimlib.session.a.c.a().b(fromProperty.getSessionId(), fromProperty.getSessionType());
        MsgDBHelper.deleteRangeHistory(fromProperty.getSessionId(), fromProperty.getSessionType(), 0L, fromProperty.getTime());
        com.netease.nimlib.session.q queryRecentContact = MsgDBHelper.queryRecentContact(fromProperty.getSessionId(), fromProperty.getSessionType());
        if (queryRecentContact == null || queryRecentContact.getTime() > fromProperty.getTime()) {
            return;
        }
        com.netease.nimlib.biz.l.y(fromProperty.getTime());
        com.netease.nimlib.i.b.a(com.netease.nimlib.session.j.a(fromProperty.getSessionId(), fromProperty.getSessionType(), queryRecentContact));
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(fromProperty);
        com.netease.nimlib.i.b.g(arrayList);
    }

    private void a(List<com.netease.nimlib.push.packet.b.c> list) {
        ArrayList<SessionMsgDeleteOption> c = com.netease.nimlib.o.f.c(list, new f.a() { // from class: com.netease.nimlib.biz.c.i.-$$Lambda$VDp2us3TYcdkd5DjgffJ4KKme-s
            @Override // com.netease.nimlib.o.f.a
            public final Object transform(Object obj) {
                return SessionMsgDeleteOption.fromProperty((com.netease.nimlib.push.packet.b.c) obj);
            }
        });
        if (com.netease.nimlib.o.f.c((Collection) c)) {
            return;
        }
        ArrayList arrayList = new ArrayList(c.size());
        for (SessionMsgDeleteOption sessionMsgDeleteOption : c) {
            com.netease.nimlib.session.a.c.a().b(sessionMsgDeleteOption.getSessionId(), sessionMsgDeleteOption.getSessionType());
            MsgDBHelper.deleteRangeHistory(sessionMsgDeleteOption.getSessionId(), sessionMsgDeleteOption.getSessionType(), 0L, sessionMsgDeleteOption.getTime());
            com.netease.nimlib.session.q queryRecentContact = MsgDBHelper.queryRecentContact(sessionMsgDeleteOption.getSessionId(), sessionMsgDeleteOption.getSessionType());
            if (queryRecentContact != null && queryRecentContact.getTime() <= sessionMsgDeleteOption.getTime()) {
                arrayList.add(com.netease.nimlib.session.j.a(sessionMsgDeleteOption.getSessionId(), sessionMsgDeleteOption.getSessionType(), queryRecentContact));
            }
        }
        com.netease.nimlib.biz.l.y(((SessionMsgDeleteOption) Collections.max(c, new Comparator() { // from class: com.netease.nimlib.biz.c.i.-$$Lambda$e$GW2WaGepVMDXl7iP8eG1ItA33ns
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int a;
                a = e.a((SessionMsgDeleteOption) obj, (SessionMsgDeleteOption) obj2);
                return a;
            }
        })).getTime());
        com.netease.nimlib.i.b.e(arrayList);
        com.netease.nimlib.i.b.g(c);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ int a(SessionMsgDeleteOption sessionMsgDeleteOption, SessionMsgDeleteOption sessionMsgDeleteOption2) {
        return (sessionMsgDeleteOption.getTime() > sessionMsgDeleteOption2.getTime() ? 1 : (sessionMsgDeleteOption.getTime() == sessionMsgDeleteOption2.getTime() ? 0 : -1));
    }
}
