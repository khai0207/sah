package com.netease.nimlib.push.packet.a.b.a;

import java.math.BigInteger;

/* compiled from: WNafUtil.java */
/* loaded from: classes.dex */
public abstract class t {
    private static final int[] a = {13, 41, 121, 337, 897, 2305};
    private static final byte[] b = new byte[0];
    private static final int[] c = new int[0];
    private static final h[] d = new h[0];

    public static int[] a(BigInteger bigInteger) {
        if ((bigInteger.bitLength() >>> 16) != 0) {
            throw new IllegalArgumentException("'k' must have bitlength < 2^16");
        }
        if (bigInteger.signum() == 0) {
            return c;
        }
        BigInteger add = bigInteger.shiftLeft(1).add(bigInteger);
        int bitLength = add.bitLength();
        int i = bitLength >> 1;
        int[] iArr = new int[i];
        BigInteger xor = add.xor(bigInteger);
        int i2 = bitLength - 1;
        int i3 = 0;
        int i4 = 0;
        int i5 = 1;
        while (i5 < i2) {
            if (xor.testBit(i5)) {
                iArr[i3] = i4 | ((bigInteger.testBit(i5) ? -1 : 1) << 16);
                i5++;
                i3++;
                i4 = 1;
            } else {
                i4++;
            }
            i5++;
        }
        int i6 = i3 + 1;
        iArr[i3] = 65536 | i4;
        return i > i6 ? a(iArr, i6) : iArr;
    }

    public static int[] a(int i, BigInteger bigInteger) {
        if (i == 2) {
            return a(bigInteger);
        }
        if (i < 2 || i > 16) {
            throw new IllegalArgumentException("'width' must be in the range [2, 16]");
        }
        if ((bigInteger.bitLength() >>> 16) != 0) {
            throw new IllegalArgumentException("'k' must have bitlength < 2^16");
        }
        if (bigInteger.signum() == 0) {
            return c;
        }
        int bitLength = (bigInteger.bitLength() / i) + 1;
        int[] iArr = new int[bitLength];
        int i2 = 1 << i;
        int i3 = i2 - 1;
        int i4 = i2 >>> 1;
        int i5 = 0;
        int i6 = 0;
        boolean z = false;
        while (i5 <= bigInteger.bitLength()) {
            if (bigInteger.testBit(i5) == z) {
                i5++;
            } else {
                bigInteger = bigInteger.shiftRight(i5);
                int intValue = bigInteger.intValue() & i3;
                if (z) {
                    intValue++;
                }
                z = (intValue & i4) != 0;
                if (z) {
                    intValue -= i2;
                }
                if (i6 > 0) {
                    i5--;
                }
                iArr[i6] = i5 | (intValue << 16);
                i5 = i;
                i6++;
            }
        }
        return bitLength > i6 ? a(iArr, i6) : iArr;
    }

    public static byte[] b(BigInteger bigInteger) {
        if (bigInteger.signum() == 0) {
            return b;
        }
        BigInteger add = bigInteger.shiftLeft(1).add(bigInteger);
        int bitLength = add.bitLength() - 1;
        byte[] bArr = new byte[bitLength];
        BigInteger xor = add.xor(bigInteger);
        int i = 1;
        while (i < bitLength) {
            if (xor.testBit(i)) {
                bArr[i - 1] = (byte) (bigInteger.testBit(i) ? -1 : 1);
                i++;
            }
            i++;
        }
        bArr[bitLength - 1] = 1;
        return bArr;
    }

    public static byte[] b(int i, BigInteger bigInteger) {
        if (i == 2) {
            return b(bigInteger);
        }
        if (i < 2 || i > 8) {
            throw new IllegalArgumentException("'width' must be in the range [2, 8]");
        }
        if (bigInteger.signum() == 0) {
            return b;
        }
        int bitLength = bigInteger.bitLength() + 1;
        byte[] bArr = new byte[bitLength];
        int i2 = 1 << i;
        int i3 = i2 - 1;
        int i4 = i2 >>> 1;
        int i5 = 0;
        int i6 = 0;
        boolean z = false;
        while (i5 <= bigInteger.bitLength()) {
            if (bigInteger.testBit(i5) == z) {
                i5++;
            } else {
                bigInteger = bigInteger.shiftRight(i5);
                int intValue = bigInteger.intValue() & i3;
                if (z) {
                    intValue++;
                }
                z = (intValue & i4) != 0;
                if (z) {
                    intValue -= i2;
                }
                if (i6 > 0) {
                    i5--;
                }
                int i7 = i6 + i5;
                bArr[i7] = (byte) intValue;
                i6 = i7 + 1;
                i5 = i;
            }
        }
        return bitLength > i6 ? a(bArr, i6) : bArr;
    }

