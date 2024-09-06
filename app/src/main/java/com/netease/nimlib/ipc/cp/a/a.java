package com.netease.nimlib.ipc.cp.a;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.netease.nimlib.amazonaws.mobileconnectors.s3.transferutility.TransferTable;
import java.util.HashMap;
import java.util.Map;

/* compiled from: ProviderClient.java */
/* loaded from: classes.dex */
public final class a {
    private static Map<String, a> a = new HashMap();
    private Context b;
    private String c;

    public static synchronized a a(Context context, String str) {
        a aVar;
        synchronized (a.class) {
            if (!a.containsKey(str)) {
                a.put(str, new a(context, str));
            }
            aVar = a.get(str);
        }
        return aVar;
    }

    private a(Context context, String str) {
        this.b = context;
        this.c = str;
    }

    public String a(String str, String str2) throws Exception {
        Cursor query = this.b.getContentResolver().query(com.netease.nimlib.ipc.cp.c.a.a(this.b, this.c, str, 1), null, null, null, null);
        if (query != null && query.moveToFirst()) {
            str2 = query.getString(query.getColumnIndex("value"));
        }
        a(query);
        return str2;
    }

    public int b(String str, String str2) {
        Uri a2 = com.netease.nimlib.ipc.cp.c.a.a(this.b, this.c, str, 1);
        ContentValues contentValues = new ContentValues();
        contentValues.put(TransferTable.COLUMN_KEY, str);
        contentValues.put("value", str2);
        return this.b.getContentResolver().update(a2, contentValues, null, null);
    }

    public void c(String str, String str2) {
        Uri a2 = com.netease.nimlib.ipc.cp.c.a.a(this.b, this.c, str, 6);
        ContentValues contentValues = new ContentValues();
        contentValues.put(TransferTable.COLUMN_KEY, str);
        contentValues.put("value", str2);
        this.b.getContentResolver().update(a2, contentValues, null, null);
    }

    private static void a(Cursor cursor) {
        if (cursor == null || cursor.isClosed()) {
            return;
        }
        cursor.close();
    }
}
