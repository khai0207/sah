package com.ipaynow.wechatpay.plugin.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.KeyEvent;

/* loaded from: classes.dex */
public final class d extends AlertDialog.Builder implements DialogInterface.OnKeyListener {
    public d(Activity activity, String str, String str2, DialogInterface.OnClickListener onClickListener, DialogInterface.OnClickListener onClickListener2) {
        super(activity);
        setTitle(str);
        setMessage(str2);
        setCancelable(false);
        setOnKeyListener(this);
        setOnCancelListener(new e(this, onClickListener2));
        setPositiveButton("确定", onClickListener);
        if (onClickListener2 != null) {
            setNegativeButton("取消", onClickListener2);
        }
    }

    @Override // android.content.DialogInterface.OnKeyListener
    public final boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        com.ipaynow.wechatpay.plugin.e.b.b("keyEvent = " + i);
        if (i != 4) {
            return true;
        }
        dialogInterface.cancel();
        return true;
    }
}
