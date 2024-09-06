package com.netease.nimlib.o;

import java.util.ArrayList;
import java.util.List;

/* compiled from: CapacityLimitedQueue.java */
/* loaded from: classes.dex */
public class e<T> {
    private e<T>.a a;
    private e<T>.a b;
    private int c = 0;
    private final int d;

    public e(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("capacity invalid");
        }
        this.d = i;
    }

    /* compiled from: CapacityLimitedQueue.java */
    /* loaded from: classes.dex */
    private class a {
        T a;
        e<T>.a b;

        private a() {
        }
    }

    public void a(T t) {
        if (c() == this.d) {
            a();
            b(t);
        } else {
            b(t);
        }
    }

    private void b(T t) {
        int i = this.c;
        if (i == 0) {
            e<T>.a aVar = new a();
            this.a = aVar;
            aVar.a = t;
            this.b = this.a;
            this.c++;
            return;
        }
        if (i > 0) {
            e<T>.a aVar2 = new a();
            aVar2.a = t;
            this.b.b = aVar2;
            this.b = aVar2;
            this.c++;
        }
    }

    public T a() {
        if (this.c == 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        e<T>.a aVar = this.a;
        this.a = aVar.b;
        this.c--;
        return aVar.a;
    }

    public void b() {
        while (c() != 0) {
            a();
        }
    }

    public int c() {
        return this.c;
    }

    public List<T> d() {
        ArrayList arrayList = new ArrayList(c());
        for (e<T>.a aVar = this.a; aVar != null; aVar = aVar.b) {
            arrayList.add(aVar.a);
        }
        return arrayList;
    }
}
