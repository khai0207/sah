package com.netease.nimlib.push.packet.a.c;

/* compiled from: Pack.java */
/* loaded from: classes.dex */
public abstract class e {
    public static int a(byte[] bArr, int i) {
        int i2 = bArr[i] << 24;
        int i3 = i + 1;
        int i4 = i2 | ((bArr[i3] & 255) << 16);
        int i5 = i3 + 1;
        return (bArr[i5 + 1] & 255) | i4 | ((bArr[i5] & 255) << 8);
    }

    public static void a(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) (i >>> 24);
        int i3 = i2 + 1;
        bArr[i3] = (byte) (i >>> 16);
        int i4 = i3 + 1;
        bArr[i4] = (byte) (i >>> 8);
        bArr[i4 + 1] = (byte) i;
    }

    public static void a(int[] iArr, byte[] bArr, int i) {
        for (int i2 : iArr) {
            a(i2, bArr, i);
            i += 4;
        }
    }
}
