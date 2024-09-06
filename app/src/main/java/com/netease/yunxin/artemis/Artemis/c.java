package com.netease.yunxin.artemis.Artemis;

import android.app.ActivityManager;
import android.content.Context;
import android.os.MessageQueue;
import com.netease.yunxin.artemis.ArtemisTask.YXArtemisDig;
import com.netease.yunxin.artemis.ArtemisTask.YXArtemisFile;
import com.netease.yunxin.artemis.ArtemisTask.YXArtemisHttp;
import com.netease.yunxin.artemis.ArtemisTask.YXArtemisIcmp;
import com.netease.yunxin.artemis.ArtemisTask.YXArtemisIcmpIpv6;
import com.netease.yunxin.artemis.ArtemisTask.YXArtemisPullTask;
import com.netease.yunxin.artemis.ArtemisTask.YXArtemisTcp;
import com.netease.yunxin.artemis.ArtemisTask.YXArtemisTelnet;
import com.netease.yunxin.artemis.ArtemisTask.YXArtemisTraceRouter;
import com.netease.yunxin.artemis.ArtemisTask.YXArtemisTraceRouterIpv6;
import com.netease.yunxin.artemis.ArtemisTask.YXArtemisUdp;
import com.netease.yunxin.artemis.a.g;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/* compiled from: YXArtemisManager.java */
/* loaded from: classes.dex */
public final class c {
    public static HashMap<Integer, String> b = new HashMap<>();
    private static c j;
    public Context c;
    public a d;
    private ActivityManager k;
    private ActivityManager.MemoryInfo l;
    private final long g = 30;
    private final long h = 30;
    private final long i = com.umeng.analytics.a.h;
    public ArrayList<b> a = new ArrayList<>();
    double e = 200.0d;
    public final MessageQueue.IdleHandler f = new MessageQueue.IdleHandler() { // from class: com.netease.yunxin.artemis.Artemis.c.1
        AnonymousClass1() {
        }

        @Override // android.os.MessageQueue.IdleHandler
        public final boolean queueIdle() {
            if (c.this.a.size() == 0) {
                return false;
            }
            if (c.this.c() < c.this.e) {
                return true;
            }
            for (int i = 0; i < c.this.a.size(); i++) {
                c cVar = c.this;
                cVar.a(cVar.a.get(i));
            }
            return false;
        }
    };

    private c() {
        b.put(1, YXArtemisTcp.class.getName());
        b.put(2, YXArtemisUdp.class.getName());
        b.put(3, YXArtemisIcmp.class.getName());
        b.put(4, YXArtemisTelnet.class.getName());
        b.put(5, YXArtemisTraceRouter.class.getName());
        b.put(6, YXArtemisHttp.class.getName());
        b.put(7, YXArtemisDig.class.getName());
        b.put(8, YXArtemisFile.class.getName());
        b.put(9, YXArtemisIcmpIpv6.class.getName());
        b.put(10, YXArtemisTraceRouterIpv6.class.getName());
    }

    public static c a() {
        if (j == null) {
            j = new c();
        }
        return j;
    }

    public final void a(b bVar) {
        if (this.d != null) {
            a.a(bVar);
        }
    }

    public final void a(MessageQueue.IdleHandler idleHandler) {
        a aVar = this.d;
        if (aVar != null) {
            aVar.a(idleHandler);
        }
    }

    public static void b() {
        try {
            long currentTimeMillis = System.currentTimeMillis() + com.umeng.analytics.a.h;
            YXArtemisPullTask.getInstance().setNextFetchTime(new Date(currentTimeMillis));
            g.a().a(String.valueOf(currentTimeMillis));
        } catch (Exception unused) {
        }
    }

    public final double c() {
        Context context = this.c;
        if (context == null) {
            return 0.0d;
        }
        if (this.k == null) {
            this.k = (ActivityManager) context.getSystemService("activity");
            this.l = new ActivityManager.MemoryInfo();
        }
        this.k.getMemoryInfo(this.l);
        double d = this.l.availMem;
        Double.isNaN(d);
        return ((d * 1.0d) / 1024.0d) / 1024.0d;
    }

    /* compiled from: YXArtemisManager.java */
    /* renamed from: com.netease.yunxin.artemis.Artemis.c$1 */
    /* loaded from: classes.dex */
    final class AnonymousClass1 implements MessageQueue.IdleHandler {
        AnonymousClass1() {
        }

        @Override // android.os.MessageQueue.IdleHandler
        public final boolean queueIdle() {
            if (c.this.a.size() == 0) {
                return false;
            }
            if (c.this.c() < c.this.e) {
                return true;
            }
            for (int i = 0; i < c.this.a.size(); i++) {
                c cVar = c.this;
                cVar.a(cVar.a.get(i));
            }
            return false;
        }
    }
}
