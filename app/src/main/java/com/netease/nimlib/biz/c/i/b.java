package com.netease.nimlib.biz.c.i;

import com.netease.nimlib.sdk.msg.attachment.FileAttachment;
import com.netease.nimlib.sdk.msg.constant.AttachStatusEnum;
import com.netease.nimlib.sdk.msg.constant.MsgStatusEnum;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.CustomMessageConfig;
import com.netease.nimlib.sdk.msg.model.GetMessagesDynamicallyParam;
import com.netease.nimlib.sdk.team.model.IMMessageFilter;
import com.netease.nimlib.session.IMMessageImpl;
import com.netease.nimlib.session.MsgDBHelper;
import com.netease.nimlib.session.v;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* compiled from: CloudMsgHistoryResponseHandler.java */
/* loaded from: classes.dex */
public class b extends com.netease.nimlib.biz.c.i {

    /* compiled from: CloudMsgHistoryResponseHandler.java */
    /* loaded from: classes.dex */
    public static abstract class a {
        public abstract void a(int i, ArrayList<IMMessageImpl> arrayList, boolean z, Throwable th);

        public void a(ArrayList<IMMessageImpl> arrayList) {
        }
    }

    /* compiled from: CloudMsgHistoryResponseHandler.java */
    /* renamed from: com.netease.nimlib.biz.c.i.b$1 */
    /* loaded from: classes.dex */
    class AnonymousClass1 extends a {
        final /* synthetic */ com.netease.nimlib.biz.e.a a;
        final /* synthetic */ com.netease.nimlib.biz.d.i.k b;

        AnonymousClass1(com.netease.nimlib.biz.e.a aVar, com.netease.nimlib.biz.d.i.k kVar) {
            r2 = aVar;
            r3 = kVar;
        }

        @Override // com.netease.nimlib.biz.c.i.b.a
        public void a(ArrayList<IMMessageImpl> arrayList) {
            super.a(arrayList);
            b.this.a(r2, arrayList);
        }

        @Override // com.netease.nimlib.biz.c.i.b.a
        public void a(int i, ArrayList<IMMessageImpl> arrayList, boolean z, Throwable th) {
            com.netease.nimlib.biz.d.i.k kVar = r3;
            if (kVar != null && !kVar.d() && com.netease.nimlib.o.f.d(arrayList)) {
                ArrayList<IMMessageImpl> arrayList2 = new ArrayList<>(arrayList);
                Collections.reverse(arrayList2);
                arrayList = arrayList2;
            }
            com.netease.nimlib.session.a.c.a().a(r3, (GetMessagesDynamicallyParam) null, arrayList, (com.netease.nimlib.i.k) null);
        }
    }

    @Override // com.netease.nimlib.biz.c.a
    public void a(com.netease.nimlib.biz.e.a aVar) {
        com.netease.nimlib.biz.d.i.k kVar = (com.netease.nimlib.biz.d.i.k) b(aVar);
        a(aVar, kVar, new a() { // from class: com.netease.nimlib.biz.c.i.b.1
            final /* synthetic */ com.netease.nimlib.biz.e.a a;
            final /* synthetic */ com.netease.nimlib.biz.d.i.k b;

            AnonymousClass1(com.netease.nimlib.biz.e.a aVar2, com.netease.nimlib.biz.d.i.k kVar2) {
                r2 = aVar2;
                r3 = kVar2;
            }

            @Override // com.netease.nimlib.biz.c.i.b.a
            public void a(ArrayList<IMMessageImpl> arrayList) {
                super.a(arrayList);
                b.this.a(r2, arrayList);
            }

            @Override // com.netease.nimlib.biz.c.i.b.a
            public void a(int i, ArrayList<IMMessageImpl> arrayList, boolean z, Throwable th) {
                com.netease.nimlib.biz.d.i.k kVar2 = r3;
                if (kVar2 != null && !kVar2.d() && com.netease.nimlib.o.f.d(arrayList)) {
                    ArrayList<IMMessageImpl> arrayList2 = new ArrayList<>(arrayList);
                    Collections.reverse(arrayList2);
                    arrayList = arrayList2;
                }
                com.netease.nimlib.session.a.c.a().a(r3, (GetMessagesDynamicallyParam) null, arrayList, (com.netease.nimlib.i.k) null);
            }
        });
    }

