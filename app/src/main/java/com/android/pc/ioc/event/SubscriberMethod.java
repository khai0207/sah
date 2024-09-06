package com.android.pc.ioc.event;

import java.lang.reflect.Method;

/* loaded from: classes.dex */
final class SubscriberMethod {
    final Class<?> eventType;
    final Method method;
    String methodString;
    final ThreadMode threadMode;

    SubscriberMethod(Method method, ThreadMode threadMode, Class<?> cls) {
        this.method = method;
        this.threadMode = threadMode;
        this.eventType = cls;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SubscriberMethod)) {
            return false;
        }
        checkMethodString();
        return this.methodString.equals(((SubscriberMethod) obj).methodString);
    }

    private synchronized void checkMethodString() {
        if (this.methodString == null) {
            StringBuilder sb = new StringBuilder(64);
            sb.append(this.method.getDeclaringClass().getName());
            sb.append('#');
            sb.append(this.method.getName());
            sb.append('(');
            sb.append(this.eventType.getName());
            this.methodString = sb.toString();
        }
    }

    public int hashCode() {
        return this.method.hashCode();
    }
}
