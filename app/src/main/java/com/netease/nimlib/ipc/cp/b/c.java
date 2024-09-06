package com.netease.nimlib.ipc.cp.b;

import android.content.Context;
import android.content.SharedPreferences;
import com.netease.nimlib.database.f;

/* compiled from: PreferenceDataHandler.java */
/* loaded from: classes.dex */
public abstract class c implements b {
    private static final String c = com.netease.nimlib.database.c.c();
    private final Context a;
    private final String b;

    @Override // com.netease.nimlib.ipc.cp.b.b
    public boolean a(String str) {
        return true;
    }

    protected c(Context context, String str) {
        this.a = context;
        this.b = str;
    }

    @Override // com.netease.nimlib.ipc.cp.b.b
    public String a(String str, String str2) {
        return a().getString(str, str2);
    }

    @Override // com.netease.nimlib.ipc.cp.b.b
    public int c(String str, String str2) {
        a().edit().putString(str, str2).apply();
        return 1;
    }

    @Override // com.netease.nimlib.ipc.cp.b.b
    public boolean a(String str, boolean z) {
        return a().getBoolean(str, z);
    }

    @Override // com.netease.nimlib.ipc.cp.b.b
    public void b(String str, boolean z) {
        a().edit().putBoolean(str, z).apply();
    }

    @Override // com.netease.nimlib.ipc.cp.b.b
    public void b(String str, int i) {
        a().edit().putInt(str, i).apply();
    }

    @Override // com.netease.nimlib.ipc.cp.b.b
    public int a(String str, int i) {
        return a().getInt(str, i);
    }

    @Override // com.netease.nimlib.ipc.cp.b.b
    public void b(String str, float f) {
        a().edit().putFloat(str, f).apply();
    }

    @Override // com.netease.nimlib.ipc.cp.b.b
    public float a(String str, float f) {
        return a().getFloat(str, f);
    }

    @Override // com.netease.nimlib.ipc.cp.b.b
    public void b(String str, long j) {
        if (c(str, j)) {
            a().edit().remove(str).apply();
        }
    }

    @Override // com.netease.nimlib.ipc.cp.b.b
    public long a(String str, long j) {
        long j2 = a().getLong(str, -1L);
        if (j2 == -1) {
            return d(str, -1L);
        }
        b(str, j2);
        return j2;
    }

    @Override // com.netease.nimlib.ipc.cp.b.b
    public void b(String str) {
        a().edit().remove(str).apply();
    }

    private SharedPreferences a() {
        return this.a.getSharedPreferences(this.b, 0);
    }

    private static boolean c(String str, long j) {
        String format = String.format("INSERT OR REPLACE INTO %s (%s) VALUES ('%s', '%s')", c, "key, long_value", com.netease.nimlib.database.a.c.a(str), Long.valueOf(j));
        b().f();
        try {
            b().a(format);
            b().h();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            b().g();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0043, code lost:
    
        if (r0.isClosed() == false) goto L12;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static long d(java.lang.String r5, long r6) {
        /*
            r0 = 0
            java.lang.String r1 = "SELECT long_value FROM %s where key='%s'"
            r2 = 2
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> L37 java.lang.Exception -> L39
            java.lang.String r3 = com.netease.nimlib.ipc.cp.b.c.c     // Catch: java.lang.Throwable -> L37 java.lang.Exception -> L39
            r4 = 0
            r2[r4] = r3     // Catch: java.lang.Throwable -> L37 java.lang.Exception -> L39
            r3 = 1
            java.lang.String r5 = com.netease.nimlib.database.a.c.a(r5)     // Catch: java.lang.Throwable -> L37 java.lang.Exception -> L39
            r2[r3] = r5     // Catch: java.lang.Throwable -> L37 java.lang.Exception -> L39
            java.lang.String r5 = java.lang.String.format(r1, r2)     // Catch: java.lang.Throwable -> L37 java.lang.Exception -> L39
            com.netease.nimlib.database.b r1 = b()     // Catch: java.lang.Throwable -> L37 java.lang.Exception -> L39
            android.database.Cursor r0 = r1.b(r5)     // Catch: java.lang.Throwable -> L37 java.lang.Exception -> L39
            if (r0 == 0) goto L2b
            boolean r5 = r0.moveToNext()     // Catch: java.lang.Throwable -> L37 java.lang.Exception -> L39
            if (r5 == 0) goto L2b
            long r5 = r0.getLong(r4)     // Catch: java.lang.Throwable -> L37 java.lang.Exception -> L39
            r6 = r5
        L2b:
            if (r0 == 0) goto L46
            boolean r5 = r0.isClosed()
            if (r5 != 0) goto L46
        L33:
            r0.close()
            goto L46
        L37:
            r5 = move-exception
            goto L47
        L39:
            r5 = move-exception
            r5.printStackTrace()     // Catch: java.lang.Throwable -> L37
            if (r0 == 0) goto L46
            boolean r5 = r0.isClosed()
            if (r5 != 0) goto L46
            goto L33
        L46:
            return r6
        L47:
            if (r0 == 0) goto L52
            boolean r6 = r0.isClosed()
            if (r6 != 0) goto L52
            r0.close()
        L52:
            goto L54
        L53:
            throw r5
        L54:
            goto L53
        */
        throw new UnsupportedOperationException("Method not decompiled: com.netease.nimlib.ipc.cp.b.c.d(java.lang.String, long):long");
    }

    private static com.netease.nimlib.database.b b() {
        return f.a().f();
    }
}
