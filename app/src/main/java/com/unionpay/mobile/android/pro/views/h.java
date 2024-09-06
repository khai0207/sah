package com.unionpay.mobile.android.pro.views;

import android.content.Context;
import com.unionpay.tsmservice.data.Constant;
import org.simalliance.openmobileapi.Reader;

/* loaded from: classes.dex */
public class h extends com.unionpay.mobile.android.nocard.views.l {
    public h(Context context) {
        super(context);
    }

    @Override // com.unionpay.mobile.android.nocard.views.l
    public final void B() {
        StringBuffer stringBuffer = new StringBuffer(Constant.DEFAULT_CVN2);
        com.unionpay.mobile.android.model.b bVar = this.a;
        if ((com.unionpay.mobile.android.model.b.aV ? new com.unionpay.mobile.android.utils.k() : null) != null && com.unionpay.mobile.android.utils.k.a() != null && com.unionpay.mobile.android.utils.k.a().isConnected()) {
            for (Reader reader : com.unionpay.mobile.android.utils.k.a().getReaders()) {
                if (reader != null) {
                    if (reader.getName().toLowerCase().startsWith("sim")) {
                        stringBuffer.setCharAt(0, '1');
                    }
                    if (reader.getName().toLowerCase().startsWith("ese")) {
                        stringBuffer.setCharAt(1, '1');
                    }
                    if (reader.getName().toLowerCase().startsWith("sd")) {
                        stringBuffer.setCharAt(2, '1');
                    }
                }
            }
        }
        d(stringBuffer.toString());
    }

    public com.unionpay.mobile.android.pro.pboc.engine.b C() {
        return null;
    }

    @Override // com.unionpay.mobile.android.nocard.views.l
    protected final void t() {
        try {
            Class.forName("org.simalliance.openmobileapi.SEService");
            com.unionpay.mobile.android.model.b bVar = this.a;
            com.unionpay.mobile.android.model.b.aV = true;
            new com.unionpay.mobile.android.utils.k(this.d, this);
        } catch (Exception e) {
            e.printStackTrace();
            com.unionpay.mobile.android.model.b bVar2 = this.a;
            com.unionpay.mobile.android.model.b.aV = false;
            u();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0033 A[Catch: all -> 0x0048, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0016, B:9:0x0028, B:11:0x0033, B:12:0x003f, B:13:0x0046, B:17:0x003b), top: B:2:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x003b A[Catch: all -> 0x0048, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0016, B:9:0x0028, B:11:0x0033, B:12:0x003f, B:13:0x0046, B:17:0x003b), top: B:2:0x0001 }] */
    @Override // com.unionpay.mobile.android.nocard.views.l
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void y() {
        /*
            r3 = this;
            monitor-enter(r3)
            java.lang.String r0 = "uppay-pro"
            java.lang.String r1 = "checkAndGetSDCardsList +++"
            com.unionpay.mobile.android.utils.j.c(r0, r1)     // Catch: java.lang.Throwable -> L48
            java.lang.String r0 = "00"
            com.unionpay.mobile.android.model.b r1 = r3.a     // Catch: java.lang.Throwable -> L48
            com.unionpay.mobile.android.plugin.c r1 = r1.D     // Catch: java.lang.Throwable -> L48
            java.lang.String r1 = r1.c     // Catch: java.lang.Throwable -> L48
            boolean r0 = r0.equalsIgnoreCase(r1)     // Catch: java.lang.Throwable -> L48
            if (r0 != 0) goto L27
            java.lang.String r0 = "95"
            com.unionpay.mobile.android.model.b r1 = r3.a     // Catch: java.lang.Throwable -> L48
            com.unionpay.mobile.android.plugin.c r1 = r1.D     // Catch: java.lang.Throwable -> L48
            java.lang.String r1 = r1.c     // Catch: java.lang.Throwable -> L48
            boolean r0 = r0.equalsIgnoreCase(r1)     // Catch: java.lang.Throwable -> L48
            if (r0 == 0) goto L25
            goto L27
        L25:
            r0 = 0
            goto L28
        L27:
            r0 = 1
        L28:
            com.unionpay.mobile.android.pro.pboc.engine.b r1 = r3.C()     // Catch: java.lang.Throwable -> L48
            com.unionpay.mobile.android.pro.views.i r2 = new com.unionpay.mobile.android.pro.views.i     // Catch: java.lang.Throwable -> L48
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L48
            if (r1 == 0) goto L3b
            com.unionpay.mobile.android.pro.pboc.engine.b r1 = r3.C()     // Catch: java.lang.Throwable -> L48
            r1.a(r2, r0)     // Catch: java.lang.Throwable -> L48
            goto L3f
        L3b:
            r0 = 0
            r2.a(r0)     // Catch: java.lang.Throwable -> L48
        L3f:
            java.lang.String r0 = "uppay-pro"
            java.lang.String r1 = "checkAndGetSDCardsList ---"
            com.unionpay.mobile.android.utils.j.c(r0, r1)     // Catch: java.lang.Throwable -> L48
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L48
            return
        L48:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.mobile.android.pro.views.h.y():void");
    }
}
