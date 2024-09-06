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

/* compiled from: Event.java */
/* loaded from: classes.dex */
public class ax implements Serializable, Cloneable, bz<ax, e> {
    public static final Map<e, cl> f;
    private static final dd g = new dd("Event");
    private static final ct h = new ct(com.alipay.sdk.m.h.c.e, (byte) 11, 1);
    private static final ct i = new ct("properties", df.k, 2);
    private static final ct j = new ct("duration", (byte) 10, 3);
    private static final ct k = new ct("acc", (byte) 8, 4);
    private static final ct l = new ct("ts", (byte) 10, 5);
    private static final Map<Class<? extends dg>, dh> m;
    private static final int n = 0;
    private static final int o = 1;
    private static final int p = 2;
    public String a;
    public Map<String, bj> b;
    public long c;
    public int d;
    public long e;
    private byte q;
    private e[] r;

    static {
        HashMap hashMap = new HashMap();
        m = hashMap;
        hashMap.put(di.class, new b());
        m.put(dj.class, new d());
        EnumMap enumMap = new EnumMap(e.class);
        enumMap.put((EnumMap) e.NAME, (e) new cl(com.alipay.sdk.m.h.c.e, (byte) 1, new cm((byte) 11)));
        enumMap.put((EnumMap) e.PROPERTIES, (e) new cl("properties", (byte) 1, new co(df.k, new cm((byte) 11), new cq((byte) 12, bj.class))));
        enumMap.put((EnumMap) e.DURATION, (e) new cl("duration", (byte) 2, new cm((byte) 10)));
        enumMap.put((EnumMap) e.ACC, (e) new cl("acc", (byte) 2, new cm((byte) 8)));
        enumMap.put((EnumMap) e.TS, (e) new cl("ts", (byte) 1, new cm((byte) 10)));
        Map<e, cl> unmodifiableMap = Collections.unmodifiableMap(enumMap);
        f = unmodifiableMap;
        cl.a(ax.class, unmodifiableMap);
    }

    /* compiled from: Event.java */
    /* loaded from: classes.dex */
    public enum e implements cg {
        NAME(1, com.alipay.sdk.m.h.c.e),
        PROPERTIES(2, "properties"),
        DURATION(3, "duration"),
        ACC(4, "acc"),
        TS(5, "ts");

        private static final Map<String, e> f = new HashMap();
        private final short g;
        private final String h;

        static {
            Iterator it = EnumSet.allOf(e.class).iterator();
            while (it.hasNext()) {
                e eVar = (e) it.next();
                f.put(eVar.b(), eVar);
            }
        }

        public static e a(int i2) {
            if (i2 == 1) {
                return NAME;
            }
            if (i2 == 2) {
                return PROPERTIES;
            }
            if (i2 == 3) {
                return DURATION;
            }
            if (i2 == 4) {
                return ACC;
            }
            if (i2 != 5) {
                return null;
            }
            return TS;
        }

        public static e b(int i2) {
            e a = a(i2);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException("Field " + i2 + " doesn't exist!");
        }

        public static e a(String str) {
            return f.get(str);
        }

        e(short s, String str) {
            this.g = s;
            this.h = str;
        }

        @Override // u.aly.cg
        public short a() {
            return this.g;
        }

        @Override // u.aly.cg
        public String b() {
            return this.h;
        }
    }

    public ax() {
        this.q = (byte) 0;
        this.r = new e[]{e.DURATION, e.ACC};
    }

    public ax(String str, Map<String, bj> map, long j2) {
        this();
        this.a = str;
        this.b = map;
        this.e = j2;
        e(true);
    }

    public ax(ax axVar) {
        this.q = (byte) 0;
        this.r = new e[]{e.DURATION, e.ACC};
        this.q = axVar.q;
        if (axVar.e()) {
            this.a = axVar.a;
        }
        if (axVar.j()) {
            HashMap hashMap = new HashMap();
            for (Map.Entry<String, bj> entry : axVar.b.entrySet()) {
                hashMap.put(entry.getKey(), new bj(entry.getValue()));
            }
            this.b = hashMap;
        }
        this.c = axVar.c;
        this.d = axVar.d;
        this.e = axVar.e;
    }

    @Override // u.aly.bz
    /* renamed from: a */
    public ax g() {
        return new ax(this);
    }

