package com.netease.nimlib.push.packet.a.b.a;

import java.math.BigInteger;
import java.util.Hashtable;

/* compiled from: ECCurve.java */
/* loaded from: classes.dex */
public abstract class d {
    protected com.netease.nimlib.push.packet.a.b.b.a a;
    protected e b;
    protected e c;
    protected BigInteger d;
    protected BigInteger e;
    protected int f = 0;
    protected com.netease.nimlib.push.packet.a.b.a.b.a g = null;
    protected g h = null;

    public abstract int a();

    public abstract e a(BigInteger bigInteger);

    protected abstract h a(int i, BigInteger bigInteger);

    protected abstract h a(e eVar, e eVar2, boolean z);

    protected abstract h a(e eVar, e eVar2, e[] eVarArr, boolean z);

    public abstract h c();

    protected d(com.netease.nimlib.push.packet.a.b.b.a aVar) {
        this.a = aVar;
    }

    public h a(BigInteger bigInteger, BigInteger bigInteger2) {
        h b = b(bigInteger, bigInteger2);
        if (b.p()) {
            return b;
        }
        throw new IllegalArgumentException("Invalid point coordinates");
    }

    public h b(BigInteger bigInteger, BigInteger bigInteger2) {
        return a(bigInteger, bigInteger2, false);
    }

    public h a(BigInteger bigInteger, BigInteger bigInteger2, boolean z) {
        return a(a(bigInteger), a(bigInteger2), z);
    }

    protected g b() {
        com.netease.nimlib.push.packet.a.b.a.b.a aVar = this.g;
        if (aVar instanceof com.netease.nimlib.push.packet.a.b.a.b.b) {
            return new m(this, (com.netease.nimlib.push.packet.a.b.a.b.b) aVar);
        }
        return new r();
    }

    public p a(h hVar, String str) {
        Hashtable hashtable;
        p pVar;
        b(hVar);
        synchronized (hVar) {
            hashtable = hVar.g;
        }
        if (hashtable == null) {
            return null;
        }
        synchronized (hashtable) {
            pVar = (p) hashtable.get(str);
        }
        return pVar;
    }

    public p a(h hVar, String str, o oVar) {
        Hashtable hashtable;
        p a2;
        b(hVar);
        synchronized (hVar) {
            hashtable = hVar.g;
            if (hashtable == null) {
                hashtable = new Hashtable(4);
                hVar.g = hashtable;
            }
        }
        synchronized (hashtable) {
            p pVar = (p) hashtable.get(str);
            a2 = oVar.a(pVar);
            if (a2 != pVar) {
                hashtable.put(str, a2);
            }
        }
        return a2;
    }

    public h a(h hVar) {
        if (this == hVar.c()) {
            return hVar;
        }
        if (hVar.o()) {
            return c();
        }
        h n = hVar.n();
        return a(n.g().a(), n.h().a(), n.f);
    }

    public void a(h[] hVarArr) {
        a(hVarArr, 0, hVarArr.length, (e) null);
    }

    public void a(h[] hVarArr, int i, int i2, e eVar) {
        b(hVarArr, i, i2);
        int i3 = i();
        if (i3 == 0 || i3 == 5) {
            if (eVar != null) {
                throw new IllegalArgumentException("'iso' not valid for affine coordinates");
            }
            return;
        }
        e[] eVarArr = new e[i2];
        int[] iArr = new int[i2];
        int i4 = 0;
        for (int i5 = 0; i5 < i2; i5++) {
            int i6 = i + i5;
            h hVar = hVarArr[i6];
            if (hVar != null && (eVar != null || !hVar.m())) {
                eVarArr[i4] = hVar.a(0);
                iArr[i4] = i6;
                i4++;
            }
        }
        if (i4 == 0) {
            return;
        }
        b.a(eVarArr, 0, i4, eVar);
        for (int i7 = 0; i7 < i4; i7++) {
            int i8 = iArr[i7];
            hVarArr[i8] = hVarArr[i8].a(eVarArr[i7]);
        }
    }

    public com.netease.nimlib.push.packet.a.b.b.a d() {
        return this.a;
    }

