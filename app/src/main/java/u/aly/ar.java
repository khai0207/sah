package u.aly;

import com.netease.nimlib.amazonaws.mobileconnectors.s3.transferutility.TransferTable;
import com.netease.nimlib.amazonaws.services.s3.internal.Constants;
import com.unionpay.tsmservice.data.Constant;
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

/* compiled from: AppInfo.java */
/* loaded from: classes.dex */
public class ar implements Serializable, Cloneable, bz<ar, e> {
    private static final int A = 1;
    public static final Map<e, cl> l;
    private static final dd m = new dd("AppInfo");
    private static final ct n = new ct(TransferTable.COLUMN_KEY, (byte) 11, 1);
    private static final ct o = new ct("version", (byte) 11, 2);
    private static final ct p = new ct("version_index", (byte) 8, 3);
    private static final ct q = new ct("package_name", (byte) 11, 4);
    private static final ct r = new ct("sdk_type", (byte) 8, 5);
    private static final ct s = new ct("sdk_version", (byte) 11, 6);
    private static final ct t = new ct(Constant.KEY_CHANNEL, (byte) 11, 7);

    /* renamed from: u */
    private static final ct f65u = new ct("wrapper_type", (byte) 11, 8);
    private static final ct v = new ct("wrapper_version", (byte) 11, 9);
    private static final ct w = new ct("vertical_type", (byte) 8, 10);
    private static final ct x = new ct("app_signature", (byte) 11, 11);
    private static final Map<Class<? extends dg>, dh> y;
    private static final int z = 0;
    private byte B;
    private e[] C;
    public String a;
    public String b;
    public int c;
    public String d;
    public bm e;
    public String f;
    public String g;
    public String h;
    public String i;
    public int j;
    public String k;

    static {
        HashMap hashMap = new HashMap();
        y = hashMap;
        hashMap.put(di.class, new b());
        y.put(dj.class, new d());
        EnumMap enumMap = new EnumMap(e.class);
        enumMap.put((EnumMap) e.KEY, (e) new cl(TransferTable.COLUMN_KEY, (byte) 1, new cm((byte) 11)));
        enumMap.put((EnumMap) e.VERSION, (e) new cl("version", (byte) 2, new cm((byte) 11)));
        enumMap.put((EnumMap) e.VERSION_INDEX, (e) new cl("version_index", (byte) 2, new cm((byte) 8)));
        enumMap.put((EnumMap) e.PACKAGE_NAME, (e) new cl("package_name", (byte) 2, new cm((byte) 11)));
        enumMap.put((EnumMap) e.SDK_TYPE, (e) new cl("sdk_type", (byte) 1, new ck(df.n, bm.class)));
        enumMap.put((EnumMap) e.SDK_VERSION, (e) new cl("sdk_version", (byte) 1, new cm((byte) 11)));
        enumMap.put((EnumMap) e.CHANNEL, (e) new cl(Constant.KEY_CHANNEL, (byte) 1, new cm((byte) 11)));
        enumMap.put((EnumMap) e.WRAPPER_TYPE, (e) new cl("wrapper_type", (byte) 2, new cm((byte) 11)));
        enumMap.put((EnumMap) e.WRAPPER_VERSION, (e) new cl("wrapper_version", (byte) 2, new cm((byte) 11)));
        enumMap.put((EnumMap) e.VERTICAL_TYPE, (e) new cl("vertical_type", (byte) 2, new cm((byte) 8)));
        enumMap.put((EnumMap) e.APP_SIGNATURE, (e) new cl("app_signature", (byte) 2, new cm((byte) 11)));
        Map<e, cl> unmodifiableMap = Collections.unmodifiableMap(enumMap);
        l = unmodifiableMap;
        cl.a(ar.class, unmodifiableMap);
    }

    /* compiled from: AppInfo.java */
    /* loaded from: classes.dex */
    public enum e implements cg {
        KEY(1, TransferTable.COLUMN_KEY),
        VERSION(2, "version"),
        VERSION_INDEX(3, "version_index"),
        PACKAGE_NAME(4, "package_name"),
        SDK_TYPE(5, "sdk_type"),
        SDK_VERSION(6, "sdk_version"),
        CHANNEL(7, Constant.KEY_CHANNEL),
        WRAPPER_TYPE(8, "wrapper_type"),
        WRAPPER_VERSION(9, "wrapper_version"),
        VERTICAL_TYPE(10, "vertical_type"),
        APP_SIGNATURE(11, "app_signature");

