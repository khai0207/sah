package com.netease.nimlib.net.a.b;

import android.content.SharedPreferences;
import com.netease.nimlib.net.a.b.c.d;

/* compiled from: UploadCache.java */
/* loaded from: classes.dex */
public class b {
    public String a(String str, Long l) {
        return a().getString(str + "_tokens_" + l, null);
    }

    public void a(String str, Long l, String str2) {
        SharedPreferences.Editor edit = a().edit();
        edit.putString(str + "_tokens_" + l, str2);
        edit.apply();
    }

    public String a(String str) {
        return a().getString("fc/" + str, null);
    }

    public void a(String str, String str2) {
        SharedPreferences.Editor edit = a().edit();
        edit.putString("fc/" + str, str2);
        edit.apply();
    }

    public void b(String str) {
        SharedPreferences.Editor edit = a().edit();
        edit.remove("fc/" + str);
        edit.apply();
    }

    public d c(String str) {
        String string = a().getString("bo/" + str, null);
        if (string == null) {
            return null;
        }
        return d.h(string);
    }

    public void a(String str, d dVar) {
        SharedPreferences.Editor edit = a().edit();
        edit.putString("bo/" + str, d.a(dVar));
        edit.apply();
    }

    public void d(String str) {
        SharedPreferences.Editor edit = a().edit();
        edit.remove("bo/" + str);
        edit.apply();
    }

    private SharedPreferences a() {
        return com.netease.nimlib.c.e().getSharedPreferences("NimSDK_NOS_" + com.netease.nimlib.c.g(), 0);
    }
}
