package u.aly;

import com.netease.nimlib.amazonaws.services.s3.internal.Constants;
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

/* compiled from: Imprint.java */
/* loaded from: classes.dex */
public class bc implements Serializable, Cloneable, bz<bc, e> {
    public static final Map<e, cl> d;
    private static final dd e = new dd("Imprint");
    private static final ct f = new ct("property", df.k, 1);
    private static final ct g = new ct("version", (byte) 8, 2);
    private static final ct h = new ct("checksum", (byte) 11, 3);
    private static final Map<Class<? extends dg>, dh> i;
    private static final int j = 0;
    public Map<String, bd> a;
    public int b;
    public String c;
    private byte k;

    static {
        HashMap hashMap = new HashMap();
        i = hashMap;
        hashMap.put(di.class, new b());
        i.put(dj.class, new d());
        EnumMap enumMap = new EnumMap(e.class);
        enumMap.put((EnumMap) e.PROPERTY, (e) new cl("property", (byte) 1, new co(df.k, new cm((byte) 11), new cq((byte) 12, bd.class))));
        enumMap.put((EnumMap) e.VERSION, (e) new cl("version", (byte) 1, new cm((byte) 8)));
        enumMap.put((EnumMap) e.CHECKSUM, (e) new cl("checksum", (byte) 1, new cm((byte) 11)));
        Map<e, cl> unmodifiableMap = Collections.unmodifiableMap(enumMap);
        d = unmodifiableMap;
        cl.a(bc.class, unmodifiableMap);
    }

    /* compiled from: Imprint.java */
    /* loaded from: classes.dex */
    public enum e implements cg {
        PROPERTY(1, "property"),
        VERSION(2, "version"),
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
                return PROPERTY;
            }
            if (i == 2) {
                return VERSION;
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

    public bc() {
        this.k = (byte) 0;
    }

    public bc(Map<String, bd> map, int i2, String str) {
        this();
        this.a = map;
        this.b = i2;
        b(true);
        this.c = str;
    }

    public bc(bc bcVar) {
        this.k = (byte) 0;
        this.k = bcVar.k;
        if (bcVar.f()) {
            HashMap hashMap = new HashMap();
            for (Map.Entry<String, bd> entry : bcVar.a.entrySet()) {
                hashMap.put(entry.getKey(), new bd(entry.getValue()));
            }
            this.a = hashMap;
        }
        this.b = bcVar.b;
        if (bcVar.m()) {
            this.c = bcVar.c;
        }
    }

    @Override // u.aly.bz
    /* renamed from: a */
    public bc g() {
        return new bc(this);
    }

    @Override // u.aly.bz
    public void b() {
        this.a = null;
        b(false);
        this.b = 0;
        this.c = null;
    }

    public int c() {
        Map<String, bd> map = this.a;
        if (map == null) {
            return 0;
        }
        return map.size();
    }

    public void a(String str, bd bdVar) {
        if (this.a == null) {
            this.a = new HashMap();
        }
        this.a.put(str, bdVar);
    }

    public Map<String, bd> d() {
        return this.a;
    }

    public bc a(Map<String, bd> map) {
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
        return this.b;
    }

    public bc a(int i2) {
        this.b = i2;
        b(true);
        return this;
    }

    public void i() {
        this.k = bw.b(this.k, 0);
    }

    public boolean j() {
        return bw.a(this.k, 0);
    }

    public void b(boolean z) {
        this.k = bw.a(this.k, 0, z);
    }

    public String k() {
        return this.c;
    }

    public bc a(String str) {
        this.c = str;
        return this;
    }

    public void l() {
        this.c = null;
    }

    public boolean m() {
        return this.c != null;
    }

    public void c(boolean z) {
        if (z) {
            return;
        }
        this.c = null;
    }

    @Override // u.aly.bz
    /* renamed from: c */
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
        StringBuilder sb = new StringBuilder("Imprint(");
        sb.append("property:");
        Map<String, bd> map = this.a;
        if (map == null) {
            sb.append(Constants.NULL_VERSION_ID);
        } else {
            sb.append(map);
        }
        sb.append(", ");
        sb.append("version:");
        sb.append(this.b);
        sb.append(", ");
        sb.append("checksum:");
        String str = this.c;
        if (str == null) {
            sb.append(Constants.NULL_VERSION_ID);
        } else {
            sb.append(str);
        }
        sb.append(")");
        return sb.toString();
    }

