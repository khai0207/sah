package com.netease.nimlib.biz.f;

import com.netease.nimlib.sdk.InvocationFuture;
import com.netease.nimlib.sdk.passthrough.PassthroughService;
import com.netease.nimlib.sdk.passthrough.model.PassthroughProxyData;

/* compiled from: PassthroughServiceRemote.java */
/* loaded from: classes.dex */
public class i extends com.netease.nimlib.i.j implements PassthroughService {
    @Override // com.netease.nimlib.sdk.passthrough.PassthroughService
    public InvocationFuture<PassthroughProxyData> httpProxy(PassthroughProxyData passthroughProxyData) {
        com.netease.nimlib.biz.d.f.a aVar = new com.netease.nimlib.biz.d.f.a(passthroughProxyData);
        aVar.a(b());
        com.netease.nimlib.biz.i.a().a(aVar);
        return null;
    }
}
