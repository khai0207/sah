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

/* compiled from: DeviceInfo.java */
/* loaded from: classes.dex */
public class au implements Serializable, Cloneable, bz<au, e> {
    private static final Map<Class<? extends dg>, dh> M;
    private static final int N = 0;
    private static final int O = 1;
    private static final int P = 2;
    public static final Map<e, cl> s;
    private byte Q;
    private e[] R;
    public String a;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;
    public String h;
    public bk i;
    public boolean j;
    public boolean k;
    public String l;
    public String m;
    public long n;
    public String o;
    public String p;
    public String q;
    public String r;
    private static final dd t = new dd("DeviceInfo");

    /* renamed from: u */
    private static final ct f66u = new ct("device_id", (byte) 11, 1);
    private static final ct v = new ct("idmd5", (byte) 11, 2);
    private static final ct w = new ct("mac_address", (byte) 11, 3);
    private static final ct x = new ct("open_udid", (byte) 11, 4);
    private static final ct y = new ct("model", (byte) 11, 5);
    private static final ct z = new ct("cpu", (byte) 11, 6);
    private static final ct A = new ct("os", (byte) 11, 7);
    private static final ct B = new ct("os_version", (byte) 11, 8);
    private static final ct C = new ct("resolution", (byte) 12, 9);
    private static final ct D = new ct("is_jailbroken", (byte) 2, 10);
    private static final ct E = new ct("is_pirated", (byte) 2, 11);
    private static final ct F = new ct("device_board", (byte) 11, 12);
    private static final ct G = new ct("device_brand", (byte) 11, 13);
    private static final ct H = new ct("device_manutime", (byte) 10, 14);
    private static final ct I = new ct("device_manufacturer", (byte) 11, 15);
    private static final ct J = new ct("device_manuid", (byte) 11, 16);
    private static final ct K = new ct("device_name", (byte) 11, 17);
    private static final ct L = new ct("wp_device", (byte) 11, 18);

    static {
        HashMap hashMap = new HashMap();
        M = hashMap;
        hashMap.put(di.class, new b());
        M.put(dj.class, new d());
        EnumMap enumMap = new EnumMap(e.class);
        enumMap.put((EnumMap) e.DEVICE_ID, (e) new cl("device_id", (byte) 2, new cm((byte) 11)));
        enumMap.put((EnumMap) e.IDMD5, (e) new cl("idmd5", (byte) 2, new cm((byte) 11)));
        enumMap.put((EnumMap) e.MAC_ADDRESS, (e) new cl("mac_address", (byte) 2, new cm((byte) 11)));
        enumMap.put((EnumMap) e.OPEN_UDID, (e) new cl("open_udid", (byte) 2, new cm((byte) 11)));
        enumMap.put((EnumMap) e.MODEL, (e) new cl("model", (byte) 2, new cm((byte) 11)));
        enumMap.put((EnumMap) e.CPU, (e) new cl("cpu", (byte) 2, new cm((byte) 11)));
        enumMap.put((EnumMap) e.OS, (e) new cl("os", (byte) 2, new cm((byte) 11)));
        enumMap.put((EnumMap) e.OS_VERSION, (e) new cl("os_version", (byte) 2, new cm((byte) 11)));
        enumMap.put((EnumMap) e.RESOLUTION, (e) new cl("resolution", (byte) 2, new cq((byte) 12, bk.class)));
        enumMap.put((EnumMap) e.IS_JAILBROKEN, (e) new cl("is_jailbroken", (byte) 2, new cm((byte) 2)));
        enumMap.put((EnumMap) e.IS_PIRATED, (e) new cl("is_pirated", (byte) 2, new cm((byte) 2)));
        enumMap.put((EnumMap) e.DEVICE_BOARD, (e) new cl("device_board", (byte) 2, new cm((byte) 11)));
        enumMap.put((EnumMap) e.DEVICE_BRAND, (e) new cl("device_brand", (byte) 2, new cm((byte) 11)));
        enumMap.put((EnumMap) e.DEVICE_MANUTIME, (e) new cl("device_manutime", (byte) 2, new cm((byte) 10)));
        enumMap.put((EnumMap) e.DEVICE_MANUFACTURER, (e) new cl("device_manufacturer", (byte) 2, new cm((byte) 11)));
        enumMap.put((EnumMap) e.DEVICE_MANUID, (e) new cl("device_manuid", (byte) 2, new cm((byte) 11)));
        enumMap.put((EnumMap) e.DEVICE_NAME, (e) new cl("device_name", (byte) 2, new cm((byte) 11)));
        enumMap.put((EnumMap) e.WP_DEVICE, (e) new cl("wp_device", (byte) 2, new cm((byte) 11)));
        Map<e, cl> unmodifiableMap = Collections.unmodifiableMap(enumMap);
        s = unmodifiableMap;
        cl.a(au.class, unmodifiableMap);
    }

