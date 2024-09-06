package com.netease.nimlib.o.b;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import com.netease.nimlib.n.b.i;
import com.netease.nimlib.n.e;
import com.netease.nimlib.sdk.util.NIMUtil;
import java.io.File;
import java.io.IOException;

/* compiled from: NimExternalStorage.java */
/* loaded from: classes.dex */
public class a {
    protected static String a = ".nomedia";
    private static a d;
    private Context b;
    private String c = null;

    private a() {
    }

    public static synchronized a a() {
        a aVar;
        synchronized (a.class) {
            if (d == null) {
                d = new a();
            }
            aVar = d;
        }
        return aVar;
    }

    public void a(Context context, String str) {
        this.b = context;
        if (TextUtils.isEmpty(str)) {
            try {
                if (context.getCacheDir() != null) {
                    str = context.getCacheDir().getAbsolutePath() + "/nim/";
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        try {
            if (!TextUtils.isEmpty(str)) {
                File file = new File(str);
                if (!file.exists()) {
                    if (file.getParentFile().exists()) {
                        file.mkdir();
                    } else {
                        file.mkdirs();
                    }
                }
                if (file.exists() && !file.isFile()) {
                    this.c = str;
                    if (!str.endsWith("/")) {
                        this.c = str + "/";
                    }
                }
            }
            if (TextUtils.isEmpty(this.c)) {
                a(context);
            }
            e();
        } catch (Exception e) {
            e.a(i.kCreateDirectory, str, "NimExternalStorage#init failed,exception = " + e);
            throw e;
        }
    }

    private void a(Context context) {
        this.c = NIMUtil.getNimDefaultCacheDir(context);
    }

    private void e() {
        File file = new File(this.c);
        if (file.exists() && !file.isDirectory()) {
            file.delete();
        }
        boolean z = true;
        for (b bVar : b.values()) {
            z &= a(this.c + bVar.a());
        }
        if (z) {
            b(this.c);
        }
    }

    private boolean a(String str) {
        File file = new File(str);
        boolean exists = file.exists();
        return !exists ? file.mkdirs() : exists;
    }

    private void b(String str) {
        File file = new File(str + "/" + a);
        try {
            if (file.exists()) {
                return;
            }
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String a(String str, b bVar) {
        return a(str, bVar, false, false);
    }

    private String a(String str, b bVar, boolean z, boolean z2) {
        StringBuilder sb = new StringBuilder(a(bVar));
        if (!z) {
            sb.append(str);
        }
        String sb2 = sb.toString();
        File file = new File(sb2);
        return z2 ? (!file.exists() || (!(z && file.isDirectory()) && (z || file.isDirectory()))) ? "" : sb2 : sb2;
    }

    public String a(b bVar) {
        return this.c + bVar.a();
    }

    String b(String str, b bVar) {
        return TextUtils.isEmpty(str) ? "" : a(str, bVar, false, true);
    }

    boolean b() {
        if (Environment.getExternalStorageDirectory() == null) {
            com.netease.nimlib.log.b.f("NimExternalStorage", "isSdkStorageReady Environment.getExternalStorageDirectory() == null");
            return true;
        }
        if (this.c.startsWith(Environment.getExternalStorageDirectory().getAbsolutePath())) {
            return Environment.getExternalStorageState().equals("mounted");
        }
        return true;
    }

    public long c() {
        return c(this.c);
    }

    private long c(String str) {
        try {
            StatFs statFs = new StatFs(str);
            return statFs.getAvailableBlocks() * statFs.getBlockSize();
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

    String d() {
        return this.c;
    }
}
