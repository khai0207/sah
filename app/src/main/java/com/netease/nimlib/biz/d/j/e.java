package com.netease.nimlib.biz.d.j;

import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;

/* compiled from: BaseStickTopSessionRequest.java */
/* loaded from: classes.dex */
public abstract class e extends com.netease.nimlib.biz.d.a {
    protected final String a;
    protected final SessionTypeEnum b;
    protected final String c;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 23;
    }

    public e(String str, SessionTypeEnum sessionTypeEnum, String str2) {
        this.a = str;
        this.b = sessionTypeEnum;
        this.c = str2;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
        cVar.a(1, com.netease.nimlib.session.j.a(this.b, this.a));
        String str = this.c;
        if (str != null) {
            cVar.a(2, str);
        }
        bVar.a(cVar);
        return bVar;
    }
}
