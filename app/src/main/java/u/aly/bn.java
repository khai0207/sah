package u.aly;

import com.netease.nimlib.amazonaws.services.s3.internal.Constants;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: Session.java */
/* loaded from: classes.dex */
public class bn implements Serializable, Cloneable, bz<bn, e> {
    public static final Map<e, cl> h;
    private static final dd i = new dd("Session");
    private static final ct j = new ct("id", (byte) 11, 1);
    private static final ct k = new ct("start_time", (byte) 10, 2);
    private static final ct l = new ct("end_time", (byte) 10, 3);
    private static final ct m = new ct("duration", (byte) 10, 4);
    private static final ct n = new ct("pages", df.m, 5);
    private static final ct o = new ct("locations", df.m, 6);
    private static final ct p = new ct("traffic", (byte) 12, 7);
    private static final Map<Class<? extends dg>, dh> q;
    private static final int r = 0;
    private static final int s = 1;
    private static final int t = 2;
    public String a;
    public long b;
    public long c;
    public long d;
    public List<bi> e;
    public List<bg> f;
    public bo g;

    /* renamed from: u */
    private byte f69u;
    private e[] v;

    static {
        HashMap hashMap = new HashMap();
        q = hashMap;
        hashMap.put(di.class, new b());
        q.put(dj.class, new d());
        EnumMap enumMap = new EnumMap(e.class);
        enumMap.put((EnumMap) e.ID, (e) new cl("id", (byte) 1, new cm((byte) 11)));
        enumMap.put((EnumMap) e.START_TIME, (e) new cl("start_time", (byte) 1, new cm((byte) 10)));
        enumMap.put((EnumMap) e.END_TIME, (e) new cl("end_time", (byte) 1, new cm((byte) 10)));
        enumMap.put((EnumMap) e.DURATION, (e) new cl("duration", (byte) 1, new cm((byte) 10)));
        enumMap.put((EnumMap) e.PAGES, (e) new cl("pages", (byte) 2, new cn(df.m, new cq((byte) 12, bi.class))));
        enumMap.put((EnumMap) e.LOCATIONS, (e) new cl("locations", (byte) 2, new cn(df.m, new cq((byte) 12, bg.class))));
        enumMap.put((EnumMap) e.TRAFFIC, (e) new cl("traffic", (byte) 2, new cq((byte) 12, bo.class)));
        Map<e, cl> unmodifiableMap = Collections.unmodifiableMap(enumMap);
        h = unmodifiableMap;
        cl.a(bn.class, unmodifiableMap);
    }

    /* compiled from: Session.java */
    /* loaded from: classes.dex */
    public enum e implements cg {
        ID(1, "id"),
        START_TIME(2, "start_time"),
        END_TIME(3, "end_time"),
        DURATION(4, "duration"),
        PAGES(5, "pages"),
        LOCATIONS(6, "locations"),
        TRAFFIC(7, "traffic");

        private static final Map<String, e> h = new HashMap();
        private final short i;
        private final String j;

        static {
            Iterator it = EnumSet.allOf(e.class).iterator();
            while (it.hasNext()) {
                e eVar = (e) it.next();
                h.put(eVar.b(), eVar);
            }
        }

        public static e a(int i) {
            switch (i) {
                case 1:
                    return ID;
                case 2:
                    return START_TIME;
                case 3:
                    return END_TIME;
                case 4:
                    return DURATION;
                case 5:
                    return PAGES;
                case 6:
                    return LOCATIONS;
                case 7:
                    return TRAFFIC;
                default:
                    return null;
            }
        }

        public static e b(int i) {
            e a = a(i);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException("Field " + i + " doesn't exist!");
        }

        public static e a(String str) {
            return h.get(str);
        }

        e(short s, String str) {
            this.i = s;
            this.j = str;
        }

        @Override // u.aly.cg
        public short a() {
            return this.i;
        }

        @Override // u.aly.cg
        public String b() {
            return this.j;
        }
    }

    public bn() {
        this.f69u = (byte) 0;
        this.v = new e[]{e.PAGES, e.LOCATIONS, e.TRAFFIC};
    }

    public bn(String str, long j2, long j3, long j4) {
        this();
        this.a = str;
        this.b = j2;
        b(true);
        this.c = j3;
        c(true);
        this.d = j4;
        d(true);
    }

