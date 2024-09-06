package com.netease.nimlib.team;

import android.util.Pair;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.netease.nimlib.session.IMMessageImpl;
import com.netease.nimlib.session.MsgDBHelper;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* compiled from: TeamMsgReceiptSender.java */
/* loaded from: classes.dex */
public class k {
    private b a = new b();

    /* compiled from: TeamMsgReceiptSender.java */
    /* loaded from: classes.dex */
    private static class a {
        static final k a = new k();
    }

    public void a(IMMessage iMMessage) {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(iMMessage);
        this.a.b(arrayList);
    }

    public void a() {
        this.a.a();
    }

    /* compiled from: TeamMsgReceiptSender.java */
    /* loaded from: classes.dex */
    private class b extends com.netease.nimlib.c.a.a<IMMessage> {
        private b() {
            super(500, "NIM_TEAM_MSG_RECEIPT_SENDER", 50);
        }

        @Override // com.netease.nimlib.c.a.a
        public void a(List<IMMessage> list) {
            if (list == null) {
                return;
            }
            final ArrayList arrayList = new ArrayList(list.size());
            HashSet hashSet = new HashSet(list.size());
            for (IMMessage iMMessage : list) {
                if (!hashSet.contains(iMMessage.getUuid())) {
                    hashSet.add(iMMessage.getUuid());
                    arrayList.add(iMMessage);
                }
            }
            final List<IMMessage> a = h.c().a(arrayList);
            List<Pair<String, Long>> c = c(a);
            if (c == null || c.isEmpty()) {
                k.this.a(list, 200);
                return;
            }
            com.netease.nimlib.log.b.N("send team message receipts request, size=" + c.size());
            com.netease.nimlib.biz.i.a().a(new com.netease.nimlib.biz.g.b(new com.netease.nimlib.biz.d.k.a(c)) { // from class: com.netease.nimlib.team.k.b.1
                @Override // com.netease.nimlib.biz.g.b, com.netease.nimlib.biz.g.c
                public void a(com.netease.nimlib.biz.e.a aVar) {
                    if (aVar.n()) {
                        HashSet hashSet2 = new HashSet(a.size());
                        Iterator it = a.iterator();
                        while (it.hasNext()) {
                            hashSet2.add(((IMMessage) it.next()).getUuid());
                        }
                        ArrayList arrayList2 = null;
                        Set<String> a2 = ((com.netease.nimlib.biz.e.l.a) aVar).a();
                        if (a2 != null) {
                            arrayList2 = new ArrayList(a2.size());
                            for (IMMessage iMMessage2 : a) {
                                if (a2.contains(iMMessage2.getUuid())) {
                                    arrayList2.add(iMMessage2);
                                    hashSet2.remove(iMMessage2.getUuid());
                                } else if (iMMessage2 instanceof IMMessageImpl) {
                                    ((IMMessageImpl) iMMessage2).setHasSendAck(true);
                                }
                            }
                        }
                        MsgDBHelper.markHasSendTeamMsgAck(new ArrayList(hashSet2));
                        h.c().b(arrayList2);
                        k.this.a(arrayList, 200);
                        return;
                    }
                    if (aVar.r() != 414) {
                        h.c().b(a);
                    }
                    k.this.a(arrayList, aVar.r());
                }
            });
        }

        private List<Pair<String, Long>> c(List<IMMessage> list) {
            if (list == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList(list.size());
            for (IMMessage iMMessage : list) {
                arrayList.add(new Pair(iMMessage.getSessionId(), Long.valueOf(((IMMessageImpl) iMMessage).getServerId())));
            }
            return arrayList;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(List<IMMessage> list, int i) {
        if (list == null) {
            return;
        }
        com.netease.nimlib.log.b.N("reply team message receipts request, size=" + list.size() + ", code=" + i);
        Iterator<IMMessage> it = list.iterator();
        while (it.hasNext()) {
            List<com.netease.nimlib.i.k> d = h.c().d(it.next().getUuid());
            if (d != null) {
                for (com.netease.nimlib.i.k kVar : d) {
                    if (kVar == null) {
                        com.netease.nimlib.log.b.N("reply team message transaction == null");
                    } else {
                        kVar.a(i).b();
                    }
                }
            }
        }
    }

    public static k b() {
        return a.a;
    }
}
