package com.netease.nimlib.o;

/* compiled from: BytesUtil.java */
/* loaded from: classes.dex */
public class d {
    public static boolean[] a(byte[] bArr) {
        boolean[] zArr = new boolean[bArr.length * 8];
        int i = 0;
        for (byte b : bArr) {
            int i2 = 0;
            while (i2 <= 7) {
                int i3 = i + 1;
                boolean z = true;
                if (((b & (1 << i2)) >> i2) != 1) {
                    z = false;
                }
                zArr[i] = z;
                i2++;
                i = i3;
            }
        }
        return zArr;
    }
}
