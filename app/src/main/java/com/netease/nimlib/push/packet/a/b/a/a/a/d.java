package com.netease.nimlib.push.packet.a.b.a.a.a;

import com.netease.nimlib.push.packet.a.b.a.e;
import com.netease.nimlib.push.packet.a.b.a.h;

/* compiled from: SM2P256V1Point.java */
/* loaded from: classes.dex */
public class d extends h.a {
    public d(com.netease.nimlib.push.packet.a.b.a.d dVar, e eVar, e eVar2) {
        this(dVar, eVar, eVar2, false);
    }

    public d(com.netease.nimlib.push.packet.a.b.a.d dVar, e eVar, e eVar2, boolean z) {
        super(dVar, eVar, eVar2);
        if ((eVar == null) != (eVar2 == null)) {
            throw new IllegalArgumentException("Exactly one of the field elements is null");
        }
        this.f = z;
    }

    d(com.netease.nimlib.push.packet.a.b.a.d dVar, e eVar, e eVar2, e[] eVarArr, boolean z) {
        super(dVar, eVar, eVar2, eVarArr);
        this.f = z;
    }

    @Override // com.netease.nimlib.push.packet.a.b.a.h
    public h b(h hVar) {
        int[] iArr;
        int[] iArr2;
        int[] iArr3;
        int[] iArr4;
        if (o()) {
            return hVar;
        }
        if (hVar.o()) {
            return this;
        }
        if (this == hVar) {
            return t();
        }
        com.netease.nimlib.push.packet.a.b.a.d c = c();
        c cVar = (c) this.c;
        c cVar2 = (c) this.d;
        c cVar3 = (c) hVar.g();
        c cVar4 = (c) hVar.h();
        c cVar5 = (c) this.e[0];
        c cVar6 = (c) hVar.a(0);
        int[] b = com.netease.nimlib.push.packet.a.b.c.c.b();
        int[] a = com.netease.nimlib.push.packet.a.b.c.c.a();
        int[] a2 = com.netease.nimlib.push.packet.a.b.c.c.a();
        int[] a3 = com.netease.nimlib.push.packet.a.b.c.c.a();
        boolean h = cVar5.h();
        if (h) {
            iArr = cVar3.h;
            iArr2 = cVar4.h;
        } else {
            b.c(cVar5.h, a2);
            b.b(a2, cVar3.h, a);
            b.b(a2, cVar5.h, a2);
            b.b(a2, cVar4.h, a2);
            iArr = a;
            iArr2 = a2;
        }
        boolean h2 = cVar6.h();
        if (h2) {
            iArr3 = cVar.h;
            iArr4 = cVar2.h;
        } else {
            b.c(cVar6.h, a3);
            b.b(a3, cVar.h, b);
            b.b(a3, cVar6.h, a3);
            b.b(a3, cVar2.h, a3);
            iArr3 = b;
            iArr4 = a3;
        }
        int[] a4 = com.netease.nimlib.push.packet.a.b.c.c.a();
        b.d(iArr3, iArr, a4);
        b.d(iArr4, iArr2, a);
        if (com.netease.nimlib.push.packet.a.b.c.c.b(a4)) {
            if (com.netease.nimlib.push.packet.a.b.c.c.b(a)) {
                return t();
            }
            return c.c();
        }
        b.c(a4, a2);
        int[] a5 = com.netease.nimlib.push.packet.a.b.c.c.a();
        b.b(a2, a4, a5);
        b.b(a2, iArr3, a2);
        b.a(a5, a5);
        com.netease.nimlib.push.packet.a.b.c.c.c(iArr4, a5, b);
        b.a(com.netease.nimlib.push.packet.a.b.c.c.b(a2, a2, a5), a5);
        c cVar7 = new c(a3);
        b.c(a, cVar7.h);
        b.d(cVar7.h, a5, cVar7.h);
        c cVar8 = new c(a5);
        b.d(a2, cVar7.h, cVar8.h);
        b.c(cVar8.h, a, b);
        b.b(b, cVar8.h);
        c cVar9 = new c(a4);
        if (!h) {
            b.b(cVar9.h, cVar5.h, cVar9.h);
        }
        if (!h2) {
            b.b(cVar9.h, cVar6.h, cVar9.h);
        }
        return new d(c, cVar7, cVar8, new e[]{cVar9}, this.f);
    }

    @Override // com.netease.nimlib.push.packet.a.b.a.h
    public h t() {
        if (o()) {
            return this;
        }
        com.netease.nimlib.push.packet.a.b.a.d c = c();
        c cVar = (c) this.d;
        if (cVar.i()) {
            return c.c();
        }
        c cVar2 = (c) this.c;
        c cVar3 = (c) this.e[0];
        int[] a = com.netease.nimlib.push.packet.a.b.c.c.a();
        int[] a2 = com.netease.nimlib.push.packet.a.b.c.c.a();
        int[] a3 = com.netease.nimlib.push.packet.a.b.c.c.a();
        b.c(cVar.h, a3);
        int[] a4 = com.netease.nimlib.push.packet.a.b.c.c.a();
        b.c(a3, a4);
        boolean h = cVar3.h();
        int[] iArr = cVar3.h;
        if (!h) {
            b.c(cVar3.h, a2);
            iArr = a2;
        }
        b.d(cVar2.h, iArr, a);
        b.a(cVar2.h, iArr, a2);
        b.b(a2, a, a2);
        b.a(com.netease.nimlib.push.packet.a.b.c.c.b(a2, a2, a2), a2);
        b.b(a3, cVar2.h, a3);
        b.a(com.netease.nimlib.push.packet.a.b.c.b.b(8, a3, 2, 0), a3);
        b.a(com.netease.nimlib.push.packet.a.b.c.b.a(8, a4, 3, 0, a), a);
        c cVar4 = new c(a4);
        b.c(a2, cVar4.h);
        b.d(cVar4.h, a3, cVar4.h);
        b.d(cVar4.h, a3, cVar4.h);
        c cVar5 = new c(a3);
        b.d(a3, cVar4.h, cVar5.h);
        b.b(cVar5.h, a2, cVar5.h);
        b.d(cVar5.h, a, cVar5.h);
        c cVar6 = new c(a2);
        b.d(cVar.h, cVar6.h);
        if (!h) {
            b.b(cVar6.h, cVar3.h, cVar6.h);
        }
        return new d(c, cVar4, cVar5, new e[]{cVar6}, this.f);
    }

    @Override // com.netease.nimlib.push.packet.a.b.a.h
    public h d(h hVar) {
        if (this == hVar) {
            return u();
        }
        if (o()) {
            return hVar;
        }
        if (hVar.o()) {
            return t();
        }
        return this.d.i() ? hVar : t().b(hVar);
    }

    @Override // com.netease.nimlib.push.packet.a.b.a.h
    public h u() {
        return (o() || this.d.i()) ? this : t().b(this);
    }

    @Override // com.netease.nimlib.push.packet.a.b.a.h
    public h s() {
        return o() ? this : new d(this.b, this.c, this.d.c(), this.e, this.f);
    }
}
