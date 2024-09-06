package com.netease.nimlib.biz.d.e;

import java.util.Iterator;
import java.util.List;

/* compiled from: BatchMarkReadRequest.java */
/* loaded from: classes.dex */
public class a extends com.netease.nimlib.biz.d.a {
    private byte a;
    private byte b;
    private List<Long> c;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 4;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 5;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        bVar.a(this.a);
        bVar.a(this.b);
        bVar.b(this.c.size());
        Iterator<Long> it = this.c.iterator();
        while (it.hasNext()) {
            bVar.a(it.next().longValue());
        }
        return bVar;
    }

    public void a(byte b) {
        this.a = b;
    }

    public void b(byte b) {
        this.b = b;
    }

    public void a(List<Long> list) {
        this.c = list;
    }
}
