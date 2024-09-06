package com.netease.nimlib.push.packet.a.b.a.a.a;

import java.math.BigInteger;

/* compiled from: SM2P256V1Field.java */
/* loaded from: classes.dex */
public class b {
    static final int[] a = {-1, -1, 0, -1, -1, -1, -1, -2};
    static final int[] b = {1, 0, -2, 1, 1, -2, 0, 2, -2, -3, 3, -2, -1, -1, 0, -2};

    public static void a(int[] iArr, int[] iArr2, int[] iArr3) {
        if (com.netease.nimlib.push.packet.a.b.c.c.a(iArr, iArr2, iArr3) != 0 || ((iArr3[7] >>> 1) >= Integer.MAX_VALUE && com.netease.nimlib.push.packet.a.b.c.c.b(iArr3, a))) {
            a(iArr3);
        }
    }

    public static int[] a(BigInteger bigInteger) {
        int[] a2 = com.netease.nimlib.push.packet.a.b.c.c.a(bigInteger);
        if ((a2[7] >>> 1) >= Integer.MAX_VALUE && com.netease.nimlib.push.packet.a.b.c.c.b(a2, a)) {
            com.netease.nimlib.push.packet.a.b.c.c.d(a, a2);
        }
        return a2;
    }

    public static void b(int[] iArr, int[] iArr2, int[] iArr3) {
        int[] b2 = com.netease.nimlib.push.packet.a.b.c.c.b();
        com.netease.nimlib.push.packet.a.b.c.c.c(iArr, iArr2, b2);
        b(b2, iArr3);
    }

    public static void c(int[] iArr, int[] iArr2, int[] iArr3) {
        if (com.netease.nimlib.push.packet.a.b.c.c.d(iArr, iArr2, iArr3) != 0 || ((iArr3[15] >>> 1) >= Integer.MAX_VALUE && com.netease.nimlib.push.packet.a.b.c.b.b(16, iArr3, b))) {
            com.netease.nimlib.push.packet.a.b.c.b.c(16, b, iArr3);
        }
    }

    public static void a(int[] iArr, int[] iArr2) {
        if (com.netease.nimlib.push.packet.a.b.c.c.b(iArr)) {
            com.netease.nimlib.push.packet.a.b.c.c.d(iArr2);
        } else {
            com.netease.nimlib.push.packet.a.b.c.c.e(a, iArr, iArr2);
        }
    }

    public static void b(int[] iArr, int[] iArr2) {
        long j = iArr[8] & 4294967295L;
        long j2 = iArr[9] & 4294967295L;
        long j3 = iArr[10] & 4294967295L;
        long j4 = iArr[11] & 4294967295L;
        long j5 = iArr[12] & 4294967295L;
        long j6 = iArr[13] & 4294967295L;
        long j7 = iArr[14] & 4294967295L;
        long j8 = iArr[15] & 4294967295L;
        long j9 = j3 + j4;
        long j10 = j6 + j7;
        long j11 = j10 + (j8 << 1);
        long j12 = j + j2 + j10;
        long j13 = j9 + j5 + j8 + j12;
        long j14 = (iArr[0] & 4294967295L) + j13 + j6 + j7 + j8 + 0;
        iArr2[0] = (int) j14;
        long j15 = (j14 >> 32) + (((iArr[1] & 4294967295L) + j13) - j) + j7 + j8;
        iArr2[1] = (int) j15;
        long j16 = (j15 >> 32) + ((iArr[2] & 4294967295L) - j12);
        iArr2[2] = (int) j16;
        long j17 = (j16 >> 32) + ((((iArr[3] & 4294967295L) + j13) - j2) - j3) + j6;
        iArr2[3] = (int) j17;
        long j18 = (j17 >> 32) + ((((iArr[4] & 4294967295L) + j13) - j9) - j) + j7;
        iArr2[4] = (int) j18;
        long j19 = (j18 >> 32) + (iArr[5] & 4294967295L) + j11 + j3;
        iArr2[5] = (int) j19;
        long j20 = (j19 >> 32) + (iArr[6] & 4294967295L) + j4 + j7 + j8;
        iArr2[6] = (int) j20;
        long j21 = (j20 >> 32) + (4294967295L & iArr[7]) + j13 + j11 + j5;
        iArr2[7] = (int) j21;
        a((int) (j21 >> 32), iArr2);
    }

