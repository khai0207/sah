package com.netease.nimlib.n.d;

import com.netease.nimlib.n.d.a.a.b;
import com.netease.nimlib.n.d.a.a.c;
import com.netease.nimlib.n.d.a.a.d;
import com.netease.nimlib.n.d.a.a.e;
import com.netease.nimlib.n.d.a.a.f;
import com.netease.nimlib.n.d.a.a.g;
import com.netease.nimlib.n.d.a.a.h;
import com.netease.nimlib.n.e.i;
import com.netease.nimlib.n.e.j;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: EventModelFilter.java */
/* loaded from: classes.dex */
public class a {
    private static boolean a = true;
    private final List<com.netease.nimlib.n.d.a.a.a> b;
    private final List<b> c;
    private final List<c> d;
    private final List<d> e;
    private final List<e> f;
    private final List<f> g;
    private final List<g> h;
    private final List<h> i;

    private a() {
        this.b = new ArrayList();
        this.c = new ArrayList();
        this.d = new ArrayList();
        this.e = new ArrayList();
        this.f = new ArrayList();
        this.g = new ArrayList();
        this.h = new ArrayList();
        this.i = new ArrayList();
        this.d.add(new com.netease.nimlib.n.d.a.a());
        this.d.add(new com.netease.nimlib.n.d.a.b());
        this.d.add(new com.netease.nimlib.n.d.a.d());
        this.d.add(new com.netease.nimlib.n.d.a.c());
        this.e.add(new com.netease.nimlib.n.d.a.e());
    }

