package com.netease.nimlib.database.encrypt;

import android.content.Context;
import android.text.TextUtils;
import com.netease.nimlib.biz.l;
import com.netease.nimlib.o.y;
import com.netease.nimlib.sdk.auth.LoginInfo;
import java.io.File;
import java.io.IOException;
import net.sqlcipher.database.SQLiteDatabase;

/* compiled from: EncryptedDatabaseRollbackUtils.java */
/* loaded from: classes.dex */
public class c {
    public static boolean a(Context context, String str, String str2) {
        if (com.netease.nimlib.c.i().rollbackSQLCipher) {
            return b(context, str, str2) || c(context, str, str2);
        }
        return false;
    }

    private static boolean b(Context context, String str, String str2) {
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
            com.netease.nimlib.log.b.v("--------- need not rollback - Msg --------- Key:" + TextUtils.isEmpty(str2) + " Uid:" + str);
            return false;
        }
        com.netease.nimlib.log.b.v("--------- need rollback - Msg --------- Key is not empty");
        return d(context, com.netease.nimlib.database.d.a(str, true), com.netease.nimlib.database.d.a(str, false));
    }

    private static boolean c(Context context, String str, String str2) {
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
            com.netease.nimlib.log.b.v("--------- need not rollback - Main --------- Key:" + TextUtils.isEmpty(str2) + " Uid:" + str);
            return false;
        }
        com.netease.nimlib.log.b.v("--------- need rollback - Main --------- Key is not empty");
        return d(context, com.netease.nimlib.database.b.a(str, true), com.netease.nimlib.database.b.a(str, false));
    }

    private static boolean d(Context context, String str, String str2) {
        if (!new File(com.netease.nimlib.database.a.a.a(context, str)).exists()) {
            com.netease.nimlib.log.b.v("--------- need not rollback：encrypted database not exists ---------");
            return false;
        }
        String a = com.netease.nimlib.database.a.a.a(context, str2);
        File file = new File(a);
        if (file.exists()) {
            File file2 = new File(a + ".rollback");
            if (!file2.exists() && file.renameTo(file2)) {
                return true;
            }
            if (file.renameTo(new File(a + ".rollback_" + y.c()))) {
                return true;
            }
            com.netease.nimlib.log.b.v("--------- need not rollback：plain database exists ---------");
            return false;
        }
        com.netease.nimlib.log.b.v("--------- need rollback ---------");
        return true;
    }

    private static boolean a(Context context, String str, String str2, String str3, String str4) throws IOException {
        SQLiteDatabase openDatabase;
        try {
            SQLiteDatabase.loadLibs(com.netease.nimlib.c.e());
            String a = com.netease.nimlib.database.a.a.a(context, str4);
            File file = new File(a);
            if (!file.exists()) {
                com.netease.nimlib.log.b.v("--------- rollback encDbFile not exists ---------");
                return false;
            }
            String a2 = com.netease.nimlib.database.a.a.a(context, str3);
            File file2 = new File(a2);
            if (file2.exists()) {
                com.netease.nimlib.log.b.v("--------- rollback plainDbFile exists ---------");
                File file3 = new File(a2 + ".rollback");
                if (!file3.exists()) {
                    if (!file2.renameTo(file3)) {
                        com.netease.nimlib.log.b.v("--------- rollback renameTo fail rollbackFile exists ---------");
                        return false;
                    }
                } else {
                    if (!file2.renameTo(new File(a2 + ".rollback_" + y.c()))) {
                        com.netease.nimlib.log.b.v("--------- rollback renameTo fail ---------");
                        return false;
                    }
                }
            }
            if (!file2.createNewFile()) {
                com.netease.nimlib.log.b.v("--------- rollback plainDbFile createNewFile fail ---------");
                return false;
            }
            SqlcipherUpdateHook a3 = d.a(str, a);
            if (a3 != null) {
                openDatabase = SQLiteDatabase.openOrCreateDatabase(a, str2, (SQLiteDatabase.CursorFactory) null, a3);
            } else {
                openDatabase = SQLiteDatabase.openDatabase(a, str2, (SQLiteDatabase.CursorFactory) null, 0);
            }
            openDatabase.rawExecSQL(String.format("PRAGMA key = '%s'", str2));
            openDatabase.rawExecSQL(String.format("ATTACH DATABASE '%s' AS plaintext KEY '%s';", a2, ""));
            openDatabase.rawExecSQL("SELECT sqlcipher_export('plaintext')");
            openDatabase.rawExecSQL("DETACH DATABASE plaintext;");
            openDatabase.close();
            if (a3 != null) {
                l.a(str, a3.getDbName(), a3.getSqlcipherVersion());
            }
            File file4 = new File(a + ".rollback");
            if (!file4.exists() && file.renameTo(file4)) {
                return true;
            }
            com.netease.nimlib.log.b.v("--------- rollback encDbFile renameTo ---------");
            boolean renameTo = file.renameTo(new File(a + ".rollback_" + y.c()));
            com.netease.nimlib.log.b.v(String.format("--------- rollback encDbFile %s ---------", Boolean.valueOf(renameTo)));
            return renameTo;
        } catch (Exception e) {
            e.printStackTrace();
            com.netease.nimlib.log.b.d("--------- rollback Exception ---------", e);
            return false;
        }
    }

    public static void a(LoginInfo loginInfo) {
        boolean z;
        boolean z2;
        if (com.netease.nimlib.c.i().rollbackSQLCipher) {
            if (b(com.netease.nimlib.c.e(), loginInfo.getAccount(), com.netease.nimlib.c.i().databaseEncryptKey)) {
                com.netease.nimlib.log.b.v("--------- rollback... Msg ---------");
                long d = y.d();
                try {
                    if (a(com.netease.nimlib.c.e(), loginInfo.getAccount(), com.netease.nimlib.c.i().databaseEncryptKey, com.netease.nimlib.database.d.a(loginInfo.getAccount(), false), com.netease.nimlib.database.d.a(loginInfo.getAccount(), true))) {
                        l.g(loginInfo.getAccount());
                    }
                    z2 = true;
                } catch (Exception e) {
                    e.printStackTrace();
                    z2 = false;
                }
                com.netease.nimlib.log.b.v(String.format("--------- rollback duration %sms success %s Msg ---------", Long.valueOf(y.d() - d), Boolean.valueOf(z2)));
            }
            if (c(com.netease.nimlib.c.e(), loginInfo.getAccount(), com.netease.nimlib.c.i().databaseEncryptKey)) {
                com.netease.nimlib.log.b.v("--------- rollback... Main ---------");
                long d2 = y.d();
                try {
                    if (a(com.netease.nimlib.c.e(), loginInfo.getAccount(), com.netease.nimlib.c.i().databaseEncryptKey, com.netease.nimlib.database.b.a(loginInfo.getAccount(), false), com.netease.nimlib.database.b.a(loginInfo.getAccount(), true))) {
                        l.f(loginInfo.getAccount());
                    }
                    z = true;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    z = false;
                }
                com.netease.nimlib.log.b.v(String.format("--------- rollback duration %sms success %s Main ---------", Long.valueOf(y.d() - d2), Boolean.valueOf(z)));
            }
        }
    }
}
