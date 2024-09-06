package com.netease.nimlib.net.a.a;

import android.os.SystemClock;
import android.text.TextUtils;
import com.alipay.sdk.app.OpenAuthTask;
import java.util.Date;
import java.util.Map;

/* compiled from: HTTPDownload.java */
/* loaded from: classes.dex */
public class c {
    private boolean a;

    private c() {
    }

    public static final c a() {
        return new c();
    }

    public final void b() {
        this.a = true;
    }

    /* compiled from: HTTPDownload.java */
    /* loaded from: classes.dex */
    public static class a {
        private final String a;
        private final String b;
        private final com.netease.nimlib.net.a.a.a c;
        private final long d;
        private final b e;
        private final String f;
        private final boolean g;
        private final Map<String, String> h;
        private final int i;
        private final int j;
        private final com.netease.nimlib.n.b.g k;

        /* compiled from: HTTPDownload.java */
        /* loaded from: classes.dex */
        public enum b {
            UNKNOWN,
            IMAGE,
            AUDIO,
            VIDEO
        }

        private a(String str, String str2, String str3, boolean z, long j, b bVar, Map<String, String> map, int i, int i2, com.netease.nimlib.net.a.a.a aVar, com.netease.nimlib.n.b.g gVar) {
            this.a = str;
            this.b = str3;
            this.d = j;
            this.e = bVar;
            this.f = str2;
            this.g = z;
            this.h = map;
            this.i = i;
            this.j = i2;
            this.c = aVar;
            this.k = gVar;
        }

