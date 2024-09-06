package com.netease.nimlib.biz.d.a;

import com.netease.nimlib.sdk.event.model.EventSubscribeRequest;
import u.aly.df;

/* compiled from: BatchUnSubscribeEventRequest.java */
/* loaded from: classes.dex */
public class a extends com.netease.nimlib.biz.d.a {
    private final EventSubscribeRequest a;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return df.l;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 5;
    }

    public a(EventSubscribeRequest eventSubscribeRequest) {
        this.a = eventSubscribeRequest;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
        cVar.a(1, this.a.getEventType());
        bVar.a(cVar);
        return bVar;
    }
}
