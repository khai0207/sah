package com.netease.nimlib.database.encrypt;

import android.content.ContentValues;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import com.netease.nimlib.o.f;
import java.util.Arrays;
import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;

/* loaded from: classes.dex */
public class SqlcipherDBHelper {
    private static final int LOCK_RETRY_CHANCES = 3;
    private static final String TAG = "db";

    public static final Cursor rawQuery(String str, boolean z, SQLiteDatabase sQLiteDatabase, String str2) {
        return rawQuery(str, z, sQLiteDatabase, str2, null);
    }

    public static final Cursor rawQuery(String str, boolean z, SQLiteDatabase sQLiteDatabase, String str2, String[] strArr) {
        boolean isSQLiteDatabaseLockedException;
        Cursor cursor = null;
        for (int i = 0; i < 3; i++) {
            try {
                cursor = sQLiteDatabase.rawQuery(str2, strArr);
            } catch (Throwable th) {
                th.printStackTrace();
                com.netease.nimlib.log.b.d(TAG, "exec sql exception: " + th);
                com.netease.nimlib.n.e.a(str, com.netease.nimlib.n.b.c.kExecuteSQL, str2, String.format("rawQuery exception: %s selectionArgs: %s", th, strArr == null ? "NULL" : f.f(Arrays.asList(strArr))));
                isSQLiteDatabaseLockedException = th instanceof SQLiteException ? isSQLiteDatabaseLockedException(th) : false;
            }
            if (isSQLiteDatabaseLockedException) {
                com.netease.nimlib.log.b.e(TAG, "locked");
            }
            if (cursor != null || !isSQLiteDatabaseLockedException) {
                break;
            }
        }
        return e.a(cursor);
    }

    public static void exeSQL(String str, boolean z, SQLiteDatabase sQLiteDatabase, String str2, Object[] objArr) {
        boolean isSQLiteDatabaseLockedException;
        boolean z2;
        for (int i = 0; i < 3; i++) {
            if (objArr == null) {
                try {
                    sQLiteDatabase.execSQL(str2);
                } catch (Throwable th) {
                    th.printStackTrace();
                    com.netease.nimlib.log.b.d(TAG, "exec sql exception: " + th);
                    String f = objArr == null ? "NULL" : f.f(Arrays.asList(objArr));
                    if (z) {
                        com.netease.nimlib.n.e.a(str, com.netease.nimlib.n.b.c.kTransaction, str2, String.format("exeSQL exception: %s bindArgs: %s", th, f));
                    } else {
                        com.netease.nimlib.n.e.a(str, com.netease.nimlib.n.b.c.kExecuteSQL, str2, String.format("exeSQL exception: %s bindArgs: %s", th, f));
                    }
                    isSQLiteDatabaseLockedException = th instanceof SQLiteException ? isSQLiteDatabaseLockedException(th) : false;
                    z2 = false;
                }
            } else {
                sQLiteDatabase.execSQL(str2, objArr);
            }
            isSQLiteDatabaseLockedException = false;
            z2 = true;
            if (isSQLiteDatabaseLockedException) {
                com.netease.nimlib.log.b.e(TAG, "locked");
            }
            if (z2 || !isSQLiteDatabaseLockedException) {
                return;
            }
        }
    }

    public static int exeDelete(String str, boolean z, SQLiteDatabase sQLiteDatabase, String str2, String str3) {
        return exeDelete(str, z, sQLiteDatabase, str2, str3, null);
    }

    public static int exeDelete(String str, boolean z, SQLiteDatabase sQLiteDatabase, String str2, String str3, String[] strArr) {
        for (int i = 0; i < 3; i++) {
            try {
                return sQLiteDatabase.delete(str2, str3, strArr);
            } catch (Throwable th) {
                th.printStackTrace();
                com.netease.nimlib.log.b.d(TAG, "exec delete exception: " + th);
                String f = str3 == null ? "NULL" : f.f(Arrays.asList(strArr));
                com.netease.nimlib.n.e.a(str, com.netease.nimlib.n.b.c.kExecuteSQL, "exeDelete table = " + str2 + ",whereClause = " + str3, String.format("exec delete exception: %s whereArgsLog: %s", th, f));
                boolean isSQLiteDatabaseLockedException = th instanceof SQLiteException ? isSQLiteDatabaseLockedException(th) : false;
                if (isSQLiteDatabaseLockedException) {
                    com.netease.nimlib.log.b.e(TAG, "locked");
                }
                if (!isSQLiteDatabaseLockedException) {
                    break;
                }
            }
        }
        return 0;
    }

    public static long insert(String str, boolean z, SQLiteDatabase sQLiteDatabase, String str2, String str3, ContentValues contentValues) {
        boolean isSQLiteDatabaseLockedException;
        boolean z2;
        long j = -1;
        for (int i = 0; i < 3; i++) {
            try {
                j = sQLiteDatabase.insert(str2, str3, contentValues);
                isSQLiteDatabaseLockedException = false;
                z2 = true;
            } catch (Throwable th) {
                th.printStackTrace();
                com.netease.nimlib.log.b.d(TAG, "exec sql exception: " + th);
                String f = contentValues == null ? "NULL" : f.f(contentValues.keySet());
                String f2 = contentValues != null ? f.f(contentValues.valueSet()) : "NULL";
                if (z) {
                    com.netease.nimlib.n.e.a(str, com.netease.nimlib.n.b.c.kTransaction, "insert table = " + str2 + ",nullColumnHack = " + str3, String.format("insert exception: %s ContentValues: %s %s", th, f, f2));
                } else {
                    com.netease.nimlib.n.e.a(str, com.netease.nimlib.n.b.c.kExecuteSQL, "insert table = " + str2 + ",nullColumnHack = " + str3, String.format("insert exception: %s ContentValues: %s %s", th, f, f2));
                }
                isSQLiteDatabaseLockedException = th instanceof SQLiteException ? isSQLiteDatabaseLockedException(th) : false;
                z2 = false;
            }
            if (isSQLiteDatabaseLockedException) {
                com.netease.nimlib.log.b.e(TAG, "locked");
            }
            if (z2 || !isSQLiteDatabaseLockedException) {
                break;
            }
        }
        return j;
    }

