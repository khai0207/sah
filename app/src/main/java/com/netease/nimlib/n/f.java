package com.netease.nimlib.n;

import android.text.TextUtils;
import com.netease.nimlib.app.AppForegroundWatcherCompat;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.netease.nimlib.session.IMMessageImpl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: MsgReceiveEventManager.java */
/* loaded from: classes.dex */
public class f {
    private final Map<String, com.netease.nimlib.n.e.f> a = new HashMap();

    /* compiled from: MsgReceiveEventManager.java */
    /* loaded from: classes.dex */
    private static class a {
        private static final f a = new f();
    }

    public static f a() {
        return a.a;
    }

    public void a(IMMessage iMMessage, long j, int i, long j2) {
        a(iMMessage, j, i, j2, false);
    }

    public void a(IMMessage iMMessage, long j, int i, long j2, boolean z) {
        try {
            String fromAccount = iMMessage.getFromAccount();
            if (TextUtils.equals(com.netease.nimlib.c.n(), fromAccount)) {
                return;
            }
            com.netease.nimlib.n.e.f fVar = new com.netease.nimlib.n.e.f();
            boolean a2 = com.netease.nimlib.n.f.a.a();
            fVar.a(a2);
            fVar.d(iMMessage.getServerId() + "");
            fVar.e(iMMessage.getUuid());
            if (iMMessage instanceof IMMessageImpl) {
                fVar.a(((IMMessageImpl) iMMessage).getTimeConsumingStatistics());
            }
            fVar.f(com.netease.nimlib.n.f.a.b(a2, j));
            fVar.g(iMMessage.getTime());
            fVar.b(i);
            fVar.i(com.netease.nimlib.n.f.a.b(a2, j2));
            fVar.c(z);
            fVar.f(fromAccount);
            fVar.g(com.netease.nimlib.c.n());
            String sessionId = iMMessage.getSessionId();
            int i2 = AnonymousClass1.a[iMMessage.getSessionType().ordinal()];
            if (i2 == 1) {
                fVar.c(com.netease.nimlib.n.b.t.P2P.a());
            } else if (i2 == 2) {
                fVar.c(com.netease.nimlib.n.b.t.Team.a());
                fVar.j(sessionId);
            } else if (i2 == 3) {
                fVar.c(com.netease.nimlib.n.b.t.SUPER_TEAM.a());
                fVar.j(sessionId);
            } else if (i2 == 4) {
                fVar.c(com.netease.nimlib.n.b.t.ChatRoom.a());
                try {
                    fVar.j(Long.parseLong(sessionId));
                } catch (Exception e) {
                    com.netease.nimlib.log.b.e("MsgReceiveEventManager", String.format("failed to convert room id(%s) to long", iMMessage.getSessionId()), e);
                }
            }
            fVar.h(com.netease.nimlib.push.b.c());
            fVar.i(com.netease.nimlib.biz.a.c());
            if (com.netease.nimlib.abtest.b.d()) {
                boolean a3 = com.netease.nimlib.n.f.a.a();
                fVar.k(a3 ? com.netease.nimlib.n.f.a.b() : 0L);
                fVar.l(AppForegroundWatcherCompat.a(a3));
                fVar.m(AppForegroundWatcherCompat.b(a3));
                if (!fVar.J() && !com.netease.nimlib.abtest.b.f()) {
                    com.netease.nimlib.log.b.G("MsgReceiveEventManager down time not reliable and no need to upload to qs: " + fVar.m());
                    return;
                }
            }
            com.netease.nimlib.log.b.G("MsgReceiveEventManager startTrackEvent model = " + fVar.m());
            this.a.put(iMMessage.getUuid(), fVar);
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("MsgReceiveEventManager", " startTrackEvent Exception", th);
        }
    }

    /* compiled from: MsgReceiveEventManager.java */
    /* renamed from: com.netease.nimlib.n.f$1, reason: invalid class name */
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

    public void a(IMMessage iMMessage, int i) {
        try {
            com.netease.nimlib.n.e.f remove = this.a.remove(iMMessage.getUuid());
            if (remove == null) {
                return;
            }
            com.netease.nimlib.log.b.G("MsgReceiveEventManager stopTrackEvent resultCode = " + i);
            remove.d(i);
            if (i != 200) {
                if (com.netease.nimlib.n.a.a.b.containsKey(Integer.valueOf(i))) {
                    remove.k(com.netease.nimlib.n.a.a.b.get(Integer.valueOf(i)));
                } else {
                    remove.k(com.netease.nimlib.n.a.a.a);
                }
            }
            long a2 = com.netease.nimlib.n.f.a.a(remove.a());
            if (a2 > 0) {
                remove.h(a2);
            }
            com.netease.nimlib.apm.a.a("msgReceive", (com.netease.nimlib.apm.b.b<? extends com.netease.nimlib.apm.b.a>) remove);
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("MsgReceiveEventManager", " stopTrackEvent Exception", th);
        }
    }

    public void a(List<IMMessageImpl> list, long j, int i, long j2) {
        if (list == null || list.isEmpty() || !com.netease.nimlib.c.i().reportIgnoredMessage) {
            return;
        }
        for (IMMessageImpl iMMessageImpl : list) {
            a().a(iMMessageImpl, j, i, j2, true);
            com.netease.nimlib.log.b.I("report ignored message, uuid: " + iMMessageImpl.getUuid() + " sessionType: " + iMMessageImpl.getSessionType());
            a().a(iMMessageImpl, 200);
        }
    }
}
