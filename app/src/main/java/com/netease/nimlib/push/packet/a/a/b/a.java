package com.netease.nimlib.push.packet.a.a.b;

import com.netease.nimlib.push.packet.a.a.c.f;
import com.netease.nimlib.push.packet.a.a.c.g;
import com.netease.nimlib.push.packet.a.a.c.i;
import com.netease.nimlib.push.packet.a.a.c.k;
import com.netease.nimlib.push.packet.a.a.e;
import com.netease.nimlib.push.packet.a.b.a.h;
import com.netease.nimlib.push.packet.a.b.a.j;
import com.netease.nimlib.push.packet.a.c.d;
import java.math.BigInteger;
import java.security.SecureRandom;

/* compiled from: SM2Engine.java */
/* loaded from: classes.dex */
public class a {
    private final e a;
    private final EnumC0056a b;
    private boolean c;
    private g d;
    private f e;
    private int f;
    private SecureRandom g;

    /* compiled from: SM2Engine.java */
    /* renamed from: com.netease.nimlib.push.packet.a.a.b.a$a */
    /* loaded from: classes.dex */
    public enum EnumC0056a {
        C1C2C3,
        C1C3C2
    }

    public a() {
        this(new com.netease.nimlib.push.packet.a.a.a.b());
    }

    public a(EnumC0056a enumC0056a) {
        this(new com.netease.nimlib.push.packet.a.a.a.b(), enumC0056a);
    }

    public a(e eVar) {
        this(eVar, EnumC0056a.C1C2C3);
    }

    public a(e eVar, EnumC0056a enumC0056a) {
        if (enumC0056a == null) {
            throw new IllegalArgumentException("mode cannot be NULL");
        }
        this.a = eVar;
        this.b = enumC0056a;
    }

    public void a(boolean z, com.netease.nimlib.push.packet.a.a.a aVar) {
        this.c = z;
        if (z) {
            k kVar = (k) aVar;
            g gVar = (g) kVar.b();
            this.d = gVar;
            this.e = gVar.a();
            if (((i) this.d).b().a(this.e.d()).o()) {
                throw new IllegalArgumentException("invalid key: [h]Q at infinity");
            }
            this.g = kVar.a();
        } else {
            g gVar2 = (g) aVar;
            this.d = gVar2;
            this.e = gVar2.a();
        }
        this.f = (this.e.a().a() + 7) / 8;
    }

    public byte[] a(byte[] bArr, int i, int i2) throws com.netease.nimlib.push.packet.a.a.g {
        if (this.c) {
            return b(bArr, i, i2);
        }
        return c(bArr, i, i2);
    }

    protected com.netease.nimlib.push.packet.a.b.a.g a() {
        return new j();
    }

    private byte[] b(byte[] bArr, int i, int i2) throws com.netease.nimlib.push.packet.a.a.g {
        byte[] a;
        h n;
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        com.netease.nimlib.push.packet.a.b.a.g a2 = a();
        do {
            BigInteger b = b();
            a = a2.a(this.e.b(), b).n().a(false);
            n = ((i) this.d).b().a(b).n();
            a(this.a, n, bArr2);
        } while (a(bArr2, bArr, i));
        byte[] bArr3 = new byte[this.a.a()];
        a(this.a, n.e());
        this.a.a(bArr, i, i2);
        a(this.a, n.f());
        this.a.a(bArr3, 0);
        if (AnonymousClass1.a[this.b.ordinal()] == 1) {
            return com.netease.nimlib.push.packet.a.c.a.a(a, bArr3, bArr2);
        }
        return com.netease.nimlib.push.packet.a.c.a.a(a, bArr2, bArr3);
    }