    public void n() throws cf {
        if (this.a == null) {
            throw new cz("Required field 'property' was not present! Struct: " + toString());
        }
        if (this.c != null) {
            return;
        }
        throw new cz("Required field 'checksum' was not present! Struct: " + toString());
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

    /* compiled from: Imprint.java */
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

    /* compiled from: Imprint.java */
    /* loaded from: classes.dex */
    private static class a extends di<bc> {
        private a() {
        }

        /* synthetic */ a(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // u.aly.dg
        /* renamed from: a */
        public void b(cy cyVar, bc bcVar) throws cf {
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
                            if (l.b == 11) {
                                bcVar.c = cyVar.z();
                                bcVar.c(true);
                            } else {
                                db.a(cyVar, l.b);
                            }
                        } else {
                            db.a(cyVar, l.b);
                        }
                    } else if (l.b == 8) {
                        bcVar.b = cyVar.w();
                        bcVar.b(true);
                    } else {
                        db.a(cyVar, l.b);
                    }
                } else if (l.b == 13) {
                    cv n = cyVar.n();
                    bcVar.a = new HashMap(n.c * 2);
                    for (int i = 0; i < n.c; i++) {
                        String z = cyVar.z();
                        bd bdVar = new bd();
                        bdVar.a(cyVar);
                        bcVar.a.put(z, bdVar);
                    }
                    cyVar.o();
                    bcVar.a(true);
                } else {
                    db.a(cyVar, l.b);
                }
                cyVar.m();
            }
            cyVar.k();
            if (!bcVar.j()) {
                throw new cz("Required field 'version' was not found in serialized data! Struct: " + toString());
            }
            bcVar.n();
        }

        @Override // u.aly.dg
        /* renamed from: b */
        public void a(cy cyVar, bc bcVar) throws cf {
            bcVar.n();
            cyVar.a(bc.e);
            if (bcVar.a != null) {
                cyVar.a(bc.f);
                cyVar.a(new cv((byte) 11, (byte) 12, bcVar.a.size()));
                for (Map.Entry<String, bd> entry : bcVar.a.entrySet()) {
                    cyVar.a(entry.getKey());
                    entry.getValue().b(cyVar);
                }
                cyVar.e();
                cyVar.c();
            }
            cyVar.a(bc.g);
            cyVar.a(bcVar.b);
            cyVar.c();
            if (bcVar.c != null) {
                cyVar.a(bc.h);
                cyVar.a(bcVar.c);
                cyVar.c();
            }
            cyVar.d();
            cyVar.b();
        }
    }

    /* compiled from: Imprint.java */
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

    /* compiled from: Imprint.java */
    /* loaded from: classes.dex */
    private static class c extends dj<bc> {
        private c() {
        }

        /* synthetic */ c(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // u.aly.dg
        public void a(cy cyVar, bc bcVar) throws cf {
            de deVar = (de) cyVar;
            deVar.a(bcVar.a.size());
            for (Map.Entry<String, bd> entry : bcVar.a.entrySet()) {
                deVar.a(entry.getKey());
                entry.getValue().b(deVar);
            }
            deVar.a(bcVar.b);
            deVar.a(bcVar.c);
        }

        @Override // u.aly.dg
        public void b(cy cyVar, bc bcVar) throws cf {
            de deVar = (de) cyVar;
            cv cvVar = new cv((byte) 11, (byte) 12, deVar.w());
            bcVar.a = new HashMap(cvVar.c * 2);
            for (int i = 0; i < cvVar.c; i++) {
                String z = deVar.z();
                bd bdVar = new bd();
                bdVar.a(deVar);
                bcVar.a.put(z, bdVar);
            }
            bcVar.a(true);
            bcVar.b = deVar.w();
            bcVar.b(true);
            bcVar.c = deVar.z();
            bcVar.c(true);
        }
    }
}
