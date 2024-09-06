package u.aly;

import com.android.pc.util.Handler_System;
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

/* compiled from: Resolution.java */
/* loaded from: classes.dex */
public class bk implements Serializable, Cloneable, bz<bk, e> {
    public static final Map<e, cl> c;
    private static final dd d = new dd("Resolution");
    private static final ct e = new ct(Handler_System.systemHeight, (byte) 8, 1);
    private static final ct f = new ct(Handler_System.systemWidth, (byte) 8, 2);
    private static final Map<Class<? extends dg>, dh> g;
    private static final int h = 0;
    private static final int i = 1;
    public int a;
    public int b;
    private byte j;

    public void j() throws cf {
    }

    static {
        HashMap hashMap = new HashMap();
        g = hashMap;
        hashMap.put(di.class, new b());
        g.put(dj.class, new d());
        EnumMap enumMap = new EnumMap(e.class);
        enumMap.put((EnumMap) e.HEIGHT, (e) new cl(Handler_System.systemHeight, (byte) 1, new cm((byte) 8)));
        enumMap.put((EnumMap) e.WIDTH, (e) new cl(Handler_System.systemWidth, (byte) 1, new cm((byte) 8)));
        Map<e, cl> unmodifiableMap = Collections.unmodifiableMap(enumMap);
        c = unmodifiableMap;
        cl.a(bk.class, unmodifiableMap);
    }

    /* compiled from: Resolution.java */
    /* loaded from: classes.dex */
    public enum e implements cg {
        HEIGHT(1, Handler_System.systemHeight),
        WIDTH(2, Handler_System.systemWidth);

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
                return HEIGHT;
            }
            if (i != 2) {
                return null;
            }
            return WIDTH;
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

    public bk() {
        this.j = (byte) 0;
    }

    public bk(int i2, int i3) {
        this();
        this.a = i2;
        a(true);
        this.b = i3;
        b(true);
    }

    public bk(bk bkVar) {
        this.j = (byte) 0;
        this.j = bkVar.j;
        this.a = bkVar.a;
        this.b = bkVar.b;
    }

    @Override // u.aly.bz
    /* renamed from: a */
    public bk g() {
        return new bk(this);
    }

    @Override // u.aly.bz
    public void b() {
        a(false);
        this.a = 0;
        b(false);
        this.b = 0;
    }

    public int c() {
        return this.a;
    }

    public bk a(int i2) {
        this.a = i2;
        a(true);
        return this;
    }

    public void d() {
        this.j = bw.b(this.j, 0);
    }

    public boolean e() {
        return bw.a(this.j, 0);
    }

    public void a(boolean z) {
        this.j = bw.a(this.j, 0, z);
    }

    public int f() {
        return this.b;
    }

    public bk c(int i2) {
        this.b = i2;
        b(true);
        return this;
    }

    public void h() {
        this.j = bw.b(this.j, 1);
    }

    public boolean i() {
        return bw.a(this.j, 1);
    }

    public void b(boolean z) {
        this.j = bw.a(this.j, 1, z);
    }

    @Override // u.aly.bz
    /* renamed from: d */
    public e b(int i2) {
        return e.a(i2);
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
        return "Resolution(height:" + this.a + ", width:" + this.b + ")";
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
            this.j = (byte) 0;
            a(new cs(new dk(objectInputStream)));
        } catch (cf e2) {
            throw new IOException(e2.getMessage());
        }
    }

    /* compiled from: Resolution.java */
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

    /* compiled from: Resolution.java */
    /* loaded from: classes.dex */
    private static class a extends di<bk> {
        private a() {
        }

        /* synthetic */ a(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // u.aly.dg
        /* renamed from: a */
        public void b(cy cyVar, bk bkVar) throws cf {
            cyVar.j();
            while (true) {
                ct l = cyVar.l();
                if (l.b == 0) {
                    break;
                }
                short s = l.c;
                if (s != 1) {
                    if (s == 2) {
                        if (l.b == 8) {
                            bkVar.b = cyVar.w();
                            bkVar.b(true);
                        } else {
                            db.a(cyVar, l.b);
                        }
                    } else {
                        db.a(cyVar, l.b);
                    }
                } else if (l.b == 8) {
                    bkVar.a = cyVar.w();
                    bkVar.a(true);
                } else {
                    db.a(cyVar, l.b);
                }
                cyVar.m();
            }
            cyVar.k();
            if (!bkVar.e()) {
                throw new cz("Required field 'height' was not found in serialized data! Struct: " + toString());
            }
            if (!bkVar.i()) {
                throw new cz("Required field 'width' was not found in serialized data! Struct: " + toString());
            }
            bkVar.j();
        }

        @Override // u.aly.dg
        /* renamed from: b */
        public void a(cy cyVar, bk bkVar) throws cf {
            bkVar.j();
            cyVar.a(bk.d);
            cyVar.a(bk.e);
            cyVar.a(bkVar.a);
            cyVar.c();
            cyVar.a(bk.f);
            cyVar.a(bkVar.b);
            cyVar.c();
            cyVar.d();
            cyVar.b();
        }
    }

    /* compiled from: Resolution.java */
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

    /* compiled from: Resolution.java */
    /* loaded from: classes.dex */
    private static class c extends dj<bk> {
        private c() {
        }

        /* synthetic */ c(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // u.aly.dg
        public void a(cy cyVar, bk bkVar) throws cf {
            de deVar = (de) cyVar;
            deVar.a(bkVar.a);
            deVar.a(bkVar.b);
        }

        @Override // u.aly.dg
        public void b(cy cyVar, bk bkVar) throws cf {
            de deVar = (de) cyVar;
            bkVar.a = deVar.w();
            bkVar.a(true);
            bkVar.b = deVar.w();
            bkVar.b(true);
        }
    }
}
