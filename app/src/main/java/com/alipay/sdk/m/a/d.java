package com.alipay.sdk.m.a;

import java.lang.reflect.Type;

/* loaded from: classes.dex */
public final class d implements i, j {
    @Override // com.alipay.sdk.m.a.j
    public final Object a(Object obj) {
        return ((Enum) obj).name();
    }

    @Override // com.alipay.sdk.m.a.i
    public final Object a(Object obj, Type type) {
        return Enum.valueOf((Class) type, obj.toString());
    }

    @Override // com.alipay.sdk.m.a.i, com.alipay.sdk.m.a.j
    public final boolean a(Class<?> cls) {
        return Enum.class.isAssignableFrom(cls);
    }
}
