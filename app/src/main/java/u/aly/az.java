package u.aly;

import com.iflytek.cloud.SpeechConstant;
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

/* compiled from: IdJournal.java */
/* loaded from: classes.dex */
public class az implements Serializable, Cloneable, bz<az, e> {
    public static final Map<e, cl> e;
    private static final dd f = new dd("IdJournal");
    private static final ct g = new ct(SpeechConstant.DOMAIN, (byte) 11, 1);
    private static final ct h = new ct("old_id", (byte) 11, 2);
    private static final ct i = new ct("new_id", (byte) 11, 3);
    private static final ct j = new ct("ts", (byte) 10, 4);
    private static final Map<Class<? extends dg>, dh> k;
    private static final int l = 0;
    public String a;
    public String b;
    public String c;
    public long d;
    private byte m;
    private e[] n;

    static {
        HashMap hashMap = new HashMap();
        k = hashMap;
        hashMap.put(di.class, new b());
        k.put(dj.class, new d());
        EnumMap enumMap = new EnumMap(e.class);
        enumMap.put((EnumMap) e.DOMAIN, (e) new cl(SpeechConstant.DOMAIN, (byte) 1, new cm((byte) 11)));
        enumMap.put((EnumMap) e.OLD_ID, (e) new cl("old_id", (byte) 2, new cm((byte) 11)));
        enumMap.put((EnumMap) e.NEW_ID, (e) new cl("new_id", (byte) 1, new cm((byte) 11)));
        enumMap.put((EnumMap) e.TS, (e) new cl("ts", (byte) 1, new cm((byte) 10)));
        Map<e, cl> unmodifiableMap = Collections.unmodifiableMap(enumMap);
        e = unmodifiableMap;
        cl.a(az.class, unmodifiableMap);
    }

    /* compiled from: IdJournal.java */
    /* loaded from: classes.dex */
    public enum e implements cg {
        DOMAIN(1, SpeechConstant.DOMAIN),
        OLD_ID(2, "old_id"),
        NEW_ID(3, "new_id"),
        TS(4, "ts");

        private static final Map<String, e> e = new HashMap();
        private final short f;
        private final String g;

        static {
            Iterator it = EnumSet.allOf(e.class).iterator();
            while (it.hasNext()) {
                e eVar = (e) it.next();
                e.put(eVar.b(), eVar);
            }
        }

        public static e a(int i) {
            if (i == 1) {
                return DOMAIN;
            }
            if (i == 2) {
                return OLD_ID;
            }
            if (i == 3) {
                return NEW_ID;
            }
            if (i != 4) {
                return null;
            }
            return TS;
        }

        public static e b(int i) {
            e a = a(i);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException("Field " + i + " doesn't exist!");
        }

        public static e a(String str) {
            return e.get(str);
        }

        e(short s, String str) {
            this.f = s;
            this.g = str;
        }

        @Override // u.aly.cg
        public short a() {
            return this.f;
        }

        @Override // u.aly.cg
        public String b() {
            return this.g;
        }
    }

    public az() {
        this.m = (byte) 0;
        this.n = new e[]{e.OLD_ID};
    }

    public az(String str, String str2, long j2) {
        this();
        this.a = str;
        this.c = str2;
        this.d = j2;
        d(true);
    }

    public az(az azVar) {
        this.m = (byte) 0;
        this.n = new e[]{e.OLD_ID};
        this.m = azVar.m;
        if (azVar.e()) {
            this.a = azVar.a;
        }
        if (azVar.i()) {
            this.b = azVar.b;
        }
        if (azVar.l()) {
            this.c = azVar.c;
        }
        this.d = azVar.d;
    }

    @Override // u.aly.bz
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public az g() {
        return new az(this);
    }

    @Override // u.aly.bz
    public void b() {
        this.a = null;
        this.b = null;
        this.c = null;
        d(false);
        this.d = 0L;
    }

    public String c() {
        return this.a;
    }

