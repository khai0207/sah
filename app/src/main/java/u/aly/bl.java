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

/* compiled from: Response.java */
/* loaded from: classes.dex */
public class bl implements Serializable, Cloneable, bz<bl, e> {
    public static final Map<e, cl> d;
    private static final dd e = new dd("Response");
    private static final ct f = new ct("resp_code", (byte) 8, 1);
    private static final ct g = new ct("msg", (byte) 11, 2);
    private static final ct h = new ct("imprint", (byte) 12, 3);
    private static final Map<Class<? extends dg>, dh> i;
    private static final int j = 0;
    public int a;
    public String b;
    public bc c;
    private byte k;
    private e[] l;

    static {
        HashMap hashMap = new HashMap();
        i = hashMap;
        hashMap.put(di.class, new b());
        i.put(dj.class, new d());
        EnumMap enumMap = new EnumMap(e.class);
        enumMap.put((EnumMap) e.RESP_CODE, (e) new cl("resp_code", (byte) 1, new cm((byte) 8)));
        enumMap.put((EnumMap) e.MSG, (e) new cl("msg", (byte) 2, new cm((byte) 11)));
        enumMap.put((EnumMap) e.IMPRINT, (e) new cl("imprint", (byte) 2, new cq((byte) 12, bc.class)));
        Map<e, cl> unmodifiableMap = Collections.unmodifiableMap(enumMap);
        d = unmodifiableMap;
        cl.a(bl.class, unmodifiableMap);
    }

    /* compiled from: Response.java */
    /* loaded from: classes.dex */
    public enum e implements cg {
        RESP_CODE(1, "resp_code"),
        MSG(2, "msg"),
        IMPRINT(3, "imprint");

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
                return RESP_CODE;
            }
            if (i == 2) {
                return MSG;
            }
            if (i != 3) {
                return null;
            }
            return IMPRINT;
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

    public bl() {
        this.k = (byte) 0;
        this.l = new e[]{e.MSG, e.IMPRINT};
    }

    public bl(int i2) {
        this();
        this.a = i2;
        a(true);
    }

    public bl(bl blVar) {
        this.k = (byte) 0;
        this.l = new e[]{e.MSG, e.IMPRINT};
        this.k = blVar.k;
        this.a = blVar.a;
        if (blVar.i()) {
            this.b = blVar.b;
        }
        if (blVar.l()) {
            this.c = new bc(blVar.c);
        }
    }

    @Override // u.aly.bz
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public bl g() {
        return new bl(this);
    }

    @Override // u.aly.bz
    public void b() {
        a(false);
        this.a = 0;
        this.b = null;
        this.c = null;
    }

    public int c() {
        return this.a;
    }

    public bl a(int i2) {
        this.a = i2;
        a(true);
        return this;
    }

    public void d() {
        this.k = bw.b(this.k, 0);
    }

    public boolean e() {
        return bw.a(this.k, 0);
    }

    public void a(boolean z) {
        this.k = bw.a(this.k, 0, z);
    }

    public String f() {
        return this.b;
    }

    public bl a(String str) {
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

    public bc j() {
        return this.c;
    }

    public bl a(bc bcVar) {
        this.c = bcVar;
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
        StringBuilder sb = new StringBuilder("Response(");
        sb.append("resp_code:");
        sb.append(this.a);
        if (i()) {
            sb.append(", ");
            sb.append("msg:");
            String str = this.b;
            if (str == null) {
                sb.append(Constants.NULL_VERSION_ID);
            } else {
                sb.append(str);
            }
        }
        if (l()) {
            sb.append(", ");
            sb.append("imprint:");
            bc bcVar = this.c;
            if (bcVar == null) {
                sb.append(Constants.NULL_VERSION_ID);
            } else {
                sb.append(bcVar);
            }
        }
        sb.append(")");
        return sb.toString();
    }

    public void m() throws cf {
        bc bcVar = this.c;
        if (bcVar != null) {
            bcVar.n();
        }
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

    /* compiled from: Response.java */
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

    /* compiled from: Response.java */
    /* loaded from: classes.dex */
    private static class a extends di<bl> {
        private a() {
        }

        @Override // u.aly.dg
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void b(cy cyVar, bl blVar) throws cf {
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
                            if (l.b == 12) {
                                blVar.c = new bc();
                                blVar.c.a(cyVar);
                                blVar.c(true);
                            } else {
                                db.a(cyVar, l.b);
                            }
                        } else {
                            db.a(cyVar, l.b);
                        }
                    } else if (l.b == 11) {
                        blVar.b = cyVar.z();
                        blVar.b(true);
                    } else {
                        db.a(cyVar, l.b);
                    }
                } else if (l.b == 8) {
                    blVar.a = cyVar.w();
                    blVar.a(true);
                } else {
                    db.a(cyVar, l.b);
                }
                cyVar.m();
            }
            cyVar.k();
            if (!blVar.e()) {
                throw new cz("Required field 'resp_code' was not found in serialized data! Struct: " + toString());
            }
            blVar.m();
        }

        @Override // u.aly.dg
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(cy cyVar, bl blVar) throws cf {
            blVar.m();
            cyVar.a(bl.e);
            cyVar.a(bl.f);
            cyVar.a(blVar.a);
            cyVar.c();
            if (blVar.b != null && blVar.i()) {
                cyVar.a(bl.g);
                cyVar.a(blVar.b);
                cyVar.c();
            }
            if (blVar.c != null && blVar.l()) {
                cyVar.a(bl.h);
                blVar.c.b(cyVar);
                cyVar.c();
            }
            cyVar.d();
            cyVar.b();
        }
    }

    /* compiled from: Response.java */
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

    /* compiled from: Response.java */
    /* loaded from: classes.dex */
    private static class c extends dj<bl> {
        private c() {
        }

        @Override // u.aly.dg
        public void a(cy cyVar, bl blVar) throws cf {
            de deVar = (de) cyVar;
            deVar.a(blVar.a);
            BitSet bitSet = new BitSet();
            if (blVar.i()) {
                bitSet.set(0);
            }
            if (blVar.l()) {
                bitSet.set(1);
            }
            deVar.a(bitSet, 2);
            if (blVar.i()) {
                deVar.a(blVar.b);
            }
            if (blVar.l()) {
                blVar.c.b(deVar);
            }
        }

        @Override // u.aly.dg
        public void b(cy cyVar, bl blVar) throws cf {
            de deVar = (de) cyVar;
            blVar.a = deVar.w();
            blVar.a(true);
            BitSet b = deVar.b(2);
            if (b.get(0)) {
                blVar.b = deVar.z();
                blVar.b(true);
            }
            if (b.get(1)) {
                blVar.c = new bc();
                blVar.c.a(deVar);
                blVar.c(true);
            }
        }
    }
}
