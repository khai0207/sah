package com.netease.nimlib.net.a.b.f;

import android.content.Context;
import com.netease.nimlib.net.a.b.c.e;
import java.io.File;

/* compiled from: NosUploadExecutor.java */
/* loaded from: classes.dex */
public class a {
    private static com.netease.nimlib.c.b.b a;

    public static void a() {
        com.netease.nimlib.c.b.b bVar = a;
        if (bVar != null) {
            bVar.b();
            a = null;
        }
    }

    public static b a(Context context, File file, Object obj, String str, e eVar, com.netease.nimlib.net.a.b.c.b bVar) throws com.netease.nimlib.net.a.b.b.b {
        com.netease.nimlib.net.a.b.e.b.a(context, file, obj, eVar, bVar);
        b bVar2 = new b(new com.netease.nimlib.net.a.b.a.e(context, eVar.d(), eVar.e(), eVar.f(), file, obj, str, eVar), bVar);
        b().execute(bVar2);
        return bVar2;
    }

    private static com.netease.nimlib.c.b.b b() {
        if (a == null) {
            a = new com.netease.nimlib.c.b.b("NosUploadManager", com.netease.nimlib.c.b.b.b, true);
        }
        return a;
    }
}
