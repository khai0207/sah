package com.netease.nimlib.net.a.b.a;

import java.io.Serializable;

/* compiled from: NosServerData.java */
/* loaded from: classes.dex */
final class d implements Serializable {
    private String[] a;
    private String[] b;

    d(String[] strArr, String[] strArr2) {
        this.a = strArr;
        this.b = strArr2;
    }

    String[] a() {
        int i;
        String[] strArr = this.a;
        int i2 = 0;
        int length = strArr == null ? 0 : strArr.length;
        String[] strArr2 = this.b;
        String[] strArr3 = new String[length + (strArr2 == null ? 0 : strArr2.length)];
        String[] strArr4 = this.a;
        if (strArr4 != null) {
            int length2 = strArr4.length;
            int i3 = 0;
            i = 0;
            while (i3 < length2) {
                strArr3[i] = strArr4[i3];
                i3++;
                i++;
            }
        } else {
            i = 0;
        }
        String[] strArr5 = this.b;
        if (strArr5 != null) {
            int length3 = strArr5.length;
            while (i2 < length3) {
                strArr3[i] = strArr5[i2];
                i2++;
                i++;
            }
        }
        return strArr3;
    }

    int b() {
        String[] strArr = this.b;
        if (strArr != null) {
            return strArr.length;
        }
        return 0;
    }

    int c() {
        String[] strArr = this.a;
        if (strArr != null) {
            return strArr.length;
        }
        return 0;
    }
}
