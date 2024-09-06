package com.unionpay.mobile.android.widgets;

import android.view.View;

/* loaded from: classes.dex */
final class l implements View.OnClickListener {
    final /* synthetic */ k a;

    l(k kVar) {
        this.a = kVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0083 A[LOOP:0: B:12:0x007d->B:14:0x0083, LOOP_END] */
    @Override // android.view.View.OnClickListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onClick(android.view.View r7) {
        /*
            r6 = this;
            com.unionpay.mobile.android.widgets.k r0 = r6.a
            boolean r0 = com.unionpay.mobile.android.widgets.k.a(r0)
            r1 = 0
            if (r0 == 0) goto L8e
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>()
            com.unionpay.mobile.android.widgets.k r2 = r6.a     // Catch: org.json.JSONException -> L6c
            java.lang.String r2 = r2.a()     // Catch: org.json.JSONException -> L6c
            boolean r2 = android.text.TextUtils.isEmpty(r2)     // Catch: org.json.JSONException -> L6c
            java.lang.String r3 = "errMsg"
            r4 = 1
            if (r2 != 0) goto L5b
            com.unionpay.mobile.android.widgets.k r2 = r6.a     // Catch: org.json.JSONException -> L6c
            java.lang.String r2 = r2.a()     // Catch: org.json.JSONException -> L6c
            java.lang.String r5 = "[A-Za-z0-9]{8,32}"
            boolean r2 = r2.matches(r5)     // Catch: org.json.JSONException -> L6c
            if (r2 == 0) goto L47
            com.unionpay.mobile.android.widgets.k r1 = r6.a     // Catch: org.json.JSONException -> L6c
            r1.a(r4)     // Catch: org.json.JSONException -> L6c
            java.lang.String r1 = "value"
            com.unionpay.mobile.android.widgets.k r2 = r6.a     // Catch: org.json.JSONException -> L6c
            java.lang.String r2 = r2.h()     // Catch: org.json.JSONException -> L6c
            r0.put(r1, r2)     // Catch: org.json.JSONException -> L6c
            java.lang.String r1 = "action"
            com.unionpay.mobile.android.widgets.k r2 = r6.a     // Catch: org.json.JSONException -> L6c
            java.lang.String r2 = com.unionpay.mobile.android.widgets.k.b(r2)     // Catch: org.json.JSONException -> L6c
            r0.put(r1, r2)     // Catch: org.json.JSONException -> L6c
            goto L70
        L47:
            com.unionpay.mobile.android.languages.c r2 = com.unionpay.mobile.android.languages.c.bD     // Catch: org.json.JSONException -> L6c
            java.lang.String r2 = r2.aD     // Catch: org.json.JSONException -> L6c
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch: org.json.JSONException -> L6c
            com.unionpay.mobile.android.languages.c r5 = com.unionpay.mobile.android.languages.c.bD     // Catch: org.json.JSONException -> L6c
            java.lang.String r5 = r5.C     // Catch: org.json.JSONException -> L6c
            r4[r1] = r5     // Catch: org.json.JSONException -> L6c
            java.lang.String r1 = java.lang.String.format(r2, r4)     // Catch: org.json.JSONException -> L6c
        L57:
            r0.put(r3, r1)     // Catch: org.json.JSONException -> L6c
            goto L70
        L5b:
            com.unionpay.mobile.android.languages.c r2 = com.unionpay.mobile.android.languages.c.bD     // Catch: org.json.JSONException -> L6c
            java.lang.String r2 = r2.aC     // Catch: org.json.JSONException -> L6c
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch: org.json.JSONException -> L6c
            com.unionpay.mobile.android.languages.c r5 = com.unionpay.mobile.android.languages.c.bD     // Catch: org.json.JSONException -> L6c
            java.lang.String r5 = r5.C     // Catch: org.json.JSONException -> L6c
            r4[r1] = r5     // Catch: org.json.JSONException -> L6c
            java.lang.String r1 = java.lang.String.format(r2, r4)     // Catch: org.json.JSONException -> L6c
            goto L57
        L6c:
            r1 = move-exception
            r1.printStackTrace()
        L70:
            r7.setTag(r0)
            com.unionpay.mobile.android.widgets.k r0 = r6.a
            java.util.ArrayList r0 = com.unionpay.mobile.android.widgets.k.c(r0)
            java.util.Iterator r0 = r0.iterator()
        L7d:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L8d
            java.lang.Object r1 = r0.next()
            android.view.View$OnClickListener r1 = (android.view.View.OnClickListener) r1
            r1.onClick(r7)
            goto L7d
        L8d:
            return
        L8e:
            com.unionpay.mobile.android.widgets.k r0 = r6.a
            com.unionpay.mobile.android.widgets.t r0 = r0.b
            r0.e()
            com.unionpay.mobile.android.widgets.k r0 = r6.a
            r0.a(r1)
            com.unionpay.mobile.android.widgets.k r0 = r6.a
            java.util.ArrayList r0 = com.unionpay.mobile.android.widgets.k.d(r0)
            java.util.Iterator r0 = r0.iterator()
        La4:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto Lb4
            java.lang.Object r1 = r0.next()
            android.view.View$OnClickListener r1 = (android.view.View.OnClickListener) r1
            r1.onClick(r7)
            goto La4
        Lb4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.mobile.android.widgets.l.onClick(android.view.View):void");
    }
}
