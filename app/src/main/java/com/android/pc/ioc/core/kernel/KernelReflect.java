package com.android.pc.ioc.core.kernel;

import com.android.pc.ioc.core.kernel.KernelLang;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public abstract class KernelReflect {
    protected static final Map<String, Method> Class_Method_Map_Method = new HashMap();

    public static <T extends Member> T memberStatic(T t) {
        return (T) memberStatic(t, null);
    }

    public static <T extends Member> T memberStatic(T t, T t2) {
        return (t == null || !Modifier.isStatic(t.getModifiers())) ? t2 : t;
    }

    public static <T extends Member> T memberAccessor(T t) {
        return (T) memberAccessor(t, null);
    }

    public static <T extends Member> T memberAccessor(T t, T t2) {
        return (t == null || Modifier.isFinal(t.getModifiers()) || Modifier.isStatic(t.getModifiers())) ? t2 : t;
    }

    public static <T> Constructor<T> constructor(Class<T> cls, Class... clsArr) {
        return declaredConstructor(cls, false, clsArr);
    }

    public static <T> Constructor<T> declaredConstructor(Class<T> cls, Class... clsArr) {
        return declaredConstructor(cls, true, clsArr);
    }

    public static <T> Constructor<T> declaredConstructor(Class<T> cls, boolean z, Class... clsArr) {
        try {
            Constructor<T> declaredConstructor = z ? cls.getDeclaredConstructor(clsArr) : cls.getConstructor(clsArr);
            if (declaredConstructor == null) {
                return null;
            }
            if (z && !declaredConstructor.isAccessible()) {
                declaredConstructor.setAccessible(true);
            }
            return declaredConstructor;
        } catch (NoSuchMethodException | SecurityException unused) {
            return null;
        }
    }

    public static <T> Constructor<T> assignableConstructor(Class<T> cls, Class... clsArr) {
        return assignableConstructor(cls, false, clsArr);
    }

    public static <T> Constructor<T> assignableConstructor(Class<T> cls, boolean z, Class... clsArr) {
        int i = -2;
        Constructor<T> constructor = null;
        for (Constructor<?> constructor2 : z ? cls.getDeclaredConstructors() : cls.getConstructors()) {
            if (KernelClass.isMatchableFrom(constructor2.getParameterTypes(), clsArr)) {
                if (!constructor2.isAccessible()) {
                    if (z) {
                        constructor2.setAccessible(true);
                    }
                }
                int similar = KernelClass.similar(clsArr, constructor2.getParameterTypes());
                if (i < similar) {
                    constructor = (Constructor<T>) constructor2;
                    i = similar;
                }
            }
        }
        return constructor;
    }

    public static <T> Constructor<T> assignableConstructor(Class<T> cls, boolean z, boolean z2, Class... clsArr) {
        return z2 ? assignableConstructor(cls, z, clsArr) : declaredConstructor(cls, z, clsArr);
    }

    public static <T> T newInstance(Constructor<T> constructor, Object... objArr) {
        return (T) newInstance(constructor, null, objArr);
    }

    public static <T> T newInstance(Constructor<T> constructor, T t, Object... objArr) {
        if (constructor != null) {
            try {
                return constructor.newInstance(objArr);
            } catch (IllegalAccessException | IllegalArgumentException | InstantiationException | InvocationTargetException unused) {
            }
        }
        return t;
    }

    public static Field field(Class cls, String str) {
        return field(cls, str, 0);
    }

    public static Field field(Class cls, String str, int i) {
        return declaredField(cls, str, i, false);
    }

    public static Field declaredField(Class cls, String str) {
        return declaredField(cls, str, 0);
    }

    public static Field declaredField(Class cls, String str, int i) {
        return declaredField(cls, str, i, true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Field declaredField(Class cls, String str, int i, boolean z) {
        Field field = null;
        while (cls != null) {
            try {
                cls = z ? cls.getDeclaredField(str) : cls.getField(str);
                field = cls;
                break;
            } catch (NoSuchFieldException unused) {
                if (i >= 1) {
                    int i2 = i - 1;
                    if (i != 1) {
                        i = i2;
                    } else {
                        cls = field;
                        i = i2;
                    }
                }
                cls = cls.getSuperclass();
            } catch (SecurityException unused2) {
            }
        }
        if (field != null && z && !field.isAccessible()) {
            field.setAccessible(true);
        }
        return field;
    }

    public static List<Field> fields(Class cls) {
        return fields(cls, 0);
    }

    public static List<Field> fields(Class cls, int i) {
        return declaredFields(cls, i, false);
    }

    public static List<Field> declaredFields(Class cls) {
        return declaredFields(cls, 0);
    }

    public static List<Field> declaredFields(Class cls, int i) {
        return declaredFields(cls, i, true);
    }

    public static List<Field> declaredFields(Class cls, int i, boolean z) {
        ArrayList arrayList = new ArrayList();
        while (cls != null) {
            for (Field field : z ? cls.getDeclaredFields() : cls.getFields()) {
                if (z && !field.isAccessible()) {
                    field.setAccessible(true);
                }
                arrayList.add(field);
            }
            if (i >= 1) {
                int i2 = i - 1;
                if (i != 1) {
                    i = i2;
                } else {
                    cls = null;
                    i = i2;
                }
            }
            cls = cls.getSuperclass();
        }
        return arrayList;
    }

    public static boolean set(Object obj, Field field, Object obj2) {
        if (field == null) {
            return false;
        }
        try {
            field.setAccessible(true);
            field.set(obj, obj2);
            return true;
        } catch (IllegalAccessException | IllegalArgumentException unused) {
            return false;
        }
    }

    public static Object get(Object obj, Field field) {
        return get(obj, null, field);
    }

    public static Object get(Object obj, Object obj2, Field field) {
        if (field != null) {
            try {
                return field.get(obj);
            } catch (IllegalAccessException | IllegalArgumentException unused) {
            }
        }
        return obj2;
    }

    public static Method method(Class cls, String str, Class... clsArr) {
        return method(cls, str, 0, clsArr);
    }

    public static Method method(Class cls, String str, int i, Class... clsArr) {
        return declaredMethod(cls, str, i, false, clsArr);
    }

    public static Method declaredMethod(Class cls, String str, Class... clsArr) {
        return declaredMethod(cls, str, 0, clsArr);
    }

    public static Method declaredMethod(Class cls, String str, int i, Class... clsArr) {
        return declaredMethod(cls, str, i, true, clsArr);
    }

    public static Method declaredMethod(Class cls, String str, int i, boolean z, Class... clsArr) {
        return assignableMethod(cls, str, i, z, false, false, clsArr);
    }

    public static Method assignableMethod(Class cls, String str, Class... clsArr) {
        return assignableMethod(cls, str, 0, clsArr);
    }

    public static Method assignableMethod(Class cls, String str, int i, Class... clsArr) {
        return assignableMethod(cls, str, i, false, clsArr);
    }

    public static Method assignableMethod(Class cls, String str, int i, boolean z, Class... clsArr) {
        return assignableMethod(cls, str, i, z, true, true, clsArr);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v11, types: [java.lang.reflect.Method] */
    /* JADX WARN: Type inference failed for: r2v2, types: [java.lang.Object, java.lang.reflect.Method] */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r2v8 */
    /* JADX WARN: Type inference failed for: r2v9 */
    public static Method assignableMethod(Class cls, final String str, int i, boolean z, boolean z2, boolean z3, final Class... clsArr) {
        String str2;
        Class cls2;
        if (z3) {
            str2 = cls.getName() + ":" + str + ":" + clsArr.length;
            Method method = Class_Method_Map_Method.get(str2);
            if (method != null && KernelClass.isMatchableFrom(method.getParameterTypes(), clsArr)) {
                return method;
            }
        } else {
            str2 = null;
        }
        ?? r2 = null;
        while (cls != null) {
            if (z2 && clsArr.length > 0) {
                final ArrayList<Method> arrayList = new ArrayList();
                KernelLang.CallbackBreak<Method> callbackBreak = new KernelLang.CallbackBreak<Method>() { // from class: com.android.pc.ioc.core.kernel.KernelReflect.1
                    @Override // com.android.pc.ioc.core.kernel.KernelLang.CallbackBreak
                    public void doWith(Method method2) throws KernelLang.BreakException {
                        if (method2.getName().equals(str) && KernelClass.isMatchableFrom(method2.getParameterTypes(), clsArr)) {
                            Iterator it = arrayList.iterator();
                            while (it.hasNext()) {
                                if (KernelArray.equal(((Method) it.next()).getParameterTypes(), method2.getParameterTypes())) {
                                    return;
                                }
                            }
                            arrayList.add(method2);
                        }
                    }
                };
                if (z) {
                    doWithDeclaredMethods(cls, callbackBreak);
                } else {
                    doWithMethods(cls, callbackBreak);
                }
                cls2 = cls;
                if (arrayList.size() > 0) {
                    if (arrayList.size() == 1) {
                        r2 = (Method) arrayList.get(0);
                        cls2 = cls;
                    } else {
                        int i2 = -2;
                        for (Method method2 : arrayList) {
                            int similar = KernelClass.similar(clsArr, method2.getParameterTypes());
                            if (i2 < similar) {
                                r2 = method2;
                                i2 = similar;
                            }
                        }
                        z3 = false;
                        cls2 = cls;
                    }
                }
            } else {
                try {
                    cls = z ? cls.getDeclaredMethod(str, clsArr) : cls.getMethod(str, clsArr);
                    r2 = cls;
                    break;
                } catch (NoSuchMethodException unused) {
                    cls2 = cls;
                } catch (SecurityException unused2) {
                }
            }
            if (i >= 1) {
                int i3 = i - 1;
                if (i != 1) {
                    i = i3;
                } else {
                    cls = null;
                    i = i3;
                }
            }
            cls = cls2.getSuperclass();
        }
        if (r2 != null) {
            if (z && !r2.isAccessible()) {
                r2.setAccessible(true);
            }
            if (z3) {
                Class_Method_Map_Method.put(str2, r2);
            }
        }
        return r2;
    }

    public static Object invoke(Object obj, Method method, Object... objArr) {
        return invoke(obj, null, method, objArr);
    }

    public static Object invoke(Object obj, Object obj2, Method method, Object... objArr) {
        if (method != null) {
            try {
                return method.invoke(obj, objArr);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException unused) {
            }
        }
        return obj2;
    }

    public static void doWithClasses(Class cls, KernelLang.CallbackBreak<Class<?>> callbackBreak) {
        doWithClasses(cls, callbackBreak, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void doWithClasses(Class cls, KernelLang.CallbackBreak<Class<?>> callbackBreak, Class cls2) {
        while (cls != null && cls != Object.class && cls != cls2) {
            try {
                callbackBreak.doWith(cls);
                cls = cls.getSuperclass();
            } catch (KernelLang.BreakException unused) {
                return;
            }
        }
    }

    public static void doWithFields(Class cls, KernelLang.CallbackBreak<Field> callbackBreak) {
        doWithFields(cls, callbackBreak, null);
    }

    public static void doWithFields(Class cls, KernelLang.CallbackBreak<Field> callbackBreak, Class cls2) {
        while (cls != null && cls != Object.class && cls != cls2) {
            try {
                for (Field field : cls.getFields()) {
                    callbackBreak.doWith(field);
                }
                cls = cls.getSuperclass();
            } catch (KernelLang.BreakException unused) {
                return;
            }
        }
    }

    public static void doWithDeclaredFields(Class cls, KernelLang.CallbackBreak<Field> callbackBreak) {
        doWithDeclaredFields(cls, callbackBreak, null);
    }

    public static void doWithDeclaredFields(Class cls, KernelLang.CallbackBreak<Field> callbackBreak, Class cls2) {
        while (cls != null && cls != Object.class && cls != cls2) {
            try {
                for (Field field : cls.getDeclaredFields()) {
                    callbackBreak.doWith(field);
                }
                cls = cls.getSuperclass();
            } catch (KernelLang.BreakException unused) {
                return;
            }
        }
    }

    public static void doWithMethods(Class cls, KernelLang.CallbackBreak<Method> callbackBreak) {
        doWithMethods(cls, callbackBreak, null);
    }

    public static void doWithMethods(Class cls, KernelLang.CallbackBreak<Method> callbackBreak, Class cls2) {
        while (cls != null && cls != Object.class && cls != cls2) {
            try {
                for (Method method : cls.getMethods()) {
                    callbackBreak.doWith(method);
                }
                cls = cls.getSuperclass();
            } catch (KernelLang.BreakException unused) {
                return;
            }
        }
    }

    public static void doWithDeclaredMethods(Class cls, KernelLang.CallbackBreak<Method> callbackBreak) {
        doWithDeclaredMethods(cls, callbackBreak, null);
    }

    public static void doWithDeclaredMethods(Class cls, KernelLang.CallbackBreak<Method> callbackBreak, Class cls2) {
        while (cls != null && cls != Object.class && cls != cls2) {
            try {
                for (Method method : cls.getDeclaredMethods()) {
                    callbackBreak.doWith(method);
                }
                cls = cls.getSuperclass();
            } catch (KernelLang.BreakException unused) {
                return;
            }
        }
    }
}
