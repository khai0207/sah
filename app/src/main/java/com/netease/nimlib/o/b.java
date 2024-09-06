package com.netease.nimlib.o;

import android.text.TextUtils;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;

/* compiled from: BaseUtil.java */
/* loaded from: classes.dex */
public class b {
    private static final String a = b.class.getSimpleName();

    public static boolean a(Collection collection) {
        return collection == null || collection.isEmpty();
    }

    public static <T> T a(T t) {
        if (t == null) {
            return null;
        }
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            new ObjectOutputStream(byteArrayOutputStream).writeObject(t);
            return (T) new ObjectInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray())).readObject();
        } catch (Exception e) {
            com.netease.nimlib.log.b.f(a, "deepClone err , origin = " + t + " , e = " + e.getMessage());
            return null;
        }
    }

    public static int a(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        try {
            return Integer.parseInt(str);
        } catch (Exception unused) {
            return 0;
        }
    }
}
