package com.android.pc.ioc.view.listener;

import android.view.View;
import com.android.pc.ioc.app.Ioc;
import com.android.pc.ioc.core.kernel.KernelClass;
import com.android.pc.ioc.core.kernel.KernelReflect;
import com.android.pc.ioc.core.kernel.KernelString;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public abstract class OnListener {
    String method;
    private boolean noArgs;
    Object target;
    private Method targetMethod;

    protected abstract void listener(View view);

    public final void listener(View view, Object obj, String str) {
        if (KernelString.isEmpty(str)) {
            return;
        }
        this.target = obj;
        this.method = str;
        listener(view);
    }

    public Object invoke(Object... objArr) {
        if (objArr == null) {
            return null;
        }
        try {
            if (this.method != null) {
                Method assignableMethod = KernelReflect.assignableMethod(this.target.getClass(), this.method, 0, true, KernelClass.parameterTypes(objArr));
                this.targetMethod = assignableMethod;
                if (assignableMethod == null) {
                    this.noArgs = true;
                    this.targetMethod = KernelReflect.declaredMethod(this.target.getClass(), this.method, new Class[0]);
                }
                this.method = null;
            }
            if (this.targetMethod != null) {
                this.targetMethod.setAccessible(true);
                if (this.noArgs) {
                    return this.targetMethod.invoke(this.target, new Object[0]);
                }
                return this.targetMethod.invoke(this.target, objArr);
            }
        } catch (Exception e) {
            if (e instanceof InvocationTargetException) {
                StringWriter stringWriter = new StringWriter();
                e.getCause().printStackTrace(new PrintWriter(stringWriter));
                Ioc.getIoc().getLogger().e(this.target.getClass().getSimpleName() + " 方法 " + this.targetMethod + "里面出错了 请检查\n" + stringWriter.toString());
                return null;
            }
            e.printStackTrace();
        }
        return null;
    }

    public String toString() {
        return "OnListener [target=" + this.target + ", method=" + this.method + ", targetMethod=" + this.targetMethod + ", noArgs=" + this.noArgs + "]";
    }
}