    public e e() {
        return this.b;
    }

    public e f() {
        return this.c;
    }

    public BigInteger g() {
        return this.d;
    }

    public BigInteger h() {
        return this.e;
    }

    public int i() {
        return this.f;
    }

    public synchronized g j() {
        if (this.h == null) {
            this.h = b();
        }
        return this.h;
    }

    public h a(byte[] bArr) {
        h c;
        int a2 = (a() + 7) / 8;
        byte b = bArr[0];
        if (b != 0) {
            if (b == 2 || b == 3) {
                if (bArr.length != a2 + 1) {
                    throw new IllegalArgumentException("Incorrect length for compressed encoding");
                }
                c = a(b & 1, com.netease.nimlib.push.packet.a.c.b.a(bArr, 1, a2));
                if (!c.a(true, true)) {
                    throw new IllegalArgumentException("Invalid point");
                }
            } else if (b != 4) {
                if (b == 6 || b == 7) {
                    if (bArr.length != (a2 * 2) + 1) {
                        throw new IllegalArgumentException("Incorrect length for hybrid encoding");
                    }
                    BigInteger a3 = com.netease.nimlib.push.packet.a.c.b.a(bArr, 1, a2);
                    BigInteger a4 = com.netease.nimlib.push.packet.a.c.b.a(bArr, a2 + 1, a2);
                    if (a4.testBit(0) != (b == 7)) {
                        throw new IllegalArgumentException("Inconsistent Y coordinate in hybrid encoding");
                    }
                    c = a(a3, a4);
                } else {
                    throw new IllegalArgumentException("Invalid point encoding 0x" + Integer.toString(b, 16));
                }
            } else {
                if (bArr.length != (a2 * 2) + 1) {
                    throw new IllegalArgumentException("Incorrect length for uncompressed encoding");
                }
                c = a(com.netease.nimlib.push.packet.a.c.b.a(bArr, 1, a2), com.netease.nimlib.push.packet.a.c.b.a(bArr, a2 + 1, a2));
            }
        } else {
            if (bArr.length != 1) {
                throw new IllegalArgumentException("Incorrect length for infinity encoding");
            }
            c = c();
        }
        if (b == 0 || !c.o()) {
            return c;
        }
        throw new IllegalArgumentException("Invalid infinity encoding");
    }

