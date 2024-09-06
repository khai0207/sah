package com.netease.nimlib.push.net.lbs;

import android.os.Handler;
import android.os.HandlerThread;
import com.netease.nimlib.d.g;
import com.netease.nimlib.push.net.lbs.a;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/* compiled from: IPVHelper.java */
/* loaded from: classes.dex */
public class a {
    public static final IPVersion a = IPVersion.IPV4;
    private static IPVersion b = null;

    /* compiled from: IPVHelper.java */
    /* renamed from: com.netease.nimlib.push.net.lbs.a$a */
    /* loaded from: classes.dex */
    public interface InterfaceC0055a {
        void onObtainedSupportive(Boolean bool);
    }

    public static IPVersion a() {
        IPVersion d = d();
        int i = AnonymousClass1.a[d.ordinal()];
        IPVersion iPVersion = null;
        if (i == 1) {
            iPVersion = IPVersion.IPV4;
        } else if (i == 2) {
            iPVersion = IPVersion.IPV6;
        } else if (i == 3) {
            try {
                iPVersion = c();
                com.netease.nimlib.log.b.f("LBSIPVHelper", "detect ip version result = " + iPVersion);
            } catch (InterruptedException unused) {
                com.netease.nimlib.log.b.f("LBSIPVHelper", "detect ip version error");
            }
            if (iPVersion == null) {
                iPVersion = a;
            }
        } else if (i == 4) {
            try {
                iPVersion = b();
                com.netease.nimlib.log.b.f("LBSIPVHelper", "detect ip version with ipv6 first result = " + iPVersion);
            } catch (InterruptedException e) {
                com.netease.nimlib.log.b.f("LBSIPVHelper", "detect ip version with ipv6 first error");
                e.printStackTrace();
            }
        }
        if (iPVersion != null) {
            d = iPVersion;
        }
        b = d;
        com.netease.nimlib.push.e.a(d);
        com.netease.nimlib.log.b.d("LBSIPVHelper", "choose ip protocol version: " + b);
        return b;
    }

