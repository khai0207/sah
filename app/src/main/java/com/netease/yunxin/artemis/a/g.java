package com.netease.yunxin.artemis.a;

import android.content.Context;
import android.content.SharedPreferences;

/* compiled from: SharedHelper.java */
/* loaded from: classes.dex */
public final class g {
    private static g b;
    public Context a;

    private g() {
    }

    public static g a() {
        if (b == null) {
            b = new g();
        }
        return b;
    }

    public final void a(String str) {
        SharedPreferences.Editor edit = this.a.getSharedPreferences("probe_rec", 0).edit();
        edit.putString("next_fetch_time", str);
        edit.apply();
    }
}
