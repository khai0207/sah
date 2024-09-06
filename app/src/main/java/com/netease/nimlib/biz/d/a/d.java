package com.netease.nimlib.biz.d.a;

import com.netease.nimlib.sdk.event.model.EventSubscribeRequest;
import java.util.Collection;
import java.util.List;
import u.aly.df;

/* compiled from: SubscribeEventRequest.java */
/* loaded from: classes.dex */
public class d extends com.netease.nimlib.biz.d.a {
    private final EventSubscribeRequest a;
    private final List<String> b;
    private final boolean c;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return df.l;
    }

    public d(EventSubscribeRequest eventSubscribeRequest, List<String> list, boolean z) {
        this.a = eventSubscribeRequest;
        this.b = list;
        this.c = z;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
        cVar.a(1, this.a.getEventType());
        if (this.c) {
            cVar.a(2, this.a.getExpiry());
            cVar.a(3, this.a.isSyncCurrentValue() ? 1 : 0);
        }
        bVar.a(cVar);
        com.netease.nimlib.push.packet.c.d.a(bVar, (Collection<?>) this.b);
        return bVar;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return this.c ? (byte) 3 : (byte) 4;
    }
}
