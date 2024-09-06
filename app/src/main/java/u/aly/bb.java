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

/* compiled from: IdTracking.java */
/* loaded from: classes.dex */
public class bb implements Serializable, Cloneable, bz<bb, e> {
    public static final Map<e, cl> d;
    private static final dd e = new dd("IdTracking");
    private static final ct f = new ct("snapshots", df.k, 1);
    private static final ct g = new ct("journals", df.m, 2);
    private static final ct h = new ct("checksum", (byte) 11, 3);
    private static final Map<Class<? extends dg>, dh> i;
    public Map<String, ba> a;
    public List<az> b;
    public String c;
    private e[] j;

    static {
        HashMap hashMap = new HashMap();
        i = hashMap;
        hashMap.put(di.class, new b());
        i.put(dj.class, new d());
        EnumMap enumMap = new EnumMap(e.class);
        enumMap.put((EnumMap) e.SNAPSHOTS, (e) new cl("snapshots", (byte) 1, new co(df.k, new cm((byte) 11), new cq((byte) 12, ba.class))));
        enumMap.put((EnumMap) e.JOURNALS, (e) new cl("journals", (byte) 2, new cn(df.m, new cq((byte) 12, az.class))));
        enumMap.put((EnumMap) e.CHECKSUM, (e) new cl("checksum", (byte) 2, new cm((byte) 11)));
        Map<e, cl> unmodifiableMap = Collections.unmodifiableMap(enumMap);
        d = unmodifiableMap;
        cl.a(bb.class, unmodifiableMap);
    }

    /* compiled from: IdTracking.java */
    /* loaded from: classes.dex */
    public enum e implements cg {
        SNAPSHOTS(1, "snapshots"),
        JOURNALS(2, "journals"),
        CHECKSUM(3, "checksum");

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
                return SNAPSHOTS;
            }
            if (i == 2) {
                return JOURNALS;
            }
            if (i != 3) {
                return null;
            }
            return CHECKSUM;
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

    public bb() {
        this.j = new e[]{e.JOURNALS, e.CHECKSUM};
    }

    public bb(Map<String, ba> map) {
        this();
        this.a = map;
    }

    public bb(bb bbVar) {
        this.j = new e[]{e.JOURNALS, e.CHECKSUM};
        if (bbVar.f()) {
            HashMap hashMap = new HashMap();
            for (Map.Entry<String, ba> entry : bbVar.a.entrySet()) {
                hashMap.put(entry.getKey(), new ba(entry.getValue()));
            }
            this.a = hashMap;
        }
        if (bbVar.l()) {
            ArrayList arrayList = new ArrayList();
            Iterator<az> it = bbVar.b.iterator();
            while (it.hasNext()) {
                arrayList.add(new az(it.next()));
            }
            this.b = arrayList;
        }
        if (bbVar.o()) {
            this.c = bbVar.c;
        }
    }

    @Override // u.aly.bz
    /* renamed from: a */
    public bb g() {
        return new bb(this);
    }

    @Override // u.aly.bz
    public void b() {
        this.a = null;
        this.b = null;
        this.c = null;
    }

    public int c() {
        Map<String, ba> map = this.a;
        if (map == null) {
            return 0;
        }
        return map.size();
    }

    public void a(String str, ba baVar) {
        if (this.a == null) {
            this.a = new HashMap();
        }
        this.a.put(str, baVar);
    }

    public Map<String, ba> d() {
        return this.a;
    }

    public bb a(Map<String, ba> map) {
        this.a = map;
        return this;
    }

    public void e() {
        this.a = null;
    }

    public boolean f() {
        return this.a != null;
    }

    public void a(boolean z) {
        if (z) {
            return;
        }
        this.a = null;
    }

    public int h() {
        List<az> list = this.b;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public Iterator<az> i() {
        List<az> list = this.b;
        if (list == null) {
            return null;
        }
        return list.iterator();
    }

    public void a(az azVar) {
        if (this.b == null) {
            this.b = new ArrayList();
        }
        this.b.add(azVar);
    }

    public List<az> j() {
        return this.b;
    }

    public bb a(List<az> list) {
        this.b = list;
        return this;
    }

    public void k() {
        this.b = null;
    }

    public boolean l() {
        return this.b != null;
    }

    public void b(boolean z) {
        if (z) {
            return;
        }
        this.b = null;
    }

    public String m() {
        return this.c;
    }

    public bb a(String str) {
        this.c = str;
        return this;
    }

    public void n() {
        this.c = null;
    }

    public boolean o() {
        return this.c != null;
    }

    public void c(boolean z) {
        if (z) {
            return;
        }
        this.c = null;
    }

    @Override // u.aly.bz
    /* renamed from: a */
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
        StringBuilder sb = new StringBuilder("IdTracking(");
        sb.append("snapshots:");
        Map<String, ba> map = this.a;
        if (map == null) {
            sb.append(Constants.NULL_VERSION_ID);
        } else {
            sb.append(map);
        }
        if (l()) {
            sb.append(", ");
            sb.append("journals:");
            List<az> list = this.b;
            if (list == null) {
                sb.append(Constants.NULL_VERSION_ID);
            } else {
                sb.append(list);
            }
        }
        if (o()) {
            sb.append(", ");
            sb.append("checksum:");
            String str = this.c;
            if (str == null) {
                sb.append(Constants.NULL_VERSION_ID);
            } else {
                sb.append(str);
            }
        }
        sb.append(")");
        return sb.toString();
    }

