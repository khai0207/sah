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

/* compiled from: IdSnapshot.java */
/* loaded from: classes.dex */
public class ba implements Serializable, Cloneable, bz<ba, e> {
    public static final Map<e, cl> d;
    private static final dd e = new dd("IdSnapshot");
    private static final ct f = new ct("identity", (byte) 11, 1);
    private static final ct g = new ct("ts", (byte) 10, 2);
    private static final ct h = new ct("version", (byte) 8, 3);
    private static final Map<Class<? extends dg>, dh> i;
    private static final int j = 0;
    private static final int k = 1;
    public String a;
    public long b;
    public int c;
    private byte l;

    static {
        HashMap hashMap = new HashMap();
        i = hashMap;
        hashMap.put(di.class, new b());
        i.put(dj.class, new d());
        EnumMap enumMap = new EnumMap(e.class);
        enumMap.put((EnumMap) e.IDENTITY, (e) new cl("identity", (byte) 1, new cm((byte) 11)));
        enumMap.put((EnumMap) e.TS, (e) new cl("ts", (byte) 1, new cm((byte) 10)));
        enumMap.put((EnumMap) e.VERSION, (e) new cl("version", (byte) 1, new cm((byte) 8)));
        Map<e, cl> unmodifiableMap = Collections.unmodifiableMap(enumMap);
        d = unmodifiableMap;
        cl.a(ba.class, unmodifiableMap);
    }

    /* compiled from: IdSnapshot.java */
    /* loaded from: classes.dex */
    public enum e implements cg {
        IDENTITY(1, "identity"),
        TS(2, "ts"),
        VERSION(3, "version");

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
                return IDENTITY;
            }
            if (i == 2) {
                return TS;
            }
            if (i != 3) {
                return null;
            }
            return VERSION;
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

    public ba() {
        this.l = (byte) 0;
    }

    public ba(String str, long j2, int i2) {
        this();
        this.a = str;
        this.b = j2;
        b(true);
        this.c = i2;
        c(true);
    }

    public ba(ba baVar) {
        this.l = (byte) 0;
        this.l = baVar.l;
        if (baVar.e()) {
            this.a = baVar.a;
        }
        this.b = baVar.b;
        this.c = baVar.c;
    }

    @Override // u.aly.bz
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ba g() {
        return new ba(this);
    }

    @Override // u.aly.bz
    public void b() {
        this.a = null;
        b(false);
        this.b = 0L;
        c(false);
        this.c = 0;
    }

    public String c() {
        return this.a;
    }

    public ba a(String str) {
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

    public long f() {
        return this.b;
    }

    public ba a(long j2) {
        this.b = j2;
        b(true);
        return this;
    }

    public void h() {
        this.l = bw.b(this.l, 0);
    }

    public boolean i() {
        return bw.a(this.l, 0);
    }

    public void b(boolean z) {
        this.l = bw.a(this.l, 0, z);
    }

    public int j() {
        return this.c;
    }

    public ba a(int i2) {
        this.c = i2;
        c(true);
        return this;
    }

    public void k() {
        this.l = bw.b(this.l, 1);
    }

    public boolean l() {
        return bw.a(this.l, 1);
    }

    public void c(boolean z) {
        this.l = bw.a(this.l, 1, z);
    }

    @Override // u.aly.bz
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
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
        StringBuilder sb = new StringBuilder("IdSnapshot(");
        sb.append("identity:");
        String str = this.a;
        if (str == null) {
            sb.append(Constants.NULL_VERSION_ID);
        } else {
            sb.append(str);
        }
        sb.append(", ");
        sb.append("ts:");
        sb.append(this.b);
        sb.append(", ");
        sb.append("version:");
        sb.append(this.c);
        sb.append(")");
        return sb.toString();
    }

    public void m() throws cf {
        if (this.a != null) {
            return;
        }
        throw new cz("Required field 'identity' was not present! Struct: " + toString());
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
            this.l = (byte) 0;
            a(new cs(new dk(objectInputStream)));
        } catch (cf e2) {
            throw new IOException(e2.getMessage());
        }
    }

    /* compiled from: IdSnapshot.java */
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

    /* compiled from: IdSnapshot.java */
    /* loaded from: classes.dex */
    private static class a extends di<ba> {
        private a() {
        }

        @Override // u.aly.dg
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void b(cy cyVar, ba baVar) throws cf {
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
                                baVar.c = cyVar.w();
                                baVar.c(true);
                            } else {
                                db.a(cyVar, l.b);
                            }
                        } else {
                            db.a(cyVar, l.b);
                        }
                    } else if (l.b == 10) {
                        baVar.b = cyVar.x();
                        baVar.b(true);
                    } else {
                        db.a(cyVar, l.b);
                    }
                } else if (l.b == 11) {
                    baVar.a = cyVar.z();
                    baVar.a(true);
                } else {
                    db.a(cyVar, l.b);
                }
                cyVar.m();
            }
            cyVar.k();
            if (!baVar.i()) {
                throw new cz("Required field 'ts' was not found in serialized data! Struct: " + toString());
            }
            if (!baVar.l()) {
                throw new cz("Required field 'version' was not found in serialized data! Struct: " + toString());
            }
            baVar.m();
        }

        @Override // u.aly.dg
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(cy cyVar, ba baVar) throws cf {
            baVar.m();
            cyVar.a(ba.e);
            if (baVar.a != null) {
                cyVar.a(ba.f);
                cyVar.a(baVar.a);
                cyVar.c();
            }
            cyVar.a(ba.g);
            cyVar.a(baVar.b);
            cyVar.c();
            cyVar.a(ba.h);
            cyVar.a(baVar.c);
            cyVar.c();
            cyVar.d();
            cyVar.b();
        }
    }

    /* compiled from: IdSnapshot.java */
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

    /* compiled from: IdSnapshot.java */
    /* loaded from: classes.dex */
    private static class c extends dj<ba> {
        private c() {
        }

        @Override // u.aly.dg
        public void a(cy cyVar, ba baVar) throws cf {
            de deVar = (de) cyVar;
            deVar.a(baVar.a);
            deVar.a(baVar.b);
            deVar.a(baVar.c);
        }

        @Override // u.aly.dg
        public void b(cy cyVar, ba baVar) throws cf {
            de deVar = (de) cyVar;
            baVar.a = deVar.z();
            baVar.a(true);
            baVar.b = deVar.x();
            baVar.b(true);
            baVar.c = deVar.w();
            baVar.c(true);
        }
    }
}
