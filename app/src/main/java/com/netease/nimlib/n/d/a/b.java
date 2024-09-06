package com.netease.nimlib.n.d.a;

import android.text.TextUtils;
import com.netease.nimlib.n.b.f;
import com.netease.nimlib.o.k;
import com.netease.nimlib.o.w;
import defpackage.C$r8$backportedMethods$utility$Objects$2$equals;
import java.net.URL;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: ExceptionEventRuleHttpTriggerArtemis.java */
/* loaded from: classes.dex */
public class b implements com.netease.nimlib.n.d.a.a.c {
    private int a = 60;
    private int b = 100;
    private int c = 60;
    private int d = 0;
    private String e = null;
    private boolean f = false;
    private final Queue<Long> g = new LinkedList();
    private final AtomicLong h = new AtomicLong(0);
    private final Random i = new SecureRandom(SecureRandom.getSeed(32));

    public b() {
        a();
    }

    @Override // com.netease.nimlib.n.d.a.a.c
    public boolean a(com.netease.nimlib.n.e.d dVar) {
        if (this.f && C$r8$backportedMethods$utility$Objects$2$equals.equals(dVar.f(), String.valueOf(f.kHTTP.a()))) {
            List<com.netease.nimlib.n.c.d> l = dVar.l();
            if (l == null || l.isEmpty() || l.get(0) == null) {
                return true;
            }
            com.netease.nimlib.n.c.d dVar2 = l.get(0);
            if (!Boolean.TRUE.equals(Boolean.valueOf(dVar2.c() != null && dVar2.c().booleanValue()))) {
                return false;
            }
            String f = dVar2.f();
            int intValue = dVar2.g() == null ? 0 : dVar2.g().intValue();
            com.netease.nimlib.log.b.c("ExceptionEventRuleHttpTriggerArtemis", "comes an ANDROID http exception event");
            if (!b() || c()) {
                return false;
            }
            String a = a(f, intValue);
            if (!TextUtils.isEmpty(a)) {
                dVar2.a(a);
            }
        }
        return false;
    }

    @Override // com.netease.nimlib.n.d.a.a.c
    public boolean a(Map<String, Object> map) {
        JSONObject d;
        try {
            if (this.f && C$r8$backportedMethods$utility$Objects$2$equals.equals(map.get("action"), String.valueOf(f.kHTTP.a()))) {
                Object obj = map.get("extension");
                if (!(obj instanceof JSONArray) || (d = k.d((JSONArray) obj, 0)) == null) {
                    return true;
                }
                if (!Boolean.TRUE.equals(d.opt("net_connect"))) {
                    return false;
                }
                String optString = d.optString("target");
                int optInt = d.optInt("code");
                com.netease.nimlib.log.b.c("ExceptionEventRuleHttpTriggerArtemis", "comes an HAV http exception event");
                if (!b() || c()) {
                    return false;
                }
                String a = a(optString, optInt);
                if (!TextUtils.isEmpty(a)) {
                    d.put("detect_task_id", a);
                }
            }
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("ExceptionEventRuleHttpTriggerArtemis", "filterFromMap error: " + th.getMessage(), th);
        }
        return false;
    }

    private void a() {
        int i;
        String d = com.netease.nimlib.abtest.c.a().d();
        if (d != null) {
            this.f = com.netease.nimlib.abtest.c.a().b("http_exception_trigger_artemis", d, "open");
            this.a = com.netease.nimlib.abtest.c.a().c("http_exception_trigger_artemis", d, "time_threshold");
            this.b = com.netease.nimlib.abtest.c.a().c("http_exception_trigger_artemis", d, "count_threshold");
            this.c = com.netease.nimlib.abtest.c.a().c("http_exception_trigger_artemis", d, "intervel");
            this.d = com.netease.nimlib.abtest.c.a().c("http_exception_trigger_artemis", d, "traceroute_probability");
            this.e = com.netease.nimlib.abtest.c.a().d("http_exception_trigger_artemis", d, com.alipay.sdk.m.h.c.f);
            if (this.f && (this.a <= 0 || this.b <= 0 || this.c <= 0 || (i = this.d) < 0 || i > 100)) {
                com.netease.nimlib.log.b.d("ExceptionEventRuleHttpTriggerArtemis", "artemis open, but config is invalid, use default config");
                this.a = 60;
                this.b = 100;
                this.c = 60;
                this.d = 0;
                this.e = null;
            }
        }
        com.netease.nimlib.log.b.M("setRuleConfig open: " + this.f + ", timeThreshold: " + this.a + ", countThreshold: " + this.b + ", interval: " + this.c + ", tracerouteProbability: " + this.d + ", defaultHost: " + this.e);
    }