    public static void a(com.netease.nimlib.biz.e.a aVar, com.netease.nimlib.biz.d.i.k kVar, a aVar2) {
        boolean z;
        int i = 0;
        if (!aVar.n() || kVar == null) {
            if (aVar2 != null) {
                aVar2.a(null);
                aVar2.a(aVar.r(), null, false, null);
                return;
            }
            return;
        }
        List<com.netease.nimlib.push.packet.b.c> a2 = ((com.netease.nimlib.biz.e.j.m) aVar).a();
        ArrayList<IMMessageImpl> arrayList = new ArrayList<>(a2.size());
        ArrayList arrayList2 = new ArrayList();
        HashSet hashSet = new HashSet(a2.size());
        Iterator<com.netease.nimlib.push.packet.b.c> it = a2.iterator();
        while (true) {
            z = true;
            if (!it.hasNext()) {
                break;
            }
            com.netease.nimlib.push.packet.b.c next = it.next();
            if (!hashSet.contains(next.c(11))) {
                IMMessageImpl a3 = com.netease.nimlib.session.g.a(next, false, false);
                IMMessageFilter h = kVar.h();
                if (a3 != null && (h == null || !h.shouldIgnore(a3))) {
                    arrayList.add(a3);
                    hashSet.add(a3.getUuid());
                    if (kVar.e() && MsgDBHelper.queryMessageIdByUuid(a3.getUuid()) == 0) {
                        arrayList2.add(a3);
                    } else if (kVar.e()) {
                        a3.setStatus(MsgStatusEnum.statusOfValue(MsgDBHelper.queryStatus(a3.getUuid(), false)));
                        if (a3.getAttachment() instanceof FileAttachment) {
                            a3.setAttachStatus(AttachStatusEnum.statusOfValue(MsgDBHelper.queryStatus(a3.getUuid(), true)));
                        }
                    }
                }
            }
        }
        com.netease.nimlib.session.g.b(arrayList);
        if (com.netease.nimlib.o.f.c((Collection) arrayList)) {
            com.netease.nimlib.log.b.d("CloudMsgHistoryResponseHandler", "empty result");
        } else {
            IMMessageImpl iMMessageImpl = arrayList.get(0);
            String uuid = iMMessageImpl == null ? "" : iMMessageImpl.getUuid();
            IMMessageImpl iMMessageImpl2 = arrayList.get(arrayList.size() - 1);
            com.netease.nimlib.log.b.d("CloudMsgHistoryResponseHandler", String.format("first msg is %s; last msg is %s", uuid, iMMessageImpl2 != null ? iMMessageImpl2.getUuid() : ""));
        }
        if (aVar2 != null) {
            aVar2.a(arrayList);
        }
        if (arrayList2.size() > 0) {
            List<IMMessageImpl> a4 = com.netease.nimlib.session.j.a(arrayList2, kVar.g());
            if (!a4.isEmpty()) {
                IMMessageImpl iMMessageImpl3 = a4.get(0);
                String sessionId = iMMessageImpl3.getSessionId();
                SessionTypeEnum sessionType = iMMessageImpl3.getSessionType();
                com.netease.nimlib.session.q queryRecentContact = MsgDBHelper.queryRecentContact(sessionId, sessionType);
                int unreadCount = queryRecentContact == null ? 0 : queryRecentContact.getUnreadCount();
                if (kVar.m()) {
                    i = v.b(sessionId, sessionType) - unreadCount;
                } else {
                    for (IMMessageImpl iMMessageImpl4 : a4) {
                        CustomMessageConfig config = iMMessageImpl4.getConfig();
                        if (config == null) {
                            config = new CustomMessageConfig();
                        }
                        config.enableUnreadCount = false;
                        iMMessageImpl4.setConfig(config);
                    }
                }
                z = MsgDBHelper.saveMessages(a4);
                com.netease.nimlib.i.b.a(com.netease.nimlib.session.j.a(iMMessageImpl3, i));
            }
        }
        if (aVar2 != null) {
            aVar2.a(aVar.r(), arrayList, z, null);
        }
    }
}
