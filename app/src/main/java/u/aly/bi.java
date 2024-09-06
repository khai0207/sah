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

/* compiled from: Page.java */
/* loaded from: classes.dex */
public class bi implements Serializable, Cloneable, bz<bi, e> {
    public static final Map<e, cl> c;
    private static final dd d = new dd("Page");
    private static final ct e = new ct("page_name", (byte) 11, 1);
    private static final ct f = new ct("duration", (byte) 10, 2);
    private static final Map<Class<? extends dg>, dh> g;
    private static final int h = 0;
    public String a;
    public long b;
    private byte i;

    static {
        HashMap hashMap = new HashMap();
        g = hashMap;
        hashMap.put(di.class, new b());
        g.put(dj.class, new d());
        EnumMap enumMap = new EnumMap(e.class);
        enumMap.put((EnumMap) e.PAGE_NAME, (e) new cl("page_name", (byte) 1, new cm((byte) 11)));
        enumMap.put((EnumMap) e.DURATION, (e) new cl("duration", (byte) 1, new cm((byte) 10)));
        Map<e, cl> unmodifiableMap = Collections.unmodifiableMap(enumMap);
        c = unmodifiableMap;
        cl.a(bi.class, unmodifiableMap);
    }

    /* compiled from: Page.java */
    /* loaded from: classes.dex */
    public enum e implements cg {
        PAGE_NAME(1, "page_name"),
        DURATION(2, "duration");

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
                return PAGE_NAME;
            }
            if (i != 2) {
                return null;
            }
            return DURATION;
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

    public bi() {
        this.i = (byte) 0;
    }

    public bi(String str, long j) {
        this();
        this.a = str;
        this.b = j;
        b(true);
    }

    public bi(bi biVar) {
        this.i = (byte) 0;
        this.i = biVar.i;
        if (biVar.e()) {
            this.a = biVar.a;
        }
        this.b = biVar.b;
    }

    @Override // u.aly.bz
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public bi g() {
        return new bi(this);
    }

    @Override // u.aly.bz
    public void b() {
        this.a = null;
        b(false);
        this.b = 0L;
    }

    public String c() {
        return this.a;
    }

    public bi a(String str) {
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

    public bi a(long j) {
        this.b = j;
        b(true);
        return this;
    }

    public void h() {
        this.i = bw.b(this.i, 0);
    }

    public boolean i() {
        return bw.a(this.i, 0);
    }

    public void b(boolean z) {
        this.i = bw.a(this.i, 0, z);
    }

    @Override // u.aly.bz
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public e b(int i) {
        return e.a(i);
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
        StringBuilder sb = new StringBuilder("Page(");
        sb.append("page_name:");
        String str = this.a;
        if (str == null) {
            sb.append(Constants.NULL_VERSION_ID);
        } else {
            sb.append(str);
        }
        sb.append(", ");
        sb.append("duration:");
        sb.append(this.b);
        sb.append(")");
        return sb.toString();
    }

    public void j() throws cf {
        if (this.a != null) {
            return;
        }
        throw new cz("Required field 'page_name' was not present! Struct: " + toString());
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
            this.i = (byte) 0;
            a(new cs(new dk(objectInputStream)));
        } catch (cf e2) {
            throw new IOException(e2.getMessage());
        }
    }

    /* compiled from: Page.java */
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

    /* compiled from: Page.java */
    /* loaded from: classes.dex */
    private static class a extends di<bi> {
        private a() {
        }

        @Override // u.aly.dg
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void b(cy cyVar, bi biVar) throws cf {
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
                            biVar.b = cyVar.x();
                            biVar.b(true);
                        } else {
                            db.a(cyVar, l.b);
                        }
                    } else {
                        db.a(cyVar, l.b);
                    }
                } else if (l.b == 11) {
                    biVar.a = cyVar.z();
                    biVar.a(true);
                } else {
                    db.a(cyVar, l.b);
                }
                cyVar.m();
            }
            cyVar.k();
            if (!biVar.i()) {
                throw new cz("Required field 'duration' was not found in serialized data! Struct: " + toString());
            }
            biVar.j();
        }

        @Override // u.aly.dg
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(cy cyVar, bi biVar) throws cf {
            biVar.j();
            cyVar.a(bi.d);
            if (biVar.a != null) {
                cyVar.a(bi.e);
                cyVar.a(biVar.a);
                cyVar.c();
            }
            cyVar.a(bi.f);
            cyVar.a(biVar.b);
            cyVar.c();
            cyVar.d();
            cyVar.b();
        }
    }

    /* compiled from: Page.java */
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

    /* compiled from: Page.java */
    /* loaded from: classes.dex */
    private static class c extends dj<bi> {
        private c() {
        }

        @Override // u.aly.dg
        public void a(cy cyVar, bi biVar) throws cf {
            de deVar = (de) cyVar;
            deVar.a(biVar.a);
            deVar.a(biVar.b);
        }

        @Override // u.aly.dg
        public void b(cy cyVar, bi biVar) throws cf {
            de deVar = (de) cyVar;
            biVar.a = deVar.z();
            biVar.a(true);
            biVar.b = deVar.x();
            biVar.b(true);
        }
    }
}
