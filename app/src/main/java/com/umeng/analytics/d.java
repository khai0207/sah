package com.umeng.analytics;

import android.content.Context;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;
import u.aly.aa;
import u.aly.ae;
import u.aly.ah;
import u.aly.bv;
import u.aly.m;
import u.aly.o;
import u.aly.p;
import u.aly.w;
import u.aly.y;

/* compiled from: InternalAgent.java */
/* loaded from: classes.dex */
public class d implements w {
    private c b;
    private p f;
    private m g;
    private Context a = null;
    private o c = new o();
    private ae d = new ae();
    private aa e = new aa();
    private boolean h = false;

    d() {
        this.c.a(this);
    }

    private void e(Context context) {
        if (this.h) {
            return;
        }
        Context applicationContext = context.getApplicationContext();
        this.a = applicationContext;
        this.f = new p(applicationContext);
        this.g = m.a(this.a);
        this.h = true;
    }

    void a(String str) {
        if (AnalyticsConfig.ACTIVITY_DURATION_OPEN) {
            return;
        }
        try {
            this.d.a(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void b(String str) {
        if (AnalyticsConfig.ACTIVITY_DURATION_OPEN) {
            return;
        }
        try {
            this.d.b(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a(c cVar) {
        this.b = cVar;
    }

    public void a(int i) {
        AnalyticsConfig.mVerticalType = i;
    }

    public void a(String str, String str2) {
        AnalyticsConfig.mWrapperType = str;
        AnalyticsConfig.mWrapperVersion = str2;
    }

    void a(Context context) {
        if (context == null) {
            bv.e("unexpected null context in onResume");
            return;
        }
        if (AnalyticsConfig.ACTIVITY_DURATION_OPEN) {
            this.d.a(context.getClass().getName());
        }
        try {
            if (!this.h) {
                e(context);
            }
            f.a(new g() { // from class: com.umeng.analytics.d.1
                final /* synthetic */ Context a;

                AnonymousClass1(Context context2) {
                    r2 = context2;
                }

                @Override // com.umeng.analytics.g
                public void a() {
                    d.this.f(r2.getApplicationContext());
                }
            });
        } catch (Exception e) {
            bv.e("Exception occurred in Mobclick.onResume(). ", e);
        }
    }

    /* compiled from: InternalAgent.java */
    /* renamed from: com.umeng.analytics.d$1 */
    /* loaded from: classes.dex */
    class AnonymousClass1 extends g {
        final /* synthetic */ Context a;

        AnonymousClass1(Context context2) {
            r2 = context2;
        }

        @Override // com.umeng.analytics.g
        public void a() {
            d.this.f(r2.getApplicationContext());
        }
    }

    void b(Context context) {
        if (context == null) {
            bv.e("unexpected null context in onPause");
            return;
        }
        if (AnalyticsConfig.ACTIVITY_DURATION_OPEN) {
            this.d.b(context.getClass().getName());
        }
        try {
            if (!this.h) {
                e(context);
            }
            f.a(new g() { // from class: com.umeng.analytics.d.2
                final /* synthetic */ Context a;

                AnonymousClass2(Context context2) {
                    r2 = context2;
                }

                @Override // com.umeng.analytics.g
                public void a() {
                    d.this.g(r2.getApplicationContext());
                }
            });
        } catch (Exception e) {
            bv.e("Exception occurred in Mobclick.onRause(). ", e);
        }
    }

    /* compiled from: InternalAgent.java */
    /* renamed from: com.umeng.analytics.d$2 */
    /* loaded from: classes.dex */
    class AnonymousClass2 extends g {
        final /* synthetic */ Context a;

        AnonymousClass2(Context context2) {
            r2 = context2;
        }

        @Override // com.umeng.analytics.g
        public void a() {
            d.this.g(r2.getApplicationContext());
        }
    }

    public aa a() {
        return this.e;
    }

    public void a(Context context, String str, HashMap<String, Object> hashMap) {
        try {
            if (!this.h) {
                e(context);
            }
            this.f.a(str, hashMap);
        } catch (Exception e) {
            bv.e(e);
        }
    }

    void a(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (context == null) {
            bv.e("unexpected null context in reportError");
            return;
        }
        try {
            if (!this.h) {
                e(context);
            }
            this.g.a(new ah(str).a(false));
        } catch (Exception e) {
            bv.e(e);
        }
    }

    void a(Context context, Throwable th) {
        if (context == null || th == null) {
            return;
        }
        try {
            if (!this.h) {
                e(context);
            }
            this.g.a(new ah(th).a(false));
        } catch (Exception e) {
            bv.e(e);
        }
    }

    public void f(Context context) {
        this.e.c(context);
        c cVar = this.b;
        if (cVar != null) {
            cVar.a();
        }
    }

    public void g(Context context) {
        this.e.d(context);
        this.d.a(context);
        c cVar = this.b;
        if (cVar != null) {
            cVar.b();
        }
        this.g.b();
    }

    void c(Context context) {
        try {
            if (!this.h) {
                e(context);
            }
            this.g.a();
        } catch (Exception e) {
            bv.e(e);
        }
    }

    public void a(Context context, String str, String str2, long j, int i) {
        try {
            if (!this.h) {
                e(context);
            }
            this.f.a(str, str2, j, i);
        } catch (Exception e) {
            bv.e(e);
        }
    }

    void a(Context context, String str, Map<String, Object> map, long j) {
        try {
            if (!this.h) {
                e(context);
            }
            this.f.a(str, map, j);
        } catch (Exception e) {
            bv.e(e);
        }
    }

    void a(Context context, String str, String str2) {
        try {
            if (!this.h) {
                e(context);
            }
            f.a(new g() { // from class: com.umeng.analytics.d.3
                final /* synthetic */ String a;
                final /* synthetic */ String b;

                AnonymousClass3(String str3, String str22) {
                    r2 = str3;
                    r3 = str22;
                }

                @Override // com.umeng.analytics.g
                public void a() {
                    d.this.f.a(r2, r3);
                }
            });
        } catch (Exception e) {
            bv.e(e);
        }
    }

    /* compiled from: InternalAgent.java */
    /* renamed from: com.umeng.analytics.d$3 */
    /* loaded from: classes.dex */
    class AnonymousClass3 extends g {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        AnonymousClass3(String str3, String str22) {
            r2 = str3;
            r3 = str22;
        }

        @Override // com.umeng.analytics.g
        public void a() {
            d.this.f.a(r2, r3);
        }
    }

    /* compiled from: InternalAgent.java */
    /* renamed from: com.umeng.analytics.d$4 */
    /* loaded from: classes.dex */
    class AnonymousClass4 extends g {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        AnonymousClass4(String str, String str2) {
            r2 = str;
            r3 = str2;
        }

        @Override // com.umeng.analytics.g
        public void a() {
            d.this.f.b(r2, r3);
        }
    }

    void b(Context context, String str, String str2) {
        try {
            f.a(new g() { // from class: com.umeng.analytics.d.4
                final /* synthetic */ String a;
                final /* synthetic */ String b;

                AnonymousClass4(String str3, String str22) {
                    r2 = str3;
                    r3 = str22;
                }

                @Override // com.umeng.analytics.g
                public void a() {
                    d.this.f.b(r2, r3);
                }
            });
        } catch (Exception e) {
            bv.e(e);
        }
    }

    void a(Context context, String str, HashMap<String, Object> hashMap, String str2) {
        try {
            if (!this.h) {
                e(context);
            }
            f.a(new g() { // from class: com.umeng.analytics.d.5
                final /* synthetic */ String a;
                final /* synthetic */ HashMap b;
                final /* synthetic */ String c;

                AnonymousClass5(String str3, HashMap hashMap2, String str22) {
                    r2 = str3;
                    r3 = hashMap2;
                    r4 = str22;
                }

                @Override // com.umeng.analytics.g
                public void a() {
                    d.this.f.a(r2, r3, r4);
                }
            });
        } catch (Exception e) {
            bv.e(e);
        }
    }

    /* compiled from: InternalAgent.java */
    /* renamed from: com.umeng.analytics.d$5 */
    /* loaded from: classes.dex */
    class AnonymousClass5 extends g {
        final /* synthetic */ String a;
        final /* synthetic */ HashMap b;
        final /* synthetic */ String c;

        AnonymousClass5(String str3, HashMap hashMap2, String str22) {
            r2 = str3;
            r3 = hashMap2;
            r4 = str22;
        }

        @Override // com.umeng.analytics.g
        public void a() {
            d.this.f.a(r2, r3, r4);
        }
    }

    /* compiled from: InternalAgent.java */
    /* renamed from: com.umeng.analytics.d$6 */
    /* loaded from: classes.dex */
    class AnonymousClass6 extends g {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        AnonymousClass6(String str, String str2) {
            r2 = str;
            r3 = str2;
        }

        @Override // com.umeng.analytics.g
        public void a() {
            d.this.f.c(r2, r3);
        }
    }

    void c(Context context, String str, String str2) {
        try {
            f.a(new g() { // from class: com.umeng.analytics.d.6
                final /* synthetic */ String a;
                final /* synthetic */ String b;

                AnonymousClass6(String str3, String str22) {
                    r2 = str3;
                    r3 = str22;
                }

                @Override // com.umeng.analytics.g
                public void a() {
                    d.this.f.c(r2, r3);
                }
            });
        } catch (Exception e) {
            bv.e(e);
        }
    }

    void d(Context context) {
        try {
            this.d.a();
            g(context);
            y.a(context).edit().commit();
            f.a();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // u.aly.w
    public void a(Throwable th) {
        try {
            this.d.a();
            if (this.a != null) {
                if (th != null && this.g != null) {
                    this.g.b(new ah(th));
                }
                g(this.a);
                y.a(this.a).edit().commit();
            }
            f.a();
        } catch (Exception e) {
            bv.e("Exception in onAppCrash", e);
        }
    }

    /* compiled from: InternalAgent.java */
    /* renamed from: com.umeng.analytics.d$7 */
    /* loaded from: classes.dex */
    class AnonymousClass7 extends g {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        AnonymousClass7(String str, String str2) {
            r2 = str;
            r3 = str2;
        }

        @Override // com.umeng.analytics.g
        public void a() {
            String[] a = e.a(d.this.a);
            if (a != null && r2.equals(a[0]) && r3.equals(a[1])) {
                return;
            }
            boolean e = d.this.a().e(d.this.a);
            m.a(d.this.a).c();
            if (e) {
                d.this.a().f(d.this.a);
            }
            e.a(d.this.a, r2, r3);
        }
    }

    void b(String str, String str2) {
        try {
            f.a(new g() { // from class: com.umeng.analytics.d.7
                final /* synthetic */ String a;
                final /* synthetic */ String b;

                AnonymousClass7(String str3, String str22) {
                    r2 = str3;
                    r3 = str22;
                }

                @Override // com.umeng.analytics.g
                public void a() {
                    String[] a = e.a(d.this.a);
                    if (a != null && r2.equals(a[0]) && r3.equals(a[1])) {
                        return;
                    }
                    boolean e = d.this.a().e(d.this.a);
                    m.a(d.this.a).c();
                    if (e) {
                        d.this.a().f(d.this.a);
                    }
                    e.a(d.this.a, r2, r3);
                }
            });
        } catch (Exception e) {
            bv.e(" Excepthon  in  onProfileSignIn", e);
        }
    }

    /* compiled from: InternalAgent.java */
    /* renamed from: com.umeng.analytics.d$8 */
    /* loaded from: classes.dex */
    class AnonymousClass8 extends g {
        AnonymousClass8() {
        }

        @Override // com.umeng.analytics.g
        public void a() {
            String[] a = e.a(d.this.a);
            if (a == null || TextUtils.isEmpty(a[0]) || TextUtils.isEmpty(a[1])) {
                return;
            }
            boolean e = d.this.a().e(d.this.a);
            m.a(d.this.a).c();
            if (e) {
                d.this.a().f(d.this.a);
            }
            e.b(d.this.a);
        }
    }

    void b() {
        try {
            f.a(new g() { // from class: com.umeng.analytics.d.8
                AnonymousClass8() {
                }

                @Override // com.umeng.analytics.g
                public void a() {
                    String[] a = e.a(d.this.a);
                    if (a == null || TextUtils.isEmpty(a[0]) || TextUtils.isEmpty(a[1])) {
                        return;
                    }
                    boolean e = d.this.a().e(d.this.a);
                    m.a(d.this.a).c();
                    if (e) {
                        d.this.a().f(d.this.a);
                    }
                    e.b(d.this.a);
                }
            });
        } catch (Exception e) {
            bv.e(" Excepthon  in  onProfileSignOff", e);
        }
    }
}
