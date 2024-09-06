package u.aly;

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

/* compiled from: Error.java */
/* loaded from: classes.dex */
public class av implements Serializable, Cloneable, bz<av, e> {
    public static final Map<e, cl> d;
    private static final dd e = new dd("Error");
    private static final ct f = new ct("ts", (byte) 10, 1);
    private static final ct g = new ct("context", (byte) 11, 2);
    private static final ct h = new ct("source", (byte) 8, 3);
    private static final Map<Class<? extends dg>, dh> i;
    private static final int j = 0;
    public long a;
    public String b;
    public aw c;
    private byte k;
    private e[] l;

    static {
        HashMap hashMap = new HashMap();
        i = hashMap;
        hashMap.put(di.class, new b());
        i.put(dj.class, new d());
        EnumMap enumMap = new EnumMap(e.class);
        enumMap.put((EnumMap) e.TS, (e) new cl("ts", (byte) 1, new cm((byte) 10)));
        enumMap.put((EnumMap) e.CONTEXT, (e) new cl("context", (byte) 1, new cm((byte) 11)));
        enumMap.put((EnumMap) e.SOURCE, (e) new cl("source", (byte) 2, new ck(df.n, aw.class)));
        Map<e, cl> unmodifiableMap = Collections.unmodifiableMap(enumMap);
        d = unmodifiableMap;
        cl.a(av.class, unmodifiableMap);
    }

    /* compiled from: Error.java */
    /* loaded from: classes.dex */
    public enum e implements cg {
        TS(1, "ts"),
        CONTEXT(2, "context"),
        SOURCE(3, "source");

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
                return TS;
            }
            if (i == 2) {
                return CONTEXT;
            }
            if (i != 3) {
                return null;
            }
            return SOURCE;
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

    public av() {
        this.k = (byte) 0;
        this.l = new e[]{e.SOURCE};
    }

    public av(long j2, String str) {
        this();
        this.a = j2;
        b(true);
        this.b = str;
    }

    public av(av avVar) {
        this.k = (byte) 0;
        this.l = new e[]{e.SOURCE};
        this.k = avVar.k;
        this.a = avVar.a;
        if (avVar.i()) {
            this.b = avVar.b;
        }
        if (avVar.l()) {
            this.c = avVar.c;
        }
    }

    @Override // u.aly.bz
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public av g() {
        return new av(this);
    }

    @Override // u.aly.bz
    public void b() {
        b(false);
        this.a = 0L;
        this.b = null;
        this.c = null;
    }

    public long c() {
        return this.a;
    }

    public av a(long j2) {
        this.a = j2;
        b(true);
        return this;
    }

    public void d() {
        this.k = bw.b(this.k, 0);
    }

    public boolean e() {
        return bw.a(this.k, 0);
    }

    public void b(boolean z) {
        this.k = bw.a(this.k, 0, z);
    }

    public String f() {
        return this.b;
    }

    public av a(String str) {
        this.b = str;
        return this;
    }

    public void h() {
        this.b = null;
    }

    public boolean i() {
        return this.b != null;
    }

    public void c(boolean z) {
        if (z) {
            return;
        }
        this.b = null;
    }

    public aw j() {
        return this.c;
    }

    public av a(aw awVar) {
        this.c = awVar;
        return this;
    }

    public void k() {
        this.c = null;
    }

    public boolean l() {
        return this.c != null;
    }

    public void d(boolean z) {
        if (z) {
            return;
        }
        this.c = null;
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
        StringBuilder sb = new StringBuilder("Error(");
        sb.append("ts:");
        sb.append(this.a);
        sb.append(", ");
        sb.append("context:");
        String str = this.b;
        if (str == null) {
            sb.append(Constants.NULL_VERSION_ID);
        } else {
            sb.append(str);
        }
        if (l()) {
            sb.append(", ");
            sb.append("source:");
            aw awVar = this.c;
            if (awVar == null) {
                sb.append(Constants.NULL_VERSION_ID);
            } else {
                sb.append(awVar);
            }
        }
        sb.append(")");
        return sb.toString();
    }

    public void m() throws cf {
        if (this.b != null) {
            return;
        }
        throw new cz("Required field 'context' was not present! Struct: " + toString());
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
            this.k = (byte) 0;
            a(new cs(new dk(objectInputStream)));
        } catch (cf e2) {
            throw new IOException(e2.getMessage());
        }
    }

    /* compiled from: Error.java */
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

    /* compiled from: Error.java */
    /* loaded from: classes.dex */
    private static class a extends di<av> {
        private a() {
        }

        @Override // u.aly.dg
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void b(cy cyVar, av avVar) throws cf {
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
                                avVar.c = aw.a(cyVar.w());
                                avVar.d(true);
                            } else {
                                db.a(cyVar, l.b);
                            }
                        } else {
                            db.a(cyVar, l.b);
                        }
                    } else if (l.b == 11) {
                        avVar.b = cyVar.z();
                        avVar.c(true);
                    } else {
                        db.a(cyVar, l.b);
                    }
                } else if (l.b == 10) {
                    avVar.a = cyVar.x();
                    avVar.b(true);
                } else {
                    db.a(cyVar, l.b);
                }
                cyVar.m();
            }
            cyVar.k();
            if (!avVar.e()) {
                throw new cz("Required field 'ts' was not found in serialized data! Struct: " + toString());
            }
            avVar.m();
        }

        @Override // u.aly.dg
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(cy cyVar, av avVar) throws cf {
            avVar.m();
            cyVar.a(av.e);
            cyVar.a(av.f);
            cyVar.a(avVar.a);
            cyVar.c();
            if (avVar.b != null) {
                cyVar.a(av.g);
                cyVar.a(avVar.b);
                cyVar.c();
            }
            if (avVar.c != null && avVar.l()) {
                cyVar.a(av.h);
                cyVar.a(avVar.c.a());
                cyVar.c();
            }
            cyVar.d();
            cyVar.b();
        }
    }

    /* compiled from: Error.java */
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

    /* compiled from: Error.java */
    /* loaded from: classes.dex */
    private static class c extends dj<av> {
        private c() {
        }

        @Override // u.aly.dg
        public void a(cy cyVar, av avVar) throws cf {
            de deVar = (de) cyVar;
            deVar.a(avVar.a);
            deVar.a(avVar.b);
            BitSet bitSet = new BitSet();
            if (avVar.l()) {
                bitSet.set(0);
            }
            deVar.a(bitSet, 1);
            if (avVar.l()) {
                deVar.a(avVar.c.a());
            }
        }

        @Override // u.aly.dg
        public void b(cy cyVar, av avVar) throws cf {
            de deVar = (de) cyVar;
            avVar.a = deVar.x();
            avVar.b(true);
            avVar.b = deVar.z();
            avVar.c(true);
            if (deVar.b(1).get(0)) {
                avVar.c = aw.a(deVar.w());
                avVar.d(true);
            }
        }
    }
}
