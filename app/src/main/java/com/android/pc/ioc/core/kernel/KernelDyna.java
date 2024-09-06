package com.android.pc.ioc.core.kernel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/* loaded from: classes.dex */
public abstract class KernelDyna {
    public static final DateFormat[] DATE_FORMAT_ARRAY;
    public static final DateFormat DATE_FORMAT_TIME;
    public static final Byte BYTE_ZERO = (byte) 0;
    public static final Short SHORT_ZERO = 0;
    public static final Integer INTEGER_ZERO = 0;
    public static final Long LONG_ZERO = 0L;
    public static final Float FLOAT_ZERO = Float.valueOf(0.0f);
    public static final Double DOUBLE_ZERO = Double.valueOf(0.0d);
    public static final Boolean BOOLEAN_ZERO = false;
    public static final Character CHARACTER_ZERO = 0;
    public static final Date DATE_ZERO = new Date(0);
    public static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final DateFormat DATE_FORMAT_DAY = new SimpleDateFormat("yyyy-MM-dd");

    static {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        DATE_FORMAT_TIME = simpleDateFormat;
        DATE_FORMAT_ARRAY = new DateFormat[]{DATE_FORMAT, DATE_FORMAT_DAY, simpleDateFormat, DateFormat.getDateTimeInstance(), DateFormat.getDateTimeInstance(1, 1), DateFormat.getDateTimeInstance(2, 2), new SimpleDateFormat("EEE MMM d hh:mm:ss a z yyyy"), new SimpleDateFormat("EEE MMM d HH:mm:ss z yyyy"), new SimpleDateFormat("MM/dd/yy hh:mm:ss a"), new SimpleDateFormat("MM/dd/yy")};
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> T to(Object obj, Class<T> cls) {
        if (obj == 0) {
            return (T) nullTo(cls);
        }
        if (cls.isAssignableFrom(obj.getClass())) {
            return obj;
        }
        if (obj instanceof Number) {
            return (T) numberTo((Number) obj, cls);
        }
        if (obj instanceof Date) {
            return (T) dateTo((Date) obj, cls);
        }
        if (obj instanceof String) {
            return (T) stringTo((String) obj, cls);
        }
        return (T) nullTo(cls);
    }

    public static <T> T nullTo(Class<T> cls) {
        if (cls == Byte.TYPE) {
            return (T) BYTE_ZERO;
        }
        if (cls == Short.TYPE) {
            return (T) SHORT_ZERO;
        }
        if (cls == Integer.TYPE) {
            return (T) INTEGER_ZERO;
        }
        if (cls == Long.TYPE) {
            return (T) LONG_ZERO;
        }
        if (cls == Float.TYPE) {
            return (T) FLOAT_ZERO;
        }
        if (cls == Double.TYPE) {
            return (T) DOUBLE_ZERO;
        }
        if (cls == Boolean.TYPE) {
            return (T) BOOLEAN_ZERO;
        }
        if (cls == Character.TYPE) {
            return (T) CHARACTER_ZERO;
        }
        return null;
    }

    public static <T> T numberTo(Number number, Class<T> cls) {
        if (cls == Byte.class || cls == Byte.TYPE) {
            return (T) Byte.valueOf(number.byteValue());
        }
        if (cls == Short.class || cls == Short.TYPE) {
            return (T) Short.valueOf(number.shortValue());
        }
        if (cls == Integer.class || cls == Integer.TYPE) {
            return (T) Integer.valueOf(number.intValue());
        }
        if (cls == Long.class || cls == Long.TYPE) {
            return (T) Long.valueOf(number.longValue());
        }
        if (cls == Float.class || cls == Float.TYPE) {
            return (T) Float.valueOf(number.floatValue());
        }
        if (cls == Double.class || cls == Double.TYPE) {
            return (T) Double.valueOf(number.doubleValue());
        }
        if (cls == Boolean.class || cls == Boolean.TYPE) {
            return (T) Boolean.valueOf(number.byteValue() != 0);
        }
        if (cls == Character.class || cls == Character.TYPE) {
            return (T) Character.valueOf((char) number.byteValue());
        }
        if (cls == Date.class) {
            return (T) new Date(number.longValue());
        }
        if (cls.isAssignableFrom(String.class)) {
            return (T) number.toString();
        }
        return null;
    }

    public static <T> T dateTo(Date date, Class<T> cls) {
        if (cls == Byte.class || cls == Byte.TYPE) {
            return (T) Byte.valueOf(Long.valueOf(date.getTime()).byteValue());
        }
        if (cls == Short.class || cls == Short.TYPE) {
            return (T) Short.valueOf(Long.valueOf(date.getTime()).shortValue());
        }
        if (cls == Integer.class || cls == Integer.TYPE) {
            return (T) Integer.valueOf(Long.valueOf(date.getTime()).intValue());
        }
        if (cls == Long.class || cls == Long.TYPE) {
            return (T) Long.valueOf(date.getTime());
        }
        if (cls == Float.class || cls == Float.TYPE) {
            return (T) Float.valueOf(Long.valueOf(date.getTime()).floatValue());
        }
        if (cls == Double.class || cls == Double.TYPE) {
            return (T) Double.valueOf(Long.valueOf(date.getTime()).doubleValue());
        }
        if (cls == Boolean.class || cls == Boolean.TYPE) {
            return (T) Boolean.valueOf(Long.valueOf(date.getTime()).byteValue() != 0);
        }
        if (cls == Character.class || cls == Character.TYPE) {
            return (T) Character.valueOf((char) Long.valueOf(date.getTime()).byteValue());
        }
        if (cls.isAssignableFrom(String.class)) {
            return (T) toString(date);
        }
        return null;
    }

    public static String toString(Date date) {
        return toString(date, 0);
    }

    public static String toString(Date date, int i) {
        try {
            return DATE_FORMAT_ARRAY[i].format(date);
        } catch (Exception unused) {
            return null;
        }
    }

    public static <T> T stringTo(String str, Class<T> cls) {
        if (KernelString.empty(str)) {
            return (T) nullTo(cls);
        }
        return (T) stringTo(str, cls, null);
    }

    public static <T> T stringTo(String str, Class<T> cls, boolean[] zArr) {
        if (cls == Byte.TYPE) {
            return (T) toByte(str);
        }
        if (cls == Byte.class) {
            return (T) toByte(str, null);
        }
        if (cls == Short.TYPE) {
            return (T) toShort(str);
        }
        if (cls == Short.class) {
            return (T) toShort(str, null);
        }
        if (cls == Integer.TYPE) {
            return (T) toInteger(str);
        }
        if (cls == Integer.class) {
            return (T) toInteger(str, null);
        }
        if (cls == Long.TYPE) {
            return (T) toLong(str);
        }
        if (cls == Long.class) {
            return (T) toLong(str, null);
        }
        if (cls == Float.TYPE) {
            return (T) toFloat(str);
        }
        if (cls == Float.class) {
            return (T) toFloat(str, null);
        }
        if (cls == Double.TYPE) {
            return (T) toDouble(str);
        }
        if (cls == Double.class) {
            return (T) toDouble(str, null);
        }
        if (cls == Boolean.TYPE) {
            return (T) toBoolean(str);
        }
        if (cls == Boolean.class) {
            return (T) toBoolean(str, null);
        }
        if (cls == Character.TYPE) {
            return (T) toCharacter(str);
        }
        if (cls == Character.class) {
            return (T) toBoolean(str, null);
        }
        if (cls == Date.class) {
            return (T) toDate(str, DATE_ZERO);
        }
        if (cls == Enum.class) {
            return (T) Enum.valueOf(cls, str);
        }
        if (zArr != null && zArr.length > 0) {
            zArr[0] = !zArr[0];
        }
        return null;
    }

    public static Byte toByte(String str) {
        return toByte(str, BYTE_ZERO);
    }

    public static Byte toByte(String str, Byte b) {
        try {
            return Byte.valueOf(str);
        } catch (Exception unused) {
            return b;
        }
    }

    public static Short toShort(String str) {
        return toShort(str, SHORT_ZERO);
    }

    public static Short toShort(String str, Short sh) {
        try {
            return Short.valueOf(str);
        } catch (Exception unused) {
            return sh;
        }
    }

    public static Integer toInteger(String str) {
        return toInteger(str, INTEGER_ZERO);
    }

    public static Integer toInteger(String str, Integer num) {
        try {
            return Integer.valueOf(str);
        } catch (Exception unused) {
            return num;
        }
    }

    public static Long toLong(String str) {
        return toLong(str, LONG_ZERO);
    }

    public static Long toLong(String str, Long l) {
        try {
            return Long.valueOf(str);
        } catch (Exception unused) {
            return l;
        }
    }

    public static Float toFloat(String str) {
        return toFloat(str, FLOAT_ZERO);
    }

    public static Float toFloat(String str, Float f) {
        try {
            return Float.valueOf(str);
        } catch (Exception unused) {
            return f;
        }
    }

    public static Double toDouble(String str) {
        return toDouble(str, DOUBLE_ZERO);
    }

    public static Double toDouble(String str, Double d) {
        try {
            return Double.valueOf(str);
        } catch (Exception unused) {
            return d;
        }
    }

    public static Boolean toBoolean(String str) {
        return toBoolean(str, BOOLEAN_ZERO);
    }

    public static Boolean toBoolean(String str, Boolean bool) {
        try {
            return Boolean.valueOf(str);
        } catch (Exception unused) {
            return bool;
        }
    }

    public static Character toCharacter(String str) {
        return toCharacter(str, CHARACTER_ZERO);
    }

    public static Character toCharacter(String str, Character ch) {
        try {
            return Character.valueOf(str.charAt(0));
        } catch (Exception unused) {
            return ch;
        }
    }

    public static Date toDate(Object obj) {
        return (Date) to(obj, Date.class);
    }

    public static Date toDate(String str) {
        return toDate(str, DATE_ZERO);
    }

    public static Date toDate(String str, Date date) {
        for (DateFormat dateFormat : DATE_FORMAT_ARRAY) {
            try {
                return dateFormat.parse(str);
            } catch (Exception unused) {
            }
        }
        return date;
    }
}