    /* compiled from: DeviceInfo.java */
    /* loaded from: classes.dex */
    public enum e implements cg {
        DEVICE_ID(1, "device_id"),
        IDMD5(2, "idmd5"),
        MAC_ADDRESS(3, "mac_address"),
        OPEN_UDID(4, "open_udid"),
        MODEL(5, "model"),
        CPU(6, "cpu"),
        OS(7, "os"),
        OS_VERSION(8, "os_version"),
        RESOLUTION(9, "resolution"),
        IS_JAILBROKEN(10, "is_jailbroken"),
        IS_PIRATED(11, "is_pirated"),
        DEVICE_BOARD(12, "device_board"),
        DEVICE_BRAND(13, "device_brand"),
        DEVICE_MANUTIME(14, "device_manutime"),
        DEVICE_MANUFACTURER(15, "device_manufacturer"),
        DEVICE_MANUID(16, "device_manuid"),
        DEVICE_NAME(17, "device_name"),
        WP_DEVICE(18, "wp_device");

        private static final Map<String, e> s = new HashMap();
        private final short t;

        /* renamed from: u */
        private final String f67u;

        static {
            Iterator it = EnumSet.allOf(e.class).iterator();
            while (it.hasNext()) {
                e eVar = (e) it.next();
                s.put(eVar.b(), eVar);
            }
        }

        public static e a(int i) {
            switch (i) {
                case 1:
                    return DEVICE_ID;
                case 2:
                    return IDMD5;
                case 3:
                    return MAC_ADDRESS;
                case 4:
                    return OPEN_UDID;
                case 5:
                    return MODEL;
                case 6:
                    return CPU;
                case 7:
                    return OS;
                case 8:
                    return OS_VERSION;
                case 9:
                    return RESOLUTION;
                case 10:
                    return IS_JAILBROKEN;
                case 11:
                    return IS_PIRATED;
                case 12:
                    return DEVICE_BOARD;
                case 13:
                    return DEVICE_BRAND;
                case 14:
                    return DEVICE_MANUTIME;
                case 15:
                    return DEVICE_MANUFACTURER;
                case 16:
                    return DEVICE_MANUID;
                case 17:
                    return DEVICE_NAME;
                case 18:
                    return WP_DEVICE;
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
            return s.get(str);
        }

        e(short s2, String str) {
            this.t = s2;
            this.f67u = str;
        }

        @Override // u.aly.cg
        public short a() {
            return this.t;
        }

        @Override // u.aly.cg
        public String b() {
            return this.f67u;
        }
    }

    public au() {
        this.Q = (byte) 0;
        this.R = new e[]{e.DEVICE_ID, e.IDMD5, e.MAC_ADDRESS, e.OPEN_UDID, e.MODEL, e.CPU, e.OS, e.OS_VERSION, e.RESOLUTION, e.IS_JAILBROKEN, e.IS_PIRATED, e.DEVICE_BOARD, e.DEVICE_BRAND, e.DEVICE_MANUTIME, e.DEVICE_MANUFACTURER, e.DEVICE_MANUID, e.DEVICE_NAME, e.WP_DEVICE};
    }

