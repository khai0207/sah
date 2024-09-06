package u.aly;

import com.netease.nimlib.amazonaws.services.s3.internal.Constants;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: UALogEntry.java */
/* loaded from: classes.dex */
public class bp implements Serializable, Cloneable, bz<bp, e> {
    private static final Map<Class<? extends dg>, dh> A;
    public static final Map<e, cl> m;
    private static final dd n = new dd("UALogEntry");
    private static final ct o = new ct("client_stats", (byte) 12, 1);
    private static final ct p = new ct("app_info", (byte) 12, 2);
    private static final ct q = new ct("device_info", (byte) 12, 3);
    private static final ct r = new ct("misc_info", (byte) 12, 4);
    private static final ct s = new ct("activate_msg", (byte) 12, 5);
    private static final ct t = new ct("instant_msgs", df.m, 6);

    /* renamed from: u */
    private static final ct f70u = new ct("sessions", df.m, 7);
    private static final ct v = new ct("imprint", (byte) 12, 8);
    private static final ct w = new ct("id_tracking", (byte) 12, 9);
    private static final ct x = new ct("active_user", (byte) 12, 10);
    private static final ct y = new ct("control_policy", (byte) 12, 11);
    private static final ct z = new ct("group_info", df.k, 12);
    private e[] B;
    public as a;
    public ar b;
    public au c;
    public bh d;
    public ap e;
    public List<be> f;
    public List<bn> g;
    public bc h;
    public bb i;
    public aq j;
    public at k;
    public Map<String, Integer> l;

    static {
        HashMap hashMap = new HashMap();
        A = hashMap;
        hashMap.put(di.class, new b());
        A.put(dj.class, new d());
        EnumMap enumMap = new EnumMap(e.class);
        enumMap.put((EnumMap) e.CLIENT_STATS, (e) new cl("client_stats", (byte) 1, new cq((byte) 12, as.class)));
        enumMap.put((EnumMap) e.APP_INFO, (e) new cl("app_info", (byte) 1, new cq((byte) 12, ar.class)));
        enumMap.put((EnumMap) e.DEVICE_INFO, (e) new cl("device_info", (byte) 1, new cq((byte) 12, au.class)));
        enumMap.put((EnumMap) e.MISC_INFO, (e) new cl("misc_info", (byte) 1, new cq((byte) 12, bh.class)));
        enumMap.put((EnumMap) e.ACTIVATE_MSG, (e) new cl("activate_msg", (byte) 2, new cq((byte) 12, ap.class)));
        enumMap.put((EnumMap) e.INSTANT_MSGS, (e) new cl("instant_msgs", (byte) 2, new cn(df.m, new cq((byte) 12, be.class))));
        enumMap.put((EnumMap) e.SESSIONS, (e) new cl("sessions", (byte) 2, new cn(df.m, new cq((byte) 12, bn.class))));
        enumMap.put((EnumMap) e.IMPRINT, (e) new cl("imprint", (byte) 2, new cq((byte) 12, bc.class)));
        enumMap.put((EnumMap) e.ID_TRACKING, (e) new cl("id_tracking", (byte) 2, new cq((byte) 12, bb.class)));
        enumMap.put((EnumMap) e.ACTIVE_USER, (e) new cl("active_user", (byte) 2, new cq((byte) 12, aq.class)));
        enumMap.put((EnumMap) e.CONTROL_POLICY, (e) new cl("control_policy", (byte) 2, new cq((byte) 12, at.class)));
        enumMap.put((EnumMap) e.GROUP_INFO, (e) new cl("group_info", (byte) 2, new co(df.k, new cm((byte) 11), new cm((byte) 8))));
        Map<e, cl> unmodifiableMap = Collections.unmodifiableMap(enumMap);
        m = unmodifiableMap;
        cl.a(bp.class, unmodifiableMap);
    }

