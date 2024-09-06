package u.aly;

import com.iflytek.cloud.SpeechConstant;
import com.netease.nimlib.amazonaws.services.s3.internal.Constants;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* compiled from: MiscInfo.java */
/* loaded from: classes.dex */
public class bh implements Serializable, Cloneable, bz<bh, e> {
    private static final int A = 3;
    public static final Map<e, cl> k;
    private static final dd l = new dd("MiscInfo");
    private static final ct m = new ct("time_zone", (byte) 8, 1);
    private static final ct n = new ct(SpeechConstant.LANGUAGE, (byte) 11, 2);
    private static final ct o = new ct("country", (byte) 11, 3);
    private static final ct p = new ct("latitude", (byte) 4, 4);
    private static final ct q = new ct("longitude", (byte) 4, 5);
    private static final ct r = new ct("carrier", (byte) 11, 6);
    private static final ct s = new ct("latency", (byte) 8, 7);
    private static final ct t = new ct("display_name", (byte) 11, 8);

    /* renamed from: u */
    private static final ct f68u = new ct("access_type", (byte) 8, 9);
    private static final ct v = new ct("access_subtype", (byte) 11, 10);
    private static final Map<Class<? extends dg>, dh> w;
    private static final int x = 0;
    private static final int y = 1;
    private static final int z = 2;
    private byte B;
    private e[] C;
    public int a;
    public String b;
    public String c;
    public double d;
    public double e;
    public String f;
    public int g;
    public String h;
    public ao i;
    public String j;

    public void H() throws cf {
    }

    static {
        HashMap hashMap = new HashMap();
        w = hashMap;
        hashMap.put(di.class, new b());
        w.put(dj.class, new d());
        EnumMap enumMap = new EnumMap(e.class);
        enumMap.put((EnumMap) e.TIME_ZONE, (e) new cl("time_zone", (byte) 2, new cm((byte) 8)));
        enumMap.put((EnumMap) e.LANGUAGE, (e) new cl(SpeechConstant.LANGUAGE, (byte) 2, new cm((byte) 11)));
        enumMap.put((EnumMap) e.COUNTRY, (e) new cl("country", (byte) 2, new cm((byte) 11)));
        enumMap.put((EnumMap) e.LATITUDE, (e) new cl("latitude", (byte) 2, new cm((byte) 4)));
        enumMap.put((EnumMap) e.LONGITUDE, (e) new cl("longitude", (byte) 2, new cm((byte) 4)));
        enumMap.put((EnumMap) e.CARRIER, (e) new cl("carrier", (byte) 2, new cm((byte) 11)));
        enumMap.put((EnumMap) e.LATENCY, (e) new cl("latency", (byte) 2, new cm((byte) 8)));
        enumMap.put((EnumMap) e.DISPLAY_NAME, (e) new cl("display_name", (byte) 2, new cm((byte) 11)));
        enumMap.put((EnumMap) e.ACCESS_TYPE, (e) new cl("access_type", (byte) 2, new ck(df.n, ao.class)));
        enumMap.put((EnumMap) e.ACCESS_SUBTYPE, (e) new cl("access_subtype", (byte) 2, new cm((byte) 11)));
        Map<e, cl> unmodifiableMap = Collections.unmodifiableMap(enumMap);
        k = unmodifiableMap;
        cl.a(bh.class, unmodifiableMap);
    }

    /* compiled from: MiscInfo.java */
    /* loaded from: classes.dex */
    public enum e implements cg {
        TIME_ZONE(1, "time_zone"),
        LANGUAGE(2, SpeechConstant.LANGUAGE),
        COUNTRY(3, "country"),
        LATITUDE(4, "latitude"),
        LONGITUDE(5, "longitude"),
        CARRIER(6, "carrier"),
        LATENCY(7, "latency"),
        DISPLAY_NAME(8, "display_name"),
        ACCESS_TYPE(9, "access_type"),
        ACCESS_SUBTYPE(10, "access_subtype");

        private static final Map<String, e> k = new HashMap();
        private final short l;
        private final String m;

        static {
            Iterator it = EnumSet.allOf(e.class).iterator();
            while (it.hasNext()) {
                e eVar = (e) it.next();
                k.put(eVar.b(), eVar);
            }
        }

