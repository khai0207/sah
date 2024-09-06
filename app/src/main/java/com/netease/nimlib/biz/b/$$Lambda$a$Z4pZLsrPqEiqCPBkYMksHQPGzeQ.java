package com.netease.nimlib.biz.b;

import com.netease.nim.highavailable.enums.HAvailableFCSDownloadType;
import java.io.Serializable;

/* compiled from: lambda */
/* renamed from: com.netease.nimlib.biz.b.-$$Lambda$a$Z4pZLsrPqEiqCPBkYMksHQPGzeQ, reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$a$Z4pZLsrPqEiqCPBkYMksHQPGzeQ implements com.netease.nimlib.c.a, Serializable {
    private final /* synthetic */ a f$0;
    private final /* synthetic */ HAvailableFCSDownloadType f$1;
    private final /* synthetic */ int f$2;
    private final /* synthetic */ int f$3;
    private final /* synthetic */ d f$4;

    public /* synthetic */ $$Lambda$a$Z4pZLsrPqEiqCPBkYMksHQPGzeQ(a aVar, HAvailableFCSDownloadType hAvailableFCSDownloadType, int i, int i2, d dVar) {
        this.f$0 = aVar;
        this.f$1 = hAvailableFCSDownloadType;
        this.f$2 = i;
        this.f$3 = i2;
        this.f$4 = dVar;
    }

    @Override // com.netease.nimlib.c.a
    public final void onCallback(Object obj) {
        this.f$0.a(this.f$1, this.f$2, this.f$3, this.f$4, (Boolean) obj);
    }
}