    public static s a(h hVar) {
        return a(hVar.c().a(hVar, "bc_wnaf"));
    }

    public static s a(p pVar) {
        if (pVar instanceof s) {
            return (s) pVar;
        }
        return null;
    }

    public static int a(int i) {
        return a(i, a);
    }

    public static int a(int i, int[] iArr) {
        int i2 = 0;
        while (i2 < iArr.length && i >= iArr[i2]) {
            i2++;
        }
        return i2 + 2;
    }

    public static h a(h hVar, int i, final boolean z, final i iVar) {
        d c2 = hVar.c();
        final s a2 = a(hVar, i, z);
        h a3 = iVar.a(hVar);
        c2.a(a3, "bc_wnaf", new o() { // from class: com.netease.nimlib.push.packet.a.b.a.t.1
            @Override // com.netease.nimlib.push.packet.a.b.a.o
            public p a(p pVar) {
                s sVar = new s();
                h c3 = s.this.c();
                if (c3 != null) {
                    sVar.a(iVar.a(c3));
                }
                h[] a4 = s.this.a();
                int length = a4.length;
                h[] hVarArr = new h[length];
                for (int i2 = 0; i2 < a4.length; i2++) {
                    hVarArr[i2] = iVar.a(a4[i2]);
                }
                sVar.a(hVarArr);
                if (z) {
                    h[] hVarArr2 = new h[length];
                    for (int i3 = 0; i3 < length; i3++) {
                        hVarArr2[i3] = hVarArr[i3].s();
                    }
                    sVar.b(hVarArr2);
                }
                return sVar;
            }
        });
        return a3;
    }

    public static s a(final h hVar, final int i, final boolean z) {
        final d c2 = hVar.c();
        return (s) c2.a(hVar, "bc_wnaf", new o() { // from class: com.netease.nimlib.push.packet.a.b.a.t.2
            /* JADX WARN: Removed duplicated region for block: B:40:0x00c5 A[LOOP:0: B:39:0x00c3->B:40:0x00c5, LOOP_END] */
            /* JADX WARN: Removed duplicated region for block: B:51:0x00ea A[LOOP:1: B:50:0x00e8->B:51:0x00ea, LOOP_END] */
            @Override // com.netease.nimlib.push.packet.a.b.a.o
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public com.netease.nimlib.push.packet.a.b.a.p a(com.netease.nimlib.push.packet.a.b.a.p r12) {
                /*
                    Method dump skipped, instructions count: 260
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.netease.nimlib.push.packet.a.b.a.t.AnonymousClass2.a(com.netease.nimlib.push.packet.a.b.a.p):com.netease.nimlib.push.packet.a.b.a.p");
            }

            private boolean a(s sVar, int i2, boolean z2) {
                return sVar != null && a(sVar.a(), i2) && (!z2 || a(sVar.b(), i2));
            }

            private boolean a(h[] hVarArr, int i2) {
                return hVarArr != null && hVarArr.length >= i2;
            }
        });
    }

    private static byte[] a(byte[] bArr, int i) {
        byte[] bArr2 = new byte[i];
        System.arraycopy(bArr, 0, bArr2, 0, i);
        return bArr2;
    }

    private static int[] a(int[] iArr, int i) {
        int[] iArr2 = new int[i];
        System.arraycopy(iArr, 0, iArr2, 0, i);
        return iArr2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static h[] b(h[] hVarArr, int i) {
        h[] hVarArr2 = new h[i];
        System.arraycopy(hVarArr, 0, hVarArr2, 0, hVarArr.length);
        return hVarArr2;
    }
}
