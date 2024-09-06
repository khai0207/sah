package com.netease.nimlib.net.trace.a;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;

/* compiled from: EvictingQueue.java */
/* loaded from: classes.dex */
public final class a<E> implements Serializable {
    private static final long serialVersionUID = 0;
    final int a;
    private final ArrayDeque<E> b;

    private a(int i) {
        b.a(i >= 0, "maxSize (%s) must >= 0", i);
        this.b = new ArrayDeque<>(i);
        this.a = i;
    }

    public static <E> a<E> a(int i) {
        return new a<>(i);
    }

    public int a() {
        return this.a - c();
    }

    protected Queue<E> b() {
        return this.b;
    }

    public int c() {
        return b().size();
    }

    public boolean a(E e) {
        b.a(e);
        if (this.a == 0) {
            return true;
        }
        if (c() == this.a) {
            this.b.remove();
        }
        this.b.add(e);
        return true;
    }

    public E d() {
        return this.b.peek();
    }

    public Iterator<E> e() {
        return this.b.iterator();
    }

    public boolean b(Object obj) {
        return b().remove(b.a(obj));
    }
}
