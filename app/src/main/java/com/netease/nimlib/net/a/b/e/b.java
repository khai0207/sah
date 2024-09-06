package com.netease.nimlib.net.a.b.e;

import android.content.Context;
import com.netease.nimlib.net.a.b.c.c;
import com.netease.nimlib.net.a.b.c.e;
import java.io.File;
import java.io.IOException;
import org.json.JSONException;

/* compiled from: Util.java */
/* loaded from: classes.dex */
public class b {
    private static final String a = a(b.class);

    public static String a(Class cls) {
        return "NOS_" + cls.getSimpleName();
    }

    public static String a(c cVar, String str) {
        if (cVar != null && cVar.b() != null && cVar.b().has(str)) {
            try {
                return cVar.b().getString(str);
            } catch (JSONException e) {
                com.netease.nimlib.log.b.e(a, "get result string parse json failed", e);
            }
        }
        return "";
    }

    public static void a(Context context, File file, Object obj, e eVar, com.netease.nimlib.net.a.b.c.b bVar) throws com.netease.nimlib.net.a.b.b.b {
        String d = eVar.d();
        String e = eVar.e();
        String f = eVar.f();
        if (context == null || file == null || obj == null || eVar == null || bVar == null || d == null || e == null || f == null) {
            throw new com.netease.nimlib.net.a.b.b.b("parameters could not be null");
        }
    }

    public static a a(Context context, File file, String str) throws IOException {
        if (file == null) {
            return null;
        }
        try {
            return new a(file, str);
        } catch (IOException e) {
            if (file != null) {
                file.delete();
            }
            throw e;
        }
    }
}
