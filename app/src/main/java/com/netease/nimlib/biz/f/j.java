package com.netease.nimlib.biz.f;

import com.netease.nimlib.sdk.InvocationFuture;
import com.netease.nimlib.sdk.redpacket.RedPacketService;

/* compiled from: RedPacketServiceRemote.java */
/* loaded from: classes.dex */
public class j extends com.netease.nimlib.i.j implements RedPacketService {
    @Override // com.netease.nimlib.sdk.redpacket.RedPacketService
    public InvocationFuture<String> getRedPacketAuthToken() {
        com.netease.nimlib.biz.d.d.n nVar = new com.netease.nimlib.biz.d.d.n();
        nVar.a(b());
        com.netease.nimlib.biz.i.a().a(nVar);
        return null;
    }
}
