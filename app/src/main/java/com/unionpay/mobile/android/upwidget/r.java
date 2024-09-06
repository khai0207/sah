package com.unionpay.mobile.android.upwidget;

import android.os.Handler;
import android.os.Message;
import java.util.List;

/* loaded from: classes.dex */
final class r extends Handler {
    final /* synthetic */ UPRadiationView a;

    r(UPRadiationView uPRadiationView) {
        this.a = uPRadiationView;
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        List list;
        List list2;
        Handler handler;
        super.handleMessage(message);
        if (message.what != 0) {
            return;
        }
        UPRadiationView.a(this.a);
        this.a.invalidate();
        list = this.a.a;
        if (list != null) {
            list2 = this.a.a;
            if (list2.size() > 0) {
                handler = this.a.e;
                handler.sendEmptyMessageDelayed(0, 50L);
            }
        }
    }
}
