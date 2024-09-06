package com.netease.nimlib.o.b;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import android.util.Pair;
import com.netease.nimlib.n.b.i;
import com.netease.nimlib.n.e;
import java.io.File;

/* compiled from: NimStorageUtil.java */
/* loaded from: classes.dex */
public class c {
    public static void a(Context context, String str) {
        a.a().a(context, str);
    }

    public static String a(String str, b bVar) {
        return a(null, str, bVar, false);
    }

    private static String a(Context context, String str, b bVar, boolean z) {
        String a = a.a().a(str, bVar);
        if (TextUtils.isEmpty(a)) {
            return null;
        }
        try {
            File parentFile = new File(a).getParentFile();
            if (parentFile != null && !parentFile.exists()) {
                parentFile.mkdirs();
            }
            return a;
        } catch (Throwable th) {
            e.a(i.kCreateDirectory, a, "NimStorageUtil#getWritePath failed,fileName = " + str + ", fileType = " + bVar + ",exception = " + th);
            throw th;
        }
    }

    public static boolean a(b bVar) {
        return a.a().b() && a.a().c() >= bVar.b();
    }

    public static String b(String str, b bVar) {
        return a.a().b(str, bVar);
    }

    public static String a(Context context, String str, b bVar) {
        return a(context, str, bVar, true);
    }

    public static String b(b bVar) {
        return a.a().a(bVar);
    }

    public static String a() {
        return a.a().d();
    }

    public static Pair<Long, Long> b() {
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        long blockSizeLong = statFs.getBlockSizeLong();
        return new Pair<>(Long.valueOf(statFs.getBlockCountLong() * blockSizeLong), Long.valueOf(statFs.getAvailableBlocksLong() * blockSizeLong));
    }
}
