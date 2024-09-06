package com.netease.nimlib.biz;

import android.content.SharedPreferences;

/* compiled from: GlobalPreferences.java */
/* loaded from: classes.dex */
public class c {
    public static void a(long j) {
        a("k_abtest_re_pull_time", j);
    }

    public static long a() {
        return b("k_abtest_re_pull_time");
    }

    public static void a(boolean z) {
        b("k_abtest_interval_flag", z);
    }

    public static boolean b() {
        return a("k_abtest_interval_flag", false);
    }

    public static void b(boolean z) {
        b("k_grow_device_enable", z);
    }

    public static boolean c() {
        return a("k_grow_device_enable", false);
    }

    public static void a(String str) {
        a("k_global_grow_device_token", str);
    }

    public static String d() {
        return c("k_global_grow_device_token");
    }

    public static void c(boolean z) {
        b("K_AOS_HEART_BEAT_TERM_ENABLED", z);
    }

    public static boolean e() {
        return a("K_AOS_HEART_BEAT_TERM_ENABLED", false);
    }

    public static void b(long j) {
        a("K_AOS_HEART_BEAT_TERM_INTERVAL", j);
    }

    public static long f() {
        return b("K_AOS_HEART_BEAT_TERM_INTERVAL", 0L);
    }

    public static void d(boolean z) {
        b("K_RECEIVE_MESSAGE_WITH_UNEXPECTED_LONG_DOWN_TIME_ENABLED", z);
    }

    public static boolean g() {
        return a("K_RECEIVE_MESSAGE_WITH_UNEXPECTED_LONG_DOWN_TIME_ENABLED", false);
    }

    public static void c(long j) {
        a("K_RECEIVE_MESSAGE_WITH_UNEXPECTED_LONG_DOWN_TIME_DT_UP_LIMIT", j);
    }

    public static long d(long j) {
        return b("K_RECEIVE_MESSAGE_WITH_UNEXPECTED_LONG_DOWN_TIME_DT_UP_LIMIT", j);
    }

    public static void e(long j) {
        a("K_RECEIVE_MESSAGE_WITH_UNEXPECTED_LONG_DOWN_TIME_KEEP_ON_FG_UP_DOWN_LIMIT", j);
    }

    public static long f(long j) {
        return b("K_RECEIVE_MESSAGE_WITH_UNEXPECTED_LONG_DOWN_TIME_KEEP_ON_FG_UP_DOWN_LIMIT", j);
    }

    public static void e(boolean z) {
        b("K_RECEIVE_MESSAGE_DOWN_TIME_RELIABLE_ENABLED", z);
    }

    public static boolean h() {
        return a("K_RECEIVE_MESSAGE_DOWN_TIME_RELIABLE_ENABLED", false);
    }

    public static void g(long j) {
        a("K_RECEIVE_MESSAGE_DOWN_TIME_RELIABLE_RELIABLE_RTT_MULTIPLE", j);
    }

    public static long i() {
        return b("K_RECEIVE_MESSAGE_DOWN_TIME_RELIABLE_RELIABLE_RTT_MULTIPLE", 0L);
    }

    public static void f(boolean z) {
        b("K_RECEIVE_MESSAGE_DOWN_TIME_RELIABLE_UPLOAD_QS", z);
    }

    public static boolean j() {
        return a("K_RECEIVE_MESSAGE_DOWN_TIME_RELIABLE_UPLOAD_QS", false);
    }

    public static void h(long j) {
        a("K_EXCEPTION_CONTEXT_DISK_INFO_FREQUENCY_CONTROL", j);
    }

    public static long k() {
        return b("K_EXCEPTION_CONTEXT_DISK_INFO_FREQUENCY_CONTROL", 0L);
    }

    public static void g(boolean z) {
        b("K_EXCEPTION_CONTEXT_DISK_INFO_ENABLED", z);
    }

    public static boolean l() {
        return a("K_EXCEPTION_CONTEXT_DISK_INFO_ENABLED", false);
    }

    public static void h(boolean z) {
        b("K_DATABASE_FUNCTION_TRANSFORMATION_ENABLE", z);
    }

    public static boolean m() {
        return a("K_DATABASE_FUNCTION_TRANSFORMATION_ENABLE", false);
    }

    public static void i(boolean z) {
        b("K_DATABASE_MESSAGE_PARAMETERIZED_ENABLE", z);
    }

    public static boolean n() {
        return a("K_DATABASE_MESSAGE_PARAMETERIZED_ENABLE", false);
    }

    public static void j(boolean z) {
        b("K_EXCEPTION_DATABASE_TRANSFORM_STRING_ERROR20231225", z);
    }

    public static boolean o() {
        return a("K_EXCEPTION_DATABASE_TRANSFORM_STRING_ERROR20231225", false);
    }

    private static boolean a(String str, boolean z) {
        return p().getBoolean(str, z);
    }

    private static void b(String str, boolean z) {
        SharedPreferences.Editor edit = p().edit();
        edit.putBoolean(str, z);
        edit.commit();
    }

    private static void a(String str, long j) {
        SharedPreferences.Editor edit = p().edit();
        edit.putLong(str, j);
        edit.commit();
    }

    private static long b(String str) {
        return p().getLong(str, 0L);
    }

    private static long b(String str, long j) {
        return p().getLong(str, j);
    }

    private static void a(String str, String str2) {
        SharedPreferences.Editor edit = p().edit();
        edit.putString(str, str2);
        edit.commit();
    }

    private static String c(String str) {
        return p().getString(str, null);
    }

    public static SharedPreferences p() {
        return com.netease.nimlib.c.e().getSharedPreferences("NIMSDK_GLOBAL_CONFIG", 4);
    }
}
