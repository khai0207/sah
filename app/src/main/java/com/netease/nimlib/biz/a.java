package com.netease.nimlib.biz;

import android.content.Context;
import com.netease.nimlib.o.v;
import com.netease.nimlib.o.y;
import com.netease.nimlib.sdk.StatusCode;
import com.netease.nimlib.session.MsgDBHelper;

/* compiled from: AppGrayConfigHelper.java */
/* loaded from: classes.dex */
public class a {
    private static final com.netease.nimlib.biz.g.a a = new com.netease.nimlib.biz.g.a(2, 30);
    private static String b = "";

    public static String a(Context context) {
        return "";
    }

    public static void a() {
        if (com.netease.nimlib.c.D() || com.netease.nimlib.c.t()) {
            long e = l.e();
            long a2 = y.a();
            com.netease.nimlib.log.b.c("AGCHelper", "app gray config expiration = " + e + "，currentTimeMillis = " + a2);
            if (e < a2) {
                String a3 = v.a();
                String b2 = v.b();
                com.netease.nimlib.biz.d.d.c cVar = new com.netease.nimlib.biz.d.d.c(a3, b2);
                com.netease.nimlib.log.b.d("AGCHelper", "[SID 6,CID 27] app gray out ttl,phoneModel = " + a3 + ", versionRelease = " + b2);
                i.a().a(new com.netease.nimlib.biz.g.b(cVar, a) { // from class: com.netease.nimlib.biz.a.1
                    AnonymousClass1(com.netease.nimlib.biz.d.a cVar2, com.netease.nimlib.biz.g.a aVar) {
                        super(cVar2, aVar);
                    }

                    @Override // com.netease.nimlib.biz.g.b, com.netease.nimlib.biz.g.c
                    public void a(com.netease.nimlib.biz.e.a aVar) {
                        if (aVar.n()) {
                            com.netease.nimlib.biz.e.d.b bVar = (com.netease.nimlib.biz.e.d.b) aVar;
                            l.b(y.a() + (bVar.c() * 1000));
                            l.b(bVar.a());
                            c.a(bVar.b());
                            com.netease.nimlib.log.b.d("AGCHelper", "[SID 6,CID 27] request success");
                            com.netease.nimlib.log.b.c("AGCHelper", "[SID 6,CID 27] ttl = " + bVar.c());
                            com.netease.nimlib.log.b.c("AGCHelper", "[SID 6,CID 27] mixStoreEnable = " + bVar.a());
                            com.netease.nimlib.log.b.c("AGCHelper", "[SID 6,CID 27] growDeviceEnable = " + bVar.d());
                            com.netease.nimlib.log.b.c("AGCHelper", "[SID 6,CID 27] abtestIntervalFlag = " + bVar.b());
                            if (com.netease.nimlib.c.D() && bVar.a()) {
                                com.netease.nimlib.log.b.d("AGCHelper", "[SID 6,CID 27] app gray mix store enable");
                                if (!com.netease.nimlib.biz.b.e.d().b()) {
                                    com.netease.nimlib.biz.b.e.d().a(new com.netease.nimlib.c.a<Boolean>() { // from class: com.netease.nimlib.biz.a.1.1
                                        C00251() {
                                        }

                                        @Override // com.netease.nimlib.c.a
                                        /* renamed from: a */
                                        public void onCallback(Boolean bool) {
                                            com.netease.nimlib.log.b.d("AGCHelper", "HighAvailableManager init result = " + bool);
                                        }
                                    });
                                } else {
                                    com.netease.nimlib.biz.b.e.d().c();
                                }
                            } else {
                                com.netease.nimlib.log.b.d("AGCHelper", "[SID 6,CID 27] app gray mix store disable");
                            }
                            if (a.b()) {
                                String unused = a.b = a.a(com.netease.nimlib.c.e());
                                return;
                            }
                            l.a("");
                            c.a("");
                            String unused2 = a.b = "";
                            return;
                        }
                        com.netease.nimlib.log.b.d("AGCHelper", "[SID 6,CID 27] request failed, error code = " + ((int) aVar.r()));
                    }

                    /* compiled from: AppGrayConfigHelper.java */
                    /* renamed from: com.netease.nimlib.biz.a$1$1 */
                    /* loaded from: classes.dex */
                    class C00251 implements com.netease.nimlib.c.a<Boolean> {
                        C00251() {
                        }

                        @Override // com.netease.nimlib.c.a
                        /* renamed from: a */
                        public void onCallback(Boolean bool) {
                            com.netease.nimlib.log.b.d("AGCHelper", "HighAvailableManager init result = " + bool);
                        }
                    }
                });
                return;
            }
            com.netease.nimlib.log.b.d("AGCHelper", "[SID 6,CID 27] app gray in ttl");
            if (com.netease.nimlib.biz.b.e.d().a()) {
                com.netease.nimlib.log.b.d("AGCHelper", "[SID 6,CID 27] app gray mix store enable");
                if (!com.netease.nimlib.biz.b.e.d().b()) {
                    com.netease.nimlib.biz.b.e.d().a(new com.netease.nimlib.c.a<Boolean>() { // from class: com.netease.nimlib.biz.a.2
                        AnonymousClass2() {
                        }

                        @Override // com.netease.nimlib.c.a
                        /* renamed from: a */
                        public void onCallback(Boolean bool) {
                            com.netease.nimlib.log.b.d("AGCHelper", "HighAvailableManager init result = " + bool);
                        }
                    });
                } else {
                    com.netease.nimlib.biz.b.e.d().c();
                }
            } else {
                com.netease.nimlib.log.b.d("AGCHelper", "[SID 6,CID 27] app gray mix store disable");
            }
            if (!b()) {
                l.a("");
                c.a("");
                b = "";
                return;
            }
            b = a(com.netease.nimlib.c.e());
        }
    }

