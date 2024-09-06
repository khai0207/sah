package com.netease.nimlib.n;

import androidx.core.app.NotificationCompat;
import org.json.JSONObject;

/* compiled from: PushSyncEventManager.java */
/* loaded from: classes.dex */
public class m {
    private com.netease.nimlib.n.e.j a;

    /* compiled from: PushSyncEventManager.java */
    /* loaded from: classes.dex */
    private static class a {
        private static final m a = new m();
    }

    public static m a() {
        return a.a;
    }

    public boolean b() {
        try {
            com.netease.nimlib.n.e.j jVar = new com.netease.nimlib.n.e.j();
            this.a = jVar;
            jVar.a(false);
            this.a.a(com.netease.nimlib.c.n());
            this.a.b(com.netease.nimlib.push.f.l().k());
            this.a.c(com.netease.nimlib.n.f.a.a(false));
            this.a.a(com.netease.nimlib.n.b.q.K_SYNC_ACTION_5_1);
            com.netease.nimlib.log.b.G("PushSyncEventManager startTrackEvent51 model = " + this.a.m());
            com.netease.nimlib.ipc.e.b(a(this.a));
            return true;
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("PushSyncEventManager", " startTrackEvent51 Exception", th);
            return false;
        }
    }

    public boolean a(boolean z, int i) {
        if (this.a == null) {
            return false;
        }
        com.netease.nimlib.log.b.G("PushSyncEventManager stopTrackEvent51 isSuccess = " + z + ", code = " + i);
        return a(z, "");
    }

    public boolean a(boolean z, String str) {
        if (this.a == null) {
            return false;
        }
        if (!z) {
            com.netease.nimlib.log.b.G("PushSyncEventManager stopTrackEvent51 error," + str);
            this.a = null;
            return false;
        }
        com.netease.nimlib.log.b.G("PushSyncEventManager stopTrackEvent51 isSuccess = " + z + ", description = " + str);
        try {
            this.a.d(com.netease.nimlib.n.f.a.a(this.a.a()));
            com.netease.nimlib.log.b.G("PushSyncEventManager stopTrackEvent51 model = " + this.a.m());
            com.netease.nimlib.ipc.e.b(a(this.a));
            this.a = null;
            return true;
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("PushSyncEventManager", " stopTrackEvent51 Exception", th);
            return false;
        }
    }

    private String a(com.netease.nimlib.n.e.j jVar) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("eventKey", jVar.o());
            jSONObject.put("priority", jVar.n());
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("user_id", jVar.d());
            jSONObject2.put("trace_id", jVar.e());
            jSONObject2.put("action", jVar.f());
            jSONObject2.put("sync_begin_time", jVar.r());
            jSONObject2.put("sync_end_time", jVar.s());
            jSONObject.put(NotificationCompat.CATEGORY_EVENT, jSONObject2);
            String jSONObject3 = jSONObject.toString();
            com.netease.nimlib.log.b.G("PushSyncEventManager getEventJson = " + jSONObject3);
            return jSONObject3;
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("PushSyncEventManager", " getEventJson Exception", th);
            return null;
        }
    }
}
