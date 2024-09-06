package com.netease.nimlib.n;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import org.json.JSONObject;

/* compiled from: ExceptionEventManager.java */
/* loaded from: classes.dex */
public class e {
    private static Boolean a;

    private static boolean a() {
        if (a == null) {
            a = Boolean.valueOf(a(com.netease.nimlib.c.e()));
        }
        return a.booleanValue();
    }

    private static boolean a(Context context) {
        boolean z = false;
        if (context == null) {
            return false;
        }
        try {
            Cursor query = context.getContentResolver().query(Uri.parse(String.format("content://%s/integer/%s/%s", context.getPackageName() + ".ipc.provider.preference", "PARAMS", "AB_LINK_KEEP_EXCEPTION_REPORT")), null, null, null, null);
            if (query != null && query.moveToFirst()) {
                int i = query.getInt(0);
                query.close();
                if (i != 0) {
                    z = true;
                }
            }
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("ExceptionEventManager", "isABLinkKeepExceptionReportOpen exception", th);
        }
        com.netease.nimlib.log.b.d("ExceptionEventManager", "isABLinkKeepExceptionReportOpen result = " + z);
        return z;
    }

    public static void a(com.netease.nimlib.push.net.lbs.b bVar, com.netease.nimlib.n.b.g gVar) {
        try {
            com.netease.nimlib.log.b.G("ExceptionEventManager#startTCPTrackEvent IN");
            if (com.netease.nimlib.h.h()) {
                r.a().a(bVar, gVar);
            } else {
                k.a().a(bVar, gVar);
            }
            com.netease.nimlib.log.b.G("ExceptionEventManager#startTCPTrackEvent OUT");
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("ExceptionEventManager", "startTCPTrackEvent exception", th);
        }
    }

    public static void a(com.netease.nimlib.push.net.lbs.b bVar, com.netease.nimlib.n.b.s sVar, int i, String str, boolean z) {
        try {
            com.netease.nimlib.log.b.G("ExceptionEventManager#stopTCPTrackEvent IN");
            com.netease.nimlib.n.c.n a2 = z ? null : com.netease.nimlib.n.c.n.a(sVar, bVar, i, str);
            com.netease.nimlib.n.b.h hVar = z ? com.netease.nimlib.n.b.h.kSucceed : com.netease.nimlib.n.b.h.kFailed;
            if (com.netease.nimlib.h.h()) {
                r.a().a(bVar, a2, hVar);
            } else {
                k.a().a(bVar, a2, hVar);
            }
            com.netease.nimlib.log.b.G("ExceptionEventManager#stopTCPTrackEvent OUT");
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("ExceptionEventManager", "stopTCPTrackEvent exception", th);
        }
    }

