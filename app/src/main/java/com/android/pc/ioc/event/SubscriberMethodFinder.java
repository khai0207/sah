package com.android.pc.ioc.event;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes.dex */
class SubscriberMethodFinder {
    private static final Map<String, List<SubscriberMethod>> methodCache = new HashMap();
    private static final Map<Class<?>, Class<?>> skipMethodNameVerificationForClasses = new ConcurrentHashMap();

    SubscriberMethodFinder() {
    }

    List<SubscriberMethod> findSubscriberMethods(Class<?> cls, String str) {
        List<SubscriberMethod> list;
        ThreadMode threadMode;
        String str2 = cls.getName() + '.' + str;
        synchronized (methodCache) {
            list = methodCache.get(str2);
        }
        if (list != null) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        HashSet hashSet = new HashSet();
        StringBuilder sb = new StringBuilder();
        for (Class<?> cls2 = cls; cls2 != null; cls2 = cls2.getSuperclass()) {
            String name = cls2.getName();
            if (name.startsWith("java.") || name.startsWith("javax.") || name.startsWith("android.")) {
                break;
            }
            for (Method method : cls2.getDeclaredMethods()) {
                String name2 = method.getName();
                if (name2.startsWith(str)) {
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    if (parameterTypes.length == 1) {
                        String substring = name2.substring(str.length());
                        if (substring.length() == 0) {
                            threadMode = ThreadMode.PostThread;
                        } else if (substring.equals("MainThread")) {
                            threadMode = ThreadMode.MainThread;
                        } else if (substring.equals("BackgroundThread")) {
                            threadMode = ThreadMode.BackgroundThread;
                        } else if (substring.equals("Async")) {
                            threadMode = ThreadMode.Async;
                        } else if (!skipMethodNameVerificationForClasses.containsKey(cls2)) {
                            throw new EventBusException("Illegal onEvent method, check for typos: " + method);
                        }
                        Class<?> cls3 = parameterTypes[0];
                        sb.setLength(0);
                        sb.append(name2);
                        sb.append('>');
                        sb.append(cls3.getName());
                        if (hashSet.add(sb.toString())) {
                            arrayList.add(new SubscriberMethod(method, threadMode, cls3));
                        }
                    } else {
                        continue;
                    }
                }
            }
        }
        if (arrayList.isEmpty()) {
            throw new EventBusException("Subscriber " + cls + " has no methods called " + str);
        }
        synchronized (methodCache) {
            methodCache.put(str2, arrayList);
        }
        return arrayList;
    }

    static void clearCaches() {
        methodCache.clear();
    }

    static void skipMethodNameVerificationFor(Class<?> cls) {
        if (!methodCache.isEmpty()) {
            throw new IllegalStateException("This method must be called before registering anything");
        }
        skipMethodNameVerificationForClasses.put(cls, cls);
    }

    public static void clearSkipMethodNameVerifications() {
        skipMethodNameVerificationForClasses.clear();
    }
}