    public f a(h[] hVarArr, int i, int i2) {
        int a2 = (a() + 7) >>> 3;
        byte[] bArr = new byte[i2 * a2 * 2];
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            h hVar = hVarArr[i + i4];
            byte[] byteArray = hVar.i().a().toByteArray();
            byte[] byteArray2 = hVar.j().a().toByteArray();
            int i5 = 1;
            int i6 = byteArray.length > a2 ? 1 : 0;
            int length = byteArray.length - i6;
            if (byteArray2.length <= a2) {
                i5 = 0;
            }
            int length2 = byteArray2.length - i5;
            int i7 = i3 + a2;
            System.arraycopy(byteArray, i6, bArr, i7 - length, length);
            i3 = i7 + a2;
            System.arraycopy(byteArray2, i5, bArr, i3 - length2, length2);
        }
        return new f() { // from class: com.netease.nimlib.push.packet.a.b.a.d.1
            final /* synthetic */ int a;
            final /* synthetic */ int b;
            final /* synthetic */ byte[] c;

            AnonymousClass1(int i22, int a22, byte[] bArr2) {
                r2 = i22;
                r3 = a22;
                r4 = bArr2;
            }

            @Override // com.netease.nimlib.push.packet.a.b.a.f
            public int a() {
                return r2;
            }

            @Override // com.netease.nimlib.push.packet.a.b.a.f
            public h a(int i8) {
                int i9;
                int i10 = r3;
                byte[] bArr2 = new byte[i10];
                byte[] bArr3 = new byte[i10];
                int i11 = 0;
                for (int i12 = 0; i12 < r2; i12++) {
                    int i13 = ((i12 ^ i8) - 1) >> 31;
                    int i14 = 0;
                    while (true) {
                        i9 = r3;
                        if (i14 < i9) {
                            byte b = bArr2[i14];
                            byte[] bArr4 = r4;
                            bArr2[i14] = (byte) (b ^ (bArr4[i11 + i14] & i13));
                            bArr3[i14] = (byte) ((bArr4[(i9 + i11) + i14] & i13) ^ bArr3[i14]);
                            i14++;
                        }
                    }
                    i11 += i9 * 2;
                }
                d dVar = d.this;
                return dVar.a(dVar.a(new BigInteger(1, bArr2)), d.this.a(new BigInteger(1, bArr3)), false);
            }
        };
    }

    /* compiled from: ECCurve.java */
    /* renamed from: com.netease.nimlib.push.packet.a.b.a.d$1 */
    /* loaded from: classes.dex */
    class AnonymousClass1 implements f {
        final /* synthetic */ int a;
        final /* synthetic */ int b;
        final /* synthetic */ byte[] c;

        AnonymousClass1(int i22, int a22, byte[] bArr2) {
            r2 = i22;
            r3 = a22;
            r4 = bArr2;
        }

        @Override // com.netease.nimlib.push.packet.a.b.a.f
        public int a() {
            return r2;
        }

        @Override // com.netease.nimlib.push.packet.a.b.a.f
        public h a(int i8) {
            int i9;
            int i10 = r3;
            byte[] bArr2 = new byte[i10];
            byte[] bArr3 = new byte[i10];
            int i11 = 0;
            for (int i12 = 0; i12 < r2; i12++) {
                int i13 = ((i12 ^ i8) - 1) >> 31;
                int i14 = 0;
                while (true) {
                    i9 = r3;
                    if (i14 < i9) {
                        byte b = bArr2[i14];
                        byte[] bArr4 = r4;
                        bArr2[i14] = (byte) (b ^ (bArr4[i11 + i14] & i13));
                        bArr3[i14] = (byte) ((bArr4[(i9 + i11) + i14] & i13) ^ bArr3[i14]);
                        i14++;
                    }
                }
                i11 += i9 * 2;
            }
            d dVar = d.this;
            return dVar.a(dVar.a(new BigInteger(1, bArr2)), d.this.a(new BigInteger(1, bArr3)), false);
        }
    }

    protected void b(h hVar) {
        if (hVar == null || this != hVar.c()) {
            throw new IllegalArgumentException("'point' must be non-null and on this curve");
        }
    }

    protected void b(h[] hVarArr, int i, int i2) {
        if (hVarArr == null) {
            throw new IllegalArgumentException("'points' cannot be null");
        }
        if (i < 0 || i2 < 0 || i > hVarArr.length - i2) {
            throw new IllegalArgumentException("invalid range specified for 'points'");
        }
        for (int i3 = 0; i3 < i2; i3++) {
            h hVar = hVarArr[i + i3];
            if (hVar != null && this != hVar.c()) {
                throw new IllegalArgumentException("'points' entries must be null or on this curve");
            }
        }
    }

    public boolean a(d dVar) {
        return this == dVar || (dVar != null && d().equals(dVar.d()) && e().a().equals(dVar.e().a()) && f().a().equals(dVar.f().a()));
    }

    public boolean equals(Object obj) {
        return this == obj || ((obj instanceof d) && a((d) obj));
    }

    public int hashCode() {
        return (d().hashCode() ^ com.netease.nimlib.push.packet.a.c.c.a(e().a().hashCode(), 8)) ^ com.netease.nimlib.push.packet.a.c.c.a(f().a().hashCode(), 16);
    }

    /* compiled from: ECCurve.java */
    /* loaded from: classes.dex */
    public static abstract class a extends d {
        protected a(BigInteger bigInteger) {
            super(com.netease.nimlib.push.packet.a.b.b.b.a(bigInteger));
        }

        @Override // com.netease.nimlib.push.packet.a.b.a.d
        protected h a(int i, BigInteger bigInteger) {
            e a = a(bigInteger);
            e f = a.d().a(this.b).b(a).a(this.c).f();
            if (f == null) {
                throw new IllegalArgumentException("Invalid point compression");
            }
            if (f.j() != (i == 1)) {
                f = f.c();
            }
            return a(a, f, true);
        }
    }
}
