package com.netease.nimlib.biz.b;

import android.os.Handler;
import android.os.Looper;
import android.util.Pair;
import com.netease.nim.highavailable.FCSChannelResponseCallback;
import com.netease.nim.highavailable.FCSCustomAuthTokenCallback;
import com.netease.nim.highavailable.FCSDownloadCallback;
import com.netease.nim.highavailable.FCSUploadCallback;
import com.netease.nim.highavailable.HighAvailableFCSCallback;
import com.netease.nim.highavailable.HighAvailableFCSService;
import com.netease.nim.highavailable.HighAvailableObject;
import com.netease.nim.highavailable.enums.HAvailableDownloadAuthType;
import com.netease.nim.highavailable.enums.HAvailableFCSChannelFunID;
import com.netease.nim.highavailable.enums.HAvailableFCSDownloadType;
import com.netease.nim.highavailable.enums.HAvailableFCSErrorCode;
import com.netease.nimlib.biz.b.e;
import com.netease.nimlib.biz.d.d.g;
import com.netease.nimlib.biz.d.d.k;
import com.netease.nimlib.biz.e.d.h;
import com.netease.nimlib.biz.i;
import com.netease.nimlib.m.d;
import com.netease.nimlib.n.b.m;
import com.netease.nimlib.n.n;
import com.netease.nimlib.sdk.FcsDownloadAuthStrategy;
import java.util.Arrays;
import java.util.concurrent.Semaphore;

/* compiled from: HighAvailableManager.java */
/* loaded from: classes.dex */
public class e extends a {
    private static e b;
    private static b c;
    private final Handler d = new Handler(Looper.getMainLooper());
    private final Handler e = com.netease.nimlib.c.b.a.c().a("fcs_handler");
    private HighAvailableFCSService f = null;
    public static final com.netease.nimlib.biz.g.a a = new com.netease.nimlib.biz.g.a(2, 30);
    private static Semaphore g = new Semaphore(1);

    public static synchronized a d() {
        synchronized (e.class) {
            if (com.netease.nimlib.abtest.b.l()) {
                if (c == null) {
                    c = new b();
                }
                return c;
            }
            if (b == null) {
                b = new e();
            }
            return b;
        }
    }

    @Override // com.netease.nimlib.biz.b.a
    public void c() {
        HighAvailableFCSService highAvailableFCSService = this.f;
        if (highAvailableFCSService == null) {
            return;
        }
        highAvailableFCSService.setAppInfo(com.netease.nimlib.c.g(), com.netease.nimlib.c.n());
        FcsDownloadAuthStrategy E = com.netease.nimlib.c.E();
        if (E != null) {
            HAvailableDownloadAuthType downloadAuthType = E.getDownloadAuthType();
            if (downloadAuthType != null) {
                com.netease.nimlib.log.b.c("HighAvailableManager", "FCS_INIT refreshConfig download auth type = " + downloadAuthType.getValue());
            } else {
                com.netease.nimlib.log.b.c("HighAvailableManager", "FCS_INIT refreshConfig download auth type = null");
            }
            this.f.setAuthType(downloadAuthType);
            Pair<String, String> authRefer = E.getAuthRefer();
            if (authRefer != null) {
                com.netease.nimlib.log.b.c("HighAvailableManager", "FCS_INIT refreshConfig refer = " + ((String) authRefer.first) + " ,ua = " + ((String) authRefer.second));
                this.f.setUA((String) authRefer.first, (String) authRefer.second);
                return;
            }
            com.netease.nimlib.log.b.c("HighAvailableManager", "FCS_INIT refreshConfig refer = null ,ua = null ");
        }
    }

    @Override // com.netease.nimlib.biz.b.a
    public void a(com.netease.nimlib.c.a<Boolean> aVar) {
        com.netease.nimlib.log.b.d("HighAvailableManager", "start init");
        if (!com.netease.nimlib.c.D()) {
            com.netease.nimlib.log.b.d("HighAvailableManager", "enableFcs not open");
            if (aVar != null) {
                aVar.onCallback(false);
                return;
            }
            return;
        }
        if (com.netease.nimlib.h.a.a().e() && this.f != null) {
            com.netease.nimlib.log.b.d("HighAvailableManager", "already init");
            if (aVar != null) {
                aVar.onCallback(true);
                return;
            }
            return;
        }
        this.e.post(new Runnable() { // from class: com.netease.nimlib.biz.b.e.1
            final /* synthetic */ com.netease.nimlib.c.a a;

            AnonymousClass1(com.netease.nimlib.c.a aVar2) {
                r2 = aVar2;
            }

            @Override // java.lang.Runnable
            public void run() {
                e.this.b((com.netease.nimlib.c.a<Boolean>) r2);
            }
        });
    }

    /* compiled from: HighAvailableManager.java */
    /* renamed from: com.netease.nimlib.biz.b.e$1 */
    /* loaded from: classes.dex */
    class AnonymousClass1 implements Runnable {
        final /* synthetic */ com.netease.nimlib.c.a a;

        AnonymousClass1(com.netease.nimlib.c.a aVar2) {
            r2 = aVar2;
        }

        @Override // java.lang.Runnable
        public void run() {
            e.this.b((com.netease.nimlib.c.a<Boolean>) r2);
        }
    }

