package com.talkingdata.sdk;

/* compiled from: td */
/* loaded from: classes.dex */
class cj implements Runnable {
    final /* synthetic */ ci a;

    cj(ci ciVar) {
        this.a = ciVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        long j;
        bu b;
        bu c;
        bu c2;
        try {
            this.a.g = System.currentTimeMillis();
            long j2 = this.a.g - this.a.h;
            j = this.a.i;
            if (j2 > j) {
                this.a.h = this.a.g;
                ci ciVar = this.a;
                b = this.a.b();
                ciVar.d = b;
                if (this.a.d == null) {
                    this.a.a();
                    ci ciVar2 = this.a;
                    c2 = this.a.c();
                    ciVar2.d = c2;
                }
                ci ciVar3 = this.a;
                c = this.a.c();
                ciVar3.e = c;
                if (this.a.d == null || this.a.e == null || this.a.a.a(this.a.d, this.a.e) >= 0.8d) {
                    return;
                }
                this.a.a();
            }
        } catch (Throwable th) {
            ce.postSDKError(th);
        }
    }
}
