package com.netease.nimlib.i;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/* compiled from: ProxyHandler.java */
/* loaded from: classes.dex */
class f implements InvocationHandler {
    f() {
    }

    @Override // java.lang.reflect.InvocationHandler
    public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
        com.netease.nimlib.a.a aVar;
        int i = 0;
        boolean z = method.getReturnType().equals(Void.TYPE) || method.getReturnType().equals(Void.class);
        boolean z2 = (z || method.getReturnType().isAssignableFrom(m.class)) ? false : true;
        if (!z2 && (aVar = (com.netease.nimlib.a.a) method.getAnnotation(com.netease.nimlib.a.a.class)) != null) {
            i = aVar.a();
        }
        k kVar = new k();
        kVar.a(objArr).a(method).a(z2).b(i).a().b(z);
        Object a2 = com.netease.nimlib.i.a.a(kVar);
        return z2 ? a.b(method.getReturnType(), a2) : a2;
    }

    /* compiled from: ProxyHandler.java */
    /* loaded from: classes.dex */
    private static class a {
        private static final Map<Class, Object> a;

        static {
            HashMap hashMap = new HashMap(7);
            a = hashMap;
            hashMap.put(Integer.TYPE, 0);
            a.put(Long.TYPE, 0L);
            a.put(Boolean.TYPE, false);
            a.put(Byte.TYPE, (byte) 0);
            a.put(Float.TYPE, Float.valueOf(0.0f));
            a.put(Double.TYPE, Double.valueOf(0.0d));
            a.put(Character.TYPE, (char) 0);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Object b(Class cls, Object obj) {
            return (obj == null && a.containsKey(cls)) ? a.get(cls) : obj;
        }
    }
}