    /* compiled from: AppGrayConfigHelper.java */
    /* renamed from: com.netease.nimlib.biz.a$1 */
    /* loaded from: classes.dex */
    static class AnonymousClass1 extends com.netease.nimlib.biz.g.b {
        AnonymousClass1(com.netease.nimlib.biz.d.a cVar2, com.netease.nimlib.biz.g.a aVar) {
            super(cVar2, aVar);
        }

        @Override // com.netease.nimlib.biz.g.b, com.netease.nimlib.biz.g.c
        public void a(com.netease.nimlib.biz.e.a aVar) {
            if (aVar.n()) {
                com.netease.nimlib.biz.e.d.b bVar = (com.netease.nimlib.biz.e.d.b) aVar;
                l.b(y.a() + (bVar.c() * 1000));
                l.b(bVar.a());
                c.a(bVar.b());
                com.netease.nimlib.log.b.d("AGCHelper", "[SID 6,CID 27] request success");
                com.netease.nimlib.log.b.c("AGCHelper", "[SID 6,CID 27] ttl = " + bVar.c());
                com.netease.nimlib.log.b.c("AGCHelper", "[SID 6,CID 27] mixStoreEnable = " + bVar.a());
                com.netease.nimlib.log.b.c("AGCHelper", "[SID 6,CID 27] growDeviceEnable = " + bVar.d());
                com.netease.nimlib.log.b.c("AGCHelper", "[SID 6,CID 27] abtestIntervalFlag = " + bVar.b());
                if (com.netease.nimlib.c.D() && bVar.a()) {
                    com.netease.nimlib.log.b.d("AGCHelper", "[SID 6,CID 27] app gray mix store enable");
                    if (!com.netease.nimlib.biz.b.e.d().b()) {
                        com.netease.nimlib.biz.b.e.d().a(new com.netease.nimlib.c.a<Boolean>() { // from class: com.netease.nimlib.biz.a.1.1
                            C00251() {
                            }

                            @Override // com.netease.nimlib.c.a
                            /* renamed from: a */
                            public void onCallback(Boolean bool) {
                                com.netease.nimlib.log.b.d("AGCHelper", "HighAvailableManager init result = " + bool);
                            }
                        });
                    } else {
                        com.netease.nimlib.biz.b.e.d().c();
                    }
                } else {
                    com.netease.nimlib.log.b.d("AGCHelper", "[SID 6,CID 27] app gray mix store disable");
                }
                if (a.b()) {
                    String unused = a.b = a.a(com.netease.nimlib.c.e());
                    return;
                }
                l.a("");
                c.a("");
                String unused2 = a.b = "";
                return;
            }
            com.netease.nimlib.log.b.d("AGCHelper", "[SID 6,CID 27] request failed, error code = " + ((int) aVar.r()));
        }

