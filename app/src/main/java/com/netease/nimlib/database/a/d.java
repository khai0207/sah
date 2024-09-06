package com.netease.nimlib.database.a;

import java.util.ArrayList;
import java.util.List;

/* compiled from: Table.java */
/* loaded from: classes.dex */
public class d {
    private final String a;
    private final boolean b;
    private final List<a> c;

    public d(String str) {
        this(str, true);
    }

    public d(String str, boolean z) {
        this.c = new ArrayList();
        this.a = str;
        this.b = z;
    }

    public String a() {
        return this.a;
    }

    public boolean b() {
        return this.b;
    }

    public List<a> c() {
        return this.c;
    }

    public final d a(a aVar) {
        this.c.add(aVar);
        return this;
    }

    /* compiled from: Table.java */
    /* loaded from: classes.dex */
    public static abstract class a {
        protected final int a;

        public abstract String[] a();

        public abstract String[] a(a aVar);

        public a(int i) {
            this.a = i;
        }

        public int b() {
            return this.a;
        }

        public String toString() {
            return Integer.toString(this.a);
        }
    }
}
