package com.netease.nimlib.biz.d.k;

import android.util.Pair;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: FetchTeamMsgAckCountRequest.java */
/* loaded from: classes.dex */
public class e extends com.netease.nimlib.biz.d.a {
    private List<com.netease.nimlib.push.packet.b.c> a;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 8;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 29;
    }

    public e(List<Pair<String, Long>> list) {
        if (list == null) {
            return;
        }
        this.a = new ArrayList(list.size());
        for (Pair<String, Long> pair : list) {
            com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
            cVar.a(0, (String) pair.first);
            cVar.a(1, ((Long) pair.second).longValue());
            this.a.add(cVar);
        }
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        List<com.netease.nimlib.push.packet.b.c> list = this.a;
        if (list != null) {
            bVar.b(list.size());
            Iterator<com.netease.nimlib.push.packet.b.c> it = this.a.iterator();
            while (it.hasNext()) {
                bVar.a(it.next());
            }
        }
        return bVar;
    }
}
