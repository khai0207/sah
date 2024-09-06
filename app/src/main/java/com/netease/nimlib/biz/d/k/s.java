package com.netease.nimlib.biz.d.k;

/* compiled from: SearchRoamingTeamMsgRequest.java */
/* loaded from: classes.dex */
public class s extends com.netease.nimlib.biz.d.a {
    private long a;
    private long b;
    private long c;
    private String d;
    private int e;
    private boolean f;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 8;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 24;
    }

    public s(long j, long j2, long j3, String str, int i, boolean z) {
        this.a = j;
        this.b = j2;
        this.c = j3;
        this.d = str;
        this.e = i;
        this.f = z;
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
