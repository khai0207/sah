package com.netease.yunxin.artemis.a;

import u.aly.df;

/* compiled from: Base64Utils.java */
/* loaded from: classes.dex */
public final class a {
    private static char[] a = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
    private static byte[] b = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, df.k, df.l, df.m, df.n, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1};

    /* JADX WARN: Code restructure failed: missing block: B:40:0x0085, code lost:
    
        if (r3 == (-1)) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0087, code lost:
    
        r1.append((char) (r3 | ((r7 & 3) << 6)));
        r3 = r6;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static byte[] a(java.lang.String r10) {
        /*
            r0 = 0
            java.lang.StringBuffer r1 = new java.lang.StringBuffer     // Catch: java.lang.Throwable -> L9c
            r1.<init>()     // Catch: java.lang.Throwable -> L9c
            java.lang.String r2 = "US-ASCII"
            byte[] r10 = r10.getBytes(r2)     // Catch: java.lang.Throwable -> L9c
            int r2 = r10.length     // Catch: java.lang.Throwable -> L9c
            r3 = 0
        Le:
            java.lang.String r4 = "iso8859-1"
            if (r3 >= r2) goto L93
        L12:
            byte[] r5 = com.netease.yunxin.artemis.a.a.b     // Catch: java.lang.Throwable -> L9c
            int r6 = r3 + 1
            r3 = r10[r3]     // Catch: java.lang.Throwable -> L9c
            r3 = r5[r3]     // Catch: java.lang.Throwable -> L9c
            r5 = -1
            if (r6 >= r2) goto L22
            if (r3 == r5) goto L20
            goto L22
        L20:
            r3 = r6
            goto L12
        L22:
            if (r3 == r5) goto L93
        L24:
            byte[] r7 = com.netease.yunxin.artemis.a.a.b     // Catch: java.lang.Throwable -> L9c
            int r8 = r6 + 1
            r6 = r10[r6]     // Catch: java.lang.Throwable -> L9c
            r6 = r7[r6]     // Catch: java.lang.Throwable -> L9c
            if (r8 >= r2) goto L33
            if (r6 == r5) goto L31
            goto L33
        L31:
            r6 = r8
            goto L24
        L33:
            if (r6 == r5) goto L93
            int r3 = r3 << 2
            r7 = r6 & 48
            int r7 = r7 >>> 4
            r3 = r3 | r7
            char r3 = (char) r3     // Catch: java.lang.Throwable -> L9c
            r1.append(r3)     // Catch: java.lang.Throwable -> L9c
        L40:
            int r3 = r8 + 1
            r7 = r10[r8]     // Catch: java.lang.Throwable -> L9c
            r8 = 61
            if (r7 != r8) goto L51
            java.lang.String r10 = r1.toString()     // Catch: java.lang.Throwable -> L9c
            byte[] r10 = r10.getBytes(r4)     // Catch: java.lang.Throwable -> L9c
            return r10
        L51:
            byte[] r9 = com.netease.yunxin.artemis.a.a.b     // Catch: java.lang.Throwable -> L9c
            r7 = r9[r7]     // Catch: java.lang.Throwable -> L9c
            if (r3 >= r2) goto L5c
            if (r7 == r5) goto L5a
            goto L5c
        L5a:
            r8 = r3
            goto L40
        L5c:
            if (r7 == r5) goto L93
            r6 = r6 & 15
            int r6 = r6 << 4
            r9 = r7 & 60
            int r9 = r9 >>> 2
            r6 = r6 | r9
            char r6 = (char) r6     // Catch: java.lang.Throwable -> L9c
            r1.append(r6)     // Catch: java.lang.Throwable -> L9c
        L6b:
            int r6 = r3 + 1
            r3 = r10[r3]     // Catch: java.lang.Throwable -> L9c
            if (r3 != r8) goto L7a
            java.lang.String r10 = r1.toString()     // Catch: java.lang.Throwable -> L9c
            byte[] r10 = r10.getBytes(r4)     // Catch: java.lang.Throwable -> L9c
            return r10
        L7a:
            byte[] r9 = com.netease.yunxin.artemis.a.a.b     // Catch: java.lang.Throwable -> L9c
            r3 = r9[r3]     // Catch: java.lang.Throwable -> L9c
            if (r6 >= r2) goto L85
            if (r3 == r5) goto L83
            goto L85
        L83:
            r3 = r6
            goto L6b
        L85:
            if (r3 == r5) goto L93
            r4 = r7 & 3
            int r4 = r4 << 6
            r3 = r3 | r4
            char r3 = (char) r3     // Catch: java.lang.Throwable -> L9c
            r1.append(r3)     // Catch: java.lang.Throwable -> L9c
            r3 = r6
            goto Le
        L93:
            java.lang.String r10 = r1.toString()     // Catch: java.lang.Throwable -> L9c
            byte[] r10 = r10.getBytes(r4)     // Catch: java.lang.Throwable -> L9c
            return r10
        L9c:
            byte[] r10 = new byte[r0]
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.netease.yunxin.artemis.a.a.a(java.lang.String):byte[]");
    }
}
