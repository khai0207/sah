package com.netease.nimlib.biz.d.k;

/* compiled from: FetchTeamMsgAckDetailRequest.java */
/* loaded from: classes.dex */
public class f extends com.netease.nimlib.biz.d.a {
    private com.netease.nimlib.push.packet.b.c a;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 8;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 30;
    }

    public f(String str, Long l, boolean z) {
        com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
        this.a = cVar;
        cVar.a(0, str);
        this.a.a(1, l.longValue());
        if (z) {
            return;
        }
        this.a.a(2, 1);
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        bVar.a(this.a);
        return bVar;
    }
}