        private static final Map<String, e> l = new HashMap();
        private final short m;
        private final String n;

        static {
            Iterator it = EnumSet.allOf(e.class).iterator();
            while (it.hasNext()) {
                e eVar = (e) it.next();
                l.put(eVar.b(), eVar);
            }
        }

        public static e a(int i) {
            switch (i) {
                case 1:
                    return KEY;
                case 2:
                    return VERSION;
                case 3:
                    return VERSION_INDEX;
                case 4:
                    return PACKAGE_NAME;
                case 5:
                    return SDK_TYPE;
                case 6:
                    return SDK_VERSION;
                case 7:
                    return CHANNEL;
                case 8:
                    return WRAPPER_TYPE;
                case 9:
                    return WRAPPER_VERSION;
                case 10:
                    return VERTICAL_TYPE;
                case 11:
                    return APP_SIGNATURE;
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
            return l.get(str);
        }

        e(short s, String str) {
            this.m = s;
            this.n = str;
        }

        @Override // u.aly.cg
        public short a() {
            return this.m;
        }

        @Override // u.aly.cg
        public String b() {
            return this.n;
        }
    }

    public ar() {
        this.B = (byte) 0;
        this.C = new e[]{e.VERSION, e.VERSION_INDEX, e.PACKAGE_NAME, e.WRAPPER_TYPE, e.WRAPPER_VERSION, e.VERTICAL_TYPE, e.APP_SIGNATURE};
    }

    public ar(String str, bm bmVar, String str2, String str3) {
        this();
        this.a = str;
        this.e = bmVar;
        this.f = str2;
        this.g = str3;
    }

    public ar(ar arVar) {
        this.B = (byte) 0;
        this.C = new e[]{e.VERSION, e.VERSION_INDEX, e.PACKAGE_NAME, e.WRAPPER_TYPE, e.WRAPPER_VERSION, e.VERTICAL_TYPE, e.APP_SIGNATURE};
        this.B = arVar.B;
        if (arVar.e()) {
            this.a = arVar.a;
        }
        if (arVar.i()) {
            this.b = arVar.b;
        }
        this.c = arVar.c;
        if (arVar.o()) {
            this.d = arVar.d;
        }
        if (arVar.r()) {
            this.e = arVar.e;
        }
        if (arVar.u()) {
            this.f = arVar.f;
        }
        if (arVar.x()) {
            this.g = arVar.g;
        }
        if (arVar.A()) {
            this.h = arVar.h;
        }
        if (arVar.D()) {
            this.i = arVar.i;
        }
        this.j = arVar.j;
        if (arVar.J()) {
            this.k = arVar.k;
        }
    }

    @Override // u.aly.bz
    /* renamed from: a */
    public ar g() {
        return new ar(this);
    }

    @Override // u.aly.bz
    public void b() {
        this.a = null;
        this.b = null;
        c(false);
        this.c = 0;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        j(false);
        this.j = 0;
        this.k = null;
    }

    public String c() {
        return this.a;
    }

