package u.aly;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/* compiled from: TBinaryProtocol.java */
/* loaded from: classes.dex */
public class cr extends cy {
    protected static final int a = -65536;
    protected static final int b = -2147418112;
    private static final dd h = new dd();
    protected boolean c;
    protected boolean d;
    protected int e;
    protected boolean f;
    private byte[] i;
    private byte[] j;
    private byte[] k;
    private byte[] l;
    private byte[] m;
    private byte[] n;
    private byte[] o;
    private byte[] p;

    @Override // u.aly.cy
    public void a() {
    }

    @Override // u.aly.cy
    public void a(dd ddVar) {
    }

    @Override // u.aly.cy
    public void b() {
    }

    @Override // u.aly.cy
    public void c() {
    }

    @Override // u.aly.cy
    public void e() {
    }

    @Override // u.aly.cy
    public void f() {
    }

    @Override // u.aly.cy
    public void g() {
    }

    @Override // u.aly.cy
    public void i() {
    }

    @Override // u.aly.cy
    public void k() {
    }

    @Override // u.aly.cy
    public void m() {
    }

    @Override // u.aly.cy
    public void o() {
    }

    @Override // u.aly.cy
    public void q() {
    }

    @Override // u.aly.cy
    public void s() {
    }

    /* compiled from: TBinaryProtocol.java */
    /* loaded from: classes.dex */
    public static class a implements da {
        protected boolean a;
        protected boolean b;
        protected int c;

        public a() {
            this(false, true);
        }

        public a(boolean z, boolean z2) {
            this(z, z2, 0);
        }

        public a(boolean z, boolean z2, int i) {
            this.a = false;
            this.b = true;
            this.a = z;
            this.b = z2;
            this.c = i;
        }

        @Override // u.aly.da
        public cy a(dm dmVar) {
            cr crVar = new cr(dmVar, this.a, this.b);
            int i = this.c;
            if (i != 0) {
                crVar.c(i);
            }
            return crVar;
        }
    }

    public cr(dm dmVar) {
        this(dmVar, false, true);
    }

    public cr(dm dmVar, boolean z, boolean z2) {
        super(dmVar);
        this.c = false;
        this.d = true;
        this.f = false;
        this.i = new byte[1];
        this.j = new byte[2];
        this.k = new byte[4];
        this.l = new byte[8];
        this.m = new byte[1];
        this.n = new byte[2];
        this.o = new byte[4];
        this.p = new byte[8];
        this.c = z;
        this.d = z2;
    }

    @Override // u.aly.cy
    public void a(cw cwVar) throws cf {
        if (this.d) {
            a(b | cwVar.b);
            a(cwVar.a);
            a(cwVar.c);
        } else {
            a(cwVar.a);
            a(cwVar.b);
            a(cwVar.c);
        }
    }

    @Override // u.aly.cy
    public void a(ct ctVar) throws cf {
        a(ctVar.b);
        a(ctVar.c);
    }

    @Override // u.aly.cy
    public void d() throws cf {
        a((byte) 0);
    }

    @Override // u.aly.cy
    public void a(cv cvVar) throws cf {
        a(cvVar.a);
        a(cvVar.b);
        a(cvVar.c);
    }

    @Override // u.aly.cy
    public void a(cu cuVar) throws cf {
        a(cuVar.a);
        a(cuVar.b);
    }

    @Override // u.aly.cy
    public void a(dc dcVar) throws cf {
        a(dcVar.a);
        a(dcVar.b);
    }

    @Override // u.aly.cy
    public void a(boolean z) throws cf {
        a(z ? (byte) 1 : (byte) 0);
    }

    @Override // u.aly.cy
    public void a(byte b2) throws cf {
        this.i[0] = b2;
        this.g.b(this.i, 0, 1);
    }

    @Override // u.aly.cy
    public void a(short s) throws cf {
        byte[] bArr = this.j;
        bArr[0] = (byte) ((s >> 8) & 255);
        bArr[1] = (byte) (s & 255);
        this.g.b(this.j, 0, 2);
    }

    @Override // u.aly.cy
    public void a(int i) throws cf {
        byte[] bArr = this.k;
        bArr[0] = (byte) ((i >> 24) & 255);
        bArr[1] = (byte) ((i >> 16) & 255);
        bArr[2] = (byte) ((i >> 8) & 255);
        bArr[3] = (byte) (i & 255);
        this.g.b(this.k, 0, 4);
    }

    @Override // u.aly.cy
    public void a(long j) throws cf {
        byte[] bArr = this.l;
        bArr[0] = (byte) ((j >> 56) & 255);
        bArr[1] = (byte) ((j >> 48) & 255);
        bArr[2] = (byte) ((j >> 40) & 255);
        bArr[3] = (byte) ((j >> 32) & 255);
        bArr[4] = (byte) ((j >> 24) & 255);
        bArr[5] = (byte) ((j >> 16) & 255);
        bArr[6] = (byte) ((j >> 8) & 255);
        bArr[7] = (byte) (j & 255);
        this.g.b(this.l, 0, 8);
    }

    @Override // u.aly.cy
    public void a(double d) throws cf {
        a(Double.doubleToLongBits(d));
    }

