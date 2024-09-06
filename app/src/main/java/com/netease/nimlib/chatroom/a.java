package com.netease.nimlib.chatroom;

import android.content.SharedPreferences;
import android.text.TextUtils;
import java.util.UUID;

/* compiled from: AnonymousAccountCache.java */
/* loaded from: classes.dex */
class a {
    static String a() {
        String b = b();
        if (!TextUtils.isEmpty(b) && a(b)) {
            return b;
        }
        String str = "nimanon_" + UUID.randomUUID().toString();
        b(str);
        return str;
    }

    static boolean a(String str) {
        return !TextUtils.isEmpty(str) && str.indexOf("nimanon_") == 0;
    }

    private static String b() {
        return c("anonymous_account");
    }

    private static void b(String str) {
        a("anonymous_account", str);
    }

    private static void a(String str, String str2) {
        SharedPreferences.Editor edit = c().edit();
        edit.putString(str, str2);
        edit.apply();
    }

    private static String c(String str) {
        return c().getString(str, null);
    }

    private static SharedPreferences c() {
        return com.netease.nimlib.c.e().getSharedPreferences("NIMSDK_ROOM_CACHE_" + com.netease.nimlib.c.g(), 0);
    }
}
