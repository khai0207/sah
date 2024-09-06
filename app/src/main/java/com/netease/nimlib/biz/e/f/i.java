package com.netease.nimlib.biz.e.f;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* compiled from: SyncSystemMessageResponse.java */
@com.netease.nimlib.biz.e.b(a = 4, b = {"6"})
/* loaded from: classes.dex */
public class i extends com.netease.nimlib.biz.e.a {
    private List<com.netease.nimlib.push.packet.b.c> c;

    @Override // com.netease.nimlib.biz.e.a
    public com.netease.nimlib.push.packet.c.f a(com.netease.nimlib.push.packet.c.f fVar) throws Exception {
        int g = fVar.g();
        this.c = new ArrayList(g);
        for (int i = 0; i < g; i++) {
            this.c.add(com.netease.nimlib.push.packet.c.d.a(fVar));
        }
        a(this.c);
        return null;
    }

    public List<com.netease.nimlib.push.packet.b.c> a() {
        return this.c;
    }

    private void a(List<com.netease.nimlib.push.packet.b.c> list) {
        Collections.sort(list, new Comparator() { // from class: com.netease.nimlib.biz.e.f.-$$Lambda$i$UVofVwFhg2fCjkOM4Pqe4YqBlWA
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int a;
                a = i.a((com.netease.nimlib.push.packet.b.c) obj, (com.netease.nimlib.push.packet.b.c) obj2);
                return a;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ int a(com.netease.nimlib.push.packet.b.c cVar, com.netease.nimlib.push.packet.b.c cVar2) {
        long e = cVar.e(0);
        long e2 = cVar2.e(0);
        if (e == e2) {
            return 0;
        }
        return e - e2 > 0 ? 1 : -1;
    }
}
