package com.ipaynow.wechatpay.plugin.view;

import android.content.Context;

/* loaded from: classes.dex */
public final class f extends k implements IpaynowLoading {
    public f(Context context) {
        super(context);
        a(m.SPIN_INDETERMINATE).ah();
    }

    @Override // com.ipaynow.wechatpay.plugin.view.k
    public final k ag() {
        return super.ag();
    }

    @Override // com.ipaynow.wechatpay.plugin.view.k, com.ipaynow.wechatpay.plugin.view.IpaynowLoading
    public final void dismiss() {
        super.dismiss();
    }

    @Override // com.ipaynow.wechatpay.plugin.view.k, com.ipaynow.wechatpay.plugin.view.IpaynowLoading
    public final boolean isShowing() {
        return super.isShowing();
    }

    @Override // com.ipaynow.wechatpay.plugin.view.IpaynowLoading
    public final void setLoadingMsg(String str) {
        super.A(str);
    }

    @Override // com.ipaynow.wechatpay.plugin.view.IpaynowLoading
    public final /* synthetic */ Object show() {
        return super.ag();
    }
}
