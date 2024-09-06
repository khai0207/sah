package com.ipaynow.wechatpay.plugin.view;

/* loaded from: classes.dex */
public interface IpaynowLoading {
    void dismiss();

    boolean isShowing();

    void setLoadingMsg(String str);

    Object show();
}
