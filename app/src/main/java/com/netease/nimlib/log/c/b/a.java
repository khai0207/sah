package com.netease.nimlib.log.c.b;

import android.text.TextUtils;
import com.netease.nimlib.log.c.a;

/* compiled from: AbsNimLog.java */
/* loaded from: classes.dex */
public abstract class a {
    private static com.netease.nimlib.log.c.a a;
    private static String b;

    protected static String R(String str) {
        return str;
    }

    protected static void a(com.netease.nimlib.log.c.a aVar, String str, String str2, String str3, int i, int i2, int i3, boolean z, a.InterfaceC0041a interfaceC0041a) {
        a = aVar;
        b = str;
        aVar.a(str2, str3, i, i2, i3, z, interfaceC0041a);
    }

    public static void c() {
        com.netease.nimlib.log.c.a aVar = a;
        if (aVar != null) {
            aVar.b();
        }
    }

    protected static com.netease.nimlib.log.c.a d() {
        com.netease.nimlib.log.c.a aVar = a;
        return aVar == null ? com.netease.nimlib.log.c.a.a() : aVar;
    }

    public static void b(String str, String str2) {
        d().b(Q(str), R(str2));
    }

    public static void a(String str, String str2, Throwable th) {
        d().b(Q(str), R(str2), th);
    }

    public static void c(String str, String str2) {
        d().d(Q(str), R(str2));
    }

    public static void b(String str, String str2, Throwable th) {
        d().d(Q(str), R(str2), th);
    }

    public static void d(String str, String str2) {
        d().a(Q(str), R(str2));
    }

    public static void c(String str, String str2, Throwable th) {
        d().a(Q(str), R(str2), th);
    }

    public static void e(String str, String str2) {
        d().e(Q(str), R(str2));
    }

    public static void d(String str, String str2, Throwable th) {
        d().e(Q(str), R(str2), th);
    }

    public static void e(String str, Throwable th) {
        d().e(Q(str), R(""), th);
    }

    public static void f(String str, String str2) {
        d().c(Q(str), R(str2));
    }

    public static void e(String str, String str2, Throwable th) {
        d().c(Q(str), R(str2), th);
    }

    public static void N(String str) {
        d().a(Q("ui"), R(str));
    }

    public static void O(String str) {
        d().a(Q("core"), R(str));
    }

    public static void P(String str) {
        d().d(Q("test"), R(str));
    }

    protected static String Q(String str) {
        if (TextUtils.isEmpty(b)) {
            return str;
        }
        return "[" + b + "]" + str;
    }

    protected static String g(String str, String str2) {
        return str + ": " + str2;
    }
}
