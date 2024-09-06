package com.netease.nimlib.database.plain;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import com.netease.nimlib.n.e;
import com.netease.nimlib.o.f;
import java.util.Arrays;

/* loaded from: classes.dex */
public class PlainDBHelper {
    private static final int LOCK_RETRY_CHANCES = 3;
    private static final String TAG = "db";

    public static final Cursor rawQuery(String str, SQLiteDatabase sQLiteDatabase, String str2) {
        return rawQuery(str, sQLiteDatabase, str2, null);
    }

    public static final Cursor rawQuery(String str, SQLiteDatabase sQLiteDatabase, String str2, String[] strArr) {
        boolean isSQLiteDatabaseLockedException;
        Cursor cursor = null;
        for (int i = 0; i < 3; i++) {
            try {
                cursor = sQLiteDatabase.rawQuery(str2, strArr);
            } catch (Throwable th) {
                th.printStackTrace();
                com.netease.nimlib.log.b.d(TAG, "exec sql exception: " + th);
                e.a(str, com.netease.nimlib.n.b.c.kExecuteSQL, str2, String.format("rawQuery exception: %s selectionArgs: %s", th, strArr == null ? "NULL" : f.f(Arrays.asList(strArr))));
                isSQLiteDatabaseLockedException = th instanceof SQLiteException ? isSQLiteDatabaseLockedException(th) : false;
            }
            if (isSQLiteDatabaseLockedException) {
                com.netease.nimlib.log.b.e(TAG, "locked");
            }
            if (cursor != null || !isSQLiteDatabaseLockedException) {
                break;
            }
        }
        return b.a(cursor);
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
                        e.a(str, com.netease.nimlib.n.b.c.kTransaction, str2, String.format("exeSQL exception: %s bindArgs: %s", th, f));
                    } else {
                        e.a(str, com.netease.nimlib.n.b.c.kExecuteSQL, str2, String.format("exeSQL exception: %s bindArgs: %s", th, f));
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

    public static int exeDelete(String str, SQLiteDatabase sQLiteDatabase, String str2, String str3) {
        return exeDelete(str, sQLiteDatabase, str2, str3, null);
    }

    public static int exeDelete(String str, SQLiteDatabase sQLiteDatabase, String str2, String str3, String[] strArr) {
        for (int i = 0; i < 3; i++) {
            try {
                return sQLiteDatabase.delete(str2, str3, strArr);
            } catch (Throwable th) {
                th.printStackTrace();
                com.netease.nimlib.log.b.d(TAG, "exec delete exception: " + th);
                String f = str3 == null ? "NULL" : f.f(Arrays.asList(strArr));
                e.a(str, com.netease.nimlib.n.b.c.kExecuteSQL, "exeDelete table = " + str2 + ",whereClause = " + str3, String.format("exec delete exception: %s whereArgsLog: %s", th, f));
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
                    e.a(str, com.netease.nimlib.n.b.c.kTransaction, "table = " + str2 + ",nullColumnHack = " + str3, String.format("insert exception: %s ContentValues: %s %s", th, f, f2));
                } else {
                    e.a(str, com.netease.nimlib.n.b.c.kExecuteSQL, "table = " + str2 + ",nullColumnHack = " + str3, String.format("insert exception: %s ContentValues: %s %s", th, f, f2));
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
                    e.a(str, com.netease.nimlib.n.b.c.kTransaction, "insertWithOnConflict table = " + str2 + ",nullColumnHack = " + str3, String.format("insertWithOnConflict %s exception: %s ContentValues: %s %s", Integer.valueOf(i), th, f, f2));
                } else {
                    e.a(str, com.netease.nimlib.n.b.c.kExecuteSQL, "insertWithOnConflict table = " + str2 + ",nullColumnHack = " + str3, String.format("insertWithOnConflict %s exception: %s ContentValues: %s %s", Integer.valueOf(i), th, f, f2));
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
                    e.a(str, com.netease.nimlib.n.b.c.kTransaction, "replace table = " + str2 + ",nullColumnHack = " + str3, String.format("replace exception: %s ContentValues: %s %s", th, f, f2));
                } else {
                    e.a(str, com.netease.nimlib.n.b.c.kExecuteSQL, "replace table = " + str2 + ",nullColumnHack = " + str3, String.format("replace exception: %s ContentValues: %s %s", th, f, f2));
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

    public static final boolean isSQLiteDatabaseLockedException(SQLiteException sQLiteException) {
        String message = sQLiteException.getMessage();
        return !TextUtils.isEmpty(message) && message.contains("lock");
    }
}
