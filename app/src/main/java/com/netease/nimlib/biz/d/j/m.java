package com.netease.nimlib.biz.d.j;

import com.netease.nimlib.session.IMMessageImpl;
import java.util.Map;

/* compiled from: RemoveQuickCommentRequest.java */
/* loaded from: classes.dex */
public class m extends com.netease.nimlib.biz.d.a {
    private final IMMessageImpl a;
    private final long b;
    private final String c;
    private final boolean d;
    private final boolean e;
    private final String f;
    private final String g;
    private final Map<String, Object> h;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 23;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 4;
    }

    public m(IMMessageImpl iMMessageImpl, long j, String str, boolean z, boolean z2, String str2, String str3, Map<String, Object> map) {
        this.a = iMMessageImpl;
        this.b = j;
        this.c = str;
        this.d = z;
        this.e = z2;
        this.f = str2;
        this.g = str3;
        this.h = map;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
        cVar.a(0, String.valueOf(this.a.getSessionType().getValue()));
        cVar.a(2, this.a.getFromAccount());
        cVar.a(1, com.netease.nimlib.session.g.a(this.a));
        cVar.a(7, this.a.getTime());
        cVar.a(12, this.a.getServerId());
        cVar.a(11, this.a.getUuid());
        bVar.a(cVar);
        com.netease.nimlib.push.packet.b.c cVar2 = new com.netease.nimlib.push.packet.b.c();
        cVar2.a(2, this.b);
        String str = this.c;
        if (str != null) {
            cVar2.a(4, str);
        }
        cVar2.a(5, this.d ? 1 : 0);
        cVar2.a(6, this.e ? 1 : 0);
        if (this.d) {
            String str2 = this.f;
            if (str2 != null) {
                cVar2.a(7, str2);
            }
            String str3 = this.g;
            if (str3 != null) {
                cVar2.a(8, str3);
            }
            Map<String, Object> map = this.h;
            if (map != null) {
                cVar2.a(9, com.netease.nimlib.session.j.a(map));
            }
        }
        bVar.a(cVar2);
        return bVar;
    }

    public IMMessageImpl d() {
        return this.a;
    }

    public long e() {
        return this.b;
    }
}
