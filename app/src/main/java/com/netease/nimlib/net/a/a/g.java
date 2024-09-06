package com.netease.nimlib.net.a.a;

import android.net.Uri;
import android.text.TextUtils;
import com.netease.nimlib.biz.d.d.k;
import com.netease.nimlib.biz.i;
import com.netease.nimlib.net.a.a.c;
import com.netease.nimlib.plugin.interact.IChatRoomInteract;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* compiled from: HttpDownloadManager.java */
/* loaded from: classes.dex */
public class g {
    private final Object a;
    private Map<String, List<e>> b;
    private Map<String, h> c;
    private com.netease.nimlib.c.b.b d;

    /* synthetic */ g(AnonymousClass1 anonymousClass1) {
        this();
    }

    /* compiled from: HttpDownloadManager.java */
    /* loaded from: classes.dex */
    private static final class b {
        private static final g a = new g();
    }

    public static g a() {
        return b.a;
    }

    private g() {
        this.a = new Object();
        c();
    }

    private void c() {
        this.b = new HashMap();
        this.c = new HashMap();
        this.d = new com.netease.nimlib.c.b.b("HttpDownloadManager", com.netease.nimlib.c.b.b.b, true);
    }

    public void a(e eVar) {
        a(true, eVar);
    }

    public void a(boolean z, e eVar) {
        h hVar;
        String f = eVar.f();
        synchronized (this.a) {
            List<e> list = this.b.get(f);
            if (list == null) {
                list = new LinkedList<>();
                this.b.put(f, list);
            }
            if (!list.contains(eVar)) {
                list.add(eVar);
            }
            hVar = this.c.get(f);
        }
        a aVar = new a(f, eVar.h(), eVar.i(), eVar.b(), eVar.c(), eVar.d(), eVar.e(), new com.netease.nimlib.net.a.a.b(f, eVar.h()), eVar.m());
        if (z) {
            if (hVar == null) {
                this.d.execute(aVar);
            }
        } else if (hVar == null) {
            aVar.a(eVar.n());
            aVar.run();
        } else {
            a(hVar);
        }
    }

    public void b(e eVar) {
        synchronized (this.a) {
            String f = eVar.f();
            List<e> list = this.b.get(f);
            if (list != null) {
                eVar.j();
                list.remove(eVar);
                if (list.size() == 0) {
                    f(f);
                }
            }
        }
    }

    public void b() {
        synchronized (this.a) {
            for (Object obj : this.b.keySet().toArray()) {
                List<e> list = this.b.get(obj);
                if (list != null) {
                    for (e eVar : list) {
                        eVar.j();
                        if (eVar.l() != null) {
                            eVar.l().onCancel(eVar);
                        }
                    }
                }
                f((String) obj);
            }
        }
    }

    private void f(String str) {
        this.b.remove(str);
        h hVar = this.c.get(str);
        if (hVar != null) {
            hVar.a.b();
            this.c.remove(str);
            b(hVar);
        }
    }

    List<e> a(String str) {
        List<e> list;
        synchronized (this.a) {
            list = this.b.get(str);
        }
        return list;
    }

    h b(String str) {
        h hVar;
        synchronized (this.a) {
            hVar = this.c.get(str);
        }
        return hVar;
    }

    public void a(String str, String str2, String str3, long j, boolean z, Map<String, String> map, int i, int i2, com.netease.nimlib.net.a.a.a aVar, com.netease.nimlib.n.b.g gVar) {
        synchronized (this.a) {
            if (this.b.containsKey(str2) && !this.c.containsKey(str2)) {
                h hVar = new h(c.a());
                this.c.put(str2, hVar);
                if (e(str2)) {
                    a(str, str2, j, aVar, str3, z, map, i, i2, hVar, gVar);
                } else {
                    b(str2, null, j, aVar, str3, z, map, i, i2, hVar, gVar);
                }
            }
        }
    }

