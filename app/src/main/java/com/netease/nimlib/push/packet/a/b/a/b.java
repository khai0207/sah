package com.netease.nimlib.push.packet.a.b.a;

import java.math.BigInteger;

/* compiled from: ECAlgorithms.java */
/* loaded from: classes.dex */
public class b {
    public static boolean a(d dVar) {
        return a(dVar.d());
    }

    public static boolean a(com.netease.nimlib.push.packet.a.b.b.a aVar) {
        return aVar.a() == 1;
    }

    public static h a(d dVar, h hVar) {
        if (!dVar.a(hVar.c())) {
            throw new IllegalArgumentException("Point must be on the same curve");
        }
        return dVar.a(hVar);
    }

    public static void a(e[] eVarArr, int i, int i2, e eVar) {
        e[] eVarArr2 = new e[i2];
        int i3 = 0;
        eVarArr2[0] = eVarArr[i];
        while (true) {
            i3++;
            if (i3 >= i2) {
                break;
            } else {
                eVarArr2[i3] = eVarArr2[i3 - 1].b(eVarArr[i + i3]);
            }
        }
        int i4 = i3 - 1;
        if (eVar != null) {
            eVarArr2[i4] = eVarArr2[i4].b(eVar);
        }
        e e = eVarArr2[i4].e();
        while (i4 > 0) {
            int i5 = i4 - 1;
            int i6 = i4 + i;
            e eVar2 = eVarArr[i6];
            eVarArr[i6] = eVarArr2[i5].b(e);
            e = e.b(eVar2);
            i4 = i5;
        }
        eVarArr[i] = e;
    }

    public static h a(h hVar, BigInteger bigInteger) {
        BigInteger abs = bigInteger.abs();
        h c = hVar.c().c();
        int bitLength = abs.bitLength();
        if (bitLength > 0) {
            if (abs.testBit(0)) {
                c = hVar;
            }
            for (int i = 1; i < bitLength; i++) {
                hVar = hVar.t();
                if (abs.testBit(i)) {
                    c = c.b(hVar);
                }
            }
        }
        return bigInteger.signum() < 0 ? c.s() : c;
    }

    static h a(h hVar) {
        if (hVar.q()) {
            return hVar;
        }
        throw new IllegalStateException("Invalid result");
    }

    static h a(h hVar, BigInteger bigInteger, h hVar2, BigInteger bigInteger2) {
        boolean z = bigInteger.signum() < 0;
        boolean z2 = bigInteger2.signum() < 0;
        BigInteger abs = bigInteger.abs();
        BigInteger abs2 = bigInteger2.abs();
        int max = Math.max(2, Math.min(16, t.a(abs.bitLength())));
        int max2 = Math.max(2, Math.min(16, t.a(abs2.bitLength())));
        s a = t.a(hVar, max, true);
        s a2 = t.a(hVar2, max2, true);
        return a(z ? a.b() : a.a(), z ? a.a() : a.b(), t.b(max, abs), z2 ? a2.b() : a2.a(), z2 ? a2.a() : a2.b(), t.b(max2, abs2));
    }

    static h a(h hVar, BigInteger bigInteger, i iVar, BigInteger bigInteger2) {
        boolean z = bigInteger.signum() < 0;
        boolean z2 = bigInteger2.signum() < 0;
        BigInteger abs = bigInteger.abs();
        BigInteger abs2 = bigInteger2.abs();
        int max = Math.max(2, Math.min(16, t.a(Math.max(abs.bitLength(), abs2.bitLength()))));
        h a = t.a(hVar, max, true, iVar);
        s a2 = t.a(hVar);
        s a3 = t.a(a);
        return a(z ? a2.b() : a2.a(), z ? a2.a() : a2.b(), t.b(max, abs), z2 ? a3.b() : a3.a(), z2 ? a3.a() : a3.b(), t.b(max, abs2));
    }

    private static h a(h[] hVarArr, h[] hVarArr2, byte[] bArr, h[] hVarArr3, h[] hVarArr4, byte[] bArr2) {
        h hVar;
        int max = Math.max(bArr.length, bArr2.length);
        h c = hVarArr[0].c().c();
        int i = max - 1;
        h hVar2 = c;
        int i2 = 0;
        while (i >= 0) {
            byte b = i < bArr.length ? bArr[i] : (byte) 0;
            byte b2 = i < bArr2.length ? bArr2[i] : (byte) 0;
            if ((b | b2) == 0) {
                i2++;
            } else {
                if (b != 0) {
                    hVar = c.b((b < 0 ? hVarArr2 : hVarArr)[Math.abs((int) b) >>> 1]);
                } else {
                    hVar = c;
                }
                if (b2 != 0) {
                    hVar = hVar.b((b2 < 0 ? hVarArr4 : hVarArr3)[Math.abs((int) b2) >>> 1]);
                }
                if (i2 > 0) {
                    hVar2 = hVar2.b(i2);
                    i2 = 0;
                }
                hVar2 = hVar2.d(hVar);
            }
            i--;
        }
        return i2 > 0 ? hVar2.b(i2) : hVar2;
    }
}
