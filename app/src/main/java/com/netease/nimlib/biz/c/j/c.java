package com.netease.nimlib.biz.c.j;

import android.text.TextUtils;
import com.netease.nimlib.biz.c.i;
import com.netease.nimlib.biz.d.j.m;
import com.netease.nimlib.biz.e.k.f;
import com.netease.nimlib.biz.e.k.j;
import com.netease.nimlib.biz.e.k.o;
import com.netease.nimlib.biz.e.k.p;
import com.netease.nimlib.sdk.msg.model.HandleQuickCommentOption;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.netease.nimlib.sdk.msg.model.MessageKey;
import com.netease.nimlib.sdk.msg.model.QuickCommentOption;
import com.netease.nimlib.sdk.msg.model.QuickCommentOptionWrapper;
import com.netease.nimlib.session.IMMessageImpl;
import com.netease.nimlib.session.MsgDBHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* compiled from: QuickCommentResponseHandler.java */
/* loaded from: classes.dex */
public class c extends i {
    @Override // com.netease.nimlib.biz.c.a
    public void a(com.netease.nimlib.biz.e.a aVar) {
        if (!aVar.n()) {
            a(aVar, (Object) null);
            return;
        }
        if (aVar instanceof f) {
            a((f) aVar);
            return;
        }
        if (aVar instanceof p) {
            a((p) aVar);
            return;
        }
        if (aVar instanceof com.netease.nimlib.biz.e.k.e) {
            a((com.netease.nimlib.biz.e.k.e) aVar);
        } else if (aVar instanceof o) {
            a((o) aVar);
        } else if (aVar instanceof j) {
            a((j) aVar);
        }
    }

    private void a(f fVar) {
        com.netease.nimlib.biz.d.j.c cVar = (com.netease.nimlib.biz.d.j.c) b(fVar);
        if (cVar == null) {
            com.netease.nimlib.log.b.d("QuickCommentResponseHandler", "retrieveRequest failed");
            a(fVar, (Object) null);
            return;
        }
        long a = fVar.a();
        IMMessageImpl a2 = a(cVar.d());
        if (a2 == null) {
            com.netease.nimlib.log.b.d("QuickCommentResponseHandler", "add: msg not in db");
            a(fVar, (Object) null);
        } else if (!a2.hasPulledQuickComment()) {
            com.netease.nimlib.log.b.d("QuickCommentResponseHandler", "add: sync quick comment to enable the table");
            a(fVar, (Object) null);
        } else {
            a(a2, a);
            a(a2.getUuid(), new QuickCommentOption(com.netease.nimlib.c.n(), cVar.e(), a, cVar.f()));
            a(fVar, (Object) null);
        }
    }

    private void a(p pVar) {
        m mVar = (m) b(pVar);
        if (mVar == null || mVar.d() == null) {
            com.netease.nimlib.log.b.d("QuickCommentResponseHandler", "retrieveRequest failed");
            a(pVar, (Object) null);
            return;
        }
        long a = pVar.a();
        IMMessageImpl a2 = a(mVar.d());
        if (a2 == null) {
            com.netease.nimlib.log.b.d("QuickCommentResponseHandler", "remove: msg not in db");
            a(pVar, (Object) null);
        } else if (!a2.hasPulledQuickComment()) {
            com.netease.nimlib.log.b.d("QuickCommentResponseHandler", "remove: sync quick comment to enable the table");
            a(pVar, (Object) null);
        } else {
            a(a2, a);
            MsgDBHelper.deleteQuickComment(a2.getUuid(), com.netease.nimlib.c.n(), mVar.e());
            a(pVar, (Object) null);
        }
    }

    private void a(com.netease.nimlib.biz.e.k.e eVar) {
        HandleQuickCommentOption handleQuickCommentOption = new HandleQuickCommentOption(eVar.a(), eVar.b());
        MessageKey key = handleQuickCommentOption.getKey();
        QuickCommentOption commentOption = handleQuickCommentOption.getCommentOption();
        if (key != null && commentOption != null) {
            String uuid = key.getUuid();
            IMMessageImpl iMMessageImpl = (IMMessageImpl) MsgDBHelper.queryMessageByUuid(uuid);
            if (iMMessageImpl != null && iMMessageImpl.hasPulledQuickComment()) {
                com.netease.nimlib.log.b.d("QuickCommentResponseHandler", "notify add: msg not exist or has not sync yet");
                a(uuid, commentOption);
                a(iMMessageImpl, commentOption.getTime());
            }
        }
        com.netease.nimlib.i.b.a(handleQuickCommentOption);
    }

