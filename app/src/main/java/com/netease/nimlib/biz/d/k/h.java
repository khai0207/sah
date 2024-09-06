package com.netease.nimlib.biz.d.k;

/* compiled from: GetMemberListRequest.java */
/* loaded from: classes.dex */
public class h extends com.netease.nimlib.biz.d.a {
    private String a;
    private long b;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 8;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 11;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        bVar.b(this.a);
        bVar.a(this.b);
        com.netease.nimlib.log.b.J("************ GetMemberListRequest begin ****************");
        com.netease.nimlib.log.b.a(b(), c(), "tid = " + this.a);
        com.netease.nimlib.log.b.a(b(), c(), "timetag = " + this.b);
        com.netease.nimlib.log.b.J("************ GetMemberListRequest end ****************");
        return bVar;
    }

    public String d() {
        return this.a;
    }

    public void a(String str) {
        this.a = str;
    }

    public void a(long j) {
        this.b = j;
    }
}
