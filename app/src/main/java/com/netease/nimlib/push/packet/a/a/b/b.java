package com.netease.nimlib.push.packet.a.a.b;

import com.netease.nimlib.push.packet.a.a.c.j;
import com.netease.nimlib.push.packet.a.a.d;
import com.netease.nimlib.push.packet.a.a.h;
import com.netease.nimlib.push.packet.a.c.e;
import u.aly.df;

/* compiled from: SM4Engine.java */
/* loaded from: classes.dex */
public class b {
    private static final byte[] a = {-42, -112, -23, -2, -52, -31, 61, -73, 22, -74, 20, -62, 40, -5, 44, 5, 43, 103, -102, 118, 42, -66, 4, -61, -86, 68, 19, 38, 73, -122, 6, -103, -100, 66, 80, -12, -111, -17, -104, 122, 51, 84, 11, 67, -19, -49, -84, 98, -28, -77, 28, -87, -55, 8, -24, -107, Byte.MIN_VALUE, -33, -108, -6, 117, -113, 63, -90, 71, 7, -89, -4, -13, 115, 23, -70, -125, 89, 60, 25, -26, -123, 79, -88, 104, 107, -127, -78, 113, 100, -38, -117, -8, -21, df.m, 75, 112, 86, -99, 53, 30, 36, df.l, 94, 99, 88, -47, -94, 37, 34, 124, 59, 1, 33, 120, -121, -44, 0, 70, 87, -97, -45, 39, 82, 76, 54, 2, -25, -96, -60, -56, -98, -22, -65, -118, -46, 64, -57, 56, -75, -93, -9, -14, -50, -7, 97, 21, -95, -32, -82, 93, -92, -101, 52, 26, 85, -83, -109, 50, 48, -11, -116, -79, -29, 29, -10, -30, 46, -126, 102, -54, 96, -64, 41, 35, -85, df.k, 83, 78, 111, -43, -37, 55, 69, -34, -3, -114, 47, 3, -1, 106, 114, 109, 108, 91, 81, -115, 27, -81, -110, -69, -35, -68, Byte.MAX_VALUE, 17, -39, 92, 65, 31, df.n, 90, -40, 10, -63, 49, -120, -91, -51, 123, -67, 45, 116, -48, 18, -72, -27, -76, -80, -119, 105, -105, 74, 12, -106, 119, 126, 101, -71, -15, 9, -59, 110, -58, -124, 24, -16, 125, -20, 58, -36, 77, 32, 121, -18, 95, 62, -41, -53, 57, 72};
    private static final int[] b = {462357, 472066609, 943670861, 1415275113, 1886879365, -1936483679, -1464879427, -993275175, -521670923, -66909679, 404694573, 876298825, 1347903077, 1819507329, -2003855715, -1532251463, -1060647211, -589042959, -117504499, 337322537, 808926789, 1280531041, 1752135293, -2071227751, -1599623499, -1128019247, -656414995, -184876535, 269950501, 741554753, 1213159005, 1684763257};
    private static final int[] c = {-1548633402, 1453994832, 1736282519, -1301273892};
    private final int[] d = new int[4];
    private int[] e;

    private int a(int i, int i2) {
        return (i >>> (-i2)) | (i << i2);
    }

    private int a(int i) {
        byte[] bArr = a;
        return (bArr[i & 255] & 255) | ((bArr[(i >> 24) & 255] & 255) << 24) | ((bArr[(i >> 16) & 255] & 255) << 16) | ((bArr[(i >> 8) & 255] & 255) << 8);
    }

    private int b(int i) {
        return a(i, 23) ^ (a(i, 13) ^ i);
    }

    private int c(int i) {
        return b(a(i));
    }

