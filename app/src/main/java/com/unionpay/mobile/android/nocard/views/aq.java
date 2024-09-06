package com.unionpay.mobile.android.nocard.views;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

/* loaded from: classes.dex */
final class aq implements Handler.Callback {
    final /* synthetic */ ao a;

    aq(ao aoVar) {
        this.a = aoVar;
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        if (this.a.a.aK != com.unionpay.mobile.android.views.order.l.a.intValue() || this.a.a.E) {
            return true;
        }
        if (!TextUtils.isEmpty(this.a.a.p)) {
            ao aoVar = this.a;
            aoVar.a(13, aoVar.p);
            return true;
        }
        if (!this.a.a.ax) {
            return true;
        }
        ao.c(this.a);
        return true;
    }
}
