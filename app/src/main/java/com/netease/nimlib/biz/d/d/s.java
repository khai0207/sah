package com.netease.nimlib.biz.d.d;

/* compiled from: UploadLogRequest.java */
/* loaded from: classes.dex */
public class s extends com.netease.nimlib.biz.d.a {
    private String a;
    private boolean b;
    private String c;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 6;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 4;
    }

    public s(String str) {
        this(str, false, "");
    }

    public s(String str, boolean z, String str2) {
        this.a = str;
        this.b = z;
        this.c = str2 == null ? "" : str2;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        bVar.a(this.a);
        com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
        cVar.a(1, this.b ? 1 : 0);
        cVar.a(2, this.c);
        bVar.a(cVar);
        return bVar;
    }
}
