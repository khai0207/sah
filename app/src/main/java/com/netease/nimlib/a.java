package com.netease.nimlib;

import android.content.Context;
import android.text.TextUtils;
import com.netease.nimlib.n.b.i;
import java.io.File;

/* compiled from: AppDirs.java */
/* loaded from: classes.dex */
public final class a {
    public static String a;
    public static String b;
    private static String c;
    private static String d;

    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return a;
        }
        return d + "/" + str;
    }

    public static void a(Context context, String str) {
        if (context == null || str == null) {
            return;
        }
        String str2 = c;
        if (str2 == null || !str2.equals(str)) {
            try {
                String str3 = context.getApplicationInfo().dataDir;
                d = str3;
                if (TextUtils.isEmpty(str3)) {
                    d = "/data/data/" + context.getPackageName();
                }
                if (context.getCacheDir() == null) {
                    b = "/data/data/" + context.getPackageName() + "/cache";
                    File file = new File(b);
                    if (!file.exists()) {
                        file.mkdir();
                    }
                } else {
                    b = context.getCacheDir().getAbsolutePath();
                }
            } catch (Exception e) {
                com.netease.nimlib.n.e.a(i.kCreateDirectory, b, "AppDirs#init failed,exception = " + e);
                e.printStackTrace();
            }
            a = d + "/" + str;
            b += "/" + str;
            c = str;
            com.netease.nimlib.log.b.c("AppDir", "DATA " + a);
            com.netease.nimlib.log.b.c("AppDir", "CACHE " + b);
        }
    }
}