    public az a(String str) {
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

    public az b(String str) {
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

    public String j() {
        return this.c;
    }

    public az c(String str) {
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

    public long m() {
        return this.d;
    }

    public az a(long j2) {
        this.d = j2;
        d(true);
        return this;
    }

    public void n() {
        this.m = bw.b(this.m, 0);
    }

    public boolean o() {
        return bw.a(this.m, 0);
    }

    public void d(boolean z) {
        this.m = bw.a(this.m, 0, z);
    }

    @Override // u.aly.bz
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public e b(int i2) {
        return e.a(i2);
    }

    @Override // u.aly.bz
    public void a(cy cyVar) throws cf {
        k.get(cyVar.D()).b().b(cyVar, this);
    }

    @Override // u.aly.bz
    public void b(cy cyVar) throws cf {
        k.get(cyVar.D()).b().a(cyVar, this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("IdJournal(");
        sb.append("domain:");
        String str = this.a;
        if (str == null) {
            sb.append(Constants.NULL_VERSION_ID);
        } else {
            sb.append(str);
        }
        if (i()) {
            sb.append(", ");
            sb.append("old_id:");
            String str2 = this.b;
            if (str2 == null) {
                sb.append(Constants.NULL_VERSION_ID);
            } else {
                sb.append(str2);
            }
        }
        sb.append(", ");
        sb.append("new_id:");
        String str3 = this.c;
        if (str3 == null) {
            sb.append(Constants.NULL_VERSION_ID);
        } else {
            sb.append(str3);
        }
        sb.append(", ");
        sb.append("ts:");
        sb.append(this.d);
        sb.append(")");
        return sb.toString();
    }

    public void p() throws cf {
        if (this.a == null) {
            throw new cz("Required field 'domain' was not present! Struct: " + toString());
        }
        if (this.c != null) {
            return;
        }
        throw new cz("Required field 'new_id' was not present! Struct: " + toString());
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
            this.m = (byte) 0;
            a(new cs(new dk(objectInputStream)));
        } catch (cf e2) {
            throw new IOException(e2.getMessage());
        }
    }

    /* compiled from: IdJournal.java */
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

    /* compiled from: IdJournal.java */
    /* loaded from: classes.dex */
    private static class a extends di<az> {
        private a() {
        }

        @Override // u.aly.dg
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void b(cy cyVar, az azVar) throws cf {
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
                            if (s == 4) {
                                if (l.b == 10) {
                                    azVar.d = cyVar.x();
                                    azVar.d(true);
                                } else {
                                    db.a(cyVar, l.b);
                                }
                            } else {
                                db.a(cyVar, l.b);
                            }
                        } else if (l.b == 11) {
                            azVar.c = cyVar.z();
                            azVar.c(true);
                        } else {
                            db.a(cyVar, l.b);
                        }
                    } else if (l.b == 11) {
                        azVar.b = cyVar.z();
                        azVar.b(true);
                    } else {
                        db.a(cyVar, l.b);
                    }
                } else if (l.b == 11) {
                    azVar.a = cyVar.z();
                    azVar.a(true);
                } else {
                    db.a(cyVar, l.b);
                }
                cyVar.m();
            }
            cyVar.k();
            if (!azVar.o()) {
                throw new cz("Required field 'ts' was not found in serialized data! Struct: " + toString());
            }
            azVar.p();
        }

        @Override // u.aly.dg
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(cy cyVar, az azVar) throws cf {
            azVar.p();
            cyVar.a(az.f);
            if (azVar.a != null) {
                cyVar.a(az.g);
                cyVar.a(azVar.a);
                cyVar.c();
            }
            if (azVar.b != null && azVar.i()) {
                cyVar.a(az.h);
                cyVar.a(azVar.b);
                cyVar.c();
            }
            if (azVar.c != null) {
                cyVar.a(az.i);
                cyVar.a(azVar.c);
                cyVar.c();
            }
            cyVar.a(az.j);
            cyVar.a(azVar.d);
            cyVar.c();
            cyVar.d();
            cyVar.b();
        }
    }

    /* compiled from: IdJournal.java */
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

    /* compiled from: IdJournal.java */
    /* loaded from: classes.dex */
    private static class c extends dj<az> {
        private c() {
        }

        @Override // u.aly.dg
        public void a(cy cyVar, az azVar) throws cf {
            de deVar = (de) cyVar;
            deVar.a(azVar.a);
            deVar.a(azVar.c);
            deVar.a(azVar.d);
            BitSet bitSet = new BitSet();
            if (azVar.i()) {
                bitSet.set(0);
            }
            deVar.a(bitSet, 1);
            if (azVar.i()) {
                deVar.a(azVar.b);
            }
        }

        @Override // u.aly.dg
        public void b(cy cyVar, az azVar) throws cf {
            de deVar = (de) cyVar;
            azVar.a = deVar.z();
            azVar.a(true);
            azVar.c = deVar.z();
            azVar.c(true);
            azVar.d = deVar.x();
            azVar.d(true);
            if (deVar.b(1).get(0)) {
                azVar.b = deVar.z();
                azVar.b(true);
            }
        }
    }
}