        public static e a(int i) {
            switch (i) {
                case 1:
                    return TIME_ZONE;
                case 2:
                    return LANGUAGE;
                case 3:
                    return COUNTRY;
                case 4:
                    return LATITUDE;
                case 5:
                    return LONGITUDE;
                case 6:
                    return CARRIER;
                case 7:
                    return LATENCY;
                case 8:
                    return DISPLAY_NAME;
                case 9:
                    return ACCESS_TYPE;
                case 10:
                    return ACCESS_SUBTYPE;
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
            return k.get(str);
        }

        e(short s, String str) {
            this.l = s;
            this.m = str;
        }

        @Override // u.aly.cg
        public short a() {
            return this.l;
        }

        @Override // u.aly.cg
        public String b() {
            return this.m;
        }
    }

    public bh() {
        this.B = (byte) 0;
        this.C = new e[]{e.TIME_ZONE, e.LANGUAGE, e.COUNTRY, e.LATITUDE, e.LONGITUDE, e.CARRIER, e.LATENCY, e.DISPLAY_NAME, e.ACCESS_TYPE, e.ACCESS_SUBTYPE};
    }

    public bh(bh bhVar) {
        this.B = (byte) 0;
        this.C = new e[]{e.TIME_ZONE, e.LANGUAGE, e.COUNTRY, e.LATITUDE, e.LONGITUDE, e.CARRIER, e.LATENCY, e.DISPLAY_NAME, e.ACCESS_TYPE, e.ACCESS_SUBTYPE};
        this.B = bhVar.B;
        this.a = bhVar.a;
        if (bhVar.i()) {
            this.b = bhVar.b;
        }
        if (bhVar.l()) {
            this.c = bhVar.c;
        }
        this.d = bhVar.d;
        this.e = bhVar.e;
        if (bhVar.u()) {
            this.f = bhVar.f;
        }
        this.g = bhVar.g;
        if (bhVar.A()) {
            this.h = bhVar.h;
        }
        if (bhVar.D()) {
            this.i = bhVar.i;
        }
        if (bhVar.G()) {
            this.j = bhVar.j;
        }
    }

    @Override // u.aly.bz
    /* renamed from: a */
    public bh g() {
        return new bh(this);
    }

    @Override // u.aly.bz
    public void b() {
        a(false);
        this.a = 0;
        this.b = null;
        this.c = null;
        d(false);
        this.d = 0.0d;
        e(false);
        this.e = 0.0d;
        this.f = null;
        g(false);
        this.g = 0;
        this.h = null;
        this.i = null;
        this.j = null;
    }

    public int c() {
        return this.a;
    }

    public bh a(int i) {
        this.a = i;
        a(true);
        return this;
    }

    public void d() {
        this.B = bw.b(this.B, 0);
    }

    public boolean e() {
        return bw.a(this.B, 0);
    }

    public void a(boolean z2) {
        this.B = bw.a(this.B, 0, z2);
    }

    public String f() {
        return this.b;
    }

    public bh a(String str) {
        this.b = str;
        return this;
    }

    public void h() {
        this.b = null;
    }

    public boolean i() {
        return this.b != null;
    }

    public void b(boolean z2) {
        if (z2) {
            return;
        }
        this.b = null;
    }

    public String j() {
        return this.c;
    }

    public bh b(String str) {
        this.c = str;
        return this;
    }

    public void k() {
        this.c = null;
    }

    public boolean l() {
        return this.c != null;
    }

    public void c(boolean z2) {
        if (z2) {
            return;
        }
        this.c = null;
    }

    public double m() {
        return this.d;
    }

    public bh a(double d2) {
        this.d = d2;
        d(true);
        return this;
    }

    public void n() {
        this.B = bw.b(this.B, 1);
    }

    public boolean o() {
        return bw.a(this.B, 1);
    }

    public void d(boolean z2) {
        this.B = bw.a(this.B, 1, z2);
    }

    public double p() {
        return this.e;
    }

    public bh b(double d2) {
        this.e = d2;
        e(true);
        return this;
    }

