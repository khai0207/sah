package com.netease.nimlib.push.c;

import android.text.TextUtils;
import android.util.Pair;
import com.netease.nim.highavailable.HighAvailableLBSService;
import com.netease.nimlib.o.f;
import com.netease.nimlib.o.y;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: QuickConnectLinkCache.java */
/* loaded from: classes.dex */
public class b {
    private static b g;
    private final List<com.netease.nimlib.push.net.lbs.b> b = new ArrayList();
    private Pair<Pair<String, Integer>, Long> c = new Pair<>(null, 0L);
    private final Set<Pair<String, Integer>> d = new HashSet();
    private final Set<Pair<String, Integer>> e = new HashSet();
    private boolean f = false;
    private final a a = a.a();

    public static synchronized b a() {
        b bVar;
        synchronized (b.class) {
            if (g == null) {
                g = new b();
            }
            bVar = g;
        }
        return bVar;
    }

    public b() {
        String string = com.netease.nimlib.biz.c.p().getString(c(), "");
        com.netease.nimlib.log.b.d("QuickConnectLinkCache", "onGetLbsResponse from cache:" + string);
        if (TextUtils.isEmpty(string)) {
            return;
        }
        a(string);
    }

    public synchronized com.netease.nimlib.push.net.lbs.b b() {
        while (!f.c((Collection) this.b)) {
            com.netease.nimlib.push.net.lbs.b bVar = this.b.get(0);
            if (!a(bVar, d(bVar.b, bVar.c), false)) {
                this.b.remove(0);
            } else {
                bVar.a(true);
                if (!this.f && this.a.c()) {
                    bVar.a(this.a.d());
                } else {
                    bVar.a(this.a.e());
                }
                com.netease.nimlib.log.b.d("QuickConnectLinkCache", "getCurrentLink: " + bVar);
                return bVar;
            }
        }
        com.netease.nimlib.log.b.d("QuickConnectLinkCache", "getCurrentLink null");
        return null;
    }

    public synchronized void a(String str) {
        JSONObject optJSONObject;
        com.netease.nimlib.log.b.d("QuickConnectLinkCache", "onGetLbsResponse:" + str);
        com.netease.nimlib.log.b.d("QuickConnectLinkCache", "onGetLbsResponse before update:" + f.f(this.b));
        this.f = false;
        try {
            optJSONObject = new JSONObject(str).optJSONObject("common");
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("QuickConnectLinkCache", "onGetLbsResponse:" + str, th);
        }
        if (optJSONObject == null) {
            return;
        }
        JSONArray optJSONArray = optJSONObject.optJSONArray("link");
        if (optJSONArray == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < optJSONArray.length(); i++) {
            arrayList.add(new com.netease.nimlib.push.net.lbs.b(optJSONArray.getString(i)));
        }
        this.b.clear();
        this.b.addAll(arrayList);
        com.netease.nimlib.biz.c.p().edit().putString(c(), str).commit();
        this.c = new Pair<>(null, 0L);
        com.netease.nimlib.log.b.d("QuickConnectLinkCache", "onGetLbsResponse after update:" + f.f(this.b));
    }

    private static String c() {
        return "CachedLbsResponse_" + com.netease.nimlib.c.g();
    }

    public synchronized void a(String str, int i) {
        com.netease.nimlib.log.b.d("QuickConnectLinkCache", String.format("onQuickConnectSucceed: %s %s", str, Integer.valueOf(i)));
        Pair<String, Integer> d = d(str, i);
        long d2 = y.d();
        if (d.equals(this.c.first)) {
            com.netease.nimlib.log.b.d("QuickConnectLinkCache", String.format("onQuickConnectSucceed: %s %s -> %s", d, this.c.second, Long.valueOf(d2)));
        } else {
            this.c = new Pair<>(d, Long.valueOf(d2));
        }
    }

    public synchronized void a(com.netease.nimlib.push.net.lbs.b bVar) {
        com.netease.nimlib.log.b.d("QuickConnectLinkCache", "onQuickConnectFailed:" + bVar);
        if (f.c((Collection) this.b) || !this.b.get(0).a(bVar)) {
            com.netease.nimlib.log.b.e("QuickConnectLinkCache", String.format("failedLink mismatch: %s %s", com.netease.nimlib.push.net.lbs.b.b(bVar), f.f(this.b)));
        } else {
            this.b.remove(0);
            if (!this.f) {
                this.f = true;
            }
        }
    }

    public void b(String str, int i) {
        com.netease.nimlib.log.b.d("QuickConnectLinkCache", String.format("reportConnectSucceed: %s %s", str, Integer.valueOf(i)));
        this.d.add(d(str, i));
    }

    public void c(String str, int i) {
        com.netease.nimlib.log.b.d("QuickConnectLinkCache", String.format("reportConnectTimeout: %s %s", str, Integer.valueOf(i)));
        this.e.add(d(str, i));
    }

    public synchronized boolean a(com.netease.nimlib.push.net.lbs.b bVar, String str, int i) {
        Pair<String, Integer> d = d(str, i);
        if (!a(bVar, d, true)) {
            return false;
        }
        if (this.d.contains(d)) {
            bVar.a(this.a.d());
        }
        return true;
    }

    private synchronized boolean a(com.netease.nimlib.push.net.lbs.b bVar, Pair<String, Integer> pair, boolean z) {
        if (this.e.contains(pair)) {
            com.netease.nimlib.log.b.e("QuickConnectLinkCache", "getCurrentLink skip: " + bVar);
            return false;
        }
        if (z) {
            String str = (String) pair.first;
            HighAvailableLBSService.AddressFamily addressFamily = com.netease.nimlib.push.net.lbs.a.a().toAddressFamily();
            if (addressFamily == HighAvailableLBSService.AddressFamily.kIPV4) {
                if (!d.a(str)) {
                    return false;
                }
            } else if (addressFamily == HighAvailableLBSService.AddressFamily.kIPV6 && !d.b(str)) {
                return false;
            }
        }
        long d = y.d();
        if (pair.equals(this.c.first)) {
            long longValue = d - ((Long) this.c.second).longValue();
            long g2 = this.a.g();
            if (longValue >= g2) {
                com.netease.nimlib.log.b.e("QuickConnectLinkCache", String.format("getCurrentLink succeedLinkTtl: %s > %s %s", Long.valueOf(longValue), Long.valueOf(g2), pair));
                com.netease.nimlib.push.net.lbs.c.a().h();
                return false;
            }
        }
        return true;
    }

    private static Pair<String, Integer> d(String str, int i) {
        return new Pair<>(str, Integer.valueOf(i));
    }
}
