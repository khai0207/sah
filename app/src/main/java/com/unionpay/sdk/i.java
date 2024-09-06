package com.unionpay.sdk;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
final class i {
    private static final Map a = new HashMap();
    private static final Map b = new HashMap();

    static Map a(Object obj) {
        Class<?> cls = obj.getClass();
        HashMap hashMap = new HashMap();
        if (!a.containsKey(cls)) {
            a((Class) cls);
        }
        Map map = (Map) a.get(cls);
        if (!map.isEmpty()) {
            for (Map.Entry entry : map.entrySet()) {
                hashMap.put(entry.getKey(), new m(obj, (Method) entry.getValue()));
            }
        }
        return hashMap;
    }

    private static void a(Class cls) {
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        for (Method method : cls.getDeclaredMethods()) {
            if (!method.isBridge()) {
                if (method.isAnnotationPresent(OttoSubscribe.class)) {
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    if (parameterTypes.length != 1) {
                        throw new IllegalArgumentException("Method " + method + " has @Subscribe annotation but requires " + parameterTypes.length + " arguments.  Methods must require a single argument.");
                    }
                    Class<?> cls2 = parameterTypes[0];
                    if (cls2.isInterface()) {
                        throw new IllegalArgumentException("Method " + method + " has @Subscribe annotation on " + cls2 + " which is an interface.  Subscription must be on a concrete class type.");
                    }
                    if ((1 & method.getModifiers()) == 0) {
                        throw new IllegalArgumentException("Method " + method + " has @Subscribe annotation on " + cls2 + " but is not 'public'.");
                    }
                    Set set = (Set) hashMap.get(cls2);
                    if (set == null) {
                        set = new HashSet();
                        hashMap.put(cls2, set);
                    }
                    set.add(method);
                } else if (method.isAnnotationPresent(OttoProduce.class)) {
                    Class<?>[] parameterTypes2 = method.getParameterTypes();
                    if (parameterTypes2.length != 0) {
                        throw new IllegalArgumentException("Method " + method + "has @Produce annotation but requires " + parameterTypes2.length + " arguments.  Methods must require zero arguments.");
                    }
                    if (method.getReturnType() == Void.class) {
                        throw new IllegalArgumentException("Method " + method + " has a return type of void.  Must declare a non-void type.");
                    }
                    Class<?> returnType = method.getReturnType();
                    if (returnType.isInterface()) {
                        throw new IllegalArgumentException("Method " + method + " has @Produce annotation on " + returnType + " which is an interface.  Producers must return a concrete class type.");
                    }
                    if (returnType.equals(Void.TYPE)) {
                        throw new IllegalArgumentException("Method " + method + " has @Produce annotation but has no return type.");
                    }
                    if ((1 & method.getModifiers()) == 0) {
                        throw new IllegalArgumentException("Method " + method + " has @Produce annotation on " + returnType + " but is not 'public'.");
                    }
                    if (hashMap2.containsKey(returnType)) {
                        throw new IllegalArgumentException("Producer for type " + returnType + " has already been registered.");
                    }
                    hashMap2.put(returnType, method);
                } else {
                    continue;
                }
            }
        }
        a.put(cls, hashMap2);
        b.put(cls, hashMap);
    }

    static Map b(Object obj) {
        Class<?> cls = obj.getClass();
        HashMap hashMap = new HashMap();
        if (!b.containsKey(cls)) {
            a((Class) cls);
        }
        Map map = (Map) b.get(cls);
        if (!map.isEmpty()) {
            for (Map.Entry entry : map.entrySet()) {
                HashSet hashSet = new HashSet();
                Iterator it = ((Set) entry.getValue()).iterator();
                while (it.hasNext()) {
                    hashSet.add(new l(obj, (Method) it.next()));
                }
                hashMap.put(entry.getKey(), hashSet);
            }
        }
        return hashMap;
    }
}
