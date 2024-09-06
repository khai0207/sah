package com.netease.nimlib.database;

import android.content.Context;
import com.kqg.main.constant.KV;
import java.io.File;

/* compiled from: DatabaseBackupUtils.java */
/* loaded from: classes.dex */
public class a {
    public static boolean a(final Context context, final String str, final g gVar) {
        com.netease.nimlib.c.b.a.c().a("TAG").post(new Runnable() { // from class: com.netease.nimlib.database.-$$Lambda$a$sIDSKw64kZzHA_lUVLVq2-pXnZc
            @Override // java.lang.Runnable
            public final void run() {
                a.b(context, str, gVar);
            }
        });
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void b(Context context, String str, g gVar) {
        try {
            com.netease.nimlib.log.b.d("db", String.format("backup database %s succeed: %s", str, Boolean.valueOf(c(context, str))));
            if (gVar.d()) {
                gVar.c();
            }
        } catch (Throwable th) {
            th.printStackTrace();
            com.netease.nimlib.log.b.e("DatabaseBackupUtils", "doBackup Exception:" + th, th);
        }
    }

    private static boolean c(Context context, String str) {
        String a = com.netease.nimlib.database.a.a.a(context, str);
        String a2 = com.netease.nimlib.database.a.a.a(context, str + ".bak");
        String a3 = com.netease.nimlib.database.a.a.a(context, str + ".bak.tmp");
        File file = new File(a);
        File file2 = new File(a2);
        File file3 = new File(a3);
        if (!file.exists()) {
            return false;
        }
        if (file2.exists()) {
            if (file3.exists()) {
                file3.delete();
            }
            com.netease.nimlib.net.a.c.a.b(a2, a3);
            file3 = new File(a3);
        }
        long a4 = com.netease.nimlib.net.a.c.a.a(a, a2, KV.GET_CODE_INTERVAL);
        if (a4 > 0) {
            com.netease.nimlib.net.a.c.a.b(a3);
        } else {
            if (a4 == -100) {
                com.netease.nimlib.log.b.e("DatabaseBackupUtils", String.format("doBackup timeout: %s", str));
            }
            com.netease.nimlib.net.a.c.a.b(a2);
            if (file3.exists()) {
                com.netease.nimlib.net.a.c.a.b(a3, a2);
            }
        }
        return a4 > 0;
    }

    public static boolean a(Context context, String str) {
        String a = com.netease.nimlib.database.a.a.a(context, str);
        String a2 = com.netease.nimlib.database.a.a.a(context, str + ".bak");
        String a3 = com.netease.nimlib.database.a.a.a(context, str + ".bak.tmp");
        new File(a);
        File file = new File(a2);
        File file2 = new File(a3);
        if (!file.exists() && !file2.exists()) {
            com.netease.nimlib.log.b.d("DatabaseBackupUtils", "restore false: dbBackupFile or dbTempFile not exist");
            return false;
        }
        if (file.exists()) {
            boolean a4 = a(a, a2);
            com.netease.nimlib.log.b.d("DatabaseBackupUtils", String.format("restore dbBackupFile: %s", Boolean.valueOf(a4)));
            if (a4) {
                return true;
            }
        }
        if (file2.exists()) {
            boolean a5 = a(a, a3);
            com.netease.nimlib.log.b.d("DatabaseBackupUtils", String.format("restore dbTempFile: %s", Boolean.valueOf(a5)));
            if (a5) {
                return true;
            }
        }
        return false;
    }

    private static boolean a(String str, String str2) {
        com.netease.nimlib.net.a.c.a.b(str);
        return com.netease.nimlib.net.a.c.a.b(str2, str);
    }

    public static void b(Context context, String str) {
        com.netease.nimlib.database.a.a.a(context, str);
        String a = com.netease.nimlib.database.a.a.a(context, str + ".bak");
        String a2 = com.netease.nimlib.database.a.a.a(context, str + ".bak.tmp");
        com.netease.nimlib.net.a.c.a.b(a);
        com.netease.nimlib.net.a.c.a.b(a2);
    }
}
