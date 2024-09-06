package com.netease.nimlib.i;

import android.os.Handler;
import android.os.Looper;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: Transaction.java */
/* loaded from: classes.dex */
public class k {
    private boolean b;
    private int c;
    private transient Looper d;
    private boolean h;
    private transient int g = 0;
    private int a = a.a();
    private transient b e = new b();
    private transient c f = new c();

    public k a(String str) {
        this.e.b = str;
        return this;
    }

    public k a(Method method) {
        this.e.a = method;
        this.e.b = e() + "/" + f();
        return this;
    }

    public k a(Object[] objArr) {
        this.e.c = objArr;
        return this;
    }

    public k a(int i) {
        this.f.a = i;
        return this;
    }

    public k a(Object obj) {
        this.f.b = obj;
        return this;
    }

    public k a(boolean z) {
        this.b = z;
        return this;
    }

    public k b(boolean z) {
        this.h = z;
        return this;
    }

    public k b(int i) {
        this.c = i;
        return this;
    }

    public k a(Throwable th) {
        this.f.a = 1000;
        this.f.b = th;
        return this;
    }

    public k b(Object obj) {
        this.f.a = 200;
        this.f.b = obj;
        return this;
    }

    public k c(int i) {
        this.g = i;
        return this;
    }

    k a() {
        if (!this.b) {
            this.d = Looper.myLooper();
        }
        return this;
    }

    public void b() {
        com.netease.nimlib.i.a.c(this);
    }

    public Method c() {
        return this.e.a;
    }

    public String d() {
        return this.e.b;
    }

    public String e() {
        return this.e.a.getDeclaringClass().getSimpleName();
    }

    public String f() {
        return this.e.a.getName();
    }

    public Object[] g() {
        return this.e.c;
    }

    public int h() {
        return this.a;
    }

    public int i() {
        return this.f.a;
    }

    public Object j() {
        return this.f.b;
    }

    public boolean k() {
        return this.b;
    }

    public boolean l() {
        return this.h;
    }

    public int m() {
        return this.c;
    }

    Handler n() {
        Looper looper = this.d;
        Handler handler = (looper == null || !looper.getThread().isAlive()) ? null : new Handler(this.d);
        this.d = null;
        return handler;
    }

    public int o() {
        int p = p();
        q();
        return p;
    }

    private int p() {
        return this.g;
    }

    private void q() {
        this.g--;
    }

    public String toString() {
        return String.format("Transaction: [id: %s, sync: %s, priority: %s,  %s%s]", Integer.valueOf(this.a), Boolean.valueOf(this.b), Integer.valueOf(this.c), this.e, this.f);
    }

    /* compiled from: Transaction.java */
    /* loaded from: classes.dex */
    private static final class a {
        private static AtomicInteger a = new AtomicInteger(0);

        public static int a() {
            return a.incrementAndGet();
        }
    }

    /* compiled from: Transaction.java */
    /* loaded from: classes.dex */
    static final class b {
        Method a;
        String b;
        Object[] c;

        b() {
        }

        public String toString() {
            return " method: " + this.b;
        }
    }

    /* compiled from: Transaction.java */
    /* loaded from: classes.dex */
    static final class c {
        int a;
        Object b;

        c() {
        }

        public String toString() {
            if (this.a == 0) {
                return "";
            }
            return ", result: " + this.a;
        }
    }
}
