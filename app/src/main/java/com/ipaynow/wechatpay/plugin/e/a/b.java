package com.ipaynow.wechatpay.plugin.e.a;

import com.alipay.sdk.m.q.h;
import com.android.pc.util.Handler_Bitmap;
import com.netease.nimlib.amazonaws.services.s3.internal.Constants;
import java.lang.reflect.Field;

/* loaded from: classes.dex */
public final class b {
    private static final String[] aN = {"int", "java.lang.String", "boolean", "char", "float", "double", "long", "short", "byte"};

    public static String g(Object obj) {
        boolean z;
        if (obj == null) {
            return "Object{object is null}";
        }
        if (!obj.toString().startsWith(String.valueOf(obj.getClass().getName()) + Handler_Bitmap.textChangLine)) {
            return obj.toString();
        }
        StringBuilder sb = new StringBuilder(String.valueOf(obj.getClass().getSimpleName()) + "{");
        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            String[] strArr = aN;
            int length = strArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    z = false;
                    break;
                }
                if (field.getType().getName().equalsIgnoreCase(strArr[i])) {
                    Object e = null;
                    try {
                        e = field.get(obj);
                    } catch (IllegalAccessException e2) {
                        e = e2;
                    } catch (Throwable unused) {
                    }
                    Object[] objArr = new Object[2];
                    objArr[0] = field.getName();
                    objArr[1] = e == null ? Constants.NULL_VERSION_ID : e.toString();
                    sb.append(String.format("%s=%s, ", objArr));
                    z = true;
                } else {
                    i++;
                }
            }
            if (!z) {
                sb.append(String.format("%s=%s, ", field.getName(), "Object"));
            }
        }
        return sb.replace(sb.length() - 2, sb.length() - 1, h.d).toString();
    }

    public static StackTraceElement q() {
        return Thread.currentThread().getStackTrace()[4];
    }
}
