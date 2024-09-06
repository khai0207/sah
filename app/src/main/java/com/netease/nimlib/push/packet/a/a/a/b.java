package com.netease.nimlib.push.packet.a.a.a;

import com.netease.nimlib.push.packet.a.c.d;
import com.netease.nimlib.push.packet.a.c.e;

/* compiled from: SM3Digest.java */
/* loaded from: classes.dex */
public class b extends a {
    private static final int[] e = new int[64];
    private int[] a;
    private int[] b;
    private int c;
    private int[] d;

    private int a(int i) {
        return (i ^ ((i << 9) | (i >>> 23))) ^ ((i << 17) | (i >>> 15));
    }

    private int a(int i, int i2, int i3) {
        return (i ^ i2) ^ i3;
    }

    private int b(int i) {
        return (i ^ ((i << 15) | (i >>> 17))) ^ ((i << 23) | (i >>> 9));
    }

    private int b(int i, int i2, int i3) {
        return (i & i3) | (i & i2) | (i2 & i3);
    }

    private int c(int i, int i2, int i3) {
        return (i ^ i2) ^ i3;
    }

    private int d(int i, int i2, int i3) {
        return ((i ^ (-1)) & i3) | (i2 & i);
    }

    @Override // com.netease.nimlib.push.packet.a.a.e
    public int a() {
        return 32;
    }

    static {
        int i;
        int i2 = 0;
        while (true) {
            if (i2 >= 16) {
                break;
            }
            e[i2] = (2043430169 >>> (32 - i2)) | (2043430169 << i2);
            i2++;
        }
        for (i = 16; i < 64; i++) {
            int i3 = i % 32;
            e[i] = (2055708042 >>> (32 - i3)) | (2055708042 << i3);
        }
    }

    public b() {
        this.a = new int[8];
        this.b = new int[16];
        this.d = new int[68];
        c();
    }

    public b(b bVar) {
        super(bVar);
        this.a = new int[8];
        this.b = new int[16];
        this.d = new int[68];
        a(bVar);
    }

    private void a(b bVar) {
        int[] iArr = bVar.a;
        int[] iArr2 = this.a;
        System.arraycopy(iArr, 0, iArr2, 0, iArr2.length);
        int[] iArr3 = bVar.b;
        int[] iArr4 = this.b;
        System.arraycopy(iArr3, 0, iArr4, 0, iArr4.length);
        this.c = bVar.c;
    }

    @Override // com.netease.nimlib.push.packet.a.c.d
    public d e() {
        return new b(this);
    }

    @Override // com.netease.nimlib.push.packet.a.c.d
    public void a(d dVar) {
        b bVar = (b) dVar;
        super.a((a) bVar);
        a(bVar);
    }

    @Override // com.netease.nimlib.push.packet.a.a.a.a
    public void c() {
        super.c();
        int[] iArr = this.a;
        iArr[0] = 1937774191;
        iArr[1] = 1226093241;
        iArr[2] = 388252375;
        iArr[3] = -628488704;
        iArr[4] = -1452330820;
        iArr[5] = 372324522;
        iArr[6] = -477237683;
        iArr[7] = -1325724082;
        this.c = 0;
    }

    @Override // com.netease.nimlib.push.packet.a.a.e
    public int a(byte[] bArr, int i) {
        b();
        e.a(this.a, bArr, i);
        c();
        return 32;
    }

    @Override // com.netease.nimlib.push.packet.a.a.a.a
    protected void b(byte[] bArr, int i) {
        int i2 = (bArr[i] & 255) << 24;
        int i3 = i + 1;
        int i4 = i2 | ((bArr[i3] & 255) << 16);
        int i5 = i3 + 1;
        int i6 = (bArr[i5 + 1] & 255) | i4 | ((bArr[i5] & 255) << 8);
        int[] iArr = this.b;
        int i7 = this.c;
        iArr[i7] = i6;
        int i8 = i7 + 1;
        this.c = i8;
        if (i8 >= 16) {
            d();
        }
    }

