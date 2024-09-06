package com.netease.nimlib.n.d.a;

import android.text.TextUtils;
import com.netease.nimlib.n.b.f;
import com.netease.nimlib.o.k;
import com.netease.nimlib.o.w;
import defpackage.C$r8$backportedMethods$utility$Objects$2$equals;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: ExceptionEventRuleTcpTriggerArtemis.java */
/* loaded from: classes.dex */
public class d implements com.netease.nimlib.n.d.a.a.c {
    private int a = 60;
    private int b = 100;
    private int c = 60;
    private String d = null;
    private String e = null;
    private boolean f = false;
    private final Queue<Long> g = new LinkedList();
    private final AtomicLong h = new AtomicLong(0);

    public d() {
        a();
    }

    @Override // com.netease.nimlib.n.d.a.a.c
    public boolean a(com.netease.nimlib.n.e.d dVar) {
        if (this.f && C$r8$backportedMethods$utility$Objects$2$equals.equals(dVar.f(), String.valueOf(f.kTCP.a()))) {
            List<com.netease.nimlib.n.c.d> l = dVar.l();
            if (l == null || l.isEmpty() || l.get(0) == null) {
                return true;
            }
            com.netease.nimlib.n.c.d dVar2 = l.get(0);
            if (!Boolean.TRUE.equals(Boolean.valueOf(dVar2.c() != null && dVar2.c().booleanValue()))) {
                return false;
            }
            com.netease.nimlib.log.b.c("ExceptionEventRuleTcpTriggerArtemis", "comes an ANDROID tcp exception event");
            if (!b() || c()) {
                return false;
            }
            String d = d();
            if (!TextUtils.isEmpty(d)) {
                dVar2.a(d);
            }
        }
        return false;
    }

    @Override // com.netease.nimlib.n.d.a.a.c
    public boolean a(Map<String, Object> map) {
        JSONObject d;
        try {
            if (this.f && C$r8$backportedMethods$utility$Objects$2$equals.equals(map.get("action"), String.valueOf(f.kTCP.a()))) {
                Object obj = map.get("extension");
                if (!(obj instanceof JSONArray) || (d = k.d((JSONArray) obj, 0)) == null) {
                    return true;
                }
                if (!Boolean.TRUE.equals(d.opt("net_connect"))) {
                    return false;
                }
                com.netease.nimlib.log.b.c("ExceptionEventRuleTcpTriggerArtemis", "comes an HAV tcp exception event");
                if (!b() || c()) {
                    return false;
                }
                String d2 = d();
                if (!TextUtils.isEmpty(d2)) {
                    d.put("detect_task_id", d2);
                }
            }
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("ExceptionEventRuleTcpTriggerArtemis", "filterFromMap error: " + th.getMessage(), th);
        }
        return false;
    }

    private void a() {
        String e = com.netease.nimlib.abtest.c.a().e();
        if (e != null) {
            this.f = com.netease.nimlib.abtest.c.a().b("udp_ping_detect_android", e, "open");
            this.a = com.netease.nimlib.abtest.c.a().c("udp_ping_detect_android", e, "time_threshold");
            this.b = com.netease.nimlib.abtest.c.a().c("udp_ping_detect_android", e, "count_threshold");
            this.c = com.netease.nimlib.abtest.c.a().c("udp_ping_detect_android", e, "intervel");
            this.d = com.netease.nimlib.abtest.c.a().d("udp_ping_detect_android", e, com.alipay.sdk.m.h.c.f);
            this.e = com.netease.nimlib.abtest.c.a().d("udp_ping_detect_android", e, "port");
            if (this.f && (this.a <= 0 || this.b <= 0 || this.c <= 0)) {
                com.netease.nimlib.log.b.d("ExceptionEventRuleTcpTriggerArtemis", "artemis open, but config is invalid, use default config");
                this.a = 60;
                this.b = 100;
                this.c = 60;
            }
        }
        com.netease.nimlib.log.b.M("ExceptionEventRuleTcpTriggerArtemis, setRuleConfig open: " + this.f + ", timeThreshold: " + this.a + ", countThreshold: " + this.b + ", interval: " + this.c + ", host: " + this.d + ", port: " + this.e);
    }

    private boolean b() {
        synchronized (this.g) {
            long currentTimeMillis = System.currentTimeMillis();
            this.g.offer(Long.valueOf(currentTimeMillis));
            if (this.g.size() >= this.b) {
                Long peek = this.g.peek();
                if (peek != null && currentTimeMillis - peek.longValue() < this.a * 1000) {
                    this.g.clear();
                    com.netease.nimlib.log.b.c("ExceptionEventRuleTcpTriggerArtemis", "should do probe");
                    return true;
                }
                while (this.g.size() >= this.b) {
                    this.g.poll();
                }
            }
            com.netease.nimlib.log.b.c("ExceptionEventRuleTcpTriggerArtemis", "should not do probe");
            return false;
        }
    }

    private boolean c() {
        synchronized (this.h) {
            long currentTimeMillis = System.currentTimeMillis();
            long j = currentTimeMillis - this.h.get();
            if (j < this.c * 1000) {
                com.netease.nimlib.log.b.c("ExceptionEventRuleTcpTriggerArtemis", "probe is cooling down, left: " + ((this.c * 1000) - j) + "ms");
                return true;
            }
            this.h.set(currentTimeMillis);
            com.netease.nimlib.log.b.c("ExceptionEventRuleTcpTriggerArtemis", "probe is not cooling down");
            return false;
        }
    }

    private String d() {
        String str = "UDP_" + w.b();
        try {
            if (TextUtils.isEmpty(this.d)) {
                com.netease.nimlib.log.b.f("ExceptionEventRuleTcpTriggerArtemis", "host is illegal, host: " + this.d);
                return null;
            }
            int parseInt = TextUtils.isEmpty(this.e) ? 80 : Integer.parseInt(this.e);
            HashMap hashMap = new HashMap();
            hashMap.put("task_id", str);
            hashMap.put("task_type", Integer.valueOf(com.netease.nimlib.n.b.d.UDP_PING.a()));
            hashMap.put("hostname", this.d);
            hashMap.put("port", Integer.toString(parseInt));
            com.netease.nimlib.biz.b.a(hashMap);
            com.netease.nimlib.log.b.c("ExceptionEventRuleTcpTriggerArtemis", "doTriggerArtemis<UDP_PING> task_id: " + str + ", hostname: " + this.d + ", port: " + this.e);
            return str;
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("ExceptionEventRuleTcpTriggerArtemis", "filter error: " + th.getMessage(), th);
            return null;
        }
    }
}
