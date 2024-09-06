package com.netease.nimlib.push.packet.symmetry;

import java.util.Random;

/* compiled from: RC4.java */
/* loaded from: classes.dex */
public class a implements c {
    private byte[] a = new byte[256];
    private int b;
    private int c;

    public static byte[] a() {
        byte[] bArr = new byte[16];
        new Random().nextBytes(bArr);
        return bArr;
    }

    public a(byte[] bArr) throws NullPointerException {
        for (int i = 0; i < 256; i++) {
            this.a[i] = (byte) i;
        }
        this.b = 0;
        this.c = 0;
        if (bArr == null || bArr.length == 0) {
            throw null;
        }
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < 256; i4++) {
            int i5 = bArr[i2] & 255;
            byte[] bArr2 = this.a;
            i3 = (i5 + (bArr2[i4] & 255) + i3) & 255;
            byte b = bArr2[i4];
            bArr2[i4] = bArr2[i3];
            bArr2[i3] = b;
            i2 = (i2 + 1) % bArr.length;
        }
    }

    public int a(byte[] bArr, int i, int i2) {
        int i3;
        if (bArr == null || i < 0 || i2 < 0 || (i3 = i + i2) > bArr.length) {
            throw new IllegalArgumentException("illegal rc4 transform arguments");
        }
        while (i < i3) {
            bArr[i] = a(bArr[i]);
            i++;
        }
        return i2;
    }

    @Override // com.netease.nimlib.push.packet.symmetry.c
    public byte[] b(byte[] bArr, int i, int i2) {
        a(bArr, i, i2);
        return null;
    }

    @Override // com.netease.nimlib.push.packet.symmetry.c
    public byte[] c(byte[] bArr, int i, int i2) {
        a(bArr, i, i2);
        return null;
    }

    private byte a(byte b) {
        int i = (this.b + 1) & 255;
        this.b = i;
        byte[] bArr = this.a;
        int i2 = ((bArr[i] & 255) + this.c) & 255;
        this.c = i2;
        byte b2 = bArr[i];
        bArr[i] = bArr[i2];
        bArr[i2] = b2;
        return (byte) (b ^ bArr[((bArr[i] & 255) + (bArr[i2] & 255)) & 255]);
    }
}
