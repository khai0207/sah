package com.unionpay.sdk;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
final class l {
    private final Object a;
    private final Method b;
    private final int c;
    private boolean d = true;

    l(Object obj, Method method) {
        if (obj == null) {
            throw new NullPointerException("EventHandler target cannot be null.");
        }
        if (method == null) {
            throw new NullPointerException("EventHandler method cannot be null.");
        }
        this.a = obj;
        this.b = method;
        method.setAccessible(true);
        this.c = ((method.hashCode() + 31) * 31) + obj.hashCode();
    }

    public final void a(Object obj) {
        if (!this.d) {
            throw new IllegalStateException(toString() + " has been invalidated and can no longer handle events.");
        }
        try {
            this.b.invoke(this.a, obj);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InvocationTargetException e2) {
            if (!(e2.getCause() instanceof Error)) {
                throw e2;
            }
            throw ((Error) e2.getCause());
        }
    }

    public final boolean a() {
        return this.d;
    }

    public final void b() {
        this.d = false;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        l lVar = (l) obj;
        return this.b.equals(lVar.b) && this.a == lVar.a;
    }

    public final int hashCode() {
        return this.c;
    }

    public final String toString() {
        return "[EventHandler " + this.b + "]";
    }
}
