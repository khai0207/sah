package com.netease.nimlib.biz.e.g;

import com.netease.nimlib.push.packet.b.c;
import com.netease.nimlib.push.packet.c.d;
import com.netease.nimlib.push.packet.c.f;

/* compiled from: PassThroughNotify.java */
@com.netease.nimlib.biz.e.b(a = 22, b = {"2"})
/* loaded from: classes.dex */
public class b extends com.netease.nimlib.biz.e.a {
    private c c;

    @Override // com.netease.nimlib.biz.e.a
    public f a(f fVar) throws Exception {
        this.c = d.a(fVar);
        return null;
    }

    public c a() {
        return this.c;
    }
}