    /* compiled from: EventModelFilter.java */
    /* renamed from: com.netease.nimlib.n.d.a$a, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    private static class C0044a {
        private static final a a = new a();
    }

    public static a a() {
        return C0044a.a;
    }

    public static void a(boolean z) {
        a = z;
    }

    public boolean a(com.netease.nimlib.apm.b.b<? extends com.netease.nimlib.apm.b.a> bVar) {
        if (!a) {
            return false;
        }
        if (bVar instanceof com.netease.nimlib.n.e.a) {
            return a((com.netease.nimlib.n.e.a) bVar);
        }
        if (bVar instanceof com.netease.nimlib.n.e.b) {
            return a((com.netease.nimlib.n.e.b) bVar);
        }
        if (bVar instanceof com.netease.nimlib.n.e.d) {
            return a((com.netease.nimlib.n.e.d) bVar);
        }
        if (bVar instanceof com.netease.nimlib.n.e.e) {
            return a((com.netease.nimlib.n.e.e) bVar);
        }
        if (bVar instanceof com.netease.nimlib.n.e.f) {
            return a((com.netease.nimlib.n.e.f) bVar);
        }
        if (bVar instanceof com.netease.nimlib.n.e.g) {
            return a((com.netease.nimlib.n.e.g) bVar);
        }
        if (bVar instanceof i) {
            return a((i) bVar);
        }
        if (bVar instanceof j) {
            return a((j) bVar);
        }
        return false;
    }

    public boolean a(String str, Map<String, Object> map) {
        if (!a || str == null || map == null) {
            return false;
        }
        char c = 65535;
        switch (str.hashCode()) {
            case -1403057933:
                if (str.equals("nim_api_trace")) {
                    c = 0;
                    break;
                }
                break;
            case -1314244092:
                if (str.equals("exceptions")) {
                    c = 2;
                    break;
                }
                break;
            case 109266:
                if (str.equals("nos")) {
                    c = 6;
                    break;
                }
                break;
            case 103149417:
                if (str.equals("login")) {
                    c = 3;
                    break;
                }
                break;
            case 223813666:
                if (str.equals("msgReceive")) {
                    c = 4;
                    break;
                }
                break;
            case 941129462:
                if (str.equals("chatroomLogin")) {
                    c = 1;
                    break;
                }
                break;
            case 1343701673:
                if (str.equals("msgSend")) {
                    c = 5;
                    break;
                }
                break;
            case 2028627725:
                if (str.equals("nim_sdk_sync")) {
                    c = 7;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return a(map);
            case 1:
                return b(map);
            case 2:
                return c(map);
            case 3:
                return d(map);
            case 4:
                return e(map);
            case 5:
                return f(map);
            case 6:
                return g(map);
            case 7:
                return h(map);
            default:
                return false;
        }
    }

    private boolean a(com.netease.nimlib.n.e.a aVar) {
        Iterator<com.netease.nimlib.n.d.a.a.a> it = this.b.iterator();
        while (it.hasNext()) {
            if (it.next().a(aVar)) {
                return true;
            }
        }
        return false;
    }

    private boolean a(com.netease.nimlib.n.e.b bVar) {
        Iterator<b> it = this.c.iterator();
        while (it.hasNext()) {
            if (it.next().a(bVar)) {
                return true;
            }
        }
        return false;
    }

    private boolean a(com.netease.nimlib.n.e.d dVar) {
        Iterator<c> it = this.d.iterator();
        while (it.hasNext()) {
            if (it.next().a(dVar)) {
                return true;
            }
        }
        return false;
    }

    private boolean a(com.netease.nimlib.n.e.e eVar) {
        Iterator<d> it = this.e.iterator();
        while (it.hasNext()) {
            if (it.next().a(eVar)) {
                return true;
            }
        }
        return false;
    }

    private boolean a(com.netease.nimlib.n.e.f fVar) {
        Iterator<e> it = this.f.iterator();
        while (it.hasNext()) {
            if (it.next().a(fVar)) {
                return true;
            }
        }
        return false;
    }

    private boolean a(com.netease.nimlib.n.e.g gVar) {
        Iterator<f> it = this.g.iterator();
        while (it.hasNext()) {
            if (it.next().a(gVar)) {
                return true;
            }
        }
        return false;
    }

    private boolean a(i iVar) {
        Iterator<g> it = this.h.iterator();
        while (it.hasNext()) {
            if (it.next().a(iVar)) {
                return true;
            }
        }
        return false;
    }

    private boolean a(j jVar) {
        Iterator<h> it = this.i.iterator();
        while (it.hasNext()) {
            if (it.next().a(jVar)) {
                return true;
            }
        }
        return false;
    }

    private boolean a(Map<String, Object> map) {
        Iterator<com.netease.nimlib.n.d.a.a.a> it = this.b.iterator();
        while (it.hasNext()) {
            if (it.next().a(map)) {
                return true;
            }
        }
        return false;
    }

    private boolean b(Map<String, Object> map) {
        Iterator<b> it = this.c.iterator();
        while (it.hasNext()) {
            if (it.next().a(map)) {
                return true;
            }
        }
        return false;
    }

    private boolean c(Map<String, Object> map) {
        Iterator<c> it = this.d.iterator();
        while (it.hasNext()) {
            if (it.next().a(map)) {
                return true;
            }
        }
        return false;
    }

    private boolean d(Map<String, Object> map) {
        Iterator<d> it = this.e.iterator();
        while (it.hasNext()) {
            if (it.next().a(map)) {
                return true;
            }
        }
        return false;
    }

    private boolean e(Map<String, Object> map) {
        Iterator<e> it = this.f.iterator();
        while (it.hasNext()) {
            if (it.next().a(map)) {
                return true;
            }
        }
        return false;
    }

    private boolean f(Map<String, Object> map) {
        Iterator<f> it = this.g.iterator();
        while (it.hasNext()) {
            if (it.next().a(map)) {
                return true;
            }
        }
        return false;
    }

    private boolean g(Map<String, Object> map) {
        Iterator<g> it = this.h.iterator();
        while (it.hasNext()) {
            if (it.next().a(map)) {
                return true;
            }
        }
        return false;
    }

    private boolean h(Map<String, Object> map) {
        Iterator<h> it = this.i.iterator();
        while (it.hasNext()) {
            if (it.next().a(map)) {
                return true;
            }
        }
        return false;
    }
}
