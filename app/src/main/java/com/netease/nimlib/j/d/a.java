package com.netease.nimlib.j.d;

import android.os.Handler;
import android.os.Looper;
import com.netease.nimlib.i.k;
import com.netease.nimlib.o.p;
import com.netease.nimlib.o.w;
import com.netease.nimlib.sdk.migration.processor.IMsgExportProcessor;
import com.netease.nimlib.sdk.migration.processor.IMsgMigrationProgress;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: AbsMigrationTask.java */
/* loaded from: classes.dex */
public abstract class a implements Runnable {
    private static String f = "AbsMigrationTask";
    volatile boolean a;
    File b;
    long c;
    k d;
    final ArrayList<File> e = new ArrayList<>();
    private Handler g;
    private IMsgMigrationProgress h;
    private boolean i;

    a(k kVar, IMsgMigrationProgress iMsgMigrationProgress, String str, boolean z) {
        f = str;
        this.d = kVar;
        this.i = z;
        if (!p.b(com.netease.nimlib.c.e())) {
            a(415);
            return;
        }
        String a = com.netease.nimlib.o.b.c.a((iMsgMigrationProgress instanceof IMsgExportProcessor ? "msg_export_origin_" : "msg_import_origin_") + w.b(), com.netease.nimlib.o.b.b.TYPE_FILE);
        if (!com.netease.nimlib.o.b.c.a(com.netease.nimlib.o.b.b.TYPE_FILE)) {
            a(-50);
            return;
        }
        this.g = new Handler(Looper.getMainLooper());
        this.h = iMsgMigrationProgress;
        File file = new File(a);
        this.b = file;
        if (!file.getParentFile().exists()) {
            this.b.getParentFile().mkdirs();
        }
        this.e.add(this.b);
    }

    public void a() {
        this.a = true;
        c();
    }

    protected void a(Throwable th, String str, int i) {
        if (this.a) {
            return;
        }
        com.netease.nimlib.log.b.e(f, str, th);
        th.printStackTrace();
        a(i);
    }

    private void c() {
        if (this.i) {
            Iterator<File> it = this.e.iterator();
            while (it.hasNext()) {
                File next = it.next();
                if (next.exists()) {
                    next.delete();
                }
            }
        }
    }

    boolean a(File file) {
        return file == null || !file.exists() || file.length() <= 0;
    }

    void a(final int i, final int i2, boolean z) {
        if (z) {
            this.h.progressUpdate(i, i2);
        } else {
            this.g.post(new Runnable() { // from class: com.netease.nimlib.j.d.a.1
                @Override // java.lang.Runnable
                public void run() {
                    a.this.h.progressUpdate(i, i2);
                }
            });
        }
    }

    void a(int i) {
        if (this.a) {
            return;
        }
        this.a = true;
        c();
        com.netease.nimlib.j.b.a().b(this.d);
        this.d.a(i).b();
    }

    public boolean b() {
        return this.a;
    }
}
