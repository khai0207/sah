package com.netease.nimlib.push.packet.a.b.c;

import com.netease.nimlib.push.packet.a.c.e;
import java.math.BigInteger;

/* compiled from: Nat256.java */
/* loaded from: classes.dex */
public abstract class c {
    public static int[] a() {
        return new int[8];
    }

    public static int[] b() {
        return new int[16];
    }

    public static int a(int[] iArr, int[] iArr2, int[] iArr3) {
        long j = (iArr[0] & 4294967295L) + (iArr2[0] & 4294967295L) + 0;
        iArr3[0] = (int) j;
        long j2 = (j >>> 32) + (iArr[1] & 4294967295L) + (iArr2[1] & 4294967295L);
        iArr3[1] = (int) j2;
        long j3 = (j2 >>> 32) + (iArr[2] & 4294967295L) + (iArr2[2] & 4294967295L);
        iArr3[2] = (int) j3;
        long j4 = (j3 >>> 32) + (iArr[3] & 4294967295L) + (iArr2[3] & 4294967295L);
        iArr3[3] = (int) j4;
        long j5 = (j4 >>> 32) + (iArr[4] & 4294967295L) + (iArr2[4] & 4294967295L);
        iArr3[4] = (int) j5;
        long j6 = (j5 >>> 32) + (iArr[5] & 4294967295L) + (iArr2[5] & 4294967295L);
        iArr3[5] = (int) j6;
        long j7 = (j6 >>> 32) + (iArr[6] & 4294967295L) + (iArr2[6] & 4294967295L);
        iArr3[6] = (int) j7;
        long j8 = (j7 >>> 32) + (iArr[7] & 4294967295L) + (iArr2[7] & 4294967295L);
        iArr3[7] = (int) j8;
        return (int) (j8 >>> 32);
    }

    public static int b(int[] iArr, int[] iArr2, int[] iArr3) {
        long j = (iArr[0] & 4294967295L) + (iArr2[0] & 4294967295L) + (iArr3[0] & 4294967295L) + 0;
        iArr3[0] = (int) j;
        long j2 = (j >>> 32) + (iArr[1] & 4294967295L) + (iArr2[1] & 4294967295L) + (iArr3[1] & 4294967295L);
        iArr3[1] = (int) j2;
        long j3 = (j2 >>> 32) + (iArr[2] & 4294967295L) + (iArr2[2] & 4294967295L) + (iArr3[2] & 4294967295L);
        iArr3[2] = (int) j3;
        long j4 = (j3 >>> 32) + (iArr[3] & 4294967295L) + (iArr2[3] & 4294967295L) + (iArr3[3] & 4294967295L);
        iArr3[3] = (int) j4;
        long j5 = (j4 >>> 32) + (iArr[4] & 4294967295L) + (iArr2[4] & 4294967295L) + (iArr3[4] & 4294967295L);
        iArr3[4] = (int) j5;
        long j6 = (j5 >>> 32) + (iArr[5] & 4294967295L) + (iArr2[5] & 4294967295L) + (iArr3[5] & 4294967295L);
        iArr3[5] = (int) j6;
        long j7 = (j6 >>> 32) + (iArr[6] & 4294967295L) + (iArr2[6] & 4294967295L) + (iArr3[6] & 4294967295L);
        iArr3[6] = (int) j7;
        long j8 = (j7 >>> 32) + (iArr[7] & 4294967295L) + (iArr2[7] & 4294967295L) + (iArr3[7] & 4294967295L);
        iArr3[7] = (int) j8;
        return (int) (j8 >>> 32);
    }

    public static void a(int[] iArr, int i, int[] iArr2, int i2) {
        iArr2[i2 + 0] = iArr[i + 0];
        iArr2[i2 + 1] = iArr[i + 1];
        iArr2[i2 + 2] = iArr[i + 2];
        iArr2[i2 + 3] = iArr[i + 3];
        iArr2[i2 + 4] = iArr[i + 4];
        iArr2[i2 + 5] = iArr[i + 5];
        iArr2[i2 + 6] = iArr[i + 6];
        iArr2[i2 + 7] = iArr[i + 7];
    }

