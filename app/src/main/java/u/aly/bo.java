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

/* compiled from: Traffic.java */
/* loaded from: classes.dex */
public class bo implements Serializable, Cloneable, bz<bo, e> {
    public static final Map<e, cl> c;
    private static final dd d = new dd("Traffic");
    private static final ct e = new ct("upload_traffic", (byte) 8, 1);
    private static final ct f = new ct("download_traffic", (byte) 8, 2);
    private static final Map<Class<? extends dg>, dh> g;
    private static final int h = 0;
    private static final int i = 1;
    public int a;
    public int b;
    private byte j;

    public void j() throws cf {
    }

    static {
        HashMap hashMap = new HashMap();
        g = hashMap;
        hashMap.put(di.class, new b());
        g.put(dj.class, new d());
        EnumMap enumMap = new EnumMap(e.class);
        enumMap.put((EnumMap) e.UPLOAD_TRAFFIC, (e) new cl("upload_traffic", (byte) 1, new cm((byte) 8)));
        enumMap.put((EnumMap) e.DOWNLOAD_TRAFFIC, (e) new cl("download_traffic", (byte) 1, new cm((byte) 8)));
        Map<e, cl> unmodifiableMap = Collections.unmodifiableMap(enumMap);
        c = unmodifiableMap;
        cl.a(bo.class, unmodifiableMap);
    }

    /* compiled from: Traffic.java */
    /* loaded from: classes.dex */
    public enum e implements cg {
        UPLOAD_TRAFFIC(1, "upload_traffic"),
        DOWNLOAD_TRAFFIC(2, "download_traffic");

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
                return UPLOAD_TRAFFIC;
            }
            if (i != 2) {
                return null;
            }
            return DOWNLOAD_TRAFFIC;
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

    public bo() {
        this.j = (byte) 0;
    }

    public bo(int i2, int i3) {
        this();
        this.a = i2;
        a(true);
        this.b = i3;
        b(true);
    }

    public bo(bo boVar) {
        this.j = (byte) 0;
        this.j = boVar.j;
        this.a = boVar.a;
        this.b = boVar.b;
    }

    @Override // u.aly.bz
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public bo g() {
        return new bo(this);
    }

    @Override // u.aly.bz
    public void b() {
        a(false);
        this.a = 0;
        b(false);
        this.b = 0;
    }

    public int c() {
        return this.a;
    }

    public bo a(int i2) {
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

    public int f() {
        return this.b;
    }

    public bo c(int i2) {
        this.b = i2;
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
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
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
        return "Traffic(upload_traffic:" + this.a + ", download_traffic:" + this.b + ")";
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

    /* compiled from: Traffic.java */
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

    /* compiled from: Traffic.java */
    /* loaded from: classes.dex */
    private static class a extends di<bo> {
        private a() {
        }

        @Override // u.aly.dg
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void b(cy cyVar, bo boVar) throws cf {
            cyVar.j();
            while (true) {
                ct l = cyVar.l();
                if (l.b == 0) {
                    break;
                }
                short s = l.c;
                if (s != 1) {
                    if (s == 2) {
                        if (l.b == 8) {
                            boVar.b = cyVar.w();
                            boVar.b(true);
                        } else {
                            db.a(cyVar, l.b);
                        }
                    } else {
                        db.a(cyVar, l.b);
                    }
                } else if (l.b == 8) {
                    boVar.a = cyVar.w();
                    boVar.a(true);
                } else {
                    db.a(cyVar, l.b);
                }
                cyVar.m();
            }
            cyVar.k();
            if (!boVar.e()) {
                throw new cz("Required field 'upload_traffic' was not found in serialized data! Struct: " + toString());
            }
            if (!boVar.i()) {
                throw new cz("Required field 'download_traffic' was not found in serialized data! Struct: " + toString());
            }
            boVar.j();
        }

        @Override // u.aly.dg
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(cy cyVar, bo boVar) throws cf {
            boVar.j();
            cyVar.a(bo.d);
            cyVar.a(bo.e);
            cyVar.a(boVar.a);
            cyVar.c();
            cyVar.a(bo.f);
            cyVar.a(boVar.b);
            cyVar.c();
            cyVar.d();
            cyVar.b();
        }
    }

    /* compiled from: Traffic.java */
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

    /* compiled from: Traffic.java */
    /* loaded from: classes.dex */
    private static class c extends dj<bo> {
        private c() {
        }

        @Override // u.aly.dg
        public void a(cy cyVar, bo boVar) throws cf {
            de deVar = (de) cyVar;
            deVar.a(boVar.a);
            deVar.a(boVar.b);
        }

        @Override // u.aly.dg
        public void b(cy cyVar, bo boVar) throws cf {
            de deVar = (de) cyVar;
            boVar.a = deVar.w();
            boVar.a(true);
            boVar.b = deVar.w();
            boVar.b(true);
        }
    }
}
