package com.netease.nimlib.biz.b;

import com.netease.nim.highavailable.enums.HAvailableFCSDownloadType;
import java.io.Serializable;

/* compiled from: lambda */
/* renamed from: com.netease.nimlib.biz.b.-$$Lambda$a$0at2o0XcGQf_VAvEnVEyE47-SsQ, reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$a$0at2o0XcGQf_VAvEnVEyE47SsQ implements com.netease.nimlib.c.a, Serializable {
    private final /* synthetic */ a f$0;
    private final /* synthetic */ HAvailableFCSDownloadType f$1;
    private final /* synthetic */ d f$2;

    public /* synthetic */ $$Lambda$a$0at2o0XcGQf_VAvEnVEyE47SsQ(a aVar, HAvailableFCSDownloadType hAvailableFCSDownloadType, d dVar) {
        this.f$0 = aVar;
        this.f$1 = hAvailableFCSDownloadType;
        this.f$2 = dVar;
    }

    @Override // com.netease.nimlib.c.a
    public final void onCallback(Object obj) {
        this.f$0.a(this.f$1, this.f$2, (Boolean) obj);
    }
}
