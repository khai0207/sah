package com.netease.nimlib.net.b.a;

/* compiled from: DelayTask.java */
/* loaded from: classes.dex */
public abstract class i implements Comparable<i>, Runnable {
    private long a;
    private boolean b;

    public i(long j) {
        this.a = j;
    }

    public long a() {
        return this.a;
    }

    public void b() {
        this.b = true;
    }

    public boolean c() {
        return this.b;
    }

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(i iVar) {
        long j = this.a;
        long j2 = iVar.a;
        if (j == j2) {
            return 0;
        }
        return j > j2 ? 1 : -1;
    }
}
