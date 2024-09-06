package com.android.pc.ioc.invoker;

import com.android.pc.ioc.app.Ioc;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public class InjectHttps extends InjectInvoker {
    Method method;

    public InjectHttps(Method method) {
        this.method = method;
    }

    @Override // com.android.pc.ioc.invoker.InjectInvoker
    public void invoke(Object obj, Object... objArr) {
        if (obj == null) {
            Ioc.getIoc().getLogger().e("接口传进来的 activity为空 , 请检查");
            return;
        }
        try {
            this.method.setAccessible(true);
            if (objArr != null && objArr.length > 0) {
                this.method.invoke(obj, objArr);
            } else {
                this.method.invoke(obj, new Object[0]);
            }
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().indexOf("wrong number of arguments") != -1) {
                Ioc.getIoc().getLogger().e(obj.getClass().getSimpleName() + " 方法 " + this.method + "参数不对 请检查\n");
                return;
            }
            if (e instanceof InvocationTargetException) {
                Ioc.getIoc().getLogger().e(obj.getClass().getSimpleName() + " 方法 " + this.method + "里面出错了 请检查\n");
                e.getCause().printStackTrace();
                return;
            }
            Ioc.getIoc().getLogger().e(obj.getClass().getSimpleName() + " 方法 " + this.method + "里面出错了 请检查\n");
            if (e.getCause() != null) {
                e.getCause().printStackTrace();
            } else {
                e.printStackTrace();
            }
        }
    }

    public String toString() {
        return "InjectHttps [method=" + this.method + "]";
    }
}
