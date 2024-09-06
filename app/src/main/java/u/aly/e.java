package u.aly;

import android.content.Context;
import android.text.TextUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* compiled from: IdTracker.java */
/* loaded from: classes.dex */
public class e {
    public static e a;
    private File c;
    private long e;
    private a h;
    private final String b = "umeng_it.cache";
    private bb d = null;
    private Set<u.aly.a> g = new HashSet();
    private long f = com.umeng.analytics.a.h;

    public String c() {
        return null;
    }

    e(Context context) {
        this.h = null;
        this.c = new File(context.getFilesDir(), "umeng_it.cache");
        a aVar = new a(context);
        this.h = aVar;
        aVar.b();
    }

    public static synchronized e a(Context context) {
        e eVar;
        synchronized (e.class) {
            if (a == null) {
                e eVar2 = new e(context);
                a = eVar2;
                eVar2.a(new f(context));
                a.a(new h(context));
                a.a(new b(context));
                a.a(new k(context));
                a.a(new j(context));
                a.a(new d(context));
                a.a(new i());
                a.e();
            }
            eVar = a;
        }
        return eVar;
    }

    public boolean a(u.aly.a aVar) {
        if (this.h.a(aVar.b())) {
            return this.g.add(aVar);
        }
        return false;
    }

    public void a(long j) {
        this.f = j;
    }

    public void a() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.e >= this.f) {
            boolean z = false;
            for (u.aly.a aVar : this.g) {
                if (aVar.c() && aVar.a()) {
                    z = true;
                    if (!aVar.c()) {
                        this.h.b(aVar.b());
                    }
                }
            }
            if (z) {
                g();
                this.h.a();
                f();
            }
            this.e = currentTimeMillis;
        }
    }

    public bb b() {
        return this.d;
    }

    private void g() {
        bb bbVar = new bb();
        HashMap hashMap = new HashMap();
        ArrayList arrayList = new ArrayList();
        for (u.aly.a aVar : this.g) {
            if (aVar.c()) {
                if (aVar.d() != null) {
                    hashMap.put(aVar.b(), aVar.d());
                }
                if (aVar.e() != null && !aVar.e().isEmpty()) {
                    arrayList.addAll(aVar.e());
                }
            }
        }
        bbVar.a(arrayList);
        bbVar.a(hashMap);
        synchronized (this) {
            this.d = bbVar;
        }
    }

    public void d() {
        boolean z = false;
        for (u.aly.a aVar : this.g) {
            if (aVar.c() && aVar.e() != null && !aVar.e().isEmpty()) {
                aVar.a((List<az>) null);
                z = true;
            }
        }
        if (z) {
            this.d.b(false);
            f();
        }
    }

    public void e() {
        bb h = h();
        if (h == null) {
            return;
        }
        ArrayList arrayList = new ArrayList(this.g.size());
        synchronized (this) {
            this.d = h;
            for (u.aly.a aVar : this.g) {
                aVar.a(this.d);
                if (!aVar.c()) {
                    arrayList.add(aVar);
                }
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                this.g.remove((u.aly.a) it.next());
            }
        }
        g();
    }

    public void f() {
        bb bbVar = this.d;
        if (bbVar != null) {
            a(bbVar);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r0v4 */
    private bb h() {
        FileInputStream fileInputStream;
        ?? exists = this.c.exists();
        try {
            if (exists == 0) {
                return null;
            }
            try {
                fileInputStream = new FileInputStream(this.c);
            } catch (Exception e) {
                e = e;
                fileInputStream = null;
            } catch (Throwable th) {
                th = th;
                exists = 0;
                bu.c((InputStream) exists);
                throw th;
            }
            try {
                byte[] b = bu.b(fileInputStream);
                bb bbVar = new bb();
                new cc().a(bbVar, b);
                bu.c(fileInputStream);
                return bbVar;
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                bu.c(fileInputStream);
                return null;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private void a(bb bbVar) {
        byte[] a2;
        if (bbVar != null) {
            try {
                synchronized (this) {
                    a2 = new ci().a(bbVar);
                }
                if (a2 != null) {
                    bu.a(this.c, a2);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* compiled from: IdTracker.java */
    /* loaded from: classes.dex */
    public static class a {
        private Context a;
        private Set<String> b = new HashSet();

        public a(Context context) {
            this.a = context;
        }

        public boolean a(String str) {
            return !this.b.contains(str);
        }

        public void b(String str) {
            this.b.add(str);
        }

        public void c(String str) {
            this.b.remove(str);
        }

        public void a() {
            if (this.b.isEmpty()) {
                return;
            }
            StringBuilder sb = new StringBuilder();
            Iterator<String> it = this.b.iterator();
            while (it.hasNext()) {
                sb.append(it.next());
                sb.append(',');
            }
            sb.deleteCharAt(sb.length() - 1);
            y.a(this.a).edit().putString("invld_id", sb.toString()).commit();
        }

        public void b() {
            String[] split;
            String string = y.a(this.a).getString("invld_id", null);
            if (TextUtils.isEmpty(string) || (split = string.split(",")) == null) {
                return;
            }
            for (String str : split) {
                if (!TextUtils.isEmpty(str)) {
                    this.b.add(str);
                }
            }
        }
    }
}
