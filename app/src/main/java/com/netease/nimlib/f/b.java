package com.netease.nimlib.f;

import com.netease.nimlib.push.packet.b.c;
import com.netease.nimlib.sdk.event.model.EventSubscribeResult;

/* compiled from: EventSubscribeResultImpl.java */
/* loaded from: classes.dex */
public class b extends EventSubscribeResult {
    public b(c cVar) {
        a(cVar);
    }

    public void a(c cVar) {
        if (cVar.f(1)) {
            this.eventType = cVar.d(1);
        }
        if (cVar.f(105)) {
            this.time = cVar.e(105);
        }
        if (cVar.f(102)) {
            this.publisherAccount = cVar.c(102);
        }
        if (cVar.f(2)) {
            this.expiry = cVar.e(2);
        }
    }
}
