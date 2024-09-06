package com.netease.nimlib.n;

import android.content.Context;
import androidx.core.app.NotificationCompat;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: UIEventReceiver.java */
/* loaded from: classes.dex */
public class q {

    /* compiled from: UIEventReceiver.java */
    /* loaded from: classes.dex */
    private static class a {
        private static final q a = new q();
    }

    public static q a() {
        return a.a;
    }

    public void a(com.netease.nimlib.apm.b.a aVar) {
        if (aVar instanceof com.netease.nimlib.n.c.i) {
            s.a().a((com.netease.nimlib.n.c.i) aVar);
        }
    }

    public void a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        String optString = jSONObject.optString("eventKey");
        if ("exceptions".equals(optString)) {
            r.a().a(jSONObject.optJSONObject(NotificationCompat.CATEGORY_EVENT));
        } else if ("nim_sdk_sync".equals(optString)) {
            t.a().a(jSONObject.optJSONObject(NotificationCompat.CATEGORY_EVENT));
        } else if ("nim_sdk_lbs_records".equals(optString)) {
            b(jSONObject.optJSONObject(NotificationCompat.CATEGORY_EVENT));
        }
    }

    public void b(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        try {
            String n = com.netease.nimlib.c.n();
            HashMap hashMap = new HashMap();
            if (n != null) {
                hashMap.put("user_id", n);
            }
            hashMap.put("start_time", Long.valueOf(jSONObject.optLong("start_time")));
            hashMap.put("duration", Long.valueOf(jSONObject.optLong("duration")));
            hashMap.put("succeed", Boolean.valueOf(jSONObject.optBoolean("succeed")));
            Context e = com.netease.nimlib.c.e();
            com.netease.nimlib.apm.b.d b = com.netease.nimlib.apm.b.d.b(com.netease.nimlib.o.p.j(e));
            Boolean valueOf = Boolean.valueOf(com.netease.nimlib.network.f.a(e));
            if (b != null) {
                hashMap.put("net_type", Integer.valueOf(b.a()));
            }
            hashMap.put("net_connect", valueOf);
            JSONArray optJSONArray = jSONObject.optJSONArray("history");
            if (optJSONArray != null && optJSONArray.length() > 0) {
                hashMap.put("history", optJSONArray);
            }
            if (com.netease.nimlib.n.d.a.a().a("nim_sdk_lbs_records", hashMap)) {
                com.netease.nimlib.log.b.G("filter recordEvent eventKey = nim_sdk_lbs_records, map = " + hashMap);
                return;
            }
            com.netease.nimlib.log.b.G("receiveLbsRecordsEventString map = " + hashMap);
            com.netease.nimlib.apm.event.a.b().a("nim_sdk_lbs_records", hashMap, 0L);
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("UIEventReceiver", "receiveLbsRecordsEventString Exception", th);
        }
    }
}