    /* compiled from: UALogEntry.java */
    /* loaded from: classes.dex */
    public enum e implements cg {
        CLIENT_STATS(1, "client_stats"),
        APP_INFO(2, "app_info"),
        DEVICE_INFO(3, "device_info"),
        MISC_INFO(4, "misc_info"),
        ACTIVATE_MSG(5, "activate_msg"),
        INSTANT_MSGS(6, "instant_msgs"),
        SESSIONS(7, "sessions"),
        IMPRINT(8, "imprint"),
        ID_TRACKING(9, "id_tracking"),
        ACTIVE_USER(10, "active_user"),
        CONTROL_POLICY(11, "control_policy"),
        GROUP_INFO(12, "group_info");

        private static final Map<String, e> m = new HashMap();
        private final short n;
        private final String o;

        static {
            Iterator it = EnumSet.allOf(e.class).iterator();
            while (it.hasNext()) {
                e eVar = (e) it.next();
                m.put(eVar.b(), eVar);
            }
        }

        public static e a(int i) {
            switch (i) {
                case 1:
                    return CLIENT_STATS;
                case 2:
                    return APP_INFO;
                case 3:
                    return DEVICE_INFO;
                case 4:
                    return MISC_INFO;
                case 5:
                    return ACTIVATE_MSG;
                case 6:
                    return INSTANT_MSGS;
                case 7:
                    return SESSIONS;
                case 8:
                    return IMPRINT;
                case 9:
                    return ID_TRACKING;
                case 10:
                    return ACTIVE_USER;
                case 11:
                    return CONTROL_POLICY;
                case 12:
                    return GROUP_INFO;
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
            return m.get(str);
        }

        e(short s, String str) {
            this.n = s;
            this.o = str;
        }

        @Override // u.aly.cg
        public short a() {
            return this.n;
        }

        @Override // u.aly.cg
        public String b() {
            return this.o;
        }
    }

    public bp() {
        this.B = new e[]{e.ACTIVATE_MSG, e.INSTANT_MSGS, e.SESSIONS, e.IMPRINT, e.ID_TRACKING, e.ACTIVE_USER, e.CONTROL_POLICY, e.GROUP_INFO};
    }

    public bp(as asVar, ar arVar, au auVar, bh bhVar) {
        this();
        this.a = asVar;
        this.b = arVar;
        this.c = auVar;
        this.d = bhVar;
    }

    public bp(bp bpVar) {
        this.B = new e[]{e.ACTIVATE_MSG, e.INSTANT_MSGS, e.SESSIONS, e.IMPRINT, e.ID_TRACKING, e.ACTIVE_USER, e.CONTROL_POLICY, e.GROUP_INFO};
        if (bpVar.e()) {
            this.a = new as(bpVar.a);
        }
        if (bpVar.i()) {
            this.b = new ar(bpVar.b);
        }
        if (bpVar.l()) {
            this.c = new au(bpVar.c);
        }
        if (bpVar.o()) {
            this.d = new bh(bpVar.d);
        }
        if (bpVar.r()) {
            this.e = new ap(bpVar.e);
        }
        if (bpVar.w()) {
            ArrayList arrayList = new ArrayList();
            Iterator<be> it = bpVar.f.iterator();
            while (it.hasNext()) {
                arrayList.add(new be(it.next()));
            }
            this.f = arrayList;
        }
        if (bpVar.B()) {
            ArrayList arrayList2 = new ArrayList();
            Iterator<bn> it2 = bpVar.g.iterator();
            while (it2.hasNext()) {
                arrayList2.add(new bn(it2.next()));
            }
            this.g = arrayList2;
        }
        if (bpVar.E()) {
            this.h = new bc(bpVar.h);
        }
        if (bpVar.H()) {
            this.i = new bb(bpVar.i);
        }
        if (bpVar.K()) {
            this.j = new aq(bpVar.j);
        }
        if (bpVar.N()) {
            this.k = new at(bpVar.k);
        }
        if (bpVar.R()) {
            HashMap hashMap = new HashMap();
            for (Map.Entry<String, Integer> entry : bpVar.l.entrySet()) {
                hashMap.put(entry.getKey(), entry.getValue());
            }
            this.l = hashMap;
        }
    }

