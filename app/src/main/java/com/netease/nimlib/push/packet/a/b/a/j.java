package com.netease.nimlib.push.packet.a.b.a;

import java.math.BigInteger;

/* compiled from: FixedPointCombMultiplier.java */
/* loaded from: classes.dex */
public class j extends a {
    @Override // com.netease.nimlib.push.packet.a.b.a.a
    protected h b(h hVar, BigInteger bigInteger) {
        d c = hVar.c();
        if (bigInteger.bitLength() > l.a(c)) {
            throw new IllegalStateException("fixed-point comb doesn't support scalars larger than the curve order");
        }
        k a = l.a(hVar);
        f a2 = a.a();
        int c2 = a.c();
        int i = ((r1 + c2) - 1) / c2;
        h c3 = c.c();
        int i2 = c2 * i;
        int[] a3 = com.netease.nimlib.push.packet.a.b.c.b.a(i2, bigInteger);
        int i3 = i2 - 1;
        for (int i4 = 0; i4 < i; i4++) {
            int i5 = 0;
            for (int i6 = i3 - i4; i6 >= 0; i6 -= i) {
                int i7 = a3[i6 >>> 5] >>> (i6 & 31);
                i5 = ((i5 ^ (i7 >>> 1)) << 1) ^ i7;
            }
            c3 = c3.d(a2.a(i5));
        }
        return c3.b(a.b());
    }
}
