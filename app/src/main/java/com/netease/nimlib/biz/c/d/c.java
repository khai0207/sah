package com.netease.nimlib.biz.c.d;

import android.os.SystemClock;
import com.netease.nimlib.biz.c.i;
import com.netease.nimlib.biz.e.f.j;
import com.netease.nimlib.n.p;
import com.netease.nimlib.o.f;
import com.netease.nimlib.sdk.msg.constant.MsgStatusEnum;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.netease.nimlib.session.IMMessageImpl;
import com.netease.nimlib.session.MsgDBHelper;
import com.netease.nimlib.session.g;
import com.netease.nimlib.session.q;
import com.netease.nimlib.session.u;
import defpackage.C$r8$backportedMethods$utility$Objects$2$equals;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: SyncUnreadMsgResponseHandler.java */
/* loaded from: classes.dex */
public class c extends i {
    @Override // com.netease.nimlib.biz.c.a
    public void a(com.netease.nimlib.biz.e.a aVar) {
        q a;
        IMMessage iMMessage;
        String str;
        ArrayList arrayList;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        com.netease.nimlib.push.packet.a j = aVar.j();
        com.netease.nimlib.log.b.d("SyncUnreadMsgResponseHandler", "SyncUnreadMsgResponse processResponse IN," + (j != null ? j.toString() : null));
        if (aVar.n()) {
            boolean d = d(aVar);
            List<com.netease.nimlib.push.packet.b.c> a2 = ((j) aVar).a();
            if (a2 == null || a2.size() == 0) {
                p.a(j, 0);
                return;
            }
            com.netease.nimlib.log.b.d("SyncUnreadMsgResponseHandler", "current msg size = " + a2.size());
            com.netease.nimlib.session.a.c.a().a(a2, d ? com.netease.nimlib.session.a.a.OFFLINE : com.netease.nimlib.session.a.a.ROAM);
            ArrayList arrayList2 = new ArrayList();
            HashSet hashSet = new HashSet();
            int size = a2.size();
            while (true) {
                size--;
                if (size < 0) {
                    break;
                }
                com.netease.nimlib.push.packet.b.c cVar = a2.get(size);
                if (!hashSet.contains(cVar.c(11))) {
                    hashSet.add(cVar.c(11));
                    arrayList2.add(cVar);
                }
            }
            Set<String> queryExistUuidsByUuids = MsgDBHelper.queryExistUuidsByUuids(arrayList2);
            com.netease.nimlib.log.b.d("SyncUnreadMsgResponseHandler", " msg has exist = " + queryExistUuidsByUuids);
            HashMap hashMap = new HashMap();
            String n = com.netease.nimlib.c.n();
            ArrayList arrayList3 = new ArrayList();
            ArrayList arrayList4 = new ArrayList();
            ArrayList<IMMessageImpl> arrayList5 = new ArrayList();
            ArrayList arrayList6 = new ArrayList();
            ArrayList arrayList7 = new ArrayList(f.e(a2));
            HashMap hashMap2 = new HashMap();
            int size2 = arrayList2.size() - 1;
            while (size2 >= 0) {
                com.netease.nimlib.push.packet.a aVar2 = j;
                com.netease.nimlib.push.packet.b.c cVar2 = arrayList2.get(size2);
                long j2 = elapsedRealtime;
                String c = cVar2.c(2);
                ArrayList arrayList8 = arrayList2;
                String c2 = cVar2.c(11);
                boolean z = !C$r8$backportedMethods$utility$Objects$2$equals.equals(c, n);
                IMMessageImpl a3 = g.a(cVar2);
                arrayList7.add(a3);
                if (com.netease.nimlib.session.j.c((IMMessage) a3)) {
                    com.netease.nimlib.log.b.d("SyncUnreadMsgResponseHandler", "IMMessageFilter ignore received message, uuid= " + a3.getUuid());
                } else {
                    if (z) {
                        if (!queryExistUuidsByUuids.contains(c2)) {
                            String sessionId = a3.getSessionId();
                            ArrayList arrayList9 = (ArrayList) hashMap2.get(sessionId);
                            str = n;
                            if (arrayList9 == null) {
                                arrayList = new ArrayList();
                                hashMap2.put(sessionId, arrayList);
                            } else {
                                arrayList = arrayList9;
                            }
                            arrayList.add(a3);
                            arrayList3.add(a3);
                            arrayList4.add(cVar2);
                            hashMap.put(c, a3.getMsgFromNick());
                        }
                    } else {
                        str = n;
                        if (queryExistUuidsByUuids.contains(c2)) {
                            arrayList5.add(a3);
                            arrayList6.add(cVar2);
                        } else {
                            String sessionId2 = a3.getSessionId();
                            ArrayList arrayList10 = (ArrayList) hashMap2.get(sessionId2);
                            if (arrayList10 == null) {
                                arrayList10 = new ArrayList();
                                hashMap2.put(sessionId2, arrayList10);
                            }
                            arrayList10.add(a3);
                            arrayList3.add(a3);
                            arrayList4.add(cVar2);
                        }
                    }
                    size2--;
                    arrayList2 = arrayList8;
                    j = aVar2;
                    elapsedRealtime = j2;
                    n = str;
                }
                str = n;
                size2--;
                arrayList2 = arrayList8;
                j = aVar2;
                elapsedRealtime = j2;
                n = str;
            }
            long j3 = elapsedRealtime;
            com.netease.nimlib.push.packet.a aVar3 = j;
            ArrayList arrayList11 = arrayList2;
            if (MsgDBHelper.saveMessages(arrayList3)) {
                com.netease.nimlib.session.a.c.a().b(arrayList7, d ? com.netease.nimlib.session.a.a.OFFLINE : com.netease.nimlib.session.a.a.ROAM);
            }
            u.c().a(arrayList3);
            Map<String, IMMessage> queryMsgMapByProperty = MsgDBHelper.queryMsgMapByProperty(arrayList6);
            MsgDBHelper.updateSyncSelfMessageStatus(arrayList5);
            for (IMMessageImpl iMMessageImpl : arrayList5) {
                if (iMMessageImpl != null && (iMMessage = queryMsgMapByProperty.get(iMMessageImpl.getUuid())) != null && (iMMessage.getStatus() != MsgStatusEnum.success || iMMessage.isInBlackList() != iMMessageImpl.isInBlackList())) {
                    com.netease.nimlib.i.b.a(iMMessageImpl);
                }
            }
            com.netease.nimlib.session.j.b((List<com.netease.nimlib.push.packet.b.c>) arrayList4);
            com.netease.nimlib.session.j.d(arrayList3);
            ArrayList arrayList12 = new ArrayList();
            for (String str2 : hashMap2.keySet()) {
                ArrayList arrayList13 = (ArrayList) hashMap2.get(str2);
                com.netease.nimlib.log.b.d("SyncUnreadMsgResponseHandler", "session id = " + str2);
                if (!f.c((Collection) arrayList13)) {
                    if (com.netease.nimlib.c.i().sessionReadAck) {
                        a = g.a((ArrayList<IMMessageImpl>) arrayList13, (String) hashMap.get(str2), false);
                    } else if (d) {
                        a = g.a((ArrayList<IMMessageImpl>) arrayList13, (String) hashMap.get(str2), false);
                    } else {
                        g.b((ArrayList<IMMessageImpl>) arrayList13);
                        com.netease.nimlib.i.b.b(arrayList13);
                        a = com.netease.nimlib.session.j.a((IMMessageImpl) arrayList13.get(arrayList13.size() - 1));
                    }
                    if (a != null) {
                        arrayList12.add(a);
                    }
                }
            }
            if (!arrayList12.isEmpty()) {
                com.netease.nimlib.i.b.e(arrayList12);
            }
            if (d) {
                a(arrayList11, aVar.l());
            }
            StringBuilder sb = new StringBuilder();
            sb.append("received ");
            sb.append(d ? "offline" : "roaming");
            sb.append(" messages, count=" + arrayList3.size());
            com.netease.nimlib.log.b.N(sb.toString());
            com.netease.nimlib.log.b.d("SyncUnreadMsgResponseHandler", "SyncUnreadMsgResponse processResponse OUT,cost = " + (SystemClock.elapsedRealtime() - j3) + "," + aVar3);
            p.a(aVar3, a2.size());
        }
    }