    public au(au auVar) {
        this.Q = (byte) 0;
        this.R = new e[]{e.DEVICE_ID, e.IDMD5, e.MAC_ADDRESS, e.OPEN_UDID, e.MODEL, e.CPU, e.OS, e.OS_VERSION, e.RESOLUTION, e.IS_JAILBROKEN, e.IS_PIRATED, e.DEVICE_BOARD, e.DEVICE_BRAND, e.DEVICE_MANUTIME, e.DEVICE_MANUFACTURER, e.DEVICE_MANUID, e.DEVICE_NAME, e.WP_DEVICE};
        this.Q = auVar.Q;
        if (auVar.e()) {
            this.a = auVar.a;
        }
        if (auVar.i()) {
            this.b = auVar.b;
        }
        if (auVar.l()) {
            this.c = auVar.c;
        }
        if (auVar.o()) {
            this.d = auVar.d;
        }
        if (auVar.r()) {
            this.e = auVar.e;
        }
        if (auVar.u()) {
            this.f = auVar.f;
        }
        if (auVar.x()) {
            this.g = auVar.g;
        }
        if (auVar.A()) {
            this.h = auVar.h;
        }
        if (auVar.D()) {
            this.i = new bk(auVar.i);
        }
        this.j = auVar.j;
        this.k = auVar.k;
        if (auVar.M()) {
            this.l = auVar.l;
        }
        if (auVar.P()) {
            this.m = auVar.m;
        }
        this.n = auVar.n;
        if (auVar.V()) {
            this.o = auVar.o;
        }
        if (auVar.Y()) {
            this.p = auVar.p;
        }
        if (auVar.ab()) {
            this.q = auVar.q;
        }
        if (auVar.ae()) {
            this.r = auVar.r;
        }
    }

    @Override // u.aly.bz
    /* renamed from: a */
    public au g() {
        return new au(this);
    }

    @Override // u.aly.bz
    public void b() {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        k(false);
        this.j = false;
        m(false);
        this.k = false;
        this.l = null;
        this.m = null;
        p(false);
        this.n = 0L;
        this.o = null;
        this.p = null;
        this.q = null;
        this.r = null;
    }

    public String c() {
        return this.a;
    }