    /* compiled from: HttpDownloadManager.java */
    /* renamed from: com.netease.nimlib.net.a.a.g$1 */
    /* loaded from: classes.dex */
    class AnonymousClass1 extends com.netease.nimlib.biz.g.b {
        final /* synthetic */ String a;
        final /* synthetic */ long b;
        final /* synthetic */ com.netease.nimlib.net.a.a.a c;
        final /* synthetic */ String d;
        final /* synthetic */ boolean e;
        final /* synthetic */ Map f;
        final /* synthetic */ int g;
        final /* synthetic */ int i;
        final /* synthetic */ h j;
        final /* synthetic */ com.netease.nimlib.n.b.g k;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(com.netease.nimlib.biz.d.a aVar, String str, long j, com.netease.nimlib.net.a.a.a aVar2, String str2, boolean z, Map map, int i, int i2, h hVar, com.netease.nimlib.n.b.g gVar) {
            super(aVar);
            r3 = str;
            r4 = j;
            r6 = aVar2;
            r7 = str2;
            r8 = z;
            r9 = map;
            r10 = i;
            r11 = i2;
            r12 = hVar;
            r13 = gVar;
        }

        @Override // com.netease.nimlib.biz.g.b, com.netease.nimlib.biz.g.c
        public void a(com.netease.nimlib.biz.e.a aVar) {
            if (aVar.n()) {
                String a = ((com.netease.nimlib.biz.e.d.h) aVar).a();
                if (!TextUtils.isEmpty(a)) {
                    g.this.b(a, r3, r4, r6, r7, r8, r9, r10, r11, r12, r13);
                    return;
                }
            }
            g.this.b(r3, null, r4, r6, r7, r8, r9, r10, r11, r12, r13);
        }
    }

    private void a(String str, String str2, long j, com.netease.nimlib.net.a.a.a aVar, String str3, boolean z, Map<String, String> map, int i, int i2, h hVar, com.netease.nimlib.n.b.g gVar) {
        AnonymousClass1 anonymousClass1 = new com.netease.nimlib.biz.g.b(new k(str2)) { // from class: com.netease.nimlib.net.a.a.g.1
            final /* synthetic */ String a;
            final /* synthetic */ long b;
            final /* synthetic */ com.netease.nimlib.net.a.a.a c;
            final /* synthetic */ String d;
            final /* synthetic */ boolean e;
            final /* synthetic */ Map f;
            final /* synthetic */ int g;
            final /* synthetic */ int i;
            final /* synthetic */ h j;
            final /* synthetic */ com.netease.nimlib.n.b.g k;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(com.netease.nimlib.biz.d.a aVar2, String str22, long j2, com.netease.nimlib.net.a.a.a aVar3, String str32, boolean z2, Map map2, int i3, int i22, h hVar2, com.netease.nimlib.n.b.g gVar2) {
                super(aVar2);
                r3 = str22;
                r4 = j2;
                r6 = aVar3;
                r7 = str32;
                r8 = z2;
                r9 = map2;
                r10 = i3;
                r11 = i22;
                r12 = hVar2;
                r13 = gVar2;
            }

            @Override // com.netease.nimlib.biz.g.b, com.netease.nimlib.biz.g.c
            public void a(com.netease.nimlib.biz.e.a aVar2) {
                if (aVar2.n()) {
                    String a2 = ((com.netease.nimlib.biz.e.d.h) aVar2).a();
                    if (!TextUtils.isEmpty(a2)) {
                        g.this.b(a2, r3, r4, r6, r7, r8, r9, r10, r11, r12, r13);
                        return;
                    }
                }
                g.this.b(r3, null, r4, r6, r7, r8, r9, r10, r11, r12, r13);
            }
        };
        if (g(str)) {
            ((IChatRoomInteract) com.netease.nimlib.plugin.interact.b.a().a(IChatRoomInteract.class)).addSendTask(anonymousClass1, str);
        } else {
            i.a().a(anonymousClass1);
        }
    }

