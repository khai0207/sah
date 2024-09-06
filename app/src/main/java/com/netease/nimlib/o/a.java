package com.netease.nimlib.o;

/* compiled from: ArrayUtil.java */
/* loaded from: classes.dex */
public class a {

    /* compiled from: ArrayUtil.java */
    /* renamed from: com.netease.nimlib.o.a$a, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public interface InterfaceC0051a<T, S> {
        S transform(T t);
    }

    public static <T> void a(T[] tArr, InterfaceC0051a<T, T> interfaceC0051a) {
        if (tArr == null || interfaceC0051a == null) {
            return;
        }
        for (int i = 0; i < tArr.length; i++) {
            tArr[i] = interfaceC0051a.transform(tArr[i]);
        }
    }
}
