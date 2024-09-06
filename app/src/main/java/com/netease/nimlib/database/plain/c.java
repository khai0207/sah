package com.netease.nimlib.database.plain;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.DefaultDatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseCorruptException;
import android.database.sqlite.SQLiteException;
import com.netease.nimlib.database.a.d;
import com.netease.nimlib.n.e;

/* compiled from: PlainDatabase.java */
/* loaded from: classes.dex */
public class c extends com.netease.nimlib.database.a.a {
    protected int a;
    protected SQLiteDatabase b;
    private Context c;
    private String d;
    private boolean e = false;
    private a f;

    @Override // com.netease.nimlib.database.g
    public boolean a() {
        return true;
    }

    @Override // com.netease.nimlib.database.a.a
    public boolean a(Context context, String str, String str2, d[] dVarArr, int i) {
        this.c = context;
        this.d = str;
        this.a = i;
        this.f = new a(dVarArr);
        com.netease.nimlib.log.b.v("open plain database: " + str.substring(str.lastIndexOf("/") + 1));
        a(str, this.a);
        return this.b != null;
    }

    @Override // com.netease.nimlib.database.a.a
    public boolean e() {
        return this.b != null;
    }

    private String c(String str) {
        return a(this.c, str);
    }

    private void a(String str, int i) {
        try {
            this.b = SQLiteDatabase.openOrCreateDatabase(c(str), null, new DatabaseErrorHandler() { // from class: com.netease.nimlib.database.plain.-$$Lambda$c$VuYJ-e07cksUj9Gz8vdW8-w-K-Q
                private final /* synthetic */ String f$1;

                public /* synthetic */ $$Lambda$c$VuYJe07cksUj9Gz8vdW8wKQ(String str2) {
                    r2 = str2;
                }

                @Override // android.database.DatabaseErrorHandler
                public final void onCorruption(SQLiteDatabase sQLiteDatabase) {
                    c.this.a(r2, sQLiteDatabase);
                }
            });
        } catch (SQLiteException e) {
            e.a(str2, com.netease.nimlib.n.b.c.kOpen, e.getMessage(), "open or upgrade PlainDatabase error = " + e);
            if (e instanceof SQLiteDatabaseCorruptException) {
                com.netease.nimlib.log.b.d("open or upgrade error, delete backup", e);
                com.netease.nimlib.database.a.b(this.c, str2);
            } else {
                com.netease.nimlib.log.b.d("open or upgrade error=" + e.getLocalizedMessage(), e);
            }
        }
        int version = this.b.getVersion();
        if (version != i) {
            this.b.beginTransaction();
            try {
                try {
                    if (version == 0) {
                        com.netease.nimlib.log.b.v("create database " + str2);
                        j();
                    } else if (version < i) {
                        com.netease.nimlib.log.b.v("upgrade database " + str2 + " from " + version + " to " + i);
                        a(version, i);
                    }
                    this.b.setVersion(i);
                    this.b.setTransactionSuccessful();
                } catch (Exception e2) {
                    com.netease.nimlib.log.b.d("create or upgrade database " + str2 + " error=" + e2.getMessage(), e2);
                    e.a(str2, com.netease.nimlib.n.b.c.kOpen, e2.getMessage(), "create or upgrade PlainDatabase error = " + e2);
                }
                this.b.endTransaction();
            } catch (Throwable th) {
                this.b.endTransaction();
                throw th;
            }
        }
    }

    public /* synthetic */ void a(String str, SQLiteDatabase sQLiteDatabase) {
        com.netease.nimlib.n.b.c cVar = this.b == null ? com.netease.nimlib.n.b.c.kOpen : com.netease.nimlib.n.b.c.kExecuteSQL;
        new DefaultDatabaseErrorHandler().onCorruption(sQLiteDatabase);
        com.netease.nimlib.log.b.v(String.format("PlainDatabase %s onCorruption restore %s", sQLiteDatabase, Boolean.valueOf(com.netease.nimlib.database.a.a(this.c, str))));
        e.a(str, cVar, "SQLiteDatabaseCorruptException", "DatabaseErrorHandler error");
    }

    @Override // com.netease.nimlib.database.a.a
    public void i() {
        SQLiteDatabase sQLiteDatabase = this.b;
        if (sQLiteDatabase != null) {
            try {
                sQLiteDatabase.close();
            } catch (Throwable th) {
                e.a(this.d, com.netease.nimlib.n.b.c.kClose, (String) null, "close PlainDatabase error = " + th);
                com.netease.nimlib.log.b.d("close PlainDatabase error", th);
            }
            this.b = null;
            com.netease.nimlib.log.b.v("close database " + this.d);
            return;
        }
        com.netease.nimlib.log.b.v("close database " + this.d + " is null");
    }

    @Override // com.netease.nimlib.database.a.a
    public void a(String str) {
        a(str, (Object[]) null);
    }

