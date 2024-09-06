package com.netease.nimlib.biz.d.c;

import com.netease.nimlib.o.f;
import com.netease.nimlib.push.packet.b.c;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* compiled from: AckSessionBatchRoutePassThroughRequest.java */
/* loaded from: classes.dex */
public class a extends b {
    private List<c> a;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 7;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 29;
    }

    public a(long j, Object obj, List<com.netease.nimlib.g.a.a> list) {
        super(j, obj);
        this.a = f.c(list, new f.a() { // from class: com.netease.nimlib.biz.d.c.-$$Lambda$JsrIX0VHfEEH5R9BXJZadH_rv_g
            @Override // com.netease.nimlib.o.f.a
            public final Object transform(Object obj2) {
                return ((com.netease.nimlib.g.a.a) obj2).a();
            }
        });
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        if (f.c((Collection) this.a)) {
            return bVar;
        }
        bVar.b(this.a.size());
        Iterator<c> it = this.a.iterator();
        while (it.hasNext()) {
            bVar.a(it.next());
        }
        com.netease.nimlib.log.b.J("************ AckSessionBatchRoutePassThroughRequest begin ****************");
        com.netease.nimlib.log.b.a(b(), c(), "sessionAckTagList", this.a);
        com.netease.nimlib.log.b.J("************ AckSessionBatchRoutePassThroughRequest end ****************");
        return bVar;
    }
}
