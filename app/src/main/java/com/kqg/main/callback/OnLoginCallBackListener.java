package com.kqg.main.callback;

import com.kqg.main.model.User;

/* loaded from: classes.dex */
public interface OnLoginCallBackListener {
    void onLoginCancel();

    void onLoginSuccess(User user);
}
