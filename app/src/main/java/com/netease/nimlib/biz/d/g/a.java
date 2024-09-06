package com.netease.nimlib.biz.d.g;

import com.netease.nimlib.push.net.lbs.IPVersion;
import com.netease.nimlib.push.packet.b.c;
import com.netease.nimlib.push.packet.c.b;

/* compiled from: QChatTokenRequest.java */
/* loaded from: classes.dex */
public class a extends com.netease.nimlib.biz.d.a {
    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 24;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 1;
    }

    @Override // com.netease.nimlib.biz.d.a
    public b a() {
        c cVar = new c();
        if (com.netease.nimlib.c.l() != null && com.netease.nimlib.c.l().ipProtocolVersion == IPVersion.ANY) {
            cVar.a(1, 2);
        } else if (com.netease.nimlib.c.l() != null && com.netease.nimlib.c.l().ipProtocolVersion == IPVersion.IPV6) {
            cVar.a(1, 1);
        } else {
            cVar.a(1, 0);
        }
        b bVar = new b();
        bVar.a(cVar);
        com.netease.nimlib.log.b.J("************ QChatTokenRequest begin ****************");
        com.netease.nimlib.log.b.a(b(), c(), "property = " + cVar);
        com.netease.nimlib.log.b.J("************ QChatTokenRequest end ****************");
        return bVar;
    }
}
