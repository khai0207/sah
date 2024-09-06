package com.netease.nimlib.n.d.a;

import com.netease.nimlib.n.b.f;
import defpackage.C$r8$backportedMethods$utility$Objects$2$equals;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;

/* compiled from: ExceptionEventRuleClearHttpTcpBusinessOffline.java */
/* loaded from: classes.dex */
public class a implements com.netease.nimlib.n.d.a.a.c {
    @Override // com.netease.nimlib.n.d.a.a.c
    public boolean a(com.netease.nimlib.n.e.d dVar) {
        if (!C$r8$backportedMethods$utility$Objects$2$equals.equals(dVar.f(), String.valueOf(f.kHTTP.a())) && !C$r8$backportedMethods$utility$Objects$2$equals.equals(dVar.f(), String.valueOf(f.kTCP.a())) && !C$r8$backportedMethods$utility$Objects$2$equals.equals(dVar.f(), String.valueOf(f.kBusiness.a()))) {
            return false;
        }
        Iterator<com.netease.nimlib.n.c.d> it = dVar.l().iterator();
        while (it.hasNext()) {
            if (Boolean.FALSE.equals(it.next().c())) {
                return true;
            }
        }
        return false;
    }

    @Override // com.netease.nimlib.n.d.a.a.c
    public boolean a(Map<String, Object> map) {
        try {
            if (C$r8$backportedMethods$utility$Objects$2$equals.equals(map.get("action"), String.valueOf(f.kHTTP.a())) || C$r8$backportedMethods$utility$Objects$2$equals.equals(map.get("action"), String.valueOf(f.kTCP.a())) || C$r8$backportedMethods$utility$Objects$2$equals.equals(map.get("action"), String.valueOf(f.kBusiness.a()))) {
                Object obj = map.get("extension");
                if (!(obj instanceof JSONArray)) {
                    return false;
                }
                JSONArray jSONArray = (JSONArray) obj;
                for (int i = 0; i < jSONArray.length(); i++) {
                    if (Boolean.FALSE.equals(jSONArray.optJSONObject(i).opt("net_connect"))) {
                        return true;
                    }
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return false;
    }
}
