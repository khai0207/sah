package com.netease.nimlib.biz;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import com.netease.nimlib.amazonaws.mobileconnectors.s3.transferutility.TransferTable;

/* loaded from: classes.dex */
public class SyncCrossProcessDBHelper {
    private static final String TAG = "SyncCPDBHelper";

    public static String getSpName() {
        return String.format("SP_CP_%s_%s", com.netease.nimlib.c.g(), com.netease.nimlib.c.n());
    }

    public static void saveLongValue(String str, long j) {
        if (com.netease.nimlib.c.O()) {
            try {
                String str2 = com.netease.nimlib.c.e().getPackageName() + ".ipc.provider.preference";
                String spName = getSpName();
                Uri parse = Uri.parse(String.format("content://%s/long/%s/%s", str2, spName, str));
                ContentValues contentValues = new ContentValues();
                contentValues.put(TransferTable.COLUMN_KEY, str);
                contentValues.put("value", Long.valueOf(j));
                int update = com.netease.nimlib.c.e().getContentResolver().update(parse, contentValues, null, null);
                if (update > 0) {
                    com.netease.nimlib.c.e().getSharedPreferences(String.format("Core_%s", spName), 4).edit().putLong(str, j).apply();
                }
                if (com.netease.nimlib.log.b.a.a()) {
                    Log.d(TAG, String.format("saveLongValue name:%s key:%s value:%s result:%s", spName, str, Long.valueOf(j), Integer.valueOf(update)));
                }
            } catch (Exception e) {
                e.printStackTrace();
                com.netease.nimlib.log.b.f(TAG, String.format("saveLongValue key %s Exception %s", str, e.toString()));
            }
        }
    }

    public static long queryLongValue(String str, long j) {
        String spName;
        SharedPreferences sharedPreferences;
        Cursor query;
        if (!com.netease.nimlib.c.O()) {
            try {
                com.netease.nimlib.log.b.d(TAG, "failed to queryLongValue");
            } catch (Throwable th) {
                th.printStackTrace();
                if (com.netease.nimlib.log.b.a.a()) {
                    Log.i(TAG, "failed to queryLongValue");
                }
            }
            return j;
        }
        try {
            String str2 = com.netease.nimlib.c.e().getPackageName() + ".ipc.provider.preference";
            spName = getSpName();
            sharedPreferences = com.netease.nimlib.c.e().getSharedPreferences(String.format("Core_%s", spName), 4);
            query = com.netease.nimlib.c.e().getContentResolver().query(Uri.parse(String.format("content://%s/long/%s/%s", str2, spName, str)), null, null, null, null);
            if (query == null) {
                if (sharedPreferences.contains(str)) {
                    long j2 = sharedPreferences.getLong(str, j);
                    com.netease.nimlib.log.b.d(TAG, String.format("queryLongValue when cursor == null, %d", Long.valueOf(j2)));
                    return j2;
                }
                com.netease.nimlib.log.b.d(TAG, String.format("queryLongValue when cursor == null", new Object[0]));
            }
        } catch (Exception e) {
            e.printStackTrace();
            com.netease.nimlib.log.b.e(TAG, String.format("queryLongValue key %s", str), e);
        }
        if (query != null && query.moveToFirst()) {
            long j3 = query.getLong(0);
            query.close();
            sharedPreferences.edit().putLong(str, j3).apply();
            return j3;
        }
        com.netease.nimlib.log.b.d(TAG, String.format("queryLongValue name:%s key:%s value:%s", spName, str, "empty"));
        return j;
    }

    public static String queryStringValue(String str, String str2) {
        String spName;
        Cursor query;
        if (!com.netease.nimlib.c.O()) {
            try {
                com.netease.nimlib.log.b.d(TAG, "failed to queryStringValue");
            } catch (Throwable th) {
                th.printStackTrace();
                if (com.netease.nimlib.log.b.a.a()) {
                    Log.i(TAG, "failed to queryStringValue");
                }
            }
            return str2;
        }
        try {
            String str3 = com.netease.nimlib.c.e().getPackageName() + ".ipc.provider.preference";
            spName = getSpName();
            query = com.netease.nimlib.c.e().getContentResolver().query(Uri.parse(String.format("content://%s/string/%s/%s", str3, spName, str)), null, null, null, null);
            if (query == null) {
                com.netease.nimlib.log.b.d(TAG, String.format("queryStringValue when cursor == null", new Object[0]));
            }
        } catch (Exception e) {
            e.printStackTrace();
            com.netease.nimlib.log.b.e(TAG, String.format("queryStringValue key %s", str), e);
        }
        if (query != null && query.moveToFirst()) {
            String string = query.getString(0);
            query.close();
            return string;
        }
        com.netease.nimlib.log.b.d(TAG, String.format("queryStringValue name:%s key:%s value:%s", spName, str, "empty"));
        return str2;
    }

    public static void saveStringValue(String str, String str2) {
        if (com.netease.nimlib.c.O()) {
            try {
                String str3 = com.netease.nimlib.c.e().getPackageName() + ".ipc.provider.preference";
                String spName = getSpName();
                Uri parse = Uri.parse(String.format("content://%s/string/%s/%s", str3, spName, str));
                ContentValues contentValues = new ContentValues();
                contentValues.put(TransferTable.COLUMN_KEY, str);
                contentValues.put("value", str2);
                int update = com.netease.nimlib.c.e().getContentResolver().update(parse, contentValues, null, null);
                if (update > 0) {
                    com.netease.nimlib.c.e().getSharedPreferences(String.format("Core_%s", spName), 4).edit().putString(str, str2).apply();
                }
                if (com.netease.nimlib.log.b.a.a()) {
                    Log.d(TAG, String.format("saveLongValue name:%s key:%s value:%s result:%s", spName, str, str2, Integer.valueOf(update)));
                }
            } catch (Exception e) {
                e.printStackTrace();
                com.netease.nimlib.log.b.f(TAG, String.format("saveLongValue key %s Exception %s", str, e.toString()));
            }
        }
    }
}