    public static void a(int i, int[] iArr) {
        long j;
        if (i != 0) {
            long j2 = i & 4294967295L;
            long j3 = (iArr[0] & 4294967295L) + j2 + 0;
            iArr[0] = (int) j3;
            long j4 = j3 >> 32;
            if (j4 != 0) {
                long j5 = j4 + (iArr[1] & 4294967295L);
                iArr[1] = (int) j5;
                j4 = j5 >> 32;
            }
            long j6 = j4 + ((iArr[2] & 4294967295L) - j2);
            iArr[2] = (int) j6;
            long j7 = (j6 >> 32) + (iArr[3] & 4294967295L) + j2;
            iArr[3] = (int) j7;
            long j8 = j7 >> 32;
            if (j8 != 0) {
                long j9 = j8 + (iArr[4] & 4294967295L);
                iArr[4] = (int) j9;
                long j10 = (j9 >> 32) + (iArr[5] & 4294967295L);
                iArr[5] = (int) j10;
                long j11 = (j10 >> 32) + (iArr[6] & 4294967295L);
                iArr[6] = (int) j11;
                j8 = j11 >> 32;
            }
            long j12 = j8 + (4294967295L & iArr[7]) + j2;
            iArr[7] = (int) j12;
            j = j12 >> 32;
        } else {
            j = 0;
        }
        if (j != 0 || ((iArr[7] >>> 1) >= Integer.MAX_VALUE && com.netease.nimlib.push.packet.a.b.c.c.b(iArr, a))) {
            a(iArr);
        }
    }

    public static void c(int[] iArr, int[] iArr2) {
        int[] b2 = com.netease.nimlib.push.packet.a.b.c.c.b();
        com.netease.nimlib.push.packet.a.b.c.c.c(iArr, b2);
        b(b2, iArr2);
    }

    public static void a(int[] iArr, int i, int[] iArr2) {
        int[] b2 = com.netease.nimlib.push.packet.a.b.c.c.b();
        com.netease.nimlib.push.packet.a.b.c.c.c(iArr, b2);
        b(b2, iArr2);
        while (true) {
            i--;
            if (i <= 0) {
                return;
            }
            com.netease.nimlib.push.packet.a.b.c.c.c(iArr2, b2);
            b(b2, iArr2);
        }
    }

    public static void d(int[] iArr, int[] iArr2, int[] iArr3) {
        if (com.netease.nimlib.push.packet.a.b.c.c.e(iArr, iArr2, iArr3) != 0) {
            b(iArr3);
        }
    }

    public static void d(int[] iArr, int[] iArr2) {
        if (com.netease.nimlib.push.packet.a.b.c.b.a(8, iArr, 0, iArr2) != 0 || ((iArr2[7] >>> 1) >= Integer.MAX_VALUE && com.netease.nimlib.push.packet.a.b.c.c.b(iArr2, a))) {
            a(iArr2);
        }
    }

    private static void a(int[] iArr) {
        long j = (iArr[0] & 4294967295L) + 1;
        iArr[0] = (int) j;
        long j2 = j >> 32;
        if (j2 != 0) {
            long j3 = j2 + (iArr[1] & 4294967295L);
            iArr[1] = (int) j3;
            j2 = j3 >> 32;
        }
        long j4 = j2 + ((iArr[2] & 4294967295L) - 1);
        iArr[2] = (int) j4;
        long j5 = (j4 >> 32) + (iArr[3] & 4294967295L) + 1;
        iArr[3] = (int) j5;
        long j6 = j5 >> 32;
        if (j6 != 0) {
            long j7 = j6 + (iArr[4] & 4294967295L);
            iArr[4] = (int) j7;
            long j8 = (j7 >> 32) + (iArr[5] & 4294967295L);
            iArr[5] = (int) j8;
            long j9 = (j8 >> 32) + (iArr[6] & 4294967295L);
            iArr[6] = (int) j9;
            j6 = j9 >> 32;
        }
        iArr[7] = (int) (j6 + (4294967295L & iArr[7]) + 1);
    }

    private static void b(int[] iArr) {
        long j = (iArr[0] & 4294967295L) - 1;
        iArr[0] = (int) j;
        long j2 = j >> 32;
        if (j2 != 0) {
            long j3 = j2 + (iArr[1] & 4294967295L);
            iArr[1] = (int) j3;
            j2 = j3 >> 32;
        }
        long j4 = j2 + (iArr[2] & 4294967295L) + 1;
        iArr[2] = (int) j4;
        long j5 = (j4 >> 32) + ((iArr[3] & 4294967295L) - 1);
        iArr[3] = (int) j5;
        long j6 = j5 >> 32;
        if (j6 != 0) {
            long j7 = j6 + (iArr[4] & 4294967295L);
            iArr[4] = (int) j7;
            long j8 = (j7 >> 32) + (iArr[5] & 4294967295L);
            iArr[5] = (int) j8;
            long j9 = (j8 >> 32) + (iArr[6] & 4294967295L);
            iArr[6] = (int) j9;
            j6 = j9 >> 32;
        }
        iArr[7] = (int) (j6 + ((4294967295L & iArr[7]) - 1));
    }
}
