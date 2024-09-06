package com.netease.nimlib.o;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/* compiled from: LruList.java */
/* loaded from: classes.dex */
public class l<E> {
    private int a;
    private LinkedList<E> b = new LinkedList<>();

    public l(int i) {
        this.a = i;
    }

    public void a(E e) {
        if (e == null) {
            return;
        }
        int indexOf = this.b.indexOf(e);
        if (indexOf >= 0) {
            this.b.remove(indexOf);
        }
        this.b.addFirst(e);
        while (this.b.size() > this.a) {
            this.b.removeLast();
        }
    }

    public void a() {
        this.b.clear();
    }

    public List<E> b() {
        return new ArrayList(this.b);
    }

    public int c() {
        return this.b.size();
    }
}
