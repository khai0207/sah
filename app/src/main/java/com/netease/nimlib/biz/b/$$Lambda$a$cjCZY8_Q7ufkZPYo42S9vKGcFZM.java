package com.netease.nimlib.biz.b;

import java.io.Serializable;

/* compiled from: lambda */
/* renamed from: com.netease.nimlib.biz.b.-$$Lambda$a$cjCZY8_Q7ufkZPYo42S9vKGcFZM, reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$a$cjCZY8_Q7ufkZPYo42S9vKGcFZM implements com.netease.nimlib.c.a, Serializable {
    private final /* synthetic */ a f$0;
    private final /* synthetic */ com.netease.nimlib.net.a.a.e f$1;

    public /* synthetic */ $$Lambda$a$cjCZY8_Q7ufkZPYo42S9vKGcFZM(a aVar, com.netease.nimlib.net.a.a.e eVar) {
        this.f$0 = aVar;
        this.f$1 = eVar;
    }

    @Override // com.netease.nimlib.c.a
    public final void onCallback(Object obj) {
        this.f$0.a(this.f$1, (Boolean) obj);
    }
}
