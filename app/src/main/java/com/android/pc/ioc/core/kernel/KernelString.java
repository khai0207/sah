package com.android.pc.ioc.core.kernel;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

/* loaded from: classes.dex */
public abstract class KernelString {
    public static final int SEQUENCE_SIZE = 26;

    /* loaded from: classes.dex */
    public interface ImplodeBuilder {
        Object glue(String str, int i, Object obj, Object obj2);
    }

    public static String valueOf(Object obj) {
        if (obj == null) {
            return null;
        }
        return String.valueOf(obj);
    }

    public static boolean empty(String str) {
        return "".equals(str);
    }

    public static boolean isEmpty(String str) {
        return str == null || empty(str);
    }

    public static String capitalize(String str) {
        char[] charArray = str.toCharArray();
        charArray[0] = Character.toLowerCase(charArray[0]);
        return String.valueOf(charArray);
    }

    public static String uncapitalize(String str) {
        char[] charArray = str.toCharArray();
        if (charArray.length < 2 || charArray[1] > 'Z' || charArray[1] < 'A') {
            charArray[0] = Character.toUpperCase(charArray[0]);
        }
        return String.valueOf(charArray);
    }

    public static String lastString(String str, char c) {
        int lastIndexOf = str.lastIndexOf(c);
        return lastIndexOf >= 0 ? str.substring(lastIndexOf + 1) : str;
    }

    public static String lastSubString(String str, char c) {
        int lastIndexOf = str.lastIndexOf(c);
        return lastIndexOf >= 0 ? str.substring(0, lastIndexOf - 1) : str;
    }

    public static String leftString(String str, int i) {
        return str.substring(0, i);
    }

    public static String leftSubString(String str, int i) {
        return str.substring(i);
    }

    public static String rightString(String str, int i) {
        return str.substring(str.length() - i);
    }

    public static String rightSubString(String str, int i) {
        return str.substring(0, str.length() - i);
    }

    public static boolean matchStrings(String str, String[] strArr) {
        if (strArr == null) {
            return false;
        }
        for (String str2 : strArr) {
            if (str.indexOf(str2) >= 0) {
                return true;
            }
        }
        return false;
    }

    public static String replaceLast(String str, String str2, String str3) {
        if (isEmpty(str) || isEmpty(str2)) {
            return str;
        }
        int lastIndexOf = str.lastIndexOf(str2);
        return str.substring(0, lastIndexOf) + str3 + str.substring(lastIndexOf + str2.length());
    }

    public static String getSequenceString(int i) {
        StringBuilder sb = new StringBuilder();
        while (true) {
            i -= 26;
            if (i <= 26) {
                break;
            }
            sb.append('z');
        }
        if (i >= 0) {
            sb.append((char) i);
        }
        return sb.toString();
    }

    public static String nextSequenceString(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(rightSubString(str, 1));
        char charAt = (char) (str.charAt(str.length() - 1) + 1);
        if (charAt <= 'z') {
            sb.append(charAt);
        } else {
            sb.append("za");
        }
        return sb.toString();
    }

    public static int compare(String str, String str2) {
        return compare(str, str2, str.length(), str2.length());
    }

    public static int compare(String str, String str2, int i, int i2) {
        if (i == 0) {
            return i2;
        }
        if (i2 == 0) {
            return i;
        }
        int[][] iArr = (int[][]) Array.newInstance((Class<?>) int.class, i + 1, i2 + 1);
        for (int i3 = 0; i3 <= i; i3++) {
            iArr[i3][0] = i3;
        }
        for (int i4 = 0; i4 <= i2; i4++) {
            iArr[0][i4] = i4;
        }
        for (int i5 = 0; i5 < i; i5++) {
            char charAt = str.charAt(i5);
            int i6 = 0;
            while (i6 < i2) {
                int i7 = i5 + 1;
                int i8 = i6 + 1;
                iArr[i7][i8] = KernelLang.min(iArr[i5][i8] + 1, iArr[i7][i6] + 1, iArr[i5][i6] + (charAt == str2.charAt(i6) ? 0 : 1));
                i6 = i8;
            }
        }
        return iArr[i][i2];
    }