        /* compiled from: AppGrayConfigHelper.java */
        /* renamed from: com.netease.nimlib.biz.a$1$1 */
        /* loaded from: classes.dex */
        class C00251 implements com.netease.nimlib.c.a<Boolean> {
            C00251() {
            }

            @Override // com.netease.nimlib.c.a
            /* renamed from: a */
            public void onCallback(Boolean bool) {
                com.netease.nimlib.log.b.d("AGCHelper", "HighAvailableManager init result = " + bool);
            }
        }
    }

    /* compiled from: AppGrayConfigHelper.java */
    /* renamed from: com.netease.nimlib.biz.a$2 */
    /* loaded from: classes.dex */
    static class AnonymousClass2 implements com.netease.nimlib.c.a<Boolean> {
        AnonymousClass2() {
        }

        @Override // com.netease.nimlib.c.a
        /* renamed from: a */
        public void onCallback(Boolean bool) {
            com.netease.nimlib.log.b.d("AGCHelper", "HighAvailableManager init result = " + bool);
        }
    }

    public static boolean b() {
        boolean c = c.c();
        boolean t = com.netease.nimlib.c.t();
        boolean z = c && t;
        com.netease.nimlib.log.b.d("AGCHelper", "current grow device enable = " + c + ",enableGrowDevice option = " + t);
        return z;
    }

    public static void a(boolean z) {
        com.netease.nimlib.log.b.d("AGCHelper", "setGrowDeviceEnable = " + z);
        c.b(z);
        if (com.netease.nimlib.h.e() == StatusCode.LOGINED) {
            if (!z) {
                l.a("");
                c.a("");
                b = "";
                return;
            }
            b = a(com.netease.nimlib.c.e());
        }
    }

    public static String c() {
        return b;
    }

    public static void b(boolean z) {
        c.g(z);
        com.netease.nimlib.log.b.d("AGCHelper", "setExceptionContextDiskInfoEnabled = " + z);
    }

    public static boolean d() {
        boolean l = c.l();
        com.netease.nimlib.log.b.d("AGCHelper", "isExceptionContextDiskInfoEnabled = " + l);
        return l;
    }

    public static void a(long j) {
        c.h(j);
        com.netease.nimlib.log.b.d("AGCHelper", "setExceptionContextDiskInfoFrequencyControl = " + j);
    }

    public static long e() {
        long k = c.k();
        com.netease.nimlib.log.b.d("AGCHelper", "getExceptionContextDiskInfoFrequencyControl = " + k);
        return k;
    }

    public static boolean f() {
        boolean m = c.m();
        com.netease.nimlib.log.b.d("AGCHelper", "isDatabaseFunctionTransformationEnable = " + m);
        return m;
    }

    public static void c(boolean z) {
        com.netease.nimlib.log.b.d("AGCHelper", "setDatabaseFunctionTransformationEnable = " + z);
        c.h(z);
        MsgDBHelper.abTestSelected = z;
    }

    public static boolean g() {
        boolean n = c.n();
        com.netease.nimlib.log.b.d("AGCHelper", "isDatabaseMessageParameterizedEnable = " + n);
        return n;
    }

    public static void d(boolean z) {
        com.netease.nimlib.log.b.d("AGCHelper", "setDatabaseMessageParameterizedEnable = " + z);
        c.i(z);
        MsgDBHelper.abTestSelectedMsg = z;
    }

    public static boolean h() {
        boolean o = c.o();
        com.netease.nimlib.log.b.d("AGCHelper", "isDatabaseMessageParameterizedEnable = " + o);
        return o;
    }

    public static void e(boolean z) {
        com.netease.nimlib.log.b.d("AGCHelper", "setExceptionDatabaseTransformStringError20231225Enable = " + z);
        c.j(z);
    }
}
