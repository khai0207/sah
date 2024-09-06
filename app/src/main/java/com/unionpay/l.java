package com.unionpay;

/* loaded from: classes.dex */
final class l implements ab {
    final /* synthetic */ UPPayWapActivity a;

    l(UPPayWapActivity uPPayWapActivity) {
        this.a = uPPayWapActivity;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0059 A[Catch: Exception -> 0x0065, TRY_LEAVE, TryCatch #2 {Exception -> 0x0065, blocks: (B:3:0x0005, B:10:0x002d, B:12:0x0059, B:20:0x0022), top: B:2:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:16:? A[RETURN, SYNTHETIC] */
    @Override // com.unionpay.ab
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a(java.lang.String r7, com.unionpay.ac r8) {
        /*
            r6 = this;
            java.lang.String r0 = "1"
            java.lang.String r1 = ""
            r2 = 0
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch: java.lang.Exception -> L65
            r3.<init>(r7)     // Catch: java.lang.Exception -> L65
            java.lang.String r7 = "url"
            java.lang.Object r7 = r3.get(r7)     // Catch: java.lang.Exception -> L1e
            java.lang.String r7 = (java.lang.String) r7     // Catch: java.lang.Exception -> L1e
            java.lang.String r4 = "title"
            java.lang.Object r3 = r3.get(r4)     // Catch: java.lang.Exception -> L1c
            java.lang.String r3 = (java.lang.String) r3     // Catch: java.lang.Exception -> L1c
            r1 = r3
            goto L2d
        L1c:
            r3 = move-exception
            goto L20
        L1e:
            r3 = move-exception
            r7 = r1
        L20:
            if (r8 == 0) goto L2d
            java.lang.String r3 = r3.getMessage()     // Catch: java.lang.Exception -> L65
            java.lang.String r3 = com.unionpay.UPPayWapActivity.a(r0, r3, r2)     // Catch: java.lang.Exception -> L65
            r8.a(r3)     // Catch: java.lang.Exception -> L65
        L2d:
            android.os.Bundle r3 = new android.os.Bundle     // Catch: java.lang.Exception -> L65
            r3.<init>()     // Catch: java.lang.Exception -> L65
            java.lang.String r4 = "waptype"
            java.lang.String r5 = "new_page"
            r3.putString(r4, r5)     // Catch: java.lang.Exception -> L65
            java.lang.String r4 = "wapurl"
            r3.putString(r4, r7)     // Catch: java.lang.Exception -> L65
            java.lang.String r7 = "waptitle"
            r3.putString(r7, r1)     // Catch: java.lang.Exception -> L65
            android.content.Intent r7 = new android.content.Intent     // Catch: java.lang.Exception -> L65
            r7.<init>()     // Catch: java.lang.Exception -> L65
            r7.putExtras(r3)     // Catch: java.lang.Exception -> L65
            com.unionpay.UPPayWapActivity r1 = r6.a     // Catch: java.lang.Exception -> L65
            java.lang.Class<com.unionpay.UPPayWapActivity> r3 = com.unionpay.UPPayWapActivity.class
            r7.setClass(r1, r3)     // Catch: java.lang.Exception -> L65
            com.unionpay.UPPayWapActivity r1 = r6.a     // Catch: java.lang.Exception -> L65
            r1.startActivity(r7)     // Catch: java.lang.Exception -> L65
            if (r8 == 0) goto L64
            java.lang.String r7 = "0"
            java.lang.String r1 = "success"
            java.lang.String r7 = com.unionpay.UPPayWapActivity.a(r7, r1, r2)     // Catch: java.lang.Exception -> L65
            r8.a(r7)     // Catch: java.lang.Exception -> L65
        L64:
            return
        L65:
            r7 = move-exception
            if (r8 == 0) goto L73
            java.lang.String r7 = r7.getMessage()
            java.lang.String r7 = com.unionpay.UPPayWapActivity.a(r0, r7, r2)
            r8.a(r7)
        L73:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.l.a(java.lang.String, com.unionpay.ac):void");
    }
}
