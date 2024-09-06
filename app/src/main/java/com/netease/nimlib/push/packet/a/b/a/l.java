package com.netease.nimlib.push.packet.a.b.a;

import java.math.BigInteger;

/* compiled from: FixedPointUtil.java */
/* loaded from: classes.dex */
public class l {
    public static int a(d dVar) {
        BigInteger g = dVar.g();
        return g == null ? dVar.a() + 1 : g.bitLength();
    }

    public static k a(final h hVar) {
        final d c = hVar.c();
        return (k) c.a(hVar, "bc_fixed_point", new o() { // from class: com.netease.nimlib.push.packet.a.b.a.l.1
            @Override // com.netease.nimlib.push.packet.a.b.a.o
            public p a(p pVar) {
                k kVar = pVar instanceof k ? (k) pVar : null;
                int a = l.a(d.this);
                int i = a > 250 ? 6 : 5;
                int i2 = 1 << i;
                if (a(kVar, i2)) {
                    return kVar;
                }
                int i3 = ((a + i) - 1) / i;
                h[] hVarArr = new h[i + 1];
                hVarArr[0] = hVar;
                for (int i4 = 1; i4 < i; i4++) {
                    hVarArr[i4] = hVarArr[i4 - 1].b(i3);
                }
                hVarArr[i] = hVarArr[0].c(hVarArr[1]);
                d.this.a(hVarArr);
                h[] hVarArr2 = new h[i2];
                hVarArr2[0] = hVarArr[0];
                for (int i5 = i - 1; i5 >= 0; i5--) {
                    h hVar2 = hVarArr[i5];
                    int i6 = 1 << i5;
                    for (int i7 = i6; i7 < i2; i7 += i6 << 1) {
                        hVarArr2[i7] = hVarArr2[i7 - i6].b(hVar2);
                    }
                }
                d.this.a(hVarArr2);
                k kVar2 = new k();
                kVar2.a(d.this.a(hVarArr2, 0, i2));
                kVar2.a(hVarArr[i]);
                kVar2.a(i);
                return kVar2;
            }

            private boolean a(k kVar, int i) {
                return kVar != null && a(kVar.a(), i);
            }

            private boolean a(f fVar, int i) {
                return fVar != null && fVar.a() >= i;
            }
        });
    }
}
