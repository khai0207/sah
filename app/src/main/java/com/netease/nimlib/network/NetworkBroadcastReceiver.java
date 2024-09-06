package com.netease.nimlib.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.netease.nimlib.o.p;

/* loaded from: classes.dex */
public class NetworkBroadcastReceiver extends BroadcastReceiver {
    private final String a = "NetworkBroadcastReceiver";
    private a b;

    /* loaded from: classes.dex */
    public interface a {
        void onNetworkBroadcastCallback(boolean z, com.netease.nimlib.network.a.a aVar);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent == null || intent.getAction() == null) {
            com.netease.nimlib.log.b.f("NetworkBroadcastReceiver", "onReceive#intent=" + intent);
            return;
        }
        com.netease.nimlib.log.b.d("NetworkBroadcastReceiver", "onReceive#action=" + intent.getAction());
        if (TextUtils.equals(intent.getAction(), "android.net.conn.CONNECTIVITY_CHANGE")) {
            boolean n = p.n(context);
            com.netease.nimlib.network.a.a m = p.m(context);
            com.netease.nimlib.log.b.d("NetworkBroadcastReceiver", "onReceive#isOnline=" + n + ", networkStatus=" + m);
            a aVar = this.b;
            if (aVar != null) {
                aVar.onNetworkBroadcastCallback(n, m);
            }
        }
    }

    public void a(a aVar) {
        this.b = aVar;
    }
}
