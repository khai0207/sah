package com.netease.nimlib.push.packet.b;

import com.netease.nimlib.push.packet.c.f;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* compiled from: LongLongMap.java */
/* loaded from: classes.dex */
public class a implements b {
    public Map<Long, Long> a = new HashMap();

    public Map<String, Long> a() {
        HashMap hashMap = new HashMap(this.a.size());
        Iterator<Long> b = b();
        while (b.hasNext()) {
            Long next = b.next();
            hashMap.put(String.valueOf(next), Long.valueOf(a(next)));
        }
        return hashMap;
    }

    @Override // com.netease.nimlib.push.packet.b.b
    public void a(com.netease.nimlib.push.packet.c.b bVar) {
        bVar.b(this.a.size());
        Iterator<Long> b = b();
        while (b.hasNext()) {
            Long next = b.next();
            bVar.a(next.longValue());
            bVar.a(a(next));
        }
    }

    @Override // com.netease.nimlib.push.packet.b.b
    public void a(f fVar) {
        int g = fVar.g();
        for (int i = 0; i < g; i++) {
            a(Long.valueOf(fVar.h()), Long.valueOf(fVar.h()));
        }
    }

    public Iterator<Long> b() {
        return this.a.keySet().iterator();
    }

    public long a(Long l) {
        Long l2 = this.a.get(l);
        if (l2 == null) {
            return 0L;
        }
        return l2.longValue();
    }

    public void a(Long l, Long l2) {
        this.a.put(l, Long.valueOf(l2 == null ? 0L : l2.longValue()));
    }
}
