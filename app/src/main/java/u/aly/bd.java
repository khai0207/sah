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

/* compiled from: ImprintValue.java */
/* loaded from: classes.dex */
public class bd implements Serializable, Cloneable, bz<bd, e> {
    public static final Map<e, cl> d;
    private static final dd e = new dd("ImprintValue");
    private static final ct f = new ct("value", (byte) 11, 1);
    private static final ct g = new ct("ts", (byte) 10, 2);
    private static final ct h = new ct("guid", (byte) 11, 3);
    private static final Map<Class<? extends dg>, dh> i;
    private static final int j = 0;
    public String a;
    public long b;
    public String c;
    private byte k;
    private e[] l;

    static {
        HashMap hashMap = new HashMap();
        i = hashMap;
        hashMap.put(di.class, new b());
        i.put(dj.class, new d());
        EnumMap enumMap = new EnumMap(e.class);
        enumMap.put((EnumMap) e.VALUE, (e) new cl("value", (byte) 2, new cm((byte) 11)));
        enumMap.put((EnumMap) e.TS, (e) new cl("ts", (byte) 1, new cm((byte) 10)));
        enumMap.put((EnumMap) e.GUID, (e) new cl("guid", (byte) 1, new cm((byte) 11)));
        Map<e, cl> unmodifiableMap = Collections.unmodifiableMap(enumMap);
        d = unmodifiableMap;
        cl.a(bd.class, unmodifiableMap);
    }

    /* compiled from: ImprintValue.java */
    /* loaded from: classes.dex */
    public enum e implements cg {
        VALUE(1, "value"),
        TS(2, "ts"),
        GUID(3, "guid");

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
                return VALUE;
            }
            if (i == 2) {
                return TS;
            }
            if (i != 3) {
                return null;
            }
            return GUID;
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

    public bd() {
        this.k = (byte) 0;
        this.l = new e[]{e.VALUE};
    }

    public bd(long j2, String str) {
        this();
        this.b = j2;
        b(true);
        this.c = str;
    }

    public bd(bd bdVar) {
        this.k = (byte) 0;
        this.l = new e[]{e.VALUE};
        this.k = bdVar.k;
        if (bdVar.e()) {
            this.a = bdVar.a;
        }
        this.b = bdVar.b;
        if (bdVar.l()) {
            this.c = bdVar.c;
        }
    }

    @Override // u.aly.bz
    /* renamed from: a */
    public bd g() {
        return new bd(this);
    }

    @Override // u.aly.bz
    public void b() {
        this.a = null;
        b(false);
        this.b = 0L;
        this.c = null;
    }

    public String c() {
        return this.a;
    }

    public bd a(String str) {
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

    public bd a(long j2) {
        this.b = j2;
        b(true);
        return this;
    }

    public void h() {
        this.k = bw.b(this.k, 0);
    }

    public boolean i() {
        return bw.a(this.k, 0);
    }

    public void b(boolean z) {
        this.k = bw.a(this.k, 0, z);
    }

    public String j() {
        return this.c;
    }

    public bd b(String str) {
        this.c = str;
        return this;
    }

    public void k() {
        this.c = null;
    }

    public boolean l() {
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
        boolean z;
        StringBuilder sb = new StringBuilder("ImprintValue(");
        if (e()) {
            sb.append("value:");
            String str = this.a;
            if (str == null) {
                sb.append(Constants.NULL_VERSION_ID);
            } else {
                sb.append(str);
            }
            z = false;
        } else {
            z = true;
        }
        if (!z) {
            sb.append(", ");
        }
        sb.append("ts:");
        sb.append(this.b);
        sb.append(", ");
        sb.append("guid:");
        String str2 = this.c;
        if (str2 == null) {
            sb.append(Constants.NULL_VERSION_ID);
        } else {
            sb.append(str2);
        }
        sb.append(")");
        return sb.toString();
    }

    public void m() throws cf {
        if (this.c != null) {
            return;
        }
        throw new cz("Required field 'guid' was not present! Struct: " + toString());
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

    /* compiled from: ImprintValue.java */
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

    /* compiled from: ImprintValue.java */
    /* loaded from: classes.dex */
    private static class a extends di<bd> {
        private a() {
        }

        /* synthetic */ a(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // u.aly.dg
        /* renamed from: a */
        public void b(cy cyVar, bd bdVar) throws cf {
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
                                bdVar.c = cyVar.z();
                                bdVar.c(true);
                            } else {
                                db.a(cyVar, l.b);
                            }
                        } else {
                            db.a(cyVar, l.b);
                        }
                    } else if (l.b == 10) {
                        bdVar.b = cyVar.x();
                        bdVar.b(true);
                    } else {
                        db.a(cyVar, l.b);
                    }
                } else if (l.b == 11) {
                    bdVar.a = cyVar.z();
                    bdVar.a(true);
                } else {
                    db.a(cyVar, l.b);
                }
                cyVar.m();
            }
            cyVar.k();
            if (!bdVar.i()) {
                throw new cz("Required field 'ts' was not found in serialized data! Struct: " + toString());
            }
            bdVar.m();
        }

        @Override // u.aly.dg
        /* renamed from: b */
        public void a(cy cyVar, bd bdVar) throws cf {
            bdVar.m();
            cyVar.a(bd.e);
            if (bdVar.a != null && bdVar.e()) {
                cyVar.a(bd.f);
                cyVar.a(bdVar.a);
                cyVar.c();
            }
            cyVar.a(bd.g);
            cyVar.a(bdVar.b);
            cyVar.c();
            if (bdVar.c != null) {
                cyVar.a(bd.h);
                cyVar.a(bdVar.c);
                cyVar.c();
            }
            cyVar.d();
            cyVar.b();
        }
    }

    /* compiled from: ImprintValue.java */
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

    /* compiled from: ImprintValue.java */
    /* loaded from: classes.dex */
    private static class c extends dj<bd> {
        private c() {
        }

        /* synthetic */ c(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // u.aly.dg
        public void a(cy cyVar, bd bdVar) throws cf {
            de deVar = (de) cyVar;
            deVar.a(bdVar.b);
            deVar.a(bdVar.c);
            BitSet bitSet = new BitSet();
            if (bdVar.e()) {
                bitSet.set(0);
            }
            deVar.a(bitSet, 1);
            if (bdVar.e()) {
                deVar.a(bdVar.a);
            }
        }

        @Override // u.aly.dg
        public void b(cy cyVar, bd bdVar) throws cf {
            de deVar = (de) cyVar;
            bdVar.b = deVar.x();
            bdVar.b(true);
            bdVar.c = deVar.z();
            bdVar.c(true);
            if (deVar.b(1).get(0)) {
                bdVar.a = deVar.z();
                bdVar.a(true);
            }
        }
    }
}
