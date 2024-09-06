package com.netease.nimlib.net.a.a;

import android.os.Handler;

/* compiled from: HttpDownloadBackgroundListener.java */
/* loaded from: classes.dex */
public class d implements f {
    private final f a;
    private final Handler b = com.netease.nimlib.c.b.a.c().a("HttpDownload");

    public d(f fVar) {
        this.a = fVar;
    }

    @Override // com.netease.nimlib.net.a.a.f
    public void onStart(final e eVar) {
        if (this.a == null) {
            return;
        }
        this.b.post(new Runnable() { // from class: com.netease.nimlib.net.a.a.-$$Lambda$d$ifUa3K53z_vfSvGLCypZueWk1vc
            @Override // java.lang.Runnable
            public final void run() {
                d.this.c(eVar);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void c(e eVar) {
        try {
            this.a.onStart(eVar);
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("HttpDownload", "HttpDownloadBackgroundListener post onStart error", th);
        }
    }

    @Override // com.netease.nimlib.net.a.a.f
    public void onOK(final e eVar) {
        if (this.a == null) {
            return;
        }
        this.b.post(new Runnable() { // from class: com.netease.nimlib.net.a.a.-$$Lambda$d$XWm_aJK11Dj-1euTlRWqNkt0cu8
            @Override // java.lang.Runnable
            public final void run() {
                d.this.b(eVar);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(e eVar) {
        try {
            this.a.onOK(eVar);
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("HttpDownload", "HttpDownloadBackgroundListener post onOK error", th);
        }
    }

    @Override // com.netease.nimlib.net.a.a.f
    public void onFail(final e eVar, final String str) {
        if (this.a == null) {
            return;
        }
        this.b.post(new Runnable() { // from class: com.netease.nimlib.net.a.a.-$$Lambda$d$jUfIdL83FrI-khMGTGmjv9YupUA
            @Override // java.lang.Runnable
            public final void run() {
                d.this.b(eVar, str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(e eVar, String str) {
        try {
            this.a.onFail(eVar, str);
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("HttpDownload", "HttpDownloadBackgroundListener post onFail error", th);
        }
    }

    @Override // com.netease.nimlib.net.a.a.f
    public void onCancel(final e eVar) {
        if (this.a == null) {
            return;
        }
        this.b.post(new Runnable() { // from class: com.netease.nimlib.net.a.a.-$$Lambda$d$dg4d0p2EMu2rHFaz4PD-myiDMoY
            @Override // java.lang.Runnable
            public final void run() {
                d.this.a(eVar);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(e eVar) {
        try {
            this.a.onCancel(eVar);
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("HttpDownload", "HttpDownloadBackgroundListener post onCancel error", th);
        }
    }

    @Override // com.netease.nimlib.net.a.a.f
    public void onProgress(final e eVar, final long j) {
        if (this.a == null) {
            return;
        }
        this.b.post(new Runnable() { // from class: com.netease.nimlib.net.a.a.-$$Lambda$d$jkEwk0FJY_Dzv55_sqRFUrmUx0s
            @Override // java.lang.Runnable
            public final void run() {
                d.this.b(eVar, j);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(e eVar, long j) {
        try {
            this.a.onProgress(eVar, j);
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("HttpDownload", "HttpDownloadBackgroundListener post onProgress error", th);
        }
    }

    @Override // com.netease.nimlib.net.a.a.f
    public void onGetLength(final e eVar, final long j) {
        if (this.a == null) {
            return;
        }
        this.b.post(new Runnable() { // from class: com.netease.nimlib.net.a.a.-$$Lambda$d$z6sKCwzFUfOFWCTIrElGs-lk4XQ
            @Override // java.lang.Runnable
            public final void run() {
                d.this.a(eVar, j);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(e eVar, long j) {
        try {
            this.a.onGetLength(eVar, j);
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("HttpDownload", "HttpDownloadBackgroundListener post onGetLength error", th);
        }
    }

    @Override // com.netease.nimlib.net.a.a.f
    public void onExpire(final e eVar, final String str) {
        if (this.a == null) {
            return;
        }
        this.b.post(new Runnable() { // from class: com.netease.nimlib.net.a.a.-$$Lambda$d$QWRQsc4v5fbT4_AWlxeF4YboByw
            @Override // java.lang.Runnable
            public final void run() {
                d.this.a(eVar, str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(e eVar, String str) {
        try {
            this.a.onExpire(eVar, str);
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("HttpDownload", "HttpDownloadBackgroundListener post onExpire error", th);
        }
    }
}
