package com.netease.nimlib.push.packet.b;

import com.netease.nimlib.push.packet.c.f;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* compiled from: StrLongMap.java */
/* loaded from: classes.dex */
public class d implements b {
    public Map<String, Long> a = new HashMap();

    @Override // com.netease.nimlib.push.packet.b.b
    public void a(com.netease.nimlib.push.packet.c.b bVar) {
        bVar.b(this.a.size());
        Iterator<String> a = a();
        while (a.hasNext()) {
            String next = a.next();
            bVar.a(next);
            bVar.a(a(next));
        }
    }

    @Override // com.netease.nimlib.push.packet.b.b
    public void a(f fVar) {
        int g = fVar.g();
        for (int i = 0; i < g; i++) {
            a(fVar.e(), Long.valueOf(fVar.h()));
        }
    }

    public Iterator<String> a() {
        return this.a.keySet().iterator();
    }

    public long a(String str) {
        Long l = this.a.get(str);
        if (l == null) {
            return 0L;
        }
        return l.longValue();
    }

    public void a(String str, Long l) {
        this.a.put(str, Long.valueOf(l == null ? 0L : l.longValue()));
    }
}
