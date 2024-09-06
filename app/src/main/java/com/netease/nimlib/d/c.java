package com.netease.nimlib.d;

import android.content.SharedPreferences;
import org.json.JSONObject;

/* compiled from: SDKConfigPush.java */
/* loaded from: classes.dex */
public class c extends b {
    @Override // com.netease.nimlib.d.b
    String b() {
        return "push";
    }

    @Override // com.netease.nimlib.d.b
    public /* bridge */ /* synthetic */ boolean a() {
        return super.a();
    }

    @Override // com.netease.nimlib.d.b
    public /* bridge */ /* synthetic */ String c() {
        return super.c();
    }

    @Override // com.netease.nimlib.d.b
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    private c() {
        f();
    }

    public void a(JSONObject jSONObject) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        if (jSONObject == null) {
            return;
        }
        boolean z5 = false;
        try {
            boolean z6 = true;
            if (jSONObject.has("loc") && (z4 = jSONObject.getBoolean("loc")) != this.a) {
                this.a = z4;
                z5 = true;
            }
            if (jSONObject.has("wifi") && (z3 = jSONObject.getBoolean("wifi")) != this.b) {
                this.b = z3;
                z5 = true;
            }
            if (jSONObject.has("ipc_ack") && (z2 = jSONObject.getBoolean("ipc_ack")) != this.c) {
                this.c = z2;
                z5 = true;
            }
            if (!jSONObject.has("self_kill") || (z = jSONObject.getBoolean("self_kill")) == this.d) {
                z6 = z5;
            } else {
                this.d = z;
            }
            com.netease.nimlib.log.b.d(d(), "read sdk config from lbs, " + toString() + ", changed=" + z6);
            if (z6) {
                g();
                com.netease.nimlib.ipc.e.d();
                com.netease.nimlib.log.b.d(d(), "notify sdk config to UI...");
            }
        } catch (Throwable th) {
            com.netease.nimlib.log.b.d(d(), "read sdk config from lbs error, e=" + th.getMessage());
        }
    }

    private void f() {
        this.a = h();
        this.b = i();
        this.c = j();
        this.d = k();
        com.netease.nimlib.log.b.d(d(), "read sdk config from SP, " + toString());
    }

    private void g() {
        a(this.a);
        b(this.b);
        c(this.c);
        d(this.d);
        com.netease.nimlib.log.b.d(d(), "write sdk config to SP, " + toString());
    }

    private static void a(boolean z) {
        a("loc", z);
    }

    private static boolean h() {
        return a("loc");
    }

    private static void b(boolean z) {
        a("wifi", z);
    }

    private static boolean i() {
        return a("wifi");
    }

    private static void c(boolean z) {
        a("ipc_ack", z);
    }

    private static boolean j() {
        return a("ipc_ack");
    }

    private static void d(boolean z) {
        a("self_kill", z);
    }

    private static boolean k() {
        return a("self_kill");
    }

    private static void a(String str, boolean z) {
        SharedPreferences.Editor edit = l().edit();
        edit.putBoolean(str, z);
        edit.apply();
    }

    private static boolean a(String str) {
        return l().getBoolean(str, true);
    }

    private static SharedPreferences l() {
        return com.netease.nimlib.c.e().getSharedPreferences("NIMSDK_CONFIG_STRATEGY_" + com.netease.nimlib.c.g(), 0);
    }

    public static c e() {
        return a.a;
    }

    /* compiled from: SDKConfigPush.java */
    /* loaded from: classes.dex */
    private static class a {
        private static final c a = new c();
    }
}
