package com.netease.nimlib.push.net.lbs;

import java.io.Serializable;

/* compiled from: lambda */
/* renamed from: com.netease.nimlib.push.net.lbs.-$$Lambda$c$m2RiDEIaAEq7ogvTwTzjNYaKnk0, reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$c$m2RiDEIaAEq7ogvTwTzjNYaKnk0 implements com.netease.nimlib.c.a, Serializable {
    private final /* synthetic */ c f$0;
    private final /* synthetic */ com.netease.nimlib.c.a f$1;

    public /* synthetic */ $$Lambda$c$m2RiDEIaAEq7ogvTwTzjNYaKnk0(c cVar, com.netease.nimlib.c.a aVar) {
        this.f$0 = cVar;
        this.f$1 = aVar;
    }

    @Override // com.netease.nimlib.c.a
    public final void onCallback(Object obj) {
        this.f$0.a(this.f$1, (Boolean) obj);
    }
}
