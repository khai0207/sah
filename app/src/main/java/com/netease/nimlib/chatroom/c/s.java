package com.netease.nimlib.chatroom.c;

import android.text.TextUtils;
import u.aly.df;

/* compiled from: TagTemporaryMuteRequest.java */
/* loaded from: classes.dex */
public class s extends com.netease.nimlib.biz.d.a {
    private String a;
    private long b;
    private boolean c;
    private String d;
    private String e;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return df.k;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 30;
    }

    public s(String str, long j, boolean z, String str2, String str3) {
        this.a = str;
        this.b = j;
        this.c = z;
        this.d = str2;
        this.e = str3;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
        cVar.a(1, this.a);
        cVar.a(2, this.b);
        cVar.a(3, this.c ? 1 : 0);
        if (!TextUtils.isEmpty(this.d)) {
            cVar.a(4, this.d);
        }
        if (!TextUtils.isEmpty(this.e)) {
            cVar.a(5, this.e);
        }
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        bVar.a(cVar);
        return bVar;
    }
}
