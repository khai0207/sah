package com.netease.nimlib.biz.e.k;

import com.talkingdata.sdk.aj;

/* compiled from: AddCollectResponse.java */
@com.netease.nimlib.biz.e.b(a = 23, b = {aj.c})
/* loaded from: classes.dex */
public class a extends com.netease.nimlib.biz.e.a {
    private com.netease.nimlib.session.a c;

    @Override // com.netease.nimlib.biz.e.a
    public com.netease.nimlib.push.packet.c.f a(com.netease.nimlib.push.packet.c.f fVar) throws Exception {
        this.c = new com.netease.nimlib.session.a(com.netease.nimlib.push.packet.c.d.a(fVar));
        return null;
    }

    public com.netease.nimlib.session.a a() {
        return this.c;
    }
}
