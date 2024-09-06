package com.unionpay.mobile.android.pboctransaction.nfc;

import u.aly.df;

/* loaded from: classes.dex */
public final class c {
    private static final char[] a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static String a(byte[] bArr, int i) {
        char[] cArr = new char[i * 2];
        int i2 = i + 0;
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            byte b = bArr[i4];
            int i5 = i3 + 1;
            char[] cArr2 = a;
            cArr[i3] = cArr2[(b >> 4) & 15];
            i3 = i5 + 1;
            cArr[i5] = cArr2[b & df.m];
        }
        return new String(cArr);
    }
}
