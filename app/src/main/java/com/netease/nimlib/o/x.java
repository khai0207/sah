package com.netease.nimlib.o;

import android.content.Context;
import android.os.Process;
import android.text.TextUtils;
import defpackage.C$r8$backportedMethods$utility$Objects$2$equals;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/* compiled from: SystemUtil.java */
/* loaded from: classes.dex */
public class x {
    public static boolean a(String str) {
        try {
            System.loadLibrary(str);
            return true;
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("SystemUtil", "load library " + str + " failed", th);
            com.netease.nimlib.n.e.a(str, com.netease.nimlib.n.b.k.kLoad, th.toString(), "load library " + str + " failed");
            return false;
        }
    }

    public static boolean b(String str) {
        try {
            System.loadLibrary(str);
            return true;
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("SystemUtil", "load library " + str + " failed", th);
            com.netease.nimlib.n.e.a(str, com.netease.nimlib.n.b.k.kLoad, th.toString(), "load library " + str + " failed");
            throw th;
        }
    }

    public static void a(Context context) {
        Process.killProcess(Process.myPid());
        System.exit(0);
    }

    public static String a(String str, String str2) {
        String c = c(str, str2);
        return C$r8$backportedMethods$utility$Objects$2$equals.equals(c, str2) ? b(str, str2) : c;
    }

    public static String b(String str, String str2) {
        String str3;
        Throwable th;
        try {
            str3 = new BufferedReader(new InputStreamReader(new ProcessBuilder("/system/bin/getprop", str).start().getInputStream())).readLine();
        } catch (Throwable th2) {
            str3 = str2;
            th = th2;
        }
        try {
            com.netease.nimlib.log.b.d("SystemUtil", "getPropertyByProcess, key = " + str + ",value = " + str3);
            if (str3 == null || TextUtils.isEmpty(str3.trim())) {
                com.netease.nimlib.log.b.f("SystemUtil", "get property empty , key = " + str);
                return str2;
            }
        } catch (Throwable th3) {
            th = th3;
            com.netease.nimlib.log.b.e("SystemUtil", "get property err , key = " + str, th);
            return str3;
        }
        return str3;
    }

    public static String c(String str, String str2) {
        String str3;
        Throwable th;
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            str3 = (String) cls.getMethod("get", String.class, String.class).invoke(cls, str, "");
        } catch (Throwable th2) {
            str3 = str2;
            th = th2;
        }
        try {
            com.netease.nimlib.log.b.d("SystemUtil", "getPropertyBySystem, key = " + str + ",value = " + str3);
        } catch (Throwable th3) {
            th = th3;
            com.netease.nimlib.log.b.e("SystemUtil", "get property by SystemProperties err , key = " + str, th);
            return str3;
        }
        return str3;
    }
}