    private boolean g(String str) {
        IChatRoomInteract iChatRoomInteract;
        if (TextUtils.isEmpty(str) || (iChatRoomInteract = (IChatRoomInteract) com.netease.nimlib.plugin.interact.b.a().a(IChatRoomInteract.class)) == null) {
            return false;
        }
        return iChatRoomInteract.independent(str);
    }

    public void b(String str, String str2, long j, com.netease.nimlib.net.a.a.a aVar, String str3, boolean z, Map<String, String> map, int i, int i2, h hVar, com.netease.nimlib.n.b.g gVar) {
        hVar.a.a(new c.a.C0045a(str, str3).a(str2).a(aVar).a(j).a(z).a(map).a(i).b(i2).a(gVar).a());
        if (!TextUtils.isEmpty(str2)) {
            str = str2;
        }
        synchronized (this.a) {
            if (this.c.get(str) == hVar) {
                this.c.remove(str);
                this.b.remove(str);
            }
        }
        b(hVar);
    }

    private void a(h hVar) {
        synchronized (hVar) {
            try {
                hVar.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void b(h hVar) {
        synchronized (hVar) {
            hVar.notifyAll();
        }
    }

    /* compiled from: HttpDownloadManager.java */
    /* loaded from: classes.dex */
    private class a implements Runnable {
        private String b;
        private String c;
        private long d;
        private com.netease.nimlib.net.a.a.a e;
        private String f;
        private final boolean g;
        private final Map<String, String> h;
        private final int i;
        private final int j;
        private final com.netease.nimlib.n.b.g k;

        public a(String str, String str2, long j, boolean z, Map<String, String> map, int i, int i2, com.netease.nimlib.net.a.a.a aVar, com.netease.nimlib.n.b.g gVar) {
            this.b = str;
            this.c = str2;
            this.d = j;
            this.e = aVar;
            this.g = z;
            this.h = map;
            this.i = i;
            this.j = i2;
            this.k = gVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                g.this.a(this.f, this.b, this.c, this.d, this.g, this.h, this.i, this.j, this.e, this.k);
            } catch (Throwable th) {
                com.netease.nimlib.log.b.d("RES", "Download exception: " + th);
            }
        }

        public void a(String str) {
            this.f = str;
        }
    }

    public static boolean c(String str) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Uri parse = Uri.parse(d(str));
        String str3 = null;
        try {
            str2 = parse.getQueryParameter("createTime");
            try {
                str3 = parse.getQueryParameter("survivalTime");
            } catch (Throwable th) {
                th = th;
                th.printStackTrace();
                return TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3) && (System.currentTimeMillis() / 1000) - Long.valueOf(str2).longValue() > Long.valueOf(str3).longValue();
            }
        } catch (Throwable th2) {
            th = th2;
            str2 = null;
        }
        return TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3) && (System.currentTimeMillis() / 1000) - Long.valueOf(str2).longValue() > Long.valueOf(str3).longValue();
    }

    public static String a(long j, boolean z) {
        if (j <= 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        if (z) {
            sb.append("?");
        } else {
            sb.append(com.alipay.sdk.m.o.a.l);
        }
        sb.append("createTime");
        sb.append("=");
        sb.append(System.currentTimeMillis() / 1000);
        sb.append(com.alipay.sdk.m.o.a.l);
        sb.append("survivalTime");
        sb.append("=");
        sb.append(j);
        return sb.toString();
    }

    public static String d(String str) {
        if (TextUtils.isEmpty(str) || str.startsWith("http://") || str.startsWith("https://")) {
            return str;
        }
        return "http://" + str;
    }

    public static boolean e(String str) {
        String str2;
        try {
            str2 = Uri.parse(str).getQueryParameter("_im_url");
        } catch (Throwable th) {
            th.printStackTrace();
            str2 = null;
        }
        return com.netease.nimlib.o.b.a(str2) == 1;
    }
}