    protected boolean d(com.netease.nimlib.biz.e.a aVar) {
        return aVar.q() == 4;
    }

    protected void a(List<com.netease.nimlib.push.packet.b.c> list, int i) {
        ArrayList arrayList = new ArrayList(list.size());
        HashMap hashMap = new HashMap();
        for (com.netease.nimlib.push.packet.b.c cVar : list) {
            long e = cVar.e(12);
            int d = cVar.d(0);
            if (d == 0) {
                arrayList.add(Long.valueOf(e));
            } else if (d == 1) {
                String c = cVar.c(1);
                Long l = (Long) hashMap.get(c);
                if (l == null || l.longValue() < e) {
                    hashMap.put(c, Long.valueOf(e));
                }
            }
        }
        if (!arrayList.isEmpty()) {
            com.netease.nimlib.biz.d.e.a aVar = new com.netease.nimlib.biz.d.e.a();
            aVar.a((byte) 7);
            aVar.b((byte) 2);
            aVar.a((List<Long>) arrayList);
            com.netease.nimlib.biz.i.a().a(aVar, com.netease.nimlib.biz.g.a.d);
        }
        if (hashMap.isEmpty()) {
            return;
        }
        ArrayList arrayList2 = new ArrayList(hashMap.values());
        com.netease.nimlib.biz.d.e.a aVar2 = new com.netease.nimlib.biz.d.e.a();
        aVar2.a((byte) 8);
        aVar2.b((byte) 3);
        aVar2.a((List<Long>) arrayList2);
        if (i > 0) {
            aVar2.a(i);
        }
        com.netease.nimlib.biz.i.a().a(aVar2, com.netease.nimlib.biz.g.a.d);
    }
}
