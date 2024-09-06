package com.netease.nimlib.biz.e.k;

import com.netease.nimlib.sdk.msg.model.MessageKey;

/* compiled from: RemoveMsgPinNotify.java */
@com.netease.nimlib.biz.e.b(a = 23, b = {"20", "117"})
/* loaded from: classes.dex */
public class m extends com.netease.nimlib.biz.e.a {
    private MessageKey c;
    private com.netease.nimlib.session.n d;

    @Override // com.netease.nimlib.biz.e.a
    public com.netease.nimlib.push.packet.c.f a(com.netease.nimlib.push.packet.c.f fVar) throws Exception {
        this.c = new MessageKey(com.netease.nimlib.push.packet.c.d.a(fVar));
        this.d = new com.netease.nimlib.session.n(com.netease.nimlib.push.packet.c.d.a(fVar));
        return null;
    }

    public MessageKey a() {
        return this.c;
    }

    public com.netease.nimlib.session.n b() {
        return this.d;
    }
}
