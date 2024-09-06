package u.aly;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.analytics.ReportPolicy;
import java.io.File;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

/* compiled from: ImprintHandler.java */
/* loaded from: classes.dex */
public class g {
    private static final String a = ".imprint";
    private static final byte[] b = "pbl0".getBytes();
    private static g f;
    private x c;
    private a d = new a();
    private bc e = null;
    private Context g;

    g(Context context) {
        this.g = context;
    }

    public static synchronized g a(Context context) {
        g gVar;
        synchronized (g.class) {
            if (f == null) {
                g gVar2 = new g(context);
                f = gVar2;
                gVar2.c();
            }
            gVar = f;
        }
        return gVar;
    }

    public void a(x xVar) {
        this.c = xVar;
    }

    public String a(bc bcVar) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry entry : new TreeMap(bcVar.d()).entrySet()) {
            sb.append((String) entry.getKey());
            if (((bd) entry.getValue()).e()) {
                sb.append(((bd) entry.getValue()).c());
            }
            sb.append(((bd) entry.getValue()).f());
            sb.append(((bd) entry.getValue()).j());
        }
        sb.append(bcVar.b);
        return bu.a(sb.toString()).toLowerCase(Locale.US);
    }

    private boolean c(bc bcVar) {
        if (!bcVar.k().equals(a(bcVar))) {
            return false;
        }
        for (bd bdVar : bcVar.d().values()) {
            byte[] a2 = com.umeng.analytics.b.a(bdVar.j());
            byte[] a3 = a(bdVar);
            for (int i = 0; i < 4; i++) {
                if (a2[i] != a3[i]) {
                    return false;
                }
            }
        }
        return true;
    }

    public byte[] a(bd bdVar) {
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(null);
        allocate.putLong(bdVar.f());
        byte[] array = allocate.array();
        byte[] bArr = b;
        byte[] bArr2 = new byte[4];
        for (int i = 0; i < 4; i++) {
            bArr2[i] = (byte) (array[i] ^ bArr[i]);
        }
        return bArr2;
    }

    public void b(bc bcVar) {
        bc a2;
        boolean z;
        if (bcVar != null && c(bcVar)) {
            synchronized (this) {
                bc bcVar2 = this.e;
                String str = null;
                String k = bcVar2 == null ? null : bcVar2.k();
                if (bcVar2 == null) {
                    a2 = d(bcVar);
                } else {
                    a2 = a(bcVar2, bcVar);
                }
                this.e = a2;
                if (a2 != null) {
                    str = a2.k();
                }
                z = !a(k, str);
            }
            bc bcVar3 = this.e;
            if (bcVar3 == null || !z) {
                return;
            }
            this.d.a(bcVar3);
            x xVar = this.c;
            if (xVar != null) {
                xVar.a(this.d);
            }
        }
    }

    private boolean a(String str, String str2) {
        if (str == null) {
            return str2 == null;
        }
        return str.equals(str2);
    }

    private bc a(bc bcVar, bc bcVar2) {
        if (bcVar2 == null) {
            return bcVar;
        }
        Map<String, bd> d = bcVar.d();
        for (Map.Entry<String, bd> entry : bcVar2.d().entrySet()) {
            if (entry.getValue().e()) {
                d.put(entry.getKey(), entry.getValue());
            } else {
                d.remove(entry.getKey());
            }
        }
        bcVar.a(bcVar2.h());
        bcVar.a(a(bcVar));
        return bcVar;
    }

    private bc d(bc bcVar) {
        Map<String, bd> d = bcVar.d();
        ArrayList arrayList = new ArrayList(d.size() / 2);
        for (Map.Entry<String, bd> entry : d.entrySet()) {
            if (!entry.getValue().e()) {
                arrayList.add(entry.getKey());
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            d.remove((String) it.next());
        }
        return bcVar;
    }

    public synchronized bc a() {
        return this.e;
    }

    public a b() {
        return this.d;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0031 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:19:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void c() {
        /*
            r4 = this;
            java.io.File r0 = new java.io.File
            android.content.Context r1 = r4.g
            java.io.File r1 = r1.getFilesDir()
            java.lang.String r2 = ".imprint"
            r0.<init>(r1, r2)
            boolean r0 = r0.exists()
            if (r0 != 0) goto L14
            return
        L14:
            r0 = 0
            android.content.Context r1 = r4.g     // Catch: java.lang.Throwable -> L22 java.lang.Exception -> L27
            java.io.FileInputStream r1 = r1.openFileInput(r2)     // Catch: java.lang.Throwable -> L22 java.lang.Exception -> L27
            byte[] r0 = u.aly.bu.b(r1)     // Catch: java.lang.Exception -> L20 java.lang.Throwable -> L4b
            goto L2c
        L20:
            r2 = move-exception
            goto L29
        L22:
            r1 = move-exception
            r3 = r1
            r1 = r0
            r0 = r3
            goto L4c
        L27:
            r2 = move-exception
            r1 = r0
        L29:
            r2.printStackTrace()     // Catch: java.lang.Throwable -> L4b
        L2c:
            u.aly.bu.c(r1)
            if (r0 == 0) goto L4a
            u.aly.bc r1 = new u.aly.bc     // Catch: java.lang.Exception -> L46
            r1.<init>()     // Catch: java.lang.Exception -> L46
            u.aly.cc r2 = new u.aly.cc     // Catch: java.lang.Exception -> L46
            r2.<init>()     // Catch: java.lang.Exception -> L46
            r2.a(r1, r0)     // Catch: java.lang.Exception -> L46
            r4.e = r1     // Catch: java.lang.Exception -> L46
            u.aly.g$a r0 = r4.d     // Catch: java.lang.Exception -> L46
            r0.a(r1)     // Catch: java.lang.Exception -> L46
            goto L4a
        L46:
            r0 = move-exception
            r0.printStackTrace()
        L4a:
            return
        L4b:
            r0 = move-exception
        L4c:
            u.aly.bu.c(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: u.aly.g.c():void");
    }

    public void d() {
        if (this.e == null) {
            return;
        }
        try {
            bu.a(new File(this.g.getFilesDir(), a), new ci().a(this.e));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean e() {
        return new File(this.g.getFilesDir(), a).delete();
    }

    /* compiled from: ImprintHandler.java */
    /* loaded from: classes.dex */
    public static class a {
        private int a = -1;
        private int b = -1;
        private int c = -1;
        private int d = -1;
        private int e = -1;
        private String f = null;
        private int g = -1;
        private String h = null;
        private int i = -1;
        private int j = -1;

        a() {
        }

        a(bc bcVar) {
            a(bcVar);
        }

        public void a(bc bcVar) {
            if (bcVar == null) {
                return;
            }
            this.a = a(bcVar, "defcon");
            this.b = a(bcVar, "latent");
            this.c = a(bcVar, "codex");
            this.d = a(bcVar, "report_policy");
            this.e = a(bcVar, "report_interval");
            this.f = b(bcVar, "client_test");
            this.g = a(bcVar, "test_report_interval");
            this.h = b(bcVar, "umid");
            this.i = a(bcVar, "integrated_test");
            this.j = a(bcVar, "latent_hours");
        }

        public int a(int i) {
            int i2 = this.a;
            return (i2 != -1 && i2 <= 3 && i2 >= 0) ? i2 : i;
        }

        public int b(int i) {
            int i2 = this.b;
            return (i2 != -1 && i2 >= 0 && i2 <= 1800) ? i2 * 1000 : i;
        }

        public int c(int i) {
            int i2 = this.c;
            return (i2 == 0 || i2 == 1 || i2 == -1) ? this.c : i;
        }

        public int[] a(int i, int i2) {
            int i3 = this.d;
            if (i3 == -1 || !ReportPolicy.a(i3)) {
                return new int[]{i, i2};
            }
            int i4 = this.e;
            if (i4 == -1 || i4 < 90 || i4 > 86400) {
                this.e = 90;
            }
            return new int[]{this.d, this.e * 1000};
        }

        public String a(String str) {
            String str2 = this.f;
            return (str2 == null || !al.a(str2)) ? str : this.f;
        }

        public int d(int i) {
            int i2 = this.g;
            return (i2 == -1 || i2 < 90 || i2 > 86400) ? i : i2 * 1000;
        }

        public boolean a() {
            return this.g != -1;
        }

        public String b(String str) {
            return this.h;
        }

        public boolean b() {
            return this.i == 1;
        }

        public long a(long j) {
            int i = this.j;
            return (i != -1 && i >= 48) ? i * com.umeng.analytics.a.i : j;
        }

        private int a(bc bcVar, String str) {
            bd bdVar;
            if (bcVar != null && bcVar.f() && (bdVar = bcVar.d().get(str)) != null && !TextUtils.isEmpty(bdVar.c())) {
                try {
                    return Integer.parseInt(bdVar.c().trim());
                } catch (Exception unused) {
                }
            }
            return -1;
        }

        private String b(bc bcVar, String str) {
            bd bdVar;
            if (bcVar == null || !bcVar.f() || (bdVar = bcVar.d().get(str)) == null || TextUtils.isEmpty(bdVar.c())) {
                return null;
            }
            return bdVar.c();
        }
    }
}
