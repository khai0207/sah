package com.netease.nimlib.biz.e.a;

import com.netease.nimlib.sdk.event.model.EventSubscribeResult;
import java.util.ArrayList;
import u.aly.df;

/* compiled from: QuerySubscribeResponse.java */
@com.netease.nimlib.biz.e.b(a = df.l, b = {"6", "7"})
/* loaded from: classes.dex */
public class e extends com.netease.nimlib.biz.e.a {
    private ArrayList<EventSubscribeResult> c;

    @Override // com.netease.nimlib.biz.e.a
    public com.netease.nimlib.push.packet.c.f a(com.netease.nimlib.push.packet.c.f fVar) throws Exception {
        int g;
        if (fVar.a() <= 0 || (g = fVar.g()) <= 0) {
            return null;
        }
        this.c = new ArrayList<>(g);
        for (int i = 0; i < g; i++) {
            this.c.add(new com.netease.nimlib.f.b(com.netease.nimlib.push.packet.c.d.a(fVar)));
        }
        return null;
    }

    public ArrayList<EventSubscribeResult> a() {
        return this.c;
    }
}
