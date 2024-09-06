package com.android.pc.ioc.core.kernel;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public class KernelCallerO extends KernelCaller {
    protected Object[] parameterObjects;

    public KernelCallerO(Object obj, Method method) {
        super(obj, method);
    }

    public KernelCallerO(Object obj, String str, Class[] clsArr) {
        super(obj, str, clsArr);
    }

    public KernelCallerO(Object obj, String str, boolean z, Class[] clsArr) {
        super(obj, str, z, clsArr);
    }

    public KernelCallerO(Object obj, String str, boolean z, Class[] clsArr, Object... objArr) {
        super(obj, str, z, clsArr);
        setParameterObjects(objArr);
    }

    public Object[] getParameterObjects() {
        return this.parameterObjects;
    }

    public void setParameterObjects(Object... objArr) {
        if (this.parameterObjects == null) {
            this.parameterObjects = new Object[this.method == null ? 0 : this.method.getParameterTypes().length];
        }
        int length = this.parameterObjects.length;
        int length2 = length - objArr.length;
        int i = length2 >= 0 ? length2 : 0;
        while (true) {
            length--;
            if (length <= i) {
                return;
            } else {
                this.parameterObjects[length] = objArr[length - i];
            }
        }
    }

    @Override // com.android.pc.ioc.core.kernel.KernelCaller
    public Object invoke(Object... objArr) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        if (this.parameterObjects == null) {
            this.parameterObjects = new Object[this.method == null ? 0 : this.method.getParameterTypes().length];
        }
        int length = this.parameterObjects.length;
        int length2 = objArr.length;
        if (length2 <= length) {
            length = length2;
        }
        for (int i = 0; i < length; i++) {
            if (objArr[i] != KernelLang.NULL_OBJECT) {
                this.parameterObjects[i] = objArr[i];
            }
        }
        return super.invoke(this.parameterObjects);
    }
}