    /* compiled from: SM2Engine.java */
    /* renamed from: com.netease.nimlib.push.packet.a.a.b.a$1 */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[EnumC0056a.values().length];
            a = iArr;
            try {
                iArr[EnumC0056a.C1C3C2.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    private byte[] c(byte[] bArr, int i, int i2) throws com.netease.nimlib.push.packet.a.a.g {
        int i3;
        int i4 = (this.f * 2) + 1;
        byte[] bArr2 = new byte[i4];
        System.arraycopy(bArr, i, bArr2, 0, i4);
        h a = this.e.a().a(bArr2);
        if (a.a(this.e.d()).o()) {
            throw new com.netease.nimlib.push.packet.a.a.g("[h]C1 at infinity");
        }
        h n = a.a(((com.netease.nimlib.push.packet.a.a.c.h) this.d).b()).n();
        int a2 = this.a.a();
        int i5 = (i2 - i4) - a2;
        byte[] bArr3 = new byte[i5];
        if (this.b == EnumC0056a.C1C3C2) {
            System.arraycopy(bArr, i + i4 + a2, bArr3, 0, i5);
        } else {
            System.arraycopy(bArr, i + i4, bArr3, 0, i5);
        }
        a(this.a, n, bArr3);
        int a3 = this.a.a();
        byte[] bArr4 = new byte[a3];
        a(this.a, n.e());
        this.a.a(bArr3, 0, i5);
        a(this.a, n.f());
        this.a.a(bArr4, 0);
        if (this.b == EnumC0056a.C1C3C2) {
            i3 = 0;
            for (int i6 = 0; i6 != a3; i6++) {
                i3 |= bArr4[i6] ^ bArr[(i + i4) + i6];
            }
        } else {
            i3 = 0;
            for (int i7 = 0; i7 != a3; i7++) {
                i3 |= bArr4[i7] ^ bArr[((i + i4) + i5) + i7];
            }
        }
        com.netease.nimlib.push.packet.a.c.a.a(bArr2, (byte) 0);
        com.netease.nimlib.push.packet.a.c.a.a(bArr4, (byte) 0);
        if (i3 == 0) {
            return bArr3;
        }
        com.netease.nimlib.push.packet.a.c.a.a(bArr3, (byte) 0);
        throw new com.netease.nimlib.push.packet.a.a.g("invalid cipher text");
    }

    private boolean a(byte[] bArr, byte[] bArr2, int i) {
        for (int i2 = 0; i2 != bArr.length; i2++) {
            if (bArr[i2] != bArr2[i + i2]) {
                return false;
            }
        }
        return true;
    }

    private void a(e eVar, h hVar, byte[] bArr) {
        d dVar;
        int a = eVar.a();
        byte[] bArr2 = new byte[Math.max(4, a)];
        d dVar2 = null;
        if (eVar instanceof d) {
            a(eVar, hVar.e());
            a(eVar, hVar.f());
            dVar2 = (d) eVar;
            dVar = dVar2.e();
        } else {
            dVar = null;
        }
        int i = 0;
        int i2 = 0;
        while (i < bArr.length) {
            if (dVar2 != null) {
                dVar2.a(dVar);
            } else {
                a(eVar, hVar.e());
                a(eVar, hVar.f());
            }
            i2++;
            com.netease.nimlib.push.packet.a.c.e.a(i2, bArr2, 0);
            eVar.a(bArr2, 0, 4);
            eVar.a(bArr2, 0);
            int min = Math.min(a, bArr.length - i);
            a(bArr, bArr2, i, min);
            i += min;
        }
    }

    private void a(byte[] bArr, byte[] bArr2, int i, int i2) {
        for (int i3 = 0; i3 != i2; i3++) {
            int i4 = i + i3;
            bArr[i4] = (byte) (bArr[i4] ^ bArr2[i3]);
        }
    }

    private BigInteger b() {
        int bitLength = this.e.c().bitLength();
        while (true) {
            BigInteger a = com.netease.nimlib.push.packet.a.c.b.a(bitLength, this.g);
            if (!a.equals(com.netease.nimlib.push.packet.a.c.b.a) && a.compareTo(this.e.c()) < 0) {
                return a;
            }
        }
    }

    private void a(e eVar, com.netease.nimlib.push.packet.a.b.a.e eVar2) {
        byte[] a = com.netease.nimlib.push.packet.a.c.b.a(this.f, eVar2.a());
        eVar.a(a, 0, a.length);
    }
}
