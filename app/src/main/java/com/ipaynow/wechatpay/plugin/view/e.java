package com.ipaynow.wechatpay.plugin.view;

import android.content.DialogInterface;

/* loaded from: classes.dex */
final class e implements DialogInterface.OnCancelListener {
    final /* synthetic */ d cq;
    private final /* synthetic */ DialogInterface.OnClickListener cr;

    e(d dVar, DialogInterface.OnClickListener onClickListener) {
        this.cq = dVar;
        this.cr = onClickListener;
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public final void onCancel(DialogInterface dialogInterface) {
        DialogInterface.OnClickListener onClickListener = this.cr;
        if (onClickListener != null) {
            onClickListener.onClick(dialogInterface, 0);
        }
    }
}
