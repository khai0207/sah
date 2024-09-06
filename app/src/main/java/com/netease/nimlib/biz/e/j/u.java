package com.netease.nimlib.biz.e.j;

import com.talkingdata.sdk.aj;
import java.util.ArrayList;
import u.aly.cs;

/* compiled from: SearchRoamingMessageResponse.java */
@com.netease.nimlib.biz.e.b(a = cs.b.g, b = {aj.c})
/* loaded from: classes.dex */
public class u extends com.netease.nimlib.biz.e.a {
    ArrayList<com.netease.nimlib.push.packet.b.c> c;

    @Override // com.netease.nimlib.biz.e.a
    public com.netease.nimlib.push.packet.c.f a(com.netease.nimlib.push.packet.c.f fVar) throws Exception {
        int g = fVar.g();
        this.c = new ArrayList<>(g);
        for (int i = 0; i < g; i++) {
            com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
            fVar.a(cVar);
            this.c.add(cVar);
        }
        return null;
    }

    public ArrayList<com.netease.nimlib.push.packet.b.c> a() {
        return this.c;
    }
}
