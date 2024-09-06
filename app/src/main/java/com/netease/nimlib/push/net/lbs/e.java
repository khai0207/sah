package com.netease.nimlib.push.net.lbs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/* compiled from: TurnAddress.java */
@Deprecated
/* loaded from: classes.dex */
public class e {
    private static e a = new e();

    public static e a() {
        return a;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v7, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.netease.nimlib.push.net.lbs.e] */
    synchronized void a(String str) {
        File a2;
        BufferedWriter bufferedWriter = null;
        BufferedWriter bufferedWriter2 = null;
        try {
            try {
                a2 = com.netease.nimlib.log.c.a.a.a(c());
            } catch (Exception e) {
                e = e;
            }
            if (a2 == null) {
                a(null);
                return;
            }
            BufferedWriter bufferedWriter3 = new BufferedWriter(new FileWriter(a2, false));
            try {
                bufferedWriter3.write(str);
                bufferedWriter3.flush();
                ?? r0 = "core";
                com.netease.nimlib.log.b.c("core", "update turn address:" + str);
                a(bufferedWriter3);
                bufferedWriter = r0;
            } catch (Exception e2) {
                e = e2;
                bufferedWriter2 = bufferedWriter3;
                e.printStackTrace();
                a(bufferedWriter2);
                bufferedWriter = bufferedWriter2;
            } catch (Throwable th) {
                th = th;
                bufferedWriter = bufferedWriter3;
                a(bufferedWriter);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public String[] b() {
        BufferedReader bufferedReader;
        String str;
        File a2;
        try {
            a2 = com.netease.nimlib.log.c.a.a.a(c());
        } catch (Throwable th) {
            th = th;
            bufferedReader = null;
        }
        if (a2 != null && a2.exists()) {
            bufferedReader = new BufferedReader(new FileReader(a2));
            try {
                str = bufferedReader.readLine();
            } catch (Throwable th2) {
                th = th2;
                try {
                    com.netease.nimlib.log.b.a("get turn address error", th);
                    a(bufferedReader);
                    str = null;
                    com.netease.nimlib.log.b.c("ui", "get turn address:" + str);
                    return str == null ? null : null;
                } finally {
                    a(bufferedReader);
                }
            }
            com.netease.nimlib.log.b.c("ui", "get turn address:" + str);
            if (str == null && !str.isEmpty()) {
                return str.split(";");
            }
        }
        a((Closeable) null);
        return null;
    }

    private String c() {
        return com.netease.nimlib.a.a + "/rtc_turn.dat";
    }

    private void a(Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
