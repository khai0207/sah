package com.ipaynow.wechatpay.plugin.d.c.b;

/* loaded from: classes.dex */
public abstract class a implements com.ipaynow.wechatpay.plugin.d.c.b.a.a {
    @Override // com.ipaynow.wechatpay.plugin.d.c.b.a.a
    public final void b(com.ipaynow.wechatpay.plugin.d.c.a.a aVar) {
        c(aVar);
        if (aVar.Y == "01") {
            j();
        } else if (aVar.Y == "02") {
            d(aVar);
        } else if (aVar.Y == "00") {
            e(aVar);
        }
    }

    public void c(com.ipaynow.wechatpay.plugin.d.c.a.a aVar) {
    }

    public abstract void d(com.ipaynow.wechatpay.plugin.d.c.a.a aVar);

    public abstract void e(com.ipaynow.wechatpay.plugin.d.c.a.a aVar);

    public abstract void j();
}
