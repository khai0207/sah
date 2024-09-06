package com.netease.nimlib.o;

import android.content.SharedPreferences;
import android.os.Build;
import java.util.UUID;

/* compiled from: StatisticUtil.java */
/* loaded from: classes.dex */
public class v {
    public static String a() {
        return com.netease.nimlib.p.a.b();
    }

    public static String b() {
        return Build.VERSION.RELEASE;
    }

    public static String c() {
        String a = com.netease.nimlib.push.e.a();
        return a != null ? a : "";
    }

    public static String d() {
        return Build.SERIAL;
    }

    public static String e() {
        String c = c();
        if (c != null && !c.toLowerCase().equals("9774d56d682e549c") && !c.equals("")) {
            return c;
        }
        String d = d();
        return d == null ? f() : d;
    }

    private static String f() {
        String str = null;
        try {
            SharedPreferences sharedPreferences = com.netease.nimlib.c.e().getSharedPreferences("OpenUdid", 0);
            str = sharedPreferences.getString("OpenUdid", "");
            if (!str.equals("")) {
                return str;
            }
            String uuid = UUID.randomUUID().toString();
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putString("OpenUdid", uuid);
            edit.commit();
            return uuid;
        } catch (Exception unused) {
            return str;
        }
    }
}
