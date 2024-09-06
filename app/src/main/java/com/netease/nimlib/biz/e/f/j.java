package com.netease.nimlib.biz.e.f;

import com.talkingdata.sdk.aj;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* compiled from: SyncUnreadMsgResponse.java */
@com.netease.nimlib.biz.e.b(a = 4, b = {aj.a, "9", "17"})
/* loaded from: classes.dex */
public class j extends com.netease.nimlib.biz.e.a {
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
        Collections.sort(list, new Comparator() { // from class: com.netease.nimlib.biz.e.f.-$$Lambda$j$C0kU6jZNwyV7eFiwIJWqF8rBEX4
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int a;
                a = j.a((com.netease.nimlib.push.packet.b.c) obj, (com.netease.nimlib.push.packet.b.c) obj2);
                return a;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ int a(com.netease.nimlib.push.packet.b.c cVar, com.netease.nimlib.push.packet.b.c cVar2) {
        long e = cVar.e(7) - cVar2.e(7);
        if (e == 0) {
            return 0;
        }
        return e > 0 ? 1 : -1;
    }
}
