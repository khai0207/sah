package com.unionpay.sdk;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.provider.BaseColumns;
import com.iflytek.cloud.SpeechEvent;
import com.netease.nimlib.amazonaws.mobileconnectors.s3.transferutility.TransferTable;
import com.unionpay.sdk.c;
import java.io.File;

/* loaded from: classes.dex */
final class ar extends v {
    private static volatile ar a;
    private static SQLiteDatabase b;
    private static int c;
    private final int d = 1;
    private final int e = 2;
    private final int f = 3;

    /* loaded from: classes.dex */
    static final class a implements BaseColumns {
        static final String[] a = {TransferTable.COLUMN_ID, com.alipay.sdk.m.h.c.e, "start_time", "duration", SpeechEvent.KEY_EVENT_SESSION_ID, "refer", "realtime"};

        static final void a(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL("CREATE TABLE activity (_id INTEGER PRIMARY KEY autoincrement,name TEXT,start_time LONG,duration INTEGER,session_id TEXT,refer TEXT,realtime LONG)");
        }
    }

    /* loaded from: classes.dex */
    static final class b implements BaseColumns {
        static final String[] a = {TransferTable.COLUMN_ID, "event_id", "event_label", SpeechEvent.KEY_EVENT_SESSION_ID, "occurtime", "paramap"};

        static final void a(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL("CREATE TABLE app_event (_id INTEGER PRIMARY KEY autoincrement,event_id TEXT,event_label TEXT,session_id TEXT,occurtime LONG,paramap BLOB)");
        }
    }

    /* loaded from: classes.dex */
    static final class c implements BaseColumns {
        static final String[] a = {TransferTable.COLUMN_ID, "error_time", "message", "repeat", "shorthashcode"};

        static final void a(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL("CREATE TABLE error_report (_id INTEGER PRIMARY KEY autoincrement,error_time LONG,message BLOB,repeat INTERGER,shorthashcode TEXT)");
        }
    }

    /* loaded from: classes.dex */
    static final class d implements BaseColumns {
        static final String[] a = {TransferTable.COLUMN_ID, SpeechEvent.KEY_EVENT_SESSION_ID, "start_time", "duration", "is_launch", "interval", "is_connected"};

        static final void a(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL("CREATE TABLE session (_id INTEGER PRIMARY KEY autoincrement,session_id TEXT,start_time LONG,duration INTEGER,is_launch INTEGER,interval LONG, is_connected INTEGER)");
        }
    }

    private ar() {
    }

    private synchronized long a(String str, ContentValues contentValues) {
        SQLiteDatabase sQLiteDatabase;
        long j = 0;
        if (aw.b(str)) {
            return 0L;
        }
        b.beginTransaction();
        try {
            j = b.insert(str, null, contentValues);
            b.setTransactionSuccessful();
            sQLiteDatabase = b;
        } catch (Throwable th) {
            try {
                al.a(th);
                sQLiteDatabase = b;
            } catch (Throwable th2) {
                b.endTransaction();
                throw th2;
            }
        }
        sQLiteDatabase.endTransaction();
        al.b("[SQL execution] ", "Return value: " + String.valueOf(j));
        return j;
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x00d2, code lost:
    
        if (r2 != null) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00e2, code lost:
    
        return 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00db, code lost:
    
        r2.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00d9, code lost:
    
        if (r2 == null) goto L37;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private synchronized long a(java.lang.String r12, com.unionpay.sdk.c.a r13, java.lang.StringBuffer r14) {
        /*
            Method dump skipped, instructions count: 238
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.sdk.ar.a(java.lang.String, com.unionpay.sdk.c$a, java.lang.StringBuffer):long");
    }

    static ar c() {
        if (a == null) {
            synchronized (ar.class) {
                if (a == null) {
                    a = new ar();
                }
            }
        }
        return a;
    }

    private synchronized void e() {
        if (b != null) {
            c++;
            return;
        }
        File file = new File(ad.c.getFilesDir(), "unionpaytcagent.db");
        boolean exists = file.exists();
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        SQLiteDatabase openOrCreateDatabase = SQLiteDatabase.openOrCreateDatabase(file, (SQLiteDatabase.CursorFactory) null);
        b = openOrCreateDatabase;
        c = 1;
        if (!exists) {
            f();
            return;
        }
        if (6 > openOrCreateDatabase.getVersion()) {
            b.execSQL("DROP TABLE IF EXISTS error_report");
            b.execSQL("DROP TABLE IF EXISTS app_event");
            b.execSQL("DROP TABLE IF EXISTS session");
            b.execSQL("DROP TABLE IF EXISTS activity");
            f();
        }
    }

    private static void f() {
        b.setVersion(6);
        d.a(b);
        a.a(b);
        b.a(b);
        c.a(b);
    }

    private synchronized void g() {
        int i = c - 1;
        c = i;
        int max = Math.max(0, i);
        c = max;
        if (max == 0 && b != null) {
            b.close();
            b = null;
        }
    }

    @Override // com.unionpay.sdk.v
    final synchronized long a(long j, String str) {
        long j2;
        String str2;
        al.b("[Save Error] ", "errorTime:" + j, ", data:" + str);
        ContentValues contentValues = new ContentValues();
        contentValues.put("error_time", ag.a(String.valueOf(j)));
        c.a aVar = new c.a();
        StringBuffer stringBuffer = new StringBuffer("");
        j2 = 0;
        try {
            long a2 = a(str, aVar, stringBuffer);
            if (0 == a2) {
                contentValues.put("message", str.getBytes("UTF-8"));
                contentValues.put("repeat", ag.a(String.valueOf(1)));
                contentValues.put("shorthashcode", ag.a(stringBuffer.toString()));
                str2 = "error_report";
            } else {
                contentValues.put("repeat", ag.a(String.valueOf(aVar.b + 1)));
                str2 = "error_report";
                String.valueOf(a2);
            }
            j2 = a(str2, contentValues);
        } catch (Throwable th) {
            al.a(th);
        }
        return j2;
    }

    @Override // com.unionpay.sdk.v
    final void a() {
        e();
    }

    @Override // com.unionpay.sdk.v
    final void b() {
        g();
    }

    final void d() {
        try {
            g();
            File file = new File(ad.c.getFilesDir(), "unionpaytcagent.db");
            if (file.exists()) {
                if (Build.VERSION.SDK_INT >= 16) {
                    SQLiteDatabase.deleteDatabase(file);
                } else {
                    file.delete();
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
