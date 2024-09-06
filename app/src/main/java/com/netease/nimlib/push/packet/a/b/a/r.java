package com.netease.nimlib.push.packet.a.b.a;

import androidx.core.internal.view.SupportMenu;
import java.math.BigInteger;

/* compiled from: WNafL2RMultiplier.java */
/* loaded from: classes.dex */
public class r extends a {
    @Override // com.netease.nimlib.push.packet.a.b.a.a
    protected h b(h hVar, BigInteger bigInteger) {
        h hVar2;
        int max = Math.max(2, Math.min(16, a(bigInteger.bitLength())));
        s a = t.a(hVar, max, true);
        h[] a2 = a.a();
        h[] b = a.b();
        int[] a3 = t.a(max, bigInteger);
        h c = hVar.c().c();
        int length = a3.length;
        if (length > 1) {
            length--;
            int i = a3[length];
            int i2 = i >> 16;
            int i3 = i & SupportMenu.USER_MASK;
            int abs = Math.abs(i2);
            h[] hVarArr = i2 < 0 ? b : a2;
            if ((abs << 2) < (1 << max)) {
                byte b2 = n.a[abs];
                int i4 = max - b2;
                hVar2 = hVarArr[((1 << (max - 1)) - 1) >>> 1].b(hVarArr[(((abs ^ (1 << (b2 - 1))) << i4) + 1) >>> 1]);
                i3 -= i4;
            } else {
                hVar2 = hVarArr[abs >>> 1];
            }
            c = hVar2.b(i3);
        }
        while (length > 0) {
            length--;
            int i5 = a3[length];
            int i6 = i5 >> 16;
            c = c.d((i6 < 0 ? b : a2)[Math.abs(i6) >>> 1]).b(i5 & SupportMenu.USER_MASK);
        }
        return c;
    }

    protected int a(int i) {
        return t.a(i);
    }
}