    @Override // com.netease.nimlib.database.a.a
    public void a(String str, Object[] objArr) {
        SQLiteDatabase sQLiteDatabase = this.b;
        if (sQLiteDatabase != null) {
            PlainDBHelper.exeSQL(this.d, this.e, sQLiteDatabase, str, objArr);
        }
    }

    @Override // com.netease.nimlib.database.a.a
    public int a(String str, String str2) {
        return a(str, str2, (String[]) null);
    }

    @Override // com.netease.nimlib.database.a.a
    public int a(String str, String str2, String[] strArr) {
        SQLiteDatabase sQLiteDatabase = this.b;
        if (sQLiteDatabase == null) {
            return 0;
        }
        return PlainDBHelper.exeDelete(this.d, sQLiteDatabase, str, str2, strArr);
    }

    @Override // com.netease.nimlib.database.a.a
    public Cursor b(String str) {
        SQLiteDatabase sQLiteDatabase = this.b;
        if (sQLiteDatabase != null) {
            return PlainDBHelper.rawQuery(this.d, sQLiteDatabase, str);
        }
        return null;
    }

    @Override // com.netease.nimlib.database.a.a
    public Cursor a(String str, String[] strArr) {
        SQLiteDatabase sQLiteDatabase = this.b;
        if (sQLiteDatabase != null) {
            return PlainDBHelper.rawQuery(this.d, sQLiteDatabase, str, strArr);
        }
        return null;
    }

    @Override // com.netease.nimlib.database.a.a
    public long a(String str, String str2, ContentValues contentValues) {
        SQLiteDatabase sQLiteDatabase = this.b;
        if (sQLiteDatabase != null) {
            return PlainDBHelper.insert(this.d, this.e, sQLiteDatabase, str, str2, contentValues);
        }
        return -1L;
    }

    @Override // com.netease.nimlib.database.a.a
    public long c(String str, String str2, ContentValues contentValues) {
        SQLiteDatabase sQLiteDatabase = this.b;
        if (sQLiteDatabase != null) {
            return PlainDBHelper.insertWithOnConflict(this.d, this.e, sQLiteDatabase, str, str2, contentValues, 5);
        }
        return -1L;
    }

    @Override // com.netease.nimlib.database.a.a
    public long b(String str, String str2, ContentValues contentValues) {
        SQLiteDatabase sQLiteDatabase = this.b;
        if (sQLiteDatabase != null) {
            return PlainDBHelper.replace(this.d, this.e, sQLiteDatabase, str, str2, contentValues);
        }
        return -1L;
    }

    @Override // com.netease.nimlib.database.a.a
    public void f() {
        SQLiteDatabase sQLiteDatabase = this.b;
        if (sQLiteDatabase != null) {
            sQLiteDatabase.beginTransaction();
            this.e = true;
        }
    }

    @Override // com.netease.nimlib.database.a.a
    public void g() {
        SQLiteDatabase sQLiteDatabase = this.b;
        if (sQLiteDatabase != null) {
            sQLiteDatabase.endTransaction();
        }
    }

    @Override // com.netease.nimlib.database.a.a
    public void h() {
        SQLiteDatabase sQLiteDatabase = this.b;
        if (sQLiteDatabase != null) {
            sQLiteDatabase.setTransactionSuccessful();
            this.e = false;
        }
    }

    private void j() {
        this.f.a(this.b, this.a);
    }

    private void a(int i, int i2) {
        this.f.a(this.b, i, i2);
    }

