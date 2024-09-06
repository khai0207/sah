package com.netease.nimlib.abtest;

import com.netease.nimlib.g;

/* compiled from: ABTestConfigHelper.java */
/* loaded from: classes.dex */
public class b {
    public static final int a;
    private static final int b;

    public static void a(boolean z) {
        com.netease.nimlib.biz.c.d(z);
    }

    public static void b(boolean z) {
        com.netease.nimlib.biz.c.c(z);
    }

    public static boolean a() {
        return com.netease.nimlib.biz.c.g();
    }

    public static void a(long j) {
        com.netease.nimlib.biz.c.c(j);
    }

    public static long b(long j) {
        return com.netease.nimlib.biz.c.d(j);
    }

    public static void c(long j) {
        com.netease.nimlib.biz.c.e(j);
    }

    public static long d(long j) {
        return com.netease.nimlib.biz.c.f(j);
    }

    public static boolean b() {
        return com.netease.nimlib.biz.c.e();
    }

    public static void e(long j) {
        com.netease.nimlib.biz.c.b(j);
    }

    public static long c() {
        return com.netease.nimlib.biz.c.f();
    }

    public static void c(boolean z) {
        com.netease.nimlib.biz.c.e(z);
    }

    public static boolean d() {
        return com.netease.nimlib.biz.c.h();
    }

    public static void f(long j) {
        com.netease.nimlib.biz.c.g(j);
    }

    public static long e() {
        return com.netease.nimlib.biz.c.i();
    }

    public static void d(boolean z) {
        com.netease.nimlib.biz.c.f(z);
    }

    public static boolean f() {
        return com.netease.nimlib.biz.c.j();
    }

    static {
        int b2 = (int) (g.c().b() / 1000);
        a = b2;
        b = b2 + 5;
    }

    public static int g() {
        return com.netease.nimlib.biz.c.p().getInt("K_QUICK_LINK_TIMEOUT", b);
    }

    public static int h() {
        return com.netease.nimlib.biz.c.p().getInt("K_QUICK_LINK_CONNECT_TIMEOUT", 5);
    }

    public static boolean i() {
        return com.netease.nimlib.biz.c.p().getBoolean("K_QUICK_LINK_LONG_TIMEOUT_AT_FIRST", true);
    }

    public static int j() {
        return com.netease.nimlib.biz.c.p().getInt("K_QUICK_LINK_REUSE_TTL", 14400);
    }

    public static boolean k() {
        return com.netease.nimlib.biz.c.p().getBoolean("K_QUICK_LINK_ENABLED", false);
    }

    public static void a(int i) {
        com.netease.nimlib.biz.c.p().edit().putInt("K_QUICK_LINK_TIMEOUT", i).commit();
    }

    public static void b(int i) {
        com.netease.nimlib.biz.c.p().edit().putInt("K_QUICK_LINK_CONNECT_TIMEOUT", i).commit();
    }

    public static void e(boolean z) {
        com.netease.nimlib.biz.c.p().edit().putBoolean("K_QUICK_LINK_LONG_TIMEOUT_AT_FIRST", z).commit();
    }

    public static void c(int i) {
        com.netease.nimlib.biz.c.p().edit().putInt("K_QUICK_LINK_REUSE_TTL", i).commit();
    }

    public static void f(boolean z) {
        com.netease.nimlib.biz.c.p().edit().putBoolean("K_QUICK_LINK_ENABLED", z).commit();
    }

    public static void g(boolean z) {
        com.netease.nimlib.biz.c.p().edit().putBoolean("K_FCS_NEXT_ENABLED", z).commit();
    }

    public static boolean l() {
        return com.netease.nimlib.biz.c.p().getBoolean("K_FCS_NEXT_ENABLED", false) && com.netease.nimlib.plugin.c.a().b() != null;
    }
}
