package com.netease.nimlib.i;

import android.os.Handler;
import com.netease.nimlib.sdk.Observer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: ObserverManager.java */
/* loaded from: classes.dex */
public final class e {
    private final Map<String, List<Observer>> a = new ConcurrentHashMap();
    private final c b;

    e(Handler handler) {
        this.b = new c(handler);
    }

    public boolean a(k kVar) {
        Object[] g;
        if ((kVar.c().getDeclaringClass().getAnnotation(d.class) == null && kVar.c().getAnnotation(d.class) == null) || (g = kVar.g()) == null || g.length != 2 || !(g[0] instanceof Observer) || !(g[1] instanceof Boolean)) {
            return false;
        }
        Observer observer = (Observer) g[0];
        if (((Boolean) g[1]).booleanValue()) {
            a(kVar.d(), observer);
            this.b.a(kVar.d(), observer);
        } else {
            b(kVar.d(), observer);
        }
        return true;
    }

    boolean b(k kVar) {
        ArrayList arrayList;
        List<Observer> list = this.a.get(kVar.d());
        if (list == null) {
            return false;
        }
        synchronized (list) {
            arrayList = new ArrayList(list);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((Observer) it.next()).onEvent(kVar.g()[0]);
        }
        return true;
    }

    private void a(String str, Observer observer) {
        List<Observer> list = this.a.get(str);
        if (list == null) {
            list = new ArrayList<>();
            this.a.put(str, list);
        }
        synchronized (list) {
            if (list.contains(observer)) {
                return;
            }
            list.add(observer);
            a(true, str);
        }
    }

    private void b(String str, Observer observer) {
        List<Observer> list = this.a.get(str);
        if (list != null) {
            synchronized (list) {
                list.remove(observer);
            }
            a(false, str);
        }
    }

    private void a(boolean z, String str) {
        StringBuilder sb = new StringBuilder("ObserverManager ");
        sb.append(z ? "add " : "remove ");
        sb.append(str);
        com.netease.nimlib.log.b.N(sb.toString());
    }
}
