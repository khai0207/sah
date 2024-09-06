package com.android.pc.ioc.db.sqlite;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes.dex */
public class DoubleKeyValueMap<K1, K2, V> {
    private ConcurrentHashMap<K1, ConcurrentHashMap<K2, V>> k1_k2V_map = new ConcurrentHashMap<>();

    public void put(K1 k1, K2 k2, V v) {
        if (this.k1_k2V_map.containsKey(k1)) {
            this.k1_k2V_map.get(k1).put(k2, v);
            return;
        }
        ConcurrentHashMap<K2, V> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put(k2, v);
        this.k1_k2V_map.put(k1, concurrentHashMap);
    }

    public Set<K1> getFirstKeys() {
        return this.k1_k2V_map.keySet();
    }

    public V get(K1 k1, K2 k2) {
        ConcurrentHashMap<K2, V> concurrentHashMap = this.k1_k2V_map.get(k1);
        if (concurrentHashMap == null) {
            return null;
        }
        return concurrentHashMap.get(k2);
    }

    public ConcurrentHashMap<K2, V> get(K1 k1) {
        return this.k1_k2V_map.get(k1);
    }

    public boolean containsKey(K1 k1, K2 k2) {
        if (this.k1_k2V_map.containsKey(k1)) {
            return this.k1_k2V_map.get(k1).containsKey(k2);
        }
        return false;
    }

    public boolean containsKey(K1 k1) {
        return this.k1_k2V_map.containsKey(k1);
    }

    public void clear() {
        if (this.k1_k2V_map.size() > 0) {
            Iterator<ConcurrentHashMap<K2, V>> it = this.k1_k2V_map.values().iterator();
            while (it.hasNext()) {
                it.next().clear();
            }
            this.k1_k2V_map.clear();
        }
    }
}