    private void a(o oVar) {
        HandleQuickCommentOption handleQuickCommentOption = new HandleQuickCommentOption(oVar.a(), oVar.b());
        MessageKey key = handleQuickCommentOption.getKey();
        QuickCommentOption commentOption = handleQuickCommentOption.getCommentOption();
        if (key != null && commentOption != null) {
            String uuid = key.getUuid();
            IMMessageImpl iMMessageImpl = (IMMessageImpl) MsgDBHelper.queryMessageByUuid(uuid);
            if (iMMessageImpl != null && iMMessageImpl.hasPulledQuickComment()) {
                com.netease.nimlib.log.b.d("QuickCommentResponseHandler", "notify remove: msg not exist or has not sync yet");
                MsgDBHelper.deleteQuickComment(uuid, commentOption.getFromAccount(), commentOption.getReplyType());
                a(iMMessageImpl, commentOption.getTime());
            }
        }
        com.netease.nimlib.i.b.b(handleQuickCommentOption);
    }

    private void a(j jVar) {
        List<IMMessage> b = b(jVar);
        int size = b.size();
        HashMap<String, QuickCommentOptionWrapper> c = c(jVar);
        ArrayList arrayList = new ArrayList(size);
        Iterator<IMMessage> it = b.iterator();
        while (it.hasNext()) {
            IMMessageImpl a = a(it.next());
            if (a != null) {
                String uuid = a.getUuid();
                QuickCommentOptionWrapper quickCommentOptionWrapper = c.get(uuid);
                if (quickCommentOptionWrapper == null || !quickCommentOptionWrapper.isModify()) {
                    quickCommentOptionWrapper = new QuickCommentOptionWrapper(a.getMessageKey(), MsgDBHelper.queryQuickCommentByUuid(uuid), false, quickCommentOptionWrapper == null ? a.getQuickCommentUpdateTime() : quickCommentOptionWrapper.getTime());
                } else {
                    MsgDBHelper.deleteQuickComment(uuid);
                    MsgDBHelper.saveQuickComment(uuid, quickCommentOptionWrapper.getQuickCommentList());
                }
                a(a, quickCommentOptionWrapper.getTime());
                arrayList.add(quickCommentOptionWrapper);
            }
        }
        a(jVar, arrayList);
    }

    private List<IMMessage> b(j jVar) {
        List<IMMessage> d;
        com.netease.nimlib.biz.d.j.i iVar = (com.netease.nimlib.biz.d.j.i) b((com.netease.nimlib.biz.e.a) jVar);
        return (iVar == null || (d = iVar.d()) == null) ? new ArrayList(0) : d;
    }

    private HashMap<String, QuickCommentOptionWrapper> c(j jVar) {
        List<com.netease.nimlib.push.packet.b.c> a = jVar.a();
        if (a == null) {
            a = new ArrayList<>(0);
        }
        HashMap<String, QuickCommentOptionWrapper> hashMap = new HashMap<>(a.size() << 1);
        Iterator<com.netease.nimlib.push.packet.b.c> it = a.iterator();
        while (it.hasNext()) {
            QuickCommentOptionWrapper fromProperty = QuickCommentOptionWrapper.fromProperty(it.next());
            MessageKey key = fromProperty.getKey();
            if (key != null) {
                String uuid = key.getUuid();
                if (!TextUtils.isEmpty(uuid)) {
                    hashMap.put(uuid, fromProperty);
                }
            }
        }
        return hashMap;
    }

    private void a(IMMessageImpl iMMessageImpl, long j) {
        com.netease.nimlib.log.b.d("QuickCommentResponseHandler", "do update time tag, time=" + j);
        iMMessageImpl.setQuickCommentUpdateTime(j);
        MsgDBHelper.updateMessage(iMMessageImpl);
    }

    private void a(String str, QuickCommentOption quickCommentOption) {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(quickCommentOption);
        MsgDBHelper.saveQuickComment(str, arrayList);
    }

    private IMMessageImpl a(IMMessage iMMessage) {
        if (iMMessage == null) {
            return null;
        }
        String uuid = iMMessage.getUuid();
        if (TextUtils.isEmpty(uuid)) {
            return null;
        }
        IMMessage queryMessageByUuid = MsgDBHelper.queryMessageByUuid(uuid);
        if (queryMessageByUuid instanceof IMMessageImpl) {
            return (IMMessageImpl) queryMessageByUuid;
        }
        return null;
    }
}
