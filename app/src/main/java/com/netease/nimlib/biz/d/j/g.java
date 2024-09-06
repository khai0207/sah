package com.netease.nimlib.biz.d.j;

/* compiled from: QueryCollectRequest.java */
/* loaded from: classes.dex */
public class g extends com.netease.nimlib.biz.d.a {
    private final Long a;
    private final Long b;
    private final Long c;
    private final int d;
    private final boolean e;
    private final Integer f;
    private final boolean g;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 23;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 11;
    }

    public g(Long l, Long l2, Long l3, int i, boolean z, Integer num, boolean z2) {
        this.a = l;
        this.b = l2;
        this.c = l3;
        this.d = i;
        this.e = z;
        this.f = num;
        this.g = z2;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
        Long l = this.a;
        if (l != null) {
            cVar.a(1, l.longValue());
        }
        Long l2 = this.b;
        if (l2 != null) {
            cVar.a(2, l2.longValue());
        }
        Long l3 = this.c;
        if (l3 != null) {
            cVar.a(3, l3.longValue());
        }
        cVar.a(4, this.d);
        cVar.a(5, this.e ? 1 : 0);
        Integer num = this.f;
        if (num != null) {
            cVar.a(6, num.intValue());
        }
        bVar.a(cVar);
        return bVar;
    }

    public boolean d() {
        return this.g;
    }
}
