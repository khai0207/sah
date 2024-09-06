package com.netease.nimlib.push.packet.a.a.c;

/* compiled from: DHValidationParameters.java */
/* loaded from: classes.dex */
public class c {
    private byte[] a;
    private int b;

    public c(byte[] bArr, int i) {
        this.a = com.netease.nimlib.push.packet.a.c.a.b(bArr);
        this.b = i;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        if (cVar.b != this.b) {
            return false;
        }
        return com.netease.nimlib.push.packet.a.c.a.a(this.a, cVar.a);
    }

    public int hashCode() {
        return this.b ^ com.netease.nimlib.push.packet.a.c.a.a(this.a);
    }
}
