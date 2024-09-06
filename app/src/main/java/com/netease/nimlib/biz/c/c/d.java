package com.netease.nimlib.biz.c.c;

import android.text.TextUtils;
import com.netease.nimlib.biz.d.d.m;
import com.netease.nimlib.biz.e.d.k;
import com.netease.nimlib.o.y;
import com.netease.nimlib.sdk.misc.model.NosConfig;

/* compiled from: NosConfigResponseHandler.java */
/* loaded from: classes.dex */
public class d extends com.netease.nimlib.biz.c.i {
    @Override // com.netease.nimlib.biz.c.a
    public void a(com.netease.nimlib.biz.e.a aVar) {
        if (aVar.n()) {
            com.netease.nimlib.push.packet.b.c a = ((k) aVar).a();
            NosConfig a2 = a(a.c(1), a.c(2), a.e(3), a.c(4));
            com.netease.nimlib.c.a(a2);
            if (a2.isValid()) {
                com.netease.nimlib.c.b.a.a(com.netease.nimlib.c.e()).postDelayed(new Runnable() { // from class: com.netease.nimlib.biz.c.c.-$$Lambda$d$3Z_3bFqhfb_DUURlvzVsKbJBHI8
                    @Override // java.lang.Runnable
                    public final void run() {
                        d.a();
                    }
                }, a2.getDeadline() - y.a());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void a() {
        com.netease.nimlib.biz.i.a().a(new m());
    }

    private NosConfig a(String str, String str2, long j, String str3) {
        if (TextUtils.isEmpty(str2) || j < -1) {
            j = 0;
        }
        return new NosConfig(str, str2, a(j), str3);
    }

    private long a(long j) {
        if (j == -1) {
            return Long.MAX_VALUE;
        }
        if (j == 0) {
            return 0L;
        }
        return y.a() + (j * 800);
    }
}
