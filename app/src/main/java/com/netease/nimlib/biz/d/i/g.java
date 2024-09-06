package com.netease.nimlib.biz.d.i;

/* compiled from: DeleteMySessionRequest.java */
/* loaded from: classes.dex */
public class g extends com.netease.nimlib.biz.d.a {
    String[] a;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 7;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 22;
    }

    public g(String[] strArr) {
        this.a = strArr;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        bVar.b(this.a.length);
        for (String str : this.a) {
            com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
            cVar.a(1, str);
            bVar.a(cVar);
        }
        return bVar;
    }
}
