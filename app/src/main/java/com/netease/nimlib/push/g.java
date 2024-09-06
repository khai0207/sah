package com.netease.nimlib.push;

import android.content.Context;
import com.netease.nimlib.o.x;
import com.netease.nimlib.service.NimReceiver;
import com.netease.nimlib.service.NimService;

/* compiled from: PushSelfKiller.java */
/* loaded from: classes.dex */
public class g {
    public static void a(Context context) {
        b(context);
        x.a(context);
    }

    public static void b(Context context) {
        com.netease.nimlib.job.a.a().b(context.getApplicationContext());
        NimService.b();
        NimReceiver.b(context);
        com.netease.nimlib.push.net.a.c();
    }
}
