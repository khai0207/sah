package u.aly;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import u.aly.cg;
import u.aly.cj;

/* compiled from: TUnion.java */
/* loaded from: classes.dex */
public abstract class cj<T extends cj<?, ?>, F extends cg> implements bz<T, F> {
    private static final Map<Class<? extends dg>, dh> a;
    protected Object b;
    protected F c;

    protected abstract Object a(cy cyVar, ct ctVar) throws cf;

    protected abstract Object a(cy cyVar, short s) throws cf;

    protected abstract ct a(F f);

    protected abstract void a(F f, Object obj) throws ClassCastException;

    protected abstract F b(short s);

    protected abstract dd c();

    protected abstract void c(cy cyVar) throws cf;

    protected abstract void d(cy cyVar) throws cf;

    protected cj() {
        this.c = null;
        this.b = null;
    }

    static {
        HashMap hashMap = new HashMap();
        a = hashMap;
        hashMap.put(di.class, new b());
        a.put(dj.class, new d());
    }

    protected cj(F f, Object obj) {
        b(f, obj);
    }

    protected cj(cj<T, F> cjVar) {
        if (!cjVar.getClass().equals(getClass())) {
            throw new ClassCastException();
        }
        this.c = cjVar.c;
        this.b = a(cjVar.b);
    }

    private static Object a(Object obj) {
        if (obj instanceof bz) {
            return ((bz) obj).g();
        }
        if (obj instanceof ByteBuffer) {
            return ca.d((ByteBuffer) obj);
        }
        if (obj instanceof List) {
            return a((List) obj);
        }
        if (obj instanceof Set) {
            return a((Set) obj);
        }
        return obj instanceof Map ? a((Map<Object, Object>) obj) : obj;
    }

    private static Map a(Map<Object, Object> map) {
        HashMap hashMap = new HashMap();
        for (Map.Entry<Object, Object> entry : map.entrySet()) {
            hashMap.put(a(entry.getKey()), a(entry.getValue()));
        }
        return hashMap;
    }

    private static Set a(Set set) {
        HashSet hashSet = new HashSet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            hashSet.add(a(it.next()));
        }
        return hashSet;
    }

    private static List a(List list) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(a(it.next()));
        }
        return arrayList;
    }

    public F i() {
        return this.c;
    }

    public Object j() {
        return this.b;
    }

    public Object b(F f) {
        if (f != this.c) {
            throw new IllegalArgumentException("Cannot get the value of field " + f + " because union's set field is " + this.c);
        }
        return j();
    }

    public Object c(int i) {
        return b((cj<T, F>) b((short) i));
    }

    public boolean k() {
        return this.c != null;
    }

    public boolean c(F f) {
        return this.c == f;
    }

    public boolean d(int i) {
        return c((cj<T, F>) b((short) i));
    }

    @Override // u.aly.bz
    public void a(cy cyVar) throws cf {
        a.get(cyVar.D()).b().b(cyVar, this);
    }

    public void b(F f, Object obj) {
        a((cj<T, F>) f, obj);
        this.c = f;
        this.b = obj;
    }

    public void a(int i, Object obj) {
        b(b((short) i), obj);
    }

    @Override // u.aly.bz
    public void b(cy cyVar) throws cf {
        a.get(cyVar.D()).b().a(cyVar, this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(getClass().getSimpleName());
        sb.append(" ");
        if (i() != null) {
            Object j = j();
            sb.append(a((cj<T, F>) i()).a);
            sb.append(":");
            if (j instanceof ByteBuffer) {
                ca.a((ByteBuffer) j, sb);
            } else {
                sb.append(j.toString());
            }
        }
        sb.append(">");
        return sb.toString();
    }

    @Override // u.aly.bz
    public final void b() {
        this.c = null;
        this.b = null;
    }

    /* compiled from: TUnion.java */
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

    /* compiled from: TUnion.java */
    /* loaded from: classes.dex */
    private static class a extends di<cj> {
        private a() {
        }

        @Override // u.aly.dg
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void b(cy cyVar, cj cjVar) throws cf {
            cjVar.c = null;
            cjVar.b = null;
            cyVar.j();
            ct l = cyVar.l();
            cjVar.b = cjVar.a(cyVar, l);
            if (cjVar.b != null) {
                cjVar.c = (F) cjVar.b(l.c);
            }
            cyVar.m();
            cyVar.l();
            cyVar.k();
        }

        @Override // u.aly.dg
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(cy cyVar, cj cjVar) throws cf {
            if (cjVar.i() == null || cjVar.j() == null) {
                throw new cz("Cannot write a TUnion with no set value!");
            }
            cyVar.a(cjVar.c());
            cyVar.a(cjVar.a((cj) cjVar.c));
            cjVar.c(cyVar);
            cyVar.c();
            cyVar.d();
            cyVar.b();
        }
    }

    /* compiled from: TUnion.java */
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

    /* compiled from: TUnion.java */
    /* loaded from: classes.dex */
    private static class c extends dj<cj> {
        private c() {
        }

        @Override // u.aly.dg
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void b(cy cyVar, cj cjVar) throws cf {
            cjVar.c = null;
            cjVar.b = null;
            short v = cyVar.v();
            cjVar.b = cjVar.a(cyVar, v);
            if (cjVar.b != null) {
                cjVar.c = (F) cjVar.b(v);
            }
        }

        @Override // u.aly.dg
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(cy cyVar, cj cjVar) throws cf {
            if (cjVar.i() == null || cjVar.j() == null) {
                throw new cz("Cannot write a TUnion with no set value!");
            }
            cyVar.a(cjVar.c.a());
            cjVar.d(cyVar);
        }
    }
}
