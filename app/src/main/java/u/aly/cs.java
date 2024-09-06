package u.aly;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/* compiled from: TCompactProtocol.java */
/* loaded from: classes.dex */
public class cs extends cy {
    private static final dd d = new dd("");
    private static final ct e = new ct("", (byte) 0, 0);
    private static final byte[] f;
    private static final byte h = -126;
    private static final byte i = 1;
    private static final byte j = 31;
    private static final byte k = -32;
    private static final int l = 5;
    byte[] a;
    byte[] b;
    byte[] c;
    private bx m;
    private short n;
    private ct o;
    private Boolean p;
    private final long q;
    private byte[] r;

    private int c(int i2) {
        return (i2 >> 31) ^ (i2 << 1);
    }

    private long c(long j2) {
        return (j2 >> 63) ^ (j2 << 1);
    }

    private boolean c(byte b2) {
        int i2 = b2 & df.m;
        return i2 == 1 || i2 == 2;
    }

    private long d(long j2) {
        return (-(j2 & 1)) ^ (j2 >>> 1);
    }

    private int g(int i2) {
        return (-(i2 & 1)) ^ (i2 >>> 1);
    }

    @Override // u.aly.cy
    public void a() throws cf {
    }

    @Override // u.aly.cy
    public void c() throws cf {
    }

    @Override // u.aly.cy
    public void e() throws cf {
    }

    @Override // u.aly.cy
    public void f() throws cf {
    }

    @Override // u.aly.cy
    public void g() throws cf {
    }

    @Override // u.aly.cy
    public void i() throws cf {
    }

    @Override // u.aly.cy
    public void m() throws cf {
    }

    @Override // u.aly.cy
    public void o() throws cf {
    }

    @Override // u.aly.cy
    public void q() throws cf {
    }

    @Override // u.aly.cy
    public void s() throws cf {
    }

    static {
        f = r0;
        byte[] bArr = {0, 0, 1, 3, 7, 0, 4, 0, 5, 0, 6, 8, 12, 11, 10, 9};
    }

    /* compiled from: TCompactProtocol.java */
    /* loaded from: classes.dex */
    public static class a implements da {
        private final long a;

        public a() {
            this.a = -1L;
        }

        public a(int i) {
            this.a = i;
        }

        @Override // u.aly.da
        public cy a(dm dmVar) {
            return new cs(dmVar, this.a);
        }
    }

    /* compiled from: TCompactProtocol.java */
    /* loaded from: classes.dex */
    private static class b {
        public static final byte a = 1;
        public static final byte b = 2;
        public static final byte c = 3;
        public static final byte d = 4;
        public static final byte e = 5;
        public static final byte f = 6;
        public static final byte g = 7;
        public static final byte h = 8;
        public static final byte i = 9;
        public static final byte j = 10;
        public static final byte k = 11;
        public static final byte l = 12;

        private b() {
        }
    }

    public cs(dm dmVar, long j2) {
        super(dmVar);
        this.m = new bx(15);
        this.n = (short) 0;
        this.o = null;
        this.p = null;
        this.a = new byte[5];
        this.b = new byte[10];
        this.r = new byte[1];
        this.c = new byte[1];
        this.q = j2;
    }

    public cs(dm dmVar) {
        this(dmVar, -1L);
    }

    @Override // u.aly.cy
    public void B() {
        this.m.c();
        this.n = (short) 0;
    }

    @Override // u.aly.cy
    public void a(cw cwVar) throws cf {
        b(h);
        d(((cwVar.b << 5) & (-32)) | 1);
        b(cwVar.c);
        a(cwVar.a);
    }

    @Override // u.aly.cy
    public void a(dd ddVar) throws cf {
        this.m.a(this.n);
        this.n = (short) 0;
    }

    @Override // u.aly.cy
    public void b() throws cf {
        this.n = this.m.a();
    }

    @Override // u.aly.cy
    public void a(ct ctVar) throws cf {
        if (ctVar.b == 2) {
            this.o = ctVar;
        } else {
            a(ctVar, (byte) -1);
        }
    }

    private void a(ct ctVar, byte b2) throws cf {
        if (b2 == -1) {
            b2 = e(ctVar.b);
        }
        if (ctVar.c > this.n && ctVar.c - this.n <= 15) {
            d(b2 | ((ctVar.c - this.n) << 4));
        } else {
            b(b2);
            a(ctVar.c);
        }
        this.n = ctVar.c;
    }

    @Override // u.aly.cy
    public void d() throws cf {
        b((byte) 0);
    }

    @Override // u.aly.cy
    public void a(cv cvVar) throws cf {
        if (cvVar.c == 0) {
            d(0);
            return;
        }
        b(cvVar.c);
        d(e(cvVar.b) | (e(cvVar.a) << 4));
    }

    @Override // u.aly.cy
    public void a(cu cuVar) throws cf {
        a(cuVar.a, cuVar.b);
    }

    @Override // u.aly.cy
    public void a(dc dcVar) throws cf {
        a(dcVar.a, dcVar.b);
    }

