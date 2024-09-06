package com.netease.nimlib.chatroom.d;

import com.netease.nimlib.sdk.util.Entry;
import java.util.ArrayList;
import u.aly.df;

/* compiled from: ListQueueResponse.java */
@com.netease.nimlib.biz.e.b(a = df.k, b = {"22"})
/* loaded from: classes.dex */
public class o extends com.netease.nimlib.biz.e.a {
    private ArrayList<Entry<String, String>> c;

    @Override // com.netease.nimlib.biz.e.a
    public com.netease.nimlib.push.packet.c.f a(com.netease.nimlib.push.packet.c.f fVar) throws Exception {
        int g = fVar.g();
        this.c = new ArrayList<>(g);
        for (int i = 0; i < g; i++) {
            int g2 = fVar.g();
            for (int i2 = 0; i2 < g2; i2++) {
                this.c.add(new Entry<>(fVar.e(), fVar.e()));
            }
        }
        return null;
    }

    public ArrayList<Entry<String, String>> a() {
        return this.c;
    }
}