    /* compiled from: IPVHelper.java */
    /* renamed from: com.netease.nimlib.push.net.lbs.a$1 */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[IPVersion.values().length];
            a = iArr;
            try {
                iArr[IPVersion.IPV4.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[IPVersion.IPV6.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[IPVersion.ANY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[IPVersion.IPV6_FIRST.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public static synchronized IPVersion b() throws InterruptedException {
        synchronized (a.class) {
            CountDownLatch countDownLatch = new CountDownLatch(1);
            Semaphore semaphore = new Semaphore(1);
            Boolean[] boolArr = {null, null};
            semaphore.acquire();
            a(new InterfaceC0055a() { // from class: com.netease.nimlib.push.net.lbs.-$$Lambda$a$M_LAWnCNGjzfvq4DS0FzCA4K2UM
                private final /* synthetic */ Boolean[] f$0;
                private final /* synthetic */ Semaphore f$1;

                public /* synthetic */ $$Lambda$a$M_LAWnCNGjzfvq4DS0FzCA4K2UM(Boolean[] boolArr2, Semaphore semaphore2) {
                    r1 = boolArr2;
                    r2 = semaphore2;
                }

                @Override // com.netease.nimlib.push.net.lbs.a.InterfaceC0055a
                public final void onObtainedSupportive(Boolean bool) {
                    a.a(r1, r2, bool);
                }
            });
            b(new InterfaceC0055a() { // from class: com.netease.nimlib.push.net.lbs.-$$Lambda$a$5W1AAxw8eRxuglPbxIFPrCnXIqQ
                private final /* synthetic */ Boolean[] f$0;
                private final /* synthetic */ CountDownLatch f$1;

                public /* synthetic */ $$Lambda$a$5W1AAxw8eRxuglPbxIFPrCnXIqQ(Boolean[] boolArr2, CountDownLatch countDownLatch2) {
                    r1 = boolArr2;
                    r2 = countDownLatch2;
                }

                @Override // com.netease.nimlib.push.net.lbs.a.InterfaceC0055a
                public final void onObtainedSupportive(Boolean bool) {
                    a.a(r1, r2, bool);
                }
            });
            countDownLatch2.await(1000L, TimeUnit.MILLISECONDS);
            if (Boolean.TRUE.equals(boolArr2[1])) {
                return IPVersion.IPV6;
            }
            semaphore2.acquire();
            semaphore2.release();
            if (Boolean.TRUE.equals(boolArr2[0])) {
                return IPVersion.IPV4;
            }
            return IPVersion.IPV6;
        }
    }

    public static /* synthetic */ void a(Boolean[] boolArr, Semaphore semaphore, Boolean bool) {
        boolArr[0] = Boolean.valueOf(Boolean.TRUE.equals(bool));
        com.netease.nimlib.log.b.d("LBSIPVHelper", !Boolean.TRUE.equals(boolArr[0]) ? "IPv4 unavailable" : "IPv4 available");
        semaphore.release();
    }

    public static /* synthetic */ void a(Boolean[] boolArr, CountDownLatch countDownLatch, Boolean bool) {
        boolArr[1] = Boolean.valueOf(Boolean.TRUE.equals(bool));
        com.netease.nimlib.log.b.d("LBSIPVHelper", !Boolean.TRUE.equals(boolArr[1]) ? "IPv6 unavailable" : "IPv6 available");
        countDownLatch.countDown();
    }

    public static synchronized IPVersion c() throws InterruptedException {
        synchronized (a.class) {
            CountDownLatch countDownLatch = new CountDownLatch(2);
            Semaphore semaphore = new Semaphore(2);
            Boolean[] boolArr = {null, null};
            com.netease.nimlib.log.b.d("LBSIPVHelper", "start detecting IP Version");
            semaphore.acquire();
            a(new InterfaceC0055a() { // from class: com.netease.nimlib.push.net.lbs.-$$Lambda$a$IJiBBu-ShHLtpjsFQx1-fh1Cz5I
                private final /* synthetic */ Boolean[] f$0;
                private final /* synthetic */ CountDownLatch f$1;
                private final /* synthetic */ Semaphore f$2;

                public /* synthetic */ $$Lambda$a$IJiBBuShHLtpjsFQx1fh1Cz5I(Boolean[] boolArr2, CountDownLatch countDownLatch2, Semaphore semaphore2) {
                    r1 = boolArr2;
                    r2 = countDownLatch2;
                    r3 = semaphore2;
                }

                @Override // com.netease.nimlib.push.net.lbs.a.InterfaceC0055a
                public final void onObtainedSupportive(Boolean bool) {
                    a.b(r1, r2, r3, bool);
                }
            });
            semaphore2.acquire();
            b(new InterfaceC0055a() { // from class: com.netease.nimlib.push.net.lbs.-$$Lambda$a$ubEnSk8XqmbQ-kQrOYeNc05oeTc
                private final /* synthetic */ Boolean[] f$0;
                private final /* synthetic */ CountDownLatch f$1;
                private final /* synthetic */ Semaphore f$2;

                public /* synthetic */ $$Lambda$a$ubEnSk8XqmbQkQrOYeNc05oeTc(Boolean[] boolArr2, CountDownLatch countDownLatch2, Semaphore semaphore2) {
                    r1 = boolArr2;
                    r2 = countDownLatch2;
                    r3 = semaphore2;
                }

                @Override // com.netease.nimlib.push.net.lbs.a.InterfaceC0055a
                public final void onObtainedSupportive(Boolean bool) {
                    a.a(r1, r2, r3, bool);
                }
            });
            countDownLatch2.await(200L, TimeUnit.MILLISECONDS);
            if (!Boolean.TRUE.equals(boolArr2[0]) && !Boolean.TRUE.equals(boolArr2[1])) {
                if (Boolean.FALSE.equals(boolArr2[0]) && Boolean.FALSE.equals(boolArr2[1])) {
                    return null;
                }
                com.netease.nimlib.log.b.d("LBSIPVHelper", "arrive the first time limit, t=200");
                semaphore2.acquire();
                if (!Boolean.TRUE.equals(boolArr2[0]) && !Boolean.TRUE.equals(boolArr2[1])) {
                    semaphore2.acquire();
                }
                if (boolArr2[0] == null && boolArr2[1] == null) {
                    com.netease.nimlib.log.b.d("LBSIPVHelper", "detect timeout, t=1000");
                } else {
                    com.netease.nimlib.log.b.d("LBSIPVHelper", "detect completed, ipv4: " + Boolean.TRUE.equals(boolArr2[0]) + "; ipv6: " + Boolean.TRUE.equals(boolArr2[1]));
                }
                return a(boolArr2[0], boolArr2[1]);
            }
            return a(boolArr2[0], boolArr2[1]);
        }
    }

    public static /* synthetic */ void b(Boolean[] boolArr, CountDownLatch countDownLatch, Semaphore semaphore, Boolean bool) {
        boolArr[0] = Boolean.valueOf(Boolean.TRUE.equals(bool));
        com.netease.nimlib.log.b.d("LBSIPVHelper", !Boolean.TRUE.equals(boolArr[0]) ? "IPv4 unavailable" : "IPv4 available");
        countDownLatch.countDown();
        semaphore.release();
    }

    public static /* synthetic */ void a(Boolean[] boolArr, CountDownLatch countDownLatch, Semaphore semaphore, Boolean bool) {
        boolArr[1] = Boolean.valueOf(Boolean.TRUE.equals(bool));
        com.netease.nimlib.log.b.d("LBSIPVHelper", !Boolean.TRUE.equals(boolArr[1]) ? "IPv6 unavailable" : "IPv6 available");
        countDownLatch.countDown();
        semaphore.release();
    }

    private static IPVersion a(Boolean bool, Boolean bool2) {
        if (Boolean.TRUE.equals(bool)) {
            return Boolean.TRUE.equals(bool2) ? IPVersion.ANY : IPVersion.IPV4;
        }
        if (Boolean.TRUE.equals(bool2)) {
            return IPVersion.IPV6;
        }
        return null;
    }

    private static void a(InterfaceC0055a interfaceC0055a) {
        a(g.c(), "LBSIPVHelper/IPv4", interfaceC0055a);
    }

    private static void b(InterfaceC0055a interfaceC0055a) {
        a(g.d(), "LBSIPVHelper/IPv6", interfaceC0055a);
    }

    private static void a(String str, String str2, InterfaceC0055a interfaceC0055a) {
        HandlerThread handlerThread = new HandlerThread(str2);
        handlerThread.start();
        new Handler(handlerThread.getLooper()).post(new Runnable() { // from class: com.netease.nimlib.push.net.lbs.-$$Lambda$a$UL5QnnL2DOQcKNZ_GNtki5nqemE
            private final /* synthetic */ String f$0;
            private final /* synthetic */ a.InterfaceC0055a f$1;
            private final /* synthetic */ Boolean[] f$2;

            public /* synthetic */ $$Lambda$a$UL5QnnL2DOQcKNZ_GNtki5nqemE(String str3, a.InterfaceC0055a interfaceC0055a2, Boolean[] boolArr) {
                r1 = str3;
                r2 = interfaceC0055a2;
                r3 = boolArr;
            }

            @Override // java.lang.Runnable
            public final void run() {
                a.a(r1, r2, r3);
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:50:0x00e1  */
    /* JADX WARN: Unreachable blocks removed: 2, instructions: 2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void a(java.lang.String r17, com.netease.nimlib.push.net.lbs.a.InterfaceC0055a r18, java.lang.Boolean[] r19) {
        /*
            Method dump skipped, instructions count: 248
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.netease.nimlib.push.net.lbs.a.a(java.lang.String, com.netease.nimlib.push.net.lbs.a$a, java.lang.Boolean[]):void");
    }

    public static IPVersion d() {
        IPVersion iPVersion = com.netease.nimlib.c.l() == null ? null : com.netease.nimlib.c.l().ipProtocolVersion;
        return iPVersion == null ? a : iPVersion;
    }

    public static IPVersion e() {
        com.netease.nimlib.log.b.d("LBSIPVHelper", "last chosen ip version is " + b);
        if (b == IPVersion.IPV4 || b == IPVersion.IPV6 || b == IPVersion.ANY) {
            return b;
        }
        return a();
    }
}
