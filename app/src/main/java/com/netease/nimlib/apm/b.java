package com.netease.nimlib.apm;

import android.content.Context;
import com.netease.nimlib.c;
import com.netease.nimlib.net.a.d.b;
import com.talkingdata.sdk.df;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* compiled from: NimEventStrategyManager.java */
/* loaded from: classes.dex */
public class b {
    private boolean a = true;
    private volatile boolean b = false;

    /* compiled from: NimEventStrategyManager.java */
    /* loaded from: classes.dex */
    private static class a {
        private static final b a = new b();
    }

    public static b a() {
        return a.a;
    }

    public void b() {
        if (c.i().disableReport || this.b) {
            return;
        }
        if (!this.a) {
            com.netease.nimlib.log.b.G("don't need request strategy");
            return;
        }
        this.b = true;
        com.netease.nimlib.log.b.G("request strategy");
        Context e = c.e();
        if (e == null) {
            return;
        }
        com.netease.nimlib.net.a.d.b.a().a(e);
        com.netease.nimlib.net.a.d.b.a().a("https://statistic.live.126.net/dispatcher/req", c(), new b.a() { // from class: com.netease.nimlib.apm.-$$Lambda$b$nbrgl_7eKRhXcMArpN_AiCBr3kQ
            public /* synthetic */ $$Lambda$b$nbrgl_7eKRhXcMArpN_AiCBr3kQ() {
            }

            @Override // com.netease.nimlib.net.a.d.b.a
            public final void onResponse(String str, int i, Throwable th) {
                b.this.a(str, i, th);
            }
        });
    }

    public /* synthetic */ void a(String str, int i, Throwable th) {
        this.b = false;
        if (i == 200 && str != null) {
            com.netease.nimlib.log.b.G("request strategy success!,response = " + str);
            a(str);
            return;
        }
        this.a = true;
        StringBuilder sb = new StringBuilder();
        sb.append("request strategy failed, code=");
        sb.append(i);
        sb.append(", e=");
        sb.append(th == null ? null : th.getMessage());
        com.netease.nimlib.log.b.H(sb.toString());
    }

    private void a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            int i = jSONObject.getInt("code");
            this.a = false;
            if (i == 200) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("data");
                com.netease.nimlib.apm.event.c.b bVar = new com.netease.nimlib.apm.event.c.b();
                bVar.a(jSONObject2.getString("endpoint"));
                bVar.c(jSONObject2.getInt("maxDelay") * 1000);
                bVar.a(jSONObject2.getInt("maxInterval") * 1000);
                bVar.b(jSONObject2.getInt("minInterval") * 1000);
                bVar.a(jSONObject2.getInt("maxSize"));
                com.netease.nimlib.apm.a.a(bVar);
                com.netease.nimlib.apm.a.b();
            } else {
                com.netease.nimlib.log.b.G("do not need report");
                com.netease.nimlib.apm.a.a((com.netease.nimlib.apm.event.c.b) null);
                com.netease.nimlib.apm.a.a();
                com.netease.nimlib.apm.a.c();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private Map<String, String> c() {
        String c = com.netease.nimlib.push.b.c();
        HashMap hashMap = new HashMap(5);
        hashMap.put(df.c, c);
        hashMap.put("sdktype", "IM");
        hashMap.put("sdkVer", "9.17.0");
        hashMap.put("platform", "AOS");
        hashMap.put(com.alipay.sdk.m.o.a.p, c.g());
        return hashMap;
    }
}
