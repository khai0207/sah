package com.netease.nimlib.n.c;

import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: FileExceptionEventExtension.java */
/* loaded from: classes.dex */
public class e extends d {
    public e(com.netease.nimlib.n.b.i iVar, String str, String str2) {
        a(Integer.valueOf(iVar.a()));
        b(str);
        b((Integer) 0);
        d(f(str));
        c(str2);
    }

    private String f(String str) {
        JSONObject jSONObject = new JSONObject();
        if (com.netease.nimlib.biz.a.d()) {
            JSONObject e = e(str);
            JSONObject j = j();
            try {
                jSONObject.put("disk_info", e);
                jSONObject.put("permission_info", j);
            } catch (JSONException e2) {
                com.netease.nimlib.log.b.e("FileExceptionEventExtension", "generateContext failed when putting diskInfoObject or permissionInfoObject, e=" + e2.getMessage(), e2);
            }
        }
        return jSONObject.toString();
    }
}
