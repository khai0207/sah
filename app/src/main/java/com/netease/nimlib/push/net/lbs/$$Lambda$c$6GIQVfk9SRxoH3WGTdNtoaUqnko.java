package com.netease.nimlib.push.net.lbs;

import com.netease.nim.highavailable.NativeHighAvailableGetLbsResponseCallback;
import java.io.Serializable;

/* compiled from: lambda */
/* renamed from: com.netease.nimlib.push.net.lbs.-$$Lambda$c$6GIQVfk9SRxoH3WGTdNtoaUqnko, reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$c$6GIQVfk9SRxoH3WGTdNtoaUqnko implements com.netease.nimlib.c.a, Serializable {
    private final /* synthetic */ c f$0;
    private final /* synthetic */ NativeHighAvailableGetLbsResponseCallback f$1;

    public /* synthetic */ $$Lambda$c$6GIQVfk9SRxoH3WGTdNtoaUqnko(c cVar, NativeHighAvailableGetLbsResponseCallback nativeHighAvailableGetLbsResponseCallback) {
        this.f$0 = cVar;
        this.f$1 = nativeHighAvailableGetLbsResponseCallback;
    }

    @Override // com.netease.nimlib.c.a
    public final void onCallback(Object obj) {
        this.f$0.b(this.f$1, (Boolean) obj);
    }
}
