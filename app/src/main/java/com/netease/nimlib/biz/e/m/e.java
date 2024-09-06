package com.netease.nimlib.biz.e.m;

import com.talkingdata.sdk.aj;
import java.util.ArrayList;
import java.util.List;

/* compiled from: SyncMuteBlackListResponse.java */
@com.netease.nimlib.biz.e.b(a = 3, b = {aj.c})
/* loaded from: classes.dex */
public class e extends com.netease.nimlib.biz.e.a {
    private List<com.netease.nimlib.push.packet.b.c> c;
    private long d;

    @Override // com.netease.nimlib.biz.e.a
    public com.netease.nimlib.push.packet.c.f a(com.netease.nimlib.push.packet.c.f fVar) throws Exception {
        int g = fVar.g();
        this.c = new ArrayList();
        for (int i = 0; i < g; i++) {
            this.c.add(com.netease.nimlib.push.packet.c.d.a(fVar));
        }
        this.d = fVar.h();
        return null;
    }

    public List<com.netease.nimlib.push.packet.b.c> a() {
        return this.c;
    }

    public long b() {
        return this.d;
    }
}
