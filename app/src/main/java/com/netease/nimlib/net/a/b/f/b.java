package com.netease.nimlib.net.a.b.f;

import com.netease.nimlib.net.a.b.a.e;

/* compiled from: UploadTask.java */
/* loaded from: classes.dex */
public class b implements Runnable {
    private static final String a = com.netease.nimlib.net.a.b.e.b.a(b.class);
    private e b;

    public b(e eVar, com.netease.nimlib.net.a.b.c.b bVar) {
        if (eVar == null) {
            return;
        }
        this.b = eVar;
        eVar.a(bVar);
    }

    public void b() {
        com.netease.nimlib.log.b.c(a, "uploading is canceling");
        this.b.a();
    }

    @Override // java.lang.Runnable
    public void run() {
        this.b.b();
    }
}
