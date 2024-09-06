package com.netease.nimlib.o;

import android.text.TextUtils;

/* compiled from: StackTraceUtils.java */
/* loaded from: classes.dex */
public class u {
    public static boolean a(StackTraceElement[] stackTraceElementArr) {
        if (stackTraceElementArr.length != 14 || !TextUtils.equals(a(stackTraceElementArr, 0), "sun.nio.ch.Net.checkAddress") || !TextUtils.equals(a(stackTraceElementArr, 1), "sun.nio.ch.SocketChannelImpl.connect") || !TextUtils.equals(a(stackTraceElementArr, 13), "java.lang.Thread.run")) {
            return false;
        }
        for (int i = 0; i < stackTraceElementArr.length; i++) {
            if (i != 0 && i != 1 && i != 13 && !a(stackTraceElementArr, i).startsWith("com.netease")) {
                return false;
            }
        }
        return true;
    }

    private static String a(StackTraceElement[] stackTraceElementArr, int i) {
        return (i < 0 || i > stackTraceElementArr.length + (-1)) ? "" : stackTraceElementArr[i].toString().replace("\n", "").replace("\t", "").replace("\r", "").replaceAll("\\(.*?\\)", "");
    }

    public static String b(StackTraceElement[] stackTraceElementArr) {
        return stackTraceElementArr.length > 2 ? String.format("%s %s %s", stackTraceElementArr[0], stackTraceElementArr[1], stackTraceElementArr[2]) : stackTraceElementArr.length > 1 ? String.format("%s %s", stackTraceElementArr[0], stackTraceElementArr[1]) : stackTraceElementArr.length > 0 ? String.format("%s", stackTraceElementArr[0]) : "empty";
    }
}
