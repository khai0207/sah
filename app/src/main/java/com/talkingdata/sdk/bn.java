package com.talkingdata.sdk;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: td */
/* loaded from: classes.dex */
final class bn {
    private static final Map a = new ConcurrentHashMap();

    private static void a(Class cls) {
        try {
            HashMap hashMap = new HashMap();
            for (Method method : cls.getDeclaredMethods()) {
                if (method.getName().startsWith("onTDEBEvent") && method.getParameterTypes().length == 1) {
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    if (parameterTypes.length != 1) {
                        an.eForInternal("Method " + method + " must have one and only one argument.");
                    }
                    Class<?> cls2 = parameterTypes[0];
                    if (cls2.isInterface()) {
                        an.eForInternal("Method " + method + " must have a argument whose type is a class which can be instantialized.");
                    }
                    if ((method.getModifiers() & 1) == 0) {
                        an.eForInternal("Method " + method + " must be 'public'.");
                    }
                    Set set = (Set) hashMap.get(cls2);
                    if (set == null) {
                        set = new HashSet();
                        hashMap.put(cls2, set);
                    }
                    set.add(method);
                }
            }
            a.put(cls, hashMap);
        } catch (Throwable th) {
            ce.postSDKError(th);
        }
    }

    static Map a(Object obj) {
        HashMap hashMap = new HashMap();
        try {
            Class<?> cls = obj.getClass();
            if (!a.containsKey(cls)) {
                a((Class) cls);
            }
            Map map = (Map) a.get(cls);
            if (map != null && !map.isEmpty()) {
                for (Map.Entry entry : map.entrySet()) {
                    HashSet hashSet = new HashSet();
                    Iterator it = ((Set) entry.getValue()).iterator();
                    while (it.hasNext()) {
                        hashSet.add(new bo(obj, (Method) it.next()));
                    }
                    hashMap.put(entry.getKey(), hashSet);
                }
            }
        } catch (Throwable th) {
            ce.postSDKError(th);
        }
        return hashMap;
    }

    private bn() {
    }
}
