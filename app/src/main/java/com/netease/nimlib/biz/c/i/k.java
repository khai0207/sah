package com.netease.nimlib.biz.c.i;

import com.netease.nimlib.biz.e.j.r;
import com.netease.nimlib.o.f;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.SessionAckInfo;
import com.netease.nimlib.session.v;
import com.netease.nimlib.session.w;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* compiled from: SessionAckResponseHandler.java */
/* loaded from: classes.dex */
public class k extends com.netease.nimlib.biz.c.i {
    @Override // com.netease.nimlib.biz.c.a
    public void a(com.netease.nimlib.biz.e.a aVar) {
        if ((aVar.n() || aVar.r() == 700) && com.netease.nimlib.c.i().sessionReadAck) {
            if (aVar instanceof com.netease.nimlib.biz.e.f.g) {
                a((com.netease.nimlib.biz.e.f.g) aVar);
                return;
            }
            if (aVar instanceof r) {
                a((r) aVar);
            } else if (aVar instanceof com.netease.nimlib.biz.e.j.b) {
                a((com.netease.nimlib.biz.e.j.b) aVar);
            } else if (aVar instanceof com.netease.nimlib.biz.e.j.a) {
                a((com.netease.nimlib.biz.e.j.a) aVar);
            }
        }
    }

    private void a(com.netease.nimlib.biz.e.f.g gVar) {
        long c = gVar.c();
        com.netease.nimlib.log.b.y("onLoginSyncSession syncTimeTag=" + c);
        Map<String, Long> a = gVar.a();
        Map<String, Long> b = gVar.b();
        ArrayList arrayList = new ArrayList(a.size() + b.size());
        for (Map.Entry<String, Long> entry : a.entrySet()) {
            arrayList.add(new w(SessionTypeEnum.P2P, entry.getKey(), entry.getValue().longValue()));
        }
        for (Map.Entry<String, Long> entry2 : b.entrySet()) {
            arrayList.add(new w(SessionTypeEnum.Team, entry2.getKey(), entry2.getValue().longValue()));
        }
        a(arrayList);
        com.netease.nimlib.biz.l.e(c);
    }

    private void a(r rVar) {
        SessionTypeEnum a = rVar.a();
        String b = rVar.b();
        long c = rVar.c();
        w wVar = new w(a, b, c);
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(wVar);
        a(arrayList);
        com.netease.nimlib.log.b.y("onOnlineSyncSessionAckNotify, sessionId=" + b + ",time=" + c);
    }

    private void a(com.netease.nimlib.biz.e.j.b bVar) {
        com.netease.nimlib.biz.d.i.b bVar2 = (com.netease.nimlib.biz.d.i.b) b(bVar);
        if (bVar2 != null) {
            v.b(bVar2.d(), bVar2.e(), bVar2.f());
            com.netease.nimlib.log.b.y("session ack response, sessionId=" + bVar2.d() + ", timetag=" + bVar2.f());
        }
        a(bVar, (Object) null);
    }

    private void a(com.netease.nimlib.biz.e.j.a aVar) {
        final ArrayList c = com.netease.nimlib.o.f.c(aVar.a(), new f.a() { // from class: com.netease.nimlib.biz.c.i.-$$Lambda$Y2qVhRpOGTs_WcTXMWEEhymPYBI
            @Override // com.netease.nimlib.o.f.a
            public final Object transform(Object obj) {
                return w.a((com.netease.nimlib.push.packet.b.c) obj);
            }
        });
        com.netease.nimlib.biz.d.i.a aVar2 = (com.netease.nimlib.biz.d.i.a) b(aVar);
        if (aVar2 == null) {
            return;
        }
        com.netease.nimlib.o.f.f(com.netease.nimlib.o.f.d(aVar2.d(), new f.a() { // from class: com.netease.nimlib.biz.c.i.-$$Lambda$k$YP5uAgAzguOHFgNHes0nAlU7UcY
            @Override // com.netease.nimlib.o.f.a
            public final Object transform(Object obj) {
                Boolean a;
                a = k.a(c, (com.netease.nimlib.push.packet.b.c) obj);
                return a;
            }
        }), new f.a() { // from class: com.netease.nimlib.biz.c.i.-$$Lambda$k$CbWpMwUoK4PQw-xB9p51G1SZ33g
            @Override // com.netease.nimlib.o.f.a
            public final Object transform(Object obj) {
                Boolean a;
                a = k.a((com.netease.nimlib.push.packet.b.c) obj);
                return a;
            }
        });
        a(aVar, c, 200);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Boolean a(ArrayList arrayList, com.netease.nimlib.push.packet.b.c cVar) {
        if (cVar == null) {
            return null;
        }
        w a = w.a(cVar);
        final String sessionId = a.getSessionId();
        final SessionTypeEnum sessionType = a.getSessionType();
        return Boolean.valueOf(!com.netease.nimlib.o.f.b(arrayList, new f.a() { // from class: com.netease.nimlib.biz.c.i.-$$Lambda$k$qwThVSwyfSdwZqjlEFJaNWxLaBg
            @Override // com.netease.nimlib.o.f.a
            public final Object transform(Object obj) {
                Boolean a2;
                a2 = k.a(sessionId, sessionType, (SessionAckInfo) obj);
                return a2;
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Boolean a(String str, SessionTypeEnum sessionTypeEnum, SessionAckInfo sessionAckInfo) {
        return Boolean.valueOf(sessionAckInfo != null && str.equals(sessionAckInfo.getSessionId()) && sessionTypeEnum == sessionAckInfo.getSessionType());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Boolean a(com.netease.nimlib.push.packet.b.c cVar) {
        if (cVar != null) {
            w a = w.a(cVar);
            v.b(a.getSessionId(), a.getSessionType(), a.getTime());
            return true;
        }
        return true;
    }

    private void a(List<SessionAckInfo> list) {
        for (SessionAckInfo sessionAckInfo : list) {
            com.netease.nimlib.log.b.y("onSessionAck" + sessionAckInfo.toString());
            String sessionId = sessionAckInfo.getSessionId();
            SessionTypeEnum sessionType = sessionAckInfo.getSessionType();
            if (v.a(sessionId, sessionType, v.b(sessionId, sessionType, sessionAckInfo.getTime()))) {
                v.c(sessionAckInfo.getSessionId(), sessionAckInfo.getSessionType());
            }
        }
        com.netease.nimlib.l.d.a(list);
    }
}
