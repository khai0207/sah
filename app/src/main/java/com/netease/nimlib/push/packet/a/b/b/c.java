package com.netease.nimlib.push.packet.a.b.b;

import java.math.BigInteger;

/* compiled from: PrimeField.java */
/* loaded from: classes.dex */
class c implements a {
    protected final BigInteger a;

    @Override // com.netease.nimlib.push.packet.a.b.b.a
    public int a() {
        return 1;
    }

    c(BigInteger bigInteger) {
        this.a = bigInteger;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof c) {
            return this.a.equals(((c) obj).a);
        }
        return false;
    }

    public int hashCode() {
        return this.a.hashCode();
    }
}