    @Override // u.aly.cy
    public void a(String str) throws cf {
        try {
            byte[] bytes = str.getBytes("UTF-8");
            a(bytes.length);
            this.g.b(bytes, 0, bytes.length);
        } catch (UnsupportedEncodingException unused) {
            throw new cf("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    @Override // u.aly.cy
    public void a(ByteBuffer byteBuffer) throws cf {
        int limit = byteBuffer.limit() - byteBuffer.position();
        a(limit);
        this.g.b(byteBuffer.array(), byteBuffer.position() + byteBuffer.arrayOffset(), limit);
    }

    @Override // u.aly.cy
    public cw h() throws cf {
        int w = w();
        if (w < 0) {
            if (((-65536) & w) != b) {
                throw new cz(4, "Bad version in readMessageBegin");
            }
            return new cw(z(), (byte) (w & 255), w());
        }
        if (this.c) {
            throw new cz(4, "Missing version in readMessageBegin, old client?");
        }
        return new cw(b(w), u(), w());
    }

    @Override // u.aly.cy
    public dd j() {
        return h;
    }

    @Override // u.aly.cy
    public ct l() throws cf {
        byte u2 = u();
        return new ct("", u2, u2 == 0 ? (short) 0 : v());
    }

    @Override // u.aly.cy
    public cv n() throws cf {
        return new cv(u(), u(), w());
    }

    @Override // u.aly.cy
    public cu p() throws cf {
        return new cu(u(), w());
    }

    @Override // u.aly.cy
    public dc r() throws cf {
        return new dc(u(), w());
    }

    @Override // u.aly.cy
    public boolean t() throws cf {
        return u() == 1;
    }

    @Override // u.aly.cy
    public byte u() throws cf {
        if (this.g.h() >= 1) {
            byte b2 = this.g.f()[this.g.g()];
            this.g.a(1);
            return b2;
        }
        a(this.m, 0, 1);
        return this.m[0];
    }

    @Override // u.aly.cy
    public short v() throws cf {
        byte[] bArr = this.n;
        int i = 0;
        if (this.g.h() >= 2) {
            bArr = this.g.f();
            i = this.g.g();
            this.g.a(2);
        } else {
            a(this.n, 0, 2);
        }
        return (short) ((bArr[i + 1] & 255) | ((bArr[i] & 255) << 8));
    }

    @Override // u.aly.cy
    public int w() throws cf {
        byte[] bArr = this.o;
        int i = 0;
        if (this.g.h() >= 4) {
            bArr = this.g.f();
            i = this.g.g();
            this.g.a(4);
        } else {
            a(this.o, 0, 4);
        }
        return (bArr[i + 3] & 255) | ((bArr[i] & 255) << 24) | ((bArr[i + 1] & 255) << 16) | ((bArr[i + 2] & 255) << 8);
    }

    @Override // u.aly.cy
    public long x() throws cf {
        byte[] bArr = this.p;
        int i = 0;
        if (this.g.h() >= 8) {
            bArr = this.g.f();
            i = this.g.g();
            this.g.a(8);
        } else {
            a(this.p, 0, 8);
        }
        return (bArr[i + 7] & 255) | ((bArr[i] & 255) << 56) | ((bArr[i + 1] & 255) << 48) | ((bArr[i + 2] & 255) << 40) | ((bArr[i + 3] & 255) << 32) | ((bArr[i + 4] & 255) << 24) | ((bArr[i + 5] & 255) << 16) | ((bArr[i + 6] & 255) << 8);
    }

    @Override // u.aly.cy
    public double y() throws cf {
        return Double.longBitsToDouble(x());
    }

    @Override // u.aly.cy
    public String z() throws cf {
        int w = w();
        if (this.g.h() >= w) {
            try {
                String str = new String(this.g.f(), this.g.g(), w, "UTF-8");
                this.g.a(w);
                return str;
            } catch (UnsupportedEncodingException unused) {
                throw new cf("JVM DOES NOT SUPPORT UTF-8");
            }
        }
        return b(w);
    }

    public String b(int i) throws cf {
        try {
            d(i);
            byte[] bArr = new byte[i];
            this.g.d(bArr, 0, i);
            return new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            throw new cf("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    @Override // u.aly.cy
    public ByteBuffer A() throws cf {
        int w = w();
        d(w);
        if (this.g.h() >= w) {
            ByteBuffer wrap = ByteBuffer.wrap(this.g.f(), this.g.g(), w);
            this.g.a(w);
            return wrap;
        }
        byte[] bArr = new byte[w];
        this.g.d(bArr, 0, w);
        return ByteBuffer.wrap(bArr);
    }

    private int a(byte[] bArr, int i, int i2) throws cf {
        d(i2);
        return this.g.d(bArr, i, i2);
    }

    public void c(int i) {
        this.e = i;
        this.f = true;
    }

    protected void d(int i) throws cf {
        if (i < 0) {
            throw new cz("Negative length: " + i);
        }
        if (this.f) {
            int i2 = this.e - i;
            this.e = i2;
            if (i2 >= 0) {
                return;
            }
            throw new cz("Message length exceeded: " + i);
        }
    }
}
