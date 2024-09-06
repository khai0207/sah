package com.ta.utdid2.a.a;

import android.content.Context;
import android.text.TextUtils;
import java.util.Random;

/* loaded from: classes.dex */
public class d {
    public static String a() {
        int currentTimeMillis = (int) (System.currentTimeMillis() / 1000);
        int nanoTime = (int) System.nanoTime();
        int nextInt = new Random().nextInt();
        int nextInt2 = new Random().nextInt();
        byte[] bytes = c.getBytes(currentTimeMillis);
        byte[] bytes2 = c.getBytes(nanoTime);
        byte[] bytes3 = c.getBytes(nextInt);
        byte[] bytes4 = c.getBytes(nextInt2);
        byte[] bArr = new byte[16];
        System.arraycopy(bytes, 0, bArr, 0, 4);
        System.arraycopy(bytes2, 0, bArr, 4, 4);
        System.arraycopy(bytes3, 0, bArr, 8, 4);
        System.arraycopy(bytes4, 0, bArr, 12, 4);
        return b.encodeToString(bArr, 2);
    }

    public static String b() {
        String str = g.get("ro.aliyun.clouduuid", "");
        if (TextUtils.isEmpty(str)) {
            str = g.get("ro.sys.aliyun.clouduuid", "");
        }
        return TextUtils.isEmpty(str) ? c() : str;
    }

    public static String b(Context context) {
        return "";
    }

    public static String c() {
        try {
            return (String) Class.forName("com.yunos.baseservice.clouduuid.CloudUUID").getMethod("getCloudUUID", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception unused) {
            return "";
        }
    }

    public static String a(Context context) {
        String b = f.m59a((String) null) ? b() : null;
        return f.m59a(b) ? a() : b;
    }
}
