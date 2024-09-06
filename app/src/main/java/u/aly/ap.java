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

/* compiled from: ActivateMsg.java */
/* loaded from: classes.dex */
public class ap implements Serializable, Cloneable, bz<ap, e> {
    public static final Map<e, cl> b;
    private static final dd c = new dd("ActivateMsg");
    private static final ct d = new ct("ts", (byte) 10, 1);
    private static final Map<Class<? extends dg>, dh> e;
    private static final int f = 0;
    public long a;
    private byte g;

    public void f() throws cf {
    }

    static {
        HashMap hashMap = new HashMap();
        e = hashMap;
        hashMap.put(di.class, new b());
        e.put(dj.class, new d());
        EnumMap enumMap = new EnumMap(e.class);
        enumMap.put((EnumMap) e.TS, (e) new cl("ts", (byte) 1, new cm((byte) 10)));
        Map<e, cl> unmodifiableMap = Collections.unmodifiableMap(enumMap);
        b = unmodifiableMap;
        cl.a(ap.class, unmodifiableMap);
    }

    /* compiled from: ActivateMsg.java */
    /* loaded from: classes.dex */
    public enum e implements cg {
        TS(1, "ts");

        private static final Map<String, e> b = new HashMap();
        private final short c;
        private final String d;

        static {
            Iterator it = EnumSet.allOf(e.class).iterator();
            while (it.hasNext()) {
                e eVar = (e) it.next();
                b.put(eVar.b(), eVar);
            }
        }

        public static e a(int i) {
            if (i != 1) {
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
            return b.get(str);
        }

        e(short s, String str) {
            this.c = s;
            this.d = str;
        }

        @Override // u.aly.cg
        public short a() {
            return this.c;
        }

        @Override // u.aly.cg
        public String b() {
            return this.d;
        }
    }

    public ap() {
        this.g = (byte) 0;
    }

    public ap(long j) {
        this();
        this.a = j;
        a(true);
    }

    public ap(ap apVar) {
        this.g = (byte) 0;
        this.g = apVar.g;
        this.a = apVar.a;
    }

    @Override // u.aly.bz
    /* renamed from: a */
    public ap g() {
        return new ap(this);
    }

    @Override // u.aly.bz
    public void b() {
        a(false);
        this.a = 0L;
    }

    public long c() {
        return this.a;
    }

    public ap a(long j) {
        this.a = j;
        a(true);
        return this;
    }

    public void d() {
        this.g = bw.b(this.g, 0);
    }

    public boolean e() {
        return bw.a(this.g, 0);
    }

    public void a(boolean z) {
        this.g = bw.a(this.g, 0, z);
    }

    @Override // u.aly.bz
    /* renamed from: a */
    public e b(int i) {
        return e.a(i);
    }

    @Override // u.aly.bz
    public void a(cy cyVar) throws cf {
        e.get(cyVar.D()).b().b(cyVar, this);
    }

    @Override // u.aly.bz
    public void b(cy cyVar) throws cf {
        e.get(cyVar.D()).b().a(cyVar, this);
    }

    public String toString() {
        return "ActivateMsg(ts:" + this.a + ")";
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
            this.g = (byte) 0;
            a(new cs(new dk(objectInputStream)));
        } catch (cf e2) {
            throw new IOException(e2.getMessage());
        }
    }

    /* compiled from: ActivateMsg.java */
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

    /* compiled from: ActivateMsg.java */
    /* loaded from: classes.dex */
    private static class a extends di<ap> {
        private a() {
        }

        /* synthetic */ a(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // u.aly.dg
        /* renamed from: a */
        public void b(cy cyVar, ap apVar) throws cf {
            cyVar.j();
            while (true) {
                ct l = cyVar.l();
                if (l.b == 0) {
                    break;
                }
                if (l.c == 1) {
                    if (l.b == 10) {
                        apVar.a = cyVar.x();
                        apVar.a(true);
                    } else {
                        db.a(cyVar, l.b);
                    }
                } else {
                    db.a(cyVar, l.b);
                }
                cyVar.m();
            }
            cyVar.k();
            if (!apVar.e()) {
                throw new cz("Required field 'ts' was not found in serialized data! Struct: " + toString());
            }
            apVar.f();
        }

        @Override // u.aly.dg
        /* renamed from: b */
        public void a(cy cyVar, ap apVar) throws cf {
            apVar.f();
            cyVar.a(ap.c);
            cyVar.a(ap.d);
            cyVar.a(apVar.a);
            cyVar.c();
            cyVar.d();
            cyVar.b();
        }
    }

    /* compiled from: ActivateMsg.java */
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

    /* compiled from: ActivateMsg.java */
    /* loaded from: classes.dex */
    private static class c extends dj<ap> {
        private c() {
        }

        /* synthetic */ c(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // u.aly.dg
        public void a(cy cyVar, ap apVar) throws cf {
            ((de) cyVar).a(apVar.a);
        }

        @Override // u.aly.dg
        public void b(cy cyVar, ap apVar) throws cf {
            apVar.a = ((de) cyVar).x();
            apVar.a(true);
        }
    }
}
