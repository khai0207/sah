package com.netease.nimlib.push.packet.asymmetric;

import android.content.Context;
import com.netease.nimlib.o.j;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.zip.CRC32;

/* compiled from: KeyStore.java */
/* loaded from: classes.dex */
public class c {
    int a;
    PublicKey b;

    public static c a(Context context) {
        c cVar = new c();
        cVar.b(context.getApplicationContext());
        return cVar;
    }

    public void a(int i, byte[] bArr, long j) {
        if (bArr == null || !a(bArr, j)) {
            return;
        }
        FileOutputStream fileOutputStream = null;
        try {
            try {
                byte[] encoded = KeyFactory.getInstance(com.alipay.sdk.m.j.d.a).generatePublic(new RSAPublicKeySpec(new BigInteger(bArr), new BigInteger("10001", 16))).getEncoded();
                ByteBuffer allocate = ByteBuffer.allocate(encoded.length + 12);
                allocate.putLong(j);
                allocate.putInt(i);
                allocate.put(encoded);
                File file = new File(b());
                if (!file.exists()) {
                    file.getParentFile().mkdirs();
                }
                FileOutputStream fileOutputStream2 = new FileOutputStream(new File(b()));
                try {
                    fileOutputStream2.write(allocate.array(), 0, allocate.capacity());
                    com.netease.nimlib.log.c.a.a.a(fileOutputStream2);
                } catch (Exception unused) {
                    fileOutputStream = fileOutputStream2;
                    d();
                    com.netease.nimlib.log.c.a.a.a(fileOutputStream);
                } catch (Throwable th) {
                    fileOutputStream = fileOutputStream2;
                    th = th;
                    com.netease.nimlib.log.c.a.a.a(fileOutputStream);
                    throw th;
                }
            } catch (Exception unused2) {
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public void a() {
        d();
    }

    private void b(Context context) {
        c();
        File file = new File(b());
        try {
            FileInputStream fileInputStream = file.exists() ? new FileInputStream(file) : null;
            if ((fileInputStream == null || !a((InputStream) fileInputStream, true)) && !a(context.getAssets().open(a(true)), false)) {
                com.netease.nimlib.log.b.O("load public key from assets failed!!");
            }
        } catch (Exception e) {
            com.netease.nimlib.log.b.e("core", "load public key error", e);
        }
        if (this.b == null) {
            file.delete();
        }
    }

    private boolean a(InputStream inputStream, boolean z) {
        byte[] bArr = new byte[256];
        try {
            try {
                int read = inputStream.read(bArr);
                long j = 0;
                ByteBuffer wrap = ByteBuffer.wrap(bArr);
                if (z) {
                    j = wrap.getLong();
                    read -= 8;
                }
                int i = wrap.getInt();
                this.a = i;
                if (i < 0) {
                    return false;
                }
                byte[] bArr2 = new byte[read - 4];
                wrap.get(bArr2);
                PublicKey generatePublic = KeyFactory.getInstance(com.alipay.sdk.m.j.d.a).generatePublic(new X509EncodedKeySpec(bArr2));
                this.b = generatePublic;
                if (!z || a(((RSAPublicKey) generatePublic).getModulus().toByteArray(), j)) {
                    try {
                        inputStream.close();
                    } catch (IOException unused) {
                    }
                    return true;
                }
                a();
                this.b = null;
                try {
                    inputStream.close();
                } catch (IOException unused2) {
                }
                return false;
            } finally {
                try {
                    inputStream.close();
                } catch (IOException unused3) {
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                inputStream.close();
            } catch (IOException unused4) {
            }
            return false;
        }
    }

    private boolean a(byte[] bArr, long j) {
        if (bArr == null) {
            return false;
        }
        CRC32 crc32 = new CRC32();
        crc32.update(bArr, 0, bArr.length);
        return j == crc32.getValue();
    }

    private final String a(boolean z) {
        return com.netease.nimlib.d.e.d() ? "bbgsvirgin4" : z ? "nim/nim_keystore" : "bbgsvirgin";
    }

    private String b() {
        return com.netease.nimlib.a.b + "/" + a(false);
    }

    private void c() {
        if (!com.netease.nimlib.d.e.d() || new File(b()).exists()) {
            return;
        }
        byte[] a = j.a(com.netease.nimlib.c.l().module);
        CRC32 crc32 = new CRC32();
        crc32.update(a, 0, a.length);
        int i = com.netease.nimlib.c.l().publicKeyVersion;
        a(i >= 0 ? i : 0, a, crc32.getValue());
    }

    private void d() {
        new File(b()).delete();
    }
}