    public static boolean a(int[] iArr, int[] iArr2) {
        for (int i = 7; i >= 0; i--) {
            if (iArr[i] != iArr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static int[] a(BigInteger bigInteger) {
        if (bigInteger.signum() < 0 || bigInteger.bitLength() > 256) {
            throw new IllegalArgumentException();
        }
        int[] a = a();
        int i = 0;
        while (bigInteger.signum() != 0) {
            a[i] = bigInteger.intValue();
            bigInteger = bigInteger.shiftRight(32);
            i++;
        }
        return a;
    }

    public static int a(int[] iArr, int i) {
        int i2;
        if (i == 0) {
            i2 = iArr[0];
        } else {
            if ((i & 255) != i) {
                return 0;
            }
            i2 = iArr[i >>> 5] >>> (i & 31);
        }
        return i2 & 1;
    }

    public static boolean b(int[] iArr, int[] iArr2) {
        for (int i = 7; i >= 0; i--) {
            int i2 = iArr[i] ^ Integer.MIN_VALUE;
            int i3 = Integer.MIN_VALUE ^ iArr2[i];
            if (i2 < i3) {
                return false;
            }
            if (i2 > i3) {
                return true;
            }
        }
        return true;
    }

    public static boolean a(int[] iArr) {
        if (iArr[0] != 1) {
            return false;
        }
        for (int i = 1; i < 8; i++) {
            if (iArr[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean b(int[] iArr) {
        for (int i = 0; i < 8; i++) {
            if (iArr[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public static void c(int[] iArr, int[] iArr2, int[] iArr3) {
        long j = iArr2[0] & 4294967295L;
        long j2 = iArr2[1] & 4294967295L;
        long j3 = iArr2[2] & 4294967295L;
        long j4 = iArr2[3] & 4294967295L;
        long j5 = iArr2[4] & 4294967295L;
        long j6 = iArr2[5] & 4294967295L;
        long j7 = iArr2[6] & 4294967295L;
        long j8 = iArr2[7] & 4294967295L;
        long j9 = iArr[0] & 4294967295L;
        long j10 = (j9 * j) + 0;
        iArr3[0] = (int) j10;
        long j11 = (j10 >>> 32) + (j9 * j2);
        iArr3[1] = (int) j11;
        long j12 = (j11 >>> 32) + (j9 * j3);
        iArr3[2] = (int) j12;
        long j13 = (j12 >>> 32) + (j9 * j4);
        iArr3[3] = (int) j13;
        long j14 = (j13 >>> 32) + (j9 * j5);
        iArr3[4] = (int) j14;
        long j15 = (j14 >>> 32) + (j9 * j6);
        iArr3[5] = (int) j15;
        long j16 = (j15 >>> 32) + (j9 * j7);
        iArr3[6] = (int) j16;
        long j17 = (j16 >>> 32) + (j9 * j8);
        iArr3[7] = (int) j17;
        int i = (int) (j17 >>> 32);
        iArr3[8] = i;
        int i2 = 1;
        for (int i3 = 8; i2 < i3; i3 = 8) {
            long j18 = iArr[i2] & 4294967295L;
            long j19 = (j18 * j) + (iArr3[r4] & 4294967295L) + 0;
            iArr3[i2 + 0] = (int) j19;
            int i4 = i2 + 1;
            long j20 = j2;
            long j21 = (j19 >>> 32) + (j18 * j2) + (iArr3[i4] & 4294967295L);
            iArr3[i4] = (int) j21;
            long j22 = j6;
            long j23 = (j21 >>> 32) + (j18 * j3) + (iArr3[r6] & 4294967295L);
            iArr3[i2 + 2] = (int) j23;
            long j24 = (j23 >>> 32) + (j18 * j4) + (iArr3[r6] & 4294967295L);
            iArr3[i2 + 3] = (int) j24;
            long j25 = (j24 >>> 32) + (j18 * j5) + (iArr3[r6] & 4294967295L);
            iArr3[i2 + 4] = (int) j25;
            long j26 = (j25 >>> 32) + (j18 * j22) + (iArr3[r3] & 4294967295L);
            iArr3[i2 + 5] = (int) j26;
            long j27 = (j26 >>> 32) + (j18 * j7) + (iArr3[r6] & 4294967295L);
            iArr3[i2 + 6] = (int) j27;
            long j28 = (j27 >>> 32) + (j18 * j8) + (iArr3[r3] & 4294967295L);
            iArr3[i2 + 7] = (int) j28;
            iArr3[i2 + 8] = (int) (j28 >>> 32);
            i2 = i4;
            j = j;
            j2 = j20;
            j6 = j22;
        }
    }

    public static int d(int[] iArr, int[] iArr2, int[] iArr3) {
        long j = 4294967295L;
        long j2 = iArr2[0] & 4294967295L;
        long j3 = iArr2[1] & 4294967295L;
        long j4 = iArr2[2] & 4294967295L;
        long j5 = iArr2[3] & 4294967295L;
        long j6 = iArr2[4] & 4294967295L;
        long j7 = iArr2[5] & 4294967295L;
        long j8 = iArr2[6] & 4294967295L;
        long j9 = iArr2[7] & 4294967295L;
        long j10 = 0;
        int i = 0;
        while (i < 8) {
            long j11 = j9;
            long j12 = iArr[i] & j;
            long j13 = j7;
            long j14 = (j12 * j2) + (iArr3[r27] & j) + 0;
            iArr3[i + 0] = (int) j14;
            int i2 = i + 1;
            long j15 = j3;
            long j16 = (j14 >>> 32) + (j12 * j3) + (iArr3[i2] & j);
            iArr3[i2] = (int) j16;
            long j17 = (j16 >>> 32) + (j12 * j4) + (iArr3[r6] & j);
            iArr3[i + 2] = (int) j17;
            long j18 = (j17 >>> 32) + (j12 * j5) + (iArr3[r6] & j);
            iArr3[i + 3] = (int) j18;
            long j19 = (j18 >>> 32) + (j12 * j6) + (iArr3[r6] & j);
            iArr3[i + 4] = (int) j19;
            long j20 = (j19 >>> 32) + (j12 * j13) + (iArr3[r6] & j);
            iArr3[i + 5] = (int) j20;
            long j21 = (j20 >>> 32) + (j12 * j8) + (iArr3[r6] & j);
            iArr3[i + 6] = (int) j21;
            long j22 = (j21 >>> 32) + (j12 * j11) + (iArr3[r6] & j);
            iArr3[i + 7] = (int) j22;
            long j23 = (j22 >>> 32) + j10 + (iArr3[r2] & j);
            iArr3[i + 8] = (int) j23;
            j10 = j23 >>> 32;
            i = i2;
            j9 = j11;
            j7 = j13;
            j3 = j15;
            j = 4294967295L;
        }
        return (int) j10;
    }

    public static void c(int[] iArr, int[] iArr2) {
        long j = iArr[0] & 4294967295L;
        int i = 16;
        int i2 = 7;
        int i3 = 0;
        while (true) {
            int i4 = i2 - 1;
            long j2 = iArr[i2] & 4294967295L;
            long j3 = j2 * j2;
            int i5 = i - 1;
            iArr2[i5] = (i3 << 31) | ((int) (j3 >>> 33));
            i = i5 - 1;
            iArr2[i] = (int) (j3 >>> 1);
            int i6 = (int) j3;
            if (i4 <= 0) {
                long j4 = j * j;
                iArr2[0] = (int) j4;
                long j5 = iArr[1] & 4294967295L;
                long j6 = (((i6 << 31) & 4294967295L) | (j4 >>> 33)) + (j5 * j);
                int i7 = (int) j6;
                iArr2[1] = (i7 << 1) | (((int) (j4 >>> 32)) & 1);
                int i8 = i7 >>> 31;
                long j7 = (iArr2[2] & 4294967295L) + (j6 >>> 32);
                long j8 = iArr[2] & 4294967295L;
                long j9 = j7 + (j8 * j);
                int i9 = (int) j9;
                iArr2[2] = (i9 << 1) | i8;
                long j10 = (iArr2[3] & 4294967295L) + (j9 >>> 32) + (j8 * j5);
                long j11 = (iArr2[4] & 4294967295L) + (j10 >>> 32);
                long j12 = iArr[3] & 4294967295L;
                long j13 = (iArr2[5] & 4294967295L) + (j11 >>> 32);
                long j14 = j11 & 4294967295L;
                long j15 = (iArr2[6] & 4294967295L) + (j13 >>> 32);
                long j16 = (j10 & 4294967295L) + (j12 * j);
                int i10 = (int) j16;
                iArr2[3] = (i10 << 1) | (i9 >>> 31);
                int i11 = i10 >>> 31;
                long j17 = j14 + (j16 >>> 32) + (j12 * j5);
                long j18 = (j13 & 4294967295L) + (j17 >>> 32) + (j12 * j8);
                long j19 = j15 + (j18 >>> 32);
                long j20 = iArr[4] & 4294967295L;
                long j21 = (iArr2[7] & 4294967295L) + (j19 >>> 32);
                long j22 = j19 & 4294967295L;
                long j23 = (iArr2[8] & 4294967295L) + (j21 >>> 32);
                long j24 = (j17 & 4294967295L) + (j20 * j);
                int i12 = (int) j24;
                iArr2[4] = (i12 << 1) | i11;
                long j25 = (j18 & 4294967295L) + (j24 >>> 32) + (j20 * j5);
                long j26 = j22 + (j25 >>> 32) + (j20 * j8);
                long j27 = (j21 & 4294967295L) + (j26 >>> 32) + (j20 * j12);
                long j28 = j23 + (j27 >>> 32);
                long j29 = j27 & 4294967295L;
                long j30 = iArr[5] & 4294967295L;
                long j31 = (iArr2[9] & 4294967295L) + (j28 >>> 32);
                long j32 = j28 & 4294967295L;
                long j33 = (iArr2[10] & 4294967295L) + (j31 >>> 32);
                long j34 = (j25 & 4294967295L) + (j30 * j);
                int i13 = (int) j34;
                iArr2[5] = (i13 << 1) | (i12 >>> 31);
                long j35 = (j26 & 4294967295L) + (j34 >>> 32) + (j30 * j5);
                long j36 = j29 + (j35 >>> 32) + (j30 * j8);
                long j37 = j32 + (j36 >>> 32) + (j30 * j12);
                long j38 = (j31 & 4294967295L) + (j37 >>> 32) + (j30 * j20);
                long j39 = j33 + (j38 >>> 32);
                long j40 = j38 & 4294967295L;
                long j41 = iArr[6] & 4294967295L;
                long j42 = (iArr2[11] & 4294967295L) + (j39 >>> 32);
                long j43 = j39 & 4294967295L;
                long j44 = (iArr2[12] & 4294967295L) + (j42 >>> 32);
                long j45 = (j35 & 4294967295L) + (j41 * j);
                int i14 = (int) j45;
                iArr2[6] = (i14 << 1) | (i13 >>> 31);
                long j46 = (j36 & 4294967295L) + (j45 >>> 32) + (j41 * j5);
                long j47 = (j37 & 4294967295L) + (j46 >>> 32) + (j41 * j8);
                long j48 = j46 & 4294967295L;
                long j49 = j40 + (j47 >>> 32) + (j41 * j12);
                long j50 = j43 + (j49 >>> 32) + (j41 * j20);
                long j51 = (j42 & 4294967295L) + (j50 >>> 32) + (j41 * j30);
                long j52 = j44 + (j51 >>> 32);
                long j53 = j51 & 4294967295L;
                long j54 = iArr[7] & 4294967295L;
                long j55 = (iArr2[13] & 4294967295L) + (j52 >>> 32);
                long j56 = j52 & 4294967295L;
                long j57 = (iArr2[14] & 4294967295L) + (j55 >>> 32);
                long j58 = 4294967295L & j55;
                long j59 = j48 + (j * j54);
                int i15 = (int) j59;
                iArr2[7] = (i14 >>> 31) | (i15 << 1);
                int i16 = i15 >>> 31;
                long j60 = (j47 & 4294967295L) + (j59 >>> 32) + (j54 * j5);
                long j61 = (j49 & 4294967295L) + (j60 >>> 32) + (j54 * j8);
                long j62 = (j50 & 4294967295L) + (j61 >>> 32) + (j54 * j12);
                long j63 = j53 + (j62 >>> 32) + (j54 * j20);
                long j64 = j56 + (j63 >>> 32) + (j54 * j30);
                long j65 = j58 + (j64 >>> 32) + (j54 * j41);
                long j66 = j57 + (j65 >>> 32);
                int i17 = (int) j60;
                iArr2[8] = i16 | (i17 << 1);
                int i18 = (int) j61;
                iArr2[9] = (i17 >>> 31) | (i18 << 1);
                int i19 = i18 >>> 31;
                int i20 = (int) j62;
                iArr2[10] = i19 | (i20 << 1);
                int i21 = (int) j63;
                iArr2[11] = (i20 >>> 31) | (i21 << 1);
                int i22 = (int) j64;
                iArr2[12] = (i21 >>> 31) | (i22 << 1);
                int i23 = i22 >>> 31;
                int i24 = (int) j65;
                iArr2[13] = i23 | (i24 << 1);
                int i25 = i24 >>> 31;
                int i26 = (int) j66;
                iArr2[14] = i25 | (i26 << 1);
                iArr2[15] = (i26 >>> 31) | ((iArr2[15] + ((int) (j66 >>> 32))) << 1);
                return;
            }
            i2 = i4;
            i3 = i6;
        }
    }

    public static int e(int[] iArr, int[] iArr2, int[] iArr3) {
        long j = ((iArr[0] & 4294967295L) - (iArr2[0] & 4294967295L)) + 0;
        iArr3[0] = (int) j;
        long j2 = (j >> 32) + ((iArr[1] & 4294967295L) - (iArr2[1] & 4294967295L));
        iArr3[1] = (int) j2;
        long j3 = (j2 >> 32) + ((iArr[2] & 4294967295L) - (iArr2[2] & 4294967295L));
        iArr3[2] = (int) j3;
        long j4 = (j3 >> 32) + ((iArr[3] & 4294967295L) - (iArr2[3] & 4294967295L));
        iArr3[3] = (int) j4;
        long j5 = (j4 >> 32) + ((iArr[4] & 4294967295L) - (iArr2[4] & 4294967295L));
        iArr3[4] = (int) j5;
        long j6 = (j5 >> 32) + ((iArr[5] & 4294967295L) - (iArr2[5] & 4294967295L));
        iArr3[5] = (int) j6;
        long j7 = (j6 >> 32) + ((iArr[6] & 4294967295L) - (iArr2[6] & 4294967295L));
        iArr3[6] = (int) j7;
        long j8 = (j7 >> 32) + ((iArr[7] & 4294967295L) - (iArr2[7] & 4294967295L));
        iArr3[7] = (int) j8;
        return (int) (j8 >> 32);
    }

    public static int d(int[] iArr, int[] iArr2) {
        long j = ((iArr2[0] & 4294967295L) - (iArr[0] & 4294967295L)) + 0;
        iArr2[0] = (int) j;
        long j2 = (j >> 32) + ((iArr2[1] & 4294967295L) - (iArr[1] & 4294967295L));
        iArr2[1] = (int) j2;
        long j3 = (j2 >> 32) + ((iArr2[2] & 4294967295L) - (iArr[2] & 4294967295L));
        iArr2[2] = (int) j3;
        long j4 = (j3 >> 32) + ((iArr2[3] & 4294967295L) - (iArr[3] & 4294967295L));
        iArr2[3] = (int) j4;
        long j5 = (j4 >> 32) + ((iArr2[4] & 4294967295L) - (iArr[4] & 4294967295L));
        iArr2[4] = (int) j5;
        long j6 = (j5 >> 32) + ((iArr2[5] & 4294967295L) - (iArr[5] & 4294967295L));
        iArr2[5] = (int) j6;
        long j7 = (j6 >> 32) + ((iArr2[6] & 4294967295L) - (iArr[6] & 4294967295L));
        iArr2[6] = (int) j7;
        long j8 = (j7 >> 32) + ((iArr2[7] & 4294967295L) - (4294967295L & iArr[7]));
        iArr2[7] = (int) j8;
        return (int) (j8 >> 32);
    }

    public static BigInteger c(int[] iArr) {
        byte[] bArr = new byte[32];
        for (int i = 0; i < 8; i++) {
            int i2 = iArr[i];
            if (i2 != 0) {
                e.a(i2, bArr, (7 - i) << 2);
            }
        }
        return new BigInteger(1, bArr);
    }

    public static void d(int[] iArr) {
        iArr[0] = 0;
        iArr[1] = 0;
        iArr[2] = 0;
        iArr[3] = 0;
        iArr[4] = 0;
        iArr[5] = 0;
        iArr[6] = 0;
        iArr[7] = 0;
    }
}
