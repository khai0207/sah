package com.netease.nimlib.l;

import com.netease.nimlib.plugin.interact.IMixPushInteract;

/* compiled from: CleanNotify.java */
/* loaded from: classes.dex */
public class a {
    public static final void a(h[] hVarArr) {
        if (hVarArr == null) {
            return;
        }
        for (h hVar : hVarArr) {
            a(hVar);
        }
    }

    public static final void a(h hVar) {
        if (hVar.b() != 1) {
            return;
        }
        d.a();
        a();
    }

    private static void a() {
        IMixPushInteract iMixPushInteract = (IMixPushInteract) com.netease.nimlib.plugin.interact.b.a().a(IMixPushInteract.class);
        if (iMixPushInteract != null) {
            iMixPushInteract.b();
        }
    }
}