        /* compiled from: HTTPDownload.java */
        /* renamed from: com.netease.nimlib.net.a.a.c$a$a, reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public static class C0045a {
            private final String a;
            private final String b;
            private String f;
            private Map<String, String> h;
            private com.netease.nimlib.net.a.a.a c = null;
            private long d = -1;
            private b e = b.UNKNOWN;
            private boolean g = true;
            private com.netease.nimlib.n.b.g i = com.netease.nimlib.n.b.g.CDN;
            private int j = 3;
            private int k = OpenAuthTask.Duplex;

            public C0045a(String str, String str2) {
                this.a = str;
                this.b = str2;
            }

            public C0045a a(com.netease.nimlib.net.a.a.a aVar) {
                this.c = aVar;
                return this;
            }

            public C0045a a(long j) {
                this.d = j;
                return this;
            }

            public C0045a a(String str) {
                this.f = str;
                return this;
            }

            public C0045a a(boolean z) {
                this.g = z;
                return this;
            }

            public C0045a a(Map<String, String> map) {
                this.h = map;
                return this;
            }

            public C0045a a(int i) {
                this.j = i;
                return this;
            }

            public C0045a b(int i) {
                this.k = i;
                return this;
            }

            public C0045a a(com.netease.nimlib.n.b.g gVar) {
                this.i = gVar;
                return this;
            }

            public a a() {
                return new a(this.a, this.f, this.b, this.g, this.d, this.e, this.h, this.j, this.k, this.c, this.i);
            }
        }
    }

    public final boolean a(a aVar) {
        return a(aVar.a, aVar.f, aVar.b, aVar.c, aVar.d, aVar.e, aVar.g, aVar.h, aVar.i, aVar.j, aVar.k);
    }

    private boolean a(String str, String str2, String str3, com.netease.nimlib.net.a.a.a aVar, long j, a.b bVar, boolean z, Map<String, String> map, int i, int i2, com.netease.nimlib.n.b.g gVar) {
        String str4;
        boolean z2;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str3)) {
            if (aVar != null) {
                aVar.a(str, "url or file path is empty");
            }
            return false;
        }
        if (z) {
            String a2 = com.netease.nimlib.net.a.c.d.a(str, str2);
            z2 = !a2.equals(str);
            str4 = a2;
        } else {
            str4 = str;
            z2 = false;
        }
        String str5 = !TextUtils.isEmpty(str2) ? str2 : str;
        String str6 = str5;
        if (a(str4, str5, str3, aVar, j, bVar, map, i, i2, gVar)) {
            return true;
        }
        if (aVar == null) {
            return false;
        }
        if (g.c(str)) {
            aVar.b(str6, "file is expire");
            return false;
        }
        aVar.a(str6, "");
        if (!z2) {
            return false;
        }
        com.netease.nimlib.net.a.b.a.a.a().c();
        return false;
    }

    private boolean a(String str, String str2, String str3, com.netease.nimlib.net.a.a.a aVar, long j, a.b bVar, Map<String, String> map, int i, int i2, com.netease.nimlib.n.b.g gVar) {
        for (int i3 = 0; i3 < i + 1; i3++) {
            if (a(str2, str, str3, aVar, j, "", map, gVar)) {
                return true;
            }
            SystemClock.sleep(i2);
            com.netease.nimlib.log.b.q("HTTPDownload USUAL RETRY " + i3);
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:184:0x0406, code lost:
    
        r6.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:185:0x0409, code lost:
    
        com.netease.nimlib.n.e.a(r25);
        com.netease.nimlib.n.n.a().b(r10, com.netease.nimlib.n.b.h.kCanceled.a());
     */
    /* JADX WARN: Code restructure failed: missing block: B:186:0x0419, code lost:
    
        r0 = c();
        com.netease.nimlib.log.b.r("HTTPDownload STAT END " + r0);
        com.netease.nimlib.log.b.r("HTTPDownload STAT COST " + (r0 - r14));
        com.netease.nimlib.net.a.c.b.b(r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:187:0x0445, code lost:
    
        if (r8 == 0) goto L358;
     */
    /* JADX WARN: Code restructure failed: missing block: B:188:0x0447, code lost:
    
        r8.disconnect();
     */
    /* JADX WARN: Code restructure failed: missing block: B:189:0x044a, code lost:
    
        return true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:190:?, code lost:
    
        return true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:198:0x04a7, code lost:
    
        r9 = r5;
        r19 = r6;
        r18 = r11;
        r11 = r10;
        r19.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:199:0x04b2, code lost:
    
        if (r9 == null) goto L199;
     */
    /* JADX WARN: Code restructure failed: missing block: B:200:0x04b7, code lost:
    
        com.netease.nimlib.n.e.a(r25);
        com.netease.nimlib.n.n.a().b(r11, com.netease.nimlib.n.b.h.kSucceed.a());
     */
    /* JADX WARN: Code restructure failed: missing block: B:201:0x04c7, code lost:
    
        r0 = c();
        com.netease.nimlib.log.b.r("HTTPDownload STAT END " + r0);
        com.netease.nimlib.log.b.r("HTTPDownload STAT COST " + (r0 - r14));
        com.netease.nimlib.net.a.c.b.b(r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:202:0x04f3, code lost:
    
        if (r8 == 0) goto L203;
     */
    /* JADX WARN: Code restructure failed: missing block: B:203:0x04f5, code lost:
    
        r8.disconnect();
     */
    /* JADX WARN: Code restructure failed: missing block: B:204:0x04f8, code lost:
    
        return true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:206:0x04f9, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:207:0x0516, code lost:
    
        r10 = r31;
        r19 = null;
        r4 = r20;
        r16 = r7;
        r8 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:209:0x04b4, code lost:
    
        r9.b(r11);
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:140:0x03ac  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x03dd A[Catch: Exception -> 0x03b4, all -> 0x03cf, TRY_ENTER, TRY_LEAVE, TryCatch #13 {all -> 0x03cf, blocks: (B:222:0x03b0, B:143:0x03c2, B:147:0x03dd, B:150:0x03e2, B:185:0x0409, B:209:0x04b4, B:200:0x04b7), top: B:221:0x03b0 }] */
    /* JADX WARN: Removed duplicated region for block: B:156:0x03fa A[Catch: all -> 0x04ff, Exception -> 0x0506, TRY_LEAVE, TryCatch #21 {Exception -> 0x0506, blocks: (B:154:0x03f4, B:156:0x03fa), top: B:153:0x03f4 }] */
    /* JADX WARN: Removed duplicated region for block: B:197:0x04a7 A[EDGE_INSN: B:197:0x04a7->B:198:0x04a7 BREAK  A[LOOP:1: B:152:0x03ef->B:168:0x048d], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:221:0x03b0 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:251:0x00fa A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x01db  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0650  */
    /* JADX WARN: Removed duplicated region for block: B:77:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0630 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:89:0x06a5  */
    /* JADX WARN: Removed duplicated region for block: B:91:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0687 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r0v139, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r28v0, types: [com.netease.nimlib.net.a.a.a] */
    /* JADX WARN: Type inference failed for: r30v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v14, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v15, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v17, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v7, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r6v1, types: [com.netease.nimlib.n.n] */
    /* JADX WARN: Type inference failed for: r8v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v13 */
    /* JADX WARN: Type inference failed for: r8v16 */
    /* JADX WARN: Type inference failed for: r8v19 */
    /* JADX WARN: Type inference failed for: r8v20, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r8v23 */
    /* JADX WARN: Type inference failed for: r8v26 */
    /* JADX WARN: Type inference failed for: r8v28 */
    /* JADX WARN: Type inference failed for: r8v29, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r8v33 */
    /* JADX WARN: Type inference failed for: r8v38 */
    /* JADX WARN: Type inference failed for: r8v41, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r8v42 */
    /* JADX WARN: Type inference failed for: r8v43 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean a(java.lang.String r25, java.lang.String r26, java.lang.String r27, com.netease.nimlib.net.a.a.a r28, long r29, java.lang.String r31, java.util.Map<java.lang.String, java.lang.String> r32, com.netease.nimlib.n.b.g r33) {
        /*
            Method dump skipped, instructions count: 1707
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.netease.nimlib.net.a.a.c.a(java.lang.String, java.lang.String, java.lang.String, com.netease.nimlib.net.a.a.a, long, java.lang.String, java.util.Map, com.netease.nimlib.n.b.g):boolean");
    }

    private static final long c() {
        return new Date().getTime();
    }
}
