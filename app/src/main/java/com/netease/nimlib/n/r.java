package com.netease.nimlib.n;

import android.content.Context;
import android.text.TextUtils;
import com.netease.nimlib.amazonaws.mobileconnectors.s3.transferutility.TransferTable;
import defpackage.C$r8$backportedMethods$utility$Objects$2$equals;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: UIExceptionEventManager.java */
/* loaded from: classes.dex */
public class r extends b {
    private final Map<String, com.netease.nimlib.n.e.d> a = new HashMap();

    /* compiled from: UIExceptionEventManager.java */
    /* loaded from: classes.dex */
    private static class a {
        private static final r a = new r();
    }

    public static r a() {
        return a.a;
    }

    @Override // com.netease.nimlib.n.b
    public void a(com.netease.nimlib.n.b.f fVar, String str, com.netease.nimlib.n.b.g gVar) {
        a(fVar, str, gVar, (com.netease.nimlib.n.c.d) null);
    }

    @Override // com.netease.nimlib.n.b
    public void a(com.netease.nimlib.n.b.f fVar, String str, com.netease.nimlib.n.b.g gVar, com.netease.nimlib.n.c.d dVar) {
        if (fVar != null) {
            try {
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                com.netease.nimlib.n.e.d dVar2 = new com.netease.nimlib.n.e.d();
                boolean a2 = com.netease.nimlib.n.f.a.a();
                dVar2.a(a2);
                dVar2.a(com.netease.nimlib.n.f.a.a(a2));
                dVar2.a(com.netease.nimlib.c.n());
                dVar2.a(fVar);
                dVar2.b(com.netease.nimlib.biz.i.a().d());
                dVar2.a(gVar);
                if (dVar != null) {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(dVar);
                    dVar2.a(arrayList);
                }
                com.netease.nimlib.log.b.G("UIExceptionEventManager startTrackEvent model = " + dVar2.m());
                this.a.put(fVar.a() + "_" + str, dVar2);
            } catch (Throwable th) {
                com.netease.nimlib.log.b.e("UIExceptionEventManager", " startTrackEvent Exception", th);
            }
        }
    }

    @Override // com.netease.nimlib.n.b
    public void a(com.netease.nimlib.n.b.f fVar, String str, com.netease.nimlib.n.c.d dVar, com.netease.nimlib.n.b.h hVar) {
        a(fVar, str, "", dVar, hVar);
    }

    @Override // com.netease.nimlib.n.b
    public void a(com.netease.nimlib.n.b.f fVar, String str, String str2, com.netease.nimlib.n.c.d dVar, com.netease.nimlib.n.b.h hVar) {
        if (fVar != null) {
            try {
                if (!TextUtils.isEmpty(str) && hVar != null) {
                    com.netease.nimlib.log.b.G("UIExceptionEventManager stopTrackEvent state = " + hVar);
                    if (hVar == com.netease.nimlib.n.b.h.kSucceed) {
                        this.a.remove(fVar.a() + "_" + str);
                        return;
                    }
                    com.netease.nimlib.n.e.d remove = this.a.remove(fVar.a() + "_" + str);
                    if (remove == null) {
                        return;
                    }
                    if (remove.f() == null) {
                        remove.a(fVar);
                    }
                    remove.d(str2);
                    remove.a(hVar.a());
                    remove.b(com.netease.nimlib.n.f.a.a(remove.a()));
                    if (dVar != null) {
                        List<com.netease.nimlib.n.c.d> l = remove.l();
                        if (l == null) {
                            l = new ArrayList<>();
                            remove.a(l);
                        }
                        l.add(dVar);
                    }
                    com.netease.nimlib.apm.a.a(remove.o(), (com.netease.nimlib.apm.b.b<? extends com.netease.nimlib.apm.b.a>) remove);
                }
            } catch (Throwable th) {
                com.netease.nimlib.log.b.e("UIExceptionEventManager", " stopTrackEvent Exception", th);
            }
        }
    }

