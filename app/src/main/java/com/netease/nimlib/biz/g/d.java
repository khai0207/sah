package com.netease.nimlib.biz.g;

import android.os.Handler;
import android.util.SparseArray;
import com.netease.nimlib.biz.c.h;
import com.netease.nimlib.n.e;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: SendTaskManager.java */
/* loaded from: classes.dex */
public class d implements h {
    private final SparseArray<a> a = new SparseArray<>();
    private final int b = 0;
    private final int c = 1;
    private AtomicInteger d = new AtomicInteger(0);
    private Handler e = com.netease.nimlib.c.b.a.b(com.netease.nimlib.c.e());

    public void c() {
    }

    public void a() {
        if (!this.d.compareAndSet(0, 1)) {
        }
    }

    public void b() {
        if (this.d.compareAndSet(1, 0)) {
            synchronized (this.a) {
                this.a.clear();
            }
        }
    }

    public boolean a(c cVar) {
        if (!a("pend task") || !cVar.d()) {
            return false;
        }
        a(new a(cVar));
        return true;
    }

    public com.netease.nimlib.biz.d.a c(com.netease.nimlib.biz.e.a aVar) {
        a d;
        if (a("retrieve request") && (d = d(aVar)) != null) {
            return d.a.b();
        }
        return null;
    }

    @Override // com.netease.nimlib.biz.c.h
    public boolean a(com.netease.nimlib.biz.e.a aVar) {
        if (!a("pre process")) {
            return false;
        }
        synchronized (this.a) {
            a d = d(aVar);
            if (d == null) {
                return false;
            }
            this.e.removeCallbacks(d);
            return true;
        }
    }

    @Override // com.netease.nimlib.biz.c.h
    public boolean b(com.netease.nimlib.biz.e.a aVar) {
        if (!a("on processed")) {
            return false;
        }
        a d = d(aVar);
        if (d != null && d.a != null) {
            d.a.a(aVar);
        }
        e.a(aVar.j(), aVar.r(), com.netease.nimlib.n.b.b.kSendAwaitablePacket);
        return a(aVar.p(), aVar) != null;
    }

    public void d() {
        if (a("onLogin")) {
            List<a> f = f();
            com.netease.nimlib.log.b.d("TaskMgr", "onLogin, dump pending tasks count=" + f.size());
            Iterator<a> it = f.iterator();
            while (it.hasNext()) {
                a(it.next(), true);
            }
        }
    }

    private void a(a aVar) {
        synchronized (this.a) {
            int b = aVar.b();
            a a2 = a(b);
            if (a2 != null) {
                com.netease.nimlib.log.b.d("TaskMgr", "exist same old TimeoutTask , remove it");
                this.e.removeCallbacks(a2);
            }
            this.a.put(b, aVar);
            this.e.postDelayed(aVar, aVar.a() * 1000);
        }
    }

    private a d(com.netease.nimlib.biz.e.a aVar) {
        synchronized (this.a) {
            a aVar2 = this.a.get(aVar.j().k());
            if (aVar2 == null || aVar2.a == null || aVar2.a.b().b() != aVar.j().i() || aVar2.a.b().c() != aVar.q()) {
                return null;
            }
            return aVar2;
        }
    }

    private a a(int i) {
        a aVar;
        synchronized (this.a) {
            aVar = this.a.get(i);
        }
        return aVar;
    }

    private a a(int i, com.netease.nimlib.biz.e.a aVar) {
        a aVar2;
        synchronized (this.a) {
            aVar2 = this.a.get(i);
            if (aVar2 != null) {
                c cVar = aVar2.a;
                if (aVar != null && cVar != null && (cVar.b().b() != aVar.j().i() || cVar.b().c() != aVar.q())) {
                    aVar2 = null;
                }
                this.a.remove(i);
            }
        }
        if (aVar2 != null) {
            this.e.removeCallbacks(aVar2);
        }
        return aVar2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(a aVar, boolean z) {
        if (aVar == null) {
            return;
        }
        com.netease.nimlib.log.b.d("TaskMgr", "onTimeout, key=" + aVar.b() + ", onLogin=" + z);
        if (a("onTimeout")) {
            if (aVar.c()) {
                com.netease.nimlib.log.b.d("TaskMgr", "onTimeout, task is removed, key=" + aVar.b());
                return;
            }
            int b = aVar.b();
            a a2 = a(b);
            if (a2 == null) {
                com.netease.nimlib.log.b.d("TaskMgr", "onTimeout, when timeout, maybe has been revoked");
                return;
            }
            if (z || a2.a.c()) {
                a2 = a(b, (com.netease.nimlib.biz.e.a) null);
            }
            if (a2 != null) {
                if (z) {
                    a2.a.g();
                } else {
                    a2.a.f();
                }
            }
        }
    }

    /* compiled from: SendTaskManager.java */
    /* loaded from: classes.dex */
    private class a implements Runnable {
        c a;
        private boolean c = false;

        a(c cVar) {
            this.a = cVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            d.this.a(this, false);
        }

        public int a() {
            return this.a.e();
        }

        public int b() {
            return this.a.b().i().k();
        }

        public boolean c() {
            return this.c;
        }

        public void a(boolean z) {
            this.c = z;
        }
    }

    private List<a> f() {
        ArrayList arrayList = new ArrayList();
        synchronized (this.a) {
            for (int i = 0; i < this.a.size(); i++) {
                arrayList.add(this.a.valueAt(i));
            }
        }
        return arrayList;
    }

    public List<c> e() {
        ArrayList arrayList = new ArrayList();
        synchronized (this.a) {
            for (int i = 0; i < this.a.size(); i++) {
                a valueAt = this.a.valueAt(i);
                if (valueAt != null && !valueAt.c()) {
                    valueAt.a(true);
                    this.e.removeCallbacks(valueAt);
                    c cVar = valueAt.a;
                    if (cVar != null) {
                        arrayList.add(cVar);
                    }
                }
            }
        }
        com.netease.nimlib.log.b.d("TaskMgr", "breakPendingTasks, pending tasks count=" + arrayList.size());
        return arrayList;
    }

    private boolean a(String str) {
        boolean z = this.d.get() == 1;
        if (!z) {
            com.netease.nimlib.log.b.d("TaskMgr", str + " while not running");
        }
        return z;
    }
}
