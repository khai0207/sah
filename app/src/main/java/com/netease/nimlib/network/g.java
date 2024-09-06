package com.netease.nimlib.network;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.netease.nimlib.o.p;

/* compiled from: NetworkPushManager.java */
/* loaded from: classes.dex */
public class g {
    private boolean a;
    private e b;
    private com.netease.nimlib.network.a c;
    private boolean d;
    private boolean e;

    /* compiled from: NetworkPushManager.java */
    /* loaded from: classes.dex */
    private static class a {
        private static final g a = new g();
    }

    private g() {
        this.a = false;
        this.d = false;
        this.e = false;
    }

    public static g a() {
        return a.a;
    }

    public void a(Context context) {
        if (this.a) {
            return;
        }
        this.a = true;
        this.d = p.c(context);
        this.e = c(context);
        e eVar = new e(context);
        this.b = eVar;
        eVar.a();
        com.netease.nimlib.network.a aVar = new com.netease.nimlib.network.a() { // from class: com.netease.nimlib.network.-$$Lambda$g$_UapI5BUZ5fAI8nOqTchMJQmbXI
            @Override // com.netease.nimlib.network.a
            public final void onNetworkChanged(boolean z, com.netease.nimlib.network.a.a aVar2) {
                g.this.a(z, aVar2);
            }
        };
        this.c = aVar;
        this.b.a(aVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(boolean z, com.netease.nimlib.network.a.a aVar) {
        com.netease.nimlib.log.b.d("NetworkPushManager", "onNetworkChanged isConnected = " + z + ",networkStatus = " + aVar);
        this.d = z;
        if (z) {
            com.netease.nimlib.push.net.lbs.c.a().h();
        }
    }

    public void a(boolean z) {
        this.e = z;
    }

    public void b(boolean z) {
        this.d = z;
    }

    public boolean b(Context context) {
        if (!this.e) {
            return p.c(context);
        }
        return this.d;
    }

    private boolean c(Context context) {
        boolean z = false;
        if (context == null) {
            return false;
        }
        try {
            Cursor query = context.getContentResolver().query(Uri.parse(String.format("content://%s/integer/%s/%s", context.getPackageName() + ".ipc.provider.preference", "PARAMS", "KEY_AB_REAL_REACHABILITY")), null, null, null, null);
            if (query != null && query.moveToFirst()) {
                int i = query.getInt(0);
                query.close();
                if (i != 0) {
                    z = true;
                }
            }
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("NetworkPushManager", "isABRealReachabilityOpen exception", th);
        }
        com.netease.nimlib.log.b.d("NetworkPushManager", "isABRealReachabilityOpen result = " + z);
        return z;
    }
}