    @Override // com.netease.nimlib.n.b
    public void a(com.netease.nimlib.n.b.f fVar, com.netease.nimlib.n.c.d dVar, com.netease.nimlib.n.b.h hVar) {
        if (fVar == null || hVar == null) {
            return;
        }
        try {
            com.netease.nimlib.log.b.G("UIExceptionEventManager recordTrackEvent state = " + hVar);
            if (hVar == com.netease.nimlib.n.b.h.kSucceed) {
                return;
            }
            com.netease.nimlib.n.e.d dVar2 = new com.netease.nimlib.n.e.d();
            boolean a2 = com.netease.nimlib.n.f.a.a();
            dVar2.a(a2);
            long a3 = com.netease.nimlib.n.f.a.a(a2);
            dVar2.a(a3);
            dVar2.a(com.netease.nimlib.c.n());
            dVar2.a(fVar);
            dVar2.b(com.netease.nimlib.biz.i.a().d());
            dVar2.a(hVar.a());
            dVar2.b(a3);
            if (dVar != null) {
                List<com.netease.nimlib.n.c.d> l = dVar2.l();
                if (l == null) {
                    l = new ArrayList<>();
                    dVar2.a(l);
                }
                l.add(dVar);
            }
            com.netease.nimlib.apm.a.a(dVar2.o(), (com.netease.nimlib.apm.b.b<? extends com.netease.nimlib.apm.b.a>) dVar2);
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("UIExceptionEventManager", " recordTrackEvent Exception", th);
        }
    }

    public void a(com.netease.nimlib.n.e.d dVar) {
        try {
            com.netease.nimlib.apm.a.a(dVar.o(), (com.netease.nimlib.apm.b.b<? extends com.netease.nimlib.apm.b.a>) dVar);
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("UIExceptionEventManager", "receivePushEvent Exception", th);
        }
    }

    public void a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        try {
            String n = com.netease.nimlib.c.n();
            String d = com.netease.nimlib.biz.i.a().d();
            HashMap hashMap = new HashMap();
            if (n != null) {
                hashMap.put("user_id", n);
            }
            if (d != null) {
                hashMap.put("trace_id", d);
            }
            String optString = jSONObject.optString("action");
            hashMap.put("action", optString);
            hashMap.put("start_time", Long.valueOf(jSONObject.optLong("start_time")));
            hashMap.put("duration", Long.valueOf(jSONObject.optLong("duration")));
            hashMap.put(TransferTable.COLUMN_STATE, Integer.valueOf(jSONObject.optInt(TransferTable.COLUMN_STATE)));
            if (jSONObject.has("process_id")) {
                hashMap.put("process_id", jSONObject.optString("process_id"));
            }
            int optInt = jSONObject.optInt("exception_service", -1);
            if (optInt >= 0) {
                hashMap.put("exception_service", Integer.valueOf(optInt));
            }
            long j = 0;
            if (C$r8$backportedMethods$utility$Objects$2$equals.equals(optString, String.valueOf(com.netease.nimlib.n.b.f.kTCP.a())) || C$r8$backportedMethods$utility$Objects$2$equals.equals(optString, String.valueOf(com.netease.nimlib.n.b.f.kBusiness.a())) || (C$r8$backportedMethods$utility$Objects$2$equals.equals(optString, String.valueOf(com.netease.nimlib.n.b.f.kHTTP.a())) && optInt > com.netease.nimlib.n.b.g.UNKNOWN.a())) {
                j = 10000;
            }
            JSONArray optJSONArray = jSONObject.optJSONArray("extension");
            JSONArray jSONArray = null;
            if (optJSONArray != null && optJSONArray.length() > 0) {
                Context e = com.netease.nimlib.c.e();
                com.netease.nimlib.apm.b.d b = com.netease.nimlib.apm.b.d.b(com.netease.nimlib.o.p.j(e));
                Boolean valueOf = Boolean.valueOf(com.netease.nimlib.network.f.a(e));
                JSONArray jSONArray2 = new JSONArray();
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    if (optJSONObject != null) {
                        if (b != null) {
                            optJSONObject.put("net_type", b.a());
                        }
                        optJSONObject.put("net_connect", valueOf);
                        jSONArray2.put(optJSONObject);
                    }
                }
                jSONArray = jSONArray2;
            }
            if (jSONArray != null && jSONArray.length() > 0) {
                hashMap.put("extension", jSONArray);
            }
            if (com.netease.nimlib.n.d.a.a().a("exceptions", hashMap)) {
                com.netease.nimlib.log.b.G("filter recordEvent eventKey = exceptions, map = " + hashMap);
                return;
            }
            com.netease.nimlib.log.b.G("receiveEventString map = " + hashMap);
            com.netease.nimlib.apm.event.a.b().a("exceptions", hashMap, j);
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("UIExceptionEventManager", "receivePushEvent Exception", th);
        }
    }
}