    public static void a(String str, com.netease.nimlib.n.b.g gVar) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            if (com.netease.nimlib.h.h()) {
                r.a().a(str, gVar);
            } else {
                k.a().a(str, gVar);
            }
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("ExceptionEventManager", "startHTTPTrackEvent exception", th);
        }
    }

    public static void a(String str, com.netease.nimlib.n.b.j jVar, String str2, int i, String str3, String str4, String str5) {
        a(str, "", jVar, str2, i, str3, str4, str5);
    }

    public static void a(String str, String str2, com.netease.nimlib.n.b.j jVar, String str3, int i, String str4, String str5, String str6) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            com.netease.nimlib.n.b.h hVar = com.netease.nimlib.n.b.h.kFailed;
            com.netease.nimlib.n.c.f a2 = com.netease.nimlib.n.c.f.a(jVar, str3, i, str4, str5, str6);
            if (com.netease.nimlib.h.h()) {
                r.a().a(str, str2, a2, hVar);
            } else {
                k.a().a(str, str2, a2, hVar);
            }
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("ExceptionEventManager", "stopHTTPTrackEventFailed exception", th);
        }
    }

    public static void a(String str) {
        a(str, "");
    }

    public static void a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            if (com.netease.nimlib.h.h()) {
                r.a().a(str, str2, (com.netease.nimlib.n.c.f) null, com.netease.nimlib.n.b.h.kSucceed);
            } else {
                k.a().a(str, str2, (com.netease.nimlib.n.c.f) null, com.netease.nimlib.n.b.h.kSucceed);
            }
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("ExceptionEventManager", "stopHTTPTrackEventSuccess exception", th);
        }
    }

    public static void a(com.netease.nimlib.biz.d.a aVar) {
        if (aVar == null) {
            return;
        }
        try {
            if (com.netease.nimlib.h.h()) {
                r.a().a(aVar.i(), com.netease.nimlib.n.b.g.UNKNOWN);
            } else {
                k.a().a(aVar.i(), com.netease.nimlib.n.b.g.UNKNOWN);
            }
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("ExceptionEventManager", "startBusinessTrackEvent exception", th);
        }
    }

    public static void a(com.netease.nimlib.push.packet.a aVar, int i, com.netease.nimlib.n.b.b bVar) {
        a(aVar, i, bVar, (com.netease.nimlib.n.b.e) null);
    }

    public static void a(com.netease.nimlib.push.packet.a aVar, int i, com.netease.nimlib.n.b.b bVar, com.netease.nimlib.n.b.e eVar) {
        if (aVar == null) {
            return;
        }
        String str = null;
        if (eVar != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("disconnect_reason", eVar.a());
                str = jSONObject.toString();
            } catch (Throwable th) {
                com.netease.nimlib.log.b.e("ExceptionEventManager", "stopBusinessTrackEvent exception", th);
                return;
            }
        }
        com.netease.nimlib.n.c.a a2 = com.netease.nimlib.n.c.a.a(bVar, aVar, i, str);
        com.netease.nimlib.n.b.h hVar = com.netease.nimlib.n.b.h.kSucceed;
        if (i == 408 || i == 415) {
            hVar = com.netease.nimlib.n.b.h.kFailed;
        }
        if (com.netease.nimlib.h.h()) {
            r.a().a(aVar, a2, hVar);
        } else {
            k.a().a(aVar, a2, hVar);
        }
    }

    public static void a(com.netease.nimlib.push.packet.a aVar, int i, com.netease.nimlib.n.b.h hVar, com.netease.nimlib.n.b.b bVar, JSONObject jSONObject) {
        String jSONObject2;
        if (aVar == null) {
            return;
        }
        if (jSONObject == null) {
            jSONObject2 = null;
        } else {
            try {
                jSONObject2 = jSONObject.toString();
            } catch (Throwable th) {
                com.netease.nimlib.log.b.e("ExceptionEventManager", "recordBusinessTrackEvent exception", th);
                return;
            }
        }
        com.netease.nimlib.n.c.a a2 = com.netease.nimlib.n.c.a.a(bVar, aVar, i, jSONObject2);
        if (hVar == null) {
            hVar = com.netease.nimlib.n.b.h.kSucceed;
            if (i == 408 || i == 415) {
                hVar = com.netease.nimlib.n.b.h.kFailed;
            }
        }
        if (com.netease.nimlib.h.h()) {
            r.a().a(a2, hVar);
        } else {
            k.a().a(a2, hVar);
        }
    }

    public static void a(String str, com.netease.nimlib.n.b.k kVar, String str2, String str3) {
        try {
            com.netease.nimlib.n.b.h hVar = com.netease.nimlib.n.b.h.kFailed;
            com.netease.nimlib.n.c.g a2 = com.netease.nimlib.n.c.g.a(kVar, str, str2, str3);
            if (com.netease.nimlib.h.h()) {
                r.a().a(a2, hVar);
            } else {
                k.a().a(a2, hVar);
            }
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("ExceptionEventManager", "stopLibraryTrackEventFailed exception", th);
        }
    }

    public static void a(String str, com.netease.nimlib.n.b.c cVar, String str2, String str3) {
        if (!TextUtils.isEmpty(str) && str.contains("event.db")) {
            com.netease.nimlib.log.b.e("ExceptionEventManager", String.format("skip to avoid recursive exception: %s %s %s %s", str, cVar, str2, str3));
            return;
        }
        try {
            com.netease.nimlib.n.b.h hVar = com.netease.nimlib.n.b.h.kFailed;
            com.netease.nimlib.n.c.c cVar2 = new com.netease.nimlib.n.c.c(cVar, str, str2, str3);
            if (com.netease.nimlib.h.h()) {
                r.a().a(cVar2, hVar);
            } else {
                k.a().a(cVar2, hVar);
            }
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("ExceptionEventManager", "stopDatabaseTrackEventFailed exception", th);
        }
    }

    public static void a(com.netease.nimlib.n.b.i iVar, String str, String str2) {
        try {
            com.netease.nimlib.n.b.h hVar = com.netease.nimlib.n.b.h.kFailed;
            com.netease.nimlib.n.c.e eVar = new com.netease.nimlib.n.c.e(iVar, str, str2);
            if (com.netease.nimlib.h.h()) {
                r.a().a(eVar, hVar);
            } else {
                k.a().a(eVar, hVar);
            }
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("ExceptionEventManager", "stopFileTrackEventFailed exception", th);
        }
    }

    public static void a(com.netease.nimlib.n.b.p pVar, String str, String str2, String str3) {
        try {
            com.netease.nimlib.n.b.h hVar = com.netease.nimlib.n.b.h.kFailed;
            com.netease.nimlib.n.c.k a2 = com.netease.nimlib.n.c.k.a(pVar, str, str2, str3);
            if (com.netease.nimlib.h.h()) {
                r.a().a(a2, hVar);
            } else {
                k.a().a(a2, hVar);
            }
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("ExceptionEventManager", "stopRuntimeTrackEventFailed exception", th);
        }
    }

    public static void b(com.netease.nimlib.push.net.lbs.b bVar, com.netease.nimlib.n.b.g gVar) {
        if (!a()) {
            com.netease.nimlib.log.b.M("link keep exception report is closed");
            return;
        }
        try {
            com.netease.nimlib.log.b.G("ExceptionEventManager#startLinkKeepTrackEvent IN");
            com.netease.nimlib.n.c.h a2 = com.netease.nimlib.n.c.h.a(com.netease.nimlib.n.b.l.kConnected, bVar, 0, "");
            if (com.netease.nimlib.h.h()) {
                r.a().a(bVar, gVar, a2);
            } else {
                k.a().a(bVar, gVar, a2);
            }
            com.netease.nimlib.log.b.G("ExceptionEventManager#startLinkKeepTrackEvent OUT");
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("ExceptionEventManager", "startLinkKeepTrackEvent exception", th);
        }
    }

    public static void a(com.netease.nimlib.push.net.lbs.b bVar, int i, String str, boolean z) {
        if (!a()) {
            com.netease.nimlib.log.b.M("link keep exception report is closed");
            return;
        }
        if (bVar == null) {
            return;
        }
        try {
            com.netease.nimlib.log.b.G("ExceptionEventManager#stopLinkKeepTrackEvent IN");
            com.netease.nimlib.n.c.h a2 = z ? null : com.netease.nimlib.n.c.h.a(com.netease.nimlib.n.b.l.kDisconnected, bVar, i, str);
            com.netease.nimlib.n.b.h hVar = z ? com.netease.nimlib.n.b.h.kSucceed : com.netease.nimlib.n.b.h.kFailed;
            if (com.netease.nimlib.h.h()) {
                r.a().a(bVar, a2, hVar);
            } else {
                k.a().a(bVar, a2, hVar);
            }
            com.netease.nimlib.log.b.G("ExceptionEventManager#stopLinkKeepTrackEvent OUT");
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("ExceptionEventManager", "stopLinkKeepTrackEvent exception", th);
        }
    }
}
