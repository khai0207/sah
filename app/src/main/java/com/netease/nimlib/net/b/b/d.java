package com.netease.nimlib.net.b.b;

import java.nio.ByteBuffer;

/* compiled from: MessageToByteEncoder.java */
/* loaded from: classes.dex */
public abstract class d<I> extends com.netease.nimlib.net.b.c.b {
    private final Class<? extends I> b;

    protected abstract ByteBuffer b(I i) throws Exception;

    protected d(Class<? extends I> cls) {
        this.b = cls;
    }

    public boolean a(Object obj) throws Exception {
        Class<? extends I> cls = this.b;
        return cls != null && cls.isInstance(obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.netease.nimlib.net.b.c.b, com.netease.nimlib.net.b.c.g
    public void a(Object obj, com.netease.nimlib.net.b.a.c cVar) {
        try {
            if (a(obj)) {
                super.a(b(obj), cVar);
            } else {
                super.a(obj, cVar);
            }
        } catch (c e) {
            throw e;
        } catch (Throwable th) {
            throw new c(th);
        }
    }
}
