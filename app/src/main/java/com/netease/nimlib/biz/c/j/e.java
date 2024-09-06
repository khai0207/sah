package com.netease.nimlib.biz.c.j;

import com.netease.nimlib.biz.c.i;
import com.netease.nimlib.biz.d.j.h;
import com.netease.nimlib.biz.d.j.j;
import com.netease.nimlib.biz.e.k.k;
import com.netease.nimlib.o.f;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.netease.nimlib.session.IMMessageImpl;
import com.netease.nimlib.session.MsgDBHelper;
import com.netease.nimlib.session.ab;
import com.netease.nimlib.session.g;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ThreadTalkResponseHandler.java */
/* loaded from: classes.dex */
public class e extends i {
    @Override // com.netease.nimlib.biz.c.a
    public void a(com.netease.nimlib.biz.e.a aVar) {
        if (!aVar.n()) {
            a(aVar, null);
        } else if (aVar instanceof k) {
            a((k) aVar);
        } else if (aVar instanceof com.netease.nimlib.biz.e.k.i) {
            a((com.netease.nimlib.biz.e.k.i) aVar);
        }
    }

    private void a(k kVar) {
        boolean d = ((j) b(kVar)).d();
        com.netease.nimlib.push.packet.b.c a = kVar.a();
        IMMessageImpl a2 = g.a(a, false, false);
        if (a2 == null) {
            com.netease.nimlib.log.b.d("ThreadTalkResponseHandler", "failed to convert from Property to , uuid is " + a.c(11));
        }
        List<com.netease.nimlib.push.packet.b.c> d2 = kVar.d();
        ArrayList arrayList = new ArrayList(d2.size());
        for (com.netease.nimlib.push.packet.b.c cVar : d2) {
            IMMessageImpl a3 = g.a(cVar, false, false);
            if (a3 == null) {
                com.netease.nimlib.log.b.d("ThreadTalkResponseHandler", "failed to convert from Property to IMMessage, uuid is " + cVar.c(11));
            } else {
                arrayList.add(a3);
            }
        }
        if (d) {
            List d3 = f.d(arrayList, new f.a() { // from class: com.netease.nimlib.biz.c.j.-$$Lambda$e$LtQzMSTp_ipyVfz8omwth7esTL8
                @Override // com.netease.nimlib.o.f.a
                public final Object transform(Object obj) {
                    Boolean a4;
                    a4 = e.a((IMMessageImpl) obj);
                    return a4;
                }
            });
            if (a2 != null && g.a(a2.getUuid())) {
                d3.add(a2);
            }
            MsgDBHelper.saveMessages(d3);
        }
        a(kVar, new ab(a2, kVar.c(), kVar.b(), arrayList));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Boolean a(IMMessageImpl iMMessageImpl) {
        return Boolean.valueOf(iMMessageImpl != null && g.a(iMMessageImpl.getUuid()));
    }

    private void a(com.netease.nimlib.biz.e.k.i iVar) {
        boolean d = ((h) b(iVar)).d();
        List<com.netease.nimlib.push.packet.b.c> a = iVar.a();
        ArrayList arrayList = new ArrayList(a.size());
        for (com.netease.nimlib.push.packet.b.c cVar : a) {
            if (cVar == null) {
                com.netease.nimlib.log.b.d("ThreadTalkResponseHandler", "with null in the received property list");
            } else {
                IMMessageImpl a2 = g.a(cVar, false, false);
                if (a2 == null) {
                    com.netease.nimlib.log.b.d("ThreadTalkResponseHandler", "failed to convert from Property to IMMessage, uuid is " + cVar.c(11));
                } else {
                    if (d && g.a(a2.getUuid())) {
                        MsgDBHelper.saveMessage(a2);
                    }
                    arrayList.add(a2);
                }
            }
        }
        com.netease.nimlib.log.b.d("ThreadTalkResponseHandler", "onQueryHistoryByIdsResponse, " + f.a(arrayList, ", ", new f.a() { // from class: com.netease.nimlib.biz.c.j.-$$Lambda$e$_v5T8DVtMR2XS6HwyGnz_DL6_3E
            @Override // com.netease.nimlib.o.f.a
            public final Object transform(Object obj) {
                String a3;
                a3 = e.a((IMMessage) obj);
                return a3;
            }
        }));
        a(iVar, arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ String a(IMMessage iMMessage) {
        return iMMessage == null ? "" : iMMessage.getUuid();
    }
}
