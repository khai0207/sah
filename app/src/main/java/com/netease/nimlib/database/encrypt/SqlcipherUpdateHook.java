package com.netease.nimlib.database.encrypt;

import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteDatabaseHook;

/* loaded from: classes.dex */
public class SqlcipherUpdateHook implements SQLiteDatabaseHook {
    private static final String TAG = "SqlcipherUpdateHook";
    private final String dbName;
    private final int sqlcipherVersion;

    public int getSqlcipherVersion() {
        return this.sqlcipherVersion;
    }

    public String getDbName() {
        return this.dbName;
    }

    public SqlcipherUpdateHook(String str, int i) {
        this.dbName = str;
        this.sqlcipherVersion = i;
    }

    public void preKey(SQLiteDatabase sQLiteDatabase) {
        com.netease.nimlib.log.b.d(TAG, "*********preKey");
    }

    public void postKey(SQLiteDatabase sQLiteDatabase) {
        com.netease.nimlib.log.b.d(TAG, "*********postKey");
        try {
            Cursor rawQuery = sQLiteDatabase.rawQuery("PRAGMA cipher_migrate", (String[]) null);
            try {
                boolean z = false;
                if (rawQuery.getCount() == 1) {
                    rawQuery.moveToFirst();
                    z = rawQuery.getString(0).equals("0");
                }
                com.netease.nimlib.log.b.d(TAG, "cipher_migrate migrationOccurred: " + z);
                if (rawQuery != null) {
                    rawQuery.close();
                }
            } finally {
            }
        } catch (Exception e) {
            com.netease.nimlib.log.b.e(TAG, "Error executing cipher_migrate: " + e.getMessage(), e);
        }
    }
}
