package com.netease.nimlib.database;

import android.content.Context;

/* compiled from: MainDatabase.java */
/* loaded from: classes.dex */
public class b extends com.netease.nimlib.database.a.b {
    public b(Context context, String str, String str2, boolean z) throws InstantiationException, IllegalAccessException {
        super((com.netease.nimlib.database.a.a) (z ? com.netease.nimlib.database.encrypt.b.class : com.netease.nimlib.database.plain.c.class).newInstance(), str);
        a(context, a(str, z), str2, c.d(), 16);
    }

    public static String a(String str, boolean z) {
        Object[] objArr = new Object[3];
        objArr[0] = com.netease.nimlib.c.g() + "/" + str;
        objArr[1] = "nim_cache.db";
        objArr[2] = z ? ".enc" : "";
        return String.format("%s/%s%s", objArr);
    }
}
