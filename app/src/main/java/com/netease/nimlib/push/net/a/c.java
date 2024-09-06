package com.netease.nimlib.push.net.a;

import com.netease.nimlib.o.j;
import java.nio.ByteBuffer;

/* compiled from: NetDiagnose.java */
/* loaded from: classes.dex */
public final class c {
    private static boolean a = false;
    private static boolean b = false;
    private byte[] d;
    private int g;
    private int i;
    private b c = new b();
    private byte[] e = new byte[20];
    private byte[] f = new byte[20];
    private byte[] h = new byte[20];

    /* compiled from: NetDiagnose.java */
    /* loaded from: classes.dex */
    public static class a {
        public static final c a = new c();
    }

    public void a(int i, ByteBuffer byteBuffer) {
        if (a) {
            if (i >= 1024) {
                i = 1024;
            }
            this.c.a(new com.netease.nimlib.push.net.a.a(byteBuffer.array(), i));
        }
    }

    public void a(byte[] bArr) {
        if (a && bArr != null && bArr.length >= 4) {
            byte[] bArr2 = new byte[4];
            this.d = bArr2;
            System.arraycopy(bArr, 0, bArr2, 0, 4);
        }
    }

    public void a(int i, byte[] bArr) {
        if (a) {
            System.arraycopy(this.e, 0, this.h, 0, 20);
            this.i = this.g;
            for (int i2 = 0; i2 < 20; i2++) {
                this.e[i2] = 0;
            }
            this.g = i;
            int i3 = i < 20 ? i : 20;
            System.arraycopy(bArr, 0, this.e, 0, i3);
            byte[] bArr2 = this.d;
            if (bArr2 == null || bArr2.length < 4) {
                com.netease.nimlib.log.b.D("packet4 null error!!!");
                this.d = new byte[4];
            }
            System.arraycopy(this.d, 0, this.e, 0, 4);
            com.netease.nimlib.log.b.D("######## raw header=" + j.a(this.e, 0, i3) + ", packet size=" + i);
        }
    }

    public void b(int i, byte[] bArr) {
        if (a) {
            if (i >= 20) {
                i = 20;
            }
            byte[] bArr2 = i == 20 ? this.f : new byte[i];
            System.arraycopy(bArr, 0, bArr2, 0, i);
            com.netease.nimlib.log.b.D("#### decrypt header=" + j.a(bArr2));
        }
    }

    public void a() {
        if (a) {
            byte[] a2 = this.c.a(this.h, this.i);
            if (a2 != null) {
                int i = this.g;
                byte[] bArr = this.e;
                if (i >= bArr.length) {
                    i = bArr.length;
                }
                boolean z = false;
                if (a2.length >= i) {
                    int i2 = 0;
                    while (true) {
                        if (i2 >= i) {
                            z = true;
                            break;
                        } else if (this.e[i2] != a2[i2]) {
                            break;
                        } else {
                            i2++;
                        }
                    }
                }
                String str = "######## check nio read=" + z + ", find nio read=" + j.a(a2);
                if (z) {
                    com.netease.nimlib.log.b.D(str);
                } else {
                    com.netease.nimlib.log.b.F(str);
                }
            }
            com.netease.nimlib.log.b.D("----------------------------------------------------------------------");
        }
    }

    public static c b() {
        return a.a;
    }

    static boolean c() {
        return a && b;
    }
}