    private int[] a(boolean z, byte[] bArr) {
        int[] iArr = new int[32];
        int[] iArr2 = {e.a(bArr, 0), e.a(bArr, 4), e.a(bArr, 8), e.a(bArr, 12)};
        int i = iArr2[0];
        int[] iArr3 = c;
        int[] iArr4 = {i ^ iArr3[0], iArr2[1] ^ iArr3[1], iArr2[2] ^ iArr3[2], iArr2[3] ^ iArr3[3]};
        if (z) {
            iArr[0] = iArr4[0] ^ c(((iArr4[1] ^ iArr4[2]) ^ iArr4[3]) ^ b[0]);
            iArr[1] = iArr4[1] ^ c(((iArr4[2] ^ iArr4[3]) ^ iArr[0]) ^ b[1]);
            iArr[2] = iArr4[2] ^ c(((iArr4[3] ^ iArr[0]) ^ iArr[1]) ^ b[2]);
            iArr[3] = iArr4[3] ^ c(((iArr[0] ^ iArr[1]) ^ iArr[2]) ^ b[3]);
            for (int i2 = 4; i2 < 32; i2++) {
                iArr[i2] = iArr[i2 - 4] ^ c(((iArr[i2 - 3] ^ iArr[i2 - 2]) ^ iArr[i2 - 1]) ^ b[i2]);
            }
        } else {
            iArr[31] = iArr4[0] ^ c(((iArr4[1] ^ iArr4[2]) ^ iArr4[3]) ^ b[0]);
            iArr[30] = iArr4[1] ^ c(((iArr4[2] ^ iArr4[3]) ^ iArr[31]) ^ b[1]);
            iArr[29] = iArr4[2] ^ c(((iArr4[3] ^ iArr[31]) ^ iArr[30]) ^ b[2]);
            iArr[28] = iArr4[3] ^ c(((iArr[30] ^ iArr[31]) ^ iArr[29]) ^ b[3]);
            for (int i3 = 27; i3 >= 0; i3--) {
                iArr[i3] = iArr[i3 + 4] ^ c(((iArr[i3 + 3] ^ iArr[i3 + 2]) ^ iArr[i3 + 1]) ^ b[31 - i3]);
            }
        }
        return iArr;
    }

    private int d(int i) {
        return a(i, 24) ^ (((a(i, 2) ^ i) ^ a(i, 10)) ^ a(i, 18));
    }

    private int e(int i) {
        return d(a(i));
    }

    private int a(int[] iArr, int i) {
        return e((iArr[3] ^ (iArr[1] ^ iArr[2])) ^ i) ^ iArr[0];
    }

    private int b(int[] iArr, int i) {
        return e((iArr[0] ^ (iArr[2] ^ iArr[3])) ^ i) ^ iArr[1];
    }

    private int c(int[] iArr, int i) {
        return e((iArr[1] ^ (iArr[3] ^ iArr[0])) ^ i) ^ iArr[2];
    }

    private int d(int[] iArr, int i) {
        return e((iArr[2] ^ (iArr[0] ^ iArr[1])) ^ i) ^ iArr[3];
    }

    public void a(boolean z, com.netease.nimlib.push.packet.a.a.a aVar) throws IllegalArgumentException {
        if (aVar instanceof j) {
            byte[] a2 = ((j) aVar).a();
            if (a2.length != 16) {
                throw new IllegalArgumentException("SM4 requires a 128 bit key");
            }
            this.e = a(z, a2);
            return;
        }
        throw new IllegalArgumentException("invalid parameter passed to SM4 init - " + aVar.getClass().getName());
    }

    public int a(byte[] bArr, int i, byte[] bArr2, int i2) throws d, IllegalStateException {
        if (this.e == null) {
            throw new IllegalStateException("SM4 not initialised");
        }
        if (i + 16 > bArr.length) {
            throw new d("input buffer too short");
        }
        if (i2 + 16 > bArr2.length) {
            throw new h("output buffer too short");
        }
        this.d[0] = e.a(bArr, i);
        this.d[1] = e.a(bArr, i + 4);
        this.d[2] = e.a(bArr, i + 8);
        this.d[3] = e.a(bArr, i + 12);
        for (int i3 = 0; i3 < 32; i3 += 4) {
            int[] iArr = this.d;
            iArr[0] = a(iArr, this.e[i3]);
            int[] iArr2 = this.d;
            iArr2[1] = b(iArr2, this.e[i3 + 1]);
            int[] iArr3 = this.d;
            iArr3[2] = c(iArr3, this.e[i3 + 2]);
            int[] iArr4 = this.d;
            iArr4[3] = d(iArr4, this.e[i3 + 3]);
        }
        e.a(this.d[3], bArr2, i2);
        e.a(this.d[2], bArr2, i2 + 4);
        e.a(this.d[1], bArr2, i2 + 8);
        e.a(this.d[0], bArr2, i2 + 12);
        return 16;
    }
}
