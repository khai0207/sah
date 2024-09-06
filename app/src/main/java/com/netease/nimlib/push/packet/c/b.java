package com.netease.nimlib.push.packet.c;

import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* compiled from: Pack.java */
/* loaded from: classes.dex */
public class b {
    private ByteBuffer a;
    private int b = 1048576;

    public b() {
        ByteBuffer a = a.a(1024);
        this.a = a;
        a.order(ByteOrder.LITTLE_ENDIAN);
    }

    public int a() {
        return this.a.position();
    }

    public ByteBuffer b() {
        ByteBuffer duplicate = this.a.duplicate();
        duplicate.flip();
        return duplicate;
    }

    public b a(byte[] bArr) {
        try {
            c(bArr.length);
            this.a.put(bArr);
            return this;
        } catch (BufferOverflowException unused) {
            throw new c();
        }
    }

    public b a(byte b) {
        try {
            c(1);
            this.a.put(b);
            return this;
        } catch (BufferOverflowException unused) {
            throw new c();
        }
    }

    public b a(String str) {
        try {
            return b(TextUtils.isEmpty(str) ? null : str.getBytes("utf-8"));
        } catch (UnsupportedEncodingException unused) {
            throw new c();
        }
    }

    public b a(int i) {
        try {
            c(4);
            this.a.putInt(i);
            return this;
        } catch (BufferOverflowException unused) {
            throw new c();
        }
    }

    public b b(int i) {
        return a(d.a(i));
    }

    public b a(boolean z) {
        int i = 1;
        try {
            c(1);
            ByteBuffer byteBuffer = this.a;
            if (!z) {
                i = 0;
            }
            byteBuffer.put((byte) i);
            return this;
        } catch (BufferOverflowException unused) {
            throw new c();
        }
    }

    public b a(long j) {
        try {
            c(8);
            this.a.putLong(j);
            return this;
        } catch (BufferOverflowException unused) {
            throw new c();
        }
    }

    public b b(String str) {
        try {
            a(Long.valueOf(str).longValue());
            return this;
        } catch (Exception unused) {
            throw new c();
        }
    }

    public b a(short s) {
        try {
            c(2);
            this.a.putShort(s);
            return this;
        } catch (BufferOverflowException unused) {
            throw new c();
        }
    }

    public b b(byte[] bArr) {
        try {
            if (bArr == null) {
                return b(0);
            }
            if (bArr.length > 2147483645) {
                throw new c();
            }
            c(d.b(bArr.length) + bArr.length);
            this.a.put(d.a(bArr.length));
            this.a.put(bArr);
            return this;
        } catch (BufferOverflowException unused) {
            throw new c();
        }
    }

    public b a(com.netease.nimlib.push.packet.b.b bVar) {
        bVar.a(this);
        return this;
    }

    public b a(ByteBuffer byteBuffer) {
        try {
            c(byteBuffer.remaining());
            this.a.put(byteBuffer);
            return this;
        } catch (BufferOverflowException unused) {
            throw new c();
        }
    }

    public void c(int i) throws BufferOverflowException {
        if (this.a.remaining() >= i) {
            return;
        }
        int capacity = (this.a.capacity() + i) - this.a.remaining();
        if (capacity > this.b) {
            throw new BufferOverflowException();
        }
        ByteBuffer allocate = ByteBuffer.allocate(Math.min(Math.max(capacity, this.a.capacity() * 2), this.b));
        allocate.order(this.a.order());
        this.a.flip();
        allocate.put(this.a);
        this.a = allocate;
    }

    public String toString() {
        return this.a.toString() + " Size " + a();
    }
}