    public static float similar(String str, String str2) {
        if (str == str2) {
            return 1.0f;
        }
        if (str == null) {
            return 0.0f;
        }
        int length = str.length();
        int length2 = str2.length();
        if (length == 0 && length2 == 0) {
            return 1.0f;
        }
        return 1.0f - (compare(str, str2, length, length2) / Math.max(str.length(), str2.length()));
    }

    public static String implode(Object[] objArr, String... strArr) {
        return implode(objArr, (ImplodeBuilder) null, (Object) null, strArr);
    }

    public static String implode(Object[] objArr, ImplodeBuilder implodeBuilder, Object obj, String... strArr) {
        StringBuilder sb = new StringBuilder();
        int length = strArr.length;
        String str = null;
        int i = 0;
        for (Object obj2 : objArr) {
            if (implodeBuilder == null) {
                if (str != null) {
                    sb.append(str);
                }
                sb.append(obj2);
            } else {
                implode(sb, implodeBuilder, str, i, obj2, obj);
            }
            i++;
            if (i >= length) {
                i = 0;
            }
            str = strArr[i];
        }
        return sb.toString();
    }

    public static String implode(Collection collection, String... strArr) {
        return implode(collection, (ImplodeBuilder) null, (Object) null, strArr);
    }

    public static String implode(Collection collection, ImplodeBuilder implodeBuilder, Object obj, String... strArr) {
        StringBuilder sb = new StringBuilder();
        int length = strArr.length;
        String str = null;
        int i = 0;
        for (Object obj2 : collection) {
            if (implodeBuilder == null) {
                if (str != null) {
                    sb.append(str);
                }
                sb.append(obj2);
            } else {
                implode(sb, implodeBuilder, str, i, obj2, obj);
            }
            i++;
            if (i >= length) {
                i = 0;
            }
            str = strArr[i];
        }
        return sb.toString();
    }

    public static String implode(Map map, String... strArr) {
        return implode((Map<?, ?>) map, (ImplodeBuilder) null, (Object) null, strArr);
    }

    public static String implode(Map<?, ?> map, ImplodeBuilder implodeBuilder, Object obj, String... strArr) {
        StringBuilder sb = new StringBuilder();
        int length = strArr.length;
        String str = null;
        int i = 0;
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            if (implodeBuilder == null) {
                if (str != null) {
                    sb.append(str);
                }
                sb.append(entry.getKey());
            } else {
                implode(sb, implodeBuilder, str, i, entry.getKey(), obj);
            }
            int i2 = i + 1;
            if (i2 >= length) {
                i2 = 0;
            }
            String str2 = strArr[i2];
            if (implodeBuilder == null) {
                if (str2 != null) {
                    sb.append(str2);
                }
                sb.append(entry.getValue());
            } else {
                implode(sb, implodeBuilder, str2, i2, entry.getValue(), obj);
            }
            i = i2 + 1;
            if (i >= length) {
                i = 0;
            }
            str = strArr[i];
        }
        return sb.toString();
    }

    protected static void implode(StringBuilder sb, ImplodeBuilder implodeBuilder, Object obj, int i, Object obj2, Object obj3) {
        Object glue = implodeBuilder.glue((String) obj, i, obj2, obj3);
        if (glue instanceof Object[]) {
            Object[] objArr = (Object[]) glue;
            if (objArr.length > 1) {
                obj = objArr[0];
                obj2 = objArr[1];
            }
            glue = null;
        }
        if (glue != null) {
            sb.append(glue);
            return;
        }
        if (obj != null) {
            sb.append(obj);
        }
        if (obj2 != null) {
            sb.append(obj2);
        }
    }
}
