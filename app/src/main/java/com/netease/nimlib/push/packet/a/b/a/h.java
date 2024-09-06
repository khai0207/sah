package com.netease.nimlib.push.packet.a.b.a;

import java.math.BigInteger;
import java.util.Hashtable;

/* compiled from: ECPoint.java */
/* loaded from: classes.dex */
public abstract class h {
    protected static final e[] a = new e[0];
    protected d b;
    protected e c;
    protected e d;
    protected e[] e;
    protected boolean f;
    protected Hashtable g;

    protected abstract boolean a();

    public abstract h b(h hVar);

    public abstract h c(h hVar);

    protected abstract boolean r();

    public abstract h s();

    public abstract h t();

    protected static e[] a(d dVar) {
        int i = dVar == null ? 0 : dVar.i();
        if (dVar == null) {
            return a;
        }
        if (i == 0 || i == 5) {
            return a;
        }
        e a2 = dVar.a(c.b);
        if (i != 1 && i != 2) {
            if (i == 3) {
                return new e[]{a2, a2, a2};
            }
            if (i == 4) {
                return new e[]{a2, dVar.e()};
            }
            if (i != 6) {
                throw new IllegalArgumentException("unknown coordinate system");
            }
        }
        return new e[]{a2};
    }

    protected h(d dVar, e eVar, e eVar2) {
        this(dVar, eVar, eVar2, a(dVar));
    }

    protected h(d dVar, e eVar, e eVar2, e[] eVarArr) {
        this.g = null;
        this.b = dVar;
        this.c = eVar;
        this.d = eVar2;
        this.e = eVarArr;
    }

    protected boolean b() {
        BigInteger g;
        return c.b.equals(this.b.h()) || (g = this.b.g()) == null || b.a(this, g).o();
    }

    public d c() {
        return this.b;
    }

    protected int d() {
        d dVar = this.b;
        if (dVar == null) {
            return 0;
        }
        return dVar.i();
    }

    public e e() {
        l();
        return g();
    }

    public e f() {
        l();
        return h();
    }

    public e g() {
        return this.c;
    }

    public e h() {
        return this.d;
    }

    public e a(int i) {
        if (i >= 0) {
            e[] eVarArr = this.e;
            if (i < eVarArr.length) {
                return eVarArr[i];
            }
        }
        return null;
    }

    public final e i() {
        return this.c;
    }

    public final e j() {
        return this.d;
    }

    protected final e[] k() {
        return this.e;
    }

    protected void l() {
        if (!m()) {
            throw new IllegalStateException("point not in normal form");
        }
    }

    public boolean m() {
        int d = d();
        return d == 0 || d == 5 || o() || this.e[0].h();
    }

    public h n() {
        int d;
        if (o() || (d = d()) == 0 || d == 5) {
            return this;
        }
        e a2 = a(0);
        return a2.h() ? this : a(a2.e());
    }

    h a(e eVar) {
        int d = d();
        if (d != 1) {
            if (d == 2 || d == 3 || d == 4) {
                e d2 = eVar.d();
                return a(d2, d2.b(eVar));
            }
            if (d != 6) {
                throw new IllegalStateException("not a projective coordinate system");
            }
        }
        return a(eVar, eVar);
    }

    protected h a(e eVar, e eVar2) {
        return c().a(i().b(eVar), j().b(eVar2), this.f);
    }

    public boolean o() {
        if (this.c != null && this.d != null) {
            e[] eVarArr = this.e;
            if (eVarArr.length <= 0 || !eVarArr[0].i()) {
                return false;
            }
        }
        return true;
    }

    public boolean p() {
        return a(false, true);
    }

    boolean q() {
        return a(false, false);
    }

    boolean a(final boolean z, final boolean z2) {
        if (o()) {
            return true;
        }
        return !((q) c().a(this, "bc_validity", new o() { // from class: com.netease.nimlib.push.packet.a.b.a.h.1
            @Override // com.netease.nimlib.push.packet.a.b.a.o
            public p a(p pVar) {
                q qVar = pVar instanceof q ? (q) pVar : null;
                if (qVar == null) {
                    qVar = new q();
                }
                if (qVar.a()) {
                    return qVar;
                }
                if (!qVar.c()) {
                    if (!z && !h.this.a()) {
                        qVar.b();
                        return qVar;
                    }
                    qVar.d();
                }
                if (z2 && !qVar.e()) {
                    if (!h.this.b()) {
                        qVar.b();
                        return qVar;
                    }
                    qVar.f();
                }
                return qVar;
            }
        })).a();
    }

    public h b(e eVar) {
        return o() ? this : c().a(i().b(eVar), j(), k(), this.f);
    }

