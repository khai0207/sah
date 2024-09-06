package com.netease.nimlib.chatroom.c;

import u.aly.df;

/* compiled from: UpdateQueueRequest.java */
/* loaded from: classes.dex */
public class x extends com.netease.nimlib.biz.d.a {
    private final String a;
    private final String b;
    private final boolean c;
    private final String d;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return df.k;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 20;
    }

    public x(String str, String str2, boolean z, String str3) {
        this.a = str;
        this.b = str2;
        this.c = z;
        this.d = str3;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        bVar.a(this.a);
        bVar.a(this.b);
        bVar.a(this.c);
        if (com.netease.nimlib.o.w.b((CharSequence) this.d)) {
            bVar.a(this.d);
        }
        return bVar;
    }
}
