package com.netease.nimlib.n.c;

/* compiled from: HttpExceptionEventExtension.java */
/* loaded from: classes.dex */
public class f extends d {
    public static f a(com.netease.nimlib.n.b.j jVar, String str, int i, String str2, String str3, String str4) {
        f fVar = new f(jVar, str, i, str2, str3, str4);
        fVar.a();
        return fVar;
    }

    public f(com.netease.nimlib.n.b.j jVar, String str, int i, String str2, String str3, String str4) {
        a(Integer.valueOf(jVar.a()));
        b(str);
        b(Integer.valueOf(i));
        d("headers = " + str2 + ",others = " + str3);
        c(str4);
    }
}
