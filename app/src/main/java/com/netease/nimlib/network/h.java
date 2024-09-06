package com.netease.nimlib.network;

import android.content.Context;
import com.netease.nimlib.biz.i;
import com.netease.nimlib.o.p;

/* compiled from: NetworkUIManager.java */
/* loaded from: classes.dex */
public class h {
    private e b;
    private d c;
    private com.netease.nimlib.network.a d;
    private boolean a = false;
    private volatile boolean e = false;
    private com.netease.nimlib.network.a.a f = com.netease.nimlib.network.a.a.NONE;
    private boolean g = true;
    private volatile boolean h = false;
    private com.netease.nimlib.abtest.a.a i = null;

    /* compiled from: NetworkUIManager.java */
    /* loaded from: classes.dex */
    private static class a {
        private static final h a = new h();
    }

    public static h a() {
        return a.a;
    }

    public void a(com.netease.nimlib.abtest.a.a aVar) {
        try {
            this.i = aVar;
            this.h = aVar != null && aVar.g();
            i.a().a(this.h);
            com.netease.nimlib.log.b.d("NetworkUIManager", "setAbRealReachability abRealReachability = " + aVar);
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("NetworkUIManager", "setAbRealReachability error", th);
        }
    }

    public void a(Context context) {
        try {
            if (this.a) {
                return;
            }
            this.a = true;
            this.e = p.c(context);
            this.c = new d();
            e eVar = new e(context);
            this.b = eVar;
            eVar.a();
            com.netease.nimlib.network.a aVar = new com.netease.nimlib.network.a() { // from class: com.netease.nimlib.network.h.1
                @Override // com.netease.nimlib.network.a
                public void onNetworkChanged(boolean z, com.netease.nimlib.network.a.a aVar2) {
                    com.netease.nimlib.log.b.d("NetworkUIManager", "onNetworkChanged isConnected = " + z + ",networkStatus = " + aVar2);
                    h.this.e = z;
                    h.this.f = aVar2;
                    if (z) {
                        if (h.this.c == null || !h.this.h || h.this.i == null) {
                            return;
                        }
                        com.netease.nimlib.log.b.d("NetworkUIManager", "onNetworkChanged startSchedule");
                        h.this.b();
                        return;
                    }
                    if (h.this.c == null || !h.this.c.a()) {
                        return;
                    }
                    com.netease.nimlib.log.b.d("NetworkUIManager", "onNetworkChanged stopSchedule");
                    h.this.c.b();
                }
            };
            this.d = aVar;
            this.b.a(aVar);
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("NetworkUIManager", "init error", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        try {
            if (this.i == null) {
                return;
            }
            this.c.a(this.i.d(), this.i.a(), this.i.e(), this.i.b(), this.i.c(), this.i.f(), new b() { // from class: com.netease.nimlib.network.-$$Lambda$h$rjKX6zmMos4rNVEq9_hNjJVrsX4
                @Override // com.netease.nimlib.network.b
                public final void onNetworkCheckResult(boolean z) {
                    h.this.b(z);
                }
            });
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("NetworkUIManager", "startScheduleNetworkCheck error", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(boolean z) {
        com.netease.nimlib.log.b.d("NetworkUIManager", "onNetworkCheckResult connected = " + z + ",isCurrentConnected = " + this.e);
        if (!this.e || z) {
            return;
        }
        this.e = false;
        i.a().b(this.e);
    }

    public void a(boolean z) {
        if (this.g == z) {
            return;
        }
        this.g = z;
        if (!z || this.c == null || !this.h || this.i == null) {
            return;
        }
        com.netease.nimlib.log.b.d("NetworkUIManager", "setForeground startSchedule");
        b();
    }

    public boolean b(Context context) {
        if (this.h) {
            return this.e;
        }
        return p.c(context);
    }

    public void a(com.netease.nimlib.network.a aVar) {
        e eVar = this.b;
        if (eVar != null) {
            eVar.a(aVar);
        }
    }
}