    public h c(e eVar) {
        return o() ? this : c().a(i(), j().b(eVar), k(), this.f);
    }

    public boolean a(h hVar) {
        h hVar2;
        if (hVar == null) {
            return false;
        }
        d c = c();
        d c2 = hVar.c();
        boolean z = c == null;
        boolean z2 = c2 == null;
        boolean o = o();
        boolean o2 = hVar.o();
        if (o || o2) {
            if (o && o2) {
                return z || z2 || c.a(c2);
            }
            return false;
        }
        if (!z || !z2) {
            if (!z) {
                if (z2) {
                    hVar2 = n();
                } else {
                    if (!c.a(c2)) {
                        return false;
                    }
                    h[] hVarArr = {this, c.a(hVar)};
                    c.a(hVarArr);
                    hVar2 = hVarArr[0];
                    hVar = hVarArr[1];
                }
                return hVar2.g().equals(hVar.g()) && hVar2.h().equals(hVar.h());
            }
            hVar = hVar.n();
        }
        hVar2 = this;
        if (hVar2.g().equals(hVar.g())) {
            return false;
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof h) {
            return a((h) obj);
        }
        return false;
    }

    public int hashCode() {
        d c = c();
        int hashCode = c == null ? 0 : c.hashCode() ^ (-1);
        if (o()) {
            return hashCode;
        }
        h n = n();
        return (hashCode ^ (n.g().hashCode() * 17)) ^ (n.h().hashCode() * 257);
    }

    public String toString() {
        if (o()) {
            return "INF";
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append('(');
        stringBuffer.append(i());
        stringBuffer.append(',');
        stringBuffer.append(j());
        for (int i = 0; i < this.e.length; i++) {
            stringBuffer.append(',');
            stringBuffer.append(this.e[i]);
        }
        stringBuffer.append(')');
        return stringBuffer.toString();
    }

    public byte[] a(boolean z) {
        if (o()) {
            return new byte[1];
        }
        h n = n();
        byte[] k = n.g().k();
        if (z) {
            byte[] bArr = new byte[k.length + 1];
            bArr[0] = (byte) (n.r() ? 3 : 2);
            System.arraycopy(k, 0, bArr, 1, k.length);
            return bArr;
        }
        byte[] k2 = n.h().k();
        byte[] bArr2 = new byte[k.length + k2.length + 1];
        bArr2[0] = 4;
        System.arraycopy(k, 0, bArr2, 1, k.length);
        System.arraycopy(k2, 0, bArr2, k.length + 1, k2.length);
        return bArr2;
    }

    public h b(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("'e' cannot be negative");
        }
        h hVar = this;
        while (true) {
            i--;
            if (i < 0) {
                return hVar;
            }
            hVar = hVar.t();
        }
    }

    public h d(h hVar) {
        return t().b(hVar);
    }

    public h u() {
        return d(this);
    }

    public h a(BigInteger bigInteger) {
        return c().j().a(this, bigInteger);
    }

    /* compiled from: ECPoint.java */
    /* loaded from: classes.dex */
    public static abstract class a extends h {
        protected a(d dVar, e eVar, e eVar2) {
            super(dVar, eVar, eVar2);
        }

        protected a(d dVar, e eVar, e eVar2, e[] eVarArr) {
            super(dVar, eVar, eVar2, eVarArr);
        }

        @Override // com.netease.nimlib.push.packet.a.b.a.h
        protected boolean r() {
            return f().j();
        }

        @Override // com.netease.nimlib.push.packet.a.b.a.h
        protected boolean a() {
            e eVar = this.c;
            e eVar2 = this.d;
            e e = this.b.e();
            e f = this.b.f();
            e d = eVar2.d();
            int d2 = d();
            if (d2 != 0) {
                if (d2 == 1) {
                    e eVar3 = this.e[0];
                    if (!eVar3.h()) {
                        e d3 = eVar3.d();
                        e b = eVar3.b(d3);
                        d = d.b(eVar3);
                        e = e.b(d3);
                        f = f.b(b);
                    }
                } else if (d2 == 2 || d2 == 3 || d2 == 4) {
                    e eVar4 = this.e[0];
                    if (!eVar4.h()) {
                        e d4 = eVar4.d();
                        e d5 = d4.d();
                        e b2 = d4.b(d5);
                        e = e.b(d5);
                        f = f.b(b2);
                    }
                } else {
                    throw new IllegalStateException("unsupported coordinate system");
                }
            }
            return d.equals(eVar.d().a(e).b(eVar).a(f));
        }

        @Override // com.netease.nimlib.push.packet.a.b.a.h
        public h c(h hVar) {
            return hVar.o() ? this : b(hVar.s());
        }
    }
}
