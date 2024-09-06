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

/* compiled from: ActiveUser.java */
/* loaded from: classes.dex */
public class aq implements Serializable, Cloneable, bz<aq, e> {
    public static final Map<e, cl> c;
    private static final dd d = new dd("ActiveUser");
    private static final ct e = new ct("provider", (byte) 11, 1);
    private static final ct f = new ct("puid", (byte) 11, 2);
    private static final Map<Class<? extends dg>, dh> g;
    public String a;
    public String b;

    static {
        HashMap hashMap = new HashMap();
        g = hashMap;
        hashMap.put(di.class, new b());
        g.put(dj.class, new d());
        EnumMap enumMap = new EnumMap(e.class);
        enumMap.put((EnumMap) e.PROVIDER, (e) new cl("provider", (byte) 1, new cm((byte) 11)));
        enumMap.put((EnumMap) e.PUID, (e) new cl("puid", (byte) 1, new cm((byte) 11)));
        Map<e, cl> unmodifiableMap = Collections.unmodifiableMap(enumMap);
        c = unmodifiableMap;
        cl.a(aq.class, unmodifiableMap);
    }

    /* compiled from: ActiveUser.java */
    /* loaded from: classes.dex */
    public enum e implements cg {
        PROVIDER(1, "provider"),
        PUID(2, "puid");

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
                return PROVIDER;
            }
            if (i != 2) {
                return null;
            }
            return PUID;
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

    public aq() {
    }

    public aq(String str, String str2) {
        this();
        this.a = str;
        this.b = str2;
    }

    public aq(aq aqVar) {
        if (aqVar.e()) {
            this.a = aqVar.a;
        }
        if (aqVar.i()) {
            this.b = aqVar.b;
        }
    }

    @Override // u.aly.bz
    /* renamed from: a */
    public aq g() {
        return new aq(this);
    }

    @Override // u.aly.bz
    public void b() {
        this.a = null;
        this.b = null;
    }

    public String c() {
        return this.a;
    }

    public aq a(String str) {
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

    public String f() {
        return this.b;
    }

    public aq b(String str) {
        this.b = str;
        return this;
    }

    public void h() {
        this.b = null;
    }

    public boolean i() {
        return this.b != null;
    }

    public void b(boolean z) {
        if (z) {
            return;
        }
        this.b = null;
    }

    @Override // u.aly.bz
    /* renamed from: a */
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
        StringBuilder sb = new StringBuilder("ActiveUser(");
        sb.append("provider:");
        String str = this.a;
        if (str == null) {
            sb.append(Constants.NULL_VERSION_ID);
        } else {
            sb.append(str);
        }
        sb.append(", ");
        sb.append("puid:");
        String str2 = this.b;
        if (str2 == null) {
            sb.append(Constants.NULL_VERSION_ID);
        } else {
            sb.append(str2);
        }
        sb.append(")");
        return sb.toString();
    }

    public void j() throws cf {
        if (this.a == null) {
            throw new cz("Required field 'provider' was not present! Struct: " + toString());
        }
        if (this.b != null) {
            return;
        }
        throw new cz("Required field 'puid' was not present! Struct: " + toString());
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

    /* compiled from: ActiveUser.java */
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

    /* compiled from: ActiveUser.java */
    /* loaded from: classes.dex */
    private static class a extends di<aq> {
        private a() {
        }

        /* synthetic */ a(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // u.aly.dg
        /* renamed from: a */
        public void b(cy cyVar, aq aqVar) throws cf {
            cyVar.j();
            while (true) {
                ct l = cyVar.l();
                if (l.b != 0) {
                    short s = l.c;
                    if (s != 1) {
                        if (s == 2) {
                            if (l.b == 11) {
                                aqVar.b = cyVar.z();
                                aqVar.b(true);
                            } else {
                                db.a(cyVar, l.b);
                            }
                        } else {
                            db.a(cyVar, l.b);
                        }
                    } else if (l.b == 11) {
                        aqVar.a = cyVar.z();
                        aqVar.a(true);
                    } else {
                        db.a(cyVar, l.b);
                    }
                    cyVar.m();
                } else {
                    cyVar.k();
                    aqVar.j();
                    return;
                }
            }
        }

        @Override // u.aly.dg
        /* renamed from: b */
        public void a(cy cyVar, aq aqVar) throws cf {
            aqVar.j();
            cyVar.a(aq.d);
            if (aqVar.a != null) {
                cyVar.a(aq.e);
                cyVar.a(aqVar.a);
                cyVar.c();
            }
            if (aqVar.b != null) {
                cyVar.a(aq.f);
                cyVar.a(aqVar.b);
                cyVar.c();
            }
            cyVar.d();
            cyVar.b();
        }
    }

    /* compiled from: ActiveUser.java */
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

    /* compiled from: ActiveUser.java */
    /* loaded from: classes.dex */
    private static class c extends dj<aq> {
        private c() {
        }

        /* synthetic */ c(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // u.aly.dg
        public void a(cy cyVar, aq aqVar) throws cf {
            de deVar = (de) cyVar;
            deVar.a(aqVar.a);
            deVar.a(aqVar.b);
        }

        @Override // u.aly.dg
        public void b(cy cyVar, aq aqVar) throws cf {
            de deVar = (de) cyVar;
            aqVar.a = deVar.z();
            aqVar.a(true);
            aqVar.b = deVar.z();
            aqVar.b(true);
        }
    }
}
