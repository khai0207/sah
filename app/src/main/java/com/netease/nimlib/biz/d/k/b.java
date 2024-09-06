package com.netease.nimlib.biz.d.k;

import java.util.Collection;
import java.util.List;
import u.aly.df;

/* compiled from: ChangeManagerRequest.java */
/* loaded from: classes.dex */
public class b extends com.netease.nimlib.biz.d.a {
    protected boolean a;
    private String b;
    private List<String> c;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 8;
    }

    public b(String str, List<String> list, boolean z) {
        this.b = str;
        this.c = list;
        this.a = z;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        bVar.b(this.b);
        com.netease.nimlib.push.packet.c.d.a(bVar, (Collection<?>) this.c);
        return bVar;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        if (this.a) {
            return df.n;
        }
        return (byte) 17;
    }
}
