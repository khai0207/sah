package com.unionpay.mobile.android.pboctransaction.samsung;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import com.unionpay.client3.tsm.ITsmConnection;
import com.unionpay.mobile.android.utils.j;

/* loaded from: classes.dex */
final class d implements ServiceConnection {
    final /* synthetic */ b a;

    d(b bVar) {
        this.a = bVar;
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        Handler handler;
        j.a("plugin-clientV3", "startSamsungService onServiceConnected");
        try {
            this.a.c = ITsmConnection.Stub.asInterface(iBinder);
            handler = this.a.f;
            handler.removeMessages(1);
            this.a.a(true);
        } catch (Exception unused) {
            this.a.a(false);
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        Handler handler;
        j.a("plugin-clientV3", "startSamsungService onServiceDisconnected");
        this.a.c = null;
        handler = this.a.f;
        handler.removeMessages(1);
        this.a.a(false);
    }
}
