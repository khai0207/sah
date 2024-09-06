package com.netease.nimlib.net.trace.a;

import android.util.Log;
import com.netease.nimlib.amazonaws.services.s3.internal.Constants;

/* compiled from: Preconditions.java */
/* loaded from: classes.dex */
public final class b {
    public static <T> T a(T t) {
        if (t != null) {
            return t;
        }
        throw null;
    }

    public static void a(boolean z, String str, int i) {
        if (!z) {
            throw new IllegalArgumentException(a(str, Integer.valueOf(i)));
        }
    }

    public static String a(String str, Object... objArr) {
        int indexOf;
        String valueOf = String.valueOf(str);
        int i = 0;
        if (objArr == null) {
            objArr = new Object[]{"(Object[])null"};
        } else {
            for (int i2 = 0; i2 < objArr.length; i2++) {
                objArr[i2] = b(objArr[i2]);
            }
        }
        StringBuilder sb = new StringBuilder(valueOf.length() + (objArr.length * 16));
        int i3 = 0;
        while (i < objArr.length && (indexOf = valueOf.indexOf("%s", i3)) != -1) {
            sb.append((CharSequence) valueOf, i3, indexOf);
            sb.append(objArr[i]);
            i3 = indexOf + 2;
            i++;
        }
        sb.append((CharSequence) valueOf, i3, valueOf.length());
        if (i < objArr.length) {
            sb.append(" [");
            sb.append(objArr[i]);
            for (int i4 = i + 1; i4 < objArr.length; i4++) {
                sb.append(", ");
                sb.append(objArr[i4]);
            }
            sb.append(']');
        }
        return sb.toString();
    }

    private static String b(Object obj) {
        if (obj == null) {
            return Constants.NULL_VERSION_ID;
        }
        try {
            return obj.toString();
        } catch (Exception e) {
            String str = obj.getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(obj));
            if (com.netease.nimlib.log.b.a.a()) {
                Log.w("Preconditions", "Exception during lenientFormat for " + str, e);
            }
            return "<" + str + " threw " + e.getClass().getName() + ">";
        }
    }
}
