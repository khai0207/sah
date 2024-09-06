package com.netease.nimlib.biz.b;

/* compiled from: HighAvailableUploadTask.java */
/* loaded from: classes.dex */
public class f extends com.netease.nimlib.net.a.b.f.b {
    private com.netease.nimlib.net.a.b.c<Object> a;
    private long b;
    private Object c;

    @Override // com.netease.nimlib.net.a.b.f.b, java.lang.Runnable
    public void run() {
    }

    public f(Object obj, com.netease.nimlib.net.a.b.c<Object> cVar) {
        super(null, null);
        this.b = 0L;
        this.c = null;
        this.c = obj;
        this.a = cVar;
    }

    public long a() {
        return this.b;
    }

    public void a(long j) {
        this.b = j;
    }

    @Override // com.netease.nimlib.net.a.b.f.b
    public void b() {
        e.d().a(this);
        com.netease.nimlib.net.a.b.c<Object> cVar = this.a;
        if (cVar != null) {
            cVar.a(this.c);
        }
    }

    public String toString() {
        return "HighAvailableUploadTask{uploadCallback=" + this.a + ", taskId=" + this.b + ", fileParam=" + this.c + '}';
    }
}
