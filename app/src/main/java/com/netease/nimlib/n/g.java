package com.netease.nimlib.n;

import android.os.SystemClock;
import android.util.Pair;
import com.netease.nimlib.o.w;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.netease.nimlib.session.IMMessageImpl;
import com.netease.nimlib.session.ac;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* compiled from: MsgSendEventManager.java */
/* loaded from: classes.dex */
public class g {
    private final Map<String, com.netease.nimlib.n.e.g> a = new HashMap();
    private final Map<String, Long> b = new HashMap();
    private final Map<Short, String> c = new HashMap();

    /* compiled from: MsgSendEventManager.java */
    /* loaded from: classes.dex */
    private static class a {
        private static final g a = new g();
    }

    public static g a() {
        return a.a;
    }

    public void a(IMMessage iMMessage, long j) {
        try {
            com.netease.nimlib.n.e.g r = com.netease.nimlib.n.e.g.r();
            boolean a2 = com.netease.nimlib.n.f.a.a();
            r.a(a2);
            long b = com.netease.nimlib.n.f.a.b(a2, j);
            r.c(b);
            r.e(iMMessage.getUuid());
            r.f(iMMessage.getTime());
            r.f(iMMessage.getFromAccount());
            String sessionId = iMMessage.getSessionId();
            int i = AnonymousClass1.a[iMMessage.getSessionType().ordinal()];
            if (i == 1) {
                r.b(com.netease.nimlib.n.b.t.P2P.a());
                r.g(sessionId);
            } else if (i == 2) {
                r.b(com.netease.nimlib.n.b.t.Team.a());
                r.j(sessionId);
            } else if (i == 3) {
                r.b(com.netease.nimlib.n.b.t.SUPER_TEAM.a());
                r.j(sessionId);
            } else if (i == 4) {
                r.b(com.netease.nimlib.n.b.t.ChatRoom.a());
                try {
                    r.h(Long.parseLong(sessionId));
                } catch (Exception e) {
                    com.netease.nimlib.log.b.e("MsgSendEventManager", String.format("failed to convert room id(%s) to long", iMMessage.getSessionId()), e);
                }
            }
            r.h(com.netease.nimlib.push.b.c());
            r.i(com.netease.nimlib.biz.a.c());
            com.netease.nimlib.log.b.G("MsgSendEventManager startTrackEvent model = " + r.m());
            this.a.put(iMMessage.getUuid(), r);
            a(iMMessage, b, a2);
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("MsgSendEventManager", " startTrackEvent Exception", th);
        }
    }

