package com.netease.nimlib.biz;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

/* compiled from: ArtemisHelper.java */
/* loaded from: classes.dex */
public class b {
    public static boolean a() {
        boolean b = com.netease.nimlib.abtest.c.a().b("net_detect", "detect_open", "open");
        com.netease.nimlib.log.b.c("ArtemisHelper", "Artemis is enable in ABTest: " + b);
        return b;
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00d0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void a(android.content.Context r18) {
        /*
            Method dump skipped, instructions count: 262
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.netease.nimlib.biz.b.a(android.content.Context):void");
    }

    private static void b() {
        try {
            Class<?> cls = Class.forName("com.netease.yunxin.artemis.Artemis.YXArtemis");
            Class<?> cls2 = Class.forName("com.netease.yunxin.artemis.Artemis.YXArtemisLogCallback");
            cls.getDeclaredMethod("setLogCallback", cls2).invoke(null, Proxy.newProxyInstance(cls2.getClassLoader(), new Class[]{cls2}, new InvocationHandler() { // from class: com.netease.nimlib.biz.-$$Lambda$b$ZnwTspcUDwUaHQbkI-lx3qqAH7A
                @Override // java.lang.reflect.InvocationHandler
                public final Object invoke(Object obj, Method method, Object[] objArr) {
                    Object b;
                    b = b.b(obj, method, objArr);
                    return b;
                }
            }));
        } catch (Throwable th) {
            com.netease.nimlib.log.b.d("ArtemisHelper", "setArtemisLogCallback failed, " + th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Object b(Object obj, Method method, Object[] objArr) throws Throwable {
        if (!method.getName().equals("onLog")) {
            return null;
        }
        com.netease.nimlib.log.b.d("ArtemisHelper", "Artemis inner log: " + objArr[0]);
        return null;
    }

    public static void a(Map<String, Object> map) {
        try {
            Class<?> cls = Class.forName("com.netease.yunxin.artemis.Artemis.YXArtemis");
            Class<?> cls2 = Class.forName("com.netease.yunxin.artemis.Artemis.YXArtemisRunTaskCallback");
            cls.getDeclaredMethod("runTask", Map.class, cls2).invoke(null, map, Proxy.newProxyInstance(cls2.getClassLoader(), new Class[]{cls2}, new InvocationHandler() { // from class: com.netease.nimlib.biz.-$$Lambda$b$bOMhD72d38tdZV6HSIZA3YVKKIA
                @Override // java.lang.reflect.InvocationHandler
                public final Object invoke(Object obj, Method method, Object[] objArr) {
                    Object a;
                    a = b.a(obj, method, objArr);
                    return a;
                }
            }));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Object a(Object obj, Method method, Object[] objArr) throws Throwable {
        if (!method.getName().equals("onCompleted")) {
            return null;
        }
        com.netease.nimlib.log.b.d("ArtemisHelper", "Artemis inner run task result: " + objArr[0]);
        return null;
    }
}