    @Override // u.aly.cy
    public void a(boolean z) throws cf {
        ct ctVar = this.o;
        if (ctVar != null) {
            a(ctVar, z ? (byte) 1 : (byte) 2);
            this.o = null;
        } else {
            b(z ? (byte) 1 : (byte) 2);
        }
    }

    @Override // u.aly.cy
    public void a(byte b2) throws cf {
        b(b2);
    }

    @Override // u.aly.cy
    public void a(short s) throws cf {
        b(c((int) s));
    }

    @Override // u.aly.cy
    public void a(int i2) throws cf {
        b(c(i2));
    }

    @Override // u.aly.cy
    public void a(long j2) throws cf {
        b(c(j2));
    }

    @Override // u.aly.cy
    public void a(double d2) throws cf {
        byte[] bArr = {0, 0, 0, 0, 0, 0, 0, 0};
        a(Double.doubleToLongBits(d2), bArr, 0);
        this.g.b(bArr);
    }

    @Override // u.aly.cy
    public void a(String str) throws cf {
        try {
            byte[] bytes = str.getBytes("UTF-8");
            a(bytes, 0, bytes.length);
        } catch (UnsupportedEncodingException unused) {
            throw new cf("UTF-8 not supported!");
        }
    }

    @Override // u.aly.cy
    public void a(ByteBuffer byteBuffer) throws cf {
        a(byteBuffer.array(), byteBuffer.position() + byteBuffer.arrayOffset(), byteBuffer.limit() - byteBuffer.position());
    }

    private void a(byte[] bArr, int i2, int i3) throws cf {
        b(i3);
        this.g.b(bArr, i2, i3);
    }

    protected void a(byte b2, int i2) throws cf {
        if (i2 <= 14) {
            d(e(b2) | (i2 << 4));
        } else {
            d(e(b2) | 240);
            b(i2);
        }
    }

    private void b(int i2) throws cf {
        int i3 = 0;
        while ((i2 & com.alipay.sdk.m.j.a.g) != 0) {
            this.a[i3] = (byte) ((i2 & 127) | 128);
            i2 >>>= 7;
            i3++;
        }
        this.a[i3] = (byte) i2;
        this.g.b(this.a, 0, i3 + 1);
    }

    private void b(long j2) throws cf {
        int i2 = 0;
        while (((-128) & j2) != 0) {
            this.b[i2] = (byte) ((127 & j2) | 128);
            j2 >>>= 7;
            i2++;
        }
        this.b[i2] = (byte) j2;
        this.g.b(this.b, 0, i2 + 1);
    }

    private void a(long j2, byte[] bArr, int i2) {
        bArr[i2 + 0] = (byte) (j2 & 255);
        bArr[i2 + 1] = (byte) ((j2 >> 8) & 255);
        bArr[i2 + 2] = (byte) ((j2 >> 16) & 255);
        bArr[i2 + 3] = (byte) ((j2 >> 24) & 255);
        bArr[i2 + 4] = (byte) ((j2 >> 32) & 255);
        bArr[i2 + 5] = (byte) ((j2 >> 40) & 255);
        bArr[i2 + 6] = (byte) ((j2 >> 48) & 255);
        bArr[i2 + 7] = (byte) ((j2 >> 56) & 255);
    }

    private void b(byte b2) throws cf {
        this.r[0] = b2;
        this.g.b(this.r);
    }

    private void d(int i2) throws cf {
        b((byte) i2);
    }

    @Override // u.aly.cy
    public cw h() throws cf {
        byte u2 = u();
        if (u2 != -126) {
            throw new cz("Expected protocol id " + Integer.toHexString(-126) + " but got " + Integer.toHexString(u2));
        }
        byte u3 = u();
        byte b2 = (byte) (u3 & j);
        if (b2 != 1) {
            throw new cz("Expected version 1 but got " + ((int) b2));
        }
        return new cw(z(), (byte) ((u3 >> 5) & 3), E());
    }

    @Override // u.aly.cy
    public dd j() throws cf {
        this.m.a(this.n);
        this.n = (short) 0;
        return d;
    }

    @Override // u.aly.cy
    public void k() throws cf {
        this.n = this.m.a();
    }

    @Override // u.aly.cy
    public ct l() throws cf {
        short s;
        byte u2 = u();
        if (u2 == 0) {
            return e;
        }
        short s2 = (short) ((u2 & 240) >> 4);
        if (s2 == 0) {
            s = v();
        } else {
            s = (short) (this.n + s2);
        }
        byte b2 = (byte) (u2 & df.m);
        ct ctVar = new ct("", d(b2), s);
        if (c(u2)) {
            this.p = b2 == 1 ? Boolean.TRUE : Boolean.FALSE;
        }
        this.n = ctVar.c;
        return ctVar;
    }

    @Override // u.aly.cy
    public cv n() throws cf {
        int E = E();
        byte u2 = E == 0 ? (byte) 0 : u();
        return new cv(d((byte) (u2 >> 4)), d((byte) (u2 & df.m)), E);
    }

