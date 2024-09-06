package com.netease.nimlib.search.b;

import android.util.Pair;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: NIMTokenizer.java */
/* loaded from: classes.dex */
public class a {
    private int[] a;

    /* compiled from: NIMTokenizer.java */
    /* renamed from: com.netease.nimlib.search.b.a$a, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static class C0057a {
        public static final a a = new a();
    }

    public a() {
        b();
    }

    private void b() {
        if (this.a != null) {
            return;
        }
        this.a = new int[128];
        for (int i = 0; i <= 32; i++) {
            this.a[i] = 4;
        }
        for (int i2 = 33; i2 <= 47; i2++) {
            this.a[i2] = 3;
        }
        this.a[39] = 0;
        for (int i3 = 48; i3 <= 57; i3++) {
            this.a[i3] = 2;
        }
        for (int i4 = 58; i4 <= 64; i4++) {
            this.a[i4] = 3;
        }
        for (int i5 = 65; i5 <= 90; i5++) {
            this.a[i5] = 0;
        }
        for (int i6 = 91; i6 <= 96; i6++) {
            this.a[i6] = 3;
        }
        for (int i7 = 97; i7 <= 122; i7++) {
            this.a[i7] = 0;
        }
        for (int i8 = 123; i8 <= 126; i8++) {
            this.a[i8] = 3;
        }
        this.a[127] = 4;
    }

    int a(char c) {
        if (c >= 128) {
            return 1;
        }
        return this.a[c];
    }

    public List<Pair<String, Boolean>> a(String str) {
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = a(str, false).iterator();
        while (it.hasNext()) {
            String next = it.next();
            int a = a(next.charAt(0));
            boolean z = true;
            if (a != 1 && a != 2) {
                z = false;
            }
            arrayList.add(new Pair(next, Boolean.valueOf(z)));
        }
        return arrayList;
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x0036, code lost:
    
        if (r5 == (-1)) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x004f, code lost:
    
        r5 = r3 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0045, code lost:
    
        if (r5 == (-1)) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x004d, code lost:
    
        if (r5 == (-1)) goto L35;
     */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0073 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.util.ArrayList<java.lang.String> a(java.lang.String r13, boolean r14) {
        /*
            r12 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            boolean r1 = android.text.TextUtils.isEmpty(r13)
            if (r1 == 0) goto Lc
            return r0
        Lc:
            int r1 = r13.length()
            r2 = 0
            r3 = 0
        L12:
            if (r3 >= r1) goto L75
            r4 = -1
            r5 = -1
            r6 = -1
        L17:
            if (r3 >= r1) goto L5e
            char r7 = r13.charAt(r3)
            r8 = 128(0x80, float:1.8E-43)
            r9 = 2
            r10 = 1
            if (r7 >= r8) goto L4b
            int[] r8 = r12.a
            r7 = r8[r7]
            if (r7 != 0) goto L32
            if (r5 != r4) goto L2d
            r5 = r3
            r6 = 0
        L2d:
            if (r6 == r10) goto L5e
            if (r6 != r9) goto L5b
            goto L5e
        L32:
            if (r7 != r9) goto L42
            if (r14 == 0) goto L39
            if (r5 != r4) goto L5e
            goto L4f
        L39:
            if (r5 != r4) goto L3d
            r5 = r3
            r6 = 2
        L3d:
            if (r6 == 0) goto L5e
            if (r6 != r10) goto L5b
            goto L5e
        L42:
            r8 = 3
            if (r7 != r8) goto L48
            if (r5 != r4) goto L5e
            goto L4f
        L48:
            if (r5 != r4) goto L5e
            goto L5b
        L4b:
            if (r14 == 0) goto L52
            if (r5 != r4) goto L5e
        L4f:
            int r5 = r3 + 1
            goto L61
        L52:
            if (r5 != r4) goto L56
            r5 = r3
            r6 = 1
        L56:
            if (r6 == 0) goto L5e
            if (r6 != r9) goto L5b
            goto L5e
        L5b:
            int r3 = r3 + 1
            goto L17
        L5e:
            r11 = r5
            r5 = r3
            r3 = r11
        L61:
            if (r3 == r4) goto L73
            java.lang.String r4 = new java.lang.String
            java.lang.String r3 = r13.substring(r3, r5)
            java.lang.String r3 = r3.toLowerCase()
            r4.<init>(r3)
            r0.add(r4)
        L73:
            r3 = r5
            goto L12
        L75:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.netease.nimlib.search.b.a.a(java.lang.String, boolean):java.util.ArrayList");
    }

    public static a a() {
        return C0057a.a;
    }
}
