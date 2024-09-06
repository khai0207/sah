package com.talkingdata.sdk;

import java.lang.reflect.Method;

/* compiled from: td */
/* loaded from: classes.dex */
final class bo {
    private final Object a;
    private final Method b;
    private final int c;
    private boolean d = true;

    bo(Object obj, Method method) {
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

    public boolean a() {
        return this.d;
    }

    public void b() {
        this.d = false;
    }

    public void handleEvent(Object obj) {
        if (!this.d) {
            an.eForInternal(toString() + " has been invalidated and can no longer handle events.");
        }
        try {
            this.b.invoke(this.a, obj);
        } catch (Throwable unused) {
        }
    }

    public String toString() {
        return "[EventHandler " + this.b + "]";
    }

    public int hashCode() {
        return this.c;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        try {
            if (getClass() != obj.getClass()) {
                return false;
            }
            bo boVar = (bo) obj;
            if (this.b.equals(boVar.b)) {
                if (this.a == boVar.a) {
                    return true;
                }
            }
            return false;
        } catch (Throwable th) {
            ce.postSDKError(th);
            return false;
        }
    }
}
