package com.netease.nimlib.net.a.a;

import android.os.Handler;
import com.netease.nimlib.o.y;
import java.util.HashSet;
import java.util.List;

/* compiled from: DownloadListenerWrapper.java */
/* loaded from: classes.dex */
class b implements a {
    private static final Handler a = com.netease.nimlib.c.b.a.b(com.netease.nimlib.c.e());
    private String b;
    private String c;
    private long d = 0;

    b(String str, String str2) {
        this.b = str;
        this.c = str2;
    }

    @Override // com.netease.nimlib.net.a.a.a
    public void a(String str) {
        c(str);
    }

    @Override // com.netease.nimlib.net.a.a.a
    public void b(String str) {
        d(str);
    }

    @Override // com.netease.nimlib.net.a.a.a
    public void a(String str, String str2) {
        d(str, str2);
    }

    @Override // com.netease.nimlib.net.a.a.a
    public void a(String str, long j) {
        d(str, j);
    }

    @Override // com.netease.nimlib.net.a.a.a
    public void b(String str, long j) {
        c(str, j);
    }

    @Override // com.netease.nimlib.net.a.a.a
    public void b(String str, String str2) {
        c(str, str2);
    }

    private void c(String str, final String str2) {
        final List<e> e = e(str);
        if (e == null) {
            return;
        }
        a(new Runnable() { // from class: com.netease.nimlib.net.a.a.b.1
            @Override // java.lang.Runnable
            public void run() {
                for (e eVar : e) {
                    if (!eVar.k() && eVar.l() != null) {
                        eVar.l().onExpire(eVar, str2);
                    }
                }
            }
        });
    }

    private void c(String str) {
        final List<e> e = e(str);
        if (e == null) {
            return;
        }
        a(new Runnable() { // from class: com.netease.nimlib.net.a.a.b.2
            @Override // java.lang.Runnable
            public void run() {
                for (e eVar : e) {
                    if (!eVar.k() && eVar.l() != null) {
                        eVar.l().onStart(eVar);
                    }
                }
            }
        });
    }

    private void c(String str, final long j) {
        final List<e> e = e(str);
        if (e == null) {
            return;
        }
        a(new Runnable() { // from class: com.netease.nimlib.net.a.a.b.3
            @Override // java.lang.Runnable
            public void run() {
                for (e eVar : e) {
                    if (!eVar.k() && eVar.l() != null) {
                        eVar.l().onGetLength(eVar, j);
                    }
                }
            }
        });
    }

    private void d(final String str, final long j) {
        final List<e> e;
        long a2 = y.a();
        if (a2 - this.d < 200 || (e = e(str)) == null) {
            return;
        }
        this.d = a2;
        a(new Runnable() { // from class: com.netease.nimlib.net.a.a.b.4
            @Override // java.lang.Runnable
            public void run() {
                for (e eVar : e) {
                    if (!eVar.k() && eVar.l() != null) {
                        eVar.l().onProgress(eVar, j);
                    }
                }
                h b = g.a().b(str);
                if (b != null) {
                    b.a(j);
                }
            }
        });
    }

    private void d(String str) {
        final List<e> e = e(str);
        if (e == null) {
            return;
        }
        a(str, e);
        a(new Runnable() { // from class: com.netease.nimlib.net.a.a.b.5
            @Override // java.lang.Runnable
            public void run() {
                for (e eVar : e) {
                    if (!eVar.k() && eVar.l() != null) {
                        eVar.l().onOK(eVar);
                    }
                }
            }
        });
    }

    private void d(String str, final String str2) {
        final List<e> e = e(str);
        if (e == null) {
            return;
        }
        a(new Runnable() { // from class: com.netease.nimlib.net.a.a.b.6
            @Override // java.lang.Runnable
            public void run() {
                for (e eVar : e) {
                    if (!eVar.k() && eVar.l() != null) {
                        eVar.l().onFail(eVar, str2);
                    }
                }
            }
        });
    }

    private List<e> e(String str) {
        return g.a().a(str);
    }

    private synchronized void a(String str, List<e> list) {
        HashSet<String> hashSet = new HashSet();
        int i = 0;
        for (int i2 = 0; i2 < list.size(); i2++) {
            hashSet.add(list.get(i2).g());
        }
        for (String str2 : hashSet) {
            i++;
            if (i == hashSet.size()) {
                com.netease.nimlib.net.a.c.a.b(this.c, str2);
            } else {
                com.netease.nimlib.net.a.c.a.a(this.c, str2);
            }
        }
    }

    private void a(final Runnable runnable) {
        a.post(new Runnable() { // from class: com.netease.nimlib.net.a.a.b.7
            @Override // java.lang.Runnable
            public void run() {
                try {
                    runnable.run();
                } catch (Throwable th) {
                    com.netease.nimlib.log.b.d("RES", "download listener exception: " + th.getMessage());
                }
            }
        });
    }
}
