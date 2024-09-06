package com.android.pc.ioc.core.kernel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* loaded from: classes.dex */
public abstract class KernelArray {

    /* loaded from: classes.dex */
    public interface ArrayAccessor {
        Object get(Object obj, int i) throws IllegalArgumentException, ArrayIndexOutOfBoundsException;

        Object newInstance(int i);

        void set(Object obj, int i, Object obj2) throws IllegalArgumentException, ArrayIndexOutOfBoundsException;
    }

    /* loaded from: classes.dex */
    public enum EnumArrayAccessor implements ArrayAccessor {
        Byte { // from class: com.android.pc.ioc.core.kernel.KernelArray.EnumArrayAccessor.1
            @Override // com.android.pc.ioc.core.kernel.KernelArray.ArrayAccessor
            public Object newInstance(int i) {
                return new byte[i];
            }

            @Override // com.android.pc.ioc.core.kernel.KernelArray.ArrayAccessor
            public Object get(Object obj, int i) {
                return Byte.valueOf(Array.getByte(obj, i));
            }

            @Override // com.android.pc.ioc.core.kernel.KernelArray.ArrayAccessor
            public void set(Object obj, int i, Object obj2) {
                Array.setByte(obj, i, ((Byte) obj2).byteValue());
            }
        },
        Short { // from class: com.android.pc.ioc.core.kernel.KernelArray.EnumArrayAccessor.2
            @Override // com.android.pc.ioc.core.kernel.KernelArray.ArrayAccessor
            public Object newInstance(int i) {
                return new short[i];
            }

            @Override // com.android.pc.ioc.core.kernel.KernelArray.ArrayAccessor
            public Object get(Object obj, int i) {
                return Short.valueOf(Array.getShort(obj, i));
            }

            @Override // com.android.pc.ioc.core.kernel.KernelArray.ArrayAccessor
            public void set(Object obj, int i, Object obj2) {
                Array.setShort(obj, i, ((Short) obj2).shortValue());
            }
        },
        Integer { // from class: com.android.pc.ioc.core.kernel.KernelArray.EnumArrayAccessor.3
            @Override // com.android.pc.ioc.core.kernel.KernelArray.ArrayAccessor
            public Object newInstance(int i) {
                return new int[i];
            }

            @Override // com.android.pc.ioc.core.kernel.KernelArray.ArrayAccessor
            public Object get(Object obj, int i) {
                return Byte.valueOf(Array.getByte(obj, i));
            }

            @Override // com.android.pc.ioc.core.kernel.KernelArray.ArrayAccessor
            public void set(Object obj, int i, Object obj2) {
                Array.setInt(obj, i, ((Integer) obj2).intValue());
            }
        },
        Long { // from class: com.android.pc.ioc.core.kernel.KernelArray.EnumArrayAccessor.4
            @Override // com.android.pc.ioc.core.kernel.KernelArray.ArrayAccessor
            public Object newInstance(int i) {
                return new long[i];
            }

            @Override // com.android.pc.ioc.core.kernel.KernelArray.ArrayAccessor
            public Object get(Object obj, int i) {
                return Long.valueOf(Array.getLong(obj, i));
            }

            @Override // com.android.pc.ioc.core.kernel.KernelArray.ArrayAccessor
            public void set(Object obj, int i, Object obj2) {
                Array.setLong(obj, i, ((Long) obj2).longValue());
            }
        },
        Float { // from class: com.android.pc.ioc.core.kernel.KernelArray.EnumArrayAccessor.5
            @Override // com.android.pc.ioc.core.kernel.KernelArray.ArrayAccessor
            public Object newInstance(int i) {
                return new float[i];
            }

            @Override // com.android.pc.ioc.core.kernel.KernelArray.ArrayAccessor
            public Object get(Object obj, int i) {
                return Float.valueOf(Array.getFloat(obj, i));
            }

            @Override // com.android.pc.ioc.core.kernel.KernelArray.ArrayAccessor
            public void set(Object obj, int i, Object obj2) {
                Array.setFloat(obj, i, ((Float) obj2).floatValue());
            }
        },
        Double { // from class: com.android.pc.ioc.core.kernel.KernelArray.EnumArrayAccessor.6
            @Override // com.android.pc.ioc.core.kernel.KernelArray.ArrayAccessor
            public Object newInstance(int i) {
                return new double[i];
            }

            @Override // com.android.pc.ioc.core.kernel.KernelArray.ArrayAccessor
            public Object get(Object obj, int i) {
                return Double.valueOf(Array.getDouble(obj, i));
            }

            @Override // com.android.pc.ioc.core.kernel.KernelArray.ArrayAccessor
            public void set(Object obj, int i, Object obj2) {
                Array.setDouble(obj, i, ((Double) obj2).doubleValue());
            }
        },
        Boolean { // from class: com.android.pc.ioc.core.kernel.KernelArray.EnumArrayAccessor.7
            @Override // com.android.pc.ioc.core.kernel.KernelArray.ArrayAccessor
            public Object newInstance(int i) {
                return new boolean[i];
            }

            @Override // com.android.pc.ioc.core.kernel.KernelArray.ArrayAccessor
            public Object get(Object obj, int i) {
                return Boolean.valueOf(Array.getBoolean(obj, i));
            }

            @Override // com.android.pc.ioc.core.kernel.KernelArray.ArrayAccessor
            public void set(Object obj, int i, Object obj2) {
                Array.setBoolean(obj, i, ((Boolean) obj2).booleanValue());
            }
        },
        Character { // from class: com.android.pc.ioc.core.kernel.KernelArray.EnumArrayAccessor.8
            @Override // com.android.pc.ioc.core.kernel.KernelArray.ArrayAccessor
            public Object newInstance(int i) {
                return new char[i];
            }

            @Override // com.android.pc.ioc.core.kernel.KernelArray.ArrayAccessor
            public Object get(Object obj, int i) {
                return Character.valueOf(Array.getChar(obj, i));
            }

            @Override // com.android.pc.ioc.core.kernel.KernelArray.ArrayAccessor
            public void set(Object obj, int i, Object obj2) {
                Array.setChar(obj, i, ((Character) obj2).charValue());
            }
        },
        Object { // from class: com.android.pc.ioc.core.kernel.KernelArray.EnumArrayAccessor.9
            @Override // com.android.pc.ioc.core.kernel.KernelArray.ArrayAccessor
            public Object newInstance(int i) {
                return new Object[i];
            }

            @Override // com.android.pc.ioc.core.kernel.KernelArray.ArrayAccessor
            public Object get(Object obj, int i) {
                return Array.get(obj, i);
            }

            @Override // com.android.pc.ioc.core.kernel.KernelArray.ArrayAccessor
            public void set(Object obj, int i, Object obj2) {
                Array.set(obj, i, obj2);
            }
        }
    }

