package com.netease.nimlib.push.packet.a.b.c;

/* compiled from: Mod.java */
/* loaded from: classes.dex */
public abstract class a {
    private static int a(int i) {
        int i2 = 0;
        while ((i & 1) == 0) {
            i >>>= 1;
            i2++;
        }
        return i2;
    }

    public static void a(int[] iArr, int[] iArr2, int[] iArr3) {
        int length = iArr.length;
        if (b.c(length, iArr2)) {
            throw new IllegalArgumentException("'x' cannot be 0");
        }
        int i = 0;
        if (b.b(length, iArr2)) {
            System.arraycopy(iArr2, 0, iArr3, 0, length);
            return;
        }
        int[] a = b.a(length, iArr2);
        int[] a2 = b.a(length);
        a2[0] = 1;
        int a3 = (1 & a[0]) == 0 ? a(iArr, a, length, a2, 0) : 0;
        if (b.b(length, a)) {
            a(iArr, a3, a2, iArr3);
            return;
        }
        int[] a4 = b.a(length, iArr);
        int[] a5 = b.a(length);
        int i2 = length;
        while (true) {
            int i3 = i2 - 1;
            if (a[i3] == 0 && a4[i3] == 0) {
                i2--;
            } else if (b.b(i2, a, a4)) {
                b.c(i2, a4, a);
                a3 = a(iArr, a, i2, a2, a3 + (b.c(length, a5, a2) - i));
                if (b.b(i2, a)) {
                    a(iArr, a3, a2, iArr3);
                    return;
                }
            } else {
                b.c(i2, a, a4);
                i = a(iArr, a4, i2, a5, i + (b.c(length, a2, a5) - a3));
                if (b.b(i2, a4)) {
                    a(iArr, i, a5, iArr3);
                    return;
                }
            }
        }
    }

    private static void a(int[] iArr, int i, int[] iArr2, int[] iArr3) {
        if (i < 0) {
            b.a(iArr.length, iArr2, iArr, iArr3);
        } else {
            System.arraycopy(iArr2, 0, iArr3, 0, iArr.length);
        }
    }

    private static int a(int[] iArr, int[] iArr2, int i, int[] iArr3, int i2) {
        int c;
        int length = iArr.length;
        int i3 = 0;
        while (iArr2[0] == 0) {
            b.b(i, iArr2, 0);
            i3 += 32;
        }
        int a = a(iArr2[0]);
        if (a > 0) {
            b.a(i, iArr2, a, 0);
            i3 += a;
        }
        for (int i4 = 0; i4 < i3; i4++) {
            if ((iArr3[0] & 1) != 0) {
                if (i2 < 0) {
                    c = b.a(length, iArr, iArr3);
                } else {
                    c = b.c(length, iArr, iArr3);
                }
                i2 += c;
            }
            b.a(length, iArr3, i2);
        }
        return i2;
    }
}
