package com.netease.nimlib.n.c;

import com.netease.nimlib.app.AppForegroundWatcherCompat;

/* compiled from: LinkKeepExceptionEventExtension.java */
/* loaded from: classes.dex */
public class h extends d {
    public static h a(com.netease.nimlib.n.b.l lVar, com.netease.nimlib.push.net.lbs.b bVar, int i, String str) {
        h hVar = new h(lVar, bVar, i, str);
        hVar.a();
        if (com.netease.nimlib.h.h()) {
            hVar.a(AppForegroundWatcherCompat.a());
        } else {
            hVar.a(com.netease.nimlib.push.f.l().f());
        }
        return hVar;
    }

    public h(com.netease.nimlib.n.b.l lVar, com.netease.nimlib.push.net.lbs.b bVar, int i, String str) {
        a(Integer.valueOf(lVar.a()));
        b(bVar.toString());
        b(Integer.valueOf(i));
        c(str);
    }
}
