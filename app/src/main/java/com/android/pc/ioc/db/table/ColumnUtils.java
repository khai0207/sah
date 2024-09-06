package com.android.pc.ioc.db.table;

import android.text.TextUtils;
import com.android.pc.ioc.app.Ioc;
import com.android.pc.ioc.db.annotation.Check;
import com.android.pc.ioc.db.annotation.NotNull;
import com.android.pc.ioc.db.annotation.Transient;
import com.android.pc.ioc.db.annotation.Unique;
import com.android.pc.ioc.db.sqlite.FinderLazyLoader;
import com.android.pc.ioc.db.sqlite.ForeignLazyLoader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;

/* loaded from: classes.dex */
public class ColumnUtils {
    private ColumnUtils() {
    }

    public static Method getColumnGetMethod(Class<?> cls, Field field) {
        String name = field.getName();
        Method booleanColumnGetMethod = field.getType() == Boolean.TYPE ? getBooleanColumnGetMethod(cls, name) : null;
        if (booleanColumnGetMethod == null) {
            String str = "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
            try {
                booleanColumnGetMethod = cls.getDeclaredMethod(str, new Class[0]);
            } catch (NoSuchMethodException unused) {
                Ioc.getIoc().getLogger().d(str + " not exist");
            }
        }
        return (booleanColumnGetMethod != null || Object.class.equals(cls.getSuperclass())) ? booleanColumnGetMethod : getColumnGetMethod(cls.getSuperclass(), field);
    }

    public static Method getColumnSetMethod(Class<?> cls, Field field) {
        String name = field.getName();
        Method booleanColumnSetMethod = field.getType() == Boolean.TYPE ? getBooleanColumnSetMethod(cls, field) : null;
        if (booleanColumnSetMethod == null) {
            String str = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
            try {
                booleanColumnSetMethod = cls.getDeclaredMethod(str, field.getType());
            } catch (NoSuchMethodException unused) {
                Ioc.getIoc().getLogger().d(str + " not exist");
            }
        }
        return (booleanColumnSetMethod != null || Object.class.equals(cls.getSuperclass())) ? booleanColumnSetMethod : getColumnSetMethod(cls.getSuperclass(), field);
    }

    public static String getColumnNameByField(Field field) {
        com.android.pc.ioc.db.annotation.Column column = (com.android.pc.ioc.db.annotation.Column) field.getAnnotation(com.android.pc.ioc.db.annotation.Column.class);
        if (column != null && !TextUtils.isEmpty(column.column())) {
            return column.column();
        }
        com.android.pc.ioc.db.annotation.Id id = (com.android.pc.ioc.db.annotation.Id) field.getAnnotation(com.android.pc.ioc.db.annotation.Id.class);
        if (id != null && !TextUtils.isEmpty(id.column())) {
            return id.column();
        }
        com.android.pc.ioc.db.annotation.Foreign foreign = (com.android.pc.ioc.db.annotation.Foreign) field.getAnnotation(com.android.pc.ioc.db.annotation.Foreign.class);
        if (foreign != null && !TextUtils.isEmpty(foreign.column())) {
            return foreign.column();
        }
        if (((com.android.pc.ioc.db.annotation.Finder) field.getAnnotation(com.android.pc.ioc.db.annotation.Finder.class)) != null) {
            return field.getName();
        }
        return field.getName();
    }

    public static String getForeignColumnNameByField(Field field) {
        com.android.pc.ioc.db.annotation.Foreign foreign = (com.android.pc.ioc.db.annotation.Foreign) field.getAnnotation(com.android.pc.ioc.db.annotation.Foreign.class);
        if (foreign != null) {
            return foreign.foreign();
        }
        return field.getName();
    }

    public static Object getColumnDefaultValue(Field field) {
        com.android.pc.ioc.db.annotation.Column column = (com.android.pc.ioc.db.annotation.Column) field.getAnnotation(com.android.pc.ioc.db.annotation.Column.class);
        if (column == null || TextUtils.isEmpty(column.defaultValue())) {
            return null;
        }
        return valueStr2SimpleTypeFieldValue(field.getType(), column.defaultValue());
    }

    public static boolean isTransient(Field field) {
        return field.getAnnotation(Transient.class) != null;
    }

    public static boolean isForeign(Field field) {
        return field.getAnnotation(com.android.pc.ioc.db.annotation.Foreign.class) != null;
    }

    public static boolean isFinder(Field field) {
        return field.getAnnotation(com.android.pc.ioc.db.annotation.Finder.class) != null;
    }

    public static boolean isSimpleColumnType(Field field) {
        return isSimpleColumnType(field.getType());
    }

    public static boolean isSimpleColumnType(Class cls) {
        return cls.isPrimitive() || cls.equals(String.class) || cls.equals(Integer.class) || cls.equals(Long.class) || cls.equals(Date.class) || cls.equals(java.sql.Date.class) || cls.equals(Boolean.class) || cls.equals(Float.class) || cls.equals(Double.class) || cls.equals(Byte.class) || cls.equals(Short.class) || cls.equals(CharSequence.class) || cls.equals(Character.class);
    }

