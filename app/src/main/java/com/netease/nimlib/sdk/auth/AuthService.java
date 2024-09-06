package com.netease.nimlib.sdk.auth;

import com.netease.nimlib.sdk.AbortableFuture;
import com.netease.nimlib.sdk.InvocationFuture;

/* loaded from: classes.dex */
public interface AuthService {
    void exit();

    String getDeviceID();

    int getKickedClientType();

    int getKickedCustomClientType();

    InvocationFuture<Void> kickOtherClient(OnlineClient onlineClient);

    void killCore();

    void killUI();

    AbortableFuture<LoginInfo> login(LoginInfo loginInfo);

    void logout();

    boolean openLocalCache(String str);
}
