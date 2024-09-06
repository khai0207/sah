package com.netease.nimlib.push.packet.asymmetric;

import android.content.Context;
import java.io.File;
import java.security.PublicKey;
import java.util.zip.CRC32;

/* compiled from: Asymmetric.java */
/* loaded from: classes.dex */
public abstract class a {
    protected final Context a;
    public int b;
    public PublicKey c;

    protected abstract void a();

    public abstract void a(int i, String str, String str2, long j);

    public abstract byte[] a(PublicKey publicKey, byte[] bArr, int i, int i2);

    protected abstract String c();

    protected abstract String e();

    public a(Context context) {
        this.a = context;
        a();
    }

    protected String b() {
        return com.netease.nimlib.a.b + "/" + c() + d();
    }

    protected String d() {
        return com.netease.nimlib.d.e.c() ? "bbgsvirgin4" : "bbgsvirgin";
    }

    protected void f() {
        if (!com.netease.nimlib.d.e.c() || new File(b()).exists()) {
            return;
        }
        String str = com.netease.nimlib.c.l().negoKeyEncaKeyParta;
        String str2 = com.netease.nimlib.c.l().negoKeyEncaKeyPartb;
        byte[] bytes = (str + str2).getBytes();
        CRC32 crc32 = new CRC32();
        crc32.update(bytes, 0, bytes.length);
        int i = com.netease.nimlib.c.l().negoKeyEncaKeyVersion;
        a(i >= 0 ? i : 0, str, str2, crc32.getValue());
    }

    protected static boolean a(String str, String str2, long j) {
        if (str == null || str2 == null) {
            return false;
        }
        CRC32 crc32 = new CRC32();
        byte[] bytes = (str + str2).getBytes();
        crc32.update(bytes, 0, bytes.length);
        return j == crc32.getValue();
    }

    protected void g() {
        new File(b()).delete();
    }
}
