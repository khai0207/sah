package u.aly;

import com.netease.nimlib.amazonaws.services.s3.internal.Constants;
import com.talkingdata.sdk.cs;
import com.unionpay.tsmservice.data.Constant;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* compiled from: UMEnvelope.java */
/* loaded from: classes.dex */
public class bq implements Serializable, Cloneable, bz<bq, e> {
    private static final int A = 3;
    public static final Map<e, cl> k;
    private static final dd l = new dd("UMEnvelope");
    private static final ct m = new ct("version", (byte) 11, 1);
    private static final ct n = new ct("address", (byte) 11, 2);
    private static final ct o = new ct(Constant.KEY_SIGNATURE, (byte) 11, 3);
    private static final ct p = new ct("serial_num", (byte) 8, 4);
    private static final ct q = new ct("ts_secs", (byte) 8, 5);
    private static final ct r = new ct(cs.a.b, (byte) 8, 6);
    private static final ct s = new ct("entity", (byte) 11, 7);
    private static final ct t = new ct("guid", (byte) 11, 8);

    /* renamed from: u */
    private static final ct f71u = new ct("checksum", (byte) 11, 9);
    private static final ct v = new ct("codex", (byte) 8, 10);
    private static final Map<Class<? extends dg>, dh> w;
    private static final int x = 0;
    private static final int y = 1;
    private static final int z = 2;
    private byte B;
    private e[] C;
    public String a;
    public String b;
    public String c;
    public int d;
    public int e;
    public int f;
    public ByteBuffer g;
    public String h;
    public String i;
    public int j;

    static {
        HashMap hashMap = new HashMap();
        w = hashMap;
        hashMap.put(di.class, new b());
        w.put(dj.class, new d());
        EnumMap enumMap = new EnumMap(e.class);
        enumMap.put((EnumMap) e.VERSION, (e) new cl("version", (byte) 1, new cm((byte) 11)));
        enumMap.put((EnumMap) e.ADDRESS, (e) new cl("address", (byte) 1, new cm((byte) 11)));
        enumMap.put((EnumMap) e.SIGNATURE, (e) new cl(Constant.KEY_SIGNATURE, (byte) 1, new cm((byte) 11)));
        enumMap.put((EnumMap) e.SERIAL_NUM, (e) new cl("serial_num", (byte) 1, new cm((byte) 8)));
        enumMap.put((EnumMap) e.TS_SECS, (e) new cl("ts_secs", (byte) 1, new cm((byte) 8)));
        enumMap.put((EnumMap) e.LENGTH, (e) new cl(cs.a.b, (byte) 1, new cm((byte) 8)));
        enumMap.put((EnumMap) e.ENTITY, (e) new cl("entity", (byte) 1, new cm((byte) 11, true)));
        enumMap.put((EnumMap) e.GUID, (e) new cl("guid", (byte) 1, new cm((byte) 11)));
        enumMap.put((EnumMap) e.CHECKSUM, (e) new cl("checksum", (byte) 1, new cm((byte) 11)));
        enumMap.put((EnumMap) e.CODEX, (e) new cl("codex", (byte) 2, new cm((byte) 8)));
        Map<e, cl> unmodifiableMap = Collections.unmodifiableMap(enumMap);
        k = unmodifiableMap;
        cl.a(bq.class, unmodifiableMap);
    }

    /* compiled from: UMEnvelope.java */
    /* loaded from: classes.dex */
    public enum e implements cg {
        VERSION(1, "version"),
        ADDRESS(2, "address"),
        SIGNATURE(3, Constant.KEY_SIGNATURE),
        SERIAL_NUM(4, "serial_num"),
        TS_SECS(5, "ts_secs"),
        LENGTH(6, cs.a.b),
        ENTITY(7, "entity"),
        GUID(8, "guid"),
        CHECKSUM(9, "checksum"),
        CODEX(10, "codex");

        private static final Map<String, e> k = new HashMap();
        private final short l;
        private final String m;

