package com.netease.nimlib.sdk.util;

import defpackage.C$r8$backportedMethods$utility$Objects$2$equals;
import java.io.Serializable;

/* loaded from: classes.dex */
public class Entry<K extends Serializable, V extends Serializable> implements Serializable {
    public final K key;
    public final V value;

    public Entry(K k, V v) {
        this.key = k;
        this.value = v;
    }

    public int hashCode() {
        K k = this.key;
        int hashCode = k == null ? 0 : k.hashCode();
        V v = this.value;
        return hashCode ^ (v != null ? v.hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Entry)) {
            return false;
        }
        Entry entry = (Entry) obj;
        return C$r8$backportedMethods$utility$Objects$2$equals.equals(this.key, entry.key) && C$r8$backportedMethods$utility$Objects$2$equals.equals(this.value, entry.value);
    }

    public static <A extends Serializable, B extends Serializable> Entry<A, B> create(A a, B b) {
        return new Entry<>(a, b);
    }
}