    public void q() {
        this.B = bw.b(this.B, 2);
    }

    public boolean r() {
        return bw.a(this.B, 2);
    }

    public void e(boolean z2) {
        this.B = bw.a(this.B, 2, z2);
    }

    public String s() {
        return this.f;
    }

    public bh c(String str) {
        this.f = str;
        return this;
    }

    public void t() {
        this.f = null;
    }

    public boolean u() {
        return this.f != null;
    }

    public void f(boolean z2) {
        if (z2) {
            return;
        }
        this.f = null;
    }

    public int v() {
        return this.g;
    }

    public bh c(int i) {
        this.g = i;
        g(true);
        return this;
    }

    public void w() {
        this.B = bw.b(this.B, 3);
    }

    public boolean x() {
        return bw.a(this.B, 3);
    }

    public void g(boolean z2) {
        this.B = bw.a(this.B, 3, z2);
    }

    public String y() {
        return this.h;
    }

    public bh d(String str) {
        this.h = str;
        return this;
    }

    public void z() {
        this.h = null;
    }

    public boolean A() {
        return this.h != null;
    }

    public void h(boolean z2) {
        if (z2) {
            return;
        }
        this.h = null;
    }

    public ao B() {
        return this.i;
    }

    public bh a(ao aoVar) {
        this.i = aoVar;
        return this;
    }

    public void C() {
        this.i = null;
    }

    public boolean D() {
        return this.i != null;
    }

    public void i(boolean z2) {
        if (z2) {
            return;
        }
        this.i = null;
    }

    public String E() {
        return this.j;
    }

    public bh e(String str) {
        this.j = str;
        return this;
    }

    public void F() {
        this.j = null;
    }

    public boolean G() {
        return this.j != null;
    }

    public void j(boolean z2) {
        if (z2) {
            return;
        }
        this.j = null;
    }

    @Override // u.aly.bz
    /* renamed from: d */
    public e b(int i) {
        return e.a(i);
    }

    @Override // u.aly.bz
    public void a(cy cyVar) throws cf {
        w.get(cyVar.D()).b().b(cyVar, this);
    }

    @Override // u.aly.bz
    public void b(cy cyVar) throws cf {
        w.get(cyVar.D()).b().a(cyVar, this);
    }

    public String toString() {
        boolean z2;
        StringBuilder sb = new StringBuilder("MiscInfo(");
        boolean z3 = false;
        if (e()) {
            sb.append("time_zone:");
            sb.append(this.a);
            z2 = false;
        } else {
            z2 = true;
        }
        if (i()) {
            if (!z2) {
                sb.append(", ");
            }
            sb.append("language:");
            String str = this.b;
            if (str == null) {
                sb.append(Constants.NULL_VERSION_ID);
            } else {
                sb.append(str);
            }
            z2 = false;
        }
        if (l()) {
            if (!z2) {
                sb.append(", ");
            }
            sb.append("country:");
            String str2 = this.c;
            if (str2 == null) {
                sb.append(Constants.NULL_VERSION_ID);
            } else {
                sb.append(str2);
            }
            z2 = false;
        }
        if (o()) {
            if (!z2) {
                sb.append(", ");
            }
            sb.append("latitude:");
            sb.append(this.d);
            z2 = false;
        }
        if (r()) {
            if (!z2) {
                sb.append(", ");
            }
            sb.append("longitude:");
            sb.append(this.e);
            z2 = false;
        }
        if (u()) {
            if (!z2) {
                sb.append(", ");
            }
            sb.append("carrier:");
            String str3 = this.f;
            if (str3 == null) {
                sb.append(Constants.NULL_VERSION_ID);
            } else {
                sb.append(str3);
            }
            z2 = false;
        }
        if (x()) {
            if (!z2) {
                sb.append(", ");
            }
            sb.append("latency:");
            sb.append(this.g);
            z2 = false;
        }
        if (A()) {
            if (!z2) {
                sb.append(", ");
            }
            sb.append("display_name:");
            String str4 = this.h;
            if (str4 == null) {
                sb.append(Constants.NULL_VERSION_ID);
            } else {
                sb.append(str4);
            }
            z2 = false;
        }
        if (D()) {
            if (!z2) {
                sb.append(", ");
            }
            sb.append("access_type:");
            ao aoVar = this.i;
            if (aoVar == null) {
                sb.append(Constants.NULL_VERSION_ID);
            } else {
                sb.append(aoVar);
            }
        } else {
            z3 = z2;
        }
        if (G()) {
            if (!z3) {
                sb.append(", ");
            }
            sb.append("access_subtype:");
            String str5 = this.j;
            if (str5 == null) {
                sb.append(Constants.NULL_VERSION_ID);
            } else {
                sb.append(str5);
            }
        }
        sb.append(")");
        return sb.toString();
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
            this.B = (byte) 0;
            a(new cs(new dk(objectInputStream)));
        } catch (cf e2) {
            throw new IOException(e2.getMessage());
        }
    }

