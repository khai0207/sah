package com.umeng.analytics;

import android.content.Context;
import u.aly.ab;
import u.aly.am;
import u.aly.bt;
import u.aly.t;

/* loaded from: classes.dex */
public class ReportPolicy {
    public static final int BATCH_AT_LAUNCH = 1;
    public static final int BATCH_BY_INTERVAL = 6;
    public static final int DAILY = 4;
    public static final int REALTIME = 0;
    public static final int SMART_POLICY = 8;
    public static final int WIFIONLY = 5;
    static final int a = 2;
    static final int b = 3;

    /* loaded from: classes.dex */
    public static class d extends i {
        @Override // com.umeng.analytics.ReportPolicy.i
        public boolean a(boolean z) {
            return z;
        }
    }

    /* loaded from: classes.dex */
    public static class h extends i {
        @Override // com.umeng.analytics.ReportPolicy.i
        public boolean a(boolean z) {
            return true;
        }
    }

    /* loaded from: classes.dex */
    public static class i {
        public boolean a() {
            return true;
        }

        public boolean a(boolean z) {
            return true;
        }
    }

    public static boolean a(int i2) {
        switch (i2) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 8:
                return true;
            case 7:
            default:
                return false;
        }
    }

    /* loaded from: classes.dex */
    public static class e extends i {
        private static long a = 90000;
        private static long b = 86400000;
        private long c;
        private ab d;

        public e(ab abVar, long j) {
            this.d = abVar;
            a(j);
        }

        @Override // com.umeng.analytics.ReportPolicy.i
        public boolean a(boolean z) {
            return System.currentTimeMillis() - this.d.c >= this.c;
        }

        public void a(long j) {
            if (j >= a && j <= b) {
                this.c = j;
            } else {
                this.c = a;
            }
        }

        public long b() {
            return this.c;
        }

        public static boolean a(int i) {
            return ((long) i) >= a;
        }
    }

    /* loaded from: classes.dex */
    public static class g extends i {
        private long a = com.umeng.analytics.a.h;
        private ab b;

        public g(ab abVar) {
            this.b = abVar;
        }

        @Override // com.umeng.analytics.ReportPolicy.i
        public boolean a(boolean z) {
            return System.currentTimeMillis() - this.b.c >= this.a;
        }
    }

    /* loaded from: classes.dex */
    public static class j extends i {
        private Context a;

        public j(Context context) {
            this.a = null;
            this.a = context;
        }

        @Override // com.umeng.analytics.ReportPolicy.i
        public boolean a(boolean z) {
            return bt.l(this.a);
        }
    }

    /* loaded from: classes.dex */
    public static class b extends i {
        private am a;
        private ab b;

        public b(ab abVar, am amVar) {
            this.b = abVar;
            this.a = amVar;
        }

        @Override // com.umeng.analytics.ReportPolicy.i
        public boolean a(boolean z) {
            return System.currentTimeMillis() - this.b.c >= this.a.a();
        }

        @Override // com.umeng.analytics.ReportPolicy.i
        public boolean a() {
            return this.a.c();
        }
    }

    /* loaded from: classes.dex */
    public static class c extends i {
        private long a;
        private long b;

        public c(int i) {
            this.b = 0L;
            this.a = i;
            this.b = System.currentTimeMillis();
        }

        @Override // com.umeng.analytics.ReportPolicy.i
        public boolean a(boolean z) {
            return System.currentTimeMillis() - this.b >= this.a;
        }

        @Override // com.umeng.analytics.ReportPolicy.i
        public boolean a() {
            return System.currentTimeMillis() - this.b < this.a;
        }
    }

    /* loaded from: classes.dex */
    public static class f extends i {
        private final int a;
        private t b;

        public f(t tVar, int i) {
            this.a = i;
            this.b = tVar;
        }

        @Override // com.umeng.analytics.ReportPolicy.i
        public boolean a(boolean z) {
            return this.b.b() > this.a;
        }
    }

    /* loaded from: classes.dex */
    public static class k extends i {
        private final long a = 10800000;
        private ab b;

        public k(ab abVar) {
            this.b = abVar;
        }

        @Override // com.umeng.analytics.ReportPolicy.i
        public boolean a(boolean z) {
            return System.currentTimeMillis() - this.b.c >= 10800000;
        }
    }

    /* loaded from: classes.dex */
    public static class a extends i {
        private final long a = 15000;
        private ab b;

        public a(ab abVar) {
            this.b = abVar;
        }

        @Override // com.umeng.analytics.ReportPolicy.i
        public boolean a(boolean z) {
            return System.currentTimeMillis() - this.b.c >= 15000;
        }
    }
}
