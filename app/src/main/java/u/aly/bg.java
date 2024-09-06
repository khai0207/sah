package u.aly;

import com.netease.nimlib.amazonaws.http.HttpHeader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* compiled from: Location.java */
/* loaded from: classes.dex */
public class bg implements Serializable, Cloneable, bz<bg, e> {
    public static final Map<e, cl> d;
    private static final dd e = new dd(HttpHeader.LOCATION);
    private static final ct f = new ct("lat", (byte) 4, 1);
    private static final ct g = new ct("lng", (byte) 4, 2);
    private static final ct h = new ct("ts", (byte) 10, 3);
    private static final Map<Class<? extends dg>, dh> i;
    private static final int j = 0;
    private static final int k = 1;
    private static final int l = 2;
    public double a;
    public double b;
    public long c;
    private byte m;

    public void m() throws cf {
    }

    static {
        HashMap hashMap = new HashMap();
        i = hashMap;
        hashMap.put(di.class, new b());
        i.put(dj.class, new d());
        EnumMap enumMap = new EnumMap(e.class);
        enumMap.put((EnumMap) e.LAT, (e) new cl("lat", (byte) 1, new cm((byte) 4)));
        enumMap.put((EnumMap) e.LNG, (e) new cl("lng", (byte) 1, new cm((byte) 4)));
        enumMap.put((EnumMap) e.TS, (e) new cl("ts", (byte) 1, new cm((byte) 10)));
        Map<e, cl> unmodifiableMap = Collections.unmodifiableMap(enumMap);
        d = unmodifiableMap;
        cl.a(bg.class, unmodifiableMap);
    }

    /* compiled from: Location.java */
    /* loaded from: classes.dex */
    public enum e implements cg {
        LAT(1, "lat"),
        LNG(2, "lng"),
        TS(3, "ts");

        private static final Map<String, e> d = new HashMap();
        private final short e;
        private final String f;

        static {
            Iterator it = EnumSet.allOf(e.class).iterator();
            while (it.hasNext()) {
                e eVar = (e) it.next();
                d.put(eVar.b(), eVar);
            }
        }

        public static e a(int i) {
            if (i == 1) {
                return LAT;
            }
            if (i == 2) {
                return LNG;
            }
            if (i != 3) {
                return null;
            }
            return TS;
        }

        public static e b(int i) {
            e a = a(i);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException("Field " + i + " doesn't exist!");
        }

        public static e a(String str) {
            return d.get(str);
        }

        e(short s, String str) {
            this.e = s;
            this.f = str;
        }

        @Override // u.aly.cg
        public short a() {
            return this.e;
        }

        @Override // u.aly.cg
        public String b() {
            return this.f;
        }
    }

    public bg() {
        this.m = (byte) 0;
    }

    public bg(double d2, double d3, long j2) {
        this();
        this.a = d2;
        a(true);
        this.b = d3;
        b(true);
        this.c = j2;
        c(true);
    }

    public bg(bg bgVar) {
        this.m = (byte) 0;
        this.m = bgVar.m;
        this.a = bgVar.a;
        this.b = bgVar.b;
        this.c = bgVar.c;
    }

    @Override // u.aly.bz
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public bg g() {
        return new bg(this);
    }

    @Override // u.aly.bz
    public void b() {
        a(false);
        this.a = 0.0d;
        b(false);
        this.b = 0.0d;
        c(false);
        this.c = 0L;
    }

    public double c() {
        return this.a;
    }

    public bg a(double d2) {
        this.a = d2;
        a(true);
        return this;
    }

    public void d() {
        this.m = bw.b(this.m, 0);
    }

    public boolean e() {
        return bw.a(this.m, 0);
    }

    public void a(boolean z) {
        this.m = bw.a(this.m, 0, z);
    }

    public double f() {
        return this.b;
    }

    public bg b(double d2) {
        this.b = d2;
        b(true);
        return this;
    }

    public void h() {
        this.m = bw.b(this.m, 1);
    }

    public boolean i() {
        return bw.a(this.m, 1);
    }

    public void b(boolean z) {
        this.m = bw.a(this.m, 1, z);
    }

    public long j() {
        return this.c;
    }

