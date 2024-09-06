package com.android.pc.ioc.core.kernel;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public class KernelCaller {
    protected Method method;
    protected Object target;

    public KernelCaller(Object obj, Method method) {
        this.target = obj;
        this.method = method;
    }

    public KernelCaller(Object obj, String str, Class... clsArr) {
        this(obj, str, false, clsArr);
    }

    public KernelCaller(Object obj, String str, boolean z, Class... clsArr) {
        this.target = obj;
        Class<?> cls = obj instanceof Class ? (Class) obj : obj.getClass();
        Method assignableMethod = KernelReflect.assignableMethod(cls, str, 0, false, z, false, clsArr);
        this.method = assignableMethod;
        if (cls == obj) {
            this.method = (Method) KernelReflect.memberStatic(assignableMethod);
        }
    }

    public Object getTarget() {
        return this.target;
    }

    public Method getMethod() {
        return this.method;
    }

    public Object invoke(Object... objArr) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        return this.method.invoke(this.target, objArr);
    }
}
