package com.android.pc.ioc.util;

import android.view.View;
import com.android.pc.ioc.core.kernel.KernelClass;

/* loaded from: classes.dex */
public abstract class InjectExcutor<T> {
    protected Object object = null;
    protected Class<?> type = KernelClass.componentClass(getClass().getGenericSuperclass());

    public abstract View findViewById(int i);

    public abstract View findViewById(T t, int i);

    public abstract View loadView(T t, int i);

    public abstract void setContentView(T t, int i);

    public InjectExcutor<T> setObject(Object obj) {
        this.object = obj;
        return this;
    }

    public Object getObject() {
        return this.object;
    }
}