        static {
            Iterator it = EnumSet.allOf(e.class).iterator();
            while (it.hasNext()) {
                e eVar = (e) it.next();
                k.put(eVar.b(), eVar);
            }
        }

        public static e a(int i) {
            switch (i) {
                case 1:
                    return VERSION;
                case 2:
                    return ADDRESS;
                case 3:
                    return SIGNATURE;
                case 4:
                    return SERIAL_NUM;
                case 5:
                    return TS_SECS;
                case 6:
                    return LENGTH;
                case 7:
                    return ENTITY;
                case 8:
                    return GUID;
                case 9:
                    return CHECKSUM;
                case 10:
                    return CODEX;
                default:
                    return null;
            }
        }

        public static e b(int i) {
            e a = a(i);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException("Field " + i + " doesn't exist!");
        }

        public static e a(String str) {
            return k.get(str);
        }

        e(short s, String str) {
            this.l = s;
            this.m = str;
        }

        @Override // u.aly.cg
        public short a() {
            return this.l;
        }

        @Override // u.aly.cg
        public String b() {
            return this.m;
        }
    }

    public bq() {
        this.B = (byte) 0;
        this.C = new e[]{e.CODEX};
    }

    public bq(String str, String str2, String str3, int i, int i2, int i3, ByteBuffer byteBuffer, String str4, String str5) {
        this();
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = i;
        d(true);
        this.e = i2;
        e(true);
        this.f = i3;
        f(true);
        this.g = byteBuffer;
        this.h = str4;
        this.i = str5;
    }

    public bq(bq bqVar) {
        this.B = (byte) 0;
        this.C = new e[]{e.CODEX};
        this.B = bqVar.B;
        if (bqVar.e()) {
            this.a = bqVar.a;
        }
        if (bqVar.i()) {
            this.b = bqVar.b;
        }
        if (bqVar.l()) {
            this.c = bqVar.c;
        }
        this.d = bqVar.d;
        this.e = bqVar.e;
        this.f = bqVar.f;
        if (bqVar.y()) {
            this.g = ca.d(bqVar.g);
        }
        if (bqVar.B()) {
            this.h = bqVar.h;
        }
        if (bqVar.E()) {
            this.i = bqVar.i;
        }
        this.j = bqVar.j;
    }

    @Override // u.aly.bz
    /* renamed from: a */
    public bq g() {
        return new bq(this);
    }

    @Override // u.aly.bz
    public void b() {
        this.a = null;
        this.b = null;
        this.c = null;
        d(false);
        this.d = 0;
        e(false);
        this.e = 0;
        f(false);
        this.f = 0;
        this.g = null;
        this.h = null;
        this.i = null;
        j(false);
        this.j = 0;
    }

    public String c() {
        return this.a;
    }

    public bq a(String str) {
        this.a = str;
        return this;
    }

    public void d() {
        this.a = null;
    }

    public boolean e() {
        return this.a != null;
    }

    public void a(boolean z2) {
        if (z2) {
            return;
        }
        this.a = null;
    }

    public String f() {
        return this.b;
    }

    public bq b(String str) {
        this.b = str;
        return this;
    }

    public void h() {
        this.b = null;
    }

    public boolean i() {
        return this.b != null;
    }

    public void b(boolean z2) {
        if (z2) {
            return;
        }
        this.b = null;
    }

    public String j() {
        return this.c;
    }

    public bq c(String str) {
        this.c = str;
        return this;
    }

    public void k() {
        this.c = null;
    }

    public boolean l() {
        return this.c != null;
    }

    public void c(boolean z2) {
        if (z2) {
            return;
        }
        this.c = null;
    }

    public int m() {
        return this.d;
    }

    public bq a(int i) {
        this.d = i;
        d(true);
        return this;
    }

    public void n() {
        this.B = bw.b(this.B, 0);
    }

    public boolean o() {
        return bw.a(this.B, 0);
    }

    public void d(boolean z2) {
        this.B = bw.a(this.B, 0, z2);
    }