    public static long insertWithOnConflict(String str, boolean z, SQLiteDatabase sQLiteDatabase, String str2, String str3, ContentValues contentValues, int i) {
        boolean isSQLiteDatabaseLockedException;
        boolean z2;
        long j = -1;
        for (int i2 = 0; i2 < 3; i2++) {
            try {
                j = sQLiteDatabase.insertWithOnConflict(str2, str3, contentValues, i);
                isSQLiteDatabaseLockedException = false;
                z2 = true;
            } catch (Throwable th) {
                th.printStackTrace();
                com.netease.nimlib.log.b.d(TAG, "exec sql exception: " + th);
                String f = contentValues == null ? "NULL" : f.f(contentValues.keySet());
                String f2 = contentValues != null ? f.f(contentValues.valueSet()) : "NULL";
                if (z) {
                    com.netease.nimlib.n.e.a(str, com.netease.nimlib.n.b.c.kTransaction, "insertWithOnConflict table = " + str2 + ",nullColumnHack = " + str3, String.format("insertWithOnConflict %s exception: %s ContentValues: %s %s", Integer.valueOf(i), th, f, f2));
                } else {
                    com.netease.nimlib.n.e.a(str, com.netease.nimlib.n.b.c.kExecuteSQL, "insertWithOnConflict table = " + str2 + ",nullColumnHack = " + str3, String.format("insertWithOnConflict %s exception: %s ContentValues: %s %s", Integer.valueOf(i), th, f, f2));
                }
                isSQLiteDatabaseLockedException = th instanceof SQLiteException ? isSQLiteDatabaseLockedException(th) : false;
                z2 = false;
            }
            if (isSQLiteDatabaseLockedException) {
                com.netease.nimlib.log.b.e(TAG, "locked");
            }
            if (z2 || !isSQLiteDatabaseLockedException) {
                break;
            }
        }
        return j;
    }

    public static long replace(String str, boolean z, SQLiteDatabase sQLiteDatabase, String str2, String str3, ContentValues contentValues) {
        boolean isSQLiteDatabaseLockedException;
        boolean z2;
        long j = -1;
        for (int i = 0; i < 3; i++) {
            try {
                j = sQLiteDatabase.replace(str2, str3, contentValues);
                isSQLiteDatabaseLockedException = false;
                z2 = true;
            } catch (Throwable th) {
                th.printStackTrace();
                com.netease.nimlib.log.b.d(TAG, "exec sql exception: " + th);
                String f = contentValues == null ? "NULL" : f.f(contentValues.keySet());
                String f2 = contentValues != null ? f.f(contentValues.valueSet()) : "NULL";
                if (z) {
                    com.netease.nimlib.n.e.a(str, com.netease.nimlib.n.b.c.kTransaction, "replace table = " + str2 + ",nullColumnHack = " + str3, String.format("replace exception: %s ContentValues: %s %s", th, f, f2));
                } else {
                    com.netease.nimlib.n.e.a(str, com.netease.nimlib.n.b.c.kExecuteSQL, "replace table = " + str2 + ",nullColumnHack = " + str3, String.format("replace exception: %s ContentValues: %s %s", th, f, f2));
                }
                isSQLiteDatabaseLockedException = th instanceof SQLiteException ? isSQLiteDatabaseLockedException(th) : false;
                z2 = false;
            }
            if (isSQLiteDatabaseLockedException) {
                com.netease.nimlib.log.b.e(TAG, "locked");
            }
            if (z2 || !isSQLiteDatabaseLockedException) {
                break;
            }
        }
        return j;
    }

    public static boolean isTableExists(SQLiteDatabase sQLiteDatabase, String str) {
        Cursor rawQuery;
        if (str == null || sQLiteDatabase == null || !sQLiteDatabase.isOpen() || (rawQuery = sQLiteDatabase.rawQuery("SELECT COUNT(*) FROM sqlite_master WHERE type=? AND name=?", new String[]{"table", str})) == null || !rawQuery.moveToFirst()) {
            return false;
        }
        int i = rawQuery.getInt(0);
        rawQuery.close();
        return i > 0;
    }

    public static boolean checkIntegrity(SQLiteDatabase sQLiteDatabase) {
        Cursor rawQuery;
        if (sQLiteDatabase != null && sQLiteDatabase.isOpen() && (rawQuery = sQLiteDatabase.rawQuery("PRAGMA quick_check", (String[]) null)) != null && rawQuery.moveToFirst()) {
            r0 = rawQuery.getCount() == 1 ? rawQuery.getString(0).equalsIgnoreCase("ok") : false;
            rawQuery.close();
        }
        return r0;
    }

    public static final boolean isSQLiteDatabaseLockedException(SQLiteException sQLiteException) {
        String message = sQLiteException.getMessage();
        return !TextUtils.isEmpty(message) && message.contains("lock");
    }
}
