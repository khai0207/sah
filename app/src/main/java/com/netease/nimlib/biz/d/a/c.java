package com.netease.nimlib.biz.d.a;

import com.netease.nimlib.sdk.event.model.EventSubscribeRequest;
import java.util.Collection;
import java.util.List;
import u.aly.df;

/* compiled from: QuerySubscribeRequest.java */
/* loaded from: classes.dex */
public class c extends com.netease.nimlib.biz.d.a {
    private final EventSubscribeRequest a;
    private List<String> b;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return df.l;
    }

    public c(EventSubscribeRequest eventSubscribeRequest, List<String> list) {
        this.a = eventSubscribeRequest;
        this.b = list;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
        cVar.a(1, this.a.getEventType());
        bVar.a(cVar);
        List<String> list = this.b;
        if (list != null) {
            com.netease.nimlib.push.packet.c.d.a(bVar, (Collection<?>) list);
        }
        return bVar;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return this.b == null ? (byte) 7 : (byte) 6;
    }
}
