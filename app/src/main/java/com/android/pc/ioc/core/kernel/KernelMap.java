package com.android.pc.ioc.core.kernel;

import java.util.Iterator;
import java.util.Map;

/* loaded from: classes.dex */
public abstract class KernelMap {
    public static <K, V> K key(Map<K, V> map) {
        if (map == null) {
            return null;
        }
        Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        if (it.hasNext()) {
            return it.next().getKey();
        }
        return null;
    }

    public static <K, V> V value(Map<K, V> map) {
        if (map == null) {
            return null;
        }
        Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        if (it.hasNext()) {
            return it.next().getValue();
        }
        return null;
    }

    public static <K, V> Map.Entry<K, V> entry(Map<K, V> map) {
        if (map == null) {
            return null;
        }
        Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        if (it.hasNext()) {
            return it.next();
        }
        return null;
    }

    public static <V> V get(Map<?, V> map, Object obj) {
        if (obj == null) {
            return null;
        }
        return map.get(obj);
    }

    public static void copy(Map<Object, Object> map, Map<Object, Object> map2) {
        for (Map.Entry<Object, Object> entry : map.entrySet()) {
            map2.put(entry.getKey(), entry.getValue());
        }
    }
}
