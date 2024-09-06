package com.netease.nimlib.database.encrypt;

import android.content.Context;
import android.text.TextUtils;
import com.netease.nimlib.biz.l;
import com.netease.nimlib.o.y;
import com.netease.nimlib.sdk.auth.LoginInfo;
import java.io.File;
import java.io.IOException;
import net.sqlcipher.database.SQLiteDatabase;

/* compiled from: EncryptedDatabaseUtils.java */
/* loaded from: classes.dex */
public class d {
    public static boolean a(Context context, String str, String str2) {
        return b(context, str, str2) || c(context, str, str2);
    }

    private static boolean b(Context context, String str, String str2) {
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
            com.netease.nimlib.log.b.v("--------- need not encrypt - Msg --------- Key:" + str2);
            return false;
        }
        com.netease.nimlib.log.b.v("--------- need encrypt - Msg --------- Key:" + str2);
        return d(context, com.netease.nimlib.database.d.a(str, true), com.netease.nimlib.database.d.a(str, false));
    }

    private static boolean c(Context context, String str, String str2) {
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
            com.netease.nimlib.log.b.v("--------- need not encrypt - Main --------- Key:" + str2);
            return false;
        }
        com.netease.nimlib.log.b.v("--------- need encrypt - Main --------- Key:" + str2);
        return d(context, com.netease.nimlib.database.b.a(str, true), com.netease.nimlib.database.b.a(str, false));
    }

    private static boolean d(Context context, String str, String str2) {
        if (new File(com.netease.nimlib.database.a.a.a(context, str)).exists()) {
            com.netease.nimlib.log.b.v("--------- need not migrate：encrypted database exists ---------");
            return false;
        }
        if (new File(com.netease.nimlib.database.a.a.a(context, str2)).exists()) {
            com.netease.nimlib.log.b.v("--------- need migrate ---------");
            return true;
        }
        com.netease.nimlib.log.b.v("--------- need not migrate：plain database not exists ---------");
        return false;
    }

    private static void a(Context context, String str, String str2, String str3, String str4) throws IOException {
        SQLiteDatabase.loadLibs(com.netease.nimlib.c.e());
        File file = new File(com.netease.nimlib.database.a.a.a(context, str3));
        if (file.exists()) {
            String a = com.netease.nimlib.database.a.a.a(context, str4);
            File createTempFile = File.createTempFile("sqlcipherutils", "tmp", context.getCacheDir());
            SqlcipherUpdateHook a2 = a(str, a);
            if (a2 != null) {
                l.a(str, a2.getDbName(), a2.getSqlcipherVersion());
            }
            SQLiteDatabase openDatabase = SQLiteDatabase.openDatabase(file.getAbsolutePath(), "", (SQLiteDatabase.CursorFactory) null, 0);
            int version = openDatabase.getVersion();
            openDatabase.rawExecSQL(String.format("ATTACH DATABASE '%s' AS encrypted KEY '%s';", createTempFile.getAbsolutePath(), str2));
            openDatabase.rawExecSQL("SELECT sqlcipher_export('encrypted')");
            openDatabase.rawExecSQL("DETACH DATABASE encrypted;");
            openDatabase.close();
            SQLiteDatabase openDatabase2 = SQLiteDatabase.openDatabase(createTempFile.getAbsolutePath(), str2, (SQLiteDatabase.CursorFactory) null, 0);
            openDatabase2.setVersion(version);
            openDatabase2.close();
            if (createTempFile.renameTo(new File(a))) {
                file.delete();
            }
        }
    }

    public static boolean a(String str, String str2, String str3) {
        if (!TextUtils.isEmpty(str3)) {
            return com.netease.nimlib.c.i().rollbackSQLCipher ? !new File(com.netease.nimlib.database.a.a.a(com.netease.nimlib.c.e(), str)).exists() && new File(com.netease.nimlib.database.a.a.a(com.netease.nimlib.c.e(), str2)).exists() : new File(com.netease.nimlib.database.a.a.a(com.netease.nimlib.c.e(), str2)).exists() || !new File(com.netease.nimlib.database.a.a.a(com.netease.nimlib.c.e(), str)).exists();
        }
        return false;
    }

    public static void a(LoginInfo loginInfo) {
        boolean z;
        boolean z2;
        if (b(com.netease.nimlib.c.e(), loginInfo.getAccount(), com.netease.nimlib.c.i().databaseEncryptKey)) {
            com.netease.nimlib.log.b.v("--------- migrating... Msg ---------");
            long d = y.d();
            try {
                a(com.netease.nimlib.c.e(), loginInfo.getAccount(), com.netease.nimlib.c.i().databaseEncryptKey, com.netease.nimlib.database.d.a(loginInfo.getAccount(), false), com.netease.nimlib.database.d.a(loginInfo.getAccount(), true));
                z2 = true;
            } catch (IOException e) {
                e.printStackTrace();
                z2 = false;
            }
            com.netease.nimlib.log.b.v(String.format("--------- migrate duration %sms success %s Msg ---------", Long.valueOf(y.d() - d), Boolean.valueOf(z2)));
        }
        if (c(com.netease.nimlib.c.e(), loginInfo.getAccount(), com.netease.nimlib.c.i().databaseEncryptKey)) {
            com.netease.nimlib.log.b.v("--------- migrating... Main ---------");
            long d2 = y.d();
            try {
                a(com.netease.nimlib.c.e(), loginInfo.getAccount(), com.netease.nimlib.c.i().databaseEncryptKey, com.netease.nimlib.database.b.a(loginInfo.getAccount(), false), com.netease.nimlib.database.b.a(loginInfo.getAccount(), true));
                z = true;
            } catch (IOException e2) {
                e2.printStackTrace();
                z = false;
            }
            com.netease.nimlib.log.b.v(String.format("--------- migrate duration %sms success %s Msg ---------", Long.valueOf(y.d() - d2), Boolean.valueOf(z)));
        }
    }

    public static SqlcipherUpdateHook a(String str, String str2) {
        com.netease.nimlib.log.b.v("getSqlcipherUpdateHook uid = " + str + ", dbPath = " + str2);
        SqlcipherUpdateHook sqlcipherUpdateHook = null;
        if (TextUtils.isEmpty(str2)) {
            com.netease.nimlib.log.b.v("getSqlcipherUpdateHook dbPath is empty");
            return null;
        }
        String substring = str2.substring(str2.lastIndexOf("/") + 1);
        int a = a();
        if (a < 4) {
            l.a(str, substring, a);
            com.netease.nimlib.log.b.v("getSqlcipherUpdateHook hook is null,sqlcipher version " + a + " < 4, dbName = " + substring);
            return null;
        }
        com.netease.nimlib.log.b.v("getSqlcipherUpdateHook sqlcipher version = 4.5.4, dbName = " + substring);
        try {
            int a2 = l.a(str, substring);
            com.netease.nimlib.log.b.v("getSqlcipherUpdateHook old sqlcipher version = " + a2 + ", dbName = " + substring);
            if (a2 < 4) {
                com.netease.nimlib.log.b.v("getSqlcipherUpdateHook version < 4, dbName = " + substring);
                if (new File(str2).exists()) {
                    sqlcipherUpdateHook = new SqlcipherUpdateHook(substring, 4);
                    com.netease.nimlib.log.b.v("getSqlcipherUpdateHook set hook of " + substring);
                } else {
                    com.netease.nimlib.log.b.v("getSqlcipherUpdateHook db file is null,dbName = " + substring);
                }
            }
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("db", "getSqlcipherUpdateHook error", th);
            sqlcipherUpdateHook = new SqlcipherUpdateHook(substring, 4);
        }
        if (sqlcipherUpdateHook == null) {
            com.netease.nimlib.log.b.v("getSqlcipherUpdateHook hook is null");
        }
        return sqlcipherUpdateHook;
    }

    private static int a() {
        try {
            String str = (String) Class.forName("net.sqlcipher.database.SQLiteDatabase").getField("SQLCIPHER_ANDROID_VERSION").get(null);
            com.netease.nimlib.log.b.v("getSqlcipherVersion versionName = " + str);
            if (str != null && !str.isEmpty()) {
                int parseInt = Integer.parseInt(str.substring(0, str.indexOf(".")));
                com.netease.nimlib.log.b.v("getSqlcipherVersion version = " + parseInt);
                return parseInt;
            }
            return 0;
        } catch (Throwable th) {
            com.netease.nimlib.log.b.d("getSqlcipherVersion error", th);
            return 0;
        }
    }
}
