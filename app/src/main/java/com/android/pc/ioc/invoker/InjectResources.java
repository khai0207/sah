package com.android.pc.ioc.invoker;

import com.android.pc.ioc.app.Ioc;
import com.android.pc.ioc.core.kernel.KernelLang;
import com.android.pc.ioc.core.kernel.KernelReflect;
import com.android.pc.ioc.util.InjectResouceSupply;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/* loaded from: classes.dex */
public class InjectResources extends InjectInvoker {
    private Field field;
    private int id;
    private Class<?> inClass;
    private Field injectAllfield;
    InjectResouceSupply.InjectResouceType<?> injectResouceType;

    public InjectResources(int i, Field field, InjectResouceSupply.InjectResouceType<?> injectResouceType, Class<?> cls, Field field2) {
        this.id = i;
        this.field = field;
        this.injectResouceType = injectResouceType;
        this.inClass = cls;
        this.injectAllfield = field2;
    }

    @Override // com.android.pc.ioc.invoker.InjectInvoker
    public void invoke(Object obj, Object... objArr) {
        Object resouce = this.injectResouceType.getResouce(this.id, this.field.getName());
        if (resouce == null || !this.field.getType().isAssignableFrom(resouce.getClass())) {
            Ioc.getIoc().getLogger().e(obj.getClass().getSimpleName() + " 对象 " + this.field.getName() + "赋值不对 请检查\n");
            return;
        }
        try {
            if (this.injectAllfield == null) {
                this.field.setAccessible(true);
                this.field.set(obj, resouce);
                return;
            }
            this.injectAllfield.setAccessible(true);
            Object obj2 = this.injectAllfield.get(obj);
            if (obj2 == null) {
                if (this.inClass.getDeclaringClass() == null) {
                    obj2 = this.inClass.newInstance();
                } else {
                    Constructor<?>[] declaredConstructors = this.inClass.getDeclaredConstructors();
                    declaredConstructors[0].setAccessible(true);
                    obj2 = declaredConstructors[0].newInstance(obj);
                }
                KernelReflect.set(obj, this.injectAllfield, obj2);
            }
            this.field.setAccessible(true);
            this.field.set(obj2, resouce);
        } catch (Exception e) {
            e.printStackTrace();
            Ioc.getIoc().getLogger().e(obj.getClass().getSimpleName() + " 对象 " + this.field.getName() + "赋值不对 请检查\n");
            throw new KernelLang.CauseRuntimeException(e);
        }
    }

    public String toString() {
        return "InjectResources [id=" + this.id + "]";
    }
}
