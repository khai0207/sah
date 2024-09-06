package com.netease.nimlib.log.d;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

/* compiled from: XLogMergeUtil.java */
/* loaded from: classes.dex */
public class a {
    private static boolean a = false;
    private static boolean b = false;
    private static int c;
    private static b d;

    /* JADX WARN: Removed duplicated region for block: B:56:0x00b2 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:63:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00a8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x009e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:84:0x0094 -> B:20:0x0097). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void a(java.lang.String r5, java.lang.String r6, java.lang.String r7) {
        /*
            Method dump skipped, instructions count: 189
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.netease.nimlib.log.d.a.a(java.lang.String, java.lang.String, java.lang.String):void");
    }

    private static void a() {
        d = null;
        a = false;
        b = false;
        c = 0;
    }

    private static b a(C0042a c0042a, C0042a c0042a2) {
        if (b) {
            if (!a) {
                c0042a = c0042a2;
            }
            return c0042a.a();
        }
        if (d == null) {
            b a2 = c0042a.a();
            b a3 = c0042a2.a();
            boolean z = a2.compareTo(a3) <= 0;
            a = z;
            d = z ? a3 : a2;
            return a ? a2 : a3;
        }
        b a4 = a ? c0042a.a() : c0042a2.a();
        if (a4 == null) {
            b = true;
            b bVar = d;
            a = !a;
            return bVar;
        }
        if (c >= 25 || a4.compareTo(d) > 0) {
            b bVar2 = d;
            d = a4;
            a = !a;
            c = 0;
            return bVar2;
        }
        c++;
        return a4;
    }

    /* compiled from: XLogMergeUtil.java */
    /* renamed from: com.netease.nimlib.log.d.a$a, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    private static class C0042a {
        private InputStream f;
        private int a = 8192;
        private byte[] b = new byte[8192];
        private int d = -1;
        private int e = 0;
        private List<Integer> c = new LinkedList();

        public C0042a(InputStream inputStream) {
            this.f = inputStream;
        }

        public b a() {
            while (b() > 0) {
                try {
                    int intValue = this.c.remove(0).intValue();
                    int i = intValue - this.d;
                    if (i >= 18) {
                        if (b(this.d, i)) {
                            this.d = intValue;
                        } else {
                            int i2 = intValue + 1;
                            if (i2 == this.a || this.b[i2] == 48 || this.b[i2] == 49 || this.b[i2] == 0) {
                                b bVar = new b(this.b, this.d + 1, i);
                                this.d = intValue;
                                return bVar;
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
            return null;
        }

        private int b() throws IOException {
            while (this.c.size() == 0) {
                int c = c();
                if (c < 0) {
                    return -1;
                }
                int read = this.f.read(this.b, c, this.a - c);
                this.e = read;
                if (read <= 0 || a(c, read)) {
                    return -1;
                }
                a(c);
                this.d = -1;
            }
            return this.e;
        }

        private void a(int i) {
            int i2 = this.e + i;
            while (i < i2) {
                if (this.b[i] == 10) {
                    this.c.add(Integer.valueOf(i));
                    i += 54;
                }
                i++;
            }
        }

        private boolean a(int i, int i2) {
            byte[] bArr = this.b;
            return bArr[i] == 0 && bArr[(i2 / 2) + i] == 0 && bArr[(this.e + i) - 1] == 0;
        }

        private boolean b(int i, int i2) {
            byte[] bArr = this.b;
            return bArr[i + 1] == 0 || bArr[(i2 / 2) + i] == 0 || bArr[(i + i2) - 1] == 0;
        }

        private int c() {
            if (this.e <= 0) {
                return 0;
            }
            int i = this.a;
            int i2 = this.d;
            int i3 = (i - i2) - 1;
            if (i2 == -1) {
                if (i >= 131072) {
                    return -1;
                }
                this.a = i << 1;
            }
            byte[] bArr = new byte[this.a];
            System.arraycopy(this.b, this.d + 1, bArr, 0, i3);
            this.b = bArr;
            return i3;
        }
    }

    /* compiled from: XLogMergeUtil.java */
    /* loaded from: classes.dex */
    private static class b implements Comparable<b> {
        private byte[] a;
        private int b;
        private int c;
        private int d;

        public byte[] a() {
            return this.a;
        }

        public int b() {
            return this.b;
        }

        public int c() {
            return this.c;
        }

        public int d() {
            return this.d;
        }

        public b(byte[] bArr, int i, int i2) {
            this.d = 18;
            this.a = bArr;
            this.b = i2;
            this.c = i;
            this.d = Math.min(18, i2);
        }

        @Override // java.lang.Comparable
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compareTo(b bVar) {
            if (bVar == null) {
                return 1;
            }
            if (d() != bVar.d()) {
                return this.d - bVar.d();
            }
            int c = bVar.c();
            for (int i = 0; i < this.d; i++) {
                byte b = this.a[this.c + i];
                byte b2 = bVar.a()[i + c];
                if (b != b2) {
                    return b - b2;
                }
            }
            return 0;
        }
    }
}
