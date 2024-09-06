package com.netease.nimlib.net.b.d;

import android.util.SparseArray;
import com.netease.nimlib.net.b.a.b;
import com.netease.nimlib.net.b.a.c;
import java.io.IOException;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* compiled from: JavaSocket.java */
/* loaded from: classes.dex */
public class a {
    private com.netease.nimlib.net.b.a.a a;
    private SocketChannel b;
    private SelectionKey c;
    private boolean d = false;
    private final List<C0049a> e = new LinkedList();

    public a(com.netease.nimlib.net.b.a.a aVar) {
        this.a = aVar;
    }

    public <T> void a(SparseArray<Object> sparseArray) throws Exception {
        SocketChannel openSocketChannel = SelectorProvider.provider().openSocketChannel();
        this.b = openSocketChannel;
        openSocketChannel.configureBlocking(false);
        b(sparseArray);
    }

    public SelectionKey a(Selector selector, com.netease.nimlib.net.b.a.a aVar) throws ClosedChannelException {
        SelectionKey register = this.b.register(selector, 0, aVar);
        this.c = register;
        return register;
    }

    public boolean a(SocketAddress socketAddress) throws Exception {
        return b(socketAddress);
    }

    public boolean a() {
        SocketChannel socketChannel = this.b;
        return socketChannel != null && socketChannel.isOpen();
    }

    public boolean b() {
        SocketChannel socketChannel = this.b;
        return socketChannel != null && socketChannel.isOpen() && this.b.isConnected();
    }

    public int a(ByteBuffer byteBuffer) throws IOException {
        return this.b.read(byteBuffer);
    }

    public void c() throws IOException {
        this.b.close();
        Iterator<C0049a> it = this.e.iterator();
        while (it.hasNext()) {
            it.next().b.a((Throwable) new b("Throwable in JavaSocket close", com.netease.nimlib.net.b.e.a.a(this.a)));
        }
        this.e.clear();
    }

    public void a(ByteBuffer byteBuffer, c cVar) {
        this.e.add(new C0049a(byteBuffer, cVar));
        d();
    }

    public void d() {
        if (this.d) {
            return;
        }
        this.d = true;
        Iterator<C0049a> it = this.e.iterator();
        while (it.hasNext()) {
            C0049a next = it.next();
            if (!b(next.a, next.b)) {
                break;
            } else {
                it.remove();
            }
        }
        this.d = false;
    }

    public boolean e() {
        return this.d;
    }

    public void f() throws Exception {
        if (!this.b.finishConnect()) {
            throw new Error();
        }
    }

    private boolean b(ByteBuffer byteBuffer, c cVar) {
        boolean z = true;
        if (byteBuffer.remaining() == 0) {
            cVar.a((Object) null);
            return true;
        }
        int i = 9;
        while (true) {
            if (i >= 16) {
                z = false;
                break;
            }
            try {
                if (this.b.write(byteBuffer) == 0) {
                    break;
                }
                if (byteBuffer.remaining() <= 0) {
                    cVar.a((Object) null);
                    return true;
                }
                i++;
            } catch (Throwable th) {
                this.a.a().a(th);
            }
        }
        a(z);
        return false;
    }

    private void b(SparseArray<Object> sparseArray) throws SocketException {
        for (int i = 0; i < sparseArray.size(); i++) {
            a(sparseArray.keyAt(i), sparseArray.valueAt(i));
        }
    }

    private <T> boolean a(int i, Object obj) throws SocketException {
        if (i == 4098) {
            this.b.socket().setReceiveBufferSize(((Integer) obj).intValue());
        } else if (i == 4097) {
            this.b.socket().setSendBufferSize(((Integer) obj).intValue());
        } else if (i == 1) {
            this.b.socket().setTcpNoDelay(((Boolean) obj).booleanValue());
        } else if (i == 8) {
            this.b.socket().setKeepAlive(((Boolean) obj).booleanValue());
        } else if (i == 4) {
            this.b.socket().setReuseAddress(((Boolean) obj).booleanValue());
        } else if (i == 128) {
            int intValue = ((Integer) obj).intValue();
            this.b.socket().setSoLinger(intValue > 0, intValue);
        } else if (i == 3) {
            this.b.socket().setTrafficClass(((Integer) obj).intValue());
        }
        return true;
    }

    protected boolean b(SocketAddress socketAddress) throws Exception {
        try {
            boolean connect = this.b.connect(socketAddress);
            if (!connect) {
                this.c.interestOps(8);
            }
            return connect;
        } catch (Throwable th) {
            try {
                this.b.close();
            } catch (IOException unused) {
            }
            throw th;
        }
    }

    private void a(boolean z) {
        if (z) {
            this.c.interestOps(4);
        } else {
            this.a.c().execute(new Runnable() { // from class: com.netease.nimlib.net.b.d.a.1
                AnonymousClass1() {
                }

                @Override // java.lang.Runnable
                public void run() {
                    a.this.d();
                }
            });
        }
    }

    /* compiled from: JavaSocket.java */
    /* renamed from: com.netease.nimlib.net.b.d.a$1 */
    /* loaded from: classes.dex */
    class AnonymousClass1 implements Runnable {
        AnonymousClass1() {
        }

        @Override // java.lang.Runnable
        public void run() {
            a.this.d();
        }
    }

    /* compiled from: JavaSocket.java */
    /* renamed from: com.netease.nimlib.net.b.d.a$a */
    /* loaded from: classes.dex */
    private static class C0049a {
        ByteBuffer a;
        c b;

        C0049a(ByteBuffer byteBuffer, c cVar) {
            this.a = byteBuffer;
            this.b = cVar;
        }
    }
}
