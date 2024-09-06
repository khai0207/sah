package com.netease.nimlib.biz.d.i;

/* compiled from: SearchRoamingMessageRequest.java */
/* loaded from: classes.dex */
public class p extends com.netease.nimlib.biz.d.a {
    private String a;
    private long b;
    private long c;
    private String d;
    private int e;
    private boolean f;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 7;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 8;
    }

    public p(String str, long j, long j2, String str2, int i, boolean z) {
        this.a = str;
        this.b = j;
        this.c = j2;
        this.d = str2;
        this.e = i;
        this.f = z;
        int max = Math.max(0, i);
        this.e = max;
        this.e = Math.min(100, max);
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        bVar.a(this.a);
        bVar.a(this.b);
        bVar.a(this.c);
        bVar.a(this.d);
        bVar.a(this.e);
        bVar.a(this.f);
        return bVar;
    }
}
