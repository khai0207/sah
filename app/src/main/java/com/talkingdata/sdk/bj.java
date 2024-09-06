package com.talkingdata.sdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/* compiled from: td */
/* loaded from: classes.dex */
final class bj implements InvocationHandler {
    final /* synthetic */ bf a;
    final /* synthetic */ Object b;

    bj(bf bfVar, Object obj) {
        this.a = bfVar;
        this.b = obj;
    }

    @Override // java.lang.reflect.InvocationHandler
    public Object invoke(Object obj, Method method, Object[] objArr) {
        this.a.a(obj, method, objArr);
        Object invoke = method.invoke(this.b, objArr);
        this.a.a(obj, method, objArr, invoke);
        return invoke;
    }
}
