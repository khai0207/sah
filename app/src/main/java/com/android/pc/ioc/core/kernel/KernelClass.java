package com.android.pc.ioc.core.kernel;

import com.android.pc.ioc.core.kernel.KernelLang;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public abstract class KernelClass {
    static final Map<Class, Object> Class_Map_Instance = new HashMap();

    public static String parentName(Class cls) {
        return KernelString.rightSubString(cls.getName(), cls.getSimpleName().length() + 1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean isAssignableFrom(Class cls, Class[] clsArr) {
        for (Class cls2 : clsArr) {
            if (cls.isAssignableFrom(cls2)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isAssignableFrom(Class[] clsArr, Class cls) {
        for (Class cls2 : clsArr) {
            if (cls2.isAssignableFrom(cls)) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean isAssignableFrom(Class[] clsArr, Class[] clsArr2) {
        int length = clsArr.length;
        if (length != clsArr2.length) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (!clsArr[i].isAssignableFrom(clsArr2[i])) {
                return false;
            }
        }
        return true;
    }

    public static <T> Class<? extends T> classAssignable(Class cls, Class cls2) {
        return classAssignable(cls, null, cls2);
    }

    public static <T> Class<? extends T> classAssignable(Class cls, Class<? extends T> cls2, Class cls3) {
        return (cls == null || !cls3.isAssignableFrom(cls)) ? cls2 : cls;
    }

    public static boolean isMatchableFrom(Class cls, Class cls2) {
        if (cls.isAssignableFrom(cls2) || cls == Object.class) {
            return true;
        }
        if (cls == Byte.TYPE) {
            return cls2 == Byte.class;
        }
        if (cls == Byte.class) {
            return cls2 == Byte.TYPE;
        }
        if (cls == Short.TYPE) {
            return cls2 == Short.class;
        }
        if (cls == Short.class) {
            return cls2 == Short.TYPE;
        }
        if (cls == Integer.TYPE) {
            return cls2 == Integer.class;
        }
        if (cls == Integer.class) {
            return cls2 == Integer.TYPE;
        }
        if (cls == Float.TYPE) {
            return cls2 == Float.class;
        }
        if (cls == Float.class) {
            return cls2 == Float.TYPE;
        }
        if (cls == Double.TYPE) {
            return cls2 == Double.class;
        }
        if (cls == Double.class) {
            return cls2 == Double.TYPE;
        }
        if (cls == Boolean.TYPE) {
            return cls2 == Boolean.class;
        }
        if (cls == Boolean.class) {
            return cls2 == Boolean.TYPE;
        }
        if (cls == Character.TYPE) {
            return cls2 == Character.class;
        }
        if (cls == Character.class) {
            return cls2 == Character.TYPE;
        }
        if (cls == Long.TYPE) {
            return cls2 == Long.class;
        }
        if (cls == Long.class) {
            return cls2 == Long.TYPE;
        }
        if (cls.isArray() && cls2.isArray()) {
            cls.getComponentType().isAssignableFrom(cls2.getComponentType());
        }
        return false;
    }

    public static boolean isMatchableFrom(Class cls, Class[] clsArr) {
        for (Class cls2 : clsArr) {
            if (isMatchableFrom(cls, cls2)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isMatchableFrom(Class[] clsArr, Class[] clsArr2) {
        int length = clsArr.length;
        if (length != clsArr2.length) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (!isMatchableFrom(clsArr[i], clsArr2[i])) {
                return false;
            }
        }
        return true;
    }

    public static Class[] parameterTypes(Object... objArr) {
        int length = objArr.length;
        Class[] clsArr = new Class[length];
        for (int i = 0; i < length; i++) {
            clsArr[i] = objArr[i].getClass();
        }
        return clsArr;
    }

    public static Class rawClass(Type type) {
        if (type instanceof ParameterizedType) {
            return (Class) ((ParameterizedType) type).getRawType();
        }
        if (type instanceof GenericArrayType) {
            try {
                return Array.newInstance((Class<?>) rawClass(((GenericArrayType) type).getGenericComponentType()), 0).getClass();
            } catch (Exception unused) {
                return Object.class;
            }
        }
        if (type instanceof Class) {
            return (Class) type;
        }
        return Object.class;
    }

    public static Type[] typeArguments(Type type) {
        if (type instanceof ParameterizedType) {
            return ((ParameterizedType) type).getActualTypeArguments();
        }
        return null;
    }

    public static Class[] rawClasses(Type[] typeArr) {
        int length = typeArr.length;
        Class[] clsArr = new Class[length];
        for (int i = 0; i < length; i++) {
            clsArr[i] = rawClass(typeArr[i]);
        }
        return clsArr;
    }

    public static Class componentClass(Class<?> cls) {
        if (cls.isArray()) {
            return cls.getComponentType();
        }
        Class<? super Object> componentClass = componentClass(cls.getGenericSuperclass());
        return componentClass == cls.getSuperclass() ? cls : componentClass;
    }

    public static Class[] componentClasses(Class<?> cls) {
        if (cls.isArray()) {
            return new Class[]{cls.getComponentType()};
        }
        Class[] componentClasses = componentClasses(cls.getGenericSuperclass());
        if (componentClasses.length == 1 && componentClasses[0] == cls.getSuperclass()) {
            componentClasses[0] = cls;
        }
        return componentClasses;
    }

    public static Class componentClass(Type type) {
        Type type2 = type;
        while (type2 != null) {
            Type[] typeArguments = typeArguments(type2);
            if (typeArguments == null || typeArguments.length <= 0) {
                Class rawClass = rawClass(type2);
                if (rawClass.isArray()) {
                    return rawClass.getComponentType();
                }
                type2 = rawClass.getGenericSuperclass();
            } else {
                return rawClass(typeArguments[0]);
            }
        }
        return rawClass(type);
    }

    public static Class[] componentClasses(Type type) {
        Type type2 = type;
        while (type2 != null) {
            Type[] typeArguments = typeArguments(type2);
            if (typeArguments == null || typeArguments.length <= 0) {
                Class rawClass = rawClass(type2);
                if (rawClass.isArray()) {
                    return new Class[]{rawClass.getComponentType()};
                }
                type2 = rawClass.getGenericSuperclass();
            } else {
                return rawClasses(typeArguments);
            }
        }
        return new Class[]{rawClass(type)};
    }

    public static int similar(Class cls, Class cls2) {
        int i = -1;
        while (cls != null) {
            if (i < 0) {
                if (cls != cls2) {
                    for (Class<?> cls3 : cls.getInterfaces()) {
                        if (cls3 != cls2) {
                        }
                    }
                }
                i = 0;
                break;
            } else {
                i++;
            }
            cls = cls.getSuperclass();
        }
        return i;
    }

    public static int similar(Class[] clsArr, Class[] clsArr2) {
        int length = clsArr.length;
        int i = -1;
        if (length == clsArr2.length) {
            for (int i2 = 0; i2 < length; i2++) {
                i += similar(clsArr[i2], clsArr2[i2]);
            }
        }
        return i;
    }

    public static Class forName(String str) {
        return forName(str, null);
    }

    public static Class forName(String str, Class cls) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException unused) {
            return cls;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T extends Annotation> T getAnnotation(Class<?> cls, Class<T> cls2) {
        Annotation[] annotations;
        T t = (T) cls.getAnnotation(cls2);
        if (t == null && !Annotation.class.isAssignableFrom(cls) && (annotations = cls.getAnnotations()) != null) {
            for (Annotation annotation : annotations) {
                t = (T) getAnnotation(annotation.annotationType(), cls2);
                if (t != null) {
                    break;
                }
            }
        }
        return t;
    }

    public static <T> T newInstance(Class<T> cls) {
        return (T) newInstance((Object) null, cls);
    }

    public static <T> T newInstance(T t, Class<T> cls) {
        try {
            return cls.newInstance();
        } catch (IllegalAccessException | InstantiationException unused) {
            return t;
        }
    }

    public static <T> T newInstance(Class<T> cls, Object... objArr) {
        return (T) newInstance(cls, null, objArr);
    }

    public static <T> T newInstance(Class<T> cls, T t, Object... objArr) {
        if (objArr.length == 0) {
            return (T) newInstance(t, cls);
        }
        return (T) newInstance(cls, t, parameterTypes(objArr), true, objArr);
    }

    public static <T> T newInstance(Class<T> cls, T t, Class[] clsArr, Object... objArr) {
        return (T) newInstance(cls, t, clsArr, false, objArr);
    }

    public static <T> T newInstance(Class<T> cls, T t, Class[] clsArr, boolean z, Object... objArr) {
        return (T) declaredNew(cls, t, false, z, clsArr, objArr);
    }

    public static <T> T declaredNew(Class<T> cls, Object... objArr) {
        return (T) declaredNew(cls, null, parameterTypes(objArr), true, objArr);
    }

    public static <T> T declaredNew(Class<T> cls, T t, Object... objArr) {
        return (T) declaredNew(cls, t, parameterTypes(objArr), true, objArr);
    }

    public static <T> T declaredNew(Class<T> cls, Class[] clsArr, Object... objArr) {
        return (T) declaredNew(cls, null, clsArr, objArr);
    }

    public static <T> T declaredNew(Class<T> cls, T t, Class[] clsArr, Object... objArr) {
        return (T) declaredNew(cls, t, clsArr, false, objArr);
    }

    public static <T> T declaredNew(Class<T> cls, T t, Class[] clsArr, boolean z, Object... objArr) {
        return (T) declaredNew(cls, t, true, z, clsArr, objArr);
    }

    public static <T> T declaredNew(Class<T> cls, T t, boolean z, boolean z2, Class[] clsArr, Object... objArr) {
        return (T) KernelReflect.newInstance(KernelReflect.assignableConstructor(cls, z, z2, clsArr), objArr);
    }

    public static <T> T getInstance(Class<T> cls) {
        Object obj = (T) Class_Map_Instance.get(cls);
        if (obj == null) {
            synchronized (cls) {
                obj = Class_Map_Instance.get(cls);
                if (obj == null) {
                    obj = (T) newInstance(cls);
                    Class_Map_Instance.put(cls, obj);
                }
            }
        }
        return (T) obj;
    }

    public static <T> T getInstance(Class<T> cls, Object... objArr) {
        Object obj = (T) Class_Map_Instance.get(cls);
        if (obj == null) {
            synchronized (cls) {
                obj = Class_Map_Instance.get(cls);
                if (obj == null) {
                    Object newInstance = newInstance(cls, objArr);
                    Class_Map_Instance.put(cls, newInstance);
                    obj = (T) newInstance;
                }
            }
        }
        return (T) obj;
    }

    public static Object declaredGet(Class cls, String str) {
        return KernelReflect.get(cls, KernelReflect.declaredField(cls.getClass(), str));
    }

    public static boolean declaredSet(Class cls, String str, Object obj) {
        return KernelReflect.set(cls, KernelReflect.declaredField(cls.getClass(), str), obj);
    }

    public static Object declaredSend(Class cls, String str, Object... objArr) {
        Method assignableMethod = KernelReflect.assignableMethod(cls, str, 0, true, parameterTypes(objArr));
        if (assignableMethod != null) {
            return KernelReflect.invoke(cls, assignableMethod, objArr);
        }
        return null;
    }

    public static Method setter(Field field) {
        return setter(field.getDeclaringClass(), field);
    }

    public static Method setter(Class cls, Field field) {
        return setter(cls, field.getName(), field.getType());
    }

    public static Method setter(Class cls, String str, Class cls2) {
        return KernelReflect.method(cls, "set" + KernelString.uncapitalize(str), cls2);
    }

    public static Method getter(Field field) {
        return getter(field.getDeclaringClass(), field);
    }

    public static Method getter(Class cls, Field field) {
        return getter(cls, field.getName(), field.getType());
    }

    public static Method getter(Class cls, String str) {
        String uncapitalize = KernelString.uncapitalize(str);
        Method method = KernelReflect.method(cls, "get" + uncapitalize, new Class[0]);
        if (method != null) {
            return method;
        }
        return KernelReflect.method(cls, "is" + uncapitalize, new Class[0]);
    }

    public static Method getter(Class cls, String str, Class cls2) {
        String uncapitalize = KernelString.uncapitalize(str);
        Method method = KernelReflect.method(cls, "get" + uncapitalize, new Class[0]);
        if (method != null && cls2.isAssignableFrom(method.getReturnType())) {
            return method;
        }
        if (cls2 == Boolean.TYPE || cls2 == Boolean.class) {
            method = KernelReflect.method(cls, "is" + uncapitalize, new Class[0]);
        }
        if (method == null || cls2.isAssignableFrom(method.getReturnType())) {
            return method;
        }
        return null;
    }

    public static <T> T instanceOf(Class<T> cls, Object obj) {
        return (T) instanceOf(cls, null, obj);
    }

    public static <T> T instanceOf(Class<T> cls, T t, Object obj) {
        T t2 = (T) newInstance(cls, obj);
        if (t2 == null) {
            t2 = (T) valueOf(cls, obj);
        }
        return t2 == null ? t : t2;
    }

    public static <T> T valueOf(Class<T> cls, Object obj) {
        return (T) valueOf(cls, null, obj);
    }

    public static <T> T valueOf(Class<T> cls, T t, Object obj) {
        Method assignableMethod = KernelReflect.assignableMethod(cls, "valueOf", obj.getClass());
        return (assignableMethod == null || !cls.isAssignableFrom(assignableMethod.getReturnType())) ? t : (T) KernelReflect.invoke(cls, assignableMethod, obj);
    }

    public static <T> Class<? extends T> cast(Class cls, Class<T> cls2) {
        return cast(cls, null, cls2);
    }

    public static <T> Class<? extends T> cast(Class cls, Class<? extends T> cls2, Class<T> cls3) {
        return (cls == null || !cls3.isAssignableFrom(cls)) ? cls2 : cls;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void doWithSuperClass(Class cls, KernelLang.CallbackBreak<Class<?>> callbackBreak) {
        while (cls != null && cls != Object.class) {
            try {
                callbackBreak.doWith(cls);
                cls = cls.getSuperclass();
            } catch (KernelLang.BreakException unused) {
                return;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void doWithAncestClass(Class cls, KernelLang.CallbackBreak<Class<?>> callbackBreak) {
        while (cls != null && cls != Object.class) {
            try {
                callbackBreak.doWith(cls);
                for (Class<?> cls2 : cls.getInterfaces()) {
                    callbackBreak.doWith(cls2);
                }
                cls = cls.getSuperclass();
            } catch (KernelLang.BreakException unused) {
                return;
            }
        }
    }
}
