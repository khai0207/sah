package com.netease.nimlib.push.packet.a.a.c;

/* compiled from: KeyParameter.java */
/* loaded from: classes.dex */
public class j implements com.netease.nimlib.push.packet.a.a.a {
    private byte[] a;

    public j(byte[] bArr) {
        this(bArr, 0, bArr.length);
    }

    public j(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        this.a = bArr2;
        System.arraycopy(bArr, i, bArr2, 0, i2);
    }

    public byte[] a() {
        return this.a;
    }
}
