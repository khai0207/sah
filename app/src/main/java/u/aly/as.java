package u.aly;

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

/* compiled from: ClientStats.java */
/* loaded from: classes.dex */
public class as implements Serializable, Cloneable, bz<as, e> {
    public static final Map<e, cl> d;
    private static final dd e = new dd("ClientStats");
    private static final ct f = new ct("successful_requests", (byte) 8, 1);
    private static final ct g = new ct("failed_requests", (byte) 8, 2);
    private static final ct h = new ct("last_request_spent_ms", (byte) 8, 3);
    private static final Map<Class<? extends dg>, dh> i;
    private static final int j = 0;
    private static final int k = 1;
    private static final int l = 2;
    public int a;
    public int b;
    public int c;
    private byte m;
    private e[] n;

    public void m() throws cf {
    }

    static {
        HashMap hashMap = new HashMap();
        i = hashMap;
        hashMap.put(di.class, new b());
        i.put(dj.class, new d());
        EnumMap enumMap = new EnumMap(e.class);
        enumMap.put((EnumMap) e.SUCCESSFUL_REQUESTS, (e) new cl("successful_requests", (byte) 1, new cm((byte) 8)));
        enumMap.put((EnumMap) e.FAILED_REQUESTS, (e) new cl("failed_requests", (byte) 1, new cm((byte) 8)));
        enumMap.put((EnumMap) e.LAST_REQUEST_SPENT_MS, (e) new cl("last_request_spent_ms", (byte) 2, new cm((byte) 8)));
        Map<e, cl> unmodifiableMap = Collections.unmodifiableMap(enumMap);
        d = unmodifiableMap;
        cl.a(as.class, unmodifiableMap);
    }

    /* compiled from: ClientStats.java */
    /* loaded from: classes.dex */
    public enum e implements cg {
        SUCCESSFUL_REQUESTS(1, "successful_requests"),
        FAILED_REQUESTS(2, "failed_requests"),
        LAST_REQUEST_SPENT_MS(3, "last_request_spent_ms");

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
                return SUCCESSFUL_REQUESTS;
            }
            if (i == 2) {
                return FAILED_REQUESTS;
            }
            if (i != 3) {
                return null;
            }
            return LAST_REQUEST_SPENT_MS;
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

    public as() {
        this.m = (byte) 0;
        this.n = new e[]{e.LAST_REQUEST_SPENT_MS};
        this.a = 0;
        this.b = 0;
    }

    public as(int i2, int i3) {
        this();
        this.a = i2;
        a(true);
        this.b = i3;
        b(true);
    }

    public as(as asVar) {
        this.m = (byte) 0;
        this.n = new e[]{e.LAST_REQUEST_SPENT_MS};
        this.m = asVar.m;
        this.a = asVar.a;
        this.b = asVar.b;
        this.c = asVar.c;
    }

    @Override // u.aly.bz
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public as g() {
        return new as(this);
    }

    @Override // u.aly.bz
    public void b() {
        this.a = 0;
        this.b = 0;
        c(false);
        this.c = 0;
    }

    public int c() {
        return this.a;
    }

    public as a(int i2) {
        this.a = i2;
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

    public int f() {
        return this.b;
    }

    public as c(int i2) {
        this.b = i2;
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

    public int j() {
        return this.c;
    }

    public as d(int i2) {
        this.c = i2;
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
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
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
        StringBuilder sb = new StringBuilder("ClientStats(");
        sb.append("successful_requests:");
        sb.append(this.a);
        sb.append(", ");
        sb.append("failed_requests:");
        sb.append(this.b);
        if (l()) {
            sb.append(", ");
            sb.append("last_request_spent_ms:");
            sb.append(this.c);
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
            this.m = (byte) 0;
            a(new cs(new dk(objectInputStream)));
        } catch (cf e2) {
            throw new IOException(e2.getMessage());
        }
    }

    /* compiled from: ClientStats.java */
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

    /* compiled from: ClientStats.java */
    /* loaded from: classes.dex */
    private static class a extends di<as> {
        private a() {
        }

        @Override // u.aly.dg
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void b(cy cyVar, as asVar) throws cf {
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
                            if (l.b == 8) {
                                asVar.c = cyVar.w();
                                asVar.c(true);
                            } else {
                                db.a(cyVar, l.b);
                            }
                        } else {
                            db.a(cyVar, l.b);
                        }
                    } else if (l.b == 8) {
                        asVar.b = cyVar.w();
                        asVar.b(true);
                    } else {
                        db.a(cyVar, l.b);
                    }
                } else if (l.b == 8) {
                    asVar.a = cyVar.w();
                    asVar.a(true);
                } else {
                    db.a(cyVar, l.b);
                }
                cyVar.m();
            }
            cyVar.k();
            if (!asVar.e()) {
                throw new cz("Required field 'successful_requests' was not found in serialized data! Struct: " + toString());
            }
            if (!asVar.i()) {
                throw new cz("Required field 'failed_requests' was not found in serialized data! Struct: " + toString());
            }
            asVar.m();
        }

        @Override // u.aly.dg
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(cy cyVar, as asVar) throws cf {
            asVar.m();
            cyVar.a(as.e);
            cyVar.a(as.f);
            cyVar.a(asVar.a);
            cyVar.c();
            cyVar.a(as.g);
            cyVar.a(asVar.b);
            cyVar.c();
            if (asVar.l()) {
                cyVar.a(as.h);
                cyVar.a(asVar.c);
                cyVar.c();
            }
            cyVar.d();
            cyVar.b();
        }
    }

    /* compiled from: ClientStats.java */
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

    /* compiled from: ClientStats.java */
    /* loaded from: classes.dex */
    private static class c extends dj<as> {
        private c() {
        }

        @Override // u.aly.dg
        public void a(cy cyVar, as asVar) throws cf {
            de deVar = (de) cyVar;
            deVar.a(asVar.a);
            deVar.a(asVar.b);
            BitSet bitSet = new BitSet();
            if (asVar.l()) {
                bitSet.set(0);
            }
            deVar.a(bitSet, 1);
            if (asVar.l()) {
                deVar.a(asVar.c);
            }
        }

        @Override // u.aly.dg
        public void b(cy cyVar, as asVar) throws cf {
            de deVar = (de) cyVar;
            asVar.a = deVar.w();
            asVar.a(true);
            asVar.b = deVar.w();
            asVar.b(true);
            if (deVar.b(1).get(0)) {
                asVar.c = deVar.w();
                asVar.c(true);
            }
        }
    }
}
