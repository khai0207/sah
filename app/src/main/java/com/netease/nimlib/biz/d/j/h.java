package com.netease.nimlib.biz.d.j;

import com.netease.nimlib.sdk.msg.model.MessageKey;
import java.util.Collections;
import java.util.List;

/* compiled from: QueryHistoryByIdsRequest.java */
/* loaded from: classes.dex */
public class h extends com.netease.nimlib.biz.d.a {
    private List<MessageKey> a;
    private boolean b;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 23;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 2;
    }

    public h(List<MessageKey> list, boolean z) {
        this.a = list == null ? Collections.emptyList() : list;
        this.b = z;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        bVar.b(this.a.size());
        for (MessageKey messageKey : this.a) {
            com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
            cVar.a(0, messageKey.getSessionType().getValue());
            cVar.a(2, messageKey.getFromAccount());
            cVar.a(1, messageKey.getToAccount());
            cVar.a(7, messageKey.getTime());
            cVar.a(12, messageKey.getServerId());
            cVar.a(11, messageKey.getUuid());
            bVar.a(cVar);
        }
        return bVar;
    }

    public boolean d() {
        return this.b;
    }
}
