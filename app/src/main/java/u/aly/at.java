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

/* compiled from: ControlPolicy.java */
/* loaded from: classes.dex */
public class at implements Serializable, Cloneable, bz<at, e> {
    public static final Map<e, cl> b;
    private static final dd c = new dd("ControlPolicy");
    private static final ct d = new ct("latent", (byte) 12, 1);
    private static final Map<Class<? extends dg>, dh> e;
    public bf a;
    private e[] f;

    static {
        HashMap hashMap = new HashMap();
        e = hashMap;
        hashMap.put(di.class, new b());
        e.put(dj.class, new d());
        EnumMap enumMap = new EnumMap(e.class);
        enumMap.put((EnumMap) e.LATENT, (e) new cl("latent", (byte) 2, new cq((byte) 12, bf.class)));
        Map<e, cl> unmodifiableMap = Collections.unmodifiableMap(enumMap);
        b = unmodifiableMap;
        cl.a(at.class, unmodifiableMap);
    }

    /* compiled from: ControlPolicy.java */
    /* loaded from: classes.dex */
    public enum e implements cg {
        LATENT(1, "latent");

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
            return LATENT;
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

    public at() {
        this.f = new e[]{e.LATENT};
    }

    public at(at atVar) {
        this.f = new e[]{e.LATENT};
        if (atVar.e()) {
            this.a = new bf(atVar.a);
        }
    }

    @Override // u.aly.bz
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public at g() {
        return new at(this);
    }

    @Override // u.aly.bz
    public void b() {
        this.a = null;
    }

    public bf c() {
        return this.a;
    }

    public at a(bf bfVar) {
        this.a = bfVar;
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

    @Override // u.aly.bz
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
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
        StringBuilder sb = new StringBuilder("ControlPolicy(");
        if (e()) {
            sb.append("latent:");
            bf bfVar = this.a;
            if (bfVar == null) {
                sb.append(Constants.NULL_VERSION_ID);
            } else {
                sb.append(bfVar);
            }
        }
        sb.append(")");
        return sb.toString();
    }

    public void f() throws cf {
        bf bfVar = this.a;
        if (bfVar != null) {
            bfVar.j();
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
            a(new cs(new dk(objectInputStream)));
        } catch (cf e2) {
            throw new IOException(e2.getMessage());
        }
    }

    /* compiled from: ControlPolicy.java */
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

    /* compiled from: ControlPolicy.java */
    /* loaded from: classes.dex */
    private static class a extends di<at> {
        private a() {
        }

        @Override // u.aly.dg
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void b(cy cyVar, at atVar) throws cf {
            cyVar.j();
            while (true) {
                ct l = cyVar.l();
                if (l.b != 0) {
                    if (l.c == 1) {
                        if (l.b == 12) {
                            atVar.a = new bf();
                            atVar.a.a(cyVar);
                            atVar.a(true);
                        } else {
                            db.a(cyVar, l.b);
                        }
                    } else {
                        db.a(cyVar, l.b);
                    }
                    cyVar.m();
                } else {
                    cyVar.k();
                    atVar.f();
                    return;
                }
            }
        }

        @Override // u.aly.dg
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(cy cyVar, at atVar) throws cf {
            atVar.f();
            cyVar.a(at.c);
            if (atVar.a != null && atVar.e()) {
                cyVar.a(at.d);
                atVar.a.b(cyVar);
                cyVar.c();
            }
            cyVar.d();
            cyVar.b();
        }
    }

    /* compiled from: ControlPolicy.java */
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

    /* compiled from: ControlPolicy.java */
    /* loaded from: classes.dex */
    private static class c extends dj<at> {
        private c() {
        }

        @Override // u.aly.dg
        public void a(cy cyVar, at atVar) throws cf {
            de deVar = (de) cyVar;
            BitSet bitSet = new BitSet();
            if (atVar.e()) {
                bitSet.set(0);
            }
            deVar.a(bitSet, 1);
            if (atVar.e()) {
                atVar.a.b(deVar);
            }
        }

        @Override // u.aly.dg
        public void b(cy cyVar, at atVar) throws cf {
            de deVar = (de) cyVar;
            if (deVar.b(1).get(0)) {
                atVar.a = new bf();
                atVar.a.a(deVar);
                atVar.a(true);
            }
        }
    }
}
