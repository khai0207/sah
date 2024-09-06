package com.netease.nimlib.d;

import android.text.TextUtils;

/* compiled from: SDKConfig.java */
/* loaded from: classes.dex */
abstract class b {
    boolean a = false;
    boolean b = true;
    boolean c = true;
    boolean d = true;

    private String a(boolean z) {
        return z ? "1" : "0";
    }

    abstract String b();

    b() {
    }

    public boolean a() {
        return this.c;
    }

    public String c() {
        return a(this.a) + "," + a(this.b) + "," + a(this.c) + "," + a(this.d);
    }

    void a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            com.netease.nimlib.log.b.d(d(), "read sdk config from " + str2 + ", but get null");
            return;
        }
        String[] split = str.split(",");
        if (split.length == 4) {
            this.a = a(split[0]);
            this.b = a(split[1]);
            this.c = a(split[2]);
            this.d = a(split[3]);
            com.netease.nimlib.log.b.d(d(), "read sdk config from " + str2 + ", " + toString());
        }
    }

    public String toString() {
        return "sdk config=[" + c() + "]";
    }

    private boolean a(String str) {
        return !"0".equals(str);
    }

    String d() {
        return "sdk_config_" + b();
    }
}
