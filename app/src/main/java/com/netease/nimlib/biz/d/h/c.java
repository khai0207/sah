package com.netease.nimlib.biz.d.h;

import java.util.Map;

/* compiled from: SyncTeamMemberRequest.java */
/* loaded from: classes.dex */
public class c extends com.netease.nimlib.biz.d.a {
    private Map<String, Long> a;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 5;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 2;
    }

    public c(Map<String, Long> map) {
        this.a = map;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        bVar.b(this.a.size());
        for (Map.Entry<String, Long> entry : this.a.entrySet()) {
            bVar.b(entry.getKey());
            bVar.a(entry.getValue().longValue());
        }
        return bVar;
    }
}
