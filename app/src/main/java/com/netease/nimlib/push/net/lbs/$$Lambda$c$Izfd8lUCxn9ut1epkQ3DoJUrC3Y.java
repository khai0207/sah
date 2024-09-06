package com.netease.nimlib.push.net.lbs;

import java.io.Serializable;

/* compiled from: lambda */
/* renamed from: com.netease.nimlib.push.net.lbs.-$$Lambda$c$Izfd8lUCxn9ut1epkQ3DoJUrC3Y, reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$c$Izfd8lUCxn9ut1epkQ3DoJUrC3Y implements com.netease.nimlib.c.a, Serializable {
    private final /* synthetic */ c f$0;
    private final /* synthetic */ b f$1;

    public /* synthetic */ $$Lambda$c$Izfd8lUCxn9ut1epkQ3DoJUrC3Y(c cVar, b bVar) {
        this.f$0 = cVar;
        this.f$1 = bVar;
    }

    @Override // com.netease.nimlib.c.a
    public final void onCallback(Object obj) {
        this.f$0.a(this.f$1, (Boolean) obj);
    }
}
