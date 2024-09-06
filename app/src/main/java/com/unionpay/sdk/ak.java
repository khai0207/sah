package com.unionpay.sdk;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.unionpay.sdk.r;
import java.util.HashMap;

/* loaded from: classes.dex */
final class ak extends Handler {
    ak(Looper looper) {
        super(looper);
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        if (AgentOption.a() && message.obj != null && (message.obj instanceof r.a)) {
            r.a aVar = (r.a) message.obj;
            aVar.a.get("controller");
            ar.c().a();
            int i = message.what;
            if (i == 101) {
                HashMap hashMap = aVar.a;
            } else if (i == 102) {
                ai.a().post(aVar);
            }
            ar.c().b();
        }
    }
}
