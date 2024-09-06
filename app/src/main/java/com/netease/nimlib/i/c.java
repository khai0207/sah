package com.netease.nimlib.i;

import android.os.Handler;
import com.netease.nimlib.plugin.interact.ISignallingInteract;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.auth.AuthServiceObserver;
import com.netease.nimlib.sdk.lifecycle.SdkLifecycleObserver;

/* compiled from: NotificationInterceptor.java */
/* loaded from: classes.dex */
class c {
    private Handler a;
    private ISignallingInteract b = (ISignallingInteract) com.netease.nimlib.plugin.interact.b.a().a(ISignallingInteract.class);

    c(Handler handler) {
        this.a = handler;
    }

    void a(String str, Observer observer) {
        if (str.equals(AuthServiceObserver.class.getSimpleName() + "/observeOnlineStatus")) {
            com.netease.nimlib.log.b.c("set status", "SDKState.getStatus():" + com.netease.nimlib.h.e());
            a(observer, com.netease.nimlib.h.e());
            return;
        }
        if (str.equals(AuthServiceObserver.class.getSimpleName() + "/observeOtherClients")) {
            a(observer, com.netease.nimlib.h.k());
            return;
        }
        if (str.equals(SdkLifecycleObserver.class.getSimpleName() + "/observeMainProcessInitCompleteResult")) {
            if (com.netease.nimlib.c.b()) {
                a(observer, (Object) true);
            }
        } else {
            ISignallingInteract iSignallingInteract = this.b;
            Object a = iSignallingInteract == null ? null : iSignallingInteract.a(str);
            if (a != null) {
                a(observer, a);
            }
        }
    }

    private void a(final Observer observer, final Object obj) {
        this.a.post(new Runnable() { // from class: com.netease.nimlib.i.c.1
            @Override // java.lang.Runnable
            public void run() {
                observer.onEvent(obj);
            }
        });
    }
}
