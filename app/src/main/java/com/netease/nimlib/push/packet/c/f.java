package com.netease.nimlib.push.packet.c;

import java.io.UnsupportedEncodingException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* compiled from: Unpack.java */
/* loaded from: classes.dex */
public class f {
    protected ByteBuffer a;

    public int a() {
        return this.a.limit() - this.a.position();
    }

    public f(byte[] bArr, int i, int i2) {
        ByteBuffer wrap = ByteBuffer.wrap(bArr, i, i2);
        this.a = wrap;
        wrap.order(ByteOrder.LITTLE_ENDIAN);
    }

    public f(byte[] bArr) {
        this(bArr, 0, bArr.length);
    }

    public f(ByteBuffer byteBuffer) {
        this(byteBuffer.array(), byteBuffer.position(), byteBuffer.limit() - byteBuffer.position());
    }

    public f() {
        this.a = null;
    }

    public ByteBuffer b() {
        return this.a.duplicate();
    }

    public byte[] a(int i) {
        try {
            byte[] bArr = new byte[i];
            this.a.get(bArr);
            return bArr;
        } catch (BufferUnderflowException unused) {
            throw new g();
        }
    }

    public byte c() {
        try {
            return this.a.get();
        } catch (BufferUnderflowException unused) {
            throw new g();
        }
    }

    public byte[] d() {
        return a(g());
    }

    public String e() {
        return a("utf-8");
    }

    public String a(String str) {
        try {
            return new String(d(), str);
        } catch (UnsupportedEncodingException unused) {
            throw new g();
        }
    }

    public int f() {
        try {
            return this.a.getInt();
        } catch (BufferUnderflowException unused) {
            throw new g();
        }
    }

    public int g() {
        return d.d(this);
    }

    public long h() {
        try {
            return this.a.getLong();
        } catch (BufferUnderflowException unused) {
            throw new g();
        }
    }

    public String i() {
        try {
            return String.valueOf(this.a.getLong());
        } catch (BufferUnderflowException unused) {
            throw new g();
        }
    }

    public short j() {
        try {
            return this.a.getShort();
        } catch (BufferUnderflowException unused) {
            throw new g();
        }
    }

    public com.netease.nimlib.push.packet.b.b a(com.netease.nimlib.push.packet.b.b bVar) {
        bVar.a(this);
        return bVar;
    }

    public boolean k() {
        return c() > 0;
    }

    public String toString() {
        return this.a.toString();
    }
}
