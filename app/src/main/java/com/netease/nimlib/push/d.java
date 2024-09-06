package com.netease.nimlib.push;

/* compiled from: LoginRepTag.java */
/* loaded from: classes.dex */
public class d {
    private String a;
    private String b;
    private int c;
    private String d;
    private String e;
    private int f;
    private int g;

    public int a() {
        return this.f;
    }

    public String b() {
        return this.a;
    }

    public void a(int i) {
        this.f = i;
    }

    public int c() {
        return this.g;
    }

    public void b(int i) {
        this.g = i;
    }

    public String d() {
        return this.d;
    }

    public static d a(com.netease.nimlib.push.packet.b.c cVar) {
        d dVar = new d();
        dVar.a = cVar.c(17);
        dVar.b = cVar.c(103);
        dVar.c = cVar.d(104);
        dVar.d = cVar.c(102);
        dVar.e = cVar.c(106);
        return dVar;
    }
}
