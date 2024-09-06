package com.netease.nimlib.i;

import java.lang.reflect.Proxy;

/* compiled from: ProxyServiceFactory.java */
/* loaded from: classes.dex */
public final class g {
    static Object a(Class<?> cls) {
        return Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new f());
    }
}
