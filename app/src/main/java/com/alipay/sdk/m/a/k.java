package com.alipay.sdk.m.a;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes.dex */
public final class k implements i {
    @Override // com.alipay.sdk.m.a.i
    public final Object a(Object obj, Type type) {
        if (!obj.getClass().equals(org.json.alipay.a.class)) {
            return null;
        }
        org.json.alipay.a aVar = (org.json.alipay.a) obj;
        HashSet hashSet = new HashSet();
        Class cls = type instanceof ParameterizedType ? ((ParameterizedType) type).getActualTypeArguments()[0] : Object.class;
        for (int i = 0; i < aVar.a(); i++) {
            hashSet.add(e.a(aVar.a(i), cls));
        }
        return hashSet;
    }

    @Override // com.alipay.sdk.m.a.i, com.alipay.sdk.m.a.j
    public final boolean a(Class<?> cls) {
        return Set.class.isAssignableFrom(cls);
    }
}
