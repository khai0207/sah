package com.netease.nimlib.j;

import android.os.Handler;
import com.netease.nimlib.i.k;
import com.netease.nimlib.j.d.c;
import com.netease.nimlib.sdk.migration.processor.IMsgExportProcessor;
import com.netease.nimlib.sdk.migration.processor.IMsgImportProcessor;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: MsgMigrationManager.java */
/* loaded from: classes.dex */
public class b {
    private Handler a;
    private ConcurrentHashMap<Integer, com.netease.nimlib.j.d.a> b;

    /* synthetic */ b(AnonymousClass1 anonymousClass1) {
        this();
    }

    private b() {
        this.b = new ConcurrentHashMap<>();
        this.a = com.netease.nimlib.c.b.a.c().b();
    }

    public static b a() {
        return a.a;
    }

    public void a(IMsgExportProcessor iMsgExportProcessor, k kVar, HashMap<String, Object> hashMap, String str, boolean z) {
        com.netease.nimlib.j.d.b bVar = new com.netease.nimlib.j.d.b(iMsgExportProcessor, kVar, hashMap, str, z);
        if (bVar.b()) {
            return;
        }
        this.b.put(Integer.valueOf(kVar.h()), bVar);
        this.a.post(bVar);
    }

    public void a(k kVar, IMsgImportProcessor iMsgImportProcessor, boolean z) {
        c cVar = new c(kVar, iMsgImportProcessor, z);
        if (cVar.b()) {
            return;
        }
        this.b.put(Integer.valueOf(kVar.h()), cVar);
        this.a.post(cVar);
    }

    public void a(k kVar) {
        com.netease.nimlib.j.d.a b = b(kVar);
        if (b != null) {
            b.a();
        }
    }

    public com.netease.nimlib.j.d.a b(k kVar) {
        com.netease.nimlib.j.d.a remove = this.b.remove(Integer.valueOf(kVar.h()));
        if (remove != null) {
            this.a.removeCallbacks(remove);
        }
        return remove;
    }

    public void a(Runnable runnable) {
        this.a.post(runnable);
    }

    /* compiled from: MsgMigrationManager.java */
    /* loaded from: classes.dex */
    private static class a {
        private static final b a = new b();
    }
}
