package com.netease.nimlib.push.packet.c;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

/* compiled from: PacketCompressor.java */
/* loaded from: classes.dex */
public class e {
    public static ByteBuffer a(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2 + 4 + (i2 / 1000) + 12];
        Deflater deflater = new Deflater();
        deflater.setInput(bArr, i, i2);
        deflater.finish();
        int deflate = deflater.deflate(bArr2);
        deflater.end();
        ByteBuffer allocate = ByteBuffer.allocate(deflate + 4);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        allocate.putInt(i2);
        allocate.put(bArr2, 0, deflate);
        allocate.flip();
        return allocate;
    }

    public static ByteBuffer a(ByteBuffer byteBuffer) {
        return a(byteBuffer.array(), byteBuffer.position(), byteBuffer.limit() - byteBuffer.position());
    }

    public static ByteBuffer a(f fVar) throws g {
        int f = fVar.f();
        if (f < 0 || f >= 10485760) {
            throw new g("invalid uncompress len:" + f);
        }
        try {
            ByteBuffer allocate = ByteBuffer.allocate(f);
            Inflater inflater = new Inflater();
            ByteBuffer b = fVar.b();
            inflater.setInput(b.array(), b.position(), b.limit() - b.position());
            allocate.position(inflater.inflate(allocate.array()));
            inflater.end();
            allocate.flip();
            return allocate;
        } catch (Exception unused) {
            throw new g("uncompress error");
        }
    }
}
