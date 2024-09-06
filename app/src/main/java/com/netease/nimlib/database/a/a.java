package com.netease.nimlib.database.a;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import com.netease.nimlib.database.g;
import java.io.File;

/* compiled from: AbstractDatabase.java */
/* loaded from: classes.dex */
public abstract class a implements g {
    public abstract int a(String str, String str2);

    public abstract int a(String str, String str2, String[] strArr);

    public abstract long a(String str, String str2, ContentValues contentValues);

    public abstract Cursor a(String str, String[] strArr);

    public abstract void a(String str);

    public abstract void a(String str, Object[] objArr);

    public abstract boolean a(Context context, String str, String str2, d[] dVarArr, int i);

    public abstract long b(String str, String str2, ContentValues contentValues);

    public abstract Cursor b(String str);

    public abstract long c(String str, String str2, ContentValues contentValues);

    public abstract boolean e();

    public abstract void f();

    public abstract void g();

    public abstract void h();

    public abstract void i();

    public static String a(Context context, String str) {
        String str2 = context.getApplicationInfo().dataDir + "/" + str;
        File file = new File(str2);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
        }
        if (com.netease.nimlib.log.b.a.a()) {
            Log.i("db", "ready to open db, path=" + str2);
        }
        return str2;
    }
}
