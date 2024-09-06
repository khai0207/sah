package com.android.pc.ioc.core.kernel;

import com.android.pc.ioc.core.kernel.KernelLang;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public abstract class KernelObject {
    public static <T> T getValue(T t, T t2) {
        return t == null ? t2 : t;
    }

    public static void set(Object obj, String str, Object obj2) {
        declaredSet(obj, str, 0, false, obj2);
    }

    public static void declaredSet(Object obj, String str, Object obj2) {
        declaredSet(obj, str, 0, true, obj2);
    }

    public static boolean declaredSet(Object obj, String str, int i, boolean z, Object obj2) {
        Field declaredField;
        if (obj instanceof Class) {
            declaredField = (Field) KernelReflect.memberStatic(KernelReflect.declaredField((Class) obj, str, i, z));
        } else {
            declaredField = KernelReflect.declaredField(obj.getClass(), str, i, z);
        }
        return KernelReflect.set(obj, declaredField, obj2);
    }

    public static Object get(Object obj, String str) {
        return declaredGet(obj, str, 0, false);
    }

    public static Object declaredGet(Object obj, String str) {
        return declaredGet(obj, str, 0, true);
    }

    public static Object declaredGet(Object obj, String str, int i, boolean z) {
        Field declaredField;
        if (obj instanceof Class) {
            declaredField = (Field) KernelReflect.memberStatic(KernelReflect.declaredField((Class) obj, str, i, z));
        } else {
            declaredField = KernelReflect.declaredField(obj.getClass(), str, i, z);
        }
        return KernelReflect.get(obj, declaredField);
    }

    public static Object send(Object obj, String str, Object... objArr) {
        return send(obj, str, 0, true, true, KernelClass.parameterTypes(objArr), objArr);
    }

    public static Object send(Object obj, String str, Class[] clsArr, Object... objArr) {
        return send(obj, str, 0, false, false, clsArr, objArr);
    }

    public static Object send(Object obj, String str, int i, boolean z, boolean z2, Class[] clsArr, Object... objArr) {
        return declaredSend(obj, str, i, false, z, z2, clsArr, objArr);
    }

    public static Object declaredSend(Object obj, String str, Object... objArr) {
        return declaredSend(obj, str, 0, true, true, true, KernelClass.parameterTypes(objArr), objArr);
    }

    public static Object declaredSend(Object obj, String str, Class[] clsArr, Object... objArr) {
        return declaredSend(obj, str, 0, true, false, false, clsArr, objArr);
    }

    public static Object declaredSend(Object obj, String str, int i, boolean z, boolean z2, boolean z3, Class[] clsArr, Object... objArr) {
        Method assignableMethod;
        if (obj instanceof Class) {
            assignableMethod = (Method) KernelReflect.memberStatic(KernelReflect.assignableMethod((Class) obj, str, i, z, z2, z3, clsArr));
        } else {
            assignableMethod = KernelReflect.assignableMethod(obj.getClass(), str, i, z, z2, z3, clsArr);
        }
        return KernelReflect.invoke(obj, assignableMethod, objArr);
    }

    public static boolean setter(Object obj, Field field, Object obj2) {
        return setter(obj, field.getName(), field.getType(), obj2);
    }

    public static boolean setter(Object obj, String str, Object obj2) {
        return setter(obj, str, obj2.getClass(), obj2);
    }

    public static boolean setter(Object obj, String str, Class cls, Object obj2) {
        Method method = KernelClass.setter(obj.getClass(), str, cls);
        return method != null && KernelReflect.invoke(obj, false, method, obj2) == null;
    }

    public static boolean publicSetter(Object obj, Field field, Object obj2) {
        if (setter(obj, field, obj2)) {
            return true;
        }
        return Modifier.isPublic(field.getModifiers()) && KernelReflect.set(obj, field, obj2);
    }

    public static boolean publicSetter(Object obj, String str, Object obj2) {
        Method method = KernelClass.setter(obj.getClass(), str, obj2.getClass());
        if (method == null) {
            return setter(obj, str, obj2);
        }
        KernelReflect.invoke(obj, method, obj2);
        return true;
    }

    public static boolean declaredSetter(Object obj, Field field, Object obj2) {
        return setter(obj, field, obj2) || KernelReflect.set(obj, field, obj2);
    }

    public static Object getter(Object obj, Field field) {
        return getter(obj, field.getName(), field.getType());
    }

    public static Object getter(Object obj, String str) {
        return getter(obj, str, Object.class);
    }

    public static Object getter(Object obj, String str, Class cls) {
        Method method = KernelClass.getter(obj.getClass(), str, cls);
        if (method != null) {
            return KernelReflect.invoke(obj, method, new Object[0]);
        }
        return null;
    }

    public static Object publicGetter(Object obj, Field field) {
        Method method = KernelClass.getter(obj.getClass(), field);
        if (method == null) {
            if (Modifier.isPublic(field.getModifiers())) {
                return KernelReflect.get(obj, field);
            }
            return null;
        }
        return KernelReflect.invoke(obj, method, new Object[0]);
    }

    public static Object publicGetter(Object obj, String str) {
        Method method = KernelClass.getter(obj.getClass(), str);
        if (method == null) {
            return get(obj, str);
        }
        return KernelReflect.invoke(obj, method, new Object[0]);
    }

    public static Object declaredGetter(Object obj, Field field) {
        Method method = KernelClass.getter(obj.getClass(), field);
        if (method == null) {
            return KernelReflect.get(obj, field);
        }
        return KernelReflect.invoke(obj, method, new Object[0]);
    }

    public static Object declaredGetter(Object obj, String str) {
        Method method = KernelClass.getter(obj.getClass(), str);
        if (method == null) {
            return declaredGet(obj, str);
        }
        return KernelReflect.invoke(obj, method, new Object[0]);
    }

    public static Object expressGetter(Object obj, String str) {
        if (KernelString.isEmpty(str)) {
            return obj;
        }
        for (String str2 : str.split("\\.")) {
            if (obj == null) {
                return null;
            }
            if (str2.startsWith(":")) {
                Method method = KernelReflect.method(obj.getClass(), str2.substring(1), new Class[0]);
                if (method != null) {
                    obj = KernelReflect.invoke(obj, method, new Object[0]);
                }
            } else {
                obj = declaredGetter(obj, str2);
            }
        }
        return obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> T cast(Object obj, Class<T> cls) {
        if (obj == 0 || !cls.isAssignableFrom(obj.getClass())) {
            return null;
        }
        return obj;
    }

    public static <T> T clone(T t) {
        try {
            if (t.getClass().isArray()) {
                return (T) KernelArray.clone(t);
            }
            T t2 = (T) t.getClass().newInstance();
            clone(t, t2);
            return t2;
        } catch (IllegalAccessException | InstantiationException unused) {
            return null;
        }
    }

    public static <T> void clone(final T t, final T t2) {
        if (t.getClass().isArray()) {
            KernelArray.copy(t, t2);
            return;
        }
        if (t instanceof Collection) {
            KernelCollection.copy((Collection) t, (Collection) t2);
        } else if (t instanceof Map) {
            KernelMap.copy((Map) t, (Map) t2);
        } else {
            KernelReflect.doWithDeclaredFields(t.getClass(), new KernelLang.CallbackBreak<Field>() { // from class: com.android.pc.ioc.core.kernel.KernelObject.1
                @Override // com.android.pc.ioc.core.kernel.KernelLang.CallbackBreak
                public void doWith(Field field) throws KernelLang.BreakException {
                    field.setAccessible(true);
                    try {
                        field.set(t2, field.get(t));
                    } catch (IllegalAccessException | IllegalArgumentException unused) {
                    }
                }
            });
        }
    }

    public static void copy(final Object obj, final Object obj2) {
        final Class<?> cls = obj2.getClass();
        if (obj.getClass().isArray()) {
            KernelArray.copy(obj, obj2);
            return;
        }
        if (obj instanceof Collection) {
            if (obj2 instanceof Collection) {
                KernelCollection.copy((Collection) obj, (Collection) obj2);
            }
        } else {
            if (obj instanceof Map) {
                if (obj2 instanceof Map) {
                    KernelMap.copy((Map) obj, (Map) obj2);
                    return;
                }
                return;
            }
            KernelReflect.doWithDeclaredFields(obj.getClass(), new KernelLang.CallbackBreak<Field>() { // from class: com.android.pc.ioc.core.kernel.KernelObject.2
                @Override // com.android.pc.ioc.core.kernel.KernelLang.CallbackBreak
                public void doWith(Field field) throws KernelLang.BreakException {
                    Field declaredField = KernelReflect.declaredField(cls, field.getName());
                    if (declaredField == null || !declaredField.getType().isAssignableFrom(field.getType())) {
                        return;
                    }
                    field.setAccessible(true);
                    try {
                        declaredField.set(obj2, field.get(obj));
                    } catch (IllegalAccessException | IllegalArgumentException unused) {
                    }
                }
            });
        }
    }

    public static byte[] serialize(Object obj) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            new ObjectOutputStream(byteArrayOutputStream).writeObject(obj);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException unused) {
            return null;
        }
    }

    public static Object unserialize(byte[] bArr) {
        try {
            return new ObjectInputStream(new ByteArrayInputStream(bArr)).readObject();
        } catch (IOException | ClassNotFoundException unused) {
            return null;
        }
    }

    public static <T> T serializeClone(T t) {
        byte[] serialize = serialize(t);
        if (serialize == null) {
            return null;
        }
        return (T) unserialize(serialize);
    }

    public static int hashCode(Object obj) {
        if (obj == null) {
            return 1;
        }
        return obj.hashCode();
    }

    public static boolean equals(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static Map<String, Object> getMap(final Object obj) {
        final HashMap hashMap = new HashMap();
        KernelReflect.doWithDeclaredFields(obj.getClass(), new KernelLang.CallbackBreak<Field>() { // from class: com.android.pc.ioc.core.kernel.KernelObject.3
            @Override // com.android.pc.ioc.core.kernel.KernelLang.CallbackBreak
            public void doWith(Field field) throws KernelLang.BreakException {
                Object publicGetter = KernelObject.publicGetter(obj, field);
                if (publicGetter != null) {
                    hashMap.put(field.getName(), publicGetter);
                }
            }
        });
        return hashMap;
    }

    public static void setMap(Object obj, Map<String, Object> map) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            publicSetter(obj, entry.getKey(), entry.getValue());
        }
    }
}
