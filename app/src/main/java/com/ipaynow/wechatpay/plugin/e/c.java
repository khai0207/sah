package com.ipaynow.wechatpay.plugin.e;

import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.alipay.sdk.m.q.h;
import java.util.Collection;
import java.util.Map;
import java.util.MissingFormatArgumentException;

/* loaded from: classes.dex */
public final class c {
    private static /* synthetic */ int[] aM;

    protected c() {
    }

    private static String a(StackTraceElement stackTraceElement) {
        String stackTraceElement2 = stackTraceElement.toString();
        String substring = stackTraceElement2.substring(stackTraceElement2.lastIndexOf(40), stackTraceElement2.length());
        String className = stackTraceElement.getClassName();
        return String.format("%s%s.%s%s", TextUtils.isEmpty(b.aL) ? "" : b.aL, className.substring(className.lastIndexOf(".") + 1), stackTraceElement.getMethodName(), substring);
    }

    private static void a(a aVar, StackTraceElement stackTraceElement, Object obj) {
        Pair f;
        StringBuilder sb;
        String sb2;
        if (b.aK) {
            if (obj == null) {
                a(aVar, stackTraceElement, com.ipaynow.wechatpay.plugin.e.a.b.g(obj), new Object[0]);
                return;
            }
            String simpleName = obj.getClass().getSimpleName();
            if (obj instanceof Throwable) {
                String a = a(stackTraceElement);
                String obj2 = obj.toString();
                Exception exc = (Exception) obj;
                switch (p()[aVar.ordinal()]) {
                    case 1:
                        Log.v(a, obj2, exc);
                        return;
                    case 2:
                        Log.d(a, obj2, exc);
                        return;
                    case 3:
                        Log.i(a, obj2, exc);
                        return;
                    case 4:
                        Log.w(a, obj2, exc);
                        return;
                    case 5:
                        Log.e(a, obj2, exc);
                        return;
                    case 6:
                        Log.wtf(a, obj2, exc);
                        return;
                    default:
                        return;
                }
            }
            if (obj instanceof String) {
                a(aVar, stackTraceElement, (String) obj, new Object[0]);
                return;
            }
            if (obj.getClass().isArray()) {
                int i = 0;
                for (int i2 = 0; i2 < obj.toString().length() && obj.toString().charAt(i2) == '['; i2++) {
                    i++;
                }
                if (i == 1) {
                    f = com.ipaynow.wechatpay.plugin.e.a.a.f(obj);
                    sb = new StringBuilder(String.valueOf(simpleName.replace("[]", "[" + f.first + "] {\n")));
                } else {
                    if (i != 2) {
                        sb2 = "Temporarily not support more than two dimensional Array!";
                        a(aVar, stackTraceElement, String.valueOf(sb2) + h.d, new Object[0]);
                        return;
                    }
                    f = com.ipaynow.wechatpay.plugin.e.a.a.e(obj);
                    Pair pair = (Pair) f.first;
                    sb = new StringBuilder(String.valueOf(simpleName.replace("[][]", "[" + pair.first + "][" + pair.second + "] {\n")));
                }
                sb.append(f.second);
                sb.append("\n");
                sb2 = sb.toString();
                a(aVar, stackTraceElement, String.valueOf(sb2) + h.d, new Object[0]);
                return;
            }
            if (!(obj instanceof Collection)) {
                if (!(obj instanceof Map)) {
                    a(aVar, stackTraceElement, com.ipaynow.wechatpay.plugin.e.a.b.g(obj), new Object[0]);
                    return;
                }
                String str = String.valueOf(simpleName) + " {\n";
                Map map = (Map) obj;
                for (Object obj3 : map.keySet()) {
                    str = String.valueOf(str) + String.format("[%s -> %s]\n", com.ipaynow.wechatpay.plugin.e.a.b.g(obj3), com.ipaynow.wechatpay.plugin.e.a.b.g(map.get(obj3)));
                }
                a(aVar, stackTraceElement, String.valueOf(str) + h.d, new Object[0]);
                return;
            }
            Collection collection = (Collection) obj;
            String format = String.format("%s size = %d [\n", simpleName, Integer.valueOf(collection.size()));
            if (!collection.isEmpty()) {
                int i3 = 0;
                for (Object obj4 : collection) {
                    StringBuilder sb3 = new StringBuilder(String.valueOf(format));
                    Object[] objArr = new Object[3];
                    objArr[0] = Integer.valueOf(i3);
                    objArr[1] = com.ipaynow.wechatpay.plugin.e.a.b.g(obj4);
                    int i4 = i3 + 1;
                    objArr[2] = i3 < collection.size() - 1 ? ",\n" : "\n";
                    sb3.append(String.format("[%d]:%s%s", objArr));
                    format = sb3.toString();
                    i3 = i4;
                }
            }
            a(aVar, stackTraceElement, String.valueOf(format) + "\n]", new Object[0]);
        }
    }

    private static void a(a aVar, StackTraceElement stackTraceElement, String str, Object... objArr) {
        if (b.aK) {
            String a = a(stackTraceElement);
            if (objArr.length > 0) {
                try {
                    str = String.format(str, objArr);
                } catch (MissingFormatArgumentException unused) {
                }
            }
            switch (p()[aVar.ordinal()]) {
                case 1:
                    Log.v(a, str);
                    return;
                case 2:
                    Log.d(a, str);
                    return;
                case 3:
                    Log.i(a, str);
                    return;
                case 4:
                    Log.w(a, str);
                    return;
                case 5:
                    Log.e(a, str);
                    return;
                case 6:
                    Log.wtf(a, str);
                    return;
                default:
                    return;
            }
        }
    }

    public static void a(StackTraceElement stackTraceElement, Object obj) {
        a(a.Debug, stackTraceElement, obj);
    }

    public static void b(StackTraceElement stackTraceElement, Object obj) {
        a(a.Error, stackTraceElement, obj);
    }

    public static void c(StackTraceElement stackTraceElement, Object obj) {
        a(a.Warn, stackTraceElement, obj);
    }

    public static void d(StackTraceElement stackTraceElement, Object obj) {
        a(a.Info, stackTraceElement, obj);
    }

    private static /* synthetic */ int[] p() {
        int[] iArr = aM;
        if (iArr != null) {
            return iArr;
        }
        int[] iArr2 = new int[a.valuesCustom().length];
        try {
            iArr2[a.Debug.ordinal()] = 2;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr2[a.Error.ordinal()] = 5;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            iArr2[a.Info.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            iArr2[a.Verbose.ordinal()] = 1;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            iArr2[a.Warn.ordinal()] = 4;
        } catch (NoSuchFieldError unused5) {
        }
        try {
            iArr2[a.Wtf.ordinal()] = 6;
        } catch (NoSuchFieldError unused6) {
        }
        aM = iArr2;
        return iArr2;
    }
}
