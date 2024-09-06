package com.netease.nimlib.net.b.b;

import android.os.SystemClock;
import com.netease.nimlib.biz.e.a;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ByteToMessageDecoder.java */
/* loaded from: classes.dex */
public abstract class a extends com.netease.nimlib.net.b.c.a {
    private ByteBuffer b;
    private long c;

    protected abstract void b(ByteBuffer byteBuffer, List<Object> list) throws Exception;

    protected a() {
    }

    @Override // com.netease.nimlib.net.b.c.a, com.netease.nimlib.net.b.c.d
    public void a(Object obj) {
        if (obj instanceof ByteBuffer) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            ArrayList arrayList = new ArrayList();
            try {
                try {
                    try {
                        ByteBuffer byteBuffer = (ByteBuffer) obj;
                        byteBuffer.flip();
                        if (this.b == null) {
                            this.c = elapsedRealtime;
                            this.b = byteBuffer;
                        } else {
                            if (this.b.capacity() - this.b.limit() < byteBuffer.remaining()) {
                                a(this.a, byteBuffer.remaining());
                            } else {
                                ByteBuffer byteBuffer2 = this.b;
                                ByteBuffer allocate = ByteBuffer.allocate(byteBuffer2.remaining() + byteBuffer.remaining());
                                this.b = allocate;
                                allocate.put(byteBuffer2);
                                this.b.flip();
                            }
                            this.b.position(this.b.limit()).limit(this.b.capacity());
                            this.b.put(byteBuffer);
                            this.b.flip();
                        }
                        a(this.b, arrayList);
                        long j = this.c;
                        ByteBuffer byteBuffer3 = this.b;
                        if (byteBuffer3 != null && byteBuffer3.remaining() <= 0) {
                            this.b = null;
                            this.c = 0L;
                        }
                        int size = arrayList.size();
                        long j2 = j;
                        for (int i = 0; i < size; i++) {
                            Object obj2 = arrayList.get(i);
                            a(obj2, j2, elapsedRealtime);
                            if (i == 0) {
                                j2 = elapsedRealtime;
                            }
                            super.a(obj2);
                        }
                        if (this.c <= 0) {
                            elapsedRealtime = 0;
                        }
                        this.c = elapsedRealtime;
                        return;
                    } catch (Throwable th) {
                        throw new b(th);
                    }
                } catch (b e) {
                    throw e;
                }
            } catch (Throwable th2) {
                long j3 = this.c;
                ByteBuffer byteBuffer4 = this.b;
                if (byteBuffer4 != null && byteBuffer4.remaining() <= 0) {
                    this.b = null;
                    this.c = 0L;
                }
                int size2 = arrayList.size();
                long j4 = j3;
                for (int i2 = 0; i2 < size2; i2++) {
                    Object obj3 = arrayList.get(i2);
                    a(obj3, j4, elapsedRealtime);
                    if (i2 == 0) {
                        j4 = elapsedRealtime;
                    }
                    super.a(obj3);
                }
                if (this.c <= 0) {
                    elapsedRealtime = 0;
                }
                this.c = elapsedRealtime;
                throw th2;
            }
        }
        super.a(obj);
    }

    private void a(Object obj, long j, long j2) {
        if (obj instanceof a.C0029a) {
            a.C0029a c0029a = (a.C0029a) obj;
            if (c0029a.a != null) {
                c0029a.a.b(j);
                c0029a.a.c(j2);
            }
        }
    }

    private void a(com.netease.nimlib.net.b.a.d dVar, int i) {
        ByteBuffer byteBuffer = this.b;
        ByteBuffer allocate = ByteBuffer.allocate(byteBuffer.remaining() + i);
        this.b = allocate;
        allocate.put(byteBuffer);
        this.b.flip();
    }

    @Override // com.netease.nimlib.net.b.c.a, com.netease.nimlib.net.b.c.d
    public void l() {
        super.l();
    }

    @Override // com.netease.nimlib.net.b.c.a, com.netease.nimlib.net.b.c.d
    public void k() {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        try {
            try {
                if (this.b != null) {
                    a(this.b, arrayList);
                    c(this.b, arrayList);
                } else {
                    c(ByteBuffer.allocate(0), arrayList);
                }
            } catch (b e) {
                throw e;
            } catch (Exception e2) {
                throw new b(e2);
            }
        } finally {
            if (this.b != null) {
                this.b = null;
            }
            int size = arrayList.size();
            while (i < size) {
                super.a(arrayList.get(i));
                i++;
            }
            super.k();
        }
    }

    protected void a(ByteBuffer byteBuffer, List<Object> list) {
        while (byteBuffer.remaining() > 0) {
            try {
                int size = list.size();
                int remaining = byteBuffer.remaining();
                b(byteBuffer, list);
                if (size == list.size()) {
                    if (remaining == byteBuffer.remaining()) {
                        return;
                    }
                } else if (remaining == byteBuffer.remaining()) {
                    throw new b(getClass().getSimpleName() + ".decode() did not read anything but decoded a message.");
                }
            } catch (b e) {
                throw e;
            } catch (Throwable th) {
                throw new b(th);
            }
        }
    }

    protected void c(ByteBuffer byteBuffer, List<Object> list) throws Exception {
        b(byteBuffer, list);
    }
}
