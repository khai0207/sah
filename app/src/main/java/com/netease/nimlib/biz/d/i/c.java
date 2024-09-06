package com.netease.nimlib.biz.d.i;

import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;

/* compiled from: ClearServerHistoryRequest.java */
/* loaded from: classes.dex */
public class c extends com.netease.nimlib.biz.d.a {
    public String a;
    public SessionTypeEnum b;
    public boolean c;
    public boolean d;
    public String e;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 7;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 18;
    }

    public c(String str, SessionTypeEnum sessionTypeEnum, boolean z, boolean z2, String str2) {
        this.b = sessionTypeEnum;
        this.a = str;
        this.c = sessionTypeEnum != SessionTypeEnum.P2P || z;
        this.d = z2;
        this.e = str2;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
        boolean z = this.b == SessionTypeEnum.P2P;
        cVar.a(0, z ? 1 : 2);
        cVar.a(z ? 1 : 3, this.a);
        cVar.a(2, this.c ? 1 : 0);
        cVar.a(4, this.d ? 1 : 0);
        cVar.a(7, this.e);
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        bVar.a(cVar);
        return bVar;
    }
}