    @Override // u.aly.bz
    public void b() {
        this.a = null;
        this.b = null;
        c(false);
        this.c = 0L;
        d(false);
        this.d = 0;
        e(false);
        this.e = 0L;
    }

    public String c() {
        return this.a;
    }

    public ax a(String str) {
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

    public int f() {
        Map<String, bj> map = this.b;
        if (map == null) {
            return 0;
        }
        return map.size();
    }

    public void a(String str, bj bjVar) {
        if (this.b == null) {
            this.b = new HashMap();
        }
        this.b.put(str, bjVar);
    }

    public Map<String, bj> h() {
        return this.b;
    }

    public ax a(Map<String, bj> map) {
        this.b = map;
        return this;
    }

    public void i() {
        this.b = null;
    }

    public boolean j() {
        return this.b != null;
    }

    public void b(boolean z) {
        if (z) {
            return;
        }
        this.b = null;
    }

    public long k() {
        return this.c;
    }

    public ax a(long j2) {
        this.c = j2;
        c(true);
        return this;
    }

    public void l() {
        this.q = bw.b(this.q, 0);
    }

    public boolean m() {
        return bw.a(this.q, 0);
    }

    public void c(boolean z) {
        this.q = bw.a(this.q, 0, z);
    }

    public int n() {
        return this.d;
    }

    public ax a(int i2) {
        this.d = i2;
        d(true);
        return this;
    }

    public void o() {
        this.q = bw.b(this.q, 1);
    }

    public boolean p() {
        return bw.a(this.q, 1);
    }

    public void d(boolean z) {
        this.q = bw.a(this.q, 1, z);
    }

    public long q() {
        return this.e;
    }

    public ax b(long j2) {
        this.e = j2;
        e(true);
        return this;
    }

    public void r() {
        this.q = bw.b(this.q, 2);
    }

    public boolean s() {
        return bw.a(this.q, 2);
    }

    public void e(boolean z) {
        this.q = bw.a(this.q, 2, z);
    }

    @Override // u.aly.bz
    /* renamed from: c */
    public e b(int i2) {
        return e.a(i2);
    }

    @Override // u.aly.bz
    public void a(cy cyVar) throws cf {
        m.get(cyVar.D()).b().b(cyVar, this);
    }

    @Override // u.aly.bz
    public void b(cy cyVar) throws cf {
        m.get(cyVar.D()).b().a(cyVar, this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Event(");
        sb.append("name:");
        String str = this.a;
        if (str == null) {
            sb.append(Constants.NULL_VERSION_ID);
        } else {
            sb.append(str);
        }
        sb.append(", ");
        sb.append("properties:");
        Map<String, bj> map = this.b;
        if (map == null) {
            sb.append(Constants.NULL_VERSION_ID);
        } else {
            sb.append(map);
        }
        if (m()) {
            sb.append(", ");
            sb.append("duration:");
            sb.append(this.c);
        }
        if (p()) {
            sb.append(", ");
            sb.append("acc:");
            sb.append(this.d);
        }
        sb.append(", ");
        sb.append("ts:");
        sb.append(this.e);
        sb.append(")");
        return sb.toString();
    }

    public void t() throws cf {
        if (this.a == null) {
            throw new cz("Required field 'name' was not present! Struct: " + toString());
        }
        if (this.b != null) {
            return;
        }
        throw new cz("Required field 'properties' was not present! Struct: " + toString());
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
            this.q = (byte) 0;
            a(new cs(new dk(objectInputStream)));
        } catch (cf e2) {
            throw new IOException(e2.getMessage());
        }
    }

    /* compiled from: Event.java */
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

    /* compiled from: Event.java */
    /* loaded from: classes.dex */
    private static class a extends di<ax> {
        private a() {
        }

        /* synthetic */ a(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // u.aly.dg
        /* renamed from: a */
        public void b(cy cyVar, ax axVar) throws cf {
            cyVar.j();
            while (true) {
                ct l = cyVar.l();
                if (l.b == 0) {
                    break;
                }
                short s = l.c;
                if (s != 1) {
                    if (s != 2) {
                        if (s != 3) {
                            if (s != 4) {
                                if (s == 5) {
                                    if (l.b == 10) {
                                        axVar.e = cyVar.x();
                                        axVar.e(true);
                                    } else {
                                        db.a(cyVar, l.b);
                                    }
                                } else {
                                    db.a(cyVar, l.b);
                                }
                            } else if (l.b == 8) {
                                axVar.d = cyVar.w();
                                axVar.d(true);
                            } else {
                                db.a(cyVar, l.b);
                            }
                        } else if (l.b == 10) {
                            axVar.c = cyVar.x();
                            axVar.c(true);
                        } else {
                            db.a(cyVar, l.b);
                        }
                    } else if (l.b == 13) {
                        cv n = cyVar.n();
                        axVar.b = new HashMap(n.c * 2);
                        for (int i = 0; i < n.c; i++) {
                            String z = cyVar.z();
                            bj bjVar = new bj();
                            bjVar.a(cyVar);
                            axVar.b.put(z, bjVar);
                        }
                        cyVar.o();
                        axVar.b(true);
                    } else {
                        db.a(cyVar, l.b);
                    }
                } else if (l.b == 11) {
                    axVar.a = cyVar.z();
                    axVar.a(true);
                } else {
                    db.a(cyVar, l.b);
                }
                cyVar.m();
            }
            cyVar.k();
            if (!axVar.s()) {
                throw new cz("Required field 'ts' was not found in serialized data! Struct: " + toString());
            }
            axVar.t();
        }

        @Override // u.aly.dg
        /* renamed from: b */
        public void a(cy cyVar, ax axVar) throws cf {
            axVar.t();
            cyVar.a(ax.g);
            if (axVar.a != null) {
                cyVar.a(ax.h);
                cyVar.a(axVar.a);
                cyVar.c();
            }
            if (axVar.b != null) {
                cyVar.a(ax.i);
                cyVar.a(new cv((byte) 11, (byte) 12, axVar.b.size()));
                for (Map.Entry<String, bj> entry : axVar.b.entrySet()) {
                    cyVar.a(entry.getKey());
                    entry.getValue().b(cyVar);
                }
                cyVar.e();
                cyVar.c();
            }
            if (axVar.m()) {
                cyVar.a(ax.j);
                cyVar.a(axVar.c);
                cyVar.c();
            }
            if (axVar.p()) {
                cyVar.a(ax.k);
                cyVar.a(axVar.d);
                cyVar.c();
            }
            cyVar.a(ax.l);
            cyVar.a(axVar.e);
            cyVar.c();
            cyVar.d();
            cyVar.b();
        }
    }

    /* compiled from: Event.java */
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

    /* compiled from: Event.java */
    /* loaded from: classes.dex */
    private static class c extends dj<ax> {
        private c() {
        }

        /* synthetic */ c(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // u.aly.dg
        public void a(cy cyVar, ax axVar) throws cf {
            de deVar = (de) cyVar;
            deVar.a(axVar.a);
            deVar.a(axVar.b.size());
            for (Map.Entry<String, bj> entry : axVar.b.entrySet()) {
                deVar.a(entry.getKey());
                entry.getValue().b(deVar);
            }
            deVar.a(axVar.e);
            BitSet bitSet = new BitSet();
            if (axVar.m()) {
                bitSet.set(0);
            }
            if (axVar.p()) {
                bitSet.set(1);
            }
            deVar.a(bitSet, 2);
            if (axVar.m()) {
                deVar.a(axVar.c);
            }
            if (axVar.p()) {
                deVar.a(axVar.d);
            }
        }

        @Override // u.aly.dg
        public void b(cy cyVar, ax axVar) throws cf {
            de deVar = (de) cyVar;
            axVar.a = deVar.z();
            axVar.a(true);
            cv cvVar = new cv((byte) 11, (byte) 12, deVar.w());
            axVar.b = new HashMap(cvVar.c * 2);
            for (int i = 0; i < cvVar.c; i++) {
                String z = deVar.z();
                bj bjVar = new bj();
                bjVar.a((cy) deVar);
                axVar.b.put(z, bjVar);
            }
            axVar.b(true);
            axVar.e = deVar.x();
            axVar.e(true);
            BitSet b = deVar.b(2);
            if (b.get(0)) {
                axVar.c = deVar.x();
                axVar.c(true);
            }
            if (b.get(1)) {
                axVar.d = deVar.w();
                axVar.d(true);
            }
        }
    }
}