    public void b(com.netease.nimlib.c.a<Boolean> aVar) {
        com.netease.nimlib.log.b.d("HighAvailableManager", "start initPri");
        try {
            g.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (com.netease.nimlib.h.a.a().e() && this.f != null) {
            com.netease.nimlib.log.b.d("HighAvailableManager", "initPri already init");
            g.release();
            this.d.post(new Runnable() { // from class: com.netease.nimlib.biz.b.e.2
                final /* synthetic */ com.netease.nimlib.c.a a;

                AnonymousClass2(com.netease.nimlib.c.a aVar2) {
                    r2 = aVar2;
                }

                @Override // java.lang.Runnable
                public void run() {
                    com.netease.nimlib.log.b.d("HighAvailableManager", "already init, post callback");
                    com.netease.nimlib.c.a aVar2 = r2;
                    if (aVar2 != null) {
                        aVar2.onCallback(true);
                    }
                }
            });
        } else {
            if (!HighAvailableObject.isLoadLibSuccess()) {
                com.netease.nimlib.log.b.d("HighAvailableManager", "init load lib failed,retry");
                HighAvailableObject.loadLibrary();
            }
            com.netease.nimlib.log.b.d("HighAvailableManager", "start initFCSService");
            com.netease.nimlib.h.a.a().a(new HighAvailableFCSCallback() { // from class: com.netease.nimlib.biz.b.e.3
                final /* synthetic */ com.netease.nimlib.c.a a;

                AnonymousClass3(com.netease.nimlib.c.a aVar2) {
                    r2 = aVar2;
                }

                @Override // com.netease.nim.highavailable.HighAvailableFCSCallback
                public void onInitCallback(boolean z) {
                    com.netease.nimlib.log.b.d("HighAvailableManager", "onInitCallback: result = " + z);
                    e.this.f = com.netease.nimlib.h.a.a().f();
                    e.this.c();
                    e.g.release();
                    e.this.d.post(new Runnable() { // from class: com.netease.nimlib.biz.b.e.3.1
                        final /* synthetic */ boolean a;

                        AnonymousClass1(boolean z2) {
                            r2 = z2;
                        }

                        @Override // java.lang.Runnable
                        public void run() {
                            com.netease.nimlib.log.b.d("HighAvailableManager", "onInitCallback post callback");
                            if (r2 != null) {
                                r2.onCallback(Boolean.valueOf(r2));
                            }
                        }
                    });
                }

                /* compiled from: HighAvailableManager.java */
                /* renamed from: com.netease.nimlib.biz.b.e$3$1 */
                /* loaded from: classes.dex */
                class AnonymousClass1 implements Runnable {
                    final /* synthetic */ boolean a;

                    AnonymousClass1(boolean z2) {
                        r2 = z2;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        com.netease.nimlib.log.b.d("HighAvailableManager", "onInitCallback post callback");
                        if (r2 != null) {
                            r2.onCallback(Boolean.valueOf(r2));
                        }
                    }
                }

                @Override // com.netease.nim.highavailable.HighAvailableFCSCallback
                public void fcsChannelRequest(HAvailableFCSChannelFunID hAvailableFCSChannelFunID, int i, long j, byte[] bArr, FCSChannelResponseCallback fCSChannelResponseCallback) {
                    int value = hAvailableFCSChannelFunID.getValue();
                    com.netease.nimlib.log.b.d("HighAvailableManager", "fcsChannelRequest: fun_id = " + value + ", code = " + i + ", sn = " + j);
                    switch (AnonymousClass6.a[hAvailableFCSChannelFunID.ordinal()]) {
                        case 2:
                            if (bArr == null) {
                                com.netease.nimlib.log.b.c("HighAvailableManager", "[SID 6,CID 18] request body == null,error code = 414");
                                fCSChannelResponseCallback.fcsChannelResponse(hAvailableFCSChannelFunID.getValue(), 414, j, null);
                                return;
                            } else {
                                i.a().a(new com.netease.nimlib.biz.g.b(new com.netease.nimlib.biz.d.d.b(bArr)) { // from class: com.netease.nimlib.biz.b.e.3.2
                                    final /* synthetic */ int a;
                                    final /* synthetic */ long b;
                                    final /* synthetic */ FCSChannelResponseCallback c;
                                    final /* synthetic */ HAvailableFCSChannelFunID d;

                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    AnonymousClass2(com.netease.nimlib.biz.d.a aVar2, int value2, long j2, FCSChannelResponseCallback fCSChannelResponseCallback2, HAvailableFCSChannelFunID hAvailableFCSChannelFunID2) {
                                        super(aVar2);
                                        r3 = value2;
                                        r4 = j2;
                                        r6 = fCSChannelResponseCallback2;
                                        r7 = hAvailableFCSChannelFunID2;
                                    }

                                    @Override // com.netease.nimlib.biz.g.b, com.netease.nimlib.biz.g.c
                                    public void a(com.netease.nimlib.biz.e.a aVar2) {
                                        if (aVar2.n()) {
                                            byte[] c2 = ((com.netease.nimlib.biz.e.d.a) aVar2).c();
                                            StringBuilder sb = new StringBuilder();
                                            sb.append("[SID 6,CID 18] response: fun_id = ");
                                            sb.append(r3);
                                            sb.append(", code = ");
                                            sb.append((int) aVar2.r());
                                            sb.append(", sn = ");
                                            sb.append(r4);
                                            sb.append(", body.length = ");
                                            sb.append(c2 == null ? null : Integer.valueOf(c2.length));
                                            com.netease.nimlib.log.b.d("HighAvailableManager", sb.toString());
                                            r6.fcsChannelResponse(r7.getValue(), 200, r4, c2);
                                            return;
                                        }
                                        com.netease.nimlib.log.b.d("HighAvailableManager", "[SID 6,CID 18] response: fun_id = " + r3 + ", code = " + ((int) aVar2.r()) + ", sn = " + r4 + ", body = null");
                                        r6.fcsChannelResponse(r7.getValue(), aVar2.r(), r4, null);
                                    }
                                });
                                return;
                            }
                        case 3:
                            if (bArr == null) {
                                com.netease.nimlib.log.b.c("HighAvailableManager", "[SID 6,CID 22] request body == null,error code = 414");
                                fCSChannelResponseCallback2.fcsChannelResponse(hAvailableFCSChannelFunID2.getValue(), 414, j2, null);
                                return;
                            } else {
                                i.a().a(new com.netease.nimlib.biz.g.b(new k(bArr)) { // from class: com.netease.nimlib.biz.b.e.3.3
                                    final /* synthetic */ int a;
                                    final /* synthetic */ long b;
                                    final /* synthetic */ FCSChannelResponseCallback c;
                                    final /* synthetic */ HAvailableFCSChannelFunID d;

                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    C00273(com.netease.nimlib.biz.d.a aVar2, int value2, long j2, FCSChannelResponseCallback fCSChannelResponseCallback2, HAvailableFCSChannelFunID hAvailableFCSChannelFunID2) {
                                        super(aVar2);
                                        r3 = value2;
                                        r4 = j2;
                                        r6 = fCSChannelResponseCallback2;
                                        r7 = hAvailableFCSChannelFunID2;
                                    }

                                    @Override // com.netease.nimlib.biz.g.b, com.netease.nimlib.biz.g.c
                                    public void a(com.netease.nimlib.biz.e.a aVar2) {
                                        if (aVar2.n()) {
                                            byte[] b2 = ((h) aVar2).b();
                                            StringBuilder sb = new StringBuilder();
                                            sb.append("[SID 6,CID 22] response: fun_id = ");
                                            sb.append(r3);
                                            sb.append(", code = ");
                                            sb.append((int) aVar2.r());
                                            sb.append(", sn = ");
                                            sb.append(r4);
                                            sb.append(", body.length = ");
                                            sb.append(b2 == null ? null : Integer.valueOf(b2.length));
                                            com.netease.nimlib.log.b.d("HighAvailableManager", sb.toString());
                                            r6.fcsChannelResponse(r7.getValue(), 200, r4, b2);
                                            return;
                                        }
                                        com.netease.nimlib.log.b.d("HighAvailableManager", "[SID 6,CID 22] response: fun_id = " + r3 + ", code = " + ((int) aVar2.r()) + ", sn = " + r4 + ", body = null");
                                        r6.fcsChannelResponse(r7.getValue(), aVar2.r(), r4, null);
                                    }
                                });
                                return;
                            }
                        case 4:
                            com.netease.nimlib.log.b.d("HighAvailableManager", "[SID 6 , CID 23] request kGET_SERVER_TIME");
                            i.a().b().a((d.a) new d.a() { // from class: com.netease.nimlib.biz.b.e.3.4
                                final /* synthetic */ FCSChannelResponseCallback a;
                                final /* synthetic */ HAvailableFCSChannelFunID b;
                                final /* synthetic */ long c;

                                AnonymousClass4(FCSChannelResponseCallback fCSChannelResponseCallback2, HAvailableFCSChannelFunID hAvailableFCSChannelFunID2, long j2) {
                                    r2 = fCSChannelResponseCallback2;
                                    r3 = hAvailableFCSChannelFunID2;
                                    r4 = j2;
                                }

                                @Override // com.netease.nimlib.m.d.a
                                public void a(long j2) {
                                    com.netease.nimlib.log.b.d("HighAvailableManager", String.format("[SID 6 , CID 23] kGET_SERVER_TIME onSuccess %s", Long.valueOf(j2)));
                                    com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
                                    bVar.a(j2);
                                    byte[] array = bVar.b().array();
                                    r2.fcsChannelResponse(r3.getValue(), 200, r4, Arrays.copyOf(array, array.length));
                                }

                                @Override // com.netease.nimlib.m.d.a
                                public void a(int i2, String str) {
                                    com.netease.nimlib.log.b.d("HighAvailableManager", String.format("[SID 6 , CID 23] kGET_SERVER_TIME onFailed %s %s", Integer.valueOf(i2), str));
                                    r2.fcsChannelResponse(r3.getValue(), i2, r4, null);
                                }
                            }, false);
                            return;
                        case 5:
                            if (bArr == null) {
                                com.netease.nimlib.log.b.c("HighAvailableManager", "[SID 6,CID 28] request body == null,error code = 414");
                                fCSChannelResponseCallback2.fcsChannelResponse(hAvailableFCSChannelFunID2.getValue(), 414, j2, null);
                                return;
                            } else {
                                i.a().a(new com.netease.nimlib.biz.g.b(new g(bArr)) { // from class: com.netease.nimlib.biz.b.e.3.5
                                    final /* synthetic */ int a;
                                    final /* synthetic */ long b;
                                    final /* synthetic */ FCSChannelResponseCallback c;
                                    final /* synthetic */ HAvailableFCSChannelFunID d;

                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    AnonymousClass5(com.netease.nimlib.biz.d.a aVar2, int value2, long j2, FCSChannelResponseCallback fCSChannelResponseCallback2, HAvailableFCSChannelFunID hAvailableFCSChannelFunID2) {
                                        super(aVar2);
                                        r3 = value2;
                                        r4 = j2;
                                        r6 = fCSChannelResponseCallback2;
                                        r7 = hAvailableFCSChannelFunID2;
                                    }

                                    @Override // com.netease.nimlib.biz.g.b, com.netease.nimlib.biz.g.c
                                    public void a(com.netease.nimlib.biz.e.a aVar2) {
                                        if (aVar2.n()) {
                                            byte[] a2 = ((com.netease.nimlib.biz.e.d.e) aVar2).a();
                                            StringBuilder sb = new StringBuilder();
                                            sb.append("[SID 6,CID 28] response: fun_id = ");
                                            sb.append(r3);
                                            sb.append(", code = ");
                                            sb.append((int) aVar2.r());
                                            sb.append(", sn = ");
                                            sb.append(r4);
                                            sb.append(", body.length = ");
                                            sb.append(a2 == null ? null : Integer.valueOf(a2.length));
                                            com.netease.nimlib.log.b.d("HighAvailableManager", sb.toString());
                                            r6.fcsChannelResponse(r7.getValue(), 200, r4, a2);
                                            return;
                                        }
                                        com.netease.nimlib.log.b.d("HighAvailableManager", "[SID 6,CID 28] response: fun_id = " + r3 + ", code = " + ((int) aVar2.r()) + ", sn = " + r4 + ", body = null");
                                        r6.fcsChannelResponse(r7.getValue(), aVar2.r(), r4, null);
                                    }
                                });
                                return;
                            }
                        case 6:
                            if (bArr == null) {
                                com.netease.nimlib.log.b.c("HighAvailableManager", "[SID 6,CID 29] request body == null,error code = 414");
                                fCSChannelResponseCallback2.fcsChannelResponse(hAvailableFCSChannelFunID2.getValue(), 414, j2, null);
                                return;
                            } else {
                                i.a().a(new com.netease.nimlib.biz.g.b(new com.netease.nimlib.biz.d.d.e(bArr)) { // from class: com.netease.nimlib.biz.b.e.3.6
                                    final /* synthetic */ int a;
                                    final /* synthetic */ long b;
                                    final /* synthetic */ FCSChannelResponseCallback c;
                                    final /* synthetic */ HAvailableFCSChannelFunID d;

                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    AnonymousClass6(com.netease.nimlib.biz.d.a aVar2, int value2, long j2, FCSChannelResponseCallback fCSChannelResponseCallback2, HAvailableFCSChannelFunID hAvailableFCSChannelFunID2) {
                                        super(aVar2);
                                        r3 = value2;
                                        r4 = j2;
                                        r6 = fCSChannelResponseCallback2;
                                        r7 = hAvailableFCSChannelFunID2;
                                    }

                                    @Override // com.netease.nimlib.biz.g.b, com.netease.nimlib.biz.g.c
                                    public void a(com.netease.nimlib.biz.e.a aVar2) {
                                        if (aVar2.n()) {
                                            byte[] a2 = ((com.netease.nimlib.biz.e.d.d) aVar2).a();
                                            StringBuilder sb = new StringBuilder();
                                            sb.append("[SID 6,CID 29] response: fun_id = ");
                                            sb.append(r3);
                                            sb.append(", code = ");
                                            sb.append((int) aVar2.r());
                                            sb.append(", sn = ");
                                            sb.append(r4);
                                            sb.append(", body.length = ");
                                            sb.append(a2 == null ? null : Integer.valueOf(a2.length));
                                            com.netease.nimlib.log.b.d("HighAvailableManager", sb.toString());
                                            r6.fcsChannelResponse(r7.getValue(), 200, r4, a2);
                                            return;
                                        }
                                        com.netease.nimlib.log.b.d("HighAvailableManager", "[SID 6,CID 29] response: fun_id = " + r3 + ", code = " + ((int) aVar2.r()) + ", sn = " + r4 + ", body = null");
                                        r6.fcsChannelResponse(r7.getValue(), aVar2.r(), r4, null);
                                    }
                                });
                                return;
                            }
                        case 7:
                            if (bArr == null) {
                                com.netease.nimlib.log.b.c("HighAvailableManager", "[SID 6,CID 30] request body == null,error code = 414");
                                fCSChannelResponseCallback2.fcsChannelResponse(hAvailableFCSChannelFunID2.getValue(), 414, j2, null);
                                return;
                            } else {
                                i.a().a(new com.netease.nimlib.biz.g.b(new com.netease.nimlib.biz.d.d.d(bArr)) { // from class: com.netease.nimlib.biz.b.e.3.7
                                    final /* synthetic */ int a;
                                    final /* synthetic */ long b;
                                    final /* synthetic */ FCSChannelResponseCallback c;
                                    final /* synthetic */ HAvailableFCSChannelFunID d;

                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    AnonymousClass7(com.netease.nimlib.biz.d.a aVar2, int value2, long j2, FCSChannelResponseCallback fCSChannelResponseCallback2, HAvailableFCSChannelFunID hAvailableFCSChannelFunID2) {
                                        super(aVar2);
                                        r3 = value2;
                                        r4 = j2;
                                        r6 = fCSChannelResponseCallback2;
                                        r7 = hAvailableFCSChannelFunID2;
                                    }

                                    @Override // com.netease.nimlib.biz.g.b, com.netease.nimlib.biz.g.c
                                    public void a(com.netease.nimlib.biz.e.a aVar2) {
                                        if (aVar2.n()) {
                                            byte[] a2 = ((com.netease.nimlib.biz.e.d.c) aVar2).a();
                                            StringBuilder sb = new StringBuilder();
                                            sb.append("[SID 6,CID 30] response: fun_id = ");
                                            sb.append(r3);
                                            sb.append(", code = ");
                                            sb.append((int) aVar2.r());
                                            sb.append(", sn = ");
                                            sb.append(r4);
                                            sb.append(", body.length = ");
                                            sb.append(a2 == null ? null : Integer.valueOf(a2.length));
                                            com.netease.nimlib.log.b.d("HighAvailableManager", sb.toString());
                                            r6.fcsChannelResponse(r7.getValue(), 200, r4, a2);
                                            return;
                                        }
                                        com.netease.nimlib.log.b.d("HighAvailableManager", "[SID 6,CID 30] response: fun_id = " + r3 + ", code = " + ((int) aVar2.r()) + ", sn = " + r4 + ", body = null");
                                        r6.fcsChannelResponse(r7.getValue(), aVar2.r(), r4, null);
                                    }
                                });
                                return;
                            }
                        default:
                            return;
                    }
                }

                /* compiled from: HighAvailableManager.java */
                /* renamed from: com.netease.nimlib.biz.b.e$3$2 */
                /* loaded from: classes.dex */
                class AnonymousClass2 extends com.netease.nimlib.biz.g.b {
                    final /* synthetic */ int a;
                    final /* synthetic */ long b;
                    final /* synthetic */ FCSChannelResponseCallback c;
                    final /* synthetic */ HAvailableFCSChannelFunID d;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    AnonymousClass2(com.netease.nimlib.biz.d.a aVar2, int value2, long j2, FCSChannelResponseCallback fCSChannelResponseCallback2, HAvailableFCSChannelFunID hAvailableFCSChannelFunID2) {
                        super(aVar2);
                        r3 = value2;
                        r4 = j2;
                        r6 = fCSChannelResponseCallback2;
                        r7 = hAvailableFCSChannelFunID2;
                    }

                    @Override // com.netease.nimlib.biz.g.b, com.netease.nimlib.biz.g.c
                    public void a(com.netease.nimlib.biz.e.a aVar2) {
                        if (aVar2.n()) {
                            byte[] c2 = ((com.netease.nimlib.biz.e.d.a) aVar2).c();
                            StringBuilder sb = new StringBuilder();
                            sb.append("[SID 6,CID 18] response: fun_id = ");
                            sb.append(r3);
                            sb.append(", code = ");
                            sb.append((int) aVar2.r());
                            sb.append(", sn = ");
                            sb.append(r4);
                            sb.append(", body.length = ");
                            sb.append(c2 == null ? null : Integer.valueOf(c2.length));
                            com.netease.nimlib.log.b.d("HighAvailableManager", sb.toString());
                            r6.fcsChannelResponse(r7.getValue(), 200, r4, c2);
                            return;
                        }
                        com.netease.nimlib.log.b.d("HighAvailableManager", "[SID 6,CID 18] response: fun_id = " + r3 + ", code = " + ((int) aVar2.r()) + ", sn = " + r4 + ", body = null");
                        r6.fcsChannelResponse(r7.getValue(), aVar2.r(), r4, null);
                    }
                }

                /* compiled from: HighAvailableManager.java */
                /* renamed from: com.netease.nimlib.biz.b.e$3$3 */
                /* loaded from: classes.dex */
                class C00273 extends com.netease.nimlib.biz.g.b {
                    final /* synthetic */ int a;
                    final /* synthetic */ long b;
                    final /* synthetic */ FCSChannelResponseCallback c;
                    final /* synthetic */ HAvailableFCSChannelFunID d;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    C00273(com.netease.nimlib.biz.d.a aVar2, int value2, long j2, FCSChannelResponseCallback fCSChannelResponseCallback2, HAvailableFCSChannelFunID hAvailableFCSChannelFunID2) {
                        super(aVar2);
                        r3 = value2;
                        r4 = j2;
                        r6 = fCSChannelResponseCallback2;
                        r7 = hAvailableFCSChannelFunID2;
                    }

                    @Override // com.netease.nimlib.biz.g.b, com.netease.nimlib.biz.g.c
                    public void a(com.netease.nimlib.biz.e.a aVar2) {
                        if (aVar2.n()) {
                            byte[] b2 = ((h) aVar2).b();
                            StringBuilder sb = new StringBuilder();
                            sb.append("[SID 6,CID 22] response: fun_id = ");
                            sb.append(r3);
                            sb.append(", code = ");
                            sb.append((int) aVar2.r());
                            sb.append(", sn = ");
                            sb.append(r4);
                            sb.append(", body.length = ");
                            sb.append(b2 == null ? null : Integer.valueOf(b2.length));
                            com.netease.nimlib.log.b.d("HighAvailableManager", sb.toString());
                            r6.fcsChannelResponse(r7.getValue(), 200, r4, b2);
                            return;
                        }
                        com.netease.nimlib.log.b.d("HighAvailableManager", "[SID 6,CID 22] response: fun_id = " + r3 + ", code = " + ((int) aVar2.r()) + ", sn = " + r4 + ", body = null");
                        r6.fcsChannelResponse(r7.getValue(), aVar2.r(), r4, null);
                    }
                }

                /* compiled from: HighAvailableManager.java */
                /* renamed from: com.netease.nimlib.biz.b.e$3$4 */
                /* loaded from: classes.dex */
                class AnonymousClass4 implements d.a {
                    final /* synthetic */ FCSChannelResponseCallback a;
                    final /* synthetic */ HAvailableFCSChannelFunID b;
                    final /* synthetic */ long c;

                    AnonymousClass4(FCSChannelResponseCallback fCSChannelResponseCallback2, HAvailableFCSChannelFunID hAvailableFCSChannelFunID2, long j2) {
                        r2 = fCSChannelResponseCallback2;
                        r3 = hAvailableFCSChannelFunID2;
                        r4 = j2;
                    }

                    @Override // com.netease.nimlib.m.d.a
                    public void a(long j2) {
                        com.netease.nimlib.log.b.d("HighAvailableManager", String.format("[SID 6 , CID 23] kGET_SERVER_TIME onSuccess %s", Long.valueOf(j2)));
                        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
                        bVar.a(j2);
                        byte[] array = bVar.b().array();
                        r2.fcsChannelResponse(r3.getValue(), 200, r4, Arrays.copyOf(array, array.length));
                    }

                    @Override // com.netease.nimlib.m.d.a
                    public void a(int i2, String str) {
                        com.netease.nimlib.log.b.d("HighAvailableManager", String.format("[SID 6 , CID 23] kGET_SERVER_TIME onFailed %s %s", Integer.valueOf(i2), str));
                        r2.fcsChannelResponse(r3.getValue(), i2, r4, null);
                    }
                }

                /* compiled from: HighAvailableManager.java */
                /* renamed from: com.netease.nimlib.biz.b.e$3$5 */
                /* loaded from: classes.dex */
                class AnonymousClass5 extends com.netease.nimlib.biz.g.b {
                    final /* synthetic */ int a;
                    final /* synthetic */ long b;
                    final /* synthetic */ FCSChannelResponseCallback c;
                    final /* synthetic */ HAvailableFCSChannelFunID d;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    AnonymousClass5(com.netease.nimlib.biz.d.a aVar2, int value2, long j2, FCSChannelResponseCallback fCSChannelResponseCallback2, HAvailableFCSChannelFunID hAvailableFCSChannelFunID2) {
                        super(aVar2);
                        r3 = value2;
                        r4 = j2;
                        r6 = fCSChannelResponseCallback2;
                        r7 = hAvailableFCSChannelFunID2;
                    }

                    @Override // com.netease.nimlib.biz.g.b, com.netease.nimlib.biz.g.c
                    public void a(com.netease.nimlib.biz.e.a aVar2) {
                        if (aVar2.n()) {
                            byte[] a2 = ((com.netease.nimlib.biz.e.d.e) aVar2).a();
                            StringBuilder sb = new StringBuilder();
                            sb.append("[SID 6,CID 28] response: fun_id = ");
                            sb.append(r3);
                            sb.append(", code = ");
                            sb.append((int) aVar2.r());
                            sb.append(", sn = ");
                            sb.append(r4);
                            sb.append(", body.length = ");
                            sb.append(a2 == null ? null : Integer.valueOf(a2.length));
                            com.netease.nimlib.log.b.d("HighAvailableManager", sb.toString());
                            r6.fcsChannelResponse(r7.getValue(), 200, r4, a2);
                            return;
                        }
                        com.netease.nimlib.log.b.d("HighAvailableManager", "[SID 6,CID 28] response: fun_id = " + r3 + ", code = " + ((int) aVar2.r()) + ", sn = " + r4 + ", body = null");
                        r6.fcsChannelResponse(r7.getValue(), aVar2.r(), r4, null);
                    }
                }

                /* compiled from: HighAvailableManager.java */
                /* renamed from: com.netease.nimlib.biz.b.e$3$6 */
                /* loaded from: classes.dex */
                class AnonymousClass6 extends com.netease.nimlib.biz.g.b {
                    final /* synthetic */ int a;
                    final /* synthetic */ long b;
                    final /* synthetic */ FCSChannelResponseCallback c;
                    final /* synthetic */ HAvailableFCSChannelFunID d;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    AnonymousClass6(com.netease.nimlib.biz.d.a aVar2, int value2, long j2, FCSChannelResponseCallback fCSChannelResponseCallback2, HAvailableFCSChannelFunID hAvailableFCSChannelFunID2) {
                        super(aVar2);
                        r3 = value2;
                        r4 = j2;
                        r6 = fCSChannelResponseCallback2;
                        r7 = hAvailableFCSChannelFunID2;
                    }

                    @Override // com.netease.nimlib.biz.g.b, com.netease.nimlib.biz.g.c
                    public void a(com.netease.nimlib.biz.e.a aVar2) {
                        if (aVar2.n()) {
                            byte[] a2 = ((com.netease.nimlib.biz.e.d.d) aVar2).a();
                            StringBuilder sb = new StringBuilder();
                            sb.append("[SID 6,CID 29] response: fun_id = ");
                            sb.append(r3);
                            sb.append(", code = ");
                            sb.append((int) aVar2.r());
                            sb.append(", sn = ");
                            sb.append(r4);
                            sb.append(", body.length = ");
                            sb.append(a2 == null ? null : Integer.valueOf(a2.length));
                            com.netease.nimlib.log.b.d("HighAvailableManager", sb.toString());
                            r6.fcsChannelResponse(r7.getValue(), 200, r4, a2);
                            return;
                        }
                        com.netease.nimlib.log.b.d("HighAvailableManager", "[SID 6,CID 29] response: fun_id = " + r3 + ", code = " + ((int) aVar2.r()) + ", sn = " + r4 + ", body = null");
                        r6.fcsChannelResponse(r7.getValue(), aVar2.r(), r4, null);
                    }
                }

                /* compiled from: HighAvailableManager.java */
                /* renamed from: com.netease.nimlib.biz.b.e$3$7 */
                /* loaded from: classes.dex */
                class AnonymousClass7 extends com.netease.nimlib.biz.g.b {
                    final /* synthetic */ int a;
                    final /* synthetic */ long b;
                    final /* synthetic */ FCSChannelResponseCallback c;
                    final /* synthetic */ HAvailableFCSChannelFunID d;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    AnonymousClass7(com.netease.nimlib.biz.d.a aVar2, int value2, long j2, FCSChannelResponseCallback fCSChannelResponseCallback2, HAvailableFCSChannelFunID hAvailableFCSChannelFunID2) {
                        super(aVar2);
                        r3 = value2;
                        r4 = j2;
                        r6 = fCSChannelResponseCallback2;
                        r7 = hAvailableFCSChannelFunID2;
                    }

                    @Override // com.netease.nimlib.biz.g.b, com.netease.nimlib.biz.g.c
                    public void a(com.netease.nimlib.biz.e.a aVar2) {
                        if (aVar2.n()) {
                            byte[] a2 = ((com.netease.nimlib.biz.e.d.c) aVar2).a();
                            StringBuilder sb = new StringBuilder();
                            sb.append("[SID 6,CID 30] response: fun_id = ");
                            sb.append(r3);
                            sb.append(", code = ");
                            sb.append((int) aVar2.r());
                            sb.append(", sn = ");
                            sb.append(r4);
                            sb.append(", body.length = ");
                            sb.append(a2 == null ? null : Integer.valueOf(a2.length));
                            com.netease.nimlib.log.b.d("HighAvailableManager", sb.toString());
                            r6.fcsChannelResponse(r7.getValue(), 200, r4, a2);
                            return;
                        }
                        com.netease.nimlib.log.b.d("HighAvailableManager", "[SID 6,CID 30] response: fun_id = " + r3 + ", code = " + ((int) aVar2.r()) + ", sn = " + r4 + ", body = null");
                        r6.fcsChannelResponse(r7.getValue(), aVar2.r(), r4, null);
                    }
                }

                @Override // com.netease.nim.highavailable.HighAvailableFCSCallback
                public void getCustomAuthToken(String str, FCSCustomAuthTokenCallback fCSCustomAuthTokenCallback) {
                    com.netease.nimlib.log.b.c("HighAvailableManager", "getCustomAuthToken: url = " + str);
                    FcsDownloadAuthStrategy E = com.netease.nimlib.c.E();
                    if (E == null) {
                        fCSCustomAuthTokenCallback.onToken(null);
                    } else {
                        fCSCustomAuthTokenCallback.onToken(E.getCustomAuthToken(str));
                    }
                }
            });
        }
    }

    /* compiled from: HighAvailableManager.java */
    /* renamed from: com.netease.nimlib.biz.b.e$2 */
    /* loaded from: classes.dex */
    class AnonymousClass2 implements Runnable {
        final /* synthetic */ com.netease.nimlib.c.a a;

        AnonymousClass2(com.netease.nimlib.c.a aVar2) {
            r2 = aVar2;
        }

        @Override // java.lang.Runnable
        public void run() {
            com.netease.nimlib.log.b.d("HighAvailableManager", "already init, post callback");
            com.netease.nimlib.c.a aVar2 = r2;
            if (aVar2 != null) {
                aVar2.onCallback(true);
            }
        }
    }

    /* compiled from: HighAvailableManager.java */
    /* renamed from: com.netease.nimlib.biz.b.e$3 */
    /* loaded from: classes.dex */
    class AnonymousClass3 implements HighAvailableFCSCallback {
        final /* synthetic */ com.netease.nimlib.c.a a;

        AnonymousClass3(com.netease.nimlib.c.a aVar2) {
            r2 = aVar2;
        }

        @Override // com.netease.nim.highavailable.HighAvailableFCSCallback
        public void onInitCallback(boolean z2) {
            com.netease.nimlib.log.b.d("HighAvailableManager", "onInitCallback: result = " + z2);
            e.this.f = com.netease.nimlib.h.a.a().f();
            e.this.c();
            e.g.release();
            e.this.d.post(new Runnable() { // from class: com.netease.nimlib.biz.b.e.3.1
                final /* synthetic */ boolean a;

                AnonymousClass1(boolean z22) {
                    r2 = z22;
                }

                @Override // java.lang.Runnable
                public void run() {
                    com.netease.nimlib.log.b.d("HighAvailableManager", "onInitCallback post callback");
                    if (r2 != null) {
                        r2.onCallback(Boolean.valueOf(r2));
                    }
                }
            });
        }

        /* compiled from: HighAvailableManager.java */
        /* renamed from: com.netease.nimlib.biz.b.e$3$1 */
        /* loaded from: classes.dex */
        class AnonymousClass1 implements Runnable {
            final /* synthetic */ boolean a;

            AnonymousClass1(boolean z22) {
                r2 = z22;
            }

            @Override // java.lang.Runnable
            public void run() {
                com.netease.nimlib.log.b.d("HighAvailableManager", "onInitCallback post callback");
                if (r2 != null) {
                    r2.onCallback(Boolean.valueOf(r2));
                }
            }
        }

        @Override // com.netease.nim.highavailable.HighAvailableFCSCallback
        public void fcsChannelRequest(HAvailableFCSChannelFunID hAvailableFCSChannelFunID2, int i, long j2, byte[] bArr, FCSChannelResponseCallback fCSChannelResponseCallback2) {
            int value2 = hAvailableFCSChannelFunID2.getValue();
            com.netease.nimlib.log.b.d("HighAvailableManager", "fcsChannelRequest: fun_id = " + value2 + ", code = " + i + ", sn = " + j2);
            switch (AnonymousClass6.a[hAvailableFCSChannelFunID2.ordinal()]) {
                case 2:
                    if (bArr == null) {
                        com.netease.nimlib.log.b.c("HighAvailableManager", "[SID 6,CID 18] request body == null,error code = 414");
                        fCSChannelResponseCallback2.fcsChannelResponse(hAvailableFCSChannelFunID2.getValue(), 414, j2, null);
                        return;
                    } else {
                        i.a().a(new com.netease.nimlib.biz.g.b(new com.netease.nimlib.biz.d.d.b(bArr)) { // from class: com.netease.nimlib.biz.b.e.3.2
                            final /* synthetic */ int a;
                            final /* synthetic */ long b;
                            final /* synthetic */ FCSChannelResponseCallback c;
                            final /* synthetic */ HAvailableFCSChannelFunID d;

                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            AnonymousClass2(com.netease.nimlib.biz.d.a aVar2, int value22, long j22, FCSChannelResponseCallback fCSChannelResponseCallback22, HAvailableFCSChannelFunID hAvailableFCSChannelFunID22) {
                                super(aVar2);
                                r3 = value22;
                                r4 = j22;
                                r6 = fCSChannelResponseCallback22;
                                r7 = hAvailableFCSChannelFunID22;
                            }

                            @Override // com.netease.nimlib.biz.g.b, com.netease.nimlib.biz.g.c
                            public void a(com.netease.nimlib.biz.e.a aVar2) {
                                if (aVar2.n()) {
                                    byte[] c2 = ((com.netease.nimlib.biz.e.d.a) aVar2).c();
                                    StringBuilder sb = new StringBuilder();
                                    sb.append("[SID 6,CID 18] response: fun_id = ");
                                    sb.append(r3);
                                    sb.append(", code = ");
                                    sb.append((int) aVar2.r());
                                    sb.append(", sn = ");
                                    sb.append(r4);
                                    sb.append(", body.length = ");
                                    sb.append(c2 == null ? null : Integer.valueOf(c2.length));
                                    com.netease.nimlib.log.b.d("HighAvailableManager", sb.toString());
                                    r6.fcsChannelResponse(r7.getValue(), 200, r4, c2);
                                    return;
                                }
                                com.netease.nimlib.log.b.d("HighAvailableManager", "[SID 6,CID 18] response: fun_id = " + r3 + ", code = " + ((int) aVar2.r()) + ", sn = " + r4 + ", body = null");
                                r6.fcsChannelResponse(r7.getValue(), aVar2.r(), r4, null);
                            }
                        });
                        return;
                    }
                case 3:
                    if (bArr == null) {
                        com.netease.nimlib.log.b.c("HighAvailableManager", "[SID 6,CID 22] request body == null,error code = 414");
                        fCSChannelResponseCallback22.fcsChannelResponse(hAvailableFCSChannelFunID22.getValue(), 414, j22, null);
                        return;
                    } else {
                        i.a().a(new com.netease.nimlib.biz.g.b(new k(bArr)) { // from class: com.netease.nimlib.biz.b.e.3.3
                            final /* synthetic */ int a;
                            final /* synthetic */ long b;
                            final /* synthetic */ FCSChannelResponseCallback c;
                            final /* synthetic */ HAvailableFCSChannelFunID d;

                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            C00273(com.netease.nimlib.biz.d.a aVar2, int value22, long j22, FCSChannelResponseCallback fCSChannelResponseCallback22, HAvailableFCSChannelFunID hAvailableFCSChannelFunID22) {
                                super(aVar2);
                                r3 = value22;
                                r4 = j22;
                                r6 = fCSChannelResponseCallback22;
                                r7 = hAvailableFCSChannelFunID22;
                            }

                            @Override // com.netease.nimlib.biz.g.b, com.netease.nimlib.biz.g.c
                            public void a(com.netease.nimlib.biz.e.a aVar2) {
                                if (aVar2.n()) {
                                    byte[] b2 = ((h) aVar2).b();
                                    StringBuilder sb = new StringBuilder();
                                    sb.append("[SID 6,CID 22] response: fun_id = ");
                                    sb.append(r3);
                                    sb.append(", code = ");
                                    sb.append((int) aVar2.r());
                                    sb.append(", sn = ");
                                    sb.append(r4);
                                    sb.append(", body.length = ");
                                    sb.append(b2 == null ? null : Integer.valueOf(b2.length));
                                    com.netease.nimlib.log.b.d("HighAvailableManager", sb.toString());
                                    r6.fcsChannelResponse(r7.getValue(), 200, r4, b2);
                                    return;
                                }
                                com.netease.nimlib.log.b.d("HighAvailableManager", "[SID 6,CID 22] response: fun_id = " + r3 + ", code = " + ((int) aVar2.r()) + ", sn = " + r4 + ", body = null");
                                r6.fcsChannelResponse(r7.getValue(), aVar2.r(), r4, null);
                            }
                        });
                        return;
                    }
                case 4:
                    com.netease.nimlib.log.b.d("HighAvailableManager", "[SID 6 , CID 23] request kGET_SERVER_TIME");
                    i.a().b().a((d.a) new d.a() { // from class: com.netease.nimlib.biz.b.e.3.4
                        final /* synthetic */ FCSChannelResponseCallback a;
                        final /* synthetic */ HAvailableFCSChannelFunID b;
                        final /* synthetic */ long c;

                        AnonymousClass4(FCSChannelResponseCallback fCSChannelResponseCallback22, HAvailableFCSChannelFunID hAvailableFCSChannelFunID22, long j22) {
                            r2 = fCSChannelResponseCallback22;
                            r3 = hAvailableFCSChannelFunID22;
                            r4 = j22;
                        }

                        @Override // com.netease.nimlib.m.d.a
                        public void a(long j22) {
                            com.netease.nimlib.log.b.d("HighAvailableManager", String.format("[SID 6 , CID 23] kGET_SERVER_TIME onSuccess %s", Long.valueOf(j22)));
                            com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
                            bVar.a(j22);
                            byte[] array = bVar.b().array();
                            r2.fcsChannelResponse(r3.getValue(), 200, r4, Arrays.copyOf(array, array.length));
                        }

                        @Override // com.netease.nimlib.m.d.a
                        public void a(int i2, String str) {
                            com.netease.nimlib.log.b.d("HighAvailableManager", String.format("[SID 6 , CID 23] kGET_SERVER_TIME onFailed %s %s", Integer.valueOf(i2), str));
                            r2.fcsChannelResponse(r3.getValue(), i2, r4, null);
                        }
                    }, false);
                    return;
                case 5:
                    if (bArr == null) {
                        com.netease.nimlib.log.b.c("HighAvailableManager", "[SID 6,CID 28] request body == null,error code = 414");
                        fCSChannelResponseCallback22.fcsChannelResponse(hAvailableFCSChannelFunID22.getValue(), 414, j22, null);
                        return;
                    } else {
                        i.a().a(new com.netease.nimlib.biz.g.b(new g(bArr)) { // from class: com.netease.nimlib.biz.b.e.3.5
                            final /* synthetic */ int a;
                            final /* synthetic */ long b;
                            final /* synthetic */ FCSChannelResponseCallback c;
                            final /* synthetic */ HAvailableFCSChannelFunID d;

                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            AnonymousClass5(com.netease.nimlib.biz.d.a aVar2, int value22, long j22, FCSChannelResponseCallback fCSChannelResponseCallback22, HAvailableFCSChannelFunID hAvailableFCSChannelFunID22) {
                                super(aVar2);
                                r3 = value22;
                                r4 = j22;
                                r6 = fCSChannelResponseCallback22;
                                r7 = hAvailableFCSChannelFunID22;
                            }

                            @Override // com.netease.nimlib.biz.g.b, com.netease.nimlib.biz.g.c
                            public void a(com.netease.nimlib.biz.e.a aVar2) {
                                if (aVar2.n()) {
                                    byte[] a2 = ((com.netease.nimlib.biz.e.d.e) aVar2).a();
                                    StringBuilder sb = new StringBuilder();
                                    sb.append("[SID 6,CID 28] response: fun_id = ");
                                    sb.append(r3);
                                    sb.append(", code = ");
                                    sb.append((int) aVar2.r());
                                    sb.append(", sn = ");
                                    sb.append(r4);
                                    sb.append(", body.length = ");
                                    sb.append(a2 == null ? null : Integer.valueOf(a2.length));
                                    com.netease.nimlib.log.b.d("HighAvailableManager", sb.toString());
                                    r6.fcsChannelResponse(r7.getValue(), 200, r4, a2);
                                    return;
                                }
                                com.netease.nimlib.log.b.d("HighAvailableManager", "[SID 6,CID 28] response: fun_id = " + r3 + ", code = " + ((int) aVar2.r()) + ", sn = " + r4 + ", body = null");
                                r6.fcsChannelResponse(r7.getValue(), aVar2.r(), r4, null);
                            }
                        });
                        return;
                    }
                case 6:
                    if (bArr == null) {
                        com.netease.nimlib.log.b.c("HighAvailableManager", "[SID 6,CID 29] request body == null,error code = 414");
                        fCSChannelResponseCallback22.fcsChannelResponse(hAvailableFCSChannelFunID22.getValue(), 414, j22, null);
                        return;
                    } else {
                        i.a().a(new com.netease.nimlib.biz.g.b(new com.netease.nimlib.biz.d.d.e(bArr)) { // from class: com.netease.nimlib.biz.b.e.3.6
                            final /* synthetic */ int a;
                            final /* synthetic */ long b;
                            final /* synthetic */ FCSChannelResponseCallback c;
                            final /* synthetic */ HAvailableFCSChannelFunID d;

                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            AnonymousClass6(com.netease.nimlib.biz.d.a aVar2, int value22, long j22, FCSChannelResponseCallback fCSChannelResponseCallback22, HAvailableFCSChannelFunID hAvailableFCSChannelFunID22) {
                                super(aVar2);
                                r3 = value22;
                                r4 = j22;
                                r6 = fCSChannelResponseCallback22;
                                r7 = hAvailableFCSChannelFunID22;
                            }

                            @Override // com.netease.nimlib.biz.g.b, com.netease.nimlib.biz.g.c
                            public void a(com.netease.nimlib.biz.e.a aVar2) {
                                if (aVar2.n()) {
                                    byte[] a2 = ((com.netease.nimlib.biz.e.d.d) aVar2).a();
                                    StringBuilder sb = new StringBuilder();
                                    sb.append("[SID 6,CID 29] response: fun_id = ");
                                    sb.append(r3);
                                    sb.append(", code = ");
                                    sb.append((int) aVar2.r());
                                    sb.append(", sn = ");
                                    sb.append(r4);
                                    sb.append(", body.length = ");
                                    sb.append(a2 == null ? null : Integer.valueOf(a2.length));
                                    com.netease.nimlib.log.b.d("HighAvailableManager", sb.toString());
                                    r6.fcsChannelResponse(r7.getValue(), 200, r4, a2);
                                    return;
                                }
                                com.netease.nimlib.log.b.d("HighAvailableManager", "[SID 6,CID 29] response: fun_id = " + r3 + ", code = " + ((int) aVar2.r()) + ", sn = " + r4 + ", body = null");
                                r6.fcsChannelResponse(r7.getValue(), aVar2.r(), r4, null);
                            }
                        });
                        return;
                    }
                case 7:
                    if (bArr == null) {
                        com.netease.nimlib.log.b.c("HighAvailableManager", "[SID 6,CID 30] request body == null,error code = 414");
                        fCSChannelResponseCallback22.fcsChannelResponse(hAvailableFCSChannelFunID22.getValue(), 414, j22, null);
                        return;
                    } else {
                        i.a().a(new com.netease.nimlib.biz.g.b(new com.netease.nimlib.biz.d.d.d(bArr)) { // from class: com.netease.nimlib.biz.b.e.3.7
                            final /* synthetic */ int a;
                            final /* synthetic */ long b;
                            final /* synthetic */ FCSChannelResponseCallback c;
                            final /* synthetic */ HAvailableFCSChannelFunID d;

                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            AnonymousClass7(com.netease.nimlib.biz.d.a aVar2, int value22, long j22, FCSChannelResponseCallback fCSChannelResponseCallback22, HAvailableFCSChannelFunID hAvailableFCSChannelFunID22) {
                                super(aVar2);
                                r3 = value22;
                                r4 = j22;
                                r6 = fCSChannelResponseCallback22;
                                r7 = hAvailableFCSChannelFunID22;
                            }

                            @Override // com.netease.nimlib.biz.g.b, com.netease.nimlib.biz.g.c
                            public void a(com.netease.nimlib.biz.e.a aVar2) {
                                if (aVar2.n()) {
                                    byte[] a2 = ((com.netease.nimlib.biz.e.d.c) aVar2).a();
                                    StringBuilder sb = new StringBuilder();
                                    sb.append("[SID 6,CID 30] response: fun_id = ");
                                    sb.append(r3);
                                    sb.append(", code = ");
                                    sb.append((int) aVar2.r());
                                    sb.append(", sn = ");
                                    sb.append(r4);
                                    sb.append(", body.length = ");
                                    sb.append(a2 == null ? null : Integer.valueOf(a2.length));
                                    com.netease.nimlib.log.b.d("HighAvailableManager", sb.toString());
                                    r6.fcsChannelResponse(r7.getValue(), 200, r4, a2);
                                    return;
                                }
                                com.netease.nimlib.log.b.d("HighAvailableManager", "[SID 6,CID 30] response: fun_id = " + r3 + ", code = " + ((int) aVar2.r()) + ", sn = " + r4 + ", body = null");
                                r6.fcsChannelResponse(r7.getValue(), aVar2.r(), r4, null);
                            }
                        });
                        return;
                    }
                default:
                    return;
            }
        }

        /* compiled from: HighAvailableManager.java */
        /* renamed from: com.netease.nimlib.biz.b.e$3$2 */
        /* loaded from: classes.dex */
        class AnonymousClass2 extends com.netease.nimlib.biz.g.b {
            final /* synthetic */ int a;
            final /* synthetic */ long b;
            final /* synthetic */ FCSChannelResponseCallback c;
            final /* synthetic */ HAvailableFCSChannelFunID d;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass2(com.netease.nimlib.biz.d.a aVar2, int value22, long j22, FCSChannelResponseCallback fCSChannelResponseCallback22, HAvailableFCSChannelFunID hAvailableFCSChannelFunID22) {
                super(aVar2);
                r3 = value22;
                r4 = j22;
                r6 = fCSChannelResponseCallback22;
                r7 = hAvailableFCSChannelFunID22;
            }

            @Override // com.netease.nimlib.biz.g.b, com.netease.nimlib.biz.g.c
            public void a(com.netease.nimlib.biz.e.a aVar2) {
                if (aVar2.n()) {
                    byte[] c2 = ((com.netease.nimlib.biz.e.d.a) aVar2).c();
                    StringBuilder sb = new StringBuilder();
                    sb.append("[SID 6,CID 18] response: fun_id = ");
                    sb.append(r3);
                    sb.append(", code = ");
                    sb.append((int) aVar2.r());
                    sb.append(", sn = ");
                    sb.append(r4);
                    sb.append(", body.length = ");
                    sb.append(c2 == null ? null : Integer.valueOf(c2.length));
                    com.netease.nimlib.log.b.d("HighAvailableManager", sb.toString());
                    r6.fcsChannelResponse(r7.getValue(), 200, r4, c2);
                    return;
                }
                com.netease.nimlib.log.b.d("HighAvailableManager", "[SID 6,CID 18] response: fun_id = " + r3 + ", code = " + ((int) aVar2.r()) + ", sn = " + r4 + ", body = null");
                r6.fcsChannelResponse(r7.getValue(), aVar2.r(), r4, null);
            }
        }

        /* compiled from: HighAvailableManager.java */
        /* renamed from: com.netease.nimlib.biz.b.e$3$3 */
        /* loaded from: classes.dex */
        class C00273 extends com.netease.nimlib.biz.g.b {
            final /* synthetic */ int a;
            final /* synthetic */ long b;
            final /* synthetic */ FCSChannelResponseCallback c;
            final /* synthetic */ HAvailableFCSChannelFunID d;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00273(com.netease.nimlib.biz.d.a aVar2, int value22, long j22, FCSChannelResponseCallback fCSChannelResponseCallback22, HAvailableFCSChannelFunID hAvailableFCSChannelFunID22) {
                super(aVar2);
                r3 = value22;
                r4 = j22;
                r6 = fCSChannelResponseCallback22;
                r7 = hAvailableFCSChannelFunID22;
            }

            @Override // com.netease.nimlib.biz.g.b, com.netease.nimlib.biz.g.c
            public void a(com.netease.nimlib.biz.e.a aVar2) {
                if (aVar2.n()) {
                    byte[] b2 = ((h) aVar2).b();
                    StringBuilder sb = new StringBuilder();
                    sb.append("[SID 6,CID 22] response: fun_id = ");
                    sb.append(r3);
                    sb.append(", code = ");
                    sb.append((int) aVar2.r());
                    sb.append(", sn = ");
                    sb.append(r4);
                    sb.append(", body.length = ");
                    sb.append(b2 == null ? null : Integer.valueOf(b2.length));
                    com.netease.nimlib.log.b.d("HighAvailableManager", sb.toString());
                    r6.fcsChannelResponse(r7.getValue(), 200, r4, b2);
                    return;
                }
                com.netease.nimlib.log.b.d("HighAvailableManager", "[SID 6,CID 22] response: fun_id = " + r3 + ", code = " + ((int) aVar2.r()) + ", sn = " + r4 + ", body = null");
                r6.fcsChannelResponse(r7.getValue(), aVar2.r(), r4, null);
            }
        }

        /* compiled from: HighAvailableManager.java */
        /* renamed from: com.netease.nimlib.biz.b.e$3$4 */
        /* loaded from: classes.dex */
        class AnonymousClass4 implements d.a {
            final /* synthetic */ FCSChannelResponseCallback a;
            final /* synthetic */ HAvailableFCSChannelFunID b;
            final /* synthetic */ long c;

            AnonymousClass4(FCSChannelResponseCallback fCSChannelResponseCallback22, HAvailableFCSChannelFunID hAvailableFCSChannelFunID22, long j22) {
                r2 = fCSChannelResponseCallback22;
                r3 = hAvailableFCSChannelFunID22;
                r4 = j22;
            }

            @Override // com.netease.nimlib.m.d.a
            public void a(long j22) {
                com.netease.nimlib.log.b.d("HighAvailableManager", String.format("[SID 6 , CID 23] kGET_SERVER_TIME onSuccess %s", Long.valueOf(j22)));
                com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
                bVar.a(j22);
                byte[] array = bVar.b().array();
                r2.fcsChannelResponse(r3.getValue(), 200, r4, Arrays.copyOf(array, array.length));
            }

            @Override // com.netease.nimlib.m.d.a
            public void a(int i2, String str) {
                com.netease.nimlib.log.b.d("HighAvailableManager", String.format("[SID 6 , CID 23] kGET_SERVER_TIME onFailed %s %s", Integer.valueOf(i2), str));
                r2.fcsChannelResponse(r3.getValue(), i2, r4, null);
            }
        }

        /* compiled from: HighAvailableManager.java */
        /* renamed from: com.netease.nimlib.biz.b.e$3$5 */
        /* loaded from: classes.dex */
        class AnonymousClass5 extends com.netease.nimlib.biz.g.b {
            final /* synthetic */ int a;
            final /* synthetic */ long b;
            final /* synthetic */ FCSChannelResponseCallback c;
            final /* synthetic */ HAvailableFCSChannelFunID d;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass5(com.netease.nimlib.biz.d.a aVar2, int value22, long j22, FCSChannelResponseCallback fCSChannelResponseCallback22, HAvailableFCSChannelFunID hAvailableFCSChannelFunID22) {
                super(aVar2);
                r3 = value22;
                r4 = j22;
                r6 = fCSChannelResponseCallback22;
                r7 = hAvailableFCSChannelFunID22;
            }

            @Override // com.netease.nimlib.biz.g.b, com.netease.nimlib.biz.g.c
            public void a(com.netease.nimlib.biz.e.a aVar2) {
                if (aVar2.n()) {
                    byte[] a2 = ((com.netease.nimlib.biz.e.d.e) aVar2).a();
                    StringBuilder sb = new StringBuilder();
                    sb.append("[SID 6,CID 28] response: fun_id = ");
                    sb.append(r3);
                    sb.append(", code = ");
                    sb.append((int) aVar2.r());
                    sb.append(", sn = ");
                    sb.append(r4);
                    sb.append(", body.length = ");
                    sb.append(a2 == null ? null : Integer.valueOf(a2.length));
                    com.netease.nimlib.log.b.d("HighAvailableManager", sb.toString());
                    r6.fcsChannelResponse(r7.getValue(), 200, r4, a2);
                    return;
                }
                com.netease.nimlib.log.b.d("HighAvailableManager", "[SID 6,CID 28] response: fun_id = " + r3 + ", code = " + ((int) aVar2.r()) + ", sn = " + r4 + ", body = null");
                r6.fcsChannelResponse(r7.getValue(), aVar2.r(), r4, null);
            }
        }

        /* compiled from: HighAvailableManager.java */
        /* renamed from: com.netease.nimlib.biz.b.e$3$6 */
        /* loaded from: classes.dex */
        class AnonymousClass6 extends com.netease.nimlib.biz.g.b {
            final /* synthetic */ int a;
            final /* synthetic */ long b;
            final /* synthetic */ FCSChannelResponseCallback c;
            final /* synthetic */ HAvailableFCSChannelFunID d;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass6(com.netease.nimlib.biz.d.a aVar2, int value22, long j22, FCSChannelResponseCallback fCSChannelResponseCallback22, HAvailableFCSChannelFunID hAvailableFCSChannelFunID22) {
                super(aVar2);
                r3 = value22;
                r4 = j22;
                r6 = fCSChannelResponseCallback22;
                r7 = hAvailableFCSChannelFunID22;
            }

            @Override // com.netease.nimlib.biz.g.b, com.netease.nimlib.biz.g.c
            public void a(com.netease.nimlib.biz.e.a aVar2) {
                if (aVar2.n()) {
                    byte[] a2 = ((com.netease.nimlib.biz.e.d.d) aVar2).a();
                    StringBuilder sb = new StringBuilder();
                    sb.append("[SID 6,CID 29] response: fun_id = ");
                    sb.append(r3);
                    sb.append(", code = ");
                    sb.append((int) aVar2.r());
                    sb.append(", sn = ");
                    sb.append(r4);
                    sb.append(", body.length = ");
                    sb.append(a2 == null ? null : Integer.valueOf(a2.length));
                    com.netease.nimlib.log.b.d("HighAvailableManager", sb.toString());
                    r6.fcsChannelResponse(r7.getValue(), 200, r4, a2);
                    return;
                }
                com.netease.nimlib.log.b.d("HighAvailableManager", "[SID 6,CID 29] response: fun_id = " + r3 + ", code = " + ((int) aVar2.r()) + ", sn = " + r4 + ", body = null");
                r6.fcsChannelResponse(r7.getValue(), aVar2.r(), r4, null);
            }
        }

        /* compiled from: HighAvailableManager.java */
        /* renamed from: com.netease.nimlib.biz.b.e$3$7 */
        /* loaded from: classes.dex */
        class AnonymousClass7 extends com.netease.nimlib.biz.g.b {
            final /* synthetic */ int a;
            final /* synthetic */ long b;
            final /* synthetic */ FCSChannelResponseCallback c;
            final /* synthetic */ HAvailableFCSChannelFunID d;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass7(com.netease.nimlib.biz.d.a aVar2, int value22, long j22, FCSChannelResponseCallback fCSChannelResponseCallback22, HAvailableFCSChannelFunID hAvailableFCSChannelFunID22) {
                super(aVar2);
                r3 = value22;
                r4 = j22;
                r6 = fCSChannelResponseCallback22;
                r7 = hAvailableFCSChannelFunID22;
            }

            @Override // com.netease.nimlib.biz.g.b, com.netease.nimlib.biz.g.c
            public void a(com.netease.nimlib.biz.e.a aVar2) {
                if (aVar2.n()) {
                    byte[] a2 = ((com.netease.nimlib.biz.e.d.c) aVar2).a();
                    StringBuilder sb = new StringBuilder();
                    sb.append("[SID 6,CID 30] response: fun_id = ");
                    sb.append(r3);
                    sb.append(", code = ");
                    sb.append((int) aVar2.r());
                    sb.append(", sn = ");
                    sb.append(r4);
                    sb.append(", body.length = ");
                    sb.append(a2 == null ? null : Integer.valueOf(a2.length));
                    com.netease.nimlib.log.b.d("HighAvailableManager", sb.toString());
                    r6.fcsChannelResponse(r7.getValue(), 200, r4, a2);
                    return;
                }
                com.netease.nimlib.log.b.d("HighAvailableManager", "[SID 6,CID 30] response: fun_id = " + r3 + ", code = " + ((int) aVar2.r()) + ", sn = " + r4 + ", body = null");
                r6.fcsChannelResponse(r7.getValue(), aVar2.r(), r4, null);
            }
        }

        @Override // com.netease.nim.highavailable.HighAvailableFCSCallback
        public void getCustomAuthToken(String str, FCSCustomAuthTokenCallback fCSCustomAuthTokenCallback) {
            com.netease.nimlib.log.b.c("HighAvailableManager", "getCustomAuthToken: url = " + str);
            FcsDownloadAuthStrategy E = com.netease.nimlib.c.E();
            if (E == null) {
                fCSCustomAuthTokenCallback.onToken(null);
            } else {
                fCSCustomAuthTokenCallback.onToken(E.getCustomAuthToken(str));
            }
        }
    }

    /* compiled from: HighAvailableManager.java */
    /* renamed from: com.netease.nimlib.biz.b.e$6 */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass6 {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[HAvailableFCSChannelFunID.values().length];
            a = iArr;
            try {
                iArr[HAvailableFCSChannelFunID.kInvalidFunID.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[HAvailableFCSChannelFunID.kFILE_QUICK_TRANSFER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[HAvailableFCSChannelFunID.kGET_SAFE_URL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[HAvailableFCSChannelFunID.kGET_SERVER_TIME.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[HAvailableFCSChannelFunID.kFCS_POLICY.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                a[HAvailableFCSChannelFunID.kFCS_AUTHORIZATION.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                a[HAvailableFCSChannelFunID.kFCS_BACK_SOURCE_TOKEN.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    @Override // com.netease.nimlib.biz.b.a
    public boolean b() {
        boolean z = com.netease.nimlib.h.a.a().e() && this.f != null;
        com.netease.nimlib.log.b.d("HighAvailableManager", "FCS_INIT isInit  " + z);
        return z;
    }

    @Override // com.netease.nimlib.biz.b.a
    protected void a(d dVar) {
        long a2 = dVar.a();
        com.netease.nimlib.log.b.d("HighAvailableManager", "FCS_DOWNLOAD cancelDownload stopDownload downloadTaskId = " + a2);
        this.f.stopDownload(a2);
        dVar.j();
    }

    @Override // com.netease.nimlib.biz.b.a
    protected void a(HAvailableFCSDownloadType hAvailableFCSDownloadType, int i, int i2, d dVar) {
        String f = dVar.f();
        String g2 = dVar.g();
        com.netease.nimlib.log.b.c("HighAvailableManager", "FCS_DOWNLOAD download url = " + com.netease.nimlib.log.b.a.a(f, com.netease.nimlib.c.i().logDesensitizationConfig) + ",filePath = " + g2 + ",type = " + hAvailableFCSDownloadType + ",thumbnailSizeWidth = " + i + ",thumbnailSizeHeight = " + i2);
        com.netease.nimlib.net.a.a.f l = dVar.l();
        n.a().a(f, m.kResourceDownlaodWayFCS.a());
        long download = this.f.download(f, g2, 30, hAvailableFCSDownloadType, i, i2, new AnonymousClass4(l, dVar, f));
        StringBuilder sb = new StringBuilder();
        sb.append("FCS_DOWNLOAD downloadTaskId =  ");
        sb.append(download);
        com.netease.nimlib.log.b.c("HighAvailableManager", sb.toString());
        dVar.a(download);
    }

    /* compiled from: HighAvailableManager.java */
    /* renamed from: com.netease.nimlib.biz.b.e$4 */
    /* loaded from: classes.dex */
    class AnonymousClass4 implements FCSDownloadCallback {
        final /* synthetic */ com.netease.nimlib.net.a.a.f a;
        final /* synthetic */ d b;
        final /* synthetic */ String c;

        AnonymousClass4(com.netease.nimlib.net.a.a.f fVar, d dVar, String str) {
            this.a = fVar;
            this.b = dVar;
            this.c = str;
        }

        @Override // com.netease.nim.highavailable.FCSDownloadCallback
        public void onDownloadResult(HAvailableFCSErrorCode hAvailableFCSErrorCode, int i, String str) {
            com.netease.nimlib.log.b.d("HighAvailableManager", "FCS_DOWNLOAD onDownloadResult resultCode = " + hAvailableFCSErrorCode.getValue() + ",httpCode = " + i + ",filePath = " + str);
            e.this.a(new Runnable() { // from class: com.netease.nimlib.biz.b.-$$Lambda$e$4$moLdKbE9L9fWNuaZPXEvw_Eyyx0
                private final /* synthetic */ com.netease.nimlib.net.a.a.f f$1;
                private final /* synthetic */ d f$2;
                private final /* synthetic */ String f$3;
                private final /* synthetic */ int f$4;

                public /* synthetic */ $$Lambda$e$4$moLdKbE9L9fWNuaZPXEvw_Eyyx0(com.netease.nimlib.net.a.a.f fVar, d dVar, String str2, int i2) {
                    r2 = fVar;
                    r3 = dVar;
                    r4 = str2;
                    r5 = i2;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    e.AnonymousClass4.a(HAvailableFCSErrorCode.this, r2, r3, r4, r5);
                }
            });
        }

        public static /* synthetic */ void a(HAvailableFCSErrorCode hAvailableFCSErrorCode, com.netease.nimlib.net.a.a.f fVar, d dVar, String str, int i) {
            if (hAvailableFCSErrorCode == HAvailableFCSErrorCode.kOK) {
                if (fVar != null) {
                    fVar.onOK(dVar);
                }
                n.a().b(str, com.netease.nimlib.n.b.h.kSucceed.a());
            } else if (hAvailableFCSErrorCode == HAvailableFCSErrorCode.kError || hAvailableFCSErrorCode == HAvailableFCSErrorCode.kErrorMoveFile) {
                if (fVar != null) {
                    fVar.onFail(dVar, "" + i);
                }
                n.a().b(str, com.netease.nimlib.n.b.h.kFailed.a());
            }
        }

        @Override // com.netease.nim.highavailable.FCSDownloadCallback
        public void onDownloadProgress(long j, long j2) {
            com.netease.nimlib.log.b.c("HighAvailableManager", "FCS_DOWNLOAD onDownloadProgress downloadSize = " + j + ",fileSize = " + j2);
            e.this.a(new Runnable() { // from class: com.netease.nimlib.biz.b.-$$Lambda$e$4$0xUHPzZendWjkdpBviuW2NJqV6g
                private final /* synthetic */ long f$1;
                private final /* synthetic */ com.netease.nimlib.net.a.a.f f$2;
                private final /* synthetic */ long f$3;

                public /* synthetic */ $$Lambda$e$4$0xUHPzZendWjkdpBviuW2NJqV6g(long j22, com.netease.nimlib.net.a.a.f fVar, long j3) {
                    r2 = j22;
                    r4 = fVar;
                    r5 = j3;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    e.AnonymousClass4.a(d.this, r2, r4, r5);
                }
            });
            n.a().a(this.c, j3, j22);
        }

        public static /* synthetic */ void a(d dVar, long j, com.netease.nimlib.net.a.a.f fVar, long j2) {
            if (dVar.i() != 0) {
                if (fVar != null) {
                    fVar.onProgress(dVar, j2);
                    return;
                }
                return;
            }
            dVar.b(j);
            if (fVar == null || j <= 0) {
                return;
            }
            fVar.onGetLength(dVar, j);
            fVar.onStart(dVar);
            fVar.onProgress(dVar, j2);
        }

        @Override // com.netease.nim.highavailable.FCSDownloadCallback
        public void onDownloadSpeed(long j) {
            com.netease.nimlib.log.b.c("HighAvailableManager", "FCS_DOWNLOAD onDownloadSpeed speed = " + j);
        }
    }

    @Override // com.netease.nimlib.biz.b.a
    protected void a(String str, String str2, String str3, int i, String str4, boolean z, FCSUploadCallback fCSUploadCallback, f fVar) {
        fVar.a(this.f.upload(str, str2, str3, i, str4, z, fCSUploadCallback));
    }

    @Override // com.netease.nimlib.biz.b.a
    protected void b(f fVar) {
        this.f.stopUpload(fVar.a());
    }

    /* compiled from: HighAvailableManager.java */
    /* renamed from: com.netease.nimlib.biz.b.e$5 */
    /* loaded from: classes.dex */
    class AnonymousClass5 implements Runnable {
        final /* synthetic */ Runnable a;

        AnonymousClass5(Runnable runnable) {
            r2 = runnable;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                r2.run();
            } catch (Throwable th) {
                com.netease.nimlib.log.b.d("HighAvailableManager", "runOnUIThread exception: " + th.getMessage());
            }
        }
    }

    public void a(Runnable runnable) {
        this.d.post(new Runnable() { // from class: com.netease.nimlib.biz.b.e.5
            final /* synthetic */ Runnable a;

            AnonymousClass5(Runnable runnable2) {
                r2 = runnable2;
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    r2.run();
                } catch (Throwable th) {
                    com.netease.nimlib.log.b.d("HighAvailableManager", "runOnUIThread exception: " + th.getMessage());
                }
            }
        });
    }
}
