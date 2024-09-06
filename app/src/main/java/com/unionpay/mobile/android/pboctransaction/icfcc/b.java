package com.unionpay.mobile.android.pboctransaction.icfcc;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import cn.gov.pbc.tsm.client.mobile.android.bank.service.a;

/* loaded from: classes.dex */
final class b implements ServiceConnection {
    final /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        com.unionpay.mobile.android.pboctransaction.b bVar;
        com.unionpay.mobile.android.pboctransaction.b bVar2;
        com.unionpay.mobile.android.pboctransaction.b bVar3;
        com.unionpay.mobile.android.pboctransaction.b bVar4;
        try {
            this.a.c = a.AbstractBinderC0001a.a(iBinder);
            bVar3 = this.a.d;
            if (bVar3 != null) {
                bVar4 = this.a.d;
                bVar4.a();
            }
        } catch (Exception unused) {
            bVar = this.a.d;
            if (bVar != null) {
                bVar2 = this.a.d;
                bVar2.b();
            }
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        this.a.c = null;
    }
}
