package com.ipaynow.wechatpay.plugin.view;

/* loaded from: classes.dex */
final class q implements Runnable {
    final /* synthetic */ p cV;

    q(p pVar) {
        this.cV = pVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        float f;
        float f2;
        float f3;
        float f4;
        boolean z;
        int i;
        p pVar = this.cV;
        f = pVar.cR;
        pVar.cR = f + 30.0f;
        p pVar2 = this.cV;
        f2 = pVar2.cR;
        if (f2 < 360.0f) {
            f4 = this.cV.cR;
        } else {
            f3 = this.cV.cR;
            f4 = f3 - 360.0f;
        }
        pVar2.cR = f4;
        this.cV.invalidate();
        z = this.cV.cT;
        if (z) {
            p pVar3 = this.cV;
            i = pVar3.cS;
            pVar3.postDelayed(this, i);
        }
    }
}