    private boolean b() {
        synchronized (this.g) {
            long currentTimeMillis = System.currentTimeMillis();
            this.g.offer(Long.valueOf(currentTimeMillis));
            if (this.g.size() >= this.b) {
                Long peek = this.g.peek();
                if (peek != null && currentTimeMillis - peek.longValue() < this.a * 1000) {
                    this.g.clear();
                    com.netease.nimlib.log.b.c("ExceptionEventRuleHttpTriggerArtemis", "should do probe");
                    return true;
                }
                while (this.g.size() >= this.b) {
                    this.g.poll();
                }
            }
            com.netease.nimlib.log.b.c("ExceptionEventRuleHttpTriggerArtemis", "should not do probe");
            return false;
        }
    }

    private boolean c() {
        synchronized (this.h) {
            long currentTimeMillis = System.currentTimeMillis();
            long j = currentTimeMillis - this.h.get();
            if (j < this.c * 1000) {
                com.netease.nimlib.log.b.c("ExceptionEventRuleHttpTriggerArtemis", "probe is cooling down, left: " + ((this.c * 1000) - j) + "ms");
                return true;
            }
            this.h.set(currentTimeMillis);
            com.netease.nimlib.log.b.c("ExceptionEventRuleHttpTriggerArtemis", "probe is not cooling down");
            return false;
        }
    }

    private URL d() {
        if (TextUtils.isEmpty(this.e) || this.e.equals("empty")) {
            com.netease.nimlib.log.b.c("ExceptionEventRuleHttpTriggerArtemis", "artemis default host is empty");
            return null;
        }
        try {
            return new URL(this.e);
        } catch (Throwable unused) {
            com.netease.nimlib.log.b.c("ExceptionEventRuleHttpTriggerArtemis", "artemis default host is illegal: " + this.e);
            return null;
        }
    }

    private boolean e() {
        return this.i.nextInt(100) < this.d;
    }

    private String a(String str, int i) {
        String b = w.b();
        try {
            URL d = d();
            if (d == null) {
                d = new URL(str);
            }
            String host = d.getHost();
            int port = d.getPort();
            if (port <= 0) {
                port = C$r8$backportedMethods$utility$Objects$2$equals.equals(d.getProtocol(), "https") ? 443 : 80;
            }
            if (e()) {
                HashMap hashMap = new HashMap();
                hashMap.put("task_id", b);
                hashMap.put("task_type", Integer.valueOf(com.netease.nimlib.n.b.d.TRACEROUTE.a()));
                hashMap.put("hostname", host);
                com.netease.nimlib.biz.b.a(hashMap);
                com.netease.nimlib.log.b.c("ExceptionEventRuleHttpTriggerArtemis", "doTriggerArtemis<TRACEROUTE> failedCode: " + i + " task_id: " + b);
            } else {
                HashMap hashMap2 = new HashMap();
                hashMap2.put("task_id", b);
                hashMap2.put("task_type", Integer.valueOf(com.netease.nimlib.n.b.d.TELNET.a()));
                hashMap2.put("hostname", host);
                hashMap2.put("port", Integer.toString(port));
                com.netease.nimlib.biz.b.a(hashMap2);
                com.netease.nimlib.log.b.c("ExceptionEventRuleHttpTriggerArtemis", "doTriggerArtemis<TELNET> failedCode: " + i + " task_id: " + b + ", hostname: " + host + ", port: " + port);
            }
            return b;
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("ExceptionEventRuleHttpTriggerArtemis", "filter error: " + th.getMessage(), th);
            return null;
        }
    }
}
