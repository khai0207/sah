package com.netease.nimlib.c.a;

import android.os.Handler;
import com.netease.nimlib.h;
import com.netease.nimlib.log.b;
import com.netease.nimlib.o.y;
import com.netease.nimlib.sdk.StatusCode;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: FrequencyControlNotifierBase.java */
/* loaded from: classes.dex */
public abstract class a<T> {
    private final int a;
    private final String b;
    private final int c;
    private Handler g;
    private List<T> d = new CopyOnWriteArrayList();
    private long e = 0;
    private boolean f = false;
    private Runnable h = new Runnable() { // from class: com.netease.nimlib.c.a.a.2
        @Override // java.lang.Runnable
        public void run() {
            a.this.c().removeCallbacks(a.this.h);
            b.d(a.this.b, "!! doNotify delay. remove cb, cache size=" + a.this.d.size());
            synchronized (h.class) {
                if (h.e() != StatusCode.UNLOGIN) {
                    a.this.b();
                }
            }
            a.this.f = false;
        }
    };

    public abstract void a(List<T> list);

    protected a(int i, String str, int i2) {
        this.a = i;
        this.b = str;
        this.c = i2;
    }

    public void a() {
        c().removeCallbacks(this.h);
        this.g = null;
        this.d.clear();
        this.e = 0L;
        this.f = false;
    }

    public void b(final List<T> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        c().post(new Runnable() { // from class: com.netease.nimlib.c.a.a.1
            @Override // java.lang.Runnable
            public void run() {
                a.this.d.addAll(list);
                long a = y.a();
                if (a - a.this.e >= a.this.a || a.this.d.size() >= a.this.c) {
                    if (a.this.f) {
                        b.c(a.this.b, "-- remove notifyRunnable");
                        a.this.c().removeCallbacks(a.this.h);
                        a.this.f = false;
                    }
                    b.d(a.this.b, "doNotify immediately, cache size=" + a.this.d.size() + ", time=" + (a - a.this.e) + "ms");
                    a.this.b();
                    return;
                }
                if (!a.this.f) {
                    b.c(a.this.b, "++ add notifyRunnable, post delay, cache size=" + a.this.d.size());
                    a.this.c().postDelayed(a.this.h, (long) a.this.a);
                    a.this.f = true;
                    return;
                }
                b.c(a.this.b, "       came new objects, cache size=" + a.this.d.size());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        ArrayList arrayList = new ArrayList();
        int size = this.d.size();
        int i = this.c;
        if (size <= i) {
            arrayList.addAll(this.d);
            this.d.clear();
        } else {
            arrayList.addAll(this.d.subList(0, i));
            List<T> list = this.d;
            this.d = list.subList(this.c, list.size());
        }
        b.d(this.b, "ready to doNotify, finally objects.size() = " + arrayList.size() + ", cacheObjects.size() = " + this.d.size());
        if (arrayList.isEmpty()) {
            return;
        }
        try {
            a(arrayList);
        } catch (Throwable th) {
            b.e(this.b, "doNotify error:" + th, th);
        }
        this.e = y.a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Handler c() {
        if (this.g == null) {
            this.g = com.netease.nimlib.c.b.a.c().a(this.b);
        }
        return this.g;
    }
}
