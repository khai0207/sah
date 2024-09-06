package com.netease.nimlib.net.b.c;

import android.os.SystemClock;
import com.netease.nimlib.net.b.a.k;
import java.io.IOException;
import java.nio.channels.CancelledKeyException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.spi.SelectorProvider;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: NioEventLoop.java */
/* loaded from: classes.dex */
public final class f extends h {
    private static final String a = f.class.getSimpleName();
    private boolean e;
    private boolean f;
    private final AtomicBoolean d = new AtomicBoolean();
    private final SelectorProvider c = SelectorProvider.provider();
    private Selector b = o();

    private Selector o() {
        try {
            return this.c.openSelector();
        } catch (IOException e) {
            throw new com.netease.nimlib.net.b.a.b("failed to open a new selector", e);
        }
    }

    public Selector a() {
        return this.b;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:52:?, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void p() {
        /*
            r7 = this;
            boolean r0 = r7.h()
            if (r0 != 0) goto Lf
            com.netease.nimlib.net.b.c.f$1 r0 = new com.netease.nimlib.net.b.c.f$1
            r0.<init>()
            r7.execute(r0)
            return
        Lf:
            java.nio.channels.Selector r0 = r7.b
            if (r0 != 0) goto L14
            return
        L14:
            java.nio.channels.Selector r1 = r7.o()     // Catch: java.lang.Exception -> L7c
        L18:
            java.util.Set r2 = r0.keys()     // Catch: java.util.ConcurrentModificationException -> L18
            java.util.Iterator r2 = r2.iterator()     // Catch: java.util.ConcurrentModificationException -> L18
        L20:
            boolean r3 = r2.hasNext()     // Catch: java.util.ConcurrentModificationException -> L18
            if (r3 == 0) goto L6d
            java.lang.Object r3 = r2.next()     // Catch: java.util.ConcurrentModificationException -> L18
            java.nio.channels.SelectionKey r3 = (java.nio.channels.SelectionKey) r3     // Catch: java.util.ConcurrentModificationException -> L18
            java.lang.Object r4 = r3.attachment()     // Catch: java.util.ConcurrentModificationException -> L18
            boolean r5 = r3.isValid()     // Catch: java.util.ConcurrentModificationException -> L18 java.lang.Exception -> L5b
            if (r5 == 0) goto L20
            java.nio.channels.SelectableChannel r5 = r3.channel()     // Catch: java.util.ConcurrentModificationException -> L18 java.lang.Exception -> L5b
            java.nio.channels.SelectionKey r5 = r5.keyFor(r1)     // Catch: java.util.ConcurrentModificationException -> L18 java.lang.Exception -> L5b
            if (r5 == 0) goto L41
            goto L20
        L41:
            int r5 = r3.interestOps()     // Catch: java.util.ConcurrentModificationException -> L18 java.lang.Exception -> L5b
            r3.cancel()     // Catch: java.util.ConcurrentModificationException -> L18 java.lang.Exception -> L5b
            java.nio.channels.SelectableChannel r3 = r3.channel()     // Catch: java.util.ConcurrentModificationException -> L18 java.lang.Exception -> L5b
            java.nio.channels.SelectionKey r3 = r3.register(r1, r5, r4)     // Catch: java.util.ConcurrentModificationException -> L18 java.lang.Exception -> L5b
            boolean r5 = r4 instanceof com.netease.nimlib.net.b.a.k     // Catch: java.util.ConcurrentModificationException -> L18 java.lang.Exception -> L5b
            if (r5 == 0) goto L20
            r5 = r4
            com.netease.nimlib.net.b.a.k r5 = (com.netease.nimlib.net.b.a.k) r5     // Catch: java.util.ConcurrentModificationException -> L18 java.lang.Exception -> L5b
            r5.a(r3)     // Catch: java.util.ConcurrentModificationException -> L18 java.lang.Exception -> L5b
            goto L20
        L5b:
            r3 = move-exception
            java.lang.String r5 = com.netease.nimlib.net.b.c.f.a     // Catch: java.util.ConcurrentModificationException -> L18
            java.lang.String r6 = "Failed to re-register a Channel to the new Selector."
            com.netease.nimlib.log.b.d(r5, r6, r3)     // Catch: java.util.ConcurrentModificationException -> L18
            boolean r3 = r4 instanceof com.netease.nimlib.net.b.a.k     // Catch: java.util.ConcurrentModificationException -> L18
            if (r3 == 0) goto L20
            com.netease.nimlib.net.b.a.k r4 = (com.netease.nimlib.net.b.a.k) r4     // Catch: java.util.ConcurrentModificationException -> L18
            r4.e()     // Catch: java.util.ConcurrentModificationException -> L18
            goto L20
        L6d:
            r7.b = r1
            r0.close()     // Catch: java.lang.Throwable -> L73
            goto L7b
        L73:
            r0 = move-exception
            java.lang.String r1 = com.netease.nimlib.net.b.c.f.a
            java.lang.String r2 = "Failed to close the old Selector."
            com.netease.nimlib.log.b.d(r1, r2, r0)
        L7b:
            return
        L7c:
            r0 = move-exception
            java.lang.String r1 = com.netease.nimlib.net.b.c.f.a
            java.lang.String r2 = "Failed to create a new Selector."
            com.netease.nimlib.log.b.d(r1, r2, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.netease.nimlib.net.b.c.f.p():void");
    }

    @Override // com.netease.nimlib.net.b.c.h
    protected void b() {
        while (true) {
            this.e = this.d.getAndSet(false);
            try {
                if (e()) {
                    d();
                } else {
                    s();
                    if (this.d.get()) {
                        this.b.wakeup();
                    }
                }
                this.f = false;
                q();
                f();
                if (j()) {
                    r();
                    if (l()) {
                        return;
                    }
                } else {
                    continue;
                }
            } catch (Throwable th) {
                com.netease.nimlib.log.b.d(a, "Unexpected exception in the selector loop.", th);
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException unused) {
                }
            }
        }
    }

    private void q() {
        a(this.b.selectedKeys());
    }

    @Override // com.netease.nimlib.net.b.c.h
    protected void c() {
        try {
            this.b.close();
        } catch (IOException e) {
            com.netease.nimlib.log.b.d(a, "Failed to close a selector.", e);
        }
    }

    private void a(Set<SelectionKey> set) {
        if (set.isEmpty()) {
            return;
        }
        Iterator<SelectionKey> it = set.iterator();
        while (true) {
            SelectionKey next = it.next();
            Object attachment = next.attachment();
            it.remove();
            if (attachment instanceof k) {
                a(next, (k) attachment);
            }
            if (!it.hasNext()) {
                return;
            }
            if (this.f) {
                Set<SelectionKey> selectedKeys = this.b.selectedKeys();
                if (selectedKeys.isEmpty()) {
                    return;
                } else {
                    it = selectedKeys.iterator();
                }
            }
        }
    }

    private static void a(SelectionKey selectionKey, k kVar) {
        if (!selectionKey.isValid()) {
            kVar.e();
            return;
        }
        try {
            int readyOps = selectionKey.readyOps();
            if ((readyOps & 1) != 0 || readyOps == 0) {
                kVar.g();
                if (!kVar.d()) {
                    return;
                }
            }
            if ((readyOps & 4) != 0) {
                kVar.b().d();
            }
            if ((readyOps & 8) != 0) {
                selectionKey.interestOps(selectionKey.interestOps() & (-9));
                kVar.a().b();
            }
        } catch (CancelledKeyException unused) {
            kVar.e();
        }
    }

    private void r() {
        Set<SelectionKey> keys = this.b.keys();
        ArrayList arrayList = new ArrayList(keys.size());
        Iterator<SelectionKey> it = keys.iterator();
        while (it.hasNext()) {
            Object attachment = it.next().attachment();
            if (attachment instanceof k) {
                arrayList.add((k) attachment);
            }
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            ((k) it2.next()).e();
        }
    }

    @Override // com.netease.nimlib.net.b.c.h
    protected void a(boolean z) {
        if (z || !this.d.compareAndSet(false, true)) {
            return;
        }
        this.b.wakeup();
    }

    public void d() throws IOException {
        try {
            this.b.selectNow();
        } finally {
            if (this.d.get()) {
                this.b.wakeup();
            }
        }
    }

    private void s() throws IOException {
        long elapsedRealtime;
        long g;
        Selector selector = this.b;
        int i = 0;
        try {
            elapsedRealtime = SystemClock.elapsedRealtime();
            g = g() + elapsedRealtime;
        } catch (CancelledKeyException e) {
            com.netease.nimlib.log.b.b(a, CancelledKeyException.class.getSimpleName() + " raised by a Selector - JDK bug?", e);
            return;
        }
        while (true) {
            long j = g - elapsedRealtime;
            if (j > 0) {
                i++;
                if (selector.select(j) != 0 || this.e || this.d.get() || e()) {
                    break;
                }
                if (Thread.interrupted()) {
                    com.netease.nimlib.log.b.c(a, "Selector.select() returned prematurely because Thread.currentThread().interrupt() was called. Use NioEventLoop.shutdownGracefully() to shutdown the NioEventLoop.");
                    break;
                }
                if (i >= 512) {
                    com.netease.nimlib.log.b.c(a, "Selector.select() returned prematurely " + i + " times in a row; rebuilding selector.");
                    p();
                    this.b.selectNow();
                    break;
                }
                elapsedRealtime = SystemClock.elapsedRealtime();
            } else if (i == 0) {
                selector.selectNow();
            }
            com.netease.nimlib.log.b.b(a, CancelledKeyException.class.getSimpleName() + " raised by a Selector - JDK bug?", e);
            return;
        }
        i = 1;
        if (i > 3) {
            com.netease.nimlib.log.b.c(a, "Selector.select() returned prematurely " + (i - 1) + " times in a row.");
        }
    }
}
