package com.netease.nimlib.push;

import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Base64;
import com.netease.nimlib.push.net.lbs.IPVersion;
import com.netease.nimlib.session.y;

/* compiled from: Preferences.java */
/* loaded from: classes.dex */
public class e {
    public static String a() {
        return d("k_android_id");
    }

    public static int b() {
        return b("k_reliable_type", y.a().b());
    }

    public static void a(int i) {
        a("k_reliable_type", i);
    }

    public static void a(String str) {
        a("k_nosdl", str);
    }

    public static String c() {
        return d("k_nosdl");
    }

    public static void b(String str) {
        a("k_link", str);
    }

    public static String d() {
        return d("k_link");
    }

    public static void c(String str) {
        a("k_default_link", str);
    }

    public static String e() {
        return d("k_default_link");
    }

    public static void a(IPVersion iPVersion) {
        if (iPVersion == null) {
            iPVersion = com.netease.nimlib.push.net.lbs.a.a;
        }
        a("k_chosen_ip_version", iPVersion.getValue());
    }

    public static IPVersion f() {
        return IPVersion.value(b("k_chosen_ip_version", com.netease.nimlib.push.net.lbs.a.a.getValue()));
    }

    private static void a(String str, String str2) {
        try {
            SharedPreferences.Editor edit = g().edit();
            if (str2 == null) {
                str2 = "";
            }
            edit.putString(str, Base64.encodeToString(str2.getBytes(), 2));
            edit.apply();
        } catch (Exception e) {
            com.netease.nimlib.log.b.e("Pre", "error base 64", e);
        }
    }

    private static String d(String str) {
        try {
            String string = g().getString(str, null);
            if (TextUtils.isEmpty(string)) {
                return null;
            }
            return new String(Base64.decode(string, 2));
        } catch (Exception e) {
            com.netease.nimlib.log.b.e("Pre", "error base 64", e);
            return null;
        }
    }

    private static void a(String str, int i) {
        SharedPreferences.Editor edit = g().edit();
        edit.putInt(str, i);
        edit.apply();
    }

    private static int b(String str, int i) {
        return g().getInt(str, i);
    }

    static SharedPreferences g() {
        return com.netease.nimlib.c.e().getSharedPreferences("NIMSDK_Config_NEW_" + com.netease.nimlib.c.g(), 0);
    }

    static {
        com.netease.nimlib.c.e().getSharedPreferences("NIMSDK_Config_" + com.netease.nimlib.c.g(), 0).edit().clear().commit();
    }
}
