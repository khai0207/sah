package com.netease.nimlib.biz.d.j;

import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.netease.nimlib.sdk.msg.model.MessageKey;
import com.netease.nimlib.session.IMMessageImpl;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/* compiled from: QueryQuickCommentRequest.java */
/* loaded from: classes.dex */
public class i extends com.netease.nimlib.biz.d.a {
    private final List<com.netease.nimlib.push.packet.b.c> a;
    private final List<IMMessage> b;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 23;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 7;
    }

    public i(List<IMMessage> list) {
        this.b = list;
        if (list == null) {
            this.a = Collections.emptyList();
            return;
        }
        this.a = new ArrayList(list.size());
        for (IMMessage iMMessage : list) {
            if ((iMMessage instanceof IMMessageImpl) && iMMessage.getQuickCommentUpdateTime() <= com.netease.nimlib.biz.l.G()) {
                this.a.add(a(((IMMessageImpl) iMMessage).getMessageKey(), iMMessage.getQuickCommentUpdateTime()));
            }
        }
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        com.netease.nimlib.push.packet.c.d.a(bVar, (Collection<?>) this.a);
        return bVar;
    }

    private com.netease.nimlib.push.packet.b.c a(MessageKey messageKey, long j) {
        com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
        if (messageKey.getSessionType() != null) {
            cVar.a(1, messageKey.getSessionType().getValue());
        }
        if (messageKey.getFromAccount() != null) {
            cVar.a(2, messageKey.getFromAccount());
        }
        if (messageKey.getToAccount() != null) {
            cVar.a(3, messageKey.getToAccount());
        }
        cVar.a(4, messageKey.getTime());
        cVar.a(5, messageKey.getServerId());
        if (messageKey.getUuid() != null) {
            cVar.a(6, messageKey.getUuid());
        }
        cVar.a(100, j);
        return cVar;
    }

    public List<IMMessage> d() {
        return this.b;
    }
}
