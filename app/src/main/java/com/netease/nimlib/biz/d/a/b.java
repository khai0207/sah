package com.netease.nimlib.biz.d.a;

import android.text.TextUtils;
import com.netease.nimlib.o.w;
import com.netease.nimlib.sdk.event.model.Event;
import u.aly.df;

/* compiled from: PublishEventRequest.java */
/* loaded from: classes.dex */
public class b extends com.netease.nimlib.biz.d.a {
    private com.netease.nimlib.f.a a;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return df.l;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 1;
    }

    public b(Event event) {
        this.a = new com.netease.nimlib.f.a(event);
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        this.a.a(w.b());
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
        cVar.a(1, this.a.getEventType());
        cVar.a(2, this.a.getEventValue());
        cVar.a(3, this.a.getEventId());
        if (!TextUtils.isEmpty(this.a.getConfig())) {
            cVar.a(4, this.a.getConfig());
        }
        cVar.a(5, this.a.getExpiry());
        cVar.a(6, this.a.isBroadcastOnlineOnly() ? 1 : 2);
        cVar.a(7, this.a.isSyncSelfEnable() ? 1 : 0);
        bVar.a(cVar);
        return bVar;
    }

    public com.netease.nimlib.f.a d() {
        return this.a;
    }
}