    /* compiled from: MsgSendEventManager.java */
    /* renamed from: com.netease.nimlib.n.g$1 */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[SessionTypeEnum.values().length];
            a = iArr;
            try {
                iArr[SessionTypeEnum.P2P.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[SessionTypeEnum.Team.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[SessionTypeEnum.SUPER_TEAM.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[SessionTypeEnum.ChatRoom.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private void a(IMMessage iMMessage, long j, boolean z) {
        if (iMMessage instanceof IMMessageImpl) {
            ac acVar = new ac();
            if (z) {
                acVar.a(j);
            }
            ((IMMessageImpl) iMMessage).setTimeConsumingStatistics(acVar);
        }
    }

    public void a(IMMessage iMMessage) {
        try {
            String uuid = iMMessage.getUuid();
            com.netease.nimlib.log.b.G("MsgSendEventManager recordStartUpload uuid = " + uuid);
            if (w.a((CharSequence) uuid)) {
                return;
            }
            this.b.put(uuid, Long.valueOf(SystemClock.elapsedRealtime()));
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("MsgSendEventManager", " recordStartUpload Exception", th);
        }
    }

    public void b(IMMessage iMMessage) {
        Long remove;
        com.netease.nimlib.n.e.g gVar;
        try {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            String uuid = iMMessage.getUuid();
            com.netease.nimlib.log.b.G("MsgSendEventManager recordStopUpload uuid = " + uuid);
            if (w.a((CharSequence) uuid) || (remove = this.b.remove(uuid)) == null || remove.longValue() <= 0 || (gVar = this.a.get(uuid)) == null) {
                return;
            }
            long longValue = elapsedRealtime - remove.longValue();
            gVar.d(longValue);
            b(iMMessage, longValue, gVar.a());
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("MsgSendEventManager", " recordStopUpload Exception", th);
        }
    }

    private void b(IMMessage iMMessage, long j, boolean z) {
        if ((iMMessage instanceof IMMessageImpl) && z) {
            a((IMMessageImpl) iMMessage).c(j);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void a(com.netease.nimlib.biz.d.a aVar) {
        if (aVar == null) {
            return;
        }
        try {
            Pair<String, Short> b = b(aVar);
            if (b != null && !w.a((CharSequence) b.first) && ((Short) b.second).shortValue() > 0) {
                this.c.put(b.second, b.first);
            }
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("MsgSendEventManager", " recordSerialIdOfProtocol Exception", th);
        }
    }

    public void a(com.netease.nimlib.ipc.a.d dVar) {
        if (dVar != null) {
            try {
                if (dVar.d() && c(dVar)) {
                    com.netease.nimlib.log.b.G("MsgSendEventManager recordSendProtocolUi");
                    com.netease.nimlib.n.e.g d = d(dVar);
                    if (d == null) {
                        return;
                    }
                    long a2 = com.netease.nimlib.n.f.a.a(d.a());
                    if (a2 > 0) {
                        d.e(a2);
                    }
                    a(dVar, a2);
                }
            } catch (Throwable th) {
                com.netease.nimlib.log.b.e("MsgSendEventManager", " recordSendProtocolUi Exception", th);
            }
        }
    }

    public void a(JSONObject jSONObject) {
        short optInt;
        com.netease.nimlib.n.e.g gVar;
        if (jSONObject == null) {
            return;
        }
        try {
            com.netease.nimlib.log.b.G("MsgSendEventManager recordSendProtocolUiFromIpc with " + jSONObject);
            if ("msgSend".equals(jSONObject.optString("eventKey")) && (optInt = (short) jSONObject.optInt("serialId")) > 0) {
                long optLong = jSONObject.optLong("sendProtocolNtpTime");
                if (optLong <= 0) {
                    long optLong2 = jSONObject.optLong("sendProtocolElapsedRealtime");
                    if (optLong2 <= 0) {
                        return;
                    } else {
                        optLong = com.netease.nimlib.n.f.a.b(false, optLong2);
                    }
                }
                String remove = this.c.remove(Short.valueOf(optInt));
                if (!w.a((CharSequence) remove) && (gVar = this.a.get(remove)) != null && optLong > 0) {
                    gVar.e(optLong);
                }
            }
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("MsgSendEventManager", " recordSendProtocolUiFromIpc Exception", th);
        }
    }

    public void b(com.netease.nimlib.ipc.a.d dVar) {
        short e;
        if (dVar != null) {
            long j = 0;
            try {
            } catch (Throwable th) {
                com.netease.nimlib.log.b.e("MsgSendEventManager", " recordSendProtocolPush Exception", th);
            }
            if (dVar.d() && c(dVar) && (e = e(dVar)) > 0) {
                com.netease.nimlib.log.b.G("MsgSendEventManager recordSendProtocolPush");
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("eventKey", "msgSend");
                jSONObject.put("serialId", (int) e);
                if (com.netease.nimlib.n.f.a.a()) {
                    j = com.netease.nimlib.n.f.a.a(true);
                    jSONObject.put("sendProtocolNtpTime", j);
                } else {
                    jSONObject.put("sendProtocolElapsedRealtime", SystemClock.elapsedRealtime());
                }
                com.netease.nimlib.ipc.e.d(jSONObject.toString());
                a(dVar, j);
            }
        }
    }

    public void a(com.netease.nimlib.ipc.a.d dVar, long j) {
        if (j <= 0) {
            return;
        }
        try {
            com.netease.nimlib.push.packet.a b = dVar.b();
            if (b != null && c(dVar)) {
                int i = b.i() == 13 ? 39 : 46;
                com.netease.nimlib.push.packet.c.f fVar = new com.netease.nimlib.push.packet.c.f(dVar.c());
                com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
                fVar.a(cVar);
                String c = cVar.c(i);
                ac a2 = ac.a(c);
                if (a2.a() > 0) {
                    a2.b(j);
                    c = a2.d().toString();
                }
                com.netease.nimlib.log.b.G("MsgSendEventManager after insertSendTimeToPacketData. clientExt is " + c);
                if (c != null) {
                    cVar.a(i, c);
                }
                com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
                bVar.a(cVar);
                dVar.a(bVar);
            }
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("MsgSendEventManager", " insertSendTimeToPacketData Exception", th);
        }
    }

    public void c(IMMessage iMMessage) {
        try {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            com.netease.nimlib.log.b.G("MsgSendEventManager recordCallbackApi time = " + elapsedRealtime);
            com.netease.nimlib.n.e.g gVar = this.a.get(iMMessage.getUuid());
            if (gVar == null) {
                return;
            }
            gVar.g(com.netease.nimlib.n.f.a.b(gVar.a(), elapsedRealtime));
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("MsgSendEventManager", " recordCallbackApi Exception", th);
        }
    }

    public void a(IMMessage iMMessage, int i) {
        try {
            com.netease.nimlib.log.b.G("MsgSendEventManager stopTrackEvent resultCode = " + i);
            com.netease.nimlib.n.e.g remove = this.a.remove(iMMessage.getUuid());
            if (remove != null) {
                remove.c(i);
                long a2 = com.netease.nimlib.n.f.a.a(remove.a());
                if (a2 > 0) {
                    remove.b(a2);
                }
                remove.f(iMMessage.getTime());
                if (i != 200) {
                    if (com.netease.nimlib.n.a.a.b.containsKey(Integer.valueOf(i))) {
                        remove.k(com.netease.nimlib.n.a.a.b.get(Integer.valueOf(i)));
                    } else {
                        remove.k(com.netease.nimlib.n.a.a.a);
                    }
                } else {
                    remove.d(iMMessage.getServerId() + "");
                }
                com.netease.nimlib.apm.a.a("msgSend", (com.netease.nimlib.apm.b.b<? extends com.netease.nimlib.apm.b.a>) remove);
            }
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("MsgSendEventManager", " stopTrackEvent Exception", th);
        }
    }

    public boolean c(com.netease.nimlib.ipc.a.d dVar) {
        if (dVar == null) {
            return false;
        }
        return b(dVar.b());
    }

    private com.netease.nimlib.n.e.g d(com.netease.nimlib.ipc.a.d dVar) {
        if (!c(dVar)) {
            return null;
        }
        String remove = this.c.remove(Short.valueOf(e(dVar)));
        if (w.a((CharSequence) remove)) {
            return null;
        }
        return this.a.get(remove);
    }

    private short e(com.netease.nimlib.ipc.a.d dVar) {
        if (dVar == null) {
            return (short) 0;
        }
        return a(dVar.b());
    }

    private Pair<String, Short> b(com.netease.nimlib.biz.d.a aVar) {
        com.netease.nimlib.push.packet.a i;
        Object[] g;
        if (aVar == null || (i = aVar.i()) == null || !b(i)) {
            return null;
        }
        Object j = aVar.j();
        if (!(j instanceof com.netease.nimlib.i.k) || (g = ((com.netease.nimlib.i.k) j).g()) == null || g.length <= 0) {
            return null;
        }
        Object obj = g[0];
        if (!(obj instanceof IMMessageImpl)) {
            return null;
        }
        String uuid = ((IMMessageImpl) obj).getUuid();
        if (w.a((CharSequence) uuid)) {
            return null;
        }
        return new Pair<>(uuid, Short.valueOf(i.k()));
    }

    private short a(com.netease.nimlib.push.packet.a aVar) {
        if (aVar == null) {
            return (short) 0;
        }
        return aVar.k();
    }

    private boolean b(com.netease.nimlib.push.packet.a aVar) {
        if (aVar == null) {
            return false;
        }
        byte i = aVar.i();
        byte j = aVar.j();
        if (i == 7 && j == 1) {
            return true;
        }
        if (i == 8 && j == 2) {
            return true;
        }
        if (i == 21 && j == 2) {
            return true;
        }
        return i == 13 && j == 6;
    }

    private ac a(IMMessageImpl iMMessageImpl) {
        ac timeConsumingStatistics = iMMessageImpl.getTimeConsumingStatistics();
        if (timeConsumingStatistics != null) {
            return timeConsumingStatistics;
        }
        ac acVar = new ac();
        iMMessageImpl.setTimeConsumingStatistics(acVar);
        return acVar;
    }
}