    public static <T> T get(T[] tArr, int i) {
        if (tArr == null || i < 0 || i >= tArr.length) {
            return null;
        }
        return tArr[i];
    }

    public static <T> void set(T[] tArr, T t) {
        set(tArr, t, tArr.length);
    }

    public static <T> void set(T[] tArr, T t, int i) {
        set(tArr, t, 0, i);
    }

    public static <T> void set(T[] tArr, T t, int i, int i2) {
        while (i < i2) {
            tArr[i] = t;
            i++;
        }
    }

    public static <T> T[] repeat(T t, int i) {
        T[] tArr = (T[]) ((Object[]) forComponentType(t.getClass()).newInstance(i));
        set(tArr, t, i);
        return tArr;
    }

    public static <T> T[] repeat(T t, int i, Class<T> cls) {
        T[] tArr = (T[]) ((Object[]) Array.newInstance((Class<?>) cls, i));
        set(tArr, t, i);
        return tArr;
    }

    public static <T> boolean contain(T[] tArr, T t) {
        for (T t2 : tArr) {
            if (t2.equals(t)) {
                return true;
            }
        }
        return false;
    }

    public static <T> boolean contains(T[] tArr, T... tArr2) {
        for (T t : tArr2) {
            if (!contain(tArr, t)) {
                return false;
            }
        }
        return true;
    }

