package u.aly;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* compiled from: PropertyValue.java */
/* loaded from: classes.dex */
public class bj extends cj<bj, a> {
    public static final Map<a, cl> a;
    private static final dd d = new dd("PropertyValue");
    private static final ct e = new ct("string_value", (byte) 11, 1);
    private static final ct f = new ct("long_value", (byte) 10, 2);

    public int hashCode() {
        return 0;
    }

    static {
        EnumMap enumMap = new EnumMap(a.class);
        enumMap.put((EnumMap) a.STRING_VALUE, (a) new cl("string_value", (byte) 3, new cm((byte) 11)));
        enumMap.put((EnumMap) a.LONG_VALUE, (a) new cl("long_value", (byte) 3, new cm((byte) 10)));
        Map<a, cl> unmodifiableMap = Collections.unmodifiableMap(enumMap);
        a = unmodifiableMap;
        cl.a(bj.class, unmodifiableMap);
    }

    /* compiled from: PropertyValue.java */
    /* loaded from: classes.dex */
    public enum a implements cg {
        STRING_VALUE(1, "string_value"),
        LONG_VALUE(2, "long_value");

        private static final Map<String, a> c = new HashMap();
        private final short d;
        private final String e;

        static {
            Iterator it = EnumSet.allOf(a.class).iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                c.put(aVar.b(), aVar);
            }
        }

        public static a a(int i) {
            if (i == 1) {
                return STRING_VALUE;
            }
            if (i != 2) {
                return null;
            }
            return LONG_VALUE;
        }

        public static a b(int i) {
            a a = a(i);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException("Field " + i + " doesn't exist!");
        }

        public static a a(String str) {
            return c.get(str);
        }

        a(short s, String str) {
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

    public bj() {
    }

    public bj(a aVar, Object obj) {
        super(aVar, obj);
    }

    public bj(bj bjVar) {
        super(bjVar);
    }

    @Override // u.aly.bz
    /* renamed from: a */
    public bj g() {
        return new bj(this);
    }

    public static bj a(String str) {
        bj bjVar = new bj();
        bjVar.b(str);
        return bjVar;
    }

    public static bj a(long j) {
        bj bjVar = new bj();
        bjVar.b(j);
        return bjVar;
    }

    /* compiled from: PropertyValue.java */
    /* renamed from: u.aly.bj$1 */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[a.values().length];
            a = iArr;
            try {
                iArr[a.STRING_VALUE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[a.LONG_VALUE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    @Override // u.aly.cj
    public void a(a aVar, Object obj) throws ClassCastException {
        int i = AnonymousClass1.a[aVar.ordinal()];
        if (i == 1) {
            if (obj instanceof String) {
                return;
            }
            throw new ClassCastException("Was expecting value of type String for field 'string_value', but got " + obj.getClass().getSimpleName());
        }
        if (i == 2) {
            if (obj instanceof Long) {
                return;
            }
            throw new ClassCastException("Was expecting value of type Long for field 'long_value', but got " + obj.getClass().getSimpleName());
        }
        throw new IllegalArgumentException("Unknown field id " + aVar);
    }

    @Override // u.aly.cj
    protected Object a(cy cyVar, ct ctVar) throws cf {
        a a2 = a.a(ctVar.c);
        if (a2 != null) {
            int i = AnonymousClass1.a[a2.ordinal()];
            if (i != 1) {
                if (i == 2) {
                    if (ctVar.b == f.b) {
                        return Long.valueOf(cyVar.x());
                    }
                    db.a(cyVar, ctVar.b);
                    return null;
                }
                throw new IllegalStateException("setField wasn't null, but didn't match any of the case statements!");
            }
            if (ctVar.b == e.b) {
                return cyVar.z();
            }
            db.a(cyVar, ctVar.b);
        }
        return null;
    }

    @Override // u.aly.cj
    protected void c(cy cyVar) throws cf {
        int i = AnonymousClass1.a[((a) this.c).ordinal()];
        if (i == 1) {
            cyVar.a((String) this.b);
        } else {
            if (i == 2) {
                cyVar.a(((Long) this.b).longValue());
                return;
            }
            throw new IllegalStateException("Cannot write union with unknown field " + this.c);
        }
    }

    @Override // u.aly.cj
    protected Object a(cy cyVar, short s) throws cf {
        a a2 = a.a(s);
        if (a2 != null) {
            int i = AnonymousClass1.a[a2.ordinal()];
            if (i == 1) {
                return cyVar.z();
            }
            if (i == 2) {
                return Long.valueOf(cyVar.x());
            }
            throw new IllegalStateException("setField wasn't null, but didn't match any of the case statements!");
        }
        throw new cz("Couldn't find a field with field id " + ((int) s));
    }

    @Override // u.aly.cj
    protected void d(cy cyVar) throws cf {
        int i = AnonymousClass1.a[((a) this.c).ordinal()];
        if (i == 1) {
            cyVar.a((String) this.b);
        } else {
            if (i == 2) {
                cyVar.a(((Long) this.b).longValue());
                return;
            }
            throw new IllegalStateException("Cannot write union with unknown field " + this.c);
        }
    }

    @Override // u.aly.cj
    public ct a(a aVar) {
        int i = AnonymousClass1.a[aVar.ordinal()];
        if (i == 1) {
            return e;
        }
        if (i == 2) {
            return f;
        }
        throw new IllegalArgumentException("Unknown field id " + aVar);
    }

    @Override // u.aly.cj
    protected dd c() {
        return d;
    }

    @Override // u.aly.cj
    /* renamed from: a */
    public a b(short s) {
        return a.b(s);
    }

    @Override // u.aly.bz
    /* renamed from: a */
    public a b(int i) {
        return a.a(i);
    }

    public String d() {
        if (i() == a.STRING_VALUE) {
            return (String) j();
        }
        throw new RuntimeException("Cannot get field 'string_value' because union is currently set to " + a(i()).a);
    }

    public void b(String str) {
        if (str == null) {
            throw null;
        }
        this.c = a.STRING_VALUE;
        this.b = str;
    }

    public long e() {
        if (i() == a.LONG_VALUE) {
            return ((Long) j()).longValue();
        }
        throw new RuntimeException("Cannot get field 'long_value' because union is currently set to " + a(i()).a);
    }

    public void b(long j) {
        this.c = a.LONG_VALUE;
        this.b = Long.valueOf(j);
    }

    public boolean f() {
        return this.c == a.STRING_VALUE;
    }

    public boolean h() {
        return this.c == a.LONG_VALUE;
    }

    public boolean equals(Object obj) {
        if (obj instanceof bj) {
            return a((bj) obj);
        }
        return false;
    }

    public boolean a(bj bjVar) {
        return bjVar != null && i() == bjVar.i() && j().equals(bjVar.j());
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
            a((cy) new cs(new dk(objectInputStream)));
        } catch (cf e2) {
            throw new IOException(e2.getMessage());
        }
    }
}