    public bn(bn bnVar) {
        this.f69u = (byte) 0;
        this.v = new e[]{e.PAGES, e.LOCATIONS, e.TRAFFIC};
        this.f69u = bnVar.f69u;
        if (bnVar.e()) {
            this.a = bnVar.a;
        }
        this.b = bnVar.b;
        this.c = bnVar.c;
        this.d = bnVar.d;
        if (bnVar.t()) {
            ArrayList arrayList = new ArrayList();
            Iterator<bi> it = bnVar.e.iterator();
            while (it.hasNext()) {
                arrayList.add(new bi(it.next()));
            }
            this.e = arrayList;
        }
        if (bnVar.y()) {
            ArrayList arrayList2 = new ArrayList();
            Iterator<bg> it2 = bnVar.f.iterator();
            while (it2.hasNext()) {
                arrayList2.add(new bg(it2.next()));
            }
            this.f = arrayList2;
        }
        if (bnVar.B()) {
            this.g = new bo(bnVar.g);
        }
    }

    @Override // u.aly.bz
    /* renamed from: a */
    public bn g() {
        return new bn(this);
    }

    @Override // u.aly.bz
    public void b() {
        this.a = null;
        b(false);
        this.b = 0L;
        c(false);
        this.c = 0L;
        d(false);
        this.d = 0L;
        this.e = null;
        this.f = null;
        this.g = null;
    }

    public String c() {
        return this.a;
    }

    public bn a(String str) {
        this.a = str;
        return this;
    }

    public void d() {
        this.a = null;
    }

    public boolean e() {
        return this.a != null;
    }

    public void a(boolean z) {
        if (z) {
            return;
        }
        this.a = null;
    }

    public long f() {
        return this.b;
    }

    public bn a(long j2) {
        this.b = j2;
        b(true);
        return this;
    }

    public void h() {
        this.f69u = bw.b(this.f69u, 0);
    }

    public boolean i() {
        return bw.a(this.f69u, 0);
    }

    public void b(boolean z) {
        this.f69u = bw.a(this.f69u, 0, z);
    }

    public long j() {
        return this.c;
    }

    public bn b(long j2) {
        this.c = j2;
        c(true);
        return this;
    }

    public void k() {
        this.f69u = bw.b(this.f69u, 1);
    }

    public boolean l() {
        return bw.a(this.f69u, 1);
    }

    public void c(boolean z) {
        this.f69u = bw.a(this.f69u, 1, z);
    }

    public long m() {
        return this.d;
    }

    public bn c(long j2) {
        this.d = j2;
        d(true);
        return this;
    }

    public void n() {
        this.f69u = bw.b(this.f69u, 2);
    }

    public boolean o() {
        return bw.a(this.f69u, 2);
    }

    public void d(boolean z) {
        this.f69u = bw.a(this.f69u, 2, z);
    }