    public au a(String str) {
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

    public au b(String str) {
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

    public au c(String str) {
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

    public String m() {
        return this.d;
    }

    public au d(String str) {
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

    public String p() {
        return this.e;
    }

    public au e(String str) {
        this.e = str;
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

    public au f(String str) {
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

    public au g(String str) {
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

    public au h(String str) {
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

    public bk B() {
        return this.i;
    }

    public au a(bk bkVar) {
        this.i = bkVar;
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

    public boolean E() {
        return this.j;
    }

    public au j(boolean z2) {
        this.j = z2;
        k(true);
        return this;
    }

    public void F() {
        this.Q = bw.b(this.Q, 0);
    }

    public boolean G() {
        return bw.a(this.Q, 0);
    }

    public void k(boolean z2) {
        this.Q = bw.a(this.Q, 0, z2);
    }

    public boolean H() {
        return this.k;
    }

    public au l(boolean z2) {
        this.k = z2;
        m(true);
        return this;
    }

    public void I() {
        this.Q = bw.b(this.Q, 1);
    }

    public boolean J() {
        return bw.a(this.Q, 1);
    }

    public void m(boolean z2) {
        this.Q = bw.a(this.Q, 1, z2);
    }

    public String K() {
        return this.l;
    }

    public au i(String str) {
        this.l = str;
        return this;
    }

    public void L() {
        this.l = null;
    }

    public boolean M() {
        return this.l != null;
    }

    public void n(boolean z2) {
        if (z2) {
            return;
        }
        this.l = null;
    }

    public String N() {
        return this.m;
    }

    public au j(String str) {
        this.m = str;
        return this;
    }

    public void O() {
        this.m = null;
    }

    public boolean P() {
        return this.m != null;
    }

    public void o(boolean z2) {
        if (z2) {
            return;
        }
        this.m = null;
    }

    public long Q() {
        return this.n;
    }

    public au a(long j) {
        this.n = j;
        p(true);
        return this;
    }

    public void R() {
        this.Q = bw.b(this.Q, 2);
    }

    public boolean S() {
        return bw.a(this.Q, 2);
    }

    public void p(boolean z2) {
        this.Q = bw.a(this.Q, 2, z2);
    }

    public String T() {
        return this.o;
    }

    public au k(String str) {
        this.o = str;
        return this;
    }

    public void U() {
        this.o = null;
    }

    public boolean V() {
        return this.o != null;
    }

    public void q(boolean z2) {
        if (z2) {
            return;
        }
        this.o = null;
    }

    public String W() {
        return this.p;
    }

    public au l(String str) {
        this.p = str;
        return this;
    }

    public void X() {
        this.p = null;
    }

    public boolean Y() {
        return this.p != null;
    }

    public void r(boolean z2) {
        if (z2) {
            return;
        }
        this.p = null;
    }

    public String Z() {
        return this.q;
    }

    public au m(String str) {
        this.q = str;
        return this;
    }

    public void aa() {
        this.q = null;
    }

    public boolean ab() {
        return this.q != null;
    }

    public void s(boolean z2) {
        if (z2) {
            return;
        }
        this.q = null;
    }

    public String ac() {
        return this.r;
    }

    public au n(String str) {
        this.r = str;
        return this;
    }

    public void ad() {
        this.r = null;
    }

    public boolean ae() {
        return this.r != null;
    }

    public void t(boolean z2) {
        if (z2) {
            return;
        }
        this.r = null;
    }

    @Override // u.aly.bz
    /* renamed from: a */
    public e b(int i) {
        return e.a(i);
    }

    @Override // u.aly.bz
    public void a(cy cyVar) throws cf {
        M.get(cyVar.D()).b().b(cyVar, this);
    }

    @Override // u.aly.bz
    public void b(cy cyVar) throws cf {
        M.get(cyVar.D()).b().a(cyVar, this);
    }

    public String toString() {
        boolean z2;
        StringBuilder sb = new StringBuilder("DeviceInfo(");
        boolean z3 = false;
        if (e()) {
            sb.append("device_id:");
            String str = this.a;
            if (str == null) {
                sb.append(Constants.NULL_VERSION_ID);
            } else {
                sb.append(str);
            }
            z2 = false;
        } else {
            z2 = true;
        }
        if (i()) {
            if (!z2) {
                sb.append(", ");
            }
            sb.append("idmd5:");
            String str2 = this.b;
            if (str2 == null) {
                sb.append(Constants.NULL_VERSION_ID);
            } else {
                sb.append(str2);
            }
            z2 = false;
        }
        if (l()) {
            if (!z2) {
                sb.append(", ");
            }
            sb.append("mac_address:");
            String str3 = this.c;
            if (str3 == null) {
                sb.append(Constants.NULL_VERSION_ID);
            } else {
                sb.append(str3);
            }
            z2 = false;
        }
        if (o()) {
            if (!z2) {
                sb.append(", ");
            }
            sb.append("open_udid:");
            String str4 = this.d;
            if (str4 == null) {
                sb.append(Constants.NULL_VERSION_ID);
            } else {
                sb.append(str4);
            }
            z2 = false;
        }
        if (r()) {
            if (!z2) {
                sb.append(", ");
            }
            sb.append("model:");
            String str5 = this.e;
            if (str5 == null) {
                sb.append(Constants.NULL_VERSION_ID);
            } else {
                sb.append(str5);
            }
            z2 = false;
        }
        if (u()) {
            if (!z2) {
                sb.append(", ");
            }
            sb.append("cpu:");
            String str6 = this.f;
            if (str6 == null) {
                sb.append(Constants.NULL_VERSION_ID);
            } else {
                sb.append(str6);
            }
            z2 = false;
        }
        if (x()) {
            if (!z2) {
                sb.append(", ");
            }
            sb.append("os:");
            String str7 = this.g;
            if (str7 == null) {
                sb.append(Constants.NULL_VERSION_ID);
            } else {
                sb.append(str7);
            }
            z2 = false;
        }
        if (A()) {
            if (!z2) {
                sb.append(", ");
            }
            sb.append("os_version:");
            String str8 = this.h;
            if (str8 == null) {
                sb.append(Constants.NULL_VERSION_ID);
            } else {
                sb.append(str8);
            }
            z2 = false;
        }
        if (D()) {
            if (!z2) {
                sb.append(", ");
            }
            sb.append("resolution:");
            bk bkVar = this.i;
            if (bkVar == null) {
                sb.append(Constants.NULL_VERSION_ID);
            } else {
                sb.append(bkVar);
            }
            z2 = false;
        }
        if (G()) {
            if (!z2) {
                sb.append(", ");
            }
            sb.append("is_jailbroken:");
            sb.append(this.j);
            z2 = false;
        }
        if (J()) {
            if (!z2) {
                sb.append(", ");
            }
            sb.append("is_pirated:");
            sb.append(this.k);
            z2 = false;
        }
        if (M()) {
            if (!z2) {
                sb.append(", ");
            }
            sb.append("device_board:");
            String str9 = this.l;
            if (str9 == null) {
                sb.append(Constants.NULL_VERSION_ID);
            } else {
                sb.append(str9);
            }
            z2 = false;
        }
        if (P()) {
            if (!z2) {
                sb.append(", ");
            }
            sb.append("device_brand:");
            String str10 = this.m;
            if (str10 == null) {
                sb.append(Constants.NULL_VERSION_ID);
            } else {
                sb.append(str10);
            }
            z2 = false;
        }
        if (S()) {
            if (!z2) {
                sb.append(", ");
            }
            sb.append("device_manutime:");
            sb.append(this.n);
            z2 = false;
        }
        if (V()) {
            if (!z2) {
                sb.append(", ");
            }
            sb.append("device_manufacturer:");
            String str11 = this.o;
            if (str11 == null) {
                sb.append(Constants.NULL_VERSION_ID);
            } else {
                sb.append(str11);
            }
            z2 = false;
        }
        if (Y()) {
            if (!z2) {
                sb.append(", ");
            }
            sb.append("device_manuid:");
            String str12 = this.p;
            if (str12 == null) {
                sb.append(Constants.NULL_VERSION_ID);
            } else {
                sb.append(str12);
            }
            z2 = false;
        }
        if (ab()) {
            if (!z2) {
                sb.append(", ");
            }
            sb.append("device_name:");
            String str13 = this.q;
            if (str13 == null) {
                sb.append(Constants.NULL_VERSION_ID);
            } else {
                sb.append(str13);
            }
        } else {
            z3 = z2;
        }
        if (ae()) {
            if (!z3) {
                sb.append(", ");
            }
            sb.append("wp_device:");
            String str14 = this.r;
            if (str14 == null) {
                sb.append(Constants.NULL_VERSION_ID);
            } else {
                sb.append(str14);
            }
        }
        sb.append(")");
        return sb.toString();
    }

    public void af() throws cf {
        bk bkVar = this.i;
        if (bkVar != null) {
            bkVar.j();
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
            this.Q = (byte) 0;
            a(new cs(new dk(objectInputStream)));
        } catch (cf e2) {
            throw new IOException(e2.getMessage());
        }
    }

    /* compiled from: DeviceInfo.java */
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

    /* compiled from: DeviceInfo.java */
    /* loaded from: classes.dex */
    private static class a extends di<au> {
        private a() {
        }

        /* synthetic */ a(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // u.aly.dg
        /* renamed from: a */
        public void b(cy cyVar, au auVar) throws cf {
            cyVar.j();
            while (true) {
                ct l = cyVar.l();
                if (l.b != 0) {
                    switch (l.c) {
                        case 1:
                            if (l.b == 11) {
                                auVar.a = cyVar.z();
                                auVar.a(true);
                                break;
                            } else {
                                db.a(cyVar, l.b);
                                break;
                            }
                        case 2:
                            if (l.b == 11) {
                                auVar.b = cyVar.z();
                                auVar.b(true);
                                break;
                            } else {
                                db.a(cyVar, l.b);
                                break;
                            }
                        case 3:
                            if (l.b == 11) {
                                auVar.c = cyVar.z();
                                auVar.c(true);
                                break;
                            } else {
                                db.a(cyVar, l.b);
                                break;
                            }
                        case 4:
                            if (l.b == 11) {
                                auVar.d = cyVar.z();
                                auVar.d(true);
                                break;
                            } else {
                                db.a(cyVar, l.b);
                                break;
                            }
                        case 5:
                            if (l.b == 11) {
                                auVar.e = cyVar.z();
                                auVar.e(true);
                                break;
                            } else {
                                db.a(cyVar, l.b);
                                break;
                            }
                        case 6:
                            if (l.b == 11) {
                                auVar.f = cyVar.z();
                                auVar.f(true);
                                break;
                            } else {
                                db.a(cyVar, l.b);
                                break;
                            }
                        case 7:
                            if (l.b == 11) {
                                auVar.g = cyVar.z();
                                auVar.g(true);
                                break;
                            } else {
                                db.a(cyVar, l.b);
                                break;
                            }
                        case 8:
                            if (l.b == 11) {
                                auVar.h = cyVar.z();
                                auVar.h(true);
                                break;
                            } else {
                                db.a(cyVar, l.b);
                                break;
                            }
                        case 9:
                            if (l.b == 12) {
                                auVar.i = new bk();
                                auVar.i.a(cyVar);
                                auVar.i(true);
                                break;
                            } else {
                                db.a(cyVar, l.b);
                                break;
                            }
                        case 10:
                            if (l.b == 2) {
                                auVar.j = cyVar.t();
                                auVar.k(true);
                                break;
                            } else {
                                db.a(cyVar, l.b);
                                break;
                            }
                        case 11:
                            if (l.b == 2) {
                                auVar.k = cyVar.t();
                                auVar.m(true);
                                break;
                            } else {
                                db.a(cyVar, l.b);
                                break;
                            }
                        case 12:
                            if (l.b == 11) {
                                auVar.l = cyVar.z();
                                auVar.n(true);
                                break;
                            } else {
                                db.a(cyVar, l.b);
                                break;
                            }
                        case 13:
                            if (l.b == 11) {
                                auVar.m = cyVar.z();
                                auVar.o(true);
                                break;
                            } else {
                                db.a(cyVar, l.b);
                                break;
                            }
                        case 14:
                            if (l.b == 10) {
                                auVar.n = cyVar.x();
                                auVar.p(true);
                                break;
                            } else {
                                db.a(cyVar, l.b);
                                break;
                            }
                        case 15:
                            if (l.b == 11) {
                                auVar.o = cyVar.z();
                                auVar.q(true);
                                break;
                            } else {
                                db.a(cyVar, l.b);
                                break;
                            }
                        case 16:
                            if (l.b == 11) {
                                auVar.p = cyVar.z();
                                auVar.r(true);
                                break;
                            } else {
                                db.a(cyVar, l.b);
                                break;
                            }
                        case 17:
                            if (l.b == 11) {
                                auVar.q = cyVar.z();
                                auVar.s(true);
                                break;
                            } else {
                                db.a(cyVar, l.b);
                                break;
                            }
                        case 18:
                            if (l.b == 11) {
                                auVar.r = cyVar.z();
                                auVar.t(true);
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
                    auVar.af();
                    return;
                }
            }
        }

        @Override // u.aly.dg
        /* renamed from: b */
        public void a(cy cyVar, au auVar) throws cf {
            auVar.af();
            cyVar.a(au.t);
            if (auVar.a != null && auVar.e()) {
                cyVar.a(au.f66u);
                cyVar.a(auVar.a);
                cyVar.c();
            }
            if (auVar.b != null && auVar.i()) {
                cyVar.a(au.v);
                cyVar.a(auVar.b);
                cyVar.c();
            }
            if (auVar.c != null && auVar.l()) {
                cyVar.a(au.w);
                cyVar.a(auVar.c);
                cyVar.c();
            }
            if (auVar.d != null && auVar.o()) {
                cyVar.a(au.x);
                cyVar.a(auVar.d);
                cyVar.c();
            }
            if (auVar.e != null && auVar.r()) {
                cyVar.a(au.y);
                cyVar.a(auVar.e);
                cyVar.c();
            }
            if (auVar.f != null && auVar.u()) {
                cyVar.a(au.z);
                cyVar.a(auVar.f);
                cyVar.c();
            }
            if (auVar.g != null && auVar.x()) {
                cyVar.a(au.A);
                cyVar.a(auVar.g);
                cyVar.c();
            }
            if (auVar.h != null && auVar.A()) {
                cyVar.a(au.B);
                cyVar.a(auVar.h);
                cyVar.c();
            }
            if (auVar.i != null && auVar.D()) {
                cyVar.a(au.C);
                auVar.i.b(cyVar);
                cyVar.c();
            }
            if (auVar.G()) {
                cyVar.a(au.D);
                cyVar.a(auVar.j);
                cyVar.c();
            }
            if (auVar.J()) {
                cyVar.a(au.E);
                cyVar.a(auVar.k);
                cyVar.c();
            }
            if (auVar.l != null && auVar.M()) {
                cyVar.a(au.F);
                cyVar.a(auVar.l);
                cyVar.c();
            }
            if (auVar.m != null && auVar.P()) {
                cyVar.a(au.G);
                cyVar.a(auVar.m);
                cyVar.c();
            }
            if (auVar.S()) {
                cyVar.a(au.H);
                cyVar.a(auVar.n);
                cyVar.c();
            }
            if (auVar.o != null && auVar.V()) {
                cyVar.a(au.I);
                cyVar.a(auVar.o);
                cyVar.c();
            }
            if (auVar.p != null && auVar.Y()) {
                cyVar.a(au.J);
                cyVar.a(auVar.p);
                cyVar.c();
            }
            if (auVar.q != null && auVar.ab()) {
                cyVar.a(au.K);
                cyVar.a(auVar.q);
                cyVar.c();
            }
            if (auVar.r != null && auVar.ae()) {
                cyVar.a(au.L);
                cyVar.a(auVar.r);
                cyVar.c();
            }
            cyVar.d();
            cyVar.b();
        }
    }

    /* compiled from: DeviceInfo.java */
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

    /* compiled from: DeviceInfo.java */
    /* loaded from: classes.dex */
    private static class c extends dj<au> {
        private c() {
        }

        /* synthetic */ c(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // u.aly.dg
        public void a(cy cyVar, au auVar) throws cf {
            de deVar = (de) cyVar;
            BitSet bitSet = new BitSet();
            if (auVar.e()) {
                bitSet.set(0);
            }
            if (auVar.i()) {
                bitSet.set(1);
            }
            if (auVar.l()) {
                bitSet.set(2);
            }
            if (auVar.o()) {
                bitSet.set(3);
            }
            if (auVar.r()) {
                bitSet.set(4);
            }
            if (auVar.u()) {
                bitSet.set(5);
            }
            if (auVar.x()) {
                bitSet.set(6);
            }
            if (auVar.A()) {
                bitSet.set(7);
            }
            if (auVar.D()) {
                bitSet.set(8);
            }
            if (auVar.G()) {
                bitSet.set(9);
            }
            if (auVar.J()) {
                bitSet.set(10);
            }
            if (auVar.M()) {
                bitSet.set(11);
            }
            if (auVar.P()) {
                bitSet.set(12);
            }
            if (auVar.S()) {
                bitSet.set(13);
            }
            if (auVar.V()) {
                bitSet.set(14);
            }
            if (auVar.Y()) {
                bitSet.set(15);
            }
            if (auVar.ab()) {
                bitSet.set(16);
            }
            if (auVar.ae()) {
                bitSet.set(17);
            }
            deVar.a(bitSet, 18);
            if (auVar.e()) {
                deVar.a(auVar.a);
            }
            if (auVar.i()) {
                deVar.a(auVar.b);
            }
            if (auVar.l()) {
                deVar.a(auVar.c);
            }
            if (auVar.o()) {
                deVar.a(auVar.d);
            }
            if (auVar.r()) {
                deVar.a(auVar.e);
            }
            if (auVar.u()) {
                deVar.a(auVar.f);
            }
            if (auVar.x()) {
                deVar.a(auVar.g);
            }
            if (auVar.A()) {
                deVar.a(auVar.h);
            }
            if (auVar.D()) {
                auVar.i.b(deVar);
            }
            if (auVar.G()) {
                deVar.a(auVar.j);
            }
            if (auVar.J()) {
                deVar.a(auVar.k);
            }
            if (auVar.M()) {
                deVar.a(auVar.l);
            }
            if (auVar.P()) {
                deVar.a(auVar.m);
            }
            if (auVar.S()) {
                deVar.a(auVar.n);
            }
            if (auVar.V()) {
                deVar.a(auVar.o);
            }
            if (auVar.Y()) {
                deVar.a(auVar.p);
            }
            if (auVar.ab()) {
                deVar.a(auVar.q);
            }
            if (auVar.ae()) {
                deVar.a(auVar.r);
            }
        }

        @Override // u.aly.dg
        public void b(cy cyVar, au auVar) throws cf {
            de deVar = (de) cyVar;
            BitSet b = deVar.b(18);
            if (b.get(0)) {
                auVar.a = deVar.z();
                auVar.a(true);
            }
            if (b.get(1)) {
                auVar.b = deVar.z();
                auVar.b(true);
            }
            if (b.get(2)) {
                auVar.c = deVar.z();
                auVar.c(true);
            }
            if (b.get(3)) {
                auVar.d = deVar.z();
                auVar.d(true);
            }
            if (b.get(4)) {
                auVar.e = deVar.z();
                auVar.e(true);
            }
            if (b.get(5)) {
                auVar.f = deVar.z();
                auVar.f(true);
            }
            if (b.get(6)) {
                auVar.g = deVar.z();
                auVar.g(true);
            }
            if (b.get(7)) {
                auVar.h = deVar.z();
                auVar.h(true);
            }
            if (b.get(8)) {
                auVar.i = new bk();
                auVar.i.a(deVar);
                auVar.i(true);
            }
            if (b.get(9)) {
                auVar.j = deVar.t();
                auVar.k(true);
            }
            if (b.get(10)) {
                auVar.k = deVar.t();
                auVar.m(true);
            }
            if (b.get(11)) {
                auVar.l = deVar.z();
                auVar.n(true);
            }
            if (b.get(12)) {
                auVar.m = deVar.z();
                auVar.o(true);
            }
            if (b.get(13)) {
                auVar.n = deVar.x();
                auVar.p(true);
            }
            if (b.get(14)) {
                auVar.o = deVar.z();
                auVar.q(true);
            }
            if (b.get(15)) {
                auVar.p = deVar.z();
                auVar.r(true);
            }
            if (b.get(16)) {
                auVar.q = deVar.z();
                auVar.s(true);
            }
            if (b.get(17)) {
                auVar.r = deVar.z();
                auVar.t(true);
            }
        }
    }
}