    @Override // u.aly.bz
    /* renamed from: a */
    public bp g() {
        return new bp(this);
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
        this.j = null;
        this.k = null;
        this.l = null;
    }

    public as c() {
        return this.a;
    }

    public bp a(as asVar) {
        this.a = asVar;
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

    public ar f() {
        return this.b;
    }

    public bp a(ar arVar) {
        this.b = arVar;
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

    public au j() {
        return this.c;
    }

    public bp a(au auVar) {
        this.c = auVar;
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

    public bh m() {
        return this.d;
    }

    public bp a(bh bhVar) {
        this.d = bhVar;
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

    public ap p() {
        return this.e;
    }

    public bp a(ap apVar) {
        this.e = apVar;
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

    public int s() {
        List<be> list = this.f;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public Iterator<be> t() {
        List<be> list = this.f;
        if (list == null) {
            return null;
        }
        return list.iterator();
    }

    public void a(be beVar) {
        if (this.f == null) {
            this.f = new ArrayList();
        }
        this.f.add(beVar);
    }

    public List<be> u() {
        return this.f;
    }

    public bp a(List<be> list) {
        this.f = list;
        return this;
    }

    public void v() {
        this.f = null;
    }

    public boolean w() {
        return this.f != null;
    }

    public void f(boolean z2) {
        if (z2) {
            return;
        }
        this.f = null;
    }

    public int x() {
        List<bn> list = this.g;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public Iterator<bn> y() {
        List<bn> list = this.g;
        if (list == null) {
            return null;
        }
        return list.iterator();
    }

    public void a(bn bnVar) {
        if (this.g == null) {
            this.g = new ArrayList();
        }
        this.g.add(bnVar);
    }

    public List<bn> z() {
        return this.g;
    }

    public bp b(List<bn> list) {
        this.g = list;
        return this;
    }

    public void A() {
        this.g = null;
    }

    public boolean B() {
        return this.g != null;
    }

    public void g(boolean z2) {
        if (z2) {
            return;
        }
        this.g = null;
    }

    public bc C() {
        return this.h;
    }

    public bp a(bc bcVar) {
        this.h = bcVar;
        return this;
    }

    public void D() {
        this.h = null;
    }

    public boolean E() {
        return this.h != null;
    }

    public void h(boolean z2) {
        if (z2) {
            return;
        }
        this.h = null;
    }

    public bb F() {
        return this.i;
    }

    public bp a(bb bbVar) {
        this.i = bbVar;
        return this;
    }

    public void G() {
        this.i = null;
    }

    public boolean H() {
        return this.i != null;
    }

    public void i(boolean z2) {
        if (z2) {
            return;
        }
        this.i = null;
    }

    public aq I() {
        return this.j;
    }

    public bp a(aq aqVar) {
        this.j = aqVar;
        return this;
    }

    public void J() {
        this.j = null;
    }

    public boolean K() {
        return this.j != null;
    }

    public void j(boolean z2) {
        if (z2) {
            return;
        }
        this.j = null;
    }

    public at L() {
        return this.k;
    }

    public bp a(at atVar) {
        this.k = atVar;
        return this;
    }

    public void M() {
        this.k = null;
    }

    public boolean N() {
        return this.k != null;
    }

    public void k(boolean z2) {
        if (z2) {
            return;
        }
        this.k = null;
    }

    public int O() {
        Map<String, Integer> map = this.l;
        if (map == null) {
            return 0;
        }
        return map.size();
    }

    public void a(String str, int i) {
        if (this.l == null) {
            this.l = new HashMap();
        }
        this.l.put(str, Integer.valueOf(i));
    }

    public Map<String, Integer> P() {
        return this.l;
    }

    public bp a(Map<String, Integer> map) {
        this.l = map;
        return this;
    }

    public void Q() {
        this.l = null;
    }

    public boolean R() {
        return this.l != null;
    }

    public void l(boolean z2) {
        if (z2) {
            return;
        }
        this.l = null;
    }

    @Override // u.aly.bz
    /* renamed from: a */
    public e b(int i) {
        return e.a(i);
    }

    @Override // u.aly.bz
    public void a(cy cyVar) throws cf {
        A.get(cyVar.D()).b().b(cyVar, this);
    }

    @Override // u.aly.bz
    public void b(cy cyVar) throws cf {
        A.get(cyVar.D()).b().a(cyVar, this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("UALogEntry(");
        sb.append("client_stats:");
        as asVar = this.a;
        if (asVar == null) {
            sb.append(Constants.NULL_VERSION_ID);
        } else {
            sb.append(asVar);
        }
        sb.append(", ");
        sb.append("app_info:");
        ar arVar = this.b;
        if (arVar == null) {
            sb.append(Constants.NULL_VERSION_ID);
        } else {
            sb.append(arVar);
        }
        sb.append(", ");
        sb.append("device_info:");
        au auVar = this.c;
        if (auVar == null) {
            sb.append(Constants.NULL_VERSION_ID);
        } else {
            sb.append(auVar);
        }
        sb.append(", ");
        sb.append("misc_info:");
        bh bhVar = this.d;
        if (bhVar == null) {
            sb.append(Constants.NULL_VERSION_ID);
        } else {
            sb.append(bhVar);
        }
        if (r()) {
            sb.append(", ");
            sb.append("activate_msg:");
            ap apVar = this.e;
            if (apVar == null) {
                sb.append(Constants.NULL_VERSION_ID);
            } else {
                sb.append(apVar);
            }
        }
        if (w()) {
            sb.append(", ");
            sb.append("instant_msgs:");
            List<be> list = this.f;
            if (list == null) {
                sb.append(Constants.NULL_VERSION_ID);
            } else {
                sb.append(list);
            }
        }
        if (B()) {
            sb.append(", ");
            sb.append("sessions:");
            List<bn> list2 = this.g;
            if (list2 == null) {
                sb.append(Constants.NULL_VERSION_ID);
            } else {
                sb.append(list2);
            }
        }
        if (E()) {
            sb.append(", ");
            sb.append("imprint:");
            bc bcVar = this.h;
            if (bcVar == null) {
                sb.append(Constants.NULL_VERSION_ID);
            } else {
                sb.append(bcVar);
            }
        }
        if (H()) {
            sb.append(", ");
            sb.append("id_tracking:");
            bb bbVar = this.i;
            if (bbVar == null) {
                sb.append(Constants.NULL_VERSION_ID);
            } else {
                sb.append(bbVar);
            }
        }
        if (K()) {
            sb.append(", ");
            sb.append("active_user:");
            aq aqVar = this.j;
            if (aqVar == null) {
                sb.append(Constants.NULL_VERSION_ID);
            } else {
                sb.append(aqVar);
            }
        }
        if (N()) {
            sb.append(", ");
            sb.append("control_policy:");
            at atVar = this.k;
            if (atVar == null) {
                sb.append(Constants.NULL_VERSION_ID);
            } else {
                sb.append(atVar);
            }
        }
        if (R()) {
            sb.append(", ");
            sb.append("group_info:");
            Map<String, Integer> map = this.l;
            if (map == null) {
                sb.append(Constants.NULL_VERSION_ID);
            } else {
                sb.append(map);
            }
        }
        sb.append(")");
        return sb.toString();
    }

    public void S() throws cf {
        as asVar = this.a;
        if (asVar == null) {
            throw new cz("Required field 'client_stats' was not present! Struct: " + toString());
        }
        if (this.b == null) {
            throw new cz("Required field 'app_info' was not present! Struct: " + toString());
        }
        if (this.c == null) {
            throw new cz("Required field 'device_info' was not present! Struct: " + toString());
        }
        if (this.d == null) {
            throw new cz("Required field 'misc_info' was not present! Struct: " + toString());
        }
        if (asVar != null) {
            asVar.m();
        }
        ar arVar = this.b;
        if (arVar != null) {
            arVar.K();
        }
        au auVar = this.c;
        if (auVar != null) {
            auVar.af();
        }
        bh bhVar = this.d;
        if (bhVar != null) {
            bhVar.H();
        }
        ap apVar = this.e;
        if (apVar != null) {
            apVar.f();
        }
        bc bcVar = this.h;
        if (bcVar != null) {
            bcVar.n();
        }
        bb bbVar = this.i;
        if (bbVar != null) {
            bbVar.p();
        }
        aq aqVar = this.j;
        if (aqVar != null) {
            aqVar.j();
        }
        at atVar = this.k;
        if (atVar != null) {
            atVar.f();
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

    /* compiled from: UALogEntry.java */
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

    /* compiled from: UALogEntry.java */
    /* loaded from: classes.dex */
    private static class a extends di<bp> {
        private a() {
        }

        /* synthetic */ a(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // u.aly.dg
        /* renamed from: a */
        public void b(cy cyVar, bp bpVar) throws cf {
            cyVar.j();
            while (true) {
                ct l = cyVar.l();
                if (l.b != 0) {
                    int i = 0;
                    switch (l.c) {
                        case 1:
                            if (l.b == 12) {
                                bpVar.a = new as();
                                bpVar.a.a(cyVar);
                                bpVar.a(true);
                                break;
                            } else {
                                db.a(cyVar, l.b);
                                break;
                            }
                        case 2:
                            if (l.b == 12) {
                                bpVar.b = new ar();
                                bpVar.b.a(cyVar);
                                bpVar.b(true);
                                break;
                            } else {
                                db.a(cyVar, l.b);
                                break;
                            }
                        case 3:
                            if (l.b == 12) {
                                bpVar.c = new au();
                                bpVar.c.a(cyVar);
                                bpVar.c(true);
                                break;
                            } else {
                                db.a(cyVar, l.b);
                                break;
                            }
                        case 4:
                            if (l.b == 12) {
                                bpVar.d = new bh();
                                bpVar.d.a(cyVar);
                                bpVar.d(true);
                                break;
                            } else {
                                db.a(cyVar, l.b);
                                break;
                            }
                        case 5:
                            if (l.b == 12) {
                                bpVar.e = new ap();
                                bpVar.e.a(cyVar);
                                bpVar.e(true);
                                break;
                            } else {
                                db.a(cyVar, l.b);
                                break;
                            }
                        case 6:
                            if (l.b == 15) {
                                cu p = cyVar.p();
                                bpVar.f = new ArrayList(p.b);
                                while (i < p.b) {
                                    be beVar = new be();
                                    beVar.a(cyVar);
                                    bpVar.f.add(beVar);
                                    i++;
                                }
                                cyVar.q();
                                bpVar.f(true);
                                break;
                            } else {
                                db.a(cyVar, l.b);
                                break;
                            }
                        case 7:
                            if (l.b == 15) {
                                cu p2 = cyVar.p();
                                bpVar.g = new ArrayList(p2.b);
                                while (i < p2.b) {
                                    bn bnVar = new bn();
                                    bnVar.a(cyVar);
                                    bpVar.g.add(bnVar);
                                    i++;
                                }
                                cyVar.q();
                                bpVar.g(true);
                                break;
                            } else {
                                db.a(cyVar, l.b);
                                break;
                            }
                        case 8:
                            if (l.b == 12) {
                                bpVar.h = new bc();
                                bpVar.h.a(cyVar);
                                bpVar.h(true);
                                break;
                            } else {
                                db.a(cyVar, l.b);
                                break;
                            }
                        case 9:
                            if (l.b == 12) {
                                bpVar.i = new bb();
                                bpVar.i.a(cyVar);
                                bpVar.i(true);
                                break;
                            } else {
                                db.a(cyVar, l.b);
                                break;
                            }
                        case 10:
                            if (l.b == 12) {
                                bpVar.j = new aq();
                                bpVar.j.a(cyVar);
                                bpVar.j(true);
                                break;
                            } else {
                                db.a(cyVar, l.b);
                                break;
                            }
                        case 11:
                            if (l.b == 12) {
                                bpVar.k = new at();
                                bpVar.k.a(cyVar);
                                bpVar.k(true);
                                break;
                            } else {
                                db.a(cyVar, l.b);
                                break;
                            }
                        case 12:
                            if (l.b == 13) {
                                cv n = cyVar.n();
                                bpVar.l = new HashMap(n.c * 2);
                                while (i < n.c) {
                                    bpVar.l.put(cyVar.z(), Integer.valueOf(cyVar.w()));
                                    i++;
                                }
                                cyVar.o();
                                bpVar.l(true);
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
                    bpVar.S();
                    return;
                }
            }
        }

        @Override // u.aly.dg
        /* renamed from: b */
        public void a(cy cyVar, bp bpVar) throws cf {
            bpVar.S();
            cyVar.a(bp.n);
            if (bpVar.a != null) {
                cyVar.a(bp.o);
                bpVar.a.b(cyVar);
                cyVar.c();
            }
            if (bpVar.b != null) {
                cyVar.a(bp.p);
                bpVar.b.b(cyVar);
                cyVar.c();
            }
            if (bpVar.c != null) {
                cyVar.a(bp.q);
                bpVar.c.b(cyVar);
                cyVar.c();
            }
            if (bpVar.d != null) {
                cyVar.a(bp.r);
                bpVar.d.b(cyVar);
                cyVar.c();
            }
            if (bpVar.e != null && bpVar.r()) {
                cyVar.a(bp.s);
                bpVar.e.b(cyVar);
                cyVar.c();
            }
            if (bpVar.f != null && bpVar.w()) {
                cyVar.a(bp.t);
                cyVar.a(new cu((byte) 12, bpVar.f.size()));
                Iterator<be> it = bpVar.f.iterator();
                while (it.hasNext()) {
                    it.next().b(cyVar);
                }
                cyVar.f();
                cyVar.c();
            }
            if (bpVar.g != null && bpVar.B()) {
                cyVar.a(bp.f70u);
                cyVar.a(new cu((byte) 12, bpVar.g.size()));
                Iterator<bn> it2 = bpVar.g.iterator();
                while (it2.hasNext()) {
                    it2.next().b(cyVar);
                }
                cyVar.f();
                cyVar.c();
            }
            if (bpVar.h != null && bpVar.E()) {
                cyVar.a(bp.v);
                bpVar.h.b(cyVar);
                cyVar.c();
            }
            if (bpVar.i != null && bpVar.H()) {
                cyVar.a(bp.w);
                bpVar.i.b(cyVar);
                cyVar.c();
            }
            if (bpVar.j != null && bpVar.K()) {
                cyVar.a(bp.x);
                bpVar.j.b(cyVar);
                cyVar.c();
            }
            if (bpVar.k != null && bpVar.N()) {
                cyVar.a(bp.y);
                bpVar.k.b(cyVar);
                cyVar.c();
            }
            if (bpVar.l != null && bpVar.R()) {
                cyVar.a(bp.z);
                cyVar.a(new cv((byte) 11, (byte) 8, bpVar.l.size()));
                for (Map.Entry<String, Integer> entry : bpVar.l.entrySet()) {
                    cyVar.a(entry.getKey());
                    cyVar.a(entry.getValue().intValue());
                }
                cyVar.e();
                cyVar.c();
            }
            cyVar.d();
            cyVar.b();
        }
    }

    /* compiled from: UALogEntry.java */
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

    /* compiled from: UALogEntry.java */
    /* loaded from: classes.dex */
    private static class c extends dj<bp> {
        private c() {
        }

        /* synthetic */ c(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // u.aly.dg
        public void a(cy cyVar, bp bpVar) throws cf {
            de deVar = (de) cyVar;
            bpVar.a.b(deVar);
            bpVar.b.b(deVar);
            bpVar.c.b(deVar);
            bpVar.d.b(deVar);
            BitSet bitSet = new BitSet();
            if (bpVar.r()) {
                bitSet.set(0);
            }
            if (bpVar.w()) {
                bitSet.set(1);
            }
            if (bpVar.B()) {
                bitSet.set(2);
            }
            if (bpVar.E()) {
                bitSet.set(3);
            }
            if (bpVar.H()) {
                bitSet.set(4);
            }
            if (bpVar.K()) {
                bitSet.set(5);
            }
            if (bpVar.N()) {
                bitSet.set(6);
            }
            if (bpVar.R()) {
                bitSet.set(7);
            }
            deVar.a(bitSet, 8);
            if (bpVar.r()) {
                bpVar.e.b(deVar);
            }
            if (bpVar.w()) {
                deVar.a(bpVar.f.size());
                Iterator<be> it = bpVar.f.iterator();
                while (it.hasNext()) {
                    it.next().b(deVar);
                }
            }
            if (bpVar.B()) {
                deVar.a(bpVar.g.size());
                Iterator<bn> it2 = bpVar.g.iterator();
                while (it2.hasNext()) {
                    it2.next().b(deVar);
                }
            }
            if (bpVar.E()) {
                bpVar.h.b(deVar);
            }
            if (bpVar.H()) {
                bpVar.i.b(deVar);
            }
            if (bpVar.K()) {
                bpVar.j.b(deVar);
            }
            if (bpVar.N()) {
                bpVar.k.b(deVar);
            }
            if (bpVar.R()) {
                deVar.a(bpVar.l.size());
                for (Map.Entry<String, Integer> entry : bpVar.l.entrySet()) {
                    deVar.a(entry.getKey());
                    deVar.a(entry.getValue().intValue());
                }
            }
        }

        @Override // u.aly.dg
        public void b(cy cyVar, bp bpVar) throws cf {
            de deVar = (de) cyVar;
            bpVar.a = new as();
            bpVar.a.a(deVar);
            bpVar.a(true);
            bpVar.b = new ar();
            bpVar.b.a(deVar);
            bpVar.b(true);
            bpVar.c = new au();
            bpVar.c.a(deVar);
            bpVar.c(true);
            bpVar.d = new bh();
            bpVar.d.a(deVar);
            bpVar.d(true);
            BitSet b = deVar.b(8);
            if (b.get(0)) {
                bpVar.e = new ap();
                bpVar.e.a(deVar);
                bpVar.e(true);
            }
            if (b.get(1)) {
                cu cuVar = new cu((byte) 12, deVar.w());
                bpVar.f = new ArrayList(cuVar.b);
                for (int i = 0; i < cuVar.b; i++) {
                    be beVar = new be();
                    beVar.a(deVar);
                    bpVar.f.add(beVar);
                }
                bpVar.f(true);
            }
            if (b.get(2)) {
                cu cuVar2 = new cu((byte) 12, deVar.w());
                bpVar.g = new ArrayList(cuVar2.b);
                for (int i2 = 0; i2 < cuVar2.b; i2++) {
                    bn bnVar = new bn();
                    bnVar.a(deVar);
                    bpVar.g.add(bnVar);
                }
                bpVar.g(true);
            }
            if (b.get(3)) {
                bpVar.h = new bc();
                bpVar.h.a(deVar);
                bpVar.h(true);
            }
            if (b.get(4)) {
                bpVar.i = new bb();
                bpVar.i.a(deVar);
                bpVar.i(true);
            }
            if (b.get(5)) {
                bpVar.j = new aq();
                bpVar.j.a(deVar);
                bpVar.j(true);
            }
            if (b.get(6)) {
                bpVar.k = new at();
                bpVar.k.a(deVar);
                bpVar.k(true);
            }
            if (b.get(7)) {
                cv cvVar = new cv((byte) 11, (byte) 8, deVar.w());
                bpVar.l = new HashMap(cvVar.c * 2);
                for (int i3 = 0; i3 < cvVar.c; i3++) {
                    bpVar.l.put(deVar.z(), Integer.valueOf(deVar.w()));
                }
                bpVar.l(true);
            }
        }
    }
}
