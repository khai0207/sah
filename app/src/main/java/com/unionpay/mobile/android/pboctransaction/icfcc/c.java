package com.unionpay.mobile.android.pboctransaction.icfcc;

import com.unionpay.mobile.android.pboctransaction.e;

/* loaded from: classes.dex */
public final class c {
    public static String a(String str, String str2) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        if (str == null) {
            return null;
        }
        byte[] a = e.a(str);
        int i10 = 0;
        while (i10 < a.length) {
            int i11 = 1;
            int i12 = ((byte) (a[i10] & 31)) == 31 ? 2 : 1;
            byte[] bArr = new byte[i12];
            System.arraycopy(a, i10, bArr, 0, i12);
            if (e.a(bArr, i12).compareToIgnoreCase(str2) == 0) {
                int i13 = i10 + i12;
                if (((byte) (a[i13] & 128)) != Byte.MIN_VALUE) {
                    i4 = a[i13];
                } else {
                    i11 = 1 + (a[i13] & 127);
                    if (i11 != 2) {
                        if (i11 == 3) {
                            i2 = (a[i13 + 1] & 255) << 8;
                            i3 = a[i13 + 2];
                        } else {
                            if (i11 != 4) {
                                i = 0;
                                byte[] bArr2 = new byte[i];
                                System.arraycopy(a, i13 + i11, bArr2, 0, i);
                                return e.a(bArr2, i);
                            }
                            i2 = ((a[i13 + 1] & 255) << 16) | ((a[i13 + 2] & 255) << 8);
                            i3 = a[i13 + 3];
                        }
                        i = i2 | (i3 & 255);
                        byte[] bArr22 = new byte[i];
                        System.arraycopy(a, i13 + i11, bArr22, 0, i);
                        return e.a(bArr22, i);
                    }
                    i4 = a[i13 + 1];
                }
                i = i4 & 255;
                byte[] bArr222 = new byte[i];
                System.arraycopy(a, i13 + i11, bArr222, 0, i);
                return e.a(bArr222, i);
            }
            int i14 = a[i10] & 32;
            int i15 = i10 + i12;
            int length = a.length;
            if (i14 != 32) {
                if (i15 >= length || ((byte) (a[i15] & 128)) != 0) {
                    i11 = i15 < a.length ? (a[i15] & 127) + 1 : 0;
                    if (i11 != 2 || (i8 = i15 + 1) >= a.length) {
                        i5 = (i11 != 3 || (i7 = i15 + 2) >= a.length) ? (i11 != 4 || (i6 = i15 + 2) >= a.length) ? 0 : ((a[i6] & 255) << 8) | ((a[i15 + 1] & 255) << 16) | (a[i15 + 3] & 255) : (a[i7] & 255) | ((a[i15 + 1] & 255) << 8);
                        i11 += i5;
                    } else {
                        i9 = a[i8];
                    }
                } else {
                    i9 = a[i15];
                }
                i5 = i9 & 255;
                i11 += i5;
            } else if (i15 < length && ((byte) (a[i15] & 128)) == Byte.MIN_VALUE) {
                i11 = 1 + (a[i15] & 127);
            }
            i10 = i15 + i11;
        }
        return null;
    }
}
