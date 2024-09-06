package com.netease.nimlib.biz.d.i;

/* compiled from: GetMySessionListRequest.java */
/* loaded from: classes.dex */
public class i extends com.netease.nimlib.biz.d.a {
    private final long a;
    private final Long b;
    private final Integer c;
    private final Integer d;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 7;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 19;
    }

    public i(long j, Long l, Integer num, Integer num2) {
        this.a = j;
        this.b = l;
        this.c = num;
        this.d = num2;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
        cVar.a(1, this.a);
        Long l = this.b;
        if (l != null) {
            cVar.a(2, l.longValue());
        }
        Integer num = this.c;
        if (num != null) {
            cVar.a(3, num.intValue());
        }
        Integer num2 = this.d;
        if (num2 != null) {
            cVar.a(4, num2.intValue());
        }
        return new com.netease.nimlib.push.packet.c.b().a(cVar);
    }
}