    public int p() {
        return this.e;
    }

    public bq c(int i) {
        this.e = i;
        e(true);
        return this;
    }

    public void q() {
        this.B = bw.b(this.B, 1);
    }

    public boolean r() {
        return bw.a(this.B, 1);
    }

    public void e(boolean z2) {
        this.B = bw.a(this.B, 1, z2);
    }

    public int s() {
        return this.f;
    }

    public bq d(int i) {
        this.f = i;
        f(true);
        return this;
    }

    public void t() {
        this.B = bw.b(this.B, 2);
    }

    public boolean u() {
        return bw.a(this.B, 2);
    }

    public void f(boolean z2) {
        this.B = bw.a(this.B, 2, z2);
    }

    public byte[] v() {
        a(ca.c(this.g));
        ByteBuffer byteBuffer = this.g;
        if (byteBuffer == null) {
            return null;
        }
        return byteBuffer.array();
    }

    public ByteBuffer w() {
        return this.g;
    }

    public bq a(byte[] bArr) {
        a(bArr == null ? (ByteBuffer) null : ByteBuffer.wrap(bArr));
        return this;
    }

    public bq a(ByteBuffer byteBuffer) {
        this.g = byteBuffer;
        return this;
    }

    public void x() {
        this.g = null;
    }

    public boolean y() {
        return this.g != null;
    }

    public void g(boolean z2) {
        if (z2) {
            return;
        }
        this.g = null;
    }

    public String z() {
        return this.h;
    }

    public bq d(String str) {
        this.h = str;
        return this;
    }

    public void A() {
        this.h = null;
    }

    public boolean B() {
        return this.h != null;
    }

    public void h(boolean z2) {
        if (z2) {
            return;
        }
        this.h = null;
    }

    public String C() {
        return this.i;
    }

    public bq e(String str) {
        this.i = str;
        return this;
    }

    public void D() {
        this.i = null;
    }

    public boolean E() {
        return this.i != null;
    }

    public void i(boolean z2) {
        if (z2) {
            return;
        }
        this.i = null;
    }

    public int F() {
        return this.j;
    }

    public bq e(int i) {
        this.j = i;
        j(true);
        return this;
    }

    public void G() {
        this.B = bw.b(this.B, 3);
    }

    public boolean H() {
        return bw.a(this.B, 3);
    }

    public void j(boolean z2) {
        this.B = bw.a(this.B, 3, z2);
    }

    @Override // u.aly.bz
    /* renamed from: f */
    public e b(int i) {
        return e.a(i);
    }

    @Override // u.aly.bz
    public void a(cy cyVar) throws cf {
        w.get(cyVar.D()).b().b(cyVar, this);
    }