    public void p() throws cf {
        if (this.a != null) {
            return;
        }
        throw new cz("Required field 'snapshots' was not present! Struct: " + toString());
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
            a(new cs(new dk(objectInputStream)));
        } catch (cf e2) {
            throw new IOException(e2.getMessage());
        }
    }

    /* compiled from: IdTracking.java */
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

    /* compiled from: IdTracking.java */
    /* loaded from: classes.dex */
    private static class a extends di<bb> {
        private a() {
        }

        /* synthetic */ a(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // u.aly.dg
        /* renamed from: a */
        public void b(cy cyVar, bb bbVar) throws cf {
            cyVar.j();
            while (true) {
                ct l = cyVar.l();
                if (l.b != 0) {
                    short s = l.c;
                    int i = 0;
                    if (s != 1) {
                        if (s != 2) {
                            if (s == 3) {
                                if (l.b == 11) {
                                    bbVar.c = cyVar.z();
                                    bbVar.c(true);
                                } else {
                                    db.a(cyVar, l.b);
                                }
                            } else {
                                db.a(cyVar, l.b);
                            }
                        } else if (l.b == 15) {
                            cu p = cyVar.p();
                            bbVar.b = new ArrayList(p.b);
                            while (i < p.b) {
                                az azVar = new az();
                                azVar.a(cyVar);
                                bbVar.b.add(azVar);
                                i++;
                            }
                            cyVar.q();
                            bbVar.b(true);
                        } else {
                            db.a(cyVar, l.b);
                        }
                    } else if (l.b == 13) {
                        cv n = cyVar.n();
                        bbVar.a = new HashMap(n.c * 2);
                        while (i < n.c) {
                            String z = cyVar.z();
                            ba baVar = new ba();
                            baVar.a(cyVar);
                            bbVar.a.put(z, baVar);
                            i++;
                        }
                        cyVar.o();
                        bbVar.a(true);
                    } else {
                        db.a(cyVar, l.b);
                    }
                    cyVar.m();
                } else {
                    cyVar.k();
                    bbVar.p();
                    return;
                }
            }
        }

        @Override // u.aly.dg
        /* renamed from: b */
        public void a(cy cyVar, bb bbVar) throws cf {
            bbVar.p();
            cyVar.a(bb.e);
            if (bbVar.a != null) {
                cyVar.a(bb.f);
                cyVar.a(new cv((byte) 11, (byte) 12, bbVar.a.size()));
                for (Map.Entry<String, ba> entry : bbVar.a.entrySet()) {
                    cyVar.a(entry.getKey());
                    entry.getValue().b(cyVar);
                }
                cyVar.e();
                cyVar.c();
            }
            if (bbVar.b != null && bbVar.l()) {
                cyVar.a(bb.g);
                cyVar.a(new cu((byte) 12, bbVar.b.size()));
                Iterator<az> it = bbVar.b.iterator();
                while (it.hasNext()) {
                    it.next().b(cyVar);
                }
                cyVar.f();
                cyVar.c();
            }
            if (bbVar.c != null && bbVar.o()) {
                cyVar.a(bb.h);
                cyVar.a(bbVar.c);
                cyVar.c();
            }
            cyVar.d();
            cyVar.b();
        }
    }

    /* compiled from: IdTracking.java */
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

    /* compiled from: IdTracking.java */
    /* loaded from: classes.dex */
    private static class c extends dj<bb> {
        private c() {
        }

        /* synthetic */ c(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // u.aly.dg
        public void a(cy cyVar, bb bbVar) throws cf {
            de deVar = (de) cyVar;
            deVar.a(bbVar.a.size());
            for (Map.Entry<String, ba> entry : bbVar.a.entrySet()) {
                deVar.a(entry.getKey());
                entry.getValue().b(deVar);
            }
            BitSet bitSet = new BitSet();
            if (bbVar.l()) {
                bitSet.set(0);
            }
            if (bbVar.o()) {
                bitSet.set(1);
            }
            deVar.a(bitSet, 2);
            if (bbVar.l()) {
                deVar.a(bbVar.b.size());
                Iterator<az> it = bbVar.b.iterator();
                while (it.hasNext()) {
                    it.next().b(deVar);
                }
            }
            if (bbVar.o()) {
                deVar.a(bbVar.c);
            }
        }

        @Override // u.aly.dg
        public void b(cy cyVar, bb bbVar) throws cf {
            de deVar = (de) cyVar;
            cv cvVar = new cv((byte) 11, (byte) 12, deVar.w());
            bbVar.a = new HashMap(cvVar.c * 2);
            for (int i = 0; i < cvVar.c; i++) {
                String z = deVar.z();
                ba baVar = new ba();
                baVar.a(deVar);
                bbVar.a.put(z, baVar);
            }
            bbVar.a(true);
            BitSet b = deVar.b(2);
            if (b.get(0)) {
                cu cuVar = new cu((byte) 12, deVar.w());
                bbVar.b = new ArrayList(cuVar.b);
                for (int i2 = 0; i2 < cuVar.b; i2++) {
                    az azVar = new az();
                    azVar.a(deVar);
                    bbVar.b.add(azVar);
                }
                bbVar.b(true);
            }
            if (b.get(1)) {
                bbVar.c = deVar.z();
                bbVar.c(true);
            }
        }
    }
}