    @Override // com.netease.nimlib.push.packet.a.a.a.a
    protected void a(long j) {
        int i = this.c;
        if (i > 14) {
            this.b[i] = 0;
            this.c = i + 1;
            d();
        }
        while (true) {
            int i2 = this.c;
            if (i2 < 14) {
                this.b[i2] = 0;
                this.c = i2 + 1;
            } else {
                int[] iArr = this.b;
                int i3 = i2 + 1;
                this.c = i3;
                iArr[i2] = (int) (j >>> 32);
                this.c = i3 + 1;
                iArr[i3] = (int) j;
                return;
            }
        }
    }

    @Override // com.netease.nimlib.push.packet.a.a.a.a
    protected void d() {
        int i;
        int i2 = 0;
        while (true) {
            if (i2 >= 16) {
                break;
            }
            this.d[i2] = this.b[i2];
            i2++;
        }
        for (int i3 = 16; i3 < 68; i3++) {
            int[] iArr = this.d;
            int i4 = iArr[i3 - 3];
            int i5 = iArr[i3 - 13];
            iArr[i3] = (b(((i4 >>> 17) | (i4 << 15)) ^ (iArr[i3 - 16] ^ iArr[i3 - 9])) ^ ((i5 >>> 25) | (i5 << 7))) ^ this.d[i3 - 6];
        }
        int[] iArr2 = this.a;
        int i6 = iArr2[0];
        int i7 = iArr2[1];
        int i8 = iArr2[2];
        int i9 = iArr2[3];
        int i10 = iArr2[4];
        int i11 = iArr2[5];
        int i12 = iArr2[6];
        int i13 = iArr2[7];
        int i14 = i12;
        int i15 = 0;
        for (i = 16; i15 < i; i = 16) {
            int i16 = (i6 << 12) | (i6 >>> 20);
            int i17 = i16 + i10 + e[i15];
            int i18 = (i17 << 7) | (i17 >>> 25);
            int[] iArr3 = this.d;
            int i19 = iArr3[i15];
            int i20 = i19 ^ iArr3[i15 + 4];
            int a = a(i6, i7, i8) + i9;
            int c = c(i10, i11, i14) + i13 + i18 + i19;
            int i21 = (i7 << 9) | (i7 >>> 23);
            int i22 = (i11 << 19) | (i11 >>> 13);
            i15++;
            i11 = i10;
            i10 = a(c);
            i9 = i8;
            i8 = i21;
            i13 = i14;
            i14 = i22;
            i7 = i6;
            i6 = a + (i18 ^ i16) + i20;
        }
        int i23 = i13;
        int i24 = i10;
        int i25 = i14;
        int i26 = i9;
        int i27 = i8;
        int i28 = i7;
        int i29 = i6;
        int i30 = 16;
        while (i30 < 64) {
            int i31 = (i29 << 12) | (i29 >>> 20);
            int i32 = i31 + i24 + e[i30];
            int i33 = (i32 << 7) | (i32 >>> 25);
            int[] iArr4 = this.d;
            int i34 = iArr4[i30];
            int i35 = i34 ^ iArr4[i30 + 4];
            int b = b(i29, i28, i27) + i26;
            int d = d(i24, i11, i25) + i23 + i33 + i34;
            int i36 = (i11 << 19) | (i11 >>> 13);
            i30++;
            i11 = i24;
            i24 = a(d);
            i26 = i27;
            i27 = (i28 >>> 23) | (i28 << 9);
            i28 = i29;
            i29 = b + (i33 ^ i31) + i35;
            i23 = i25;
            i25 = i36;
        }
        int[] iArr5 = this.a;
        iArr5[0] = i29 ^ iArr5[0];
        iArr5[1] = iArr5[1] ^ i28;
        iArr5[2] = iArr5[2] ^ i27;
        iArr5[3] = iArr5[3] ^ i26;
        iArr5[4] = iArr5[4] ^ i24;
        iArr5[5] = iArr5[5] ^ i11;
        iArr5[6] = i25 ^ iArr5[6];
        iArr5[7] = iArr5[7] ^ i23;
        this.c = 0;
    }
}
