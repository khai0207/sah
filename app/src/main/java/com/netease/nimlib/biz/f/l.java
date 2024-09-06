package com.netease.nimlib.biz.f;

import com.netease.nimlib.sdk.InvocationFuture;
import com.netease.nimlib.sdk.settings.SettingsService;

/* compiled from: SettingsServiceRemote.java */
/* loaded from: classes.dex */
public class l extends com.netease.nimlib.i.j implements SettingsService {
    @Override // com.netease.nimlib.sdk.settings.SettingsService
    public InvocationFuture<Void> updateMultiportPushConfig(boolean z) {
        com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
        cVar.a(1, z ? 1 : 2);
        com.netease.nimlib.biz.d.l.g gVar = new com.netease.nimlib.biz.d.l.g(cVar);
        gVar.a(b());
        com.netease.nimlib.biz.i.a().a(gVar);
        return null;
    }

    @Override // com.netease.nimlib.sdk.settings.SettingsService
    public boolean isMultiportPushOpen() {
        return com.netease.nimlib.biz.l.r();
    }
}
