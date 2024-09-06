package com.kqg.main.callback;

import com.kqg.main.model.PayResult;

/* loaded from: classes.dex */
public interface OnPayCallBackListener {
    void onPayFail(PayResult payResult);

    void onPaySuccess();

    void onProcess();
}