    public static boolean isUnique(Field field) {
        return field.getAnnotation(Unique.class) != null;
    }

    public static boolean isNotNull(Field field) {
        return field.getAnnotation(NotNull.class) != null;
    }

    public static String getCheck(Field field) {
        Check check = (Check) field.getAnnotation(Check.class);
        if (check != null) {
            return check.value();
        }
        return null;
    }

    public static Object valueStr2SimpleTypeFieldValue(Class cls, String str) {
        Object date;
        if (isSimpleColumnType(cls) && str != null) {
            if (cls.equals(String.class) || cls.equals(CharSequence.class)) {
                return str;
            }
            if (cls.equals(Integer.TYPE) || cls.equals(Integer.class)) {
                return Integer.valueOf(str);
            }
            if (cls.equals(Long.TYPE) || cls.equals(Long.class)) {
                return Long.valueOf(str);
            }
            if (cls.equals(java.sql.Date.class)) {
                date = new java.sql.Date(Long.valueOf(str).longValue());
            } else if (cls.equals(Date.class)) {
                date = new Date(Long.valueOf(str).longValue());
            } else {
                if (cls.equals(Boolean.TYPE) || cls.equals(Boolean.class)) {
                    return convert2Boolean(str);
                }
                if (cls.equals(Float.TYPE) || cls.equals(Float.class)) {
                    return Float.valueOf(str);
                }
                if (cls.equals(Double.TYPE) || cls.equals(Double.class)) {
                    return Double.valueOf(str);
                }
                if (cls.equals(Byte.TYPE) || cls.equals(Byte.class)) {
                    return Byte.valueOf(str);
                }
                if (cls.equals(Short.TYPE) || cls.equals(Short.class)) {
                    return Short.valueOf(str);
                }
                if (cls.equals(Character.TYPE) || cls.equals(Character.class)) {
                    return Character.valueOf(str.charAt(0));
                }
            }
            return date;
        }
        return null;
    }

    public static Class<?> getForeignEntityType(Foreign foreign) {
        Class<?> type = foreign.getColumnField().getType();
        return (type.equals(ForeignLazyLoader.class) || type.equals(List.class)) ? (Class) ((ParameterizedType) foreign.getColumnField().getGenericType()).getActualTypeArguments()[0] : type;
    }

    public static Class<?> getFinderTargetEntityType(Finder finder) {
        Class<?> type = finder.getColumnField().getType();
        return (type.equals(FinderLazyLoader.class) || type.equals(List.class)) ? (Class) ((ParameterizedType) finder.getColumnField().getGenericType()).getActualTypeArguments()[0] : type;
    }

    public static Boolean convert2Boolean(Object obj) {
        if (obj != null) {
            String obj2 = obj.toString();
            return Boolean.valueOf(obj2.length() == 1 ? "1".equals(obj2) : Boolean.valueOf(obj2).booleanValue());
        }
        return false;
    }

    public static Object convert2DbColumnValueIfNeeded(Object obj) {
        if (obj == null) {
            return obj;
        }
        if (obj instanceof Boolean) {
            return Integer.valueOf(((Boolean) obj).booleanValue() ? 1 : 0);
        }
        if (obj instanceof java.sql.Date) {
            return Long.valueOf(((java.sql.Date) obj).getTime());
        }
        return obj instanceof Date ? Long.valueOf(((Date) obj).getTime()) : obj;
    }

    public static String fieldType2DbType(Class<?> cls) {
        return (cls.equals(Integer.TYPE) || cls.equals(Integer.class) || cls.equals(Boolean.TYPE) || cls.equals(Boolean.class) || cls.equals(Date.class) || cls.equals(java.sql.Date.class) || cls.equals(Long.TYPE) || cls.equals(Long.class) || cls.equals(Byte.TYPE) || cls.equals(Byte.class) || cls.equals(Short.TYPE) || cls.equals(Short.class)) ? "INTEGER" : (cls.equals(Float.TYPE) || cls.equals(Float.class) || cls.equals(Double.TYPE) || cls.equals(Double.class)) ? "REAL" : "TEXT";
    }

    private static boolean isStartWithIs(String str) {
        return str != null && str.startsWith("is");
    }

    private static Method getBooleanColumnGetMethod(Class<?> cls, String str) {
        String str2 = "is" + str.substring(0, 1).toUpperCase() + str.substring(1);
        if (!isStartWithIs(str)) {
            str = str2;
        }
        try {
            return cls.getDeclaredMethod(str, new Class[0]);
        } catch (NoSuchMethodException unused) {
            Ioc.getIoc().getLogger().d(str + " not exist");
            return null;
        }
    }

    private static Method getBooleanColumnSetMethod(Class<?> cls, Field field) {
        String str;
        String name = field.getName();
        if (isStartWithIs(field.getName())) {
            str = "set" + name.substring(2, 3).toUpperCase() + name.substring(3);
        } else {
            str = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
        }
        try {
            return cls.getDeclaredMethod(str, field.getType());
        } catch (NoSuchMethodException unused) {
            Ioc.getIoc().getLogger().d(str + " not exist");
            return null;
        }
    }
}