    /* compiled from: MiscInfo.java */
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

    /* compiled from: MiscInfo.java */
    /* loaded from: classes.dex */
    private static class a extends di<bh> {
        private a() {
        }

        /* synthetic */ a(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // u.aly.dg
        /* renamed from: a */
        public void b(cy cyVar, bh bhVar) throws cf {
            cyVar.j();
            while (true) {
                ct l = cyVar.l();
                if (l.b != 0) {
                    switch (l.c) {
                        case 1:
                            if (l.b == 8) {
                                bhVar.a = cyVar.w();
                                bhVar.a(true);
                                break;
                            } else {
                                db.a(cyVar, l.b);
                                break;
                            }
                        case 2:
                            if (l.b == 11) {
                                bhVar.b = cyVar.z();
                                bhVar.b(true);
                                break;
                            } else {
                                db.a(cyVar, l.b);
                                break;
                            }
                        case 3:
                            if (l.b == 11) {
                                bhVar.c = cyVar.z();
                                bhVar.c(true);
                                break;
                            } else {
                                db.a(cyVar, l.b);
                                break;
                            }
                        case 4:
                            if (l.b == 4) {
                                bhVar.d = cyVar.y();
                                bhVar.d(true);
                                break;
                            } else {
                                db.a(cyVar, l.b);
                                break;
                            }
                        case 5:
                            if (l.b == 4) {
                                bhVar.e = cyVar.y();
                                bhVar.e(true);
                                break;
                            } else {
                                db.a(cyVar, l.b);
                                break;
                            }
                        case 6:
                            if (l.b == 11) {
                                bhVar.f = cyVar.z();
                                bhVar.f(true);
                                break;
                            } else {
                                db.a(cyVar, l.b);
                                break;
                            }
                        case 7:
                            if (l.b == 8) {
                                bhVar.g = cyVar.w();
                                bhVar.g(true);
                                break;
                            } else {
                                db.a(cyVar, l.b);
                                break;
                            }
                        case 8:
                            if (l.b == 11) {
                                bhVar.h = cyVar.z();
                                bhVar.h(true);
                                break;
                            } else {
                                db.a(cyVar, l.b);
                                break;
                            }
                        case 9:
                            if (l.b == 8) {
                                bhVar.i = ao.a(cyVar.w());
                                bhVar.i(true);
                                break;
                            } else {
                                db.a(cyVar, l.b);
                                break;
                            }
                        case 10:
                            if (l.b == 11) {
                                bhVar.j = cyVar.z();
                                bhVar.j(true);
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
                    bhVar.H();
                    return;
                }
            }
        }

        @Override // u.aly.dg
        /* renamed from: b */
        public void a(cy cyVar, bh bhVar) throws cf {
            bhVar.H();
            cyVar.a(bh.l);
            if (bhVar.e()) {
                cyVar.a(bh.m);
                cyVar.a(bhVar.a);
                cyVar.c();
            }
            if (bhVar.b != null && bhVar.i()) {
                cyVar.a(bh.n);
                cyVar.a(bhVar.b);
                cyVar.c();
            }
            if (bhVar.c != null && bhVar.l()) {
                cyVar.a(bh.o);
                cyVar.a(bhVar.c);
                cyVar.c();
            }
            if (bhVar.o()) {
                cyVar.a(bh.p);
                cyVar.a(bhVar.d);
                cyVar.c();
            }
            if (bhVar.r()) {
                cyVar.a(bh.q);
                cyVar.a(bhVar.e);
                cyVar.c();
            }
            if (bhVar.f != null && bhVar.u()) {
                cyVar.a(bh.r);
                cyVar.a(bhVar.f);
                cyVar.c();
            }
            if (bhVar.x()) {
                cyVar.a(bh.s);
                cyVar.a(bhVar.g);
                cyVar.c();
            }
            if (bhVar.h != null && bhVar.A()) {
                cyVar.a(bh.t);
                cyVar.a(bhVar.h);
                cyVar.c();
            }
            if (bhVar.i != null && bhVar.D()) {
                cyVar.a(bh.f68u);
                cyVar.a(bhVar.i.a());
                cyVar.c();
            }
            if (bhVar.j != null && bhVar.G()) {
                cyVar.a(bh.v);
                cyVar.a(bhVar.j);
                cyVar.c();
            }
            cyVar.d();
            cyVar.b();
        }
    }

    /* compiled from: MiscInfo.java */
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

    /* compiled from: MiscInfo.java */
    /* loaded from: classes.dex */
    private static class c extends dj<bh> {
        private c() {
        }

        /* synthetic */ c(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // u.aly.dg
        public void a(cy cyVar, bh bhVar) throws cf {
            de deVar = (de) cyVar;
            BitSet bitSet = new BitSet();
            if (bhVar.e()) {
                bitSet.set(0);
            }
            if (bhVar.i()) {
                bitSet.set(1);
            }
            if (bhVar.l()) {
                bitSet.set(2);
            }
            if (bhVar.o()) {
                bitSet.set(3);
            }
            if (bhVar.r()) {
                bitSet.set(4);
            }
            if (bhVar.u()) {
                bitSet.set(5);
            }
            if (bhVar.x()) {
                bitSet.set(6);
            }
            if (bhVar.A()) {
                bitSet.set(7);
            }
            if (bhVar.D()) {
                bitSet.set(8);
            }
            if (bhVar.G()) {
                bitSet.set(9);
            }
            deVar.a(bitSet, 10);
            if (bhVar.e()) {
                deVar.a(bhVar.a);
            }
            if (bhVar.i()) {
                deVar.a(bhVar.b);
            }
            if (bhVar.l()) {
                deVar.a(bhVar.c);
            }
            if (bhVar.o()) {
                deVar.a(bhVar.d);
            }
            if (bhVar.r()) {
                deVar.a(bhVar.e);
            }
            if (bhVar.u()) {
                deVar.a(bhVar.f);
            }
            if (bhVar.x()) {
                deVar.a(bhVar.g);
            }
            if (bhVar.A()) {
                deVar.a(bhVar.h);
            }
            if (bhVar.D()) {
                deVar.a(bhVar.i.a());
            }
            if (bhVar.G()) {
                deVar.a(bhVar.j);
            }
        }

        @Override // u.aly.dg
        public void b(cy cyVar, bh bhVar) throws cf {
            de deVar = (de) cyVar;
            BitSet b = deVar.b(10);
            if (b.get(0)) {
                bhVar.a = deVar.w();
                bhVar.a(true);
            }
            if (b.get(1)) {
                bhVar.b = deVar.z();
                bhVar.b(true);
            }
            if (b.get(2)) {
                bhVar.c = deVar.z();
                bhVar.c(true);
            }
            if (b.get(3)) {
                bhVar.d = deVar.y();
                bhVar.d(true);
            }
            if (b.get(4)) {
                bhVar.e = deVar.y();
                bhVar.e(true);
            }
            if (b.get(5)) {
                bhVar.f = deVar.z();
                bhVar.f(true);
            }
            if (b.get(6)) {
                bhVar.g = deVar.w();
                bhVar.g(true);
            }
            if (b.get(7)) {
                bhVar.h = deVar.z();
                bhVar.h(true);
            }
            if (b.get(8)) {
                bhVar.i = ao.a(deVar.w());
                bhVar.i(true);
            }
            if (b.get(9)) {
                bhVar.j = deVar.z();
                bhVar.j(true);
            }
        }
    }
}
