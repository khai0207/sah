package com.netease.nimlib.biz.d.j;

import android.util.Pair;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* compiled from: RemoveCollectRequest.java */
/* loaded from: classes.dex */
public class k extends com.netease.nimlib.biz.d.a {
    private final List<Pair<Long, Long>> a;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 23;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 9;
    }

    public k(List<Pair<Long, Long>> list) {
        this.a = list == null ? new ArrayList<>(0) : list;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        ArrayList arrayList = new ArrayList(this.a.size());
        for (Pair<Long, Long> pair : this.a) {
            com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
            cVar.a(1, ((Long) pair.first).longValue());
            cVar.a(6, ((Long) pair.second).longValue());
            arrayList.add(cVar);
        }
        com.netease.nimlib.push.packet.c.d.a(bVar, (Collection<?>) arrayList);
        return bVar;
    }

    public List<Pair<Long, Long>> d() {
        return this.a;
    }
}
