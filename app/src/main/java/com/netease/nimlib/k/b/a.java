package com.netease.nimlib.k.b;

import com.netease.nimlib.push.packet.b.c;

/* compiled from: NimDectInfo.java */
/* loaded from: classes.dex */
public class a {
    private com.netease.nimlib.k.a.a a;
    private String b;
    private String c;
    private Long d;
    private Long e;
    private Long f;
    private Long g;
    private Long h;

    public com.netease.nimlib.k.a.a a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    public Long d() {
        return this.d;
    }

    public Long e() {
        return this.e;
    }

    public Long f() {
        return this.f;
    }

    public void a(Long l) {
        this.f = l;
    }

    public static a a(c cVar) {
        if (cVar == null) {
            return null;
        }
        a aVar = new a();
        aVar.a = com.netease.nimlib.k.a.a.a(cVar.d(1));
        aVar.b = cVar.c(2);
        aVar.c = cVar.c(3);
        if (cVar.f(100)) {
            aVar.d = Long.valueOf(cVar.e(100));
        }
        if (cVar.f(101)) {
            aVar.e = Long.valueOf(cVar.e(101));
        }
        if (cVar.f(102)) {
            aVar.f = Long.valueOf(cVar.e(102));
        }
        if (cVar.f(103)) {
            aVar.g = Long.valueOf(cVar.e(104));
        }
        if (cVar.f(105)) {
            aVar.h = Long.valueOf(cVar.e(105));
        }
        return aVar;
    }
}
