package com.unionpay.mobile.android.pboctransaction.samsung;

import android.util.Log;
import com.unionpay.mobile.android.utils.j;
import com.unionpay.tsmservice.UPTsmAddon;

/* loaded from: classes.dex */
final class h implements UPTsmAddon.UPTsmConnectionListener {
    final /* synthetic */ f a;

    h(f fVar) {
        this.a = fVar;
    }

    @Override // com.unionpay.tsmservice.UPTsmAddon.UPTsmConnectionListener
    public final void onTsmConnected() {
        j.c("uppay", "TsmService connected.");
        this.a.f();
    }

    @Override // com.unionpay.tsmservice.UPTsmAddon.UPTsmConnectionListener
    public final void onTsmDisconnected() {
        Log.e("uppay", "TsmService disconnected.");
        this.a.a(false);
    }
}
