package com.netease.nimlib.push.packet.a.b.b;

import java.math.BigInteger;

/* compiled from: FiniteFields.java */
/* loaded from: classes.dex */
public abstract class b {
    static final a a = new c(BigInteger.valueOf(2));
    static final a b = new c(BigInteger.valueOf(3));

    public static a a(BigInteger bigInteger) {
        int bitLength = bigInteger.bitLength();
        if (bigInteger.signum() <= 0 || bitLength < 2) {
            throw new IllegalArgumentException("'characteristic' must be >= 2");
        }
        if (bitLength < 3) {
            int intValue = bigInteger.intValue();
            if (intValue == 2) {
                return a;
            }
            if (intValue == 3) {
                return b;
            }
        }
        return new c(bigInteger);
    }
}
