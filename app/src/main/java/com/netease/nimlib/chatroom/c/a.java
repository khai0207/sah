package com.netease.nimlib.chatroom.c;

import android.text.TextUtils;
import u.aly.df;

/* compiled from: BatchUpdateQueueRequest.java */
/* loaded from: classes.dex */
public class a extends com.netease.nimlib.biz.d.a {
    private com.netease.nimlib.push.packet.b.e a;
    private boolean b;
    private String c;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return df.k;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 26;
    }

    public a(com.netease.nimlib.push.packet.b.e eVar, boolean z, String str) {
        this.a = eVar;
        this.b = z;
        this.c = str;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        bVar.a(this.a);
        bVar.a(this.b);
        if (!TextUtils.isEmpty(this.c)) {
            bVar.a(this.c);
        }
        return bVar;
    }
}