    public bg a(long j2) {
        this.c = j2;
        c(true);
        return this;
    }

    public void k() {
        this.m = bw.b(this.m, 2);
    }

    public boolean l() {
        return bw.a(this.m, 2);
    }

    public void c(boolean z) {
        this.m = bw.a(this.m, 2, z);
    }

    @Override // u.aly.bz
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public e b(int i2) {
        return e.a(i2);
    }

    @Override // u.aly.bz
    public void a(cy cyVar) throws cf {
        i.get(cyVar.D()).b().b(cyVar, this);
    }

    @Override // u.aly.bz
    public void b(cy cyVar) throws cf {
        i.get(cyVar.D()).b().a(cyVar, this);
    }

    public String toString() {
        return "Location(lat:" + this.a + ", lng:" + this.b + ", ts:" + this.c + ")";
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
            this.m = (byte) 0;
            a(new cs(new dk(objectInputStream)));
        } catch (cf e2) {
            throw new IOException(e2.getMessage());
        }
    }

    /* compiled from: Location.java */
    /* loaded from: classes.dex */
    private static class b implements dh {
        private b() {
        }

        @Override // u.aly.dh
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public a b() {
            return new a();
        }
    }

    /* compiled from: Location.java */
    /* loaded from: classes.dex */
    private static class a extends di<bg> {
        private a() {
        }

        @Override // u.aly.dg
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void b(cy cyVar, bg bgVar) throws cf {
            cyVar.j();
            while (true) {
                ct l = cyVar.l();
                if (l.b == 0) {
                    break;
                }
                short s = l.c;
                if (s != 1) {
                    if (s != 2) {
                        if (s == 3) {
                            if (l.b == 10) {
                                bgVar.c = cyVar.x();
                                bgVar.c(true);
                            } else {
                                db.a(cyVar, l.b);
                            }
                        } else {
                            db.a(cyVar, l.b);
                        }
                    } else if (l.b == 4) {
                        bgVar.b = cyVar.y();
                        bgVar.b(true);
                    } else {
                        db.a(cyVar, l.b);
                    }
                } else if (l.b == 4) {
                    bgVar.a = cyVar.y();
                    bgVar.a(true);
                } else {
                    db.a(cyVar, l.b);
                }
                cyVar.m();
            }
            cyVar.k();
            if (!bgVar.e()) {
                throw new cz("Required field 'lat' was not found in serialized data! Struct: " + toString());
            }
            if (!bgVar.i()) {
                throw new cz("Required field 'lng' was not found in serialized data! Struct: " + toString());
            }
            if (!bgVar.l()) {
                throw new cz("Required field 'ts' was not found in serialized data! Struct: " + toString());
            }
            bgVar.m();
        }

        @Override // u.aly.dg
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(cy cyVar, bg bgVar) throws cf {
            bgVar.m();
            cyVar.a(bg.e);
            cyVar.a(bg.f);
            cyVar.a(bgVar.a);
            cyVar.c();
            cyVar.a(bg.g);
            cyVar.a(bgVar.b);
            cyVar.c();
            cyVar.a(bg.h);
            cyVar.a(bgVar.c);
            cyVar.c();
            cyVar.d();
            cyVar.b();
        }
    }

    /* compiled from: Location.java */
    /* loaded from: classes.dex */
    private static class d implements dh {
        private d() {
        }

        @Override // u.aly.dh
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public c b() {
            return new c();
        }
    }

    /* compiled from: Location.java */
    /* loaded from: classes.dex */
    private static class c extends dj<bg> {
        private c() {
        }

        @Override // u.aly.dg
        public void a(cy cyVar, bg bgVar) throws cf {
            de deVar = (de) cyVar;
            deVar.a(bgVar.a);
            deVar.a(bgVar.b);
            deVar.a(bgVar.c);
        }

        @Override // u.aly.dg
        public void b(cy cyVar, bg bgVar) throws cf {
            de deVar = (de) cyVar;
            bgVar.a = deVar.y();
            bgVar.a(true);
            bgVar.b = deVar.y();
            bgVar.b(true);
            bgVar.c = deVar.x();
            bgVar.c(true);
        }
    }
}