    @Override // com.netease.nimlib.database.g
    public void b() {
        SQLiteDatabase sQLiteDatabase = this.b;
        if (sQLiteDatabase == null) {
            com.netease.nimlib.log.b.f("PlainDatabase", "enableWal database null");
            return;
        }
        sQLiteDatabase.enableWriteAheadLogging();
        Cursor cursor = null;
        try {
            try {
                cursor = this.b.rawQuery("PRAGMA wal_autocheckpoint = 0;", null);
                cursor.moveToFirst();
                com.netease.nimlib.log.b.d("PlainDatabase", "enableWal wal_autocheckpoint:" + cursor.getInt(0));
                if (cursor == null || cursor.isClosed()) {
                    return;
                }
            } catch (Exception e) {
                com.netease.nimlib.log.b.c("PlainDatabase", "enableWal wal_autocheckpoint Exception:" + e, e);
                e.printStackTrace();
                e.a(this.d, com.netease.nimlib.n.b.c.kWalCheckPoint, "enableWal PlainDatabase", "enableWal PlainDatabase exception = " + e);
                if (cursor == null || cursor.isClosed()) {
                    return;
                }
            }
            cursor.close();
        } catch (Throwable th) {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x00ad A[LOOP:1: B:18:0x003c->B:28:0x00ad, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00bd A[SYNTHETIC] */
    @Override // com.netease.nimlib.database.g
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void c() {
        /*
            r14 = this;
            android.database.sqlite.SQLiteDatabase r0 = r14.b
            java.lang.String r1 = "PlainDatabase"
            if (r0 != 0) goto Lc
            java.lang.String r0 = "disableWal database null"
            com.netease.nimlib.log.b.f(r1, r0)
            return
        Lc:
            r0 = 0
            r2 = 0
            r3 = 0
        Lf:
            java.lang.String r4 = "disableWal Throwable:"
            r5 = 3
            r6 = 1
            if (r2 >= r5) goto L38
            android.database.sqlite.SQLiteDatabase r7 = r14.b     // Catch: java.lang.Throwable -> L1c
            r7.disableWriteAheadLogging()     // Catch: java.lang.Throwable -> L1c
            r3 = 1
            goto L32
        L1c:
            r7 = move-exception
            r7.printStackTrace()
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r8.append(r4)
            r8.append(r7)
            java.lang.String r8 = r8.toString()
            com.netease.nimlib.log.b.e(r1, r8, r7)
        L32:
            if (r3 == 0) goto L35
            goto L38
        L35:
            int r2 = r2 + 1
            goto Lf
        L38:
            if (r3 == 0) goto L3b
            return
        L3b:
            r2 = 0
        L3c:
            if (r2 >= r5) goto Lbd
            r7 = 0
            android.database.sqlite.SQLiteDatabase r8 = r14.b     // Catch: java.lang.Throwable -> L6f
            java.lang.String r9 = "PRAGMA wal_autocheckpoint = 100;"
            android.database.Cursor r7 = r8.rawQuery(r9, r7)     // Catch: java.lang.Throwable -> L6f
            r7.moveToFirst()     // Catch: java.lang.Throwable -> L6f
            int r8 = r7.getInt(r0)     // Catch: java.lang.Throwable -> L6f
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L6f
            r9.<init>()     // Catch: java.lang.Throwable -> L6f
            java.lang.String r10 = "disableWal wal_autocheckpoint:"
            r9.append(r10)     // Catch: java.lang.Throwable -> L6f
            r9.append(r8)     // Catch: java.lang.Throwable -> L6f
            java.lang.String r8 = r9.toString()     // Catch: java.lang.Throwable -> L6f
            com.netease.nimlib.log.b.d(r1, r8)     // Catch: java.lang.Throwable -> L6f
            if (r7 == 0) goto L6d
            boolean r3 = r7.isClosed()
            if (r3 != 0) goto L6d
            r7.close()
        L6d:
            r3 = 1
            goto Laa
        L6f:
            r8 = move-exception
            r8.printStackTrace()     // Catch: java.lang.Throwable -> Lb0
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lb0
            r9.<init>()     // Catch: java.lang.Throwable -> Lb0
            r9.append(r4)     // Catch: java.lang.Throwable -> Lb0
            r9.append(r8)     // Catch: java.lang.Throwable -> Lb0
            java.lang.String r9 = r9.toString()     // Catch: java.lang.Throwable -> Lb0
            com.netease.nimlib.log.b.e(r1, r9, r8)     // Catch: java.lang.Throwable -> Lb0
            java.lang.String r9 = r14.d     // Catch: java.lang.Throwable -> Lb0
            com.netease.nimlib.n.b.c r10 = com.netease.nimlib.n.b.c.kWalCheckPoint     // Catch: java.lang.Throwable -> Lb0
            java.lang.String r11 = "disableWal PlainDatabase"
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lb0
            r12.<init>()     // Catch: java.lang.Throwable -> Lb0
            java.lang.String r13 = "disableWal PlainDatabase exception = "
            r12.append(r13)     // Catch: java.lang.Throwable -> Lb0
            r12.append(r8)     // Catch: java.lang.Throwable -> Lb0
            java.lang.String r8 = r12.toString()     // Catch: java.lang.Throwable -> Lb0
            com.netease.nimlib.n.e.a(r9, r10, r11, r8)     // Catch: java.lang.Throwable -> Lb0
            if (r7 == 0) goto Laa
            boolean r8 = r7.isClosed()
            if (r8 != 0) goto Laa
            r7.close()
        Laa:
            if (r3 == 0) goto Lad
            goto Lbd
        Lad:
            int r2 = r2 + 1
            goto L3c
        Lb0:
            r0 = move-exception
            if (r7 == 0) goto Lbc
            boolean r1 = r7.isClosed()
            if (r1 != 0) goto Lbc
            r7.close()
        Lbc:
            throw r0
        Lbd:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.netease.nimlib.database.plain.c.c():void");
    }

    @Override // com.netease.nimlib.database.g
    public boolean d() {
        SQLiteDatabase sQLiteDatabase = this.b;
        return sQLiteDatabase != null && sQLiteDatabase.isOpen();
    }
}
