package com.iflytek.cloud.util.a.c;

import android.content.Context;
import android.net.Uri;
import android.provider.CallLog;
import com.alipay.sdk.m.h.c;

/* loaded from: classes.dex */
public abstract class a {
    protected static final String[] a = {"number", c.e, "date"};
    protected static String[] c;
    protected Context b;

    public a(Context context) {
        this.b = null;
        this.b = context;
    }

    public abstract Uri a();

    /* JADX WARN: Code restructure failed: missing block: B:11:0x008a, code lost:
    
        r0.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0088, code lost:
    
        if (0 == 0) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x007f, code lost:
    
        if (r0 != null) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x008d, code lost:
    
        return r9;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.util.HashMap<java.lang.String, java.lang.String> a(int r9) {
        /*
            r8 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "date DESC limit "
            r0.append(r1)
            r0.append(r9)
            java.lang.String r7 = r0.toString()
            java.util.HashMap r9 = new java.util.HashMap
            r9.<init>()
            r0 = 0
            java.lang.String r5 = "0==0) GROUP BY (number"
            android.content.Context r1 = r8.b     // Catch: java.lang.Throwable -> L82 java.lang.Exception -> L84
            android.content.ContentResolver r2 = r1.getContentResolver()     // Catch: java.lang.Throwable -> L82 java.lang.Exception -> L84
            android.net.Uri r3 = android.provider.CallLog.Calls.CONTENT_URI     // Catch: java.lang.Throwable -> L82 java.lang.Exception -> L84
            java.lang.String[] r4 = com.iflytek.cloud.util.a.c.a.a     // Catch: java.lang.Throwable -> L82 java.lang.Exception -> L84
            r6 = 0
            android.database.Cursor r0 = r2.query(r3, r4, r5, r6, r7)     // Catch: java.lang.Throwable -> L82 java.lang.Exception -> L84
            java.lang.String r1 = "iFly_ContactManager"
            if (r0 != 0) goto L32
            java.lang.String r2 = "queryCallLog ----------------cursor is null"
        L2e:
            com.iflytek.cloud.a.g.a.a.a(r1, r2)     // Catch: java.lang.Throwable -> L82 java.lang.Exception -> L84
            goto L7f
        L32:
            int r2 = r0.getCount()     // Catch: java.lang.Throwable -> L82 java.lang.Exception -> L84
            if (r2 != 0) goto L3b
            java.lang.String r2 = "queryCallLog ----------------cursor getCount == 0"
            goto L2e
        L3b:
            boolean r2 = r0.moveToNext()     // Catch: java.lang.Throwable -> L82 java.lang.Exception -> L84
            if (r2 == 0) goto L69
            java.lang.String r2 = "number"
            int r2 = r0.getColumnIndex(r2)     // Catch: java.lang.Throwable -> L82 java.lang.Exception -> L84
            java.lang.String r2 = r0.getString(r2)     // Catch: java.lang.Throwable -> L82 java.lang.Exception -> L84
            java.lang.String r3 = "name"
            int r3 = r0.getColumnIndex(r3)     // Catch: java.lang.Throwable -> L82 java.lang.Exception -> L84
            java.lang.String r3 = r0.getString(r3)     // Catch: java.lang.Throwable -> L82 java.lang.Exception -> L84
            java.lang.String r4 = "date"
            int r4 = r0.getColumnIndex(r4)     // Catch: java.lang.Throwable -> L82 java.lang.Exception -> L84
            java.lang.String r4 = r0.getString(r4)     // Catch: java.lang.Throwable -> L82 java.lang.Exception -> L84
            if (r3 != 0) goto L65
            java.lang.String r3 = com.iflytek.cloud.util.a.e.a(r2)     // Catch: java.lang.Throwable -> L82 java.lang.Exception -> L84
        L65:
            r9.put(r4, r3)     // Catch: java.lang.Throwable -> L82 java.lang.Exception -> L84
            goto L3b
        L69:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L82 java.lang.Exception -> L84
            r2.<init>()     // Catch: java.lang.Throwable -> L82 java.lang.Exception -> L84
            java.lang.String r3 = "queryCallLog ----------------cursor getCount =="
            r2.append(r3)     // Catch: java.lang.Throwable -> L82 java.lang.Exception -> L84
            int r3 = r0.getCount()     // Catch: java.lang.Throwable -> L82 java.lang.Exception -> L84
            r2.append(r3)     // Catch: java.lang.Throwable -> L82 java.lang.Exception -> L84
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L82 java.lang.Exception -> L84
            goto L2e
        L7f:
            if (r0 == 0) goto L8d
            goto L8a
        L82:
            r9 = move-exception
            goto L8e
        L84:
            r1 = move-exception
            r1.printStackTrace()     // Catch: java.lang.Throwable -> L82
            if (r0 == 0) goto L8d
        L8a:
            r0.close()
        L8d:
            return r9
        L8e:
            if (r0 == 0) goto L93
            r0.close()
        L93:
            goto L95
        L94:
            throw r9
        L95:
            goto L94
        */
        throw new UnsupportedOperationException("Method not decompiled: com.iflytek.cloud.util.a.c.a.a(int):java.util.HashMap");
    }

    protected void a(Context context) {
        String[] strArr = new String[100];
        c = strArr;
        strArr[0] = "其他";
        strArr[1] = "住宅";
        strArr[2] = "手机";
        strArr[3] = "工作";
        strArr[4] = "工作传真";
        strArr[5] = "住宅传真";
        strArr[6] = "寻呼机";
        strArr[7] = "其他";
        strArr[9] = "SIM卡";
        int i = 10;
        while (true) {
            String[] strArr2 = c;
            if (i >= strArr2.length) {
                return;
            }
            strArr2[i] = "自定义电话";
            i++;
        }
    }

    protected abstract String[] b();

    protected abstract String c();

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0075, code lost:
    
        r8.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0073, code lost:
    
        if (0 == 0) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x006a, code lost:
    
        if (r8 != null) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0078, code lost:
    
        return r7;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.util.HashMap<java.lang.String, java.lang.String> d() {
        /*
            r9 = this;
            java.lang.String[] r6 = r9.b()
            java.util.HashMap r7 = new java.util.HashMap
            r7.<init>()
            r8 = 0
            android.content.Context r0 = r9.b     // Catch: java.lang.Throwable -> L6d java.lang.Exception -> L6f
            android.content.ContentResolver r0 = r0.getContentResolver()     // Catch: java.lang.Throwable -> L6d java.lang.Exception -> L6f
            android.net.Uri r1 = r9.a()     // Catch: java.lang.Throwable -> L6d java.lang.Exception -> L6f
            r3 = 0
            r4 = 0
            java.lang.String r5 = r9.c()     // Catch: java.lang.Throwable -> L6d java.lang.Exception -> L6f
            r2 = r6
            android.database.Cursor r8 = r0.query(r1, r2, r3, r4, r5)     // Catch: java.lang.Throwable -> L6d java.lang.Exception -> L6f
            java.lang.String r0 = "iFly_ContactManager"
            if (r8 != 0) goto L29
            java.lang.String r1 = "queryContacts------cursor is null"
        L25:
            com.iflytek.cloud.a.g.a.a.a(r0, r1)     // Catch: java.lang.Throwable -> L6d java.lang.Exception -> L6f
            goto L6a
        L29:
            int r1 = r8.getCount()     // Catch: java.lang.Throwable -> L6d java.lang.Exception -> L6f
            if (r1 != 0) goto L32
            java.lang.String r1 = "queryContacts------cursor getCount == 0"
            goto L25
        L32:
            boolean r1 = r8.moveToNext()     // Catch: java.lang.Throwable -> L6d java.lang.Exception -> L6f
            if (r1 == 0) goto L54
            r1 = 0
            r1 = r6[r1]     // Catch: java.lang.Throwable -> L6d java.lang.Exception -> L6f
            int r1 = r8.getColumnIndex(r1)     // Catch: java.lang.Throwable -> L6d java.lang.Exception -> L6f
            java.lang.String r1 = r8.getString(r1)     // Catch: java.lang.Throwable -> L6d java.lang.Exception -> L6f
            r2 = 1
            r2 = r6[r2]     // Catch: java.lang.Throwable -> L6d java.lang.Exception -> L6f
            int r2 = r8.getColumnIndex(r2)     // Catch: java.lang.Throwable -> L6d java.lang.Exception -> L6f
            java.lang.String r2 = r8.getString(r2)     // Catch: java.lang.Throwable -> L6d java.lang.Exception -> L6f
            if (r1 == 0) goto L32
            r7.put(r2, r1)     // Catch: java.lang.Throwable -> L6d java.lang.Exception -> L6f
            goto L32
        L54:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L6d java.lang.Exception -> L6f
            r1.<init>()     // Catch: java.lang.Throwable -> L6d java.lang.Exception -> L6f
            java.lang.String r2 = "queryContacts_20------count = "
            r1.append(r2)     // Catch: java.lang.Throwable -> L6d java.lang.Exception -> L6f
            int r2 = r8.getCount()     // Catch: java.lang.Throwable -> L6d java.lang.Exception -> L6f
            r1.append(r2)     // Catch: java.lang.Throwable -> L6d java.lang.Exception -> L6f
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L6d java.lang.Exception -> L6f
            goto L25
        L6a:
            if (r8 == 0) goto L78
            goto L75
        L6d:
            r0 = move-exception
            goto L79
        L6f:
            r0 = move-exception
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L6d
            if (r8 == 0) goto L78
        L75:
            r8.close()
        L78:
            return r7
        L79:
            if (r8 == 0) goto L7e
            r8.close()
        L7e:
            goto L80
        L7f:
            throw r0
        L80:
            goto L7f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.iflytek.cloud.util.a.c.a.d():java.util.HashMap");
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0080, code lost:
    
        if (r1 != null) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x008e, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x008b, code lost:
    
        r1.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0089, code lost:
    
        if (0 == 0) goto L25;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.util.List<com.iflytek.cloud.util.a.a.a> e() {
        /*
            r11 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
            android.content.Context r2 = r11.b     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L85
            android.content.ContentResolver r3 = r2.getContentResolver()     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L85
            java.lang.String r2 = "content://icc/adn"
            android.net.Uri r4 = android.net.Uri.parse(r2)     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L85
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            android.database.Cursor r1 = r3.query(r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L85
            java.lang.String r2 = "iFly_ContactManager"
            if (r1 == 0) goto L7d
            int r3 = r1.getCount()     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L85
            if (r3 <= 0) goto L7d
        L24:
            boolean r3 = r1.moveToNext()     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L85
            if (r3 == 0) goto L64
            java.lang.String r3 = "name"
            int r3 = r1.getColumnIndex(r3)     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L85
            java.lang.String r6 = r1.getString(r3)     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L85
            java.lang.String r3 = "_id"
            int r3 = r1.getColumnIndex(r3)     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L85
            java.lang.String r5 = r1.getString(r3)     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L85
            java.lang.String r3 = "number"
            int r3 = r1.getColumnIndex(r3)     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L85
            java.lang.String r3 = r1.getString(r3)     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L85
            java.lang.String r3 = com.iflytek.cloud.util.a.e.a(r3)     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L85
            java.lang.String r8 = com.iflytek.cloud.a.g.d.a(r3)     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L85
            if (r6 == 0) goto L24
            com.iflytek.cloud.util.a.a.a r3 = new com.iflytek.cloud.util.a.a.a     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L85
            r7 = 0
            r9 = 0
            java.lang.String[] r4 = com.iflytek.cloud.util.a.c.a.c     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L85
            r10 = 9
            r10 = r4[r10]     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L85
            r4 = r3
            r4.<init>(r5, r6, r7, r8, r9, r10)     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L85
            r0.add(r3)     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L85
            goto L24
        L64:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L85
            r3.<init>()     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L85
            java.lang.String r4 = "querySIM-------count = "
            r3.append(r4)     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L85
            int r4 = r1.getCount()     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L85
            r3.append(r4)     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L85
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L85
        L79:
            com.iflytek.cloud.a.g.a.a.a(r2, r3)     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L85
            goto L80
        L7d:
            java.lang.String r3 = "querySIM-------cursor getCount = 0 or cursor is null"
            goto L79
        L80:
            if (r1 == 0) goto L8e
            goto L8b
        L83:
            r0 = move-exception
            goto L8f
        L85:
            r2 = move-exception
            r2.printStackTrace()     // Catch: java.lang.Throwable -> L83
            if (r1 == 0) goto L8e
        L8b:
            r1.close()
        L8e:
            return r0
        L8f:
            if (r1 == 0) goto L94
            r1.close()
        L94:
            goto L96
        L95:
            throw r0
        L96:
            goto L95
        */
        throw new UnsupportedOperationException("Method not decompiled: com.iflytek.cloud.util.a.c.a.e():java.util.List");
    }

    public Uri f() {
        return CallLog.Calls.CONTENT_URI;
    }
}
