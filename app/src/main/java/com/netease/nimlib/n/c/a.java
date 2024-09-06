package com.netease.nimlib.n.c;

import com.netease.nimlib.app.AppForegroundWatcherCompat;

/* compiled from: BusinessExceptionEventExtension.java */
/* loaded from: classes.dex */
public class a extends d {
    public static a a(com.netease.nimlib.n.b.b bVar, com.netease.nimlib.push.packet.a aVar, int i, String str) {
        a aVar2 = new a(bVar, aVar, i, str);
        aVar2.a();
        return aVar2;
    }

    public a(com.netease.nimlib.n.b.b bVar, com.netease.nimlib.push.packet.a aVar, int i, String str) {
        a(Integer.valueOf(bVar.a()));
        if (aVar != null) {
            b(((int) aVar.i()) + "-" + ((int) aVar.j()));
            b(Integer.valueOf(i));
            d(((int) aVar.k()) + "");
            c(str);
            if (com.netease.nimlib.h.h()) {
                a(AppForegroundWatcherCompat.a());
            } else {
                a(com.netease.nimlib.push.f.l().f());
            }
        }
    }
}
