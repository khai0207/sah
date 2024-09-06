package com.netease.nimlib.biz.c.i;

import com.netease.nimlib.biz.e.j.v;
import com.netease.nimlib.biz.e.j.x;
import com.netease.nimlib.sdk.msg.model.MessageReceipt;
import com.netease.nimlib.session.MsgDBHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: MessageReceiptResponseHandler.java */
/* loaded from: classes.dex */
public class f extends com.netease.nimlib.biz.c.i {
    @Override // com.netease.nimlib.biz.c.a
    public void a(com.netease.nimlib.biz.e.a aVar) {
        if (!aVar.n()) {
            a(aVar, null);
            return;
        }
        if (aVar instanceof v) {
            com.netease.nimlib.biz.d.i.q qVar = (com.netease.nimlib.biz.d.i.q) b(aVar);
            MessageReceipt messageReceipt = new MessageReceipt(qVar.d(), Math.min(qVar.e(), ((v) aVar).a()));
            MsgDBHelper.saveSendReceiptRecord(messageReceipt);
            com.netease.nimlib.session.e.b().b(messageReceipt);
            a(aVar, null);
            return;
        }
        if (aVar instanceof com.netease.nimlib.biz.e.j.o) {
            a((com.netease.nimlib.biz.e.j.o) aVar);
        } else if (aVar instanceof x) {
            a((x) aVar);
        }
    }

    private void a(com.netease.nimlib.biz.e.j.o oVar) {
        String a = oVar.a();
        String b = oVar.b();
        long c = oVar.c();
        long messageTimeByUuid = MsgDBHelper.getMessageTimeByUuid(b);
        if (messageTimeByUuid <= 0) {
            messageTimeByUuid = c;
        }
        com.netease.nimlib.session.f fVar = new com.netease.nimlib.session.f(a, messageTimeByUuid, c);
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(fVar);
        a(arrayList);
    }

    private void a(x xVar) {
        a(xVar.a());
        com.netease.nimlib.biz.l.r(xVar.b());
    }

    private void a(List<com.netease.nimlib.session.f> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        ArrayList arrayList = new ArrayList(list.size());
        ArrayList arrayList2 = new ArrayList(list.size());
        Iterator<com.netease.nimlib.session.f> it = list.iterator();
        while (it.hasNext()) {
            arrayList2.add(it.next().a);
        }
        Map<String, com.netease.nimlib.session.f> queryMessageReceipt = MsgDBHelper.queryMessageReceipt(arrayList2);
        for (com.netease.nimlib.session.f fVar : list) {
            com.netease.nimlib.session.f fVar2 = queryMessageReceipt.get(fVar.a);
            if (fVar2 == null || fVar.c > fVar2.c) {
                fVar.b = fVar.c;
                arrayList.add(fVar);
            }
        }
        if (arrayList.isEmpty()) {
            return;
        }
        MsgDBHelper.saveMessageReceipt(arrayList);
        com.netease.nimlib.session.e.b().a(arrayList);
        com.netease.nimlib.i.b.c(b(arrayList));
    }

    private List<MessageReceipt> b(List<com.netease.nimlib.session.f> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (com.netease.nimlib.session.f fVar : list) {
            arrayList.add(new MessageReceipt(fVar.a, fVar.b));
        }
        return arrayList;
    }
}