    @Override // u.aly.cy
    public cu p() throws cf {
        byte u2 = u();
        int i2 = (u2 >> 4) & 15;
        if (i2 == 15) {
            i2 = E();
        }
        return new cu(d(u2), i2);
    }

    @Override // u.aly.cy
    public dc r() throws cf {
        return new dc(p());
    }

    @Override // u.aly.cy
    public boolean t() throws cf {
        Boolean bool = this.p;
        if (bool == null) {
            return u() == 1;
        }
        boolean booleanValue = bool.booleanValue();
        this.p = null;
        return booleanValue;
    }

    @Override // u.aly.cy
    public byte u() throws cf {
        if (this.g.h() > 0) {
            byte b2 = this.g.f()[this.g.g()];
            this.g.a(1);
            return b2;
        }
        this.g.d(this.c, 0, 1);
        return this.c[0];
    }

    @Override // u.aly.cy
    public short v() throws cf {
        return (short) g(E());
    }

    @Override // u.aly.cy
    public int w() throws cf {
        return g(E());
    }

    @Override // u.aly.cy
    public long x() throws cf {
        return d(F());
    }

    @Override // u.aly.cy
    public double y() throws cf {
        byte[] bArr = new byte[8];
        this.g.d(bArr, 0, 8);
        return Double.longBitsToDouble(a(bArr));
    }

    @Override // u.aly.cy
    public String z() throws cf {
        int E = E();
        f(E);
        if (E == 0) {
            return "";
        }
        try {
            if (this.g.h() >= E) {
                String str = new String(this.g.f(), this.g.g(), E, "UTF-8");
                this.g.a(E);
                return str;
            }
            return new String(e(E), "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            throw new cf("UTF-8 not supported!");
        }
    }

    @Override // u.aly.cy
    public ByteBuffer A() throws cf {
        int E = E();
        f(E);
        if (E == 0) {
            return ByteBuffer.wrap(new byte[0]);
        }
        byte[] bArr = new byte[E];
        this.g.d(bArr, 0, E);
        return ByteBuffer.wrap(bArr);
    }

    private byte[] e(int i2) throws cf {
        if (i2 == 0) {
            return new byte[0];
        }
        byte[] bArr = new byte[i2];
        this.g.d(bArr, 0, i2);
        return bArr;
    }

    private void f(int i2) throws cz {
        if (i2 < 0) {
            throw new cz("Negative length: " + i2);
        }
        long j2 = this.q;
        if (j2 == -1 || i2 <= j2) {
            return;
        }
        throw new cz("Length exceeded max allowed: " + i2);
    }

    private int E() throws cf {
        int i2 = 0;
        if (this.g.h() >= 5) {
            byte[] f2 = this.g.f();
            int g = this.g.g();
            int i3 = 0;
            int i4 = 0;
            while (true) {
                byte b2 = f2[g + i2];
                i3 |= (b2 & Byte.MAX_VALUE) << i4;
                if ((b2 & 128) != 128) {
                    this.g.a(i2 + 1);
                    return i3;
                }
                i4 += 7;
                i2++;
            }
        } else {
            int i5 = 0;
            while (true) {
                byte u2 = u();
                i2 |= (u2 & Byte.MAX_VALUE) << i5;
                if ((u2 & 128) != 128) {
                    return i2;
                }
                i5 += 7;
            }
        }
    }

    private long F() throws cf {
        int i2 = 0;
        long j2 = 0;
        if (this.g.h() >= 10) {
            byte[] f2 = this.g.f();
            int g = this.g.g();
            long j3 = 0;
            int i3 = 0;
            while (true) {
                j3 |= (r4 & Byte.MAX_VALUE) << i3;
                if ((f2[g + i2] & 128) != 128) {
                    this.g.a(i2 + 1);
                    return j3;
                }
                i3 += 7;
                i2++;
            }
        } else {
            while (true) {
                j2 |= (r0 & Byte.MAX_VALUE) << i2;
                if ((u() & 128) != 128) {
                    return j2;
                }
                i2 += 7;
            }
        }
    }

    private long a(byte[] bArr) {
        return ((bArr[7] & 255) << 56) | ((bArr[6] & 255) << 48) | ((bArr[5] & 255) << 40) | ((bArr[4] & 255) << 32) | ((bArr[3] & 255) << 24) | ((bArr[2] & 255) << 16) | ((bArr[1] & 255) << 8) | (255 & bArr[0]);
    }

    private byte d(byte b2) throws cz {
        byte b3 = (byte) (b2 & df.m);
        switch (b3) {
            case 0:
                return (byte) 0;
            case 1:
            case 2:
                return (byte) 2;
            case 3:
                return (byte) 3;
            case 4:
                return (byte) 6;
            case 5:
                return (byte) 8;
            case 6:
                return (byte) 10;
            case 7:
                return (byte) 4;
            case 8:
                return (byte) 11;
            case 9:
                return df.m;
            case 10:
                return df.l;
            case 11:
                return df.k;
            case 12:
                return (byte) 12;
            default:
                throw new cz("don't know what type: " + ((int) b3));
        }
    }

    private byte e(byte b2) {
        return f[b2];
    }
}
