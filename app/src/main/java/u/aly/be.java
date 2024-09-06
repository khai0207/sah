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

/* compiled from: InstantMsg.java */
/* loaded from: classes.dex */
public class be implements Serializable, Cloneable, bz<be, e> {
    public static final Map<e, cl> e;
    private static final dd f = new dd("InstantMsg");
    private static final ct g = new ct("id", (byte) 11, 1);
    private static final ct h = new ct("errors", df.m, 2);
    private static final ct i = new ct("events", df.m, 3);
    private static final ct j = new ct("game_events", df.m, 4);
    private static final Map<Class<? extends dg>, dh> k;
    public String a;
    public List<av> b;
    public List<ax> c;
    public List<ax> d;
    private e[] l;

    static {
        HashMap hashMap = new HashMap();
        k = hashMap;
        hashMap.put(di.class, new b());
        k.put(dj.class, new d());
        EnumMap enumMap = new EnumMap(e.class);
        enumMap.put((EnumMap) e.ID, (e) new cl("id", (byte) 1, new cm((byte) 11)));
        enumMap.put((EnumMap) e.ERRORS, (e) new cl("errors", (byte) 2, new cn(df.m, new cq((byte) 12, av.class))));
        enumMap.put((EnumMap) e.EVENTS, (e) new cl("events", (byte) 2, new cn(df.m, new cq((byte) 12, ax.class))));
        enumMap.put((EnumMap) e.GAME_EVENTS, (e) new cl("game_events", (byte) 2, new cn(df.m, new cq((byte) 12, ax.class))));
        Map<e, cl> unmodifiableMap = Collections.unmodifiableMap(enumMap);
        e = unmodifiableMap;
        cl.a(be.class, unmodifiableMap);
    }

    /* compiled from: InstantMsg.java */
    /* loaded from: classes.dex */
    public enum e implements cg {
        ID(1, "id"),
        ERRORS(2, "errors"),
        EVENTS(3, "events"),
        GAME_EVENTS(4, "game_events");

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
                return ID;
            }
            if (i == 2) {
                return ERRORS;
            }
            if (i == 3) {
                return EVENTS;
            }
            if (i != 4) {
                return null;
            }
            return GAME_EVENTS;
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

    public be() {
        this.l = new e[]{e.ERRORS, e.EVENTS, e.GAME_EVENTS};
    }

    public be(String str) {
        this();
        this.a = str;
    }

    public be(be beVar) {
        this.l = new e[]{e.ERRORS, e.EVENTS, e.GAME_EVENTS};
        if (beVar.e()) {
            this.a = beVar.a;
        }
        if (beVar.k()) {
            ArrayList arrayList = new ArrayList();
            Iterator<av> it = beVar.b.iterator();
            while (it.hasNext()) {
                arrayList.add(new av(it.next()));
            }
            this.b = arrayList;
        }
        if (beVar.p()) {
            ArrayList arrayList2 = new ArrayList();
            Iterator<ax> it2 = beVar.c.iterator();
            while (it2.hasNext()) {
                arrayList2.add(new ax(it2.next()));
            }
            this.c = arrayList2;
        }
        if (beVar.u()) {
            ArrayList arrayList3 = new ArrayList();
            Iterator<ax> it3 = beVar.d.iterator();
            while (it3.hasNext()) {
                arrayList3.add(new ax(it3.next()));
            }
            this.d = arrayList3;
        }
    }

    @Override // u.aly.bz
    /* renamed from: a */
    public be g() {
        return new be(this);
    }

    @Override // u.aly.bz
    public void b() {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
    }

    public String c() {
        return this.a;
    }

