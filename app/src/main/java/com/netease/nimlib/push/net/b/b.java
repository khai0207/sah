package com.netease.nimlib.push.net.b;

import com.netease.nimlib.biz.d.a;
import com.netease.nimlib.net.b.b.c;
import com.netease.nimlib.net.b.b.d;
import com.netease.nimlib.push.net.e;
import java.nio.ByteBuffer;

/* compiled from: PacketEncoder.java */
/* loaded from: classes.dex */
public class b extends d<Object> {
    e b;

    public b(e eVar) {
        super(Object.class);
        this.b = eVar;
    }

    @Override // com.netease.nimlib.net.b.b.d
    protected ByteBuffer b(Object obj) throws Exception {
        if (obj instanceof com.netease.nimlib.biz.d.a) {
            com.netease.nimlib.biz.d.a aVar = (com.netease.nimlib.biz.d.a) obj;
            return this.b.a(new a.C0028a(aVar.i(), aVar.a().b())).b();
        }
        if (obj instanceof com.netease.nimlib.ipc.a.d) {
            com.netease.nimlib.ipc.a.d dVar = (com.netease.nimlib.ipc.a.d) obj;
            return this.b.a(new a.C0028a(dVar.b(), dVar.c())).b();
        }
        throw new c("unsupport request type: " + obj.getClass().getName());
    }
}
