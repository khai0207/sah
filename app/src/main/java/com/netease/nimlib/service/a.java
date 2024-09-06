package com.netease.nimlib.service;

import android.content.Context;
import com.netease.nimlib.c;
import com.netease.nimlib.o.aa;
import com.netease.nimlib.o.e;
import com.netease.nimlib.o.i;
import com.netease.nimlib.push.g;
import java.util.Iterator;
import java.util.List;

/* compiled from: AwakeUI.java */
/* loaded from: classes.dex */
public class a {
    private long b = 0;
    private boolean d = false;
    private i a = new i(2000, 0);
    private e<Integer> c = new e<>(31);

    public void a(Context context, int i) {
        com.netease.nimlib.log.b.e("AwakeUI.tryAwake start, pendingSize = " + i);
        if (c.H() || c.s() || c.G()) {
            g.a(context.getApplicationContext());
            return;
        }
        if (this.a.b()) {
            if (this.b == 0) {
                this.b = System.currentTimeMillis();
            }
            this.c.a(Integer.valueOf(i));
            if (b()) {
                com.netease.nimlib.log.b.O("IPC has broken, push process unable to awake UI, kill self!!!");
                g.a(context.getApplicationContext());
                return;
            }
            com.netease.nimlib.log.b.e("AwakeUI.tryAwake awake UI to bind Push process, pending data... " + i);
            if (!aa.a()) {
                ResponseReceiver.a(context);
            }
            ResponseService.a(context);
            this.a.a();
        }
    }

    public void a() {
        this.b = 0L;
        this.c.b();
    }

    private boolean b() {
        if (System.currentTimeMillis() - this.b >= 300000) {
            com.netease.nimlib.log.b.e("AwakeUI.killSelfCheck, wait time over 5 minutes, kill self!!!");
            return true;
        }
        List<Integer> d = this.c.d();
        com.netease.nimlib.log.b.e("AwakeUI.killSelfCheck, pendingSizeQueue = " + d);
        if (d == null || d.size() < 30) {
            return false;
        }
        Iterator<Integer> it = d.iterator();
        int i = 0;
        while (it.hasNext()) {
            int intValue = it.next().intValue();
            if (intValue < i) {
                return false;
            }
            i = intValue;
        }
        com.netease.nimlib.log.b.e("AwakeUI.killSelfCheck, return true ");
        return true;
    }
}
