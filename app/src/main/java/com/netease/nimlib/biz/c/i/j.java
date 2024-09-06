package com.netease.nimlib.biz.c.i;

import android.text.TextUtils;
import com.netease.nimlib.biz.e.j.s;
import com.netease.nimlib.biz.e.j.t;
import com.netease.nimlib.biz.e.j.y;
import com.netease.nimlib.sdk.msg.MessageBuilder;
import com.netease.nimlib.sdk.msg.constant.RevokeType;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.netease.nimlib.sdk.msg.model.RevokeMsgNotification;
import com.netease.nimlib.session.IMMessageImpl;
import com.netease.nimlib.session.MsgDBHelper;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import u.aly.df;

/* compiled from: RevokeMessageResponseHandler.java */
/* loaded from: classes.dex */
public class j extends com.netease.nimlib.biz.c.i {
    @Override // com.netease.nimlib.biz.c.a
    public void a(com.netease.nimlib.biz.e.a aVar) {
        if (aVar instanceof t) {
            d(aVar);
        } else if (aVar instanceof s) {
            a((s) aVar);
        } else if (aVar instanceof y) {
            b((y) aVar);
        }
    }

    private void b(y yVar) {
        IMMessageImpl iMMessageImpl;
        a(yVar);
        List<com.netease.nimlib.push.packet.b.c> b = yVar.b();
        ArrayList<RevokeMsgNotification> arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        HashSet hashSet = new HashSet();
        byte c = yVar.c();
        Iterator<com.netease.nimlib.push.packet.b.c> it = b.iterator();
        while (it.hasNext()) {
            com.netease.nimlib.push.packet.b.c next = it.next();
            String c2 = next.c(10);
            if (TextUtils.isEmpty(MsgDBHelper.queryRevokeMessage(c2))) {
                long e = next.e(6);
                long e2 = next.e(14);
                SessionTypeEnum a = a(next);
                IMMessageImpl iMMessageImpl2 = (IMMessageImpl) MsgDBHelper.queryMessageByUuid(c2);
                if (iMMessageImpl2 == null) {
                    iMMessageImpl = a(next, e2, a);
                } else {
                    if (MsgDBHelper.deleteMessage(iMMessageImpl2) > 0 && com.netease.nimlib.session.j.a((IMMessage) iMMessageImpl2, true)) {
                        hashSet.add(c2);
                    }
                    iMMessageImpl = iMMessageImpl2;
                }
                MsgDBHelper.saveRevokeMessage(c2);
                String c3 = next.c(16);
                if (c3 == null) {
                    c3 = next.c(3);
                }
                String str = c3;
                String c4 = next.c(4);
                String c5 = next.c(5);
                int d = next.d(1);
                Iterator<com.netease.nimlib.push.packet.b.c> it2 = it;
                arrayList.add(new RevokeMsgNotification(iMMessageImpl, c5, str, c4, c, RevokeType.typeOfValue(d), next.c(22)));
                if (e > 0) {
                    arrayList2.add(Long.valueOf(e));
                }
                it = it2;
            }
        }
        a(c, arrayList2);
        for (RevokeMsgNotification revokeMsgNotification : arrayList) {
            String uuid = revokeMsgNotification.getMessage().getUuid();
            a(revokeMsgNotification, !TextUtils.isEmpty(uuid) && hashSet.contains(uuid));
        }
    }

    protected void a(y yVar) {
        com.netease.nimlib.biz.l.h(yVar.a());
    }

    private void a(s sVar) {
        com.netease.nimlib.push.packet.b.c a = sVar.a();
        String c = a.c(10);
        if (TextUtils.isEmpty(MsgDBHelper.queryRevokeMessage(c))) {
            IMMessageImpl iMMessageImpl = (IMMessageImpl) MsgDBHelper.queryMessageByUuid(c);
            long e = a.e(14);
            SessionTypeEnum a2 = a(a);
            boolean z = false;
            if (iMMessageImpl == null) {
                iMMessageImpl = a(a, e, a2);
            } else if (MsgDBHelper.deleteMessage(iMMessageImpl) > 0 && com.netease.nimlib.session.j.a((IMMessage) iMMessageImpl, true)) {
                z = true;
            }
            IMMessageImpl iMMessageImpl2 = iMMessageImpl;
            MsgDBHelper.saveRevokeMessage(c);
            String c2 = a.c(5);
            String c3 = a.c(16);
            if (c3 == null) {
                c3 = a.c(3);
            }
            String str = c3;
            int d = a.d(1);
            a(new RevokeMsgNotification(iMMessageImpl2, c2, str, a.c(4), 0, RevokeType.typeOfValue(d), a.c(22)), z);
        }
    }

    private void d(com.netease.nimlib.biz.e.a aVar) {
        if (aVar.n()) {
            com.netease.nimlib.biz.d.i.o oVar = (com.netease.nimlib.biz.d.i.o) b(aVar);
            IMMessageImpl d = oVar != null ? oVar.d() : null;
            if (d != null) {
                if (MsgDBHelper.deleteMessage(d) > 0 && com.netease.nimlib.session.j.a((IMMessage) d, true)) {
                    com.netease.nimlib.session.j.a((IMMessage) d);
                }
                MsgDBHelper.saveRevokeMessage(d.getUuid());
                com.netease.nimlib.session.j.b((IMMessage) d);
            }
        }
        a(aVar, (Object) null);
    }

    private SessionTypeEnum a(com.netease.nimlib.push.packet.b.c cVar) {
        SessionTypeEnum sessionTypeEnum = SessionTypeEnum.None;
        int d = cVar.d(1);
        if (d != 7) {
            if (d != 8) {
                switch (d) {
                    case 12:
                        return SessionTypeEnum.SUPER_TEAM;
                    case 13:
                        break;
                    case 14:
                        break;
                    default:
                        return sessionTypeEnum;
                }
            }
            return SessionTypeEnum.Team;
        }
        return SessionTypeEnum.P2P;
    }

    private IMMessageImpl a(com.netease.nimlib.push.packet.b.c cVar, long j, SessionTypeEnum sessionTypeEnum) {
        String c = cVar.c(2);
        String c2 = cVar.c(3);
        if (!TextUtils.isEmpty(c) && c.equals(com.netease.nimlib.c.n())) {
            c = c2;
        }
        IMMessageImpl iMMessageImpl = (IMMessageImpl) MessageBuilder.createEmptyMessage(c, sessionTypeEnum, j);
        iMMessageImpl.setFromAccount(c2);
        return iMMessageImpl;
    }

    private void a(RevokeMsgNotification revokeMsgNotification, boolean z) {
        IMMessage message = revokeMsgNotification.getMessage();
        if (z) {
            com.netease.nimlib.session.j.a(message);
        }
        com.netease.nimlib.l.d.a(revokeMsgNotification);
        com.netease.nimlib.session.j.b(message);
        com.netease.nimlib.i.b.a(revokeMsgNotification);
    }

    protected void a(int i, List<Long> list) {
        if (i != 1) {
            return;
        }
        com.netease.nimlib.biz.d.e.a aVar = new com.netease.nimlib.biz.d.e.a();
        aVar.a((byte) 7);
        aVar.b(df.m);
        aVar.a(list);
        com.netease.nimlib.biz.i.a().a(aVar, com.netease.nimlib.biz.g.a.d);
    }
}
