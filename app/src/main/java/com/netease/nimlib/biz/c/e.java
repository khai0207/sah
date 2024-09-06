package com.netease.nimlib.biz.c;

import android.util.SparseArray;
import android.util.SparseIntArray;
import com.netease.nimlib.biz.c.f;
import com.netease.nimlib.biz.c.g;
import u.aly.df;

/* compiled from: ResponseFactory.java */
/* loaded from: classes.dex */
public abstract class e {
    private SparseArray<Class<? extends com.netease.nimlib.biz.e.a>> a = new SparseArray<>();
    private SparseIntArray b = new SparseIntArray();
    private SparseArray<a> c = new SparseArray<>();

    protected static int a(byte b, byte b2) {
        return (b << df.n) | b2;
    }

    protected abstract void a();

    public static e a(boolean z) {
        return z ? f.a.a : g.a.a;
    }

    protected e() {
        a();
    }

    protected void a(Class<? extends com.netease.nimlib.biz.e.a> cls, a aVar) {
        com.netease.nimlib.biz.e.b bVar = (com.netease.nimlib.biz.e.b) cls.getAnnotation(com.netease.nimlib.biz.e.b.class);
        if (bVar == null) {
            return;
        }
        byte a = bVar.a();
        String[] b = bVar.b();
        if (b.length != 0) {
            for (String str : b) {
                String[] split = str.split("#");
                if (split.length != 0) {
                    int a2 = a(a, Byte.parseByte(split[0]));
                    if (split.length >= 2) {
                        this.b.append(a2, Integer.parseInt(split[1]));
                    }
                    this.a.append(a2, cls);
                    if (aVar != null) {
                        this.c.append(a2, aVar);
                    }
                }
            }
        }
    }

    public boolean a(com.netease.nimlib.push.packet.a aVar) {
        return aVar != null && this.a.indexOfKey(e(aVar)) >= 0;
    }

    private Class<? extends com.netease.nimlib.biz.e.a> f(com.netease.nimlib.push.packet.a aVar) {
        SparseArray<Class<? extends com.netease.nimlib.biz.e.a>> sparseArray;
        if (aVar == null || (sparseArray = this.a) == null) {
            return null;
        }
        return sparseArray.get(e(aVar));
    }

    Integer b(com.netease.nimlib.push.packet.a aVar) {
        SparseIntArray sparseIntArray;
        if (aVar == null || (sparseIntArray = this.b) == null) {
            return null;
        }
        return Integer.valueOf(sparseIntArray.get(e(aVar)));
    }

    public a c(com.netease.nimlib.push.packet.a aVar) {
        SparseArray<a> sparseArray;
        if (aVar == null || (sparseArray = this.c) == null) {
            return null;
        }
        return sparseArray.get(e(aVar));
    }

    com.netease.nimlib.biz.e.a d(com.netease.nimlib.push.packet.a aVar) {
        Class<? extends com.netease.nimlib.biz.e.a> f = f(aVar);
        if (f == null) {
            return null;
        }
        try {
            return f.newInstance();
        } catch (IllegalAccessException e) {
            com.netease.nimlib.log.b.e("ResponseFactory", "newResponse is error", e);
            return null;
        } catch (InstantiationException e2) {
            com.netease.nimlib.log.b.e("ResponseFactory", "newResponse is error", e2);
            return null;
        }
    }

    protected static int e(com.netease.nimlib.push.packet.a aVar) {
        return a(aVar.i(), aVar.j());
    }
}
