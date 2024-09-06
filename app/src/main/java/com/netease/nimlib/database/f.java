package com.netease.nimlib.database;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import com.netease.nimlib.biz.i;
import com.netease.nimlib.h;
import com.netease.nimlib.sdk.StatusCode;
import com.netease.nimlib.session.MsgDBHelper;
import java.io.File;

/* compiled from: NimDatabases.java */
/* loaded from: classes.dex */
public class f {
    private static f d = new f();
    private String a;
    private b b;
    private d c;

    public static f a() {
        return d;
    }

    public synchronized boolean a(Context context, String str) {
        this.a = str;
        String str2 = com.netease.nimlib.c.i().databaseEncryptKey;
        if (com.netease.nimlib.c.i().rollbackSQLCipher) {
            if (com.netease.nimlib.database.encrypt.c.a(com.netease.nimlib.c.e(), str, str2)) {
                i.a().h();
                h.a(StatusCode.DATA_UPGRADE);
                com.netease.nimlib.i.b.a(StatusCode.DATA_UPGRADE);
                return false;
            }
        } else if (com.netease.nimlib.database.encrypt.d.a(com.netease.nimlib.c.e(), str, str2)) {
            i.a().h();
            h.a(StatusCode.DATA_UPGRADE);
            com.netease.nimlib.i.b.a(StatusCode.DATA_UPGRADE);
            return false;
        }
        try {
            if (this.b == null || !this.b.e()) {
                boolean a = com.netease.nimlib.database.encrypt.d.a(b.a(str, false), b.a(str, true), str2);
                String a2 = b.a(str, a);
                if (!com.netease.nimlib.net.a.c.a.d(com.netease.nimlib.database.a.a.a(context, a2))) {
                    a.a(context, a2);
                }
                this.b = new b(context, str, str2, a);
                if (c() && this.b.a()) {
                    if (com.netease.nimlib.c.i().enableDatabaseBackup) {
                        this.b.b();
                        com.netease.nimlib.log.b.d("db", "backup main database started:" + a(context, b.a(str, a), this.b));
                    } else {
                        this.b.c();
                    }
                }
            }
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("db", "open main database error", th);
        }
        try {
            if (this.c == null || !this.c.e()) {
                boolean a3 = com.netease.nimlib.database.encrypt.d.a(d.a(str, false), d.a(str, true), str2);
                String a4 = d.a(str, a3);
                if (!com.netease.nimlib.net.a.c.a.d(com.netease.nimlib.database.a.a.a(context, a4))) {
                    a.a(context, a4);
                }
                this.c = new d(context, str, str2, a3);
                if (d()) {
                    if (com.netease.nimlib.c.i().enableRecentContactsTimeIndex) {
                        MsgDBHelper.createTimeIndex(this.c);
                    } else {
                        MsgDBHelper.dropTimeIndex(this.c);
                    }
                }
                if (d() && this.c.a()) {
                    if (com.netease.nimlib.c.i().enableDatabaseBackup) {
                        this.c.b();
                        com.netease.nimlib.log.b.d("db", "backup msg database started:" + a(context, d.a(str, a3), this.c));
                    } else {
                        this.c.c();
                    }
                }
            }
        } catch (Throwable th2) {
            com.netease.nimlib.log.b.e("db", "open msg database error", th2);
        }
        return b();
    }

    private boolean a(Context context, String str, g gVar) {
        if (!gVar.a()) {
            com.netease.nimlib.log.b.d("db", String.format("skip backup: %s not support wal", str));
            return false;
        }
        String a = com.netease.nimlib.database.a.a.a(context, str);
        if (!com.netease.nimlib.net.a.c.a.d(a)) {
            com.netease.nimlib.log.b.d("db", String.format("skip backup: %s is not exist", str));
            return false;
        }
        long length = new File(a).length();
        long availableBytes = new StatFs(Environment.getDataDirectory().getAbsolutePath()).getAvailableBytes();
        if (availableBytes < 2 * length) {
            com.netease.nimlib.log.b.d("db", String.format("skip backup: %s filesize %s availableBytes %s", str, Long.valueOf(length), Long.valueOf(availableBytes)));
            return false;
        }
        try {
            return a.a(context, str, gVar);
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("db", String.format("backup database: %s error", str), th);
            return false;
        }
    }

    public boolean b() {
        d dVar;
        b bVar = this.b;
        return bVar != null && bVar.e() && (dVar = this.c) != null && dVar.e();
    }

    public boolean c() {
        b bVar = this.b;
        return bVar != null && bVar.e();
    }

    public boolean d() {
        d dVar = this.c;
        return dVar != null && dVar.e();
    }

    public synchronized void e() {
        if (this.b != null) {
            this.b.i();
            this.b = null;
        }
        if (this.c != null) {
            this.c.i();
            this.c = null;
        }
    }

    public b f() {
        b bVar = this.b;
        if (bVar != null) {
            return bVar;
        }
        throw new IllegalStateException("Cache is not ready. Please login first!");
    }

    public d g() {
        d dVar = this.c;
        if (dVar != null) {
            return dVar;
        }
        throw new IllegalStateException("MsgDatabase is not opened. Please login first!");
    }

    public String h() {
        return this.a;
    }
}