    public int p() {
        List<bi> list = this.e;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public Iterator<bi> q() {
        List<bi> list = this.e;
        if (list == null) {
            return null;
        }
        return list.iterator();
    }

    public void a(bi biVar) {
        if (this.e == null) {
            this.e = new ArrayList();
        }
        this.e.add(biVar);
    }

    public List<bi> r() {
        return this.e;
    }

    public bn a(List<bi> list) {
        this.e = list;
        return this;
    }

    public void s() {
        this.e = null;
    }

    public boolean t() {
        return this.e != null;
    }

    public void e(boolean z) {
        if (z) {
            return;
        }
        this.e = null;
    }

    public int u() {
        List<bg> list = this.f;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public Iterator<bg> v() {
        List<bg> list = this.f;
        if (list == null) {
            return null;
        }
        return list.iterator();
    }

    public void a(bg bgVar) {
        if (this.f == null) {
            this.f = new ArrayList();
        }
        this.f.add(bgVar);
    }

    public List<bg> w() {
        return this.f;
    }

    public bn b(List<bg> list) {
        this.f = list;
        return this;
    }

    public void x() {
        this.f = null;
    }

    public boolean y() {
        return this.f != null;
    }

    public void f(boolean z) {
        if (z) {
            return;
        }
        this.f = null;
    }

    public bo z() {
        return this.g;
    }

    public bn a(bo boVar) {
        this.g = boVar;
        return this;
    }

    public void A() {
        this.g = null;
    }

    public boolean B() {
        return this.g != null;
    }

    public void g(boolean z) {
        if (z) {
            return;
        }
        this.g = null;
    }

    @Override // u.aly.bz
    /* renamed from: a */
    public e b(int i2) {
        return e.a(i2);
    }

    @Override // u.aly.bz
    public void a(cy cyVar) throws cf {
        q.get(cyVar.D()).b().b(cyVar, this);
    }

    @Override // u.aly.bz
    public void b(cy cyVar) throws cf {
        q.get(cyVar.D()).b().a(cyVar, this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Session(");
        sb.append("id:");
        String str = this.a;
        if (str == null) {
            sb.append(Constants.NULL_VERSION_ID);
        } else {
            sb.append(str);
        }
        sb.append(", ");
        sb.append("start_time:");
        sb.append(this.b);
        sb.append(", ");
        sb.append("end_time:");
        sb.append(this.c);
        sb.append(", ");
        sb.append("duration:");
        sb.append(this.d);
        if (t()) {
            sb.append(", ");
            sb.append("pages:");
            List<bi> list = this.e;
            if (list == null) {
                sb.append(Constants.NULL_VERSION_ID);
            } else {
                sb.append(list);
            }
        }
        if (y()) {
            sb.append(", ");
            sb.append("locations:");
            List<bg> list2 = this.f;
            if (list2 == null) {
                sb.append(Constants.NULL_VERSION_ID);
            } else {
                sb.append(list2);
            }
        }
        if (B()) {
            sb.append(", ");
            sb.append("traffic:");
            bo boVar = this.g;
            if (boVar == null) {
                sb.append(Constants.NULL_VERSION_ID);
            } else {
                sb.append(boVar);
            }
        }
        sb.append(")");
        return sb.toString();
    }

    public void C() throws cf {
        if (this.a == null) {
            throw new cz("Required field 'id' was not present! Struct: " + toString());
        }
        bo boVar = this.g;
        if (boVar != null) {
            boVar.j();
        }
    }

    private void a(ObjectOutputStream objectOutputStream) throws IOException {
        try {
            b(new cs(new dk(objectOutputStream)));
        } catch (cf e2) {
            throw new IOException(e2.getMessage());
        }
    }

    private void a(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        try {
            this.f69u = (byte) 0;
            a(new cs(new dk(objectInputStream)));
        } catch (cf e2) {
            throw new IOException(e2.getMessage());
        }
    }

    /* compiled from: Session.java */
    /* loaded from: classes.dex */
    private static class b implements dh {
        private b() {
        }

        /* synthetic */ b(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // u.aly.dh
        /* renamed from: a */
        public a b() {
            return new a();
        }
    }

    /* compiled from: Session.java */
    /* loaded from: classes.dex */
    private static class a extends di<bn> {
        private a() {
        }

        /* synthetic */ a(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // u.aly.dg
        /* renamed from: a */
        public void b(cy cyVar, bn bnVar) throws cf {
            cyVar.j();
            while (true) {
                ct l = cyVar.l();
                if (l.b != 0) {
                    int i = 0;
                    switch (l.c) {
                        case 1:
                            if (l.b == 11) {
                                bnVar.a = cyVar.z();
                                bnVar.a(true);
                                break;
                            } else {
                                db.a(cyVar, l.b);
                                break;
                            }
                        case 2:
                            if (l.b == 10) {
                                bnVar.b = cyVar.x();
                                bnVar.b(true);
                                break;
                            } else {
                                db.a(cyVar, l.b);
                                break;
                            }
                        case 3:
                            if (l.b == 10) {
                                bnVar.c = cyVar.x();
                                bnVar.c(true);
                                break;
                            } else {
                                db.a(cyVar, l.b);
                                break;
                            }
                        case 4:
                            if (l.b == 10) {
                                bnVar.d = cyVar.x();
                                bnVar.d(true);
                                break;
                            } else {
                                db.a(cyVar, l.b);
                                break;
                            }
                        case 5:
                            if (l.b == 15) {
                                cu p = cyVar.p();
                                bnVar.e = new ArrayList(p.b);
                                while (i < p.b) {
                                    bi biVar = new bi();
                                    biVar.a(cyVar);
                                    bnVar.e.add(biVar);
                                    i++;
                                }
                                cyVar.q();
                                bnVar.e(true);
                                break;
                            } else {
                                db.a(cyVar, l.b);
                                break;
                            }
                        case 6:
                            if (l.b == 15) {
                                cu p2 = cyVar.p();
                                bnVar.f = new ArrayList(p2.b);
                                while (i < p2.b) {
                                    bg bgVar = new bg();
                                    bgVar.a(cyVar);
                                    bnVar.f.add(bgVar);
                                    i++;
                                }
                                cyVar.q();
                                bnVar.f(true);
                                break;
                            } else {
                                db.a(cyVar, l.b);
                                break;
                            }
                        case 7:
                            if (l.b == 12) {
                                bnVar.g = new bo();
                                bnVar.g.a(cyVar);
                                bnVar.g(true);
                                break;
                            } else {
                                db.a(cyVar, l.b);
                                break;
                            }
                        default:
                            db.a(cyVar, l.b);
                            break;
                    }
                    cyVar.m();
                } else {
                    cyVar.k();
                    if (!bnVar.i()) {
                        throw new cz("Required field 'start_time' was not found in serialized data! Struct: " + toString());
                    }
                    if (!bnVar.l()) {
                        throw new cz("Required field 'end_time' was not found in serialized data! Struct: " + toString());
                    }
                    if (!bnVar.o()) {
                        throw new cz("Required field 'duration' was not found in serialized data! Struct: " + toString());
                    }
                    bnVar.C();
                    return;
                }
            }
        }

        @Override // u.aly.dg
        /* renamed from: b */
        public void a(cy cyVar, bn bnVar) throws cf {
            bnVar.C();
            cyVar.a(bn.i);
            if (bnVar.a != null) {
                cyVar.a(bn.j);
                cyVar.a(bnVar.a);
                cyVar.c();
            }
            cyVar.a(bn.k);
            cyVar.a(bnVar.b);
            cyVar.c();
            cyVar.a(bn.l);
            cyVar.a(bnVar.c);
            cyVar.c();
            cyVar.a(bn.m);
            cyVar.a(bnVar.d);
            cyVar.c();
            if (bnVar.e != null && bnVar.t()) {
                cyVar.a(bn.n);
                cyVar.a(new cu((byte) 12, bnVar.e.size()));
                Iterator<bi> it = bnVar.e.iterator();
                while (it.hasNext()) {
                    it.next().b(cyVar);
                }
                cyVar.f();
                cyVar.c();
            }
            if (bnVar.f != null && bnVar.y()) {
                cyVar.a(bn.o);
                cyVar.a(new cu((byte) 12, bnVar.f.size()));
                Iterator<bg> it2 = bnVar.f.iterator();
                while (it2.hasNext()) {
                    it2.next().b(cyVar);
                }
                cyVar.f();
                cyVar.c();
            }
            if (bnVar.g != null && bnVar.B()) {
                cyVar.a(bn.p);
                bnVar.g.b(cyVar);
                cyVar.c();
            }
            cyVar.d();
            cyVar.b();
        }
    }

    /* compiled from: Session.java */
    /* loaded from: classes.dex */
    private static class d implements dh {
        private d() {
        }

        /* synthetic */ d(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // u.aly.dh
        /* renamed from: a */
        public c b() {
            return new c();
        }
    }

    /* compiled from: Session.java */
    /* loaded from: classes.dex */
    private static class c extends dj<bn> {
        private c() {
        }

        /* synthetic */ c(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // u.aly.dg
        public void a(cy cyVar, bn bnVar) throws cf {
            de deVar = (de) cyVar;
            deVar.a(bnVar.a);
            deVar.a(bnVar.b);
            deVar.a(bnVar.c);
            deVar.a(bnVar.d);
            BitSet bitSet = new BitSet();
            if (bnVar.t()) {
                bitSet.set(0);
            }
            if (bnVar.y()) {
                bitSet.set(1);
            }
            if (bnVar.B()) {
                bitSet.set(2);
            }
            deVar.a(bitSet, 3);
            if (bnVar.t()) {
                deVar.a(bnVar.e.size());
                Iterator<bi> it = bnVar.e.iterator();
                while (it.hasNext()) {
                    it.next().b(deVar);
                }
            }
            if (bnVar.y()) {
                deVar.a(bnVar.f.size());
                Iterator<bg> it2 = bnVar.f.iterator();
                while (it2.hasNext()) {
                    it2.next().b(deVar);
                }
            }
            if (bnVar.B()) {
                bnVar.g.b(deVar);
            }
        }

        @Override // u.aly.dg
        public void b(cy cyVar, bn bnVar) throws cf {
            de deVar = (de) cyVar;
            bnVar.a = deVar.z();
            bnVar.a(true);
            bnVar.b = deVar.x();
            bnVar.b(true);
            bnVar.c = deVar.x();
            bnVar.c(true);
            bnVar.d = deVar.x();
            bnVar.d(true);
            BitSet b = deVar.b(3);
            if (b.get(0)) {
                cu cuVar = new cu((byte) 12, deVar.w());
                bnVar.e = new ArrayList(cuVar.b);
                for (int i = 0; i < cuVar.b; i++) {
                    bi biVar = new bi();
                    biVar.a(deVar);
                    bnVar.e.add(biVar);
                }
                bnVar.e(true);
            }
            if (b.get(1)) {
                cu cuVar2 = new cu((byte) 12, deVar.w());
                bnVar.f = new ArrayList(cuVar2.b);
                for (int i2 = 0; i2 < cuVar2.b; i2++) {
                    bg bgVar = new bg();
                    bgVar.a(deVar);
                    bnVar.f.add(bgVar);
                }
                bnVar.f(true);
            }
            if (b.get(2)) {
                bnVar.g = new bo();
                bnVar.g.a(deVar);
                bnVar.g(true);
            }
        }
    }
}
