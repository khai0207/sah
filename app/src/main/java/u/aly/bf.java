package u.aly;

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

/* compiled from: Latent.java */
/* loaded from: classes.dex */
public class bf implements Serializable, Cloneable, bz<bf, e> {
    public static final Map<e, cl> c;
    private static final dd d = new dd("Latent");
    private static final ct e = new ct("latency", (byte) 8, 1);
    private static final ct f = new ct("interval", (byte) 10, 2);
    private static final Map<Class<? extends dg>, dh> g;
    private static final int h = 0;
    private static final int i = 1;
    public int a;
    public long b;
    private byte j;

    public void j() throws cf {
    }

    static {
        HashMap hashMap = new HashMap();
        g = hashMap;
        hashMap.put(di.class, new b());
        g.put(dj.class, new d());
        EnumMap enumMap = new EnumMap(e.class);
        enumMap.put((EnumMap) e.LATENCY, (e) new cl("latency", (byte) 1, new cm((byte) 8)));
        enumMap.put((EnumMap) e.INTERVAL, (e) new cl("interval", (byte) 1, new cm((byte) 10)));
        Map<e, cl> unmodifiableMap = Collections.unmodifiableMap(enumMap);
        c = unmodifiableMap;
        cl.a(bf.class, unmodifiableMap);
    }

    /* compiled from: Latent.java */
    /* loaded from: classes.dex */
    public enum e implements cg {
        LATENCY(1, "latency"),
        INTERVAL(2, "interval");

        private static final Map<String, e> c = new HashMap();
        private final short d;
        private final String e;

        static {
            Iterator it = EnumSet.allOf(e.class).iterator();
            while (it.hasNext()) {
                e eVar = (e) it.next();
                c.put(eVar.b(), eVar);
            }
        }

        public static e a(int i) {
            if (i == 1) {
                return LATENCY;
            }
            if (i != 2) {
                return null;
            }
            return INTERVAL;
        }

        public static e b(int i) {
            e a = a(i);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException("Field " + i + " doesn't exist!");
        }

        public static e a(String str) {
            return c.get(str);
        }

        e(short s, String str) {
            this.d = s;
            this.e = str;
        }

        @Override // u.aly.cg
        public short a() {
            return this.d;
        }

        @Override // u.aly.cg
        public String b() {
            return this.e;
        }
    }

    public bf() {
        this.j = (byte) 0;
    }

    public bf(int i2, long j) {
        this();
        this.a = i2;
        a(true);
        this.b = j;
        b(true);
    }

    public bf(bf bfVar) {
        this.j = (byte) 0;
        this.j = bfVar.j;
        this.a = bfVar.a;
        this.b = bfVar.b;
    }

    @Override // u.aly.bz
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public bf g() {
        return new bf(this);
    }

    @Override // u.aly.bz
    public void b() {
        a(false);
        this.a = 0;
        b(false);
        this.b = 0L;
    }

    public int c() {
        return this.a;
    }

    public bf a(int i2) {
        this.a = i2;
        a(true);
        return this;
    }

    public void d() {
        this.j = bw.b(this.j, 0);
    }

    public boolean e() {
        return bw.a(this.j, 0);
    }

    public void a(boolean z) {
        this.j = bw.a(this.j, 0, z);
    }

    public long f() {
        return this.b;
    }

    public bf a(long j) {
        this.b = j;
        b(true);
        return this;
    }

    public void h() {
        this.j = bw.b(this.j, 1);
    }

    public boolean i() {
        return bw.a(this.j, 1);
    }

    public void b(boolean z) {
        this.j = bw.a(this.j, 1, z);
    }

    @Override // u.aly.bz
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public e b(int i2) {
        return e.a(i2);
    }

    @Override // u.aly.bz
    public void a(cy cyVar) throws cf {
        g.get(cyVar.D()).b().b(cyVar, this);
    }

    @Override // u.aly.bz
    public void b(cy cyVar) throws cf {
        g.get(cyVar.D()).b().a(cyVar, this);
    }

    public String toString() {
        return "Latent(latency:" + this.a + ", interval:" + this.b + ")";
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
            this.j = (byte) 0;
            a(new cs(new dk(objectInputStream)));
        } catch (cf e2) {
            throw new IOException(e2.getMessage());
        }
    }

    /* compiled from: Latent.java */
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

    /* compiled from: Latent.java */
    /* loaded from: classes.dex */
    private static class a extends di<bf> {
        private a() {
        }

        @Override // u.aly.dg
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void b(cy cyVar, bf bfVar) throws cf {
            cyVar.j();
            while (true) {
                ct l = cyVar.l();
                if (l.b == 0) {
                    break;
                }
                short s = l.c;
                if (s != 1) {
                    if (s == 2) {
                        if (l.b == 10) {
                            bfVar.b = cyVar.x();
                            bfVar.b(true);
                        } else {
                            db.a(cyVar, l.b);
                        }
                    } else {
                        db.a(cyVar, l.b);
                    }
                } else if (l.b == 8) {
                    bfVar.a = cyVar.w();
                    bfVar.a(true);
                } else {
                    db.a(cyVar, l.b);
                }
                cyVar.m();
            }
            cyVar.k();
            if (!bfVar.e()) {
                throw new cz("Required field 'latency' was not found in serialized data! Struct: " + toString());
            }
            if (!bfVar.i()) {
                throw new cz("Required field 'interval' was not found in serialized data! Struct: " + toString());
            }
            bfVar.j();
        }

        @Override // u.aly.dg
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(cy cyVar, bf bfVar) throws cf {
            bfVar.j();
            cyVar.a(bf.d);
            cyVar.a(bf.e);
            cyVar.a(bfVar.a);
            cyVar.c();
            cyVar.a(bf.f);
            cyVar.a(bfVar.b);
            cyVar.c();
            cyVar.d();
            cyVar.b();
        }
    }

    /* compiled from: Latent.java */
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

    /* compiled from: Latent.java */
    /* loaded from: classes.dex */
    private static class c extends dj<bf> {
        private c() {
        }

        @Override // u.aly.dg
        public void a(cy cyVar, bf bfVar) throws cf {
            de deVar = (de) cyVar;
            deVar.a(bfVar.a);
            deVar.a(bfVar.b);
        }

        @Override // u.aly.dg
        public void b(cy cyVar, bf bfVar) throws cf {
            de deVar = (de) cyVar;
            bfVar.a = deVar.w();
            bfVar.a(true);
            bfVar.b = deVar.x();
            bfVar.b(true);
        }
    }
}
