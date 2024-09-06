package com.netease.nimlib.biz.d.h;

import android.util.Pair;
import java.util.List;

/* compiled from: SyncSuperTeamMemberRequest.java */
/* loaded from: classes.dex */
public class b extends com.netease.nimlib.biz.d.a {
    private List<Pair<String, Long>> a;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 5;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 3;
    }

    public b(List<Pair<String, Long>> list) {
        this.a = list;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        bVar.b(this.a.size());
        for (Pair<String, Long> pair : this.a) {
            bVar.b((String) pair.first);
            bVar.a(((Long) pair.second).longValue());
        }
        return bVar;
    }
}
