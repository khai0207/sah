package com.netease.nimlib;

import com.netease.nimlib.sdk.ModeCode;
import com.netease.nimlib.sdk.StatusCode;
import java.util.ArrayList;

/* compiled from: SDKState.java */
/* loaded from: classes.dex */
public class h {
    private static boolean c;
    private static boolean d;
    private static boolean e;
    private static int g;
    private static int h;
    private static int i;
    private static ArrayList<com.netease.nimlib.biz.f> j;
    private static ModeCode a = ModeCode.INIT;
    private static StatusCode b = StatusCode.UNLOGIN;
    private static String f = "";

    public static boolean a() {
        return c;
    }

    public static void a(boolean z) {
        c = z;
    }

    public static boolean b() {
        return d;
    }

    public static void b(boolean z) {
        d = z;
    }

    public static boolean c() {
        return e;
    }

    public static void c(boolean z) {
        e = z;
    }

    public static String d() {
        return f;
    }

    public static void a(String str) {
        f = str;
    }

    public static synchronized StatusCode e() {
        StatusCode statusCode;
        synchronized (h.class) {
            statusCode = b;
        }
        return statusCode;
    }

    public static synchronized void a(StatusCode statusCode) {
        synchronized (h.class) {
            com.netease.nimlib.log.b.c("SDKState", "set status to " + statusCode);
            b = statusCode;
        }
    }

    public static ModeCode f() {
        return a;
    }

    public static synchronized void a(ModeCode modeCode) {
        synchronized (h.class) {
            a = modeCode;
            com.netease.nimlib.log.b.A("set sdk mode to " + modeCode);
        }
    }

    public static boolean g() {
        return (g & 1) != 0;
    }

    public static boolean h() {
        return (g & 2) != 0;
    }

    public static void a(int i2) {
        g = i2 | g;
    }

    public static int i() {
        return h;
    }

    public static void b(int i2) {
        h = i2;
    }

    public static int j() {
        return i;
    }

    public static void c(int i2) {
        i = i2;
    }

    public static ArrayList<com.netease.nimlib.biz.f> k() {
        return j;
    }

    public static void a(ArrayList<com.netease.nimlib.biz.f> arrayList) {
        j = arrayList;
    }
}