    public static boolean equal(Object[] objArr, Object[] objArr2) {
        int length = objArr.length;
        if (length != objArr2.length) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (objArr[i] != objArr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static boolean equals(Object[] objArr, Object[] objArr2) {
        int length = objArr.length;
        if (length != objArr2.length) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (!KernelObject.equals(objArr[i], objArr2[i])) {
                return false;
            }
        }
        return true;
    }

    public static <T> T[] concat(T[] tArr, T[] tArr2) {
        T[] tArr3 = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), tArr.length + tArr2.length));
        System.arraycopy(tArr, 0, tArr3, 0, tArr.length);
        try {
            System.arraycopy(tArr2, 0, tArr3, tArr.length, tArr2.length);
            return tArr3;
        } catch (ArrayStoreException unused) {
            return tArr;
        }
    }

    public static <T> void copy(T[] tArr, T[] tArr2) {
        int length = tArr.length;
        for (int i = 0; i < length; i++) {
            tArr2[i] = tArr[i];
        }
    }

    public static <T> void copy(T[] tArr, Collection<T> collection) {
        for (T t : tArr) {
            collection.add(t);
        }
    }

    public static <T> List<T> toList(T[] tArr) {
        ArrayList arrayList = new ArrayList(tArr.length);
        copy((Object[]) tArr, (Collection) arrayList);
        return arrayList;
    }

    public static <T> Set<T> toSet(T[] tArr) {
        HashSet hashSet = new HashSet(tArr.length);
        copy((Object[]) tArr, (Collection) hashSet);
        return hashSet;
    }

    /* loaded from: classes.dex */
    public static class ComponentArrayAsscessor implements ArrayAccessor {
        private Class componentType;

        public ComponentArrayAsscessor(Class cls) {
            this.componentType = cls;
        }

        @Override // com.android.pc.ioc.core.kernel.KernelArray.ArrayAccessor
        public Object newInstance(int i) {
            return Array.newInstance((Class<?>) this.componentType, i);
        }

        @Override // com.android.pc.ioc.core.kernel.KernelArray.ArrayAccessor
        public Object get(Object obj, int i) {
            return Array.get(obj, i);
        }

        @Override // com.android.pc.ioc.core.kernel.KernelArray.ArrayAccessor
        public void set(Object obj, int i, Object obj2) {
            Array.set(obj, i, obj2);
        }
    }

    public static ArrayAccessor forClass(Class cls) {
        if (cls.isArray()) {
            return forComponentType(cls.getComponentType());
        }
        return null;
    }

    public static ArrayAccessor forComponentType(Class cls) {
        if (cls == Byte.TYPE) {
            return EnumArrayAccessor.Byte;
        }
        if (cls == Short.TYPE) {
            return EnumArrayAccessor.Short;
        }
        if (cls == Integer.TYPE) {
            return EnumArrayAccessor.Integer;
        }
        if (cls == Long.TYPE) {
            return EnumArrayAccessor.Long;
        }
        if (cls == Float.TYPE) {
            return EnumArrayAccessor.Float;
        }
        if (cls == Double.TYPE) {
            return EnumArrayAccessor.Double;
        }
        if (cls == Boolean.TYPE) {
            return EnumArrayAccessor.Boolean;
        }
        if (cls == Character.TYPE) {
            return EnumArrayAccessor.Character;
        }
        if (cls == Object.class) {
            return EnumArrayAccessor.Object;
        }
        return new ComponentArrayAsscessor(cls);
    }

    public static <T> void copy(Object obj, Object obj2) {
        if (obj.getClass().isArray() && obj2.getClass().isArray() && obj.getClass().getComponentType().isAssignableFrom(obj2.getClass().getComponentType())) {
            ArrayAccessor forClass = forClass(obj.getClass());
            int length = Array.getLength(obj);
            for (int i = 0; i < length; i++) {
                forClass.set(obj2, i, forClass.get(obj, i));
            }
        }
    }

    public static <T> T clone(T t) {
        ArrayAccessor forClass = forClass(t.getClass());
        if (forClass == null) {
            return null;
        }
        int length = Array.getLength(t);
        T t2 = (T) forClass.newInstance(length);
        for (int i = 0; i < length; i++) {
            forClass.set(t2, i, forClass.get(t, i));
        }
        return t2;
    }
}
