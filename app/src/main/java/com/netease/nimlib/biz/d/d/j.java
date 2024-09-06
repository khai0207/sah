package com.netease.nimlib.biz.d.d;

/* compiled from: GetNosTokenRequest.java */
/* loaded from: classes.dex */
public class j extends com.netease.nimlib.biz.d.a {
    private int a;
    private String b;
    private long c;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 6;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 1;
    }

    public j(int i, String str, long j) {
        this.a = i;
        this.b = str;
        this.c = j;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
        long j = this.c;
        if (j > 0) {
            cVar.a(7, j);
        }
        cVar.a(8, this.b);
        bVar.a(this.a);
        bVar.a(cVar);
        com.netease.nimlib.log.b.J("************ GetNosTokenRequest begin ****************");
        com.netease.nimlib.log.b.a(i());
        com.netease.nimlib.log.b.a(b(), c(), "property", cVar);
        com.netease.nimlib.log.b.J("************ GetNosTokenRequest end ****************");
        return bVar;
    }

    public String d() {
        return this.b;
    }
}
