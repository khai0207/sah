package u.aly;

/* compiled from: TMemoryInputTransport.java */
/* loaded from: classes.dex */
public final class dl extends dm {
    private byte[] a;
    private int b;
    private int c;

    @Override // u.aly.dm
    public boolean a() {
        return true;
    }

    @Override // u.aly.dm
    public void b() throws dn {
    }

    @Override // u.aly.dm
    public void c() {
    }

    public dl() {
    }

    public dl(byte[] bArr) {
        a(bArr);
    }

    public dl(byte[] bArr, int i, int i2) {
        c(bArr, i, i2);
    }

    public void a(byte[] bArr) {
        c(bArr, 0, bArr.length);
    }

    public void c(byte[] bArr, int i, int i2) {
        this.a = bArr;
        this.b = i;
        this.c = i + i2;
    }

    public void e() {
        this.a = null;
    }

    @Override // u.aly.dm
    public int a(byte[] bArr, int i, int i2) throws dn {
        int h = h();
        if (i2 > h) {
            i2 = h;
        }
        if (i2 > 0) {
            System.arraycopy(this.a, this.b, bArr, i, i2);
            a(i2);
        }
        return i2;
    }

    @Override // u.aly.dm
    public void b(byte[] bArr, int i, int i2) throws dn {
        throw new UnsupportedOperationException("No writing allowed!");
    }

    @Override // u.aly.dm
    public byte[] f() {
        return this.a;
    }

    @Override // u.aly.dm
    public int g() {
        return this.b;
    }

    @Override // u.aly.dm
    public int h() {
        return this.c - this.b;
    }

    @Override // u.aly.dm
    public void a(int i) {
        this.b += i;
    }
}
