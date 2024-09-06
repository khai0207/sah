package com.netease.nimlib.push.net.b;

import com.netease.nimlib.biz.e.a;
import com.netease.nimlib.push.net.a.c;
import com.netease.nimlib.push.net.e;
import com.netease.nimlib.push.packet.c.g;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;

/* compiled from: PacketDecoder.java */
/* loaded from: classes.dex */
public class a extends com.netease.nimlib.net.b.b.a {
    private e b;

    public a(e eVar) {
        this.b = eVar;
    }

    @Override // com.netease.nimlib.net.b.b.a
    protected void b(ByteBuffer byteBuffer, List<Object> list) throws Exception {
        ByteBuffer order = byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        while (order.remaining() > 0) {
            try {
                a.C0029a a = this.b.a(order);
                if (a == null) {
                    return;
                }
                c.b().a();
                if (!a.a()) {
                    com.netease.nimlib.log.b.f("netty", "PacketDecoder check response raw data invalid");
                    a(order, a.a == null ? (byte) 0 : a.a.i(), a.a == null ? (byte) 0 : a.a.j(), "packet header invalid");
                    return;
                }
                list.add(a);
            } catch (g e) {
                e.printStackTrace();
                a(order, 0, 0, e.getMessage());
                return;
            }
        }
    }

    @Override // com.netease.nimlib.net.b.b.a, com.netease.nimlib.net.b.c.a, com.netease.nimlib.net.b.c.d
    public void k() {
        super.k();
        this.b.b();
    }

    private void a(ByteBuffer byteBuffer, int i, int i2, String str) {
        com.netease.nimlib.log.b.d("netty", "on decode exception,sid=" + i + ", cid=" + i2 + ", desc=" + str);
        byteBuffer.clear();
        this.a.i().e();
    }
}
