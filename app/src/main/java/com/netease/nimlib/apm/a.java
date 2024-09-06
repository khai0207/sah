package com.netease.nimlib.apm;

import android.content.Context;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: NimEventReporter.java */
/* loaded from: classes.dex */
public class a {
    private static Map<String, com.netease.nimlib.apm.b.b<? extends com.netease.nimlib.apm.b.a>> a = new ConcurrentHashMap();
    private static com.netease.nimlib.apm.c.a b = null;
    private static List<String> c = new LinkedList();
    private static final Object d = new Object();

    public static void a(Context context) {
        com.netease.nimlib.apm.event.a.b().a(context);
    }

    public static void a(com.netease.nimlib.apm.c.a aVar) {
        b = aVar;
    }

    public static void a(Map<String, String> map, Map<String, Object> map2) {
        com.netease.nimlib.apm.event.a.c().a(map);
        com.netease.nimlib.apm.event.a.c().b(map2);
    }

    public static void a(String str, Object obj) {
        Map<String, Object> c2 = com.netease.nimlib.apm.event.a.c().c();
        if (c2 == null) {
            return;
        }
        c2.put(str, obj);
    }

    public static void a(Map<String, Object> map) {
        Map<String, Object> c2 = com.netease.nimlib.apm.event.a.c().c();
        if (c2 == null) {
            return;
        }
        for (String str : map.keySet()) {
            c2.put(str, map.get(str));
        }
    }

    public static void a(com.netease.nimlib.apm.event.c.b bVar) {
        com.netease.nimlib.apm.event.a.c().a(bVar);
    }

    public static void a() {
        com.netease.nimlib.apm.event.a.b().d();
    }

    public static <T extends com.netease.nimlib.apm.b.a> void a(com.netease.nimlib.apm.b.b<T> bVar, T t) {
        if (bVar == null || t == null) {
            return;
        }
        com.netease.nimlib.log.b.G("updateTrackEventExtension eventKey = " + bVar.o() + ",extension = " + t.d());
        List<T> l = bVar.l();
        if (l == null) {
            l = new ArrayList<>();
            bVar.a(l);
        }
        if (l.size() == 0) {
            l.add(t);
            return;
        }
        for (T t2 : l) {
            if (t2 != null && t2.a(t)) {
                return;
            }
        }
        l.add(t);
    }

    public static <T extends com.netease.nimlib.apm.b.a> void a(com.netease.nimlib.apm.b.b<T> bVar, com.netease.nimlib.apm.b.b<T> bVar2) {
        if (bVar == null) {
            return;
        }
        bVar.a(bVar2.d());
        bVar.c(bVar2.f());
        bVar.b(bVar2.h());
        List<T> l = bVar.l();
        if (l == null) {
            l = new ArrayList<>();
            bVar.a(l);
        }
        List<T> l2 = bVar2.l();
        if (l2 != null) {
            ArrayList arrayList = new ArrayList();
            for (T t : l2) {
                boolean z = false;
                Iterator<T> it = l.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    T next = it.next();
                    if (next != null && next.a(t)) {
                        z = true;
                        break;
                    }
                }
                if (!z) {
                    arrayList.add(t);
                }
            }
            if (arrayList.size() > 0) {
                l.addAll(arrayList);
            }
        }
    }

    public static void a(String str, com.netease.nimlib.apm.b.b<? extends com.netease.nimlib.apm.b.a> bVar) {
        if (bVar == null) {
            return;
        }
        if (com.netease.nimlib.n.d.a.a().a(bVar)) {
            com.netease.nimlib.log.b.G("filter recordEvent eventKey = " + str + ", map = " + bVar.m());
            return;
        }
        Map<String, Object> m = bVar.m();
        com.netease.nimlib.log.b.G("recordEvent eventKey = " + str + ",eventModel = " + m);
        com.netease.nimlib.apm.event.a.b().a(str, m, bVar.n());
    }

    public static void b() {
        com.netease.nimlib.apm.event.a.b().e();
    }

    public static void c() {
        com.netease.nimlib.apm.event.a.b().f();
    }
}
