package com.netease.nimlib.team;

/* compiled from: TeamConfig.java */
/* loaded from: classes.dex */
public class b {
    private static long a(long j, boolean z, long j2) {
        return z ? j | j2 : j & (j2 ^ (-1));
    }

    private static boolean a(long j, long j2) {
        return (j & j2) != 0;
    }

    public static boolean a(long j) {
        return a(j, 1L);
    }

    public static long a(long j, boolean z) {
        return a(j, z, 1L);
    }

    public static boolean b(long j) {
        return a(j, 2L);
    }

    public static long b(long j, boolean z) {
        return a(j, z, 2L);
    }
}
