package com.netease.nimlib.push.net.a;

/* compiled from: Buffer.java */
/* loaded from: classes.dex */
class a {
    private byte[] a;

    a(byte[] bArr, int i) {
        byte[] bArr2 = new byte[i];
        System.arraycopy(bArr, 0, bArr2, 0, i);
        this.a = bArr2;
    }

    public byte[] a() {
        return this.a;
    }

    public int b() {
        byte[] bArr = this.a;
        if (bArr == null) {
            return 0;
        }
        return bArr.length;
    }
}
