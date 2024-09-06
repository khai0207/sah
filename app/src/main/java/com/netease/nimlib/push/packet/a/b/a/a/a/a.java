package com.netease.nimlib.push.packet.a.b.a.a.a;

import com.netease.nimlib.push.packet.a.b.a.d;
import com.netease.nimlib.push.packet.a.b.a.e;
import com.netease.nimlib.push.packet.a.b.a.f;
import com.netease.nimlib.push.packet.a.b.a.h;
import java.math.BigInteger;

/* compiled from: SM2P256V1Curve.java */
/* loaded from: classes.dex */
public class a extends d.a {
    public static final BigInteger i = new BigInteger(1, com.netease.nimlib.push.packet.a.c.a.c.a("FFFFFFFEFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF00000000FFFFFFFFFFFFFFFF"));
    protected d j;

    public a() {
        super(i);
        this.j = new d(this, null, null);
        this.b = a(new BigInteger(1, com.netease.nimlib.push.packet.a.c.a.c.a("FFFFFFFEFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF00000000FFFFFFFFFFFFFFFC")));
        this.c = a(new BigInteger(1, com.netease.nimlib.push.packet.a.c.a.c.a("28E9FA9E9D9F5E344D5A9E4BCF6509A7F39789F515AB8F92DDBCBD414D940E93")));
        this.d = new BigInteger(1, com.netease.nimlib.push.packet.a.c.a.c.a("FFFFFFFEFFFFFFFFFFFFFFFFFFFFFFFF7203DF6B21C6052B53BBF40939D54123"));
        this.e = BigInteger.valueOf(1L);
        this.f = 2;
    }

    @Override // com.netease.nimlib.push.packet.a.b.a.d
    public int a() {
        return i.bitLength();
    }

    @Override // com.netease.nimlib.push.packet.a.b.a.d
    public e a(BigInteger bigInteger) {
        return new c(bigInteger);
    }

    @Override // com.netease.nimlib.push.packet.a.b.a.d
    protected h a(e eVar, e eVar2, boolean z) {
        return new d(this, eVar, eVar2, z);
    }

    @Override // com.netease.nimlib.push.packet.a.b.a.d
    protected h a(e eVar, e eVar2, e[] eVarArr, boolean z) {
        return new d(this, eVar, eVar2, eVarArr, z);
    }

    @Override // com.netease.nimlib.push.packet.a.b.a.d
    public h c() {
        return this.j;
    }

    @Override // com.netease.nimlib.push.packet.a.b.a.d
    public f a(h[] hVarArr, int i2, final int i3) {
        final int[] iArr = new int[i3 * 8 * 2];
        int i4 = 0;
        for (int i5 = 0; i5 < i3; i5++) {
            h hVar = hVarArr[i2 + i5];
            com.netease.nimlib.push.packet.a.b.c.c.a(((c) hVar.i()).h, 0, iArr, i4);
            int i6 = i4 + 8;
            com.netease.nimlib.push.packet.a.b.c.c.a(((c) hVar.j()).h, 0, iArr, i6);
            i4 = i6 + 8;
        }
        return new f() { // from class: com.netease.nimlib.push.packet.a.b.a.a.a.a.1
            @Override // com.netease.nimlib.push.packet.a.b.a.f
            public int a() {
                return i3;
            }

            @Override // com.netease.nimlib.push.packet.a.b.a.f
            public h a(int i7) {
                int[] a = com.netease.nimlib.push.packet.a.b.c.c.a();
                int[] a2 = com.netease.nimlib.push.packet.a.b.c.c.a();
                int i8 = 0;
                for (int i9 = 0; i9 < i3; i9++) {
                    int i10 = ((i9 ^ i7) - 1) >> 31;
                    for (int i11 = 0; i11 < 8; i11++) {
                        int i12 = a[i11];
                        int[] iArr2 = iArr;
                        a[i11] = i12 ^ (iArr2[i8 + i11] & i10);
                        a2[i11] = a2[i11] ^ (iArr2[(i8 + 8) + i11] & i10);
                    }
                    i8 += 16;
                }
                return a.this.a((e) new c(a), (e) new c(a2), false);
            }
        };
    }
}
