package com.android.pc.ioc.invoker;

import android.app.Activity;
import android.view.View;
import com.android.pc.ioc.app.Ioc;
import com.android.pc.ioc.util.InjectExcutor;
import com.android.pc.ioc.view.listener.OnListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/* loaded from: classes.dex */
public class InjectMethods extends InjectInvoker {
    Class[] clazz;
    int[] ids;
    InjectExcutor<?> injectExcutor;
    Method method;

    public InjectMethods(Method method, int[] iArr, Class[] clsArr, InjectExcutor<?> injectExcutor) {
        this.method = method;
        this.clazz = clsArr;
        this.ids = iArr;
        this.injectExcutor = injectExcutor;
    }

    @Override // com.android.pc.ioc.invoker.InjectInvoker
    public void invoke(Object obj, Object... objArr) {
        View findViewById;
        try {
            if (this.clazz != null && this.ids != null) {
                for (int i = 0; i < this.ids.length; i++) {
                    int i2 = this.ids[i];
                    if (this.injectExcutor.getObject() != null) {
                        findViewById = this.injectExcutor.findViewById(i2);
                    } else {
                        findViewById = this.injectExcutor.findViewById((Activity) obj, i2);
                    }
                    if (findViewById == null) {
                        Ioc.getIoc().getLogger().e(obj.getClass().getSimpleName() + " 方法 " + this.method + " 对应的ids出错\n");
                    } else {
                        for (int i3 = 0; i3 < this.clazz.length; i3++) {
                            ((OnListener) this.clazz[i3].newInstance()).listener(findViewById, obj, this.method.getName());
                        }
                    }
                }
                return;
            }
            this.method.setAccessible(true);
            if (objArr != null) {
                try {
                    if (objArr.length > 0) {
                        this.method.invoke(obj, objArr);
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
                    return;
                }
            }
            this.method.invoke(obj, new Object[0]);
        } catch (Exception e2) {
            e2.printStackTrace();
            if (e2.getCause() != null) {
                e2.getCause().printStackTrace();
            }
        }
    }

    public String toString() {
        return "InjectMethods [method=" + this.method + ", ids=" + Arrays.toString(this.ids) + ", clazz=" + Arrays.toString(this.clazz) + "]";
    }
}
