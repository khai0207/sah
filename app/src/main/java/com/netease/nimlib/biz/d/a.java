package com.netease.nimlib.biz.d;

import java.nio.ByteBuffer;

/* compiled from: Request.java */
/* loaded from: classes.dex */
public abstract class a {
    private com.netease.nimlib.push.packet.a a;
    private Object b;
    private int c;
    private boolean d;

    public abstract com.netease.nimlib.push.packet.c.b a();

    public abstract byte b();

    public abstract byte c();

    /* compiled from: Request.java */
    /* renamed from: com.netease.nimlib.biz.d.a$a, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static class C0028a {
        public com.netease.nimlib.push.packet.a a;
        public ByteBuffer b;

        public C0028a(com.netease.nimlib.push.packet.a aVar, ByteBuffer byteBuffer) {
            this.a = aVar;
            this.b = byteBuffer;
        }
    }

    public com.netease.nimlib.push.packet.a i() {
        if (this.a == null) {
            this.a = new com.netease.nimlib.push.packet.a(b(), c());
        }
        return this.a;
    }

    public Object j() {
        return this.b;
    }

    public void a(Object obj) {
        this.b = obj;
    }

    public int k() {
        return this.c;
    }

    public void a(int i) {
        this.c = i;
    }

    public boolean l() {
        return this.d;
    }

    protected void a(boolean z) {
        this.d = z;
    }

    public String toString() {
        return "Request [SID " + ((int) b()) + " , CID " + ((int) c()) + "]";
    }
}