    @Override // u.aly.bz
    public void b(cy cyVar) throws cf {
        w.get(cyVar.D()).b().a(cyVar, this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("UMEnvelope(");
        sb.append("version:");
        String str = this.a;
        if (str == null) {
            sb.append(Constants.NULL_VERSION_ID);
        } else {
            sb.append(str);
        }
        sb.append(", ");
        sb.append("address:");
        String str2 = this.b;
        if (str2 == null) {
            sb.append(Constants.NULL_VERSION_ID);
        } else {
            sb.append(str2);
        }
        sb.append(", ");
        sb.append("signature:");
        String str3 = this.c;
        if (str3 == null) {
            sb.append(Constants.NULL_VERSION_ID);
        } else {
            sb.append(str3);
        }
        sb.append(", ");
        sb.append("serial_num:");
        sb.append(this.d);
        sb.append(", ");
        sb.append("ts_secs:");
        sb.append(this.e);
        sb.append(", ");
        sb.append("length:");
        sb.append(this.f);
        sb.append(", ");
        sb.append("entity:");
        ByteBuffer byteBuffer = this.g;
        if (byteBuffer == null) {
            sb.append(Constants.NULL_VERSION_ID);
        } else {
            ca.a(byteBuffer, sb);
        }
        sb.append(", ");
        sb.append("guid:");
        String str4 = this.h;
        if (str4 == null) {
            sb.append(Constants.NULL_VERSION_ID);
        } else {
            sb.append(str4);
        }
        sb.append(", ");
        sb.append("checksum:");
        String str5 = this.i;
        if (str5 == null) {
            sb.append(Constants.NULL_VERSION_ID);
        } else {
            sb.append(str5);
        }
        if (H()) {
            sb.append(", ");
            sb.append("codex:");
            sb.append(this.j);
        }
        sb.append(")");
        return sb.toString();
    }

    public void I() throws cf {
        if (this.a == null) {
            throw new cz("Required field 'version' was not present! Struct: " + toString());
        }
        if (this.b == null) {
            throw new cz("Required field 'address' was not present! Struct: " + toString());
        }
        if (this.c == null) {
            throw new cz("Required field 'signature' was not present! Struct: " + toString());
        }
        if (this.g == null) {
            throw new cz("Required field 'entity' was not present! Struct: " + toString());
        }
        if (this.h == null) {
            throw new cz("Required field 'guid' was not present! Struct: " + toString());
        }
        if (this.i != null) {
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
            this.B = (byte) 0;
            a(new cs(new dk(objectInputStream)));
        } catch (cf e2) {
            throw new IOException(e2.getMessage());
        }
    }

    /* compiled from: UMEnvelope.java */
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

    /* compiled from: UMEnvelope.java */
    /* loaded from: classes.dex */
    private static class a extends di<bq> {
        private a() {
        }

        /* synthetic */ a(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // u.aly.dg
        /* renamed from: a */
        public void b(cy cyVar, bq bqVar) throws cf {
            cyVar.j();
            while (true) {
                ct l = cyVar.l();
                if (l.b != 0) {
                    switch (l.c) {
                        case 1:
                            if (l.b == 11) {
                                bqVar.a = cyVar.z();
                                bqVar.a(true);
                                break;
                            } else {
                                db.a(cyVar, l.b);
                                break;
                            }
                        case 2:
                            if (l.b == 11) {
                                bqVar.b = cyVar.z();
                                bqVar.b(true);
                                break;
                            } else {
                                db.a(cyVar, l.b);
                                break;
                            }
                        case 3:
                            if (l.b == 11) {
                                bqVar.c = cyVar.z();
                                bqVar.c(true);
                                break;
                            } else {
                                db.a(cyVar, l.b);
                                break;
                            }
                        case 4:
                            if (l.b == 8) {
                                bqVar.d = cyVar.w();
                                bqVar.d(true);
                                break;
                            } else {
                                db.a(cyVar, l.b);
                                break;
                            }
                        case 5:
                            if (l.b == 8) {
                                bqVar.e = cyVar.w();
                                bqVar.e(true);
                                break;
                            } else {
                                db.a(cyVar, l.b);
                                break;
                            }
                        case 6:
                            if (l.b == 8) {
                                bqVar.f = cyVar.w();
                                bqVar.f(true);
                                break;
                            } else {
                                db.a(cyVar, l.b);
                                break;
                            }
                        case 7:
                            if (l.b == 11) {
                                bqVar.g = cyVar.A();
                                bqVar.g(true);
                                break;
                            } else {
                                db.a(cyVar, l.b);
                                break;
                            }
                        case 8:
                            if (l.b == 11) {
                                bqVar.h = cyVar.z();
                                bqVar.h(true);
                                break;
                            } else {
                                db.a(cyVar, l.b);
                                break;
                            }
                        case 9:
                            if (l.b == 11) {
                                bqVar.i = cyVar.z();
                                bqVar.i(true);
                                break;
                            } else {
                                db.a(cyVar, l.b);
                                break;
                            }
                        case 10:
                            if (l.b == 8) {
                                bqVar.j = cyVar.w();
                                bqVar.j(true);
                                break;
                            } else {
                                db.a(cyVar, l.b);
                                break;
                            }
                        default:
                            db.a(cyVar, l.b);
                            break;
                    }
                    cyVar.m();
                } else {
                    cyVar.k();
                    if (!bqVar.o()) {
                        throw new cz("Required field 'serial_num' was not found in serialized data! Struct: " + toString());
                    }
                    if (!bqVar.r()) {
                        throw new cz("Required field 'ts_secs' was not found in serialized data! Struct: " + toString());
                    }
                    if (!bqVar.u()) {
                        throw new cz("Required field 'length' was not found in serialized data! Struct: " + toString());
                    }
                    bqVar.I();
                    return;
                }
            }
        }

        @Override // u.aly.dg
        /* renamed from: b */
        public void a(cy cyVar, bq bqVar) throws cf {
            bqVar.I();
            cyVar.a(bq.l);
            if (bqVar.a != null) {
                cyVar.a(bq.m);
                cyVar.a(bqVar.a);
                cyVar.c();
            }
            if (bqVar.b != null) {
                cyVar.a(bq.n);
                cyVar.a(bqVar.b);
                cyVar.c();
            }
            if (bqVar.c != null) {
                cyVar.a(bq.o);
                cyVar.a(bqVar.c);
                cyVar.c();
            }
            cyVar.a(bq.p);
            cyVar.a(bqVar.d);
            cyVar.c();
            cyVar.a(bq.q);
            cyVar.a(bqVar.e);
            cyVar.c();
            cyVar.a(bq.r);
            cyVar.a(bqVar.f);
            cyVar.c();
            if (bqVar.g != null) {
                cyVar.a(bq.s);
                cyVar.a(bqVar.g);
                cyVar.c();
            }
            if (bqVar.h != null) {
                cyVar.a(bq.t);
                cyVar.a(bqVar.h);
                cyVar.c();
            }
            if (bqVar.i != null) {
                cyVar.a(bq.f71u);
                cyVar.a(bqVar.i);
                cyVar.c();
            }
            if (bqVar.H()) {
                cyVar.a(bq.v);
                cyVar.a(bqVar.j);
                cyVar.c();
            }
            cyVar.d();
            cyVar.b();
        }
    }

    /* compiled from: UMEnvelope.java */
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

    /* compiled from: UMEnvelope.java */
    /* loaded from: classes.dex */
    private static class c extends dj<bq> {
        private c() {
        }

        /* synthetic */ c(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // u.aly.dg
        public void a(cy cyVar, bq bqVar) throws cf {
            de deVar = (de) cyVar;
            deVar.a(bqVar.a);
            deVar.a(bqVar.b);
            deVar.a(bqVar.c);
            deVar.a(bqVar.d);
            deVar.a(bqVar.e);
            deVar.a(bqVar.f);
            deVar.a(bqVar.g);
            deVar.a(bqVar.h);
            deVar.a(bqVar.i);
            BitSet bitSet = new BitSet();
            if (bqVar.H()) {
                bitSet.set(0);
            }
            deVar.a(bitSet, 1);
            if (bqVar.H()) {
                deVar.a(bqVar.j);
            }
        }

        @Override // u.aly.dg
        public void b(cy cyVar, bq bqVar) throws cf {
            de deVar = (de) cyVar;
            bqVar.a = deVar.z();
            bqVar.a(true);
            bqVar.b = deVar.z();
            bqVar.b(true);
            bqVar.c = deVar.z();
            bqVar.c(true);
            bqVar.d = deVar.w();
            bqVar.d(true);
            bqVar.e = deVar.w();
            bqVar.e(true);
            bqVar.f = deVar.w();
            bqVar.f(true);
            bqVar.g = deVar.A();
            bqVar.g(true);
            bqVar.h = deVar.z();
            bqVar.h(true);
            bqVar.i = deVar.z();
            bqVar.i(true);
            if (deVar.b(1).get(0)) {
                bqVar.j = deVar.w();
                bqVar.j(true);
            }
        }
    }
}