    public be a(String str) {
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

    public int f() {
        List<av> list = this.b;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public Iterator<av> h() {
        List<av> list = this.b;
        if (list == null) {
            return null;
        }
        return list.iterator();
    }

    public void a(av avVar) {
        if (this.b == null) {
            this.b = new ArrayList();
        }
        this.b.add(avVar);
    }

    public List<av> i() {
        return this.b;
    }

    public be a(List<av> list) {
        this.b = list;
        return this;
    }

    public void j() {
        this.b = null;
    }

    public boolean k() {
        return this.b != null;
    }

    public void b(boolean z) {
        if (z) {
            return;
        }
        this.b = null;
    }

    public int l() {
        List<ax> list = this.c;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public Iterator<ax> m() {
        List<ax> list = this.c;
        if (list == null) {
            return null;
        }
        return list.iterator();
    }

    public void a(ax axVar) {
        if (this.c == null) {
            this.c = new ArrayList();
        }
        this.c.add(axVar);
    }

    public List<ax> n() {
        return this.c;
    }

    public be b(List<ax> list) {
        this.c = list;
        return this;
    }

    public void o() {
        this.c = null;
    }

    public boolean p() {
        return this.c != null;
    }

    public void c(boolean z) {
        if (z) {
            return;
        }
        this.c = null;
    }

    public int q() {
        List<ax> list = this.d;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public Iterator<ax> r() {
        List<ax> list = this.d;
        if (list == null) {
            return null;
        }
        return list.iterator();
    }

    public void b(ax axVar) {
        if (this.d == null) {
            this.d = new ArrayList();
        }
        this.d.add(axVar);
    }

    public List<ax> s() {
        return this.d;
    }

    public be c(List<ax> list) {
        this.d = list;
        return this;
    }

    public void t() {
        this.d = null;
    }

    public boolean u() {
        return this.d != null;
    }

    public void d(boolean z) {
        if (z) {
            return;
        }
        this.d = null;
    }

    @Override // u.aly.bz
    /* renamed from: a */
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
        StringBuilder sb = new StringBuilder("InstantMsg(");
        sb.append("id:");
        String str = this.a;
        if (str == null) {
            sb.append(Constants.NULL_VERSION_ID);
        } else {
            sb.append(str);
        }
        if (k()) {
            sb.append(", ");
            sb.append("errors:");
            List<av> list = this.b;
            if (list == null) {
                sb.append(Constants.NULL_VERSION_ID);
            } else {
                sb.append(list);
            }
        }
        if (p()) {
            sb.append(", ");
            sb.append("events:");
            List<ax> list2 = this.c;
            if (list2 == null) {
                sb.append(Constants.NULL_VERSION_ID);
            } else {
                sb.append(list2);
            }
        }
        if (u()) {
            sb.append(", ");
            sb.append("game_events:");
            List<ax> list3 = this.d;
            if (list3 == null) {
                sb.append(Constants.NULL_VERSION_ID);
            } else {
                sb.append(list3);
            }
        }
        sb.append(")");
        return sb.toString();
    }

    public void v() throws cf {
        if (this.a != null) {
            return;
        }
        throw new cz("Required field 'id' was not present! Struct: " + toString());
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

    /* compiled from: InstantMsg.java */
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

    /* compiled from: InstantMsg.java */
    /* loaded from: classes.dex */
    private static class a extends di<be> {
        private a() {
        }

        /* synthetic */ a(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // u.aly.dg
        /* renamed from: a */
        public void b(cy cyVar, be beVar) throws cf {
            cyVar.j();
            while (true) {
                ct l = cyVar.l();
                if (l.b != 0) {
                    short s = l.c;
                    if (s != 1) {
                        int i = 0;
                        if (s != 2) {
                            if (s != 3) {
                                if (s == 4) {
                                    if (l.b == 15) {
                                        cu p = cyVar.p();
                                        beVar.d = new ArrayList(p.b);
                                        while (i < p.b) {
                                            ax axVar = new ax();
                                            axVar.a(cyVar);
                                            beVar.d.add(axVar);
                                            i++;
                                        }
                                        cyVar.q();
                                        beVar.d(true);
                                    } else {
                                        db.a(cyVar, l.b);
                                    }
                                } else {
                                    db.a(cyVar, l.b);
                                }
                            } else if (l.b == 15) {
                                cu p2 = cyVar.p();
                                beVar.c = new ArrayList(p2.b);
                                while (i < p2.b) {
                                    ax axVar2 = new ax();
                                    axVar2.a(cyVar);
                                    beVar.c.add(axVar2);
                                    i++;
                                }
                                cyVar.q();
                                beVar.c(true);
                            } else {
                                db.a(cyVar, l.b);
                            }
                        } else if (l.b == 15) {
                            cu p3 = cyVar.p();
                            beVar.b = new ArrayList(p3.b);
                            while (i < p3.b) {
                                av avVar = new av();
                                avVar.a(cyVar);
                                beVar.b.add(avVar);
                                i++;
                            }
                            cyVar.q();
                            beVar.b(true);
                        } else {
                            db.a(cyVar, l.b);
                        }
                    } else if (l.b == 11) {
                        beVar.a = cyVar.z();
                        beVar.a(true);
                    } else {
                        db.a(cyVar, l.b);
                    }
                    cyVar.m();
                } else {
                    cyVar.k();
                    beVar.v();
                    return;
                }
            }
        }

        @Override // u.aly.dg
        /* renamed from: b */
        public void a(cy cyVar, be beVar) throws cf {
            beVar.v();
            cyVar.a(be.f);
            if (beVar.a != null) {
                cyVar.a(be.g);
                cyVar.a(beVar.a);
                cyVar.c();
            }
            if (beVar.b != null && beVar.k()) {
                cyVar.a(be.h);
                cyVar.a(new cu((byte) 12, beVar.b.size()));
                Iterator<av> it = beVar.b.iterator();
                while (it.hasNext()) {
                    it.next().b(cyVar);
                }
                cyVar.f();
                cyVar.c();
            }
            if (beVar.c != null && beVar.p()) {
                cyVar.a(be.i);
                cyVar.a(new cu((byte) 12, beVar.c.size()));
                Iterator<ax> it2 = beVar.c.iterator();
                while (it2.hasNext()) {
                    it2.next().b(cyVar);
                }
                cyVar.f();
                cyVar.c();
            }
            if (beVar.d != null && beVar.u()) {
                cyVar.a(be.j);
                cyVar.a(new cu((byte) 12, beVar.d.size()));
                Iterator<ax> it3 = beVar.d.iterator();
                while (it3.hasNext()) {
                    it3.next().b(cyVar);
                }
                cyVar.f();
                cyVar.c();
            }
            cyVar.d();
            cyVar.b();
        }
    }

    /* compiled from: InstantMsg.java */
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

    /* compiled from: InstantMsg.java */
    /* loaded from: classes.dex */
    private static class c extends dj<be> {
        private c() {
        }

        /* synthetic */ c(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // u.aly.dg
        public void a(cy cyVar, be beVar) throws cf {
            de deVar = (de) cyVar;
            deVar.a(beVar.a);
            BitSet bitSet = new BitSet();
            if (beVar.k()) {
                bitSet.set(0);
            }
            if (beVar.p()) {
                bitSet.set(1);
            }
            if (beVar.u()) {
                bitSet.set(2);
            }
            deVar.a(bitSet, 3);
            if (beVar.k()) {
                deVar.a(beVar.b.size());
                Iterator<av> it = beVar.b.iterator();
                while (it.hasNext()) {
                    it.next().b(deVar);
                }
            }
            if (beVar.p()) {
                deVar.a(beVar.c.size());
                Iterator<ax> it2 = beVar.c.iterator();
                while (it2.hasNext()) {
                    it2.next().b(deVar);
                }
            }
            if (beVar.u()) {
                deVar.a(beVar.d.size());
                Iterator<ax> it3 = beVar.d.iterator();
                while (it3.hasNext()) {
                    it3.next().b(deVar);
                }
            }
        }

        @Override // u.aly.dg
        public void b(cy cyVar, be beVar) throws cf {
            de deVar = (de) cyVar;
            beVar.a = deVar.z();
            beVar.a(true);
            BitSet b = deVar.b(3);
            if (b.get(0)) {
                cu cuVar = new cu((byte) 12, deVar.w());
                beVar.b = new ArrayList(cuVar.b);
                for (int i = 0; i < cuVar.b; i++) {
                    av avVar = new av();
                    avVar.a(deVar);
                    beVar.b.add(avVar);
                }
                beVar.b(true);
            }
            if (b.get(1)) {
                cu cuVar2 = new cu((byte) 12, deVar.w());
                beVar.c = new ArrayList(cuVar2.b);
                for (int i2 = 0; i2 < cuVar2.b; i2++) {
                    ax axVar = new ax();
                    axVar.a(deVar);
                    beVar.c.add(axVar);
                }
                beVar.c(true);
            }
            if (b.get(2)) {
                cu cuVar3 = new cu((byte) 12, deVar.w());
                beVar.d = new ArrayList(cuVar3.b);
                for (int i3 = 0; i3 < cuVar3.b; i3++) {
                    ax axVar2 = new ax();
                    axVar2.a(deVar);
                    beVar.d.add(axVar2);
                }
                beVar.d(true);
            }
        }
    }
}
