package com.netease.nimlib.push.a.c;

import java.util.List;
import u.aly.df;

/* compiled from: RoomTokenResponse.java */
@com.netease.nimlib.biz.e.b(a = df.k, b = {"1#2"})
/* loaded from: classes.dex */
public class f extends com.netease.nimlib.biz.e.a {
    private List<String> c;

    @Override // com.netease.nimlib.biz.e.a
    public com.netease.nimlib.push.packet.c.f a(com.netease.nimlib.push.packet.c.f fVar) throws Exception {
        this.c = com.netease.nimlib.push.packet.c.d.b(fVar);
        return null;
    }

    public List<String> a() {
        return this.c;
    }

    public void a(List<String> list) {
        this.c = list;
    }
}
