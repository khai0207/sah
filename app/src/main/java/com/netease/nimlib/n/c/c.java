package com.netease.nimlib.n.c;

import android.content.Context;
import org.json.JSONObject;

/* compiled from: DatabaseExceptionEventExtension.java */
/* loaded from: classes.dex */
public class c extends d {
    public c(com.netease.nimlib.n.b.c cVar, String str, String str2, String str3) {
        a(Integer.valueOf(cVar.a()));
        b(str);
        b((Integer) 0);
        d(a(str2, str));
        c(str3);
    }

    private String a(String str, String str2) {
        Context e = com.netease.nimlib.c.e();
        JSONObject e2 = com.netease.nimlib.biz.a.d() ? e(e == null ? "" : com.netease.nimlib.database.a.a.a(e, str2)) : null;
        JSONObject jSONObject = new JSONObject();
        if (str != null) {
            try {
                jSONObject.put("description", str);
            } catch (Throwable th) {
                com.netease.nimlib.log.b.e("DatabaseExceptionEventExtension", "generateContext failed when putting description, e=" + th.getMessage(), th);
            }
        }
        if (e2 != null) {
            jSONObject.put("disk_info", e2);
        }
        return jSONObject.toString();
    }
}
