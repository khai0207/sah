package com.netease.nimlib.push.packet.a.a.c;

/* compiled from: DSAValidationParameters.java */
/* loaded from: classes.dex */
public class e {
    private int a;
    private byte[] b;
    private int c;

    public e(byte[] bArr, int i) {
        this(bArr, i, -1);
    }

    public e(byte[] bArr, int i, int i2) {
        this.b = com.netease.nimlib.push.packet.a.c.a.b(bArr);
        this.c = i;
        this.a = i2;
    }

    public int a() {
        return this.c;
    }

    public byte[] b() {
        return com.netease.nimlib.push.packet.a.c.a.b(this.b);
    }

    public int hashCode() {
        return this.c ^ com.netease.nimlib.push.packet.a.c.a.a(this.b);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof e)) {
            return false;
        }
        e eVar = (e) obj;
        if (eVar.c != this.c) {
            return false;
        }
        return com.netease.nimlib.push.packet.a.c.a.a(this.b, eVar.b);
    }
}
