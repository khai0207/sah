package com.netease.nimlib.session;

import com.netease.nimlib.net.a.b.a;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: MessageManager.java */
/* loaded from: classes.dex */
public class d {
    private static d e = new d();
    private Set<String> a = Collections.synchronizedSet(new HashSet());
    private Set<String> b = Collections.synchronizedSet(new HashSet());
    private ConcurrentHashMap<String, a.c> c = new ConcurrentHashMap<>();
    private i d = new i();

    private d() {
    }

    public static d a() {
        return e;
    }

    public void b() {
        com.netease.nimlib.net.a.a.g.a().b();
        if (com.netease.nimlib.net.a.b.a.e()) {
            com.netease.nimlib.net.a.b.a.a().b();
        }
        com.netease.nimlib.l.d.a();
        this.a.clear();
        this.b.clear();
    }

    public void a(String str) {
        this.a.add(str);
    }

    public void b(String str) {
        this.a.remove(str);
    }

    public boolean c(String str) {
        return this.a.contains(str);
    }

    public void d(String str) {
        this.b.add(str);
    }

    public void e(String str) {
        this.b.remove(str);
    }

    public boolean f(String str) {
        return this.b.contains(str);
    }

    public i c() {
        return this.d;
    }

    public void a(String str, a.c cVar) {
        this.c.put(str, cVar);
    }

    public a.c g(String str) {
        return this.c.remove(str);
    }

    public boolean h(String str) {
        return this.c.containsKey(str);
    }
}
