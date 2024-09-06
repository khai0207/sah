package com.netease.nimlib.database.encrypt;

import android.content.ContentValues;
import android.content.Context;
import com.netease.nimlib.n.b.k;
import net.sqlcipher.Cursor;
import net.sqlcipher.DefaultDatabaseErrorHandler;
import net.sqlcipher.database.SQLiteDatabase;

/* compiled from: EncryptedDatabase.java */
/* loaded from: classes.dex */
public class b extends com.netease.nimlib.database.a.a {
    protected int a;
    protected SQLiteDatabase b;
    private Context c;
    private String d;
    private String e;
    private boolean f = false;
    private a g;

    @Override // com.netease.nimlib.database.g
    public boolean a() {
        return false;
    }

    @Override // com.netease.nimlib.database.a.a
    public boolean a(Context context, String str, String str2, com.netease.nimlib.database.a.d[] dVarArr, int i) {
        this.c = context;
        this.d = str;
        if (str2 == null) {
            str2 = "";
        }
        this.e = str2;
        this.a = i;
        this.g = new a(dVarArr);
        try {
            SQLiteDatabase.loadLibs(context);
            com.netease.nimlib.log.b.v("open encrypted database: " + str.substring(str.lastIndexOf("/") + 1));
            a(str, this.a);
            return this.b != null;
        } catch (Throwable th) {
            com.netease.nimlib.n.e.a("sqlcipher", k.kLoad, th.toString(), "load library sqlcipher failed");
            throw th;
        }
    }

    @Override // com.netease.nimlib.database.a.a
    public boolean e() {
        return this.b != null;
    }

    private String d(String str) {
        return a(this.c, str);
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x00e1, code lost:
    
        if (r7 != null) goto L76;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x00e3, code lost:
    
        r7.endTransaction();
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0123, code lost:
    
        if (r7 == null) goto L85;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void a(java.lang.String r7, int r8) {
        /*
            Method dump skipped, instructions count: 320
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.netease.nimlib.database.encrypt.b.a(java.lang.String, int):void");
    }

    public /* synthetic */ void a(String str, SQLiteDatabase sQLiteDatabase) {
        com.netease.nimlib.n.b.c cVar = this.b == null ? com.netease.nimlib.n.b.c.kOpen : com.netease.nimlib.n.b.c.kExecuteSQL;
        new DefaultDatabaseErrorHandler().onCorruption(sQLiteDatabase);
        com.netease.nimlib.log.b.v(String.format("EncryptedDatabase %s onCorruption restore %s", sQLiteDatabase, Boolean.valueOf(com.netease.nimlib.database.a.a(this.c, str))));
        com.netease.nimlib.n.e.a(str, cVar, "SQLiteDatabaseCorruptException", "DatabaseErrorHandler error");
    }

    @Override // com.netease.nimlib.database.a.a
    public void i() {
        SQLiteDatabase sQLiteDatabase = this.b;
        if (sQLiteDatabase != null) {
            try {
                sQLiteDatabase.close();
            } catch (Throwable th) {
                com.netease.nimlib.n.e.a(this.d, com.netease.nimlib.n.b.c.kClose, (String) null, "close EncryptedDatabase error = " + th);
                com.netease.nimlib.log.b.d("close EncryptedDatabase error", th);
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
            SqlcipherDBHelper.exeSQL(this.d, this.f, sQLiteDatabase, str, objArr);
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
        return SqlcipherDBHelper.exeDelete(this.d, this.f, sQLiteDatabase, str, str2, strArr);
    }

    @Override // com.netease.nimlib.database.a.a
    /* renamed from: c */
    public Cursor b(String str) {
        SQLiteDatabase sQLiteDatabase = this.b;
        if (sQLiteDatabase != null) {
            return SqlcipherDBHelper.rawQuery(this.d, this.f, sQLiteDatabase, str);
        }
        return null;
    }

    @Override // com.netease.nimlib.database.a.a
    public android.database.Cursor a(String str, String[] strArr) {
        SQLiteDatabase sQLiteDatabase = this.b;
        if (sQLiteDatabase != null) {
            return SqlcipherDBHelper.rawQuery(this.d, this.f, sQLiteDatabase, str, strArr);
        }
        return null;
    }

    @Override // com.netease.nimlib.database.a.a
    public long a(String str, String str2, ContentValues contentValues) {
        SQLiteDatabase sQLiteDatabase = this.b;
        if (sQLiteDatabase != null) {
            return SqlcipherDBHelper.insert(this.d, this.f, sQLiteDatabase, str, str2, contentValues);
        }
        return -1L;
    }

    @Override // com.netease.nimlib.database.a.a
    public long c(String str, String str2, ContentValues contentValues) {
        SQLiteDatabase sQLiteDatabase = this.b;
        if (sQLiteDatabase != null) {
            return SqlcipherDBHelper.insertWithOnConflict(this.d, this.f, sQLiteDatabase, str, str2, contentValues, 5);
        }
        return -1L;
    }

    @Override // com.netease.nimlib.database.a.a
    public long b(String str, String str2, ContentValues contentValues) {
        SQLiteDatabase sQLiteDatabase = this.b;
        if (sQLiteDatabase != null) {
            return SqlcipherDBHelper.replace(this.d, this.f, sQLiteDatabase, str, str2, contentValues);
        }
        return -1L;
    }

    @Override // com.netease.nimlib.database.a.a
    public void f() {
        SQLiteDatabase sQLiteDatabase = this.b;
        if (sQLiteDatabase != null) {
            sQLiteDatabase.beginTransaction();
            this.f = true;
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
            this.f = false;
        }
    }

    private void j() {
        this.g.a(this.b, this.a);
    }

    private void a(int i, int i2) {
        this.g.a(this.b, i, i2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0039, code lost:
    
        if (r2.isClosed() == false) goto L77;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x003b, code lost:
    
        r2.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x003e, code lost:
    
        r2 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x007a, code lost:
    
        if (r2.isClosed() == false) goto L77;
     */
    @Override // com.netease.nimlib.database.g
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void b() {
        /*
            Method dump skipped, instructions count: 258
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.netease.nimlib.database.encrypt.b.b():void");
    }

    @Override // com.netease.nimlib.database.g
    public void c() {
        SQLiteDatabase sQLiteDatabase = this.b;
        if (sQLiteDatabase == null) {
            com.netease.nimlib.log.b.f("EncryptedDatabase", "disableWal database null");
            return;
        }
        Cursor cursor = null;
        try {
            try {
                cursor = sQLiteDatabase.rawQuery("PRAGMA journal_mode = DELETE;", (String[]) null);
                cursor.moveToFirst();
                com.netease.nimlib.log.b.d("EncryptedDatabase", "disableWal journal_mode:" + cursor.getString(0));
                if (cursor == null || cursor.isClosed()) {
                    return;
                }
            } catch (Exception e) {
                com.netease.nimlib.log.b.c("EncryptedDatabase", "disableWal journal_mode Exception:" + e, e);
                e.printStackTrace();
                com.netease.nimlib.n.e.a(this.d, com.netease.nimlib.n.b.c.kWalCheckPoint, "disableWal EncryptedDatabase", "disableWal EncryptedDatabase exception = " + e);
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

    @Override // com.netease.nimlib.database.g
    public boolean d() {
        SQLiteDatabase sQLiteDatabase = this.b;
        return sQLiteDatabase != null && sQLiteDatabase.isOpen();
    }
}
