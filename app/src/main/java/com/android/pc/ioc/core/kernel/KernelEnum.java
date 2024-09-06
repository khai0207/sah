package com.android.pc.ioc.core.kernel;

import java.lang.reflect.Array;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public abstract class KernelEnum {
    public static Object[] values(Class<? extends Enum> cls) {
        return (Object[]) KernelObject.send(cls, "values", new Object[0]);
    }

    public static <T> T[] toArray(Class<? extends Enum> cls, Class cls2) {
        return (T[]) toArray(cls, "getValue", cls2);
    }

    public static <T> T[] toArray(Class<? extends Enum> cls, String str, Class cls2) {
        try {
            return (T[]) toArray(cls, cls.getMethod(str, new Class[0]), cls2);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> T[] toArray(Class<? extends Enum> cls, Method method, Class cls2) {
        Enum[] enumArr = (Enum[]) cls.getEnumConstants();
        int length = enumArr.length;
        T[] tArr = (T[]) ((Object[]) Array.newInstance((Class<?>) cls2, length));
        for (int i = 0; i < length; i++) {
            try {
                tArr[i] = method.invoke(enumArr[i], new Object[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return tArr;
    }
}
