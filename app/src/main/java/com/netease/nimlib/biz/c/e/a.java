package com.netease.nimlib.biz.c.e;

import com.netease.nimlib.biz.c.i;
import com.netease.nimlib.biz.e.g.b;
import com.netease.nimlib.sdk.passthrough.model.PassthroughNotifyData;
import com.netease.nimlib.sdk.passthrough.model.PassthroughProxyData;

/* compiled from: PassthroughHandler.java */
/* loaded from: classes.dex */
public class a extends i {
    @Override // com.netease.nimlib.biz.c.a
    public void a(com.netease.nimlib.biz.e.a aVar) {
        if (aVar instanceof com.netease.nimlib.biz.e.g.a) {
            a(aVar, aVar.n() ? PassthroughProxyData.fromProperty(((com.netease.nimlib.biz.e.g.a) aVar).a()) : null);
        } else if (aVar instanceof b) {
            com.netease.nimlib.i.b.a(PassthroughNotifyData.fromProperty(((b) aVar).a()));
        }
    }
}
