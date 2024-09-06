package com.netease.nimlib.o;

import android.text.TextUtils;
import java.lang.reflect.Method;

/* compiled from: ReflectionUtil.java */
/* loaded from: classes.dex */
public class t {
    static final /* synthetic */ boolean a = !t.class.desiredAssertionStatus();

    public static Object a(Object obj, String str, Object[] objArr) {
        return a(obj, str, objArr, null);
    }

    public static Object a(Object obj, String str, Object[] objArr, Class<?>[] clsArr) {
        Method method;
        if (obj != null && !TextUtils.isEmpty(str)) {
            Class<?> cls = obj.getClass();
            if (clsArr == null && objArr != null) {
                try {
                    clsArr = new Class[objArr.length];
                    for (int i = 0; i < objArr.length; i++) {
                        clsArr[i] = objArr[i].getClass();
                    }
                } catch (NoSuchMethodException unused) {
                    com.netease.nimlib.log.b.f("ReflectionUtil", "method " + str + " not found in " + obj.getClass().getName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (clsArr != null) {
                method = cls.getMethod(str, clsArr);
            } else {
                method = cls.getMethod(str, new Class[0]);
            }
            method.setAccessible(true);
            return method.invoke(obj, objArr);
        }
        return null;
    }
}