    public ar a(String str) {
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

    public ar b(String str) {
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

    public int j() {
        return this.c;
    }

    public ar a(int i) {
        this.c = i;
        c(true);
        return this;
    }

    public void k() {
        this.B = bw.b(this.B, 0);
    }

    public boolean l() {
        return bw.a(this.B, 0);
    }

    public void c(boolean z2) {
        this.B = bw.a(this.B, 0, z2);
    }

    public String m() {
        return this.d;
    }

    public ar c(String str) {
        this.d = str;
        return this;
    }

    public void n() {
        this.d = null;
    }

    public boolean o() {
        return this.d != null;
    }

    public void d(boolean z2) {
        if (z2) {
            return;
        }
        this.d = null;
    }

    public bm p() {
        return this.e;
    }

    public ar a(bm bmVar) {
        this.e = bmVar;
        return this;
    }

    public void q() {
        this.e = null;
    }

    public boolean r() {
        return this.e != null;
    }

    public void e(boolean z2) {
        if (z2) {
            return;
        }
        this.e = null;
    }

    public String s() {
        return this.f;
    }

    public ar d(String str) {
        this.f = str;
        return this;
    }

    public void t() {
        this.f = null;
    }

    public boolean u() {
        return this.f != null;
    }

    public void f(boolean z2) {
        if (z2) {
            return;
        }
        this.f = null;
    }

    public String v() {
        return this.g;
    }

    public ar e(String str) {
        this.g = str;
        return this;
    }

    public void w() {
        this.g = null;
    }

    public boolean x() {
        return this.g != null;
    }

    public void g(boolean z2) {
        if (z2) {
            return;
        }
        this.g = null;
    }

    public String y() {
        return this.h;
    }

    public ar f(String str) {
        this.h = str;
        return this;
    }

    public void z() {
        this.h = null;
    }

    public boolean A() {
        return this.h != null;
    }

    public void h(boolean z2) {
        if (z2) {
            return;
        }
        this.h = null;
    }

    public String B() {
        return this.i;
    }

    public ar g(String str) {
        this.i = str;
        return this;
    }

    public void C() {
        this.i = null;
    }

    public boolean D() {
        return this.i != null;
    }

    public void i(boolean z2) {
        if (z2) {
            return;
        }
        this.i = null;
    }

    public int E() {
        return this.j;
    }

    public ar c(int i) {
        this.j = i;
        j(true);
        return this;
    }

    public void F() {
        this.B = bw.b(this.B, 1);
    }

    public boolean G() {
        return bw.a(this.B, 1);
    }

    public void j(boolean z2) {
        this.B = bw.a(this.B, 1, z2);
    }

    public String H() {
        return this.k;
    }

    public ar h(String str) {
        this.k = str;
        return this;
    }

    public void I() {
        this.k = null;
    }

    public boolean J() {
        return this.k != null;
    }

    public void k(boolean z2) {
        if (z2) {
            return;
        }
        this.k = null;
    }

    @Override // u.aly.bz
    /* renamed from: d */
    public e b(int i) {
        return e.a(i);
    }

    @Override // u.aly.bz
    public void a(cy cyVar) throws cf {
        y.get(cyVar.D()).b().b(cyVar, this);
    }

    @Override // u.aly.bz
    public void b(cy cyVar) throws cf {
        y.get(cyVar.D()).b().a(cyVar, this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("AppInfo(");
        sb.append("key:");
        String str = this.a;
        if (str == null) {
            sb.append(Constants.NULL_VERSION_ID);
        } else {
            sb.append(str);
        }
        if (i()) {
            sb.append(", ");
            sb.append("version:");
            String str2 = this.b;
            if (str2 == null) {
                sb.append(Constants.NULL_VERSION_ID);
            } else {
                sb.append(str2);
            }
        }
        if (l()) {
            sb.append(", ");
            sb.append("version_index:");
            sb.append(this.c);
        }
        if (o()) {
            sb.append(", ");
            sb.append("package_name:");
            String str3 = this.d;
            if (str3 == null) {
                sb.append(Constants.NULL_VERSION_ID);
            } else {
                sb.append(str3);
            }
        }
        sb.append(", ");
        sb.append("sdk_type:");
        bm bmVar = this.e;
        if (bmVar == null) {
            sb.append(Constants.NULL_VERSION_ID);
        } else {
            sb.append(bmVar);
        }
        sb.append(", ");
        sb.append("sdk_version:");
        String str4 = this.f;
        if (str4 == null) {
            sb.append(Constants.NULL_VERSION_ID);
        } else {
            sb.append(str4);
        }
        sb.append(", ");
        sb.append("channel:");
        String str5 = this.g;
        if (str5 == null) {
            sb.append(Constants.NULL_VERSION_ID);
        } else {
            sb.append(str5);
        }
        if (A()) {
            sb.append(", ");
            sb.append("wrapper_type:");
            String str6 = this.h;
            if (str6 == null) {
                sb.append(Constants.NULL_VERSION_ID);
            } else {
                sb.append(str6);
            }
        }
        if (D()) {
            sb.append(", ");
            sb.append("wrapper_version:");
            String str7 = this.i;
            if (str7 == null) {
                sb.append(Constants.NULL_VERSION_ID);
            } else {
                sb.append(str7);
            }
        }
        if (G()) {
            sb.append(", ");
            sb.append("vertical_type:");
            sb.append(this.j);
        }
        if (J()) {
            sb.append(", ");
            sb.append("app_signature:");
            String str8 = this.k;
            if (str8 == null) {
                sb.append(Constants.NULL_VERSION_ID);
            } else {
                sb.append(str8);
            }
        }
        sb.append(")");
        return sb.toString();
    }

    public void K() throws cf {
        if (this.a == null) {
            throw new cz("Required field 'key' was not present! Struct: " + toString());
        }
        if (this.e == null) {
            throw new cz("Required field 'sdk_type' was not present! Struct: " + toString());
        }
        if (this.f == null) {
            throw new cz("Required field 'sdk_version' was not present! Struct: " + toString());
        }
        if (this.g != null) {
            return;
        }
        throw new cz("Required field 'channel' was not present! Struct: " + toString());
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

    /* compiled from: AppInfo.java */
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

    /* compiled from: AppInfo.java */
    /* loaded from: classes.dex */
    private static class a extends di<ar> {
        private a() {
        }

        /* synthetic */ a(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // u.aly.dg
        /* renamed from: a */
        public void b(cy cyVar, ar arVar) throws cf {
            cyVar.j();
            while (true) {
                ct l = cyVar.l();
                if (l.b != 0) {
                    switch (l.c) {
                        case 1:
                            if (l.b == 11) {
                                arVar.a = cyVar.z();
                                arVar.a(true);
                                break;
                            } else {
                                db.a(cyVar, l.b);
                                break;
                            }
                        case 2:
                            if (l.b == 11) {
                                arVar.b = cyVar.z();
                                arVar.b(true);
                                break;
                            } else {
                                db.a(cyVar, l.b);
                                break;
                            }
                        case 3:
                            if (l.b == 8) {
                                arVar.c = cyVar.w();
                                arVar.c(true);
                                break;
                            } else {
                                db.a(cyVar, l.b);
                                break;
                            }
                        case 4:
                            if (l.b == 11) {
                                arVar.d = cyVar.z();
                                arVar.d(true);
                                break;
                            } else {
                                db.a(cyVar, l.b);
                                break;
                            }
                        case 5:
                            if (l.b == 8) {
                                arVar.e = bm.a(cyVar.w());
                                arVar.e(true);
                                break;
                            } else {
                                db.a(cyVar, l.b);
                                break;
                            }
                        case 6:
                            if (l.b == 11) {
                                arVar.f = cyVar.z();
                                arVar.f(true);
                                break;
                            } else {
                                db.a(cyVar, l.b);
                                break;
                            }
                        case 7:
                            if (l.b == 11) {
                                arVar.g = cyVar.z();
                                arVar.g(true);
                                break;
                            } else {
                                db.a(cyVar, l.b);
                                break;
                            }
                        case 8:
                            if (l.b == 11) {
                                arVar.h = cyVar.z();
                                arVar.h(true);
                                break;
                            } else {
                                db.a(cyVar, l.b);
                                break;
                            }
                        case 9:
                            if (l.b == 11) {
                                arVar.i = cyVar.z();
                                arVar.i(true);
                                break;
                            } else {
                                db.a(cyVar, l.b);
                                break;
                            }
                        case 10:
                            if (l.b == 8) {
                                arVar.j = cyVar.w();
                                arVar.j(true);
                                break;
                            } else {
                                db.a(cyVar, l.b);
                                break;
                            }
                        case 11:
                            if (l.b == 11) {
                                arVar.k = cyVar.z();
                                arVar.k(true);
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
                    arVar.K();
                    return;
                }
            }
        }

        @Override // u.aly.dg
        /* renamed from: b */
        public void a(cy cyVar, ar arVar) throws cf {
            arVar.K();
            cyVar.a(ar.m);
            if (arVar.a != null) {
                cyVar.a(ar.n);
                cyVar.a(arVar.a);
                cyVar.c();
            }
            if (arVar.b != null && arVar.i()) {
                cyVar.a(ar.o);
                cyVar.a(arVar.b);
                cyVar.c();
            }
            if (arVar.l()) {
                cyVar.a(ar.p);
                cyVar.a(arVar.c);
                cyVar.c();
            }
            if (arVar.d != null && arVar.o()) {
                cyVar.a(ar.q);
                cyVar.a(arVar.d);
                cyVar.c();
            }
            if (arVar.e != null) {
                cyVar.a(ar.r);
                cyVar.a(arVar.e.a());
                cyVar.c();
            }
            if (arVar.f != null) {
                cyVar.a(ar.s);
                cyVar.a(arVar.f);
                cyVar.c();
            }
            if (arVar.g != null) {
                cyVar.a(ar.t);
                cyVar.a(arVar.g);
                cyVar.c();
            }
            if (arVar.h != null && arVar.A()) {
                cyVar.a(ar.f65u);
                cyVar.a(arVar.h);
                cyVar.c();
            }
            if (arVar.i != null && arVar.D()) {
                cyVar.a(ar.v);
                cyVar.a(arVar.i);
                cyVar.c();
            }
            if (arVar.G()) {
                cyVar.a(ar.w);
                cyVar.a(arVar.j);
                cyVar.c();
            }
            if (arVar.k != null && arVar.J()) {
                cyVar.a(ar.x);
                cyVar.a(arVar.k);
                cyVar.c();
            }
            cyVar.d();
            cyVar.b();
        }
    }

    /* compiled from: AppInfo.java */
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

    /* compiled from: AppInfo.java */
    /* loaded from: classes.dex */
    private static class c extends dj<ar> {
        private c() {
        }

        /* synthetic */ c(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // u.aly.dg
        public void a(cy cyVar, ar arVar) throws cf {
            de deVar = (de) cyVar;
            deVar.a(arVar.a);
            deVar.a(arVar.e.a());
            deVar.a(arVar.f);
            deVar.a(arVar.g);
            BitSet bitSet = new BitSet();
            if (arVar.i()) {
                bitSet.set(0);
            }
            if (arVar.l()) {
                bitSet.set(1);
            }
            if (arVar.o()) {
                bitSet.set(2);
            }
            if (arVar.A()) {
                bitSet.set(3);
            }
            if (arVar.D()) {
                bitSet.set(4);
            }
            if (arVar.G()) {
                bitSet.set(5);
            }
            if (arVar.J()) {
                bitSet.set(6);
            }
            deVar.a(bitSet, 7);
            if (arVar.i()) {
                deVar.a(arVar.b);
            }
            if (arVar.l()) {
                deVar.a(arVar.c);
            }
            if (arVar.o()) {
                deVar.a(arVar.d);
            }
            if (arVar.A()) {
                deVar.a(arVar.h);
            }
            if (arVar.D()) {
                deVar.a(arVar.i);
            }
            if (arVar.G()) {
                deVar.a(arVar.j);
            }
            if (arVar.J()) {
                deVar.a(arVar.k);
            }
        }

        @Override // u.aly.dg
        public void b(cy cyVar, ar arVar) throws cf {
            de deVar = (de) cyVar;
            arVar.a = deVar.z();
            arVar.a(true);
            arVar.e = bm.a(deVar.w());
            arVar.e(true);
            arVar.f = deVar.z();
            arVar.f(true);
            arVar.g = deVar.z();
            arVar.g(true);
            BitSet b = deVar.b(7);
            if (b.get(0)) {
                arVar.b = deVar.z();
                arVar.b(true);
            }
            if (b.get(1)) {
                arVar.c = deVar.w();
                arVar.c(true);
            }
            if (b.get(2)) {
                arVar.d = deVar.z();
                arVar.d(true);
            }
            if (b.get(3)) {
                arVar.h = deVar.z();
                arVar.h(true);
            }
            if (b.get(4)) {
                arVar.i = deVar.z();
                arVar.i(true);
            }
            if (b.get(5)) {
                arVar.j = deVar.w();
                arVar.j(true);
            }
            if (b.get(6)) {
                arVar.k = deVar.z();
                arVar.k(true);
            }
        }
    }
}
