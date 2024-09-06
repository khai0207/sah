package com.unionpay.sdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
final class ax implements InvocationHandler {
    final /* synthetic */ ao a;
    final /* synthetic */ Object b;

    ax(ao aoVar, Object obj) {
        this.a = aoVar;
        this.b = obj;
    }

    @Override // java.lang.reflect.InvocationHandler
    public final Object invoke(Object obj, Method method, Object[] objArr) {
        this.a.a(method);
        return method.invoke(this.b, objArr);
    }
}
