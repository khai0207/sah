package com.android.pc.ioc.core.kernel;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes.dex */
public class KernelCollection {
    public static <T> T[] toArray(Collection<? extends T> collection, Class<T> cls) {
        if (collection == null) {
            return null;
        }
        T[] tArr = (T[]) ((Object[]) Array.newInstance((Class<?>) cls, collection.size()));
        int i = 0;
        Iterator<? extends T> it = collection.iterator();
        while (it.hasNext()) {
            tArr[i] = it.next();
            i++;
        }
        return tArr;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> T[] castToArray(Collection collection, Class<T> cls) {
        if (collection == null) {
            return null;
        }
        T[] tArr = (T[]) ((Object[]) Array.newInstance((Class<?>) cls, collection.size()));
        int i = 0;
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            tArr[i] = KernelDyna.to(it.next(), cls);
            i++;
        }
        return tArr;
    }

    public static <T> void addAll(Collection<T> collection, T[] tArr) {
        for (T t : tArr) {
            collection.add(t);
        }
    }

    public static void copy(Collection collection, Collection collection2) {
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            collection2.add(it.next());
        }
    }

    public static Map toMap(Collection collection) {
        Object next;
        if (collection == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        Iterator it = collection.iterator();
        while (true) {
            Object obj = null;
            while (it.hasNext()) {
                next = it.next();
                if (obj == null) {
                    obj = next;
                }
            }
            return hashMap;
            hashMap.put(obj, next);
        }
    }
}
