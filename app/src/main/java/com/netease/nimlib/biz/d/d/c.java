package com.netease.nimlib.biz.d.d;

/* compiled from: GetAppGrayConfigRequest.java */
/* loaded from: classes.dex */
public class c extends com.netease.nimlib.biz.d.a {
    private String a;
    private String b;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 6;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 27;
    }

    public c(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
        cVar.a(0, this.a);
        cVar.a(1, this.b);
        com.netease.nimlib.log.b.J("************ GetAppGrayConfigRequest begin ****************");
        com.netease.nimlib.log.b.a(b(), c(), "property", cVar);
        com.netease.nimlib.log.b.J("************ GetAppGrayConfigRequest end ****************");
        return new com.netease.nimlib.push.packet.c.b().a(cVar);
    }
}
